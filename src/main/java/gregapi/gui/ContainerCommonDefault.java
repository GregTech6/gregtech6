package gregapi.gui;

import static gregapi.data.CS.*;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Gregorius Techneticies
 */
@invtweaks.api.container.ChestContainer
public class ContainerCommonDefault extends ContainerCommon {
	public ContainerCommonDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aOffset, int aSlotCount) {
		super(aInventoryPlayer, aTileEntity, aOffset, aSlotCount);
	}
	
	public ContainerCommonDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override public boolean useDefaultSlots() {return T;}
}