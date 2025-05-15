/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregtech.blocks.tree;

import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

public class BlockTreeLog1 extends BlockBaseBeamFlammable implements IBlockToolable {
	public BlockTreeLog1(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.LOGS_1);
		
		LH.add(getUnlocalizedName()+ ".0", "Dead Log");
		LH.add(getUnlocalizedName()+ ".4", "Dead Log");
		LH.add(getUnlocalizedName()+ ".8", "Dead Log");
		LH.add(getUnlocalizedName()+".12", "Dead Log");
		
		LH.add(getUnlocalizedName()+ ".1", "Rotten Log");
		LH.add(getUnlocalizedName()+ ".5", "Rotten Log");
		LH.add(getUnlocalizedName()+ ".9", "Rotten Log");
		LH.add(getUnlocalizedName()+".13", "Rotten Log");
		
		LH.add(getUnlocalizedName()+ ".2", "Mossy Log");
		LH.add(getUnlocalizedName()+ ".6", "Mossy Log");
		LH.add(getUnlocalizedName()+".10", "Mossy Log");
		LH.add(getUnlocalizedName()+".14", "Mossy Log");
		
		LH.add(getUnlocalizedName()+ ".3", "Frozen Log");
		LH.add(getUnlocalizedName()+ ".7", "Frozen Log");
		LH.add(getUnlocalizedName()+".11", "Frozen Log");
		LH.add(getUnlocalizedName()+".15", "Frozen Log");
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			if (aWorld.isRemote) return 0;
			switch (aWorld.getBlockMetadata(aX, aY, aZ) & PILLAR_DATA) {
			case 0: ST.give(aPlayer, IL.Bark_Dry.get(1), aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]); break;
			case 1: ST.give(aPlayer, IL.FR_Mulch.get(1, OM.dust(MT.WOODS.Rotten)), aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]); break;
			case 2: ST.give(aPlayer, IL.FR_Mulch.get(1, OM.dust(MT.WOODS.Mossy )), aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]); break;
			case 3: ST.give(aPlayer, OM.dust(MT.Ice), aWorld, aX + OFFX[aSide], aY + OFFY[aSide], aZ + OFFZ[aSide]); break;
			}
			aWorld.setBlockToAir(aX, aY, aZ);
			return 1000;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.log.getBlockHardness(aWorld, aX, aY, aZ) / 2;}
}
