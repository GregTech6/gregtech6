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