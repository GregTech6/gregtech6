/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.code.TagData;
import gregapi.data.MD;
import gregapi.data.TD;
import gregapi.util.UT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * For mostly Internal Use.
 */
public class EnergyCompat {
	public static boolean RF_ENERGY = F, RF_ENERGY_NEW = F, AE_ENERGY = F, FL_ENERGY = F, WD_ENERGY = F, IC_ENERGY = F, BB_ENERGY = F, GC_ENERGY = F, BC_LASER = F;
	
	/** Gets Called once during postInit to see which Interfaces are there and Classloaded. */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void checkAvailabilities() {
		try {
			com.rwtema.funkylocomotion.blocks.TilePusher                 .class.getCanonicalName();
			com.rwtema.funkylocomotion.blocks.TileBooster                .class.getCanonicalName();
			FL_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			cr0s.warpdrive.block.TileEntityAbstractEnergy                .class.getCanonicalName();
			WD_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			cofh.api.energy.IEnergyHandler                               .class.getCanonicalName();
			cofh.api.energy.IEnergyConnection                            .class.getCanonicalName();
			RF_ENERGY = T;
			// Some Mods do not include this File, due to badly referencing old RF-API stuff, so this gets a separate Boolean now.
			cofh.api.energy.IEnergyReceiver                              .class.getCanonicalName();
			RF_ENERGY_NEW = T;
		} catch(Throwable e) {/**/}
		try {
			appeng.tile.powersink.IC2                                    .class.getCanonicalName();
			AE_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			ic2.api.energy.tile.IEnergyTile                              .class.getCanonicalName();
			ic2.api.energy.tile.IEnergySink                              .class.getCanonicalName();
			ic2.api.energy.tile.IEnergySource                            .class.getCanonicalName();
			ic2.api.energy.tile.IEnergyConductor                         .class.getCanonicalName();
			IC_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			com.builtbroken.mc.api.energy.IEnergyBufferProvider          .class.getCanonicalName();
			com.builtbroken.mc.api.energy.IEnergyBuffer                  .class.getCanonicalName();
			BB_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC      .class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.power.EnergySource          .class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.transmission.tile.IConnector.class.getCanonicalName();
			micdoodle8.mods.galacticraft.api.transmission.NetworkType    .class.getCanonicalName();
			micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler .class.getCanonicalName();
			GC_ENERGY = T;
		} catch(Throwable e) {/**/}
		try {
			buildcraft.api.power.ILaserTarget                            .class.getCanonicalName();
			BC_LASER = T;
		} catch(Throwable e) {/**/}
	}
	
	public static boolean isElectricRFReceiver(TileEntity aReceiver) {
		if (aReceiver == null) return F;
		String tClass = null;
		if (MD.OMT .mLoaded) {                    tClass = aReceiver.getClass().getName(); if (tClass.startsWith("openmodularturrets"             )) return T;}
		if (MD.IE  .mLoaded) {if (tClass == null) tClass = aReceiver.getClass().getName(); if (tClass.startsWith("blusunrize.immersiveengineering")) return T;}
		if (MD.OC  .mLoaded) {if (tClass == null) tClass = aReceiver.getClass().getName(); if (tClass.startsWith("li.cil.oc"                      )) return T;}
		if (MD.TG  .mLoaded) {if (tClass == null) tClass = aReceiver.getClass().getName(); if (tClass.startsWith("techguns"                       )) return T;}
		return F;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean canConnectElectricity(TileEntity aThis, TileEntity aTarget, byte aSide) {
		if (aTarget == null) return F;
		if (aTarget instanceof ITileEntityEnergy                                  ) return ((ITileEntityEnergy                   )aTarget).isEnergyAcceptingFrom(TD.Energy.EU, aSide, T) || ((ITileEntityEnergy                   )aTarget).isEnergyEmittingTo(TD.Energy.EU, aSide, T);
		if (aTarget instanceof gregapi.tileentity.ITileEntityEnergy               ) return ((gregapi.tileentity.ITileEntityEnergy)aTarget).isEnergyAcceptingFrom(TD.Energy.EU, aSide, T) || ((gregapi.tileentity.ITileEntityEnergy)aTarget).isEnergyEmittingTo(TD.Energy.EU, aSide, T);
		// IMPORTANT: Ignore the Fact that this SEEMS to be unused. It does exist, SOMETIMES.
		if (aTarget instanceof gregtech.api.interfaces.tileentity.IEnergyConnected) return ((gregtech.api.interfaces.tileentity.IEnergyConnected)aTarget).inputEnergyFrom(aSide) || ((gregtech.api.interfaces.tileentity.IEnergyConnected)aTarget).outputsEnergyTo(aSide);
		
		if (AE_ENERGY &&  aTarget instanceof appeng.tile.powersink.IC2) return aThis == null || ((appeng.tile.powersink.IC2)aTarget).acceptsEnergyFrom(aThis, FORGE_DIR[aSide]);
		
		if (FL_ENERGY && (aTarget instanceof com.rwtema.funkylocomotion.blocks.TilePusher || aTarget instanceof com.rwtema.funkylocomotion.blocks.TileBooster)) return T;
		
		if (WD_ENERGY &&  aTarget instanceof cr0s.warpdrive.block.TileEntityAbstractEnergy) return ((cr0s.warpdrive.block.TileEntityAbstractEnergy)aTarget).energy_canInput(FORGE_DIR[aSide]);
		
		if (GC_ENERGY &&  aTarget instanceof micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC && (!(aTarget instanceof micdoodle8.mods.galacticraft.api.transmission.tile.IConnector) || ((micdoodle8.mods.galacticraft.api.transmission.tile.IConnector)aTarget).canConnect(FORGE_DIR[aSide], micdoodle8.mods.galacticraft.api.transmission.NetworkType.POWER))) return T;
		
		if (BB_ENERGY &&  aTarget instanceof com.builtbroken.mc.api.energy.IEnergyBufferProvider && ((com.builtbroken.mc.api.energy.IEnergyBufferProvider)aTarget).getEnergyBuffer(FORGE_DIR[aSide]) != null) return T;
		
		if (IC_ENERGY) {
			TileEntity tConnected = (aTarget instanceof ic2.api.energy.tile.IEnergyTile || ic2.api.energy.EnergyNet.instance == null ? aTarget : ic2.api.energy.EnergyNet.instance.getTileEntity(aTarget.getWorldObj(), aTarget.xCoord, aTarget.yCoord, aTarget.zCoord));
			if (tConnected instanceof ic2.api.energy.tile.IEnergySink   && (aThis == null || ((ic2.api.energy.tile.IEnergySink  )tConnected).acceptsEnergyFrom(aThis, FORGE_DIR[aSide]))) return T;
			if (tConnected instanceof ic2.api.energy.tile.IEnergySource && (aThis == null || ((ic2.api.energy.tile.IEnergySource)tConnected).emitsEnergyTo    (aThis, FORGE_DIR[aSide]))) return T;
		}
		
		// IMPORTANT: Ignore the Fact that IEnergyConnection is SUPPOSEDLY part of IEnergyHandler. There is versions of the RF API in circulation, where this is NOT the case!!!
		if (RF_ENERGY && (EMIT_EU_AS_RF || isElectricRFReceiver(aTarget)) && (aTarget instanceof cofh.api.energy.IEnergyHandler || (RF_ENERGY_NEW && aTarget instanceof cofh.api.energy.IEnergyReceiver))) return !(aTarget instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aTarget).canConnectEnergy(FORGE_DIR[aSide]);
		
		return F;
	}
	
	public static boolean checkOverCharge(long aSize, TileEntity aReceiver) {
		if (aSize > VMAX[3]) {
			World tWorld = aReceiver.getWorldObj();
			tWorld.setBlockToAir(aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord);
			tWorld.newExplosion(null, aReceiver.xCoord+0.5, aReceiver.yCoord+0.5, aReceiver.zCoord+0.5, 5, F, T);
			return T;
		}
		return F;
	}
	
	@SuppressWarnings("deprecation")
	public static long insertEnergyInto(TagData aEnergyType, byte aSide, long aSize, long aAmount, Object aEmitter, TileEntity aReceiver) {
		if (aAmount <= 0 || aSize == 0 || aReceiver == null) return 0;
		// Obvious GT6 Blocks should not be eligible for Compat. Should reduce some IC2 Compat Lag.
		if (aReceiver instanceof gregapi.tileentity.base.TileEntityBase01Root) return 0;
		
		if (aEnergyType == TD.Energy.EU) {
			// Nothing here needs the Negative Part of this, so it's gonna be skipped.
			aSize = Math.abs(aSize);
			
			// Applied Energistics gets a special case.
			if (AE_ENERGY && aReceiver instanceof appeng.tile.powersink.IC2) {
				if (((appeng.tile.powersink.IC2)aReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSide])) {
					if (checkOverCharge(aSize, aReceiver)) return aAmount;
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((appeng.tile.powersink.IC2)aReceiver).getDemandedEnergy() >= aSize && ((appeng.tile.powersink.IC2)aReceiver).injectEnergy(FORGE_DIR[aSide], aSize, aSize) < aSize) rUsedAmount++;
					return rUsedAmount;
				}
				return 0;
			}
			
			// Funky Locomotion includes the OLD RF-API that it does not even use, while also using NEWER parts of the RF API that it does not include... This sort of utter Bullshit makes RF-Mods incompatible with each other...
			if (FL_ENERGY) {
				if (aReceiver instanceof com.rwtema.funkylocomotion.blocks.TilePusher ) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((com.rwtema.funkylocomotion.blocks.TilePusher )aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU * 10), F), aSize * RF_PER_EU * 10);
				if (aReceiver instanceof com.rwtema.funkylocomotion.blocks.TileBooster) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((com.rwtema.funkylocomotion.blocks.TileBooster)aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU * 10), F), aSize * RF_PER_EU * 10);
			}
			
			// WarpDrive does not include ANY of the APIs it uses inside its Jar, which is a good thing, but it does force me to do this special case...
			if (WD_ENERGY) {
				if (aReceiver instanceof cr0s.warpdrive.block.TileEntityAbstractEnergy) {
					if (((cr0s.warpdrive.block.TileEntityAbstractEnergy)aReceiver).energy_getEnergyStored() >= ((cr0s.warpdrive.block.TileEntityAbstractEnergy)aReceiver).energy_getMaxStorage()) return 0;
					if (checkOverCharge(aSize, aReceiver)) return aAmount;
					// I love how this does not have any sanity checks, and not even a boolean to check if it worked XD
					((cr0s.warpdrive.block.TileEntityAbstractEnergy)aReceiver).energy_consume(-aSize);
					return 1;
				}
			}
			
			// GalactiCraft and its Addons
			if (GC_ENERGY && COMPAT_GC != null) {
				if (aReceiver instanceof micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC && !(RF_ENERGY && isElectricRFReceiver(aReceiver))) {
					if (!(aReceiver instanceof micdoodle8.mods.galacticraft.api.transmission.tile.IConnector) || ((micdoodle8.mods.galacticraft.api.transmission.tile.IConnector)aReceiver).canConnect(FORGE_DIR[aSide], micdoodle8.mods.galacticraft.api.transmission.NetworkType.POWER)) {
						if (checkOverCharge(aSize, aReceiver)) return aAmount;
						float tSizeToReceive = aSize * micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler.IC2_RATIO, tStored = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).getEnergyStoredGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide));
						if (tSizeToReceive >= tStored || tSizeToReceive <= ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).getMaxEnergyStoredGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide)) - tStored) {
							float tReceived = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).receiveEnergyGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide), tSizeToReceive, F);
							if (tReceived > 0) {
								tSizeToReceive -= tReceived;
								while (tSizeToReceive > 0) {
									tReceived = ((micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC)aReceiver).receiveEnergyGC((micdoodle8.mods.galacticraft.api.power.EnergySource)COMPAT_GC.dir(aSide), tSizeToReceive, F);
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
			
			// Voltz Stuff
			if (BB_ENERGY && aReceiver instanceof com.builtbroken.mc.api.energy.IEnergyBufferProvider) {
				Object tEnergyBuffer = ((com.builtbroken.mc.api.energy.IEnergyBufferProvider)aReceiver).getEnergyBuffer(FORGE_DIR[aSide]);
				if (tEnergyBuffer != null) {
					//noinspection CastCanBeRemovedNarrowingVariableType
					return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((com.builtbroken.mc.api.energy.IEnergyBuffer)tEnergyBuffer).addEnergyToStorage(UT.Code.bind31(aSize * aAmount) * J_PER_EU, T), aSize * J_PER_EU);
				}
			}
			
			// Electricity alike RF Receivers that are whitelisted for my Power System.
			if (RF_ENERGY && (EMIT_EU_AS_RF || isElectricRFReceiver(aReceiver))) {
				if (!(aReceiver instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSide])) {
					if (RF_ENERGY_NEW && aReceiver instanceof cofh.api.energy.IEnergyReceiver) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((cofh.api.energy.IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
					if (                 aReceiver instanceof cofh.api.energy.IEnergyHandler ) return checkOverCharge(aSize, aReceiver) ? aAmount : UT.Code.divup(((cofh.api.energy.IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * aSize * RF_PER_EU), F), aSize * RF_PER_EU);
				}
				return 0;
			}
			
			// Since GT5U is still basically an IC2-Addon, I don't want the IC2 Power System to come before this by accident.
			if (aReceiver instanceof gregtech.api.interfaces.tileentity.IEnergyConnected) {
				return ((gregtech.api.interfaces.tileentity.IEnergyConnected)aReceiver).injectEnergyUnits(aSide, aSize, aAmount);
			}
			
			// IC2 Power at last, because special cases should always override the very "compatible" IC2 Stuff.
			if (IC_ENERGY) {
				TileEntity tReceiver = (aReceiver instanceof ic2.api.energy.tile.IEnergyTile || ic2.api.energy.EnergyNet.instance == null ? aReceiver : ic2.api.energy.EnergyNet.instance.getTileEntity(aReceiver.getWorldObj(), aReceiver.xCoord, aReceiver.yCoord, aReceiver.zCoord));
				if (tReceiver instanceof ic2.api.energy.tile.IEnergySink && ((ic2.api.energy.tile.IEnergySink)tReceiver).acceptsEnergyFrom(aEmitter instanceof TileEntity ? (TileEntity)aEmitter : null, FORGE_DIR[aSide])) {
					long rUsedAmount = 0;
					while (aAmount > rUsedAmount && ((ic2.api.energy.tile.IEnergySink)tReceiver).getDemandedEnergy() >= (rUsedAmount <= 0 && aSize <= VMAX[0] ? 4 : aSize) && ((ic2.api.energy.tile.IEnergySink)tReceiver).injectEnergy(FORGE_DIR[aSide], aSize, aSize) < aSize) rUsedAmount++;
					if (rUsedAmount > 0) {
						int tTier = ((ic2.api.energy.tile.IEnergySink)tReceiver).getSinkTier();
						if (tTier >= 0 && tTier < VMAX.length-1 && aSize > VMAX[tTier]) {
							World tWorld = tReceiver.getWorldObj();
							tWorld.setBlockToAir(tReceiver.xCoord, tReceiver.yCoord, tReceiver.zCoord);
							tWorld.newExplosion(null, tReceiver.xCoord+0.5, tReceiver.yCoord+0.5, tReceiver.zCoord+0.5, tTier+1, F, T);
							return aAmount;
						}
					}
					return rUsedAmount;
				}
			}
			
			// No need to check the rest since this isn't RF.
			return 0;
		}
		
		if (RF_ENERGY && aSize > 0) {
			long tSizeToReceive = 0;
			// GT KineticUnits auto-convert to RF, but only in the Push Phase, so when they are postive!
			if (aEnergyType == TD.Energy.KU) tSizeToReceive = aSize * RF_PER_EU; else
			// MJ auto-convert to RF too. And yes I do know that BuildCraft and other Mods moved away from MJ to use RF.
			if (aEnergyType == TD.Energy.MJ) tSizeToReceive = aSize * RF_PER_MJ; else
			// RF is RF and therefore doesn't really need to be converted, meaning a 1:1 Ratio
			if (aEnergyType == TD.Energy.RF) tSizeToReceive = aSize;
			
			if (tSizeToReceive > 0) {
				if (!(aReceiver instanceof cofh.api.energy.IEnergyConnection) || ((cofh.api.energy.IEnergyConnection)aReceiver).canConnectEnergy(FORGE_DIR[aSide])) {
					if (RF_ENERGY_NEW && aReceiver instanceof cofh.api.energy.IEnergyReceiver) return UT.Code.divup(((cofh.api.energy.IEnergyReceiver)aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
					if (                 aReceiver instanceof cofh.api.energy.IEnergyHandler ) return UT.Code.divup(((cofh.api.energy.IEnergyHandler )aReceiver).receiveEnergy(FORGE_DIR[aSide], UT.Code.bind31(aAmount * tSizeToReceive), F), tSizeToReceive);
				}
			}
		}
		return 0;
	}
}
