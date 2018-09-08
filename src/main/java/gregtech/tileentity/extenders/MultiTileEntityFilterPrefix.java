package gregtech.tileentity.extenders;

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ItemStackContainer;
import gregapi.data.TD;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Holo;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictPrefix;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 * 
 * An example implementation of a Miniature Nether Portal with my MultiTileEntity System.
 */
public class MultiTileEntityFilterPrefix extends MultiTileEntityExtender {
	public OreDictPrefix mFilter = null;
	public ItemStack mCycle = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		mFilter = OreDictPrefix.sPrefixes.get(aNBT.getString(NBT_INV_FILTER));
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		if (mFilter != null) aNBT.setString(NBT_INV_FILTER, mFilter.mNameInternal);
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (mFilter != null) aNBT.setString(NBT_INV_FILTER, mFilter.mNameInternal);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && aTimer % 10 == 1) {
			if (mFilter == null || mFilter.mRegisteredItems.isEmpty()) {
				mCycle = null;
			} else {
				mCycle = ST.make(UT.Code.select(rng(mFilter.mRegisteredItems.size()), null, mFilter.mRegisteredItems.toArray(new ItemStackContainer[mFilter.mRegisteredItems.size()])), mFilter.mNameInternal, null);
				if (mCycle != null && ST.meta(mCycle) == W) ST.meta(mCycle, 0);
			}
		}
	}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new MultiTileEntityGUIClientFilterPrefix(aPlayer.inventory, this);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new MultiTileEntityGUICommonFilterPrefix(aPlayer.inventory, this);}
	@Override public int getSizeInventoryGUI() {return 1;}
	@Override public ItemStack getStackInSlotGUI(int aSlot) {return mCycle;}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement) {return null;}
	@Override public ItemStack getStackInSlotOnClosingGUI(int aSlot) {return null;}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack) {mCycle = aStack;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return 1;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.filter.prefix";}
	
	public boolean allowInput(ItemStack aStack) {
		return mFilter != null && mFilter.contains(aStack);
	}
	public boolean allowInput(FluidStack aFluid) {
		return T;
	}
	public boolean allowInput(Fluid aFluid) {
		return T;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}
	
	@Override
	public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide == mFacing || allowInput(aStack))) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof IInventory) return ((IInventory)tTileEntity.mTileEntity).isItemValidForSlot(aSlot, aStack);
		}
		return F;
	}
	
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide == mFacing || allowInput(aStack))) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canInsertItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity instanceof IInventory) return T;
		}
		return F;
	}
	
	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide != mFacing || allowInput(aStack))) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canExtractItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity instanceof IInventory) return T;
		}
		return F;
	}
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, aFluid)) return 0;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.fill(tTileEntity.getForgeSideOfTileEntity(), aFluid, aDoFill);
		}
		return 0;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean aDoDrain) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide != mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, aFluid)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aFluid, aDoDrain);
		}
		return null;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, int aToDrain, boolean aDoDrain) {
		if ((mModes & MODE_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, null)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return (aSide != mFacing || allowInput(tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aToDrain, F))) ? tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aToDrain, aDoDrain) : null;
		}
		return null;
	}
	
	@Override
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, UT.Fluids.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canFill(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	@Override
	public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide != mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, UT.Fluids.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canDrain(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	
	public class MultiTileEntityGUICommonFilterPrefix extends ContainerCommon {
		public MultiTileEntityGUICommonFilterPrefix(InventoryPlayer aInventoryPlayer, MultiTileEntityFilterPrefix aTileEntity) {
			super(aInventoryPlayer, aTileEntity);
		}
		
		@Override
		public int addSlots(InventoryPlayer aPlayerInventory) {
			addSlotToContainer(new Slot_Holo(mTileEntity, mOffset, 80, 35, F, F, 1));
			return 84;
		}
		
		@Override
		public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
			if (aSlotIndex < 0 || aSlotIndex >= mTileEntity.getSizeInventoryGUI()) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
			
			ItemStack tStack = aPlayer.inventory.getItemStack();
			if (tStack == null) {
				((MultiTileEntityFilterPrefix)mTileEntity).mFilter = null;
			} else {
				OreDictItemData tData = OM.anyassociation_(tStack);
				if (tData == null || ((MultiTileEntityFilterPrefix)mTileEntity).mFilter == tData.mPrefix) {
					for (OreDictPrefix tPrefix : OreDictPrefix.VALUES_SORTED) if (((MultiTileEntityFilterPrefix)mTileEntity).mFilter != tPrefix && !tPrefix.containsAny(TD.Prefix.UNIFICATABLE, TD.Prefix.NO_PREFIX_FILTERING) && tPrefix.contains(tStack)) {
						((MultiTileEntityFilterPrefix)mTileEntity).mFilter = tPrefix;
						break;
					}
				} else {
					((MultiTileEntityFilterPrefix)mTileEntity).mFilter = tData.mPrefix;
				}
			}
			return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientFilterPrefix extends ContainerClient {
		public MultiTileEntityGUIClientFilterPrefix(InventoryPlayer aInventoryPlayer, MultiTileEntityFilterPrefix aTileEntity) {
			super(new MultiTileEntityGUICommonFilterPrefix(aInventoryPlayer, aTileEntity), RES_PATH_GUI + "machines/FilterPrefix.png");
		}
	}
}