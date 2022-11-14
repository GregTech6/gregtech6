/**
 * Copyright (c) 2022 GregTech-6 Team
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
		MT.Ir                .addEnchantmentForWeapons(this, 6).addEnchantmentForAmmo(this, 6);
		MT.Osmiridium        .addEnchantmentForWeapons(this, 6).addEnchantmentForAmmo(this, 6);
		MT.HSSS              .addEnchantmentForWeapons(this, 6).addEnchantmentForAmmo(this, 6);
		MT.Ag                .addEnchantmentForWeapons(this, 4).addEnchantmentForAmmo(this, 4);
		MT.Electrum          .addEnchantmentForWeapons(this, 3).addEnchantmentForAmmo(this, 3);
		MT.BlackBronze       .addEnchantmentForWeapons(this, 2).addEnchantmentForAmmo(this, 2);
		MT.BlackSteel        .addEnchantmentForWeapons(this, 2).addEnchantmentForAmmo(this, 2);
		MT.MeteoricBlackSteel.addEnchantmentForWeapons(this, 2).addEnchantmentForAmmo(this, 2);
		MT.RedSteel          .addEnchantmentForWeapons(this, 1).addEnchantmentForAmmo(this, 1);
		MT.MeteoricRedSteel  .addEnchantmentForWeapons(this, 1).addEnchantmentForAmmo(this, 1);
		MT.BlueSteel         .addEnchantmentForWeapons(this, 3).addEnchantmentForAmmo(this, 3);
		MT.MeteoricBlueSteel .addEnchantmentForWeapons(this, 3).addEnchantmentForAmmo(this, 3);
		MT.SterlingSilver    .addEnchantmentForWeapons(this, 4).addEnchantmentForAmmo(this, 4);
		MT.AstralSilver      .addEnchantmentForWeapons(this, 5).addEnchantmentForAmmo(this, 5);
		MT.VibraniumSilver   .addEnchantmentForWeapons(this,10).addEnchantmentForAmmo(this,10);
		MT.Craponite         .addEnchantmentForWeapons(this,10).addEnchantmentForAmmo(this,10);
		MT.Tc                .addEnchantmentForWeapons(this,10).addEnchantmentForAmmo(this,10);
		MT.Infinity          .addEnchantmentForWeapons(this,10).addEnchantmentForAmmo(this,10);
		MT.DiamondPink       .addEnchantmentForWeapons(this,10).addEnchantmentForAmmo(this,10);
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
