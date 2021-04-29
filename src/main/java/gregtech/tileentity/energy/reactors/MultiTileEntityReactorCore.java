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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.fluid.FluidTankGT;
import gregapi.item.IItemReactorRod;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityReactorCore extends TileEntityBase10FacingDouble implements ITileEntityServerTickPost, IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityRunningActively, ITileEntitySwitchableOnOff, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	public int[] mNeutronCounts = new int[]{0, 0, 0, 0};
	public int[] oNeutronCounts = new int[]{0, 0, 0, 0};
	public long mEnergy = 0, oEnergy = 0;
	public byte mMode = 0;
	public boolean mRunning = F, mStopped = F;
	public FluidTankGT[] mTanks = {new FluidTankGT(64000), new FluidTankGT(64000)};

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mMode = aNBT.getByte(NBT_MODE);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mRunning = aNBT.getBoolean(NBT_ACTIVE);
		mStopped = aNBT.getBoolean(NBT_STOPPED);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");
		for (int i = 0; i < 4; i++) {
			mNeutronCounts[i] = aNBT.getInteger(NBT_VALUE+".m."+i);
			oNeutronCounts[i] = aNBT.getInteger(NBT_VALUE+".o."+i);
		}
		
		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mRunning);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
		for (int i = 0; i < 4; i++) {
			UT.NBT.setNumber(aNBT, NBT_VALUE+".m."+i, mNeutronCounts[i]);
			UT.NBT.setNumber(aNBT, NBT_VALUE+".o."+i, oNeutronCounts[i]);
		}
	}
	
	static {
		LH.add("gt.tooltip.reactorcore.1", "Reactor Slots can be accessed from the Top and Bottom Side.");
		LH.add("gt.tooltip.reactorcore.2", "Accessing a reactor slot is only possible when the slot or the reactor is off.");
		LH.add("gt.tooltip.reactorcore.3", "Primary Facing Emits Hot Coolant.");
		LH.add("gt.tooltip.reactorcore.4", "Secondary Facing Emits Cold Coolant when over half full.");
		LH.add("gt.tooltip.reactorcore.5", "Choice of Coolant can have special Effects.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.2"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.3"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.4"));
		aList.add(Chat.GREEN    + LH.get("gt.tooltip.reactorcore.5"));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TAKE_PINCERS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_GEIGER_COUNTER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_THERMOMETER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mHasToAddTimer) {
				GT_API_Proxy.SERVER_TICK_POST.add(this);
				GT_API_Proxy.SERVER_TICK_PO2T.add(this);
				mHasToAddTimer = F;
			}
			if (mTanks[0].isHalf()) FL.move(mTanks[0], getAdjacentTank(mSecondFacing), mTanks[0].amount() - mTanks[0].capacity() / 2);
			if (mTanks[1].has()) FL.move(mTanks[1], getAdjacentTank(mFacing));
			
			if (mTanks[0].check()) updateClientData();
		}
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_POST.remove(this);
		GT_API_Proxy.SERVER_TICK_PO2T.remove(this);
		onUnregisterPost();
	}
	
	public static final int[] S2103 = new int[] {0,0,2,1,0,3,0}, S0312 = new int[] {0,0,0,3,1,2,0};
	
	// 0 and 2 are at SIDE_Z_NEG    1 3      -->X+
	// 1 and 3 are at SIDE_Z_POS  2|0 2|0   |0 2
	// 0 and 1 are at SIDE_X_NEG  3|1 3|1   v1 3
	// 2 and 3 are at SIDE_X_POS    0 2     Z+

	public boolean isReactorRodModerated(int aSlot) {
		return false;
	}

	public void updateReactorRodModeration(int aSlot) {/**/}

	public int getReactorRodNeutronEmission(int aSlot) {
		return 0;
	}
	
	public boolean getReactorRodNeutronReaction(int aSlot) {
		return F;
	}
	
	public int getReactorRodNeutronReflection(int aSlot, int aNeutrons, boolean aModerated) {
		return 0;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_softhammer)) {
			mStopped = !mStopped;
			if (aChatReturn != null) {
				aChatReturn.add(mStopped?"Reactor Block is OFF":"Reactor Block is ON");
			}
			return 10000;
		}
		if (aTool.equals(TOOL_thermometer)) {
			if (aChatReturn != null) {
				aChatReturn.add("Heat Levels: " + (oEnergy <= 0 ? "None" : oEnergy + " HU"));
			}
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add("Input: "  + mTanks[0].content());
				aChatReturn.add("Output: " + mTanks[1].content());
				aChatReturn.add(mStopped?"Reactor Block is OFF":"Reactor Block is ON");
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide]) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (ST.item(aStack) instanceof IItemReactorRod && ((IItemReactorRod)ST.item_(aStack)).isReactorRod(aStack)) {
				int tSlot = aHitX < 0.5 ? aHitZ < 0.5 ? 0 : 1 : aHitZ < 0.5 ? 2 : 3;
				if (!slotHas(tSlot) && ST.use(aPlayer, aStack)) {
					slot(tSlot, ST.amount(1, aStack));
					mStopped = T;
					UT.Sounds.send(SFX.MC_CLICK, this);
					updateClientData();
				}
			}
		}
		return T;
	}
	
	protected byte oVisual = 0;
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return oVisual != getVisualData() || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oVisual = getVisualData();
	}
	
	@Override
	public void setVisualData(byte aData) {
		mStopped = ((aData & 32) != 0);
		mRunning = ((aData & 16) != 0);
		mMode = (byte)(aData & 15);
	}
	
	@Override public byte getVisualData() {return (byte)(mStopped?mRunning?mMode|48:mMode|32:mRunning?mMode|16:mMode);}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public byte getDefaultSecondSide() {return SIDE_BOTTOM;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return FL.Coolant_IC2.is(aFluidToFill)
			|| FL.distw(aFluidToFill)
			|| FL.Thorium_Salt.is(aFluidToFill)
			|| MT.Sn.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.Na.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.HDO.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.D2O.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.T2O.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.LiCl.mLiquid.isFluidEqual(aFluidToFill)
			|| MT.He.mGas.isFluidEqual(aFluidToFill)
			|| MT.CO2.mGas.isFluidEqual(aFluidToFill)
			? mTanks[0] : null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTanks[1];
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTanks;
	}


	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		if (!( FL.Coolant_IC2.is(aFluid)
			|| FL.distw(aFluid)
			|| FL.Thorium_Salt.is(aFluid)
			|| MT.Sn.mLiquid.isFluidEqual(aFluid)
			|| MT.Na.mLiquid.isFluidEqual(aFluid)
			|| MT.HDO.mLiquid.isFluidEqual(aFluid)
			|| MT.D2O.mLiquid.isFluidEqual(aFluid)
			|| MT.T2O.mLiquid.isFluidEqual(aFluid)
			|| MT.LiCl.mLiquid.isFluidEqual(aFluid)
			|| MT.He.mGas.isFluidEqual(aFluid)
			|| MT.CO2.mGas.isFluidEqual(aFluid)
			)) return 0;
		updateInventory();
		return mTanks[0].fill(aFluid, aDoFill);
	}

	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTanks[mTanks[1].has() ? 1 : 0].drain(aMaxDrain, aDoDrain);
	}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mRunning) {UT.Entities.applyHeatDamage(aEntity, 5); UT.Entities.applyRadioactivity(aEntity, 3, 1);}}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], PX_P[1], PX_P[1], PX_N[1], PX_N[1], PX_N[1]);}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public int getInventoryStackLimit() {return 1;}
	
	@Override public boolean getStateRunningPassively() {return mRunning;}
	@Override public boolean getStateRunningPossible() {return mRunning;}
	@Override public boolean getStateRunningActively() {return mRunning;}
	@Override public boolean setStateOnOff(boolean aOnOff) {return mStopped = !aOnOff;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	public byte setStateMode(byte aMode) {return mMode = aMode;}
	public byte getStateMode() {return mMode;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";}
}
