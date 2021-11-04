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

package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.ANY;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.item.IItemColorableRGB;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.wooddict.PlankEntry;
import gregapi.wooddict.WoodDictionary;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapBath extends RecipeMap {
	public static FL[] OILS = {FL.Oil_Seed, FL.Oil_Lin, FL.Oil_Hemp, FL.Oil_Nut, FL.Oil_Olive, FL.Oil_Sunflower, FL.Oil_Creosote};
	
	public RecipeMapBath(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length < 1 || aFluids.length < 1 || aFluids[0] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
			PlankEntry aEntry = WoodDictionary.PLANKS_ANY.get(aInput);
			if (aEntry != null && (ANY.WoodUntreated.mToThis.contains(aEntry.mMaterialPlank) || MD.MC.owns(aInput))) {
				if (ST.valid(aEntry.mPlank)) {
					if (IL.MaCu_Polished_Planks.exists()) {
						addRecipe1(F, 0, 144, aEntry.mPlank, FL.Oil_Fish .make(1000), NF, IL.MaCu_Polished_Planks.get(1));
						addRecipe1(F, 0, 144, aEntry.mPlank, FL.Oil_Whale.make( 500), NF, IL.MaCu_Polished_Planks.get(1));
					}
					
					if (!IL.Treated_Planks.equal(aEntry.mPlank, F, T) && !IL.IE_Treated_Planks.equal(aEntry.mPlank, F, T)) {
						ItemStack tTreated = IL.IE_Treated_Planks.get(1, IL.Treated_Planks.get(1));
						for (FL tFluid : OILS)
						addRecipe1(F, 0, 144, aEntry.mPlank, tFluid.make(100), NF, tTreated);
					}
					if (IL.ERE_White_Planks.exists() && !IL.ERE_White_Planks.equal(aEntry.mPlank, F, T)) {
						ItemStack tTreated = IL.ERE_White_Planks.get(1);
						for (FluidStack tFluid : DYE_FLUIDS[DYE_INDEX_White])
						addRecipe1(F, 0, 144, aEntry.mPlank, tFluid, NF, tTreated);
					}
				}
				
				if (ST.valid(aEntry.mStair)) {
					if (IL.IE_Treated_Stairs.exists() && !IL.IE_Treated_Stairs.equal(aEntry.mStair, F, T)) {
						ItemStack tTreated = IL.IE_Treated_Stairs.get(1);
						for (FL tFluid : OILS)
						addRecipe1(F, 0, 102, aEntry.mStair, tFluid.make( 75), NF, tTreated);
					}
					if (IL.ERE_White_Stairs.exists() && !IL.ERE_White_Stairs.equal(aEntry.mStair, F, T)) {
						ItemStack tTreated = IL.ERE_White_Stairs.get(1);
						for (FluidStack tFluid : DYE_FLUIDS[DYE_INDEX_White])
						addRecipe1(F, 0, 102, aEntry.mStair, FL.mul(tFluid, 3, 4, T), NF, tTreated);
					}
				}
				
				if (ST.valid(aEntry.mSlab)) {
					if (!IL.Treated_Planks_Slab.equal(aEntry.mSlab, F, T) && !IL.IE_Treated_Slab.equal(aEntry.mSlab, F, T)) {
						ItemStack tTreated = IL.IE_Treated_Slab.get(1, IL.Treated_Planks_Slab.get(1));
						for (FL tFluid : OILS)
						addRecipe1(F, 0,  72, aEntry.mSlab, tFluid.make( 50), NF, tTreated);
					}
					if (IL.ERE_White_Slab.exists() && !IL.ERE_White_Slab.equal(aEntry.mSlab, F, T)) {
						ItemStack tTreated = IL.ERE_White_Slab.get(1);
						for (FluidStack tFluid : DYE_FLUIDS[DYE_INDEX_White])
						addRecipe1(F, 0,  72, aEntry.mSlab, FL.mul(tFluid, 1, 2, T), NF, tTreated);
					}
				}
				
				rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
			}
			
			if (MD.ATUM.mLoaded) {
				Item tItem = ST.item(MD.ATUM, "item.loot");
				if (aInput.getItem() == tItem) {
					short tMeta = ST.meta_(aInput);
					if ((tMeta & 31) == 1) return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(ST.make(tItem, 1, (tMeta & ~31) + 2 + 2 * RNGSUS.nextInt(6))), null, null, FL.array(FL.water(aFluids[0])?FL.amount(aFluids[0], 100):FL.Water.make(100)), ZL_FS, 512, 0, 0).setNeedEmptyOut();
					return null;
				}
			}
			if (aInput.getItem() instanceof ItemArmor) for (byte tColor = 0; tColor < 16; tColor++) for (FluidStack aDye : DYE_FLUIDS[tColor]) if (FL.equal(aDye, aFluids[0])) {
				ItemStack tOutput = CR.getany(aTileEntity==null?DW:aTileEntity.getWorld(), ST.array(aInput, NI, NI, NI, NI, NI, NI, NI, ST.make(Items.dye, 1, tColor)));
				if (ST.invalid(tOutput)) return null;
				return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(aDye), ZL_FS, 512, 0, 0);
			}
			if (aInput.getItem() instanceof IItemColorableRGB) {
				if (((IItemColorableRGB)aInput.getItem()).canDecolorItem(aInput)) if (FL.equal(MT.Cl.fluid(U, T), aFluids[0])) {
					ItemStack tOutput = ST.amount(1, aInput);
					if (!((IItemColorableRGB)tOutput.getItem()).decolorItem(tOutput) || ST.invalid(tOutput)) return null;
					return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(MT.Cl.fluid(U/8, T)), ZL_FS, 512, 0, 0);
				}
				if (((IItemColorableRGB)aInput.getItem()).canRecolorItem(aInput)) for (byte tColor = 0; tColor < 16; tColor++) for (FluidStack aDye : DYE_FLUIDS[tColor]) if (FL.equal(aDye, aFluids[0])) {
					ItemStack tOutput = ST.amount(1, aInput);
					if (!((IItemColorableRGB)tOutput.getItem()).recolorItem(tOutput, DYES_INT[tColor]) || ST.invalid(tOutput)) return null;
					return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.mul(aDye, 1, 8, T)), ZL_FS, 512, 0, 0);
				}
			}
			if (ST.food(aInput) > 0 && FL.getFluid(aInput, T) == null) {
				ItemStack tOutput = ST.amount(1, aInput);
				NBTTagCompound tNBT = UT.NBT.getNBT(tOutput);
				tOutput.setTagCompound(tNBT);
				if (!tNBT.hasKey(NBT_EFFECTS)) {
					if (FL.Med_Heal                .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.regeneration  .id, "time",  120, "lvl", 4, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Med_Laxative            .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.hunger        .id, "time",  300, "lvl",10, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Harm_1           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.harm          .id, "time",    1, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Harm_2           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.harm          .id, "time",    1, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Heal_1           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.heal          .id, "time",    1, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Heal_2           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.heal          .id, "time",    1, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Jump_1           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.jump          .id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Jump_2           .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.jump          .id, "time", 1800, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Speed_1          .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.moveSpeed     .id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Speed_2          .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.moveSpeed     .id, "time", 1800, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Speed_1L         .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.moveSpeed     .id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Strength_1       .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.damageBoost   .id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Strength_2       .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.damageBoost   .id, "time", 1800, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Strength_1L      .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.damageBoost   .id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Regen_1          .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.regeneration  .id, "time",  900, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Regen_2          .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.regeneration  .id, "time",  450, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Regen_1L         .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.regeneration  .id, "time", 2400, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Poison_1         .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.poison        .id, "time",  900, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Poison_2         .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.poison        .id, "time",  450, "lvl", 1, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Poison_1L        .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.poison        .id, "time", 2400, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_FireResistance_1 .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.fireResistance.id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_FireResistance_1L.is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.fireResistance.id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_NightVision_1    .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.nightVision   .id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_NightVision_1L   .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.nightVision   .id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Weakness_1       .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.weakness      .id, "time", 1800, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Weakness_1L      .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.weakness      .id, "time", 4800, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Slowness_1       .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.moveSlowdown  .id, "time", 1800, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Slowness_1L      .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.moveSlowdown  .id, "time", 4800, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_WaterBreathing_1 .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.waterBreathing.id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_WaterBreathing_1L.is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.waterBreathing.id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Invisibility_1   .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.invisibility  .id, "time", 3600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
					if (FL.Potion_Invisibility_1L  .is(aFluids[0])) {tNBT.setTag(NBT_EFFECTS, UT.NBT.make("id", Potion.invisibility  .id, "time", 9600, "lvl", 0, "chance", 90)); return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, FL.array(FL.amount(aFluids[0], 250)), ZL_FS, 512, 0, 0);}
				}
			}
		}
		return rRecipe;
	}
	
	@Override
	public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
		PlankEntry aEntry = WoodDictionary.PLANKS_ANY.get(aStack);
		return (aEntry != null && ANY.WoodUntreated.mToThis.contains(aEntry.mMaterialPlank)) || (aStack != null && (aStack.getItem() instanceof ItemArmor || (aStack.getItem() instanceof IItemColorableRGB && (((IItemColorableRGB)aStack.getItem()).canRecolorItem(aStack) || ((IItemColorableRGB)aStack.getItem()).canDecolorItem(aStack))))) || (ST.food(aStack) > 0 && FL.getFluid(aStack, T) == null) || super.containsInput(aStack, aTileEntity, aSpecialSlot);
	}
	@Override public boolean containsInput(FluidStack aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return aFluid != null && aFluid.getFluid() != null && (super.containsInput(aFluid, aTileEntity, aSpecialSlot) || FluidsGT.BATH.contains(aFluid.getFluid().getName()));}
	@Override public boolean containsInput(Fluid aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return aFluid != null && (super.containsInput(aFluid, aTileEntity, aSpecialSlot) || FluidsGT.BATH.contains(aFluid.getName()));}
}
