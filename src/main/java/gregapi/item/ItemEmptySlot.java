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

package gregapi.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.CS.ItemsGT;
import gregapi.data.MD;
import gregapi.old.Textures;
import gregapi.util.ST;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * @author Gregorius Techneticies
 */
public class ItemEmptySlot extends ItemBase {
	public ItemEmptySlot() {
		super(MD.GAPI.mID, "gt.empty_slot", "Empty Slot", "This Slot has to be left Empty");
		ItemsGT.ILLEGAL_DROPS.add(this);
		ST.hide(this);
	}
	
	@Override
	public IIcon getIconFromDamage(int aMeta) {
		return Textures.ItemIcons.VOID.getIcon(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		// No Icons to register!
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public final void getSubItems(Item var1, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		//
	}
}
