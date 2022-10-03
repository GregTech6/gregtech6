/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregapi.wooddict;

import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.data.ANY;
import gregapi.data.CS.*;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IconContainerCopied;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Set;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class PlankEntry {
	public final Set<WoodEntry> mWoodEntries = new HashSetNoNulls<>();
	public final Set<BeamEntry> mBeamEntries = new HashSetNoNulls<>();
	public ItemStack mPlank, mSlab, mStair, mStick;
	public OreDictMaterial mMaterialPlank = MT.Wood;
	public int mPlankIndex, mStickCountHand = 1, mStickCountSaw = 2, mStickCountLathe = 2;
	
	public PlankEntry(ItemStack aPlank) {
		this(aPlank, -1);
	}
	public PlankEntry(ItemStack aPlank, int aPlankIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aPlankIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab) {
		this(aPlank, aSlab, -1);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, int aPlankIndex) {
		this(aPlank, aSlab, IL.Plank_Stairs.get(1, ST.make(Blocks.oak_stairs, 1, 0)), aPlankIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair) {
		this(aPlank, aSlab, aStair, -1);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, int aPlankIndex) {
		this(aPlank, aSlab, aStair, MT.Wood, aPlankIndex);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank) {
		this(aPlank, aMaterialPlank, -1);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank, int aPlankIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aMaterialPlank, aPlankIndex, aMaterialPlank == MT.Wood ? IL.Stick.get(1) : OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aMaterialPlank, aPlankIndex, aStick);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aMaterialPlank, aPlankIndex, aStick, aStickCountHand, aStickCountSaw, aStickCountLathe);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aMaterialPlank, -1);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIndex) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIndex, aMaterialPlank == MT.Wood ? IL.Stick.get(1) : OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		this(aPlank, aSlab, IL.Plank_Stairs.get(1, ST.make(Blocks.oak_stairs, 1, 0)), aMaterialPlank, aPlankIndex, aStick, aStickCountHand, aStickCountSaw, aStickCountLathe);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aStair, aMaterialPlank, -1);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIndex) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIndex, aMaterialPlank == MT.Wood ? IL.Stick.get(1) : OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		if (ST.invalid(aPlank)) return;
		mPlank = ST.amount(1, aPlank);
		mStair = ST.amount(1, aStair);
		mSlab = ST.amount(1, aSlab);
		mStick = ST.amount(1, aStick);
		mStickCountHand = aStickCountHand;
		mStickCountSaw = aStickCountSaw;
		mStickCountLathe = aStickCountLathe;
		mMaterialPlank = aMaterialPlank;
		mPlankIndex = aPlankIndex;
		
		if (mPlankIndex < 0 || mPlankIndex >= PlankData.PLANK_ENTRIES.length) {
			mPlankIndex = 0;
		} else if (PlankData.PLANK_ENTRIES[mPlankIndex] == null) {
			PlankData.PLANK_ENTRIES[mPlankIndex] = this;
			PlankData.PLANKS       [mPlankIndex] = ST.amount(1, mPlank);
			PlankData.PLANK_ICONS  [mPlankIndex] = new IconContainerCopied(ST.block(mPlank), ST.meta_(mPlank), SIDE_ANY);
		}
		
		if (ST.valid(mPlank) && !WoodDictionary.PLANKS.containsKey(new ItemStackContainer(mPlank))) {
			if (ST.meta(mPlank) == W) for (int i = 0; i < 16; i++) {ItemStack tPlank = ST.copyMeta(i, mPlank);
			if (OM.materialcontained(tPlank, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(tPlank, new OreDictItemData(mMaterialPlank, U));}
			if (OM.materialcontained(mPlank, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(mPlank, new OreDictItemData(mMaterialPlank, U));
			WoodDictionary.PLANKS_ANY.put(mPlank, this);
			WoodDictionary.PLANKS.put(mPlank, this);
			WoodDictionary.LIST_PLANKS_ANY.add(this);
			WoodDictionary.LIST_PLANKS.add(this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mPlank));
		}
		if (ST.valid(mStair) && !WoodDictionary.STAIRS.containsKey(new ItemStackContainer(mStair))) {
			if (ST.meta(mStair) == W) for (int i = 0; i < 16; i++) {ItemStack tStair = ST.copyMeta(i, mStair);
			if (OM.materialcontained(tStair, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(tStair, new OreDictItemData(mMaterialPlank, U4*3));}
			if (OM.materialcontained(mStair, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(mStair, new OreDictItemData(mMaterialPlank, U4*3));
			WoodDictionary.PLANKS_ANY.put(mStair, this);
			WoodDictionary.STAIRS.put(mStair, this);
			WoodDictionary.LIST_PLANKS_ANY.add(this);
			WoodDictionary.LIST_STAIRS.add(this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mStair));
		}
		if (ST.valid(mSlab) && !WoodDictionary.SLABS.containsKey(new ItemStackContainer(mSlab))) {
			if (ST.meta(mSlab ) == W) for (int i = 0; i < 16; i++) {ItemStack tSlab  = ST.copyMeta(i, mSlab );
			if (OM.materialcontained(tSlab , T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(tSlab , new OreDictItemData(mMaterialPlank, U2));}
			if (OM.materialcontained(mSlab , T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(mSlab , new OreDictItemData(mMaterialPlank, U2));
			WoodDictionary.PLANKS_ANY.put(mSlab, this);
			WoodDictionary.SLABS.put(mSlab, this);
			WoodDictionary.LIST_PLANKS_ANY.add(this);
			WoodDictionary.LIST_SLABS.add(this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mSlab));
		}
	}
}
