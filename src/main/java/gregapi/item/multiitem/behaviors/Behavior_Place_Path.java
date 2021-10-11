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

package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.SFX;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Place_Path extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Place_Path(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || WD.block(aWorld, aX, aY+1, aZ).isOpaqueCube()) return F;
		Block aBlock = WD.block(aWorld, aX, aY, aZ);
		
		if (aBlock == BlocksGT.Paths || IL.EtFu_Path.equal(aBlock)) {
			return T;
		}
		if (aBlock == Blocks.grass || aBlock == Blocks.dirt || aBlock == Blocks.mycelium || aBlock == BlocksGT.Grass || IL.BoP_Grass_Long.equal(aBlock) || IL.BoP_Grass_Origin.equal(aBlock) || IL.AETHER_Grass_Enchanted_Vanilla.equal(aBlock)) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 0, 3);
					return T;
				}
				return F;
			}
			if (IL.EtFu_Path.exists()) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, IL.EtFu_Path.block());
					return T;
				}
				return F;
			}
		}
		if (IL.AETHER_Dirt.equal(aBlock) || IL.AETHER_Grass.equal(aBlock) || IL.AETHER_Grass_Enchanted.equal(aBlock)) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 1, 3);
					return T;
				}
				return F;
			}
		}
		
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		
		if ((IL.BoP_Grass_Loamy.equal(aBlock) && aMeta == 0) || (IL.BoP_Dirt_Loamy.equal(aBlock) && aMeta == 0) || (IL.BoP_Coarse_Loamy.equal(aBlock) && aMeta == 1)) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 2, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.BoP_Grass_Sandy.equal(aBlock) && aMeta == 1) || (IL.BoP_Dirt_Sandy.equal(aBlock) && aMeta == 2) || (IL.BoP_Coarse_Sandy.equal(aBlock) && aMeta == 3)) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 3, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.BoP_Grass_Silty.equal(aBlock) && aMeta == 2) || (IL.BoP_Dirt_Silty.equal(aBlock) && aMeta == 4) || (IL.BoP_Coarse_Silty.equal(aBlock) && aMeta == 5)) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 4, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Alfisol.equal(aBlock) || IL.EB_Grass_Alfisol.equal(aBlock)) && aMeta == 0) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 5, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Andisol.equal(aBlock) || IL.EB_Grass_Andisol.equal(aBlock)) && aMeta == 1) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 6, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Gelisol.equal(aBlock) || IL.EB_Grass_Gelisol.equal(aBlock)) && aMeta == 3) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 7, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Histosol.equal(aBlock) || IL.EB_Grass_Histosol.equal(aBlock)) && aMeta == 4) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 8, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Inceptisol.equal(aBlock) || IL.EB_Grass_Inceptisol.equal(aBlock)) && aMeta == 5) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths, 9, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Mollisol.equal(aBlock) || IL.EB_Grass_Mollisol.equal(aBlock)) && aMeta == 6) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths,10, 3);
					return T;
				}
				return F;
			}
		}
		if ((IL.EB_Dirt_Oxisol.equal(aBlock) || IL.EB_Grass_Oxisol.equal(aBlock)) && aMeta == 7) {
			if (BlocksGT.Paths != null) {
				if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
					UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
					aWorld.setBlock(aX, aY, aZ, BlocksGT.Paths,11, 3);
					return T;
				}
				return F;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.path", "Creates Dirt Paths on Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (IL.EtFu_Path.exists() || BlocksGT.Paths != null) aList.add(LH.get("gt.behaviour.path"));
		return aList;
	}
}
