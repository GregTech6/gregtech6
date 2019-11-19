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

import gregapi.network.INetworkHandler;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketDeathPoint extends PacketCoordinates {
	public PacketDeathPoint(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketDeathPoint(ChunkCoordinates aCoords) {
		super(aCoords);
	}
	
	public PacketDeathPoint(int aX, int aY, int aZ) {
		super(aX, aY, aZ);
	}
	
	@Override
	public byte getPacketIDOffset() {
		return +72;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketDeathPoint(aX, aY, aZ);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		LAST_DEATH_OF_THE_PLAYER = new ChunkCoordinates(mX, mY, mZ);
	}
}
