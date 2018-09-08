package gregapi.network.packets.data;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.block.IBlockSyncData;
import gregapi.network.INetworkHandler;
import gregapi.network.packets.PacketCoordinates;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataByteArray extends PacketCoordinates {
	public byte[] mData;
	
	public PacketSyncDataByteArray(int aDecoderType) {
		super(aDecoderType);
	}
	
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArray(int aX, int aY, int aZ, byte... aData) {
		super(aX, aY, aZ);
		mData = aData;
	}
	/** aData is limited to length = 256 */
	public PacketSyncDataByteArray(ChunkCoordinates aCoords, byte... aData) {
		super(aCoords);
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return -56;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeByte(mData.length);
		aData.write(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		byte[] tData = new byte[UT.Code.unsignB(aData.readByte())];
		aData.readFully(tData);
		return new PacketSyncDataByteArray(aX, aY, aZ, tData);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncData) ((IBlockSyncData)tBlock).receiveDataByteArray(aWorld, mX, mY, mZ, mData, aNetworkHandler);
		}
	}
}