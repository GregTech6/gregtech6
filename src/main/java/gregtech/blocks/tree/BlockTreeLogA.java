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

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
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

public class BlockTreeLogA extends BlockBaseLogFlammable implements IBlockToolable {
	public BlockTreeLogA(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.LOGS_A);
		
		LH.add(getUnlocalizedName()+ ".0", "Rubber Log");
		LH.add(getUnlocalizedName()+ ".4", "Rubber Log");
		LH.add(getUnlocalizedName()+ ".8", "Rubber Log");
		LH.add(getUnlocalizedName()+".12", "Rubber Log");
		OM.reg(ST.make(this, 1, 0), OD.logRubber);
		OM.reg(ST.make(this, 1, 4), OD.logRubber);
		OM.reg(ST.make(this, 1, 8), OD.logRubber);
		OM.reg(ST.make(this, 1,12), OD.logRubber);
		
		LH.add(getUnlocalizedName()+ ".1", "Maple Log");
		LH.add(getUnlocalizedName()+ ".5", "Maple Log");
		LH.add(getUnlocalizedName()+ ".9", "Maple Log");
		LH.add(getUnlocalizedName()+".13", "Maple Log");
		OM.reg(ST.make(this, 1, 1), OD.logWood);
		OM.reg(ST.make(this, 1, 5), OD.logWood);
		OM.reg(ST.make(this, 1, 9), OD.logWood);
		OM.reg(ST.make(this, 1,13), OD.logWood);
		
		LH.add(getUnlocalizedName()+ ".2", "Willow Log");
		LH.add(getUnlocalizedName()+ ".6", "Willow Log");
		LH.add(getUnlocalizedName()+".10", "Willow Log");
		LH.add(getUnlocalizedName()+".14", "Willow Log");
		OM.reg(ST.make(this, 1, 2), OD.logWood);
		OM.reg(ST.make(this, 1, 6), OD.logWood);
		OM.reg(ST.make(this, 1,10), OD.logWood);
		OM.reg(ST.make(this, 1,14), OD.logWood);
		
		LH.add(getUnlocalizedName()+ ".3", "Blue Mahoe Log");
		LH.add(getUnlocalizedName()+ ".7", "Blue Mahoe Log");
		LH.add(getUnlocalizedName()+".11", "Blue Mahoe Log");
		LH.add(getUnlocalizedName()+".15", "Blue Mahoe Log");
		OM.reg(ST.make(this, 1, 3), OD.logWood);
		OM.reg(ST.make(this, 1, 7), OD.logWood);
		OM.reg(ST.make(this, 1,11), OD.logWood);
		OM.reg(ST.make(this, 1,15), OD.logWood);
	}
	
	@Override public int getLeavesRangeSide(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_AB).getLeavesRangeSide((byte)(aMeta & 3));}
	@Override public int getLeavesRangeYPos(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_AB).getLeavesRangeYNeg((byte)(aMeta & 3));} // Yes it has to be the Negative Range of the Leaves here
	@Override public int getLeavesRangeYNeg(byte aMeta) {return ((BlockBaseLeaves)BlocksGT.Leaves_AB).getLeavesRangeYPos((byte)(aMeta & 3));} // Yes it has to be the Positive Range of the Leaves here
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			if (aWorld.isRemote) return 0;
			byte aMeta = WD.meta(aWorld, aX, aY, aZ);
			aWorld.setBlock(aX, aY, aZ, BlocksGT.BeamA, aMeta, 3);
			UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, OM.dust(MT.Bark), aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]);
			return aTool.equals(TOOL_axe) ? 500 : 1000;
		}
		if (SIDES_HORIZONTAL[aSide] && aTool.equals(TOOL_drill) && WD.meta(aWorld, aX, aY, aZ) == 1) {
			if (aWorld.isRemote) return 0;
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
			if (tRegistry != null) {
				tRegistry.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN, (short)32761, UT.NBT.make(NBT_FACING, aSide), T, T);
				return aTool.equals(TOOL_axe) ? 500 : 1000;
			}
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
}
