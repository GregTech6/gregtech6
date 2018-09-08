package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS.ModIDs;
import gregapi.data.CS.ToolsGT;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "ic2.api.tile.IWrenchable", modid = ModIDs.IC2)
})
public abstract class TileEntityBase08Directional extends TileEntityBase07Paintable implements ic2.api.tile.IWrenchable {
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData()) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setVisualData(aData[3]);
		setDirectionData(aData[4]);
		return T;
	}
	
	// Stuff to Override
	public byte getDirectionData() {return 0;}
	public void setDirectionData(byte aData) {/**/}
	public String getFacingTool() {return "";}
	
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return super.isUsingWrenchingOverlay(aStack, aSide) || (getFacingTool() != null && ToolsGT.contains(getFacingTool(), aStack));}
	@Override public boolean wrenchCanRemove(EntityPlayer aPlayer) {return F;}
	@Override public float getWrenchDropRate() {return 1.0F;}
	@Override public ItemStack getWrenchDrop(EntityPlayer aPlayer) {return null;}
}