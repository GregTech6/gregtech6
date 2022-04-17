/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.tileentity.tanks;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.*;
import gregapi.tileentity.ITileEntityTapFillable;
import gregapi.tileentity.tank.TileEntityBase10FluidContainerSyncSmall;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMeasuringPot extends TileEntityBase10FluidContainerSyncSmall implements ITileEntityTapFillable, IMTE_IgnorePlayerCollisionWhenPlacing {
	public long mCapacity = 1000;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mCapacity = mTank.capacity();
		if (aNBT.hasKey(NBT_MODE)) mTank.setCapacity(aNBT.getLong(NBT_MODE));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		if (mCapacity != mTank.capacity()) UT.NBT.setNumber(aNBT, NBT_MODE, mTank.capacity());
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (mCapacity != mTank.capacity()) UT.NBT.setNumber(aNBT, NBT_MODE, mTank.capacity());
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.CYAN + LH.get(LH.NO_GUI_CLICK_TO_LIMIT));
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer.getCurrentEquippedItem() == null && SIDES_HORIZONTAL[aSide]) {
			if (isClientSide()) return T;
			if (aHitY > PX_P[4]) {
				if (aHitY > PX_P[6]) {
					mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ? 5 : 50)));
				} else {
					mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ? 1 : 10)));
				}
			} else {
				if (aHitY > PX_P[2]) {
					mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ? 1 : 10)));
				} else {
					mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ? 5 : 50)));
				}
			}
			UT.Entities.sendchat(aPlayer, "Limit: " + mTank.capacity() + "L");
			return T;
		}
		return super.onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return mTank.isEmpty() ? 5 : 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_N[11], PX_N[ 8], PX_N[ 5]); return T;
		case  1: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 4], PX_N[ 5], PX_N[ 8], PX_N[11]); return T;
		case  2: box(aBlock, PX_P[11], PX_P[ 1], PX_P[ 5], PX_N[ 4], PX_N[ 8], PX_N[ 5]); return T;
		case  3: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[11], PX_N[ 5], PX_N[ 8], PX_N[ 4]); return T;
		case  4: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[15], PX_N[ 5]); return T;
		case  5: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 9], PX_N[ 5]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/bottom"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/bottom");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aSide) {
		case SIDE_X_NEG: return aRenderPass == 2 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_X_POS: return aRenderPass == 0 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_NEG: return aRenderPass == 3 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_POS: return aRenderPass == 1 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Y_NEG: return aRenderPass != 4 || aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayBottom)) : null;
		case SIDE_Y_POS: return aRenderPass == 5 ? BlockTextureFluid.get(mTank) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayTop));
		}
		return null;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 4];}
	
	@Override public boolean canWaterCrops() {return T;}
	@Override public boolean canPickUpFluids() {return T;}
	@Override public boolean canFillWithRain() {return T;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.measuring.pot";}
}
