/**
 * Copyright (c) 2019 Gregorius Techneticies
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
import gregapi.data.IL;
import gregapi.tileentity.base.TileEntityBase06Covers;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.init.Blocks;
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
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", mHeight);
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
			
			tRegistry.mBlock.placeBlock(aWorld, 39, mHeight+2, -18, SIDE_UNKNOWN, (short) 4033, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
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
