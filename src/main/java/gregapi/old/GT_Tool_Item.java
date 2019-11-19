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

package gregapi.old;

import gregapi.data.MD;
import gregapi.item.ItemBase;

/**
 * This is just a basic Tool, which has normal durability and could break Blocks.
 */
public class GT_Tool_Item extends ItemBase {
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed) {
		this(aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, -1, -1);
	}
	
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID) {
		this(aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, aChargedGTID, aDisChargedGTID, 0, 0.0F);
	}
	
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID, int aToolQuality, float aToolStrength) {
		super(MD.GT.mID, aUnlocalized, aEnglish, aTooltip);
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
		setNoRepair();
		setFull3D();
	}
}
