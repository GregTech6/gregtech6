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

package gregapi.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockBase extends IBlock {
	// TODO Change all the aMeta s into short for 6.11.00
	public String name(int aMeta);
	public boolean useGravity(int aMeta);
	public boolean doesWalkSpeed(short aMeta);
	public boolean doesPistonPush(short aMeta);
	public boolean canCreatureSpawn(int aMeta);
	public boolean isSealable(int aMeta, byte aSide);
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H);
	public float getExplosionResistance(int aMeta);
	public int getItemStackLimit(ItemStack aStack);
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer);
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ);
	public boolean onItemUseFirst(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ);
}
