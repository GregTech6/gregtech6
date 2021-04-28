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

public class BlockTreePlanks2FireProof extends BlockBasePlanks {
	public BlockTreePlanks2FireProof(String aName) {
		super(ItemBlockMetaType.class, Material.wood, soundTypeWood, aName, "", ANY.Wood, 1, 1, 0, 1, Textures.BlockIcons.PLANKS_2);
		LH.add(getUnlocalizedName()+ ".0.name", "Blue Spruce Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".1.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".2.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".3.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".4.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".5.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".6.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".7.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".8.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".9.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".10.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".11.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".12.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".13.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".14.name", " Planks (Fireproof)");
	//  LH.add(getUnlocalizedName()+".15.name", " Planks (Fireproof)");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OD.plankWood);
			for (byte tSide : ALL_SIDES_VALID) OM.reg(ST.make(mSlabs[tSide], 1, i), OD.slabWood);
		}
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockTreePlanks2FireProof(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockTreePlanks2FireProof(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		LH.add(getUnlocalizedName()+ ".0.name", "Blue Spruce Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".1.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".2.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".3.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".4.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".5.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".6.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".7.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".8.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+ ".9.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".10.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".11.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".12.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".13.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".14.name", " Slab (Fireproof)");
	//  LH.add(getUnlocalizedName()+".15.name", " Slab (Fireproof)");
	}
}
