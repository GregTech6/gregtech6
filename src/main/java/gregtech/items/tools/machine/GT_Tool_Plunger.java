package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import gregtech.items.behaviors.Behavior_Plunger_Fluid;
import gregtech.items.behaviors.Behavior_Plunger_Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_Plunger extends ToolStats {
	@Override
	public float getBaseDamage() {
		return 1.25F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.25F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_TRAMPOLINE;
	}
	
	@Override public boolean canCollect()													{return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return tTool != null && tTool.equalsIgnoreCase(TOOL_plunger);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.PLUNGER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_plunger, SFX.IC_TRAMPOLINE, 100, !canBlock()));
		aItem.addItemBehavior(aID, new Behavior_Plunger_Item(getToolDamagePerDropConversion()));
		aItem.addItemBehavior(aID, new Behavior_Plunger_Fluid(getToolDamagePerDropConversion()));
		try {
			Object tObject = UT.Reflection.callConstructor("gregtech.common.items.behaviors.Behaviour_Plunger_Essentia", 0, null, false, getToolDamagePerDropConversion());
			if (tObject instanceof IBehavior<?>) aItem.addItemBehavior(aID, (IBehavior<MultiItem>)tObject);
		} catch (Throwable e) {/**/}
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got stuck trying to escape through a Pipe while fighting [KILLER]";
	}
}