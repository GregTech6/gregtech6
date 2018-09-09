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