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

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BlockTreeBeamC extends BlockBaseBeamFlammable {
	public BlockTreeBeamC(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 1, Textures.BlockIcons.BEAMS_C);
		
		LH.add(getUnlocalizedName()+ ".0", "Blue Spruce Beam");
		LH.add(getUnlocalizedName()+ ".4", "Blue Spruce Beam");
		LH.add(getUnlocalizedName()+ ".8", "Blue Spruce Beam");
		LH.add(getUnlocalizedName()+".12", "Blue Spruce Beam");
		
	//  LH.add(getUnlocalizedName()+ ".1", " Beam");
	//  LH.add(getUnlocalizedName()+ ".5", " Beam");
	//  LH.add(getUnlocalizedName()+ ".9", " Beam");
	//  LH.add(getUnlocalizedName()+".13", " Beam");
		
	//  LH.add(getUnlocalizedName()+ ".2", " Beam");
	//  LH.add(getUnlocalizedName()+ ".6", " Beam");
	//  LH.add(getUnlocalizedName()+".10", " Beam");
	//  LH.add(getUnlocalizedName()+".14", " Beam");
		
	//  LH.add(getUnlocalizedName()+ ".3", " Beam");
	//  LH.add(getUnlocalizedName()+ ".7", " Beam");
	//  LH.add(getUnlocalizedName()+".11", " Beam");
	//  LH.add(getUnlocalizedName()+".15", " Beam");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		super.addInformation(aStack, aMeta, aPlayer, aList, aF3_H);
		if (XMAS_IN_JULY && (aMeta & 3) == 0) {
			aList.add(LH.Chat.RAINBOW_SLOW + "Save on everything at Christmas in July!");
		}
	}
}
