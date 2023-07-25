/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.item.multiitem;

import cpw.mods.fml.common.Optional;
import gregapi.item.multiitem.food.IFoodStat;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.ModIDs;

/**
 * @author Gregorius Techneticies
 * 
 * For Custom Items.
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = ModIDs.APC)
, @Optional.Interface(iface = "ic2.api.item.IItemReactorPlanStorage", modid = ModIDs.IC2C)
, @Optional.Interface(iface = "ic2.api.item.IBoxable", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
})
public abstract class MultiItemRandomWithCompat extends MultiItemRandom implements Runnable, squeek.applecore.api.food.IEdible, ic2.api.item.IBoxable, ic2.api.item.IItemReactorPlanStorage, ISpecialElectricItem, IElectricItemManager, IItemElectric {
	/**
	 * Creates the Item using these Parameters.
	 * @param aUnlocalized The unlocalised Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 */
	public MultiItemRandomWithCompat(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
	}
	
	@Override @Optional.Method(modid = ModIDs.APC)
	public squeek.applecore.api.food.FoodValues getFoodValues(ItemStack aStack) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		if (tStat == null) return null;
		int tFoodLevel = tStat.getFoodLevel(this, aStack, null);
		return tFoodLevel > 0 ? new squeek.applecore.api.food.FoodValues(tFoodLevel, tStat.getSaturation(this, aStack, null)) : null;
	}
	
	@Override @Optional.Method(modid = ModIDs.IC2)
	public IElectricItemManager getManager(ItemStack aStack) {return this;}
}
