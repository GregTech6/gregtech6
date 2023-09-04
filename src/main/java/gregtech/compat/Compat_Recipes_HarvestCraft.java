/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregtech.compat;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;

import static gregapi.data.CS.*;

public class Compat_Recipes_HarvestCraft extends CompatMods {
	public Compat_Recipes_HarvestCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing HarvestCraft Recipes.");
		final ItemStack tYogurt = ST.make(MD.HaC, "plainyogurtItem", 1);
		
		CR.delate(MD.HaC, "paneertikkamasalaItem", "mortarandpestleItem", "flourItem", "cornmealItem", "plainyogurtItem", "coconutcreamItem", "heavycreamItem", "mayoItem", "ketchupItem", "vinegarItem", "oliveoilItem", "saladdressingItem", "peanutbutterItem", "nutellaItem", "appleciderItem", "beefjerkyItem", "zombiejerkyItem", "peppermintItem", "doughItem", "marzipanItem", "chocolatemilkshakeItem", "strawberrymilkshakeItem", "bananamilkshakeItem", "gooseberrymilkshakeItem", "saltedsunflowerseedsItem", "cherryjuiceItem", "bananajuiceItem", "strawberryjuiceItem", "persimmonjuiceItem", "lemonaideItem", "applejuiceItem", "grapejuiceItem", "melonjuiceItem", "kiwijuiceItem", "raspberryjuiceItem", "blackberryjuiceItem", "blueberryjuiceItem", "cranberryjuiceItem", "gooseberryjuiceItem", "carrotjuiceItem", "grapefruitjuiceItem", "pearjuiceItem", "apricotjuiceItem", "plumjuiceItem", "peachjuiceItem", "limejuiceItem", "orangejuiceItem", "pomegranatejuiceItem", "mangojuiceItem", "figjuiceItem", "papayajuiceItem", "cactusfruitjuiceItem", "starfruitjuiceItem", "coconutmilkItem", "grapefruitsmoothieItem", "pearsmoothieItem", "apricotsmoothieItem", "plumsmoothieItem", "peachsmoothieItem", "limesmoothieItem", "orangesmoothieItem", "cranberrysmoothieItem", "cherrysmoothieItem", "bananasmoothieItem", "lemonsmoothieItem", "applesmoothieItem", "grapesmoothieItem", "melonsmoothieItem", "kiwismoothieItem", "raspberrysmoothieItem", "blackberrysmoothieItem", "blueberrysmoothieItem", "gooseberrysmoothieItem", "strawberrysmoothieItem", "pomegranatesmoothieItem", "persimmonsmoothieItem", "figsmoothieItem", "starfruitsmoothieItem", "mangosmoothieItem", "papayasmoothieItem", "coconutsmoothieItem", "chocolateyogurtItem", "vanillayogurtItem", "coconutyogurtItem", "papayayogurtItem", "figyogurtItem", "mangoyogurtItem", "starfruityogurtItem", "pomegranateyogurtItem", "grapefruityogurtItem", "persimmonyogurtItem", "pearyogurtItem", "apricotyogurtItem", "plumyogurtItem", "peachyogurtItem", "limeyogurtItem", "orangeyogurtItem", "cranberryyogurtItem", "pineappleyogurtItem", "cherryyogurtItem", "bananayogurtItem", "lemonyogurtItem", "appleyogurtItem", "grapeyogurtItem", "grapeyogurtItem", "melonyogurtItem", "kiwiyogurtItem", "raspberryyogurtItem", "blackberryyogurtItem", "blueberryyogurtItem", "gooseberryyogurtItem", "strawberryyogurtItem", "pumpkinyogurtItem", "icecreamItem", "strawberryicecreamItem", "cherryicecreamItem", "spumoniicecreamItem", "neapolitanicecreamItem", "vanillaicecreamItem", "chocolateicecreamItem", "pistachioicecreamItem", "mochaicecreamItem", "caramelicecreamItem", "mintchocolatechipicemcreamItem", "extremechiliItem", "batterItem", "eggnogItem", "caramelItem", "ricecakeItem", "garammasalaItem", "chocolatestrawberryItem", "chocolatecherryItem", "chocolatebaconItem", "maplecandiedbaconItem", "epicbaconItem", "chocolatedonutItem", "cinnamonsugardonutItem", "powdereddonutItem", "frosteddonutItem", "donutItem", "jellydonutItem");
		CR.remove(ST.make(MD.HaC, "potItem", 1), ST.make(Items.water_bucket, 1, 0));
		
		for (int i = 0; i < 16; i++) {
		CR.delate(MD.HaC, "pamcandleDeco"+(i+1));
		CR.shaped(ST.make(MD.HaC, "pamcandleDeco"+(i+1), 1, 0), CR.DEF_NCC, "S", "W", "D", 'W', OD.materialPressedwax, 'S', OD.itemString, 'D', DYE_OREDICTS[15-i]);
		}
		
		ArrayList<ItemStack> tListFoodRice = OreDictionary.getOres("foodRice"), tListCropRice = OreDictionary.getOres("cropRice");
		ArrayList<ItemStack> tListFoodOats = OreDictionary.getOres("dustOat"), tListCropOats = OreDictionary.getOres("cropOats");
		ArrayList<ItemStack> tListFoodBarley = OreDictionary.getOres("dustBarley"), tListCropBarley = OreDictionary.getOres("cropBarley");
		ArrayList<ItemStack> tListFoodVanilla = OreDictionary.getOres("foodVanilla"), tListDustVanilla = OreDictionary.getOres("dustVanilla");
		ArrayList<ItemStack> tListFoodOliveOil = OreDictionary.getOres("foodOliveoil"), tListFoodCookingOil = OreDictionary.getOres("listAllcookingoil");
		for (IRecipe tRecipe : CR.list()) if (tRecipe.getClass() == ShapelessOreRecipe.class) {
			ItemStack tOutput = tRecipe.getRecipeOutput();
			if (ST.valid(tOutput) && tOutput.getItem() instanceof ItemFood) {
				ArrayList<Object> tInputs = ((ShapelessOreRecipe)tRecipe).getInput();
				int tSize = tInputs.size();
				if (tSize > 2) for (int i = 0; i < tSize; i++) {
					if (tListCropRice == tInputs.get(i)) tInputs.set(i, tListFoodRice); else
					if (tListCropOats == tInputs.get(i)) tInputs.set(i, tListFoodOats); else
					if (tListCropBarley == tInputs.get(i)) tInputs.set(i, tListFoodBarley); else
					if (tListFoodVanilla == tInputs.get(i)) tInputs.set(i, tListDustVanilla); else
					if (tListFoodOliveOil == tInputs.get(i)) tInputs.set(i, tListFoodCookingOil);
				}
			}
		}
		
		FoodsGT.put(ST.make(MD.HaC, "fruitsaladItem"    , 1), 0, 0, 0, 20, 0);
		FoodsGT.put(ST.make(MD.HaC, "citrussaladItem"   , 1), 0, 0, 0, 10, 0);
		FoodsGT.put(ST.make(MD.HaC, "springsaladItem"   , 1), 0, 0, 0, 10, 0);
		
		RM.add_smelting(ST.make(MD.HaC, "turkeyrawItem"     , 1), ST.make(MD.HaC, "turkeycookedItem"    , 1), F, T, F);
		RM.add_smelting(ST.make(MD.HaC, "rabbitrawItem"     , 1), ST.make(MD.HaC, "rabbitcookedItem"    , 1), F, T, F);
		RM.add_smelting(ST.make(MD.HaC, "venisonrawItem"    , 1), ST.make(MD.HaC, "venisoncookedItem"   , 1), F, T, F);
		
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Loom     .addRecipe2(T, 64,  128, ST.make(MD.HaC, "hardenedleatherItem", 6), (tMat==MT.Enori?OP.plateGem:OP.plate)   .mat(tMat           , 8), ST.make(Items.iron_horse_armor, 1, 0));
		RM.Loom     .addRecipe2(T, 64,  128, ST.make(MD.HaC, "hardenedleatherItem", 6), OP.plate                                .mat(MT.Au          , 8), ST.make(Items.golden_horse_armor, 1, 0));
		RM.Loom     .addRecipe2(T, 64,  128, ST.make(MD.HaC, "hardenedleatherItem", 6), OP.plateGem                             .mat(MT.Diamond     , 8), ST.make(Items.diamond_horse_armor, 1, 0));
		for (OreDictMaterial tMat : ANY.Steel.mToThis)
		RM.Loom     .addRecipeX(T, 64,  128, ST.array(ST.make(MD.HaC, "hardenedleatherItem", 6), OP.ring.mat(tMat, 2), OP.stick.mat(tMat, 3)), ST.make(Items.saddle, 1, 0));
		RM.Loom     .addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.HaC, "hardenedleatherItem", 5), ST.make(MD.HaC, "hardenedleatherhelmItem", 1));
		RM.Loom     .addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.HaC, "hardenedleatherItem", 8), ST.make(MD.HaC, "hardenedleatherchestItem", 1));
		RM.Loom     .addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.HaC, "hardenedleatherItem", 7), ST.make(MD.HaC, "hardenedleatherleggingsItem", 1));
		RM.Loom     .addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.HaC, "hardenedleatherItem", 4), ST.make(MD.HaC, "hardenedleatherbootsItem", 1));
		
		RM.Shredder .addRecipe1(T, 16,   16, ST.make(Blocks.double_plant    , 1, 0), ST.make(MD.HaC, "sunflowerseedsItem", 1));
		
		CR.shaped(IL.Food_Toast_Sliced.get(4), CR.DEF_NCC, "kX", 'X', ST.make(MD.HaC, "toastItem", 1));
		RM.Slicer.addRecipe2(T, 16, 16, ST.make(MD.HaC, "toastItem", 1), IL.Shape_Slicer_Flat.get(0), IL.Food_Toast_Sliced.get(4));
		
		RM.Press    .addRecipe2(T, 16,   32, OM.dust(MT.Chili, U4), OP.ingot.mat(MT.Chocolate, 1), ST.make(MD.HaC, "chilichocolateItem", 1));
		RM.Press    .addRecipe2(T, 16,   32, OM.dust(MT.Chili, U3), OP.ingot.mat(MT.Chocolate, 1), ST.make(MD.HaC, "chilichocolateItem", 1));
		RM.Press    .addRecipe2(T, 16,  128, OM.dust(MT.Chili, U ), OP.ingot.mat(MT.Chocolate, 4), ST.make(MD.HaC, "chilichocolateItem", 4));
		
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), IL.Remains_Fruit  .get(3), ST.make(MD.HaC, "fruitbaitItem"   , 4));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), IL.Remains_Veggie .get(3), ST.make(MD.HaC, "veggiebaitItem"  , 4));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), IL.Remains_Nut    .get(3), ST.make(MD.HaC, "grainbaitItem"   , 4));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), OM.dust(MT.FishRaw,  U*3), ST.make(MD.HaC, "fishtrapbaitItem", 4));
		
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.beef        , 1, W), OM.dust(MT.NaCl, U4), ST.make(MD.HaC, "beefjerkyItem"  , 1));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.rotten_flesh, 1, W), OM.dust(MT.NaCl, U4), ST.make(MD.HaC, "zombiejerkyItem", 1));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.beef        , 1, W), OM.dust(MT.NaCl, U3), ST.make(MD.HaC, "beefjerkyItem"  , 1));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.rotten_flesh, 1, W), OM.dust(MT.NaCl, U3), ST.make(MD.HaC, "zombiejerkyItem", 1));
		RM.Mixer    .addRecipe2(T, 16,   64, ST.make(Items.beef        , 4, W), OM.dust(MT.NaCl, U ), ST.make(MD.HaC, "beefjerkyItem"  , 4));
		RM.Mixer    .addRecipe2(T, 16,   64, ST.make(Items.rotten_flesh, 4, W), OM.dust(MT.NaCl, U ), ST.make(MD.HaC, "zombiejerkyItem", 4));
		
		RM.Mixer    .addRecipe1(T,  0,   16, OM.dust(MT.Mint, U9), MT.Sugar.liquid(  U4, T), NF, ST.make(MD.HaC, "peppermintItem", 1));
		RM.Mixer    .addRecipe1(T,  0,   32, OM.dust(MT.Mint, U4), MT.Sugar.liquid(  U2, T), NF, ST.make(MD.HaC, "peppermintItem", 2));
		RM.Mixer    .addRecipe1(T,  0,  128, OM.dust(MT.Mint, U ), MT.Sugar.liquid(9*U4, T), NF, ST.make(MD.HaC, "peppermintItem", 9));
		
		for (FluidStack tWater : FL.waters(1000))
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(MD.HaC, "flourItem", 1), tWater, NF, IL.Food_Dough.get(1));
		
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond, U4), FL.RoyalJelly.make(10), NF, ST.make(MD.HaC, "marzipanItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond    ), FL.RoyalJelly.make(40), NF, ST.make(MD.HaC, "marzipanItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond, U4), FL.Honey    .make(100), NF, ST.make(MD.HaC, "marzipanItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond    ), FL.Honey    .make(400), NF, ST.make(MD.HaC, "marzipanItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond, U4), FL.HoneyGrC .make(100), NF, ST.make(MD.HaC, "marzipanItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond    ), FL.HoneyGrC .make(400), NF, ST.make(MD.HaC, "marzipanItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond, U4), FL.HoneyBoP .make(100), NF, ST.make(MD.HaC, "marzipanItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Almond    ), FL.HoneyBoP .make(400), NF, ST.make(MD.HaC, "marzipanItem", 4));
		
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(MD.HaC, "chiliItem", 1), FL.Sauce_Chili.make(100), NF, ST.make(MD.HaC, "extremechiliItem", 1));
		RM.Mixer    .addRecipe2(T, 16,   16, ST.make(MD.HaC, "chiliItem", 1), OM.dust(MT.Chili), ST.make(MD.HaC, "extremechiliItem", 1));
		
		RM.Mixer    .addRecipe1(T, 16,   16, IL.HaC_Royal_Jelly.get(1), FL.Honeydew.make(40), FL.make("potion.ambrosia", 80), ZL_IS);
		
		if (IL.GrC_Starter_Culture.exists()) {
		RM.Mixer    .addRecipe1(T, 16,   16, IL.GrC_Starter_Culture.get(1), FL.Milk   .make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(T, 16,   16, IL.GrC_Starter_Culture.get(1), FL.MilkGrC.make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(T, 16,   16, IL.GrC_Starter_Culture.get(1), FL.MilkSoy.make( 250), NF, tYogurt);
		} else {
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.leather, 1, W), FL.Milk   .make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.leather, 1, W), FL.MilkGrC.make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.leather, 1, W), FL.MilkSoy.make( 250), NF, tYogurt);
		}
		RM.Mixer    .addRecipe1(F, 16,   16, ST.amount(0, tYogurt), FL.Milk   .make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(F, 16,   16, ST.amount(0, tYogurt), FL.MilkGrC.make( 250), NF, tYogurt);
		RM.Mixer    .addRecipe1(F, 16,   16, ST.amount(0, tYogurt), FL.MilkSoy.make( 250), NF, tYogurt);
		
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.snowball, 1, W)  , FL.make("chocolatemilk"            ,  250) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Snow, U4)           , FL.make("chocolatemilk"            ,  250) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   64, ST.make(Blocks.snow, 1, W)     , FL.make("chocolatemilk"            , 1000) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   64, OM.dust(MT.Snow)               , FL.make("chocolatemilk"            , 1000) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.snowball, 1, W)  , FL.make("potion.darkchocolatemilk" ,  250) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   16, OM.dust(MT.Snow, U4)           , FL.make("potion.darkchocolatemilk" ,  250) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 1));
		RM.Mixer    .addRecipe1(T, 16,   64, ST.make(Blocks.snow, 1, W)     , FL.make("potion.darkchocolatemilk" , 1000) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 4));
		RM.Mixer    .addRecipe1(T, 16,   64, OM.dust(MT.Snow)               , FL.make("potion.darkchocolatemilk" , 1000) , NF, ST.make(MD.HaC, "chocolatemilkshakeItem", 4));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.Milk   .make( 250), FL.Smoothie_Strawberry.make(200)), ZL_FS, ST.make(MD.HaC, "strawberrymilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkGrC.make( 250), FL.Smoothie_Strawberry.make(200)), ZL_FS, ST.make(MD.HaC, "strawberrymilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkSoy.make( 250), FL.Smoothie_Strawberry.make(200)), ZL_FS, ST.make(MD.HaC, "strawberrymilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.Milk   .make( 250), FL.Smoothie_Banana.make(200)), ZL_FS, ST.make(MD.HaC, "bananamilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkGrC.make( 250), FL.Smoothie_Banana.make(200)), ZL_FS, ST.make(MD.HaC, "bananamilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkSoy.make( 250), FL.Smoothie_Banana.make(200)), ZL_FS, ST.make(MD.HaC, "bananamilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.Milk   .make( 250), FL.Smoothie_Gooseberry.make(200)), ZL_FS, ST.make(MD.HaC, "gooseberrymilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkGrC.make( 250), FL.Smoothie_Gooseberry.make(200)), ZL_FS, ST.make(MD.HaC, "gooseberrymilkshakeItem", 1));
		RM.Mixer    .addRecipe0(T, 16,   16, FL.array(FL.MilkSoy.make( 250), FL.Smoothie_Gooseberry.make(200)), ZL_FS, ST.make(MD.HaC, "gooseberrymilkshakeItem", 1));
		
		RM.Mixer    .addRecipe2(T, 16,   16, OM.dust(MT.NaCl, U4)   , ST.make(MD.HaC, "sunflowerseedsItem", 1), ST.make(MD.HaC, "saltedsunflowerseedsItem", 1));
		
		RM.Mixer    .addRecipe2(T, 16,   16, tYogurt, OM.dust(MT.Vanilla                 ), ST.make(MD.HaC, "vanillayogurtItem"     , 1));
		RM.Mixer    .addRecipe2(T, 16,   16, tYogurt, OM.dust(MT.Chocolate               ), ST.make(MD.HaC, "chocolateyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Grapefruit   .make(100), NF, ST.make(MD.HaC, "grapefruityogurtItem"  , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Pear         .make(100), NF, ST.make(MD.HaC, "pearyogurtItem"        , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Apricot      .make(100), NF, ST.make(MD.HaC, "apricotyogurtItem"     , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Plum         .make(100), NF, ST.make(MD.HaC, "plumyogurtItem"        , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Peach        .make(100), NF, ST.make(MD.HaC, "peachyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Lime         .make(100), NF, ST.make(MD.HaC, "limeyogurtItem"        , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Orange       .make(100), NF, ST.make(MD.HaC, "orangeyogurtItem"      , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Persimmon    .make(100), NF, ST.make(MD.HaC, "persimmonyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Cranberry    .make(100), NF, ST.make(MD.HaC, "cranberryyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Ananas       .make(100), NF, ST.make(MD.HaC, "pineappleyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Cherry       .make(100), NF, ST.make(MD.HaC, "cherryyogurtItem"      , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Banana       .make(100), NF, ST.make(MD.HaC, "bananayogurtItem"      , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Lemon        .make(100), NF, ST.make(MD.HaC, "lemonyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Apple        .make(100), NF, ST.make(MD.HaC, "appleyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_AppleGrC     .make(100), NF, ST.make(MD.HaC, "appleyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Grape_Red    .make(100), NF, ST.make(MD.HaC, "grapeyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Grape_White  .make(100), NF, ST.make(MD.HaC, "grapeyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Grape_Green  .make(100), NF, ST.make(MD.HaC, "grapeyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Grape_Purple .make(100), NF, ST.make(MD.HaC, "grapeyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Melon        .make(100), NF, ST.make(MD.HaC, "melonyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Kiwi         .make(100), NF, ST.make(MD.HaC, "kiwiyogurtItem"        , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Raspberry    .make(100), NF, ST.make(MD.HaC, "raspberryyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Blackberry   .make(100), NF, ST.make(MD.HaC, "blackberryyogurtItem"  , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Blueberry    .make(100), NF, ST.make(MD.HaC, "blueberryyogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Gooseberry   .make(100), NF, ST.make(MD.HaC, "gooseberryyogurtItem"  , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Strawberry   .make(100), NF, ST.make(MD.HaC, "strawberryyogurtItem"  , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Pumpkin      .make(100), NF, ST.make(MD.HaC, "pumpkinyogurtItem"     , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Pomegranate  .make(100), NF, ST.make(MD.HaC, "pomegranateyogurtItem" , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Fig          .make(100), NF, ST.make(MD.HaC, "figyogurtItem"         , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Starfruit    .make(100), NF, ST.make(MD.HaC, "starfruityogurtItem"   , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Mango        .make(100), NF, ST.make(MD.HaC, "mangoyogurtItem"       , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Papaya       .make(100), NF, ST.make(MD.HaC, "papayayogurtItem"      , 1));
		RM.Mixer    .addRecipe1(T, 16,   16, tYogurt, FL.Juice_Coconut      .make(100), NF, ST.make(MD.HaC, "coconutyogurtItem"     , 1));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("cropCoconut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, ST.make(MD.HaC, "toastedcoconutItem", 1), F, T, F);
		}});
		addListener("cropStrawberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, MT.Chocolate.liquid(U4, T), NF, ST.make(MD.HaC, "chocolatestrawberryItem", 1));
		}});
		addListener("cropCherry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, MT.Chocolate.liquid(U4, T), NF, ST.make(MD.HaC, "chocolatecherryItem", 1));
		}});
		addListener("cropCorn", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.Milk   .make( 250), NF, ST.make(MD.HaC, "cornflakesItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.MilkGrC.make( 250), NF, ST.make(MD.HaC, "cornflakesItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.MilkSoy.make( 250), NF, ST.make(MD.HaC, "cornflakesItem", 1));
		}});
		addListener("cropBeet", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.PepperBlack, U4), aEvent.mStack), FL.Milk                .make( 250), NF, ST.make(MD.HaC, "beetsoupItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.PepperBlack, U4), aEvent.mStack), FL.MilkGrC             .make( 250), NF, ST.make(MD.HaC, "beetsoupItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.PepperBlack, U4), aEvent.mStack), FL.MilkSoy             .make( 250), NF, ST.make(MD.HaC, "beetsoupItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   64, ST.array(IL.Food_Onion_Sliced.get(12), OM.dust(MT.PepperBlack), ST.amount(3, aEvent.mStack)), FL.Milk      .make(1000), NF, ST.make(MD.HaC, "beetsoupItem", 4));
			RM.Mixer    .addRecipeX(T, 16,   64, ST.array(IL.Food_Onion_Sliced.get(12), OM.dust(MT.PepperBlack), ST.amount(3, aEvent.mStack)), FL.MilkGrC   .make(1000), NF, ST.make(MD.HaC, "beetsoupItem", 4));
			RM.Mixer    .addRecipeX(T, 16,   64, ST.array(IL.Food_Onion_Sliced.get(12), OM.dust(MT.PepperBlack), ST.amount(3, aEvent.mStack)), FL.MilkSoy   .make(1000), NF, ST.make(MD.HaC, "beetsoupItem", 4));
		}});
		addListener("cropSpiceleaf", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, OM.dust(MT.PepperBlack), OM.dust(MT.Nutmeg), OM.dust(MT.Cinnamon)), ST.make(MD.HaC, "garammasalaItem", 4));
		}});
		addListener("cropRadish", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (String tFluid : FluidsGT.VINEGAR) if (FL.exists(tFluid)) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), IL.Food_Cucumber_Sliced.get( 3), aEvent.mStack), FL.make(tFluid, 125), NF, ST.make(MD.HaC, "summerradishsaladItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), IL.Food_Pickle_Sliced  .get( 3), aEvent.mStack), FL.make(tFluid,  50), NF, ST.make(MD.HaC, "summerradishsaladItem", 1));
			}
		}});
		addListener("cropCabbage", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Carrot_Sliced.get( 3), OM.dust(MT.PepperBlack, U4), aEvent.mStack), FL.Mayo.make(125), NF, ST.make(MD.HaC, "coleslawItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Carrot_Sliced.get(12), OM.dust(MT.PepperBlack), ST.amount(3, aEvent.mStack)), FL.Mayo.make(500), NF, ST.make(MD.HaC, "coleslawItem", 4));
		}});
		addListener("cropBean", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.FishCooked   ), aEvent.mStack), ST.make(MD.HaC, "chiliItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.MeatCooked   ), aEvent.mStack), ST.make(MD.HaC, "chiliItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.SoylentGreen ), aEvent.mStack), ST.make(MD.HaC, "chiliItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(IL.Food_Onion_Sliced.get( 3), OM.dust(MT.Tofu         ), aEvent.mStack), ST.make(MD.HaC, "chiliItem", 1));
		}});
		
		
		addListener("foodCaramel", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Press    .addRecipe2(T, 16,   32, aEvent.mStack, OP.ingot.mat(MT.Chocolate, 1), ST.make(MD.HaC, "chocolatecaramelfudgeItem", 1));
		}});
		addListener("foodFlour", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.Milk   .make( 250), NF, ST.make(MD.HaC, "batterItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.MilkGrC.make( 250), NF, ST.make(MD.HaC, "batterItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.MilkSoy.make( 250), NF, ST.make(MD.HaC, "batterItem", 1));
		}});
		addListener("foodDough", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, MT.FryingOilHot.liquid(U100, T), NF, ST.make(MD.HaC, "donutItem", 1));
		}});

		addListener(new OreDictListenerEvent_TwoNames("listAlljelly", "foodDonut") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Injector .addRecipe2(T, 16,   64, aStack1, ST.amount(4, aStack2), ST.make(MD.HaC, "jellydonutItem", 4));
		}});
		addListener("foodDonut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Bath     .addRecipeX(T,  0,   16, ST.array(aEvent.mStack, IL.Dye_Cactus.get(1), ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 11)), MT.Sugar.liquid(U4, T), NF, ST.make(MD.HaC, "frosteddonutItem", 1));
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, MT.Chocolate.liquid(U4, T), NF, ST.make(MD.HaC, "chocolatedonutItem", 1));
			RM.Mixer    .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Sugar, U4), ST.make(MD.HaC, "powdereddonutItem", 1));
			RM.Mixer    .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Sugar, U3), ST.make(MD.HaC, "powdereddonutItem", 1));
			RM.Mixer    .addRecipe2(T, 16,   64, ST.amount(4, aEvent.mStack), OM.dust(MT.Sugar), ST.make(MD.HaC, "powdereddonutItem", 4));
		}});
		addListener("foodPowdereddonut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer    .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Cinnamon, U4), ST.make(MD.HaC, "cinnamonsugardonutItem", 1));
			RM.Mixer    .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Cinnamon, U3), ST.make(MD.HaC, "cinnamonsugardonutItem", 1));
			RM.Mixer    .addRecipe2(T, 16,   64, ST.amount(4, aEvent.mStack), OM.dust(MT.Cinnamon), ST.make(MD.HaC, "cinnamonsugardonutItem", 4));
		}});
		addListener("foodFilledhoneycomb", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Press    .addRecipe2(T, 16,   32, aEvent.mStack, OP.ingot.mat(MT.Chocolate, 1), ST.make(MD.HaC, "honeycombchocolatebarItem", 1));
		}});
		
		addListener("listAllgrain", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack))
			RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), ST.amount(3, aEvent.mStack), ST.make(MD.HaC, "grainbaitItem", 4));
		}});
		addListener("listAllveggie", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack))
			RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), ST.amount(3, aEvent.mStack), ST.make(MD.HaC, "veggiebaitItem", 4));
		}});
		addListener("listAllfishraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack) && !OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), ST.amount(3, aEvent.mStack), ST.make(MD.HaC, "fishtrapbaitItem", 8));
		}});
		addListener("listAllfruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack))
			RM.Mixer    .addRecipe2(T, 16,   16, ST.make(Items.string, 1, W), ST.amount(3, aEvent.mStack), ST.make(MD.HaC, "fruitbaitItem", 4));
		}});
		addListener("listAllegg", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack) && !OD.listAllmeatsubstitute.is_(aEvent.mStack)) {
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Nutmeg), OM.dust(MT.Cinnamon), aEvent.mStack), FL.Milk   .make( 250), NF, ST.make(MD.HaC, "eggnogItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Nutmeg), OM.dust(MT.Cinnamon), aEvent.mStack), FL.MilkGrC.make( 250), NF, ST.make(MD.HaC, "eggnogItem", 1));
			RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Nutmeg), OM.dust(MT.Cinnamon), aEvent.mStack), FL.MilkSoy.make( 250), NF, ST.make(MD.HaC, "eggnogItem", 1));
			}
		}});
		addListener("listAllsugar", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			OreDictItemData tData = OM.data(aEvent.mStack);
			if (tData == null || tData.mMaterial == null) {
			if (!MD.HaC.owns(aEvent.mStack))
			RM.add_smelting(aEvent.mStack, ST.make(MD.HaC, "caramelItem", 1), F, T, F);
			} else if (tData.mMaterial.mMaterial == MT.Sugar && tData.mMaterial.mAmount >= U) {
			RM.add_smelting(aEvent.mStack, ST.make(MD.HaC, "caramelItem", tData.mMaterial.mAmount / U), F, T, F);
			}
		}});
		addListener("foodBaconcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.ingredable(aEvent.mStack) && !OD.listAllmeatsubstitute.is_(aEvent.mStack)) {
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, MT.Chocolate.liquid(U4, T), NF, ST.make(MD.HaC, "chocolatebaconItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.Syrup_Maple.make(50), NF, ST.make(MD.HaC, "maplecandiedbaconItem", 1));
			RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.Sap_Rainbow.make(50), NF, ST.make(MD.HaC, "epicbaconItem", 1));
			}
		}});
		addListener("foodScrapmeat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			// TODO: Spice + Salt + Meat
			RM.Injector .addRecipe2(T, 16,   16, OM.dust(MT.MeatRaw, U), aEvent.mStack, ST.make(MD.HaC, "porksausageItem", 1));
		}});
		addListener("foodHoneydrop", "dropHoney", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			CR.remove(ST.make(MD.HaC, "potItem", 1), aEvent.mStack);
		}});
		addListener("dustRice", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, ST.make(MD.HaC, "ricecakeItem", 1), F, T, F);
		}});
		addListener(OP.stick.dat(ANY.WoodNormal), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom.addRecipe2(T, 16,   16, ST.make(MD.HaC, "hardenedleatherItem", 1), ST.amount(8, aEvent.mStack), ST.make(Items.item_frame, 1, 0));
		}});
		}};
		
		for (OreDictMaterial tMat : ANY.Wax.mToThis) {
			RM.Laminator.addRecipe2(T, 16,   16, OP.foil.mat(tMat, 1), ST.make(Items.leather, 1, W), ST.make(MD.HaC, "hardenedleatherItem", 1));
		}
	}
}
