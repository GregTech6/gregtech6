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

package gregapi.wooddict;

import static gregapi.data.CS.*;

import java.util.Set;

import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.data.ANY;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class BeamEntry {
	public final Set<WoodEntry> mWoodEntries = new HashSetNoNulls<>();
	public ItemStack mBeam, mStick;
	public int mPlankCountHand, mPlankCountSaw, mPlankCountBuzz, mStickCountSaw, mStickCountLathe, mCharcoalCount, mCreosoteAmount;
	public PlankEntry mPlankEntry;
	public OreDictMaterial mMaterialBeam = MT.Wood;
	
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank) {
		this(aBeam, aPlank, 1, 200, 3, 5, 7);
	}
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount) {
		this(aBeam, aPlank, aCharcoalCount, aCreosoteAmount, 3, 5, 7);
	}
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz) {
		this(aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aPlank.mMaterialPlank);
	}
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, OreDictMaterial aMaterialWood) {
		this(aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aMaterialWood, OP.stickLong.mat(aMaterialWood, 1));
	}
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, OreDictMaterial aMaterialWood, ItemStack aStick) {
		this(aBeam, aPlank, aCharcoalCount, aCreosoteAmount, aPlankCountHand, aPlankCountSaw, aPlankCountBuzz, aMaterialWood, aStick, 3, 5);
	}
	public BeamEntry(ItemStack aBeam, PlankEntry aPlank, int aCharcoalCount, int aCreosoteAmount, int aPlankCountHand, int aPlankCountSaw, int aPlankCountBuzz, OreDictMaterial aMaterialWood, ItemStack aStick, int aStickCountSaw, int aStickCountLathe) {
		if (ST.invalid(aBeam)) return;
		
		mBeam = ST.amount(1, aBeam);
		mStick = ST.amount(1, aStick);
		mStickCountSaw = aStickCountSaw;
		mStickCountLathe = aStickCountLathe;
		mMaterialBeam = aMaterialWood;
		mCharcoalCount = aCharcoalCount;
		mCreosoteAmount = aCreosoteAmount;
		mPlankCountHand = aPlankCountHand;
		mPlankCountSaw = aPlankCountSaw;
		mPlankCountBuzz = aPlankCountBuzz;
		mPlankEntry = aPlank;
		mPlankEntry.mBeamEntries.add(this);
		
		if (ST.valid(mBeam) && !WoodDictionary.WOODS.containsKey(new ItemStackContainer(mBeam))) {
			if (ST.meta(mBeam) == W) for (int i = 0; i < 16; i++) {ItemStack tBeam = ST.copyMeta(i, mBeam);
			if (OM.materialcontained(tBeam, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(tBeam, new OreDictItemData(mMaterialBeam, (mPlankCountBuzz+1)*U));}
			if (OM.materialcontained(mBeam, T, MT.Wood, MT.WoodRubber, ANY.Wood)) OreDictManager.INSTANCE.setItemData_(mBeam, new OreDictItemData(mMaterialBeam, (mPlankCountBuzz+1)*U));
			WoodDictionary.BEAMS.put(mBeam, this);
			WoodDictionary.LIST_BEAMS.add(this);
			WoodDictionary.IGNORED_OREDICT_REGISTRATIONS.add(ST.item_(mBeam));
		}
	}
}
