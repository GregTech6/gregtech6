package gregapi.network.packets;

import static gregapi.data.CS.*;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public class PacketPrefix implements IPacket {
	public final OreDictPrefix mPrefix;
	
	public PacketPrefix() {
		mPrefix = null;
	}
	
	public PacketPrefix(OreDictPrefix aPrefix) {
		mPrefix = aPrefix;
	}
	
	@Override
	public byte getPacketID() {
		return 126;
	}
	
	@Override
	public ByteArrayDataOutput encode() {
        ByteArrayDataOutput rOut = ByteStreams.newDataOutput();
        rOut.writeUTF(mPrefix.mNameInternal);
        rOut.writeByte(mPrefix.mConfigStackSize);
		return rOut;
	}
	
	@Override
	public IPacket decode(ByteArrayDataInput aData) {
		String tPrefix = aData.readUTF();
		OreDictPrefix aPrefix = OreDictPrefix.sPrefixes.get(tPrefix);
		if (aPrefix != null) {
			aPrefix.setStacksize(aData.readByte());
		} else {
			ERR.println("Unknown Prefix Data Received, this means the Mod Versions don't match: " + tPrefix);
		}
		return new PacketPrefix(aPrefix);
	}
	
	@Override
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
		if (mPrefix == null) return;
		mPrefix.applyStackSizes();
	}
}