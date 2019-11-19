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

package gregtech.tileentity.autotools;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.TagData;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MultiTileEntityAutoToolIgniter extends TileEntityBase09FacingSingle implements ITileEntityEnergyElectricityAcceptor, ITileEntitySwitchableOnOff {
	public boolean mStopped = F;
	public byte mQuality = 2, mCoolDown = 0, mSendSound = 0;
	public long mEnergy = 0, mInput = 32;
	public TagData mEnergyTypeAccepted = TD.Energy.EU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_QUALITY)) mQuality = aNBT.getByte(NBT_QUALITY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_IGNITER));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_QUALITY)        + ": " + Chat.WHITE + mQuality);
		LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, null, LH.get(LH.FACE_ANYBUT_FRONT), null);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (SERVER_TIME % 10 == 0) {
				if (mCoolDown > 0) mCoolDown--;
				if (mEnergy > 0) {
					DelegatorTileEntity<TileEntity> tD = getAdjacentTileEntity(mFacing);
					if (IBlockToolable.Util.onToolClick(TOOL_igniter, mEnergy * 20, mQuality, null, null, null, F, null, tD.mWorld, tD.mSideOfTileEntity, tD.mX, tD.mY, tD.mZ, 0.5F, 0.5F, 0.5F) > 0 || WD.fire(tD.mWorld, tD.getCoords(), F)) mSendSound = 1;
					mEnergy = 0;
					mCoolDown = 10;
				}
			}
		} else {
			if (mSendSound != 0) {
				UT.Sounds.play(SFX.MC_IGNITE, 20, 1.0F, xCoord, yCoord, zCoord);
				mSendSound = 0;
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mSendSound != 0 || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		mSendSound = 0;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mSendSound = aData;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		aAmount = Math.min(aAmount, 1);
		if (aDoInject && mEnergy < mInput * 10) {
			if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) overcharge(aSize, aEnergyType); else if (mCoolDown <= 0) mEnergy += aSize * aAmount;
		}
		return aAmount;
	}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public byte getVisualData() {return mSendSound;}
	
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && aSide != mFacing && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:1;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/igniter/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/igniter/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/igniter/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/igniter/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.autotool.igniter";}
}
