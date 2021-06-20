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

package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import java.util.UUID;

import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.tileentity.ITileEntitySynchronising;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author Gregorius Techneticies
 * 
 * TileEntity with Network Code
 */
public abstract class TileEntityBase03TicksAndSync extends TileEntityBase02AdjacentTEBuffer implements ITileEntitySynchronising {
	/** Gets set if/when needed. */
	public UUID mOwner = null;
	
	/** Variable for seeing if the Tick Function is called right now. */
	public boolean mIsRunningTick = F;
	
	/** Variable for updating Data to the Client */
	private boolean mSendClientData = F;
	
	/** Gets set to true when the Block received a Block Update. */
	public boolean mBlockUpdated = F;
	
	/** @return a Packet containing all Data which has to be synchronised to the Client */
	public abstract IPacket getClientDataPacket(boolean aSendAll);
	
	/** Sends all Data to the Clients in Range */
	public void sendClientData(boolean aSendAll, EntityPlayerMP aPlayer) {
		if (aPlayer == null) {
			IPacket tPacket = getClientDataPacket(aSendAll);
			if (mOwner == null) {
				getNetworkHandler().sendToAllPlayersInRange(tPacket, worldObj, getCoords());
			} else {
				getNetworkHandler().sendToPlayerIfInRange(tPacket, mOwner, worldObj, getCoords());
				getNetworkHandlerNonOwned().sendToAllPlayersInRangeExcept(tPacket, mOwner, worldObj, getCoords());
			}
		} else if (!mSendClientData) {
			IPacket tPacket = getClientDataPacket(aSendAll);
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
	
	@Override
	public void processPacket(INetworkHandler aNetworkHandler) {
		if (isClientSide()) mOwner = (aNetworkHandler == getNetworkHandlerNonOwned() ? NOT_YOU : null);
	}
	
	/** @return the used Network Handler. Defaults to the API Handler. */
	public INetworkHandler getNetworkHandler() {return NW_API;}
	public INetworkHandler getNetworkHandlerNonOwned() {return NW_AP2;}
	
	/** Called to send all Data to the close Clients */
	public void updateClientData() {mSendClientData = T;}
	
	@Override public void onCoordinateChange() {super.onCoordinateChange(); updateClientData();}
	
	@Override public final net.minecraft.network.Packet getDescriptionPacket() {return null;}
	
	@Override
	public void validate() {
		super.validate();
		updateClientData();
	}
	
	@Override
	public final void sendUpdateToPlayer(EntityPlayerMP aPlayer) {
		sendClientData(T, aPlayer);
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		return mOwner == null || (aEntity != null && mOwner.equals(aEntity.getUniqueID()));
	}
	
	@Override
	public final void updateEntity() {
		mIsRunningTick = T;
		boolean tIsServerSide = isServerSide();
		try {
			if (mTimer == 0) {
				markDirty();
				WD.mark(this);
				onTickFirst(tIsServerSide);
			}
			if (!isDead()) onTickStart(mTimer, tIsServerSide);
			if (!isDead()) super.updateEntity();
			if (!isDead()) onTick(mTimer, tIsServerSide);
			if (!isDead() && tIsServerSide && mTimer > 2 && (mSendClientData || onTickCheck(mTimer))) {
				sendClientData(mSendClientData, null);
				mSendClientData = F;
				onTickResetChecks(mTimer, tIsServerSide);
			}
			if (!isDead()) onTickEnd(mTimer, tIsServerSide);
		} catch(Throwable e1) {
			e1.printStackTrace(ERR);
			setError((tIsServerSide?"Serverside: ":"Clientside: ") + e1);
			try {
				onTickFailed(mTimer, tIsServerSide);
			} catch(Throwable e2) {
				e2.printStackTrace(ERR);
				setError((tIsServerSide?"Serverside: ":"Clientside: ") + e2);
			}
		}
		mIsRunningTick = F;
	}
	
	/** Used to reset all Variables which have something to do with the detection of Changes. A super Call is important for this one! */
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {mBlockUpdated = F;}
	
	/** The very first Tick happening to this TileEntity */
	public void onTickFirst(boolean aIsServerSide) {/**/}
	
	/** The first Part of the Tick. */
	public void onTickStart(long aTimer, boolean aIsServerSide) {/**/}
	
	/** The regular Tick. */
	public void onTick(long aTimer, boolean aIsServerSide) {/**/}
	
	/** Use this to check if it is required to send an update to the Clients. If you want you can call "updateClientData", but then you need to return true in order for it to work.*/
	public boolean onTickCheck(long aTimer) {return F;}
	
	/** The absolutely last Part of the Tick. */
	public void onTickEnd(long aTimer, boolean aIsServerSide) {/**/}
	
	/** Gets called when there is an Exception happening during one of the Tick Functions. */
	public void onTickFailed(long aTimer, boolean aIsServerSide) {/**/}
}
