/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

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
