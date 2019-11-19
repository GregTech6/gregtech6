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

import gregapi.block.IBlockErrorable;
import gregapi.network.INetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class PacketBlockError extends PacketCoordinates {
	private String mError;
	
	public PacketBlockError(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketBlockError(int aX, int aY, int aZ, String aError) {
		super(aX, aY, aZ);
		mError = aError;
	}
	
	public PacketBlockError(ChunkCoordinates aCoords, String aError) {
		super(aCoords);
		mError = aError;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return 104;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeUTF(mError);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketBlockError(aX, aY, aZ, aData.readUTF());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld instanceof World) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockErrorable) ((IBlockErrorable)tBlock).receiveBlockError(aWorld, mX, mY, mZ, mError);
		}
	}
}
