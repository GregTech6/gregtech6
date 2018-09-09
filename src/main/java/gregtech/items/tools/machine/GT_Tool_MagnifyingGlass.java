package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_MagnifyingGlass extends ToolStats {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return false;
	}
	
	@Override
	public String getMiningSound() {
		return RNGSUS.nextInt(3) == 0 ? SFX.MC_HMM : SFX.MC_AHA;
	}
	
	@Override
	public String getCraftingSound() {
		return RNGSUS.nextInt(3) == 0 ? SFX.MC_HMM : SFX.MC_AHA;
	}
	
	@Override
	public String getEntityHitSound() {
		return RNGSUS.nextInt(3) == 0 ? SFX.MC_HMM : SFX.MC_AHA;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Glass).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_magnifyingglass, SFX.MC_AHA, 100, !canBlock()));
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.MAGNIFYING_GLASS : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got investigated very closely by [KILLER]";
	}
}