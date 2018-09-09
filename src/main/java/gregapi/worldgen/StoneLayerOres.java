package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

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
	public ArrayList<String> mBiomes = new ArrayList();
	
	public StoneLayerOres(OreDictMaterial aMaterial, long aChance, int aMinY, int aMaxY) {
		mMaterial = (aMaterial == null || aMaterial.mID < 0 ? MT.Empty : aMaterial);
		mChance = UT.Code.bind(1, U, aChance);
		if (aMinY > aMaxY) {mMinY = aMaxY; mMaxY = aMinY;} else {mMinY = aMinY; mMaxY = aMaxY;}
	}
	public StoneLayerOres(OreDictMaterial aMaterial, long aChance, int aMinY, int aMaxY, Collection<String> aBiomes) {
		this(aMaterial, aChance, aMinY, aMaxY);
		mBiomes.addAll(aBiomes);
	}
	
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, int aRandomNumber) {
		return aY >= mMinY && aY <= mMaxY && aRandomNumber < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome.biomeName));
	}
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, Random aRandom) {
		return aY >= mMinY && aY <= mMaxY && aRandom.nextInt((int)U) < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome.biomeName));
	}
	public boolean check(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aY >= mMinY && aY <= mMaxY && RNGSUS.nextInt((int)U) < mChance && (mBiomes.isEmpty() || mBiomes.contains(aBiome.biomeName));
	}
	public boolean set(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome, Random aRandom) {
		return aY == mMinY || aY == mMaxY || aRandom.nextBoolean() ? small(aLayer, aWorld, aX, aY, aZ, aBiome) : normal(aLayer, aWorld, aX, aY, aZ, aBiome);
	}
	public boolean set(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aY == mMinY || aY == mMaxY || RNGSUS.nextBoolean() ? small(aLayer, aWorld, aX, aY, aZ, aBiome) : normal(aLayer, aWorld, aX, aY, aZ, aBiome);
	}
	public boolean normal(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOre.placeBlock(aWorld, aX, aY, aZ, (byte)6, mMaterial.mID, null, F, T);
	}
	public boolean small(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOreSmall.placeBlock(aWorld, aX, aY, aZ, (byte)6, mMaterial.mID, null, F, T);
	}
	public boolean broken(StoneLayer aLayer, World aWorld, int aX, int aY, int aZ, BiomeGenBase aBiome) {
		return aLayer.mOreBroken.placeBlock(aWorld, aX, aY, aZ, (byte)6, mMaterial.mID, null, F, T);
	}
}