package gregapi.gui;

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Gregorius Techneticies
 */
@SideOnly(Side.CLIENT)
public class ContainerClientDefault extends ContainerClient {
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, String aBackgroundPath) {
		super(new ContainerCommonDefault(aInventoryPlayer, aTileEntity), aBackgroundPath);
	}
	
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
		this(aInventoryPlayer, aTileEntity, RES_PATH_GUI + "chests/" + aTileEntity.getSizeInventoryGUI() + ".png");
	}
	
	public ContainerClientDefault(ContainerCommonDefault aContainer) {
		super(aContainer, RES_PATH_GUI + "chests/" + aContainer.mSlotCount + ".png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		if (mContainer.mSlotCount != 16 && mContainer.mSlotCount <= 27) fontRendererObj.drawString(mContainer.mTileEntity.getInventoryNameGUI(), 8,  4, 4210752);
	}
}