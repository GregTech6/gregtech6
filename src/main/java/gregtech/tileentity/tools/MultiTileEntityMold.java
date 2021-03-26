/**
 * Copyright (c) 2021 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.tileentity.tools;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.code.TagData;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.IconsGT;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityCrucible;
import gregapi.tileentity.machines.ITileEntityMold;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMold extends TileEntityBase07Paintable implements ITileEntityEnergy, IFluidHandler, ITileEntityTemperature, ITileEntityMold, ITileEntityServerTickPost, IMTE_SetBlockBoundsBasedOnState, IMTE_OnEntityCollidedWithBlock, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips, IMTE_OnPlaced {
	private static double HEAT_RESISTANCE_BONUS = 1.25;
	
	public static final Map<Integer, OreDictPrefix> MOLD_RECIPES = new HashMap<>();
	
	public OreDictPrefix getMoldRecipe(int aShape) {
		if (aShape == 0) return null;
		OreDictPrefix rRecipe = MOLD_RECIPES.get(aShape & (B[25]-1));
		return rRecipe == null ? OP.nugget : rRecipe;
	}
	
	protected boolean mAcidProof = F, mUseRedstone = F;
	protected byte mAutoPullDirections = 0;
	protected short mDisplay = 0;
	protected int mShape = 0;
	protected long mTemperature = DEF_ENV_TEMP;
	protected OreDictMaterialStack mContent = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.mold")) mShape = aNBT.getInteger("gt.mold");
		if (aNBT.hasKey(NBT_MODE)) mUseRedstone = aNBT.getBoolean(NBT_MODE);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_CONNECTION)) mAutoPullDirections = aNBT.getByte(NBT_CONNECTION);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mTemperature = aNBT.getLong(NBT_TEMPERATURE);
		mContent = OreDictMaterialStack.load(NBT_MATERIALS, aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_CONNECTION, mAutoPullDirections);
		UT.NBT.setBoolean(aNBT, NBT_MODE, mUseRedstone);
		UT.NBT.setNumber(aNBT, NBT_TEMPERATURE, mTemperature);
		UT.NBT.setNumber(aNBT, "gt.mold", mShape);
		if (mContent != null) mContent.save(NBT_MATERIALS, aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT.setInteger("gt.mold", mShape);
		aNBT.setByte(NBT_CONNECTION, mAutoPullDirections);
		UT.NBT.setBoolean(aNBT, NBT_MODE, mUseRedstone);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (mShape == 0)
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MOLD_SELECT));
		else
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MOLD) + " " + getMoldRecipe(mShape).mNameLocal + " " + Chat.WHITE + UT.Code.displayUnits(getMoldRequiredMaterialUnits()) + " Units");
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + getMoldMaxTemperature() + " K)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TAKE_PINCERS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_PO2T.remove(this);
		onUnregisterPost();
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void onServerTickPost(boolean aFirst) {
		long tTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		short tDisplay = mDisplay;
		
		if (mTemperature > tTemperature) mTemperature -= Math.min(5, mTemperature-tTemperature); else if (mTemperature < tTemperature) mTemperature += Math.min(5, tTemperature-mTemperature);
		
		if (mContent != null && mContent.mAmount <= 0 && !slotHas(0)) {
			mContent = null;
			mDisplay = 0;
			mTemperature = tTemperature;
			mInventoryChanged = T;
		}
		
		if (mContent == null) {
			if (mAutoPullDirections != 0 && (mInventoryChanged || SERVER_TIME % 50 == 5 || (mBlockUpdated && mUseRedstone)) && (!mUseRedstone || hasRedstoneIncoming())) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mAutoPullDirections]) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityCrucible && ((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(this, tDelegator.mSideOfTileEntity, tSide)) break;
				}
			}
		}
		
		if (mContent != null) {
			if (mTemperature > mContent.mMaterial.mBoilingPoint || mTemperature > getMoldMaxTemperature()) {
				UT.Sounds.send(SFX.MC_FIZZ, this);
				mContent = null;
				mDisplay = 0;
				slotTrash(0);
				worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.flowing_lava, 1, 3);
				return;
			}
			mDisplay = mContent.mMaterial.mID;
			if (mDisplay < 0) mDisplay = MT.Tc.mID;
			if (mTemperature < mContent.mMaterial.mMeltingPoint) {
				mContent.mMaterial = mContent.mMaterial.mTargetSolidifying.mMaterial;
				mDisplay = (short)~mDisplay;
				if (mContent.mAmount > 0 && !slotHas(0)) {
					OreDictPrefix tPrefix = getMoldRecipe(mShape);
					if (tPrefix == OP.plate && mContent.mMaterial == MT.Glass) tPrefix = OP.plateGem;
					if (tPrefix != null) {
						slot(0, tPrefix.mat(mContent.mMaterial, mContent.mAmount / tPrefix.mAmount));
						mContent.mAmount = 0;
					}
				}
			}
		}
		
		if (tDisplay != mDisplay) updateClientData();
		
		// Moved from onTickResetChecks
		if (hasCovers()) mCovers.resetSync();
		mInventoryChanged = mBlockUpdated = F;
	}
	
	@Override
	public long getTemperatureValue(byte aSide) {
		return mTemperature;
	}
	
	@Override
	public long getTemperatureMax(byte aSide) {
		return getMoldMaxTemperature();
	}
	
	@Override
	public boolean isMoldInputSide(byte aSide) {
		return SIDES_TOP_HORIZONTAL[aSide];
	}
	
	@Override
	public long getMoldMaxTemperature() {
		return (long)(mMaterial.mMeltingPoint * HEAT_RESISTANCE_BONUS);
	}
	
	@Override
	public long getMoldRequiredMaterialUnits() {
		OreDictPrefix tPrefix = getMoldRecipe(mShape);
		if (tPrefix == null) return 0;
		if (tPrefix == OP.nugget) {
			long rAmount = 0;
			for (int i = 0; i < 25; i++) if ((mShape & B[i]) != 0) rAmount += U9;
			return rAmount;
		}
		return tPrefix.mAmount;
	}
	
	@Override
	public long fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide) {
		if (aMaterial == null || aMaterial.mMaterial == null || (!mAcidProof && aMaterial.mMaterial.contains(TD.Properties.ACID))) return 0;
		OreDictPrefix tPrefix = getMoldRecipe(mShape);
		if (tPrefix != null && mContent == null && slot(0) == null && isMoldInputSide(aSide) && aMaterial.mAmount > 0) {
			if (tPrefix == OP.plate && aMaterial.mMaterial == MT.Glass) tPrefix = OP.plateGem;
			if (tPrefix.mat(aMaterial.mMaterial.mTargetSolidifying.mMaterial, 1) != null) {
				long tRequiredAmount = getMoldRequiredMaterialUnits(), rAmount = UT.Code.units(tRequiredAmount, U, aMaterial.mMaterial.mTargetSolidifying.mAmount, T);
				if (aMaterial.mAmount >= rAmount) {
					mContent = OM.stack(aMaterial.mMaterial, tRequiredAmount);
					mTemperature = aTemperature;
					return rAmount;
				}
			}
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (SIDES_TOP_HORIZONTAL[aSide]) {
			if (isServerSide() && !pickUpItem(aPlayer, T) && SIDES_TOP[aSide]) {
				byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
				if (SIDES_VERTICAL[tSide]) {
					for (byte tSide2 : ALL_SIDES_HORIZONTAL) {
						if (isMoldInputSide(tSide2)) {
							DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide2);
							if (tDelegator.mTileEntity instanceof ITileEntityCrucible) {
								((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(this, tDelegator.mSideOfTileEntity, tSide2);
								return T;
							}
						}
					}
				} else {
					if (isMoldInputSide(tSide)) {
						DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
						if (tDelegator.mTileEntity instanceof ITileEntityCrucible) {
							((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(this, tDelegator.mSideOfTileEntity, tSide);
							return T;
						}
					}
				}
			}
			return T;
		}
		return F;
	}
	
	public boolean pickUpItem(EntityPlayer aPlayer, boolean aCauseDamage) {
		ItemStack tOutputStack = slot(0);
		if (tOutputStack != null) {
			OreDictItemData tData = OM.anyassociation(tOutputStack);
			if (tData != null) for (Achievement tAchievement : tData.mMaterial.mMaterial.mAchievementsForCreation) aPlayer.triggerAchievement(tAchievement);
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (aStack == null) {
				aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, tOutputStack);
				slotKill(0);
				if (aCauseDamage) UT.Entities.applyTemperatureDamage(aPlayer, mTemperature, 1);
				return T;
			}
			if (ST.equal(aStack, tOutputStack) && aStack.stackSize < aStack.getMaxStackSize()) {
				int tDifference = Math.min(tOutputStack.stackSize, aStack.getMaxStackSize() - aStack.stackSize);
				aStack.stackSize+=tDifference;
				decrStackSize(0, tDifference);
				if (aCauseDamage) UT.Entities.applyTemperatureDamage(aPlayer, mTemperature, 1);
				return T;
			}
			if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(0), F)) {
				if (aCauseDamage) UT.Entities.applyTemperatureDamage(aPlayer, mTemperature, 1);
				slotKill(0);
				return T;
			}
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {if (aChatReturn != null) aChatReturn.add("Temperature: " + mTemperature + "K"); return 10000;}
		if (aTool.equals(TOOL_chisel) && mContent == null && slot(0) == null && aHitX > PX_P[2] && aHitX < PX_N[2] && aHitZ > PX_P[2] && aHitZ < PX_N[2]) {
			int tBit = B[((int)(5 * (aHitX - PX_P[2]) / PX_P[12]))*5+(int)(5 * (aHitZ - PX_P[2]) / PX_P[12])];
			if ((mShape & tBit) == 0) {
				UT.Sounds.send(SFX.MC_DIG_ROCK, 1.0F, -1.0F, this);
				mShape |= tBit;
				updateClientData();
				return 10000;
			}
		}
		if (aTool.equals(TOOL_softhammer)) {
			mUseRedstone = F;
			mAutoPullDirections = 0;
			if (aChatReturn != null) aChatReturn.add("Crucible Auto-Input: OFF & NO REDSTONE");
			return 10000;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			if (SIDES_TOP[aSide]) {
				byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
				if (SIDES_HORIZONTAL[tSide]) {
					mAutoPullDirections ^= SBIT[tSide];
					if (aChatReturn != null) aChatReturn.add(FACE_CONNECTED[tSide][mAutoPullDirections] ? "Crucible Auto-Input: ON" : "Crucible Auto-Input: OFF");
					return 10000;
				}
				mUseRedstone = !mUseRedstone;
				if (aChatReturn != null) aChatReturn.add(mUseRedstone ? "Crucible Auto-Input: REDSTONE" + (mAutoPullDirections == 0 ? " (WARNING: No Direction Selected!)" : "") : "Crucible Auto-Input: NO REDSTONE");
				return 10000;
			}
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (mShape == 0)
				aChatReturn.add(Chat.CYAN       + "Use a Chisel in order to select the Shape of the Mold");
				else
				aChatReturn.add(Chat.CYAN       + "This Mold produces " + getMoldRecipe(mShape).mNameLocal);
				if (SIDES_TOP[aSide]) {
					byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
					if (SIDES_HORIZONTAL[tSide]) aChatReturn.add(FACE_CONNECTED[tSide][mAutoPullDirections] ? "Crucible Auto-Input: ON" : "Crucible Auto-Input: OFF");
				}
				aChatReturn.add(mUseRedstone ? "Crucible Auto-Input: REDSTONE" + (mAutoPullDirections == 0 ? " (WARNING: No Direction Selected!)" : "") : "Crucible Auto-Input: NO REDSTONE");
			}
			return 1;
		}
		if (aTool.equals(TOOL_pincers)) {
			if (aPlayer instanceof EntityPlayer && pickUpItem((EntityPlayer)aPlayer, F)) {
				return 1000;
			}
		}
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_screwdriver) || aTool.equals(TOOL_rotator)) {
			int tResult = 0;
			if ((mShape & B[ 0]) != 0) {tResult |= B[ 4];}
			if ((mShape & B[ 1]) != 0) {tResult |= B[ 9];}
			if ((mShape & B[ 2]) != 0) {tResult |= B[14];}
			if ((mShape & B[ 3]) != 0) {tResult |= B[19];}
			if ((mShape & B[ 4]) != 0) {tResult |= B[24];}
			if ((mShape & B[ 5]) != 0) {tResult |= B[ 3];}
			if ((mShape & B[ 6]) != 0) {tResult |= B[ 8];}
			if ((mShape & B[ 7]) != 0) {tResult |= B[13];}
			if ((mShape & B[ 8]) != 0) {tResult |= B[18];}
			if ((mShape & B[ 9]) != 0) {tResult |= B[23];}
			if ((mShape & B[10]) != 0) {tResult |= B[ 2];}
			if ((mShape & B[11]) != 0) {tResult |= B[ 7];}
			if ((mShape & B[12]) != 0) {tResult |= B[12];}
			if ((mShape & B[13]) != 0) {tResult |= B[17];}
			if ((mShape & B[14]) != 0) {tResult |= B[22];}
			if ((mShape & B[15]) != 0) {tResult |= B[ 1];}
			if ((mShape & B[16]) != 0) {tResult |= B[ 6];}
			if ((mShape & B[17]) != 0) {tResult |= B[11];}
			if ((mShape & B[18]) != 0) {tResult |= B[16];}
			if ((mShape & B[19]) != 0) {tResult |= B[21];}
			if ((mShape & B[20]) != 0) {tResult |= B[ 0];}
			if ((mShape & B[21]) != 0) {tResult |= B[ 5];}
			if ((mShape & B[22]) != 0) {tResult |= B[10];}
			if ((mShape & B[23]) != 0) {tResult |= B[15];}
			if ((mShape & B[24]) != 0) {tResult |= B[20];}
			mShape = tResult;
			updateClientData();
			return 10000;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		return T;
	}
	
	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mContent);
		return super.breakBlock();
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {/* Needed to be delayed. */}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(T, UT.Code.toByteS(mDisplay, 0), UT.Code.toByteS(mDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), UT.Code.toByteI(mShape, 0), UT.Code.toByteI(mShape, 1), UT.Code.toByteI(mShape, 2), UT.Code.toByteI(mShape, 3));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplay = UT.Code.combine(aData[0], aData[1]);
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
		if (aData.length > 5) mShape = UT.Code.combine(aData[5], aData[6], aData[7], aData[8]);
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING), F);
		short tDisplay = (short)(mDisplay<0 ? ~mDisplay : mDisplay);
		if (tDisplay != 0 && UT.Code.exists(tDisplay, OreDictMaterial.MATERIAL_ARRAY)) {
			OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[tDisplay];
			if (tMaterial == MT.Lava) {
				mTextureMolten = BlockTextureCopied.get(Blocks.lava);
			} else if (tMaterial == MT.H2O) {
				mTextureMolten = BlockTextureCopied.get(Blocks.water);
			} else if (mDisplay < 0) {
				if (tMaterial == MT.Stone) {
					mTextureMolten = BlockTextureCopied.get(Blocks.stone);
				} else if (tMaterial == MT.Glass) {
					mTextureMolten = BlockTextureCopied.get(Blocks.glass);
				} else if (tMaterial == MT.Au) {
					mTextureMolten = BlockTextureCopied.get(Blocks.gold_block);
				} else if (tMaterial == MT.Fe) {
					mTextureMolten = BlockTextureCopied.get(Blocks.iron_block);
				} else if (tMaterial == MT.Redstone) {
					mTextureMolten = BlockTextureCopied.get(Blocks.redstone_block);
				} else if (tMaterial == MT.Obsidian) {
					mTextureMolten = BlockTextureCopied.get(Blocks.obsidian);
				} else if (tMaterial == MT.Glowstone) {
					mTextureMolten = BlockTextureCopied.get(Blocks.glowstone);
				} else if (tMaterial == MT.Ice) {
					mTextureMolten = BlockTextureCopied.get(Blocks.packed_ice);
				} else if (tMaterial == MT.Snow) {
					mTextureMolten = BlockTextureCopied.get(Blocks.snow);
				} else {
					mTextureMolten = BlockTextureDefault.get(tMaterial, OP.blockSolid.mIconIndexBlock, STATE_SOLID, tMaterial.contains(TD.Properties.GLOWING));
				}
			} else {
				mTextureMolten = BlockTextureDefault.get(tMaterial, IconsGT.INDEX_BLOCK_MOLTEN, STATE_LIQUID, T, F);
			}
		} else {
			mTextureMolten = null;
		}
		return MOLD_BOUNDS.length;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			box(aBlock, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[mDisplay<0?14:13]-0.005F, PX_N[ 1]);
		} else {
			box(aBlock, MOLD_BOUNDS[aRenderPass][0], MOLD_BOUNDS[aRenderPass][1], MOLD_BOUNDS[aRenderPass][2], MOLD_BOUNDS[aRenderPass][3], MOLD_BOUNDS[aRenderPass][4], MOLD_BOUNDS[aRenderPass][5]); return T;
		}
		return T;
	}
	
	protected static final float[][] MOLD_BOUNDS = {
		  {PX_P[ 1], PX_P[ 2], PX_P[ 1], PX_N[ 1], PX_N[ 1]       , PX_N[ 1]}
		, {PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[15]       , PX_N[ 0]}
		, {PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[12]+0.001F, PX_N[ 0]}
		, {PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[12]       , PX_N[ 0]}
		, {PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[12]+0.001F, PX_N[ 0]}
		, {PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[12]       , PX_N[14]}
		
		, {PX_P[ 6], PX_P[ 4], PX_P[ 0], PX_P[ 7], PX_P[ 6], PX_P[ 2]}
		, {PX_P[ 9], PX_P[ 4], PX_P[ 0], PX_P[10], PX_P[ 6], PX_P[ 2]}
		, {PX_P[ 6], PX_P[ 6], PX_P[ 0], PX_P[10], PX_P[ 7], PX_P[ 2]}
		, {PX_P[ 6], PX_P[ 4], PX_P[14], PX_P[ 7], PX_P[ 6], PX_P[16]}
		, {PX_P[ 9], PX_P[ 4], PX_P[14], PX_P[10], PX_P[ 6], PX_P[16]}
		, {PX_P[ 6], PX_P[ 6], PX_P[14], PX_P[10], PX_P[ 7], PX_P[16]}
		, {PX_P[ 0], PX_P[ 4], PX_P[ 6], PX_P[ 2], PX_P[ 6], PX_P[ 7]}
		, {PX_P[ 0], PX_P[ 4], PX_P[ 9], PX_P[ 2], PX_P[ 6], PX_P[10]}
		, {PX_P[ 0], PX_P[ 6], PX_P[ 6], PX_P[ 2], PX_P[ 7], PX_P[10]}
		, {PX_P[14], PX_P[ 4], PX_P[ 6], PX_P[16], PX_P[ 6], PX_P[ 7]}
		, {PX_P[14], PX_P[ 4], PX_P[ 9], PX_P[16], PX_P[ 6], PX_P[10]}
		, {PX_P[14], PX_P[ 6], PX_P[ 6], PX_P[16], PX_P[ 7], PX_P[10]}
		
		, {PX_P[ 2]                      , PX_P[ 0], PX_P[ 2]                      , PX_N[ 2] - 4 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 4 * PX_P[12] / 5.0F}
		, {PX_P[ 2]                      , PX_P[ 0], PX_P[ 2] +     PX_P[12] / 5.0F, PX_N[ 2] - 4 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 3 * PX_P[12] / 5.0F}
		, {PX_P[ 2]                      , PX_P[ 0], PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_N[ 2] - 4 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 2 * PX_P[12] / 5.0F}
		, {PX_P[ 2]                      , PX_P[ 0], PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_N[ 2] - 4 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] -     PX_P[12] / 5.0F}
		, {PX_P[ 2]                      , PX_P[ 0], PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_N[ 2] - 4 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2]                      }
		
		, {PX_P[ 2] +     PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2]                      , PX_N[ 2] - 3 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 4 * PX_P[12] / 5.0F}
		, {PX_P[ 2] +     PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] +     PX_P[12] / 5.0F, PX_N[ 2] - 3 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 3 * PX_P[12] / 5.0F}
		, {PX_P[ 2] +     PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_N[ 2] - 3 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 2 * PX_P[12] / 5.0F}
		, {PX_P[ 2] +     PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_N[ 2] - 3 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] -     PX_P[12] / 5.0F}
		, {PX_P[ 2] +     PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_N[ 2] - 3 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2]                      }
		
		, {PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2]                      , PX_N[ 2] - 2 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 4 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] +     PX_P[12] / 5.0F, PX_N[ 2] - 2 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 3 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_N[ 2] - 2 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 2 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_N[ 2] - 2 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] -     PX_P[12] / 5.0F}
		, {PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_N[ 2] - 2 * PX_P[12] / 5.0F, PX_N[13], PX_N[ 2]                      }
		
		, {PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2]                      , PX_N[ 2] -     PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 4 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] +     PX_P[12] / 5.0F, PX_N[ 2] -     PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 3 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_N[ 2] -     PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] - 2 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_N[ 2] -     PX_P[12] / 5.0F, PX_N[13], PX_N[ 2] -     PX_P[12] / 5.0F}
		, {PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_N[ 2] -     PX_P[12] / 5.0F, PX_N[13], PX_N[ 2]                      }
		
		, {PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2]                      , PX_N[ 2]                      , PX_N[13], PX_N[ 2] - 4 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] +     PX_P[12] / 5.0F, PX_N[ 2]                      , PX_N[13], PX_N[ 2] - 3 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 2 * PX_P[12] / 5.0F, PX_N[ 2]                      , PX_N[13], PX_N[ 2] - 2 * PX_P[12] / 5.0F}
		, {PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 3 * PX_P[12] / 5.0F, PX_N[ 2]                      , PX_N[13], PX_N[ 2] -     PX_P[12] / 5.0F}
		, {PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_P[ 0], PX_P[ 2] + 4 * PX_P[12] / 5.0F, PX_N[ 2]                      , PX_N[13], PX_N[ 2]                      }
	};
	
	protected ITexture mTexture, mTextureMolten;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDES_TOP[aSide]?mTextureMolten:null;
		case  1: return SIDES_VERTICAL[aSide]?mTexture:null;
		case  2:
		case  4: return SIDES_BOTTOM[aSide]||SIDES_AXIS_Z[aSide]?null:mTexture;
		case  3:
		case  5: return SIDES_BOTTOM[aSide]||SIDES_AXIS_X[aSide]?null:mTexture;
		case  8:
		case 11:
		case 14:
		case 17: return mTexture;
		case  6:
		case  7:
		case  9:
		case 10:
		case 12:
		case 13:
		case 15:
		case 16: return SIDES_HORIZONTAL[aSide]?mTexture:null;
		}
		
		aRenderPass -= 18;
		if ((mShape & B[aRenderPass]) != 0 || SIDES_BOTTOM[aSide]) return null;
		switch (aSide) {
		case SIDE_X_POS: return aRenderPass     < 20 && (mShape & B[aRenderPass+5]) != 0 ? mTexture : null;
		case SIDE_X_NEG: return aRenderPass     >= 5 && (mShape & B[aRenderPass-5]) != 0 ? mTexture : null;
		case SIDE_Z_POS: return aRenderPass % 5 != 4 && (mShape & B[aRenderPass+1]) != 0 ? mTexture : null;
		case SIDE_Z_NEG: return aRenderPass % 5 != 0 && (mShape & B[aRenderPass-1]) != 0 ? mTexture : null;
		}
		return mTexture;
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override
	public void onEntityCollidedWithBlock(Entity aEntity) {
		if (mTemperature > C + 40) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mTemperature / 100.0F));
	}
	
	@Override
	public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return (SIDES_BOTTOM_HORIZONTAL[aSide] || getClass() != MultiTileEntityMold.class) && super.checkObstruction(aPlayer, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[14], PX_N[ 2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[13], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[13], PX_N[ 0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_P[10]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == TD.Energy.CU;}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == TD.Energy.CU;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return aEnergyType == TD.Energy.CU;}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {mTemperature = Math.max(mTemperature - Math.abs(aAmount * aSize), Math.min(200, WD.envTemp(worldObj, xCoord, yCoord, zCoord)));} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return mTemperature - 200;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 16;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.CU.AS_LIST;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	private static final int[] ACCESSIBLE_SLOTS = UT.Code.getAscendingArray(1);
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return mTemperature - 50 < WD.envTemp(worldObj, xCoord, yCoord, zCoord);}
	
	@Override
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		return aFluid != null && fill(aDirection, FL.make(aFluid, Integer.MAX_VALUE), F) > 0;
	}
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null || aFluid.amount <= 0 || FL.gas(aFluid) || mContent != null || slotHas(0)) return 0;
		OreDictMaterialStack aFluidRatio = OreDictMaterial.FLUID_MAP.get(aFluid.getFluid().getName()), aMaterial = null;
		if (aFluidRatio == null || aFluidRatio.mAmount <= 0) return 0;
		aMaterial = OM.stack(aFluidRatio.mMaterial, UT.Code.units(aFluid.amount, aFluidRatio.mAmount, U, F));
		if (aMaterial == null || aMaterial.mAmount <= 0) return 0;
		OreDictPrefix tPrefix = getMoldRecipe(mShape);
		if (tPrefix == OP.plate && aMaterial.mMaterial == MT.Glass) tPrefix = OP.plateGem;
		if (tPrefix == null || tPrefix.mat(aMaterial.mMaterial.mTargetSolidifying.mMaterial, 1) == null) return 0;
		long tRequiredAmount = getMoldRequiredMaterialUnits();
		long rAmount = UT.Code.units(tRequiredAmount, U, aMaterial.mMaterial.mTargetSolidifying.mAmount, T);
		if (aMaterial.mAmount >= rAmount) {
			if (aDoFill) {
				mContent = OM.stack(aMaterial.mMaterial, tRequiredAmount);
				mTemperature = Math.max(FL.temperature(aFluid), aMaterial.mMaterial.mMeltingPoint);
			}
			return UT.Code.bindInt(UT.Code.units(rAmount, U, aFluidRatio.mAmount, T));
		}
		return 0;
	}
	
	@Override public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean aDoDrain) {return NF;}
	@Override public FluidStack drain(ForgeDirection aDirection, int aAmountToDrain, boolean aDoDrain) {return NF;}
	@Override public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {return F;}
	@Override public FluidTankInfo[] getTankInfo(ForgeDirection aDirection) {return L1_FLUIDTANKINFO_DUMMY;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.mold";}
	
	static {
		Map<Integer, OreDictPrefix> TEMP_MOLD_RECIPES = new HashMap<>();
		
		TEMP_MOLD_RECIPES.put(0b0_00100_11111_01110_01010_00000, OP.toolHeadBuilderwand);
		TEMP_MOLD_RECIPES.put(0b0_00000_00100_11111_01110_01010, OP.toolHeadBuilderwand);
		
		TEMP_MOLD_RECIPES.put(
		B[ 0]|B[ 1]|B[ 2]|B[ 3]|
		B[ 5]|B[ 6]|B[ 7]|B[ 8]|
		B[10]|B[11]|B[12]|B[13]|B[14]|
		B[15]|B[16]|B[17]|B[18]|
		B[20]|B[21]|B[22]|B[23]
		, OP.toolHeadRawPlow);
		
		TEMP_MOLD_RECIPES.put(
								B[ 4]|
						  B[ 8]|
					B[12]|
			  B[16]|
		B[20]
		, OP.stickLong);
		
		TEMP_MOLD_RECIPES.put(
		B[ 0]|B[ 1]|B[ 2]|B[ 3]|B[ 4]|
		B[ 5]|B[ 6]|B[ 7]|B[ 8]|B[ 9]|
		B[10]|B[11]|B[12]|B[13]|B[14]|
		B[15]|B[16]|B[17]|B[18]|B[19]|
		B[20]|B[21]|B[22]|B[23]|B[24]
		, OP.plate);
		
		TEMP_MOLD_RECIPES.put(
		B[ 0]|B[ 1]|B[ 2]|    B[ 4]|
		B[ 5]|B[ 6]|B[ 7]|    B[ 9]|
		B[10]|B[11]|B[12]|    B[14]|
							  B[19]|
		B[20]|B[21]|B[22]
		, OP.casingSmall);
		
		TEMP_MOLD_RECIPES.put(
		B[ 0]|      B[ 2]|      B[ 4]|
			  B[ 6]|B[ 7]|B[ 8]|
		B[10]|B[11]|      B[13]|B[14]|
			  B[16]|B[17]|B[18]|
		B[20]|      B[22]|      B[24]
		, OP.gearGt);
		
		TEMP_MOLD_RECIPES.put(
			  B[ 1]|      B[ 3]|
		B[ 5]|B[ 6]|B[ 7]|B[ 8]|B[ 9]|
			  B[11]|      B[13]|
		B[15]|B[16]|B[17]|B[18]|B[19]|
			  B[21]|      B[23]
		, OP.gearGtSmall);
		
		for (int i = 0; i < 3; i++) {
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|B[i  + 2]|
			B[i  + 5]|B[i  + 6]|B[i  + 7]|
			B[i  +10]|B[i  +11]|B[i  +12]|
			B[i  +15]|B[i  +16]|B[i  +17]|
			B[i  +20]|B[i  +21]|B[i  +22]
			, OP.ingot);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|B[i  + 2]|
			B[i  + 5]|B[i  + 6]|
			B[i  +10]|B[i  +11]|
			B[i  +15]|B[i  +16]|
			B[i  +20]|B[i  +21]|B[i  +22]
			, OP.toolHeadRawAxeDouble);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|B[i  + 2]|
			B[i  + 5]|B[i  + 6]|B[i  + 7]|
			B[i  +10]|        B[i  +12]|
			B[i  +15]|B[i  +16]|B[i  +17]|
			B[i  +20]|B[i  +21]|B[i  +22]
			, OP.toolHeadHammer);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|
			B[i  + 5]|
			B[i  +10]|
			B[i  +15]|
			B[i  +20]
			, OP.stick);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|B[i  + 2]|
					  B[i  + 6]|
					  B[i  +11]|
					  B[i  +16]|
					  B[i  +21]
			, OP.toolHeadRawChisel);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|B[i  + 2]|
			B[i  + 5]|B[i  + 6]|B[i  + 7]|
			B[i  +10]|B[i  +11]|B[i  +12]|
					  B[i  +16]|
					  B[i  +21]
			, OP.toolHeadFile);
			
			TEMP_MOLD_RECIPES.put(
					  B[i  + 1]|
			B[i  + 5]|B[i  + 6]|B[i  + 7]|
			B[i  +10]|B[i  +11]|B[i  +12]|
			B[i  +15]|B[i  +16]|B[i  +17]|
			B[i  +20]|B[i  +21]|B[i  +22]
			, OP.toolHeadRawSword);
			
			for (int j = 0; j < 4; j++) {
				TEMP_MOLD_RECIPES.put(
							  B[i  +j*5+ 1]|B[i  +j*5+ 2]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]
				, OP.toolHeadRawHoe);
			}
			
			for (int j = 0; j < 3; j++) {
				TEMP_MOLD_RECIPES.put(
							  B[i  +j*5+ 1]|
							  B[i  +j*5+ 6]|
				B[i  +j*5+10]|B[i  +j*5+11]|B[i  +j*5+12]
				, OP.toolHeadRawArrow);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|B[i  +j*5+ 1]|B[i  +j*5+ 2]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]|
				B[i  +j*5+10]
				, OP.toolHeadRawAxe);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|B[i  +j*5+ 1]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]
				, OP.chunkGt);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|B[i  +j*5+ 1]|B[i  +j*5+ 2]|
				B[i  +j*5+ 5]|            B[i  +j*5+ 7]|
				B[i  +j*5+10]|B[i  +j*5+11]|B[i  +j*5+12]
				, OP.ring);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|B[i  +j*5+ 1]|B[i  +j*5+ 2]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]|
				B[i  +j*5+10]|B[i  +j*5+11]|B[i  +j*5+12]
				, OP.plateTiny);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|
				B[i  +j*5+ 5]
				, OP.bolt);
			}
			
			for (int j = 0; j < 2; j++) {
				TEMP_MOLD_RECIPES.put(
							  B[i  +j*5+ 1]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]|
				B[i  +j*5+10]|B[i  +j*5+11]|B[i  +j*5+12]|
				B[i  +j*5+15]|B[i  +j*5+16]|B[i  +j*5+17]
				, OP.toolHeadRawShovel);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|B[i  +j*5+ 1]|B[i  +j*5+ 2]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]|
				B[i  +j*5+10]|B[i  +j*5+11]|B[i  +j*5+12]|
				B[i  +j*5+15]|            B[i  +j*5+17]
				, OP.toolHeadRawSpade);
				
				TEMP_MOLD_RECIPES.put(
							  B[i  +j*5+ 1]|
				B[i  +j*5+ 5]|B[i  +j*5+ 6]|B[i  +j*5+ 7]|
				B[i  +j*5+10]|B[i  +j*5+11]|
				B[i  +j*5+15]|B[i  +j*5+16]|B[i  +j*5+17]
				, OP.toolHeadRawUniversalSpade);
				
				TEMP_MOLD_RECIPES.put(
				B[i  +j*5+ 0]|
				B[i  +j*5+ 5]|
				B[i  +j*5+10]|
				B[i  +j*5+15]
				, OP.toolHeadScrewdriver);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			TEMP_MOLD_RECIPES.put(
					  B[i  + 1]|
			B[i  + 5]|
			B[i  +10]|
			B[i  +15]|
					  B[i  +21]
			, OP.toolHeadRawPickaxe);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|
			B[i  + 5]|B[i  + 6]|
			B[i  +10]|B[i  +11]|
			B[i  +15]|B[i  +16]|
			B[i  +20]|B[i  +21]
			, OP.toolHeadRawSaw);
			
			TEMP_MOLD_RECIPES.put(
			B[i  + 0]|B[i  + 1]|
			B[i  + 5]|B[i  + 6]|
			B[i  +10]|B[i  +11]|
			B[i  +15]|B[i  +16]|
					  B[i  +21]
			, OP.toolHeadRawSense);
			
		}
		
		for (Entry<Integer, OreDictPrefix> tEntry : TEMP_MOLD_RECIPES.entrySet()) {
			int tKey = tEntry.getKey(), tResult1 = 0, tResult2 = 0, tResult3 = 0;
			MOLD_RECIPES.put(tKey, tEntry.getValue());
			
			if ((tKey & B[ 0]) != 0) {tResult1 |= B[ 4]; tResult2 |= B[24]; tResult3 |= B[20];}
			if ((tKey & B[ 1]) != 0) {tResult1 |= B[ 9]; tResult2 |= B[23]; tResult3 |= B[15];}
			if ((tKey & B[ 2]) != 0) {tResult1 |= B[14]; tResult2 |= B[22]; tResult3 |= B[10];}
			if ((tKey & B[ 3]) != 0) {tResult1 |= B[19]; tResult2 |= B[21]; tResult3 |= B[ 5];}
			if ((tKey & B[ 4]) != 0) {tResult1 |= B[24]; tResult2 |= B[20]; tResult3 |= B[ 0];}
			
			if ((tKey & B[ 5]) != 0) {tResult1 |= B[ 3]; tResult2 |= B[19]; tResult3 |= B[21];}
			if ((tKey & B[ 6]) != 0) {tResult1 |= B[ 8]; tResult2 |= B[18]; tResult3 |= B[16];}
			if ((tKey & B[ 7]) != 0) {tResult1 |= B[13]; tResult2 |= B[17]; tResult3 |= B[11];}
			if ((tKey & B[ 8]) != 0) {tResult1 |= B[18]; tResult2 |= B[16]; tResult3 |= B[ 6];}
			if ((tKey & B[ 9]) != 0) {tResult1 |= B[23]; tResult2 |= B[15]; tResult3 |= B[ 1];}
			
			if ((tKey & B[10]) != 0) {tResult1 |= B[ 2]; tResult2 |= B[14]; tResult3 |= B[22];}
			if ((tKey & B[11]) != 0) {tResult1 |= B[ 7]; tResult2 |= B[13]; tResult3 |= B[17];}
			if ((tKey & B[12]) != 0) {tResult1 |= B[12]; tResult2 |= B[12]; tResult3 |= B[12];}
			if ((tKey & B[13]) != 0) {tResult1 |= B[17]; tResult2 |= B[11]; tResult3 |= B[ 7];}
			if ((tKey & B[14]) != 0) {tResult1 |= B[22]; tResult2 |= B[10]; tResult3 |= B[ 2];}
			
			if ((tKey & B[15]) != 0) {tResult1 |= B[ 1]; tResult2 |= B[ 9]; tResult3 |= B[23];}
			if ((tKey & B[16]) != 0) {tResult1 |= B[ 6]; tResult2 |= B[ 8]; tResult3 |= B[18];}
			if ((tKey & B[17]) != 0) {tResult1 |= B[11]; tResult2 |= B[ 7]; tResult3 |= B[13];}
			if ((tKey & B[18]) != 0) {tResult1 |= B[16]; tResult2 |= B[ 6]; tResult3 |= B[ 8];}
			if ((tKey & B[19]) != 0) {tResult1 |= B[21]; tResult2 |= B[ 5]; tResult3 |= B[ 3];}
			
			if ((tKey & B[20]) != 0) {tResult1 |= B[ 0]; tResult2 |= B[ 4]; tResult3 |= B[24];}
			if ((tKey & B[21]) != 0) {tResult1 |= B[ 5]; tResult2 |= B[ 3]; tResult3 |= B[19];}
			if ((tKey & B[22]) != 0) {tResult1 |= B[10]; tResult2 |= B[ 2]; tResult3 |= B[14];}
			if ((tKey & B[23]) != 0) {tResult1 |= B[15]; tResult2 |= B[ 1]; tResult3 |= B[ 9];}
			if ((tKey & B[24]) != 0) {tResult1 |= B[20]; tResult2 |= B[ 0]; tResult3 |= B[ 4];}
			
			MOLD_RECIPES.put(tResult1, tEntry.getValue());
			MOLD_RECIPES.put(tResult2, tEntry.getValue());
			MOLD_RECIPES.put(tResult3, tEntry.getValue());
		}
		
		TEMP_MOLD_RECIPES.putAll(MOLD_RECIPES);
		
		for (Entry<Integer, OreDictPrefix> tEntry : TEMP_MOLD_RECIPES.entrySet()) {
			int tKey = tEntry.getKey(), tResult1 = 0, tResult2 = 0;
			
			if ((tKey & B[ 0]) != 0) {tResult1 |= B[ 4]; tResult2 |= B[20];}
			if ((tKey & B[ 1]) != 0) {tResult1 |= B[ 3]; tResult2 |= B[21];}
			if ((tKey & B[ 2]) != 0) {tResult1 |= B[ 2]; tResult2 |= B[22];}
			if ((tKey & B[ 3]) != 0) {tResult1 |= B[ 1]; tResult2 |= B[23];}
			if ((tKey & B[ 4]) != 0) {tResult1 |= B[ 0]; tResult2 |= B[24];}
			
			if ((tKey & B[ 5]) != 0) {tResult1 |= B[ 9]; tResult2 |= B[15];}
			if ((tKey & B[ 6]) != 0) {tResult1 |= B[ 8]; tResult2 |= B[16];}
			if ((tKey & B[ 7]) != 0) {tResult1 |= B[ 7]; tResult2 |= B[17];}
			if ((tKey & B[ 8]) != 0) {tResult1 |= B[ 6]; tResult2 |= B[18];}
			if ((tKey & B[ 9]) != 0) {tResult1 |= B[ 5]; tResult2 |= B[19];}
			
			if ((tKey & B[10]) != 0) {tResult1 |= B[14]; tResult2 |= B[10];}
			if ((tKey & B[11]) != 0) {tResult1 |= B[13]; tResult2 |= B[11];}
			if ((tKey & B[12]) != 0) {tResult1 |= B[12]; tResult2 |= B[12];}
			if ((tKey & B[13]) != 0) {tResult1 |= B[11]; tResult2 |= B[13];}
			if ((tKey & B[14]) != 0) {tResult1 |= B[10]; tResult2 |= B[14];}
			
			if ((tKey & B[15]) != 0) {tResult1 |= B[19]; tResult2 |= B[ 5];}
			if ((tKey & B[16]) != 0) {tResult1 |= B[18]; tResult2 |= B[ 6];}
			if ((tKey & B[17]) != 0) {tResult1 |= B[17]; tResult2 |= B[ 7];}
			if ((tKey & B[18]) != 0) {tResult1 |= B[16]; tResult2 |= B[ 8];}
			if ((tKey & B[19]) != 0) {tResult1 |= B[15]; tResult2 |= B[ 9];}
			
			if ((tKey & B[20]) != 0) {tResult1 |= B[24]; tResult2 |= B[ 0];}
			if ((tKey & B[21]) != 0) {tResult1 |= B[23]; tResult2 |= B[ 1];}
			if ((tKey & B[22]) != 0) {tResult1 |= B[22]; tResult2 |= B[ 2];}
			if ((tKey & B[23]) != 0) {tResult1 |= B[21]; tResult2 |= B[ 3];}
			if ((tKey & B[24]) != 0) {tResult1 |= B[20]; tResult2 |= B[ 4];}
			
			MOLD_RECIPES.put(tResult1, tEntry.getValue());
			MOLD_RECIPES.put(tResult2, tEntry.getValue());
		}
	}
}
