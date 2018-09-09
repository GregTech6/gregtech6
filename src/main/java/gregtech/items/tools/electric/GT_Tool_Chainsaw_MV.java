package gregtech.items.tools.electric;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Chainsaw_MV extends GT_Tool_Chainsaw_LV {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 10;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 800;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 8000;
	}
	
	@Override
	public int getBaseQuality() {
		return 1;
	}
	
	@Override
	public float getBaseDamage() {
		return 3.5F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 3.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 2.0F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ?  Textures.ItemIcons.POWER_UNIT_MV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadChainsaw.mIconIndexItem);
	}
}