package gregapi.tileentity.tank;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetMaxStackSize;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.RM;
import gregapi.data.TD;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase08Barrel extends TileEntityBase07Paintable implements IMTE_AddToolTips, IMTE_GetMaxStackSize, ITileEntityFunnelAccessible, ITileEntityTapAccessible, ITileEntityProgress, ITileEntityConnectedTank, IFluidHandler, IFluidContainerItem, IItemRottable {
	public FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(16000)};
	public byte mMode = 0;
	public long mSealedTime = 0, mMaxSealedTime = 0;
	public Recipe mRecipe = null;
	public boolean mGasProof = F, mAcidProof = F, mPlasmaProof = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
    	if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
    	if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
    	if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		mMode = aNBT.getByte(NBT_MODE);
		mSealedTime = aNBT.getLong(NBT_PROGRESS);
		mTanks[0].setCapacity(aNBT.getInteger(NBT_TANK_CAPACITY));
		mTanks[0].readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mSealedTime);
		mTanks[0].writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mSealedTime);
		mTanks[0].writeToNBT(aNBT, NBT_TANK);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		if (mTanks[0].getFluidAmount() > 0) {
			aList.add(mTanks[0].getFluidAmount() + " L of " + UT.Fluids.name(mTanks[0].getFluid(), T) + " (" + (UT.Fluids.gas(mTanks[0].getFluid()) ? "Gaseous" : "Liquid") + "; Max: "+mTanks[0].getCapacity()+" L)");
			if ((mMode & B[1]) != 0) aList.add(Chat.CYAN + "Sealed (" + mSealedTime + ")");
		} else {
			aList.add(Chat.CYAN + "Max: " + mTanks[0].getCapacity() + " L");
		}
		aList.add(Chat.ORANGE	+ LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.ORANGE	+ LH.get(LH.NO_POWER_CONDUCTING_FLUIDS));
		if (mGasProof		) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof		) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof	) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_PLASMAPROOF));
		aList.add(Chat.DRED		+ LH.get(LH.HAZARD_MELTDOWN) + " (" + mMaterial.mMeltingPoint + " K)");
		aList.add(Chat.DGRAY	+ LH.get(LH.TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY	+ LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY	+ LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_softhammer)) {
			mMode ^= B[1];
			aChatReturn.add((mMode & B[1]) == 0 ? "Normal" : "Sealed");
			mMaxSealedTime = 0;
			mSealedTime = 0;
			return 10000;
		}
		if (aTool.equals(TOOL_plunger)) {
			mMaxSealedTime = 0;
			mSealedTime = 0;
			return GarbageGT.trash(mTanks, 1000);
		}
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_monkeywrench)) {
			mMode ^= B[0];
			aChatReturn.add((mMode & B[0]) == 0 ? "Won't fill adjacent Tanks" : "Will fill adjacent Tanks");
			updateClientData();
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				FluidStack tFluid = mTanks[0].getFluid();
				if (tFluid == null) {
					aChatReturn.add("Tank is empty");
				} else {
					aChatReturn.add("Contains: " + tFluid.amount + " L of " + UT.Fluids.name(tFluid, T) + " (" + (UT.Fluids.gas(tFluid) ? "Gaseous" : "Liquid") + ")");
					if ((mMode & B[1]) != 0) {
						if (mMaxSealedTime > 0) {
							aChatReturn.add("Sealed (" + mSealedTime + " / " + mMaxSealedTime + ")");
						} else {
							aChatReturn.add("Sealed");
						}
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
			FluidStack tFluid = mTanks[0].getFluid();
			if (tFluid != null && tFluid.amount > 0) {
				if (UT.Fluids.temperature(tFluid) >= mMaterial.mMeltingPoint && meltdown()) return;
				if (!mAcidProof && UT.Fluids.acid(tFluid)) {
					GarbageGT.trash(mTanks);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 0.5F, getCoords());
					setToAir();
					return;
				}
				if (!mPlasmaProof && UT.Fluids.plasma(tFluid)) {
					GarbageGT.trash(mTanks);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else
				if (!mGasProof && UT.Fluids.gas(tFluid)) {
					GarbageGT.trash(mTanks);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else
				if (!allowFluid(tFluid)) {
					GarbageGT.trash(mTanks);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else {
					if ((mMode & B[1]) != 0) {
						if (mMaxSealedTime == 0) {
							mRecipe = RM.Fermenter.findRecipe(this, mRecipe, T, Long.MAX_VALUE, NI, new FluidStack[] {mTanks[0].getFluid()}, ST.tag(0));
							if (mRecipe != null && mRecipe.mFluidInputs.length > 0 && mRecipe.mFluidOutputs.length > 0 && !UT.Fluids.gas(mRecipe.mFluidInputs[0]) && !UT.Fluids.gas(mRecipe.mFluidOutputs[0])) {
								mMaxSealedTime = (mRecipe.mDuration * mRecipe.mEUt * mTanks[0].getFluidAmount()) / mRecipe.mFluidInputs[0].amount;
							} else {
								mRecipe = null;
							}
						}
						mSealedTime++;
						if (mRecipe != null && mMaxSealedTime > 0 && mSealedTime >= mMaxSealedTime) {
							mTanks[0].setFluid(UT.Fluids.mul(mRecipe.mFluidOutputs[0], mTanks[0].getFluidAmount(), mRecipe.mFluidInputs[0].amount, F));
							mMaxSealedTime = 0;
							mSealedTime = 0;
							mRecipe = null;
						}
					} else {
						if ((mMode & B[0]) != 0) {
							byte[] tSides = ZL_BYTE;
							if (UT.Fluids.gas(tFluid)) tSides = ALL_SIDES_VERTICAL; else if (tFluid.getFluid().getDensity(tFluid)<0) tSides = ALL_SIDES_TOP; else tSides = ALL_SIDES_BOTTOM;
							for (byte tSide : tSides) if (UT.Fluids.move(mTanks[0], getAdjacentTank(tSide)) > 0) updateInventory();
						}
					}
				}
			}
		}
	}
	
	public boolean meltdown() {
		if (UT.Fluids.lava(mTanks[0].getFluid()) && mTanks[0].getFluidAmount() >= 1000) {
			mTanks[0].remove(1000);
			GarbageGT.trash(mTanks);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.flowing_lava, 0, 3);
		} else {
			GarbageGT.trash(mTanks);
			setToFire();
		}
		WD.burn(worldObj, getCoords(), F, F);
		return T;
	}
	
	public boolean allowFluid(FluidStack aFluid) {
		return !UT.Fluids.powerconducting(aFluid) && UT.Fluids.temperature(aFluid) < mMaterial.mMeltingPoint;
	}
	
	@Override
	public FluidStack getFluid(ItemStack aStack) {
		return mTanks[0].getFluid();
	}
	
	@Override
	public int getCapacity(ItemStack aStack) {
		return mTanks[0].getCapacity();
	}
	
	@Override
	public int fill(ItemStack aStack, FluidStack aFluid, boolean aDoFill) {
		if (!allowFluid(aFluid)) return 0;
		if (!mGasProof && UT.Fluids.gas(aFluid)) return 0;
		if (!mAcidProof && UT.Fluids.acid(aFluid)) return 0;
		if (!mPlasmaProof && UT.Fluids.plasma(aFluid)) return 0;
		int tFilled = mTanks[0].fill(aFluid, aDoFill);
		if (tFilled > 0 && aDoFill) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tFilled;
	}
	
	@Override
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		FluidStack tDrained = mTanks[0].drain(aMaxDrain, aDoDrain);
		if (tDrained != NF && aDoDrain) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tDrained;
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return mTanks[0].fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		return mTanks[0].drain(aMaxDrain, aDoDrain);
	}
	
	@Override public long getProgressValue(byte aSide) {return mSealedTime;}
	@Override public long getProgressMax(byte aSide) {return mMaxSealedTime;}
	@Override public boolean canDrop(int aSlot) {return F;}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return mTanks[0].getFluidAmount() > 0 ? 1 : aDefault;}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return (mMode & B[1]) != 0 ? null : mTanks[0];}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return (mMode & B[1]) != 0 ? null : mTanks[0];}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public ItemStack getRotten(ItemStack aStack) {return mMaterial.contains(TD.Properties.BETWEENLANDS) ? aStack : IItemRottable.RottingUtil.rotting(aStack, (IFluidContainerItem)aStack.getItem());}
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return mMaterial.contains(TD.Properties.BETWEENLANDS) ? aStack : IItemRottable.RottingUtil.rotting(aStack, (IFluidContainerItem)aStack.getItem());}
	
	@Override
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated) {
		if (aFluid == NF || (mTanks[0].getFluidAmount() <= 0 && aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated)) return 0;
		return mTanks[0].fill(aFluid, T);
	}
	
	@Override
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if (aFluid == NF || !UT.Fluids.equal(mTanks[0].getFluid(), aFluid) || mTanks[0].getFluidAmount() < (aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return 0;
		return (int)mTanks[0].remove(aFluid.amount);
	}
	
	@Override
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid) {
		return aFluid == NF || mTanks[0].getFluid() == NF || !UT.Fluids.equal(mTanks[0].getFluid(), aFluid) ? 0 : mTanks[0].getFluidAmount();
	}
}