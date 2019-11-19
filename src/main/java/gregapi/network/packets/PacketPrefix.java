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
import gregapi.oredict.OreDictPrefix;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketPrefix implements IPacket {
	public final OreDictPrefix mPrefix;
	
	public PacketPrefix() {
		mPrefix = null;
	}
	
	public PacketPrefix(OreDictPrefix aPrefix) {
		mPrefix = aPrefix;
	}
	
	@Override
	public byte getPacketID() {
		return 126;
	}
	
	@Override
	public ByteArrayDataOutput encode() {
		ByteArrayDataOutput rOut = ByteStreams.newDataOutput();
		rOut.writeUTF(mPrefix.mNameInternal);
		rOut.writeByte(mPrefix.mConfigStackSize);
		return rOut;
	}
	
	@Override
	public IPacket decode(ByteArrayDataInput aData) {
		String tPrefix = aData.readUTF();
		OreDictPrefix aPrefix = OreDictPrefix.sPrefixes.get(tPrefix);
		if (aPrefix != null) {
			aPrefix.setStacksize(aData.readByte());
		} else {
			ERR.println("Unknown Prefix Data Received, this means the Mod Versions don't match: " + tPrefix);
		}
		return new PacketPrefix(aPrefix);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (mPrefix == null) return;
		mPrefix.applyStackSizes();
	}
}
