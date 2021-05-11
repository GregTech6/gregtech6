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

package gregtech.blocks.plants;

import static gregapi.data.CS.*;

import gregapi.block.misc.BlockBaseLilyPad;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockGlowtus extends BlockBaseLilyPad {
	public BlockGlowtus(String aUnlocalised) {
		super(null, aUnlocalised, Material.plants, soundTypeGrass, 16, Textures.BlockIcons.GLOWTUS);
		for (int i = 0; i < 16; i++) LH.add(getUnlocalizedName()+"."+i+".name", DYE_NAMES[i] + " Glowtus");
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getLightValue() {return 15;}
}
