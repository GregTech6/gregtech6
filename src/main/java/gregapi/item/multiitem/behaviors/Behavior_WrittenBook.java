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

package gregapi.item.multiitem.behaviors;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_WrittenBook extends AbstractBehaviorDefault {
	public static final Behavior_WrittenBook INSTANCE = new Behavior_WrittenBook();
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		UT.Books.display(aPlayer, aStack);
		return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		String tTitle = UT.NBT.getBookTitle(aStack);
		if (UT.Code.stringValid(tTitle)) {
			aList.add(LH.Chat.CYAN + tTitle);
			aList.add(LH.Chat.CYAN + "by " + UT.NBT.getBookAuthor(aStack));
		} else {
			aList.add(LH.Chat.CYAN + "This Book is Empty");
		}
		return aList;
	}
}
