/**
 * Copyright (c) 2021 GregTech-6 Team
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

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface provides everything you need to make a Block receptacle to any Tools you want, with as many Parameters as possible.
 */
public interface IBlockToolable {
	/**
	 * Called when a Block is clicked using a Tool (a Tool which actually calls this Function). Every clicking Tool just has to call this Function.
	 * @param aTool The Name of the Tool (100% lowercase!). For example: 
	 * "rotator" if you have a rotation-setter-only Tool,
	 * "igniter" if the Tool is supposed to ignite things like TNT,
	 * "whacker" if you just whack at something with it,
	 * "wrench", "monkeywrench", "crowbar", "axe", "pickaxe", "sword", "shovel", "hoe", "grafter", "saw", "file", "hammer", "plow", "plunger", "scoop", "screwdriver", "sense", "scythe", "softhammer", "cutter", "plasmatorch", "solderingtool"
	 * More can be found inside the CS.java where all the known Tool Variables are (prefixed with "TOOL_").
	 * @param aRemainingDurability The Durability of the Tool which is remaining. This could for example influence the outcome when using an almost broken Tool (which would directly break when used) or an almost empty Electric Tool (if an electric Wrench doesn't work as strong as it should, because low Battery). The Value is in vanilla Tool Damage per broken Block * 10000.
	 * @param aPlayer The clicking Player (or Mob or any Entity, but usually a Player). This may be null in case there is no actual Player doing the click (saves on FakePlayers).
	 * @param aChatReturn a List you can add a returned Chat Message to. Use this instead of directly calling for the Chat Function. This List may be null!
	 * @param aPlayerInventory The Inventory of the clicking Player. This may be null in case there is no actual Player doing the click (saves also on FakePlayers). It can be the Inventory of something other than a Player.
	 * @param aSneaking true if the clicking Player is sneaking.
	 * @param aStack The ItemStack that is used. This may be null if there is no Item used to perform the task (if there is a WrenchInator-Block or something).
	 * @param aWorld The World the Block is in. Must be != null
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @param aX The Coordinate of the Block
	 * @param aY The Coordinate of the Block
	 * @param aZ The Coordinate of the Block
	 * @param aHitX The exact Coordinate of the clicked Block itself (from 0.0F to 1.0F)
	 * @param aHitY The exact Coordinate of the clicked Block itself (from 0.0F to 1.0F)
	 * @param aHitZ The exact Coordinate of the clicked Block itself (from 0.0F to 1.0F)
	 * @return the Damage to be dealt to the Tool. 10000 equals 1 vanilla Damage Point, that way, lower percentages are also possible.
	 */
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ);
	
	/** Utility for calling the onToolClick Function properly. Also has some default Hooks inside the ToolCompat Class. */
	public static class Util {
		@Deprecated
		public static long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			return onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, null, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		@Deprecated
		public static long onToolClick(Block aBlock, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			return onToolClick(aBlock, aTool, aRemainingDurability, aQuality, aPlayer, null, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		
		public static long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			return onToolClick(aWorld.getBlock(aX, aY, aZ), aTool.toLowerCase(), aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		
		public static long onToolClick(Block aBlock, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			if (aBlock instanceof IBlockToolable) return ((IBlockToolable)aBlock).onToolClick(aTool.toLowerCase(), aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
			return ToolCompat.onToolClick(aBlock, aTool.toLowerCase(), aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		
		public static long onToolClickWithoutCompat(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			return onToolClickWithoutCompat(aWorld.getBlock(aX, aY, aZ), aTool.toLowerCase(), aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		
		public static long onToolClickWithoutCompat(Block aBlock, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
			return aBlock instanceof IBlockToolable ? ((IBlockToolable)aBlock).onToolClick(aTool.toLowerCase(), aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ) : 0;
		}
	}
}
