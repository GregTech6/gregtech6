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

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.network.INetworkHandler;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class PacketBlockEvent extends PacketCoordinates {
	public byte mID = 0, mData = 0;
	
	public PacketBlockEvent(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketBlockEvent(int aX, int aY, int aZ, byte aID, byte aData) {
		super(aX, aY, aZ);
		mID = aID;
		mData = aData;
	}
	
	public PacketBlockEvent(ChunkCoordinates aCoords, byte aID, byte aData) {
		super(aCoords);
		mID = aID;
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return 112;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeByte(mID);
		aData.writeByte(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketBlockEvent(aX, aY, aZ, aData.readByte(), aData.readByte());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld instanceof World) aWorld.getBlock(mX, mY, mZ).onBlockEventReceived((World)aWorld, mX, mY, mZ, mID, mData);
	}
}
