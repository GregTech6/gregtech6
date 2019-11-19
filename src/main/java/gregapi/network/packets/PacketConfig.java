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

package gregapi.network.packets;

import static gregapi.data.CS.*;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketConfig implements IPacket {
	@Override
	public byte getPacketID() {
		return 127;
	}
	
	@Override
	public ByteArrayDataOutput encode() {
		ByteArrayDataOutput rOut = ByteStreams.newDataOutput();
		rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_SAND);
		rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_ROCK);
		rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_ORES);
		return rOut;
	}
	
	@Override
	public IPacket decode(ByteArrayDataInput aData) {
		HARDNESS_MULTIPLIER_SAND = aData.readDouble();
		HARDNESS_MULTIPLIER_ROCK = aData.readDouble();
		HARDNESS_MULTIPLIER_ORES = aData.readDouble();
		return this;
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		// Since this actually doesn't process anything I can set the static variables directly in decode.
	}
}
