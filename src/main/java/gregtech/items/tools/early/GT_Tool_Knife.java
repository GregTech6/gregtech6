package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class GT_Tool_Knife extends GT_Tool_Sword {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public float getBaseDamage() {
		return 2.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.5F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override public boolean canCollect()													{return T;}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? Textures.ItemIcons.KNIFE : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "<[VICTIM]> [KILLER] what are you doing?, [KILLER]?!? STAHP!!!";
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_knife, SFX.MC_DIG_CLOTH, getToolDamagePerContainerCraft(), !canBlock()));
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		aPlayer.triggerAchievement(AchievementList.buildSword);
	}
}