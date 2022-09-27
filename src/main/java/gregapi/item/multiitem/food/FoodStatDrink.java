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

package gregapi.item.multiitem.food;

import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.util.UT;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static gregapi.data.CS.*;

public class FoodStatDrink extends FoodStat {
	public final String mFluid;
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, int aRadiation, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aRadiation, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, int aRadiation, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aRadiation, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, int aRadiation, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, aRadiation, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid, aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, 0, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid, aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, 0, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid, aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, 0, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	
	public FoodStatDrink(FluidStack aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getFluid(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(Fluid aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		this(aFluid.getName(), aToolTip, aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, aAlwaysEdible, aInvisibleParticles, aIsRotten, aPotionEffects);
	}
	public FoodStatDrink(String aFluid, String aToolTip, int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, int... aPotionEffects) {
		super(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAction, NI, aAlwaysEdible || DRINKS_ALWAYS_DRINKABLE || MD.ENVM.mLoaded, aInvisibleParticles, aIsRotten, T, aPotionEffects);
		mFluid = aFluid;
		if (UT.Code.stringValid(mFluid)) {
			LH.add("gt.drink." + mFluid, aToolTip);
			DrinksGT.REGISTER.put(mFluid, this);
		}
	}
	
	@Override
	public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
		String tTooltip = LH.get("gt.drink." + mFluid, "");
		if (UT.Code.stringValid(tTooltip)) aList.add(tTooltip);
		super.addAdditionalToolTips(aItem, aList, aStack, aF3_H);
	}
}
