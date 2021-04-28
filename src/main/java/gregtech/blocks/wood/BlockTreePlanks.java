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

package gregtech.blocks.wood;

import static gregapi.data.CS.*;

import gregapi.block.metatype.BlockBasePlanksFlammable;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.ANY;
import gregapi.data.LH;
import gregapi.data.OD;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

public class BlockTreePlanks extends BlockBasePlanksFlammable {
	public BlockTreePlanks(String aName) {
		super(ItemBlockMetaType.class, Material.wood, soundTypeWood, aName, "", ANY.Wood, 1, 1, 0, 16, Textures.BlockIcons.PLANKS);
		LH.add(getUnlocalizedName()+ ".0.name", "Rubberwood Planks");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Planks");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Planks");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Planks");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Planks");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Planks");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Planks");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Planks");
		LH.add(getUnlocalizedName()+ ".8.name", "Compressed Wood Planks");
		LH.add(getUnlocalizedName()+ ".9.name", "Wood Planks");
		LH.add(getUnlocalizedName()+".10.name", "Treated Planks");
		LH.add(getUnlocalizedName()+".11.name", "Crate");
		LH.add(getUnlocalizedName()+".12.name", "Dead Planks");
		LH.add(getUnlocalizedName()+".13.name", "Rotten Planks");
		LH.add(getUnlocalizedName()+".14.name", "Mossy Planks");
		LH.add(getUnlocalizedName()+".15.name", "Frozen Planks");
		
		for (int i = 0; i < maxMeta(); i++) {
			if (i != 10) OM.reg(ST.make(this, 1, i), OD.plankWood);
			for (byte tSide : ALL_SIDES_VALID) OM.reg(ST.make(mSlabs[tSide], 1, i), OD.slabWood);
		}
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockTreePlanks(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockTreePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		LH.add(getUnlocalizedName()+ ".0.name", "Rubberwood Slab");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Slab");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Slab");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Slab");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Slab");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Slab");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Slab");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Slab");
		LH.add(getUnlocalizedName()+ ".8.name", "Compressed Wood Slab");
		LH.add(getUnlocalizedName()+ ".9.name", "Wood Slab");
		LH.add(getUnlocalizedName()+".10.name", "Treated Slab");
		LH.add(getUnlocalizedName()+".11.name", "Crate Slab");
		LH.add(getUnlocalizedName()+".12.name", "Dead Slab");
		LH.add(getUnlocalizedName()+".13.name", "Rotten Slab");
		LH.add(getUnlocalizedName()+".14.name", "Mossy Slab");
		LH.add(getUnlocalizedName()+".15.name", "Frozen Slab");
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return (aWorld.getBlockMetadata(aX, aY, aZ) < 12 ? 1.0F : 0.5F) * super.getBlockHardness(aWorld, aX, aY, aZ);}
}
