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

package gregapi.block.metatype;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBasePlanks extends BlockMetaType {
	public BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons);
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(this, 1, W));
	}
	
	protected BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(this, 1, W));
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_axe;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.planks.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier;}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.planks.getExplosionResistance(null) * mResistanceMultiplier;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.plank.mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));}
	@Override public boolean canCreatureSpawn(byte aMeta) {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
}
