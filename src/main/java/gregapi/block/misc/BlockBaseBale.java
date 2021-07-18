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

package gregapi.block.misc;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseBale extends BlockBaseMeta {
	public BlockBaseBale(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, Math.min(4, aMaxMeta), aIcons);
		setCreativeTab(CreativeTabs.tabDecorations);
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(this, 1, W));
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_sword;}
	@Override public int damageDropped(int aMeta) {return aMeta & PILLAR_DATA;}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return WD.meta(aWorld, aX, aY, aZ) & PILLAR_DATA;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.hay_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.hay_block.getExplosionResistance(null);}
	@Override public int getItemStackLimit(ItemStack aStack) {return ST.make(Blocks.hay_block, 1, 0).getMaxStackSize();}
	@Override public int getRenderType() {return PILLAR_RENDER;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return F;}
	@Override public int getFlammability(byte aMeta) {return 150;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 150;}
	@Override public int onBlockPlaced(World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ, int aMeta) {return PILLAR_DATA_SIDE[aMeta][aSide];}
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[2*(aMeta&PILLAR_DATA)+(PILLAR_TO_AXIS[aMeta][aSide]?0:1)].getIcon(0);}
}
