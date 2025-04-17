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

package gregapi.tileentity.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.cover.CoverData;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ICover;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.FL;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.network.packets.covers.*;
import gregapi.network.packets.covervisuals.*;
import gregapi.network.packets.data.*;
import gregapi.network.packets.ids.*;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.tileentity.render.ITileEntityOnDrawBlockHighlight;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase06Covers extends TileEntityBase05Inventories implements ITileEntityCoverable, ISidedInventory, ITileEntitySurface, ITileEntityOnDrawBlockHighlight, IMTE_ShouldCheckWeakPower, IMTE_IsSealable, IMTE_OnWalkOver, IMTE_IsSideSolid, IMTE_SyncDataCovers, IMTE_OnToolClick, IMTE_AddCollisionBoxesToList, IMTE_IsProvidingWeakPower, IMTE_IsProvidingStrongPower {
	public CoverData mCovers = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_COVERS)) mCovers = CoverRegistry.coverdata(this, aNBT.getCompoundTag(NBT_COVERS));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (hasCovers()) aNBT.setTag(NBT_COVERS, mCovers.writeToNBT(UT.NBT.make(), T));
	}
	
	/** Writes eventual Item Data to the NBT. */
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {return aNBT;}
	
	@Override
	public final NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(writeItemNBT2(aNBT));
		if (hasCovers()) aNBT.setTag(NBT_COVERS, mCovers.writeToNBT(UT.NBT.make(), F));
		return aNBT;
	}
	
	@Override
	public boolean receiveDataCovers(short[] aCoverVisuals, boolean[] aVisualsToSync, INetworkHandler aNetworkHandler) {
		if (!hasCovers()) return F;
		for (byte i = 0; i < mCovers.mVisuals.length; i++) if (aVisualsToSync[i]) mCovers.mVisuals[i] = aCoverVisuals[i];
		return T;
	}
	
	@Override
	public boolean receiveDataCovers(short[] aCoverIDs, short[] aCoverMetas, INetworkHandler aNetworkHandler) {
		if (aCoverIDs == null) {
			mCovers = null;
		} else {
			if (mCovers == null) mCovers = CoverRegistry.coverdata(this, null);
			mCovers.setIDs(aCoverIDs, aCoverMetas);
		}
		return T;
	}
	
	@Override
	public final boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (SIDES_INVALID[aSide] || aPlayer == null || !allowInteraction(aPlayer)) return onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ);
		byte tSide = usePipePlacementMode(aSide)?UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ):aSide;
		if (hasCovers()) {
			if (mCovers.mBehaviours[aSide] != null) {
				if (mCovers.mBehaviours[aSide].onCoverClickedRight(aSide, mCovers, aPlayer, aSide, aHitX, aHitY, aHitZ)) return T;
				if (mCovers.mBehaviours[aSide].interceptClickRight(aSide, mCovers, aPlayer, aSide, aHitX, aHitY, aHitZ)) return F;
			} else if (mCovers.mBehaviours[tSide] != null) {
				if (mCovers.mBehaviours[tSide].onCoverClickedRight(tSide, mCovers, aPlayer, aSide, aHitX, aHitY, aHitZ)) return T;
				if (mCovers.mBehaviours[tSide].interceptClickRight(tSide, mCovers, aPlayer, aSide, aHitX, aHitY, aHitZ)) return F;
			}
		}
		if (attachCoversFirst(aSide)) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (aStack != null && aStack.stackSize > 0 && setCoverItem(tSide, aStack, aPlayer, F, T)) {
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
				return T;
			}
			return onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ);
		}
		if (onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ)) return T;
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		if (aStack != null && aStack.stackSize > 0 && setCoverItem(tSide, aStack, aPlayer, F, T)) {
			if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
			return T;
		}
		return F;
	}
	
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return F;
	}
	
	@Override
	public final long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!allowInteraction(aPlayer)) return 0;
		if (checkObstruction(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, aSide, aHitX, aHitY, aHitZ)) return 0;
		if (SIDES_VALID[aSide] && hasCovers()) {
			byte tSide = usePipePlacementMode(aSide) && mCovers.mIDs[aSide] == 0 ? UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ) : aSide;
			if (aTool.equals(TOOL_crowbar) && isServerSide()) {
				ItemStack tStack = getCoverItem(tSide);
				ICover tCover = mCovers.mBehaviours[tSide];
				if (tStack != null && setCoverItem(tSide, null, aPlayer, F, T)) {
					if (!(aPlayer instanceof EntityPlayer) || !UT.Inventories.addStackToPlayerInventory((EntityPlayer)aPlayer, tStack, F)) ST.place(worldObj, getOffsetX(aSide)+0.5, getOffsetY(aSide)+0.5, getOffsetZ(aSide)+0.5, tStack);
					if (tCover != null) tCover.onAfterCrowbar(this);
					return 10000;
				}
			}
			if (mCovers.mBehaviours[tSide] != null) {
				if (tSide != aSide) {
					long rDamage = onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
					if (rDamage > 0) return rDamage;
				}
				return mCovers.mBehaviours[tSide].onToolClick(tSide, mCovers, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
			}
		}
		return onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return 0;
	}
	
	@Override public IPacket getClientDataPacket(boolean aSendAll)                              {return aSendAll ? hasCovers() ? new PacketSyncDataIDsAndCovers             (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID()                , mCovers) : new PacketSyncDataIDs              (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID()                ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataCoverVisuals              (getCoords()                , mCovers) : null;}
	@Override public IPacket getClientDataPacketByte(boolean aSendAll, byte aByte)              {return aSendAll ? hasCovers() ? new PacketSyncDataByteAndIDsAndCovers      (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aByte         , mCovers) : new PacketSyncDataByteAndIDs       (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aByte         ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataByteAndCoverVisuals       (getCoords(), aByte         , mCovers) : new PacketSyncDataByte         (getCoords(), aByte         );}
	@Override public IPacket getClientDataPacketShort(boolean aSendAll, short aShort)           {return aSendAll ? hasCovers() ? new PacketSyncDataShortAndIDsAndCovers     (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aShort        , mCovers) : new PacketSyncDataShortAndIDs      (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aShort        ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataShortAndCoverVisuals      (getCoords(), aShort        , mCovers) : new PacketSyncDataShort        (getCoords(), aShort        );}
	@Override public IPacket getClientDataPacketInteger(boolean aSendAll, int aInteger)         {return aSendAll ? hasCovers() ? new PacketSyncDataIntegerAndIDsAndCovers   (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aInteger      , mCovers) : new PacketSyncDataIntegerAndIDs    (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aInteger      ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataIntegerAndCoverVisuals    (getCoords(), aInteger      , mCovers) : new PacketSyncDataInteger      (getCoords(), aInteger      );}
	@Override public IPacket getClientDataPacketLong(boolean aSendAll, long aLong)              {return aSendAll ? hasCovers() ? new PacketSyncDataLongAndIDsAndCovers      (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aLong         , mCovers) : new PacketSyncDataLongAndIDs       (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aLong         ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataLongAndCoverVisuals       (getCoords(), aLong         , mCovers) : new PacketSyncDataLong         (getCoords(), aLong         );}
	@Override public IPacket getClientDataPacketByteArray(boolean aSendAll, byte... aByteArray) {return aSendAll ? hasCovers() ? new PacketSyncDataByteArrayAndIDsAndCovers (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aByteArray    , mCovers) : new PacketSyncDataByteArrayAndIDs  (getCoords(), getMultiTileEntityRegistryID(), getMultiTileEntityID(), aByteArray    ) : hasCovers() && mCovers.requiresSync() ? new PacketSyncDataByteArrayAndCoverVisuals  (getCoords(), aByteArray    , mCovers) : new PacketSyncDataByteArray    (getCoords(), aByteArray    );}
	
	@Override
	public void onTickChecked(long aTimer) {
		super.onTickChecked(aTimer);
		if (hasCovers()) mCovers.resetSync();
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return (hasCovers() && mCovers.requiresSync()) || super.onTickCheck(aTimer);
	}
	
	@Override
	public final void onTickFirst(boolean aIsServerSide) {
		super.onTickFirst(aIsServerSide);
		checkCoverValidity();
		onTickFirst2(aIsServerSide);
	}
	
	public void onTickFirst2(boolean aIsServerSide) {/**/}
	
	@Override
	public final void onTick(long aTimer, boolean aIsServerSide) {
		super.onTick(aTimer, aIsServerSide);
		if (hasCovers()) mCovers.tickPre (aTimer, aIsServerSide, mBlockUpdated, mInventoryChanged);
		onTick2(aTimer, aIsServerSide);
		if (hasCovers()) mCovers.tickPost(aTimer, aIsServerSide, mBlockUpdated, mInventoryChanged);
	}
	
	public void onTick2(long aTimer, boolean aIsServerSide) {/**/}
	
	public void checkCoverValidity() {
		if (worldObj != null && isServerSide() && hasCovers()) for (byte tSide : ALL_SIDES_VALID) if (!allowCovers(tSide)) {
			ItemStack tStack = getCoverItem(tSide);
			if (setCoverItem(tSide, null, null, T, T)) {
				ST.place(worldObj, getOffset(tSide, 1), tStack);
				UT.Sounds.send(SFX.MC_BREAK, 1.0F, -1.0F, this, F);
			}
		}
	}
	
	@Override public boolean isFireProof        (byte aSide) {return hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isOpaque(aSide, mCovers);}
	@Override public boolean isRainProof        (byte aSide) {return hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isOpaque(aSide, mCovers);}
	@Override public boolean isWaterProof       (byte aSide) {return hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isOpaque(aSide, mCovers);}
	@Override public boolean isThunderProof     (byte aSide) {return hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isOpaque(aSide, mCovers);}
	
	public boolean allowCover(byte aSide, ICover aCover) {
		return allowCovers(aSide);
	}
	
	public boolean allowCovers(byte aSide) {
		return T;
	}
	
	public boolean allowCoverHolders(byte aSide) {
		return T;
	}
	
	public boolean attachCoversFirst(byte aSide) {
		return T;
	}
	
	public boolean isCoverSurface(byte aSide) {
		return getSurfaceSize(aSide) == 1.0F && getSurfaceDistance(aSide) == 0.0F;
	}
	
	public boolean isCoverSurface(byte aSide, int aRenderpass) {
		return isCoverSurface(aSide);
	}
	
	public boolean hasCovers() {
		return mCovers != null;
	}
	
	public boolean isCovered(byte aSide) {
		return hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null;
	}
	
	public boolean usePipePlacementMode(byte aSide) {
		return F;
	}
	
	@Override
	public void updateCoverVisuals() {
		//
	}
	
	@Override
	public boolean canTick() {
		return T;
	}
	
	@Override
	public void openCoverGUI(byte aSide, EntityPlayer aPlayer) {
		openGUI(aPlayer, -aSide-1);
	}
	
	@Override
	public CoverData getCoverData() {
		return hasCovers() ? mCovers : null;
	}
	
	@Override
	public ItemStack getCoverItem(byte aSide) {
		return hasCovers() && SIDES_VALID[aSide] ? mCovers.getCoverItem(aSide) : null;
	}
	
	@Override
	public boolean setCoverItem(byte aSide, ItemStack aStack, Entity aPlayer, boolean aForce, boolean aBlockUpdate) {
		if (SIDES_INVALID[aSide] || (!allowCovers(aSide) && aStack != null)) return F;
		if (aStack == null && getCoverItem(aSide) == null) return F;
		
		if (mCovers == null) mCovers = CoverRegistry.coverdata(this, null);
		
		if (!aForce) {
			if (aStack == null) {
				if (mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptCoverRemoval(aSide, mCovers, aPlayer)) return !checkIfCoversEmptyAndDeleteIfNeeded();
			} else {
				if (mCovers.mBehaviours[aSide] != null) return !checkIfCoversEmptyAndDeleteIfNeeded();
				ICover tCover = CoverRegistry.get(aStack);
				if (tCover == null || !allowCover(aSide, tCover) || tCover.interceptCoverPlacement(aSide, mCovers, aPlayer)) return !checkIfCoversEmptyAndDeleteIfNeeded();
			}
		}
		
		if (mCovers.mBehaviours[aSide] != null) mCovers.mBehaviours[aSide].onCoverRemove(aSide, mCovers, aPlayer);
		
		mCovers.set(aSide, aStack);
		
		if (mCovers.mBehaviours[aSide] != null) mCovers.mBehaviours[aSide].onCoverPlaced(aSide, mCovers, aPlayer, aStack);
		
		if (aBlockUpdate) causeBlockUpdate();
		
		updateClientData();
		
		return checkIfCoversEmptyAndDeleteIfNeeded();
	}
	
	private boolean checkIfCoversEmptyAndDeleteIfNeeded() {
		for (byte tSide : ALL_SIDES_VALID) if (mCovers.mBehaviours[tSide] != null) return T;
		mCovers = null;
		return T;
	}
	
	@Override
	public final int[] getAccessibleSlotsFromSide(int aSide) {
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].getAccessibleSlotsFromSideOverride(UT.Code.side(aSide), mCovers, UT.Code.side(aSide))) return mCovers.mBehaviours[aSide].getAccessibleSlotsFromSide(UT.Code.side(aSide), mCovers, UT.Code.side(aSide), getAccessibleSlotsFromSide2(UT.Code.side(aSide)));
		return getAccessibleSlotsFromSide2(UT.Code.side(aSide));
	}
	
	@Override
	public final boolean canInsertItem(int aSlot, ItemStack aStack, int aSide) {
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null) {
			if (mCovers.mBehaviours[aSide].interceptItemInsert(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide))) return F;
			if (mCovers.mBehaviours[aSide].canInsertItemOverride(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide))) return mCovers.mBehaviours[aSide].canInsertItem(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide)) && canInsertItem2(aSlot, aStack, UT.Code.side(aSide));
		}
		return canInsertItem2(aSlot, aStack, UT.Code.side(aSide));
	}
	
	@Override
	public final boolean canExtractItem(int aSlot, ItemStack aStack, int aSide) {
		if (ST.invalid(aStack)) aStack = slot(aSlot);
		if (ST.debug(aStack)) return F;
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null) {
			if (mCovers.mBehaviours[aSide].interceptItemExtract(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide))) return F;
			if (mCovers.mBehaviours[aSide].canExtractItemOverride(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide))) return mCovers.mBehaviours[aSide].canExtractItem(UT.Code.side(aSide), mCovers, aSlot, aStack, UT.Code.side(aSide)) && canExtractItem2(aSlot, aStack, UT.Code.side(aSide));
		}
		return canExtractItem2(aSlot, aStack, UT.Code.side(aSide));
	}
	
	@Override
	protected final IFluidTank getFluidTankFillable(byte aSide, FluidStack aFluidToFill) {
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null) {
			if (mCovers.mBehaviours[aSide].getFluidTankFillableOverride(aSide, mCovers, aSide, aFluidToFill)) return mCovers.mBehaviours[aSide].getFluidTankFillable(aSide, mCovers, aSide, aFluidToFill, getFluidTankFillable2(aSide, aFluidToFill));
			if (mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, aFluidToFill)) return null;
		}
		return getFluidTankFillable2(aSide, aFluidToFill);
	}
	
	@Override
	protected final IFluidTank getFluidTankDrainable(byte aSide, FluidStack aFluidToDrain) {
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null) {
			if (mCovers.mBehaviours[aSide].getFluidTankDrainableOverride(aSide, mCovers, aSide, aFluidToDrain)) return mCovers.mBehaviours[aSide].getFluidTankDrainable(aSide, mCovers, aSide, aFluidToDrain, getFluidTankDrainable2(aSide, aFluidToDrain));
			if (aFluidToDrain == null) {
				IFluidTank rTank = getFluidTankDrainable2(aSide, null);
				if (rTank == null) return null;
				if (mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, FL.mul(rTank.getFluid(), 1))) return null;
				return rTank;
			}
			if (mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, aFluidToDrain)) return null;
		}
		return getFluidTankDrainable2(aSide, aFluidToDrain);
	}
	
	@Override
	protected final IFluidTank[] getFluidTanks(byte aSide) {
		if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].getFluidTanksOverride(aSide, mCovers, aSide)) return mCovers.mBehaviours[aSide].getFluidTanks(aSide, mCovers, aSide, getFluidTanks2(aSide));
		return getFluidTanks2(aSide);
	}
	
	public int[] getAccessibleSlotsFromSide2(byte aSide) {return UT.Code.getAscendingArray(getSizeInventory());}
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return null;}
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return null;}
	protected IFluidTank[] getFluidTanks2(byte aSide) {return ZL_FT;}
	
	@Override public void onNeighborBlockChange(World aWorld, Block aBlock) {super.onNeighborBlockChange(aWorld, aBlock); if (hasCovers()) mCovers.onBlockUpdate();}
	@Override public void receiveBlockUpdateFromCover() {mBlockUpdated = T; if (hasCovers()) mCovers.onBlockUpdate();}
	@Override public void sendBlockUpdateFromCover() {causeBlockUpdate();}
	
	@Override
	public final byte getRedstoneIncoming(byte aSide) {
		if (!hasCovers()) return super.getRedstoneIncoming(aSide);
		if (SIDES_INVALID[aSide]) {
			byte rRedstone = 0;
			for (byte tSide : ALL_SIDES_VALID) {
				if (mCovers.mBehaviours[tSide] == null) {
					rRedstone = (byte)Math.max(rRedstone, worldObj.getIndirectPowerLevelTo(getOffsetX(tSide), getOffsetY(tSide), getOffsetZ(tSide), tSide));
				} else {
					rRedstone = (byte)Math.max(rRedstone, mCovers.mBehaviours[tSide].getRedstoneIn(tSide, mCovers));
				}
				if (rRedstone >= 15) return 15;
			}
			return rRedstone;
		}
		return mCovers.mBehaviours[aSide] == null ? UT.Code.bind4(worldObj.getIndirectPowerLevelTo(getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), aSide)) : mCovers.mBehaviours[aSide].getRedstoneIn(aSide, mCovers);
	}
	
	@Override
	public final int isProvidingWeakPower(byte aOppositeSide) {
		byte tActualSide = OPOS[aOppositeSide];
		if (hasCovers() && SIDES_VALID[tActualSide] && mCovers.mBehaviours[tActualSide] != null) return mCovers.mBehaviours[tActualSide].getRedstoneOutWeak(tActualSide, mCovers, isProvidingWeakPower2(aOppositeSide));
		return isProvidingWeakPower2(aOppositeSide);
	}
	
	@Override
	public final int isProvidingStrongPower(byte aOppositeSide) {
		byte tActualSide = OPOS[aOppositeSide];
		if (hasCovers() && SIDES_VALID[tActualSide] && mCovers.mBehaviours[tActualSide] != null) return mCovers.mBehaviours[tActualSide].getRedstoneOutStrong(tActualSide, mCovers, isProvidingStrongPower2(aOppositeSide));
		return isProvidingStrongPower2(aOppositeSide);
	}
	
	public byte isProvidingWeakPower2(byte aSide) {return 0;}
	public byte isProvidingStrongPower2(byte aSide) {return 0;}
	
	@Override public final boolean isSealable       (byte aSide) {return (hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isSealable    (aSide, mCovers)) || isSealable2(aSide);}
	@Override public final boolean isSideSolid      (byte aSide) {return (hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isSolid       (aSide, mCovers)) || isSideSolid2(aSide);}
	@Override public final boolean isSurfaceOpaque  (byte aSide) {return (hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isOpaque      (aSide, mCovers)) || isSurfaceOpaque2(aSide);}
	public boolean isSealable2      (byte aSide) {return F;}
	public boolean isSideSolid2     (byte aSide) {return isSurfaceSolid(aSide);}
	public boolean isSurfaceOpaque2 (byte aSide) {return T;}
	
	@Override public final void onWalkOver(EntityLivingBase aEntity) {if (!hasCovers() || mCovers.mBehaviours[SIDE_TOP] == null || !mCovers.mBehaviours[SIDE_TOP].onWalkOver(SIDE_TOP, mCovers, aEntity)) onWalkOver2(aEntity);}
	public void onWalkOver2(EntityLivingBase aEntity) {/**/}
	
	private int mInternalRenderPasses = 0;
	
	@Override
	public final int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		return (mInternalRenderPasses = getRenderPasses2(aBlock, aShouldSideBeRendered)) + (hasCovers() ? 12 : 0);
	}
	
	@Override
	public final boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {
		return aRenderPass < mInternalRenderPasses ? usesRenderPass2(aRenderPass, aShouldSideBeRendered) : mCovers.mBehaviours[(aRenderPass-mInternalRenderPasses)/2] != null && ((aRenderPass-mInternalRenderPasses)%2!=0 || allowCoverHolders((byte)((aRenderPass-mInternalRenderPasses)/2))) && !isCoverSurface((byte)((aRenderPass-mInternalRenderPasses)/2));
	}
	
	@Override
	public final boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < mInternalRenderPasses) return setBlockBounds2(aBlock, aRenderPass, aShouldSideBeRendered);
		int tRenderPass = aRenderPass-mInternalRenderPasses;
		return box(aBlock, tRenderPass % 2 == 0 ? mCovers.mBehaviours[tRenderPass/2].getHolderBounds((byte)(tRenderPass/2), mCovers) : mCovers.mBehaviours[tRenderPass/2].getCoverBounds((byte)(tRenderPass/2), mCovers));
	}
	
	@Override
	public final ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < mInternalRenderPasses) {
			if (hasCovers() && mCovers.mBehaviours[aSide] != null && isCoverSurface(aSide, aRenderPass)) return BlockTextureMulti.get(getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered), mCovers.mBehaviours[aSide].getCoverTextureSurface(aSide, mCovers));
			return getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered);
		}
		int tRenderPass = aRenderPass-mInternalRenderPasses;
		if (tRenderPass % 2 == 0) {
			tRenderPass /= 2;
			return ALONG_AXIS[aSide][tRenderPass] ? null : mCovers.mBehaviours[tRenderPass].getCoverTextureHolder((byte)tRenderPass, mCovers, aSide);
		}
		tRenderPass /= 2;
		return !ALONG_AXIS[aSide][tRenderPass] && ((mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].isFullTexture(aSide, mCovers)) || isCoverSurface(aSide, aRenderPass)) ? null : aSide == OPOS[tRenderPass] || aShouldSideBeRendered[aSide] ? mCovers.mBehaviours[tRenderPass].getCoverTextureAttachment((byte)tRenderPass, mCovers, aSide) : null;
	}
	
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	public abstract ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered);
	
	@Override
	public final void addCollisionBoxesToList(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		if (addDefaultCollisionBoxToList()) box(getCollisionBoundingBoxFromPool(), aAABB, aList);
		if (hasCovers()) for (byte tSide : ALL_SIDES_VALID) if (mCovers.mBehaviours[tSide] != null) mCovers.mBehaviours[tSide].getCollisions(tSide, mCovers, aAABB, aList, aEntity);
		addCollisionBoxesToList2(aAABB, aList, aEntity);
	}
	
	public boolean addDefaultCollisionBoxToList() {return T;}
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {/**/}
	
	// GUI Stuff
	@SideOnly(Side.CLIENT)
	@Override public final Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return aGUIID <= -1 && aGUIID >= -6 ? hasCovers() && mCovers.mBehaviours[-aGUIID-1] != null ? mCovers.mBehaviours[-aGUIID-1].getGUIServer((byte)(-aGUIID-1), mCovers, aPlayer) : null : getGUIClient2(aGUIID, aPlayer);}
	@Override public final Object getGUIServer(int aGUIID, EntityPlayer aPlayer) {return aGUIID <= -1 && aGUIID >= -6 ? hasCovers() && mCovers.mBehaviours[-aGUIID-1] != null ? mCovers.mBehaviours[-aGUIID-1].getGUIClient((byte)(-aGUIID-1), mCovers, aPlayer) : null : getGUIServer2(aGUIID, aPlayer);}
	@SideOnly(Side.CLIENT)
	public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return null;}
	public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return null;}
	
	@Override
	public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {
		if (ToolsGT.contains(TOOL_magnifyingglass, aStack)) return T;
		if (!usePipePlacementMode(aSide)) return F;
		if (CoverRegistry.get(aStack) != null) return T;
		if (hasCovers() && mCovers.mBehaviours[aSide] != null) return F;
		return ToolsGT.contains(TOOL_crowbar, aStack);
	}
}
