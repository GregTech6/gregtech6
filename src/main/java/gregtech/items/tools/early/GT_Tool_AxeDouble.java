package gregtech.items.tools.early;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.render.IIconContainer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class GT_Tool_AxeDouble extends GT_Tool_Axe {
	@Override
	public float getBaseDamage() {
		return 6.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return super.getMaxDurabilityMultiplier() * 1.5F;
	}
	
	@Override
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		return aOriginalHurtResistance*2;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadAxeDouble.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got beheaded by [KILLER]";
	}
}