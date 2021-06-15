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

package gregtech.items;

import static gregapi.data.CS.*;

import gregapi.code.ItemStackContainer;
import gregapi.cover.covers.*;
import gregapi.data.*;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.ItemsGT;
import gregapi.item.CreativeTab;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.behaviors.Behavior_Turn_Into;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregtech.items.behaviors.Behavior_DataStorage;
import gregtech.items.behaviors.Behavior_DataStorage16;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public class MultiItemTechnological extends MultiItemRandom {
	public MultiItemTechnological() {
		super(MD.GT.mID, "gt.multiitem.technological");
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Technology", this, (short)30501));
	}

	@Override
	public void addItems() {
		int tLastID = 0;
		
		for (int i = 0; i < 10; i++) {
		IL.MOTORS[i]          .set(addItem(12000+i, "Compact Electric Motor ("+VN[i]+")"     , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.MOTUS, 1+i)));
		IL.PUMPS[i]           .set(addItem(12020+i, "Compact Electric Pump ("+VN[i]+")"      , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.ITER, 1+i), TC.stack(TC.AQUA, 1+i), new CoverPump(250<<(2*i))));
		IL.CONVEYERS[i]       .set(addItem(12040+i, "Compact Electric Conveyor ("+VN[i]+")"  , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.ITER, 1+i), new CoverConveyor(512>>i)));
		IL.PISTONS[i]         .set(addItem(12060+i, "Compact Electric Piston ("+VN[i]+")"    , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.MOTUS, 1+i)));
		IL.ROBOT_ARMS[i]      .set(addItem(12080+i, "Compact Robot Arm ("+VN[i]+")"          , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.HUMANUS, 1+i), new CoverRobotArm(512>>i)));
		IL.FIELD_GENERATORS[i].set(addItem(12100+i, "Compact Force Field Emitter ("+VN[i]+")", "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.TUTAMEN, 1+i)));
		IL.EMITTERS[i]        .set(addItem(12120+i, "Compact Signal Emitter ("+VN[i]+")"     , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.LUX, 1+i)));
		IL.SENSORS[i]         .set(addItem(12140+i, "Compact Sensor ("+VN[i]+")"             , "", TC.stack(TC.ELECTRUM, 1+i), TC.stack(TC.MACHINA, 1+i), TC.stack(TC.SENSUS, 1+i)));
		}
		
		IL.Cover_Blank                           .set(addItem(tLastID =  1000, "Blank Cover"                           , "*BLANK*"                                         , new CoverTextureMulti(T, T, "machines/covers/blank/", 6)   , TC.stack(TC.MACHINA, 2)));
		IL.Cover_Crafting                        .set(addItem(tLastID =  1001, "Crafting Table Cover"                  , "A regular old Workbench as a Cover"              , new CoverCrafting("machines/covers/crafting/", 6)          , TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 3)));
		IL.Cover_Machine_Display                 .set(addItem(tLastID =  1002, "Machine Status Display Cover"          , "Shows Machine Status and has ON/OFF Switch"      , new CoverControllerDisplay()                               , TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 3)));
		IL.Cover_Auto_Switch                     .set(addItem(tLastID =  1003, "Automatic Machine Switch"              , "Automatically turns Machines ON/OFF when needed" , new CoverControllerAuto()                                  , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 3)));
		IL.Cover_Energy_Display                  .set(addItem(tLastID =  1004, "Energy Display Cover"                  , "Displays contained Energy"                       , new CoverDisplayEnergy()                                   , TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 2), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Redstone_Switch                 .set(addItem(tLastID =  1005, "Redstone Machine Switch"               , "Turns Machines ON/OFF using Redstone"            , new CoverControllerRedstone()                              , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Auto_Switch_Redstone            .set(addItem(tLastID =  1006, "Auto Redstone Machine Switch"          , "Turns ON/OFF using Redstone but lets it finish"  , new CoverControllerAutoRedstone()                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 3)));
		IL.Cover_Redstone_Selector               .set(addItem(tLastID =  1007, "Redstone Selector"                     , "Selector Cover that is controlled by Redstone"   , new CoverSelectorRedstone()                                , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Manual_Selector                 .set(addItem(tLastID =  1008, "Manual Selector"                       , "Selector Cover that is controlled by Buttons"    , new CoverSelectorManual()                                  , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Auto_Switch_01_Minute           .set(addItem(tLastID =  1009, "Auto Reboot Switch (1 min)"            , "Attempts to Reboot a Machine every Minute"       , new CoverControllerAutoTimer( 1200)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_05_Minute           .set(addItem(tLastID =  1010, "Auto Reboot Switch (5 mins)"           , "Attempts to Reboot a Machine every 5 Minutes"    , new CoverControllerAutoTimer( 6000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_10_Minute           .set(addItem(tLastID =  1011, "Auto Reboot Switch (10 mins)"          , "Attempts to Reboot a Machine every 10 Minutes"   , new CoverControllerAutoTimer(12000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_20_Minute           .set(addItem(tLastID =  1012, "Auto Reboot Switch (20 mins)"          , "Attempts to Reboot a Machine every 20 Minutes"   , new CoverControllerAutoTimer(24000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Auto_Switch_30_Minute           .set(addItem(tLastID =  1013, "Auto Reboot Switch (30 mins)"          , "Attempts to Reboot a Machine every 30 Minutes"   , new CoverControllerAutoTimer(36000)                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Scale_Energy                    .set(addItem(tLastID =  1014, "Energy Sensor"                         , "Emits depending on Energy stored"                , new CoverScaleEnergy()                                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Detector_Possible               .set(addItem(tLastID =  1015, "Activity Detector (Possible)"          , "Emits when Machine could run"                    , new CoverDetectorRunningPossible()                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Detector_Passively              .set(addItem(tLastID =  1016, "Activity Detector (Running)"           , "Emits when Machine is running"                   , new CoverDetectorRunningPassively()                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Detector_Actively               .set(addItem(tLastID =  1017, "Activity Detector (Processing)"        , "Emits when Machine is processing"                , new CoverDetectorRunningActively()                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Scale_Progress                  .set(addItem(tLastID =  1018, "Progress Sensor"                       , "Emits depending on Progress"                     , new CoverScaleProgress()                                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.FABRICO, 1)));
		IL.Cover_Detector_Success                .set(addItem(tLastID =  1019, "Activity Detector (Success)"           , "Emits when Machine produced something"           , new CoverDetectorRunningSuccessfully()                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.FABRICO, 1)));
		IL.Cover_Drain                           .set(addItem(tLastID =  1020, "Drain"                                 , ""                                                , new CoverDrain()                                           , TC.stack(TC.MACHINA, 1), TC.stack(TC.AQUA, 1), TC.stack(TC.VACUOS, 1)));
		IL.Cover_Redstone_Emitter                .set(addItem(tLastID =  1021, "Redstone Emitter"                      , "Emits a constant Redstone Signal"                , new CoverRedstoneEmitter()                                 , TC.stack(TC.MACHINA, 1), TC.stack(TC.POTENTIA, 1)));
		IL.Cover_Vent                            .set(addItem(tLastID =  1022, "Air Vent"                              , "Ventilates Air into Tanks and Pipes"             , new CoverVent()                                            , TC.stack(TC.MACHINA, 1), TC.stack(TC.AER, 1), TC.stack(TC.VACUOS, 1)));
		IL.Cover_Filter_Item                     .set(addItem(tLastID =  1023, "Item Filter"                           , "Filters for an Item"                             , new CoverFilterItem()                                      , TC.stack(TC.MACHINA, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.ITER, 1)));
		IL.Cover_Filter_Fluid                    .set(addItem(tLastID =  1024, "Fluid Filter"                          , "Filters for a Fluid"                             , new CoverFilterFluid()                                     , TC.stack(TC.MACHINA, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.AQUA, 1)));
		IL.Cover_Controller                      .set(addItem(tLastID =  1025, "Cover Controller"                      , "Turns Redstone into ON/OFF State for Covers"     , new CoverControllerCovers()                                , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 2)));
		IL.Cover_Shutter                         .set(addItem(tLastID =  1026, "Shutter Cover"                         , "Connects and Disconnects Pipes and Wires"        , new CoverShutter()                                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Button_Selector                 .set(addItem(tLastID =  1027, "Button Panel Selector"                 , "Selector Cover that is controlled by Buttons"    , new CoverSelectorButtonPanel()                             , TC.stack(TC.MACHINA, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Cover_Warning                         .set(addItem(tLastID =  1028, "Warning Cover"                         , "Warns about certain Types of Danger"             , new CoverTextureMulti(T, T, "machines/covers/warning/", 20), TC.stack(TC.MACHINA, 1), TC.stack(TC.SENSUS, 1), TC.stack(TC.VINCULUM, 1)));
		IL.Cover_Redstone_Conductor_IN           .set(addItem(tLastID =  1029, "Redstone Conductor Cover (Accept)"     , "Redstone will transfer to emitting Conductors"   , new CoverRedstoneConductorIN()                             , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 1)));
		IL.Cover_Redstone_Conductor_OUT          .set(addItem(tLastID =  1030, "Redstone Conductor Cover (Emit)"       , "Redstone of accepting Conductors gets emitted"   , new CoverRedstoneConductorOUT()                            , TC.stack(TC.MACHINA, 2), TC.stack(TC.VINCULUM, 1)));
		IL.Cover_Retriever_Item                  .set(addItem(tLastID =  1031, "Item Retriever Cover"                  , "Pulls Items from the attached Pipe Network"      , new CoverRetrieverItem()                                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.ITER, 1)));
		
		
		RM.Other.addFakeRecipe(F, ST.array(IL.Cover_Vent .get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Air.make(0), FL.Air_Nether.make(0), FL.Air_End.make(0)), 0, 0, 0);
		RM.Other.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Water.make(0), FL.Ocean.make(0), FL.Dirty_Water.make(0)), 0, 0, 0);
		if (FL.XP.exists() || FL.Mob.exists())
		RM.Other.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.XP.make(20), FL.Mob.make(66)), 0, 0, 0);
		if (FL.Sewage.exists())
		RM.Other.addFakeRecipe(F, ST.array(IL.Cover_Drain.get(1)), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sewage.make(0)), 0, 0, 0);
		
		
		IL.Cover_Logistics_Display_CPU_Logic     .set(addItem(tLastID =  1086, "Logistics Display (CPU Logic)"         , "For use with Logistics Cores and Wiring"         , CoverLogisticsDisplayCPULogic.INSTANCE                     , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Control   .set(addItem(tLastID =  1087, "Logistics Display (CPU Control)"       , "For use with Logistics Cores and Wiring"         , CoverLogisticsDisplayCPUControl.INSTANCE                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Storage   .set(addItem(tLastID =  1088, "Logistics Display (CPU Storage)"       , "For use with Logistics Cores and Wiring"         , CoverLogisticsDisplayCPUStorage.INSTANCE                   , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Display_CPU_Conversion.set(addItem(tLastID =  1089, "Logistics Display (CPU Conversion)"    , "For use with Logistics Cores and Wiring"         , CoverLogisticsDisplayCPUConversion.INSTANCE                , TC.stack(TC.MACHINA, 2), TC.stack(TC.SENSUS, 2), TC.stack(TC.ITER, 1)));
		IL.Cover_Logistics_Fluid_Export          .set(addItem(tLastID =  1090, "Filtered Logistics Export Bus (Fluid)" , "For use with Logistics Cores and Wiring"         , CoverLogisticsFluidExport.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Fluid_Import          .set(addItem(tLastID =  1091, "Filtered Logistics Import Bus (Fluid)" , "For use with Logistics Cores and Wiring"         , CoverLogisticsFluidImport.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Fluid_Storage         .set(addItem(tLastID =  1092, "Filtered Logistics Storage Bus (Fluid)", "For use with Logistics Cores and Wiring"         , CoverLogisticsFluidStorage.INSTANCE                        , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Export           .set(addItem(tLastID =  1093, "Filtered Logistics Export Bus (Item)"  , "For use with Logistics Cores and Wiring"         , CoverLogisticsItemExport.INSTANCE                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Import           .set(addItem(tLastID =  1094, "Filtered Logistics Import Bus (Item)"  , "For use with Logistics Cores and Wiring"         , CoverLogisticsItemImport.INSTANCE                          , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Item_Storage          .set(addItem(tLastID =  1095, "Filtered Logistics Storage Bus (Item)" , "For use with Logistics Cores and Wiring"         , CoverLogisticsItemStorage.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Export        .set(addItem(tLastID =  1096, "Generic Logistics Export Bus"          , "For use with Logistics Cores and Wiring"         , CoverLogisticsGenericExport.INSTANCE                       , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Import        .set(addItem(tLastID =  1097, "Generic Logistics Import Bus"          , "For use with Logistics Cores and Wiring"         , CoverLogisticsGenericImport.INSTANCE                       , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Generic_Storage       .set(addItem(tLastID =  1098, "Generic Logistics Storage Bus"         , "For use with Logistics Cores and Wiring"         , CoverLogisticsGenericStorage.INSTANCE                      , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		IL.Cover_Logistics_Dump                  .set(addItem(tLastID =  1099, "Logistics Dump Bus (Item)"             , "For use with Logistics Cores and Wiring"         , CoverLogisticsGenericDump.INSTANCE                         , TC.stack(TC.MACHINA, 2), TC.stack(TC.COGNITIO, 2), TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.ITER, 4)));
		
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Logic     .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Conversion});
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Control   .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Logic     });
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Storage   .get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Control   });
		CR.shapeless(IL.Cover_Logistics_Display_CPU_Conversion.get(1), new Object[] {IL.Cover_Logistics_Display_CPU_Storage   });
		
		CR.shapeless(IL.Cover_Logistics_Fluid_Export          .get(1), new Object[] {IL.Cover_Logistics_Dump                  });
		CR.shapeless(IL.Cover_Logistics_Fluid_Import          .get(1), new Object[] {IL.Cover_Logistics_Fluid_Export          });
		CR.shapeless(IL.Cover_Logistics_Fluid_Storage         .get(1), new Object[] {IL.Cover_Logistics_Fluid_Import          });
		CR.shapeless(IL.Cover_Logistics_Item_Export           .get(1), new Object[] {IL.Cover_Logistics_Fluid_Storage         });
		CR.shapeless(IL.Cover_Logistics_Item_Import           .get(1), new Object[] {IL.Cover_Logistics_Item_Export           });
		CR.shapeless(IL.Cover_Logistics_Item_Storage          .get(1), new Object[] {IL.Cover_Logistics_Item_Import           });
		CR.shapeless(IL.Cover_Logistics_Generic_Export        .get(1), new Object[] {IL.Cover_Logistics_Item_Storage          });
		CR.shapeless(IL.Cover_Logistics_Generic_Import        .get(1), new Object[] {IL.Cover_Logistics_Generic_Export        });
		CR.shapeless(IL.Cover_Logistics_Generic_Storage       .get(1), new Object[] {IL.Cover_Logistics_Generic_Import        });
		CR.shapeless(IL.Cover_Logistics_Dump                  .get(1), new Object[] {IL.Cover_Logistics_Generic_Storage       });
		
		
		
		CR.shaped(IL.Cover_Blank                    .get(1), CR.DEF_REV, "Sh" , "Pd"        , 'P', OP.plate.dat(MT.Al), 'S', OP.screw.dat(MT.Al));
		CR.shaped(IL.Cover_Crafting                 .get(1), CR.DEF_REV,  "C" ,  "Q"        , 'Q', IL.Cover_Blank, 'C', OD.craftingWorkBench);
		CR.shaped(IL.Cover_Machine_Display          .get(1), CR.DEF_REV, "LLB", "CQW"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.lever, 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Auto_Switch              .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.lever);
		CR.shaped(IL.Cover_Energy_Display           .get(1), CR.DEF_REV, "CLB", "WQW"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', MT.DATA.WIRES_01[1], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Redstone_Switch          .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', OD.craftingRedstoneTorch);
		CR.shaped(IL.Cover_Auto_Switch_Redstone     .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[2], 'B', OD.lever);
		CR.shaped(IL.Cover_Redstone_Selector        .get(1), CR.DEF_REV, " C ", "WQX", " B ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[2], 'B', Items.comparator, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Manual_Selector          .get(1), CR.DEF_REV, " C ", "WQX", " B ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[2], 'B', OD.button, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Auto_Switch_01_Minute    .get(1), CR.DEF_REV, "BWd", "CQ "       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[5], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_05_Minute    .get(1), CR.DEF_REV, "BW ", "CQd"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[4], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_10_Minute    .get(1), CR.DEF_REV, "BW ", "CQ ", "  d", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[3], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_20_Minute    .get(1), CR.DEF_REV, "BW" , "CQ" , " d" , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Auto_Switch_30_Minute    .get(1), CR.DEF_REV, "BW" , "CQ" , "d " , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Scale_Energy             .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', MT.DATA.WIRES_01[1]);
		CR.shaped(IL.Cover_Detector_Possible        .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator);
		CR.shaped(IL.Cover_Detector_Passively       .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.repeater);
		CR.shaped(IL.Cover_Detector_Actively        .get(1), CR.DEF_REV, "WQW", "BCX"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator, 'X', Items.repeater);
		CR.shaped(IL.Cover_Scale_Progress           .get(1), CR.DEF_REV, "WQW", "BCB"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', OP.gearGtSmall.dat(MT.Brass));
		CR.shaped(IL.Cover_Detector_Success         .get(1), CR.DEF_REV, "WQW", "BCX"       , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', OD.button, 'X', OD.craftingRedstoneTorch);
		CR.shaped(IL.Cover_Drain                    .get(1), CR.DEF_REV, "RRR", "RwR", "RRR", 'R', OP.stick.dat(ANY.Iron));
		CR.shaped(IL.Cover_Redstone_Emitter         .get(1), CR.DEF_REV, "BQB", "WXW"       , 'Q', IL.Cover_Blank, 'W', MT.DATA.CABLES_01[1], 'B', OD.button, 'X', Items.comparator);
		CR.shaped(IL.Cover_Vent                     .get(1), CR.DEF_REV, "RRR", "RXR", "RRR", 'R', OP.stick.dat(ANY.Iron), 'X', OP.rotor.dat(ANY.Iron));
		CR.shaped(IL.Cover_Filter_Item              .get(1), CR.DEF_REV, " Z ", "ZQZ", " Z ", 'Q', IL.Cover_Blank, 'Z', OP.foil.dat(MT.Zn));
		CR.shaped(IL.Cover_Filter_Fluid             .get(1), CR.DEF_REV, "Z Z", " Q ", "Z Z", 'Q', IL.Cover_Blank, 'Z', OP.foil.dat(MT.Zn));
		CR.shaped(IL.Cover_Controller               .get(1), CR.DEF_REV, "BW" , "CQ"        , 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[1], 'B', Items.comparator);
		CR.shaped(IL.Cover_Shutter                  .get(1), CR.DEF_REV, "TwT", "PQP", "TdT", 'Q', IL.Cover_Blank, 'P', OP.plate.dat(MT.StainlessSteel), 'T', OP.screw.dat(MT.StainlessSteel));
		CR.shaped(IL.Cover_Button_Selector          .get(1), CR.DEF_REV, "BXB", "BQB", "BCB", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[1], 'B', OD.button, 'X', IL.Circuit_Selector.wild(1));
		CR.shaped(IL.Cover_Warning                  .get(1), CR.DEF_REV, "GB" , "YQ"        , 'Q', IL.Cover_Blank, 'Y', DYE_OREDICTS[DYE_INDEX_Yellow], 'G', OD.itemGlue);
		CR.shaped(IL.Cover_Redstone_Conductor_IN    .get(1), CR.DEF_REV,  "R" ,  "Q"        , 'Q', IL.Cover_Blank, 'R', OP.wireGt01.dat(MT.RedAlloy));
		CR.shaped(IL.Cover_Redstone_Conductor_OUT   .get(1), CR.DEF_REV,  "Q" ,  "R"        , 'Q', IL.Cover_Blank, 'R', OP.wireGt01.dat(MT.RedAlloy));
		CR.shaped(IL.Cover_Retriever_Item           .get(1), CR.DEF_REV, "RPR", "CQC"       , 'Q', IL.Cover_Filter_Item, 'P', IL.PISTONS[1], 'C', OD_CIRCUITS[3], 'R', OP.plateCurved.dat(MT.Electrum));
		
		CR.shapeless(IL.Cover_Redstone_Conductor_IN .get(1), new Object[] {IL.Cover_Redstone_Conductor_OUT.get(1)});
		CR.shapeless(IL.Cover_Redstone_Conductor_OUT.get(1), new Object[] {IL.Cover_Redstone_Conductor_IN .get(1)});


		IL.Cover_Pressure_Valve            .set(addItem(tLastID =  2000, "Pressure Valve"                   , ""                                                , new CoverPressureValve()                              , TC.stack(TC.MACHINA, 1), TC.stack(TC.AER, 1), TC.stack(TC.VACUOS, 1)));

		CR.shaped(IL.Cover_Pressure_Valve           .get(1), CR.DEF_REV, "TCT", "wPd"       , 'C', OP.plateCurved.dat(MT.Brass), 'P', OP.plate.dat(MT.Brass), 'T', OP.screw.dat(MT.Brass));



		IL.Shape_Extruder_Empty            .set(addItem(tLastID = 10000, "Empty Extruder Shape"             , "Raw Plate to make Extruder Shapes"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));

		CR.shaped(IL.Shape_Extruder_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(MT.TungstenCarbide));

		IL.Shape_Extruder_Plate            .set(addItem(tLastID = 10001, "Extruder Shape (Plate)"                   , "Extruder Shape for making Plates"                    , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Rod_Long         .set(addItem(tLastID = 10002, "Extruder Shape (Long Rod)"                , "Extruder Shape for making long Rods"                 , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Bolt             .set(addItem(tLastID = 10003, "Extruder Shape (Bolt)"                    , "Extruder Shape for making Bolts"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Ring             .set(addItem(tLastID = 10004, "Extruder Shape (Ring)"                    , "Extruder Shape for making Rings"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Cell             .set(addItem(tLastID = 10005, "Extruder Shape (Cell)"                    , "Extruder Shape for making Cells"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Ingot            .set(addItem(tLastID = 10006, "Extruder Shape (Ingot)"                   , "Extruder Shape for making Ingots"                    , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Wire             .set(addItem(tLastID = 10007, "Extruder Shape (Wire)"                    , "Extruder Shape for making Wires"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Casing           .set(addItem(tLastID = 10008, "Extruder Shape (Casing)"                  , "Extruder Shape for making Item Casings"              , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Tiny        .set(addItem(tLastID = 10009, "Extruder Shape (Tiny Pipe)"               , "Extruder Shape for making tiny Pipes"                , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Small       .set(addItem(tLastID = 10010, "Extruder Shape (Small Pipe)"              , "Extruder Shape for making small Pipes"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Medium      .set(addItem(tLastID = 10011, "Extruder Shape (Normal Pipe)"             , "Extruder Shape for making Pipes"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Large       .set(addItem(tLastID = 10012, "Extruder Shape (Large Pipe)"              , "Extruder Shape for making large Pipes"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pipe_Huge        .set(addItem(tLastID = 10013, "Extruder Shape (Huge Pipe)"               , "Extruder Shape for making full Block Pipes"          , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Block            .set(addItem(tLastID = 10014, "Extruder Shape (Block)"                   , "Extruder Shape for making Blocks"                    , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Sword            .set(addItem(tLastID = 10015, "Extruder Shape (Sword Blade)"             , "Extruder Shape for making Swords"                    , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Pickaxe          .set(addItem(tLastID = 10016, "Extruder Shape (Pickaxe Head)"            , "Extruder Shape for making Pickaxes"                  , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Shovel           .set(addItem(tLastID = 10017, "Extruder Shape (Shovel Head)"             , "Extruder Shape for making Shovels"                   , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Axe              .set(addItem(tLastID = 10018, "Extruder Shape (Axe Head)"                , "Extruder Shape for making Axes"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Hoe              .set(addItem(tLastID = 10019, "Extruder Shape (Hoe Head)"                , "Extruder Shape for making Hoes"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Hammer           .set(addItem(tLastID = 10020, "Extruder Shape (Hammer Head)"             , "Extruder Shape for making Hammers"                   , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_File             .set(addItem(tLastID = 10021, "Extruder Shape (File Head)"               , "Extruder Shape for making Files"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Saw              .set(addItem(tLastID = 10022, "Extruder Shape (Saw Blade)"               , "Extruder Shape for making Saws"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Gear             .set(addItem(tLastID = 10023, "Extruder Shape (Gear)"                    , "Extruder Shape for making Gears"                     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Bottle           .set(addItem(tLastID = 10024, "Extruder Shape (Bottle)"                  , "Extruder Shape for making Bottles"                   , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Plate_Curved     .set(addItem(tLastID = 10025, "Extruder Shape (Curved Plate)"            , "Extruder Shape for making Curved Plates"             , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Gear_Small       .set(addItem(tLastID = 10026, "Extruder Shape (Small Gear)"              , "Extruder Shape for making small Gears"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Rod              .set(addItem(tLastID = 10027, "Extruder Shape (Rod)"                     , "Extruder Shape for making Rods"                      , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_CCC              .set(addItem(tLastID = 10028, "Extruder Shape (Capsule-Cell-Container)"  , "Extruder Shape for making Capsule-Cell-Containers"   , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Foil             .set(addItem(tLastID = 10029, "Extruder Shape (Foil)"                    , "Extruder Shape for making Foils from Non-Metals"     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_Extruder_Plate_Tiny       .set(addItem(tLastID = 10030, "Extruder Shape (Tiny Plate)"              , "Extruder Shape for making Tiny Plates"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));

		CR.shaped(IL.Shape_Extruder_Ingot           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Plate_Tiny      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Plate_Curved    .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Rod             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Foil            .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Empty);
		CR.shaped(IL.Shape_Extruder_Ring            .get(1), CR.DEF_REV, "   ", " P ", " x ", 'P', IL.Shape_Extruder_Empty);

		CR.shaped(IL.Shape_Extruder_Bolt            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Rod);
		CR.shaped(IL.Shape_Extruder_Wire            .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Rod);
		CR.shaped(IL.Shape_Extruder_Rod_Long        .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Rod);

		CR.shaped(IL.Shape_Extruder_Block           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Pickaxe         .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Hammer          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Ingot);
		CR.shaped(IL.Shape_Extruder_Hoe             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Ingot);

		CR.shaped(IL.Shape_Extruder_Gear            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Gear_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Bottle          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_Cell            .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Ring);
		CR.shaped(IL.Shape_Extruder_CCC             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Ring);

		CR.shaped(IL.Shape_Extruder_Axe             .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Shovel          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_File            .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Sword           .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Plate_Tiny);
		CR.shaped(IL.Shape_Extruder_Saw             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Plate_Tiny);

		CR.shaped(IL.Shape_Extruder_Plate           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Foil);
		CR.shaped(IL.Shape_Extruder_Casing          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Foil);

		CR.shaped(IL.Shape_Extruder_Pipe_Tiny       .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Medium     .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Large      .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_Extruder_Plate_Curved);
		CR.shaped(IL.Shape_Extruder_Pipe_Huge       .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_Extruder_Plate_Curved);

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Empty.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Plate.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Rod_Long.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Bolt.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Ring.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Cell.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Ingot.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Wire.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Casing.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pipe_Tiny.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pipe_Small.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pipe_Medium.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pipe_Large.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pipe_Huge.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Block.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Sword.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Pickaxe.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Shovel.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Axe.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Hoe.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Hammer.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_File.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Saw.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Gear.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Bottle.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Plate_Curved.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Gear_Small.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Rod.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_CCC.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Foil.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Extruder_Plate_Tiny.get(1)), (byte)45);



		IL.Shape_SimpleEx_Empty            .set(addItem(tLastID = 10200, "Empty Extruder Shape"             , "Raw Plate to make Extruder Shapes"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));

		CR.shaped(IL.Shape_SimpleEx_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(ANY.Steel));

		IL.Shape_SimpleEx_Plate            .set(addItem(tLastID = 10201, "Low Heat Extruder Shape (Plate)"                  , "Extruder Shape for making Plates"                    , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Rod_Long         .set(addItem(tLastID = 10202, "Low Heat Extruder Shape (Long Rod)"               , "Extruder Shape for making long Rods"                 , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Bolt             .set(addItem(tLastID = 10203, "Low Heat Extruder Shape (Bolt)"                   , "Extruder Shape for making Bolts"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Ring             .set(addItem(tLastID = 10204, "Low Heat Extruder Shape (Ring)"                   , "Extruder Shape for making Rings"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Cell             .set(addItem(tLastID = 10205, "Low Heat Extruder Shape (Cell)"                   , "Extruder Shape for making Cells"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Ingot            .set(addItem(tLastID = 10206, "Low Heat Extruder Shape (Ingot)"                  , "Extruder Shape for making Ingots"                    , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Wire             .set(addItem(tLastID = 10207, "Low Heat Extruder Shape (Wire)"                   , "Extruder Shape for making Wires"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Casing           .set(addItem(tLastID = 10208, "Low Heat Extruder Shape (Casing)"                 , "Extruder Shape for making Item Casings"              , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Tiny        .set(addItem(tLastID = 10209, "Low Heat Extruder Shape (Tiny Pipe)"              , "Extruder Shape for making tiny Pipes"                , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Small       .set(addItem(tLastID = 10210, "Low Heat Extruder Shape (Small Pipe)"             , "Extruder Shape for making small Pipes"               , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Medium      .set(addItem(tLastID = 10211, "Low Heat Extruder Shape (Normal Pipe)"            , "Extruder Shape for making Pipes"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Large       .set(addItem(tLastID = 10212, "Low Heat Extruder Shape (Large Pipe)"             , "Extruder Shape for making large Pipes"               , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pipe_Huge        .set(addItem(tLastID = 10213, "Low Heat Extruder Shape (Huge Pipe)"              , "Extruder Shape for making full Block Pipes"          , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Block            .set(addItem(tLastID = 10214, "Low Heat Extruder Shape (Block)"                  , "Extruder Shape for making Blocks"                    , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Sword            .set(addItem(tLastID = 10215, "Low Heat Extruder Shape (Sword Blade)"            , "Extruder Shape for making Swords"                    , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Pickaxe          .set(addItem(tLastID = 10216, "Low Heat Extruder Shape (Pickaxe Head)"           , "Extruder Shape for making Pickaxes"                  , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Shovel           .set(addItem(tLastID = 10217, "Low Heat Extruder Shape (Shovel Head)"            , "Extruder Shape for making Shovels"                   , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Axe              .set(addItem(tLastID = 10218, "Low Heat Extruder Shape (Axe Head)"               , "Extruder Shape for making Axes"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Hoe              .set(addItem(tLastID = 10219, "Low Heat Extruder Shape (Hoe Head)"               , "Extruder Shape for making Hoes"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Hammer           .set(addItem(tLastID = 10220, "Low Heat Extruder Shape (Hammer Head)"            , "Extruder Shape for making Hammers"                   , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_File             .set(addItem(tLastID = 10221, "Low Heat Extruder Shape (File Head)"              , "Extruder Shape for making Files"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Saw              .set(addItem(tLastID = 10222, "Low Heat Extruder Shape (Saw Blade)"              , "Extruder Shape for making Saws"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Gear             .set(addItem(tLastID = 10223, "Low Heat Extruder Shape (Gear)"                   , "Extruder Shape for making Gears"                     , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Bottle           .set(addItem(tLastID = 10224, "Low Heat Extruder Shape (Bottle)"                 , "Extruder Shape for making Bottles"                   , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Plate_Curved     .set(addItem(tLastID = 10225, "Low Heat Extruder Shape (Curved Plate)"           , "Extruder Shape for making Curved Plates"             , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Gear_Small       .set(addItem(tLastID = 10226, "Low Heat Extruder Shape (Small Gear)"             , "Extruder Shape for making small Gears"               , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Rod              .set(addItem(tLastID = 10227, "Low Heat Extruder Shape (Rod)"                    , "Extruder Shape for making Rods"                      , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_CCC              .set(addItem(tLastID = 10228, "Low Heat Extruder Shape (Capsule-Cell-Container)" , "Extruder Shape for making Capsule-Cell-Containers"   , TC.stack(TC.FABRICO, 1), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Foil             .set(addItem(tLastID = 10229, "Low Heat Extruder Shape (Foil)"                   , "Extruder Shape for making Foils from Non-Metals"     , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));
		IL.Shape_SimpleEx_Plate_Tiny       .set(addItem(tLastID = 10230, "Low Heat Extruder Shape (Tiny Plate)"             , "Extruder Shape for making Tiny Plates"               , TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 2)));

		CR.shaped(IL.Shape_SimpleEx_Ingot           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Plate_Tiny      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Plate_Curved    .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Rod             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Foil            .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Empty);
		CR.shaped(IL.Shape_SimpleEx_Ring            .get(1), CR.DEF_REV, "   ", " P ", " x ", 'P', IL.Shape_SimpleEx_Empty);

		CR.shaped(IL.Shape_SimpleEx_Bolt            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);
		CR.shaped(IL.Shape_SimpleEx_Wire            .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);
		CR.shaped(IL.Shape_SimpleEx_Rod_Long        .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Rod);

		CR.shaped(IL.Shape_SimpleEx_Block           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Pickaxe         .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Hammer          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Ingot);
		CR.shaped(IL.Shape_SimpleEx_Hoe             .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Ingot);

		CR.shaped(IL.Shape_SimpleEx_Gear            .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Gear_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Bottle          .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_Cell            .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Ring);
		CR.shaped(IL.Shape_SimpleEx_CCC             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Ring);

		CR.shaped(IL.Shape_SimpleEx_Axe             .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Shovel          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_File            .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Sword           .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Plate_Tiny);
		CR.shaped(IL.Shape_SimpleEx_Saw             .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Plate_Tiny);

		CR.shaped(IL.Shape_SimpleEx_Plate           .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Foil);
		CR.shaped(IL.Shape_SimpleEx_Casing          .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Foil);

		CR.shaped(IL.Shape_SimpleEx_Pipe_Tiny       .get(1), CR.DEF_REV, "x  ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Small      .get(1), CR.DEF_REV, " x ", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Medium     .get(1), CR.DEF_REV, "  x", " P ", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Large      .get(1), CR.DEF_REV, "   ", " Px", "   ", 'P', IL.Shape_SimpleEx_Plate_Curved);
		CR.shaped(IL.Shape_SimpleEx_Pipe_Huge       .get(1), CR.DEF_REV, "   ", " P ", "  x", 'P', IL.Shape_SimpleEx_Plate_Curved);

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Empty.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Plate.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Rod_Long.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Bolt.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Ring.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Cell.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Ingot.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Wire.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Casing.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pipe_Tiny.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pipe_Small.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pipe_Medium.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pipe_Large.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pipe_Huge.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Block.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Sword.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Pickaxe.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Shovel.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Axe.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Hoe.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Hammer.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_File.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Saw.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Gear.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Bottle.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Plate_Curved.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Gear_Small.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Rod.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_CCC.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Foil.get(1)), (byte)55);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_SimpleEx_Plate_Tiny.get(1)), (byte)55);


		IL.Shape_Foodmold_Empty            .set(addItem(tLastID = 10800, "Empty Food Grade Mold"            , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));

		CR.shaped(IL.Shape_Foodmold_Empty.get(1), CR.DEF_REV, "hf" , "xP", 'P', OP.plateDouble.dat(MT.StainlessSteel));

		IL.Shape_Foodmold_Bun              .set(addItem(tLastID = 10801, "Food Grade Mold (Bun)"            , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Bread            .set(addItem(tLastID = 10802, "Food Grade Mold (Bread)"          , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Baguette         .set(addItem(tLastID = 10803, "Food Grade Mold (Baguette)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		IL.Shape_Foodmold_Cylinder         .set(addItem(tLastID = 10804, "Food Grade Mold (Cylinder)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));

		CR.shaped(IL.Shape_Foodmold_Bun             .get(1), CR.DEF_REV, "h  ", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Bread           .get(1), CR.DEF_REV, " h ", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Baguette        .get(1), CR.DEF_REV, "  h", " P ", "   ", 'P', IL.Shape_Foodmold_Empty);
		CR.shaped(IL.Shape_Foodmold_Cylinder        .get(1), CR.DEF_REV, "   ", " Ph", "   ", 'P', IL.Shape_Foodmold_Empty);

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Foodmold_Empty.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Foodmold_Bun.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Foodmold_Bread.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Foodmold_Baguette.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Foodmold_Cylinder.get(1)), (byte)45);
		
		
		
		
		IL.Shape_Press_Bullet_Casing_Small .set(addItem(tLastID = 10896, "Bullet Casing Mold (Small)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 2)));
		IL.Shape_Press_Bullet_Casing_Medium.set(addItem(tLastID = 10897, "Bullet Casing Mold (Medium)"      , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 3)));
		IL.Shape_Press_Bullet_Casing_Large .set(addItem(tLastID = 10898, "Bullet Casing Mold (Large)"       , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.TELUM, 4)));

		CR.shaped(IL.Shape_Press_Bullet_Casing_Small .get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateDouble.dat(ANY.Steel));
		CR.shaped(IL.Shape_Press_Bullet_Casing_Medium.get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateTriple.dat(ANY.Steel));
		CR.shaped(IL.Shape_Press_Bullet_Casing_Large .get(1), CR.DEF_REV, "TPT", "dyh", "SPS", 'S', OP.stick.dat(ANY.Steel), 'T', OP.screw.dat(ANY.Steel), 'P', OP.plateQuadruple.dat(ANY.Steel));

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Press_Bullet_Casing_Small .get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Press_Bullet_Casing_Medium.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Press_Bullet_Casing_Large .get(1)), (byte)45);
		
		
		
		IL.Shape_Slicer_Empty              .set(addItem(tLastID = 10900, "Slicer Blade Frame"               , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1)));
		
		CR.shaped(IL.Shape_Slicer_Empty.get(1), CR.DEF_REV, " R ", "RhR", " R ", 'R', OP.stick.dat(MT.StainlessSteel));
		
		IL.Shape_Slicer_Flat               .set(addItem(tLastID = 10901, "Slicer Blades (Flat)"             , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Grid               .set(addItem(tLastID = 10902, "Slicer Blades (Grid)"             , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Eigths             .set(addItem(tLastID = 10903, "Slicer Blades (Eigths)"           , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Eigths_Hollow      .set(addItem(tLastID = 10904, "Slicer Blades (Hollow Eigths)"    , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Split              .set(addItem(tLastID = 10905, "Slicer Blades (Split)"            , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Quarters           .set(addItem(tLastID = 10906, "Slicer Blades (Quarters)"         , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));
		IL.Shape_Slicer_Quarters_Hollow    .set(addItem(tLastID = 10907, "Slicer Blades (Hollow Quarters)"  , "", TC.stack(TC.FABRICO, 2), TC.stack(TC.METALLUM, 1), TC.stack(TC.TELUM, 1)));

		CR.shaped(IL.Shape_Slicer_Flat              .get(1), CR.DEF_REV, "B f", "BO ", "B s", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Grid              .get(1), CR.DEF_REV, " Bf", "BOB", " Bs", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Eigths            .get(1), CR.DEF_REV, "B B", "s f", "BOB", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Eigths_Hollow     .get(1), CR.DEF_REV, "B B", "sRf", "BOB", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel), 'R', OP.ring.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Split             .get(1), CR.DEF_REV, " Of", "BBB", "  s", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Quarters          .get(1), CR.DEF_REV, "fB ", "B s", " O ", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel));
		CR.shaped(IL.Shape_Slicer_Quarters_Hollow   .get(1), CR.DEF_REV, "fB ", "BRs", " O ", 'O', IL.Shape_Slicer_Empty, 'B', OP.plateTiny.dat(MT.StainlessSteel), 'R', OP.ring.dat(MT.StainlessSteel));

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Empty.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Flat.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Grid.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Eigths.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Eigths_Hollow.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Split.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Quarters.get(1)), (byte)45);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.Shape_Slicer_Quarters_Hollow.get(1)), (byte)45);


		IL.Comp_Laser_Gas_Empty            .set(addItem(tLastID = 11000, "Empty Gas Laser Emitter"          , "For Electric Lasers"                             , TC.stack(TC.LUX, 1), TC.stack(TC.VACUOS, 2)));
		CR.shaped(IL.Comp_Laser_Gas_Empty.get(1), CR.DEF_REV_NCC, "CWM", "WGx", "MTd", 'W', MT.DATA.CABLES_01[2], 'C', OD_CIRCUITS[2], 'M', OP.plate.dat(MT.Ag), 'T', OP.screw.dat(MT.StainlessSteel), 'G', OD.blockGlassColorless);

		IL.Comp_Laser_Gas_He               .set(addItem(tLastID = 11001, "Helium Laser Emitter"             , "Purpose: Weak Optical Appliances"                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Ne               .set(addItem(tLastID = 11002, "Neon Laser Emitter"               , "Purpose: Weak Optical Appliances"                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Ar               .set(addItem(tLastID = 11003, "Argon Laser Emitter"              , "Purpose: Strong Optical Appliances"              , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Kr               .set(addItem(tLastID = 11004, "Krypton Laser Emitter"            , "Purpose: Science"                                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_Xe               .set(addItem(tLastID = 11005, "Xenon Laser Emitter"              , "Purpose: Science"                                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_HeNe             .set(addItem(tLastID = 11006, "Helium-Neon Laser Emitter"        , "Purpose: Weak Optical Appliances"                , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_CO               .set(addItem(tLastID = 11007, "Carbon Monoxide Laser Emitter"    , "Purpose: Weak Material Processing"               , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));
		IL.Comp_Laser_Gas_CO2              .set(addItem(tLastID = 11008, "Carbon Dioxide Laser Emitter"     , "Purpose: Strong Material Processing"             , TC.stack(TC.LUX, 2), TC.stack(TC.AER, 1), OM.data(IL.Comp_Laser_Gas_Empty.get(1))));

		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.He      .gas(U, T), NF, IL.Comp_Laser_Gas_He.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Ne      .gas(U, T), NF, IL.Comp_Laser_Gas_Ne.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Ar      .gas(U, T), NF, IL.Comp_Laser_Gas_Ar.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Kr      .gas(U, T), NF, IL.Comp_Laser_Gas_Kr.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.Xe      .gas(U, T), NF, IL.Comp_Laser_Gas_Xe.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.HeNe    .gas(U, T), NF, IL.Comp_Laser_Gas_HeNe.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.CO      .gas(U, T), NF, IL.Comp_Laser_Gas_CO.get(1));
		RM.Canner.addRecipe1(T, 16, 128, IL.Comp_Laser_Gas_Empty.get(1), MT.CO2     .gas(U, T), NF, IL.Comp_Laser_Gas_CO2.get(1));
		
		CR.shaped(IL.MOTORS[0].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.bolt     .dat(MT.IronMagnetic)       , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'R', OP.stick.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.MOTORS[0].get(1), CR.DEF       , "CWR", "WIW", "PWC", 'I', OP.bolt     .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'R', OP.stick.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.MOTORS[1].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.IronMagnetic)       , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'R', OP.stick.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.MOTORS[1].get(1), CR.DEF       , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'R', OP.stick.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.MOTORS[2].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'R', OP.stick.dat(MT.DATA.Electric_T[2]), 'W', OP.wireGt02.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[2]);
		CR.shaped(IL.MOTORS[3].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.SteelMagnetic)      , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'R', OP.stick.dat(MT.DATA.Electric_T[3]), 'W', OP.wireGt03.dat(ANY.Cu), 'C', MT.DATA.CABLES_01[3]);
		CR.shaped(IL.MOTORS[4].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'R', OP.stick.dat(MT.DATA.Electric_T[4]), 'W', OP.wireGt04.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[4]);
		CR.shaped(IL.MOTORS[5].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stick    .dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'R', OP.stick.dat(MT.DATA.Electric_T[5]), 'W', OP.wireGt05.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[5]);
		CR.shaped(IL.MOTORS[6].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'R', OP.stick.dat(MT.DATA.Electric_T[6]), 'W', OP.wireGt06.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[6]);
		CR.shaped(IL.MOTORS[7].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'R', OP.stick.dat(MT.DATA.Electric_T[7]), 'W', OP.wireGt07.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[7]);
		CR.shaped(IL.MOTORS[8].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'R', OP.stick.dat(MT.DATA.Electric_T[8]), 'W', OP.wireGt08.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[8]);
		CR.shaped(IL.MOTORS[9].get(1), CR.DEF_REV   , "CWR", "WIW", "PWC", 'I', OP.stickLong.dat(MT.NeodymiumMagnetic)  , 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'R', OP.stick.dat(MT.DATA.Electric_T[9]), 'W', OP.wireGt09.dat(MT.AnnealedCopper), 'C', MT.DATA.CABLES_01[9]);
		
		for (int i = 0; i < 10; i++) {
		CR.shaped(IL.PUMPS      [i].get(1), CR.DEF_REV, "TXO", "dPw", "OMT", 'M', IL.MOTORS[i], 'O', OP.ring.dat(MT.Rubber), 'X', OP.rotor.dat(MT.DATA.Electric_T[i]), 'T', OP.screw.dat(MT.DATA.Electric_T[i]), 'P', OP.plateCurved.dat(MT.DATA.Electric_T[i]));
		CR.shaped(IL.CONVEYERS  [i].get(1), CR.DEF_REV, "RRR", "MCM", "RRR", 'M', IL.MOTORS[i], 'C', MT.DATA.CABLES_01[i], 'R', OP.plate.dat(MT.Rubber));
		CR.shaped(IL.PISTONS    [i].get(1), CR.DEF_REV, "TPP", "dSS", "TMG", 'M', IL.MOTORS[i], 'P', OP.plate.dat(MT.DATA.Electric_T[i]), 'S', OP.stick.dat(MT.DATA.Electric_T[i]), 'G', OP.gearGtSmall.dat(MT.DATA.Electric_T[i]), 'T', OP.screw.dat(MT.DATA.Electric_T[i]));
		CR.shaped(IL.ROBOT_ARMS [i].get(1), CR.DEF_REV, "CCC", "MSM", "PES", 'M', IL.MOTORS[i], 'C', MT.DATA.CABLES_01[i], 'E', OD_CIRCUITS[i], 'S', OP.stick.dat(MT.DATA.Electric_T[i]), 'P', IL.PISTONS[i]);
		}
		
		CR.shaped(IL.FIELD_GENERATORS[0].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderPearl ), 'C', OD_CIRCUITS[0], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[0]), 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[1].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderPearl ), 'C', OD_CIRCUITS[1], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[1]), 'W', OP.wireGt01.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[2].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderEye   ), 'C', OD_CIRCUITS[2], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[2]), 'W', OP.wireGt02.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[3].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.EnderEye   ), 'C', OD_CIRCUITS[3], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[3]), 'W', OP.wireGt04.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[4].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[4], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[4]), 'W', OP.wireGt06.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[5].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[5], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[5]), 'W', OP.wireGt08.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[6].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[6], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[6]), 'W', OP.wireGt10.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[7].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[7], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[7]), 'W', OP.wireGt12.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[8].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[8], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[8]), 'W', OP.wireGt14.dat(MT.Os));
		CR.shaped(IL.FIELD_GENERATORS[9].get(1), CR.DEF_REV, "WPW", "CGC", "WPW", 'G', OP.gem.dat(MT.NetherStar ), 'C', OD_CIRCUITS[9], 'P', OP.plateDouble.dat(MT.DATA.Electric_T[9]), 'W', OP.wireGt16.dat(MT.Os));
		
		CR.shaped(IL.EMITTERS[0].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[0], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'C', OD_CIRCUITS[0], 'W', MT.DATA.CABLES_01[0]);
		CR.shaped(IL.EMITTERS[1].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[1], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'C', OD_CIRCUITS[1], 'W', MT.DATA.CABLES_01[1]);
		CR.shaped(IL.EMITTERS[2].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.SiO2)         , 'S', MT.DATA.WIRES_04[2], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'C', OD_CIRCUITS[2], 'W', MT.DATA.CABLES_01[2]);
		CR.shaped(IL.EMITTERS[3].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(ANY.Emerald)      , 'S', MT.DATA.WIRES_04[3], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'C', OD_CIRCUITS[3], 'W', MT.DATA.CABLES_01[3]);
		CR.shaped(IL.EMITTERS[4].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.EnderPearl)    , 'S', MT.DATA.WIRES_04[4], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'C', OD_CIRCUITS[4], 'W', MT.DATA.CABLES_01[4]);
		CR.shaped(IL.EMITTERS[5].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.EnderEye)      , 'S', MT.DATA.WIRES_04[5], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'C', OD_CIRCUITS[5], 'W', MT.DATA.CABLES_01[5]);
		CR.shaped(IL.EMITTERS[6].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[6], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'C', OD_CIRCUITS[6], 'W', MT.DATA.CABLES_01[6]);
		CR.shaped(IL.EMITTERS[7].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[7], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'C', OD_CIRCUITS[7], 'W', MT.DATA.CABLES_01[7]);
		CR.shaped(IL.EMITTERS[8].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[8], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'C', OD_CIRCUITS[8], 'W', MT.DATA.CABLES_01[8]);
		CR.shaped(IL.EMITTERS[9].get(1), CR.DEF_REV, "SPC", "WQP", "CWS", 'Q', OP.gem.dat(MT.NetherStar)    , 'S', MT.DATA.WIRES_04[9], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'C', OD_CIRCUITS[9], 'W', MT.DATA.CABLES_01[9]);
		
		CR.shaped(IL.SENSORS[0].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[0], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[0]), 'C', OD_CIRCUITS[0]);
		CR.shaped(IL.SENSORS[1].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[1], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[1]), 'C', OD_CIRCUITS[1]);
		CR.shaped(IL.SENSORS[2].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.SiO2)          , 'S', MT.DATA.WIRES_01[2], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[2]), 'C', OD_CIRCUITS[2]);
		CR.shaped(IL.SENSORS[3].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(ANY.Emerald)       , 'S', MT.DATA.WIRES_01[3], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[3]), 'C', OD_CIRCUITS[3]);
		CR.shaped(IL.SENSORS[4].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.EnderPearl)     , 'S', MT.DATA.WIRES_01[4], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[4]), 'C', OD_CIRCUITS[4]);
		CR.shaped(IL.SENSORS[5].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.EnderEye)       , 'S', MT.DATA.WIRES_01[5], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[5]), 'C', OD_CIRCUITS[5]);
		CR.shaped(IL.SENSORS[6].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[6], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[6]), 'C', OD_CIRCUITS[6]);
		CR.shaped(IL.SENSORS[7].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[7], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[7]), 'C', OD_CIRCUITS[7]);
		CR.shaped(IL.SENSORS[8].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[8], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[8]), 'C', OD_CIRCUITS[8]);
		CR.shaped(IL.SENSORS[9].get(1), CR.DEF_REV, "P Q", "PS ", "CPP", 'Q', OP.gem.dat(MT.NetherStar)     , 'S', MT.DATA.WIRES_01[9], 'P', OP.plateCurved.dat(MT.DATA.Electric_T[9]), 'C', OD_CIRCUITS[9]);
		
		
		
		
		IL.Battery_Lead_Acid_Cell_Empty    .set(addItem(tLastID = 20000, "Lead-Acid Cell (Empty)"           , "Battery Part (doesn't require Canning Machine!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(MT.Pb, U, MT.BatteryAlloy, U)));
		IL.Battery_Lead_Acid_Cell_Filled   .set(addItem(tLastID = 20001, "Lead-Acid Cell (Filled)"          , "Battery Part"                                    , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENENUM, 2), new OreDictItemData(MT.Pb, U, MT.BatteryAlloy, U, MT.H2SO4, 2*U), new FluidContainerData(MT.H2SO4.liquid(2*U, T), ST.make(this, 1, 20001), ST.make(this, 1, 20000), F)));
		ItemsGT.addNEIRedirects(IL.Battery_Lead_Acid_Cell_Empty.get(1), IL.Battery_Lead_Acid_Cell_Filled.get(1));
		CR.shaped(IL.Battery_Lead_Acid_Cell_Empty.get(1), CR.DEF_NCC, " Fh", "FPF", "xF ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Pb));

		IL.Battery_Alkaline_Cell_Empty     .set(addItem(tLastID = 20002, "Alkaline Button Cell (Empty)"     , "Battery Part (doesn't require Canning Machine!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(MT.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(ANY.C, U), OM.stack(MT.KOH, U), OM.stack(MT.Zn, U), OM.stack(MT.MnO2, U))));
		IL.Battery_Alkaline_Cell_Filled    .set(addItem(tLastID = 20003, "Alkaline Button Cell (Filled)"    , "Battery Part"                                    , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENENUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(MT.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(ANY.C, U), OM.stack(MT.KOH, U), OM.stack(MT.Zn, U), OM.stack(MT.MnO2, U), OM.stack(MT.H2O, U)), new FluidContainerData(FL.DistW.make(1000), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_Alkaline_Cell_Empty.get(1), IL.Battery_Alkaline_Cell_Filled.get(1));
		CR.shaped(IL.Battery_Alkaline_Cell_Empty.get(1), CR.DEF_NCC, "KSM", "OPF", "CWZ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Al), 'S', OP.plateCurved.dat(MT.StainlessSteel), 'O', OP.ring.dat(MT.Plastic), 'W', OP.wireGt01.dat(ANY.Iron), 'C', OP.dust.dat(ANY.C), 'K', OP.dust.dat(MT.KOH), 'Z', OP.dust.dat(MT.Zn), 'M', OP.dust.dat(MT.MnO2));

		IL.Battery_NiCd_Cell_Empty         .set(addItem(tLastID = 20004, "Nickel-Cadmium Cell (Empty)"      , "Battery Part (doesn't require Canning Machine!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(MT.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.KOH, U), OM.stack(MT.Ni, U), OM.stack(MT.Cd, U))));
		IL.Battery_NiCd_Cell_Filled        .set(addItem(tLastID = 20005, "Nickel-Cadmium Cell (Filled)"     , "Battery Part"                                    , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENENUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Al, U4), OM.stack(MT.StainlessSteel, U), OM.stack(MT.Plastic, U4), OM.stack(ANY.Iron, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.KOH, U), OM.stack(MT.Ni, U), OM.stack(MT.Cd, U), OM.stack(MT.H2O, U)), new FluidContainerData(FL.DistW.make(1000), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_NiCd_Cell_Empty.get(1), IL.Battery_NiCd_Cell_Filled.get(1));
		CR.shaped(IL.Battery_NiCd_Cell_Empty.get(1), CR.DEF_NCC, "KSM", "OPF", "CWZ", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'F', OP.foil.dat(MT.Al), 'S', OP.plateCurved.dat(MT.StainlessSteel), 'O', OP.ring.dat(MT.Plastic), 'W', OP.wireGt01.dat(ANY.Iron), 'C', OP.stick.dat(MT.Graphite), 'K', OP.dust.dat(MT.KOH), 'Z', OP.plateCurved.dat(MT.Ni), 'M', OP.plateCurved.dat(MT.Cd));

		IL.Battery_LiCoO2_Cell_Empty       .set(addItem(tLastID = 20006, "Lithium-Cobalt Cell (Empty)"      , "Battery Part (doesn't require Canning Machine!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Co, U2), OM.stack(MT.Cr, U), OM.stack(MT.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2))));
		IL.Battery_LiCoO2_Cell_Filled      .set(addItem(tLastID = 20007, "Lithium-Cobalt Cell (Filled)"     , "Battery Part"                                    , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENENUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Co, U2), OM.stack(MT.Cr, U), OM.stack(MT.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2), OM.stack(MT.HCl, U*2)), new FluidContainerData(MT.HCl.gas(U*2, T), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_LiCoO2_Cell_Empty.get(1), IL.Battery_LiCoO2_Cell_Filled.get(1));
		CR.shaped(IL.Battery_LiCoO2_Cell_Empty.get(1), CR.DEF_NCC, "CLF", "XSG", "FLP", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'X', OP.stick.dat(MT.Co), 'G', OP.stick.dat(MT.Graphite), 'L', OP.dust.dat(MT.LiClO4), 'S', OP.plateCurved.dat(MT.Cr), 'F', OP.foil.dat(MT.Plastic), 'C', OD_CIRCUITS[4]);

		IL.Battery_LiMn_Cell_Empty         .set(addItem(tLastID = 20008, "Lithium-Manganese Cell (Empty)"   , "Battery Part (doesn't require Canning Machine!)" , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.METALLUM, 2), TC.stack(TC.VACUOS , 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Mn, U2), OM.stack(MT.Cr, U), OM.stack(MT.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2))));
		IL.Battery_LiMn_Cell_Filled        .set(addItem(tLastID = 20009, "Lithium-Manganese Cell (Filled)"  , "Battery Part"                                    , ItemsGT.NEI_DONT_SHOW_FLUIDS, TC.stack(TC.ELECTRUM, 2), TC.stack(TC.VENENUM, 2), new OreDictItemData(OM.stack(MT.BatteryAlloy, U), OM.stack(MT.Mn, U2), OM.stack(MT.Cr, U), OM.stack(MT.Plastic, U2), OM.stack(MT.Graphite, U2), OM.stack(MT.LiClO4, U*2), OM.stack(MT.HF, U*2)), new FluidContainerData(MT.HF.gas(U*2, T), ST.make(this, 1, tLastID), ST.make(this, 1, tLastID-1), F)));
		ItemsGT.addNEIRedirects(IL.Battery_LiMn_Cell_Empty.get(1), IL.Battery_LiMn_Cell_Filled.get(1));
		CR.shaped(IL.Battery_LiMn_Cell_Empty.get(1), CR.DEF_NCC, "CLF", "XSG", "FLP", 'P', OP.plateCurved.dat(MT.BatteryAlloy), 'X', OP.stick.dat(MT.Mn), 'G', OP.stick.dat(MT.Graphite), 'L', OP.dust.dat(MT.LiClO4), 'S', OP.plateCurved.dat(MT.Cr), 'F', OP.foil.dat(MT.Plastic), 'C', OD_CIRCUITS[6]);






		// DEPRECATED START
		addItem(tLastID = 20101, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20102, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20103, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20104, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20105, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20106, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20107, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_LV));
		addItem(tLastID = 20201, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20202, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20203, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20204, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20205, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20206, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20207, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_MV));
		addItem(tLastID = 20301, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20302, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20303, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20304, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20305, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20306, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 20307, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Battery_Lead_Acid_HV));
		addItem(tLastID = 21101, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Crystal_Energium_Red_ULV));
		addItem(tLastID = 21201, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Crystal_Energium_Red_LV));
		addItem(tLastID = 21301, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Crystal_Energium_Red_MV));
		addItem(tLastID = 21401, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Crystal_Energium_Red_HV));
		addItem(tLastID = 21501, "", "", TD.Creative.HIDDEN, new Behavior_Turn_Into(IL.Crystal_Energium_Red_EV));
		// DEPRECATED END



		IL.Electrode_FR_Copper              .set(addItem(tLastID = 29987, "Electrode (Copper)"              , "Needs Glass Tube"                                , new OreDictItemData(ANY.Cu            , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Tin                 .set(addItem(tLastID = 29988, "Electrode (Tin)"                 , "Needs Glass Tube"                                , new OreDictItemData(MT.Sn             , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Bronze              .set(addItem(tLastID = 29989, "Electrode (Bronze)"              , "Needs Glass Tube"                                , new OreDictItemData(MT.Bronze         , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Iron                .set(addItem(tLastID = 29990, "Electrode (Iron)"                , "Needs Glass Tube"                                , new OreDictItemData(ANY.Fe            , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Gold                .set(addItem(tLastID = 29991, "Electrode (Gold)"                , "Needs Glass Tube"                                , new OreDictItemData(MT.Au             , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Diamond             .set(addItem(tLastID = 29992, "Electrode (Diamond)"             , "Needs Glass Tube"                                , new OreDictItemData(ANY.Diamond       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Obsidian            .set(addItem(tLastID = 29993, "Electrode (Obsidian)"            , "Needs Glass Tube"                                , new OreDictItemData(MT.Obsidian       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Blaze               .set(addItem(tLastID = 29994, "Electrode (Blaze)"               , "Needs Glass Tube"                                , new OreDictItemData(MT.Blaze          , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Rubber              .set(addItem(tLastID = 29995, "Electrode (Rubber)"              , "Needs Glass Tube"                                , new OreDictItemData(MT.Rubber         , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Emerald             .set(addItem(tLastID = 29996, "Electrode (Emerald)"             , "Needs Glass Tube"                                , new OreDictItemData(ANY.Emerald       , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Apatite             .set(addItem(tLastID = 29997, "Electrode (Apatite)"             , "Needs Glass Tube"                                , new OreDictItemData(MT.Apatite        , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Lapis               .set(addItem(tLastID = 29998, "Electrode (Lapis)"               , "Needs Glass Tube"                                , new OreDictItemData(MT.Lapis          , 5*U4, MT.Redstone, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Electrode_FR_Ender               .set(addItem(tLastID = 29999, "Electrode (Ender)"               , "Needs Glass Tube"                                , new OreDictItemData(MT.Endstone       , 5*U4, MT.EnderEye, U2), TC.stack(TC.ELECTRUM, 1), MD.FR.mLoaded ? null : TD.Creative.HIDDEN));

		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Copper     .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Sn          , 2), OP.bolt.mat(MT.Sn      , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Tin        .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Bronze      , 2), OP.bolt.mat(MT.Bronze  , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Bronze     .get(1));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Iron       .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Au          , 2), OP.bolt.mat(MT.Au      , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Gold       .get(1));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Diamond    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Obsidian    , 2), OP.bolt.mat(MT.Obsidian, 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Obsidian   .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Blaze       , 2), OP.bolt.mat(MT.Blaze   , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Blaze      .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Rubber      , 2), OP.bolt.mat(MT.Rubber  , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Rubber     .get(1));
		for (OreDictMaterial tMat : ANY.Emerald.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 2), OP.bolt.mat(tMat       , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Emerald    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Apatite     , 2), OP.bolt.mat(MT.Apatite , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Apatite    .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Lapis       , 2), OP.bolt.mat(MT.Lapis   , 2), OP.dustSmall.mat(MT.Redstone, 2)), IL.Electrode_FR_Lapis      .get(1));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone, 5)                             , OP.dustSmall.mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender      .get(1));

		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Copper      .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Sn          , 4), OP.bolt.mat(MT.Sn      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Tin         .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Bronze      , 4), OP.bolt.mat(MT.Bronze  , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Bronze      .get(2));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Iron        .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Au          , 4), OP.bolt.mat(MT.Au      , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Gold        .get(2));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Diamond     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Obsidian    , 4), OP.bolt.mat(MT.Obsidian, 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Obsidian    .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Blaze       , 4), OP.bolt.mat(MT.Blaze   , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Blaze       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Rubber      , 4), OP.bolt.mat(MT.Rubber  , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Rubber      .get(2));
		for (OreDictMaterial tMat : ANY.Emerald.mToThis)
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(tMat           , 4), OP.bolt.mat(tMat       , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Emerald     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Apatite     , 4), OP.bolt.mat(MT.Apatite , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Apatite     .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.stick.mat(MT.Lapis       , 4), OP.bolt.mat(MT.Lapis   , 4), OP.dust     .mat(MT.Redstone, 1)), IL.Electrode_FR_Lapis       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone,10)                             , OP.dust     .mat(MT.EnderEye, 1)), IL.Electrode_FR_Ender       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dustSmall.mat(MT.Endstone,10)                             , OP.gem      .mat(MT.EnderEye, 1)), IL.Electrode_FR_Ender       .get(2));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dust.mat(MT.Endstone , 5)                                 , OP.dust     .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(OP.dust.mat(MT.Endstone , 5)                                 , OP.gem      .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(ST.make(Blocks.end_stone, 5, W)                              , OP.dust     .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));
		RM.Press.addRecipeX(T, 16, 64, ST.array(ST.make(Blocks.end_stone, 5, W)                              , OP.gem      .mat(MT.EnderEye, 2)), IL.Electrode_FR_Ender       .get(4));


		IL.Circuit_Plate_Empty             .set(addItem(tLastID = 30000, "Circuit Plate"                    , "Needs Circuit Wiring"                            , new OreDictItemData(ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1)));

		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
			ItemStack tDust = OP.dust.mat(tMat, 1);
			if (ST.valid(tDust)) RM.Press.addRecipe2(T, F, F, F, T, 16, 64, OP.plate.mat(MT.Plastic, 1), tDust, IL.Circuit_Plate_Empty.get(1));
		}

		IL.Circuit_Wire_Copper             .set(addItem(tLastID = 30001, "Circuit Wiring (Copper)"          , "Needs to be placed on an empty Circuit Plate"    , new OreDictItemData(MT.Cu, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Circuit_Plate_Copper            .set(addItem(tLastID = 30002, "Circuit Plate (Copper)"           , "Needs Circuit Parts"                             , new OreDictItemData(MT.Cu, U, ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Circuit_Wire_Gold               .set(addItem(tLastID = 30003, "Circuit Wiring (Gold)"            , "Needs to be placed on an empty Circuit Plate"    , new OreDictItemData(MT.Au, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.LUCRUM, 1)));
		IL.Circuit_Plate_Gold              .set(addItem(tLastID = 30004, "Circuit Plate (Gold)"             , "Needs Circuit Parts"                             , new OreDictItemData(MT.Au, U, ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.LUCRUM, 1), "oc:materialCircuitBoardPrinted"));
		IL.Circuit_Wire_Platinum           .set(addItem(tLastID = 30005, "Circuit Wiring (Platinum)"        , "Needs to be placed on an empty Circuit Plate"    , new OreDictItemData(MT.Pt, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.NEBRISUM, 1)));
		IL.Circuit_Plate_Platinum          .set(addItem(tLastID = 30006, "Circuit Plate (Platinum)"         , "Needs Circuit Parts"                             , new OreDictItemData(MT.Pt, U, ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.NEBRISUM, 1)));

		IL.Circuit_Wire_Magic              .set(addItem(tLastID = 30011, "Circuit Wiring (Magic)"           , "Needs to be placed on an empty Circuit Plate"    , TC.stack(TC.FABRICO, 1), TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Plate_Magic             .set(addItem(tLastID = 30012, "Circuit Plate (Magic)"            , "Needs Circuit Parts"                             , new OreDictItemData(ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Wire_Enderium           .set(addItem(tLastID = 30013, "Circuit Wiring (Enderium)"        , "Needs to be placed on an empty Circuit Plate"    , new OreDictItemData(MT.Enderium, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Plate_Enderium          .set(addItem(tLastID = 30014, "Circuit Plate (Enderium)"         , "Needs Circuit Parts"                             , new OreDictItemData(MT.Enderium, U, ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Wire_Signalum           .set(addItem(tLastID = 30015, "Circuit Wiring (Signalum)"        , "Needs to be placed on an empty Circuit Plate"    , new OreDictItemData(MT.Signalum, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.POTENTIA, 1)));
		IL.Circuit_Plate_Signalum          .set(addItem(tLastID = 30016, "Circuit Plate (Signalum)"         , "Needs Circuit Parts"                             , new OreDictItemData(MT.Signalum, U, ANY.SiO2, U, MT.Plastic, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.POTENTIA, 1)));

		IL.Circuit_Plate_HSLA              .set(addItem(tLastID = 30099, "Circuit Plate (HSLA)"             , "Needs Circuit Parts"                             , new OreDictItemData(MT.HSLA, U, MT.Au, U), TC.stack(TC.FABRICO, 1), TC.stack(TC.MACHINA, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));

		CR.shaped(IL.Circuit_Wire_Copper        .get(1), CR.DEF, "WWW", "WxW", "WWW", 'W', OP.wireFine.dat(ANY.Cu));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Copper    .get(1), IL.Circuit_Plate_Copper    .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Gold      .get(1), IL.Circuit_Plate_Gold      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Platinum  .get(1), IL.Circuit_Plate_Platinum  .get(1));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Magic     .get(1), IL.Circuit_Plate_Magic     .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Enderium  .get(1), IL.Circuit_Plate_Enderium  .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Empty.get(1), IL.Circuit_Wire_Signalum  .get(1), IL.Circuit_Plate_Signalum  .get(1));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, OP.plate.mat(MT.HSLA, 1), IL.Circuit_Wire_Gold.get(1), IL.Circuit_Plate_HSLA.get(1));

		IL.Circuit_Part_Basic              .set(addItem(tLastID = 30101, "Circuit Part (Basic)"             , "Needs to be placed on a Copper Circuit Plate"    , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Good               .set(addItem(tLastID = 30102, "Circuit Part (Good)"              , "Needs to be placed on a Copper Circuit Plate"    , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Advanced           .set(addItem(tLastID = 30103, "Circuit Part (Advanced)"          , "Needs to be placed on a Gold Circuit Plate"      , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Elite              .set(addItem(tLastID = 30104, "Circuit Part (Elite)"             , "Needs to be placed on a Gold Circuit Plate"      , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Master             .set(addItem(tLastID = 30105, "Circuit Part (Master)"            , "Needs to be placed on a Platinum Circuit Plate"  , TC.stack(TC.COGNITIO, 1)));
		IL.Circuit_Part_Ultimate           .set(addItem(tLastID = 30106, "Circuit Part (Ultimate)"          , "Needs to be placed on a Platinum Circuit Plate"  , TC.stack(TC.COGNITIO, 1)));

		IL.Circuit_Part_Magic              .set(addItem(tLastID = 30111, "Circuit Part (Magic)"             , "Needs to be placed on a Magical Circuit Plate"   , TC.stack(TC.PRAECANTIO, 1)));
		IL.Circuit_Part_Enderium           .set(addItem(tLastID = 30113, "Circuit Part (Enderium)"          , "Needs to be placed on an Enderium Circuit Plate" , TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Part_Signalum           .set(addItem(tLastID = 30115, "Circuit Part (Signalum)"          , "Needs to be placed on a Signalum Circuit Plate"  , TC.stack(TC.POTENTIA, 1)));

		IL.Circuit_Part_EnderPearl         .set(addItem(tLastID = 30198, "Circuit Part (Enderpearl)"        , "Needs to be placed on a Circuit Plate"           , TC.stack(TC.ALIENIS, 1)));
		IL.Circuit_Part_EnderEye           .set(addItem(tLastID = 30199, "Circuit Part (Ender Eye)"         , "Needs to be placed on a Circuit Plate"           , TC.stack(TC.ALIENIS, 1)));

		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_Basic        .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Basic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Cu            , 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.AnnealedCopper, 1), OP.wireFine.mat(MT.RedAlloy, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Good         .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Advanced     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Advanced     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Au            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Elite        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Master       .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Master       .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Pt            , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Ultimate     .get(1));

		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Thaumium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Manasteel     , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Mithril       , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Magic        .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Enderium      , 1), OP.wireFine.mat(MT.Signalum, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Enderium     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_Signalum     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_Signalum     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_Signalum     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.wireFine.mat(MT.Signalum      , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_Signalum     .get(1));

		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_EnderPearl   .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 9), OP.dust    .mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 9)), IL.Circuit_Part_EnderEye     .get(9));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderPearl, 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_EnderPearl   .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Si           , 1)), IL.Circuit_Part_EnderEye     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.Ge           , 1)), IL.Circuit_Part_EnderEye     .get(1));
		RM.Press.addRecipeX(T, F, F, F, T, 16, 16, ST.array(OP.plateGemTiny.mat(MT.EnderEye  , 1), OP.dustTiny.mat(MT.Redstone, 1), OP.plateGemTiny.mat(MT.RedstoneAlloy, 1)), IL.Circuit_Part_EnderEye     .get(1));

		IL.Circuit_Board_Basic             .set(addItem(tLastID = 30201, "Circuit Board (Basic)"            , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Good              .set(addItem(tLastID = 30202, "Circuit Board (Good)"             , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Advanced          .set(addItem(tLastID = 30203, "Circuit Board (Advanced)"         , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Elite             .set(addItem(tLastID = 30204, "Circuit Board (Elite)"            , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Master            .set(addItem(tLastID = 30205, "Circuit Board (Master)"           , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Ultimate          .set(addItem(tLastID = 30206, "Circuit Board (Ultimate)"         , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));

		IL.Circuit_Board_Magic             .set(addItem(tLastID = 30211, "Circuit Board (Magic)"            , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Enderium          .set(addItem(tLastID = 30213, "Circuit Board (Enderium)"         , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));
		IL.Circuit_Board_Signalum          .set(addItem(tLastID = 30215, "Circuit Board (Signalum)"         , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1)));

		IL.Circuit_Board_BC_Redstone       .set(addItem(tLastID = 30280, "Circuit Board (BC Redstone)"      , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_Board_BC_Iron           .set(addItem(tLastID = 30281, "Circuit Board (BC Iron)"          , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Fe, 4*U))));
		IL.Circuit_Board_BC_Gold           .set(addItem(tLastID = 30282, "Circuit Board (BC Gold)"          , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.Au, 4*U))));
		IL.Circuit_Board_BC_Diamond        .set(addItem(tLastID = 30283, "Circuit Board (BC Diamond)"       , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Diamond, 4*U))));
		IL.Circuit_Board_BC_Ender          .set(addItem(tLastID = 30284, "Circuit Board (BC Ender)"         , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.EnderPearl, 4*U))));
		IL.Circuit_Board_BC_Quartz         .set(addItem(tLastID = 30285, "Circuit Board (BC Quartz)"        , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,5*U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_Board_BC_Comparator     .set(addItem(tLastID = 30286, "Circuit Board (BC Comparator)"    , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone,16*U))));
		IL.Circuit_Board_BC_Emerald        .set(addItem(tLastID = 30287, "Circuit Board (BC Emerald)"       , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Emerald, 4*U))));

		IL.Circuit_Board_HSLA_Circuit      .set(addItem(tLastID = 30298, "Circuit Board (HSLA Circuit)"     , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));
		IL.Circuit_Board_Power_Module      .set(addItem(tLastID = 30299, "Circuit Board (Power Module)"     , "Needs to be soldered properly"                   , TC.stack(TC.FABRICO, 1), MD.RoC.mLoaded ? null : TD.Creative.HIDDEN));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Copper      .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Advanced      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Gold        .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Basic      .get(4), IL.Circuit_Board_Basic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Good       .get(4), IL.Circuit_Board_Good          .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Advanced   .get(4), IL.Circuit_Board_Advanced      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Elite      .get(4), IL.Circuit_Board_Elite         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Master     .get(4), IL.Circuit_Board_Master        .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Platinum    .get(1), IL.Circuit_Part_Ultimate   .get(4), IL.Circuit_Board_Ultimate      .get(1));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Magic       .get(1), IL.Circuit_Part_Magic      .get(4), IL.Circuit_Board_Magic         .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Enderium    .get(1), IL.Circuit_Part_Enderium   .get(4), IL.Circuit_Board_Enderium      .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_Signalum    .get(1), IL.Circuit_Part_Signalum   .get(4), IL.Circuit_Board_Signalum      .get(1));

		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_HSLA        .get(1), IL.Circuit_Part_EnderPearl .get(4), IL.Circuit_Board_HSLA_Circuit  .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 64, IL.Circuit_Plate_HSLA        .get(1), IL.Circuit_Part_EnderEye   .get(4), IL.Circuit_Board_Power_Module  .get(1));

		IL.Circuit_Basic                   .set(addItem(tLastID = 30301, "Circuit T1 (Basic)"               , "Computes simple Data very slowly"                , MT.DATA.CIRCUITS[1], OD_CIRCUITS[1], TC.stack(TC.COGNITIO, 2)));
		IL.Circuit_Good                    .set(addItem(tLastID = 30302, "Circuit T2 (Good)"                , "Computes simple Data slowly"                     , MT.DATA.CIRCUITS[2], OD_CIRCUITS[2], TC.stack(TC.COGNITIO, 3)));
		IL.Circuit_Advanced                .set(addItem(tLastID = 30303, "Circuit T3 (Advanced)"            , "Computes simple Data with average speed"         , MT.DATA.CIRCUITS[3], OD_CIRCUITS[3], TC.stack(TC.COGNITIO, 4)));
		IL.Circuit_Elite                   .set(addItem(tLastID = 30304, "Circuit T4 (Elite)"               , "Computes simple Data with improved speed"        , MT.DATA.CIRCUITS[4], OD_CIRCUITS[4], TC.stack(TC.COGNITIO, 5)));
		IL.Circuit_Master                  .set(addItem(tLastID = 30305, "Circuit T5 (Master)"              , "Computes simple Data efficiently"                , MT.DATA.CIRCUITS[5], OD_CIRCUITS[5], TC.stack(TC.COGNITIO, 6)));
		IL.Circuit_Ultimate                .set(addItem(tLastID = 30306, "Circuit T6 (Ultimate)"            , "Computes simple Data very efficiently"           , MT.DATA.CIRCUITS[6], OD_CIRCUITS[6], TC.stack(TC.COGNITIO, 7)));

		IL.Circuit_Magic                   .set(addItem(tLastID = 30311, "Circuit (Magic)"                  , "Computes simple Data magically"                  , OP.circuit.dat(MT.Magic)));
		IL.Circuit_Enderium                .set(addItem(tLastID = 30313, "Circuit (Enderium)"               , "Computes simple Data somewhere else"             , OP.circuit.dat(MT.Enderium)));
		IL.Circuit_Signalum                .set(addItem(tLastID = 30315, "Circuit (Signalum)"               , "Computes simple Logic"                           , OP.circuit.dat(MT.Signalum)));

		IL.Circuit_BC_Redstone             .set(addItem(tLastID = 30380, "Circuit (BC Redstone)"            , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 1), TC.stack(TC.POTENTIA, 3), TC.stack(TC.MACHINA, 1)     , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_BC_Iron                 .set(addItem(tLastID = 30381, "Circuit (BC Iron)"                , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.METALLUM, 4)                              , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Fe, 4*U))));
		IL.Circuit_BC_Gold                 .set(addItem(tLastID = 30382, "Circuit (BC Gold)"                , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.METALLUM, 2)      , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.Au, 4*U))));
		IL.Circuit_BC_Diamond              .set(addItem(tLastID = 30383, "Circuit (BC Diamond)"             , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2)       , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Diamond, 4*U))));
		IL.Circuit_BC_Ender                .set(addItem(tLastID = 30384, "Circuit (BC Ender)"               , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ALIENIS, 4)                               , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(MT.EnderPearl, 4*U))));
		IL.Circuit_BC_Quartz               .set(addItem(tLastID = 30385, "Circuit (BC Quartz)"              , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.POTENTIA, 2), TC.stack(TC.VITREUS, 2)     , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,5*U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U))));
		IL.Circuit_BC_Comparator           .set(addItem(tLastID = 30386, "Circuit (BC Comparator)"          , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TC.stack(TC.MACHINA, 4)                               , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone,16*U))));
		IL.Circuit_BC_Emerald              .set(addItem(tLastID = 30387, "Circuit (BC Emerald)"             , "Made for tweaking Recipes"                       , MD.BC_SILICON.mLoaded ? null : TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2)       , new OreDictItemData(OM.stack(MT.Signalum, U), OM.stack(ANY.SiO2,  U), OM.stack(MT.Plastic, U), OM.stack(MT.Redstone, 4*U), OM.stack(ANY.Emerald, 4*U))));

		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Basic         .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Good          .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Basic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Advanced      .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Good          .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Elite         .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Advanced      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Master        .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Master        .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Elite         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Master        .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Ultimate      .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Ultimate      .get(1));

		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Magic         .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Magic         .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Enderium      .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Enderium      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.Pb              .liquid(U, T), NF, IL.Circuit_Signalum      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.Sn              .liquid(U, T), NF, IL.Circuit_Signalum      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_Signalum      .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_Signalum      .get(1));

		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Redstone   .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Redstone   .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Iron       .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Iron       .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Gold       .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Gold       .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Diamond    .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Diamond    .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Ender      .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Ender      .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Quartz     .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Quartz     .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Comparator .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Comparator .get(1));
		RM.Bath.addRecipe1(T, F, F, F, T, 0, 64, IL.Circuit_Board_BC_Emerald    .get(1), MT.SolderingAlloy  .liquid(U, T), NF, IL.Circuit_BC_Emerald    .get(1));

		IL.Circuit_Crystal_Diamond         .set(addItem(tLastID = 30401, "Crystal Circuit (Diamond)"        , "Logic Diamond"                                   , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Diamond, U))));
		IL.Circuit_Crystal_Ruby            .set(addItem(tLastID = 30402, "Crystal Circuit (Ruby)"           , "Control Ruby"                                    , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(MT.Ruby, U))));
		IL.Circuit_Crystal_Emerald         .set(addItem(tLastID = 30403, "Crystal Circuit (Emerald)"        , "Storage Emerald"                                 , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Emerald, U))));
		IL.Circuit_Crystal_Sapphire        .set(addItem(tLastID = 30404, "Crystal Circuit (Sapphire)"       , "Conversion Sapphire"                             , TC.stack(TC.COGNITIO, 3), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(OM.stack(ANY.Sapphire, U))));

		IL.Processor_Crystal_Empty         .set(addItem(tLastID = 30500, "Crystal Processor Socket"         , "Base for Crystal Circuits"                       , TC.stack(TC.COGNITIO, 5), TC.stack(TC.VACUOS, 2)));
		IL.Processor_Crystal_Diamond       .set(addItem(tLastID = 30501, "Crystal Processor (Diamond)"      , "Logic Processor Circuit"                         , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Diamond, U)));
		IL.Processor_Crystal_Ruby          .set(addItem(tLastID = 30502, "Crystal Processor (Ruby)"         , "Control Processor Circuit"                       , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, MT.Ruby, U)));
		IL.Processor_Crystal_Emerald       .set(addItem(tLastID = 30503, "Crystal Processor (Emerald)"      , "Storage Processor Circuit"                       , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Emerald, U)));
		IL.Processor_Crystal_Sapphire      .set(addItem(tLastID = 30504, "Crystal Processor (Sapphire)"     , "Conversion Processor Circuit"                    , TC.stack(TC.COGNITIO, 5), TC.stack(TC.LUCRUM, 2), TC.stack(TC.VITREUS, 2), new OreDictItemData(MT.Pt, U, ANY.Sapphire, U)));

		CR.shaped(IL.Processor_Crystal_Empty.get(1), CR.DEF_REV, "CLC", "LBL", "CLC", 'C', OD_CIRCUITS[6], 'B', IL.Circuit_Plate_Platinum, 'L', IL.Comp_Laser_Gas_HeNe);

		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Diamond .get(1), IL.Processor_Crystal_Diamond .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Ruby    .get(1), IL.Processor_Crystal_Ruby    .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Emerald .get(1), IL.Processor_Crystal_Emerald .get(1));
		RM.Press.addRecipe2(T, F, F, F, T, 16, 16, IL.Processor_Crystal_Empty.get(1), IL.Circuit_Crystal_Sapphire.get(1), IL.Processor_Crystal_Sapphire.get(1));
		
		
		CR.shaped(IL.Cover_Logistics_Display_CPU_Logic     .get(1), CR.DEF_REV, "dL ", " Q ", " C ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Control   .get(1), CR.DEF_REV, " Ld", " Q ", " C ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Storage   .get(1), CR.DEF_REV, " L ", " Q ", "dC ", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		CR.shaped(IL.Cover_Logistics_Display_CPU_Conversion.get(1), CR.DEF_REV, " L ", " Q ", " Cd", 'Q', IL.Cover_Blank, 'C', OD_CIRCUITS[2], 'L', OP.wireGt01.dat(MT.Lumium));
		
		CR.shaped(IL.Cover_Logistics_Fluid_Export          .get(1), CR.DEF_REV, "  w", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Fluid_Import          .get(1), CR.DEF_REV, " w ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Fluid_Storage         .get(1), CR.DEF_REV, "w  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Export           .get(1), CR.DEF_REV, "  r", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Import           .get(1), CR.DEF_REV, " r ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Item_Storage          .get(1), CR.DEF_REV, "r  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Export        .get(1), CR.DEF_REV, "  d", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Import        .get(1), CR.DEF_REV, " d ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Generic_Storage       .get(1), CR.DEF_REV, "d  ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		CR.shaped(IL.Cover_Logistics_Dump                  .get(1), CR.DEF_REV, "   ", "WQW", "CPC", 'Q', IL.Cover_Blank, 'P', IL.Processor_Crystal_Emerald, 'C', OD_CIRCUITS[4], 'W', OP.wireFine.dat(MT.Os));
		
		
		
		IL.USB_Stick_1                     .set(addItem(tLastID = 32001, "USB 1.0 Stick"                    , "Stores Data"                                     , OD_USB_STICKS[1], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 1)));
		IL.USB_Stick_2                     .set(addItem(tLastID = 32002, "USB 2.0 Stick"                    , "Stores Data"                                     , OD_USB_STICKS[2], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.MOTUS, 1)));
		IL.USB_Stick_3                     .set(addItem(tLastID = 32003, "USB 3.0 Stick"                    , "Stores Data"                                     , OD_USB_STICKS[3], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.MOTUS, 2)));
		IL.USB_Stick_4                     .set(addItem(tLastID = 32004, "USB 4.0 Stick"                    , "Stores Data"                                     , OD_USB_STICKS[4], Behavior_DataStorage.INSTANCE, TC.stack(TC.COGNITIO, 6), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.MOTUS, 3)));

		CR.shaped(IL.USB_Stick_1.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[3], 'W', MT.DATA.WIRES_01[3], 'P', OP.plate.dat(MT.Al                ), 'T', OP.screw.dat(MT.Al              ));
		CR.shaped(IL.USB_Stick_2.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[4], 'W', MT.DATA.WIRES_01[4], 'P', OP.plate.dat(MT.StainlessSteel    ), 'T', OP.screw.dat(MT.StainlessSteel  ));
		CR.shaped(IL.USB_Stick_3.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[5], 'W', MT.DATA.WIRES_01[5], 'P', OP.plate.dat(MT.Cr                ), 'T', OP.screw.dat(MT.Cr              ));
		CR.shaped(IL.USB_Stick_4.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', OD_CIRCUITS[6], 'W', MT.DATA.WIRES_01[6], 'P', OP.plate.dat(MT.Ti                ), 'T', OP.screw.dat(MT.Ti              ));


		IL.USB_Cable_1                     .set(addItem(tLastID = 32011, "USB 1.0 Cable"                    , "Replaces USB Sticks when connected to USB Ports" , OD_USB_CABLES[1], TC.stack(TC.COGNITIO, 2), TC.stack(TC.ELECTRUM, 1)));
		IL.USB_Cable_2                     .set(addItem(tLastID = 32012, "USB 2.0 Cable"                    , "Replaces USB Sticks when connected to USB Ports" , OD_USB_CABLES[2], TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.ITER, 1)));
		IL.USB_Cable_3                     .set(addItem(tLastID = 32013, "USB 3.0 Cable"                    , "Replaces USB Sticks when connected to USB Ports" , OD_USB_CABLES[3], TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.ITER, 2)));
		IL.USB_Cable_4                     .set(addItem(tLastID = 32014, "USB 4.0 Cable"                    , "Replaces USB Sticks when connected to USB Ports" , OD_USB_CABLES[4], TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.ITER, 3)));

		CR.shaped(IL.USB_Cable_1.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[3], 'W', MT.DATA.WIRES_01[3], 'P', OP.plate.dat(MT.Al              ), 'T', OP.screw.dat(MT.Al              ));
		CR.shaped(IL.USB_Cable_2.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[4], 'W', MT.DATA.WIRES_01[4], 'P', OP.plate.dat(MT.StainlessSteel  ), 'T', OP.screw.dat(MT.StainlessSteel  ));
		CR.shaped(IL.USB_Cable_3.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[5], 'W', MT.DATA.WIRES_01[5], 'P', OP.plate.dat(MT.Cr              ), 'T', OP.screw.dat(MT.Cr              ));
		CR.shaped(IL.USB_Cable_4.get(1), CR.DEF_REV, "xWd", "PCP", "TCT", 'C', MT.DATA.CABLES_01[6], 'W', MT.DATA.WIRES_01[6], 'P', OP.plate.dat(MT.Ti              ), 'T', OP.screw.dat(MT.Ti              ));


		IL.USB_HDD_1                       .set(addItem(tLastID = 32021, "USB 1.0 HDD"                      , "Stores up to 16 Files at once"                   , OD_USB_DRIVES[1], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 3), TC.stack(TC.ELECTRUM, 2), TC.stack(TC.MOTUS, 1)));
		IL.USB_HDD_2                       .set(addItem(tLastID = 32022, "USB 2.0 HDD"                      , "Stores up to 16 Files at once"                   , OD_USB_DRIVES[2], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 4), TC.stack(TC.ELECTRUM, 3), TC.stack(TC.MOTUS, 2)));
		IL.USB_HDD_3                       .set(addItem(tLastID = 32023, "USB 3.0 HDD"                      , "Stores up to 16 Files at once"                   , OD_USB_DRIVES[3], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 5), TC.stack(TC.ELECTRUM, 4), TC.stack(TC.MOTUS, 3)));
		IL.USB_HDD_4                       .set(addItem(tLastID = 32024, "USB 4.0 HDD"                      , "Stores up to 16 Files at once"                   , OD_USB_DRIVES[4], Behavior_DataStorage16.INSTANCE, TC.stack(TC.COGNITIO, 6), TC.stack(TC.ELECTRUM, 5), TC.stack(TC.MOTUS, 4)));

		CR.shaped(IL.USB_HDD_1.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[3], 'W', IL.USB_Cable_1, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Al             ), 'T', OP.screw.dat(MT.Al              )); // TODO: Replace record with a CD (made of aluminium foils and plastic plates in a Press)
		CR.shaped(IL.USB_HDD_2.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[4], 'W', IL.USB_Cable_2, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.StainlessSteel ), 'T', OP.screw.dat(MT.StainlessSteel  ));
		CR.shaped(IL.USB_HDD_3.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[5], 'W', IL.USB_Cable_3, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Cr             ), 'T', OP.screw.dat(MT.Cr              ));
		CR.shaped(IL.USB_HDD_4.get(1), CR.DEF_REV, "PLT", "dRW", "TCP", 'C', OD_CIRCUITS[6], 'W', IL.USB_Cable_4, 'L', IL.Comp_Laser_Gas_He, 'R', OD.record, 'P', OP.plate.dat(MT.Ti             ), 'T', OP.screw.dat(MT.Ti              ));

		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.USB_HDD_1.get(1)), (byte)54);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.USB_HDD_2.get(1)), (byte)54);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.USB_HDD_3.get(1)), (byte)54);
		BooksGT.BOOK_REGISTER.put(new ItemStackContainer(IL.USB_HDD_4.get(1)), (byte)54);
	}
}
