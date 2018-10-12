/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataConductor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAxle extends TileEntityBase11ConnectorStraight implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor, ITileEntityProgress {
	public long mTransferredPower = 0, mTransferredWattage = 0, mWattageLast = 0, mPower = 1, mSpeed = 32;
	public byte mRotationDir = 0, oRotationDir = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_PIPESIZE)) mSpeed = Math.max(1, aNBT.getLong(NBT_PIPESIZE));
		if (aNBT.hasKey(NBT_PIPEBANDWIDTH)) mPower = Math.max(1, aNBT.getLong(NBT_PIPEBANDWIDTH));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		// TODO
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_VOLTAGE) + mSpeed + " " + TD.Energy.RU.getLocalisedNameShort());
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_AMPERAGE) + mPower);
		if (mContactDamage) aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide) {
			if (mWattageLast == 0) mRotationDir = 0;
			mWattageLast = mTransferredWattage;
			mTransferredWattage = 0;
			mTransferredPower = 0;
		}
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mRotationDir != oRotationDir || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oRotationDir = mRotationDir;}
	@Override public void setVisualData(byte aData) {mRotationDir = aData;}
	@Override public byte getVisualData() {return mRotationDir;}
	
	public long transferRotations(byte aSide, long aSpeed, long aPower, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		if (mTimer < 1) return 0;
		
		switch(aSide) {
		case SIDE_X_NEG: mRotationDir = (byte)(aSpeed<0?1:2); break;
		case SIDE_Y_NEG: mRotationDir = (byte)(aSpeed<0?1:2); break;
		case SIDE_Z_NEG: mRotationDir = (byte)(aSpeed<0?1:2); break;
		case SIDE_X_POS: mRotationDir = (byte)(aSpeed<0?2:1); break;
		case SIDE_Y_POS: mRotationDir = (byte)(aSpeed<0?2:1); break;
		case SIDE_Z_POS: mRotationDir = (byte)(aSpeed<0?2:1); break;
		}
		
		long rUsedAmperes = 0;
		for (byte tSide : ALL_SIDES_VALID_BUT[aSide]) if (canEmitEnergyTo(tSide)) {
			if (aPower <= rUsedAmperes) break;
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (aAlreadyPassed.add(tDelegator.mTileEntity)) {
				if (tDelegator.mTileEntity instanceof MultiTileEntityAxle) {
					if (((MultiTileEntityAxle)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.RU, tDelegator.mSideOfTileEntity, F)) {
						rUsedAmperes += ((MultiTileEntityAxle)tDelegator.mTileEntity).transferRotations(tDelegator.mSideOfTileEntity, aSpeed, aPower-rUsedAmperes, aChannel, aAlreadyPassed);
					}
				} else {
					rUsedAmperes += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.RU, tDelegator.mSideOfTileEntity, aSpeed, aPower-rUsedAmperes, this, tDelegator.mTileEntity);
				}
			}
		}
		return rUsedAmperes > 0 ? addToEnergyTransferred(aSpeed, rUsedAmperes) ? rUsedAmperes : aPower : 0;
	}
	
	public boolean addToEnergyTransferred(long aSpeed, long aPower) {
		mTransferredPower += aPower;
		mTransferredWattage += Math.abs(aSpeed * aPower);
		if (Math.abs(aSpeed) > mSpeed || mTransferredPower > mPower) {
			// TODO
			return F;
		}
		return T;
	}
	
	@Override
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityEnergy) return ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.RU, aDelegator.mSideOfTileEntity, T) || ((ITileEntityEnergy)aDelegator.mTileEntity).isEnergyEmittingTo(TD.Energy.RU, aDelegator.mSideOfTileEntity, T);
		return F;
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEnergyType == TD.Energy.RU;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.RU.AS_LIST;}
	
	@Override public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && canEmitEnergyTo    (aSide);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && canAcceptEnergyFrom(aSide);}
	@Override public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return 0;}
	@Override public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ?  aDoInject ? transferRotations(aSide, aSize, aAmount, -1, new HashSetNoNulls<TileEntity>(F, this)) : aAmount : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mSpeed;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return mSpeed;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return T;}
	
	@Override public boolean isEnergyConducting(TagData aEnergyType) {return aEnergyType == TD.Energy.RU;}
	@Override public long getEnergyMaxSize(TagData aEnergyType) {return aEnergyType == TD.Energy.RU ? mSpeed : 0;}
	@Override public long getEnergyMaxPackets(TagData aEnergyType) {return aEnergyType == TD.Energy.RU ? mPower : 0;}
	@Override public long getEnergyLossPerMeter(TagData aEnergyType) {return 0;}
	@Override public OreDictMaterial getEnergyConductorMaterial() {return mMaterial;}
	@Override public OreDictMaterial getEnergyConductorInsulation() {return MT.NULL;}
	
	public boolean canEmitEnergyTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptEnergyFrom                      (byte aSide) {return connected(aSide);}
	
	@Override public long getProgressValue                  (byte aSide) {return mTransferredPower;}
	@Override public long getProgressMax                    (byte aSide) {return mPower;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.AXLES[(mConnections & 12) != 0 ? 0 : (mConnections & 48) != 0 ? 2 : 1][aSide][mRotationDir], mMaterial.mRGBaSolid);}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.AXLES[(mConnections & 12) != 0 ? 0 : (mConnections & 48) != 0 ? 2 : 1][aSide][mRotationDir], mMaterial.mRGBaSolid);}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.AXLE_ROTATION.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_wrench;}
	
	@Override public String getTileEntityName               () {return "gt.multitileentity.connector.axle.rotation";}
}
