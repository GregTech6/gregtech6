package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.recipes.Recipe;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_JackHammer_HV extends GT_Tool_MiningDrill_LV {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 200;
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
		return 3.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 12.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 2.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.IC_DRILL_HARD;
	}
	
	@Override public boolean canCollect()													{return F;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase("pickaxe")) || aBlock.getMaterial() == Material.rock || aBlock.getMaterial() == Material.glass || aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		int rConversions = 0;
		Recipe tRecipe;
		if (aBlock.hasTileEntity(aMetaData) || null == (tRecipe = RM.Hammer.findRecipe(null, null, true, Integer.MAX_VALUE, null, ZL_FS, ST.make(aBlock, 1, aMetaData)))) {
			List<ItemStack> tDrops = new ArrayListNoNulls();
			for (int i = 0; i < aDrops.size(); i++) {
				tRecipe = RM.Hammer.findRecipe(null, null, true, Integer.MAX_VALUE, null, ZL_FS, ST.amount(1, aDrops.get(i)));
				if (tRecipe != null) {
					byte tStackSize = (byte)aDrops.get(i).stackSize;
					rConversions += tStackSize;
					aDrops.remove(i--);
					if (tRecipe.mOutputs.length > 0) for (byte j = 0; j < tStackSize; j++) {
						ItemStack[] tHammeringOutput = tRecipe.getOutputs(RNGSUS);
						for (int k = 0; k < tHammeringOutput.length; k++) if (tHammeringOutput[k] != null) tDrops.add(tHammeringOutput[k]);
					}
				}
			}
			aDrops.addAll(tDrops);
		} else {
			aDrops.clear();
			ItemStack[] tHammeringOutput = tRecipe.getOutputs(RNGSUS);
			for (int k = 0; k < tHammeringOutput.length; k++) if (tHammeringOutput[k] != null) aDrops.add(tHammeringOutput[k]);
			rConversions++;
		}
		return rConversions;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.JACKHAMMER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been jackhammered into pieces by [KILLER]";
	}
}