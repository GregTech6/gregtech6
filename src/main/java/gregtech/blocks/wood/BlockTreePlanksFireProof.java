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

import gregapi.block.metatype.BlockBasePlanks;
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

public class BlockTreePlanksFireProof extends BlockBasePlanks {
	public BlockTreePlanksFireProof(String aName) {
		super(ItemBlockMetaType.class, Material.wood, soundTypeWood, aName, "", ANY.Wood, 1, 1, 0, 16, Textures.BlockIcons.PLANKS);
		LH.add(getUnlocalizedName()+ ".0.name", "Rubberwood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Compressed Wood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Wood Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Treated Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Crate (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Dead Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Rotten Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Mossy Planks (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Frozen Planks (Fireproof)");
		
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
		LH.add(getUnlocalizedName()+ ".0.name", "Rubberwood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Compressed Wood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Wood Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Treated Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Crate Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Dead Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Rotten Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Mossy Slab (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Frozen Slab (Fireproof)");
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return (aWorld.getBlockMetadata(aX, aY, aZ) < 12 ? 1.0F : 0.5F) * super.getBlockHardness(aWorld, aX, aY, aZ);}
}
