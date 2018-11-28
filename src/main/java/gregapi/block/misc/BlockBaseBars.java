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

package gregapi.block.misc;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import gregapi.block.BlockBaseSealable;
import gregapi.block.ItemBlockBase;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IRenderedBlock;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.ITexture;
import gregapi.render.RendererBlockTextured;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBaseBars extends BlockBaseSealable implements IRenderedBlock {
	public final OreDictMaterial mMat;
	
	public BlockBaseBars(String aNameInternal, OreDictMaterial aMat) {
		super(null, aNameInternal, Material.iron, Block.soundTypeMetal);
		mMat = aMat;
		
		CR.shaped(ST.make(this, 1, 0), CR.DEF_REV_NCC, "B B", " h ", "B B", 'B', OP.stick.dat(mMat));
		
		if (CODE_CLIENT) {
			mRenderers[ 0] = new BarRendererXPos(aMat);
			mRenderers[ 1] = new BarRendererXPos(aMat);
			mRenderers[ 2] = new BarRendererXPos(aMat);
			mRenderers[ 3] = new BarRendererXPos(aMat);
			mRenderers[ 4] = new BarRendererXPos(aMat);
			mRenderers[ 5] = new BarRendererXPos(aMat);
			mRenderers[ 6] = new BarRendererXPos(aMat);
			mRenderers[ 7] = new BarRendererXPos(aMat);
			mRenderers[ 8] = new BarRendererXPos(aMat);
			mRenderers[ 9] = new BarRendererXPos(aMat);
			mRenderers[10] = new BarRendererXPos(aMat);
			mRenderers[11] = new BarRendererXPos(aMat);
			mRenderers[12] = new BarRendererXPos(aMat);
			mRenderers[13] = new BarRendererXPos(aMat);
			mRenderers[14] = new BarRendererXPos(aMat);
			mRenderers[15] = new BarRendererXPos(aMat);
		}
	}
	
	@Override
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack.stackSize == 0) return F;
		
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		
		if (tBlock == this) {
			// TODO placement of multiple Bars inside one Block
			if (F) {
				aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
				aStack.stackSize--;
			}
			return F;
		}
		
		if (tBlock == Blocks.snow_layer && (aWorld.getBlockMetadata(aX, aY, aZ) & 7) < 1) {
			aSide = SIDE_UP;
		} else if (tBlock != Blocks.vine && tBlock != Blocks.tallgrass && tBlock != Blocks.deadbush && !tBlock.isReplaceable(aWorld, aX, aY, aZ)) {
			aX += OFFSETS_X[aSide]; aY += OFFSETS_Y[aSide]; aZ += OFFSETS_Z[aSide];
		}
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || !aWorld.canPlaceEntityOnSide(this, aX, aY, aZ, F, aSide, aPlayer, aStack)) return F;
		
		// if used in conjunction with << 2 , these Meta Values return the Side Bits perfectly.
		// Z- = 1, Z+ = 2, X- = 4, X+ = 8
		if (aItem.placeBlockAt(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, (aHitX < aHitZ ? aHitX + aHitZ < 1 ? 4 : 2 : aHitX + aHitZ < 1 ? 1 : 8))) {
			aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
			aStack.stackSize--;
		}
		return T;
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return mMat.mToolQuality;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int damageDropped(int aMeta) {return 0;}
	@Override public int quantityDropped(int aMeta, int aFortune, Random aRandom) {return aMeta == 0 ? 1 : FACE_CONNECTION_COUNT[aMeta];}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return 30;}
	@Override public float getExplosionResistance(int aMeta) {return 5;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean useGravity(int aMeta) {return (aMeta & 7) == 7;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return T;}
	@SuppressWarnings("unchecked") @Override public void getSubBlocks(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {aList.add(ST.make(aItem, 1, 0));}
	
	@Override public ItemStack getPickBlock(MovingObjectPosition aTarget, World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {return ST.make(this, 1, 0);}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
		switch(aWorld.getBlockMetadata(aX, aY, aZ) & 7) {
		case SIDE_X_POS: return AxisAlignedBB.getBoundingBox(aX+0.4, aY    , aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Y_POS: return AxisAlignedBB.getBoundingBox(aX    , aY+0.4, aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Z_POS: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ+0.4, aX+1  , aY+1  , aZ+1  );
		case SIDE_X_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+0.6, aY+1  , aZ+1  );
		case SIDE_Y_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+0.6, aZ+1  );
		case SIDE_Z_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+1  , aZ+0.6);
		default: return AxisAlignedBB.getBoundingBox(aX+0.125, aY+0.125, aZ+0.125, aX+0.875, aY+0.875, aZ+0.875);
		}
	}
	
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1);}
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?23:RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.iron_bars.getIcon(2, 0);}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {return null;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {return null;}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(ItemStack aStack) {return 0;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return 0;}
	@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return mRenderers[ST.meta_(aStack) & 15];}
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return mRenderers[aWorld.getBlockMetadata(aX, aY, aZ)];}
	
	public BarRendererBase[] mRenderers = new BarRendererBase[16];
	
	public static abstract class BarRendererBase implements IRenderedBlockObject {
		public ITexture mTextureNormal, mTextureUsed;
		public BarRendererBase(OreDictMaterial aMat) {mTextureUsed = mTextureNormal = BlockTextureDefault.get(aMat, OP.blockSolid, F);}
		
		@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return APRIL_FOOLS ? 5 : 13;}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTextureUsed;}
		@Override public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
		@Override public boolean renderItem (Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {mTextureUsed = mTextureNormal; return this;}
		@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {mTextureUsed = (APRIL_FOOLS ? BlockTextureDefault.get(Textures.BlockIcons.CFOAM_HARDENED, RAINBOW_ARRAY[WD.random(42069, aX, aY, aZ, 12) * 2]) : mTextureNormal); return this;}
	}
	
	public static class BarRendererXPos extends BarRendererBase {
		public BarRendererXPos(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_X_POS == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 2], PX_P[ 2], PX_N[ 1], PX_P[ 7], PX_P[ 7]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 2], PX_P[ 9], PX_N[ 1], PX_P[ 7], PX_P[14]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 9], PX_P[ 2], PX_N[ 1], PX_P[14], PX_P[ 7]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 9], PX_P[ 9], PX_N[ 1], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 8], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[ 4], PX_N[ 1], PX_P[ 5], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[11], PX_N[ 1], PX_P[ 5], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[ 4], PX_N[ 1], PX_P[12], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[11], PX_N[ 1], PX_P[12], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 3], PX_P[ 3], PX_N[ 1], PX_P[ 6], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 3], PX_P[10], PX_N[ 1], PX_P[ 6], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[ 3], PX_N[ 1], PX_P[13], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[10], PX_N[ 1], PX_P[13], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 2], PX_N[ 1], PX_P[ 7], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 9], PX_N[ 1], PX_P[ 7], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 2], PX_N[ 1], PX_P[14], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_N[ 1], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
		}
	}
}
