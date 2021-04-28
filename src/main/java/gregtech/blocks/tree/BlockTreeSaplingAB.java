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

import java.util.Random;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.block.tree.BlockBaseSapling;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTreeSaplingAB extends BlockBaseSapling {
	public BlockTreeSaplingAB(String aUnlocalised) {
		super(null, aUnlocalised, Material.plants, soundTypeGrass, 8, Textures.BlockIcons.SAPLINGS_AB);
		LH.add(getUnlocalizedName()+ ".0.name", "Rubber Sapling");
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Sapling");
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Sapling");
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Sapling");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Sapling");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Sapling");
		LH.add(getUnlocalizedName()+ ".6.name", "Coconut Sapling");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Sapling");
		LH.add(getUnlocalizedName()+ ".8.name", "Rubber Sapling");
		LH.add(getUnlocalizedName()+ ".9.name", "Maple Sapling");
		LH.add(getUnlocalizedName()+".10.name", "Willow Sapling");
		LH.add(getUnlocalizedName()+".11.name", "Blue Mahoe Sapling");
		LH.add(getUnlocalizedName()+".12.name", "Hazel Sapling");
		LH.add(getUnlocalizedName()+".13.name", "Cinnamon Sapling");
		LH.add(getUnlocalizedName()+".14.name", "Coconut Sapling");
		LH.add(getUnlocalizedName()+".15.name", "Rainbowood Sapling");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OP.treeSapling);
			OM.reg(ST.make(this, 1, i+8), OP.treeSapling);
		}
	}
	
	@Override
	public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY-1, aZ);
		if (tBlock.canSustainPlant(aWorld, aX, aY-1, aZ, ForgeDirection.UP, (IPlantable)Blocks.sapling)) return T;
		// Coconut Trees should be able to grow on Sand, not because realism, but because it makes it easier to plant them in Deserts and Beaches.
		// Weirdly this Line of Code does not work, so maybe such a Hybrid Plant is not possible thanks to Forge fuckery.
		return (tBlock == Blocks.sand || tBlock.canSustainPlant(aWorld, aX, aY-1, aZ, ForgeDirection.UP, (IPlantable)Blocks.cactus)) && (aWorld.getBlockMetadata(aX, aY, aZ) & 7) == 6;
	}
	
	@Override
	public boolean grow(World aWorld, int aX, int aY, int aZ, byte aMeta, Random aRandom) {
		int tMaxHeight = 0;
		switch(aMeta & 7) {
		case 0:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 9);
			if (tMaxHeight < 7) return F;
			tMaxHeight = aY+7+aRandom.nextInt(tMaxHeight-6);
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-5, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogA, 0, 3);
			
			boolean tCanPlaceResinHole = T;
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) {
				if (tCanPlaceResinHole && tMaxHeight - tY > 5 && aRandom.nextInt(2) == 0) {
					tCanPlaceResinHole = F;
					MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
					if (tRegistry != null) {
						tRegistry.mBlock.placeBlock(aWorld, aX, tY, aZ, SIDE_UNKNOWN, (short)32762, UT.NBT.make(NBT_FACING, ALL_SIDES_HORIZONTAL[aRandom.nextInt(4)]), T, T);
						continue;
					}
				}
				placeTree(aWorld, aX, tY, aZ, BlocksGT.LogA, 0);
			}
			
			placeTree(aWorld, aX, tMaxHeight  , aZ, BlocksGT.Leaves_AB, 8);
			placeTree(aWorld, aX, tMaxHeight+1, aZ, BlocksGT.Leaves_AB, 8);
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) if (i != 0 || j != 0) {
				placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 8);
			}
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) {
				if (Math.abs(i*j) < 2) {
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 8);
				}
				if (Math.abs(i*j) < 4) {
					placeTree(aWorld, aX+i, tMaxHeight-3, aZ+j, BlocksGT.Leaves_AB, 8);
					placeTree(aWorld, aX+i, tMaxHeight-4, aZ+j, BlocksGT.Leaves_AB, 8);
				}
				placeTree(aWorld, aX+i, tMaxHeight-5, aZ+j, BlocksGT.Leaves_AB, 8);
			}
			return T;
		case 1:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 11);
			if (tMaxHeight < 9) return F;
			tMaxHeight = aY+9+aRandom.nextInt(tMaxHeight-8);
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-4, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogA, 1, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogA, 1);
			
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) {
				placeTree(aWorld, aX+i, tMaxHeight+1, aZ+j, BlocksGT.Leaves_AB, 9);
			}
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) {
				placeTree(aWorld, aX+i, tMaxHeight, aZ+j, BlocksGT.Leaves_AB, 9);
				if (i != 0 || j != 0) {
					if (Math.abs(i*j) < 4) {
						placeTree(aWorld, aX+i, tMaxHeight-7, aZ+j, BlocksGT.Leaves_AB, 9);
					}
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 9);
				}
			}
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (i != 0 || j != 0) {
				if (Math.abs(i*j) < 9) {
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 9);
					placeTree(aWorld, aX+i, tMaxHeight-3, aZ+j, BlocksGT.Leaves_AB, 9);
					placeTree(aWorld, aX+i, tMaxHeight-6, aZ+j, BlocksGT.Leaves_AB, 9);
				}
				placeTree(aWorld, aX+i, tMaxHeight-4, aZ+j, BlocksGT.Leaves_AB, 9);
				placeTree(aWorld, aX+i, tMaxHeight-5, aZ+j, BlocksGT.Leaves_AB, 9);
			}
			return T;
		case 2:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 7);
			if (tMaxHeight < 5) return F;
			tMaxHeight = aY+5+aRandom.nextInt(tMaxHeight-4);
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-2, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogA, 2, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogA, 2);
			
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) {
				if (Math.abs(i*j) < 9) {
					placeTree(aWorld, aX+i, tMaxHeight+1, aZ+j, BlocksGT.Leaves_AB, 10);
					if (i != 0 || j != 0) placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 10);
				}
				placeTree(aWorld, aX+i, tMaxHeight, aZ+j, BlocksGT.Leaves_AB, 10);
			}
			for (int i = -4; i <= 4; i++) for (int j = -4; j <= 4; j++) if (i != 0 || j != 0) {
				if (Math.abs(i*j) < 10) {
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 10);
					if (tMaxHeight-2 <= aY) continue;
					if (Math.abs(i*j) > 6) {
						placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 10);
						if (tMaxHeight-3 <= aY) continue;
						placeTree(aWorld, aX+i, tMaxHeight-3, aZ+j, BlocksGT.Leaves_AB, 10);
						if (tMaxHeight-4 <= aY) continue;
						placeTree(aWorld, aX+i, tMaxHeight-4, aZ+j, BlocksGT.Leaves_AB, 10);
						if (tMaxHeight-5 <= aY) continue;
						placeTree(aWorld, aX+i, tMaxHeight-5, aZ+j, BlocksGT.Leaves_AB, 10);
					}
				}
			}
			return T;
		case 3:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 5);
			if (tMaxHeight < 4) return F;
			tMaxHeight = aY+4+aRandom.nextInt(tMaxHeight-3);
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-2, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogA, 3, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogA, 3);
			
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) {
				placeTree(aWorld, aX+i, tMaxHeight+3, aZ+j, BlocksGT.Leaves_AB, 11);
			}
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (Math.abs(i*j) < 4) {
				placeTree(aWorld, aX+i, tMaxHeight+2, aZ+j, BlocksGT.Leaves_AB, 11);
				placeTree(aWorld, aX+i, tMaxHeight+1, aZ+j, BlocksGT.Leaves_AB, 11);
				if (i != 0 || j != 0) {
					placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 11);
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 11);
				}
			}
			return T;
		case 4:
			if (getMaxHeight(aWorld, aX, aY, aZ, 4) < 4) return F;
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, aY+2, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogB, 0, 3);
			
			placeTree(aWorld, aX, aY+1, aZ, BlocksGT.LogB, 0);
			placeTree(aWorld, aX, aY+2, aZ, BlocksGT.LogB, 0);
			
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) {
				placeTree(aWorld, aX+i, aY+4, aZ+j, BlocksGT.Leaves_AB, 12);
			}
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) {
				if (i != 0 || j != 0) placeTree(aWorld, aX+i, aY+2, aZ+j, BlocksGT.Leaves_AB, 12);
				if (Math.abs(i*j) < 4) placeTree(aWorld, aX+i, aY+3, aZ+j, BlocksGT.Leaves_AB, 12);
			}
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) {
				if (Math.abs(i*j) < 9 && (i != 0 || j != 0)) placeTree(aWorld, aX+i, aY+1, aZ+j, BlocksGT.Leaves_AB, 12);
			}
			return T;
		case 5:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 8);
			if (tMaxHeight < 6) return F;
			tMaxHeight = aY+6+aRandom.nextInt(tMaxHeight-5);
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-4, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogB, 1, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogB, 1);
			
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) {
				placeTree(aWorld, aX+i, tMaxHeight+2, aZ+j, BlocksGT.Leaves_AB, 13);
				if (i != 0 || j != 0) {
					placeTree(aWorld, aX+i, tMaxHeight-4, aZ+j, BlocksGT.Leaves_AB, 13);
				}
			}
			
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (Math.abs(i*j) < 9) {
				if (i != 0 || j != 0) {
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 13);
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 13);
					placeTree(aWorld, aX+i, tMaxHeight-3, aZ+j, BlocksGT.Leaves_AB, 13);
				}
				placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 13);
				placeTree(aWorld, aX+i, tMaxHeight+1, aZ+j, BlocksGT.Leaves_AB, 13);
			}
			return T;
		case 6:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 12);
			if (tMaxHeight < 8) return F;
			tMaxHeight = aY+8+aRandom.nextInt(tMaxHeight-7);
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogB, 2, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogB, 2);
			
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (i == j || i == -j) {
				if (Math.abs(i) == 3 || Math.abs(j) == 3) {
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 14);
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 14);
				} else if (Math.abs(i) == 2 || Math.abs(j) == 2) {
					placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 14);
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 14);
				} else {
					placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 14);
				}
			}
			for (int i = -4; i <= 4; i++) for (int j = -4; j <= 4; j++) if (i == 0 || j == 0) {
				if (Math.abs(i) == 4 || Math.abs(j) == 4) {
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 14);
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 14);
				} else if (Math.abs(i) == 3 || Math.abs(j) == 3) {
					placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 14);
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 14);
				} else {
					placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 14);
				}
			}
			return T;
		case 7:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 9);
			if (tMaxHeight < 7) return F;
			tMaxHeight = aY+7+aRandom.nextInt(tMaxHeight-6);
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-4, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogB, 3, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogB, 3);
			
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) {
				placeTree(aWorld, aX+i, tMaxHeight+2, aZ+j, BlocksGT.Leaves_AB, 15);
				if (i != 0 || j != 0) {
					placeTree(aWorld, aX+i, tMaxHeight-4, aZ+j, BlocksGT.Leaves_AB, 15);
				}
			}
			
			for (int i = -3; i <= 3; i++) for (int j = -3; j <= 3; j++) if (Math.abs(i*j) < 9) {
				if (i != 0 || j != 0) {
					placeTree(aWorld, aX+i, tMaxHeight-1, aZ+j, BlocksGT.Leaves_AB, 15);
					placeTree(aWorld, aX+i, tMaxHeight-2, aZ+j, BlocksGT.Leaves_AB, 15);
					placeTree(aWorld, aX+i, tMaxHeight-3, aZ+j, BlocksGT.Leaves_AB, 15);
				}
				placeTree(aWorld, aX+i, tMaxHeight  , aZ+j, BlocksGT.Leaves_AB, 15);
				placeTree(aWorld, aX+i, tMaxHeight+1, aZ+j, BlocksGT.Leaves_AB, 15);
			}
			
			return T;
		}
		return F;
	}
}
