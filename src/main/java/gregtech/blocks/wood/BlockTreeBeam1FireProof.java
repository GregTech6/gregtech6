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

import gregapi.block.tree.BlockBaseBeam;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam1FireProof extends BlockBaseBeam {
	public BlockTreeBeam1FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_1);
		
		LH.add(getUnlocalizedName()+ ".0", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".12", "Oak Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".13", "Spruce Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".10", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".14", "Birch Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".11", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".15", "Jungle Beam (Fireproof)");
	}
}
