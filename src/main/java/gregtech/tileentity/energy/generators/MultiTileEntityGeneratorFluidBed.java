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

package gregtech.tileentity.energy.generators;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.code.TagData;
import gregapi.data.CS.GarbageGT;
import gregapi.data.FM;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
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
public class MultiTileEntityGeneratorFluidBed extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityEnergy, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	private static int FLAME_RANGE = 2;
	
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mRate = 1;
	protected boolean mBurning = F, oBurning = F;
	protected TagData mEnergyTypeEmitted = TD.Energy.HU;
	protected RecipeMap mRecipes = FM.FluidBed;
	protected Recipe mLastRecipe = null;
	protected FluidTankGT mTank = new FluidTankGT(1000);
	protected ItemStack mOutput1 = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mBurning = aNBT.getBoolean(NBT_ACTIVE);
		mOutput1 = ST.load(aNBT, NBT_INV_OUT + ".1");
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTank.setCapacity(Math.max(mRecipes.mMaxFluidInputSize, mRate));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mBurning);
		ST.save(aNBT, NBT_INV_OUT + ".1", mOutput1);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES)        + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_AIR_IN_FRONT));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_EMPTY_ASHES) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_MOLTEN_CALCITE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INVENTORY) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_TOP) + ")");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBurning) {
				if (mEnergy >= mRate) {
					ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, Math.min(mRate, mEnergy), this);
					mEnergy -= mRate;
					if (mEfficiency < 1 || rng(mEfficiency) == 0) {
						WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), T);
					}
				}
				if (mEnergy < mRate * 2) {
					WD.burn(worldObj, getOffset(mFacing, 1), T, T);
					if (addStackToSlot(1, mOutput1)) mOutput1 = null;
					if (mOutput1 == null && !WD.hasCollide(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing)) && !getBlockAtSide(mFacing).getMaterial().isLiquid() && WD.oxygen(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
						Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, NI, mTank.AS_ARRAY, slot(0));
						if (tRecipe != null && tRecipe.isRecipeInputEqual(T, F, mTank.AS_ARRAY, slot(0))) {
							mLastRecipe = tRecipe;
							ItemStack[] tOutputs = tRecipe.getOutputs();
							if (tOutputs.length > 0) mOutput1 = ST.copy(tOutputs[0]);
							mEnergy += UT.Code.units(tRecipe.getAbsoluteTotalPower(), 10000, mEfficiency, F);
							removeAllDroppableNullStacks();
						}
					}
				}
				// Out of Fuel I guess.
				if (mEnergy < mRate) mBurning = F;
			} else {
				// Something burning in front of it? Lets ignite!
				if (rng(200) == 0 && WD.flaming(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
					mBurning = T;
				}
			}
			// Out of Fuel I guess.
			if (mEnergy < 0) mEnergy = 0;
		} else {
			if (mBurning && rng(4) == 0) spawnBurningParticles(xCoord+0.5+OFFX[mFacing]*0.55+(SIDES_AXIS_X[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F), yCoord+RNGSUS.nextFloat()*0.375F, zCoord+0.5+OFFZ[mFacing]*0.55+(SIDES_AXIS_Z[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F));
		}
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing) return F;
		if (isServerSide() && !mBurning) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (aStack == null) {
				if (slotHas(1)) {
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slot(1));
					slotKill(1);
					return T;
				}
				if (slotHas(0)) {
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slot(0));
					slotKill(0);
					return T;
				}
			} else if (!slotHas(0)) {
				if (canInsertItem(0, aStack, SIDE_INSIDE)) {
					slot(0, aStack);
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
					return T;
				}
			} else if (ST.equal(aStack, slot(0))) {
				int tDifference = Math.min(aStack.stackSize, slot(0).getMaxStackSize() - slot(0).stackSize);
				aStack.stackSize-=tDifference;
				slot(0).stackSize+=tDifference;
				return T;
			} else if (ST.equal(aStack, slot(1))) {
				int tDifference = Math.min(slot(1).stackSize, aStack.getMaxStackSize() - aStack.stackSize);
				aStack.stackSize+=tDifference;
				slot(1).stackSize-=tDifference;
				removeAllDroppableNullStacks();
				return T;
			}
		}
		return T;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_igniter       ) && (aSide == mFacing || aPlayer == null)) {mBurning = T; return 10000;}
		if (aTool.equals(TOOL_extinguisher  ) && (aSide == mFacing || aPlayer == null)) {mBurning = F; return 10000;}
		
		if (aTool.equals(TOOL_plunger)) return GarbageGT.trash(mTank);
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(mTank.content());
			return 1;
		}
		return 0;
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mBurning != oBurning || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oBurning = mBurning;}
	@Override public void setVisualData(byte aData) {mBurning = ((aData & 1) != 0);}
	
	@Override public byte getVisualData() {return (byte)(mBurning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return mBurning ? SIDES_THIS[mFacing] : SIDES_HORIZONTAL;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) ? mTank : null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mBurning ? null : mTank;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTank.AS_ARRAY;
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTank.drain(aMaxDrain, aDoDrain);
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		if (!mRecipes.containsInput(aFluid, this, NI)) return 0;
		updateInventory();
		return mTank.fill(aFluid, aDoFill);
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mBurning) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mRate / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, 0, 0, 1, 0.875, 1);}
	
	// Inventory Stuff
	private static final int[] ACCESSABLE_SLOTS = new int[] {0, 1};
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return aSide == mFacing ? ZL_INTEGER : ACCESSABLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aSlot == 0 && aSide != mFacing && mRecipes.containsInput(aStack, this, NI);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aSlot == 1 && aSide != mFacing;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[2];}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return SIDES_TOP[aSide] && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean getStateRunningPassively() {return mBurning;}
	@Override public boolean getStateRunningPossible() {return mBurning;}
	@Override public boolean getStateRunningActively() {return mBurning;}
	
	protected void spawnBurningParticles(double aX, double aY, double aZ) {
		worldObj.spawnParticle("smoke", aX, aY, aZ, 0, 0, 0);
		worldObj.spawnParticle("flame", aX, aY, aZ, 0, 0, 0);
	}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_fluidbed/overlay_active/back")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.burning_fluidbed";}
}
