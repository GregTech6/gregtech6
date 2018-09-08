package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregtech.items.tools.early.GT_Tool_Saw;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_BuzzSaw_LV extends GT_Tool_Saw {
	@Override
	public int getToolDamagePerContainerCraft() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 300;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_CHAINSAW_01;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_CHAINSAW_02;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_CHAINSAW_01;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadBuzzSaw.mIconIndexItem) : Textures.ItemIcons.HANDLE_BUZZSAW;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.StainlessSteel).mRGBaSolid;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got buzzed by [KILLER]";
	}
}