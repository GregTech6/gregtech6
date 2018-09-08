package gregapi.compat.industrialcraft;

import gregapi.compat.ICompat;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface ICompatIC2 extends ICompat {
	/** adds a Block to the valuable ores, metadata sensitive */
	public boolean valuable(Block aBlock, int aMeta, int aValue);
	/** adds a Block to the valuable ores */
	public boolean valuable(Block aBlock, int aValue);
	/** gives random Scrapbox Drop */
	public ItemStack scrapbox(ItemStack aBox);
	/** registers a Scrapbox Drop */
	public boolean scrapbox(float aChance, ItemStack aOutput);
	/** gives Recycler Output */
	public ItemStack recycler(ItemStack aInput, int aScrapChance);
	/** Adds Item to the Recycler Blacklist */
	public boolean blacklist(ItemStack aBlacklisted);
	public boolean isExplosionWhitelisted(Block aBlock);
	public void addToExplosionWhitelist(Block aBlock);
	public Object makeInput(ItemStack aStack);
	public Object makeInput(String aOreDict, long aAmount);
	public Object makeOutput(NBTTagCompound aNBT, ItemStack... aStacks);
	
	public boolean isReactorItem(ItemStack aStack);
}