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

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.ConfigsGT;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.WD;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOresSmall extends WorldgenObject {
	public final short mMinY, mMaxY, mAmount;
	public final OreDictMaterial mMaterial;
	
	@SafeVarargs
	public WorldgenOresSmall(String aName, boolean aDefault, int aMinY, int aMaxY, int aAmount, OreDictMaterial aPrimary, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mMinY               = (short)                   ConfigsGT.WORLDGEN.get(mCategory, "MinHeight"   , aMinY);
		mMaxY               = (short)Math.max(mMinY+1,  ConfigsGT.WORLDGEN.get(mCategory, "MaxHeight"   , aMaxY));
		mAmount             = (short)Math.max(1,        ConfigsGT.WORLDGEN.get(mCategory, "Amount"      , aAmount));
		mMaterial           =                           ConfigsGT.WORLDGEN.get(mCategory, "Ore"         , aPrimary);
		
		if (mEnabled && mMaterial.mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mMaterial.mNameInternal);
		
		if (mMaterial.mID <= 0) {
			ERR.println("The Material is not valid for Ores: " + mMaterial);
			mInvalid = T;
		}
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (GENERATE_BIOMES && aDimType == DIM_OVERWORLD && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;
		for (int i = 0, j = Math.max(1, mAmount/2 + aRandom.nextInt(1+mAmount)/2); i < j; i++) WD.setSmallOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), mMaterial.mID);
		return T;
	}
}
