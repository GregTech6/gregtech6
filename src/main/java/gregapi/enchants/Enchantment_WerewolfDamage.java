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

package gregapi.enchants;

import gregapi.config.Config;
import gregapi.config.ConfigCategories;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import static gregapi.data.CS.RNGSUS;

/**
 * @author Gregorius Techneticies
 */
public class Enchantment_WerewolfDamage extends EnchantmentDamage {
	public static Enchantment_WerewolfDamage INSTANCE;
	
	public Enchantment_WerewolfDamage() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Werebane", 12), 2, -1);
		LH.add(getName(), "Werebane");
		MT.Ir                  .addEnchantmentForDamage(this, 6);
		MT.Osmiridium          .addEnchantmentForDamage(this, 6);
		MT.HSSS                .addEnchantmentForDamage(this, 6);
		MT.Ag                  .addEnchantmentForDamage(this, 4);
		MT.Electrum            .addEnchantmentForDamage(this, 3);
		MT.BlackBronze         .addEnchantmentForDamage(this, 2);
		MT.BlackSteel          .addEnchantmentForDamage(this, 2);
		MT.MeteoricBlackSteel  .addEnchantmentForDamage(this, 2);
		MT.MeteoflameBlackSteel.addEnchantmentForDamage(this, 2);
		MT.RedSteel            .addEnchantmentForDamage(this, 1);
		MT.MeteoricRedSteel    .addEnchantmentForDamage(this, 1);
		MT.MeteoflameRedSteel  .addEnchantmentForDamage(this, 1);
		MT.BlueSteel           .addEnchantmentForDamage(this, 3);
		MT.MeteoricBlueSteel   .addEnchantmentForDamage(this, 3);
		MT.MeteoflameBlueSteel .addEnchantmentForDamage(this, 3);
		MT.SterlingSilver      .addEnchantmentForDamage(this, 4);
		MT.AstralSilver        .addEnchantmentForDamage(this, 5);
		MT.VibraniumSilver     .addEnchantmentForDamage(this,10);
		MT.Craponite           .addEnchantmentForDamage(this,10);
		MT.Tc                  .addEnchantmentForDamage(this,10);
		MT.Infinity            .addEnchantmentForDamage(this,10);
		MT.DiamondPink         .addEnchantmentForDamage(this,10);
		INSTANCE = this;
	}
	
	@Override
	public int getMinEnchantability(int aLevel) {
		return 5 + (aLevel - 1) * 8;
	}
	
	@Override
	public int getMaxEnchantability(int aLevel) {
		return this.getMinEnchantability(aLevel) + 20;
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
	public void func_151367_b(EntityLivingBase aHurtEntity, Entity aDamagingEntity, int aLevel) {
		if (UT.Entities.isWereCreature(aHurtEntity)) {
			// Anti Bear Damage now works through the Quantum Suit too, just in a different way. XD
			if (!aHurtEntity.worldObj.isRemote && aHurtEntity instanceof EntityPlayer && "Bear989Sr".equalsIgnoreCase(aHurtEntity.getCommandSenderName())) {
				UT.Sounds.send(SFX.MC_FIREWORK_LARGE, aHurtEntity);
				for (int i = -1; i < aLevel; i++) {
					int tSlot = RNGSUS.nextInt(((EntityPlayer)aHurtEntity).inventory.mainInventory.length);
					ItemStack tStack = ((EntityPlayer)aHurtEntity).inventory.mainInventory[tSlot];
					if (ST.valid(tStack)) {
						EntityItem tEntity = ST.drop(aHurtEntity, ST.copy_(tStack));
						if (tEntity != null) {
							tEntity.delayBeforeCanPickup = 40;
							((EntityPlayer)aHurtEntity).inventory.mainInventory[tSlot] = null;
						}
					}
				}
			}
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.wither.id, aLevel * 200, (int)UT.Code.bind(1, 5, (10*aLevel) / 7)));
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.poison.id, aLevel * 200, (int)UT.Code.bind(1, 5, (10*aLevel) / 7)));
		}
	}
	
	@Override
	public String getName() {
		return "enchantment.damage.werewolf";
	}
}
