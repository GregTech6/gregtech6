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

package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOresBedrock extends WorldgenObject {
	public final int mProbability;
	public final OreDictMaterial mMaterial;
	public final boolean mIndicatorRocks, mIndicatorFlowers;
	public final Block mFlower;
	public final byte mFlowerMeta;

	public static boolean CAN_GENERATE_BEDROCK_ORE = T;

	@SafeVarargs
	public WorldgenOresBedrock(String aName, boolean aDefault, int aProbability, OreDictMaterial aPrimary, List<WorldgenObject>... aLists) {
		this(aName, aDefault, T, aProbability, aPrimary, aLists);
	}
	@SafeVarargs
	public WorldgenOresBedrock(String aName, boolean aDefault, boolean aIndicatorRocks, int aProbability, OreDictMaterial aPrimary, List<WorldgenObject>... aLists) {
		this(aName, aDefault, aIndicatorRocks, aProbability, aPrimary, null, 0, aLists);
	}
	@SafeVarargs
	public WorldgenOresBedrock(String aName, boolean aDefault, boolean aIndicatorRocks, int aProbability, OreDictMaterial aPrimary, Block aFlower, long aFlowerMeta, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mProbability        = Math.max(1,           ConfigsGT.WORLDGEN.get(mCategory, "Probability"         , aProbability));
		mMaterial           = OreDictMaterial.get(  ConfigsGT.WORLDGEN.get(mCategory, "Ore"                 , aPrimary.mNameInternal));
		mIndicatorRocks     =                       ConfigsGT.WORLDGEN.get(mCategory, "IndicatorRocks"      , aIndicatorRocks);
		mIndicatorFlowers   =                       ConfigsGT.WORLDGEN.get(mCategory, "IndicatorFlowers"    , aFlower != null && aFlower != NB);

		if (mIndicatorFlowers && (aFlower == null || aFlower == NB)) {
			mFlower = Blocks.yellow_flower;
			mFlowerMeta = 0;
		} else {
			mFlower = aFlower;
			mFlowerMeta = UT.Code.bind4(aFlowerMeta);
		}

		if (mEnabled) OreDictManager.INSTANCE.triggerVisibility("ore"+mMaterial.mNameInternal);

		if (mMaterial.mID <= 0) {
			ERR.println("The Material is not valid for Ores: " + mMaterial);
			mInvalid = T;
		} else {
			ItemStack[] tOres = new ItemStack[mMaterial.mByProducts.size() + 2];
			tOres[0] = ST.make((Block)BlocksGT.oreBroken, 1, mMaterial.mID);
			tOres[tOres.length-1] = OP.dustImpure.mat(MT.Bedrock, 1);

			long[] tChances = new long[tOres.length];
			tChances[0] = (tChances.length > 2 ? 9687 : 10000);
			tChances[tChances.length-1] = 10;

			for (int i = 0, j = mMaterial.mByProducts.size(); i < j; i++) {
				OreDictMaterial tByProduct = mMaterial.mByProducts.get(i);
				tOres[i+1] = ST.make((Block)BlocksGT.oreBroken, 1, (tByProduct.mID>0?tByProduct:mMaterial).mID);
				tChances[i+1] = UT.Code.divup(10000, (32 * (tChances.length - 2)));
			}

			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, mMaterial.mID)), tOres, null, tChances, null, null, 0, 0, 0);
		}
	}

	@Override
	public void reset(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		CAN_GENERATE_BEDROCK_ORE = T;
	}

	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (!CAN_GENERATE_BEDROCK_ORE || aRandom.nextInt(mProbability) != 0) return F;
		if (GENERATE_BIOMES && aWorld.provider.dimensionId == 0 && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;

		if (WD.bedrock(aWorld, aMinX+8, 0, aMinZ+8)) {
			CAN_GENERATE_BEDROCK_ORE = F;

			if ((mIndicatorRocks || mIndicatorFlowers) && (!(GENERATE_STREETS && aWorld.provider.dimensionId == 0) || (Math.abs(aMinX) >= 64 && Math.abs(aMaxX) >= 64 && Math.abs(aMinZ) >= 64 && Math.abs(aMaxZ) >= 64))) {
				ItemStack tRock = OP.rockGt.mat(mMaterial, 1);
				if (ST.valid(tRock)) {
					MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
					if (tRegistry != null) {
						for (int i = 0; i < 32; i++) {
							int tX = aMinX+aRandom.nextInt(32)-8, tZ = aMinZ+aRandom.nextInt(32)-8;
							for (int tY = 127; tY > 62; tY--) {
								Block tContact = aWorld.getBlock(tX, tY, tZ);
								if (tContact.getMaterial().isLiquid()) break;
								if (!tContact.isOpaqueCube()) continue;
								if (!WD.easyRep(aWorld, tX, tY+1, tZ)) break;
								if (mIndicatorFlowers && (tContact != Blocks.dirt || !BIOMES_WASTELANDS.contains(aBiomes[8][8].biomeName)) && (!mIndicatorRocks || aRandom.nextInt(4) > 0)) {
									WD.set(aWorld, tX, tY+1, tZ, mFlower, mFlowerMeta, 0);
									if (mFlower.canBlockStay(aWorld, tX, tY+1, tZ)) break;
									WD.set(aWorld, tX, tY+1, tZ, NB, 0, 0);
								}
								if (mIndicatorRocks && (tContact.getMaterial() == Material.grass || tContact.getMaterial() == Material.ground || tContact.getMaterial() == Material.sand || tContact.getMaterial() == Material.rock)) {
									tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, aRandom.nextInt(3)!=0?ST.save(UT.NBT.make(), NBT_VALUE, tRock):UT.NBT.make(), F, T);
									break;
								}
								break;
							}
						}
					}
				}
			}

			int[] tDistances = new int[] {0, 3, 6, 8, 9, 7, 4};
			for (int tY = 1; tY < 7; tY++) for (int tX = -tDistances[tY]; tX <= tDistances[tY]; tX++) for (int tZ = -tDistances[tY]; tZ <= tDistances[tY]; tZ++) {
				WD.removeBedrock(aWorld, aMinX+8+tX, tY, aMinZ+8+tZ);
				switch(aRandom.nextInt(6)) {
				case 0:         WD.setOre(aWorld, aMinX+8+tX, tY, aMinZ+8+tZ, mMaterial); break;
				case 1: case 2: WD.setSmallOre(aWorld, aMinX+8+tX, tY, aMinZ+8+tZ, mMaterial); break;
				}
			}
			for (int tX = -2; tX <= 2; tX++) for (int tZ = -2; tZ <= 2; tZ++) {
				switch(aRandom.nextInt(6)) {
				case 0:         BlocksGT.oreBedrock         .placeBlock(aWorld, aMinX+8+tX, 0, aMinZ+8+tZ, (byte)6, mMaterial.mID, null, F, T); break;
				case 1: case 2: BlocksGT.oreSmallBedrock    .placeBlock(aWorld, aMinX+8+tX, 0, aMinZ+8+tZ, (byte)6, mMaterial.mID, null, F, T); break;
				}
			}
			// At least one Ore Block must be there. So place a large one in the Center.
			BlocksGT.oreBedrock.placeBlock(aWorld, aMinX+8, 0, aMinZ+8, (byte)6, mMaterial.mID, null, F, T);
		}
		return T;
	}
}
