package gregtech.items.tools.electric;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_MiningDrill_HV extends GT_Tool_MiningDrill_LV {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 1600;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 12800;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 3200;
	}
	
	@Override
	public int getBaseQuality() {
		return 1;
	}
	
	@Override
	public float getBaseDamage() {
		return 3.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 9.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_HV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadDrill.mIconIndexItem);
	}
}