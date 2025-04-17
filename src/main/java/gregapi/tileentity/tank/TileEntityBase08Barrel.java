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

package gregapi.tileentity.tank;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetMaxStackSize;
import gregapi.data.*;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.item.IItemRottable;
import gregapi.recipes.Recipe;
import gregapi.tileentity.ITileEntityConnectedTank;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase08Barrel extends TileEntityBase07Paintable implements IMTE_AddToolTips, IMTE_GetMaxStackSize, ITileEntityFunnelAccessible, ITileEntityTapAccessible, ITileEntityProgress, ITileEntityConnectedTank, IFluidHandler, IFluidContainerItem, IItemRottable {
	public FluidTankGT mTank = new FluidTankGT(16000);
	public byte mMode = 0;
	public long mSealedTime = 0, mMaxSealedTime = 0, mMeltingPoint = Long.MAX_VALUE;
	public Recipe mRecipe = null;
	public boolean mGasProof = F, mAcidProof = F, mPlasmaProof = F, mMagicProof = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_MAGICPROOF)) mMagicProof = aNBT.getBoolean(NBT_MAGICPROOF);
		if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		if (aNBT.hasKey(NBT_CAPACITY_HU)) mMeltingPoint = aNBT.getLong(NBT_CAPACITY_HU); else mMeltingPoint = (long)(mMaterial.mMeltingPoint * 1.25);
		mMode = aNBT.getByte(NBT_MODE);
		mSealedTime = aNBT.getLong(NBT_PROGRESS);
		mTank.setPreventDraining(keepsFilter()).setCapacity(aNBT.getLong(NBT_TANK_CAPACITY)).readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mSealedTime);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mSealedTime);
		mTank.writeToNBT(aNBT, NBT_TANK);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + mTank.contentcap());
		if (mTank.has() && (mMode & B[1]) != 0) aList.add(Chat.CYAN + "Sealed (" + mSealedTime + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_POWER_CONDUCTING_FLUIDS));
		if (onlySimple()) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ONLY_SIMPLE));
		if (mGasProof   ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof  ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_PLASMAPROOF));
		if (mMagicProof ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_MAGICPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + mMeltingPoint + " K)");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_softhammer)) {
			if (mTank.amount() <= 0) {
				mTank.setEmpty();
				mMaxSealedTime = 0;
				mSealedTime = 0;
				mMode &= +B[1];
				return 10000;
			}
			if (canBeSealed()) {
				mMode ^= B[1];
				aChatReturn.add((mMode & B[1]) == 0 ? "Normal" : "Sealed");
				mMaxSealedTime = 0;
				mSealedTime = 0;
			}
			return 10000;
		}
		if (aTool.equals(TOOL_plunger)) {
			mMaxSealedTime = 0;
			mSealedTime = 0;
			return GarbageGT.trash(mTank, 1000);
		}
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_monkeywrench)) {
			mMode ^= B[0];
			aChatReturn.add((mMode & B[0]) == 0 ? "Won't fill vertically adjacent Tanks" : "Will fill vertically adjacent Tanks (depending on Gravity and State of Matter)");
			updateClientData();
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_thermometer)) {if (aChatReturn != null) aChatReturn.add("Temperature: " + FL.temperature(mTank) + "K"); return 10000;}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add((mMode & B[0]) == 0 ? "Won't fill vertically adjacent Tanks" : "Will fill vertically adjacent Tanks (depending on Gravity and State of Matter)");
				aChatReturn.add(mTank.contentcap());
				if (!mTank.isEmpty() && (mMode & B[1]) != 0) {
					if (mMaxSealedTime > 0) {
						aChatReturn.add("Sealed (" + mSealedTime + " / " + mMaxSealedTime + ")");
					} else {
						aChatReturn.add("Sealed");
					}
				}
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			FluidStack tFluid = mTank.getFluid();
			if (tFluid != null && tFluid.amount > 0) {
				if (FL.temperature(tFluid) >= mMeltingPoint && meltdown()) return;
				
				if (!mMagicProof && FL.magic(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
					WD.set(worldObj, xCoord, yCoord, zCoord, FL.gas(tFluid) ? IL.TC_Flux_Gas.block() : IL.TC_Flux_Goo.block(), IL.TC_Flux_Goo.exists() ? 7 : 0, 3);
					return;
				}
				if (!mAcidProof && FL.acid(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
					setToAir();
					return;
				}
				if (!mPlasmaProof && FL.plasma(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
				} else
				if (!mGasProof && FL.gas(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
				} else
				if (!allowFluid(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
				} else {
					if ((mMode & B[1]) != 0) {
						if (mMaxSealedTime <= 0 || mRecipe == null) {
							mRecipe = RM.Fermenter.findRecipe(this, mRecipe, T, Long.MAX_VALUE, NI, FL.array(mTank.getFluid()), ST.tag(0));
							if (mRecipe != null && mRecipe.mFluidInputs.length > 0 && mRecipe.mFluidOutputs.length > 0 && !FL.gas(mRecipe.mFluidInputs[0]) && !FL.gas(mRecipe.mFluidOutputs[0])) {
								mMaxSealedTime = UT.Code.divup(Math.max(1, mRecipe.getAbsoluteTotalPower()) * Math.max(1, mTank.amount()), Math.max(1, mRecipe.mFluidInputs[0].amount));
							} else {
								mMaxSealedTime = 0;
								mSealedTime = 0;
								mRecipe = null;
							}
						} else {
							if (mSealedTime < mMaxSealedTime) {
								mSealedTime++;
							} else {
								mTank.setFluid(FL.mul(mRecipe.mFluidOutputs[0], mTank.amount(), mRecipe.mFluidInputs[0].amount, F));
								mMaxSealedTime = 0;
								mSealedTime = 0;
								mRecipe = null;
							}
						}
					} else {
						if ((mMode & B[0]) != 0) {
							byte[] tSides = ZL_BYTE;
							if (FL.gas(tFluid)) tSides = ALL_SIDES_VERTICAL; else if (FL.lighter(tFluid)) tSides = ALL_SIDES_TOP; else tSides = ALL_SIDES_BOTTOM;
							for (byte tSide : tSides) if (mTank.has() && FL.move(mTank, getAdjacentTank(tSide)) > 0) updateInventory();
						}
					}
				}
			}
		}
	}
	
	public boolean meltdown() {
		if (FL.lava(mTank) && mTank.has(1000)) {
			mTank.remove(1000);
			GarbageGT.trash(mTank);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.flowing_lava, 0, 3);
		} else {
			GarbageGT.trash(mTank);
			setToFire();
		}
		WD.burn(worldObj, getCoords(), F, F);
		return T;
	}
	
	public boolean allowFluid(FluidStack aFluid) {
		return !FL.powerconducting(aFluid) && FL.temperature(aFluid) < mMeltingPoint && (!onlySimple() || FL.simple(aFluid));
	}
	
	@Override
	public FluidStack getFluid(ItemStack aStack) {
		return mTank.getFluid();
	}
	
	@Override
	public int getCapacity(ItemStack aStack) {
		return mTank.getCapacity();
	}
	
	@Override
	public int fill(ItemStack aStack, FluidStack aFluid, boolean aDoFill) {
		if ((mMode & B[1]) != 0) return 0;
		if (!allowFluid(aFluid)) return 0;
		if (!mGasProof && FL.gas(aFluid)) return 0;
		if (!mAcidProof && FL.acid(aFluid)) return 0;
		if (!mMagicProof && FL.magic(aFluid)) return 0;
		if (!mPlasmaProof && FL.plasma(aFluid)) return 0;
		int tFilled = mTank.fill(aFluid, aDoFill);
		if (tFilled > 0 && aDoFill) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tFilled;
	}
	
	@Override
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		if ((mMode & B[1]) != 0) return null;
		FluidStack tDrained = mTank.drain(aMaxDrain, aDoDrain);
		if (tDrained != NF && aDoDrain) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tDrained;
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return (mMode & B[1]) != 0 ? 0 : mTank.fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		return (mMode & B[1]) != 0 ? null : mTank.drain(aMaxDrain, aDoDrain);
	}
	
	@Override public long getProgressValue(byte aSide) {return mSealedTime;}
	@Override public long getProgressMax(byte aSide) {return mMaxSealedTime;}
	@Override public boolean canDrop(int aSlot) {return F;}
	
	public boolean onlySimple() {return F;}
	public boolean canBeSealed() {return T;}
	public boolean keepsFilter() {return F;}
	public int getLogisticsPriorityFluid() {return mTank.isEmpty() ? 1 : 2;}
	public int getLogisticsPriorityItem() {return 0;}
	public Fluid getLogisticsFilterFluid() {return mTank.fluid();}
	public ItemStack getLogisticsFilterItem() {return null;}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return mTank.has() ? 1 : aDefault;}
	
	@Override protected IFluidTank getFluidTankFillable2 (byte aSide, FluidStack aFluidToFill ) {return (mMode & B[1]) != 0 ? null : mTank;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return (mMode & B[1]) != 0 ? null : mTank;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTank.AS_ARRAY;}
	
	@Override public ItemStack getRotten(ItemStack aStack) {return mMaterial.contains(TD.Properties.BETWEENLANDS) ? aStack : IItemRottable.RottingUtil.rotting(aStack, (IFluidContainerItem)aStack.getItem());}
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return mMaterial.contains(TD.Properties.BETWEENLANDS) ? aStack : IItemRottable.RottingUtil.rotting(aStack, (IFluidContainerItem)aStack.getItem());}
	
	@Override
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated) {
		if ((mMode & B[1]) != 0) return 0;
		if (aFluid == NF || (mTank.isEmpty() && aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated)) return 0;
		return mTank.fill(aFluid, T);
	}
	
	@Override
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if ((mMode & B[1]) == 0 && mTank.contains(aFluid) && mTank.has(aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return (int)mTank.remove(aFluid.amount);
		return 0;
	}
	
	@Override
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid) {
		return (mMode & B[1]) == 0 && mTank.contains(aFluid) ? mTank.amount() : 0;
	}
}
