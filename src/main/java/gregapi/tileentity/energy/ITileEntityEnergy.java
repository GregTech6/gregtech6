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

package gregapi.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.WD;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 * 
 * Interface for getting connected to any Energy Network.
 * 
 * Note:
 * A correct implementation of this is only required Server Side (worldObj.isRemote == false).
 * Client Side Worlds (worldObj.isRemote == true) should never access any of these Functions nor expect anything from calling them.
 * 
 * This is the moved Version of the Interface, so better use this instead.
 */
@SuppressWarnings("deprecation")
public interface ITileEntityEnergy extends gregapi.tileentity.ITileEntityEnergy {
	/**
	 * You do not have to check for this Function, this is only for things like Energy Network optimisation and similar.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aEmitting if it is asked to emit this Energy Type, otherwise it is asked to accept this Energy Type.
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return if this TileEntity has anything to do with this Type of Energy, depending on insert or extract request. The returning Value must be constant for this TileEntity.
	 */
	@Override
	public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting);
	
	/**
	 * Gets all the Types of Energy, which are relevant to this TileEntity.
	 * 
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (should return all related Types of Energy)
	 * @return any Type of Energy that is related to this TileEntity. This is especially useful for Data Displays and Redstone Conditions, where people can select the Energy Type via GUI or something.
	 */
	@Override
	public Collection<TagData> getEnergyTypes(byte aSide);
	
	// Connectivity
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @param aTheoretical true if this is only checking for Conductor Connections. Use it in case things like Redstone or Covers for example can toggle the connectivity of this Side for this Energy Type, but you still want to visually connect to it, even if it temporarily wouldn't accept Energy Interactions from that Side right now. Basically, so that the Conductor doesn't visually toggle on/off all the time causing Sync Lag.
	 */
	@Override
	public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it. As in do not make a direct call to set Blocks or explode things, just set a flag to do it in the next tick.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @param aTheoretical true if this is only checking for Conductor Connections. Use it in case things like Redstone or Covers for example can toggle the connectivity of this Side for this Energy Type, but you still want to visually connect to it, even if it temporarily wouldn't accept Energy Interactions from that Side right now. Basically, so that the Conductor doesn't visually toggle on/off all the time causing Sync Lag.
	 */
	@Override
	public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical);
	
	
	// Push based Energy.
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it. As in do not make a direct call to set Blocks or explode things, just set a flag to do it in the next tick.
	 * 
	 * Inject Energy Call for Electricity. Gets called by EnergyEmitters to inject Energy into your Block
	 * 
	 * Note: The IMPLEMENTOR of this Function has to check for isEnergyAcceptingFrom, when implementing this Function, NOT the one injecting the Energy, because the Network won't check for that by itself.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aDoInject if this is supposed to increase the internal Energy. true = Yes, this is a normal Operation. false = No, this is just a simulation.
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return amount of used aAmount. 0 if not accepted anything.
	 */
	@Override
	public long doEnergyInjection(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * Some Energy Networks use this Value to pre-calculate the Energy Flow.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @param aSize the Energy Packet Size to be demanded. getEnergySizeInputRecommended is the recommended Power Level.
	 * @return The Amount of Energy Packets of aSize Size this TileEntity needs
	 */
	@Override
	public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize);
	
	
	// Pull based Energy.
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * Extract Energy Call for Electricity. Gets called by Energy Networks to extract Energy from your Block
	 * 
	 * Note: The IMPLEMENTOR of this Function has to check for isEnergyEmittingTo, when implementing this Function, NOT the one extracting the Energy, because the Network won't check for that by itself.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aDoExtract if this is supposed to decrease the internal Energy. true = Yes, this is a normal Operation. false = No, this is just a simulation.
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return amount of taken aAmount. 0 if not accepted anything.
	 */
	@Override
	public long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * Some Energy Networks use this Value to pre-calculate the Energy Flow.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @param aSize the Energy Packet Size to be taken. getEnergySizeOutputRecommended is the recommended Power Level.
	 * @return The Amount of Energy Packets of aSize Size this TileEntity offers
	 */
	@Override
	public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize);
	
	
	// Parameters for the minimum Packet Sizes in order for things to actually work.
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The minimum Energy Packet Size for the INPUT of this Object.
	 */
	@Override
	public long getEnergySizeInputMin(TagData aEnergyType, byte aSide);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The minimum Energy Packet Size for the OUTPUT of this Object.
	 */
	@Override
	public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide);
	
	
	// Parameters for the recommended Packet Sizes.
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The recommended Energy Packet Size for the INPUT of this Object.
	 */
	@Override
	public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The recommended Energy Packet Size for the OUTPUT of this Object.
	 */
	@Override
	public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide);
	
	
	// Parameters for the maximum Packet Sizes before bad shit happens. Anything greater than this Value would cause an explosion in a GregTech Machine. Just saying.
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The maximum Energy Packet Size for the INPUT of this Object.
	 */
	@Override
	public long getEnergySizeInputMax(TagData aEnergyType, byte aSide);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return The maximum Energy Packet Size for the OUTPUT of this Object.
	 */
	@Override
	public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide);
	
	/** Utility for the Energy Networks */
	public static class Util {
		/**
		 * Emits Energy to all the Blocks adjacent to an Output Side.
		 * Also compatible with IC2 TileEntities when electric and RF TileEntities when RedstoneFlux.
		 * @param aEnergyType The Type of Energy to be emitted
		 * @param aSize The Minimum Transfer Rate of Energy (like Voltage for example). This can be negative too in case it has a direction for example (clockwise/counterclockwise)
		 * @param aAmount The Amount of Packets in size of aSize to be emitted (like Amperage for example)
		 * @param aEmitter The TileEntity which emits the Energy.
		 * @return the amount of used Energy Packets.
		 */
		public static final long emitEnergyToNetwork(TagData aEnergyType, long aSize, long aAmount, gregapi.tileentity.ITileEntityEnergy aEmitter) {
			long rUsedAmount = 0;
			for (byte tSide : ALL_SIDES_VALID) if (aEmitter.isEnergyEmittingTo(aEnergyType, tSide, F)) {
				rUsedAmount += emitEnergyToSide(aEnergyType, tSide, aSize, aAmount-rUsedAmount, (TileEntity)aEmitter);
				if (aAmount <= rUsedAmount) break;
			}
			return rUsedAmount;
		}
		
		/**
		 * Emits Energy to the adjacent Block.
		 * Also compatible with IC2 TileEntities when electric and RF TileEntities when RedstoneFlux.
		 * @param aEnergyType The Type of Energy to be emitted
		 * @param aSideOutOf The Side of the TileEntity to output Energy out of.
		 * @param aSize The Minimum Transfer Rate of Energy (like Voltage for example). This can be negative too in case it has a direction for example (clockwise/counterclockwise)
		 * @param aAmount The Amount of Packets in size of aSize to be emitted (like Amperage for example)
		 * @param aEmitter The TileEntity which emits the Energy.
		 * @return the amount of used Energy Packets.
		 */
		public static final long emitEnergyToSide(TagData aEnergyType, byte aSideOutOf, long aSize, long aAmount, TileEntity aEmitter) {
			DelegatorTileEntity<TileEntity> tDelegator = aEmitter instanceof IHasWorldAndCoords ? ((IHasWorldAndCoords)aEmitter).getAdjacentTileEntity(aSideOutOf) : WD.te(aEmitter.getWorldObj(), aEmitter.xCoord+OFFX[aSideOutOf], aEmitter.yCoord+OFFY[aSideOutOf], aEmitter.zCoord+OFFZ[aSideOutOf], OPOS[aSideOutOf], F);
			return insertEnergyInto(aEnergyType, aSize, aAmount, aEmitter, tDelegator);
		}
		
		/**
		 * Inserts Energy into the TileEntity.
		 * Also compatible with IC2 TileEntities when electric and RF TileEntities when RedstoneFlux.
		 * @param aEnergyType The Type of Energy to be emitted
		 * @param aSideInto The Side of the receiving TileEntity to insert the Energy into.
		 * @param aSize The Minimum Transfer Rate of Energy (like Voltage for example). This can be negative too in case it has a direction for example (clockwise/counterclockwise)
		 * @param aAmount The Amount of Packets in size of aSize to be emitted (like Amperage for example)
		 * @param aEmitter The TileEntity which emits the Energy. May be null!
		 * @param aReceiver The TileEntity which receives the Energy.
		 * @return the amount of used Energy Packets.
		 */
		public static final long insertEnergyInto(TagData aEnergyType, byte aSideInto, long aSize, long aAmount, Object aEmitter, TileEntity aReceiver) {
			return aReceiver instanceof ITileEntityEnergy ? ((ITileEntityEnergy)aReceiver).doEnergyInjection(aEnergyType, aSideInto, aSize, aAmount, T) : EnergyCompat.insertEnergyInto(aEnergyType, aSideInto, aSize, aAmount, aEmitter, aReceiver);
		}
		
		/**
		 * Inserts Energy into the TileEntity.
		 * Also compatible with IC2 TileEntities when electric and RF TileEntities when RedstoneFlux.
		 * @param aEnergyType The Type of Energy to be emitted
		 * @param aSideInto The Side of the receiving TileEntity to insert the Energy into.
		 * @param aSize The Minimum Transfer Rate of Energy (like Voltage for example). This can be negative too in case it has a direction for example (clockwise/counterclockwise)
		 * @param aAmount The Amount of Packets in size of aSize to be emitted (like Amperage for example)
		 * @param aEmitter The TileEntity which emits the Energy. May be null!
		 * @param aReceiver The TileEntity which receives the Energy.
		 * @return the amount of used Energy Packets.
		 */
		public static final long insertEnergyInto(TagData aEnergyType, long aSize, long aAmount, Object aEmitter, DelegatorTileEntity<TileEntity> aReceiver) {
			return insertEnergyInto(aEnergyType, aReceiver.mSideOfTileEntity, aSize, aAmount, aEmitter, aReceiver.mTileEntity);
		}
	}
}
