/**
 * Copyright (c) 2018 Gregorius Techneticies
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
import gregapi.data.CS.BlocksGT;
import gregapi.random.WorldAndCoords;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class DungeonChunkData extends WorldAndCoords {
	public final MultiTileEntityRegistry mMTERegistryGT;
	public final BlockStones mPrimary, mSecondary;
	public final byte[][] mRoomLayout;
	public final byte mColor, mColorInversed;
	public final int mRoomX, mRoomZ, mConnectionCount;
	public final long[] mKeyIDs;
	public final ItemStack[] mKeyStacks;
	public final boolean[] mGeneratedKeys;
	public final HashSetNoNulls<ChunkCoordinates> mLightUpdateCoords;
	public final WorldgenDungeonGT mStructure;
	public final NBTTagCompound mCoin;
	public final Random mRandom;
	
	public DungeonChunkData(World aWorld, int aX, int aY, int aZ, WorldgenDungeonGT aStructure, BlockStones aPrimaryBlock, BlockStones aSecondaryBlock, MultiTileEntityRegistry aRegistry, HashSetNoNulls<ChunkCoordinates> aLightUpdateCoords, long[] aKeyIDs, ItemStack[] aKeyStacks, boolean[] aGeneratedKeys, byte[][] aRoomLayout, int aRoomX, int aRoomZ, int aConnectionCount, int aColor, Random aRandom, NBTTagCompound aCoin) {
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
		mColor = UT.Code.bind4(aColor);
		mColorInversed = UT.Code.bind4(15-aColor);
		mCoin = aCoin;
		mRandom = aRandom;
	}
	
	public boolean bricks     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, 3+mRandom.nextInt(3), 2);}
	public boolean brick      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.BRICK, 2);}
	public boolean redstoned  (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.RSTBR, 3);}
	public boolean cracked    (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CRACK, 2);}
	public boolean mossy      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.MBRIK, 2);}
	public boolean chiseled   (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.CHISL, 2);}
	public boolean tiles      (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.TILES, 2);}
	public boolean smalltiles (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.STILE, 2);}
	public boolean smallbricks(int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SBRIK, 2);}
	public boolean smooth     (int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, aY == 2 ? aSecondary : aPrimary, BlockStones.SMOTH, 2);}
	public boolean setAirBlock(int aX, int aY, int aZ, Block aPrimary, Block aSecondary) {return set(aX, aY, aZ, NB, 0, 2);}
	
	public boolean bricks     (int aX, int aY, int aZ) {return set(aX, aY, aZ, aY == 2 ? mSecondary : mPrimary, 3+mRandom.nextInt(3), 2);}
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
		for (int i = 0; i < 16; i++) mCoin.setByte("gt.coin.stacksize."+i, (byte)(mRandom.nextInt(3) == 0 ? mRandom.nextInt(8) : 0));
		mCoin.setByte("gt.coin.stacksize."+mRandom.nextInt(16), (byte)(1+mRandom.nextInt(8)));
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, SIDE_UNKNOWN, (short)32700, mCoin, T, T);
	}
	
	public boolean set(int aX, int aY, int aZ, byte aSide, long aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return mMTERegistryGT.mBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMetaData, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean set(IBlockPlacable aBlock, int aX, int aY, int aZ, byte aSide, long aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		return aBlock.placeBlock(mWorld, mX+aX, mY+aY, mZ+aZ, aSide, (short)aMetaData, aNBT, aCauseBlockUpdates, aForcePlacement);
	}
	
	public boolean flower(int aX, int aY, int aZ) {
		int tIndex = mRandom.nextInt(BlocksGT.FLOWER_TILES.length);
		return set(aX, aY, aZ, BlocksGT.FLOWER_TILES[tIndex], BlocksGT.FLOWER_METAS[tIndex], 2);
	}
	
	public boolean pot(int aX, int aY, int aZ) {
		int tIndex = mRandom.nextInt(BlocksGT.POT_FLOWER_TILES.length);
		set(aX, aY, aZ, Blocks.flower_pot, 0, 2);
		TileEntity tTileEntity = mWorld.getTileEntity(mX+aX, mY+aY, mZ+aZ);
		if (tTileEntity instanceof TileEntityFlowerPot) ((TileEntityFlowerPot)tTileEntity).func_145964_a(Item.getItemFromBlock(BlocksGT.POT_FLOWER_TILES[tIndex]), BlocksGT.POT_FLOWER_METAS[tIndex]);
		return T;
	}
	
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags) {
		return mWorld.setBlock(mX+aX, mY+aY, mZ+aZ, aBlock, aMeta, aFlags);
	}
	
	public boolean set(int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags, int aRotationCount) {
		set(aX, aY, aZ, aBlock, aMeta, aFlags);
		while (aRotationCount-->0) aBlock.rotateBlock(mWorld, mX+aX, mY+aY, mZ+aZ, FORGE_DIR[SIDE_Y_POS]);
		return T;
	}
}
