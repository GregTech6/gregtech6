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

package gregapi.compat.galacticraft;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.compat.CompatBase;
import gregapi.worldgen.GT6WorldGenerator;
import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.api.power.EnergySource;
import micdoodle8.mods.galacticraft.api.power.EnergySource.EnergySourceAdjacent;
import micdoodle8.mods.galacticraft.api.power.IEnergyHandlerGC;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.api.transmission.tile.IConnector;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraftforge.common.MinecraftForge;

public class CompatGC extends CompatBase implements ICompatGC {
	public final EnergySourceAdjacent[] ENERGY_DIR = {new EnergySourceAdjacent(FORGE_DIR[0]), new EnergySourceAdjacent(FORGE_DIR[1]), new EnergySourceAdjacent(FORGE_DIR[2]), new EnergySourceAdjacent(FORGE_DIR[3]), new EnergySourceAdjacent(FORGE_DIR[4]), new EnergySourceAdjacent(FORGE_DIR[5]), new EnergySourceAdjacent(FORGE_DIR[6])};
	
	public CompatGC() {
		NetworkType.POWER.toString();
		IConnector.class.getCanonicalName();
		IEnergyHandlerGC.class.getCanonicalName();
		EnergySource.EnergySourceAdjacent.class.getCanonicalName();
		EnergyConfigHandler.class.getCanonicalName();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override public Object dir(byte aSide) {return ENERGY_DIR[aSide];}
	
	@SubscribeEvent
	public void populate(GCCoreEventPopulate.Post aEvent) {
		GT6WorldGenerator.generate(aEvent.worldObj, aEvent.chunkX, aEvent.chunkZ, T);
	}
}
