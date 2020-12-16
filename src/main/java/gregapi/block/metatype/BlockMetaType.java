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

package gregapi.block.metatype;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.BlockBaseMeta;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetaType extends BlockBaseMeta {
	public final float mHardnessMultiplier, mResistanceMultiplier;
	public final int mHarvestLevel;
	public final byte mSide;
	public final boolean mIsWall, mIsSlab, mIsStair;
	public final BlockMetaType mBlock;
	public final BlockMetaType[] mSlabs;
	
	public BlockMetaType(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons) {
		super(aItemClass == null ? ItemBlockMetaType.class : aItemClass, aNameInternal, aVanillaMaterial, aSoundType, aCount, aIcons);
		if (aItemClass == null) aItemClass = ItemBlockMetaType.class;
		onBlockCreation(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons);
		setHardness(aHardnessMultiplier * 1.5F);
		setResistance(aResistanceMultiplier * 10.0F);
		setCreativeTab(CreativeTabs.tabBlock);
		mIsWall = F;
		mIsSlab = F;
		mIsStair = F;
		mBlock = this;
		mSide = SIDE_UNKNOWN;
		mHarvestLevel = aHarvestLevel;
		mHardnessMultiplier = aHardnessMultiplier;
		mResistanceMultiplier = aResistanceMultiplier;
		mSlabs = new BlockMetaType[] {
		  makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_DOWN    , this)
		, makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_UP      , this)
		, makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_NORTH   , this)
		, makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_SOUTH   , this)
		, makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_WEST    , this)
		, makeSlab(aItemClass, aVanillaMaterial, aSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier / 2, aHardnessMultiplier / 2, aHarvestLevel, maxMeta(), aIcons, SIDE_EAST    , this)
		, null};
		mSlabs[SIDE_INVALID] = mSlabs[SIDE_DOWN];
		ST.hide(mSlabs[SIDE_UP]);
		ST.hide(mSlabs[SIDE_NORTH]);
		ST.hide(mSlabs[SIDE_SOUTH]);
		ST.hide(mSlabs[SIDE_WEST]);
		ST.hide(mSlabs[SIDE_EAST]);
		for (byte i = 0; i < 16; i++) {
			RM.sawing(16, 16, F, 5, ST.make(this, 1, i), ST.make(mSlabs[0], 2, i));
			CR.shaped(ST.make(mSlabs[0], 2, i), CR.DEF_NAC, "sX", 'X', ST.make(this, 1, i));
			CR.shaped(ST.make(this, 1, i), CR.DEF_NAC, "X", "X", 'X', ST.make(mSlabs[0], 1, i));
		}
	}
	
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockMetaType(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockMetaType(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass == null ? ItemBlockMetaType.class : aItemClass, aName+".slab."+aSlabType, aVanillaMaterial, aSoundType, aCount, aIcons);
		if (aItemClass == null) aItemClass = ItemBlockMetaType.class;
		onSlabCreation(aItemClass, aVanillaMaterial, aSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		setHardness(aHardnessMultiplier * 1.5F);
		setResistance(aResistanceMultiplier * 10.0F);
		mIsWall = F;
		mIsSlab = T;
		mIsStair = F;
		mBlock = aBlock;
		mSide = aSlabType;
		mHarvestLevel = aHarvestLevel;
		mHardnessMultiplier = aHardnessMultiplier;
		mResistanceMultiplier = aResistanceMultiplier;
		mSlabs = null;
		setBlockBounds(
		mSide == SIDE_X_POS ? 0.5F : 0.0F,
		mSide == SIDE_Y_POS ? 0.5F : 0.0F,
		mSide == SIDE_Z_POS ? 0.5F : 0.0F,
		mSide == SIDE_X_NEG ? 0.5F : 1.0F,
		mSide == SIDE_Y_NEG ? 0.5F : 1.0F,
		mSide == SIDE_Z_NEG ? 0.5F : 1.0F
		);
	}
	
	public void onBlockCreation(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons) {
		//
	}
	
	public void onSlabCreation(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		//
	}
	
	@Override
	public boolean onBlockActivated(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (mBlock == this || aSide != OPPOSITES[mSide] || (mBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null && !aWorld.checkNoEntityCollision(mBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ)))) return F;
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		byte aMetaData = WD.meta(aWorld, aX, aY, aZ);
		if (ST.equal(aStack, mBlock.mSlabs[0], aMetaData)) {
			aWorld.setBlock(aX, aY, aZ, mBlock, aMetaData, 3);
			aWorld.playSoundEffect(aX + 0.5F, aY + 0.5F, aZ + 0.5F, mBlock.stepSound.func_150496_b(), (mBlock.stepSound.getVolume() + 1.0F) / 2.0F, mBlock.stepSound.getPitch() * 0.8F);
			if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
			return T;
		}
		return F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		if (aSide == OPPOSITES[mSide]) return T;
		if (aSide != mSide && SIDES_VALID[mSide]) {
			Block aBlock = aWorld.getBlock(aX, aY, aZ);
			if (aBlock instanceof BlockMetaType && ((BlockMetaType)aBlock).mSide == mSide) return aBlock.getRenderBlockPass() != 0;
		}
		return super.shouldSideBeRendered(aWorld, aX, aY, aZ, aSide);
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return mHarvestLevel;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier;}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.stone.getExplosionResistance(null) * mResistanceMultiplier;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return mBlock == this || mSide == aSide;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return mBlock == this;}
	@Override public boolean isNormalCube() {return mBlock == this;}
	@Override public boolean isOpaqueCube() {return mBlock == this;}
	@Override public boolean renderAsNormalBlock() {return mBlock == this;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public int getLightOpacity() {return mBlock == this ? LIGHT_OPACITY_MAX : LIGHT_OPACITY_WATER;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.stone.mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));}
	@Override public Item getItemDropped(int par1, Random par2Random, int par3) {return Item.getItemFromBlock(mBlock == this ? mBlock : mBlock.mSlabs[0]);}
	@Override public void getSubBlocks(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {if (mBlock == this || mBlock.mSlabs[0] == this) super.getSubBlocks(aItem, aTab, aList);}
	@Override public Item getItem(World aWorld, int aX, int aY, int aZ) {return Item.getItemFromBlock(mBlock == this ? mBlock : mBlock.mSlabs[0]);}
}
