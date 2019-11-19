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

package gregapi.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

/**
 * @author Gregorius Techneticies
 */
public class DamageSources {
	public static DamageSource getElectricDamage() {
		try {return ic2.api.info.Info.DMG_ELECTRIC;} catch(Throwable e) {/**/}
		return getHeatDamage();
	}
	
	public static DamageSource getRadioactiveDamage() {
		try {return ic2.api.info.Info.DMG_RADIATION;} catch(Throwable e) {/**/}
		return getHeatDamage();
	}
	
	public static DamageSource getNukeExplosionDamage() {
		try {return ic2.api.info.Info.DMG_NUKE_EXPLOSION;} catch(Throwable e) {/**/}
		return getHeatDamage();
	}
	
	public static DamageSource getExplodingDamage() {
		return new DamageSourceExploding();
	}
	
	public static DamageSource getCombatDamage(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage) {
		return new DamageSourceCombat(aType, aPlayer, aDeathMessage);
	}
	
	public static DamageSource getSpikeDamage() {
		return new DamageSourceSpike();
	}
	
	public static DamageSource getShredderDamage() {
		return new DamageSourceShredder();
	}
	
	public static DamageSource getCrusherDamage() {
		return new DamageSourceCrusher();
	}
	
	public static DamageSource getHeatDamage() {
		return new DamageSourceHeat();
	}
	
	public static DamageSource getFrostDamage() {
		return new DamageSourceFrost();
	}
	
	public static DamageSource getChemDamage() {
		return new DamageSourceChem();
	}
	
	public static DamageSource getBumbleDamage() {
		return new DamageSourceBumble();
	}
	
	public static DamageSource getAlcoholDamage() {
		return new DamageSourceAlcohol();
	}
	
	public static DamageSource getCaffeineDamage() {
		return new DamageSourceCaffeine();
	}
	
	public static DamageSource getDehydrationDamage() {
		return new DamageSourceDehydration();
	}
	
	public static DamageSource getSugarDamage() {
		return new DamageSourceSugar();
	}
	
	public static DamageSource getFatDamage() {
		return new DamageSourceFat();
	}
}
