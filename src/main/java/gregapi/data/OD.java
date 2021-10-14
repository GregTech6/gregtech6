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

package gregapi.data;

import gregapi.oredict.OreDictManager;
import net.minecraft.item.ItemStack;

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
	, container250poison
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
	, bookEmpty
	, bookWritten
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
	, beeComb
	, beeCombCrossbred
	, hardenedClay
	, fiberCarbon
	, slimeball
	, slimeballPink
	, slimeballRice
	, slimeballSwet
	, slimeballBorax
	, itemGrassTall
	, itemGrass
	, itemGrassDry
	, itemGrassMoldy
	, itemGrassRotten
	, baleGrass
	, baleGrassDry
	, baleGrassMoldy
	, baleGrassRotten
	, itemKey
	, itemMud
	, itemTar
	, itemMoss
	, itemSlag
	, itemGlue
	, itemPoison
	, itemBarkDry
	, itemLubricant
	, itemLubricantEarly
	, itemResin
	, itemRubber
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
	, itemEgg
	, itemFeather
	, itemLeather
	, itemLeatherTreated
	, itemLeatherHardened
	, itemLeatherImpregnated
	, itemSkin
	, itemFur
	, itemPelt
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
	, paneGlass
	, paneGlassColorless
	, blockGlass
	, blockGlassColorless
	, blockClay
	, blockCandle
	, blockTorch
	, blockSoulTorch
	, blockShadowTorch
	, blockFoxfireTorch
	, listAllmeatsubstitute
	;
	
	OD() {
		OreDictManager.INSTANCE.addKnownName(name());
	}
	
	public boolean is (ItemStack aStack) {return OreDictManager.isItemStackInstanceOf (aStack, name());}
	public boolean is_(ItemStack aStack) {return OreDictManager.isItemStackInstanceOf_(aStack, name());}
}
