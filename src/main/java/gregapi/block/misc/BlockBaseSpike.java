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

package gregapi.block.misc;

import gregapi.block.BlockBaseSealable;
import gregapi.block.IBlockOnWalkOver;
import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.data.CS.*;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.*;
import gregapi.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static gregapi.data.CS.*;

public abstract class BlockBaseSpike extends BlockBaseSealable implements IBlockOnWalkOver, IBlockToolable, IRenderedBlock {
	public final OreDictMaterial mMat1, mMat2;
	
	public BlockBaseSpike(String aNameInternal, OreDictMaterial aMat1, OreDictMaterial aMat2) {
		super(null, aNameInternal, Material.iron, Block.soundTypeMetal);
		setCreativeTab(CreativeTabs.tabRedstone);
		mMat1 = aMat1; mMat2 = aMat2;
		
		CR.shaped(ST.make(this, 1, 0), CR.DEF_NCC, "BTB", "TPT", "BTB", 'B', OP.toolHeadSword.dat(mMat1), 'P', OP.plate.dat(mMat1), 'T', OP.screw.dat(mMat1));
		CR.shaped(ST.make(this, 1, 6), CR.DEF_NCC, "TBT", "BPB", "TBT", 'B', OP.toolHeadSword.dat(mMat1), 'P', OP.plate.dat(mMat1), 'T', OP.screw.dat(mMat1));
		CR.shaped(ST.make(this, 1, 8), CR.DEF_NCC, "BTB", "TPT", "BTB", 'B', OP.toolHeadSword.dat(mMat2), 'P', OP.plate.dat(mMat2), 'T', OP.screw.dat(mMat2));
		CR.shaped(ST.make(this, 1,14), CR.DEF_NCC, "TBT", "BPB", "TBT", 'B', OP.toolHeadSword.dat(mMat2), 'P', OP.plate.dat(mMat2), 'T', OP.screw.dat(mMat2));
		
		CR.shapeless(ST.make(this, 1, 7), CR.DEF_NCC, new Object[] {ST.make(this, 1, 6)});
		CR.shapeless(ST.make(this, 1,15), CR.DEF_NCC, new Object[] {ST.make(this, 1,14)});
		
		CR.shapeless(ST.make(this, 1, 6), CR.DEF_NCC, new Object[] {ST.make(this, 1, 7)});
		CR.shapeless(ST.make(this, 1,14), CR.DEF_NCC, new Object[] {ST.make(this, 1,15)});
		
		OM.data(ST.make(this, 1, 0), aMat1, U*9);
		OM.data(ST.make(this, 1, 6), aMat1, U*9);
		OM.data(ST.make(this, 1, 7), aMat1, U*9);
		OM.data(ST.make(this, 1, 8), aMat2, U*9);
		OM.data(ST.make(this, 1,14), aMat2, U*9);
		OM.data(ST.make(this, 1,15), aMat2, U*9);
		
		if (CODE_CLIENT) {
			mRenderers[ 0] = new SpikeRendererYNeg(aMat1);
			mRenderers[ 1] = new SpikeRendererYPos(aMat1);
			mRenderers[ 2] = new SpikeRendererZNeg(aMat1);
			mRenderers[ 3] = new SpikeRendererZPos(aMat1);
			mRenderers[ 4] = new SpikeRendererXNeg(aMat1);
			mRenderers[ 5] = new SpikeRendererXPos(aMat1);
			mRenderers[ 6] = mRenderers[ 7] = new SpikeRendererOmni(aMat1);
			mRenderers[ 8] = new SpikeRendererYNeg(aMat2);
			mRenderers[ 9] = new SpikeRendererYPos(aMat2);
			mRenderers[10] = new SpikeRendererZNeg(aMat2);
			mRenderers[11] = new SpikeRendererZPos(aMat2);
			mRenderers[12] = new SpikeRendererXNeg(aMat2);
			mRenderers[13] = new SpikeRendererXPos(aMat2);
			mRenderers[14] = mRenderers[15] = new SpikeRendererOmni(aMat2);
		}
		
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(this, 1, W));
	}
	
	@Override public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {if ((WD.meta(aWorld, aX, aY, aZ) & 7) != SIDE_UP) {aEntity.motionX *= 0.1; aEntity.motionZ *= 0.1;}}
	@Override public int onBlockPlaced(World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ, int aMeta) {return (aMeta & 7) < 6 ? (aMeta & 8) | OPOS[aSide] : aMeta;}
	@Override public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {if (useGravity(WD.meta(aWorld, aX, aY, aZ))) UT.Sounds.send(SFX.MC_ANVIL_LAND, 1, 2, aWorld, aX, aY, aZ);}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return aMeta < 8 ? mMat1.mToolQuality : mMat2.mToolQuality;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int damageDropped(int aMeta) {return (aMeta & 7) < 6 ? aMeta & 8 : aMeta;}
	@Override public byte maxMeta() {return 16;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return 30;}
	@Override public float getExplosionResistance(byte aMeta) {return 5;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return (aMeta & 7) < 6 && aMeta == aSide;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean useGravity(byte aMeta) {return (aMeta & 7) == 7;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return T;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return T;}
	@SuppressWarnings("unchecked") @Override public void getSubBlocks(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {aList.add(ST.make(aItem, 1, 0)); aList.add(ST.make(aItem, 1, 6)); aList.add(ST.make(aItem, 1, 7)); aList.add(ST.make(aItem, 1, 8)); aList.add(ST.make(aItem, 1, 14)); aList.add(ST.make(aItem, 1, 15));}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition aTarget, World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		return ST.make(this, 1, (aMeta & 7) < 6 ? aMeta & 8 : aMeta);
	}
	
	@Override
	public boolean rotateBlock(World aWorld, int aX, int aY, int aZ, ForgeDirection aAxis) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		return (aMeta & 7) < 6 && aWorld.setBlock(aX, aY, aZ, this, (aMeta & 8) | (((aMeta & 7) + 1) % 6), 3);
	}
	
	@Override
	public ForgeDirection[] getValidRotations(World aWorld, int aX, int aY, int aZ) {
		return (WD.meta(aWorld, aX, aY, aZ) & 7) < 6 ? ForgeDirection.VALID_DIRECTIONS : null;
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_rotator)) {
			if (aWorld.isRemote) return 0;
			int aMeta = WD.meta(aWorld, aX, aY, aZ);
			if ((aMeta & 7) >= 6) return 0;
			byte tSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			return (aMeta & 7) != tSide && aWorld.setBlock(aX, aY, aZ, this, (aMeta & 8) | tSide, 3) ? 2000 : 0;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
		switch(WD.meta(aWorld, aX, aY, aZ) & 7) {
		case SIDE_X_POS: return AxisAlignedBB.getBoundingBox(aX+0.4, aY    , aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Y_POS: return AxisAlignedBB.getBoundingBox(aX    , aY+0.4, aZ    , aX+1  , aY+1  , aZ+1  );
		case SIDE_Z_POS: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ+0.4, aX+1  , aY+1  , aZ+1  );
		case SIDE_X_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+0.6, aY+1  , aZ+1  );
		case SIDE_Y_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+0.6, aZ+1  );
		case SIDE_Z_NEG: return AxisAlignedBB.getBoundingBox(aX    , aY    , aZ    , aX+1  , aY+1  , aZ+0.6);
		default: return AxisAlignedBB.getBoundingBox(aX+0.125, aY+0.125, aZ+0.125, aX+0.875, aY+0.875, aZ+0.875);
		}
	}
	
	@Override
	public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB aAABB, @SuppressWarnings("rawtypes") List aList, Entity aEntity) {
		if (aEntity instanceof EntityItem || aEntity instanceof EntityXPOrb || aEntity instanceof IProjectile) return;
		super.addCollisionBoxesToList(aWorld, aX, aY, aZ, aAABB, aList, aEntity);
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
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return mRenderers[WD.meta(aWorld, aX, aY, aZ)];}
	
	public SpikeRendererBase[] mRenderers = new SpikeRendererBase[16];
	
	public static abstract class SpikeRendererBase implements IRenderedBlockObject {
		public ITexture mTextureNormal, mTextureUsed;
		public SpikeRendererBase(OreDictMaterial aMat) {mTextureUsed = mTextureNormal = aMat.getTextureSmooth();}
		
		@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return APRIL_FOOLS ? 5 : 13;}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTextureUsed;}
		@Override public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
		@Override public boolean renderItem (Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer, IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
		@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {mTextureUsed = mTextureNormal; return this;}
		@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {mTextureUsed = (APRIL_FOOLS ? BlockTextureDefault.get(Textures.BlockIcons.CFOAM_HARDENED, RAINBOW_ARRAY[WD.random(42069, aX, aY, aZ, 12) * 2]) : mTextureNormal); return this;}
	}
	
	public static class SpikeRendererXPos extends SpikeRendererBase {
		public SpikeRendererXPos(OreDictMaterial aMat) {super(aMat);}
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
	public static class SpikeRendererXNeg extends SpikeRendererBase {
		public SpikeRendererXNeg(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_X_NEG == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 2], PX_N[ 5], PX_P[ 7], PX_P[ 7]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 9], PX_N[ 5], PX_P[ 7], PX_P[14]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 2], PX_N[ 5], PX_P[14], PX_P[ 7]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 9], PX_N[ 5], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 8], PX_N[ 0], PX_N[ 0]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[ 4], PX_N[ 1], PX_P[ 5], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 4], PX_P[11], PX_N[ 1], PX_P[ 5], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[ 4], PX_N[ 1], PX_P[12], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 1], PX_P[11], PX_P[11], PX_N[ 1], PX_P[12], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 3], PX_P[ 3], PX_N[ 5], PX_P[ 6], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 3], PX_P[10], PX_N[ 5], PX_P[ 6], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[ 1], PX_P[10], PX_P[ 3], PX_N[ 5], PX_P[13], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[ 1], PX_P[10], PX_P[10], PX_N[ 5], PX_P[13], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 2], PX_N[ 9], PX_P[ 7], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 2], PX_P[ 9], PX_N[ 9], PX_P[ 7], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 2], PX_N[ 9], PX_P[14], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 1], PX_P[ 9], PX_P[ 9], PX_N[ 9], PX_P[14], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]); return T;
			}
		}
	}
	public static class SpikeRendererYPos extends SpikeRendererBase {
		public SpikeRendererYPos(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_Y_POS == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 5], PX_P[ 2], PX_P[ 7], PX_N[ 1], PX_P[ 7]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 5], PX_P[ 9], PX_P[ 7], PX_N[ 1], PX_P[14]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 5], PX_P[ 2], PX_P[14], PX_N[ 1], PX_P[ 7]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 5], PX_P[ 9], PX_P[14], PX_N[ 1], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 8], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[ 4], PX_P[ 5], PX_N[ 1], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[11], PX_P[ 5], PX_N[ 1], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[ 4], PX_P[12], PX_N[ 1], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[11], PX_P[12], PX_N[ 1], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 5], PX_P[ 3], PX_P[ 6], PX_N[ 1], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 5], PX_P[10], PX_P[ 6], PX_N[ 1], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[ 3], PX_P[13], PX_N[ 1], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[10], PX_P[13], PX_N[ 1], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 2], PX_P[ 7], PX_N[ 1], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 9], PX_P[ 7], PX_N[ 1], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 2], PX_P[14], PX_N[ 1], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_P[14], PX_N[ 1], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
		}
	}
	public static class SpikeRendererYNeg extends SpikeRendererBase {
		public SpikeRendererYNeg(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_Y_NEG == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_P[ 7], PX_N[ 5], PX_P[ 7]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 9], PX_P[ 7], PX_N[ 5], PX_P[14]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 2], PX_P[14], PX_N[ 5], PX_P[ 7]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 9], PX_P[14], PX_N[ 5], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[ 4], PX_P[ 5], PX_N[ 1], PX_P[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 1], PX_P[11], PX_P[ 5], PX_N[ 1], PX_P[12]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[ 4], PX_P[12], PX_N[ 1], PX_P[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[ 1], PX_P[11], PX_P[12], PX_N[ 1], PX_P[12]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 1], PX_P[ 3], PX_P[ 6], PX_N[ 5], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 1], PX_P[10], PX_P[ 6], PX_N[ 5], PX_P[13]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 1], PX_P[ 3], PX_P[13], PX_N[ 5], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 1], PX_P[10], PX_P[13], PX_N[ 5], PX_P[13]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_P[ 7], PX_N[ 9], PX_P[ 7]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 1], PX_P[ 9], PX_P[ 7], PX_N[ 9], PX_P[14]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 2], PX_P[14], PX_N[ 9], PX_P[ 7]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 1], PX_P[ 9], PX_P[14], PX_N[ 9], PX_P[14]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]); return T;
			}
		}
	}
	public static class SpikeRendererZPos extends SpikeRendererBase {
		public SpikeRendererZPos(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_Z_POS == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 5], PX_P[ 7], PX_P[ 7], PX_N[ 1]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 5], PX_P[ 7], PX_P[14], PX_N[ 1]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 5], PX_P[14], PX_P[ 7], PX_N[ 1]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 5], PX_P[14], PX_P[14], PX_N[ 1]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 8], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_P[ 5], PX_N[ 1]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[11], PX_P[ 1], PX_P[ 5], PX_P[12], PX_N[ 1]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 4], PX_P[ 1], PX_P[12], PX_P[ 5], PX_N[ 1]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[11], PX_P[ 1], PX_P[12], PX_P[12], PX_N[ 1]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 3], PX_P[ 5], PX_P[ 6], PX_P[ 6], PX_N[ 1]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[10], PX_P[ 5], PX_P[ 6], PX_P[13], PX_N[ 1]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 3], PX_P[ 5], PX_P[13], PX_P[ 6], PX_N[ 1]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 5], PX_P[13], PX_P[13], PX_N[ 1]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 9], PX_P[ 7], PX_P[ 7], PX_N[ 1]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 9], PX_P[ 7], PX_P[14], PX_N[ 1]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 9], PX_P[14], PX_P[ 7], PX_N[ 1]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 9], PX_P[14], PX_P[14], PX_N[ 1]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
			}
		}
	}
	public static class SpikeRendererZNeg extends SpikeRendererBase {
		public SpikeRendererZNeg(OreDictMaterial aMat) {super(aMat);}
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDE_Z_NEG == aSide && (aRenderPass != 0 || !aShouldSideBeRendered[aSide]) ? null : super.getTexture(aBlock, aRenderPass, aSide, aShouldSideBeRendered);}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			if (APRIL_FOOLS) switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 1], PX_P[ 7], PX_P[ 7], PX_N[ 5]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 1], PX_P[ 7], PX_P[14], PX_N[ 5]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 1], PX_P[14], PX_P[ 7], PX_N[ 5]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 1], PX_P[14], PX_P[14], PX_N[ 5]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 8]); return T;
			}
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_P[ 5], PX_N[ 1]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 4], PX_P[11], PX_P[ 1], PX_P[ 5], PX_P[12], PX_N[ 1]); return T;
			case  3: aBlock.setBlockBounds(PX_P[11], PX_P[ 4], PX_P[ 1], PX_P[12], PX_P[ 5], PX_N[ 1]); return T;
			case  4: aBlock.setBlockBounds(PX_P[11], PX_P[11], PX_P[ 1], PX_P[12], PX_P[12], PX_N[ 1]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 3], PX_P[ 3], PX_P[ 1], PX_P[ 6], PX_P[ 6], PX_N[ 5]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 3], PX_P[10], PX_P[ 1], PX_P[ 6], PX_P[13], PX_N[ 5]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 3], PX_P[ 1], PX_P[13], PX_P[ 6], PX_N[ 5]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 1], PX_P[13], PX_P[13], PX_N[ 5]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 2], PX_P[ 1], PX_P[ 7], PX_P[ 7], PX_N[ 9]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 2], PX_P[ 9], PX_P[ 1], PX_P[ 7], PX_P[14], PX_N[ 9]); return T;
			case 11: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 2], PX_P[ 1], PX_P[14], PX_P[ 7], PX_N[ 9]); return T;
			case 12: aBlock.setBlockBounds(PX_P[ 9], PX_P[ 9], PX_P[ 1], PX_P[14], PX_P[14], PX_N[ 9]); return T;
			default: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]); return T;
			}
		}
	}
	public static class SpikeRendererOmni extends SpikeRendererBase {
		public SpikeRendererOmni(OreDictMaterial aMat) {super(aMat);}
		@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 13;}
		@Override
		public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
			switch(aRenderPass) {
			case  1: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 5], PX_P[ 5], PX_N[ 0], PX_P[ 6], PX_P[ 6]); return T;
			case  2: aBlock.setBlockBounds(PX_P[ 0], PX_P[ 5], PX_P[10], PX_N[ 0], PX_P[ 6], PX_P[11]); return T;
			case  3: aBlock.setBlockBounds(PX_P[ 0], PX_P[10], PX_P[ 5], PX_N[ 0], PX_P[11], PX_P[ 6]); return T;
			case  4: aBlock.setBlockBounds(PX_P[ 0], PX_P[10], PX_P[10], PX_N[ 0], PX_P[11], PX_P[11]); return T;
			case  5: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_P[ 6], PX_N[ 0], PX_P[ 6]); return T;
			case  6: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 0], PX_P[10], PX_P[ 6], PX_N[ 0], PX_P[11]); return T;
			case  7: aBlock.setBlockBounds(PX_P[10], PX_P[ 0], PX_P[ 5], PX_P[11], PX_N[ 0], PX_P[ 6]); return T;
			case  8: aBlock.setBlockBounds(PX_P[10], PX_P[ 0], PX_P[10], PX_P[11], PX_N[ 0], PX_P[11]); return T;
			case  9: aBlock.setBlockBounds(PX_P[ 5], PX_P[ 5], PX_P[ 0], PX_P[ 6], PX_P[ 6], PX_N[ 0]); return T;
			case 10: aBlock.setBlockBounds(PX_P[ 5], PX_P[10], PX_P[ 0], PX_P[ 6], PX_P[11], PX_N[ 0]); return T;
			case 11: aBlock.setBlockBounds(PX_P[10], PX_P[ 5], PX_P[ 0], PX_P[11], PX_P[ 6], PX_N[ 0]); return T;
			case 12: aBlock.setBlockBounds(PX_P[10], PX_P[10], PX_P[ 0], PX_P[11], PX_P[11], PX_N[ 0]); return T;
			default: aBlock.setBlockBounds(PX_P[ 4], PX_P[ 4], PX_P[ 4], PX_N[ 4], PX_N[ 4], PX_N[ 4]); return T;
			}
		}
	}
}
