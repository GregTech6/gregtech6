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

package gregtech.blocks.plants;

import gregapi.block.misc.BlockBaseLilyPad;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;

import static gregapi.data.CS.*;

public class BlockGlowtus extends BlockBaseLilyPad implements Runnable {
	public BlockGlowtus(String aUnlocalised) {
		super(null, aUnlocalised, Material.plants, soundTypeGrass, 16, Textures.BlockIcons.GLOWTUS);
		
		GT.mBeforePostInit.add(this);
		
		for (int i = 0; i < 16; i++) LH.add(getUnlocalizedName()+"."+i, DYE_NAMES[i] + " Glowtus");
		
		OM.data(ST.make(this, 1, W), MT.Glowstone, U4);
	}
	
	@Override
	public void run() {
		RM.biomass(ST.make(this, 4, W));
		RM.mortarize(ST.make(this, 1, W), OM.dust(MT.Glowstone, U4));
		RM.ic2_extractor(ST.make(this, 1, W), OM.dust(MT.Glowstone, U4));
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getLightValue() {return 15;}
}
