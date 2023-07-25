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

package gregtech.blocks.tree;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.tree.BlockBaseLeaves;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.*;
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

import java.util.ArrayList;

import static gregapi.data.CS.*;

public class BlockTreeLeavesAB extends BlockBaseLeaves implements Runnable {
	public BlockTreeLeavesAB(String aUnlocalised, Block aSaplings) {
		super(null, aUnlocalised, Material.leaves, soundTypeGrass, 8, Textures.BlockIcons.LEAVES_AB, aSaplings, new Block[] {BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogA, BlocksGT.LogB, BlocksGT.LogB, BlocksGT.LogB, BlocksGT.LogB}, new byte[] {0, 1, 2, 3, 0, 1, 2, 3});
		LH.add(getUnlocalizedName()+ ".0", "Rubber Leaves");
		LH.add(getUnlocalizedName()+ ".1", "Maple Leaves");
		LH.add(getUnlocalizedName()+ ".2", "Willow Leaves");
		LH.add(getUnlocalizedName()+ ".3", "Blue Mahoe Leaves");
		LH.add(getUnlocalizedName()+ ".4", "Hazel Leaves");
		LH.add(getUnlocalizedName()+ ".5", "Cinnamon Leaves");
		LH.add(getUnlocalizedName()+ ".6", "Coconut Leaves");
		LH.add(getUnlocalizedName()+ ".7", "Rainbow Leaves");
		LH.add(getUnlocalizedName()+ ".8", "Rubber Leaves");
		LH.add(getUnlocalizedName()+ ".9", "Maple Leaves");
		LH.add(getUnlocalizedName()+".10", "Willow Leaves");
		LH.add(getUnlocalizedName()+".11", "Blue Mahoe Leaves");
		LH.add(getUnlocalizedName()+".12", "Hazel Leaves");
		LH.add(getUnlocalizedName()+".13", "Cinnamon Leaves");
		LH.add(getUnlocalizedName()+".14", "Coconut Leaves");
		LH.add(getUnlocalizedName()+".15", "Rainbow Leaves");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OP.treeLeaves);
			OM.reg(ST.make(this, 1, i+8), OP.treeLeaves);
		}
		
		GAPI.mAfterPostInit.add(this);
	}
	
	@Override
	public void run() {
		if (COMPAT_FR != null) {
			COMPAT_FR.addWindfall(IL.Food_Hazelnut.get(1));
			COMPAT_FR.addWindfall(IL.Food_Coconut.get(1));
			COMPAT_FR.addWindfall(IL.Stick.get(1));
			COMPAT_FR.addWindfall(OP.stick.mat(MT.WOODS.Willow   , 1));
			COMPAT_FR.addWindfall(OP.stick.mat(MT.WOODS.BlueMahoe, 1));
			COMPAT_FR.addWindfall(OP.stick.mat(MT.WOODS.Hazel    , 1));
		}
	}
	
	@Override
	public int getLeavesRangeSide(byte aMeta) {
		switch(aMeta) {
		case  0: case  8: return 2;
		case  2: case 10: return 4;
		case  6: case 14: return 4;
		default:          return 3;
		}
	}
	
	@Override
	public int getLeavesRangeYNeg(byte aMeta) {
		switch(aMeta) {
		case  3: case 11: return 4;
		case  5: case 13: return 3;
		case  6: case 14: return 1;
		case  7: case 15: return 3;
		default:          return 2;
		}
	}
	
	@Override public int getLeavesRangeYPos(byte aMeta) {return 0;} // There is no instance where Leaves are below the Logs for these Trees.
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		ArrayListNoNulls<ItemStack> rDrops = ST.arraylist();
		int tChance = 50;
		if (aFortune > 0) {
			tChance -= 5 << aFortune;
			if (tChance < 5) tChance = 5;
		}
		if (RNGSUS.nextInt(tChance) < ((aMeta & 7) == 6 ? 2 : 1)) {
			rDrops.add(ST.make(getItemDropped(aMeta, RNGSUS, aFortune), 1, damageDropped(aMeta)));
		} else {
			switch(aMeta & 7) {
			case 2: if (RNGSUS.nextInt(tChance) < 2) rDrops.add(OP.stick.mat(MT.WOODS.Willow   , 1)); break;
			case 3: if (RNGSUS.nextInt(tChance) < 2) rDrops.add(OP.stick.mat(MT.WOODS.BlueMahoe, 1)); break;
			case 4: if (RNGSUS.nextInt(tChance) < 2) rDrops.add(OP.stick.mat(MT.WOODS.Hazel    , 1)); break;
			}
		}
		if ((aMeta & 7) == 4 && RNGSUS.nextInt(tChance) < 2) rDrops.add(IL.Food_Hazelnut.get(1));
		if ((aMeta & 7) == 6 && RNGSUS.nextInt(tChance) < 2) rDrops.add(IL.Food_Coconut.get(1));
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
