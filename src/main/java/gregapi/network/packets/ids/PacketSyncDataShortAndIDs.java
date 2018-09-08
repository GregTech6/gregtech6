package gregapi.network.packets.ids;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.block.IBlockSyncData.IBlockSyncDataAndIDs;
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
public class PacketSyncDataShortAndIDs extends PacketCoordinates {
	public short mData = 0;
	public short mID1 = 0, mID2 = 0;
	
	public PacketSyncDataShortAndIDs(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSyncDataShortAndIDs(int aX, int aY, int aZ, short aID1, short aID2, short aData) {
		super(aX, aY, aZ);
		mData = aData;
		mID1 = aID1;
		mID2 = aID2;
	}
	public PacketSyncDataShortAndIDs(ChunkCoordinates aCoords, short aID1, short aID2, short aData) {
		super(aCoords);
		mData = aData;
		mID1 = aID1;
		mID2 = aID2;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -72;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeShort(mID1);
		aData.writeShort(mID2);
		aData.writeShort(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSyncDataShortAndIDs(aX, aY, aZ, aData.readShort(), aData.readShort(), aData.readShort());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncDataAndIDs) ((IBlockSyncDataAndIDs)tBlock).receiveDataShort(aWorld, mX, mY, mZ, mData, aNetworkHandler, mID1, mID2);
		}
	}
}