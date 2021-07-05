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

package gregapi.block.metatype;

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.LH;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public class BlockColored extends BlockMetaType {
	public BlockColored(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, IIconContainer[] aIcons) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, 16, aIcons);
		if (aDefaultLocalised != null) for (int i = 0; i < 16; i++) LH.add(getUnlocalizedName()+"."+i+".name", DYE_NAMES[i] + " " + aDefaultLocalised);
		if (aMaterial != null) for (int i = 0; i < 16; i++) OM.data(ST.make(this, 1, i), new OreDictItemData(aMaterial, U));
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockColored(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockColored(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		if (aDefaultLocalised != null) for (int i = 0; i < 16; i++) LH.add(getUnlocalizedName()+"."+i+".name", DYE_NAMES[i] + " " + aDefaultLocalised + " Slab");
		if (aMaterial != null) for (int i = 0; i < 16; i++) OM.data(ST.make(this, 1, i), new OreDictItemData(aMaterial, U2));
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int aMeta) {
		return DYES_INT[UT.Code.bind4(aMeta)];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return DYES_INT[UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ))];
	}
	
	@Override
	public boolean recolourBlock(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection, int aColor) {
		aColor = ~aColor & 15;
		return aWorld.getBlockMetadata(aX, aY, aZ) != aColor && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, UT.Code.bind4(aColor), 3);
	}
}
