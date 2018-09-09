package gregapi.gui;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Gregorius Techneticies
 */
@invtweaks.api.container.ChestContainer(isLargeChest = true)
public class ContainerCommonChest extends ContainerCommon {
	public ContainerCommonChest(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	public ContainerCommonChest(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aOffset, int aSlotCount) {
		super(aInventoryPlayer, aTileEntity, aOffset, aSlotCount);
	}
	
	@Override
	public int addSlots(InventoryPlayer aInventoryPlayer) {
		int tSize = mTileEntity.getSizeInventoryGUI(), tRows = tSize/9 + (tSize%9==0?0:1);
		for (int y = 0, i = 0; y < tRows; y++) for (int x = 0; x < 9 && i < tSize; x++) addSlotToContainer(new Slot_Normal(mTileEntity, mOffset+i++, 8 + x * 18, 18 + y * 18));
		return 103+(tRows-4)*18;
	}
	
	@Override public int getSlotCount() {return mSlotCount;}
	@Override public int getShiftClickSlotCount() {return mSlotCount;}
}