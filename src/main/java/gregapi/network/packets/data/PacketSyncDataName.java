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
 * Transmits the extended Data of a Block.
 */
public class PacketSyncDataName extends PacketCoordinates {
	public String mData = "";
	
	public PacketSyncDataName(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketSyncDataName(int aX, int aY, int aZ, String aData) {
		super(aX, aY, aZ);
		mData = aData;
	}
	public PacketSyncDataName(ChunkCoordinates aCoords, String aData) {
		super(aCoords);
		mData = aData;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return 64;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeUTF(mData);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketSyncDataName(aX, aY, aZ, aData.readUTF());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld != null) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockSyncData) ((IBlockSyncData)tBlock).receiveDataName(aWorld, mX, mY, mZ, mData, aNetworkHandler);
		}
	}
}