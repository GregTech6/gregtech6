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

package gregtech.blocks;

import gregapi.block.misc.BlockBaseBale;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;

public class BlockBaleCrop extends BlockBaseBale {
	public BlockBaleCrop(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 4, Textures.BlockIcons.BALES_CROP);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Rye Bale");
		LH.add(getUnlocalizedName()+ ".4.name", "Rye Bale");
		LH.add(getUnlocalizedName()+ ".8.name", "Rye Bale");
		LH.add(getUnlocalizedName()+".12.name", "Rye Bale");
		OM.reg(ST.make(this, 1, 0), "baleRye");
		OM.reg(ST.make(this, 1, 4), "baleRye");
		OM.reg(ST.make(this, 1, 8), "baleRye");
		OM.reg(ST.make(this, 1,12), "baleRye");
		IL.Bale_Rye.set(ST.make(this, 1, 0));
		
		LH.add(getUnlocalizedName()+ ".1.name", "Oats Bale");
		LH.add(getUnlocalizedName()+ ".5.name", "Oats Bale");
		LH.add(getUnlocalizedName()+ ".9.name", "Oats Bale");
		LH.add(getUnlocalizedName()+".13.name", "Oats Bale");
		OM.reg(ST.make(this, 1, 1), "baleOats");
		OM.reg(ST.make(this, 1, 5), "baleOats");
		OM.reg(ST.make(this, 1, 9), "baleOats");
		OM.reg(ST.make(this, 1,13), "baleOats");
		IL.Bale_Oats.set(ST.make(this, 1, 1));
		
		LH.add(getUnlocalizedName()+ ".2.name", "Barley Bale");
		LH.add(getUnlocalizedName()+ ".6.name", "Barley Bale");
		LH.add(getUnlocalizedName()+".10.name", "Barley Bale");
		LH.add(getUnlocalizedName()+".14.name", "Barley Bale");
		OM.reg(ST.make(this, 1, 2), "baleBarley");
		OM.reg(ST.make(this, 1, 6), "baleBarley");
		OM.reg(ST.make(this, 1,10), "baleBarley");
		OM.reg(ST.make(this, 1,14), "baleBarley");
		IL.Bale_Barley.set(ST.make(this, 1, 2));
		
		LH.add(getUnlocalizedName()+ ".3.name", "Rice Bale");
		LH.add(getUnlocalizedName()+ ".7.name", "Rice Bale");
		LH.add(getUnlocalizedName()+".11.name", "Rice Bale");
		LH.add(getUnlocalizedName()+".15.name", "Rice Bale");
		OM.reg(ST.make(this, 1, 3), "baleRice");
		OM.reg(ST.make(this, 1, 7), "baleRice");
		OM.reg(ST.make(this, 1,11), "baleRice");
		OM.reg(ST.make(this, 1,15), "baleRice");
		IL.Bale_Rice.set(ST.make(this, 1, 3));
	}
}
