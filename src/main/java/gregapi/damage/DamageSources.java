package gregapi.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

/**
 * @author Gregorius Techneticies
 */
public class DamageSources {
	public static DamageSource getElectricDamage() {
		return ic2.api.info.Info.DMG_ELECTRIC;
	}
	
	public static DamageSource getRadioactiveDamage() {
		return ic2.api.info.Info.DMG_RADIATION;
	}
	
	public static DamageSource getNukeExplosionDamage() {
		return ic2.api.info.Info.DMG_NUKE_EXPLOSION;
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