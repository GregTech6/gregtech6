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

import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.code.TagData;
import gregapi.data.FL;
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
import gregapi.tileentity.behavior.TE_Behavior_Active_Trinary;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import gregapi.util.WD;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorHotFluid extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityEnergy, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock, IMultiTileEntity.IMTE_WailaDetectable {
	private static int FLAME_RANGE = 2;
	
	public short mEfficiency = 10000;
	public long mEnergy = 0, mRate = 1;
	public TagData mEnergyTypeEmitted = TD.Energy.HU;
	public RecipeMap mRecipes = FM.Hot;
	public Recipe mLastRecipe = null;
	public FluidTankGT[] mTanks = {new FluidTankGT(1000), new FluidTankGT()};
	public TE_Behavior_Active_Trinary mActivity = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mActivity = new TE_Behavior_Active_Trinary(this, aNBT);
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTanks[0].setCapacity(mRate * 10);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		mActivity.save(aNBT);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			// Emit buffered Energy. And yes if you use a strong enough Fuel, that Energy would stay buffered even while the Box is Off. This is very intended and represents partially used Fuel.
			if (mEnergy > 0) {
				ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, Math.min(mRate, mEnergy), this);
				mEnergy -= Math.min(mRate, mEnergy);
				// Burn surrounding Area.
				if (mEfficiency < 1 || rng(mEfficiency) == 0) {
					WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), T);
				}
			}
			// Check if it needs to use more Fuel, or if the buffered Energy is enough.
			if (mEnergy < mRate * 2) {
				// Will be set back to true if the Recipe finds enough Fuel.
				mActivity.mActive = F;
				// Output isn't allowed to be completely filled.
				if (!mTanks[1].has(mRate * 20)) {
					// Find and apply fitting Recipe.
					Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, NI, mTanks[0].AS_ARRAY, ZL_IS);
					if (tRecipe != null) {
						if (tRecipe.mFluidOutputs.length <= 0 || mTanks[1].canFillAll(tRecipe.mFluidOutputs[0])) {
							if (tRecipe.isRecipeInputEqual(T, F, mTanks[0].AS_ARRAY, ZL_IS)) {
								mActivity.mActive = T;
								mLastRecipe = tRecipe;
								mEnergy += UT.Code.units(tRecipe.getAbsoluteTotalPower(), 10000, mEfficiency, F);
								if (tRecipe.mFluidOutputs.length > 0) mTanks[1].fill(tRecipe.mFluidOutputs[0]);
								// Use as much as needed to keep up the Power per Tick.
								while (mEnergy < mRate * 2 && (tRecipe.mFluidOutputs.length <= 0 || mTanks[1].canFillAll(tRecipe.mFluidOutputs[0])) && tRecipe.isRecipeInputEqual(T, F, mTanks[0].AS_ARRAY, ZL_IS)) {
									mEnergy += UT.Code.units(tRecipe.getAbsoluteTotalPower(), 10000, mEfficiency, F);
									if (tRecipe.mFluidOutputs.length > 0) mTanks[1].fill(tRecipe.mFluidOutputs[0]);
									if (mTanks[0].isEmpty()) break;
								}
							} else {
								// set remaining Fluid to null, in case the Fuel Type needs to be swapped out. But only if it was inactive for 64 ticks.
								if (mActivity.mData == 0) mTanks[0].setEmpty();
							}
						}
					} else {
						// set remaining Fluid to null, because it is not valid Fuel anymore for whatever reason. MineTweaker happens to live Modpacks too sometimes. ;)
						mTanks[0].setEmpty();
					}
				}
			}
			// Out of Fuel I guess.
			if (mEnergy < 0) mEnergy = 0;
			// Output used Liquid to the Front.
			if (mTanks[1].has()) FL.move(mTanks[1], getAdjacentTank(mFacing));
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_plunger)) {
			if (mTanks[1].has()) return GarbageGT.trash(mTanks[1]);
			return GarbageGT.trash(mTanks[0]);
		}
		
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
		return mActivity.check(F) || super.onTickCheck(aTimer);
	}
	
	@Override
	public void setVisualData(byte aData) {
		mActivity.mState = (byte)(aData & 127);
	}
	
	@Override public byte getVisualData() {return mActivity.mState;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM_HORIZONTAL;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) ? mTanks[0] : null;
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
		if (!mRecipes.containsInput(aFluid, this, NI)) return 0;
		updateInventory();
		return mTanks[0].fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTanks[mTanks[1].has() ? 1 : 0].drain(aMaxDrain, aDoDrain);
	}

	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]]), mActivity.mState>0? BlockTextureDefault.get(sOverlaysActiveGlowing[FACING_ROTATIONS[mFacing][aSide]],true):null): null;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mActivity.mState>0) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mRate / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, 0, 0, 1, 0.875, 1);}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return SIDES_TOP[aSide] && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean getStateRunningPassively() {return mActivity.mActive;}
	@Override public boolean getStateRunningPossible() {return mActivity.mActive || mTanks[0].has();}
	@Override public boolean getStateRunningActively() {return mActivity.mActive;}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active/back")
	}, sOverlaysActiveGlowing = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/hot_fluid/overlay_active_glowing/back")
	};

	@Override
	public NBTTagCompound getWailaNBT(TileEntity te, NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
		return aNBT;
	}

	@Override
	public List<String> getWailaBody(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		NBTTagCompound aNBT = accessor.getNBTData();

		mEnergy = aNBT.getLong(NBT_ENERGY);
		mTanks[0].setCapacity(mRate * 10);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");

		IMTE_WailaDetectable.addTankDesc(currentTip, LH.get(LH.CONTENT)+" ", mTanks[0],"");
		IMTE_WailaDetectable.addTankDesc(currentTip, LH.get(LH.CONTENT)+" ", mTanks[1],"");
		IMTE_WailaDetectable.addEnergyStoreDesc(currentTip, LH.get(LH.ENERGY_CONTAINED)+" ", mEnergyTypeEmitted, mEnergy,"");
		return currentTip;
	}

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.hot_fluid";}
}
