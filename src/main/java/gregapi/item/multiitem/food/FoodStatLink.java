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

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.DrinksGT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FoodStatLink implements IFoodStat {
	public final IFoodStat mStats;
	
	public FoodStatLink(IFoodStat aStats) {
		mStats = aStats;
	}
	public FoodStatLink(String aFluid) {
		mStats = DrinksGT.REGISTER.get(aFluid);
	}
	public FoodStatLink(Fluid aFluid) {
		mStats = DrinksGT.REGISTER.get(aFluid.getName());
	}
	public FoodStatLink(FluidStack aFluid) {
		mStats = DrinksGT.REGISTER.get(aFluid.getFluid().getName());
	}
	
	@Override
	public int getFoodLevel(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.getFoodLevel(aItem, aStack, aPlayer);
	}
	
	@Override
	public float getSaturation(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.getSaturation(aItem, aStack, aPlayer);
	}
	
	@Override
	public float getHydration(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.getHydration(aItem, aStack, aPlayer);
	}
	
	@Override
	public float getTemperature(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.getTemperature(aItem, aStack, aPlayer);
	}
	
	@Override
	public float getTemperatureEffect(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.getTemperatureEffect(aItem, aStack, aPlayer);
	}
	
	@Override
	public boolean alwaysEdible(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.alwaysEdible(aItem, aStack, aPlayer);
	}
	
	@Override
	public boolean isRotten(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.isRotten(aItem, aStack, aPlayer);
	}
	
	@Override
	public EnumAction getFoodAction(Item aItem, ItemStack aStack) {
		return mStats.getFoodAction(aItem, aStack);
	}
	
	@Override
	public boolean useAppleCoreFunctionality(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mStats.useAppleCoreFunctionality(aItem, aStack, aPlayer);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem) {
		mStats.onEaten(aItem, aStack, aPlayer, aConsumeItem, T);
	}
	
	@Override
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem, boolean aMakeSound) {
		mStats.onEaten(aItem, aStack, aPlayer, aConsumeItem, aMakeSound);
	}
	
	@Override
	public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
		mStats.addAdditionalToolTips(aItem, aList, aStack, aF3_H);
	}
}
