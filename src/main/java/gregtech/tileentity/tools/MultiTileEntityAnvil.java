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

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnRegistration;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.BI;
import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.machines.ITileEntityAnvil;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAnvil extends TileEntityBase09FacingSingle implements ITileEntityAnvil, IMTE_OnRegistration, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public short mMaterialA = 0, mMaterialB = 0;
	public byte mShapeA = 0, mShapeB = 0;
	public long mDurability = 10000;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DURABILITY)) mDurability = aNBT.getLong(NBT_DURABILITY);
		// To make onTick actually update the Visual Data once.
		updateInventory();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(RM.Anvil.mNameInternal) +Chat.CYAN+" (D: "+Chat.WHITE+UT.Code.makeString(UT.Code.divup(mDurability, 10000))+Chat.CYAN+")");
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_ANVIL_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT) + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if ((SIDES_TOP_HORIZONTAL[aSide] || aPlayer == null) && aTool.equals(TOOL_hammer) && (slotHas(0)||slotHas(1))) {
			RecipeMap tRecipeMap = RM.Anvil;
			if (SIDES_HORIZONTAL[aSide]) {
				if (ALONG_AXIS[mFacing][aSide]) {
					switch(mFacing) {
					case SIDE_X_NEG: tRecipeMap = (aHitZ < 0.5 ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_X_POS: tRecipeMap = (aHitZ > 0.5 ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_Z_NEG: tRecipeMap = (aHitX < 0.5 ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_Z_POS: tRecipeMap = (aHitX > 0.5 ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					}
				} else {
					switch(mFacing) {
					case SIDE_X_NEG: tRecipeMap = (aSide == SIDE_Z_NEG ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_X_POS: tRecipeMap = (aSide == SIDE_Z_POS ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_Z_NEG: tRecipeMap = (aSide == SIDE_X_NEG ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					case SIDE_Z_POS: tRecipeMap = (aSide == SIDE_X_POS ? RM.AnvilBendSmall : RM.AnvilBendBig); break;
					}
				}
			}
			Recipe tRecipe = tRecipeMap.findRecipe(this, null, F, Long.MAX_VALUE, NI, ZL_FLUIDTANKGT, slotHas(0)?slot(0):ST.emptySlot(), slotHas(1)?slot(1):ST.emptySlot());
			if (tRecipe != null && tRecipe.isRecipeInputEqual(T, F, ZL_FLUIDTANKGT, slotHas(0)?slot(0):ST.emptySlot(), slotHas(1)?slot(1):ST.emptySlot())) {
				ItemStack[] tOutputItems = tRecipe.getOutputs();
				for (int i = 0; i < tOutputItems.length; i++) if (ST.valid(tOutputItems[i]) && !UT.Inventories.addStackToPlayerInventory(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, aPlayerInventory, tOutputItems[i], F)) ST.place(worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5, tOutputItems[i]);
				removeAllDroppableNullStacks();
				long tDurability = Math.max(10000, UT.Code.divup(Math.max(1, tRecipe.getAbsoluteTotalPower()), 4));
				mDurability -= tDurability;
				if (mDurability <= 0) {
					UT.Sounds.send(SFX.MC_BREAK, this);
					ST.drop(worldObj, getCoords(), OP.scrapGt.mat(mMaterial, 48+rng(16))); // Drops up to 63 Scraps, so 7 Units.
					setToAir();
				}
				updateInventory();
				return tDurability;
			}
			return 0;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add("Remaining Durability: " + UT.Code.makeString(UT.Code.divup(mDurability, 10000)));
			return 1;
		}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mInventoryChanged) {
				mShapeA = mShapeB = 0;
				if (ToolsGT.contains(TOOL_hammer, slot(0))) {
					mMaterialA = MultiItemTool.getPrimaryMaterial(slot(0), MT.Steel).mID;
					if (mMaterialA <= 0) mMaterialA = MT.Steel.mID;
					mShapeA = 8;
				} else if (ToolsGT.contains(TOOL_hammer, slot(1))) {
					mMaterialA = MultiItemTool.getSecondaryMaterial(slot(1), MT.WOODS.Spruce).mID;
					if (mMaterialA <= 0) mMaterialA = MT.WOODS.Spruce.mID;
					mShapeA = 9;
				} else if (slotHas(0)) {
					mMaterialA = MT.Fe.mID;
					OreDictItemData tData = OM.anydata(slot(0));
					if (tData != null) {
						if (tData.mMaterial != null && tData.mMaterial.mMaterial.mID > 0) mMaterialA = tData.mMaterial.mMaterial.mID;
						if (tData.mPrefix != null) {
							if (tData.mPrefix.mNameInternal.startsWith("ingot")) mShapeA = 1; else
							if (tData.mPrefix.mNameInternal.startsWith("plate")) mShapeA = 2; else
							if (tData.mPrefix.mNameInternal.startsWith("plank")) mShapeA = 2; else
							if (tData.mPrefix.mNameInternal.startsWith("stick")) mShapeA = 3; else
							if (tData.mPrefix.mNameInternal.startsWith("wire" )) mShapeA = 3; else
							if (tData.mPrefix.mNameInternal.startsWith("chunk")) mShapeA = 4; else
							if (tData.mPrefix.mNameInternal.startsWith("ring" )) mShapeA = 5; else
							if (tData.mPrefix.mNameInternal.startsWith("gem"  )) mShapeA = 6; else
							if (tData.mPrefix.mNameInternal.startsWith("ore"  )) mShapeA = 7; else
							if (tData.mPrefix.mNameInternal.startsWith("rock" )) mShapeA = 7; else
							if (tData.mPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) mShapeA = 7; else
							mShapeA = 0;
						}
					}
				} else {
					mMaterialA = 0;
				}
				
				
				if (ToolsGT.contains(TOOL_hammer, slot(1))) {
					mMaterialB = MultiItemTool.getPrimaryMaterial(slot(1), MT.Steel).mID;
					if (mMaterialB <= 0) mMaterialB = MT.Steel.mID;
					mShapeB = 8;
				} else if (ToolsGT.contains(TOOL_hammer, slot(0))) {
					mMaterialB = MultiItemTool.getSecondaryMaterial(slot(0), MT.WOODS.Spruce).mID;
					if (mMaterialB <= 0) mMaterialB = MT.WOODS.Spruce.mID;
					mShapeB = 9;
				} else if (slotHas(1)) {
					mMaterialB = MT.Fe.mID;
					OreDictItemData tData = OM.anydata(slot(1));
					if (tData != null) {
						if (tData.mMaterial != null && tData.mMaterial.mMaterial.mID > 0) mMaterialB = tData.mMaterial.mMaterial.mID;
						if (tData.mPrefix != null) {
							if (tData.mPrefix.mNameInternal.startsWith("ingot")) mShapeB = 1; else
							if (tData.mPrefix.mNameInternal.startsWith("plate")) mShapeB = 2; else
							if (tData.mPrefix.mNameInternal.startsWith("plank")) mShapeB = 2; else
							if (tData.mPrefix.mNameInternal.startsWith("stick")) mShapeB = 3; else
							if (tData.mPrefix.mNameInternal.startsWith("wire" )) mShapeB = 3; else
							if (tData.mPrefix.mNameInternal.startsWith("chunk")) mShapeB = 4; else
							if (tData.mPrefix.mNameInternal.startsWith("ring" )) mShapeB = 5; else
							if (tData.mPrefix.mNameInternal.startsWith("gem"  )) mShapeB = 6; else
							if (tData.mPrefix.mNameInternal.startsWith("ore"  )) mShapeB = 7; else
							if (tData.mPrefix.mNameInternal.startsWith("rock" )) mShapeB = 7; else
							if (tData.mPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) mShapeB = 7; else
							mShapeB = 0;
						}
					}
				} else {
					mMaterialB = 0;
				}
				updateClientData();
			}
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			if (aHitY < PX_P[4]) return T;
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			byte tSlot = (byte)((SIDES_AXIS_Z[mFacing]?aHitX:aHitZ) < 0.5 ? 0 : 1);
			boolean tHasHammer = (ToolsGT.contains(TOOL_hammer, slot(0)) || ToolsGT.contains(TOOL_hammer, slot(1)));
			
			if (SIDES_TOP[aSide]) {
				if (tHasHammer) {
					if (slotHas(0) && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, slot(0), T, worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5)) {
						slotKill(0);
						updateInventory();
						return T;
					}
					if (slotHas(1) && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, slot(1), T, worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5)) {
						slotKill(1);
						updateInventory();
						return T;
					}
					return T;
				}
				if (ST.valid(aStack)) {
					if (((ToolsGT.contains(TOOL_hammer, aStack) && !slotHas(0) && !slotHas(1)) || RM.Anvil.containsInput(aStack, this, NI) || RM.AnvilBendSmall.containsInput(aStack, this, NI) || RM.AnvilBendBig.containsInput(aStack, this, NI)) && ST.move(aPlayer.inventory, this, aPlayer.inventory.currentItem, tSlot) > 0) playClick();
					return T;
				}
				if (slotHas(tSlot) && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, slot(tSlot), T, worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5)) {
					slotKill(tSlot);
					updateInventory();
					return T;
				}
			}
			if (slotHas(0)) {
				if (!slotHas(1)) {
					if (slot(0).stackSize % 2 != 0 && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.amount(1, slot(0)), T, worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5)) slot(0).stackSize--;
					if (slot(0).stackSize > 1) {
						slot(0).stackSize /= 2;
						slot(1, ST.copy(slot(0)));
					}
					slotNull(0);
					updateInventory();
					return T;
				}
			} else if (slotHas(1)) {
				if (slot(1).stackSize % 2 != 0 && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.amount(1, slot(1)), T, worldObj, xCoord+0.5, yCoord+1.2, zCoord+0.5)) slot(1).stackSize--;
				if (slot(1).stackSize > 1) {
					slot(1).stackSize /= 2;
					slot(0, ST.copy(slot(1)));
				}
				slotNull(1);
				updateInventory();
				return T;
			}
			return T;
		}
		if (aHitY < PX_P[4]) {RM.Anvil.openNEI(); return T;}
		return T;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
		if (aMTEContainer.mBlock.stepSound != Block.soundTypeMetal || mMaterial.contains(TD.Properties.STONE) || mMaterial == MT.IronWood) return T;
		aWorld.playSoundEffect(aX+0.5, aY+0.5, aZ+0.5, Blocks.anvil.stepSound.func_150496_b(), (Blocks.anvil.stepSound.getVolume()+1)/2, Blocks.anvil.stepSound.getPitch()*0.8F);
		return F;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(), mShapeA, mShapeB, UT.Code.toByteS(mMaterialA, 0), UT.Code.toByteS(mMaterialA, 1), UT.Code.toByteS(mMaterialB, 0), UT.Code.toByteS(mMaterialB, 1));
		return super.getClientDataPacket(aSendAll);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		super.receiveDataByteArray(aData, aNetworkHandler);
		if (aData.length > 4) {
			mShapeA = aData[5];
			mShapeB = aData[6];
			mMaterialA = UT.Code.combine(aData[7], aData[ 8]);
			mMaterialB = UT.Code.combine(aData[9], aData[10]);
		}
		return T;
	}
	
	public ITexture mTextureAnvil, mTextureA, mTextureB;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureAnvil = mMaterial.getTextureSmooth(mRGBa, T);
		
		if (mMaterialA <= 0) mTextureA = null; else if (UT.Code.exists(mMaterialA, OreDictMaterial.MATERIAL_ARRAY)) switch(mShapeA) {
		case  6: mTextureA = OreDictMaterial.MATERIAL_ARRAY[mMaterialA].getTextureGem(); break;
		case  7: mTextureA = BlockTextureDefault.get(OreDictMaterial.MATERIAL_ARRAY[mMaterialA], OP.blockRaw); break;
		default: mTextureA = OreDictMaterial.MATERIAL_ARRAY[mMaterialA].getTextureSolid(); break;
		} else mTextureA = BlockTextureCopied.get(Blocks.iron_block);
		
		if (mMaterialB <= 0) mTextureB = null; else if (UT.Code.exists(mMaterialB, OreDictMaterial.MATERIAL_ARRAY)) switch(mShapeB) {
		case  6: mTextureB = OreDictMaterial.MATERIAL_ARRAY[mMaterialB].getTextureGem(); break;
		case  7: mTextureB = BlockTextureDefault.get(OreDictMaterial.MATERIAL_ARRAY[mMaterialB], OP.blockRaw); break;
		default: mTextureB = OreDictMaterial.MATERIAL_ARRAY[mMaterialB].getTextureSolid(); break;
		} else mTextureB = BlockTextureCopied.get(Blocks.iron_block);
		
		return mTextureB == null ? mTextureA == null ? 6 : 7 : 8;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 4: 2], PX_P[ 0], PX_P[SIDES_AXIS_Z[mFacing]? 4: 2], PX_N[SIDES_AXIS_X[mFacing]? 4: 2], PX_N[12], PX_N[SIDES_AXIS_Z[mFacing]? 4: 2]);
		case  1: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6: 4], PX_P[ 4], PX_P[SIDES_AXIS_Z[mFacing]? 6: 4], PX_N[SIDES_AXIS_X[mFacing]? 6: 4], PX_N[ 8], PX_N[SIDES_AXIS_Z[mFacing]? 6: 4]);
		case  2: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 4: 1], PX_P[ 8], PX_P[SIDES_AXIS_Z[mFacing]? 4: 1], PX_N[SIDES_AXIS_X[mFacing]? 4: 1], PX_N[ 4], PX_N[SIDES_AXIS_Z[mFacing]? 4: 1]);
		case  3:
			switch(mFacing) {
			case SIDE_X_NEG: return box(aBlock, PX_P[ 5], PX_P[ 8], PX_P[15], PX_N[ 5], PX_N[ 4], PX_N[ 0]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 5], PX_P[ 8], PX_P[ 0], PX_N[ 5], PX_N[ 4], PX_N[15]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[15], PX_P[ 8], PX_P[ 5], PX_N[ 0], PX_N[ 4], PX_N[ 5]);
			default        : return box(aBlock, PX_P[ 0], PX_P[ 8], PX_P[ 5], PX_N[15], PX_N[ 4], PX_N[ 5]);
			}
		case  4:
			switch(mFacing) {
			case SIDE_X_NEG: return box(aBlock, PX_P[ 4], PX_P[ 9], PX_P[ 0], PX_N[ 4], PX_N[ 5], PX_N[15]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 4], PX_P[ 9], PX_P[15], PX_N[ 4], PX_N[ 5], PX_N[ 0]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 0], PX_P[ 9], PX_P[ 4], PX_N[15], PX_N[ 5], PX_N[ 4]);
			default        : return box(aBlock, PX_P[15], PX_P[ 9], PX_P[ 4], PX_N[ 0], PX_N[ 5], PX_N[ 4]);
			}
		case  5:
			switch(mFacing) {
			case SIDE_X_NEG: return box(aBlock, PX_P[ 4]-0.0001F, PX_P[ 0], PX_P[ 2], PX_P[12]+0.0001F, PX_P[ 2], PX_P[ 4]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 4]-0.0001F, PX_P[ 0], PX_P[12], PX_P[12]+0.0001F, PX_P[ 2], PX_P[14]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 4]-0.0001F, PX_P[ 4], PX_P[ 2], PX_P[12]+0.0001F);
			default        : return box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 4]-0.0001F, PX_P[14], PX_P[ 2], PX_P[12]+0.0001F);
			}
		case  6:
			switch(mShapeA) {
			case  1: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 3], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 3], PX_N[SIDES_AXIS_X[mFacing]? 5:10], PX_N[ 1], PX_N[SIDES_AXIS_Z[mFacing]? 5:10]);
			case  2: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 1], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 1], PX_N[SIDES_AXIS_X[mFacing]? 5: 9], PX_N[ 3], PX_N[SIDES_AXIS_Z[mFacing]? 5: 9]);
			case  3: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 7: 1], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 7: 1], PX_N[SIDES_AXIS_X[mFacing]? 7: 9], PX_N[ 2], PX_N[SIDES_AXIS_Z[mFacing]? 7: 9]);
			case  4: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6: 2], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6: 2], PX_N[SIDES_AXIS_X[mFacing]? 6:10], PX_N[ 2], PX_N[SIDES_AXIS_Z[mFacing]? 6:10]);
			case  5: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6: 2], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6: 2], PX_N[SIDES_AXIS_X[mFacing]? 6:10], PX_N[ 3], PX_N[SIDES_AXIS_Z[mFacing]? 6:10]);
			case  6: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6: 2], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6: 2], PX_N[SIDES_AXIS_X[mFacing]? 6:10], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 6:10]);
			case  7: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 1], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 1], PX_N[SIDES_AXIS_X[mFacing]? 5: 9], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5: 9]);
			case  8: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 2], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 2], PX_N[SIDES_AXIS_X[mFacing]? 5:10], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5:10]);
			case  9: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 7: 1], PX_P[13], PX_P[SIDES_AXIS_Z[mFacing]? 7: 1], PX_N[SIDES_AXIS_X[mFacing]? 7: 1], PX_N[ 1], PX_N[SIDES_AXIS_Z[mFacing]? 7: 1]);
			default: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 1], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 1], PX_N[SIDES_AXIS_X[mFacing]? 5: 9], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5: 9]);
			}
		case  7:
			switch(mShapeB) {
			case  1: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5:10], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5:10], PX_N[SIDES_AXIS_X[mFacing]? 5: 3], PX_N[ 1], PX_N[SIDES_AXIS_Z[mFacing]? 5: 3]);
			case  2: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 9], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 9], PX_N[SIDES_AXIS_X[mFacing]? 5: 1], PX_N[ 3], PX_N[SIDES_AXIS_Z[mFacing]? 5: 1]);
			case  3: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 7: 9], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 7: 9], PX_N[SIDES_AXIS_X[mFacing]? 7: 1], PX_N[ 2], PX_N[SIDES_AXIS_Z[mFacing]? 7: 1]);
			case  4: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6:10], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6:10], PX_N[SIDES_AXIS_X[mFacing]? 6: 2], PX_N[ 2], PX_N[SIDES_AXIS_Z[mFacing]? 6: 2]);
			case  5: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6:10], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6:10], PX_N[SIDES_AXIS_X[mFacing]? 6: 2], PX_N[ 3], PX_N[SIDES_AXIS_Z[mFacing]? 6: 2]);
			case  6: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 6:10], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 6:10], PX_N[SIDES_AXIS_X[mFacing]? 6: 2], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 6: 2]);
			case  7: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 9], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 9], PX_N[SIDES_AXIS_X[mFacing]? 5: 1], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5: 1]);
			case  8: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5:10], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5:10], PX_N[SIDES_AXIS_X[mFacing]? 5: 2], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5: 2]);
			case  9: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 7: 1], PX_P[13], PX_P[SIDES_AXIS_Z[mFacing]? 7: 1], PX_N[SIDES_AXIS_X[mFacing]? 7: 1], PX_N[ 1], PX_N[SIDES_AXIS_Z[mFacing]? 7: 1]);
			default: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 5: 9], PX_P[12], PX_P[SIDES_AXIS_Z[mFacing]? 5: 9], PX_N[SIDES_AXIS_X[mFacing]? 5: 1], PX_N[ 0], PX_N[SIDES_AXIS_Z[mFacing]? 5: 1]);
			}
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDES_TOP_HORIZONTAL[aSide] || aShouldSideBeRendered[aSide] ? mTextureAnvil : null;
		case  1: return SIDES_HORIZONTAL[aSide] ? mTextureAnvil : null;
		case  2: return mTextureAnvil;
		case  3: return mTextureAnvil;
		case  4: return mTextureAnvil;
		case  5: return ALONG_AXIS[mFacing][aSide] ? BI.nei() : null;
		case  6: return SIDES_TOP_HORIZONTAL[aSide] ? mTextureA : null;
		case  7: return SIDES_TOP_HORIZONTAL[aSide] ? mTextureB : null;
		default: return mTextureAnvil;
		}
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[SIDES_AXIS_X[mFacing]? 4: 0], PX_P[ 0], PX_P[SIDES_AXIS_Z[mFacing]? 4: 0], PX_N[SIDES_AXIS_X[mFacing]? 4: 0], PX_N[4], PX_N[SIDES_AXIS_Z[mFacing]? 4: 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[SIDES_AXIS_X[mFacing]? 4: 0], PX_P[ 0], PX_P[SIDES_AXIS_Z[mFacing]? 4: 0], PX_N[SIDES_AXIS_X[mFacing]? 4: 0], PX_N[4], PX_N[SIDES_AXIS_Z[mFacing]? 4: 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 4: 0], PX_P[ 0], PX_P[SIDES_AXIS_Z[mFacing]? 4: 0], PX_N[SIDES_AXIS_X[mFacing]? 4: 0], PX_N[4], PX_N[SIDES_AXIS_Z[mFacing]? 4: 0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]||ALONG_AXIS[mFacing][aSide]?PX_P[ 4]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isAnvil                (byte aSide) {return T;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[2];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return !ToolsGT.contains(TOOL_hammer, slot(0)) && !ToolsGT.contains(TOOL_hammer, slot(1)) && (RM.Anvil.containsInput(aStack, this, NI) || RM.AnvilBendSmall.containsInput(aStack, this, NI) || RM.AnvilBendBig.containsInput(aStack, this, NI));}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		RM.Anvil         .mRecipeMachineList.add(aRegistry.getItem(aID));
		RM.AnvilBendSmall.mRecipeMachineList.add(aRegistry.getItem(aID));
		RM.AnvilBendBig  .mRecipeMachineList.add(aRegistry.getItem(aID));
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.anvil.simple";}
}
