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

import java.util.Set;

import gregapi.code.HashSetNoNulls;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class LeafEntry {
	public final Set<SaplingEntry> mSaplingEntries = new HashSetNoNulls<>();
	public ItemStack mLeaf;
	
	public LeafEntry(ItemStack aLeaf) {
		if (ST.invalid(aLeaf)) return;
		
		mLeaf = ST.amount(1, aLeaf);
		
		WoodDictionary.LEAVES.put(mLeaf, this);
		WoodDictionary.LIST_LEAVES.add(this);
	}
}
