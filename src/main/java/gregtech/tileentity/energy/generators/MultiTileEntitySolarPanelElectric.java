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

package gregtech.tileentity.energy.generators;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityEmitter;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MultiTileEntitySolarPanelElectric extends TileEntityBase09FacingSingle implements ITileEntityEnergyElectricityEmitter, ITileEntityRunningActively, ITileEntitySwitchableOnOff {
	protected boolean mEmitsEnergy = F, mStopped = F, mActive = F, oActive = F, mCheck = T, mSky = F;
	protected long mEnergy = 0, mOutput = 8;
	protected TagData mEnergyTypeEmitted = TD.Energy.QU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_ACTIVE_ENERGY)) mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_OUTPUT)) mOutput = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		addToolTipsEnergy(aList, aStack, aF3_H);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_FRONT));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (!mStopped) generateEnergy(aTimer);
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mActive != oActive || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oActive = mActive;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mActive = ((aData & 1) != 0);
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered                  (TagData aEnergyType, byte aSide, long aSize) {return mEnergy;}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return mOutput / 8;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public double getOfferedEnergy() {return mEmitsEnergy?0:mEnergy;}
	@Override public void drawEnergy(double aAmount) {mEnergy -= aAmount;}
	
	@Override
	public long doExtract(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {
		aSize = Math.abs(aSize);
		if (aSize * aAmount <= mEnergy) {
			if (aDoExtract) mEnergy -= aSize * aAmount;
			return aAmount;
		}
		return 0;
	}
	
	public void generateEnergy(long aTimer) {
		if ((mCheck || mBlockUpdated || mTimer % 600 == 5)) {
			mCheck = F;
			mSky = getSkyAtSide(SIDE_TOP);
		}
		if (mSky) {
			if (worldObj.isThundering()) {
				mEnergy = 0;
			} else {
				if (WD.dimTF(worldObj)) {
					mEnergy = mOutput / 2;
				} else if (worldObj.isDaytime()) {
					if (worldObj.isRaining() && getBiome().rainfall > 0) {
						mEnergy = mOutput / 8;
					} else {
						mEnergy = mOutput;
					}
				} else {
					if (worldObj.isRaining() && getBiome().rainfall > 0) {
						mEnergy = 0;
					} else {
						mEnergy = mOutput / 8;
					}
				}
			}
		} else {
			mEnergy = 0;
		}
		
		mActive = (mEnergy >= getEnergySizeOutputMin(mEnergyTypeEmitted, SIDE_ANY));
		mEmitsEnergy = F;
		
		if (mActive) {
			if (mTimer % 600 == 5) doDefaultStructuralChecks();
			if (TD.Energy.ALL_SIZE_IRRELEVANT.contains(mEnergyTypeEmitted)) {
				mEmitsEnergy = (ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, mEnergy, this) > 0);
			} else {
				mEmitsEnergy = (ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, mEnergy, 1, this) > 0);
			}
		}
		
		if (mEmitsEnergy) mEnergy = 0;
	}
	
	@Override public boolean isRainProof(byte aSide) {return T;}
	@Override public boolean isThunderProof(byte aSide) {return T;}
	@Override public boolean useSidePlacementRotation() {return T;}
	@Override public boolean useInversePlacementRotation() {return T;}
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mEmitsEnergy;}
	@Override public boolean setStateOnOff(boolean aOnOff) {if (mStopped && aOnOff) mCheck = T; mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM_HORIZONTAL;}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override
	public byte getVisualData() {return (byte)(mActive?1:0);}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = SIDES_TOP[aSide]?4:aSide==mFacing?SIDES_HORIZONTAL[aSide]?0:2:SIDES_HORIZONTAL[aSide]?1:3;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActive?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/colored/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/colored/side"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/colored/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/colored/top"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay/side"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay/top"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay_active/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay_active/side"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay_active/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/solarpanels/solarpanel_electric_8eu/overlay_active/top"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.solarpanel.electric_8eu";}
}
