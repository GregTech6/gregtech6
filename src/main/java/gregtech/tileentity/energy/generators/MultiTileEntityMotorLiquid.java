/**
 * Copyright (c) 2020 Gregorius Techneticies
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

import gregapi.code.TagData;
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
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMotorLiquid extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityFunnelAccessible, ITileEntityTapAccessible, ITileEntityEnergy, ITileEntityRunningActively {
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mRate = 1;
	protected boolean mBurning = F, oBurning = F;
	protected TagData mEnergyTypeEmitted = TD.Energy.RU;
	protected RecipeMap mRecipes = FM.Engine;
	protected Recipe mLastRecipe = null;
	protected FluidTankGT mTankInput = new FluidTankGT(1000), mTankOutput = new FluidTankGT(10000);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mBurning = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTankInput.setCapacity(mRate * 10);
		mTankInput.readFromNBT(aNBT, NBT_TANK);
		mTankOutput.setCapacity(mRate * 100);
		mTankOutput.readFromNBT(aNBT, NBT_TANK_OUT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mBurning);
		mTankInput.writeToNBT(aNBT, NBT_TANK);
		mTankOutput.writeToNBT(aNBT, NBT_TANK_OUT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES)        + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_FRONT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mEnergy >= mRate) {
				ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, Math.min(mRate, mEnergy), this);
				mEnergy -= mRate;
			}
			if (mEnergy < mRate * 2) {
				mBurning = F;
				Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, NI, mTankInput.AS_ARRAY, ZL_IS);
				if (tRecipe != null && tRecipe.isRecipeInputEqual(T, F, mTankInput.AS_ARRAY, ZL_IS)) {
					mBurning = T;
					mLastRecipe = tRecipe;
					mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
					while (mEnergy < mRate * 2 && tRecipe.isRecipeInputEqual(T, F, mTankInput.AS_ARRAY, ZL_IS)) {
						mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
						if (mTankInput.isEmpty()) break;
					}
				} else {
					mTankInput.setEmpty();
				}
				if (mEnergy < 0) mEnergy = 0;
			}
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add(mTankInput .content());
				aChatReturn.add(mTankOutput.content());
			}
			return 1;
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
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) ? mTankInput : null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTankOutput;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return new IFluidTank[] {mTankInput, mTankOutput};
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTankOutput.has() ? mTankOutput.drain(aMaxDrain, aDoDrain) : mTankInput.drain(aMaxDrain, aDoDrain);
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoDrain) {
		updateInventory();
		return mTankInput.fill(aFluid, aDoDrain);
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean getStateRunningPassively() {return mBurning;}
	@Override public boolean getStateRunningPossible() {return mBurning;}
	@Override public boolean getStateRunningActively() {return mBurning;}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/colored/sides"),
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay/sides"),
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/generators/engine_liquid/overlay_active/sides"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.engine_liquid";}
}
