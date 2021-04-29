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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.item.IItemReactorRod;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodBase extends TileEntityBase07Paintable implements IItemReactorRod, ITileEntityQuickObstructionCheck, IMTE_AddToolTips, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + "Empty Reactor Rod, transparent to Neutrons.");
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return SIDES_HORIZONTAL[aSide] ? getReactorRodTextureSides(null, 0, null, T) : getReactorRodTextureTop(null, 0, null, T);
	}

	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/colored/sides")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_rods/overlay/sides")
	};

	@Override public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]); return T;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}

	@Override public float getSurfaceSize           (byte aSide) {return 0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 6];}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}

	@Override public boolean  isReactorRod(ItemStack aStack) {return T;}
	@Override public boolean  isModerated(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return false;}
	@Override public void     updateModeration(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {/**/}
	@Override public int      getReactorRodNeutronEmission  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return 0;}
	@Override public boolean  getReactorRodNeutronReaction  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return F;}
	@Override public int      getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {return 0;}
	@Override public int      getReactorRodNeutronMaximum   (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return 0;}

	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, F), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, F), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}

	@Override public boolean canDrop(int aSlot) {return F;}

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.empty";}
}
