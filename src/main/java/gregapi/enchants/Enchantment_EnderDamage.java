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
public class Enchantment_EnderDamage extends EnchantmentDamage {
	public static Enchantment_EnderDamage INSTANCE;
	
	public Enchantment_EnderDamage() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Disjunction", 15), 2, -1);
		LH.add(getName(), "Disjunction");
		MT.Hg               .addEnchantmentForTools(this, 3);
		MT.Ag               .addEnchantmentForTools(this, 4);
		MT.RedMeteor        .addEnchantmentForTools(this, 3);
		MT.Electrum         .addEnchantmentForTools(this, 3);
		MT.BlackBronze      .addEnchantmentForTools(this, 2);
		MT.BlackSteel       .addEnchantmentForTools(this, 2);
		MT.RedSteel         .addEnchantmentForTools(this, 1);
		MT.BlueSteel        .addEnchantmentForTools(this, 3);
		MT.ElectrumFlux     .addEnchantmentForTools(this, 3);
		MT.Meutoite         .addEnchantmentForTools(this, 3);
		MT.SterlingSilver   .addEnchantmentForTools(this, 4);
		MT.AstralSilver     .addEnchantmentForTools(this, 5);
		MT.Desichalkos      .addEnchantmentForTools(this, 6);
		MT.VibraniumSilver  .addEnchantmentForTools(this,10);
		MT.Infinity         .addEnchantmentForTools(this,10);
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
		if (UT.Entities.isEnderCreature(aHurtEntity)) {
			// Weakness causes Endermen to not be able to teleport with GT being installed.
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.weakness.id , aLevel * 200, (int)UT.Code.bind(1, 5, (5*aLevel) / 7)));
			// They also get Poisoned. If you have this Enchant on an Arrow, you can kill the Ender Dragon easier.
			aHurtEntity.addPotionEffect(new PotionEffect(Potion.poison.id   , aLevel * 200, (int)UT.Code.bind(1, 5, (5*aLevel) / 7)));
		}
	}
	
	@Override
	public String getName() {
		return "enchantment.damage.endermen";
	}
}
