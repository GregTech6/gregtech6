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

package gregapi.block.tree;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ModIDs;
import gregapi.data.MD;
import gregapi.data.OP;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock", modid = ModIDs.GC)
})
public abstract class BlockBaseLeaves extends BlockBaseTree implements IShearable, IOxygenReliantBlock {
	public final Block mSaplings;
	public final Block[] mLogs;
	public final byte[] mLogMetas;
	
	public BlockBaseLeaves(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons, Block aSaplings, Block[] aLogs, byte[] aLogMetas) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, Math.min(8, aMaxMeta), aIcons);
		setCreativeTab(CreativeTabs.tabDecorations);
		mSaplings = aSaplings;
		mLogMetas = aLogMetas;
		mLogs = aLogs;
		setHardness(0.2F);
	}
	
	@Override public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return F;}
	@Override public int getFlammability(byte aMeta) {return 30;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 60;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_sword;}
	@Override public int damageDropped(int aMeta) {return aMeta & 7;}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return WD.meta(aWorld, aX, aY, aZ) & 7;}
	@Override public Item getItemDropped(int aMeta, Random aRandom, int aFortune) {return Item.getItemFromBlock(mSaplings);}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.leaves.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.leaves.getExplosionResistance(null);}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public boolean isSideSolid(int aMeta, byte aSide) {return F;}
	@Override public boolean isLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return T;}
	@Override public boolean isShearable(ItemStack aItem, IBlockAccess aWorld, int aX, int aY, int aZ) {return T;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_LEAVES;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.treeLeaves.mDefaultStackSize);}
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[(aMeta&7)|(Blocks.leaves.isOpaqueCube()?8:0)].getIcon(0);}
	@Override public ArrayList<ItemStack> onSheared(ItemStack aItem, IBlockAccess aWorld, int aX, int aY, int aZ, int aFortune) {return new ArrayListNoNulls<>(F, ST.make(this, 1, WD.meta(aWorld, aX, aY, aZ) & 7));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return MD.TFC.mLoaded || MD.TFCP.mLoaded ? null : super.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ);}
	@Override public void onOxygenAdded(World aWorld, int aX, int aY, int aZ) {/**/}
	@Override public void onOxygenRemoved(World aWorld, int aX, int aY, int aZ) {if (!aWorld.isRemote) {aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 201+RNGSUS.nextInt(100)); return;}}
	
	@Override
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {
		if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 201+RNGSUS.nextInt(100)); return;}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		return !(aBlock.isOpaqueCube() || (Blocks.leaves.isOpaqueCube() && aBlock instanceof BlockBaseLeaves));
	}
	
	@Override
	public void beginLeavesDecay(World aWorld, int aX, int aY, int aZ) {
		if (aWorld.isRemote) return;
		if (!WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 201+RNGSUS.nextInt(100)); return;}
		if (WD.meta(aWorld, aX, aY, aZ) < 8) return;
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 1+RNGSUS.nextInt(100));
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (aWorld.isRemote) return;
		if (!WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.setBlockToAir(aX, aY, aZ); return;}
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aMeta < 8) return;
		int tRangeSide = getLeavesRangeSide(aMeta), tRangeYNeg = getLeavesRangeYNeg(aMeta), tRangeYPos = getLeavesRangeYPos(aMeta);
		for (int i = -tRangeSide; i <= tRangeSide; ++i) for (int j = -tRangeYNeg; j <= tRangeYPos; ++j) for (int k = -tRangeSide; k <= tRangeSide; ++k) {
			if (mLogs    [aMeta & 7] != WD.block(aWorld, aX + i, aY + j, aZ + k)) continue;
			if (mLogMetas[aMeta & 7] != (WD.meta(aWorld, aX + i, aY + j, aZ + k) & 3)) continue;
			return;
		}
		if (!(MD.TFC.mLoaded || MD.TFCP.mLoaded) || aRandom.nextInt(4) == 0) dropBlockAsItem(aWorld, aX, aY, aZ, aMeta, 0);
		aWorld.setBlockToAir(aX, aY, aZ);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		ArrayListNoNulls<ItemStack> rDrops = new ArrayListNoNulls<>();
		int tChance = 50;
		if (aFortune > 0) {
			tChance -= 5 << aFortune;
			if (tChance < 5) tChance = 5;
		}
		if (RNGSUS.nextInt(tChance) == 0) rDrops.add(ST.make(getItemDropped(aMeta, RNGSUS, aFortune), 1, damageDropped(aMeta)));
		return rDrops;
	}
	
	@Override @SideOnly(Side.CLIENT)
	public int getBlockColor() {return ColorizerFoliage.getFoliageColor(0.5, 1.0);}
	@Override @SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_) {return ColorizerFoliage.getFoliageColorBasic();}
	@Override @SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
		int l = 0, i1 = 0, j1 = 0;
		for (int k1 = -1; k1 <= 1; ++k1) for (int l1 = -1; l1 <= 1; ++l1) {
			int i2 = aWorld.getBiomeGenForCoords(aX + l1, aZ + k1).getBiomeFoliageColor(aX + l1, aY, aZ + k1);
			l += (i2 & 16711680) >> 16;
			i1 += (i2 & 65280) >> 8;
			j1 += i2 & 255;
		}
		return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
	}
}
