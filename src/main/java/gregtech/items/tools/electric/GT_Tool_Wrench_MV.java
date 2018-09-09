package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Wrench_MV extends GT_Tool_Wrench_LV {
	
	public GT_Tool_Wrench_MV(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 3200;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 800;
	}
	
	@Override
	public int getBaseQuality() {
		return 1;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.5F;
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
	public boolean canBlock() {
		return F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_MV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadWrench.mIconIndexItem);
	}
}