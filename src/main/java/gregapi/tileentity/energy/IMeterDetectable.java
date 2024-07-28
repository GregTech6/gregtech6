package gregapi.tileentity.energy;

import gregapi.code.TagData;

import java.util.ArrayList;
import java.util.List;

public interface IMeterDetectable {

    static void sendReceiveEmitMessage(ArrayList<MeterData> receivedEnergyList, TagData mEnergyTypeEmitting, long mSizeEmitting, long mAmperageEmitting, List<String> aChatReturn) {
        if (!receivedEnergyList.isEmpty()) {
            aChatReturn.add("Receiving Energies: ");
            receivedEnergyList.forEach(e->aChatReturn.add(e.mSize + " "+e.mEnergyType.getLocalisedChatNameShort()+"\u00a7r/A * "+e.mAmperage +" A/t"));
        }
        else aChatReturn.add("Not Receiving Power");

        if (mSizeEmitting!=0&&mAmperageEmitting !=0) aChatReturn.add("Emitting: "+mSizeEmitting+" "+mEnergyTypeEmitting.getLocalisedChatNameShort()+"\u00a7r/A * "+mAmperageEmitting + " A/t");
        else aChatReturn.add("Not Emitting Power");
    }
    static void sendTransferMessage(ArrayList<MeterData> transferedEnergyList, List<String> aChatReturn) {
        if (!transferedEnergyList.isEmpty()) {
            aChatReturn.add("Transferring Energies: ");
            transferedEnergyList.forEach(e->aChatReturn.add(e.mSize + " "+e.mEnergyType.getLocalisedChatNameShort()+"\u00a7r/A * "+e.mAmperage +" A/t"));
        }
        else aChatReturn.add("Not Transfer Power");
    }
    public class MeterData {
        public final long mSize;
        public final long mAmperage;
        public final TagData mEnergyType;

        public MeterData(TagData mEnergyType, long mSize, long mAmperage) {
            this.mSize = mSize;
            this.mAmperage = mAmperage;
            this.mEnergyType = mEnergyType;
        }
    }
}
