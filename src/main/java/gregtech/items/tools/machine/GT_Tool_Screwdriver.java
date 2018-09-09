package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Tool_Screwdriver extends ToolStats {
	public static final List<String> mEffectiveList = Arrays.asList(
		EntityCaveSpider.class.getName(),
		EntitySpider.class.getName(),
		"EntityTFHedgeSpider",
		"EntityTFKingSpider",
		"EntityTFSwarmSpider",
		"EntityTFTowerBroodling"
	);
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		String tName = aEntity.getClass().getName();
		tName = tName.substring(tName.lastIndexOf(".")+1);
		return mEffectiveList.contains(tName)?aOriginalDamage*2:aOriginalDamage;
	}
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public int getBaseQuality() {
		return 0;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.5F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_WRENCH;
	}
	
	@Override
	public boolean isMiningTool() {
		return F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return tTool != null && tTool.equalsIgnoreCase(TOOL_screwdriver) || aBlock.getMaterial() == Material.circuits;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadScrewdriver.mIconIndexItem) : Textures.ItemIcons.HANDLE_SCREWDRIVER;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_screwdriver, SFX.IC_WRENCH, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has screwed with [KILLER] for the last time!";
	}
}