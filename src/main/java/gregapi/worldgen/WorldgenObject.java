package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.ConfigsGT;
import gregapi.util.UT;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public abstract class WorldgenObject {
	public boolean mEnabled, mInvalid = F;
	public final String mName, mCategory;
	public final Map<Integer, Boolean> mDimEnabled = new HashMap();
	
	public WorldgenObject(String aName, boolean aDefault, List[] aLists) {
		if (UT.Code.stringInvalid(aName)) throw new IllegalArgumentException("The Name has to be not null and is also not allowed to be an empty String");
		mName = aName;
		mCategory = "worldgenerator."+mName;
		mEnabled = ConfigsGT.WORLDGEN.get(mCategory, "Enabled", aDefault);
		for (List aList : aLists) aList.add(this);
	}
	
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		// Insert your WorldGen Code Here.
		return F;
	}
	
	public boolean enabled(World aWorld, int aDimType) {
		if (mInvalid) return F;
		Boolean tAllowed = mDimEnabled.get(aWorld.provider.dimensionId);
		if (tAllowed != null) return tAllowed && mEnabled;
		boolean tValue = ConfigsGT.WORLDGEN.get(mCategory+".dim", aWorld.provider.getDimensionName().replaceAll(" ", "_"), T);
		mDimEnabled.put(aWorld.provider.dimensionId, tValue);
		return tValue && mEnabled;
	}
	
	public void reset(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {/**/}
	
	public boolean checkForMajorWorldgen(World aWorld, int aMinX, int aMinZ, int aMaxX, int aMaxZ) {
		if (aWorld.provider.dimensionId == DIM_OVERWORLD) {
			if (GENERATE_STREETS && (Math.abs(aMinX) < 64 || Math.abs(aMaxX) < 64 || Math.abs(aMinZ) < 64 || Math.abs(aMaxZ) < 64)) return T;
			if (GENERATE_BIOMES && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return T;
		}
		return F;
	}
}