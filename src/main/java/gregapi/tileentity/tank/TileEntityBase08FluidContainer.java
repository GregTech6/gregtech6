/**
 * Copyright (c) 2022 GregTech-6 Team
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

import enviromine.handlers.EM_StatusManager;
import enviromine.trackers.EnviroDataTracker;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityItemInternal;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MD;
import gregapi.fluid.FluidTankGT;
import gregapi.item.multiitem.food.FoodStatFluid;
import gregapi.tileentity.ITileEntityConnectedTank;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import ic2.api.crops.ICropTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;
import squeek.applecore.api.food.FoodValues;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase08FluidContainer extends TileEntityBase07Paintable implements ITileEntityConnectedTank, IMTE_GetMaxStackSize, IMTE_OnlyPlaceableWhenSneaking, IMTE_OnItemRightClick, IMTE_OnItemUseFirst, IMTE_AddToolTips, IFluidContainerItem {
	public FluidTankGT mTank = new FluidTankGT(1000);
	public boolean mLiquidProof = T, mGasProof = F, mAcidProof = F, mPlasmaProof = F;
	public long mTemperatureMax = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_LIQUIDPROOF)) mLiquidProof = aNBT.getBoolean(NBT_LIQUIDPROOF);
		if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mTemperatureMax = aNBT.getLong(NBT_TEMPERATURE); else mTemperatureMax = mMaterial.mMeltingPoint - 50;
		if (aNBT.hasKey(NBT_TANK_CAPACITY)) mTank.setCapacity(aNBT.getLong(NBT_TANK_CAPACITY));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		mTank.writeToNBT(aNBT, NBT_TANK);
		if (isClientSide() && !mTank.isEmpty()) aNBT.setTag("display", UT.NBT.makeString(aNBT.getCompoundTag("display"), "Name", FL.name(mTank, T)));
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + mTank.contentcap());
		if (mTank.has(250) && isDrinkable()) {
			FoodStatFluid.INSTANCE.addAdditionalToolTips(aStack.getItem(), aList, aStack, aF3_H);
			if (aStack.stackSize != 1) aList.add(LH.Chat.RED + LH.get(LH.REQUIREMENT_UNSTACKED));
		}
		aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_HEATPROOF) + LH.Chat.WHITE + mTemperatureMax + LH.Chat.RED + " K");
		if (mLiquidProof    ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_LIQUIDPROOF));
		if (mGasProof       ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof      ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof    ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_PLASMAPROOF));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && canFillWithRain() && SERVER_TIME % 600 == 10 && worldObj.isRaining() && getRainOffset(0, 1, 0)) {
			BiomeGenBase tBiome = getBiome();
			if (tBiome.rainfall > 0 && tBiome.temperature >= 0.2) {
				Block tInFront = getBlockAtSide(SIDE_TOP);
				if (!(tInFront instanceof BlockLiquid) && !(tInFront instanceof IFluidBlock) && !tInFront.isSideSolid(worldObj, xCoord, yCoord+1, zCoord, FORGE_DIR_OPPOSITES[SIDE_TOP]) && !tInFront.isSideSolid(worldObj, xCoord, yCoord+1, zCoord, FORGE_DIR[SIDE_TOP])) {
					mTank.fill(FL.Water.make((long)Math.max(1, tBiome.rainfall*100) * (worldObj.isThundering()?2:1)), T);
				}
			}
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if (aTool.equals(TOOL_plunger)) {
			return GarbageGT.trash(mTank, 1000);
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(mTank.contentcap());
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		
		ItemStack aStack = aPlayer.getCurrentEquippedItem(), tStack = ST.container(ST.amount(1, aStack), T);
		FluidStack tFluid = FL.getFluid(ST.amount(1, aStack), T);
		if (aStack != null && isFluidAllowed(tFluid) && mTank.fillAll(tFluid)) {
			aStack.stackSize--;
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
			return T;
		}
		if (aStack != null) if ((tStack = FL.fill(mTank, ST.amount(1, aStack), T, T, T, T)) != null) {
			aStack.stackSize--;
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
			return T;
		}
		
		if (isDrinkable() && canDrinkFromSide(aSide)) {
			aStack = toStack();
			if (aStack == null) return T;
			if (UT.Entities.isCreative(aPlayer) || aPlayer.getFoodStats().needFood() || FoodStatFluid.INSTANCE.alwaysEdible(aStack.getItem(), aStack, aPlayer)) {
				switch(FoodStatFluid.INSTANCE.getFoodAction(aStack.getItem(), aStack)) {
				case eat: UT.Sounds.send(worldObj, SFX.MC_EAT  , 1.0F, 1.0F, getCoords()); break;
				default : UT.Sounds.send(worldObj, SFX.MC_DRINK, 1.0F, 1.0F, getCoords()); break;
				}
				mTank.remove(250);
				aStack.getItem().onEaten(aStack, worldObj, aPlayer);
			}
		}
		return T;
	}
	
	@Override public boolean onlyPlaceableWhenSneaking() {return T;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
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
		if (!isFluidAllowed(aFluid) || aStack.stackSize != 1) return 0;
		int tFilled = mTank.fill(aFluid, aDoFill);
		if (tFilled > 0 && aDoFill) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tFilled;
	}
	
	@Override
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		if (aStack.stackSize != 1) return NF;
		FluidStack tDrained = mTank.drain(aMaxDrain, aDoDrain);
		if (tDrained != NF && aDoDrain) UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tDrained;
	}
	
	@Override
	public boolean onItemUseFirst(MultiTileEntityItemInternal aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || aStack.stackSize != 1) return F;
		if (canWaterCrops()) {
			FluidStack mFluid = aItem.getFluid(aStack);
			if (FL.water(mFluid)) {
				Block aBlock = aWorld.getBlock(aX, aY, aZ);
				int aMeta = aWorld.getBlockMetadata(aX, aY, aZ);
				
				if (aBlock instanceof BlockCauldron) {
					if (aMeta >= 3 || mFluid.amount < 334) return F;
					if (mFluid.amount >= 1000 && aMeta <= 0) {
						aItem.drain(aStack, 1000, T);
						WD.set(aWorld, aX, aY, aZ, aBlock, aMeta+3, 3);
					} else if (mFluid.amount >= 667 && aMeta <= 1) {
						aItem.drain(aStack, 667, T);
						WD.set(aWorld, aX, aY, aZ, aBlock, aMeta+2, 3);
					} else if (aMeta <= 2) {
						aItem.drain(aStack, 334, T);
						WD.set(aWorld, aX, aY, aZ, aBlock, aMeta+1, 3);
					}
					UT.Sounds.send(worldObj, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, getCoords());
					return T;
				}
				
				if (IL.GrC_Paddy.exists() && mFluid.amount >= 10) {
					if (IL.GrC_Paddy.block() == aBlock) {
						int tIncrement = Math.min(7-aMeta, mFluid.amount/10);
						if (tIncrement > 0) {
							aItem.drain(aStack, tIncrement*10, T);
							aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMeta+tIncrement, 3);
							UT.Sounds.send(aWorld, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, aX, aY, aZ);
						}
						return T;
					}
					if (IL.GrC_Paddy.block() == aWorld.getBlock(aX, aY-1, aZ)) {
						int tMeta = aWorld.getBlockMetadata(aX, aY-1, aZ);
						int tIncrement = Math.min(7-tMeta, mFluid.amount/10);
						if (tIncrement > 0) {
							aItem.drain(aStack, tIncrement*10, T);
							aWorld.setBlockMetadataWithNotify(aX, aY-1, aZ, tMeta+tIncrement, 3);
							UT.Sounds.send(aWorld, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, aX, aY-1, aZ);
						}
						return T;
					}
				}
				
				TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, F);
				try {if (tTileEntity instanceof ICropTile) {
					int tHydration = ((ICropTile)tTileEntity).getHydrationStorage();
					int tDrained = Math.min((200-tHydration)/10, mFluid.amount);
					if (tDrained > 0) {
						aItem.drain(aStack, tDrained, T);
						((ICropTile)tTileEntity).setHydrationStorage(tHydration + tDrained*10);
						UT.Sounds.send(aWorld, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, aX, aY, aZ);
					}
					return T;
				}} catch(Throwable e) {/**/}
			}
		}
		return F;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (canPickUpFluids() && aStack.stackSize == 1) {
			MovingObjectPosition tTarget = WD.getMOP(aWorld, aPlayer, T);
			if (tTarget != null && tTarget.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && aWorld.canMineBlock(aPlayer, tTarget.blockX, tTarget.blockY, tTarget.blockZ)) {
				Block tBlock = aWorld.getBlock(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
				if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
					if (aWorld.getBlockMetadata(tTarget.blockX, tTarget.blockY, tTarget.blockZ) == 0) {
						if (WD.infiniteWater(aWorld, tTarget.blockX, tTarget.blockY, tTarget.blockZ)) {
							aItem.fill(aStack, FL.Water.make(1000), T);
						} else {
							if (aItem.fill(aStack, FL.Water.make(1000), F) == 1000) {
								aWorld.setBlockToAir(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
								aItem.fill(aStack, FL.Water.make(1000), T);
							}
						}
					}
					return aStack;
				}
				if (tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) {
					if (aWorld.getBlockMetadata(tTarget.blockX, tTarget.blockY, tTarget.blockZ) == 0 && aItem.fill(aStack, FL.Lava.make(1000), F) == 1000) {
						aWorld.setBlockToAir(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
						aItem.fill(aStack, FL.Lava.make(1000), T);
					}
					return aStack;
				}
				if (tBlock == BlocksGT.River || WD.waterstream(tBlock)) {
					aItem.fill(aStack, FL.Water.make(1000), T);
					return aStack;
				}
				if (tBlock == BlocksGT.Ocean) {
					aItem.fill(aStack, FL.Ocean.make(1000), T);
					return aStack;
				}
				if (tBlock == BlocksGT.Swamp) {
					aItem.fill(aStack, FL.Dirty_Water.make(1000), T);
					return aStack;
				}
				if (tBlock instanceof IFluidBlock) {
					FluidStack tDrained = ((IFluidBlock)tBlock).drain(aWorld, tTarget.blockX, tTarget.blockY, tTarget.blockZ, F);
					if (tDrained != null && tDrained.amount > 0 && aItem.fill(aStack, tDrained, F) == tDrained.amount) {
						// Forge fucked up the Fluid Draining Function, meaning if you insert true for doDrain it will ALWAYS return a null Fluid for the finite Fluid Blocks. That's why I take the result from the simulation instead of the actual draining.
						aItem.fill(aStack, tDrained, T);
						((IFluidBlock)tBlock).drain(aWorld, tTarget.blockX, tTarget.blockY, tTarget.blockZ, T);
					}
					return aStack;
				}
				
				tTarget.blockX+=OFFX[tTarget.sideHit];
				tTarget.blockY+=OFFY[tTarget.sideHit];
				tTarget.blockZ+=OFFZ[tTarget.sideHit];
				tBlock = aWorld.getBlock(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
				
				if (tBlock instanceof IFluidBlock) {
					FluidStack tDrained = ((IFluidBlock)tBlock).drain(aWorld, tTarget.blockX, tTarget.blockY, tTarget.blockZ, F);
					if (tDrained != null && tDrained.amount > 0 && aItem.fill(aStack, tDrained, F) == tDrained.amount) {
						// Forge fucked up the Fluid Draining Function, meaning if you insert true for doDrain it will ALWAYS return a null Fluid for the finite Fluid Blocks. That's why I take the result from the simulation instead of the actual draining.
						aItem.fill(aStack, tDrained, T);
						((IFluidBlock)tBlock).drain(aWorld, tTarget.blockX, tTarget.blockY, tTarget.blockZ, T);
					}
					return aStack;
				}
			}
		}
		if (isDrinkable() && aStack.stackSize == 1 && (UT.Entities.isCreative(aPlayer) || aPlayer.getFoodStats().needFood() || FoodStatFluid.INSTANCE.alwaysEdible(aStack.getItem(), aStack, aPlayer))) {
			aPlayer.setItemInUse(aStack, Math.max(FoodStatFluid.INSTANCE.getFoodLevel(aStack.getItem(), aStack, null) * 8, 32));
			return aStack;
		}
		return aStack;
	}
	
	public int getMaxItemUseDuration(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		return isDrinkable() && aStack.stackSize == 1 ? Math.max(FoodStatFluid.INSTANCE.getFoodLevel(aStack.getItem(), aStack, null) * 8, 32) : 0;
	}
	
	public EnumAction getItemUseAction(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		return isDrinkable() && aStack.stackSize == 1 ? FoodStatFluid.INSTANCE.getFoodAction(aStack.getItem(), aStack) : EnumAction.none;
	}
	
	public ItemStack onEaten(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (!isDrinkable() || aStack.stackSize != 1) return aStack;
		
		int tFoodLevel = FoodStatFluid.INSTANCE.getFoodLevel(aStack.getItem(), aStack, aPlayer);
		
		if (tFoodLevel > 0) {
			if (FoodStatFluid.INSTANCE.useAppleCoreFunctionality(aStack.getItem(), aStack, aPlayer) && MD.APC.mLoaded) {
				aPlayer.getFoodStats().func_151686_a((ItemFood)UT.Reflection.callConstructor("squeek.applecore.api.food.ItemFoodProxy", 0, null, T, aStack.getItem()), aStack);
			} else {
				aPlayer.getFoodStats().addStats(tFoodLevel, FoodStatFluid.INSTANCE.getSaturation(aStack.getItem(), aStack, aPlayer));
			}
		}
		
		if (!aWorld.isRemote && MD.ENVM.mLoaded) {
			try {
				float tTemperature = FoodStatFluid.INSTANCE.getTemperature(aStack.getItem(), aStack, aPlayer) - C, tHydration = FoodStatFluid.INSTANCE.getHydration(aStack.getItem(), aStack, aPlayer);
				Object tTracker = EM_StatusManager.lookupTracker(aPlayer);
				if (tTracker != null && ((EnviroDataTracker)tTracker).bodyTemp >= 0) {
					((EnviroDataTracker)tTracker).bodyTemp += (tTemperature - ((EnviroDataTracker)tTracker).bodyTemp) * FoodStatFluid.INSTANCE.getTemperatureEffect(aStack.getItem(), aStack, aPlayer);
					if (tHydration > 0) ((EnviroDataTracker)tTracker).hydrate(tHydration); else if (tHydration < 0) ((EnviroDataTracker)tTracker).dehydrate(-tHydration);
				}
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
		}
		
		FoodStatFluid.INSTANCE.onEaten(aStack.getItem(), aStack, aPlayer, F, T);
		
		mTank.remove(250);
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return aStack;
	}
	
	public FoodValues getFoodValues(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		int tFoodLevel = FoodStatFluid.INSTANCE.getFoodLevel(aStack.getItem(), aStack, null);
		return tFoodLevel > 0 && isDrinkable() ? new squeek.applecore.api.food.FoodValues(tFoodLevel, FoodStatFluid.INSTANCE.getSaturation(aStack.getItem(), aStack, null)) : null;
	}
	
	@Override
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated) {
		if (aFluid == NF || !isFluidAllowed(aFluid) || (mTank.isEmpty() && aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated)) return 0;
		return mTank.fill(aFluid, T);
	}
	
	@Override
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if (mTank.contains(aFluid) && mTank.has(aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return (int)mTank.remove(aFluid.amount);
		return 0;
	}
	
	@Override
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid) {
		return mTank.contains(aFluid) ? mTank.amount() : 0;
	}
	
	public int tapFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return SIDES_TOP[aSide] && isFluidAllowed(aFluid) ? mTank.fill(aFluid, aDoFill) : 0;
	}
	
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return SIDES_TOP[aSide] && isFluidAllowed(aFluid) ? mTank.fill(aFluid, aDoFill) : 0;
	}
	
	public boolean isDrinkable() {
		return this instanceof IMTE_GetFoodValues && mTank.has(250) && DrinksGT.REGISTER.containsKey(mTank.name());
	}
	
	public boolean isFluidAllowed(FluidStack aFluid) {
		return aFluid != null && !FL.powerconducting(aFluid) && (FL.gas(aFluid) ? mGasProof : mLiquidProof) && (mAcidProof || !FL.acid(aFluid)) && (mPlasmaProof || !FL.plasma(aFluid) && FL.temperature(aFluid) <= mTemperatureMax);
	}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return mTank.has() ? 1 : aDefault;}
	
	public boolean canWaterCrops() {return F;}
	public boolean canPickUpFluids() {return F;}
	public boolean canFillWithRain() {return F;}
	public boolean canDrinkFromSide(byte aSide) {return F;}
}
