/**
 * Copyright (c) 2018 Gregorius Techneticies
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
	, listAllpropolis
	, listAllmushroom
	, container1000water
	, container250water
	, container1000lava
	, container250lava
	, container1000milk
	, container250milk
	, container1000honey
	, container250honey
	, container1000creosote
	, container250creosote
	, enderChest
	, pestleAndMortar
	, materialPressedwax
	, plankSkyroot
	, plankWeedwood
	, plankAnyWood
	, plankWood
	, stairWood
	, slabWood
	, beamWood
	, logWood
	, logRubber
	, flower
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
	, itemGrassTall
	, itemGrass
	, itemGrassDry
	, itemGrassMoldy
	, itemGrassRotten
	, itemMud
	, itemTar
	, itemMoss
	, itemSlag
	, itemGlue
	, itemResin
	, itemRubber
	, itemFlint
	, itemPearl
	, itemString
	, itemCompass
	, itemRedstone
	, itemClay
	, itemFeather
	, itemLeather
	, itemLeatherTreated
	, itemLeatherHardened
	, itemLeatherImpregnated
	, itemSkin
	, itemFur
	, itemFertilizer
	, itemPlantRemains
	, itemGhastTear
	, fruitBait
	, grainBait
	, veggieBait
	, fishtrapBait
	;
	
	OD() {
		OreDictManager.INSTANCE.addKnownName(name());
	}
}
