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
