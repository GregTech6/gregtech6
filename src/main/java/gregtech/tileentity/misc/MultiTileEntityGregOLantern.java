/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregtech.tileentity.misc;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi.old.Textures;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGregOLantern extends TileEntityBase09FacingSingle implements IMTE_GetLightValue {
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? aSide == mFacing ? BlockTextureDefault.get(Textures.BlockIcons.GREG_O_LANTERN, mRGBa, F, T, T, F) : BlockTextureCopied.get(Blocks.lit_pumpkin, aSide, 4, mRGBa, F, T, T) : null;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getLightValue() {return 15;}
	
	@Override public float getExplosionResistance2(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ) {return Blocks.lit_pumpkin.getExplosionResistance(aExploder);}
	@Override public float getExplosionResistance2() {return Blocks.lit_pumpkin.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return Blocks.lit_pumpkin.getBlockHardness(null, 0, 0, 0);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return T;}
	@Override public boolean isSideSolid2           (byte aSide) {return T;}
	
	@Override public boolean canDrop(int aSlot) {return F;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.greg.o.lantern";}
}
