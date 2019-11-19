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

package gregapi.code;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemContainer {
	public Item item();
	public Block block();
	public boolean equal(Object aStack);
	public boolean equal(Object aStack, boolean aWildcard, boolean aIgnoreNBT);
	public ItemStack get(long aAmount, Object... aReplacements);
	public ItemStack wild(long aAmount, Object... aReplacements);
	public ItemStack getUndamaged(long aAmount, Object... aReplacements);
	public ItemStack getAlmostBroken(long aAmount, Object... aReplacements);
	public ItemStack getWithMeta(long aAmount, long aMetaValue, Object... aReplacements);
	public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements);
	public ItemStack getWithNBT(long aAmount, NBTTagCompound aNBT, Object... aReplacements);
	public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements);
	public ItemStack getWithNameAndNBT(long aAmount, String aDisplayName, NBTTagCompound aNBT, Object... aReplacements);
	public ItemStack getWithCharge(long aAmount, long aEnergy, Object... aReplacements);
	public IItemContainer set(Item aItem);
	public IItemContainer set(ItemStack aStack);
	public IItemContainer registerOre(Object... aOreNames);
	public IItemContainer registerWildcardAsOre(Object... aOreNames);
	public boolean hasBeenSet();
	public boolean exists();
	
	@Deprecated public Item getItem();
	@Deprecated public Block getBlock();
	@Deprecated public ItemStack getWildcard(long aAmount, Object... aReplacements);
}
