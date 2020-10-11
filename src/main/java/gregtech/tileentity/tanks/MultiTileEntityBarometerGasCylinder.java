/**
 * Copyright (c) 2020 GregTech-6 Team
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

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase09FluidContainerSmall;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBarometerGasCylinder extends TileEntityBase09FluidContainerSmall {
	public long mCapacity = 8000;
	
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
			if (aHitY > PX_P[8]) {
				if (aHitY > PX_P[12]) {
					if (aHitY > PX_P[14]) {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ? 50 : 500)));
					} else {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ? 10 : 100)));
					}
				} else {
					if (aHitY > PX_P[10]) {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ?  5 :  50)));
					} else {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() + (aPlayer.isSneaking() ?  1 :  10)));
					}
				}
			} else {
				if (aHitY > PX_P[4]) {
					if (aHitY > PX_P[6]) {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ?  1 :  10)));
					} else {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ?  5 :  50)));
					}
				} else {
					if (aHitY > PX_P[2]) {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ? 10 : 100)));
					} else {
						mTank.setCapacity(UT.Code.bind(1, mCapacity, mTank.capacity() - (aPlayer.isSneaking() ? 50 : 500)));
					}
				}
			}
			UT.Entities.sendchat(aPlayer, "Limit: " + mTank.capacity() + "L");
			return T;
		}
		return super.onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 5;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 5], PX_N[ 4], PX_P[ 8], PX_N[ 5]); return T;
		case  1: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 4], PX_N[ 5], PX_P[ 8], PX_N[ 4]); return T;
		case  2: box(aBlock, PX_P[ 5], PX_P[ 8], PX_P[ 5], PX_N[ 5], PX_P[ 9], PX_N[ 5]); return T;
		case  3: box(aBlock, PX_P[ 7], PX_P[ 9], PX_P[ 7], PX_N[ 7], PX_P[16], PX_N[ 7]); return T;
		case  4: box(aBlock, PX_P[ 6], PX_P[10], PX_P[ 6], PX_N[ 6], PX_P[14], PX_N[ 6]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/colored/sides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/colored/bottom"),
	sTextureBarometer   = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/colored/barometer"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/overlay/sides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/overlay/bottom"),
	sOverlayBarometer   = new Textures.BlockIcons.CustomIcon("machines/tanks/barometer_gas_cylinder/overlay/barometer");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aRenderPass > 2 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureBarometer, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayBarometer)) : SIDES_HORIZONTAL[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlaySides)) : SIDES_TOP[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayTop)) : aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlayBottom)) : null;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 0], PX_N[ 4]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 4];}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.barometer.gas.cylinder";}
}
