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

package gregapi.block.tree;

import static gregapi.data.CS.*;
import static net.minecraftforge.common.EnumPlantType.*;

import java.util.Random;

import cpw.mods.fml.common.Optional;
import gregapi.block.BlockBaseMeta;
import gregapi.data.CS.ModIDs;
import gregapi.data.OP;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import gregapi.util.WD;
import micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.TerrainGen;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock", modid = ModIDs.GC)
})
public abstract class BlockBaseSapling extends BlockBaseMeta implements IPlantable, IGrowable, IOxygenReliantBlock {
	public BlockBaseSapling(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, Math.min(8, aMaxMeta), aIcons);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		setTickRandomly(T);
		setHardness(0);
	}
	
	public abstract boolean grow(World aWorld, int aX, int aY, int aZ, byte aMeta, Random aRandom);
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_sword;}
	@Override public int damageDropped(int aMeta) {return aMeta & 7;}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return aWorld.getBlockMetadata(aX, aY, aZ) & 7;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.sapling.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.sapling.getExplosionResistance(null);}
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return T;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_LEAVES;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.treeSapling.mDefaultStackSize);}
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[aMeta & 15].getIcon(0);}
	@Override public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY - 1, aZ).canSustainPlant(aWorld, aX, aY - 1, aZ, ForgeDirection.UP, (IPlantable)Blocks.sapling);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return null;}
	@Override public int getRenderType() {return 1;}
	@Override public void onOxygenAdded(World aWorld, int aX, int aY, int aZ) {/**/}
	@Override public void onOxygenRemoved(World aWorld, int aX, int aY, int aZ) {if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.setBlock(aX, aY, aZ, Blocks.deadbush, 0, 3); return;}}
	
	@Override
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {
		if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.setBlock(aX, aY, aZ, Blocks.deadbush, 0, 3); return;}
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.setBlock(aX, aY, aZ, Blocks.deadbush, 0, 3); return;}
		if (aWorld.isRemote || checkAndDropBlock(aWorld, aX, aY, aZ) || aWorld.getBlockLightValue(aX, aY+1, aZ) < 9 || aRandom.nextInt(7) != 0) return;
		tryGrow(aWorld, aX, aY, aZ, aRandom);
	}
	
	public boolean tryGrow(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {
			aWorld.setBlock(aX, aY, aZ, Blocks.deadbush, 0, 3);
			return F;
		}
		if (TREE_GROWTH_TIME > 1 && RNGSUS.nextInt(TREE_GROWTH_TIME) > 0) return F;
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (aMeta < 8) {
			aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMeta | 8, 2);
			return F;
		}
		return TerrainGen.saplingGrowTree(aWorld, aRandom, aX, aY, aZ) && grow(aWorld, aX, aY, aZ, (byte)(aMeta & 7), aRandom);
	}
	
	public int getMaxHeight(World aWorld, int aX, int aY, int aZ, int aMaxTreeHeight) {
		aMaxTreeHeight--;
		int rMaxHeight = 0;
		while (rMaxHeight++ < aMaxTreeHeight) if (aY+rMaxHeight >= aWorld.getHeight() || !canPlaceTree(aWorld, aX, aY+rMaxHeight, aZ)) return rMaxHeight-1;
		return rMaxHeight;
	}
	
	public boolean placeTree(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta) {
		return canPlaceTree(aWorld, aX, aY, aZ) && WD.set(aWorld, aX, aY, aZ, aBlock, aMeta, 3);
	}
	
	public boolean canPlaceTree(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		return tBlock instanceof BlockTallGrass || tBlock.canBeReplacedByLeaves(aWorld, aX, aY, aZ);
	}
	
	@Override public boolean canPlaceBlockAt(World aWorld, int aX, int aY, int aZ) {return super.canPlaceBlockAt(aWorld, aX, aY, aZ) && canBlockStay(aWorld, aX, aY, aZ);}
	
	@Override
	public void onNeighborBlockChange2(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		checkAndDropBlock(aWorld, aX, aY, aZ);
	}
	
	public boolean checkAndDropBlock(World aWorld, int aX, int aY, int aZ) {
		if (canBlockStay(aWorld, aX, aY, aZ)) return F;
		dropBlockAsItem(aWorld, aX, aY, aZ, aWorld.getBlockMetadata(aX, aY, aZ), 0);
		aWorld.setBlock(aX, aY, aZ, NB, 0, 2);
		return T;
	}
	
	@Override public EnumPlantType getPlantType(IBlockAccess aWorld, int aX, int aY, int aZ) {return Plains;}
	@Override public Block getPlant(IBlockAccess aWorld, int aX, int aY, int aZ) {return this;}
	@Override public int getPlantMetadata(IBlockAccess aWorld, int aX, int aY, int aZ) {return aWorld.getBlockMetadata(aX, aY, aZ);}
	@Override public boolean func_149851_a(World aWorld, int aX, int aY, int aZ, boolean aIsRemote) {return T;}
	@Override public boolean func_149852_a(World aWorld, Random aRandom, int aX, int aY, int aZ) {return aRandom.nextFloat() < 0.45;}
	@Override public void func_149853_b(World aWorld, Random aRandom, int aX, int aY, int aZ) {tryGrow(aWorld, aX, aY, aZ, aRandom);}
}
