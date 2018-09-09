package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.CS.OreDictToolNames;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class Loader_Recipes_Aether implements Runnable {
	@Override
	public void run() {if (MD.AETHER.mLoaded) {OUT.println("GT_Mod: Doing Aether Recipes.");
		ST.item(MD.AETHER, "moaEgg").setMaxStackSize(64);
		
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHER, "skyrootSignItem"			, 1, W), IL.AETHER_Skyroot_Planks.get(2), OM.dust(MT.Skyroot, OP.stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHER, "skyrootFenceGate"		, 1, W), IL.AETHER_Skyroot_Planks.get(2), OM.dust(MT.Skyroot, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(MD.AETHER, "skyrootBedItem"			, 1, W), IL.AETHER_Skyroot_Planks.get(3), ST.make(Blocks.wool, 3, 0));
		RM.sawing(16,  48, F, 100, ST.make(MD.AETHER, "skyrootTrapDoor"			, 1, W), IL.AETHER_Skyroot_Planks.get(3));
		RM.sawing(16,  64, F, 100, ST.make(MD.AETHER, "skyrootCraftingTable"	, 1, W), IL.AETHER_Skyroot_Planks.get(4));
		RM.sawing(16,  96, F, 100, ST.make(MD.AETHER, "skyrootDoorItem"			, 1, W), IL.AETHER_Skyroot_Planks.get(6));
		RM.sawing(16,  96, F, 100, ST.make(MD.AETHER, "skyrootBookshelf"		, 1, W), IL.AETHER_Skyroot_Planks.get(6), ST.make(Items.book, 3, 0));
		RM.sawing(16, 128, F, 100, ST.make(MD.AETHER, "skyrootChest"			, 1, W), IL.AETHER_Skyroot_Planks.get(8));
		
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootSignItem"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(2), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootFenceGate"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootTrapDoor"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(3), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootBedItem"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(4), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootCraftingTable"	, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(6), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootDoorItem"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(6), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootBookshelf"		, 1, W)});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(8), CR.DEF_NAC_NCC, new Object[] {OreDictToolNames.saw, ST.make(MD.AETHER, "skyrootChest"			, 1, W)});
		
		RM.unbox(IL.AETHER_Skyroot_Planks.get(3), ST.make(MD.AETHER, "skyrootBookshelf", 1, W), ST.make(Items.book, 3, 0));
		
		RM.biomass(ST.make(MD.AETHER, "purpleFlower", 16, W), 64);
		RM.biomass(ST.make(MD.AETHER, "whiteRose", 16, W), 64);
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "purpleFlower"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "whiteRose"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White], 2), OM.dust(MT.White));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "purpleFlower"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.AETHER, "whiteRose"		, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White], 2), OM.dust(MT.White));
		RM.ic2_extractor(ST.make(MD.AETHER, "purpleFlower"	, 1, W), ST.make(Items.dye, 3, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.AETHER, "whiteRose"		, 1, W), OM.dust(MT.White, U*3));
	}}
}