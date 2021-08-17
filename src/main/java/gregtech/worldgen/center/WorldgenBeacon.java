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

import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenBeacon extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenBeacon(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = getConfigFile().get(mCategory, "Height", WD.waterLevel()+4);
		GENERATE_BEACON = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_BEACON && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if ((aMinX != -16 && aMinX != 0) || (aMinZ == -16 && aMinZ == 0)) return F;
		if (!GENERATE_STREETS) {
			for (int i = -5; i < 5; i++) for (int j = -5; j < 5; j++) WD.set(aWorld, i, mHeight+1, j, Blocks.iron_block, 0, 0);
			for (int i = -4; i < 4; i++) for (int j = -4; j < 4; j++) WD.set(aWorld, i, mHeight+2, j, Blocks.iron_block, 0, 0);
			for (int i = -3; i < 3; i++) for (int j = -3; j < 3; j++) WD.set(aWorld, i, mHeight+3, j, Blocks.iron_block, 0, 0);
			for (int i = -2; i < 2; i++) for (int j = -2; j < 2; j++) WD.set(aWorld, i, mHeight+4, j, Blocks.iron_block, 0, 0);
			
			TileEntity tTileEntity;
			
			WD.set(aWorld, -1, mHeight+5, -1, Blocks.beacon, 0, 3);
			tTileEntity = WD.te(aWorld, -1, mHeight+5, -1, T);
			if (tTileEntity instanceof TileEntityBeacon) {
				NBTTagCompound tNBT = UT.NBT.make();
				tTileEntity.writeToNBT(tNBT);
				tNBT.setInteger("Primary", Potion.moveSpeed.id);
				tNBT.setInteger("Secondary", Potion.moveSpeed.id);
				tNBT.setInteger("Levels", 4);
				tTileEntity.readFromNBT(tNBT);
			}
			
			WD.set(aWorld, -1, mHeight+5, 0, Blocks.beacon, 0, 3);
			tTileEntity = WD.te(aWorld, -1, mHeight+5, 0, T);
			if (tTileEntity instanceof TileEntityBeacon) {
				NBTTagCompound tNBT = UT.NBT.make();
				tTileEntity.writeToNBT(tNBT);
				tNBT.setInteger("Primary", Potion.digSpeed.id);
				tNBT.setInteger("Secondary", Potion.digSpeed.id);
				tNBT.setInteger("Levels", 4);
				tTileEntity.readFromNBT(tNBT);
			}
			
			WD.set(aWorld, 0, mHeight+5, -1, Blocks.beacon, 0, 3);
			tTileEntity = WD.te(aWorld, 0, mHeight+5, -1, T);
			if (tTileEntity instanceof TileEntityBeacon) {
				NBTTagCompound tNBT = UT.NBT.make();
				tTileEntity.writeToNBT(tNBT);
				tNBT.setInteger("Primary", Potion.damageBoost.id);
				tNBT.setInteger("Secondary", Potion.damageBoost.id);
				tNBT.setInteger("Levels", 4);
				tTileEntity.readFromNBT(tNBT);
			}
			
			WD.set(aWorld, 0, mHeight+5, 0, Blocks.beacon, 0, 3);
			tTileEntity = WD.te(aWorld, 0, mHeight+5, 0, T);
			if (tTileEntity instanceof TileEntityBeacon) {
				NBTTagCompound tNBT = UT.NBT.make();
				tTileEntity.writeToNBT(tNBT);
				tNBT.setInteger("Primary", Potion.resistance.id);
				tNBT.setInteger("Secondary", Potion.regeneration.id);
				tNBT.setInteger("Levels", 4);
				tTileEntity.readFromNBT(tNBT);
			}
		}
		
		aWorld.setSpawnLocation(0, mHeight+5, 0);
		return T;
	}
}
