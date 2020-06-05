/**
 * Copyright (c) 2020 GregTech-6 Team
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

import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.data.CS.ItemsGT;
import gregapi.data.IL;
import gregapi.data.OD;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapScannerVisuals extends RecipeMap {
	public RecipeMapScannerVisuals(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower);
		mMappings.put(new ItemStackContainer(Items.flint_and_steel, 1, W), ST.make(Blocks.fire, 1, 0));
		mMappings.put(new ItemStackContainer(Items.reeds, 1, W), ST.make(Blocks.reeds, 1, 0));
		mMappings.put(new ItemStackContainer(Items.snowball, 1, W), ST.make(Blocks.snow, 1, 0));
		mMappings.put(new ItemStackContainer(Items.wheat_seeds, 1, W), ST.make(Blocks.wheat, 1, 0));
		mMappings.put(new ItemStackContainer(Items.wheat, 1, W), ST.make(Blocks.wheat, 1, 7));
		mMappings.put(new ItemStackContainer(Items.carrot, 1, W), ST.make(Blocks.carrots, 1, 7));
		mMappings.put(new ItemStackContainer(Items.poisonous_potato, 1, W), ST.make(Blocks.potatoes, 1, 0));
		mMappings.put(new ItemStackContainer(Items.potato, 1, W), ST.make(Blocks.potatoes, 1, 7));
		mMappings.put(new ItemStackContainer(Items.melon_seeds, 1, W), ST.make(Blocks.melon_stem, 1, 0));
		mMappings.put(new ItemStackContainer(Items.melon, 1, W), ST.make(Blocks.melon_stem, 1, 7));
		mMappings.put(new ItemStackContainer(Items.pumpkin_seeds, 1, W), ST.make(Blocks.pumpkin_stem, 1, 7));
		mMappings.put(new ItemStackContainer(Items.dye, 1, 3), ST.make(Blocks.cocoa, 1, 8));
		mMappings.put(new ItemStackContainer(Items.string, 1, W), ST.make(Blocks.web, 1, 0));
		mMappings.put(new ItemStackContainer(Items.nether_wart, 1, W), ST.make(Blocks.nether_wart, 1, 3));
		mMappings.put(new ItemStackContainer(Items.comparator, 1, W), ST.make(Blocks.powered_comparator, 1, 0));
		mMappings.put(new ItemStackContainer(Items.repeater, 1, W), ST.make(Blocks.powered_repeater, 1, 0));
		mMappings.put(new ItemStackContainer(Items.bed, 1, W), ST.make(Blocks.bed, 1, 0));
		mMappings.put(new ItemStackContainer(Items.iron_door, 1, W), ST.make(Blocks.iron_door, 1, 0));
		mMappings.put(new ItemStackContainer(Items.wooden_door, 1, W), ST.make(Blocks.wooden_door, 1, 0));
		mMappings.put(new ItemStackContainer(Items.ender_pearl, 1, W), ST.make(Blocks.portal, 1, 0));
		mMappings.put(new ItemStackContainer(Items.ender_eye, 1, W), ST.make(Blocks.end_portal_frame, 1, 0));
		mMappings.put(new ItemStackContainer(Items.water_bucket, 1, W), ST.make(Blocks.water, 1, 0));
		mMappings.put(new ItemStackContainer(Items.lava_bucket, 1, W), ST.make(Blocks.lava, 1, 0));
		mMappings.put(new ItemStackContainer(Items.cauldron, 1, W), ST.make(Blocks.cauldron, 1, 0));
		mMappings.put(new ItemStackContainer(Items.brewing_stand, 1, W), ST.make(Blocks.brewing_stand, 1, 0));
		mMappings.put(new ItemStackContainer(Items.flower_pot, 1, W), ST.make(Blocks.flower_pot, 1, 0));
	}
	
	public final ItemStackMap<ItemStackContainer, ItemStack> mMappings = new ItemStackMap<>();
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length < 2 || aInputs[0] == null || aInputs[1] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			ItemStack tUSB = null, tScanned = null;
			for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
				if (ST.invalid(tUSB) && OM.is_(OD_USB_STICKS[1], aInput)) tUSB = aInput; else tScanned = aInput;
				if (ST.valid(tUSB) && ST.valid(tScanned)) {
					assert tScanned != null; // just to make eclipse shut the fuck up
					if (IL.Paper_Blueprint_Used.equal(tScanned, F, T)) {
						ItemStack[] tCraftingRecipe = UT.NBT.getBlueprintCrafting(tScanned);
						if (tCraftingRecipe != ZL_IS) {
							rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 64, 16, 0);
							if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
							rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.setBlueprintCrafting(UT.NBT.make(), tCraftingRecipe));
							rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
							return rRecipe;
						}
						return rRecipe;
					}
					if (OM.is_("gt:canvas", tScanned)) {
						if (tScanned.hasTagCompound() && tScanned.getTagCompound().hasKey(NBT_CANVAS_BLOCK)) {
							rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 64, 16, 0);
							if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
							NBTTagCompound tNBT = UT.NBT.make();
							tNBT.setInteger(NBT_CANVAS_BLOCK, tScanned.getTagCompound().getInteger(NBT_CANVAS_BLOCK));
							tNBT.setInteger(NBT_CANVAS_META, tScanned.getTagCompound().getInteger(NBT_CANVAS_META));
							rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, tNBT);
							rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
							return rRecipe;
						}
						return rRecipe;
					}
					if ((tScanned.getItem() == ItemsGT.BOOKS || OD.bookWritten.is_(tScanned) || IL.Paper_Printed_Pages.equal(tScanned, F, T) || IL.Paper_Printed_Pages_Many.equal(tScanned, F, T)) && UT.Code.stringValid(UT.NBT.getBookTitle(tScanned))) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 512, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, tScanned.getTagCompound().copy());
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					if (tScanned.getItem() == Items.filled_map) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 64, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.setMapID(UT.NBT.make(), ST.meta_(tScanned)));
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					if (IL.GC_Schematic_1.equal(tScanned, T, T)) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 1024, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeShort("gc_schematics_1", ST.meta_(tScanned)));
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					if (IL.GC_Schematic_2.equal(tScanned, T, T)) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 1024, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeShort("gc_schematics_2", ST.meta_(tScanned)));
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					if (IL.GC_Schematic_3.equal(tScanned, T, T)) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 1024, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeShort("gc_schematics_3", ST.meta_(tScanned)));
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					if (IL.IE_Blueprint_Projectiles_Common.equal(tScanned, T, T)) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 1024, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeShort("ie_blueprint", ST.meta_(tScanned)));
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					
					ItemStack tMapping = mMappings.get(tScanned.getItem(), ST.meta_(tScanned));
					if (tMapping == null) tMapping = mMappings.get(tScanned.getItem(), W);
					Block tBlock = ST.block_(tMapping==null?tScanned:tMapping);
					if (tBlock != null && tBlock != NB) {
						rRecipe = new Recipe(F, F, F, ST.array(ST.amount(1, tScanned), ST.amount(1, tUSB)), ST.array(ST.amount(1, tUSB), ST.amount(1, tScanned)), null, null, null, null, 512, 16, 0);
						if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
						NBTTagCompound tNBT = UT.NBT.make();
						tNBT.setInteger(NBT_CANVAS_BLOCK, Block.getIdFromBlock(tBlock));
						tNBT.setInteger(NBT_CANVAS_META, ST.meta_(tScanned));
						rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, tNBT);
						rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)1);
						return rRecipe;
					}
					return rRecipe;
				}
			}
		}
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return T;}
}
