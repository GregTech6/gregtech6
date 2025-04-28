/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.F;

/**
 * @author Gregorius Techneticies
 */
public class SaplingEntry {
	public ItemStack mSapling;
	public WoodEntry mWoodEntry;
	public LeafEntry mLeafEntry;
	public OreDictMaterial mMaterialSapling = MT.Wood;
	public boolean mBaggedSaplingLoot = F;
	public int mCount = 1;
	
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood) {
		if (ST.invalid(aSapling)) return;
		mSapling = ST.amount(1, aSapling);
		mCount = UT.Code.bindStack(ST.size(aSapling));
		
		mWoodEntry = aWood;
		if (mWoodEntry != null) {
			mWoodEntry.mSaplingEntries.add(this);
			mMaterialSapling = mWoodEntry.mMaterialWood;
		}
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
		WoodDictionary.LIST_SAPLINGS.add(this);
	}
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood, ItemStack aLeaf) {
		if (ST.invalid(aSapling) || ST.invalid(aLeaf)) return;
		mSapling = ST.amount(1, aSapling);
		mCount = UT.Code.bindStack(ST.size(aSapling));
		
		mLeafEntry = new LeafEntry(aLeaf);
		mLeafEntry.mSaplingEntries.add(this);
		mWoodEntry = aWood;
		if (mWoodEntry != null) {
			mWoodEntry.mSaplingEntries.add(this);
			mMaterialSapling = mWoodEntry.mMaterialWood;
		}
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
		WoodDictionary.LIST_SAPLINGS.add(this);
	}
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood, LeafEntry aLeaf) {
		if (ST.invalid(aSapling)) return;
		mSapling = ST.amount(1, aSapling);
		mCount = UT.Code.bindStack(ST.size(aSapling));
		
		mLeafEntry = aLeaf;
		mLeafEntry.mSaplingEntries.add(this);
		mWoodEntry = aWood;
		if (mWoodEntry != null) {
			mWoodEntry.mSaplingEntries.add(this);
			mMaterialSapling = mWoodEntry.mMaterialWood;
		}
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
		WoodDictionary.LIST_SAPLINGS.add(this);
	}
}
