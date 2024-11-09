/**
 * Copyright (c) 2024 GregTech-6 Team
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

package gregapi.recipes.maps;

import gregapi.data.FL;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.computer.ITileEntityUSBPort;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapReplicator extends RecipeMap {
	public RecipeMapReplicator(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, F, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
		mMaxFluidInputSize = 2000;
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		
		if (rRecipe != null || aInputs == null || aFluids == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		
		ItemStack tUSB = null;
		NBTTagCompound tData = null;
		for (ItemStack aInput : aInputs) if (aInput != null) {
			if (tData == null) {
				if (OM.is_(OD_USB_STICKS[3], aInput)) {
					if (!aInput.hasTagCompound()) return rRecipe;
					tUSB = aInput;
					tData = tUSB.getTagCompound().getCompoundTag(NBT_USB_DATA);
				} else if (OM.is_(OD_USB_CABLES[3], aInput)) {
					if (aTileEntity == null) return rRecipe;
					tUSB = aInput;
					for (byte tSide : ALL_SIDES_VALID_ONLY[tUSB.hasTagCompound() && tUSB.getTagCompound().hasKey(NBT_USB_DIRECTION) ? tUSB.getTagCompound().getByte(NBT_USB_DIRECTION) : SIDE_ANY]) {
						DelegatorTileEntity<TileEntity> tDelegator = aTileEntity.getAdjacentTileEntity(tSide);
						if (tDelegator.mTileEntity instanceof ITileEntityUSBPort) {
							tData = ((ITileEntityUSBPort)tDelegator.mTileEntity).getUSBData(tDelegator.mSideOfTileEntity, 3);
							if (tData != null) if (tData.hasNoTags()) tData = null; else break;
						}
					}
				}
			}
		}
		if (tData == null || tData.hasNoTags()) return rRecipe;
		if (tUSB != null && tData.hasKey(NBT_REPLICATOR_DATA)) {
			short tID = tData.getShort(NBT_REPLICATOR_DATA);
			if (tID > 0 && UT.Code.exists(tID, OreDictMaterial.MATERIAL_ARRAY)) return getReplicatorRecipe(OreDictMaterial.MATERIAL_ARRAY[tID], tUSB);
		}
		return rRecipe;
	}
	
	public static Recipe getReplicatorRecipe(OreDictMaterial aMaterial, ItemStack aUSB) {
		if (aMaterial.contains(TD.Processing.UUM) && !aMaterial.contains(TD.Atomic.ANTIMATTER)) {
			FluidStack[] tMatters = FL.array(aMaterial.mNeutrons<=0?NF:FL.MatterNeutral.make(aMaterial.mNeutrons), aMaterial.mProtons<=0?NF:FL.MatterCharged.make(aMaterial.mProtons));
			long tPower = (aMaterial.mProtons+aMaterial.mNeutrons) * 256;
			if (aMaterial.mMeltingPoint <= DEF_ENV_TEMP) {
				FluidStack tFluidOutput = aMaterial.fluid(DEF_ENV_TEMP, U, F);
				if (FL.nonzero(tFluidOutput)) return new Recipe(F, F, T, ST.array(ST.amount(0, aUSB)), ZL_IS, null, null, tMatters, FL.array(tFluidOutput), tPower, 1, 0).setNoBuffering();
			}
			ItemStack tOutput = NI;
			if (aMaterial.mPriorityPrefix != null) tOutput = aMaterial.mPriorityPrefix.mat(aMaterial, 1);
			if (ST.invalid(tOutput)) {
				if (ST.invalid(tOutput = OP.gem      .mat(aMaterial, 1)) && ST.invalid(tOutput = OP.plateGem.mat(aMaterial, 1))
				&&  ST.invalid(tOutput = OP.ingot    .mat(aMaterial, 1)) && ST.invalid(tOutput = OP.plate   .mat(aMaterial, 1))
				&&  ST.invalid(tOutput = OP.nugget   .mat(aMaterial, 9)) && ST.invalid(tOutput = OP.chunkGt .mat(aMaterial, 4))
				&&  ST.invalid(tOutput = OP.dust     .mat(aMaterial, 1)) && ST.invalid(tOutput = OP.dustTiny.mat(aMaterial, 9))
				&&  ST.invalid(tOutput = OP.dustSmall.mat(aMaterial, 4)) && ST.invalid(tOutput = OP.stick   .mat(aMaterial, 2))) {
					FluidStack tFluidOutput = aMaterial.liquid(U, F);
					if (FL.zero(tFluidOutput)) tFluidOutput = aMaterial.gas(U, F);
					if (FL.zero(tFluidOutput)) tFluidOutput = aMaterial.plasma(U, F);
					if (FL.zero(tFluidOutput)) return null;
					return new Recipe(F, F, T, ST.array(ST.amount(0, aUSB)), ZL_IS, null, null, tMatters, FL.array(tFluidOutput), tPower, 1, 0).setNoBuffering();
				}
			}
			return new Recipe(F, F, T, ST.array(ST.amount(0, aUSB)), ST.array(tOutput), null, null, tMatters, null, tPower, 1, 0).setNoBuffering();
		}
		return null;
	}
	
	@Override public boolean containsInput(FluidStack aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return super.containsInput(aFluid, aTileEntity, aSpecialSlot) || FL.is(aFluid, "neutralmatter", "chargedmatter");}
	@Override public boolean containsInput(Fluid aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return super.containsInput(aFluid, aTileEntity, aSpecialSlot) || FL.is(aFluid, "neutralmatter", "chargedmatter");}
}
