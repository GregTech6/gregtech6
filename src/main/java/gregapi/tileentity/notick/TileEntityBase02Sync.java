package gregapi.tileentity.notick;

import static gregapi.data.CS.*;

import java.util.UUID;

import gregapi.GT_API_Proxy;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.tileentity.ITileEntityScheduledUpdate;
import gregapi.tileentity.ITileEntitySynchronising;
import gregapi.tileentity.base.TileEntityBase01Root;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase02Sync extends TileEntityBase01Root implements ITileEntitySynchronising, ITileEntityScheduledUpdate {
	/** Gets set if/when needed. */
	public UUID mOwner;
	
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
	
	@Override public final net.minecraft.network.Packet getDescriptionPacket() {return null;}
	
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