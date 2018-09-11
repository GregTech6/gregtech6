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

package gregtech.old;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.BlockBaseSealable;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class GT_Block_Casings_Abstract extends BlockBaseSealable {
	public GT_Block_Casings_Abstract(String aName, Material aMaterial) {
		super(null, aName, aMaterial, soundTypeMetal);
		ITileEntityMachineBlockUpdateable.Util.registerMachineBlock(this, ~0);
	}
	
	@Override
	public String getHarvestTool(int aMeta) {
		return "wrench";
	}
	
	@Override
	public int getHarvestLevel(int aMeta) {
		return 2;
	}
	
	@Override
	public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {
		return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);
	}
	
	@Override
	public float getExplosionResistance(Entity aTNT) {
		return Blocks.iron_block.getExplosionResistance(aTNT);
	}
	
	@Override
	public boolean canSilkHarvest() {
		return false;
	}
	
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return false;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)	{return true;}
	@Override public boolean renderAsNormalBlock() {return true;}
	@Override public boolean isOpaqueCube() {return true;}
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return false;
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
	
	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister aIconRegister) {
		//
	}
	
	@Override @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item aItem, CreativeTabs par2CreativeTabs, List aList) {
		for (int i = 0; i < 16; i++) aList.add(ST.make(aItem, 1, i));
	}
}
