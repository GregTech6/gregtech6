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

package gregtech.worldgen.overworld;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.MT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOresBedrock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenColtan extends WorldgenObject {
	public final short mMinY, mMaxY, mAmount;
	public final int mRange;
	
	@SafeVarargs
	public WorldgenColtan(String aName, boolean aDefault, int aMinY, int aMaxY, int aAmount, int aRange, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mMinY               = (short)                   getConfigFile().get(mCategory, "MinHeight"   , aMinY);
		mMaxY               = (short)Math.max(mMinY+1,  getConfigFile().get(mCategory, "MaxHeight"   , aMaxY));
		mAmount             = (short)Math.max(1,        getConfigFile().get(mCategory, "Amount"      , aAmount));
		mRange              = Math.max(16,              getConfigFile().get(mCategory, "Range"       , aRange));
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		Random tRandom = new Random(aWorld.getSeed()+5);
		int tX = (int)(tRandom.nextGaussian()*1500), tZ = (int)(tRandom.nextGaussian()*1500);
		// Generate Bedrock Vein where the Coltass points to.
		if ((tX>>4) == (aMinX>>4) && (tZ>>4) == (aMinZ>>4)) WorldgenOresBedrock.generateVein(MT.OREMATS.Coltan, aWorld, aDimType, aMinX, aMinZ, aRandom);
		if (GENERATE_BIOMES && aDimType == DIM_OVERWORLD && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;
		int tDistance = (tX-aMinX)*(tX-aMinX) + (tZ-aMinZ)*(tZ-aMinZ);
		
		// Generate Coltan a the large Area around the Center Point of Coltan Contention.
		if (tDistance > mRange*mRange) return F;
		for (int i = 0, j = Math.max(1, mAmount/2 + aRandom.nextInt(1+mAmount)/2); i < j; i++) {
			switch(aRandom.nextInt(5)) {
			default: WD.setSmallOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Coltan   .mID); break;
			case  0: WD.setSmallOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Columbite.mID); break;
			case  1: WD.setSmallOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Tantalite.mID); break;
			}
		}
		
		// If close to the Bedrock Vein, generate a few large Ores too. (64*64=4096)
		if (tDistance > 4096) return T;
		for (int i = 0, j = Math.max(1, mAmount/2 + aRandom.nextInt(1+mAmount)/2); i < j; i++) {
			switch(aRandom.nextInt(5)) {
			default: WD.setOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Coltan   .mID); break;
			case  0: WD.setOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Columbite.mID); break;
			case  1: WD.setOre(aWorld, aMinX+aRandom.nextInt(16), mMinY+aRandom.nextInt(Math.max(1, mMaxY-mMinY)), aMinZ+aRandom.nextInt(16), MT.OREMATS.Tantalite.mID); break;
			}
		}
		return T;
	}
}
