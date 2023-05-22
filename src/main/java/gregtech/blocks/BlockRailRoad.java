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
import gregapi.block.misc.BlockBaseRail;
import gregapi.render.IIconContainer;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

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
		// NO-OP
	}
	
	@Override
	protected void func_150048_a(World aWorld, int aX, int aY, int aZ, int aMeta, int aData, Block aBlock) {
		// NO-OP
	}
	
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
		// NO-OP
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
}
