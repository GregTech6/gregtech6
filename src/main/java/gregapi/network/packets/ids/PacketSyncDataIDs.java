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
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataIDs extends PacketCoordinates {
	public short mID1 = 0, mID2 = 0;
	
	public PacketSyncDataIDs(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSyncDataIDs(int aX, int aY, int aZ, short aID1, short aID2) {
		super(aX, aY, aZ);
		mID1 = aID1;
		mID2 = aID2;
	}
	public PacketSyncDataIDs(ChunkCoordinates aCoords, short aID1, short aID2) {
		super(aCoords);
		mID1 = aID1;
		mID2 = aID2;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -40;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeShort(mID1);
		aData.writeShort(mID2);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSyncDataIDs(aX, aY, aZ, aData.readShort(), aData.readShort());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncDataAndIDs) ((IBlockSyncDataAndIDs)tBlock).receiveData(aWorld, mX, mY, mZ, aNetworkHandler, mID1, mID2);
		}
	}
}