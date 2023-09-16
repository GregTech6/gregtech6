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

package gregtech.blocks.wood;

import gregapi.block.metatype.BlockBasePlanks;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.*;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

import static gregapi.data.CS.ALL_SIDES_VALID;

public class BlockTreePlanksFireProof extends BlockBasePlanks {
	public BlockTreePlanksFireProof(String aName) {
		super(ItemBlockMetaType.class, Material.wood, soundTypeWood, aName, "", ANY.Wood, 1, 1, 0, 16, Textures.BlockIcons.PLANKS);
		LH.add(getUnlocalizedName()+ ".0", "Rubberwood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".1", "Maple Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".2", "Willow Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".3", "Blue Mahoe Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4", "Hazel Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5", "Cinnamon Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6", "Coconut Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7", "Rainbowood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8", "Compressed Wood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9", "Wood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".10", "Treated Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".11", "Crate (Fireproof)");
		LH.add(getUnlocalizedName()+".12", "Dead Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".13", "Rotten Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".14", "Mossy Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".15", "Frozen Planks (Fireproof)");
		
		OM.reg(ST.make(this, 1, 7), OP.plank.dat(MT.WOODS.Rainbowood));
		
		for (int i = 0; i < maxMeta(); i++) {
			if (i != 10) OM.reg(ST.make(this, 1, i), OD.plankWood);
			for (byte tSide : ALL_SIDES_VALID) OM.reg(ST.make(mSlabs[tSide], 1, i), OD.slabWood);
		}
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockTreePlanksFireProof(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockTreePlanksFireProof(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		LH.add(getUnlocalizedName()+ ".0", "Rubberwood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".1", "Maple Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".2", "Willow Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".3", "Blue Mahoe Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4", "Hazel Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5", "Cinnamon Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6", "Coconut Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7", "Rainbowood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8", "Compressed Wood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9", "Wood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".10", "Treated Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".11", "Crate Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".12", "Dead Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".13", "Rotten Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".14", "Mossy Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".15", "Frozen Slab (Fireproof)");
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return (aWorld.getBlockMetadata(aX, aY, aZ) < 12 ? 1.0F : 0.5F) * super.getBlockHardness(aWorld, aX, aY, aZ);}
}
