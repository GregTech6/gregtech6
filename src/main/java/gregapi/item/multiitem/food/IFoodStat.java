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

package gregapi.item.multiitem.food;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IFoodStat {
	/** Warning: the "aPlayer" Parameter may be null! May return a number between 0 and 20, 1 being half a Pork. */
	public int getFoodLevel(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! May return a Number between 0.0F and 1.0F */
	public float getSaturation(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! Amount of Water you get from this, negative = salty [-100.0F - 100.0F] */
	public float getHydration(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! Temperature you get from this in Kelvin. C+37 is Body Temperature. */
	public float getTemperature(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! Temperature modification strength. Usually 0.5F for things that can affect it and 0.1F for things that can't do much. 1.0F is max! */
	public float getTemperatureEffect(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! */
	public boolean alwaysEdible(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! */
	public boolean isRotten(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	/** Warning: the "aPlayer" Parameter may be null! */
	public EnumAction getFoodAction(Item aItem, ItemStack aStack);
	/** Warning: the "aPlayer" Parameter may be null! */
	public boolean useAppleCoreFunctionality(Item aItem, ItemStack aStack, EntityPlayer aPlayer);
	
	@Deprecated
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem);
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem, boolean aMakeSound);
	
	public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H);
}
