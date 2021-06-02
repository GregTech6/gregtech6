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

package gregapi.worldgen.dungeon;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.block.IBlockPlacable;
import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.OP;
import gregapi.fluid.FluidTankGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.random.WorldAndCoords;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Gregorius Techneticies
 */
public class DungeonData extends WorldAndCoords {
	public final MultiTileEntityRegistry mMTERegistryGT;
	public final BlockStones mPrimary, mSecondary;
	public final byte mColor, mColorInversed, mRoomLayout[][];
	public final int mRoomX, mRoomZ, mConnectionCount;
	public final long mKeyIDs[];
	public final ItemStack mKeyStacks[];
	public final boolean mGeneratedKeys[];
	public final HashSetNoNulls<ChunkCoordinates> mLightUpdateCoords;
	public final HashSetNoNulls<TagData> mTags;
	public final WorldgenDungeonGT mStructure;
	public final NBTTagCompound mCoin;
	public final Random mRandom;
	
	public DungeonData(World aWorld, int aX, int aY, int aZ, WorldgenDungeonGT aStructure, BlockStones aPrimaryBlock, BlockStones aSecondaryBlock, MultiTileEntityRegistry aRegistry, HashSetNoNulls<ChunkCoordinates> aLightUpdateCoords, HashSetNoNulls<TagData> aTags, long[] aKeyIDs, ItemStack[] aKeyStacks, boolean[] aGeneratedKeys, byte[][] aRoomLayout, int aRoomX, int aRoomZ, int aConnectionCount, int aColor, Random aRandom, NBTTagCompound aCoin) {
		super(aWorld, aX, aY, aZ);
		mStructure = aStructure;
		mPrimary = aPrimaryBlock;
		mSecondary = aSecondaryBlock;
		mMTERegistryGT = aRegistry;
		mRoomLayout = aRoomLayout;
		mRoomX = aRoomX;
		mRoomZ = aRoomZ;
		mConnectionCount = aConnectionCount;
		mKeyIDs = aKeyIDs;
		mKeyStacks = aKeyStacks;
		mGeneratedKeys = aGeneratedKeys;
		mLightUpdateCoords = aLightUpdateCoords;
		mTags = aTags;
		mColor = UT.Code.bind4(aColor);
		mColorInversed = UT.Code.bind4(15-aColor);
		mCoin = aCoin;
		mRandom = aRandom;
	}
	
	public int next(int aNumber) {return mRandom.nextInt(aNumber);}
	/** Gives a random positive StackSize */
	public int nextStack() {return 1+mRandom.nextInt(64);}
	/** Gives a random MetaData but biased towards the Dungeons Color. */
	public int nextMetaA() {return next1in3() ? mColor         : next(16);}
	/** Gives a random MetaData but biased towards the Dungeons Inverse Color. */
	public int nextMetaB() {return next1in3() ? mColorInversed : next(16);}
	public boolean next1in2() {return mRandom.nextBoolean();}
	public boolean next1in3() {return next(3)<1;}
	public boolean next1in4() {return next(4)<1;}
	public boolean next1in5() {return next(5)<1;}
	public boolean next1in6() {return next(6)<1;}
	public boolean next1in7() {return next(7)<1;}
	public boolean next1in8() {return next(8)<1;}
	public boolean next1in9() {return next(9)<1;}
	public boolean next2in3() {return next(3)<2;}
	public boolean next2in5() {return next(5)<2;}
	public boolean next2in7() {return next(7)<2;}
	public boolean next2in9() {return next(9)<2;}
	public boolean next3in4() {return next(4)<3;}
	public boolean next3in5() {return next(5)<3;}
	public boolean next3in7() {return next(7)<3;}
	public boolean next3in8() {return next(8)<3;}
	public boolean next4in5() {return next(5)<4;}
	public boolean next4in7() {return next(7)<4;}
	public boolean next4in9() {return next(9)<4;}
	public boolean next5in6() {return next(6)<5;}
	public boolean next5in7() {return next(7)<5;}
	public boolean next5in8() {return next(8)<5;}
	public boolean next5in9() {return next(9)<5;}
	public boolean next6in7() {return next(7)<6;}
	public boolean next7in8() {return next(8)<7;}
	public boolean next7in9() {return next(9)<7;}
	public boolean next8in9() {return next(9)<8;}
	
	public boolean bricks     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, 3+next(3), 2);}
	public boolean brick      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.BRICK, 2);}
	public boolean redstoned  (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.RSTBR, 3);}
	public boolean cracked    (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CRACK, 2);}
	public boolean mossy      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.MBRIK, 2);}
	public boolean chiseled   (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CHISL, 2);}
	public boolean tiles      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.TILES, 2);}
	public boolean smalltiles (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.STILE, 2);}
	public boolean smallbricks(int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SBRIK, 2);}
	public boolean smooth     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SMOTH, 2);}
	
	public boolean bricks     (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, 3+next(3), 2);}
	public boolean brick      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.BRICK, 2);}
	public boolean redstoned  (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.RSTBR, 3);}
	public boolean cracked    (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.CRACK, 2);}
	public boolean mossy      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.MBRIK, 2);}
	public boolean chiseled   (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.CHISL, 2);}
	public boolean tiles      (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.TILES, 2);}
	public boolean smalltiles (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.STILE, 2);}
	public boolean smallbricks(int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.SBRIK, 2);}
	public boolean smooth     (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, BlockStones.SMOTH, 2);}
	public boolean air        (int aX, int aY, int aZ) {return set(aX, aY, aZ, NB, 0, 2);}
	
	public boolean glass      (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.Glass, mColor, 2);}
	public boolean glassglow  (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.GlowGlass, mColor, 2);}
	public boolean colored    (int aX, int aY, int aZ) {return set(aX, aY, aZ, BlocksGT.Concrete, mColor, 2);}
	
	public boolean lamp(int aX, int aY, int aZ, Block aPrimary, Block aSecondary, int aGenerateRedstoneBrick) {
		mLightUpdateCoords.add(new ChunkCoordinates(mX+aX, mY+aY, mZ+aZ));
		if (aGenerateRedstoneBrick != 0) redstoned(aX, aY+aGenerateRedstoneBrick, aZ);
		return set(aX, aY, aZ, aGenerateRedstoneBrick == 0 ? Blocks.redstone_lamp : Blocks.lit_redstone_lamp, 0, 2);
	}
	
	public boolean lamp(int aX, int aY, int aZ, int aGenerateRedstoneBrick) {
		mLightUpdateCoords.add(new ChunkCoordinates(mX+aX, mY+aY, mZ+aZ));
		if (aGenerateRedstoneBrick != 0) redstoned(aX, aY+aGenerateRedstoneBrick, aZ);
		return set(aX, aY, aZ, aGenerateRedstoneBrick == 0 ? Blocks.redstone_lamp : Blocks.lit_redstone_lamp, 0, 2);
	}
	
	public boolean coins(int aX, int aY, int aZ) {
		for (int i = 0; i < 16; i++) mCoin.setByte("gt.coin.stacksize."+i, (byte)(next1in3() ? next(8) : 0));
		mCoin.setByte("gt.coin.stacksize."+next(16), (byte)(1+next(8)));
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)32700, mCoin, T, T);
	}
	
	public boolean set(int aX, int aY, int aZ, long aMeta) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, T, T);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, T, T);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, T, T);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, T, T);
	}
	public boolean set(int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long[] aMetas) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMetas[next(aMetas.length)], null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, OreDictMaterial... aMaterials) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, aMaterials[next(aMaterials.length)].mID, null, T, T);
	}
	public boolean set(IBlockPlacable[] aBlocks, int aX, int aY, int aZ, long[] aMetas) {
		return aBlocks[next(aBlocks.length)].placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMetas[next(aMetas.length)], null, T, T);
	}
	public boolean set(IBlockPlacable[] aBlocks, int aX, int aY, int aZ, OreDictMaterial... aMaterials) {
		return aBlocks[next(aBlocks.length)].placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, aMaterials[next(aMaterials.length)].mID, null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, null, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, T, T);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMeta, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMeta, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean obsidian(int aX, int aY, int aZ, boolean aGravity) {
		return set(aX, aY, aZ, Blocks.obsidian, 0, IL.NeLi_Obsidian.exists() ? IL.NeLi_Obsidian.block() : IL.NePl_Obsidian.block(), 0, IL.EtFu_Obsidian.block(), 0, aGravity && IL.RC_Crushed_Obsidian.exists() ? IL.RC_Crushed_Obsidian.block() : Blocks.obsidian, aGravity && IL.RC_Crushed_Obsidian.exists() ? 4 : 0);
	}
	
	public boolean flower(int aX, int aY, int aZ) {
		return flower(aX, aY, aZ, F, F);
	}
	public boolean flower(int aX, int aY, int aZ, boolean aGrassOnly, boolean aVanillaOnly) {
		if (BlocksGT.FlowersA != null) {
			if (aGrassOnly || BlocksGT.FlowersB == null) {
				if (next1in2()) return set(aX, aY, aZ, (Block)BlocksGT.FlowersA, next(BlocksGT.FlowersA.maxMeta()), 2);
			} else {
				switch (next(3)) {
				case 0: return set(aX, aY, aZ, (Block)BlocksGT.FlowersA, next(BlocksGT.FlowersA.maxMeta()), 2);
				case 1: return set(aX, aY, aZ, (Block)BlocksGT.FlowersB, next(BlocksGT.FlowersB.maxMeta()), 2);
				}
			}
		}
		int tIndex = next(BlocksGT.FLOWER_TILES.length);
		return set(aX, aY, aZ, BlocksGT.FLOWER_TILES[tIndex], BlocksGT.FLOWER_METAS[tIndex], 2);
	}
	
	public boolean ingots_or_plates(int aX, int aY, int aZ, long aStackSize, OreDictMaterial... aMaterials) {
		ItemStack aIngot = OP.ingot.mat(aMaterials[next(aMaterials.length)], aStackSize <= 0 ? nextStack() : UT.Code.bindStack(aStackSize));
		ItemStack aPlate = OP.plate.mat(aMaterials[next(aMaterials.length)], aStackSize <= 0 ? nextStack() : UT.Code.bindStack(aStackSize));
		if (ST.valid(aIngot)) return ST.valid(aPlate) && next1in2() ? set(aX, aY, aZ, 32085, ST.save(NBT_VALUE, aPlate)) : set(aX, aY, aZ, 32084, ST.save(NBT_VALUE, aIngot));
		return ST.valid(aPlate) && set(aX, aY, aZ, 32085, ST.save(NBT_VALUE, aPlate));
	}
	public boolean ingots(int aX, int aY, int aZ, long aStackSize, OreDictMaterial... aMaterials) {
		ItemStack aStack = OP.ingot.mat(aMaterials[next(aMaterials.length)], aStackSize <= 0 ? nextStack() : UT.Code.bindStack(aStackSize));
		return ST.valid(aStack) && set(aX, aY, aZ, 32084, ST.save(NBT_VALUE, aStack));
	}
	public boolean plates(int aX, int aY, int aZ, long aStackSize, OreDictMaterial... aMaterials) {
		ItemStack aStack = OP.plate.mat(aMaterials[next(aMaterials.length)], aStackSize <= 0 ? nextStack() : UT.Code.bindStack(aStackSize));
		return ST.valid(aStack) && set(aX, aY, aZ, 32085, ST.save(NBT_VALUE, aStack));
	}
	public boolean gemplates(int aX, int aY, int aZ, long aStackSize, OreDictMaterial... aMaterials) {
		ItemStack aStack = OP.plateGem.mat(aMaterials[next(aMaterials.length)], aStackSize <= 0 ? nextStack() : UT.Code.bindStack(aStackSize));
		return ST.valid(aStack) && set(aX, aY, aZ, 32086, ST.save(NBT_VALUE, aStack));
	}
	
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", aLootFront));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", aLootFront, NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, String aLootBack) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", aLootFront, "gt.dungeonloot.back", aLootBack));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String aLootFront, String aLootBack, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", aLootFront, "gt.dungeonloot.back", aLootBack, NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront)));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), NBT_INV_LIST, aInventory));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, String[] aLootBack) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), "gt.dungeonloot.back", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootBack)));
	}
	public boolean shelf(int aX, int aY, int aZ, long aMeta, byte aFacing, String[] aLootFront, String[] aLootBack, NBTTagList aInventory) {
		return set(aX, aY, aZ, aMeta, UT.NBT.make(NBT_FACING, aFacing, "gt.dungeonloot.front", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootFront), "gt.dungeonloot.back", UT.Code.select(ChestGenHooks.STRONGHOLD_LIBRARY, aLootBack), NBT_INV_LIST, aInventory));
	}
	
	public boolean zpm(int aX, int aY, int aZ) {
		return zpm(aX, aY, aZ, next2in3());
	}
	public boolean zpm(int aX, int aY, int aZ, boolean aActive) {
		return mStructure.mZPM && set(aX, aY, aZ, 14999, UT.NBT.make(NBT_ACTIVE_ENERGY, aActive));
	}
	
	public boolean cup(int aX, int aY, int aZ, FL aFluid) {
		return set(aX, aY, aZ, 32739, FluidTankGT.writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[mColor], NBT_PAINTED, T), NBT_TANK, aFluid == null ? null : aFluid.make(250)));
	}
	public boolean cup(int aX, int aY, int aZ, Fluid aFluid) {
		return set(aX, aY, aZ, 32739, FluidTankGT.writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[mColor], NBT_PAINTED, T), NBT_TANK, FL.make(aFluid, 250)));
	}
	public boolean cup(int aX, int aY, int aZ, FL aFluid, Block aBlock, int aMeta) {
		if (aBlock != NB && aBlock != null && next1in2()) return set(aX, aY, aZ, aBlock, aMeta, 2);
		return cup(aX, aY, aZ, aFluid);
	}
	public boolean cup(int aX, int aY, int aZ, Fluid aFluid, Block aBlock, int aMeta) {
		if (aBlock != NB && aBlock != null && next1in2()) return set(aX, aY, aZ, aBlock, aMeta, 2);
		return cup(aX, aY, aZ, aFluid);
	}
	
	public boolean pot(int aX, int aY, int aZ) {
		int tIndex = next(BlocksGT.POT_FLOWER_TILES.length);
		set(aX, aY, aZ, Blocks.flower_pot, 0, 2);
		TileEntity tTileEntity = mWorld.getTileEntity(mX+aX, mY+aY, mZ+aZ);
		if (tTileEntity instanceof TileEntityFlowerPot) {
			if (next1in2()) {
				((TileEntityFlowerPot)tTileEntity).func_145964_a(ST.item(BlocksGT.POT_FLOWER_TILES[tIndex]), BlocksGT.POT_FLOWER_METAS[tIndex]);
			} else {
				if (next1in2()) {
					((TileEntityFlowerPot)tTileEntity).func_145964_a(ST.item((Block)BlocksGT.FlowersA), next(BlocksGT.FlowersA.maxMeta()));
				} else {
					((TileEntityFlowerPot)tTileEntity).func_145964_a(ST.item((Block)BlocksGT.FlowersB), next(BlocksGT.FlowersB.maxMeta()));
				}
			}
		}
		return T;
	}
	public boolean pot(int aX, int aY, int aZ, Block aBlock, int aMeta) {
		if (aBlock != NB && aBlock != null && next1in2()) return set(aX, aY, aZ, aBlock, aMeta, 2);
		return pot(aX, aY, aZ);
	}
	
	public boolean set(int aX, int aY, int aZ, Block aBlock) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, 0, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, aMeta, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, aMeta, aFlags);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags, int aRotationCount) {
		if (!set(aX, aY, aZ, aBlock, aMeta, aFlags)) return F;
		while (aRotationCount-->0) aBlock.rotateBlock(mWorld, mX+aX, mY+aY, mZ+aZ, FORGE_DIR[SIDE_Y_POS]);
		return T;
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2) {
		return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2, int aFlags) {
		if (aBlock1 == NB || aBlock1 == null) return set(aX, aY, aZ                 , aBlock2, aMeta2, aFlags);
		if (aBlock2 == NB || aBlock2 == null) return set(aX, aY, aZ, aBlock1, aMeta1                 , aFlags);
		switch(next(2)) {
		case  0: return set(aX, aY, aZ, aBlock1, aMeta1, aFlags);
		default: return set(aX, aY, aZ, aBlock2, aMeta2, aFlags);
		}
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2, Block aBlock3, int aMeta3) {
		return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2, aBlock3, aMeta3, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2, Block aBlock3, int aMeta3, int aFlags) {
		if (aBlock1 == NB || aBlock1 == null) return set(aX, aY, aZ                 , aBlock2, aMeta2, aBlock3, aMeta3, aFlags);
		if (aBlock2 == NB || aBlock2 == null) return set(aX, aY, aZ, aBlock1, aMeta1                 , aBlock3, aMeta3, aFlags);
		if (aBlock3 == NB || aBlock3 == null) return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2                 , aFlags);
		switch(next(3)) {
		case  0: return set(aX, aY, aZ, aBlock1, aMeta1, aFlags);
		case  1: return set(aX, aY, aZ, aBlock2, aMeta2, aFlags);
		default: return set(aX, aY, aZ, aBlock3, aMeta3, aFlags);
		}
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2, Block aBlock3, int aMeta3, Block aBlock4, int aMeta4) {
		return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2, aBlock3, aMeta3, aBlock4, aMeta4, 2);
	}
	public boolean set(int aX, int aY, int aZ, Block aBlock1, int aMeta1, Block aBlock2, int aMeta2, Block aBlock3, int aMeta3, Block aBlock4, int aMeta4, int aFlags) {
		if (aBlock1 == NB || aBlock1 == null) return set(aX, aY, aZ                 , aBlock2, aMeta2, aBlock3, aMeta3, aBlock4, aMeta4, aFlags);
		if (aBlock2 == NB || aBlock2 == null) return set(aX, aY, aZ, aBlock1, aMeta1                 , aBlock3, aMeta3, aBlock4, aMeta4, aFlags);
		if (aBlock3 == NB || aBlock3 == null) return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2                 , aBlock4, aMeta4, aFlags);
		if (aBlock4 == NB || aBlock4 == null) return set(aX, aY, aZ, aBlock1, aMeta1, aBlock2, aMeta2, aBlock3, aMeta3                 , aFlags);
		switch(next(4)) {
		case  0: return set(aX, aY, aZ, aBlock1, aMeta1, aFlags);
		case  1: return set(aX, aY, aZ, aBlock2, aMeta2, aFlags);
		case  2: return set(aX, aY, aZ, aBlock3, aMeta3, aFlags);
		default: return set(aX, aY, aZ, aBlock4, aMeta4, aFlags);
		}
	}
}
