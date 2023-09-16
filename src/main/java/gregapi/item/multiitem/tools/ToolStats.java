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

package gregapi.item.multiitem.tools;

import gregapi.damage.DamageSources;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.UT.Enchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class ToolStats implements IToolStats {
	public static final Enchantment[] FORTUNE_ENCHANTMENT = new Enchantment[] {Enchantment.fortune};
	public static final Enchantment[] LOOTING_ENCHANTMENT = new Enchantment[] {Enchantment.looting};
	
	@Override public int getToolDamagePerBlockBreak()                                       {return 100;}
	@Override public int getToolDamagePerDropConversion()                                   {return 100;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 100;}
	@Override public int getToolDamagePerEntityAttack()                                     {return 100;}
	@Override public int getBaseQuality()                                                   {return   0;}
	@Override public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {return aOriginalHurtResistance;}
	@Override public float getBaseDamage()                                                  {return 1.0F;}
	@Override public float getSpeedMultiplier()                                             {return 1.0F;}
	@Override public float getMaxDurabilityMultiplier()                                     {return 1.0F;}
	@Override public float getExhaustionPerAttack(Entity aEntity)                           {return 0.3F;}
	@Override public String getMiningSound()                                                {return null;}
	@Override public String getCraftingSound()                                              {return null;}
	@Override public String getEntityHitSound()                                             {return null;}
	@Override public String getBreakingSound()                                              {return SFX.MC_BREAK;}
	@Override public boolean canCollect()                                                   {return F;}
	@Override public boolean canBlock()                                                     {return F;}
	@Override public boolean canPenetrate()                                                 {return F;}
	@Override public boolean isWrench()                                                     {return F;}
	@Override public boolean isCrowbar()                                                    {return F;}
	@Override public boolean isGrafter()                                                    {return F;}
	@Override public boolean isWeapon()                                                     {return F;}
	@Override public boolean isRangedWeapon()                                               {return F;}
	@Override public boolean isMiningTool()                                                 {return T;}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData) {
		return isMinableBlock(aBlock, aMetaData) ? 1 : 0;
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		return aDefault;
	}
	
	@Override
	public DamageSource getDamageSource(EntityLivingBase aPlayer, Entity aEntity) {
		return DamageSources.getCombatDamage(aPlayer instanceof EntityPlayer ? "player" : "mob", aPlayer, aEntity instanceof EntityLivingBase ? getDeathMessage(aPlayer, (EntityLivingBase)aEntity) : null);
	}
	
	public IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {return DamageSources.getDeathMessage(aPlayer, aEntity, getDeathMessage());}
	public IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity, String aNamePlayer, String aNameEntity) {return DamageSources.getDeathMessage(aPlayer, aEntity, aNamePlayer, aNameEntity, getDeathMessage());}
	public String getDeathMessage() {
		return "";
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		return 0;
	}
	
	public boolean harvestGrass(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock == Blocks.tallgrass) {
			switch(aMetaData) {
			case 1: case 2: aDrops.add(IL.Grass.get(1+RNGSUS.nextInt(1+aFortune))); return T;
			}
			return F;
		}
		if (aBlock == Blocks.double_plant) {
			switch(aMetaData & 7) {
			case 2: case 3: aDrops.add(IL.Grass.get(2+RNGSUS.nextInt(1+aFortune)+RNGSUS.nextInt(1+aFortune))); return T;
			}
			return F;
		}
		if (IL.TF_Tall_Grass.equal(aBlock)) {
			switch(aMetaData) {
			case 10: aDrops.add(IL.Grass.get(1+RNGSUS.nextInt(1+aFortune))); return T;
			}
			return F;
		}
		if (IL.AETHER_Tall_Grass.equal(aBlock)) {
			aDrops.add(IL.Grass.get(1+RNGSUS.nextInt(1+aFortune)));
			return T;
		}
		if (MD.BoP.mLoaded) {
			if (aBlock == ST.block(MD.BoP, "foliage")) {
				switch(aMetaData) {
				case  1: if (RNGSUS.nextInt(4) <= aFortune) aDrops.add(IL.Grass.get(1)); return T;
				case  2: if (RNGSUS.nextInt(2) <= aFortune) aDrops.add(IL.Grass.get(1)); return T;
				case 10: aDrops.add(IL.Grass.get(1+RNGSUS.nextInt(1+aFortune))); return T;
				case 11: aDrops.add(IL.Grass.get(1+RNGSUS.nextInt(1+aFortune))); return T;
				}
				return F;
			}
		}
		return F;
	}
	
	public boolean harvestStick(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock == Blocks.tallgrass) {
			switch(aMetaData) {
			case 0: aDrops.add(OP.stick.mat(MT.WOODS.Dead, 1+RNGSUS.nextInt(2+aFortune))); return T;
			}
			return F;
		}
		if (IL.TF_Tall_Grass.equal(aBlock)) {
			switch(aMetaData) {
			case 11: aDrops.add(IL.Stick.get(1+RNGSUS.nextInt(2+aFortune))); return T;
			}
			return F;
		}
		if (aBlock == Blocks.deadbush) {
			aDrops.add(OP.stick.mat(MT.WOODS.Dead, 1+RNGSUS.nextInt(2+aFortune)));
			return T;
		}
		if (MD.BoP.mLoaded) {
			if (aBlock == ST.block(MD.BoP, "foliage")) {
				switch(aMetaData) {
				case  4: aDrops.add(IL.Stick.get(1+RNGSUS.nextInt(2+aFortune))); return T;
				case  8: aDrops.add(IL.Stick.get(1+RNGSUS.nextInt(2+aFortune))); return T;
				case  9: aDrops.add(IL.Stick.get(1+RNGSUS.nextInt(2+aFortune))); return T;
				}
				return F;
			}
		}
		return F;
	}
	
	private long mMaterialAmount = 0;
	
	public ToolStats setMaterialAmount(long aMaterialAmount) {
		mMaterialAmount = aMaterialAmount;
		return this;
	}
	
	@Override
	public ItemStack getBrokenItem(ItemStack aStack) {
		return mMaterialAmount < U4 ? null : OP.scrapGt.mat(MultiItemTool.getPrimaryMaterial(aStack), 1+RNGSUS.nextInt(1+(int)(4*mMaterialAmount/U)));
	}
	
	@Override
	public Enchantment[] getEnchantments(ItemStack aStack, OreDictMaterial aMaterial) {
		return ZL_ENCHANTMENT;
	}
	
	@Override
	public int[] getEnchantmentLevels(ItemStack aStack, OreDictMaterial aMaterial) {
		return getEnchantmentLevels(aStack);
	}
	@Deprecated
	public int[] getEnchantmentLevels(ItemStack aStack) {
		return ZL_INTEGER;
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		aPlayer.triggerAchievement(AchievementList.openInventory);
		aPlayer.triggerAchievement(AchievementList.mineWood);
		aPlayer.triggerAchievement(AchievementList.buildWorkBench);
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		//
	}
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		return aOriginalDamage;
	}
	
	@Override
	public float getMagicDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		return aOriginalDamage;
	}
	
	@Override
	public void afterDealingDamage(float aNormalDamage, float aMagicDamage, int aFireAspect, boolean aCriticalHit, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		if (aEntity instanceof EntityLivingBase && aFireAspect > 0) aEntity.setFire(aFireAspect * 4);
		int tKnockback = (aPlayer.isSprinting()?1:0) + (aEntity instanceof EntityLivingBase?EnchantmentHelper.getKnockbackModifier(aPlayer, (EntityLivingBase)aEntity):0);
		if (tKnockback > 0) {
			aEntity.addVelocity(-MathHelper.sin((float)(aPlayer.rotationYaw * Math.PI / 180)) * tKnockback * 0.5, 0.1, MathHelper.cos((float)(aPlayer.rotationYaw * Math.PI / 180)) * tKnockback * 0.5);
			aPlayer.motionX *= 0.6;
			aPlayer.motionZ *= 0.6;
			aPlayer.setSprinting(F);
		}
		if (aCriticalHit) aPlayer.onCriticalHit(aEntity);
		if (aMagicDamage > 0) aPlayer.onEnchantmentCritical(aEntity);
		if (aNormalDamage+aMagicDamage >= 18) aPlayer.triggerAchievement(AchievementList.overkill);
		aPlayer.setLastAttacker(aEntity);
		if (aEntity instanceof EntityLivingBase) Enchantments.applyBullshitA((EntityLivingBase)aEntity, aPlayer, aStack);
		Enchantments.applyBullshitB(aPlayer, aEntity, aStack);
		if (aEntity instanceof EntityLivingBase) aPlayer.addStat(StatList.damageDealtStat, Math.round((aNormalDamage+aMagicDamage) * 10));
		aEntity.hurtResistantTime = Math.max(1, getHurtResistanceTime(aEntity.hurtResistantTime, aEntity));
		aPlayer.addExhaustion(getExhaustionPerAttack(aEntity));
	}
	
	@Override
	public void afterBreaking(ItemStack aStack, EntityPlayer aPlayer) {
		// If you work so hard that your Tool breaks, you should probably take a break yourself. :P
		UT.Entities.applyPotion(aPlayer, Potion.weakness   ,  300, 2, F);
		UT.Entities.applyPotion(aPlayer, Potion.digSlowdown, 1200, 2, F);
	}
	
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return Textures.ItemIcons.VOID;
	}
	
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return null;
	}
	
	@Override
	public int getRenderPasses() {
		return 4;
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		switch(aRenderPass) {
		case 0: return getIcon(F, aStack).getIcon(0);
		case 1: return getIcon(F, aStack).getIcon(1);
		case 2: return getIcon(T, aStack).getIcon(0);
		case 3: return getIcon(T, aStack).getIcon(1);
		}
		return null;
	}
	
	@Override
	public short[] getRGBa(ItemStack aStack, int aRenderPass) {
		switch(aRenderPass) {
		case 0: return getRGBa(F, aStack);
		case 1: return UNCOLOURED;
		case 2: return getRGBa(T, aStack);
		case 3: return UNCOLOURED;
		}
		return UNCOLOURED;
	}
}
