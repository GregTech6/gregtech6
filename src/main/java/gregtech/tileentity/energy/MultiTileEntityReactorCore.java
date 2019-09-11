/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
public class MultiTileEntityReactorCore extends TileEntityBase10FacingDouble implements IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	protected boolean mRunning = F, oRunning = F;
	protected FluidTankGT[] mTanks = {new FluidTankGT(16000), new FluidTankGT(16000)};
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mRunning = aNBT.getBoolean(NBT_ACTIVE);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mRunning);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
	}
	
	static {
		LH.add("gt.tooltip.reactorcore.1", "Primary Facing Emits Hot Coolant.");
		LH.add("gt.tooltip.reactorcore.2", "Secondary Facing Emits Cold Coolant when nearly full.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.2"));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			
			
			
			if (mTanks[1].has()) FL.move(delegator(mFacing), getAdjacentTank(mFacing));
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add("Input: "  + mTanks[0].content());
				aChatReturn.add("Output: " + mTanks[1].content());
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mRunning != oRunning || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oRunning = mRunning;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mRunning = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)(mRunning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return FL.Coolant_IC2.is(aFluidToFill) ? mTanks[0] : null;
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
		if (!FL.Coolant_IC2.is(aFluid)) return 0;
		updateInventory();
		return mTanks[0].fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTanks[mTanks[1].has() ? 1 : 0].drain(aMaxDrain, aDoDrain);
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return null;
	}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mRunning) {UT.Entities.applyHeatDamage(aEntity, 5); UT.Entities.applyRadioactivity(aEntity, 3, 1);}}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], PX_P[1], PX_P[1], PX_N[1], PX_N[1], PX_N[1]);}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean getStateRunningPassively() {return mRunning;}
	@Override public boolean getStateRunningPossible() {return mRunning;}
	@Override public boolean getStateRunningActively() {return mRunning;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";}
}
