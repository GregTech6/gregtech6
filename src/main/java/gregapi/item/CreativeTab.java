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

package gregapi.item;

import gregapi.data.LH;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author Gregorius Techneticies
 */
public class CreativeTab extends CreativeTabs {
	public final Item mItem;
	public final short mMetaData;
	
	public CreativeTab(String aName, String aLocal, Item aItem, short aMetaData) {
		super(aName);
		LH.add("itemGroup." + aName, aLocal);
		mItem = aItem;
		mMetaData = aMetaData;
	}
	
	@Override
	public Item getTabIconItem() {
		return mItem;
	}
	
	@Override
	public int func_151243_f() {
		return mMetaData;
	}
}
