package gregapi.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import gregapi.block.IBlockErrorable;
import gregapi.network.INetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class PacketBlockError extends PacketCoordinates {
	private String mError;
	
	public PacketBlockError(int aDecoderType) {
		super(aDecoderType);
	}
	
	public PacketBlockError(int aX, int aY, int aZ, String aError) {
		super(aX, aY, aZ);
		mError = aError;
	}
	
	public PacketBlockError(ChunkCoordinates aCoords, String aError) {
		super(aCoords);
		mError = aError;
	}
	
	@Override
	public byte getPacketIDOffset() {
		return 104;
	}
	
	@Override
	public ByteArrayDataOutput encode2(ByteArrayDataOutput aData) {
		aData.writeUTF(mError);
		return aData;
	}
	
	@Override
	public PacketCoordinates decode2(int aX, int aY, int aZ, ByteArrayDataInput aData) {
		return new PacketBlockError(aX, aY, aZ, aData.readUTF());
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (aWorld instanceof World) {
			Block tBlock = aWorld.getBlock(mX, mY, mZ);
			if (tBlock instanceof IBlockErrorable) ((IBlockErrorable)tBlock).receiveBlockError(aWorld, mX, mY, mZ, mError);
		}
	}
}