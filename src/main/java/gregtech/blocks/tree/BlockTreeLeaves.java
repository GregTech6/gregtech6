/**
 * Copyright (c) 2020 GregTech-6 Team
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

import static gregapi.data.CS.*;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.tree.BlockBaseLeaves;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTreeLeaves extends BlockBaseLeaves {
	public BlockTreeLeaves(String aUnlocalised, Block aSaplings) {
		super(null, aUnlocalised, Material.leaves, soundTypeGrass, 8, Textures.BlockIcons.LEAVES, aSaplings, new Block[] {BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogB, BlocksGT.LogB, BlocksGT.LogB, BlocksGT.LogB}, new byte[] {0, 1, 2, 3, 0, 1, 2, 3});
		LH.add(getUnlocalizedName()+ ".0.name", "Rubber Leaves");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Leaves");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Leaves");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Leaves");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Leaves");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Leaves");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Leaves");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbow Leaves");
		LH.add(getUnlocalizedName()+ ".8.name", "Rubber Leaves");
		LH.add(getUnlocalizedName()+ ".9.name", "Maple Leaves");
		LH.add(getUnlocalizedName()+".10.name", "Willow Leaves");
		LH.add(getUnlocalizedName()+".11.name", "Blue Mahoe Leaves");
		LH.add(getUnlocalizedName()+".12.name", "Hazel Leaves");
		LH.add(getUnlocalizedName()+".13.name", "Cinnamon Leaves");
		LH.add(getUnlocalizedName()+".14.name", "Coconut Leaves");
		LH.add(getUnlocalizedName()+".15.name", "Rainbow Leaves");
		
		for (int i = 0; i < 16; i++) OM.reg(ST.make(this, 1, i), OP.treeLeaves);
		
		if (COMPAT_FR != null) COMPAT_FR.addWindfall(OP.stick.mat(MT.Wood, 1));
	}
	
	@Override
	public int getLeavesRangeSide(byte aMeta) {
		switch(aMeta & 7) {
		case 0: return 2;
		case 1: return 3;
		case 2: return 4;
		case 3: return 3;
		case 4: return 3;
		case 5: return 3;
		case 6: return 3;
		case 7: return 3;
		}
		return 3;
	}
	
	@Override
	public int getLeavesRangeYNeg(byte aMeta) {
		switch(aMeta & 7) {
		case 0: return 2;
		case 1: return 2;
		case 2: return 2;
		case 3: return 4;
		case 4: return 2;
		case 5: return 3;
		case 6: return 3;
		case 7: return 3;
		}
		return 2;
	}
	
	@Override public int getLeavesRangeYPos(byte aMeta) {return 0;}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		ArrayListNoNulls<ItemStack> rDrops = new ArrayListNoNulls<>();
		int tChance = 50;
		if (aFortune > 0) {
			tChance -= 5 << aFortune;
			if (tChance < 5) tChance = 5;
		}
		if (RNGSUS.nextInt(tChance) == 0) {
			rDrops.add(ST.make(getItemDropped(aMeta, RNGSUS, aFortune), 1, damageDropped(aMeta)));
		} else {
			switch(aMeta & 7) {case 2: case 3: case 4: if (RNGSUS.nextInt(tChance) < 2) rDrops.add(OP.stick.mat(MT.Wood, 1));}
		}
		if ((aMeta & 7) == 4 && RNGSUS.nextInt(tChance) < 2) rDrops.add(IL.Food_Hazelnut.get(1));
		return rDrops;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int aMeta) {
		return (aMeta & 7) == 7 ? RAINBOW_ARRAY[(int)((CLIENT_TIME / 10) % RAINBOW_ARRAY.length)] : UNCOLORED;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return (aWorld.getBlockMetadata(aX, aY, aZ) & 7) == 7 ? RAINBOW_ARRAY[(Math.abs(aX) + Math.abs(aY) + Math.abs(aZ)) % RAINBOW_ARRAY.length] : UNCOLORED;
	}
}
