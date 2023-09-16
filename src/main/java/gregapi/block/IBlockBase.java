/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.block;

import gregapi.item.IItemGT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockBase extends IBlock, IItemGT {
	/** @returns A number between 1 and 16, the Maximum Metadata for this Block in Item form for things like Creative Tabs. Pillar Blocks like Logs and Beams should return 4. */
	public byte maxMeta();
	public String name(byte aMeta);
	public boolean useGravity(byte aMeta);
	public boolean doesWalkSpeed(byte aMeta);
	public boolean doesPistonPush(byte aMeta);
	public boolean canSilkHarvest(byte aMeta);
	public boolean canCreatureSpawn(byte aMeta);
	public boolean isSealable(byte aMeta, byte aSide);
	public boolean isFlammable(byte aMeta);
	public boolean isFireSource(byte aMeta);
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H);
	public float getExplosionResistance(byte aMeta);
	public int getFlammability(byte aMeta);
	public int getFireSpreadSpeed(byte aMeta);
	public int getItemStackLimit(ItemStack aStack);
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer);
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ);
	public boolean onItemUseFirst(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ);
}
