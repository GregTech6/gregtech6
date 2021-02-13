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

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.FL;
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
		this(aName, aDefault, aIndicatorRocks, aProbability, aPrimary, NB, 0, aLists);
	}
	@SafeVarargs
	public WorldgenOresBedrock(String aName, boolean aDefault, boolean aIndicatorRocks, int aProbability, OreDictMaterial aPrimary, Object aFlower, long aFlowerMeta, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		aFlower             = aFlower instanceof Block ? (Block)aFlower : NB;
		mProbability        = Math.max(1,         ConfigsGT.WORLDGEN.get(mCategory, "Probability"     , aProbability));
		mMaterial           = OreDictMaterial.get(ConfigsGT.WORLDGEN.get(mCategory, "Ore"             , aPrimary.mNameInternal));
		mIndicatorRocks     =                     ConfigsGT.WORLDGEN.get(mCategory, "IndicatorRocks"  , aIndicatorRocks);
		mIndicatorFlowers   =                     ConfigsGT.WORLDGEN.get(mCategory, "IndicatorFlowers", aFlower != NB);
		
		if (mIndicatorFlowers && aFlower == NB) {
			mFlower = Blocks.yellow_flower;
			mFlowerMeta = 0;
		} else {
			mFlower = (Block)aFlower;
			mFlowerMeta = UT.Code.bind4(aFlowerMeta);
		}
		
		if (mEnabled) OreDictManager.INSTANCE.triggerVisibility("ore"+mMaterial.mNameInternal);
		
		if (mMaterial == ANY.Hexorium) {
			ItemStack[] tOres = new ItemStack[6];
			tOres[0] = ST.make((Block)BlocksGT.oreBroken, 1, MT.HexoriumWhite.mID);
			tOres[1] = ST.make((Block)BlocksGT.oreBroken, 1, MT.HexoriumBlack.mID);
			tOres[2] = ST.make((Block)BlocksGT.oreBroken, 1, MT.HexoriumRed  .mID);
			tOres[3] = ST.make((Block)BlocksGT.oreBroken, 1, MT.HexoriumGreen.mID);
			tOres[4] = ST.make((Block)BlocksGT.oreBroken, 1, MT.HexoriumBlue .mID);
			tOres[5] = OP.dustImpure.mat(MT.Bedrock, 1);
			
			long[] tChances = new long[tOres.length];
			tChances[0] = 5000;
			tChances[1] = 3500;
			tChances[2] =  500;
			tChances[3] =  500;
			tChances[4] =  500;
			tChances[5] =   10;
			
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, MT.HexoriumWhite.mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, MT.HexoriumBlack.mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, MT.HexoriumRed  .mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, MT.HexoriumGreen.mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, MT.HexoriumBlue .mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
		} else if (mMaterial.mID <= 0) {
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
			
			RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make((Block)BlocksGT.oreBedrock, 1, mMaterial.mID)), tOres, null, tChances, FL.array(FL.lube(100)), null, 0, 0, 0);
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
			
			try {
				for (int tX = -2; tX <= 2; tX++) for (int tZ = -2; tZ <= 2; tZ++) {
					switch(aRandom.nextInt(6)) {
					case 0:         BlocksGT.oreBedrock         .placeBlock(aWorld, aMinX+8+tX, 0, aMinZ+8+tZ, SIDE_UNKNOWN, (mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial).mID, null, F, T); break;
					case 1: case 2: BlocksGT.oreSmallBedrock    .placeBlock(aWorld, aMinX+8+tX, 0, aMinZ+8+tZ, SIDE_UNKNOWN, (mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial).mID, null, F, T); break;
					}
				}
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
			
			try {
				// Keep Distances within the Chunk for this important step.
				int[] tD = new int[] {0, 3, 5, 6, 7, 6, 4};
				for (int tY = 1; tY < tD.length; tY++) for (int tX = -tD[tY]; tX <= tD[tY]; tX++) for (int tZ = -tD[tY]; tZ <= tD[tY]; tZ++) {
					WD.removeBedrock(aWorld, aMinX+8+tX, tY, aMinZ+8+tZ);
					switch(aRandom.nextInt(6)) {
					case 0:         WD.setOre     (aWorld, aMinX+8+tX, tY, aMinZ+8+tZ, mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial); break;
					case 1: case 2: WD.setSmallOre(aWorld, aMinX+8+tX, tY, aMinZ+8+tZ, mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial); break;
					}
				}
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
			
			if ((mIndicatorRocks || mIndicatorFlowers) && (!GENERATE_STREETS || aWorld.provider.dimensionId != 0 || (Math.abs(aMinX) >= 64 && Math.abs(aMaxX) >= 64 && Math.abs(aMinZ) >= 64 && Math.abs(aMaxZ) >= 64))) { 
				MultiTileEntityRegistry tRegistry = (mIndicatorRocks ? MultiTileEntityRegistry.getRegistry("gt.multitileentity") : null);
				ItemStack tRock = (tRegistry == null ? null : OP.rockGt.mat(mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial, 1));
				boolean tFlowers = (mIndicatorFlowers && !BIOMES_WASTELANDS.contains(aBiomes[8][8].biomeName)), tRocks = ST.valid(tRock);
				
				int tMinHeight = Math.min(aWorld.getHeight()-2, WD.waterLevel(aWorld)-1)
				,   tMaxHeight = Math.min(aWorld.getHeight()-1, tMinHeight * 2 + 16);
				// Generate first an 8x8 of 4, then a 16x16 of 8, and at the end a 32x32 of 16 Rocks/Flowers. That way the Pattern gets denser in the middle, and Chunk Boundary Issues of GalactiCraft wont be as terrible.
				for (int tD = 4; tD <= 16; tD *= 2) try {for (int i = 0; i < tD; i++) {
					int tX = aMinX+aRandom.nextInt(tD*2)+8-tD, tZ = aMinZ+aRandom.nextInt(tD*2)+8-tD;
					for (int tY = tMaxHeight; tY > tMinHeight; tY--) {
						Block tContact = aWorld.getBlock(tX, tY, tZ);
						if (tContact.getMaterial().isLiquid() || tContact == Blocks.farmland) break;
						if (!tContact.isOpaqueCube() || tContact.isWood(aWorld, tX, tY, tZ) || tContact.isLeaves(aWorld, tX, tY, tZ)) continue;
						if (!WD.easyRep(aWorld, tX, tY+1, tZ)) break;
						if (tFlowers && tContact != Blocks.dirt && (!tRocks || aRandom.nextInt(4) > 0)) {
							WD.set(aWorld, tX, tY+1, tZ, mFlower, mFlowerMeta, 0);
							if (mFlower.canBlockStay(aWorld, tX, tY+1, tZ)) break;
							WD.set(aWorld, tX, tY+1, tZ, NB, 0, 0);
						}
						if (tRocks && (tContact.getMaterial() == Material.grass || tContact.getMaterial() == Material.ground || tContact.getMaterial() == Material.sand || tContact.getMaterial() == Material.rock)) {
							tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tRock), F, T);
							break;
						}
						break;
					}
				}} catch(Throwable e) {
					ERR.println(tD);
					e.printStackTrace(ERR);
				}
			}
			// At least one Ore Block must be there. So place a large one in the Center.
			BlocksGT.oreBedrock.placeBlock(aWorld, aMinX+8, 0, aMinZ+8, SIDE_UNKNOWN, (mMaterial == ANY.Hexorium ? UT.Code.select(MT.HexoriumBlack, ANY.Hexorium.mToThis.toArray(ZL_MATERIAL)) : mMaterial).mID, null, F, T);
		}
		return T;
	}
}
