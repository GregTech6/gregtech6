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

package gregtech.worldgen;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.wooddict.SaplingEntry;
import gregapi.wooddict.WoodDictionary;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import twilightforest.TFTreasure;
import twilightforest.TFTreasureTable;

import java.util.HashMap;
import java.util.Random;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class TwilightTreasureReplacer extends TFTreasure {
	public static final HashMap<String, TwilightTreasureReplacer> TWILIGHT_TREASURE = new HashMap<>();
	/** needed simply because Hill 3 has way too many Chests and Hill 2 exclusively has the Peacock Fan. So I'm downgrading Hill 3 a little. */
	public static TwilightTreasureReplacer HILLS_2;
	
	public final TFTreasure mTreasure;
	public final String mCategory, mVanillacategory;
	/** remember 32745 is the normal Loot Chest. */
	public final short mChestID;
	/** Twilight Forest Treasure Table Index */
	public final int mTreasureID;
	/** Amount of each Category to drop */
	public int mRare = 2, mUncommon = 6, mCommon = 10;
	
	public static TFTreasure create(TFTreasure aTreasure, int aIndex, String aCategory, String aVanillacategory, long aChestID) {return new TwilightTreasureReplacer(aTreasure, aIndex, aCategory, aVanillacategory, aChestID);}
	public TwilightTreasureReplacer(TFTreasure aTreasure, int aIndex, String aCategory, String aVanillacategory, long aChestID) {
		super(aIndex);
		mCategory        = "twilightforest:"+aCategory;
		mVanillacategory = aVanillacategory;
		mChestID         = (short)aChestID;
		mTreasureID      = aIndex;
		mTreasure        = aTreasure;
		useless          = (TFTreasureTable)UT.Reflection.getFieldContent(mTreasure, "useless"  , T, T);
		common           = (TFTreasureTable)UT.Reflection.getFieldContent(mTreasure, "common"   , T, T);
		uncommon         = (TFTreasureTable)UT.Reflection.getFieldContent(mTreasure, "uncommon" , T, T);
		rare             = (TFTreasureTable)UT.Reflection.getFieldContent(mTreasure, "rare"     , T, T);
		ultrarare        = (TFTreasureTable)UT.Reflection.getFieldContent(mTreasure, "ultrarare", T, T);
		
		// Hedge Maze
		if (aIndex ==  4) {
			// Harvestcraft Gardens
			if (MD.HaC.mLoaded) {ItemStack
			tStack = ST.make(MD.HaC, "tropicalgarden", 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "watergarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "herbgarden"    , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "grassgarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "textilegarden" , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "groundgarden"  , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "berrygarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "mushroomgarden", 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "leafygarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "desertgarden"  , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "stalkgarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			tStack = ST.make(MD.HaC, "gourdgarden"   , 4, 0); if (ST.valid(tStack)) useless.add(tStack);
			}
			// Seeds for Stuff.
			uncommon .add(IL.Dye_Cocoa.get(4));
			if (IL.EtFu_Beet_Seeds.exists())
			uncommon .add(IL.EtFu_Beet_Seeds.get(4));
			if (IL.GaSu_Beet_Seeds.exists())
			uncommon .add(IL.GaSu_Beet_Seeds.get(4));
			if (IL.BoP_Turnip_Seeds.exists())
			uncommon .add(IL.BoP_Turnip_Seeds.get(4));
			// Some other things.
			uncommon .add(IL.Resin.get(24));
			uncommon .add(IL.Food_Cinnamon.get(12));
		}
		
		// Basic Chests of the Lich Tower need to contain some otherwise insanely hard to obtain Items.
		if (aIndex ==  7) {
			// Clear the normal Junk List.
			useless  .clear();
			// Dimension Stuff that is nowhere else to be found.
			useless  .add(Items.nether_wart, 12);
			useless  .add(Items.quartz, 48);
			useless  .add(Blocks.soul_sand, 24);
			useless  .add(Blocks.netherrack, 36);
			if (IL.EtFu_Magmatic_Netherrack.exists())
			useless  .add(IL.EtFu_Magmatic_Netherrack.get(24));
		}
		
		// Library Chests of the Lich Tower need to contain some otherwise insanely hard to obtain Items.
		if (aIndex ==  8) {
			// Clear the normal Junk List.
			useless  .clear();
			// The only practical way to get vanilla Ink.
			useless  .add(IL.Dye_SquidInk.get(36));
			useless  .add(IL.Bottle_Ink.get(16));
			useless  .add(Items.feather, 8);
			useless  .add(IL.Porcelain_Cup.get(4));
			useless  .add(IL.Food_Pickle.get(8));
			// Add a Death Compass to the Ultra Rares, even though it is super easy to get.
			ultrarare.add(IL.Compass_Death.get(1));
		}
		
		// Labyrinth Vault
		if (aIndex == 10) {
			mRare     =  4;
		}
		
		// Basic Chests of the Dark Tower need to contain some otherwise insanely hard to obtain Items.
		if (aIndex == 11) {
			// Clear the normal Junk List.
			useless  .clear();
			// Dimension Stuff that is nowhere else to be found.
			useless  .add(Items.quartz, 48);
			useless  .add(Blocks.end_stone, 36);
			if (IL.EtFu_Magmatic_Netherrack.exists())
			useless  .add(IL.EtFu_Magmatic_Netherrack.get(24));
		}
		
		// Dark Tower Key Chest
		if (aIndex == 12) {
			// AE essentials just in case.
			if (MD.AE.mLoaded) {
			useless  .add(OP.gem.mat(MT.CertusQuartz, 48));
			useless  .add(OP.gem.mat(MT.ChargedCertusQuartz, 8));
			useless  .add(OP.rockGt.mat(MT.STONES.SkyStone, 48));
			}
			// Enderpearls are a bitch to get in Twilight Forest.
			useless  .add(Items.ender_pearl, 4);
		}
		
		// Urghast Loot
		if (aIndex == 13) {
			mCommon   =  8;
			mUncommon = 27;
			mRare     =  1;
		}
		
		// Tree Cache
		if (aIndex == 14) {
			mRare     =  4;
			// Thaumcraft Saplings
			if (IL.TC_Greatwood_Sapling.exists())
			rare     .add(IL.TC_Greatwood_Sapling.get(4));
			if (IL.TC_Silverwood_Sapling.exists())
			ultrarare.add(IL.TC_Silverwood_Sapling.get(1));
			// All the Variety Saplings.
			for (SaplingEntry tEntry : WoodDictionary.LIST_SAPLINGS) {
			if (MD.EBXL.owns(tEntry.mSapling)) {useless .add(ST.validMeta(16, tEntry.mSapling)); continue;}
			if (MD.EB  .owns(tEntry.mSapling)) {useless .add(ST.validMeta(16, tEntry.mSapling)); continue;}
			if (MD.BoP .owns(tEntry.mSapling)) {useless .add(ST.validMeta(16, tEntry.mSapling)); continue;}
			if (MD.HiL .owns(tEntry.mSapling)) {uncommon.add(ST.validMeta( 4, tEntry.mSapling)); continue;}
			if (MD.HaC .owns(tEntry.mSapling)) {uncommon.add(ST.validMeta( 4, tEntry.mSapling)); continue;}
			if (MD.MF2 .owns(tEntry.mSapling)) {uncommon.add(ST.validMeta( 4, tEntry.mSapling)); continue;}
			}
		}
		
		// Troll Gardens
		if (aIndex == 21) {
			mRare     =  1;
			// Enderpearls are a bitch to get in Twilight Forest.
			useless  .add(Items.ender_pearl, 16);
			// Dimension Stuff that is nowhere else to be found.
			useless  .add(Blocks.end_stone, 64);
			if (IL.EtFu_Chorus_Fruit.exists())
			useless  .add(IL.EtFu_Chorus_Fruit.get(24));
			if (IL.EtFu_Chorus_Flower.exists())
			uncommon .add(IL.EtFu_Chorus_Flower.get(8));
		}
		
		// Troll Vaults
		if (aIndex == 22) {
			mRare     =  1;
			// Dimension Stuff that is nowhere else to be found.
			if (IL.EtFu_Dragon_Breath.exists())
			useless  .add(IL.EtFu_Dragon_Breath.get(36));
			useless  .add(IL.Ancient_Debris.get(1, OP.crushed.mat(MT.AncientDebris, 2)));
			common   .add(IL.Ancient_Debris.get(2, OP.crushed.mat(MT.AncientDebris, 4)));
			uncommon .add(IL.Ancient_Debris.get(4, OP.crushed.mat(MT.AncientDebris, 8)));
			uncommon .add(ST.make(Items.skull, 2, 1));
		}
		
		TWILIGHT_TREASURE.put(mCategory, this);
		ST.LOOT_TABLES.add(mCategory);
		if (aIndex == 2) HILLS_2 = this;
	}
	
	@Override public boolean generate(World aWorld, Random aRandom, int aX, int aY, int aZ) {return generate(aWorld, aRandom, aX, aY, aZ, Blocks.chest);}
	@Override public boolean generate(World aWorld, Random aRandom, int aX, int aY, int aZ, Block aChest) {
		if (mTreasureID == 3 && RNGSUS.nextBoolean()) return HILLS_2.generate(aWorld, aRandom, aX, aY, aZ, aChest);
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return super.generate(aWorld, aRandom, aX, aY, aZ, aChest);
		// Narrow down facing direction of the Chest if it is a double chest.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL_ORDER.length)]) {
			Block tBlock = WD.block(aWorld, aX, aY, aZ, tSide);
			if (tBlock == Blocks.chest || tBlock == Blocks.trapped_chest) {
				// replace adjacent vanilla Chests with Firefly Jars. Should help with the Maze Vault randomly exploding from Mob spawns.
				WD.set(aWorld, aX+OFFX[tSide], aY, aZ+OFFZ[tSide], IL.TF_Firefly_Jar.block(), 0, 3);
				// face away from close Wall.
				for (byte tFace : (SIDES_AXIS_Z[tSide] ? ALL_SIDES_X : ALL_SIDES_Z)) if (WD.opq(aWorld, aX+OFFX[tFace]  , aY, aZ+OFFZ[tFace]  , T, T)) {
					return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, OPOS[tFace], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
				}
				// face away from further Wall then.
				for (byte tFace : (SIDES_AXIS_Z[tSide] ? ALL_SIDES_X : ALL_SIDES_Z)) if (WD.opq(aWorld, aX+OFFX[tFace]*2, aY, aZ+OFFZ[tFace]*2, T, T)) {
					return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, OPOS[tFace], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
				}
				// guess there is no close enough Walls.
				return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, (SIDES_AXIS_Z[tSide] ? ALL_SIDES_X : ALL_SIDES_Z)[RNGSUS.nextInt(2)], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
			}
		}
		// face towards Air if possible.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL_ORDER.length)]) {
			if (WD.air(aWorld, aX+OFFX[tSide]  , aY, aZ+OFFZ[tSide]  ))
			return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, tSide, NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
		}
		// face away from close Wall.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL_ORDER.length)]) {
			if (WD.opq(aWorld, aX+OFFX[tSide]  , aY, aZ+OFFZ[tSide]  , T, T))
			return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, OPOS[tSide], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
		}
		// face away from further Wall then.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL_ORDER.length)]) {
			if (WD.opq(aWorld, aX+OFFX[tSide]*2, aY, aZ+OFFZ[tSide]*2, T, T))
			return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, OPOS[tSide], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
		}
		// well, random it is!
		return tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, mChestID, UT.NBT.make(NBT_FACING, ALL_SIDES_HORIZONTAL[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL.length)], NBT_TRAPPED, T, "gt.dungeonloot", mCategory), F, T);
	}
	
	public static boolean generate(IInventory aInventory, String aCategory) {
		TwilightTreasureReplacer tTreasure = TWILIGHT_TREASURE.get(aCategory);
		return tTreasure != null && tTreasure.generate(aInventory);
	}
	
	public boolean generate(IInventory aInventory) {
		boolean rReturn = T;
		// About twice as much Loot as normal TF because the Loot is quite lackluster compared to the time investment otherwise.
		for (int i = 0; i < mRare    ; i++) rReturn &= addToInventory(aInventory, mTreasure.getRareItem    (RNGSUS));
		for (int i = 0; i < mUncommon; i++) rReturn &= addToInventory(aInventory, mTreasure.getUncommonItem(RNGSUS));
		for (int i = 0; i < mCommon  ; i++) rReturn &= addToInventory(aInventory, mTreasure.getCommonItem  (RNGSUS));
		// Some Extra Loot from a fitting Vanilla Category in order to make most Modded Loot Items available in TF if you can't find the few Vanilla Dungeons.
		if (UT.Code.stringValid(mVanillacategory)) for (int i = 0, j = 9+RNGSUS.nextInt(10); i < j; i++) rReturn &= addToInventory(aInventory, ChestGenHooks.getOneItem(mVanillacategory, RNGSUS));
		return rReturn;
	}
	
	public boolean addToInventory(IInventory aInventory, ItemStack aStack) {
		int tSlot = findEmptySlot(aInventory);
		if (tSlot == -1) return F;
		aInventory.setInventorySlotContents(tSlot, IL.TF_Uncrafting.equal(aStack, T, T) ? IL.TF_Transformation_Powder.get(12+RNGSUS.nextInt(13)) : aStack);
		return T;
	}
	
	public int findEmptySlot(IInventory aInventory) {
		int j = aInventory.getSizeInventory();
		for (int i = 0; i < 100; i++) {int k = RNGSUS.nextInt(j); if (aInventory.getStackInSlot(k) == null) return k;}
		for (int i = 0; i <   j; i++) if (aInventory.getStackInSlot(i) == null) return i;
		return -1;
	}
}
