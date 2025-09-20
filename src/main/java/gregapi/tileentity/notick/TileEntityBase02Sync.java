/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity.notick;

import gregapi.GT_API_Proxy;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.tileentity.ITileEntityScheduledUpdate;
import gregapi.tileentity.ITileEntitySynchronising;
import gregapi.tileentity.base.TileEntityBase01Root;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase02Sync extends TileEntityBase01Root implements ITileEntitySynchronising, ITileEntityScheduledUpdate {
	/** Gets set if/when needed. */
	public UUID mOwner = null;
	
	public TileEntityBase02Sync() {
		super(F);
	}
	
	@Override
	public final void onAdjacentBlockChange(int aTileX, int aTileY, int aTileZ) {
		super.onAdjacentBlockChange(aTileX, aTileY, aTileZ);
		onAdjacentBlockChange2(aTileX, aTileY, aTileZ);
	}
	
	public void onAdjacentBlockChange2(int aTileX, int aTileY, int aTileZ) {
		//
	}
	
	/** sends all Data to the Clients in Range */
	public void sendClientData(boolean aSendAll, EntityPlayerMP aPlayer) {
		IPacket tPacket = getClientDataPacket(aSendAll);
		if (aPlayer == null) {
			if (mOwner == null) {
				getNetworkHandler().sendToAllPlayersInRange(tPacket, worldObj, getCoords());
			} else {
				getNetworkHandler().sendToPlayerIfInRange(tPacket, mOwner, worldObj, getCoords());
				getNetworkHandlerNonOwned().sendToAllPlayersInRangeExcept(tPacket, mOwner, worldObj, getCoords());
			}
		} else {
			if (mOwner == null) {
				getNetworkHandler().sendToPlayer(tPacket, aPlayer);
			} else {
				if (mOwner.equals(aPlayer.getUniqueID())) {
					getNetworkHandler().sendToPlayer(tPacket, aPlayer);
				} else {
					getNetworkHandlerNonOwned().sendToPlayer(tPacket, aPlayer);
				}
			}
		}
	}
	
	/** @return a Packet containing all Data which has to be synchronised to the Client */
	public abstract IPacket getClientDataPacket(boolean aSendAll);
	
	@Override
	public void processPacket(INetworkHandler aNetworkHandler) {
		if (isClientSide()) mOwner = (aNetworkHandler == getNetworkHandlerNonOwned() ? NOT_YOU : null);
	}
	
	/** @return the used Network Handler. Defaults to the API Handler. */
	public INetworkHandler getNetworkHandler() {return NW_API;}
	public INetworkHandler getNetworkHandlerNonOwned() {return NW_AP2;}
	
	/** Called to send all Data to the close Clients */
	public void updateClientData() {sendClientData(T, null);}
	
	@Override public void onCoordinateChange() {super.onCoordinateChange(); updateClientData();}
	
	@Override public final net.minecraft.network.Packet getDescriptionPacket() {updateClientData(); return null;}
	
	@Override
	public final void sendUpdateToPlayer(EntityPlayerMP aPlayer) {
		sendClientData(T, aPlayer);
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		return mOwner == null || (aEntity != null && mOwner.equals(aEntity.getUniqueID()));
	}
	
	@Override
	public void onTileEntityPlaced() {
		if (isServerSide()) GT_API_Proxy.SCHEDULED_TILEENTITY_UPDATES.add(this);
	}
	
	@Override
	public void onScheduledUpdate() {
		onInitialTick();
		updateClientData();
	}
	
	public void onInitialTick() {
		//
	}
}
