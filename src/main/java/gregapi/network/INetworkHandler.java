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
