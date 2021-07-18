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

package gregtech.tileentity.energy.converters;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MultiTileEntityEngineFlux extends TileEntityBase09FacingSingle implements ITileEntityAdjacentOnOff, ITileEntityEnergyFluxHandler, ITileEntityRunningActively, ITileEntitySwitchableMode {
	/** The Array containing the different Engine State Colours from Blue over Green to Red */
	public static final int sEngineColors[] = {0x0000ff, 0x0011ee, 0x0022dd, 0x0033cc, 0x0044bb, 0x0055aa, 0x006699, 0x007788, 0x008877, 0x009966, 0x00aa55, 0x00bb44, 0x00cc33, 0x00dd22, 0x00ee11, 0x00ff00, 0x00ff00, 0x11ee00, 0x22dd00, 0x33cc00, 0x44bb00, 0x55aa00, 0x669900, 0x778800, 0x887700, 0x996600, 0xaa5500, 0xbb4400, 0xcc3300, 0xdd2200, 0xee1100, 0xff0000};
	
	protected boolean mEmitsEnergy = F, mStopped = F, mActive = F, oActive = F;
	protected byte mState = 15, mPiston = 0;
	protected long mEnergy = 0, mInput = 32, mOutput = 16;
	protected TagData mEnergyTypeEmitted = TD.Energy.KU, mEnergyTypeAccepted = TD.Energy.RF;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_ACTIVE_ENERGY)) mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_MODE)) mState = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_PISTON)) mPiston = aNBT.getByte(NBT_PISTON);
		if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_OUTPUT)) mOutput = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		if (mState != 15) aNBT.setByte(NBT_MODE, mState);
		aNBT.setByte(NBT_PISTON, mPiston);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		if (mState != 15) aNBT.setByte(NBT_MODE, mState);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, mEnergyTypeEmitted, LH.get(LH.FACE_BACK), LH.get(LH.FACE_FRONT));
		addToolTipsEfficiency(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (TD.Energy.ALL_EU.contains(mEnergyTypeAccepted)) {
			if (TD.Energy.ALL_EU.contains(mEnergyTypeEmitted)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mInput, mOutput*2, F)));
			} else {
				if (mEnergyTypeEmitted == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mInput*4, mOutput*2, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(mEnergyTypeEmitted) && mEnergyTypeAccepted == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mInput, mOutput*8, F)));
		}
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (mActive && aTimer%(32-mState)==0) {mPiston+=1; mPiston&=3;}
		mEmitsEnergy = F;
		
		if (aIsServerSide) {
			long tOutput = (mOutput * (mState + 1)) / 16, tInput = ((mInput * (mState + 1)) / 16) + ((mInput * (mState + 1)) % 16 == 0 ? 0 : 1);
			
			mActive = (mEnergy >= tInput);
			
			if (mActive) {
				mEmitsEnergy = (ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, mPiston > 1 ? -tOutput : tOutput, 1, this) > 0);
				mEnergy -= tInput;
				if (mTimer % 600 == 5) doDefaultStructuralChecks();
			}
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_screwdriver)) {
			mState = (byte)((mState + 1) % 32);
			aChatReturn.add("Input: " + (((mInput * (mState + 1)) / 16) + ((mInput * (mState + 1)) % 16 == 0 ? 0 : 1)));
			aChatReturn.add("Output: " + ((mOutput * (mState + 1)) / 16));
			updateClientData();
			return 1000;
		}
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (mStopped) {
					aChatReturn.add("Stopped");
				} else {
					if (mActive) {
						aChatReturn.add("Running");
					} else {
						aChatReturn.add("Capable of Running");
					}
				}
				aChatReturn.add("Input: " + (((mInput * (mState + 1)) / 16) + ((mInput * (mState + 1)) % 16 == 0 ? 0 : 1)));
				aChatReturn.add("Output: " + ((mOutput * (mState + 1)) / 16));
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mState = UT.Code.bind5(mState);
		return mActive != oActive || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oActive = mActive;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mPiston = (byte)(aData & 3);
		mActive = ((aData & 4) != 0);
		mState = (byte)((aData >>> 3) & 31);
	}
	
	@Override public byte getVisualData() {return (byte)((mPiston&3) | (mActive?4:0) | (mState << 3));}
	
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?0.5F:1.0F;}
	@Override public boolean isSideSolid2           (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	@Override public boolean allowCovers            (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		long tInputMax = getEnergySizeInputMax(aEnergyType, aSide);
		aSize = Math.abs(aSize);
		if (aSize > tInputMax) {
			if (aDoInject) overcharge(aSize, aEnergyType);
			return aAmount;
		}
		long tInput = Math.min(tInputMax - mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
		if (aDoInject) mEnergy += tConsumed * aSize;
		return tConsumed;
	}
	
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mEmitsEnergy;}
	@Override public boolean setAdjacentOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	@Override public byte setStateMode(byte aMode) {aMode = (byte)(aMode * 2 + 1); if (mState != aMode) {mState = aMode; updateClientData();} return getStateMode();}
	@Override public byte getStateMode() {return (byte)(mState / 2);}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? aEnergyType == mEnergyTypeEmitted : aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && aSide == OPOS[mFacing] && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mEnergyTypeEmitted, mEnergyTypeAccepted);}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 5;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case 0: return box(aBlock, PX_P[                         1], PX_P[                       1], PX_P[                       1], PX_N[                       1], PX_N[                       1], PX_N[                       1]);
		case 1: return box(aBlock, PX_P[                         0], PX_P[                       0], PX_P[                       0], PX_N[SIDES_AXIS_X[mFacing]?14: 0], PX_N[SIDES_AXIS_Y[mFacing]?14: 0], PX_N[SIDES_AXIS_Z[mFacing]?14: 0]);
		case 2: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]?14: 0], PX_P[SIDES_AXIS_Y[mFacing]?14: 0], PX_P[SIDES_AXIS_Z[mFacing]?14: 0], PX_N[                        0], PX_N[                       0], PX_N[                       0]);
		case 3: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0: 4], PX_P[SIDES_AXIS_Y[mFacing]? 0: 4], PX_P[SIDES_AXIS_Z[mFacing]? 0: 4], PX_N[SIDES_AXIS_X[mFacing]? 0: 4], PX_N[SIDES_AXIS_Y[mFacing]? 0: 4], PX_N[SIDES_AXIS_Z[mFacing]? 0: 4]);
		case 4: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0: 3], PX_P[SIDES_AXIS_Y[mFacing]? 0: 3], PX_P[SIDES_AXIS_Z[mFacing]? 0: 3], PX_N[SIDES_AXIS_X[mFacing]? 0: 3], PX_N[SIDES_AXIS_Y[mFacing]? 0: 3], PX_N[SIDES_AXIS_Z[mFacing]? 0: 3]);
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case 0: return ALONG_AXIS[aSide][mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[3], mRGBa), BlockTextureDefault.get(sOverlays[3]));
		case 1: case 2: return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?0:aSide==OPOS[mFacing]?1:2], mRGBa), BlockTextureDefault.get(sOverlays[aSide==mFacing?0:aSide==OPOS[mFacing]?1:2]));
		case 3: return aSide==OPOS [mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[4], sEngineColors[mState]), BlockTextureDefault.get(sOverlays[4]));
		case 4: return ALONG_AXIS[aSide][mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[5], mRGBa), BlockTextureDefault.get(sOverlays[5]));
		}
		return null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/cage"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/engine"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/colored/engine_hull")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/cage"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/engine"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_flux/overlay/engine_hull")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.engine.kinetic_flux";}
}
