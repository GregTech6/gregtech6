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

package gregtech.blocks;

import gregapi.block.ItemBlockBase;
import gregapi.block.ToolCompat;
import gregapi.block.misc.BlockBaseRail;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class BlockRailRoad extends BlockBaseRail {
	/** @param aSpeed is usually 0.4F */
	public BlockRailRoad(Class<? extends ItemBlockBase> aItemClass, String aNameInternal, String aLocalName, float aSpeed, float aExplosionResistance, int aHarvestLevel, IIconContainer aIconPrimary, IIconContainer aIconSecondary) {
		super(aItemClass, aNameInternal, aLocalName, T, F, aSpeed, aExplosionResistance, aHarvestLevel, aIconPrimary, aIconSecondary);
	}
	
	@Override
	protected boolean func_150057_a(World aWorld, int aX, int aY, int aZ, boolean p_150057_5_, int p_150057_6_, int p_150057_7_) {
		if (aWorld.getBlock(aX, aY, aZ) == this) {
			int tRailMeta = WD.meta(7, aWorld, aX, aY, aZ);
			
			if (p_150057_7_ == 1 && (tRailMeta == 0 || tRailMeta == 4 || tRailMeta == 5)) return F;
			if (p_150057_7_ == 0 && (tRailMeta == 1 || tRailMeta == 2 || tRailMeta == 3)) return F;
		}
		return F;
	}
	
	@Override
	public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		if (!aWorld.isRemote) {
			if (!World.doesBlockHaveSolidTopSurface(aWorld, aX, aY-1, aZ)) {
				dropBlockAsItem(aWorld, aX, aY, aZ, 0, 0);
				aWorld.setBlockToAir(aX, aY, aZ);
			}
		}
	}
	
	@Override
	protected void func_150048_a(World aWorld, int aX, int aY, int aZ, int aMeta, int aData, Block aBlock) {
		// NO-OP
	}
	
	@Override
	protected void func_150052_a(World aWorld, int aX, int aY, int aZ, boolean p_150052_5_) {
		// NO-OP
	}
	
	@Override
	public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		// NO-OP
	}
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta) {
		// NO-OP
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (!aWorld.isRemote) if (aTool.equals(TOOL_crowbar) || aTool.equals(TOOL_chisel) || aTool.equals(TOOL_shears) || aTool.equals(TOOL_scissors) || aTool.equals(TOOL_knife)) {
			return aWorld.setBlock(aX, aY, aZ, this, WD.meta(aWorld, aX, aY, aZ) ^ 8, 0)?1000:0;
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public void onMinecartPass(World aWorld, EntityMinecart aCart, int aX, int aY, int aZ) {
		double tMotion = Math.sqrt(aCart.motionX*aCart.motionX + aCart.motionZ*aCart.motionZ);
		if (tMotion > 0.01) {
			aCart.motionX *= 2;
			aCart.motionZ *= 2;
		} else {
			byte tRailMeta = WD.meta(7, aWorld, aX, aY, aZ);
			if (tRailMeta == 1) {
					 if (aWorld.getBlock(aX-1, aY, aZ).isNormalCube(aWorld, aX-1, aY, aZ)) aCart.motionX = +0.02;
				else if (aWorld.getBlock(aX+1, aY, aZ).isNormalCube(aWorld, aX+1, aY, aZ)) aCart.motionX = -0.02;
			} else if (tRailMeta == 0) {
					 if (aWorld.getBlock(aX, aY, aZ-1).isNormalCube(aWorld, aX, aY, aZ-1)) aCart.motionZ = +0.02;
				else if (aWorld.getBlock(aX, aY, aZ+1).isNormalCube(aWorld, aX, aY, aZ+1)) aCart.motionZ = -0.02;
			}
		}
	}
	
	@Override
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack.stackSize == 0) return F;
		
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock == Blocks.snow_layer && (WD.meta(aWorld, aX, aY, aZ) & 7) < 1) {
			aSide = SIDE_UP;
		} else if (tBlock != Blocks.vine && tBlock != Blocks.tallgrass && tBlock != Blocks.deadbush && !tBlock.isReplaceable(aWorld, aX, aY, aZ)) {
			aX += OFFX[aSide]; aY += OFFY[aSide]; aZ += OFFZ[aSide];
		}
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || (aY == 255 && getMaterial().isSolid()) || !aWorld.canPlaceEntityOnSide(this, aX, aY, aZ, F, aSide, aPlayer, aStack)) return F;
		
		if (aItem.placeBlockAt(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, SIDES_AXIS_X[UT.Code.getHorizontalForPlayerPlacing(aPlayer)] ? aHitZ > 0.5 ? 9 : 1 : aHitX > 0.5 ? 8 : 0)) {
			aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
			aStack.stackSize--;
		}
		return T;
	}
	
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.rail.getBlockHardness(aWorld, aX, aY, aZ) / 2;}
}
