package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_MonkeyWrench extends GT_Tool_Wrench {
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (super.isMinableBlock(aBlock, aMetaData)) return T;
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equals(TOOL_monkeywrench));
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.MONKEYWRENCH : Textures.ItemIcons.VOID;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_monkeywrench, SFX.IC_WRENCH, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] threw a Monkey Wrench into the Plans of [VICTIM]";
	}
}