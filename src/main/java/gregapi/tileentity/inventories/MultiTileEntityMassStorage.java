/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity.inventories;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetMaxStackSize;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnRegistrationFirstClient;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SyncDataInteger;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SyncDataShort;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.data.*;
import gregapi.data.LH.Chat;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.RenderHelper;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityConnectedInventory;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.logistics.ITileEntityLogisticsSemiFilteredItem;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fluids.Fluid;

import java.util.List;

import static gregapi.data.CS.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityMassStorage extends TileEntityBase09FacingSingle implements ITileEntityConnectedInventory, ITileEntityLogisticsSemiFilteredItem, ITileEntityProgress, ITileEntityAdjacentInventoryUpdatable, IMTE_GetMaxStackSize, IMTE_SyncDataInteger, IMTE_SyncDataShort, IMTE_OnRegistrationFirstClient {
	public int oStacksize = 0, mMaxStorage = 1000000;
	public long mPartialUnits = 0;
	public byte mMode = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_CAPACITY)) mMaxStorage = aNBT.getInteger(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_INPUT)) mPartialUnits = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_STATE)) slot(1, ST.load(aNBT, NBT_STATE)); 
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_INPUT, mPartialUnits);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (isClientSide() && slotHas(1)) aNBT.setTag("display", UT.NBT.makeString(aNBT.getCompoundTag("display"), "Name", slot(1).getDisplayName()));
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		return super.writeItemNBT2(aNBT);
	}
	
	static {
		LH.add("gt.multitileentity.massstorage.tooltip.1", "Can store Items of one Type, Capacity: ");
		LH.add("gt.multitileentity.massstorage.tooltip.2", "Can be used adjacent to Advanced Crafting Tables");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (slotHas(1)) aList.add(Chat.YELLOW + slot(1).getDisplayName() + Chat.GRAY + ": " + Chat.WHITE + slot(1).stackSize);
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.massstorage.tooltip.1") + UT.Code.makeString(mMaxStorage));
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.massstorage.tooltip.2"));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TAKE_PINCERS));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH));
		if ((mMode & B[3]) == 0) {
			aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
			aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TAPE));
		} else {
			aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_UNTAPE));
		}
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_pincers)) {
			if ((mMode & B[3]) != 0) return 0;
			long rCount = 0;
			if (mPartialUnits > 0) {
				ST.give(aPlayer, getPartialStack(), worldObj, xCoord + OFFX[mFacing], yCoord, zCoord + OFFZ[mFacing]);
				mPartialUnits = 0;
				rCount += 10;
			}
			if (slotHas(0)) for (int j = 9; j < 36; j++) {
				rCount += ST.move(this, aPlayerInventory, 0, j);
				if (!slotHas(0)) break;
			}
			if (slotHas(1)) for (int j = 9; j < 36; j++) {
				rCount += ST.move(this, aPlayerInventory, 0, j);
				if (!slotHas(1)) break;
			}
			// Nothing was done.
			if (rCount <= 0) return 1;
			// Make Sound and update Player Inventory if Items got transferred.
			UT.Sounds.send(SFX.MC_COLLECT, this, F);
			ST.update(aPlayer);
			// Update Mass Storage itself too.
			updateClientData();
			updateInventory();
			return rCount;
		}
		if (aTool.equals(TOOL_softhammer)) {
			if ((mMode & B[3]) != 0) return 0;
			if (slotHas(0)) {
				ST.place(worldObj, xCoord+OFFX[mFacing]+0.5, yCoord+OFFY[mFacing]+0.5, zCoord+OFFZ[mFacing]+0.5, ST.copy(slot(0)));
				slotKill(0);
				updateInventory();
				return 10000;
			}
			if (slotHas(1)) {
				for (int i = 0; i < 128 && slot(1).stackSize > Math.max(1, slot(1).getMaxStackSize()); i++) {
					ST.place(worldObj, xCoord+OFFX[mFacing]+0.5, yCoord+OFFY[mFacing]+0.5, zCoord+OFFZ[mFacing]+0.5, ST.amount(Math.max(1, slot(1).getMaxStackSize()), slot(1)));
					slot(1).stackSize -= Math.max(1, slot(1).getMaxStackSize());
				}
				if (mPartialUnits > 0) {
					ST.drop(worldObj, getCoords(), getPartialStack());
					mPartialUnits = 0;
				}
				if (slot(1).stackSize > 0) {
					if (slot(1).stackSize <= Math.max(1, slot(1).getMaxStackSize())) {
						ST.place(worldObj, xCoord+OFFX[mFacing]+0.5, yCoord+OFFY[mFacing]+0.5, zCoord+OFFZ[mFacing]+0.5, ST.copy(slot(1)));
						slotKill(1);
						updateClientData();
					}
				} else {
					slotKill(1);
					updateClientData();
				}
				updateInventory();
				return 2000;
			}
		}
		if (aTool.equals(TOOL_ducttape)) {
			if ((mMode & B[3]) != 0 || !slotHas(1)) return 0;
			if (slot(1).stackSize > aRemainingDurability) {
				aChatReturn.add("Not enough Tape left to contain the Items!");
				return 0;
			}
			mMode |= B[3];
			updateClientData();
			updateInventory();
			return Math.max(100, slot(1).stackSize);
		}
		if (aTool.equals(TOOL_scissors) || aTool.equals(TOOL_knife)) {
			if ((mMode & B[3]) == 0) return 0;
			mMode &= ~B[3];
			updateClientData();
			updateInventory();
			return 1000;
		}
		if (aTool.equals(TOOL_cutter)) {
			mMode ^= B[2];
			aChatReturn.add((mMode & B[2]) == 0 ? "Won't emit Overflow" : "Will emit Overflow to Inventories below");
			updateClientData();
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			mMode ^= B[1];
			aChatReturn.add((mMode & B[1]) == 0 ? "Filter stays when empty" : "Filter resets when empty");
			if (!allowZeroStacks(1)) slotNull(1);
			updateClientData();
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			mMode ^= B[0];
			aChatReturn.add((mMode & B[0]) == 0 ? "Won't fill Inventories below" : "Will fill Inventories below");
			updateClientData();
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (slotHas(1)) {
					aChatReturn.add("Contains: " + slot(1).stackSize + " " + slot(1).getDisplayName());
				} else {
					aChatReturn.add("Storage is empty");
				}
				aChatReturn.add((mMode & B[0]) == 0 ? "Won't fill Inventories below" : "Will fill Inventories below");
				aChatReturn.add((mMode & B[1]) == 0 ? "Filter stays when empty" : "Filter resets when empty");
				aChatReturn.add((mMode & B[2]) == 0 ? "Won't emit Overflow" : "Will emit Overflow to Inventories below");
				if ((mMode & B[3]) != 0) aChatReturn.add("Will keep content when harvested.");
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing || (mMode & B[3]) != 0 || isCovered(aSide)) return F;
		float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
		if (tCoords[0] < PX_P[1] || tCoords[0] > PX_N[1] || tCoords[1] < PX_P[1] || tCoords[1] > PX_N[1]) return F;
		if (isServerSide() && aPlayer != null) {
			updatePartialContent();
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (slotHas(1)) {
				int tAmount = 0;
				if (tCoords[1] >= PX_P[ 6] && tCoords[1] <= PX_P[ 8]) {
					if (tCoords[0] >= PX_P[ 1] && tCoords[0] <= PX_P[ 3]) {tAmount =  8;}
					if (tCoords[0] >= PX_N[ 3] && tCoords[0] <= PX_N[ 1]) {tAmount = 64;}
				}
				if (tCoords[1] >= PX_P[ 9] && tCoords[1] <= PX_P[11]) {
					if (tCoords[0] >= PX_P[ 1] && tCoords[0] <= PX_P[ 3]) {tAmount =  4;}
					if (tCoords[0] >= PX_N[ 3] && tCoords[0] <= PX_N[ 1]) {tAmount = 32;}
				}
				if (tCoords[1] >= PX_P[12] && tCoords[1] <= PX_P[14]) {
					if (tCoords[0] >= PX_P[ 1] && tCoords[0] <= PX_P[ 3]) {tAmount =  1;}
					if (tCoords[0] >= PX_N[ 3] && tCoords[0] <= PX_N[ 1]) {tAmount = 16;}
				}
				if (tCoords[1] >= PX_P[ 6]) {
					if (tCoords[0] >= PX_P[ 4] && tCoords[0] <= PX_N[ 4]) {tAmount = -1;}
				}
				if (tAmount > 0) {
					tAmount = Math.min(tAmount, slot(1).stackSize);
					if (tAmount > 0) {
						slot(1).stackSize -= tAmount;
						while (tAmount > 0) {
							ItemStack tStack = ST.amount(Math.min(tAmount, Math.max(1, slot(1).getMaxStackSize())), slot(1));
							tAmount -= tStack.stackSize;
							ST.place(worldObj, getOffsetX(mFacing)+0.5, getOffsetY(mFacing)+0.5, getOffsetZ(mFacing)+0.5, tStack);
						}
						updateInventory();
						playCollect();
					}
				} else {
					if (ST.valid(aStack)) {
						ItemStack tStack = insertItems(aStack, T);
						if (tStack == null) {
							aStack.stackSize = 0;
							playCollect();
						} else if (tStack.stackSize < aStack.stackSize) {
							aStack.stackSize = tStack.stackSize;
							playCollect();
						}
					} else {
						if (tAmount == -1) {
							boolean temp = F;
							for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) if (aPlayer.inventory.mainInventory[i] != null && allowInsertion(aPlayer.inventory.mainInventory[i])) {
								ItemStack tStack = insertItems(aPlayer.inventory.mainInventory[i], F);
								if (tStack == null) {
									temp = T;
									aPlayer.inventory.mainInventory[i] = null;
									continue;
								}
								if (tStack.stackSize < aPlayer.inventory.mainInventory[i].stackSize) {
									temp = T;
									aPlayer.inventory.mainInventory[i] = tStack;
									continue;
								}
								break;
							}
							if (temp) {
								ST.update(aPlayer);
								playCollect();
							}
						}
					}
				}
				if ((mMode & B[1]) != 0 && mPartialUnits <= 0 && slotNull(1)) {
					updateClientData();
					updateInventory();
				}
			} else {
				if (ST.valid(aStack)) {
					ItemStack tStack = insertItems(aStack, T);
					if (tStack == null) {
						aStack.stackSize = 0;
						playCollect();
					} else if (tStack.stackSize < aStack.stackSize) {
						aStack.stackSize = tStack.stackSize;
						playCollect();
					}
				}
			}
			updatePartialContent();
		}
		return T;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && (mMode & B[3]) == 0) {
			if (!slotNull(0)) slot(0, insertItems(slot(0), F));
			boolean temp = F;
			if (mInventoryChanged || aTimer % 100 == 0) {
				if (slotHas(1)) {
					if ((mMode & B[0]) != 0 && slot(1).stackSize > 0) {
						if (ST.move(delegator(SIDE_BOTTOM), getAdjacentInventory(SIDE_BOTTOM)) > 0) temp = T;
					} else // else, because if it already tried to emit normally, then it doesn't need to check a second time.
					if ((mMode & B[2]) != 0 && slot(1).stackSize > mMaxStorage) {
						emitOverflow();
					}
				}
			}
			if (mInventoryChanged || temp) {
				for (byte tSide : ALL_SIDES_BUT_BOTTOM) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
						((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
					}
				}
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || (isFaceVisible() && (slotHas(1) ? slot(1).stackSize != oStacksize && (Math.abs(slot(1).stackSize - oStacksize) > 64 ? SERVER_TIME % 5 == 0 : SYNC_SECOND) : oStacksize != 0));
	}
	
	@Override
	public void onTickChecked(long aTimer) {
		super.onTickChecked(aTimer);
		oStacksize = slotHas(1) ? slot(1).stackSize : 0;
	}
	
	@Override
	public void setInventorySlotContents(int aSlot, ItemStack aStack) {
		if (aSlot == 0) slot(aSlot, insertItems(OM.get(aStack), F));
		if (aSlot == 1 && slotHas(aSlot)) decrStackSize(aSlot, aStack == null ? slot(aSlot).stackSize : slot(aSlot).stackSize - aStack.stackSize);
	}
	
	public int getMaxContent() {
		return (mMode & B[2]) != 0 ? mMaxStorage + 256 : mMaxStorage;
	}
	
	/** @return Items Leftover after inserting. */
	public ItemStack insertItems(ItemStack aStack, boolean aCheckForNEI) {
		if (ST.invalid(aStack) || (mMode & B[3]) != 0) return aStack;
		
		if (!slotHas(1)) {
			mLogisticsCache = null;
			if (aCheckForNEI && aStack.stackSize == NEI_INFINITE) {
				slot(1, ST.amount(mMaxStorage, aStack));
				mPartialUnits = 0;
				updateClientData();
				updateInventory();
				return aStack;
			}
			slot(1, ST.copy(aStack));
			updateClientData();
			updateInventory();
			if ((mMode & B[2]) != 0 && aStack.stackSize > mMaxStorage) emitOverflow();
			return null;
		}
		
		int tMaxStorage = getMaxContent();
		ItemStack tContent = slot(1);
		
		if (tContent.stackSize >= tMaxStorage) return aStack;
		
		if (ST.equal(aStack, tContent)) {
			if (aCheckForNEI && aStack.stackSize == NEI_INFINITE) {
				tContent.stackSize = mMaxStorage;
				mPartialUnits = 0;
				updateInventory();
				return aStack;
			}
			ItemStack rStack = null;
			if (aStack.stackSize + tContent.stackSize > tMaxStorage) rStack = ST.amount(aStack.stackSize + tContent.stackSize - tMaxStorage, aStack);
			tContent.stackSize = Math.min(tMaxStorage, tContent.stackSize + aStack.stackSize);
			updateInventory();
			if ((mMode & B[2]) != 0 && tContent.stackSize > mMaxStorage) emitOverflow();
			return rStack;
		}
		
		if (updatePartialContent(getUnitAmount(aStack) * aStack.stackSize)) {
			if ((mMode & B[2]) != 0 && tContent.stackSize > mMaxStorage) emitOverflow();
			return null;
		}
		return aStack;
	}
	
	public void emitOverflow() {
		DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(SIDE_BOTTOM);
		while (slotHas(1) && slot(1).stackSize > mMaxStorage) {
			int tToBeMoved = UT.Code.bindStack(slot(1).stackSize - mMaxStorage);
			if (ST.move(delegator(SIDE_BOTTOM), tTileEntity, null, F, F, F, T, tToBeMoved, 1, tToBeMoved, 1) <= 0) break;
		}
	}
	
	private ItemStackSet<ItemStackContainer> mLogisticsCache = null;
	
	@Override
	public ItemStackSet<ItemStackContainer> getLogisticsFilter(byte aSide) {
		if (!slotHas(1)) return mLogisticsCache = null;
		if (mLogisticsCache != null) return mLogisticsCache;
		mLogisticsCache = ST.hashset(slot(1));
		OreDictItemData tData = OM.data_(slot(1));
		if (tData != null && tData.validData()) {
			if (tData.mPrefix.contains(TD.Prefix.DUST_BASED)) {
				for (ItemStack tStack : OreDictManager.getOres(OP.blockDust             , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.dust                  , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.dustSmall             , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.dustTiny              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.dustDiv72             , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix.contains(TD.Prefix.INGOT_BASED)) {
				for (ItemStack tStack : OreDictManager.getOres(OP.blockIngot            , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.ingot                 , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.billet                , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.chunkGt               , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.nugget                , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix.contains(TD.Prefix.WIRE_BASED)) {
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt01              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt02              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt03              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt04              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt05              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt06              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt07              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt08              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt09              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt10              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt11              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt12              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt13              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt14              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt15              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.wireGt16              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.gem || tData.mPrefix == OP.blockGem) {
				for (ItemStack tStack : OreDictManager.getOres(OP.gem                   , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.blockGem              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.plate || tData.mPrefix == OP.blockPlate) {
				for (ItemStack tStack : OreDictManager.getOres(OP.plate                 , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.blockPlate            , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.plateGem || tData.mPrefix == OP.blockPlateGem) {
				for (ItemStack tStack : OreDictManager.getOres(OP.plateGem              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.blockPlateGem         , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.crushed || tData.mPrefix == OP.crushedTiny) {
				for (ItemStack tStack : OreDictManager.getOres(OP.crushed               , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.crushedTiny           , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.crushedPurified || tData.mPrefix == OP.crushedPurifiedTiny) {
				for (ItemStack tStack : OreDictManager.getOres(OP.crushedPurified       , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.crushedPurifiedTiny   , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.crushedCentrifuged || tData.mPrefix == OP.crushedCentrifugedTiny) {
				for (ItemStack tStack : OreDictManager.getOres(OP.crushedCentrifuged    , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.crushedCentrifugedTiny, tData.mMaterial, F)) mLogisticsCache.add(tStack);
			} else if (tData.mPrefix == OP.oreRaw || tData.mPrefix == OP.blockRaw) {
				for (ItemStack tStack : OreDictManager.getOres(OP.oreRaw                , tData.mMaterial, F)) mLogisticsCache.add(tStack);
				for (ItemStack tStack : OreDictManager.getOres(OP.blockRaw              , tData.mMaterial, F)) mLogisticsCache.add(tStack);
			}
		}
		return mLogisticsCache;
	}
	
	// Default implementation for Logistics Stuffs
	public boolean canLogistics(byte aSide) {return T;}
	public int getLogisticsPriorityItem() {return slotHas(1) ? 2 : 1;}
	public int getLogisticsPriorityFluid() {return 0;}
	public Fluid getLogisticsFilterFluid() {return null;}
	public ItemStack getLogisticsFilterItem() {return null;}
	
	public boolean isFaceVisible() {
		return SIDES_HORIZONTAL[mFacing] && (mMode & B[3]) == 0 && (!hasCovers()||mCovers.mBehaviours[mFacing]==null||!mCovers.mBehaviours[mFacing].isOpaque(mFacing, mCovers));
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		int tStacksize = slotHas(1) ? slot(1).stackSize : -1;
		short tMeta = slotHas(1) ? ST.meta_(slot(1)) : 0, tID = ST.id(slot(1));
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData(), mMode, UT.Code.toByteS(tID, 0), UT.Code.toByteS(tID, 1), UT.Code.toByteS(tMeta, 0), UT.Code.toByteS(tMeta, 1), UT.Code.toByteI(tStacksize, 0), UT.Code.toByteI(tStacksize, 1), UT.Code.toByteI(tStacksize, 2), UT.Code.toByteI(tStacksize, 3)) : tStacksize <= Short.MAX_VALUE ? getClientDataPacketShort(aSendAll, (short)tStacksize) : getClientDataPacketInteger(aSendAll, tStacksize);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setDirectionData(aData[3]);
		mMode = aData[4];
		slot(1, ST.make(UT.Code.combine(aData[5], aData[6]), UT.Code.combine(aData[9], aData[10], aData[11], aData[12]), UT.Code.combine(aData[7], aData[8])));
		return T;
	}
	
	@Override
	public boolean receiveDataInteger(int aData, INetworkHandler aNetworkHandler) {
		if (aData < 0) slotKill(1); else if (slotHas(1)) slot(1).stackSize = aData;
		return T;
	}
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		if (aData < 0) slotKill(1); else if (slotHas(1)) slot(1).stackSize = aData;
		return T;
	}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public boolean attachCoversFirst(byte aSide) {return aSide != mFacing;}
	
	public static final int[] ACCESSIBLE_SLOTS = {0, 1};
	
	@Override public void onExploded(Explosion aExplosion) {slotKill(1); super.onExploded(aExplosion);}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[2];}
	@Override public int getInventoryStackLimit() {return Math.min(slotHas(1) ? getMaxContent() - slot(1).stackSize : getMaxContent(), 64);}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == 0 && (mMode & B[3]) == 0 && (!SIDES_BOTTOM[aSide] || (mMode & B[0]) == 0) && (!slotHas(1) || (slot(1).stackSize < getMaxContent() && allowInsertion(aStack)));}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == 0 || (slotHas(1) && slot(1).stackSize > 0 && (mMode & B[3]) == 0);}
	@Override public boolean allowZeroStacks(int aSlot) {return aSlot == 1 && ((mMode & B[1]) == 0 || mPartialUnits > 0);}
	@Override public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity) {if (SIDES_BOTTOM[aSide]) updateInventory();}
	@Override public long getAmountOfItemsInConnectedInventory(byte aSide, ItemStack aStack, long aStopCountingAtThisNumber) {return slotHas(1) && ST.equal(slot(1), aStack) ? slot(1).stackSize : 0;}
	@Override public long getProgressValue(byte aSide) {return slotHas(1) ? slot(1).stackSize : 0;}
	@Override public long getProgressMax(byte aSide) {return mMaxStorage;}
	@Override public boolean canDrop (int aSlot) {return !keepSlot(aSlot);}
	@Override public boolean keepSlot(int aSlot) {return aSlot == 1 && (mMode & B[3]) != 0;}
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return slotHas(1) ? 1 : aDefault;}
	
	@Override
	public boolean breakBlock() {
		if (isServerSide() && mPartialUnits > 0) {
			ST.drop(worldObj, getCoords(), getPartialStack());
			mPartialUnits = 0;
		}
		return super.breakBlock();
	}
	
	@Override
	public int addStackToConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated) {
		if ((mMode & B[3]) != 0) return 0;
		if (!aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated || slotHas(1)) {
			ItemStack tStack = insertItems(aStack, F);
			if (tStack == null) return aStack.stackSize;
			if (tStack.stackSize < aStack.stackSize) return aStack.stackSize - tStack.stackSize;
		}
		return 0;
	}
	
	@Override
	public int removeStackFromConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if ((mMode & B[3]) != 0) return 0;
		if (slotHas(1) && ST.equal(slot(1), aStack)) {
			if (aOnlyRemoveIfItCanRemoveAllAtOnce && slot(1).stackSize < aStack.stackSize) return 0;
			int tAmount = Math.min(aStack.stackSize, slot(1).stackSize);
			slot(1).stackSize -= tAmount;
			if ((mMode & B[1]) != 0 && mPartialUnits <= 0 && slotNull(1)) updateClientData();
			updateInventory();
			return tAmount;
		}
		return 0;
	}
	
	public ItemStack getPartialStack() {
		if (mPartialUnits <= 0) return NI;
		OreDictItemData mData = OM.anydata(slot(1));
		if (mData == null || !mData.validPrefix()) return NI;
		
		if (mData.mPrefix.contains(TD.Prefix.DUST_BASED)) return OM.dust(mData.mMaterial.mMaterial, mPartialUnits);
		if (mData.mPrefix.contains(TD.Prefix.INGOT_BASED)) return OM.ingot(mData.mMaterial.mMaterial, mPartialUnits);
		if (mData.mPrefix.contains(TD.Prefix.WIRE_BASED)) return OP.wireGt01.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.wireGt01.mAmount);
		
		if (mData.mPrefix == OP.gem || mData.mPrefix == OP.blockGem) {
			return OP.gem.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.gem.mAmount);
		}
		if (mData.mPrefix == OP.plate || mData.mPrefix == OP.blockPlate) {
			return OP.plate.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.plate.mAmount);
		}
		if (mData.mPrefix == OP.plateGem || mData.mPrefix == OP.blockPlateGem) {
			return OP.plateGem.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.plateGem.mAmount);
		}
		if (mData.mPrefix == OP.crushed || mData.mPrefix == OP.crushedTiny) {
			return OP.crushedTiny.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.crushedTiny.mAmount);
		}
		if (mData.mPrefix == OP.crushedPurified || mData.mPrefix == OP.crushedPurifiedTiny) {
			return OP.crushedPurifiedTiny.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.crushedPurifiedTiny.mAmount);
		}
		if (mData.mPrefix == OP.crushedCentrifuged || mData.mPrefix == OP.crushedCentrifugedTiny) {
			return OP.crushedCentrifugedTiny.mat(mData.mMaterial.mMaterial, mPartialUnits / OP.crushedCentrifugedTiny.mAmount);
		}
		if (mData.mPrefix == OP.oreRaw || mData.mPrefix == OP.blockRaw) {
			return OP.oreRaw.mat(mData.mMaterial.mMaterial, mPartialUnits / U);
		}
		return NI;
	}

	public long getUnitAmount(OreDictPrefix aPrefix) {
		return aPrefix == OP.oreRaw ? U : aPrefix == OP.blockRaw ? U * 9 : aPrefix.mAmount;
	}
	
	public long getUnitAmount(ItemStack aStack) {
		OreDictItemData mData = OM.anydata_(slot(1)), aData = OM.anydata_(aStack);
		if (mData != null && aData != null && mData.validData() && aData.validData() && mData.mMaterial.mMaterial == aData.mMaterial.mMaterial && (!mData.mBlackListed || (mData.mMaterial.mMaterial != MT.Glass && MD.MC.owns(slot(1)))) && mPartialUnits < getUnitAmount(mData.mPrefix)) {
			if (mData.mPrefix.contains(TD.Prefix.DUST_BASED)) {
				return aData.mPrefix.contains(TD.Prefix.DUST_BASED) ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix.contains(TD.Prefix.INGOT_BASED)) {
				return aData.mPrefix.contains(TD.Prefix.INGOT_BASED) ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix.contains(TD.Prefix.WIRE_BASED)) {
				return aData.mPrefix.contains(TD.Prefix.WIRE_BASED) ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.gem || mData.mPrefix == OP.blockGem) {
				return aData.mPrefix == OP.gem || aData.mPrefix == OP.blockGem ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.plate || mData.mPrefix == OP.blockPlate) {
				return aData.mPrefix == OP.plate || aData.mPrefix == OP.blockPlate ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.plateGem || mData.mPrefix == OP.blockPlateGem) {
				return aData.mPrefix == OP.plateGem || aData.mPrefix == OP.blockPlateGem ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.crushed || mData.mPrefix == OP.crushedTiny) {
				return aData.mPrefix == OP.crushed || aData.mPrefix == OP.crushedTiny ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.crushedPurified || mData.mPrefix == OP.crushedPurifiedTiny) {
				return aData.mPrefix == OP.crushedPurified || aData.mPrefix == OP.crushedPurifiedTiny ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.crushedCentrifuged || mData.mPrefix == OP.crushedCentrifugedTiny) {
				return aData.mPrefix == OP.crushedCentrifuged || aData.mPrefix == OP.crushedCentrifugedTiny ? aData.mPrefix.mAmount : 0;
			}
			if (mData.mPrefix == OP.oreRaw || mData.mPrefix == OP.blockRaw) {
				return aData.mPrefix == OP.oreRaw ? U : aData.mPrefix == OP.blockRaw ? U * 9 : 0;
			}
		}
		return 0;
	}
	
	public boolean allowInsertion(ItemStack aStack) {
		if ((mMode & B[3]) != 0) return F;
		if (ST.equal(slot(1), aStack)) return T;
		return getUnitAmount(aStack) > 0;
	}
	
	public boolean updatePartialContent(long aAmountAdded) {
		if (aAmountAdded <= 0) return F;
		mPartialUnits += aAmountAdded;
		return updatePartialContent();
	}
	
	public boolean updatePartialContent() {
		int tMaxStorage = getMaxContent();
		ItemStack tContent = slot(1);
		if (mPartialUnits > 0 && slotHas(1) && tContent.stackSize < tMaxStorage) {
			OreDictItemData mData = OM.anydata_(tContent);
			if (mData != null && mData.validPrefix()) {
				long tTargetAmount = getUnitAmount(mData.mPrefix);
				if (mPartialUnits >= tTargetAmount) {
					ItemStack tStack = ST.amount(mPartialUnits / tTargetAmount, tContent);
					if (tStack.stackSize > 0) {
						mPartialUnits -= tTargetAmount * tStack.stackSize;
						if (tStack.stackSize + tContent.stackSize > tMaxStorage) mPartialUnits += tTargetAmount * (tStack.stackSize + tContent.stackSize - tMaxStorage);
						tContent.stackSize = Math.min(tMaxStorage, tContent.stackSize + tStack.stackSize);
						updateInventory();
					}
				}
			}
		}
		return T;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRegistrationFirstClient(MultiTileEntityRegistry aRegistry, short aID) {
		ClientRegistry.bindTileEntitySpecialRenderer(getClass(), MultiTileEntityRendererMassStorage.INSTANCE);
	}
	
	@SideOnly(Side.CLIENT)
	public static class MultiTileEntityRendererMassStorage extends TileEntitySpecialRenderer {
		public static MultiTileEntityRendererMassStorage INSTANCE = new MultiTileEntityRendererMassStorage();
		
		@Override
		public void renderTileEntityAt(TileEntity aTileEntity, double aX, double aY, double aZ, float aPartialTick) {
			if (aTileEntity instanceof MultiTileEntityMassStorage && ((MultiTileEntityMassStorage)aTileEntity).slotHas(1) && ((MultiTileEntityMassStorage)aTileEntity).isFaceVisible()) {
				MultiTileEntityMassStorage tTileEntity = ((MultiTileEntityMassStorage)aTileEntity);
				
//              boolean tBlend = glGetBoolean(GL_BLEND);
//              boolean tLight = glGetBoolean(GL_LIGHTING);
				int tTexIndex = glGetInteger(GL_TEXTURE_BINDING_2D);
				
				glDisable(GL_BLEND);
				glDisable(GL_LIGHTING);
				
				glPushMatrix();
				
				glTranslated(aX+0.5+OFFX[tTileEntity.mFacing]*0.502-OFFZ[tTileEntity.mFacing]*0.25, aY+0.625, aZ+0.5+OFFZ[tTileEntity.mFacing]*0.502+OFFX[tTileEntity.mFacing]*0.25);
				glRotatef(180, 0, 0, 1);
				glRotatef(COMPASS_FROM_SIDE[tTileEntity.mFacing] * 90, 0, 1, 0);
				
				glScalef(1/256F, 1/256F, -0.0001F);
				glScalef(8.0F, 8.0F, 1.0F);
				
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
				
				try {
					if (!ForgeHooksClient.renderInventoryItem(RenderHelper.mRenderBlocks, Minecraft.getMinecraft().renderEngine, tTileEntity.slot(1), T, 0, 0, 0)) {
						RenderHelper.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, tTileEntity.slot(1), 0, 0, F);
					}
				} catch(Throwable e) {
					e.printStackTrace(ERR);
				}
				
				glPopMatrix();
				
				glColor4f(1, 1, 1, 1);
				glAlphaFunc(GL_GREATER, 0.1F);
				
				OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
				
//              if (tBlend) glEnable(GL_BLEND       ); else glDisable(GL_BLEND      );
//              if (tLight) glEnable(GL_LIGHTING    ); else glDisable(GL_LIGHTING   );
				
				glDisable(GL_BLEND);
				glEnable(GL_LIGHTING);
				glEnable(GL_ALPHA_TEST);
				
				glBindTexture(GL_TEXTURE_2D, tTexIndex);
			}
		}
	}
}
