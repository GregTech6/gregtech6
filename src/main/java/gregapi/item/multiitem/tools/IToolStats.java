/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.item.multiitem.tools;

import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictMaterial;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

/**
 * @author Gregorius Techneticies
 * 
 * The Stats for GT Tools. Not including any Material Modifiers.
 * 
 * And this is supposed to not have any ItemStack Parameters as these are generic Stats.
 */
public interface IToolStats {
	/**
	 * Called when aPlayer crafts this Tool
	 */
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer);
	
	/**
	 * Called when this gets added to a Tool Item
	 */
	public void onStatsAddedToTool(MultiItemTool aItem, int aID);
	
	/**
	 * @return Damage the Tool receives when breaking a Block. 100 is one Damage Point (or 100 EU).
	 */
	public int getToolDamagePerBlockBreak();
	
	/**
	 * @return Damage the Tool receives when converting the drops of a Block. 100 is one Damage Point (or 100 EU).
	 */
	public int getToolDamagePerDropConversion();
	
	/**
	 * @return Damage the Tool receives when being used as Container Item. 100 is one use, however it is usually 8 times more than normal.
	 */
	public int getToolDamagePerContainerCraft();
	
	/**
	 * @return Damage the Tool receives when being used as Weapon, 200 is the normal Value, 100 for actual Weapons.
	 */
	public int getToolDamagePerEntityAttack();
	
	/**
	 * @return Basic Quality of the Tool, 0 is normal. If increased, it will increase the general quality of all Tools of this Type. Decreasing is also possible.
	 */
	public int getBaseQuality();
	
	/**
	 * @return The Damage Bonus for this Type of Tool against Mobs. 1.0F is normal punch.
	 */
	public float getBaseDamage();
	
	/**
	 * @return This gets the Hurt Resistance time for Entities getting hit. (always does 1 as minimum)
	 */
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity);
	
	/**
	 * @return The Exhaustion when using this as Weapon. 0.3F is Default.
	 */
	public float getExhaustionPerAttack(Entity aEntity);
	
	/**
	 * @return This is a multiplier for the Tool Speed. 1.0F = no special Speed.
	 */
	public float getSpeedMultiplier();
	
	/**
	 * @return This is a multiplier for the Tool Durability. 1.0F = no special Durability.
	 */
	public float getMaxDurabilityMultiplier();
	
	public DamageSource getDamageSource(EntityLivingBase aPlayer, Entity aEntity);
	
	public String getMiningSound();
	public String getCraftingSound();
	public String getEntityHitSound();
	public String getBreakingSound();
	
	public Enchantment[] getEnchantments(ItemStack aStack, OreDictMaterial aMaterial);
	public int[] getEnchantmentLevels(ItemStack aStack, OreDictMaterial aMaterial);
	
	/**
	 * @return If this Tool can instant collect Items.
	 */
	public boolean canCollect();
	
	/**
	 * @return If this Tool can be used for blocking Damage like a Sword.
	 */
	public boolean canBlock();
	
	/**
	 * @return If this Tool can be used as a RC Crowbar.
	 */
	public boolean isCrowbar();
	
	/**
	 * @return If this Tool can be used as a BC Wrench.
	 */
	public boolean isWrench();
	
	/**
	 * @return If this Tool can be used as a Forestry Grafter.
	 */
	public boolean isGrafter();
	
	/**
	 * @return If this Tool can be used as Weapon i.e. if that is the main purpose. (for enchants)
	 */
	public boolean isWeapon();
	
	/**
	 * @return If this Tool is a Ranged Weapon. Return false at isWeapon unless you have a Blade attached to your Bow/Gun or something
	 */
	public boolean isRangedWeapon();
	
	/**
	 * @return If this Tool can be used as Mining Tool i.e. if that is the main purpose. (for enchants)
	 */
	public boolean isMiningTool();
	
	/**
	 * @return If this Tools Damage does setDamageBypassesArmor() for its DamageSource.
	 */
	public boolean canPenetrate();
	
	/**
	 * @return If this Tool can make Mobs and Players drop Heads.
	 */
	public boolean canBehead();
	
	/**
	 * aBlock.getHarvestTool(aMetaData) can return the following Values for example.
	 * "chisel", "axe", "pickaxe", "sword", "shovel", "hoe", "grafter", "saw", "wrench", "monkeywrench", "crowbar", "file", "hammer", "plow", "plunger", "scoop", "screwdriver", "sense", "scythe", "softhammer", "cutter", "plasmatorch", "solderingtool"
	 * @return If this is a minable Block. Tool Quality checks (like Diamond Tier or something) are separate from this check.
	 */
	public boolean isMinableBlock(Block aBlock, byte aMetaData);
	
	/**
	 * aBlock.getHarvestTool(aMetaData) can return the following Values for example.
	 * "chisel", "axe", "pickaxe", "sword", "shovel", "hoe", "grafter", "saw", "wrench", "monkeywrench", "crowbar", "file", "hammer", "plow", "plunger", "scoop", "screwdriver", "sense", "scythe", "softhammer", "cutter", "plasmatorch", "solderingtool"
	 * @return Mining Speed for this Block from this Tool, 1.0 = Default Speed, 0.0 = cannot be mined. Tool Quality checks (like Diamond Tier or something) are separate from this check.
	 */
	public float getMiningSpeed(Block aBlock, byte aMetaData);
	
	/**
	 * @return Mining Speed for this Block from this Tool. Return aDefault if you don't want to override this.
	 */
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ);
	
	/**
	 * This lets you modify the Drop List, when this type of Tool has been used.
	 * @return the Amount of modified Items.
	 */
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableConversions, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent);
	
	/**
	 * @return Returns a broken Version of the Item.
	 */
	public ItemStack getBrokenItem(ItemStack aStack);
	
	/**
	 * @return the Damage actually done to the Mob.
	 */
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer);
	
	/**
	 * @return the Damage actually done to the Mob.
	 */
	public float getMagicDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer);
	
	/**
	 * Gets called after successfully dealing Damage to a Mob.
	 */
	public void afterDealingDamage(float aNormalDamage, float aMagicDamage, int aFireAspect, boolean aCriticalHit, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer);
	
	/**
	 * Gets called right before the Tool gets removed from the Inventory.
	 */
	public void afterBreaking(ItemStack aStack, EntityPlayer aPlayer);
	
	public int getRenderPasses();
	public IIcon getIcon(ItemStack aStack, int aRenderPass);
	public short[] getRGBa(ItemStack aStack, int aRenderPass);
}
