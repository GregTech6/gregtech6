/**
 * Copyright (c) 2024 GregTech-6 Team
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
import gregapi.util.UT;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketSound extends PacketCoordinates {
	private String mSoundName;
	private float mSoundStrength, mSoundPitch;
	
	public PacketSound(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSound(String aSoundName, float aSoundStrength, float aSoundPitch, ChunkCoordinates aCoords) {
		super(aCoords);
		mSoundName = aSoundName;
		mSoundStrength = aSoundStrength;
		mSoundPitch = aSoundPitch;
	}
	
	public PacketSound(String aSoundName, float aSoundStrength, float aSoundPitch, int aX, int aY, int aZ) {
		super(aX, aY, aZ);
		mSoundName = aSoundName;
		mSoundStrength = aSoundStrength;
		mSoundPitch = aSoundPitch;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -128;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeUTF(mSoundName);
		aData.writeFloat(mSoundStrength);
		aData.writeFloat(mSoundPitch);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSound(aData.readUTF(), aData.readFloat(), aData.readFloat(), aX, aY, aZ);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		UT.Sounds.play(mSoundName, 2, mSoundStrength, mSoundPitch, mX, mY, mZ);
	}
}
