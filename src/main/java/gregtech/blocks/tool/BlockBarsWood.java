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

package gregtech.blocks.tool;

import gregapi.block.misc.BlockBaseBars;
import gregapi.data.ANY;
import gregapi.data.LH;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBarsWood extends BlockBaseBars {
	public BlockBarsWood(String aNameInternal) {
		super(aNameInternal, ANY.Wood, Material.wood, Block.soundTypeWood);
		LH.add(getUnlocalizedName()+ ".0", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".1", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".2", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".3", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".4", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".5", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".6", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".7", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".8", "Wood Bars");
		LH.add(getUnlocalizedName()+ ".9", "Wood Bars");
		LH.add(getUnlocalizedName()+".10", "Wood Bars");
		LH.add(getUnlocalizedName()+".11", "Wood Bars");
		LH.add(getUnlocalizedName()+".12", "Wood Bars");
		LH.add(getUnlocalizedName()+".13", "Wood Bars");
		LH.add(getUnlocalizedName()+".14", "Wood Bars");
		LH.add(getUnlocalizedName()+".15", "Wood Bars");
	}
	
	@Override public float getExplosionResistance(byte aMeta) {return 3;}
	@Override public int getFlammability(byte aMeta) {return 150;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 150;}
}
