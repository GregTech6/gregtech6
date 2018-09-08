package gregapi.network.packets;

import static gregapi.data.CS.*;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketConfig implements IPacket {
	@Override
	public byte getPacketID() {
		return 127;
	}
	
	@Override
	public ByteArrayDataOutput encode() {
        ByteArrayDataOutput rOut = ByteStreams.newDataOutput();
        rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_SAND);
        rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_ROCK);
        rOut.writeDouble(CONFIG_HARDNESS_MULTIPLIER_ORES);
		return rOut;
	}
	
	@Override
	public IPacket decode(ByteArrayDataInput aData) {
		HARDNESS_MULTIPLIER_SAND = aData.readDouble();
		HARDNESS_MULTIPLIER_ROCK = aData.readDouble();
		HARDNESS_MULTIPLIER_ORES = aData.readDouble();
		return this;
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		// Since this actually doesn't process anything I can set the static variables directly in decode.
	}
}