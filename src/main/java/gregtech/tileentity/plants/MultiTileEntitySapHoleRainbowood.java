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

package gregtech.tileentity.plants;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.old.Textures;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.misc.MultiTileEntityTreeHole;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySapHoleRainbowood extends MultiTileEntityTreeHole {
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (!mHasResin && aTimer % 600 == 0) {
				int tTreeHeight = yCoord+1, tLeavesCount = 0;
				for (int i = 1; i < 12; i++) {
					if (getBlockAtSideAndDistance(SIDE_TOP, i) != BlocksGT.LogB && getMetaDataAtSideAndDistance(SIDE_TOP, i) != 3) break;
					tTreeHeight++;
				}
				
				for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) {
					if (checkLeaves(xCoord+i, tTreeHeight+2, zCoord+j)) tLeavesCount++;
					if (i != 0 || j != 0) {
						if (checkLeaves(xCoord+i, tTreeHeight-4, zCoord+j)) tLeavesCount++;
					}
				}
				
				for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (Math.abs(i*j) < 9) {
					if (i != 0 || j != 0) {
						if (checkLeaves(xCoord+i, tTreeHeight-1, zCoord+j)) tLeavesCount++;
						if (checkLeaves(xCoord+i, tTreeHeight-2, zCoord+j)) tLeavesCount++;
						if (checkLeaves(xCoord+i, tTreeHeight-3, zCoord+j)) tLeavesCount++;
					}
					if (checkLeaves(xCoord+i, tTreeHeight  , zCoord+j)) tLeavesCount++;
					if (checkLeaves(xCoord+i, tTreeHeight+1, zCoord+j)) tLeavesCount++;
				}
				
				// 271 Leaves if Ideal. So a Chance of 5% every 30 seconds for a healthy Tree.
				if (tLeavesCount > 250 && rng(420) < tLeavesCount - 250) {
					mHasResin = T;
					updateClientData();
				}
			}
		}
	}
	
	private boolean checkLeaves(int aX, int aY, int aZ) {return getBlock(aX, aY, aZ) == BlocksGT.Leaves_AB && getMetaData(aX, aY, aZ) == 15;}
	
	@Override public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {return isClientSide() ? super.getDrops(aFortune, aSilkTouch) : new ArrayListNoNulls<>(F, ST.make(BlocksGT.LogB, 1, 1));}
	@Override public ItemStack getResinItem(byte aSide) {return null;}
	@Override public FluidStack getResinFluid(byte aSide) {return FL.Sap_Rainbow.make(250);}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? aSide != mFacing ? BlockTextureCopied.get(BlocksGT.LogB, SIDE_ANY, 3) : BlockTextureDefault.get(mHasResin?Textures.BlockIcons.LOG_SAP_RAINBOWOOD:Textures.BlockIcons.LOG_HOLE_RAINBOWOOD) : null;}
	@Override public float getExplosionResistance2() {return BlocksGT.LogB.getExplosionResistance((byte)3);}
	@Override public String getTileEntityName() {return "gt.multitileentity.tree.rainbowood.saphole";}
}
