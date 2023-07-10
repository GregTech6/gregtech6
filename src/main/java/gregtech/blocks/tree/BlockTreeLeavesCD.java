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
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class BlockTreeLeavesCD extends BlockBaseLeaves implements Runnable {
	public BlockTreeLeavesCD(String aUnlocalised, Block aSaplings) {
		super(null, aUnlocalised, Material.leaves, soundTypeGrass, 1, Textures.BlockIcons.LEAVES_CD, aSaplings, new Block[] {BlocksGT.LogC, BlocksGT.LogC, BlocksGT.LogC, BlocksGT.LogC, BlocksGT.LogD, BlocksGT.LogD, BlocksGT.LogD, BlocksGT.LogD}, new byte[] {0, 1, 2, 3, 0, 1, 2, 3});
		LH.add(getUnlocalizedName()+ ".0", "Blue Spruce Leaves");
	//  LH.add(getUnlocalizedName()+ ".1", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".2", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".3", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".4", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".5", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".6", " Leaves");
	//  LH.add(getUnlocalizedName()+ ".7", " Leaves");
		LH.add(getUnlocalizedName()+ ".8", "Blue Spruce Leaves");
	//  LH.add(getUnlocalizedName()+ ".9", " Leaves");
	//  LH.add(getUnlocalizedName()+".10", " Leaves");
	//  LH.add(getUnlocalizedName()+".11", " Leaves");
	//  LH.add(getUnlocalizedName()+".12", " Leaves");
	//  LH.add(getUnlocalizedName()+".13", " Leaves");
	//  LH.add(getUnlocalizedName()+".14", " Leaves");
	//  LH.add(getUnlocalizedName()+".15", " Leaves");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OP.treeLeaves);
			OM.reg(ST.make(this, 1, i+8), OP.treeLeaves);
		}
		
		GAPI.mAfterPostInit.add(this);
	}
	
	@Override
	public void run() {
		if (COMPAT_FR != null) {
			COMPAT_FR.addWindfall(IL.BoP_Pinecone.get(1));
		}
	}
	
	@Override
	public int getLeavesRangeSide(byte aMeta) {
		switch(aMeta) {
		case  0: case  8: return 6;
		default:          return 3;
		}
	}
	
	@Override
	public int getLeavesRangeYNeg(byte aMeta) {
		switch(aMeta) {
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
		if (RNGSUS.nextInt(tChance) == 0 && ((aMeta & 7) != 0 || RNGSUS.nextInt(3) == 0)) {
			rDrops.add(ST.make(getItemDropped(aMeta, RNGSUS, aFortune), 1, damageDropped(aMeta)));
		} else {
			switch(aMeta & 7) {
			case 0: if (RNGSUS.nextInt(tChance) < 2) rDrops.add(IL.BoP_Pinecone.get(1)); break;
			}
		}
		return rDrops;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int aMeta) {
		return UNCOLORED;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return UNCOLORED;
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		super.addInformation(aStack, aMeta, aPlayer, aList, aF3_H);
		if (XMAS_IN_JULY && (aMeta & 7) == 0) {
			aList.add(LH.Chat.RAINBOW_SLOW + "Save on everything at Christmas in July!");
		}
	}
}
