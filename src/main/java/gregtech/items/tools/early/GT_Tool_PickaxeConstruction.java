package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.render.IIconContainer;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Tool_PickaxeConstruction extends GT_Tool_Pickaxe {
	@Override
	public float getSpeedMultiplier() {
		return super.getSpeedMultiplier() * 2;
	}
	
	@Override public boolean canCollect()													{return T;}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadConstructionPickaxe.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		if (WD.ore_stone(aBlock, aMetaData)) return aDefault / 4;
		return aDefault;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got demolished by [KILLER]";
	}
}