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

package gregtech.items.tools.guns;

import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregtech.items.behaviors.Behavior_Gun;
import net.minecraft.item.ItemStack;

public class GT_Tool_Carbine extends GT_Tool_Pistol {
	@Override public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {return !aIsToolHead ? Textures.ItemIcons.CARBINE : Textures.ItemIcons.HANDLE_CARBINE;}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, Behavior_Gun.BULLETS_MEDIUM);
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got whacked over the head by [KILLER]";
	}
}
