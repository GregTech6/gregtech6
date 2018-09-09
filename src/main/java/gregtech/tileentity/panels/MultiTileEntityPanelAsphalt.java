package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelAsphalt extends MultiTileEntityPanelColored implements IMTE_AddToolTips {
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(Textures.BlockIcons.ASPHALT, DYES[mColor]);
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_WALKSPEED));
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.panel.asphalt";}
}