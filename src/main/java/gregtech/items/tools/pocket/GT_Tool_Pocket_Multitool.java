package gregtech.items.tools.pocket;

import static gregapi.data.CS.*;

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_Pocket_Multitool extends ToolStats {
	public final int mSwitchIndex;
	
	public GT_Tool_Pocket_Multitool(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POCKET_MULTITOOL_CLOSED : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
	}
	
	@Override
	public float getBaseDamage() {
		return 0;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
}