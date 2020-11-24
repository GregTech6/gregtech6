/**
 * Copyright (c) 2020 GregTech-6 Team
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gregapi.block.BlockBase;
import gregapi.block.IBlockPlacable;
import gregapi.block.metatype.BlockStones;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS.BlocksGT;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class StoneLayer {
	public final Block mStone, mCobble, mMossy;
	public final byte mMetaStone, mMetaCobble, mMetaMossy;
	public final OreDictMaterial mMaterial;
	public final ItemStackContainer mStack;
	public final List<StoneLayerOres> mOres;
	public IBlockPlacable mOre, mOreSmall, mOreBroken;
	
	public StoneLayer(BlockBase aStone, StoneLayerOres... aOres) {
		this(aStone, 0, aStone, 1, aStone, 2, ((BlockStones)aStone).mMaterial, aOres);
	}
	public StoneLayer(Block aStone, OreDictMaterial aMaterial, StoneLayerOres... aOres) {
		this(aStone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, aMaterial, aOres);
	}
	public StoneLayer(Block aStone, long aMetaStone, OreDictMaterial aMaterial, StoneLayerOres... aOres) {
		this(aStone, aMetaStone, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, aMaterial, aOres);
	}
	public StoneLayer(Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, OreDictMaterial aMaterial, StoneLayerOres... aOres) {
		this(aStone, aMetaStone, aCobble, aMetaCobble, aCobble, aMetaCobble, aMaterial, aOres);
	}
	public StoneLayer(Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, Block aMossy, long aMetaMossy, OreDictMaterial aMaterial, StoneLayerOres... aOres) {
		mStone  = (aStone  == null || aStone  == NB ? Blocks.stone : aStone);
		mMossy  = (aMossy  == null || aMossy  == NB ? mStone : aMossy);
		mCobble = (aCobble == null || aCobble == NB ? mStone : aCobble);
		mMetaStone = UT.Code.bind4(aMetaStone);
		mMetaMossy = UT.Code.bind4(aMetaMossy);
		mMetaCobble = UT.Code.bind4(aMetaCobble);
		mMaterial = (aMaterial == null ? MT.Stone : aMaterial);
		mStack = new ItemStackContainer(mStone, 1, mMetaStone);
		mOre = BlocksGT.stoneToNormalOres.get(mStack);
		mOreSmall = BlocksGT.stoneToSmallOres.get(mStack);
		mOreBroken = BlocksGT.stoneToBrokenOres.get(mStack);
		mOres = new ArrayListNoNulls<>(F, aOres);
	}
	
	/** List of Stone and Ore Blocks, that can simply be replaced by the Stone Layers. */
	public static final Set<Block> REPLACEABLE_BLOCKS = new HashSetNoNulls<>(F, Blocks.stone, Blocks.coal_ore, Blocks.iron_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.lapis_ore, Blocks.redstone_ore, Blocks.lit_redstone_ore);
	/** List of generateable Stone Layers, via ItemStack of the Stone Block, so that MetaData is usable. */
	public static final List<StoneLayer> LAYERS = new ArrayList<>();
	/** Whenever two Rock Types hit each other in WorldGen an Ore from the returned List will spawn. The first ones mentioned inside the List can override the chances for others by spawning before, so insert the lowest chances first and then the high chances. */
	public static final Map<OreDictMaterial, Map<OreDictMaterial, List<StoneLayerOres>>> MAP = new HashMap<>();
	
	public static boolean bothsides(OreDictMaterial aMaterialA, OreDictMaterial aMaterialB, StoneLayerOres... aOreChances) {
		return topbottom(aMaterialA, aMaterialB, aOreChances) && topbottom(aMaterialB, aMaterialA, aOreChances);
	}
	public static boolean topbottom(OreDictMaterial aTop, OreDictMaterial aBottom, StoneLayerOres... aOreChances) {
		if (aOreChances.length <= 0) return F;
		Map<OreDictMaterial, List<StoneLayerOres>> tMap = MAP.get(aTop);
		if (tMap == null) MAP.put(aTop, tMap = new HashMap<>());
		List<StoneLayerOres> tList = tMap.get(aBottom);
		if (tList == null) tMap.put(aBottom, tList = new ArrayList<>(aOreChances.length));
		for (StoneLayerOres tMat : aOreChances) if (tMat != null) tList.add(tMat);
		return T;
	}
	
	public static OreDictMaterial oTop = null, oBottom = null;
	public static List<StoneLayerOres> oList = Collections.emptyList();
	
	public static List<StoneLayerOres> get(StoneLayer aTop, StoneLayer aBottom) {
		return get(aTop.mMaterial, aBottom.mMaterial);
	}
	public static List<StoneLayerOres> get(OreDictMaterial aTop, OreDictMaterial aBottom) {
		if (aTop == oTop && aBottom == oBottom) return oList;
		oTop = aTop; oBottom = aBottom;
		Map<OreDictMaterial, List<StoneLayerOres>> tMap = MAP.get(aTop);
		if (tMap == null) return oList = Collections.emptyList();
		List<StoneLayerOres> tList = tMap.get(aBottom);
		if (tList == null) return oList = Collections.emptyList();
		return oList = tList;
	}
}
