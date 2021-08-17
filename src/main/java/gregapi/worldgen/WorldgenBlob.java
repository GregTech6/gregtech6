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
import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public abstract class WorldgenBlob extends WorldgenObject {
	public final int mBlockMeta, mAmount, mSize, mMinY, mMaxY, mProbability;
	public final Block mBlock;
	public final Collection<String> mBiomeList;
	public final boolean mAllowToGenerateinVoid;
	
	@SafeVarargs
	public WorldgenBlob(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mBlock          = aBlock==null?Blocks.cobblestone:aBlock;
		mBlockMeta      = UT.Code.bind4(aBlockMeta);
		mProbability    =                           getConfigFile().get(mCategory, "Probability"   , aProbability);
		mAmount         = (int)UT.Code.bind(1,  16, getConfigFile().get(mCategory, "Amount"        , aAmount));
		mSize           = (int)UT.Code.bind(4, 250, getConfigFile().get(mCategory, "Size"          , aSize));
		mMinY           =                           getConfigFile().get(mCategory, "MinHeight"     , aMinY);
		mMaxY           =                           getConfigFile().get(mCategory, "MaxHeight"     , aMaxY);
		mBiomeList = aBiomeList;
		mAllowToGenerateinVoid = aAllowToGenerateinVoid;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (mBiomeList != null) {
			boolean temp = T;
			for (String tName : aBiomeNames) if (mBiomeList.contains(tName)) {temp = F; break;}
			if (temp) return F;
		}
		if (mProbability <= 1 || aRandom.nextInt(mProbability) == 0) {
			for (int i = 0; i < mAmount; i++) {
				int tX = aMinX + aRandom.nextInt(16), tY = mMinY + aRandom.nextInt(mMaxY - mMinY), tZ = aMinZ + aRandom.nextInt(16);
				if (mAllowToGenerateinVoid || !aWorld.getBlock(tX, tY, tZ).isAir(aWorld, tX, tY, tZ)) {
					float var6 = aRandom.nextFloat() * (float)Math.PI;
					double aX1 = ((tX + 8) + MathHelper.sin(var6) * mSize / 8);
					double aX2 = ((tX + 8) - MathHelper.sin(var6) * mSize / 8);
					double aZ1 = ((tZ + 8) + MathHelper.cos(var6) * mSize / 8);
					double aZ2 = ((tZ + 8) - MathHelper.cos(var6) * mSize / 8);
					double aY1 = (tY + aRandom.nextInt(3) - 2);
					double aY2 = (tY + aRandom.nextInt(3) - 2);
					
					int bMinX = Integer.MAX_VALUE, bMinY = Integer.MAX_VALUE, bMinZ = Integer.MAX_VALUE, bMaxX = Integer.MIN_VALUE, bMaxY = Integer.MIN_VALUE, bMaxZ = Integer.MIN_VALUE;
					
					double[] tRandoms = new double[mSize+1];
					
					for (int j = 0; j < tRandoms.length; j++) tRandoms[j] = aRandom.nextDouble() * mSize / 16;
					
					for (int j = 0; j <= mSize; ++j) {
						double bX = aX1 + (aX2 - aX1) * j / mSize, bY = aY1 + (aY2 - aY1) * j / mSize, bZ = aZ1 + (aZ2 - aZ1) * j / mSize, b = ((MathHelper.sin(j * (float)Math.PI / mSize) + 1) * tRandoms[j] + 1) / 2;
						bMinX = Math.min(bMinX, UT.Code.roundDown(bX - b));
						bMinY = Math.min(bMinY, Math.max(0, UT.Code.roundDown(bY - b)));
						bMinZ = Math.min(bMinZ, UT.Code.roundDown(bZ - b));
						bMaxX = Math.max(bMaxX, UT.Code.roundDown(bX + b));
						bMaxY = Math.max(bMaxY, Math.min(aWorld.getHeight(), UT.Code.roundDown(bY + b)));
						bMaxZ = Math.max(bMaxZ, UT.Code.roundDown(bZ + b));
					}
					
					boolean[][][] tCheck = new boolean[Math.max(4, bMaxX-bMinX+1)][Math.max(4, bMaxY-bMinY+1)][Math.max(4, bMaxZ-bMinZ+1)];
					
					for (int j = 0; j <= mSize; ++j) {
						double bX = aX1 + (aX2 - aX1) * j / mSize, bY = aY1 + (aY2 - aY1) * j / mSize, bZ = aZ1 + (aZ2 - aZ1) * j / mSize, b = ((MathHelper.sin(j * (float)Math.PI / mSize) + 1) * tRandoms[j] + 1) / 2;
						int tMinX = UT.Code.roundDown(bX - b);
						int tMinY = Math.max(0, UT.Code.roundDown(bY - b));
						int tMinZ = UT.Code.roundDown(bZ - b);
						int tMaxX = UT.Code.roundDown(bX + b);
						int tMaxY = Math.min(aWorld.getHeight(), UT.Code.roundDown(bY + b));
						int tMaxZ = UT.Code.roundDown(bZ + b);
						
						for (int eX = tMinX; eX <= tMaxX; ++eX) {
							double cX = (eX + 0.5 - bX) / b;
							if (cX * cX < 1) for (int eY = tMinY; eY <= tMaxY; ++eY) {
								double cY = (eY + 0.5 - bY) / b;
								if (cX * cX + cY * cY < 1) for (int eZ = tMinZ; eZ <= tMaxZ; ++eZ) {
									if (!tCheck[eX-bMinX][eY-bMinY][eZ-bMinZ]) {
										double cZ = (eZ + 0.5 - bZ) / b;
										if (cX * cX + cY * cY + cZ * cZ < 1) {
											tCheck[eX-bMinX][eY-bMinY][eZ-bMinZ] = T;
										}
									}
								}
							}
						}
					}
					
					for (int a = 0; a < tCheck.length; a++) for (int b = 0; b < tCheck[a].length; b++) for (int c = 0; c < tCheck[a][b].length; c++) if (tCheck[a][b][c]) {
						tryPlaceStuff(aWorld, bMinX+a, bMinY+b, bMinZ+c, aRandom);
					}
				}
			}
			return T;
		}
		return F;
	}
	
	public abstract boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom);
}
