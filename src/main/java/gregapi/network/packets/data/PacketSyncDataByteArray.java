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

package gregapi.network.packets.data;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.block.IBlockSyncData;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.PacketCoordinates;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataByteArray extends PacketCoordinates {
	public byte[] mData;
	
	public PacketSyncDataByteArray(int aDecoderType) {
		super(aDecoderType);
	}
	
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArray(int aX, int aY, int aZ, byte... aData) {
		super(aX, aY, aZ);
		mData = aData;
	}
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArray(ChunkCoordinates aCoords, byte... aData) {
		super(aCoords);
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -56;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeByte(mData.length);
		aData.write(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		byte[] tData = new byte[UT.Code.unsignB(aData.readByte())];
		aData.readFully(tData);
		return new PacketSyncDataByteArray(aX, aY, aZ, tData);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncData) ((IBlockSyncData)tBlock).receiveDataByteArray(aWorld, mX, mY, mZ, mData, aNetworkHandler);
		}
	}
}
