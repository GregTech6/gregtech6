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

package gregapi.tileentity.machines;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import buildcraft.api.tiles.IHasWork;
import cpw.mods.fml.common.Optional;
import gregapi.GT_API;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.TagData;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ModIDs;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.gui.ContainerClientBasicMachine;
import gregapi.gui.ContainerCommonBasicMachine;
import gregapi.old.Textures;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 * 
 * This is the Base Class for almost all my Basic Machines. It is almost too simple to use.
 * 
 * In order to create a Basic Machine (Steel Shredder in this example), I use the following NBT Parameters in my MultiTileEntity System:
 * 
 * NBT_MATERIAL                     = MT.Steel
 * NBT_HARDNESS                     = 6.0F
 * NBT_RESISTANCE                   = 6.0F
 * NBT_COLOR                        = UT.Code.getRGBInt(MT.Steel.mRGBaSolid)
 * NBT_INPUT                        = 128
 * NBT_GUI                          = RES_PATH_GUI+"machines/Shredder"
 * NBT_TEXTURE                      = "shredder"
 * NBT_ENERGY_ACCEPTED              = TD.Energy.RU
 * NBT_RECIPEMAP                    = RecipeMap.sShredderRecipes
 * NBT_EFFICIENCY                   = 10000
 * NBT_INV_SIDE_INPUT               = SIDE_BITS[SIDE_TOP]
 * NBT_INV_SIDE_AUTO_INPUT          = SIDE_TOP
 * NBT_INV_SIDE_OUTPUT              = SIDE_BITS[SIDE_BOTTOM]
 * NBT_INV_SIDE_AUTO_OUTPUT         = SIDE_BOTTOM
 * NBT_ENERGY_ACCEPTED_SIDES        = SIDE_BITS[SIDE_LEFT]|SIDE_BITS[SIDE_RIGHT]
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "buildcraft.api.tiles.IHasWork", modid = ModIDs.BC)
})
public class MultiTileEntityBasicMachine extends TileEntityBase09FacingSingle implements IHasWork, ITileEntityFunnelAccessible, ITileEntityTapAccessible, ITileEntitySwitchableOnOff, ITileEntityRunningSuccessfully, ITileEntityAdjacentInventoryUpdatable, ITileEntityEnergy, ITileEntityProgress, ITileEntityGibbl, IFluidHandler {
	public boolean mSpecialIsStartEnergy = F, mNoConstantEnergy = F, mCheapOverclocking = F, mCouldUseRecipe = F, mStopped = F, oActive = F, oRunning = F, mStateNew = F, mStateOld = F, mDisabledItemInput = F, mDisabledItemOutput = F, mDisabledFluidInput = F, mDisabledFluidOutput = F, mRequiresIgnition = F, mParallelDuration = F, mCanUseOutputTanks = F;
	public byte mEnergyInputs = 127, mEnergyOutput = SIDE_UNDEFINED, mOutputBlocked = 0, mMode = 0, mIgnited = 0;
	public byte mItemInputs   = 127, mItemOutputs  = 127, mItemAutoInput  = SIDE_UNDEFINED, mItemAutoOutput  = SIDE_UNDEFINED;
	public byte mFluidInputs  = 127, mFluidOutputs = 127, mFluidAutoInput = SIDE_UNDEFINED, mFluidAutoOutput = SIDE_UNDEFINED;
	public short mEfficiency = 10000;
	public int mParallel = 1;
	public long mEnergy = 0, mInputMin = 16, mInput = 32, mInputMax = 64, mMinEnergy = 0, mOutputEnergy = 0, mChargeRequirement = 0;
	public TagData mEnergyTypeAccepted = TD.Energy.TU, mEnergyTypeEmitted = TD.Energy.QU, mEnergyTypeCharged = TD.Energy.TU;
	public Recipe mLastRecipe = null, mCurrentRecipe = null;
	public FluidTankGT[] mTanksInput = ZL_FT, mTanksOutput = ZL_FT;
	public ItemStack[] mOutputItems = ZL_IS;
	public FluidStack[] mOutputFluids = ZL_FS;
	public IIconContainer[] mTexturesMaterial = L6_IICONCONTAINER, mTexturesInactive = L6_IICONCONTAINER, mTexturesActive = L6_IICONCONTAINER, mTexturesRunning = L6_IICONCONTAINER;
	
	public String mGUITexture = "";
	public RecipeMap mRecipes = RM.Furnace;
	public long mProgress = 0, mMaxProgress = 0;
	public boolean mSuccessful = F, mActive = F, mRunning = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mGUITexture = mRecipes.mGUIPath;
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_ACTIVE)) mCouldUseRecipe = mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_RUNNING)) mRunning = aNBT.getBoolean(NBT_RUNNING);
		if (aNBT.hasKey(NBT_STATE+".new")) mStateNew = aNBT.getBoolean(NBT_STATE+".new");
		if (aNBT.hasKey(NBT_STATE+".old")) mStateOld = aNBT.getBoolean(NBT_STATE+".old");
		if (aNBT.hasKey(NBT_NEEDS_IGNITION)) mRequiresIgnition = aNBT.getBoolean(NBT_NEEDS_IGNITION);
		if (aNBT.hasKey(NBT_CHEAP_OVERCLOCKING)) mCheapOverclocking = aNBT.getBoolean(NBT_CHEAP_OVERCLOCKING);
		if (aNBT.hasKey(NBT_NO_CONSTANT_POWER)) mNoConstantEnergy = aNBT.getBoolean(NBT_NO_CONSTANT_POWER);
		if (aNBT.hasKey(NBT_SPECIAL_IS_START_ENERGY)) mSpecialIsStartEnergy = aNBT.getBoolean(NBT_SPECIAL_IS_START_ENERGY);
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_INPUT)) {mInput = aNBT.getLong(NBT_INPUT); mInputMin = mInput / 2; mInputMax = mInput * 2;}
		if (aNBT.hasKey(NBT_INPUT_MIN)) {mInputMin = aNBT.getLong(NBT_INPUT_MIN);}
		if (aNBT.hasKey(NBT_INPUT_MAX)) {mInputMax = aNBT.getLong(NBT_INPUT_MAX);}
		if (aNBT.hasKey(NBT_MINENERGY)) {mMinEnergy = aNBT.getLong(NBT_MINENERGY);}
		if (aNBT.hasKey(NBT_PARALLEL)) {mParallel = Math.max(1, aNBT.getInteger(NBT_PARALLEL));}
		if (aNBT.hasKey(NBT_PARALLEL_DURATION)) mParallelDuration = aNBT.getBoolean(NBT_PARALLEL_DURATION);
		if (aNBT.hasKey(NBT_USE_OUTPUT_TANK)) mCanUseOutputTanks = aNBT.getBoolean(NBT_USE_OUTPUT_TANK);
		if (aNBT.hasKey(NBT_PROGRESS)) {mProgress = aNBT.getLong(NBT_PROGRESS);}
		if (aNBT.hasKey(NBT_MAXPROGRESS)) {mMaxProgress = aNBT.getLong(NBT_MAXPROGRESS);}
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_IGNITION)) mIgnited = aNBT.getByte(NBT_IGNITION);
		if (aNBT.hasKey(NBT_INV_SIDE_IN)) mItemInputs = (byte)(aNBT.getByte(NBT_INV_SIDE_IN) | SBIT_A);
		if (aNBT.hasKey(NBT_INV_SIDE_OUT)) mItemOutputs = (byte)(aNBT.getByte(NBT_INV_SIDE_OUT) | SBIT_A);
		if (aNBT.hasKey(NBT_INV_SIDE_AUTO_IN)) mItemAutoInput = aNBT.getByte(NBT_INV_SIDE_AUTO_IN);
		if (aNBT.hasKey(NBT_INV_SIDE_AUTO_OUT)) mItemAutoOutput = aNBT.getByte(NBT_INV_SIDE_AUTO_OUT);
		if (aNBT.hasKey(NBT_INV_DISABLED_IN)) mDisabledItemInput = aNBT.getBoolean(NBT_INV_DISABLED_IN);
		if (aNBT.hasKey(NBT_INV_DISABLED_OUT)) mDisabledItemOutput = aNBT.getBoolean(NBT_INV_DISABLED_OUT);
		if (aNBT.hasKey(NBT_TANK_SIDE_IN)) mFluidInputs = (byte)(aNBT.getByte(NBT_TANK_SIDE_IN) | SBIT_A);
		if (aNBT.hasKey(NBT_TANK_SIDE_OUT)) mFluidOutputs = (byte)(aNBT.getByte(NBT_TANK_SIDE_OUT) | SBIT_A);
		if (aNBT.hasKey(NBT_TANK_SIDE_AUTO_IN)) mFluidAutoInput = aNBT.getByte(NBT_TANK_SIDE_AUTO_IN);
		if (aNBT.hasKey(NBT_TANK_SIDE_AUTO_OUT)) mFluidAutoOutput = aNBT.getByte(NBT_TANK_SIDE_AUTO_OUT);
		if (aNBT.hasKey(NBT_TANK_DISABLED_IN)) mDisabledFluidInput = aNBT.getBoolean(NBT_TANK_DISABLED_IN);
		if (aNBT.hasKey(NBT_TANK_DISABLED_OUT)) mDisabledFluidOutput = aNBT.getBoolean(NBT_TANK_DISABLED_OUT);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED_2)) mEnergyTypeCharged = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED_2));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED_SIDES)) mEnergyInputs = (byte)(aNBT.getByte(NBT_ENERGY_ACCEPTED_SIDES) | SBIT_A);
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED_SIDES)) mEnergyOutput = aNBT.getByte(NBT_ENERGY_EMITTED_SIDES);
		if (aNBT.hasKey(NBT_OUTPUT)) mOutputEnergy = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_INPUT_EU)) mChargeRequirement = aNBT.getLong(NBT_INPUT_EU);
		
		long tCapacity = 1000;
		if (aNBT.hasKey(NBT_TANK_CAPACITY)) tCapacity = UT.Code.bindInt(aNBT.getLong(NBT_TANK_CAPACITY));
		mTanksInput = new FluidTankGT[mRecipes.mInputFluidCount];
		for (int i = 0; i < mTanksInput.length; i++) mTanksInput[i] = new FluidTankGT(tCapacity).setCapacity(mRecipes, mParallel * 2L).readFromNBT(aNBT, NBT_TANK+".in."+i);
		mTanksOutput = new FluidTankGT[mRecipes.mOutputFluidCount];
		for (int i = 0; i < mTanksOutput.length; i++) mTanksOutput[i] = new FluidTankGT().readFromNBT(aNBT, NBT_TANK+".out."+i);
		
		mOutputFluids = new FluidStack[mRecipes.mOutputFluidCount];
		for (int i = 0; i < mOutputFluids.length; i++) mOutputFluids[i] = FL.load(aNBT, NBT_TANK_OUT+"."+i);
		mOutputItems = new ItemStack[mRecipes.mOutputItemsCount];
		for (int i = 0; i < mOutputItems.length; i++) mOutputItems[i] = ST.load(aNBT, NBT_INV_OUT+"."+i);
		
		if (CODE_CLIENT) {
			if (aNBT.hasKey(NBT_GUI)) {
				mGUITexture = aNBT.getString(NBT_GUI);
				if (!mGUITexture.endsWith(".png")) mGUITexture += ".png";
			}
			if (GT_API.sBlockIcons == null && aNBT.hasKey(NBT_TEXTURE)) {
				String tTextureName = aNBT.getString(NBT_TEXTURE);
				mTexturesMaterial = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/top"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/left"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/front"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/right"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/colored/back")};
				mTexturesInactive = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/top"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/left"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/front"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/right"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay/back")};
				mTexturesActive = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/top"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/left"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/front"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/right"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_active/back")};
				mTexturesRunning = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/top"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/left"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/front"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/right"),
				new Textures.BlockIcons.CustomIcon("machines/basicmachines/"+tTextureName+"/overlay_running/back")};
			} else {
				TileEntity tCanonicalTileEntity = MultiTileEntityRegistry.getCanonicalTileEntity(getMultiTileEntityRegistryID(), getMultiTileEntityID());
				if (tCanonicalTileEntity instanceof MultiTileEntityBasicMachine) {
					mTexturesMaterial = ((MultiTileEntityBasicMachine)tCanonicalTileEntity).mTexturesMaterial;
					mTexturesInactive = ((MultiTileEntityBasicMachine)tCanonicalTileEntity).mTexturesInactive;
					mTexturesRunning  = ((MultiTileEntityBasicMachine)tCanonicalTileEntity).mTexturesRunning;
					mTexturesActive   = ((MultiTileEntityBasicMachine)tCanonicalTileEntity).mTexturesActive;
				} else {
					mTexturesMaterial = mTexturesInactive = mTexturesRunning = mTexturesActive = L6_IICONCONTAINER;
				}
			}
		}
		
		updateAccessibleSlots();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_MINENERGY, mMinEnergy);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mProgress);
		UT.NBT.setNumber(aNBT, NBT_MAXPROGRESS, mMaxProgress);
		UT.NBT.setNumber(aNBT, NBT_OUTPUT, mOutputEnergy);
		UT.NBT.setNumber(aNBT, NBT_INPUT_EU, mChargeRequirement);
		
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_RUNNING, mRunning);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setBoolean(aNBT, NBT_STATE+".new", mStateNew);
		UT.NBT.setBoolean(aNBT, NBT_STATE+".old", mStateOld);
		
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_IGNITION, mIgnited);
		UT.NBT.setBoolean(aNBT, NBT_INV_DISABLED_IN, mDisabledItemInput);
		UT.NBT.setBoolean(aNBT, NBT_INV_DISABLED_OUT, mDisabledItemOutput);
		UT.NBT.setBoolean(aNBT, NBT_TANK_DISABLED_IN, mDisabledFluidInput);
		UT.NBT.setBoolean(aNBT, NBT_TANK_DISABLED_OUT, mDisabledFluidOutput);
		
		for (int i = 0; i < mTanksInput  .length; i++) mTanksInput [i].writeToNBT(aNBT, NBT_TANK+".in." +i);
		for (int i = 0; i < mTanksOutput .length; i++) mTanksOutput[i].writeToNBT(aNBT, NBT_TANK+".out."+i);
		for (int i = 0; i < mOutputFluids.length; i++) FL.save(aNBT, NBT_TANK_OUT+"."+i, mOutputFluids[i]);
		for (int i = 0; i < mOutputItems .length; i++) ST.save(aNBT, NBT_INV_OUT +"."+i, mOutputItems [i]);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		UT.NBT.setBoolean(aNBT, NBT_INV_DISABLED_IN, mDisabledItemInput);
		UT.NBT.setBoolean(aNBT, NBT_INV_DISABLED_OUT, mDisabledItemOutput);
		UT.NBT.setBoolean(aNBT, NBT_TANK_DISABLED_IN, mDisabledFluidInput);
		UT.NBT.setBoolean(aNBT, NBT_TANK_DISABLED_OUT, mDisabledFluidOutput);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal) + (mParallel > 1 ? " (up to "+mParallel+"x processed per run)" : ""));
		
		if (mCheapOverclocking)
		aList.add(Chat.YELLOW + LH.get(LH.CHEAP_OVERCLOCKING));
		if (mEfficiency != 10000)
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		
		addToolTipsSided(aList, aStack, aF3_H);
		
		if (mRequiresIgnition)
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE));
		
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		if (SIDES_VALID[mFluidAutoInput] || SIDES_VALID[mItemAutoInput])
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH));
		if (SIDES_VALID[mFluidAutoOutput] || SIDES_VALID[mItemAutoOutput])
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addToolTipsSided(List<String> aList, ItemStack aStack, boolean aF3_H) {
		String tSideNames = "";
		if (mEnergyTypeAccepted != TD.Energy.TU) {
			if (mEnergyInputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mEnergyInputs]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide]);}
			}
			LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, null, tSideNames, null);
			tSideNames = "";
		}
		if (mEnergyTypeCharged != TD.Energy.TU) {
			if (mEnergyInputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mEnergyInputs]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide]);}
			}
			LH.addEnergyToolTips(this, aList, mEnergyTypeCharged, null, tSideNames, null);
			tSideNames = "";
		}
		if (mRecipes.mInputItemsCount > 0) {
			if (mItemInputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mItemInputs  ]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide])+(tSide==mItemAutoInput  ?" (auto)":"");}
				if (UT.Code.stringValid(tSideNames)) aList.add(Chat.GREEN   + LH.get(LH.ITEM_INPUT)     + ": " + Chat.WHITE + tSideNames);
				tSideNames = "";
			} else if (SIDES_VALID[mItemAutoInput]) {
				aList.add(Chat.GREEN + LH.get(LH.ITEM_INPUT) + ": " + Chat.WHITE + LH.get(LH.FACES[mItemAutoInput]) + " (auto, otherwise any)");
			} else {
				aList.add(Chat.GREEN + LH.get(LH.ITEM_INPUT) + ": " + Chat.WHITE + LH.get(LH.FACE_ANY) + " (no auto)");
			}
		}
		if (mRecipes.mOutputItemsCount > 0) {
			if (mItemOutputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mItemOutputs ]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide])+(tSide==mItemAutoOutput ?" (auto)":"");}
				if (UT.Code.stringValid(tSideNames)) aList.add(Chat.RED     + LH.get(LH.ITEM_OUTPUT)    + ": " + Chat.WHITE + tSideNames);
				tSideNames = "";
			} else if (SIDES_VALID[mItemAutoOutput]) {
				aList.add(Chat.RED + LH.get(LH.ITEM_OUTPUT) + ": " + Chat.WHITE + LH.get(LH.FACES[mItemAutoOutput]) + " (auto, otherwise any)");
			} else {
				aList.add(Chat.RED + LH.get(LH.ITEM_OUTPUT) + ": " + Chat.WHITE + LH.get(LH.FACE_ANY) + " (no auto)");
			}
		}
		if (mRecipes.mInputFluidCount > 0) {
			if (mFluidInputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mFluidInputs ]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide])+(tSide==mFluidAutoInput ?" (auto)":"");}
				if (UT.Code.stringValid(tSideNames)) aList.add(Chat.GREEN   + LH.get(LH.FLUID_INPUT)    + ": " + Chat.WHITE + tSideNames);
				tSideNames = "";
			} else if (SIDES_VALID[mFluidAutoInput]) {
				aList.add(Chat.GREEN + LH.get(LH.FLUID_INPUT) + ": " + Chat.WHITE + LH.get(LH.FACES[mFluidAutoInput]) + " (auto, otherwise any)");
			} else {
				aList.add(Chat.GREEN + LH.get(LH.FLUID_INPUT) + ": " + Chat.WHITE + LH.get(LH.FACE_ANY) + " (no auto)");
			}
		}
		if (mRecipes.mOutputFluidCount > 0) {
			if (mFluidOutputs != 127) {
				for (byte tSide : ALL_SIDES_VALID) if (FACE_CONNECTED[tSide][mFluidOutputs]) {tSideNames += (UT.Code.stringValid(tSideNames)?", ":"")+LH.get(LH.FACES[tSide])+(tSide==mFluidAutoOutput?" (auto)":"");}
				if (UT.Code.stringValid(tSideNames)) aList.add(Chat.RED     + LH.get(LH.FLUID_OUTPUT)   + ": " + Chat.WHITE + tSideNames);
			} else if (SIDES_VALID[mFluidAutoOutput]) {
				aList.add(Chat.RED + LH.get(LH.FLUID_OUTPUT) + ": " + Chat.WHITE + LH.get(LH.FACES[mFluidAutoOutput]) + " (auto, otherwise any)");
			} else {
				aList.add(Chat.RED + LH.get(LH.FLUID_OUTPUT) + ": " + Chat.WHITE + LH.get(LH.FACE_ANY) + " (no auto)");
			}
		}
	}
	
	public long onToolClick3(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ, ChunkCoordinates aFrom) {
		return onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_screwdriver)) {
			mMode = (byte)((mMode + 1) % 4);
			aChatReturn.add("========================================");
			aChatReturn.add((mMode & 1) != 0 ?"Only produce when Output is completely empty":"Produce whenever there is space");
			aChatReturn.add((mMode & 2) != 0 ?"Only accept Input on empty Input Slots":"Accept Input on all Input Slots");
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			mProgress = mMinEnergy = mMaxProgress = mOutputEnergy = mChargeRequirement = 0;
			mOutputFluids = ZL_FS;
			mOutputItems = ZL_IS;
			updateInventory();
			return 10000;
		}
		if (aTool.equals(TOOL_igniter)) {
			if (mRequiresIgnition) {
				mIgnited = 40;
				return 10000;
			}
			return 0;
		}
		if (aTool.equals(TOOL_plunger)) {
			updateInventory();
			for (FluidTankGT tTank : mTanksOutput) {long rAmount = GarbageGT.trash(tTank, 1000); if (rAmount > 0) return rAmount;}
			for (FluidTankGT tTank : mTanksInput ) {long rAmount = GarbageGT.trash(tTank, 1000); if (rAmount > 0) return rAmount;}
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			long rOutput = 0;
			if (FACING_TO_SIDE[mFacing][mItemAutoInput] == aSide) {
				mDisabledItemInput = !mDisabledItemInput;
				aChatReturn.add(mDisabledItemInput?"Auto Item Input Disabled":"Auto Item Input Enabled");
				rOutput += 10000;
			}
			if (FACING_TO_SIDE[mFacing][mItemAutoOutput] == aSide) {
				mDisabledItemOutput = !mDisabledItemOutput;
				aChatReturn.add(mDisabledItemOutput?"Auto Item Output Disabled":"Auto Item Output Enabled");
				rOutput += 10000;
			}
			if (FACING_TO_SIDE[mFacing][mFluidAutoInput] == aSide) {
				mDisabledFluidInput = !mDisabledFluidInput;
				aChatReturn.add(mDisabledFluidInput?"Auto Fluid Input Disabled":"Auto Fluid Input Enabled");
				rOutput += 10000;
			}
			if (FACING_TO_SIDE[mFacing][mFluidAutoOutput] == aSide) {
				mDisabledFluidOutput = !mDisabledFluidOutput;
				aChatReturn.add(mDisabledFluidOutput?"Auto Fluid Output Disabled":"Auto Fluid Output Enabled");
				rOutput += 10000;
			}
			if (rOutput > 0) {
				updateInventory();
				return rOutput;
			}
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) onMagnifyingGlass(aChatReturn);
			return 1;
		}
		return 0;
	}
	
	public void onMagnifyingGlass(List<String> aChatReturn) {
		aChatReturn.add((mMode & 1) != 0 ?"Only produce when Output is completely empty":"Produce whenever there is space");
		aChatReturn.add((mMode & 2) != 0 ?"Only accept Input on empty Input Slots":"Accept Input on all Input Slots");
		if (SIDES_VALID[mItemAutoInput  ]) aChatReturn.add(mDisabledItemInput  ?"Auto Item Input Disabled"  :"Auto Item Input Enabled"  );
		if (SIDES_VALID[mItemAutoOutput ]) aChatReturn.add(mDisabledItemOutput ?"Auto Item Output Disabled" :"Auto Item Output Enabled" );
		if (SIDES_VALID[mFluidAutoInput ]) aChatReturn.add(mDisabledFluidInput ?"Auto Fluid Input Disabled" :"Auto Fluid Input Enabled" );
		if (SIDES_VALID[mFluidAutoOutput]) aChatReturn.add(mDisabledFluidOutput?"Auto Fluid Output Disabled":"Auto Fluid Output Enabled");
	}
	
	@Override
	public void onCoordinateChange() {
		updateAdjacentToggleableEnergySources();
	}
	
	@Override
	public void onTickFailed(long aTimer, boolean aIsServerSide) {
		super.onTickFailed(aTimer, aIsServerSide);
		// Just to prevent Infinite Item dupes in case this happens during the Processing Functions.
		mProgress = mMinEnergy = mMaxProgress = mOutputEnergy = mChargeRequirement = 0;
		mOutputFluids = ZL_FS;
		mOutputItems = ZL_IS;
	}
	
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		super.onTickFirst2(aIsServerSide);
		if (aIsServerSide) {
			updateAdjacentToggleableEnergySources();
			if (checkStructure(T) && !mActive) checkRecipe(F, mRunning || mStopped);
		}
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBlockUpdated) updateAdjacentToggleableEnergySources();
			if (!mStopped) {
				if (mEnergyTypeAccepted == TD.Energy.TU) mEnergy++;
				if (mChargeRequirement > 0 && mEnergyTypeCharged == TD.Energy.TU) mChargeRequirement--;
			}
			
			if (!mDisabledFluidOutput && SIDES_VALID[mFluidAutoOutput]) doOutputFluids();
			
			doWork(aTimer);
			
			if (mTimer % 600 == 5 && mRunning) doDefaultStructuralChecks();
			
			for (int i = 0; i < mTanksInput .length; i++) slot(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount + 1 + i                       , FL.display(mTanksInput [i], T, T));
			for (int i = 0; i < mTanksOutput.length; i++) slot(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount + 1 + i + mTanksInput.length  , FL.display(mTanksOutput[i], T, T));
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mActive != oActive || mRunning != oRunning || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oRunning = mRunning;
		oActive  = mActive;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) openGUI(aPlayer, aSide);
		return T;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mStopped) return 0;
		boolean tPositive = (aSize > 0);
		aSize = Math.abs(aSize);
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overcharge(aSize, aEnergyType);
			return aAmount;
		}
		if (aEnergyType == mEnergyTypeCharged && mChargeRequirement > 0) {
			if (aDoInject) mChargeRequirement -= aSize * aAmount;
			return aAmount;
		}
		if (aEnergyType == mEnergyTypeAccepted) {
			if (aDoInject) mStateNew = tPositive;
			long tInput = Math.min(mInputMax - mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
			if (aDoInject) mEnergy += tConsumed * aSize;
			return tConsumed;
		}
		return 0;
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? aEnergyType == mEnergyTypeEmitted : aEnergyType == mEnergyTypeAccepted || aEnergyType == mEnergyTypeCharged;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) &&                   FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mEnergyInputs] && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && (SIDES_INVALID[mEnergyOutput] || FACING_ROTATIONS[mFacing][aSide]==mEnergyOutput) && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return mInputMin;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return mInputMax;}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mInputMax;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return Integer.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	// Inventory Stuff
	
	@Override
	public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
		ACCESSIBLE_SLOTS = UT.Code.getAscendingArray(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount);
		ACCESSIBLE_INPUTS = UT.Code.getAscendingArray(mRecipes.mInputItemsCount);
		ACCESSIBLE_OUTPUTS = new int[mRecipes.mOutputItemsCount];
		for (int i = 0; i < ACCESSIBLE_OUTPUTS.length; i++) ACCESSIBLE_OUTPUTS[i] = i + mRecipes.mInputItemsCount;
		return new ItemStack[mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount + 1 + mRecipes.mInputFluidCount + mRecipes.mOutputFluidCount];
	}
	
	public void updateAccessibleSlots() {
		for (byte i = 0; i < ACCESSIBLE.length; i++) {
			if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][i]][mItemInputs]) {
				if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][i]][mItemOutputs]) ACCESSIBLE[i] = ACCESSIBLE_SLOTS; else ACCESSIBLE[i] = ACCESSIBLE_INPUTS;
			} else {
				if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][i]][mItemOutputs]) ACCESSIBLE[i] = ACCESSIBLE_OUTPUTS; else ACCESSIBLE[i] = ZL_INTEGER;
			}
		}
	}
	
	public int[][] ACCESSIBLE = new int[7][];
	public int[] ACCESSIBLE_SLOTS, ACCESSIBLE_INPUTS, ACCESSIBLE_OUTPUTS;
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE[aSide];}
	@Override public boolean canDrop(int aInventorySlot) {return aInventorySlot < mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount + 1;}
	
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (aSlot >= mRecipes.mInputItemsCount) return F;
		if ((mMode & 2) != 0 && slotHas(aSlot)) return F;
		for (int i = 0; i < mRecipes.mInputItemsCount; i++) if (ST.equal(aStack, slot(i), T)) return i == aSlot;
		return mRecipes.containsInput(aStack, this, slot(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount));
	}
	
	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		return aSlot >= mRecipes.mInputItemsCount && aSlot < mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount;
	}
	
	// Tank things
	
	@Override
	public IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		if (!FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mFluidInputs]) return null;
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].contains(aFluidToFill)) return mTanksInput[i];
		if (!mRecipes.containsInput(aFluidToFill, this, slot(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount))) return null;
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].isEmpty()) return mTanksInput[i];
		return null;
	}
	
	@Override
	public IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (!FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mFluidOutputs]) return null;
		if (aFluidToDrain == null) {
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].has()) return mTanksOutput[i];
		} else {
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].contains(aFluidToDrain)) return mTanksOutput[i];
		}
		return null;
	}
	
	@Override
	public IFluidTank[] getFluidTanks2(byte aSide) {
		if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mFluidInputs]) {
			if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mFluidOutputs]) {
				IFluidTank[] rTanks = new IFluidTank[mTanksInput.length + mTanksOutput.length];
				for (int i = 0; i < mTanksInput .length; i++) rTanks[i] = mTanksInput[i];
				for (int i = 0; i < mTanksOutput.length; i++) rTanks[mTanksInput.length+i] = mTanksOutput[i];
				return rTanks;
			}
			return mTanksInput;
		}
		if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mFluidOutputs]) return mTanksOutput;
		return ZL_FT;
	}
	
	@Override
	public boolean breakBlock() {
		setStateOnOff(T);
		GarbageGT.trash(mTanksInput);
		GarbageGT.trash(mTanksOutput);
		return super.breakBlock();
	}
	
	public void updateAdjacentToggleableEnergySources() {
		for (byte tSide : ALL_SIDES_VALID) if (isEnergyAcceptingFrom(mEnergyTypeAccepted, tSide, T)) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
				((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
			}
		}
	}
	
	// Stuff to Override
	
	public int canOutput(Recipe aRecipe) {
		int rMaxTimes = mParallel;
		
		// Don't do more than 1-4 Minutes worth of Input at a time, when doing the Chain Processing.
		if (mParallelDuration) {
			// Ugh, I do not feel like Maths right now, but the previous incarnation of this seemed a tiny bit wrong, so I will make sure it works properly.
			while (rMaxTimes > 1 && aRecipe.getAbsoluteTotalPower() * rMaxTimes > mInputMax * 1200) rMaxTimes--;
		}
		
		for (int i = 0, j = mRecipes.mInputItemsCount; i < mRecipes.mOutputItemsCount && i < aRecipe.mOutputs.length; i++, j++) if (ST.valid(aRecipe.mOutputs[i])) {
			if (slotHas(j)) {
				if ((mMode & 1) != 0 || aRecipe.mNeedsEmptyOutput) {
					mOutputBlocked++;
					return 0;
				}
				if (!ST.equal(slot(j), aRecipe.mOutputs[i], F)) {
					mOutputBlocked++;
					return 0;
				}
				rMaxTimes = Math.min(rMaxTimes, (slot(j).getMaxStackSize() - slot(j).stackSize) / aRecipe.mOutputs[i].stackSize);
				if (rMaxTimes <= 0) {
					mOutputBlocked++;
					return 0;
				}
			} else {
				rMaxTimes = Math.min(rMaxTimes, Math.max(1, 64 / aRecipe.mOutputs[i].stackSize));
			}
		}
		if (aRecipe.mFluidOutputs.length > 0) {
			int tEmptyOutputTanks = 0, tRequiredEmptyTanks = aRecipe.mFluidOutputs.length;
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].isEmpty()) tEmptyOutputTanks++; else if (aRecipe.mNeedsEmptyOutput || (mMode & 1) != 0) return 0;
			// This optimisation would not work! The Tanks would not have an Output Amount Limiter if this was in the Code!
			//if (tRequiredEmptyTanks <= tEmptyOutputTanks) {
			for (int j = 0; j < aRecipe.mFluidOutputs.length; j++) {
				if (aRecipe.mFluidOutputs[j] == null) {
					tRequiredEmptyTanks--;
				} else for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].contains(aRecipe.mFluidOutputs[j])) {
					if (mTanksOutput[i].has(Math.max(16000, 1+aRecipe.mFluidOutputs[j].amount*mParallel)) && !FluidsGT.VOID_OVERFLOW.contains(aRecipe.mFluidOutputs[j].getFluid().getName())) return 0;
					tRequiredEmptyTanks--;
					break;
				}
			}
			if (tRequiredEmptyTanks > tEmptyOutputTanks) return 0;
			//}
		}
		return rMaxTimes;
	}
	
	/** return codes for checkRecipe() */
	public static final int
	DID_NOT_FIND_RECIPE = 0,
	FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS = 1,
	FOUND_AND_SUCCESSFULLY_USED_RECIPE = 2,
	FOUND_AND_COULD_HAVE_USED_RECIPE = 3;
	
	/**
	 * Override this to check the Recipes yourself, super calls to this could be useful if you just want to add a special case
	 * I thought about Enum too, but Enum doesn't add support for people adding other return Systems.
	 * Funny how Eclipse marks the word Enum as not correctly spelled.
	 * @return see constants above
	 */
	public int checkRecipe(boolean aApplyRecipe, boolean aUseAutoInputs) {
		mCouldUseRecipe = F;
		if (mRecipes == null) return DID_NOT_FIND_RECIPE;
		
		byte tAutoInput = FACING_TO_SIDE[mFacing][mItemAutoInput];
		
		if (aUseAutoInputs && !mDisabledItemInput && SIDES_VALID[tAutoInput]) {
			ST.moveAll(getItemInputTarget(tAutoInput), delegator(tAutoInput));
		}
		
		int tInputItemsCount = 0, tInputFluidsCount = 0;
		ItemStack[] tInputs = new ItemStack[mRecipes.mInputItemsCount];
		for (int i = 0; i < mRecipes.mInputItemsCount; i++) {
			tInputs[i] = slot(i);
			if (ST.valid(tInputs[i])) tInputItemsCount++;
		}
		
		tAutoInput = FACING_TO_SIDE[mFacing][mFluidAutoInput];
		if (aUseAutoInputs && !mDisabledFluidInput && SIDES_VALID[tAutoInput]) {
			DelegatorTileEntity<IFluidHandler> tTileEntity = getFluidInputTarget(tAutoInput);
			if (tTileEntity != null && tTileEntity.mTileEntity != null) {
				FluidTankInfo[] tInfos = tTileEntity.mTileEntity.getTankInfo(FORGE_DIR[tTileEntity.mSideOfTileEntity]);
				if (tInfos != null) for (FluidTankInfo tInfo : tInfos) if (tInfo != null && tInfo.fluid != null && tInfo.fluid.amount > 0 && getFluidTankFillable(SIDE_ANY, tInfo.fluid) != null) {
					if (FL.move_(tTileEntity, delegator(tAutoInput), tInfo.fluid) > 0) updateInventory();
				}
			}
		}
		for (FluidTankGT tTank : mTanksInput) if (tTank.has()) tInputFluidsCount++;
		
		if (tInputItemsCount                     < mRecipes.mMinimalInputItems ) return DID_NOT_FIND_RECIPE;
		if (tInputFluidsCount                    < mRecipes.mMinimalInputFluids) return DID_NOT_FIND_RECIPE;
		if (tInputItemsCount + tInputFluidsCount < mRecipes.mMinimalInputs     ) return DID_NOT_FIND_RECIPE;
		
		Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, mEnergyTypeAccepted == TD.Energy.RF ? mInputMax / RF_PER_EU : mInputMax, slot(mRecipes.mInputItemsCount+mRecipes.mOutputItemsCount), mTanksInput, tInputs);
		
		int tMaxProcessCount = 0;
		
		if (tRecipe == null) {
			if (!mCanUseOutputTanks) return DID_NOT_FIND_RECIPE;
			tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, mEnergyTypeAccepted == TD.Energy.RF ? mInputMax / RF_PER_EU : mInputMax, slot(mRecipes.mInputItemsCount+mRecipes.mOutputItemsCount), mTanksOutput, tInputs);
			if (tRecipe == null) return DID_NOT_FIND_RECIPE;
			
			if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
			tMaxProcessCount = canOutput(tRecipe);
			if (tMaxProcessCount <= 0) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
			if (aApplyRecipe) aApplyRecipe = (!mRequiresIgnition || mIgnited > 0 || mActive);
			if (!tRecipe.isRecipeInputEqual(aApplyRecipe, F, mTanksOutput, tInputs)) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
			mCouldUseRecipe = T;
			if (!aApplyRecipe) return FOUND_AND_COULD_HAVE_USED_RECIPE;
			
			if (tMaxProcessCount > 1) {
				if (!mParallelDuration && mEnergyTypeAccepted != TD.Energy.TU) tMaxProcessCount = (int)UT.Code.bind(1, tMaxProcessCount, mInput / Math.max(1, (mEnergyTypeAccepted == TD.Energy.RF ? tRecipe.mEUt * RF_PER_EU : tRecipe.mEUt)));
				tMaxProcessCount = 1+tRecipe.isRecipeInputEqual(tMaxProcessCount-1, mTanksOutput, tInputs);
			}
		} else {
			if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
			tMaxProcessCount = canOutput(tRecipe);
			if (tMaxProcessCount <= 0) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
			if (aApplyRecipe) aApplyRecipe = (!mRequiresIgnition || mIgnited > 0 || mActive);
			if (!tRecipe.isRecipeInputEqual(aApplyRecipe, F, mTanksInput, tInputs)) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
			mCouldUseRecipe = T;
			if (!aApplyRecipe) return FOUND_AND_COULD_HAVE_USED_RECIPE;
			
			if (tMaxProcessCount > 1) {
				if (!mParallelDuration && mEnergyTypeAccepted != TD.Energy.TU) tMaxProcessCount = (int)UT.Code.bind(1, tMaxProcessCount, mInput / Math.max(1, (mEnergyTypeAccepted == TD.Energy.RF ? tRecipe.mEUt * RF_PER_EU : tRecipe.mEUt)));
				tMaxProcessCount = 1+tRecipe.isRecipeInputEqual(tMaxProcessCount-1, mTanksInput, tInputs);
			}
		}
		
		for (byte tSide : ALL_SIDES_VALID_FIRST[FACING_TO_SIDE[mFacing][mItemAutoInput]]) if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][tSide]][mItemInputs]) {
			DelegatorTileEntity<IInventory> tDelegator = getItemInputTarget(tSide);
			if (tDelegator != null && tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
				((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
			}
		}
		
		if (mSpecialIsStartEnergy && (!mActive || (mCurrentRecipe != null && mCurrentRecipe != tRecipe))) mChargeRequirement = tRecipe.mSpecialValue;
		
		mCurrentRecipe = tRecipe;
		mOutputItems   = tRecipe.getOutputs(RNGSUS, tMaxProcessCount);
		mOutputFluids  = tRecipe.getFluidOutputs(RNGSUS, tMaxProcessCount);
		
		if (tRecipe.mEUt < 0) {
			mOutputEnergy = -tRecipe.mEUt;
			mMaxProgress = tRecipe.mDuration;
			mMinEnergy = 0;
		} else {
			if (mParallelDuration) {
				mMinEnergy = Math.max(1, (mEnergyTypeAccepted == TD.Energy.RF ? tRecipe.mEUt * RF_PER_EU : mEnergyTypeAccepted == TD.Energy.TU ? tRecipe.mEUt : tRecipe.mEUt));
				mMaxProgress = Math.max(1, UT.Code.units(mMinEnergy * Math.max(1, tRecipe.mDuration) * tMaxProcessCount, mEfficiency, 10000, T));
			} else {
				mMinEnergy = Math.max(1, (mEnergyTypeAccepted == TD.Energy.RF ? tRecipe.mEUt * RF_PER_EU * tMaxProcessCount : mEnergyTypeAccepted == TD.Energy.TU ? tRecipe.mEUt : tRecipe.mEUt * tMaxProcessCount));
				mMaxProgress = Math.max(1, UT.Code.units(mMinEnergy * Math.max(1, tRecipe.mDuration), mEfficiency, 10000, T));
			}
			if (mMinEnergy > 0 && !mCheapOverclocking) while (mMinEnergy < mInputMin && mMinEnergy * 4 <= mInputMax) {mMinEnergy *= 4; mMaxProgress *= 2;}
		}
		
		removeAllDroppableNullStacks();
		return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
	}
	
	public void doWork(long aTimer) {
		if ((mEnergy >= mInputMin || mEnergyTypeAccepted == TD.Energy.TU) && mEnergy >= mMinEnergy && checkStructure(F)) {
			mActive = doActive(aTimer, Math.min(mInputMax, mEnergy));
			mRunning = T;
		} else {
			if (aTimer > 40) {
				mActive = doInactive(aTimer);
				mRunning = F;
			}
			mSuccessful = F;
		}
		mEnergy -= mInputMax; if (mEnergy < 0) mEnergy = 0;
		if (mIgnited > 0) mIgnited--;
	}
	
	public boolean doActive(long aTimer, long aEnergy) {
		boolean rActive = F;
		
		if (mMaxProgress <= 0) {
			// Successfully produced something or just got ignited || Some Inventory Stuff changes || The Machine has just been turned ON || Check once every Minute
			if ((mIgnited > 0 || mInventoryChanged || !mRunning || aTimer%1200 == 5) && checkRecipe(!mStopped, T) == FOUND_AND_SUCCESSFULLY_USED_RECIPE) {
				onProcessStarted();
			} else {
				mProgress = 0;
			}
		}
		
		mSuccessful = F;
		
		if (mMaxProgress > 0 && !(mSpecialIsStartEnergy && mChargeRequirement > 0)) {
			rActive = T;
			if (mProgress <= mMaxProgress) {
				if (mOutputEnergy > 0) doOutputEnergy();
				mProgress += aEnergy;
			}
			if (mProgress >= mMaxProgress && (mStateOld&&!mStateNew || !TD.Energy.ALL_ALTERNATING.contains(mEnergyTypeAccepted))) {
				for (int i = 0; i < mOutputItems .length; i++) if (mOutputItems [i] != null && addStackToSlot(mRecipes.mInputItemsCount+(i % mRecipes.mOutputItemsCount), mOutputItems[i])) {mSuccessful = T; mIgnited = 40; mOutputItems[i] = null; continue;}
				for (int i = 0; i < mOutputFluids.length; i++) if (mOutputFluids[i] != null) for (int j = 0; j < mTanksOutput.length; j++) {
					if (mTanksOutput[j].contains(mOutputFluids[i])) {
						updateInventory();
						mTanksOutput[j].add(mOutputFluids[i].amount);
						mSuccessful = T;
						mIgnited = 40;
						mOutputFluids[i] = null;
						break;
					}
				}
				for (int i = 0; i < mOutputFluids.length; i++) if (mOutputFluids[i] != null) for (int j = 0; j < mTanksOutput.length; j++) {
					if (mTanksOutput[j].isEmpty()) {
						mTanksOutput[j].setFluid(mOutputFluids[i]);
						mSuccessful = T;
						mIgnited = 40;
						mOutputFluids[i] = null;
						break;
					}
				}
				
				if (UT.Code.containsSomething(mOutputItems) || UT.Code.containsSomething(mOutputFluids)) {
					mMinEnergy = 0;
					mOutputEnergy = 0;
					mChargeRequirement = 0;
					mProgress = mMaxProgress;
				} else {
					mProgress -= mMaxProgress; // this way the leftover energy can be used on the next processed thing, unless it gets stuck on an output.
					mMinEnergy = 0;
					mMaxProgress = 0;
					mOutputEnergy = 0;
					mChargeRequirement = 0;
					mOutputItems = ZL_IS;
					mOutputFluids = ZL_FS;
					mSuccessful = T;
					mIgnited = 40;
					
					for (byte tSide : ALL_SIDES_VALID_FIRST[FACING_TO_SIDE[mFacing][mItemAutoOutput]]) if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][tSide]][mItemOutputs]) {
						DelegatorTileEntity<TileEntity> tDelegator = getItemOutputTarget(tSide);
						if (tDelegator != null && tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
							((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
						}
					}
					
					onProcessFinished();
				}
			}
		}
		
		mStateOld = mStateNew;
		
		if (!mDisabledItemOutput && SIDES_VALID[mItemAutoOutput]) {
			boolean
			tOutputEmpty = T;
			for (int i = mRecipes.mInputItemsCount, j = i + mRecipes.mOutputItemsCount; i < j; i++) if (slotHas(i)) {tOutputEmpty = F; break;}
			
			// Output not Empty && (Successfully produced something or just got ignited || Some Inventory Stuff changes || The Machine has just been turned ON || Output has been blocked since 256 active ticks || Check once every 10 Seconds)
			if (!tOutputEmpty && (mIgnited > 0 || mInventoryChanged || !mRunning || mOutputBlocked == 1 || aTimer%200 == 5)) {
				boolean tInventoryChanged = mInventoryChanged;
				mInventoryChanged = F;
				doOutputItems();
				if (mInventoryChanged) mOutputBlocked = 0; else mInventoryChanged |= tInventoryChanged;
			}
			
			tOutputEmpty = T;
			for (int i = mRecipes.mInputItemsCount, j = i + mRecipes.mOutputItemsCount; i < j; i++) if (slotHas(i)) {tOutputEmpty = F; mOutputBlocked++; break;}
			
			if (tOutputEmpty) mOutputBlocked = 0;
		}
		
		return rActive;
	}
	
	public boolean doInactive(long aTimer) {
		if (mActive) {
			doSoundInterrupt();
			if (!mDisabledItemOutput) doOutputItems();
		}
		if (CONSTANT_ENERGY && !mNoConstantEnergy) mProgress = 0;
		if (mRunning || mIgnited > 0 || mInventoryChanged || aTimer%1200 == 5) {
			if (!checkStructure(F)) checkStructure(T);
			checkRecipe(F, T);
		}
		return F;
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		for (FluidTankGT tTank : mTanksInput) if (tTank.contains(aFluid)) {
			updateInventory();
			return tTank.fill(aFluid, aDoFill);
		}
		if (!mRecipes.containsInput(aFluid, this, slot(mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount))) return 0;
		for (FluidTankGT tTank : mTanksInput) if (tTank.isEmpty()) {
			updateInventory();
			return tTank.fill(aFluid, aDoFill);
		}
		return 0;
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		for (FluidTankGT tTank : mTanksOutput) if (tTank.has() && !FL.gas(tTank)) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksInput) if (tTank.has() && !FL.gas(tTank)) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksOutput) if (tTank.has()) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksInput) if (tTank.has()) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		return null;
	}
	
	@Override
	public FluidStack nozzleDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		for (FluidTankGT tTank : mTanksOutput) if (tTank.has() && FL.gas(tTank)) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksInput) if (tTank.has() && FL.gas(tTank)) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksOutput) if (tTank.has()) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		for (FluidTankGT tTank : mTanksInput) if (tTank.has()) {
			updateInventory();
			return tTank.drain(aMaxDrain, aDoDrain);
		}
		return null;
	}
	
	public boolean doSoundInterrupt() {
		return UT.Sounds.send(mRequiresIgnition?SFX.MC_FIZZ:mNoConstantEnergy?SFX.IC_MACHINE_INTERRUPT:SFX.MC_CLICK, this);
	}
	
	public boolean checkStructure(boolean aForceReset) {
		return T;
	}
	
	public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {
		return getAdjacentInventory(aSide);
	}
	
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(aSide);
	}
	
	public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {
		return getAdjacentTank(aSide);
	}
	
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(aSide);
	}
	
	public void doOutputItems() {
		byte tAutoOutput = FACING_TO_SIDE[mFacing][mItemAutoOutput];
		ST.moveAll(delegator(tAutoOutput), getItemOutputTarget(tAutoOutput));
	}
	
	public void doOutputFluids() {
		for (FluidTankGT tCheck : mTanksOutput) if (tCheck.has()) {if (FL.move(tCheck, getFluidOutputTarget(FACING_TO_SIDE[mFacing][mFluidAutoOutput], tCheck.fluid())) > 0) updateInventory();}
	}
	
	public void doOutputEnergy() {
		ITileEntityEnergy.Util.emitEnergyToSide(mEnergyTypeEmitted, FACING_TO_SIDE[mFacing][mEnergyOutput], mOutputEnergy, 1, this);
	}
	
	public void onProcessStarted () {/**/}
	public void onProcessFinished() {/**/}
	
	@Override public void onFacingChange(byte aPreviousFacing) {updateAccessibleSlots();}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientBasicMachine(aPlayer.inventory, this, mRecipes, aGUIID, mGUITexture);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonBasicMachine(aPlayer.inventory, this, mRecipes, aGUIID);}
	
	@Override public byte getVisualData() {return (byte)((mActive?1:0)|(mRunning?2:0));}
	
	@Override public void setVisualData(byte aData) {mRunning=((aData&2)!=0); mActive=((aData&1)!=0);}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return mActive ? SIDES_THIS[mFacing] : SIDES_HORIZONTAL;}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(mTexturesMaterial[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mActive||worldObj==null?mTexturesActive:mRunning?mTexturesRunning:mTexturesInactive)[FACING_ROTATIONS[mFacing][aSide]])) : null;}
	
	@Override public boolean canSave(int aSlot) {return !IL.Display_Fluid.equal(slot(aSlot), T, T);}
	@Override public boolean hasWork() {return mMaxProgress > 0 || mChargeRequirement > 0;}
	@Override public long getProgressValue(byte aSide) {return mSuccessful ? getProgressMax(aSide) : mMinEnergy < 1 ? mProgress    : mProgress    / mMinEnergy + (mProgress    % mMinEnergy == 0 ? 0 : 1) ;}
	@Override public long getProgressMax  (byte aSide) {return Math.max(1,                           mMinEnergy < 1 ? mMaxProgress : mMaxProgress / mMinEnergy + (mMaxProgress % mMinEnergy == 0 ? 0 : 1));}
	@Override public long getGibblValue   (byte aSide) {long rGibbl = 0; for (int i = 0; i < mTanksInput.length; i++) rGibbl += mTanksInput[i].amount  (); return rGibbl;}
	@Override public long getGibblMax     (byte aSide) {long rGibbl = 0; for (int i = 0; i < mTanksInput.length; i++) rGibbl += mTanksInput[i].capacity(); return rGibbl;}
	
	@Override public boolean getStateRunningPossible() {return mCouldUseRecipe || mActive || mMaxProgress > 0 || mChargeRequirement > 0 || (mIgnited > 0 && !mDisabledItemOutput && mOutputBlocked != 0);}
	@Override public boolean getStateRunningPassively() {return mRunning;}
	@Override public boolean getStateRunningActively() {return mActive;}
	@Override public boolean getStateRunningSuccessfully() {return mSuccessful;}
	@Override public boolean setStateOnOff(boolean aOnOff) {if (mStopped == aOnOff) {mStopped = !aOnOff; updateAdjacentToggleableEnergySources();} return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.machine.basic";}
	
	@Override
	public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity) {
		if (FACE_CONNECTED[FACING_ROTATIONS[mFacing][aSide]][mItemInputs|mItemOutputs]) updateInventory();
	}
}
