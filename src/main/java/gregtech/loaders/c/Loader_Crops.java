package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.GT_BaseCrop;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Crops implements Runnable {
	@Override
	public void run() {
		if (!MD.IC2.mLoaded) return;
		OUT.println("GT_Mod: Register Crops to IC2.");
		try {
			//																																																																																				   TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
			new GT_BaseCrop("Indigo"				, "Eloraam"					, OP.plantGtBlossom.mat(MT.Indigo, 1)				, null																																									, OP.plantGtBlossom.mat(MT.Indigo, 4)				, 2, 4,     0, 1, 4, 1, 1, 0, 4, 0, new String[] {"Flower"		, "Color", "Ingredient"});
			new GT_BaseCrop("Flax"					, "Eloraam"					, ST.make(Items.string, 1, 0)						, null																																									, null												, 2, 4,     0, 1, 4, 1, 1, 2, 0, 1, new String[] {"Vine"		, "Silk", "Addictive"});
			new GT_BaseCrop("Oil Berries"			, "Spacetoad"				, OP.plantGtBerry.mat(MT.Oil, 1)					, null																																									, null												, 9, 4, 27000, 1, 4, 6, 1, 2, 1,12, new String[] {"Reed"		, "Fire", "Dark", "Rotten", "Coal", "Oil", "Berry"});
			new GT_BaseCrop("Bobsyeruncle Ranks"	, "GenerikB"				, OP.plantGtBerry.mat(MT.Emerald, 1)				, null																																									, null												,11, 4, 16500, 1, 4, 4, 0, 8, 2, 9, new String[] {"Vine"		, "Shiny", "Emerald", "Berylium", "Crystal"});
			new GT_BaseCrop("Diareed"				, "Direwolf20"				, OP.dustTiny.mat(MT.Diamond ,1)					, null																																									, null												,12, 4, 36000, 1, 4, 5, 0,10, 2,10, new String[] {"Reed"		, "Fire", "Shiny", "Coal", "Diamond", "Crystal"});
			new GT_BaseCrop("Withereed"				, "CovertJaguar"			, OP.dust.mat(MT.Coal, 1)							, new ItemStack[] {ST.make(Items.coal, 1, 0), ST.make(Items.coal, 1, 0)}																								, null												, 8, 4, 24000, 1, 4, 2, 0, 4, 1, 6, new String[] {"Reed"		, "Fire", "Undead", "Coal", "Rotten", "Wither"});
			new GT_BaseCrop("Blaze Reed"			, "Mr. Brain"				, ST.make(Items.blaze_powder, 1, 0)					, new ItemStack[] {ST.make(Items.blaze_rod, 1, 0)}																														, null												,10, 4,  9000, 1, 4, 4, 0, 8, 2,10, new String[] {"Reed"		, "Fire", "Blaze", "Sulfur"});
			new GT_BaseCrop("Eggplant"				, "Link"					, ST.make(Items.egg, 1, 0)							, new ItemStack[] {ST.make(Items.chicken, 1, 0), ST.make(Items.feather, 1, 0), ST.make(Items.feather, 1, 0), ST.make(Items.feather, 1, 0)}								, null												, 6, 3,   900, 2, 3, 0, 4, 1, 0, 0, new String[] {"Flower"		, "Food", "Chicken", "Egg", "Feather", "Addictive"});
			new GT_BaseCrop("Corium"				, "Gregorius Techneticies"	, ST.make(Items.leather, 1, 0)						, null																																									, null												, 6, 4,     0, 1, 4, 0, 2, 3, 1, 0, new String[] {"Vine"		, "Cow", "Silk"});
			new GT_BaseCrop("Corpse Plant"			, "Mr. Kenny"				, ST.make(Items.rotten_flesh, 1, 0)					, new ItemStack[] {IL.Dye_Bonemeal.get(1), IL.Dye_Bonemeal.get(1), ST.make(Items.bone, 1, 0)}																			, null												, 5, 4,     0, 1, 4, 0, 2, 1, 0, 3, new String[] {"Vine"		, "Food", "Toxic", "Undead", "Rotten"});
			new GT_BaseCrop("Creeper Weed"			, "General Spaz"			, OP.dust.mat(MT.Gunpowder, 1)						, null																																									, null												, 7, 4,     0, 1, 4, 3, 0, 5, 1, 3, new String[] {"Vine"		, "Creeper", "Explosive", "Fire", "Sulfur", "Saltpeter", "Coal"});
			new GT_BaseCrop("Ender Bloom"			, "RichardG"				, OP.dust.mat(MT.EnderPearl, 1)						, new ItemStack[] {ST.make(Items.ender_pearl, 1, 0), ST.make(Items.ender_pearl, 1, 0), ST.make(Items.ender_eye, 1, 0)}													, null												,10, 4, 15000, 1, 4, 5, 0, 2, 1, 6, new String[] {"Flower"		, "Shiny", "Ender"});
			new GT_BaseCrop("Meat Rose"				, "VintageBeef"				, ST.make(Items.dye, 1, 9)							, new ItemStack[] {ST.make(Items.beef, 1, 0), ST.make(Items.porkchop, 1, 0), ST.make(Items.chicken, 1, 0), ST.make(Items.fish, 1, 0)}									, null												, 7, 4,  1500, 1, 4, 0, 4, 1, 3, 0, new String[] {"Flower"		, "Food", "Cow", "Fish", "Chicken", "Pig"});
			new GT_BaseCrop("Milkwart"				, "Mr. Brain"				, OP.plantGtWart.mat(MT.Milk, 1)					, null																																									, OP.plantGtWart.mat(MT.Milk, 1)					, 6, 3,   900, 1, 3, 0, 3, 0, 1, 0, new String[] {"Wart"		, "Food", "Milk", "Cow", "Ingredient"});
			new GT_BaseCrop("Glowshrooms"			, "Speiger"					, OP.plantGtWart.mat(MT.Glowstone, 1)				, null																																									, OP.plantGtWart.mat(MT.Glowstone, 1)				, 6, 3,  2400, 1, 3, 5, 0, 0, 2, 0, new String[] {"Wart"		, "Shiny", "Ingredient"});
			new GT_BaseCrop("Slime Plant"			, "Neowulf"					, ST.make(Items.slime_ball, 1, 0)					, null																																									, null												, 6, 4,     0, 3, 4, 3, 0, 0, 0, 2, new String[] {"Bush"		, "Slime", "Bouncy", "Sticky"});
			new GT_BaseCrop("Spidernip"				, "Ms. Muffet"				, ST.make(Items.string, 1, 0)						, new ItemStack[] {ST.make(Items.spider_eye, 1, 0), ST.make(Blocks.web, 1, 0)}																							, null												, 4, 4,   600, 1, 4, 2, 1, 4, 1, 3, new String[] {"Flower"		, "Toxic", "Silk", "Spider", "Ingredient", "Addictive"});
			new GT_BaseCrop("Tear Stalks"			, "Neowulf"					, ST.make(Items.ghast_tear, 1, 0)					, null																																									, null												, 8, 4,  2400, 1, 4, 1, 2, 0, 0, 0, new String[] {"Reed"		, "Healing", "Nether", "Ingredient", "Ghast"});
			new GT_BaseCrop("Tine"					, "Gregorius Techneticies"	, OP.plantGtTwig.mat(MT.Sn, 1)						, null																																									, null												, 5, 3, 15000, 2, 3, 2, 0, 3, 0, 0, new String[] {"Bush"		, "Shiny", "Metal", "Pine", "Tin"});
			new GT_BaseCrop("Coppon"				, "Mr. Brain"				, OP.plantGtFiber.mat(MT.Cu, 1)						, null																																									, null												, 6, 3, 18000, 2, 3, 2, 0, 1, 1, 1, new String[] {"Bush"		, "Shiny", "Metal", "Cotton", "Copper"});
			new GT_BaseCrop("Argentia"				, "Eloraam"					, OP.plantGtBlossom.mat(MT.Ag, 1)					, null																																									, null												, 7, 4, 21000, 3, 4, 2, 0, 1, 0, 0, new String[] {"Reed"		, "Shiny", "Metal", "Silver"});
			new GT_BaseCrop("Plumbilia"				, "KingLemming"				, OP.plantGtBlossom.mat(MT.Pb, 1)					, null																																									, null												, 6, 4, 18000, 3, 4, 2, 0, 3, 1, 1, new String[] {"Reed"		, "Heavy", "Metal", "Lead"});
			new GT_BaseCrop("Steeleaf Ranks"		, "Benimatic"				, OP.nugget.mat(MT.Steeleaf, 1)						, new ItemStack[] {OP.ingot.mat(MT.Steeleaf, 1)}																														, null												,10, 4, 30000, 1, 4, 3, 0, 7, 2, 8, new String[] {"Vine"		, "Metal", "Iron"});
			new GT_BaseCrop("Liveroots"				, "Benimatic"				, OP.dust.mat(MT.LiveRoot, 1)						, new ItemStack[] {IL.TF_LiveRoot.get(1)}																																, null												, 8, 4,     0, 1, 4, 2, 0, 5, 2, 6, new String[] {"Vine"		, "Wood"});
			//																																																																																				   TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
			new GT_BaseCrop("Rye"					, "Binnie"					, IL.Crop_Rye.get(1)								, null																																									, IL.Crop_Rye.get(1)								, 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"		, "Food", "Grain"});
			new GT_BaseCrop("Barley"				, "Glitchfiend"				, IL.Crop_Barley.get(1)								, null																																									, IL.Crop_Barley.get(1)								, 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"		, "Food", "Grain"});
			new GT_BaseCrop("Oats"					, "Pam"						, IL.Crop_Oats.get(1)								, null																																									, IL.Crop_Oats.get(1)								, 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"		, "Food", "Grain"});
			new GT_BaseCrop("Rice"					, "Ellpeck"					, IL.Crop_Rice.get(1)								, null																																									, IL.Crop_Rice.get(1)								, 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"		, "Food", "Grain"});
			//																																																																																				   TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
			new GT_BaseCrop("Tea"					, "Pam"						, OP.plantGtBlossom.mat(MT.Tea, 1)					, null																																									, OP.plantGtBlossom.mat(MT.Tea, 4)					, 2, 4,     0, 1, 4, 2, 4, 2, 0, 1, new String[] {"Leaves"		, "Food", "Addictive"});
			new GT_BaseCrop("Mint"					, "Gregorius Techneticies"	, OP.plantGtBlossom.mat(MT.Mint, 1)					, null																																									, OP.plantGtBlossom.mat(MT.Mint, 4)					, 2, 4,     0, 1, 4, 2, 3, 5, 0, 1, new String[] {"Leaves"		, "Food", "Fresh"});
			new GT_BaseCrop("Lemon Plant"			, "Cave Johnson"			, IL.Food_Lemon.get(1)								, null																																									, IL.Food_Lemon.get(4)								, 3, 4,     0, 3, 4, 3, 4, 3, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Explosive"});
			new GT_BaseCrop("Green Apple Tree"		, "The Guy"					, IL.Food_Apple_Green.get(1)						, null																																									, IL.Food_Apple_Green.get(4)						, 3, 4,     0, 3, 4, 3, 3, 5, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Apple"});
			new GT_BaseCrop("Yellow Apple Tree"		, "The Guy"					, IL.Food_Apple_Yellow.get(1)						, null																																									, IL.Food_Apple_Yellow.get(4)						, 3, 4,     0, 3, 4, 2, 4, 4, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Apple"});
			new GT_BaseCrop("Red Apple Tree"		, "The Guy"					, IL.Food_Apple_Red.get(1)							, null																																									, IL.Food_Apple_Red.get(4)							, 3, 4,     0, 3, 4, 2, 4, 3, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Apple"});
			new GT_BaseCrop("Dark Red Apple Tree"	, "The Guy"					, IL.Food_Apple_DarkRed.get(1)						, null																																									, IL.Food_Apple_DarkRed.get(4)						, 3, 4,     0, 3, 4, 2, 5, 2, 2, 3, new String[] {"Bush"		, "Food", "Fruit", "Apple"});
			new GT_BaseCrop("Tomato Plant"			, "Kirby"					, IL.Food_Tomato.get(1)								, null																																									, IL.Food_Tomato.get(4)								, 2, 4,     0, 3, 4, 2, 4, 2, 1, 3, new String[] {"Vine"		, "Food", "Fruit", "Vegetable"});
			new GT_BaseCrop("Red Grapes"			, "Pam"						, IL.Food_Grapes_Red.get(1)							, null																																									, IL.Food_Grapes_Red.get(4)							, 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"		, "Food", "Fruit"});
			new GT_BaseCrop("White Grapes"			, "Binnie"					, IL.Food_Grapes_White.get(1)						, null																																									, IL.Food_Grapes_White.get(4)						, 3, 4,     0, 3, 4, 1, 4, 0, 0, 3, new String[] {"Vine"		, "Food", "Fruit"});
			new GT_BaseCrop("Green Grapes"			, "Gwafu"					, IL.Food_Grapes_Green.get(1)						, null																																									, IL.Food_Grapes_Green.get(4)						, 3, 4,     0, 3, 4, 1, 4, 0, 1, 3, new String[] {"Vine"		, "Food", "Fruit"});
			new GT_BaseCrop("Purple Grapes"			, "Gregorius Techneticies"	, IL.Food_Grapes_Purple.get(1)						, new ItemStack[] {IL.Food_Grapes_Purple.getWithName(1, "Member Berries")}																								, IL.Food_Grapes_Purple.get(4)						, 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"		, "Food", "Fruit", "Member?"});
			new GT_BaseCrop("Blueberry Bush"		, "Pam"						, IL.Food_Blueberry.get(1)							, null																																									, IL.Food_Blueberry.get(4)							, 3, 5,     0, 4, 5, 1, 4, 1, 4, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry", "Color"});
			new GT_BaseCrop("Gooseberry Bush"		, "Pam"						, IL.Food_Gooseberry.get(1)							, null																																									, IL.Food_Gooseberry.get(4)							, 3, 5,     0, 4, 5, 1, 4, 1, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Candleberry Bush"		, "Pam"						, IL.Food_Candleberry.get(1)						, null																																									, IL.Food_Candleberry.get(4)						, 3, 5,     0, 4, 5, 3, 3, 1, 0, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry", "Wax"});
			new GT_BaseCrop("Cranberries"			, "Pam"						, IL.Food_Cranberry.get(1)							, null																																									, IL.Food_Cranberry.get(4)							, 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Black Currants"		, "Gregorius Techneticies"	, IL.Food_Currants_Black.get(1)						, null																																									, IL.Food_Currants_Black.get(4)						, 3, 5,     0, 4, 5, 1, 4, 1, 1, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("White Currants"		, "Gregorius Techneticies"	, IL.Food_Currants_White.get(1)						, null																																									, IL.Food_Currants_White.get(4)						, 3, 5,     0, 4, 5, 1, 4, 1, 0, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Red Currants"			, "Gregorius Techneticies"	, IL.Food_Currants_Red.get(1)						, null																																									, IL.Food_Currants_Red.get(4)						, 3, 5,     0, 4, 5, 1, 4, 1, 2, 2, new String[] {"Bush"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Blackberries"			, "Pam"						, IL.Food_Blackberry.get(1)							, null																																									, IL.Food_Blackberry.get(4)							, 3, 4,     0, 3, 4, 1, 4, 4, 2, 3, new String[] {"Vine"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Raspberries"			, "Pam"						, IL.Food_Raspberry.get(1)							, null																																									, IL.Food_Raspberry.get(4)							, 3, 4,     0, 3, 4, 1, 4, 1, 2, 3, new String[] {"Vine"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Strawberries"			, "Pam"						, IL.Food_Strawberry.get(1)							, null																																									, IL.Food_Strawberry.get(4)							, 3, 4,     0, 1, 4, 1, 4, 0, 2, 4, new String[] {"Bush"		, "Food", "Fruit", "Berry"});
			new GT_BaseCrop("Onion"					, "Onion San"				, IL.Food_Onion.get(1)								, null																																									, IL.Food_Onion.get(4)								, 2, 4,     0, 1, 4, 3, 3, 3, 0, 1, new String[] {"Vegetable"	, "Food", "Ingredient"});
			new GT_BaseCrop("Cucumber"				, "Pam"						, IL.Food_Cucumber.get(1)							, null																																									, IL.Food_Cucumber.get(4)							, 2, 4,     0, 1, 4, 1, 5, 0, 0, 2, new String[] {"Vegetable"	, "Food", "Ingredient"});
			new GT_BaseCrop("Peanuts"				, "Snoopy"					, IL.Food_Peanut.get(1)								, null																																									, IL.Food_Peanut.get(4)								, 3, 4,     0, 1, 4, 1, 4, 0, 2, 4, new String[] {"Bush"		, "Food", "Nut"});
			new GT_BaseCrop("Ananas"				, "Spongebob"				, IL.Food_Ananas.get(1)								, null																																									, IL.Food_Ananas.get(4)								, 4, 3,     0, 1, 3, 3, 3, 5, 1, 1, new String[] {"Bush"		, "Food", "Fruit", "Pine", "Apple"});
			//																																																																																				   TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
			new GT_BaseCrop("Desert Nova"			, "Mithion"					, IL.ARS_DesertNova.get(1, IL.DesertNova.get(1))	, null																																									, IL.ARS_DesertNova.get(4, IL.DesertNova.get(4))	, 6, 4,     0, 1, 4, 5, 1, 7, 4,10, new String[] {"Cactus"		, "Magic", "Fire", "Explosive"});
			new GT_BaseCrop("Cerublossom"			, "Mithion"					, IL.ARS_Cerublossom.get(1, IL.Cerublossom.get(1))	, null																																									, IL.ARS_Cerublossom.get(4, IL.Cerublossom.get(4))	, 6, 4,     0, 1, 4, 1, 1, 2, 4,10, new String[] {"Flower"		, "Magic", "Shiny"});
			new GT_BaseCrop("Shimmerleaf"			, "Azanor"					, OP.nugget.mat(MT.Hg, 1)							, null																																									, IL.TC_Shimmerleaf.get(4)							,11, 4,     0, 1, 4, 5, 1, 4, 1, 8, new String[] {"Flower"		, "Magic", "Shiny", "Metal", "Mercury"});
			new GT_BaseCrop("Cinderpearl"			, "Azanor"					, ST.make(Items.blaze_powder, 1, 0)					, null																																									, IL.TC_Cinderpearl.get(4)							, 8, 4,     0, 1, 4, 3, 1, 8, 2, 8, new String[] {"Flower"		, "Magic", "Fire", "Blaze", "Sulfur", "Ingredient"});
		} catch(Throwable e) {
			ERR.println("GT_Mod: Failed to register Crops to IC2.");
			e.printStackTrace(ERR);
		}
	}
}