package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Wrench_HV extends GT_Tool_Wrench_LV {
	
	public GT_Tool_Wrench_HV(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 800;
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
		return 2.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 4.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
	
	@Override
	public boolean canBlock() {
		return F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_HV : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadWrench.mIconIndexItem);
	}
}