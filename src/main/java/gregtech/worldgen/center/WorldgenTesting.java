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

package gregtech.worldgen.center;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.metatype.BlockMetaType;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.tileentity.base.TileEntityBase06Covers;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTesting extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenTesting(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", WD.waterLevel()+4);
		GENERATE_TESTING = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_TESTING && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if ((aMinX != 32 && aMinX != 48) || (aMinZ != -32 && aMinZ != -48)) return F;
		
		for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
			for (int k = 1; k <= mHeight; k++) WD.set(aChunk, i, k, j, BlocksGT.Concrete, DYE_INDEX_Gray);
			for (int k = mHeight+2; k < 256; k++) WD.set(aChunk, i, k, j, NB, 0);
			
			WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.CFoam, DYE_INDEX_Gray);
			if ((i == 0 && aMinX == 32) || (i == 15 && aMinX == 48) || (j == 0 && aMinZ == -48) || (j == 15 && aMinZ == -32)) {
			WD.set(aChunk, i, mHeight+ 2, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 3, j, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, i, mHeight+ 4, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 5, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 6, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 7, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 8, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+10, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+11, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+12, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+13, j, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, i, mHeight+14, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+15, j, BlocksGT.CFoam, DYE_INDEX_Gray);
			} else if ((i != 1 && i != 5 && i != 10 && i != 14) && (j != 1 && j != 5 && j != 10 && j != 14)) {
			WD.set(aChunk, i, mHeight+15, j, ((BlockMetaType)BlocksGT.GlowGlass).mSlabs[1], DYE_INDEX_LightBlue);
			} else {
			WD.set(aChunk, i, mHeight+15, j, ((BlockMetaType)BlocksGT.CFoam).mSlabs[1], DYE_INDEX_LightGray);
			}
		}
		
		if (aMinX == 32 && aMinZ == -32) {
			WD.set(aChunk, 0, mHeight+ 2, 5, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 2, 6, NB, 0);
			WD.set(aChunk, 0, mHeight+ 2, 7, NB, 0);
			WD.set(aChunk, 0, mHeight+ 2, 8, NB, 0);
			WD.set(aChunk, 0, mHeight+ 2, 9, NB, 0);
			WD.set(aChunk, 0, mHeight+ 2,10, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 2, 6, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 2, 9, BlocksGT.CFoam, DYE_INDEX_Gray);
			
			WD.set(aChunk, 0, mHeight+ 3, 5, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, 0, mHeight+ 3, 6, NB, 0);
			WD.set(aChunk, 0, mHeight+ 3, 7, NB, 0);
			WD.set(aChunk, 0, mHeight+ 3, 8, NB, 0);
			WD.set(aChunk, 0, mHeight+ 3, 9, NB, 0);
			WD.set(aChunk, 0, mHeight+ 3,10, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, 1, mHeight+ 3, 6, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, 1, mHeight+ 3, 9, BlocksGT.CFoam, DYE_INDEX_Yellow);
			
			WD.set(aChunk, 0, mHeight+ 4, 5, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 4, 6, NB, 0);
			WD.set(aChunk, 0, mHeight+ 4, 7, NB, 0);
			WD.set(aChunk, 0, mHeight+ 4, 8, NB, 0);
			WD.set(aChunk, 0, mHeight+ 4, 9, NB, 0);
			WD.set(aChunk, 0, mHeight+ 4,10, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 4, 6, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 4, 7, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 4, 8, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 1, mHeight+ 4, 9, BlocksGT.CFoam, DYE_INDEX_Gray);
			
			WD.set(aChunk, 0, mHeight+ 5, 5, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 5, 6, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 5, 7, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 5, 8, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 5, 9, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, 0, mHeight+ 5,10, BlocksGT.CFoam, DYE_INDEX_Gray);
			
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
			
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+2, -20, SIDE_UNKNOWN, (short) 7133, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+2, -21, SIDE_UNKNOWN, (short) 7133, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+2, -22, SIDE_UNKNOWN, (short) 7133, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+3, -20, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+3, -21, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+3, -22, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+4, -20, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+4, -21, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+4, -22, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+5, -20, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+5, -21, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+5, -22, SIDE_UNKNOWN, (short) 6033, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+5, -23, SIDE_UNKNOWN, (short)14999, UT.NBT.make(NBT_ACTIVE_ENERGY, T), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+5, -24, SIDE_UNKNOWN, (short)14999, UT.NBT.make(NBT_ACTIVE_ENERGY, F), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+2, -19, SIDE_UNKNOWN, (short)32057, null, T, T);
			
			WD.set                     (aWorld, 33, mHeight+2, -18, Blocks.crafting_table, 0, 3);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+3, -18, SIDE_UNKNOWN, (short)32737, null, T, T);
			tRegistry.mBlock.placeBlock(aWorld, 33, mHeight+4, -18, SIDE_UNKNOWN, (short)32727, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 34, mHeight+2, -18, SIDE_UNKNOWN, (short) 5033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 34, mHeight+3, -18, SIDE_UNKNOWN, (short)32739, null, T, T);
			tRegistry.mBlock.placeBlock(aWorld, 34, mHeight+4, -18, SIDE_UNKNOWN, (short)32062, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight+3, -18, SIDE_UNKNOWN, (short)32719, null, T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight+2, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight+1, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight  , -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight-1, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight-2, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight-3, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_D)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight-4, -18, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, (byte)(SBIT_U | SBIT_S)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight-4, -17, SIDE_UNKNOWN, (short)26304, UT.NBT.make(NBT_CONNECTION, SBIT_N), T, T);
			TileEntity tTileEntity = WD.te(aWorld, 36, mHeight-4, -17, T);
			if (tTileEntity instanceof TileEntityBase06Covers) ((TileEntityBase06Covers)tTileEntity).setCoverItem(SIDE_Z_POS, IL.Cover_Drain.get(1), null, T, F);
			
			tRegistry.mBlock.placeBlock(aWorld, 35, mHeight+2, -18, SIDE_UNKNOWN, (short)32705, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 35, mHeight+3, -18, SIDE_UNKNOWN, (short)32732, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 35, mHeight+4, -18, SIDE_UNKNOWN, (short)32750, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
			
			WD.set                     (aWorld, 36, mHeight+2, -19, Blocks.cauldron, 0, 3);
			tRegistry.mBlock.placeBlock(aWorld, 36, mHeight+3, -19, SIDE_UNKNOWN, (short)32732, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 37, mHeight+2, -18, SIDE_UNKNOWN, (short)32707, UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 37, mHeight+3, -18, SIDE_UNKNOWN, (short)32732, UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
			
			WD.set                     (aWorld, 38, mHeight+2, -18, Blocks.crafting_table, 0, 3);
			tRegistry.mBlock.placeBlock(aWorld, 38, mHeight+3, -18, SIDE_UNKNOWN, (short)32744, null, T, T);
			
			// Lots of Items I want to have ready whenever I generate a new Test World.
			ItemStack[] tInventory = {
			  ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SWORD                 , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE               , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SHOVEL                , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.AXE                   , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HOE                   , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SAW                   , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HARDHAMMER            , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SOFTHAMMER            , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH                , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MONKEY_WRENCH         , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FILE                  , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CROWBAR               , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SCREWDRIVER           , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB                  , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WIRECUTTER            , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SCOOP                 , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BRANCHCUTTER          , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.UNIVERSALSPADE        , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.KNIFE                 , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BUTCHERYKNIFE         , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PLOW                  , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.ROLLING_PIN           , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHISEL                , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER      , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BENDING_CYLINDER      , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BENDING_CYLINDER_SMALL, MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.DOUBLE_AXE            , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CONSTRUCTION_PICK     , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SCISSORS              , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PINCERS               , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SPADE                 , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HAND_DRILL            , MT.NetherizedDiamond, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SENSE                 , MT.NetherizedDiamond, MT.LightBlue)
			, NI//ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SICKLE            , MT.NetherizedDiamond, MT.LightBlue)
			, NI
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BUILDERWAND           , MT.Heliodor, MT.WOODS.Scorched)
			
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.POCKET_MULTITOOL      , MT.Vibramantium, MT.Vibramantium)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PLUNGER               , MT.Greatwood, MT.Greatwood)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MAGNIFYING_GLASS      , MT.DiamondPink, MT.LightBlue)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.GEM_PICK              , MT.DiamondPink, MT.LightBlue)
			, ST.make(Items.wooden_pickaxe , 1, 0)
			, ST.make(Items.stone_pickaxe  , 1, 0)
			, ST.make(Items.golden_pickaxe , 1, 0)
			, ST.make(Items.iron_pickaxe   , 1, 0)
			, ST.make(Items.diamond_pickaxe, 1, 0)
			
			, IL.Thermometer_Quicksilver.get(1)
			, IL.Tool_Worldgen_Debugger.get(1)
			, IL.Tool_Cheat.get(1)
			, IL.IC2_Debug.get(1)
			, IL.Pill_Cure_All.get(64)
			, IL.Brain_Tape.get(64)
			, IL.Circuit_Selector.getWithDamage(1, 0)
			, IL.Circuit_Selector.getWithDamage(1, 1)
			, IL.Circuit_Selector.getWithDamage(1, 2)
			
			, IL.TC_Thaumometer.get(1)
			, IL.TC_Crimson_Rites.get(1)
			, IL.TC_Thaumonomicon.get(1)
			, ST.make(MD.TC, "ItemThaumonomicon", 1, 42)
			, ST.make(Items.item_frame, 1, 0)
			, ST.make(Items.bucket, 1, 0)
			, ST.make(Items.water_bucket, 1, 0)
			, ST.make(Items.lava_bucket, 1, 0)
			, ST.make(Items.milk_bucket, 1, 0)
			
			, ST.make(Blocks.stone_button, 1, 0)
			, ST.make(Blocks.lever, 1, 0)
			, ST.make(Items.redstone, 1, 0)
			, ST.make(Blocks.redstone_torch, 1, 0)
			, ST.make(Items.repeater, 1, 0)
			, ST.make(Items.comparator, 1, 0)
			, ST.make(Blocks.redstone_lamp, 1, 0)
			, OP.cableGt01.mat(MT.Signalum, 1)
			, OP.wireGt01.mat(MT.Lumium, 1)
			
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH_LV     , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MININGDRILL_LV, 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHAINSAW_LV   , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, NI
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MIXER_LV      , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.DRILL_LV      , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SCREWDRIVER_LV, 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.BUZZSAW_LV    , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.TRIMMER_LV    , 1, MT.NetherizedDiamond, MT.Magenta, V[1] * 1000, V[1], Long.MAX_VALUE)
			
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH_MV     , 1, MT.NetherizedDiamond, MT.Magenta, V[2] * 1000, V[2], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MININGDRILL_MV, 1, MT.NetherizedDiamond, MT.Magenta, V[2] * 1000, V[2], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHAINSAW_MV   , 1, MT.NetherizedDiamond, MT.Magenta, V[2] * 1000, V[2], Long.MAX_VALUE)
			, NI
			, NI
			, NI
			, NI
			, NI
			, NI
			
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.WRENCH_HV     , 1, MT.NetherizedDiamond, MT.Magenta, V[3] * 1000, V[3], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.MININGDRILL_HV, 1, MT.NetherizedDiamond, MT.Magenta, V[3] * 1000, V[3], Long.MAX_VALUE)
			, ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CHAINSAW_HV   , 1, MT.NetherizedDiamond, MT.Magenta, V[3] * 1000, V[3], Long.MAX_VALUE)
			, NI
			, NI
			, NI
			, NI
			, NI
			, NI
			
			, IL.Battery_Lead_Acid_LV.getWithCharge(1, Long.MAX_VALUE)
			, IL.Battery_Lead_Acid_MV.getWithCharge(1, Long.MAX_VALUE)
			, IL.Battery_Lead_Acid_HV.getWithCharge(1, Long.MAX_VALUE)
			, OP.plateCurved.mat(MT.SteelGalvanized, 64)
			, OP.plateCurved.mat(MT.Al, 64)
			, OP.plateCurved.mat(MT.StainlessSteel, 64)
			, IL.MOTORS[1].get(64)
			, IL.MOTORS[2].get(64)
			, IL.MOTORS[3].get(64)
			};
			
			tRegistry.mBlock.placeBlock(aWorld, 39, mHeight+2, -18, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, UT.NBT.makeInv(tInventory)), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 39, mHeight+3, -18, SIDE_UNKNOWN, (short)32722, null, T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 40, mHeight+2, -18, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 40, mHeight+3, -18, SIDE_UNKNOWN, (short)32735, null, T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 41, mHeight+2, -18, SIDE_UNKNOWN, (short) 5033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
			
			
			tRegistry.mBlock.placeBlock(aWorld, 42, mHeight+2, -18, SIDE_UNKNOWN, (short)32703, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_STATE, 4), T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 43, mHeight+2, -18, SIDE_UNKNOWN, (short)32048, null, T, T);
			
			tRegistry.mBlock.placeBlock(aWorld, 44, mHeight+1, -20, SIDE_UNKNOWN, (short)32709, null, T, T);
			tRegistry.mBlock.placeBlock(aWorld, 44, mHeight+1, -19, SIDE_UNKNOWN, (short) 8033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 44, mHeight+2, -20, SIDE_UNKNOWN, (short)32711, UT.NBT.make(NBT_FACING, SIDE_Y_POS, NBT_MODE, T), T, T);
			tRegistry.mBlock.placeBlock(aWorld, 44, mHeight+2, -19, SIDE_UNKNOWN, (short)32702, null, T, T);
			tRegistry.mBlock.placeBlock(aWorld, 44, mHeight+2, -18, SIDE_UNKNOWN, (short) 8033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
			
			WD.set                     (aWorld, 45, mHeight+2, -18, Blocks.ender_chest, 2, 3);
			
			WD.set                     (aWorld, 46, mHeight+2, -18, Blocks.crafting_table, 0, 3);
			WD.set                     (aWorld, 46, mHeight+3, -18, Blocks.brewing_stand, 0, 3);
			
			WD.set                     (aWorld, 47, mHeight+2, -18, Blocks.anvil, 0, 3);
		}
		
		aWorld.setSpawnLocation(0, mHeight+5, 0);
		return T;
	}
}
