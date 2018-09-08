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
 * Transmits the extended MetaData of a Block.
 */
public class PacketSyncDataShort extends PacketCoordinates {
	public short mData = 0;
	
	public PacketSyncDataShort(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSyncDataShort(int aX, int aY, int aZ, short aData) {
		super(aX, aY, aZ);
		mData = aData;
	}
	public PacketSyncDataShort(ChunkCoordinates aCoords, short aData) {
		super(aCoords);
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -120;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeShort(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSyncDataShort(aX, aY, aZ, aData.readShort());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncData) ((IBlockSyncData)tBlock).receiveDataShort(aWorld, mX, mY, mZ, mData, aNetworkHandler);
		}
	}
}