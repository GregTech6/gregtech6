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

package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class ChestGenHooksChestReplacer extends ChestGenHooks {
	public final ChestGenHooks mHookToReplaceChestsOf;
	public final String mCategory;
	public final short mChestID;
	
	// MineTweaker does Reflection the wrong way...
	@SuppressWarnings("rawtypes")
	public ArrayList contents;
	
	public ChestGenHooksChestReplacer(String aCategory) {this(aCategory, (short)32745);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ChestGenHooksChestReplacer(String aCategory, long aChestID) {
		super(aCategory);
		mCategory = aCategory;
		mChestID = (short)aChestID;
		mHookToReplaceChestsOf = ChestGenHooks.getInfo(aCategory);
		super.setMin(mHookToReplaceChestsOf.getMin());
		super.setMax(mHookToReplaceChestsOf.getMax());
		try {
			Field tField = ChestGenHooks.class.getDeclaredField("chestInfo");
			tField.setAccessible(T);
			((HashMap)tField.get(null)).put(aCategory, this);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			Field tField = ChestGenHooks.class.getDeclaredField("contents");
			tField.setAccessible(T);
			tField.set(this, contents = (ArrayList)(tField.get(mHookToReplaceChestsOf)));
		} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public WeightedRandomChestContent[] getItems(Random aRandom) {
		WeightedRandomChestContent[] rReturn = mHookToReplaceChestsOf.getItems(aRandom);
		if (GAPI.mStartedServerStarted < 1 || aRandom == RNGSUS) return rReturn;
		for (int i = 0; i < rReturn.length; i++) rReturn[i] = new WeightedRandomChestContentChestReplacer(rReturn[i], mCategory, mChestID);
		return rReturn;
	}
	
	@Override public void addItem(WeightedRandomChestContent aItem) {mHookToReplaceChestsOf.addItem(aItem);}
	@Override public void removeItem(ItemStack aStack) {mHookToReplaceChestsOf.removeItem(aStack);}
	@Override public int getCount(Random aRandom) {return mHookToReplaceChestsOf.getCount(aRandom);}
	@Override public ItemStack getOneItem(Random aRandom) {return mHookToReplaceChestsOf.getOneItem(aRandom);}
	@Override public int getMin() {return mHookToReplaceChestsOf.getMin();}
	@Override public int getMax() {return mHookToReplaceChestsOf.getMax();}
	@Override public void setMin(int aValue) {mHookToReplaceChestsOf.setMin(aValue); super.setMin(aValue);}
	@Override public void setMax(int aValue) {mHookToReplaceChestsOf.setMax(aValue); super.setMax(aValue);}
	
	public static class WeightedRandomChestContentChestReplacer extends WeightedRandomChestContent {
		public final WeightedRandomChestContent mContent;
		public final String mCategory;
		public final short mChestID;
		
		public WeightedRandomChestContentChestReplacer(WeightedRandomChestContent aContent, String aCategory, short aChestID) {
			super(aContent.theItemId, aContent.theMinimumChanceToGenerateItem, aContent.theMaximumChanceToGenerateItem, aContent.itemWeight);
			mCategory = aCategory;
			mContent = aContent;
			mChestID = aChestID;
		}
		
		@Override
		protected ItemStack[] generateChestContent(Random aRandom, IInventory aInventory) {
			// Only unmodified Vanilla Chests!
			if (aInventory.getClass() != TileEntityChest.class) return generateChestContent2(aRandom, aInventory);
			// We need a World Object.
			World aWorld = ((TileEntityChest)aInventory).getWorldObj();
			if (aWorld == null) return generateChestContent2(aRandom, aInventory);
			// XYZ and check if the Block we replace is a regular Chest.
			int aX = ((TileEntityChest)aInventory).xCoord, aY = ((TileEntityChest)aInventory).yCoord, aZ = ((TileEntityChest)aInventory).zCoord;
			if (Blocks.chest != aWorld.getBlock(aX, aY, aZ)) return generateChestContent2(aRandom, aInventory);
			// Does my Registry exist?
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
			if (tRegistry == null) return generateChestContent2(aRandom, aInventory);
			// Grab the Chests Facing.
			int tFacing = VALIDATE_HORIZONTAL[aWorld.getBlockMetadata(aX, aY, aZ)];
			// Erase the Chest with a Block Update.
			aWorld.setBlock(aX, aY, aZ, NB, 0, 1);
			// Erase it again just to fucking make sure!
			aWorld.setBlock(aX, aY, aZ, NB, 0, 1);
			// Place the better Loot Chest.
			tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, tFacing, "gt.dungeonloot", mCategory), F, T);
			// Loot wont need to be generated anymore in that case.
			return ZL_IS;
		}
		
		protected ItemStack[] generateChestContent2(Random aRandom, IInventory aInventory) {
			try {
				Method tMethod = mContent.getClass().getDeclaredMethod("generateChestContent", Random.class, IInventory.class);
				tMethod.setAccessible(T);
				return (ItemStack[])tMethod.invoke(mContent, aRandom, aInventory);
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
			return super.generateChestContent(aRandom, aInventory);
		}
	}
}
