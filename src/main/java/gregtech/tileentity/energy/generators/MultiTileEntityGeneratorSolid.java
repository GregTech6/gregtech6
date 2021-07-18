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
import gregapi.data.FM;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityGeneratorSolid extends TileEntityBase09FacingSingle implements ITileEntityEnergy, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	private static int FLAME_RANGE = 3;
	
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mRate = 1;
	protected boolean mBurning = F, oBurning = F;
	protected TagData mEnergyTypeEmitted = TD.Energy.HU;
	protected RecipeMap mRecipes = FM.Furnace;
	protected Recipe mLastRecipe = null;
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
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mBurning);
		ST.save(aNBT, NBT_INV_OUT + ".1", mOutput1);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES)        + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_AIR_IN_FRONT));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_EMPTY_ASHES) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INVENTORY) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_REMOVE_SHOVEL));
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
					if (mOutput1 == null && slotHas(0) && !WD.hasCollide(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing)) && !getBlockAtSide(mFacing).getMaterial().isLiquid() && WD.oxygen(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
						if (IL.RC_Firestone_Refined.equal(slot(0), T, T)) {
							ItemStack tStack = ST.container(slot(0), F);
							if (ST.invalid(tStack)) {
								// Just dump the empty Firestone to the Output. This should not happen, unless you insert an empty Firestone.
								if (addStackToSlot(1, slot(0))) slotKill(0);
							} else if (ST.invalid(ST.container(tStack, F))) {
								// 80% of whatever Heat Energy you get from Lava. This is over 10 times the normal Firestone Furnace burn Value.
								mEnergy += 800 * EU_PER_LAVA;
								// Prevent using up the Firestone entirely.
								slotKill(0);
								mOutput1 = tStack;
							} else {
								// 80% of whatever Heat Energy you get from Lava. This is over 10 times the normal Firestone Furnace burn Value.
								mEnergy += 800 * EU_PER_LAVA;
								// Continue using the Firestone.
								slot(0, tStack);
							}
						} else if (IL.RC_Firestone_Cracked.equal(slot(0), T, T)) {
							ItemStack tStack = ST.container(slot(0), F);
							if (ST.invalid(tStack)) {
								// Just dump the empty Firestone to the Output. This should not happen, unless you insert an empty Firestone.
								if (addStackToSlot(1, slot(0))) slotKill(0);
							} else if (ST.invalid(ST.container(tStack, F))) {
								// Less Power for the broken Firestone, so 60%.
								mEnergy += 600 * EU_PER_LAVA;
								// Prevent using up the Firestone entirely.
								slotKill(0);
								mOutput1 = tStack;
							} else {
								// Less Power for the broken Firestone, so 60%.
								mEnergy += 600 * EU_PER_LAVA;
								// Continue using the Firestone.
								slot(0, tStack);
								// Cracked Firestones cause Fire to be released way more often.
								WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), T);
							}
						} else {
							Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, null, ZL_FS, slot(0));
							if (tRecipe != null && tRecipe.isRecipeInputEqual(T, F, ZL_FS, slot(0))) {
								mLastRecipe = tRecipe;
								ItemStack[] tOutputs = tRecipe.getOutputs();
								if (tOutputs.length > 0) mOutput1 = ST.copy(tOutputs[0]);
								mEnergy += UT.Code.units(tRecipe.getAbsoluteTotalPower(), 10000, mEfficiency, F);
								removeAllDroppableNullStacks();
							}
						}
					}
				}
			} else {
				// Something burning in front of it? Lets ignite!
				if (rng(200) == 0 && WD.burning(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
					mBurning = T;
				}
			}
			if (mEnergy <     0) mEnergy = 0;
			if (mEnergy < mRate) mBurning = F;
		} else {
			if (mBurning && rng(4) == 0) spawnBurningParticles(xCoord+0.5+OFFX[mFacing]*0.55+(SIDES_AXIS_X[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F), yCoord+RNGSUS.nextFloat()*0.375F, zCoord+0.5+OFFZ[mFacing]*0.55+(SIDES_AXIS_Z[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F));
		}
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing) return F;
		if (isServerSide()) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (aStack == null) {
				if (slotHas(1)) {
					aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slot(1));
					slotKill(1);
					if (mBurning) UT.Entities.applyHeatDamage(aPlayer, Math.max(1.0F, Math.min(5.0F, mRate / 20.0F)));
					return T;
				}
				if (!mBurning && slotHas(0)) {
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
				if (mBurning) UT.Entities.applyHeatDamage(aPlayer, Math.max(1.0F, Math.min(5.0F, mRate / 20.0F)));
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
		if (aTool.equals(TOOL_shovel        ) &&  aSide == mFacing && slotHas(1)) {
			long rDamage = 1000 * slot(1).stackSize;
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, slot(1), worldObj, xCoord, yCoord, zCoord);
			slotKill(1);
			return rDamage;
		}
		
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mBurning != oBurning || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oBurning = mBurning;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mBurning = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)(mBurning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return mBurning ? SIDES_THIS[mFacing] : SIDES_HORIZONTAL;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mBurning) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mRate / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, 0, 0, 1, 0.875, 1);}
	
	// Inventory Stuff
	private static final int[] ACCESSABLE_SLOTS = new int[] {0, 1};
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return aSide == mFacing ? ZL_INTEGER : ACCESSABLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aStack != null && aSlot == 0 && aSide != mFacing && (mRecipes.containsInput(aStack, this, NI) || IL.RC_Firestone_Refined.equal(aStack, T, T) || IL.RC_Firestone_Cracked.equal(aStack, T, T));}
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
	
	@Override public float getBlockHardness() {return mBurning ? super.getBlockHardness() * 16 : super.getBlockHardness();}
	
	protected void spawnBurningParticles(double aX, double aY, double aZ) {
		worldObj.spawnParticle("smoke", aX, aY, aZ, 0, 0, 0);
		worldObj.spawnParticle("flame", aX, aY, aZ, 0, 0, 0);
	}
}
