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
import gregapi.data.CS.ConfigsGT;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class StoneLayer {
	public final Block mStone, mCobble, mMossy;
	public final byte mMetaStone, mMetaCobble, mMetaMossy;
	public final OreDictMaterial mMaterial, mMaterialSurface;
	public final ItemStackContainer mStack;
	public final List<StoneLayerOres> mOres;
	public IBlockPlacable mOre, mOreSmall, mOreBroken;
	public boolean mNoDeep = F;
	
	public StoneLayer(BlockBase aStoneGT, StoneLayerOres... aOreChances) {
		this(ST.invalid(aStoneGT) ? Blocks.stone             : aStoneGT
		,    ST.invalid(aStoneGT) ? 0                        : 0
		,    ST.invalid(aStoneGT) ? Blocks.cobblestone       : aStoneGT
		,    ST.invalid(aStoneGT) ? 0                        : 1
		,    ST.invalid(aStoneGT) ? Blocks.mossy_cobblestone : aStoneGT
		,    ST.invalid(aStoneGT) ? 0                        : 2
		,    ST.invalid(aStoneGT) ? MT.Stone                 : ((BlockStones)aStoneGT).mMaterial
		, aOreChances);
	}
	public StoneLayer(BlockBase aStoneGT, OreDictMaterial aMaterial, Block aStone, StoneLayerOres... aOreChances) {
		this(ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.stone             : aStoneGT                          : aStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 0                                 : 0
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.cobblestone       : aStoneGT                          : Blocks.cobblestone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 1                                 : 0
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.mossy_cobblestone : aStoneGT                          : Blocks.mossy_cobblestone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 2                                 : 0
		, aMaterial
		, aOreChances);
	}
	public StoneLayer(BlockBase aStoneGT, OreDictMaterial aMaterial, Block aStone, long aMetaStone, StoneLayerOres... aOreChances) {
		this(ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.stone             : aStoneGT                          : aStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 0                                 : aMetaStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.cobblestone       : aStoneGT                          : Blocks.cobblestone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 1                                 : 0
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.mossy_cobblestone : aStoneGT                          : Blocks.mossy_cobblestone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 2                                 : 0
		, aMaterial
		, aOreChances);
	}
	public StoneLayer(BlockBase aStoneGT, OreDictMaterial aMaterial, Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, StoneLayerOres... aOreChances) {
		this(ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.stone             : aStoneGT                          : aStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 0                                 : aMetaStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.cobblestone       : aStoneGT                          : aCobble
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 1                                 : aMetaCobble
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.mossy_cobblestone : aStoneGT                          : Blocks.mossy_cobblestone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 2                                 : 0
		, aMaterial
		, aOreChances);
	}
	public StoneLayer(BlockBase aStoneGT, OreDictMaterial aMaterial, Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, Block aMossy, long aMetaMossy, StoneLayerOres... aOreChances) {
		this(ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.stone             : aStoneGT                          : aStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 0                                 : aMetaStone
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.cobblestone       : aStoneGT                          : aCobble
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 1                                 : aMetaCobble
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? Blocks.mossy_cobblestone : aStoneGT                          : aMossy
		,    ST.invalid(aStone) ? ST.invalid(aStoneGT) ? 0                        : 2                                 : aMetaMossy
		, aMaterial
		, aOreChances);
	}
	public StoneLayer(Block aStone, OreDictMaterial aMaterial, StoneLayerOres... aOreChances) {
		this(aStone, 0, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, aMaterial, aOreChances);
	}
	public StoneLayer(Block aStone, long aMetaStone, OreDictMaterial aMaterial, StoneLayerOres... aOreChances) {
		this(aStone, aMetaStone, Blocks.cobblestone, 0, Blocks.mossy_cobblestone, 0, aMaterial, aOreChances);
	}
	public StoneLayer(Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, OreDictMaterial aMaterial, StoneLayerOres... aOreChances) {
		this(aStone, aMetaStone, aCobble, aMetaCobble, aCobble, aMetaCobble, aMaterial, aOreChances);
	}
	public StoneLayer(Block aStone, long aMetaStone, Block aCobble, long aMetaCobble, Block aMossy, long aMetaMossy, OreDictMaterial aMaterial, StoneLayerOres... aOreChances) {
		mStone           = (ST.invalid(aStone ) ?                                 Blocks.stone                       : aStone );
		mCobble          = (ST.invalid(aCobble) ? Blocks.stone       == mStone  ? Blocks.cobblestone       : mStone  : aCobble);
		mMossy           = (ST.invalid(aMossy ) ? Blocks.cobblestone == mCobble ? Blocks.mossy_cobblestone : mCobble : aMossy );
		mMetaStone       = (Blocks.stone             == mStone  ? 0 : UT.Code.bind4(aMetaStone ));
		mMetaCobble      = (Blocks.cobblestone       == mCobble ? 0 : UT.Code.bind4(aMetaCobble));
		mMetaMossy       = (Blocks.mossy_cobblestone == mMossy  ? 0 : UT.Code.bind4(aMetaMossy ));
		mMaterial        = (aMaterial == null ? MT.Stone : aMaterial);
		mMaterialSurface = (aStone == Blocks.stone ? MT.Stone : aStone instanceof BlockStones ? ((BlockStones)aStone).mMaterial : mMaterial);
		mStack           = new ItemStackContainer(mStone, 1, mMetaStone);
		mOre             = BlocksGT.stoneToNormalOres.get(mStack);
		mOreBroken       = BlocksGT.stoneToBrokenOres.get(mStack);
		mOreSmall        = BlocksGT.stoneToSmallOres .get(mStack);
		mOres            = new ArrayListNoNulls<>(8);
		for (StoneLayerOres tOre : aOreChances) if (tOre != null && tOre.mMaterial != MT.Empty && ConfigsGT.WORLDGEN.get("stonelayers."+mMaterial.mNameInternal, tOre.mMaterial.mNameInternal, T)) mOres.add(tOre);
	}
	
	public StoneLayer setNoDeep() {mNoDeep = T; return this;}
	
	/** List of Stone and Ore Blocks, that can simply be replaced by the Stone Layers. */
	public static final Set<Block> REPLACEABLE_BLOCKS = new HashSetNoNulls<>(F, Blocks.stone, Blocks.coal_ore, Blocks.iron_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.lapis_ore, Blocks.redstone_ore, Blocks.lit_redstone_ore);
	/** List of generateable Stone Layers, via ItemStack of the Stone Block, so that MetaData is usable. */
	public static final List<StoneLayer> LAYERS = new ArrayListNoNulls<>();
	/** Deepslate Special Case. */
	public static StoneLayer DEEPSLATE = null;
	/** Whenever two Rock Types hit each other in WorldGen an Ore from the returned List will spawn. The first ones mentioned inside the List can override the chances for others by spawning before, so insert the lowest chances first and then the high chances. */
	public static final Map<OreDictMaterial, Map<OreDictMaterial, List<StoneLayerOres>>> MAP = new HashMap<>();
	/** List of random Small Ore Materials that can generate between Layers. */
	public static final List<OreDictMaterial> RANDOM_SMALL_GEM_ORES = new ArrayListNoNulls<>();
	
	public static boolean bothsides(OreDictMaterial aMaterialA, OreDictMaterial aMaterialB, StoneLayerOres... aOreChances) {
		return topbottom(aMaterialA, aMaterialB, aOreChances) && topbottom(aMaterialB, aMaterialA, aOreChances);
	}
	public static boolean topbottom(OreDictMaterial aTop, OreDictMaterial aBottom, StoneLayerOres... aOreChances) {
		if (aOreChances.length <= 0) return F;
		Map<OreDictMaterial, List<StoneLayerOres>> tMap = MAP.get(aTop);
		if (tMap == null) MAP.put(aTop, tMap = new HashMap<>());
		List<StoneLayerOres> tList = tMap.get(aBottom);
		if (tList == null) tMap.put(aBottom, tList = new ArrayList<>(aOreChances.length));
		for (StoneLayerOres tOre : aOreChances) if (tOre != null && tOre.mMaterial != MT.Empty && ConfigsGT.WORLDGEN.get("doublelayers."+aTop.mNameInternal+"."+aBottom.mNameInternal, tOre.mMaterial.mNameInternal, T)) tList.add(tOre);
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
