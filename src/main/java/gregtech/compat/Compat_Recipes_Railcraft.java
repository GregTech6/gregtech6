/**
 * Copyright (c) 2022 GregTech-6 Team
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
import gregapi.config.ConfigCategories;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;
import static gregapi.util.CR.*;

public class Compat_Recipes_Railcraft extends CompatMods {
	public Compat_Recipes_Railcraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing RC Recipes.");
		long tBits = DEF_REV | DEL_OTHER_SHAPED_RECIPES | DEL_OTHER_NATIVE_RECIPES | ONLY_IF_HAS_OTHER_RECIPES;
		char tHammer = ' ', tFile = ' ', tWrench = ' ';
		OreDictPrefix tIngot = OP.ingot;
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "railcraft_stuff_use_tools", T))  {tHammer = 'h'; tFile = 'f'; tWrench = 'w';}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "railcraft_stuff_use_plates", T)) {tIngot = OP.plate;}
		
		CR.shaped(ST.make(MD.RC, "part.gear"   , 2,  3), tBits | MIR, tHammer+""+tFile, "XX" , "XX", 'X', tIngot.dat(MT.Sn));
		
		CR.shaped(ST.make(MD.RC, "part.gear"   , 1,  0), tBits, tHammer+"X "    , "XGX", " X"+tFile, 'X', OP.nugget.dat(MT.Au) , 'G', ST.make(MD.RC, "part.gear", 1, 3));
		CR.shaped(ST.make(MD.RC, "part.gear"   , 1,  1), tBits, tHammer+"X "    , "XGX", " X"+tFile, 'X', tIngot.dat(ANY.Fe   ), 'G', ST.make(MD.RC, "part.gear", 1, 3));
		CR.shaped(ST.make(MD.RC, "part.gear"   , 1,  2), tBits, tHammer+"X "    , "XGX", " X"+tFile, 'X', tIngot.dat(ANY.Steel), 'G', ST.make(MD.RC, "part.gear", 1, 3));
		
		CR.shaped(ST.make(MD.RC, "part.circuit", 1,  0), tBits, "dCW", "GAR", "WRL", 'W', ST.make(Blocks.wool, 1, 14), 'L', OP.gem.dat(MT.Lapis), 'A', tIngot.dat(MT.Au), 'C', Items.repeater, 'R', OD.itemRedstone, 'G', OD.itemGlue);
		CR.shaped(ST.make(MD.RC, "part.circuit", 1,  1), tBits, "dCW", "GAR", "WRL", 'W', ST.make(Blocks.wool, 1, 13), 'L', OP.gem.dat(MT.Lapis), 'A', tIngot.dat(MT.Au), 'C', Items.repeater, 'R', OD.itemRedstone, 'G', OD.itemGlue);
		CR.shaped(ST.make(MD.RC, "part.circuit", 1,  2), tBits, "dCW", "GAR", "WRL", 'W', ST.make(Blocks.wool, 1,  4), 'L', OP.gem.dat(MT.Lapis), 'A', tIngot.dat(MT.Au), 'C', Items.repeater, 'R', OD.itemRedstone, 'G', OD.itemGlue);
		
		CR.shapeless(ST.make(Blocks.tnt, 1, 0), DEF, new Object[] {ST.make(MD.RC, "cart.tnt.wood", 1, 0)});
		RM.unbox(IL.Plank_Slab.get(5), ST.make(MD.RC, "cart.tnt.wood", 1, 0), ST.make(Blocks.tnt, 1, 0));
		
		CR.shapeless(ST.make(MD.RC, "tool.notepad", 1, 0), tBits, new Object[] {OD.itemFeather, DYE_OREDICTS[DYE_INDEX_Black], OD.paperEmpty, ST.make(MD.RC, "tool.magnifying.glass", 1, 0)});
		
		CR.shapeless(ST.make(MD.RC, "part.gear", 1,  1), CR.DEF_NCC, new Object[] {OP.gearGt.dat(ANY.Fe   ), ST.make(MD.RC, "part.gear", 1, 3)});
		CR.shapeless(ST.make(MD.RC, "part.gear", 1,  2), CR.DEF_NCC, new Object[] {OP.gearGt.dat(ANY.Steel), ST.make(MD.RC, "part.gear", 1, 3)});
		CR.shapeless(IL.RC_Tie_Wood .get(4), CR.DEF_NCC, new Object[] {IL.RC_Bed_Wood});
		CR.shapeless(IL.RC_Tie_Stone.get(4), CR.DEF_NCC, new Object[] {IL.RC_Bed_Stone});
		RM.packunpack(IL.RC_Tie_Wood .get(4), IL.RC_Bed_Wood .get(1));
		RM.packunpack(IL.RC_Tie_Stone.get(4), IL.RC_Bed_Stone.get(1));
		
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8,  0), tBits | DEL_IF_NO_DYES                                , tHammer+"PP"   , tWrench+"PP"                            , 'P', OP.plate.dat(ANY.Fe)                     );
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8,  1), tBits | DEL_IF_NO_DYES                                , "GPG"          , "PGP"              , "GPG"              , 'P', OP.plate.dat(ANY.Fe)                     , 'G', OD.paneGlassColorless);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8,  2), tBits | DEL_IF_NO_DYES                                , "BPB"          , "PLP"              , "BPB"              , 'P', OP.plate.dat(ANY.Fe)                     , 'B', Blocks.iron_bars, 'L', OD.lever);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  3), tBits | DEL_IF_NO_DYES                                , tWrench+"P"    , tHammer+"P"                             , 'P', OP.plate.dat(ANY.Fe)                     );
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  4), tBits | DEL_IF_NO_DYES                                , tWrench+"P"    , tHammer+"P"                             , 'P', OP.plate.dat(ANY.Steel)                  );
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  5), tBits                                                 , "BBB"          , "BFB"              , "BOB"              , 'B', OP.ingot.dat(MT.Brick)                   , 'F', Items.fire_charge, 'O', OD.craftingFurnace);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  6), tBits                                                 , "PUP"          , "BFB"              , "POP"              , 'P', OP.plate.dat(ANY.Steel)                  , 'B', Blocks.iron_bars, 'F', Items.fire_charge, 'U', Items.bucket, 'O', OD.craftingFurnace);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  7), tBits | MIR                                           , "PPP"          , tHammer+"G"+tWrench, "OTO"              , 'P', OP.nugget.dat(MT.Au)                     , 'O', ST.make(MD.RC, "part.gear", 1, 0), 'G', OD.blockGlassColorless, 'T', OD.craftingPiston);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  8), tBits | MIR                                           , "PPP"          , tHammer+"G"+tWrench, "OTO"              , 'P', OP.plate.dat(ANY.Fe)                     , 'O', ST.make(MD.RC, "part.gear", 1, 1), 'G', OD.blockGlassColorless, 'T', OD.craftingPiston);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1,  9), tBits | MIR                                           , "PPP"          , tHammer+"G"+tWrench, "OTO"              , 'P', OP.plate.dat(ANY.Steel)                  , 'O', ST.make(MD.RC, "part.gear", 1, 2), 'G', OD.blockGlassColorless, 'T', OD.craftingPiston);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1, 10), tBits                                                 , " E "          , " O "              , "OIO"              , 'I', tIngot.dat(MT.Au)                        , 'E', OP.gem.dat(MT.EnderPearl), 'O', OP.blockSolid.dat(MT.Obsidian));
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1, 11), tBits                                                 , "OOO"          , "OEO"              , "OOO"              , 'E', OP.gem.dat(MT.EnderPearl)                , 'O', OP.blockSolid.dat(MT.Obsidian));
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 1, 12), tBits                                                 , "GPG"          , "PAP"              , "GPG"              , 'P', OD.craftingPiston                        , 'A', OD.craftingAnvil, 'G', ST.make(MD.RC, "part.gear", 1, 2));
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8, 13), tBits | DEL_IF_NO_DYES                                , tHammer+"PP"   , tWrench+"PP"                            , 'P', OP.plate.dat(ANY.Steel)                  );
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8, 14), tBits | DEL_IF_NO_DYES                                , "GPG"          , "PGP"              , "GPG"              , 'P', OP.plate.dat(ANY.Steel)                  , 'G', OD.paneGlassColorless);
		CR.shaped(ST.make(MD.RC, "machine.beta"           , 8, 15), tBits | DEL_IF_NO_DYES                                , "BPB"          , "PLP"              , "BPB"              , 'P', OP.plate.dat(ANY.Steel)                  , 'B', Blocks.iron_bars, 'L', OD.lever);
		
		CR.shaped(IL.RC_ShuntingWireFrame.get(6)                  , tBits                                                 , "PPP"          , "R"+tWrench+"R"    , "RRR"              , 'P', OP.plate.dat(ANY.Fe)                     , 'R', IL.RC_Rebar.get(1));
		
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  0), tBits                                                 , "IOI"          , "GEG"              , "IOI"              , 'I', tIngot.dat(MT.Au)                        , 'G', OP.gem.dat(ANY.Diamond), 'E', OP.gem.dat(MT.EnderPearl), 'O', OP.blockSolid.dat(MT.Obsidian));
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 3,  1), tBits                                                 , "BPB"          , "P"+tWrench+"P"    , "BPB"              , 'P', OP.plate.dat(ANY.Steel)                  , 'B', OP.blockSolid.dat(ANY.Steel));
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  2), tBits                                                 , "IOI"          , "GEG"              , "IOI"              , 'I', tIngot.dat(MT.Au)                        , 'G', OP.gem.dat(ANY.Emerald), 'E', OP.gem.dat(MT.EnderPearl), 'O', OP.blockSolid.dat(MT.Obsidian));
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 4,  3), tBits                                                 , "PPP"          , "PFP"              , "PPP"              , 'P', OP.plate.dat(ANY.Steel)                  , 'F', OD.craftingFurnace);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  5), tBits                                                 , " N "          , "RCR"                                   , 'R', OD.itemRedstone                          , 'N', OP.stone.dat(MT.Netherrack), 'C', Items.cauldron);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  6), tBits                                                 , "PGP"          , "EDE"              , "PGP"              , 'E', OP.gem.dat(ANY.Emerald)                  , 'P', OP.plate.dat(ANY.Steel), 'G', OD.paneGlassColorless, 'D', Blocks.dispenser);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  8), tBits                                                 , "IPI"          , "PCP"              , "IPI"              , 'P', OD.craftingPiston                        , 'I', tIngot.dat(ANY.Fe), 'C', OD.craftingWorkBench);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1,  9), tBits                                                 , " I "          , " T "              , " D "              , 'I', Blocks.iron_bars                         , 'T', ST.make(MD.RC, "machine.beta", 1, 4), 'D', Blocks.dispenser);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1, 10), tBits                                                 , " I "          , "RTR"              , " D "              , 'I', Blocks.iron_bars                         , 'T', ST.make(MD.RC, "machine.beta", 1, 4), 'D', Blocks.dispenser, 'R', OD.itemRedstone);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1, 10), DEF                                                   , "RTR"                                                    , 'R', OD.itemRedstone                          , 'T', ST.make(MD.RC, "machine.alpha", 1, 9));
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1, 11), tBits                                                 , "PCP"          , "CSC"              , "PCP"              , 'P', OD.plankAnyWood                          , 'S', OP.plate.dat(ANY.Steel), 'C', Items.golden_carrot);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 1, 13), tBits                                                 , "IOI"          , "GEG"              , "IOI"              , 'I', tIngot.dat(MT.Au)                        , 'G', DYE_OREDICTS[DYE_INDEX_Cyan], 'E', OP.gem.dat(MT.EnderPearl), 'O', OP.blockSolid.dat(MT.Obsidian));
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 6, 14), tBits                                                 , "PPP"          , "ISI"              , "PPP"              , 'P', OD.plankAnyWood                          , 'I', tIngot.dat(ANY.Fe), 'S', OD.itemGlue);
		CR.shaped(ST.make(MD.RC, "machine.alpha"          , 4, 15), tBits                                                 , "PDP"          , "DBD"              , "PDP"              , 'P', OD.craftingPiston                        , 'B', OP.blockSolid.dat(ANY.Steel), 'D', OP.gem.dat(ANY.Diamond));
		
		CR.shaped(ST.make(MD.RC, "machine.epsilon"        , 1,  0), tBits                                                 , "PWP"          , "WWW"              , "PWP"              , 'P', OP.plate.dat(ANY.Fe)                     , 'W', OP.wireGt02.dat(ANY.Cu));
		CR.shaped(ST.make(MD.RC, "machine.epsilon"        , 1,  5), tBits                                                 , "TSB"          , "SCS"              , "PSP"              , 'P', OD.craftingPiston                        , 'S', OP.plate.dat(ANY.Steel), 'B', OD.craftingBook, 'C', OD.craftingWorkBench, 'T', ST.make(Items.diamond_pickaxe, 1, 0));
		
		CR.shaped(ST.make(MD.RC, "tool.crowbar"           , 1,  0), tBits                                                 , tHammer+"DS"   , "DSD"              , "SD"+tFile         , 'S', OP.ingot.dat(ANY.Fe)                     , 'D', DYE_OREDICTS[DYE_INDEX_Red]);
		CR.shaped(ST.make(MD.RC, "tool.crowbar.reinforced", 1,  0), tBits                                                 , tHammer+"DS"   , "DSD"              , "SD"+tFile         , 'S', OP.ingot.dat(ANY.Steel)                  , 'D', DYE_OREDICTS[DYE_INDEX_Red]);
		CR.shaped(ST.make(MD.RC, "tool.whistle.tuner"     , 1,  0), tBits | MIR                                           , "S"+tHammer+"S", "SSS"              , " S"+tFile         , 'S', OP.nugget.dat(ANY.Fe)                    );
		CR.shaped(ST.make(MD.RC, "part.turbine.blade"     , 1,  0), tBits                                                 , "S"+tFile      , "S "               , "S"+tHammer        , 'S', tIngot.dat(ANY.Steel)                    );
		CR.shaped(ST.make(MD.RC, "part.turbine.disk"      , 1,  0), tBits                                                 , "SSS"          , "SBS"              , "SSS"              , 'B', OP.blockSolid.dat(ANY.Steel)             , 'S', ST.make(MD.RC, "part.turbine.blade", 1, 0));
		CR.shaped(ST.make(MD.RC, "part.turbine.rotor"     , 1,  0), tBits                                                 , "SSS"          , " "+tWrench+" "                         , 'S', ST.make(MD.RC, "part.turbine.disk", 1, 0));
		CR.shaped(ST.make(MD.RC, "borehead.iron"          , 1,  0), tBits                                                 , "SSS"          , "SBS"              , "SSS"              , 'B', OP.blockSolid.dat(ANY.Fe)                , 'S', tIngot.dat(ANY.Steel));
		CR.shaped(ST.make(MD.RC, "borehead.steel"         , 1,  0), tBits                                                 , "SSS"          , "SBS"              , "SSS"              , 'B', OP.blockSolid.dat(ANY.Steel)             , 'S', tIngot.dat(ANY.Steel));
		CR.shaped(ST.make(MD.RC, "borehead.diamond"       , 1,  0), tBits                                                 , "SSS"          , "SBS"              , "SSS"              , 'B', OP.blockGem.dat(ANY.Diamond)             , 'S', tIngot.dat(ANY.Steel));
		
		CR.shaped(ST.make(MD.RC, "cart.loco.steam.solid"  , 1,  0), tBits | DEL_IF_NO_DYES | DEL_OTHER_RECIPES_IF_SAME_NBT, "TTF"          , "TTF"              , "BCC"              , 'C', Items.minecart                           , 'T', ST.make(MD.RC, "machine.beta", 1, 4), 'F', ST.make(MD.RC, "machine.beta", 1, 5), 'B', Blocks.iron_bars);
		CR.shaped(ST.make(MD.RC, "cart.loco.electric"     , 1,  0), tBits | DEL_IF_NO_DYES | DEL_OTHER_RECIPES_IF_SAME_NBT, "LP"+tWrench   , "PEP"              , "GCG"              , 'C', Items.minecart                           , 'E', ST.make(MD.RC, "machine.epsilon", 1, 0), 'G', ST.make(MD.RC, "part.gear", 1, 2), 'L', Blocks.redstone_lamp, 'P', OP.plate.dat(ANY.Steel));
		CR.shaped(ST.make(MD.RC, "cart.bore"              , 1,  0), tBits | DEL_IF_NO_DYES | DEL_OTHER_RECIPES_IF_SAME_NBT, "BCB"          , "FCF"              , tHammer+"A"+tWrench, 'C', Items.minecart                           , 'A', Items.chest_minecart, 'F', OD.craftingFurnace, 'B', OP.blockSolid.dat(ANY.Steel));
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "railcraft_admin_anchor_recipe", F)) {
			CR.shaped(ST.make(MD.RC, "machine.alpha", 1, 4), tBits, "IOI", "GEG", "IOI", 'I', tIngot.dat(MT.Au), 'G', OP.gem.dat(MT.NetherStar), 'E', OP.gem.dat(MT.EnderPearl), 'O', OP.blockSolid.dat(MT.Obsidian));
		} else {
			ItemsGT.DEBUG_ITEMS.add(ST.make(MD.RC, "machine.alpha", 1, 4));
			ItemsGT.ILLEGAL_DROPS.add(ST.make(MD.RC, "machine.alpha", 1, 4));
			GarbageGT.BLACKLIST.add(ST.make(MD.RC, "machine.alpha", 1, 4));
		}
		
		RM.pulverizing(ST.make(MD.RC, "cube.crushed.obsidian", 1), OP.dust.mat(MT.Obsidian, 7), OP.dust.mat(MT.Obsidian, 1), 25, T);
		
		CR.delate(MD.RC, "part.bleached.clay");
		
		RM.Drying.addRecipe1(T, 16,  16, IL.RC_Rebar.get(1), FL.Concrete.make(L), FL.DistW.make(8), IL.RC_Concrete.get(2));
		
		RM.Injector.addRecipe1(T, 16, 4096, IL.RC_Firestone_Refined.getWithMeta(1, 5000), FL.Coolant_IC2_Hot.make(20000000), FL.Coolant_IC2  .make(20000000), IL.RC_Firestone_Refined.get(1));
		RM.Injector.addRecipe1(T, 16, 8192, IL.RC_Firestone_Cracked.getWithMeta(1, 5000), FL.Coolant_IC2_Hot.make(20000000), FL.Coolant_IC2  .make(20000000), IL.RC_Firestone_Cracked.get(1));
		RM.Injector.addRecipe1(T, 16, 2048, IL.RC_Firestone_Refined.getWithMeta(1, 5000), FL.Lava           .make( 5000000), FL.Lava_Pahoehoe.make( 5000000), IL.RC_Firestone_Refined.get(1));
		RM.Injector.addRecipe1(T, 16, 4096, IL.RC_Firestone_Cracked.getWithMeta(1, 5000), FL.Lava           .make( 5000000), FL.Lava_Pahoehoe.make( 5000000), IL.RC_Firestone_Cracked.get(1));
		if (FL.Lava_Volcanic.exists()) {
		RM.Injector.addRecipe1(T, 16,  128, IL.RC_Firestone_Refined.getWithMeta(1, 5000), FL.Lava_Volcanic  .make(  312500), FL.Lava_Pahoehoe.make(  312500), IL.RC_Firestone_Refined.get(1));
		RM.Injector.addRecipe1(T, 16,  256, IL.RC_Firestone_Cracked.getWithMeta(1, 5000), FL.Lava_Volcanic  .make(  312500), FL.Lava_Pahoehoe.make(  312500), IL.RC_Firestone_Cracked.get(1));
		}
		
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (tPrefix.contains(TD.Prefix.ORE)) tPrefix.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.isGT(aEvent.mStack)) return;
			Block tBlock = ST.block(aEvent.mStack);
			if (tBlock != NB) try {
				short tMeta = ST.meta(aEvent.mStack);
				EntityTunnelBore.addMineableBlock(tBlock, tMeta >= 16 ? -1 : tMeta);
			} catch(Throwable e) {e.printStackTrace(ERR);}
		}});
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener(new Object[] {OP.stone, OP.cobblestone, OP.treeSapling, OP.treeLeaves, OP.tree, OP.log, OP.plank, OP.beam}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.isGT(aEvent.mStack)) return;
			Block tBlock = ST.block(aEvent.mStack);
			if (tBlock != NB) try {
				short tMeta = ST.meta(aEvent.mStack);
				EntityTunnelBore.addMineableBlock(tBlock, tMeta >= 16 ? -1 : tMeta);
			} catch(Throwable e) {e.printStackTrace(ERR);}
		}});
		addListener(OD.itemClay, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer.addRecipe2(T, 16, 16, aEvent.mStack, OM.dust(MT.Bone, U*3), ST.make(MD.RC, "part.bleached.clay", 1, 0));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Cyan], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.LaserEngraver.addRecipe2(T, 16, 2048, OP.gem.mat(MT.Firestone, 1), ST.amount(0, aEvent.mStack), IL.RC_Firestone_Cut.get(1));
		}});
		}};
		
		
		
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Ag      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 4));
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(MT.Ag      , 1), OP.dustSmall.mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 1));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Au      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 4));
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(MT.Au      , 1), OP.dustSmall.mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 1));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Electrum, 4), OP.dust     .mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 4));
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(MT.Electrum, 1), OP.dustSmall.mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 1));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Pt      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 8));
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(MT.Pt      , 1), OP.dustSmall.mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 2));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Os      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get(16));
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(MT.Os      , 1), OP.dustSmall.mat(MT.Redstone, 1)), IL.RC_Rail_Adv.get( 4));
		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Press.addRecipeX(F, 16, 16, ST.array(IL.RC_Rail_Standard.get(1), OP.railGt.mat(tMat       , 1)), IL.RC_Rail_Electric.get(1));
		for (OreDictMaterial tMat : ANY.Blaze.mToThis) {
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Ag      , 4), OP.dustTiny .mat(tMat, 1)), IL.RC_Rail_HS.get( 4));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Au      , 4), OP.dustTiny .mat(tMat, 1)), IL.RC_Rail_HS.get( 4));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Electrum, 4), OP.dustTiny .mat(tMat, 1)), IL.RC_Rail_HS.get( 4));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Pt      , 4), OP.dustTiny .mat(tMat, 1)), IL.RC_Rail_HS.get( 8));
		RM.Press.addRecipeX(F, 16, 64, ST.array(IL.RC_Rail_Standard.get(4), OP.railGt.mat(MT.Os      , 4), OP.dustTiny .mat(tMat, 1)), IL.RC_Rail_HS.get(16));
		}
		
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.Al              ,  6), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.Magnalium       ,  3), IL.RC_Rail_Standard.get( 2));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.Fe              ,  3), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.WroughtIron     ,  2), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.MeteoricIron    ,  3), IL.RC_Rail_Standard.get( 2));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.Bronze          ,  4), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  32, OP.railGt.mat(MT.HSLA            ,  3), IL.RC_Rail_Standard.get( 2));
		RM.RollFormer.addRecipe1(T, 16,  32, OP.railGt.mat(MT.Steel           ,  3), IL.RC_Rail_Standard.get( 2));
		RM.RollFormer.addRecipe1(T, 16,  32, OP.railGt.mat(MT.MeteoricSteel   ,  1), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  16, OP.railGt.mat(MT.StainlessSteel  ,  1), IL.RC_Rail_Standard.get( 1));
		RM.RollFormer.addRecipe1(T, 16,  64, OP.railGt.mat(MT.Ti              ,  3), IL.RC_Rail_Standard.get( 4));
		RM.RollFormer.addRecipe1(T, 16,  64, OP.railGt.mat(MT.W               ,  3), IL.RC_Rail_Standard.get( 4));
		RM.RollFormer.addRecipe1(T, 16,  64, OP.railGt.mat(MT.TungstenSintered,  3), IL.RC_Rail_Standard.get( 4));
		RM.RollFormer.addRecipe1(T, 64,  32, OP.railGt.mat(MT.Meteorite       ,  1), IL.RC_Rail_Reinforced.get( 1));
		RM.RollFormer.addRecipe1(T, 64,  32, OP.railGt.mat(MT.ObsidianSteel   ,  1), IL.RC_Rail_Reinforced.get( 1));
		RM.RollFormer.addRecipe1(T, 64,  64, OP.railGt.mat(MT.TungstenSteel   ,  1), IL.RC_Rail_Reinforced.get( 2));
		RM.RollFormer.addRecipe1(T, 64,  64, OP.railGt.mat(MT.TungstenCarbide ,  1), IL.RC_Rail_Reinforced.get( 2));
		RM.RollFormer.addRecipe1(T, 64,  96, OP.railGt.mat(MT.HSSG            ,  1), IL.RC_Rail_Reinforced.get( 3));
		RM.RollFormer.addRecipe1(T, 64, 128, OP.railGt.mat(MT.HSSE            ,  1), IL.RC_Rail_Reinforced.get( 4));
		RM.RollFormer.addRecipe1(T, 64, 128, OP.railGt.mat(MT.HSSS            ,  1), IL.RC_Rail_Reinforced.get( 4));
		RM.RollFormer.addRecipe1(T, 64,2048, OP.railGt.mat(MT.Ad              ,  1), IL.RC_Rail_Reinforced.get(64));
		
		RM.RollFormer.addRecipe1(T, 16,  64, OP.stick.mat(MT.Al               ,  3), IL.RC_Rebar.get( 1));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.stick.mat(MT.Magnalium        ,  3), IL.RC_Rebar.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 128, OP.stick.mat(MT.Fe               ,  3), IL.RC_Rebar.get( 2));
		RM.RollFormer.addRecipe1(T, 16, 320, OP.stick.mat(MT.WroughtIron      ,  6), IL.RC_Rebar.get( 5));
		RM.RollFormer.addRecipe1(T, 16,  64, OP.stick.mat(MT.Bronze           ,  2), IL.RC_Rebar.get( 1));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.stick.mat(MT.Steel            ,  3), IL.RC_Rebar.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.stick.mat(MT.HSLA             ,  3), IL.RC_Rebar.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 128, OP.stick.mat(MT.StainlessSteel   ,  1), IL.RC_Rebar.get( 2));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.stick.mat(MT.Ti               ,  3), IL.RC_Rebar.get( 8));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.stick.mat(MT.W                ,  3), IL.RC_Rebar.get( 8));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.stick.mat(MT.TungstenSintered ,  3), IL.RC_Rebar.get( 8));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.stick.mat(MT.TungstenSteel    ,  1), IL.RC_Rebar.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.stick.mat(MT.TungstenCarbide  ,  1), IL.RC_Rebar.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 384, OP.stick.mat(MT.HSSG             ,  1), IL.RC_Rebar.get( 6));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.stick.mat(MT.HSSE             ,  1), IL.RC_Rebar.get( 8));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.stick.mat(MT.HSSS             ,  1), IL.RC_Rebar.get( 8));
		RM.RollFormer.addRecipe1(T, 16,4096, OP.stick.mat(MT.Ad               ,  1), IL.RC_Rebar.get(64));
		
		RM.RollFormer.addRecipe1(T, 16,  64, OP.ingot.mat(MT.Al               ,  3), IL.RC_Post_Metal.get( 4));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.ingot.mat(MT.Magnalium        ,  3), IL.RC_Post_Metal.get(16));
		RM.RollFormer.addRecipe1(T, 16, 128, OP.ingot.mat(MT.Fe               ,  3), IL.RC_Post_Metal.get( 8));
		RM.RollFormer.addRecipe1(T, 16, 192, OP.ingot.mat(MT.WroughtIron      ,  3), IL.RC_Post_Metal.get(12));
		RM.RollFormer.addRecipe1(T, 16,  96, OP.ingot.mat(MT.Bronze           ,  3), IL.RC_Post_Metal.get( 6));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.ingot.mat(MT.Steel            ,  3), IL.RC_Post_Metal.get(16));
		RM.RollFormer.addRecipe1(T, 16, 256, OP.ingot.mat(MT.HSLA             ,  3), IL.RC_Post_Metal.get(16));
		RM.RollFormer.addRecipe1(T, 16, 384, OP.ingot.mat(MT.StainlessSteel   ,  3), IL.RC_Post_Metal.get(24));
		RM.RollFormer.addRecipe1(T, 16, 448, OP.ingot.mat(MT.Ti               ,  3), IL.RC_Post_Metal.get(28));
		RM.RollFormer.addRecipe1(T, 16, 448, OP.ingot.mat(MT.W                ,  3), IL.RC_Post_Metal.get(28));
		RM.RollFormer.addRecipe1(T, 16, 448, OP.ingot.mat(MT.TungstenSintered ,  3), IL.RC_Post_Metal.get(28));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.ingot.mat(MT.TungstenSteel    ,  3), IL.RC_Post_Metal.get(32));
		RM.RollFormer.addRecipe1(T, 16, 512, OP.ingot.mat(MT.TungstenCarbide  ,  3), IL.RC_Post_Metal.get(32));
		RM.RollFormer.addRecipe1(T, 16, 768, OP.ingot.mat(MT.HSSG             ,  3), IL.RC_Post_Metal.get(48));
		RM.RollFormer.addRecipe1(T, 16,1024, OP.ingot.mat(MT.HSSE             ,  3), IL.RC_Post_Metal.get(64));
		RM.RollFormer.addRecipe1(T, 16,1024, OP.ingot.mat(MT.HSSS             ,  3), IL.RC_Post_Metal.get(64));
		RM.RollFormer.addRecipe1(T, 16,1024, OP.ingot.mat(MT.Ad               ,  1), IL.RC_Post_Metal.get(64));
	}
}
