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
import gregapi.oredict.OreDictMaterial;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataConductor;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAxle extends TileEntityBase11ConnectorStraight implements ITileEntityQuickObstructionCheck, ITileEntityEnergy, ITileEntityEnergyDataConductor, ITileEntityProgress {
	public long mTransferredPower = 0, mTransferredWattage = 0, mWattageLast = 0, mPower = 1, mSpeed = 32;
	
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
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_VOLTAGE) + mSpeed + " " + TD.Energy.RU.getLocalisedNameShort() + " (" + VN[UT.Code.tierMin(mSpeed)] + ")");
		aList.add(Chat.CYAN     + LH.get(LH.WIRE_STATS_AMPERAGE) + mPower);
		if (mContactDamage) aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide) {
			// TODO
		}
	}
	
	public long transferRotations(byte aSide, long aSpeed, long aPower, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		if (mTimer < 1) return 0;
		
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
	
	public boolean addToEnergyTransferred(long aVoltage, long aAmperage) {
		mTransferredPower += aAmperage;
		mTransferredWattage += Math.abs(aVoltage * aAmperage);
		if (Math.abs(aVoltage) > mSpeed || mTransferredPower > mPower) {
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
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return null;} // TODO
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return null;} // TODO
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.AXLE_ROTATION.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_wrench;}
	
	@Override public String getTileEntityName               () {return "gt.multitileentity.connector.axle.rotation";}
}
