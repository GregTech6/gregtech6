package gregapi.tileentity;

import gregapi.network.INetworkHandler;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntitySynchronising extends ITileEntityUnloadable {
    /** Called when the passed Player starts watching this Chunk. */
    public void sendUpdateToPlayer(EntityPlayerMP aPlayer);
    /** Called by Packets. */
    public void processPacket(INetworkHandler aNetworkHandler);
}