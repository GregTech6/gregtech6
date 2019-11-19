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
import com.google.common.io.ByteStreams;

import gregapi.network.IPacket;
import gregapi.util.UT;
import net.minecraft.util.ChunkCoordinates;

/**
 * @author Gregorius Techneticies
 * 
 * optimised to have a smaller Packet Size within XZ[-32768;+32767] and within Y[0;+255] (since there are Worlds with larger Heights than just 256).
 * Because most people don't go farther, they shouldn't be punished with Bandwidth waste-age, if they are not that far away.
 */
public abstract class PacketCoordinates implements IPacket {
	public final int mX;
	public final int mY;
	public final int mZ;
	private byte mDecoderType = 0;
	
	/** @param aDecoderType has to be a Number between [0 and 7] */
	public PacketCoordinates(int aDecoderType) {
		mDecoderType = (byte)(aDecoderType & 7);
		mX = mY = mZ = 0;
	}
	
	/** The Super-Constructor for the Packet to be sent. */
	public PacketCoordinates(int aX, int aY, int aZ) {
		mX = aX;
		mY = aY;
		mZ = aZ;
		mDecoderType = (byte)((mX>=Short.MIN_VALUE&&mX<=Short.MAX_VALUE?1:0)|(mY>=0&&mY<=255?2:0)|(mZ>=Short.MIN_VALUE&&mZ<=Short.MAX_VALUE?4:0));
	}
	
	/** The Super-Constructor for the Packet to be sent. */
	public PacketCoordinates(ChunkCoordinates aCoords) {
		mX = aCoords.posX;
		mY = aCoords.posY;
		mZ = aCoords.posZ;
		mDecoderType = (byte)((mX>=Short.MIN_VALUE&&mX<=Short.MAX_VALUE?1:0)|(mY>=0&&mY<=255?2:0)|(mZ>=Short.MIN_VALUE&&mZ<=Short.MAX_VALUE?4:0));
	}
	
	@Override
	public final byte getPacketID() {
		return (byte)(getPacketIDOffset()+mDecoderType); // 8 Packet Handlers need to be registered to receive the possibilities of the 0-7 Range of mDecoderType.
	}
	
	@Override
	public final ByteArrayDataOutput encode() {
		ByteArrayDataOutput rOut = ByteStreams.newDataOutput(8);
		if ((mDecoderType&1)!=0) rOut.writeShort(mX); else rOut.writeInt  (mX);
		if ((mDecoderType&2)!=0) rOut.writeByte (mY); else rOut.writeShort(mY);
		if ((mDecoderType&4)!=0) rOut.writeShort(mZ); else rOut.writeInt  (mZ);
		return encode2(rOut);
	}
	
	@Override
	public final IPacket decode(ByteArrayDataInput aData) {
		return decode2((mDecoderType&1)!=0?aData.readShort():aData.readInt(), (mDecoderType&2)!=0?UT.Code.unsignB(aData.readByte()):aData.readShort(), (mDecoderType&4)!=0?aData.readShort():aData.readInt(), aData);
	}
	
	public abstract byte getPacketIDOffset();
	public abstract ByteArrayDataOutput encode2(ByteArrayDataOutput aData);
	public abstract PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData);
}
