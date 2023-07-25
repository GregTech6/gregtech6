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

package gregtech.blocks;

import gregapi.block.IBlockFoamable;
import gregapi.block.metatype.BlockColored;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.CS.*;
import gregapi.data.MT;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import static gregapi.data.CS.*;

public class BlockCFoamFresh extends BlockColored implements IBlockFoamable {
	public BlockCFoamFresh(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.sponge, soundTypeCloth, aUnlocalised, "Fresh C-Foam", MT.ConstructionFoam, 1.0F, 0.0F, 0, Textures.BlockIcons.CFOAMS_FRESH);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockCFoamFresh(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockCFoamFresh(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	@Override
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {
		if (!aWorld.isRemote) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 100+RNGSUS.nextInt(5900));
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!aWorld.isRemote) dryFoam(aWorld, aX, aY, aZ, SIDE_ANY);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		return ST.arraylist();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return F;
	}
	
	@Override
	public boolean isSideSolid(int aMeta, byte aSide) {
		return F;
	}
	
	@Override
	public boolean isSealable(byte aMeta, byte aSide) {
		return F;
	}
	
	@Override
	public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return T;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
		return null;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		return F;
	}
	
	@Override
	public boolean applyFoam(World aWorld, int aX, int aY, int aZ, byte aSide, short[] aCFoamRGB, byte aVanillaColor) {
		return F;
	}
	
	@Override
	public boolean dryFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return aWorld.setBlock(aX, aY, aZ, SIDES_VALID[mSide]?((BlockMetaType)BlocksGT.CFoam).mSlabs[mSide]:BlocksGT.CFoam, WD.meta(aWorld, aX, aY, aZ), 3);
	}
	
	@Override
	public boolean removeFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return aWorld.setBlock(aX, aY, aZ, NB, 0, 3);
	}
	
	@Override
	public boolean hasFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return T;
	}
	
	@Override
	public boolean driedFoam(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return F;
	}
}
