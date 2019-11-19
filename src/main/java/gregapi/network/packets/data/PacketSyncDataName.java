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
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataName extends PacketCoordinates {
	public String mData = "";
	
	public PacketSyncDataName(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSyncDataName(int aX, int aY, int aZ, String aData) {
		super(aX, aY, aZ);
		mData = aData;
	}
	public PacketSyncDataName(ChunkCoordinates aCoords, String aData) {
		super(aCoords);
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return 64;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeUTF(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSyncDataName(aX, aY, aZ, aData.readUTF());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncData) ((IBlockSyncData)tBlock).receiveDataName(aWorld, mX, mY, mZ, mData, aNetworkHandler);
		}
	}
}
