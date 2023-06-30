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
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.util.UT;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @author Gregorius Techneticies
 */
public class Enchantment_SlimeDamage extends EnchantmentDamage {
	public static Enchantment_SlimeDamage INSTANCE;
	
	public Enchantment_SlimeDamage() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Dissolving", 13), 2, -1);
		LH.add(getName(), "Dissolving");
		MT.KCl                 .addEnchantmentForDamage(this, 2);
		MT.KIO3                .addEnchantmentForDamage(this, 3);
		MT.NaCl                .addEnchantmentForDamage(this, 3);
		MT.As                  .addEnchantmentForDamage(this, 3);
		MT.Cu                  .addEnchantmentForDamage(this, 4);
		MT.Brass               .addEnchantmentForDamage(this, 2);
		MT.CobaltBrass         .addEnchantmentForDamage(this, 2);
		MT.BismuthBronze       .addEnchantmentForDamage(this, 2);
		MT.RoseGold            .addEnchantmentForDamage(this, 2);
		MT.SterlingSilver      .addEnchantmentForDamage(this, 2);
		MT.Bronze              .addEnchantmentForDamage(this, 2);
		MT.BlackBronze         .addEnchantmentForDamage(this, 2);
		MT.BlackSteel          .addEnchantmentForDamage(this, 2);
		MT.MeteoricBlackSteel  .addEnchantmentForDamage(this, 2);
		MT.MeteoflameBlackSteel.addEnchantmentForDamage(this, 2);
		MT.RedSteel            .addEnchantmentForDamage(this, 2);
		MT.MeteoricRedSteel    .addEnchantmentForDamage(this, 2);
		MT.MeteoflameRedSteel  .addEnchantmentForDamage(this, 2);
		MT.BlueSteel           .addEnchantmentForDamage(this, 2);
		MT.MeteoricBlueSteel   .addEnchantmentForDamage(this, 2);
		MT.MeteoflameBlueSteel .addEnchantmentForDamage(this, 2);
		MT.Constantan          .addEnchantmentForDamage(this, 3);
		MT.AnnealedCopper      .addEnchantmentForDamage(this, 4);
		MT.Hepatizon           .addEnchantmentForDamage(this, 5);
		MT.Vyroxeres           .addEnchantmentForDamage(this, 5);
		MT.ArsenicCopper       .addEnchantmentForDamage(this, 5);
		MT.ArsenicBronze       .addEnchantmentForDamage(this, 7);
		MT.Infinity            .addEnchantmentForDamage(this,10);
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
		if (UT.Entities.isSlimeCreature(aHurtEntity)) {
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.weakness.id , aLevel * 200, (int)UT.Code.bind(1, 5, (5*aLevel) / 7)));
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.poison.id   , aLevel * 200, (int)UT.Code.bind(1, 5, (5*aLevel) / 7)));
		}
	}
	
	@Override
	public String getName() {
		return "enchantment.damage.slime";
	}
}
