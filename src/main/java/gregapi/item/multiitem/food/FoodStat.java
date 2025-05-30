/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.damage.DamageSources;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.player.EntityFoodTracker;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

public class FoodStat implements IFoodStat {
	private final int mFoodLevel, mAlcohol, mCaffeine, mDehydration, mSugar, mFat, mRadiation;
	private final int[] mPotionEffects;
	private final float mSaturation, mHydration, mTemperature, mTemperatureEffect;
	private final EnumAction mAction;
	private final ItemStack mEmptyContainer;
	private final boolean mAlwaysEdible, mInvisibleParticles, mIsRotten;
	public boolean mExplosive = F, mMilk = F, mExtinguish = F, mUseAPC = T, mAutoDetectEmpty = F;
	public int mRebreathe = 0;
	
	/**
	 * @param aFoodLevel Amount of Food in Half Bacon [0 - 20]
	 * @param aSaturation Amount of Saturation [0.0F - 1.0F]
	 * @param aHydration Amount of Water you get from this, negative = salty [-100.0F - 100.0F]
	 * @param aTemperature Temperature you get from this in Kelvin. C+37 is Body Temperature.
	 * @param aAction The Action to be used. If this is null, it uses the Eating Action
	 * @param aEmptyContainer An empty Container (Optional)
	 * @param aAlwaysEdible If this Item is always edible, like Golden Apples or Potions
	 * @param aInvisibleParticles If the Particles of the Potion Effects are invisible
	 * @param aPotionEffects An Array of Potion Effects with %4==0 Elements as follows
	 * ID of a Potion Effect. 0 for none
	 * Duration of the Potion in Ticks
	 * Level of the Effect. [0, 1, 2] are for [I, II, III], negative to remove existing levels.
	 * The likelihood that this Potion Effect takes place upon being eaten [1 - 100]
	 */
	public FoodStat(int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, int aRadiation, EnumAction aAction, ItemStack aEmptyContainer, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, boolean aAutoDetectEmpty, int... aPotionEffects) {
		mFoodLevel = aFoodLevel;
		mSaturation = aSaturation;
		mHydration = aHydration;
		mTemperature = aTemperature;
		mTemperatureEffect = aTemperatureEffect;
		mAlcohol = aAlcohol;
		mCaffeine = aCaffeine;
		mDehydration = aDehydration;
		mSugar = aSugar;
		mFat = aFat;
		mRadiation = aRadiation;
		mAction = aAction==null?EnumAction.eat:aAction;
		mPotionEffects = aPotionEffects;
		mEmptyContainer = ST.copy(aEmptyContainer);
		mInvisibleParticles = aInvisibleParticles;
		mAlwaysEdible = aAlwaysEdible;
		mAutoDetectEmpty = aAutoDetectEmpty;
		mIsRotten = aIsRotten;
	}
	
	public FoodStat(int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat, EnumAction aAction, ItemStack aEmptyContainer, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, boolean aAutoDetectEmpty, int... aPotionEffects) {
		this(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, aAlcohol, aCaffeine, aDehydration, aSugar, aFat, 0, aAction, aEmptyContainer, aAlwaysEdible, aInvisibleParticles, aIsRotten, aAutoDetectEmpty, aPotionEffects);
	}
	public FoodStat(int aFoodLevel, float aSaturation, float aHydration, float aTemperature, float aTemperatureEffect, EnumAction aAction, ItemStack aEmptyContainer, boolean aAlwaysEdible, boolean aInvisibleParticles, boolean aIsRotten, boolean aAutoDetectEmpty, int... aPotionEffects) {
		this(aFoodLevel, aSaturation, aHydration, aTemperature, aTemperatureEffect, 0, 0, 0, 0, 0, 0, aAction, aEmptyContainer, aAlwaysEdible, aInvisibleParticles, aIsRotten, aAutoDetectEmpty, aPotionEffects);
	}
	
	public FoodStat setExplosive() {
		mExplosive = T;
		return this;
	}
	
	public FoodStat setExtinguish() {
		mExtinguish = T;
		return this;
	}
	
	/** 300 is a full Air Bar, it can be overfilled but only while underwater, as it will be set to 300 while not submerged. */
	public FoodStat setRebreathe(int aRebreathe) {
		mRebreathe = aRebreathe;
		return this;
	}
	
	public FoodStat setMilk() {
		mMilk = T;
		return this;
	}
	
	@Override
	public int getFoodLevel(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mFoodLevel;
	}
	
	@Override
	public float getSaturation(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mSaturation;
	}
	
	@Override
	public float getHydration(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mHydration;
	}
	
	@Override
	public float getTemperature(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mTemperature;
	}
	
	@Override
	public float getTemperatureEffect(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mTemperatureEffect;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem) {
		onEaten(aItem, aStack, aPlayer, aConsumeItem, T);
	}
	
	@Override
	public void onEaten(Item aItem, ItemStack aStack, EntityPlayer aPlayer, boolean aConsumeItem, boolean aMakeSound) {
		if (aConsumeItem && !UT.Entities.hasInfiniteItems(aPlayer)) {
			aStack.stackSize--;
			ItemStack tStack = OM.get(ST.copy(mEmptyContainer));
			if (tStack == null && mAutoDetectEmpty) tStack = ST.container(aStack, F);
			ST.give(aPlayer, tStack, F);
		}
		if (aMakeSound) aPlayer.worldObj.playSoundAtEntity(aPlayer, "random.burp", 0.5F, RNGSUS.nextFloat() * 0.1F + 0.9F);
		if (!aPlayer.worldObj.isRemote) {
			if (mExtinguish) aPlayer.extinguish();
			if (mRebreathe > 0) aPlayer.setAir(aPlayer.getAir()+mRebreathe);
			if (mMilk) aPlayer.curePotionEffects(ST.make(Items.milk_bucket, 1, 0));
			for (int i = 3; i < mPotionEffects.length; i+=4) if (RNGSUS.nextInt(100) < mPotionEffects[i]) {
				UT.Entities.applyPotion(aPlayer, mPotionEffects[i-3], mPotionEffects[i-2], mPotionEffects[i-1], mInvisibleParticles);
			}
			if (mExplosive) {
				aPlayer.worldObj.newExplosion(aPlayer, aPlayer.posX, aPlayer.posY, aPlayer.posZ, 4, T, T);
				aPlayer.attackEntityFrom(DamageSources.getExplodingDamage(), Float.MAX_VALUE);
			}
			EntityFoodTracker tTracker = EntityFoodTracker.get(aPlayer);
			if (tTracker != null) {
				if (mAlcohol        != 0) tTracker.changeAlcohol(mAlcohol);
				if (mCaffeine       != 0) tTracker.changeCaffeine(mCaffeine);
				if (mDehydration    != 0) tTracker.changeDehydration(mDehydration);
				if (mSugar          != 0) tTracker.changeSugar(mSugar);
				if (mFat            != 0) tTracker.changeFat(mFat);
				if (mRadiation      != 0) tTracker.changeRadiation(mRadiation);
			}
		}
	}
	
	@Override
	public EnumAction getFoodAction(Item aItem, ItemStack aStack) {
		return mAction;
	}
	
	@Override
	public boolean alwaysEdible(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mAlwaysEdible;
	}
	
	@Override
	public boolean isRotten(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mIsRotten;
	}
	
	@Override
	public boolean useAppleCoreFunctionality(Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return MD.APC.mLoaded && mUseAPC;
	}
	
	@Override
	public void addAdditionalToolTips(Item aItem, List<String> aList, ItemStack aStack, boolean aF3_H) {
		if ((!useAppleCoreFunctionality(aItem, aStack, null)) && (mFoodLevel > 0 || mSaturation > 0.0F)) aList.add(LH.Chat.RED + "Food: " + mFoodLevel + " - Saturation: " + mSaturation);
		String tString = (mTemperature >= C+40.0F?"Hot"+(mHydration==0?"":" - "):mTemperature >= C+38.0F?"Warm"+(mHydration==0?"":" - "):mTemperature <= C+34.0F?"Very Cold"+(mHydration==0?"":" - "):mTemperature <= C+36.0F?"Cold"+(mHydration==0?"":" - "):"") + (mHydration>0?"Hydration: " + mHydration:mHydration<0?"Dehydration: " + (-mHydration):"");
		if (UT.Code.stringValid(tString) && MD.ENVM.mLoaded) aList.add(LH.Chat.RED + tString);
		if (mExplosive) aList.add(LH.Chat.DRED + "smells like explosives");
		if (mIsRotten) aList.add(LH.Chat.DRED + "smells rotten");
	}
}
