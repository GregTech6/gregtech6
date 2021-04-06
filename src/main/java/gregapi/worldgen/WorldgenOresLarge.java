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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.OP;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOresLarge extends WorldgenObject {
	public static ArrayList<WorldgenOresLarge> sList = new ArrayListNoNulls<>();
	public final int mWeight, mDistance;
	public final short mMinY, mMaxY, mDensity, mSize;
	public final OreDictMaterial mTop, mBottom, mBetween, mSpread;
	public final boolean mIndicatorRocks;
	
	@SafeVarargs
	public WorldgenOresLarge(String aName, boolean aDefault, int aMinY, int aMaxY, int aWeight, int aDensity, int aSize, OreDictMaterial aTop, OreDictMaterial aBottom, OreDictMaterial aBetween, OreDictMaterial aSpread, List<WorldgenObject>... aLists) {
		this(aName, aDefault, T, aMinY, aMaxY, aWeight, aDensity, aSize, aTop, aBottom, aBetween, aSpread, aLists);
	}
	
	@SafeVarargs
	public WorldgenOresLarge(String aName, boolean aDefault, boolean aIndicatorRocks, int aMinY, int aMaxY, int aWeight, int aDensity, int aSize, OreDictMaterial aTop, OreDictMaterial aBottom, OreDictMaterial aBetween, OreDictMaterial aSpread, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mMinY               = (short)Math.max(0,        ConfigsGT.WORLDGEN.get(mCategory, "MinHeight"        , aMinY));
		mMaxY               = (short)Math.max(mMinY+5,  ConfigsGT.WORLDGEN.get(mCategory, "MaxHeight"        , aMaxY));
		mWeight             =        Math.max(1,        ConfigsGT.WORLDGEN.get(mCategory, "RandomWeight"     , aWeight));
		mDensity            = (short)Math.max(1,        ConfigsGT.WORLDGEN.get(mCategory, "Density"          , aDensity));
		mDistance           =        Math.max(0,        ConfigsGT.WORLDGEN.get(mCategory, "DistanceFromSpawn", 0));
		mSize               = (short)Math.max(1,        ConfigsGT.WORLDGEN.get(mCategory, "Size"             , aSize));
		mIndicatorRocks     =                           ConfigsGT.WORLDGEN.get(mCategory, "IndicatorRocks"   , aIndicatorRocks);
		mTop                =                           ConfigsGT.WORLDGEN.get(mCategory, "OreTop"           , aTop);
		mBottom             =                           ConfigsGT.WORLDGEN.get(mCategory, "OreBottom"        , aBottom);
		mBetween            =                           ConfigsGT.WORLDGEN.get(mCategory, "OreBetween"       , aBetween);
		mSpread             =                           ConfigsGT.WORLDGEN.get(mCategory, "OreSpread"        , aSpread);
		
		if (mEnabled) {
			if (mTop        .mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mTop      .mNameInternal);
			if (mBottom     .mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mBottom   .mNameInternal);
			if (mBetween    .mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mBetween  .mNameInternal);
			if (mSpread     .mID > 0) OreDictManager.INSTANCE.triggerVisibility("ore"+mSpread   .mNameInternal);
		}
		
		if (mTop        .mID <= 0) ERR.println("The OreTop Material is not valid for Ores: " + mTop);
		if (mBottom     .mID <= 0) ERR.println("The OreBottom Material is not valid for Ores: " + mBottom);
		if (mBetween    .mID <= 0) ERR.println("The OreBetween Material is not valid for Ores: " + mBetween);
		if (mSpread     .mID <= 0) ERR.println("The OreSpread Material is not valid for Ores: " + mSpread);
		
		if (mTop.mID <= 0 && mBottom.mID <= 0 && mBetween.mID <= 0 && mSpread.mID <= 0) mInvalid = T;
	}
	
	public boolean generate(World aWorld, Chunk aChunk, int aMinX, int aMinZ, int aMaxX, int aMaxZ, int aOriginChunkX, int aOriginChunkZ, Random aRandom) {
		if (GENERATE_BIOMES && aWorld.provider.dimensionId == 0 && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;
		if (mDistance > 0 && Math.abs(aMinX) <= mDistance && Math.abs(aMinZ) <= mDistance) return F;
		
		int tMinY = mMinY + WD.random(aWorld, aOriginChunkX, aOriginChunkZ).nextInt(mMaxY - mMinY - 5);
		
		if (mIndicatorRocks && (!(GENERATE_STREETS && aWorld.provider.dimensionId == 0) || (Math.abs(aMinX) >= 64 && Math.abs(aMaxX) >= 64 && Math.abs(aMinZ) >= 64 && Math.abs(aMaxZ) >= 64))) {
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
			if (tRegistry != null) {
				for (int i = 0, j = 1+aRandom.nextInt(3); i < j; i++) {
					int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
					for (int tY = Math.min(aWorld.getHeight(), tMinY+25); tY >= tMinY-10 && tY > 0; tY--) {
						Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
						if (tContact.getMaterial().isLiquid()) break;
						if (!tContact.isOpaqueCube()) continue;
						if (tContact.getMaterial() != Material.grass && tContact.getMaterial() != Material.ground && tContact.getMaterial() != Material.sand && tContact.getMaterial() != Material.rock) break;
						if (WD.easyRep(aWorld, tX, tY+1, tZ)) tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, aRandom.nextInt(3)!=0?ST.save(NBT_VALUE, OP.rockGt.mat(UT.Code.select(mTop, mTop, mBottom, mBetween, mSpread), 1)):UT.NBT.make(), F, T);
						break;
					}
				}
			}
		}
		
		for (int cX=aOriginChunkX-aRandom.nextInt(mSize), eX=aOriginChunkX+16+aRandom.nextInt(mSize), tX=Math.max(aMinX, cX); tX<=Math.min(aMaxX, eX); tX++)
		for (int cZ=aOriginChunkZ-aRandom.nextInt(mSize), eZ=aOriginChunkZ+16+aRandom.nextInt(mSize), tZ=Math.max(aMinZ, cZ); tZ<=Math.min(aMaxZ, eZ); tZ++) {
			if (mBottom.mID > 0) for (int i=tMinY-1; i<tMinY+2; i++) {
				if (aRandom.nextInt(Math.max(1, Math.max(Math.abs(cZ-tZ), Math.abs(eZ-tZ)) / mDensity)) == 0 || aRandom.nextInt(Math.max(1, Math.max(Math.abs(cX-tX), Math.abs(eX-tX)) / mDensity)) == 0) {
					WD.setOre(aWorld, tX, i, tZ, mBottom.mID);
				}
			}
			if (mTop.mID > 0) for (int i=tMinY+3; i<tMinY+6; i++) {
				if (aRandom.nextInt(Math.max(1, Math.max(Math.abs(cZ-tZ), Math.abs(eZ-tZ)) / mDensity)) == 0 || aRandom.nextInt(Math.max(1, Math.max(Math.abs(cX-tX), Math.abs(eX-tX)) / mDensity)) == 0) {
					WD.setOre(aWorld, tX, i, tZ, mTop.mID);
				}
			}
			if (mBetween.mID > 0) if (aRandom.nextInt(Math.max(1, Math.max(Math.abs(cZ-tZ), Math.abs(eZ-tZ)) / mDensity)) == 0 || aRandom.nextInt(Math.max(1, Math.max(Math.abs(cX-tX), Math.abs(eX-tX)) / mDensity)) == 0) {
				WD.setOre(aWorld, tX, tMinY+2+aRandom.nextInt(2), tZ, mBetween.mID);
			}
			if (mSpread.mID > 0) if (aRandom.nextInt(Math.max(1, Math.max(Math.abs(cZ-tZ), Math.abs(eZ-tZ)) / mDensity)) == 0 || aRandom.nextInt(Math.max(1, Math.max(Math.abs(cX-tX), Math.abs(eX-tX)) / mDensity)) == 0) {
				WD.setOre(aWorld, tX, tMinY-1+aRandom.nextInt(7), tZ, mSpread.mID);
			}
		}
		return T;
	}
}
