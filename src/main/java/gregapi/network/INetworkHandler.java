package gregapi.network;

import java.util.UUID;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface INetworkHandler {
	/** It sends a Packet from Client to Server. */
	public void sendToServer(IPacket aPacket);
	/** It sends a Packet to the Player, who is mentioned inside the Parameter. */
	public void sendToPlayer(IPacket aPacket, EntityPlayerMP aPlayer);
	/** It sends a Packet to all Players, who are in the specified Range. */
	public void sendToAllAround(IPacket aPacket, TargetPoint aPosition);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToAllPlayersInRange(IPacket aPacket, World aWorld, int aX, int aZ);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToAllPlayersInRange(IPacket aPacket, World aWorld, ChunkCoordinates aCoords);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToPlayerIfInRange(IPacket aPacket, UUID aPlayer, World aWorld, int aX, int aZ);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToPlayerIfInRange(IPacket aPacket, UUID aPlayer, World aWorld, ChunkCoordinates aCoords);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToAllPlayersInRangeExcept(IPacket aPacket, UUID aPlayer, World aWorld, int aX, int aZ);
	/** It sends a Packet to all Players, who watch the Chunk on these X/Z Coordinates. */
	public void sendToAllPlayersInRangeExcept(IPacket aPacket, UUID aPlayer, World aWorld, ChunkCoordinates aCoords);
	
	/** For very advanced usage only! */
	public FMLEmbeddedChannel getChannel(Side aSide);
}