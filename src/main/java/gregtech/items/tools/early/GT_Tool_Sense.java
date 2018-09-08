package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Sense extends ToolStats {
	private ThreadLocal sIsHarvestingRightNow = new ThreadLocal();
	
	@Override
	public float getBaseDamage() {
		return 3.00F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null &&( tTool.equalsIgnoreCase(TOOL_sense) || tTool.equalsIgnoreCase(TOOL_scythe))) || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		int rConversions = 0;
		if (sIsHarvestingRightNow.get() == null && aPlayer instanceof EntityPlayerMP) {
			sIsHarvestingRightNow.set(this);
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) if (i != 0 || j != 0 || k != 0) if (aStack.getItem().getDigSpeed(aStack, aPlayer.worldObj.getBlock(aX+i, aY+j, aZ+k), aPlayer.worldObj.getBlockMetadata(aX+i, aY+j, aZ+k)) > 0) if (((EntityPlayerMP)aPlayer).theItemInWorldManager.tryHarvestBlock(aX+i, aY+j, aZ+k)) rConversions++;
			sIsHarvestingRightNow.set(null);
		}
		harvestGrass(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent);
		return rConversions;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadSense.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_sense, null, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] has taken the Soul of [VICTIM]";
	}
}