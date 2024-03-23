package gregapi.network.packets;

public class PacketMoldEvent extends PacketBlockEvent {
    public PacketMoldEvent(int aDecoderType) {
        super(aDecoderType);
    }

    public PacketMoldEvent(int aX, int aY, int aZ, int aBit) {
        super(aX, aY, aZ, (byte) 120, (byte) aBit);
    }

    public int getBit() {
        return mData;
    }

    @Override
    public byte getPacketIDOffset() {
        return (byte) 80;
    }
}