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

package gregtech.blocks.tree;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.block.tree.BlockBaseLeaves;
import gregapi.block.tree.BlockBaseLogFlammable;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTreeLogC extends BlockBaseLogFlammable implements IBlockToolable {
	public BlockTreeLogC(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 1, Textures.BlockIcons.LOGS_C);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Blue Spruce Log");
		LH.add(getUnlocalizedName()+ ".4.name", "Blue Spruce Log");
		LH.add(getUnlocalizedName()+ ".8.name", "Blue Spruce Log");
		LH.add(getUnlocalizedName()+".12.name", "Blue Spruce Log");
		OM.reg(ST.make(this, 1, 0), OD.logWood);
		OM.reg(ST.make(this, 1, 4), OD.logWood);
		OM.reg(ST.make(this, 1, 8), OD.logWood);
		OM.reg(ST.make(this, 1,12), OD.logWood);
		
		LH.add(getUnlocalizedName()+ ".1.name", " Log");
		LH.add(getUnlocalizedName()+ ".5.name", " Log");
		LH.add(getUnlocalizedName()+ ".9.name", " Log");
		LH.add(getUnlocalizedName()+".13.name", " Log");
		OM.reg(ST.make(this, 1, 1), OD.logWood);
		OM.reg(ST.make(this, 1, 5), OD.logWood);
		OM.reg(ST.make(this, 1, 9), OD.logWood);
		OM.reg(ST.make(this, 1,13), OD.logWood);
		
		LH.add(getUnlocalizedName()+ ".2.name", " Log");
		LH.add(getUnlocalizedName()+ ".6.name", " Log");
		LH.add(getUnlocalizedName()+".10.name", " Log");
		LH.add(getUnlocalizedName()+".14.name", " Log");
		OM.reg(ST.make(this, 1, 2), OD.logWood);
		OM.reg(ST.make(this, 1, 6), OD.logWood);
		OM.reg(ST.make(this, 1,10), OD.logWood);
		OM.reg(ST.make(this, 1,14), OD.logWood);
		
		LH.add(getUnlocalizedName()+ ".3.name", " Log");
		LH.add(getUnlocalizedName()+ ".7.name", " Log");
		LH.add(getUnlocalizedName()+".11.name", " Log");
		LH.add(getUnlocalizedName()+".15.name", " Log");
		OM.reg(ST.make(this, 1, 3), OD.logWood);
		OM.reg(ST.make(this, 1, 7), OD.logWood);
		OM.reg(ST.make(this, 1,11), OD.logWood);
		OM.reg(ST.make(this, 1,15), OD.logWood);
	}
	
	@Override public int getLeavesRangeSide(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_CD).getLeavesRangeSide((byte)(aMeta & 3));}
	@Override public int getLeavesRangeYPos(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_CD).getLeavesRangeYNeg((byte)(aMeta & 3));} // Yes it has to be the Negative Range of the Leaves here
	@Override public int getLeavesRangeYNeg(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_CD).getLeavesRangeYPos((byte)(aMeta & 3));} // Yes it has to be the Positive Range of the Leaves here
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			if (aWorld.isRemote) return 0;
			byte aMeta = WD.meta(aWorld, aX, aY, aZ);
			aWorld.setBlock(aX, aY, aZ, BlocksGT.BeamC, aMeta, 3);
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, OM.dust(MT.Bark), aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]);
			return aTool.equals(TOOL_axe) ? 500 : 1000;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
}
