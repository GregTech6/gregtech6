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

package gregapi.dummies;

import static gregapi.data.CS.*;

import java.io.File;
import java.util.Random;

import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class DummyWorld extends World {
	public class GT_IteratorRandom extends Random {
		private static final long serialVersionUID = 1L;
		
		public int mIterationStep = Integer.MAX_VALUE;
		
		@Override public int nextInt(int aParameter) {
			if (mIterationStep == 0 || mIterationStep > aParameter) {
				mIterationStep = aParameter;
			}
			return --mIterationStep;
		}
	}
	
	public GT_IteratorRandom mRandom = new GT_IteratorRandom();
	public ItemStack mLastSetBlock = null;
	
	public DummyWorld(ISaveHandler par1iSaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler) {
		super(par1iSaveHandler, par2Str, par4WorldSettings, par3WorldProvider, par5Profiler);
		rand = mRandom;
	}
	
	public DummyWorld() {
		this(
		new ISaveHandler() {
			@Override public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {/*Do nothing*/}
			@Override public void saveWorldInfo(WorldInfo var1) {/*Do nothing*/}
			@Override public WorldInfo loadWorldInfo() {return null;}
			@Override public IPlayerFileData getSaveHandler() {return null;}
			@Override public File getMapFileFromName(String var1) {return null;}
			@Override public IChunkLoader getChunkLoader(WorldProvider var1) {return null;}
			@Override public void flush() {/*Do nothing*/}
			@Override public void checkSessionLock() {/*Do nothing*/}
			@Override public String getWorldDirectoryName() {return null;}
			@Override public File getWorldDirectory() {return null;}
		},
		"DUMMY_DIMENSION",
		new WorldProvider() {
			@Override public String getDimensionName() {return "DUMMY_DIMENSION";}
		},
		new WorldSettings(new WorldInfo(UT.NBT.make())),
		new Profiler()
		);
	}
	
	@Override
	protected IChunkProvider createChunkProvider() {
		return null;
	}
	
	@Override
	public Entity getEntityByID(int aEntityID) {
		return null;
	}
	
	@Override
	public boolean setBlock(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags) {
		mLastSetBlock = ST.make(aBlock, 1, aMeta);
		return T;
	}
	
	@Override
	public float getSunBrightnessFactor(float p_72967_1_) {
		return 1.0F;
	}
	
	@Override
	public BiomeGenBase getBiomeGenForCoords(int aX, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return BiomeGenBase.plains;
		return BiomeGenBase.ocean;
	}
	
	@Override
	public int getFullBlockLightValue(int aX, int aY, int aZ) {
		return 10;
	}
	
	@Override
	public Block getBlock(int aX, int aY, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return aY == 64?Blocks.grass:NB;
		return NB;
	}
	
	@Override
	public int getBlockMetadata(int aX, int aY, int aZ) {
		return 0;
	}
	
	@Override
	public boolean canBlockSeeTheSky(int aX, int aY, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return aY > 64;
		return T;
	}
	
	@Override
	protected int func_152379_p() {
		return 0;
	}
}
