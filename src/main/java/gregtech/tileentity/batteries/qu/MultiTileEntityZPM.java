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

package gregtech.tileentity.batteries.qu;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.old.Textures.BlockIcons;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.TileEntityBase08Battery;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityZPM extends TileEntityBase08Battery implements IMTE_GetLightValue {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (UT.Code.inside(1, mCapacity-1, mEnergy)) {
			super.addToolTips(aList, aStack, aF3_H);
		} else {
			aList.add(LH.Chat.CYAN + "An Ancient Artifact of huge Power");
			aList.add(LH.Chat.WHITE + "Capacity: " + mCapacity + mType.getLocalisedChatNameShort());
		}
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 2;
	}
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(SIDES_VERTICAL[aSide]?SIDES_TOP[aSide]?BlockIcons.ZPM_TOP:BlockIcons.ZPM_BOTTOM:BlockIcons.ZPM_SIDES, mDisplayedEnergy == 0 ? UT.Code.getRGBInt(UT.Code.getR(mRGBa) / 2, UT.Code.getG(mRGBa) / 2, UT.Code.getB(mRGBa) / 2) : mRGBa, 120+mDisplayedEnergy*8);
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0)
		box(aBlock, PX_P[ 5], PX_P[ 4], PX_P[ 5], PX_N[ 5], PX_N[ 4], PX_N[ 5]);
		else
		box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[12], PX_N[ 4]);
		return T;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box( PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 4], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box( PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 4], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock  , PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 4], PX_N[ 4]);}
	
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return aDefault;}
	@Override public byte getDisplayScaleMax() {return 15;}
	@Override public int getLightValue() {return mDisplayedEnergy;}
	@Override public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {return 0;}
	@Override public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.zpm";}
}
