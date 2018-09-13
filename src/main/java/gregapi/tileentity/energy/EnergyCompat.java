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

package gregapi.tileentity.energy;

import static gregapi.data.CS.*;

import buildcraft.api.power.ILaserTarget;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import gregapi.code.TagData;
import gregapi.data.MD;
import gregapi.data.TD;
import gregapi.util.UT;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import micdoodle8.mods.galacticraft.api.power.EnergySource;
import micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * For Internal Use.
 */
public class EnergyCompat {
	public static boolean RF_ENERGY = F, AE_ENERGY = F, IC_ENERGY = F, BC_LASER = F;
	
	/** Gets Called once during postInit to see which Interfaces are there.*/
	public static void checkAvailabilities() {
		try {
			IEnergyHandler.class.getCanonicalName();
			IEnergyReceiver.class.getCanonicalName();
			IEnergyConnection.class.getCanonicalName();
			RF_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			appeng.tile.powersink.IC2.class.getCanonicalName();
			AE_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			IEnergySink.class.getCanonicalName();
			IEnergySource.class.getCanonicalName();
			IEnergyConductor.class.getCanonicalName();
			IC_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			ILaserTarget.class.getCanonicalName();
			BC_LASER = T;
		} catch(Throwable e) {/**/}
	}
	
	public static boolean isElectricRFReceiver(TileEntity aReceiver) {
		if (aReceiver == null) return F;
		if (MD.OMT  .mLoaded && aReceiver.getClass().getName().startsWith("openmodularturrets"              )) return T;
		if (MD.TG   .mLoaded && aReceiver.getClass().getName().startsWith("techguns"                        )) return T;
		if (MD.IE   .mLoaded && aReceiver.getClass().getName().startsWith("blusunrize.immersiveengineering" )) return T;
		return F;
	}
	
	public static boolean checkOverCharge(long aSize, TileEntity aReceiver) {
		if (aSize > VMAX[3]) {
			World tWorld = aReceiver.getWorldObj();
			tWorld.setBlockToAir(aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord);
			tWorld.newExplosion(null, aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord, 5, F, T);
			return T;
		}
		return F;
	}
	
	public static long insertEnergyInto(TagData aEnergyType, byte aSideInto, long aSize, long aAmount, Object aEmitter, TileEntity aReceiver) {
		if (aAmount <= 0 || aSize == 0 || aReceiver == null) return 0;
		
		
		if (aEnergyType == TD.Energy.EU) {
			aSize = Math.abs(aSize);
			
			if (AE_ENERGY && aReceiver instanceof appeng.tile.powersink.IC2) {
				if (((appeng.tile.powersink.IC2)aReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSideInto])) {
					if (checkOverCharge(aSize, aReceiver)) return aAmount;
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((appeng.tile.powersink.IC2)aReceiver).getDemandedEnergy() >= aSize && ((appeng.tile.powersink.IC2)aReceiver).injectEnergy(FORGE_DIR[aSideInto], aSize, aSize) < aSize) rUsedAmount++;
					return rUsedAmount;
				}
				return 0;
			}
			
			if (RF_ENERGY && isElectricRFReceiver(aReceiver)) {
				if (!(aReceiver instanceof IEnergyConnection) || ((IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSideInto])) {
					if (aReceiver instanceof IEnergyReceiver) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSideInto], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
					if (aReceiver instanceof IEnergyHandler ) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSideInto], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
				}
				return 0;
			}
			
			if (COMPAT_GC != null) {
				if (aReceiver instanceof IEnergyHandlerGC) {
					if (!(aReceiver instanceof IConnector) || ((IConnector)aReceiver).canConnect(FORGE_DIR[aSideInto], NetworkType.POWER)) {
						if (checkOverCharge(aSize, aReceiver)) return aAmount;
						float tSizeToReceive = aSize * EnergyConfigHandler.IC2_RATIO, tStored = ((IEnergyHandlerGC)aReceiver).getEnergyStoredGC((EnergySource)COMPAT_GC.dir(aSideInto));
						if (tSizeToReceive >= tStored || tSizeToReceive <= ((IEnergyHandlerGC)aReceiver).getMaxEnergyStoredGC((EnergySource)COMPAT_GC.dir(aSideInto)) - tStored) {
							float tReceived = ((IEnergyHandlerGC)aReceiver).receiveEnergyGC((EnergySource)COMPAT_GC.dir(aSideInto), tSizeToReceive, F);
							if (tReceived > 0) {
								tSizeToReceive -= tReceived;
								while (tSizeToReceive > 0) {
									tReceived = ((IEnergyHandlerGC)aReceiver).receiveEnergyGC((EnergySource)COMPAT_GC.dir(aSideInto), tSizeToReceive, F);
									if (tReceived < 1) break;
									tSizeToReceive -= tReceived;
								}
								return 1;
							}
						}
					}
					return 0;
				}
			}
			
			if (IC_ENERGY) {
				TileEntity tReceiver = (aReceiver instanceof IEnergyTile || EnergyNet.instance == null ? aReceiver : EnergyNet.instance.getTileEntity(aReceiver.getWorldObj(), aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord));
				if (tReceiver instanceof IEnergySink && ((IEnergySink)tReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSideInto])) {
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((IEnergySink)tReceiver).getDemandedEnergy() >= aSize && ((IEnergySink)tReceiver).injectEnergy(FORGE_DIR[aSideInto], aSize, aSize) < aSize) rUsedAmount++;
					if (rUsedAmount > 0) {
						int tTier = ((IEnergySink)tReceiver).getSinkTier();
						if (tTier >= 0 && tTier < VMAX.length-1 && aSize > VMAX[tTier]) {
							World tWorld = tReceiver.getWorldObj();
							tWorld.setBlockToAir(tReceiver.xCoord, tReceiver.yCoord, tReceiver.zCoord);
							tWorld.newExplosion(null, tReceiver.xCoord, tReceiver.yCoord, tReceiver.zCoord, tTier+1, F, T);
							return aAmount;
						}
					}
					return rUsedAmount;
				}
			}
		}
		
		if (RF_ENERGY && aSize > 0) {
			long tSizeToReceive = 0;
			// GT KineticUnits auto-convert to RF, but only in the Push Phase, so when they are postive!
			if (aEnergyType == TD.Energy.KU) tSizeToReceive = aSize * RF_PER_EU; else
			// MJ auto-convert to RF too. And yes I do know that BuildCraft and other Mods moved away from MJ to use RF.
			if (aEnergyType == TD.Energy.MJ) tSizeToReceive = aSize * RF_PER_MJ; else
			// RF is RF and therefore doesn't really need to be converted, therefore 1:1 Ratio
			if (aEnergyType == TD.Energy.RF) tSizeToReceive = aSize; else
			// If the usage of EU from GT6 counts as RF for Consumers then allow this
			if (aEnergyType == TD.Energy.EU && EMIT_EU_AS_RF) tSizeToReceive = aSize * RF_PER_EU;
			
			if (tSizeToReceive > 0) {
				if (!(aReceiver instanceof IEnergyConnection) || ((IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSideInto])) {
					if (aReceiver instanceof IEnergyReceiver) return UT.Code.divup(((IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSideInto], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
					if (aReceiver instanceof IEnergyHandler ) return UT.Code.divup(((IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSideInto], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
				}
			}
		}
		return 0;
	}
}
