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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import gregapi.config.Config;
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
	public final Map<Integer, Boolean> mDimEnabled = new HashMap<>();
	
	@SafeVarargs
	public WorldgenObject(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		if (UT.Code.stringInvalid(aName)) throw new IllegalArgumentException("The Name has to be not null and is also not allowed to be an empty String");
		mName = aName;
		mCategory = "worldgenerator."+mName;
		mEnabled = getConfigFile().get(mCategory, "Enabled", aDefault);
		for (List<WorldgenObject> aList : aLists) aList.add(this);
	}
	
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		// Insert your WorldGen Code Here.
		return F;
	}
	
	public boolean enabled(World aWorld, int aDimType) {
		if (mInvalid) return F;
		Boolean tAllowed = mDimEnabled.get(aWorld.provider.dimensionId);
		if (tAllowed != null) return tAllowed && mEnabled;
		boolean tValue = getConfigFile().get(mCategory+".dim", aWorld.provider.getDimensionName().replaceAll(" ", "_"), T);
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
	
	public Config getConfigFile() {
		return ConfigsGT.WORLDGEN;
	}
}
