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

package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.Random;

import gregapi.code.BiomeNameSet;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class StoneLayerOres {
	public int mMinY, mMaxY;
	public OreDictMaterial mMaterial;
	/** The Material Amount will determine the chance in the form of an X of U Chance. */
	public long mChance;
	public BiomeNameSet mBiomes = new BiomeNameSet();
	
	public StoneLayerOres(OreDictMaterial aMaterial, long aChance, int aMinY, int aMaxY) {
		mMaterial = (aMaterial != null && aMaterial.mID > 0 ? aMaterial : MT.Empty);
		mChance = UT.Code.bind(1, U, aChance);
		if (aMinY > aMaxY) {mMinY = aMaxY; mMaxY = aMinY;} else {mMinY = aMinY; mMaxY = aMaxY;}
	}
	public StoneLayerOres(OreDictMaterial aMaterial, long aChance, int aMinY, int aMaxY, Collection<String> aBiomes) {
		this(aMaterial, aChance, aMinY, aMaxY);
		mBiomes.addAll(aBiomes);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, int aRandomNumber) {
		return aY >= mMinY && aY <= mMaxY && aRandomNumber           < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome));
	}
	@SuppressWarnings("unlikely-arg-type")
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, Random aRandom) {
		return aY >= mMinY && aY <= mMaxY && aRandom.nextInt((int)U) < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome));
	}
	@SuppressWarnings("unlikely-arg-type")
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aY >= mMinY && aY <= mMaxY && RNGSUS .nextInt((int)U) < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome));
	}
	
	public boolean set(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, Random aRandom) {
		return aY == mMinY || aY == mMaxY || aRandom.nextBoolean() ? small(aLayer, aWorld, aX, aY, aZ, aBiome) : normal(aLayer, aWorld, aX, aY, aZ, aBiome);
	}
	public boolean set(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aY == mMinY || aY == mMaxY || RNGSUS .nextBoolean() ? small(aLayer, aWorld, aX, aY, aZ, aBiome) : normal(aLayer, aWorld, aX, aY, aZ, aBiome);
	}
	public boolean normal(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOre       != null && aLayer.mOre      .placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mMaterial.mID, null, F, T);
	}
	public boolean small(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOreSmall  != null && aLayer.mOreSmall .placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mMaterial.mID, null, F, T);
	}
	public boolean broken(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOreBroken != null && aLayer.mOreBroken.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mMaterial.mID, null, F, T);
	}
}
