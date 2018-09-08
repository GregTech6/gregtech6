package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.CS.PlankData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelWood extends MultiTileEntityPanel implements IMTE_AddToolTips {
	public short mIndex = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mIndex = (short)(aNBT.getShort(NBT_TEXTURE) % PlankData.PLANK_ICONS.length);
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		ItemStack tStack = PlankData.PLANKS[mIndex % PlankData.PLANKS.length];
		if (ST.valid(tStack)) aList.add(LH.Chat.CYAN + tStack.getDisplayName());
	}
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(PlankData.PLANK_ICONS[mIndex]);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.panel.wood";}
}