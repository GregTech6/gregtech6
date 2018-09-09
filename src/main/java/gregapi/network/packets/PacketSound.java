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
		UT.Sounds.play(mSoundName, 10, mSoundStrength, mSoundPitch, mX, mY, mZ);
	}
}