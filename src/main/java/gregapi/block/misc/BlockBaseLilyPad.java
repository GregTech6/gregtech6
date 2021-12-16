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

package gregapi.block.misc;

import static gregapi.data.CS.*;
import static net.minecraftforge.common.EnumPlantType.*;

import java.util.List;
import java.util.Random;

import gregapi.block.BlockBaseMeta;
import gregapi.block.ItemBlockBase;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.IRenderedBlock;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.ITexture;
import gregapi.render.RendererBlockTextured;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockBaseLilyPad extends BlockBaseMeta implements IPlantable, IRenderedBlock {
	public BlockBaseLilyPad(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(ItemBlockBase.class, aNameInternal, Material.plants, soundTypeGrass, aMaxMeta, aIcons);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
		RM.chisel(aNameInternal, ST.make(this, 1, W));
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(this, 1, W));
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_sword;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB aAABB, @SuppressWarnings("rawtypes") List aList, Entity aEntity) {if (!(aEntity instanceof EntityBoat)) super.addCollisionBoxesToList(aWorld, aX, aY, aZ, aAABB, aList, aEntity);}
	@Override public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {return aY >= 0 && aY < 256 && aWorld.getBlock(aX, aY - 1, aZ).getMaterial() == Material.water && aWorld.getBlockMetadata(aX, aY - 1, aZ) == 0;}
	@Override public boolean canPlaceBlockAt(World aWorld, int aX, int aY, int aZ) {return super.canPlaceBlockAt(aWorld, aX, aY, aZ) && canBlockStay(aWorld, aX, aY, aZ);}
	@Override public boolean checkNoEntityCollision(World aWorld, int aX, int aY, int aZ, byte aMeta, Entity aExceptThisOne) {return T;}
	@Override public void onNeighborBlockChange2(World aWorld, int aX, int aY, int aZ, Block aBlock) {checkAndDropBlock(aWorld, aX, aY, aZ);}
	@Override public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {checkAndDropBlock(aWorld, aX, aY, aZ);}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public EnumPlantType getPlantType(IBlockAccess aWorld, int aX, int aY, int aZ) {return Water;}
	@Override public Block getPlant(IBlockAccess aWorld, int aX, int aY, int aZ) {return this;}
	@Override public int getPlantMetadata(IBlockAccess aWorld, int aX, int aY, int aZ) {return WD.meta(aWorld, aX, aY, aZ);}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.waterlily.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.waterlily.getExplosionResistance(null);}
	@Override public int getItemStackLimit(ItemStack aStack) {return 64;}
	
	public void checkAndDropBlock(World aWorld, int aX, int aY, int aZ) {
		if (!canBlockStay(aWorld, aX, aY, aZ)) {
			dropBlockAsItem(aWorld, aX, aY, aZ, WD.meta(aWorld, aX, aY, aZ), 0);
			aWorld.setBlock(aX, aY, aZ, getBlockById(0), 0, 2);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		MovingObjectPosition tPos = WD.getMOP(aWorld, aPlayer, T);
		if (tPos == null || tPos.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return aStack;
		int aX = tPos.blockX, aY = tPos.blockY, aZ = tPos.blockZ;
		if (!aWorld.canMineBlock(aPlayer, aX, aY, aZ) || !aPlayer.canPlayerEdit(aX, aY, aZ, tPos.sideHit, aStack)) return aStack;
		if (aWorld.getBlock(aX, aY, aZ).getMaterial() == Material.water && WD.meta(aWorld, aX, aY, aZ) == 0 && aWorld.isAirBlock(aX, aY+1, aZ)) {
			aWorld.setBlock(aX, aY+1, aZ, this, ST.meta_(aStack), 3);
			if (!UT.Entities.hasInfiniteItems(aPlayer)) {aStack.stackSize--;}
		}
		return aStack;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+0.015625F, aZ+1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool (World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+0.015625F, aZ+1);}
	@Override public void setBlockBoundsBasedOnState(IBlockAccess aWorld, int aX, int aY, int aZ) {setBlockBounds(0, 0, 0, 1, 0.015625F, 1);}
	
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[aMeta % mIcons.length].getIcon(0);}
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?23:RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {return SIDES_VERTICAL[aSide] ? BlockTextureDefault.get(mIcons[ST.meta_(aStack) % mIcons.length]) : null;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {return SIDES_VERTICAL[aSide] ? BlockTextureDefault.get(mIcons[WD.meta(aWorld, aX, aY, aZ) % mIcons.length]) : null;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {setBlockBounds(0, 0, 0, 1, 0.015625F, 1); return T;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {setBlockBounds(0, 0, 0, 1, 0.015625F, 1); return T;}
	@Override public int getRenderPasses(ItemStack aStack) {return 1;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return null;}
	@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return null;}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return T;}
}
