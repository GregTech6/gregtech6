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

import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public abstract class WorldgenOnSurface extends WorldgenObject {
	public final int mAmount, mProbability;
	
	@SafeVarargs
	public WorldgenOnSurface(String aName, boolean aDefault, int aAmount, int aProbability, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mAmount      = Math.max(1, getConfigFile().get(mCategory, "Amount", aAmount));
		mProbability = Math.max(1, getConfigFile().get(mCategory, "Probability", aProbability));
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		// How many times can we cast a ray downwards?
		int aAmount = canGenerate(aWorld, aChunk, aDimType, aMinX, aMinZ, aMaxX, aMaxZ, aRandom, aBiomes, aBiomeNames);
		if (aAmount <= 0) return F;
		// Determine the Local Height from which to trace downwards.
		int tMinHeight = Math.min(aWorld.getHeight()-2, WD.waterLevel(aWorld)-1)
		,   tMaxHeight = Math.min(aWorld.getHeight()-1, aWorld.provider.hasNoSky ? 80 : tMinHeight * 2 + 16);
		// Mark some Target Positions for this Chunk.
		boolean tTargets[][] = new boolean[16][16], rResult = F;
		for (int i = 0; i < aAmount; i++) tTargets[aRandom.nextInt(16)][aRandom.nextInt(16)] = T;
		// Go over all Target Positions.
		for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) if (tTargets[i][j]) if (aRandom.nextInt(mProbability) == 0) {
			int tX = aMinX+i, tZ = aMinZ+j;
			for (int tY = tMaxHeight; tY >= tMinHeight; tY--) {
				// Efficiently grab the Block at that Position.
				Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
				// Don't put shit on Farmland, that usually looks ugly as heck.
				if (tContact == Blocks.farmland) break;
				// Lets ignore all non-full Blocks and Trees on the way down, except Fluids.
				if (!tContact.getMaterial().isLiquid()) if (!tContact.isOpaqueCube() || tContact.isWood(aWorld, tX, tY, tZ) || tContact.isLeaves(aWorld, tX, tY, tZ)) continue;
				// Try to place the Stuff into the World.
				rResult |= tryPlaceStuff(aWorld, tX, tY, tZ, aRandom, tContact);
				// And on to the next Sky Ray Cast.
				break;
			}
		}
		return rResult;
	}
	
	public int canGenerate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {return checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ) ? 0 : mAmount;}
	
	public abstract boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom, Block aContact);
}
