/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.data;

import gregapi.oredict.OreDictManager;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

/**
 * @author Gregorius Techneticies
 */
public enum OD {
	  craftingWorkBench
	, craftingFurnace
	, craftingChest
	, craftingAnvil
	, craftingBook
	, craftingFeather
	, craftingFur
	, craftingHardenedClay
	, craftingLeather
	, craftingPistonIngot
	, craftingPistonGlue
	, craftingPiston
	, craftingRedstoneTorch
	, craftingQuartz
	, craftingFirestarter
	, craftingWireCopper
	, craftingWireGold
	, craftingWireIron
	, craftingWireTin
	, craftingDuctTape
	, crateGtEmpty
	, lever
	, button
	, buttonWood
	, buttonStone
	, listAllpropolis
	, listAllmushroom
	, listAllwheats
	, container1000water
	, container250water
	, container1000lava
	, container250lava
	, container1000milk
	, container250milk
	, container1000soymilk
	, container250soymilk
	, container1000honey
	, container250honey
	, container1000juice
	, container250juice
	, container1000seedoil
	, container250seedoil
	, container1000creosote
	, container250creosote
	, container1000rubbertreesap
	, container250rubbertreesap
	, container1000spruceresin
	, container250spruceresin
	, container1000rainbowsap
	, container250rainbowsap
	, container1000maplesap
	, container250maplesap
	, container1000latex
	, container250latex
	, container1000lubricant
	, container250lubricant
	, container1000glue
	, container250glue
	, container1000poison
	, container500poison
	, container250poison
	, container1000blood
	, container250blood
	, container1000slimegreen
	, container1000slimeblue
	, container1000slimepink
	, container1000slime
	, container250slimegreen
	, container250slimeblue
	, container250slimepink
	, container250slime
	, container1000tar
	, container250tar
	, aquaRegia
	, sulfuricAcid
	, enderChest
	, sandstone
	, soulsand
	, glowstone
	, dirt
	, sand
	, gravel
	, pestleAndMortar
	, materialPressedwax
	, materialWaxcomb
	, materialHoneycomb
	, materialWax
	, plankSkyroot
	, plankWeedwood
	, plankAnyWood
	, plankWood
	, stickAnyWood
	, stickWood
	, pressurePlateWood
	, pressurePlateStone
	, pressurePlateIron
	, pressurePlateGold
	, paperMap
	, paperEmpty
	, paperWritten
	, paperWritable
	, paperEnchanted
	, bookEmptySmall
	, bookEmptyBig
	, bookEmpty
	, bookWrittenSmall
	, bookWrittenBig
	, bookWritten
	, bookWritableSmall
	, bookWritableBig
	, bookWritable
	, bookEnchanted
	, stairWood
	, slabWood
	, beamWood
	, logWood
	, logRubber
	, woodLog
	, woodRubber
	, flower
	, flowerWither
	, bamboo
	, record
	, dropHoney
	, dropHoneydew
	, dropRoyalJelly
	, beeComb
	, beeCombCrossbred
	, hardenedClay
	, fiberCarbon
	, slimeball
	, slimeballBlue
	, slimeballPink
	, slimeballRice
	, slimeballSwet
	, slimeballBorax
	, slimeballAnimal
	, itemGrassTall
	, itemGrass
	, itemGrassDry
	, itemGrassMoldy
	, itemGrassRotten
	, cropGrain
	, baleGrass
	, baleGrassDry
	, baleGrassMoldy
	, baleGrassRotten
	, itemKey
	, itemMud
	, itemMudBrick
	, itemTar
	, itemMoss
	, itemCoral
	, itemSlag
	, itemGlue
	, itemBlood
	, itemPoison
	, itemBarkDry
	, itemLubricant
	, itemLubricantEarly
	, itemResin
	, itemRubber
	, itemInsulator
	, itemSalt
	, itemRock
	, itemFlint
	, itemPearl
	, itemString
	, itemCompass
	, itemRedstone
	, itemQuicksilver
	, itemCompressedCarbon
	, itemClay
	, itemEgg, itemEggBig
	, itemFeather, chocobofeather
	, itemLeather
	, itemLeatherTreated
	, itemLeatherHardened
	, itemLeatherImpregnated
	, itemSkin
	, itemFur
	, itemPelt
	, itemIvory
	, itemTusk
	, itemHoof
	, itemHorn
	, itemAntler
	, itemMulch
	, itemCompost
	, itemFertilizer
	, itemPlantRemains
	, itemGhastTear
	, itemSilicon
	, itemCertusQuartz
	, itemNetherQuartz
	, fruitBait
	, grainBait
	, veggieBait
	, fishtrapBait
	, obsidian
	, cryingObsidian
	, glass
	, glassColorless
	, paneGlass
	, paneGlassColorless
	, blockGlass
	, blockGlassColorless
	, blockMud
	, blockMudBricks
	, blockClay
	, blockCandle
	, blockTorch
	, blockSoulTorch
	, blockShadowTorch
	, blockFoxfireTorch
	, listAllmeatsubstitute
	, listAllmeatcooked
	, listAllmeatraw
	;
	
	public final List<ItemStack> mItems;
	
	OD() {
		OreDictManager.INSTANCE.addKnownName(name());
		mItems = OreDictionary.getOres(name());
	}
	
	public boolean is(ItemStack aStack) {
		return ST.valid(aStack) && is_(aStack);
	}
	public boolean is_(ItemStack aStack) {
		for (ItemStack tOreStack : mItems) if (ST.equal(tOreStack, aStack, T)) return T;
		return F;
	}
}
