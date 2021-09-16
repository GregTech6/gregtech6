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
		MT.KCl               .addEnchantmentForTools(this, 2);
		MT.KIO3              .addEnchantmentForTools(this, 3);
		MT.NaCl              .addEnchantmentForTools(this, 3);
		MT.Cu                .addEnchantmentForTools(this, 4);
		MT.Brass             .addEnchantmentForTools(this, 2);
		MT.CobaltBrass       .addEnchantmentForTools(this, 2);
		MT.BismuthBronze     .addEnchantmentForTools(this, 2);
		MT.RoseGold          .addEnchantmentForTools(this, 2);
		MT.SterlingSilver    .addEnchantmentForTools(this, 2);
		MT.Bronze            .addEnchantmentForTools(this, 2);
		MT.BlackBronze       .addEnchantmentForTools(this, 2);
		MT.BlackSteel        .addEnchantmentForTools(this, 2);
		MT.MeteoricBlackSteel.addEnchantmentForTools(this, 2);
		MT.RedSteel          .addEnchantmentForTools(this, 2);
		MT.MeteoricRedSteel  .addEnchantmentForTools(this, 2);
		MT.BlueSteel         .addEnchantmentForTools(this, 2);
		MT.MeteoricBlueSteel .addEnchantmentForTools(this, 2);
		MT.Constantan        .addEnchantmentForTools(this, 3);
		MT.AnnealedCopper    .addEnchantmentForTools(this, 5);
		MT.Hepatizon         .addEnchantmentForTools(this, 5);
		MT.Vyroxeres         .addEnchantmentForTools(this, 5);
		MT.Infinity          .addEnchantmentForTools(this,10);
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
