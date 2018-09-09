package gregapi.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IPacket {
	/**
	 * The Unique ID of this Packet inside the targeted INetworkHandler
	 * 
	 * Use constant IDs instead of Dynamic ones, since that is much more fail safe.
	 * @return a Packet ID for this Class
	 */
	public byte getPacketID();
	
	/**
	 * @return encoded byte Stream
	 */
	public ByteArrayDataOutput encode();
	
	/**
	 * It is VERY important that decoding and processing are in two different Steps! Otherwise there will be a Memory-Leak caused by the Network Handler!
	 * @return encoded byte Stream
	 */
	public IPacket decode(ByteArrayDataInput aData);
	
	/**
	 * It is VERY important that decoding and processing are in two different Steps! Otherwise there will be a Memory-Leak caused by the Network Handler!
	 * process the Packet after it has been received, inside this Function.
	 * @param aWorld The World this Packet was sent for on the Client, or null on the Server Side.
	 */
	public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler);
	
	/**
	 * This simple Class is an example on how to implement the Functions of this Interface properly.
	 * 
	 * A better example might be provided by {@link gregapi.network.packets.PacketCoordinates} and {@link gregapi.network.packets.data.PacketSyncDataShort}, because those ones are actually useful unlike this example.
	 */
	public static final class BasicPacket implements IPacket {
		public String mTransmittedData = "TEST";
		
		@Override
		public byte getPacketID() {
			return 0; // This should be a constant. Do not make it a Variable, unless you REALLY know what you are doing.
		}
		
		@Override
		public ByteArrayDataOutput encode() {
	        ByteArrayDataOutput tOut = ByteStreams.newDataOutput(4);
	        tOut.writeUTF(mTransmittedData);
			return tOut;
		}
		
		@Override
		public IPacket decode(ByteArrayDataInput aData) {
			BasicPacket rPacket = new BasicPacket();
			rPacket.mTransmittedData = aData.readUTF();
			return rPacket;
		}
		
		@Override
		public void process(IBlockAccess aWorld, INetworkHandler aNetworkHandler) {
			// And here you can do something with mTransmittedData, instead of printing out Data for no apparent Reason.
			System.out.println("We have received: " + mTransmittedData);
		}
	}
}