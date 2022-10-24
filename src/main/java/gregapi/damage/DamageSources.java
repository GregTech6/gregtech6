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

package gregapi.damage;

import gregapi.util.UT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;

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
	
	public static IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity, String aMessage) {
		String aNamePlayer = aPlayer.getCommandSenderName(), aNameEntity = aEntity.getCommandSenderName();
		if (UT.Code.stringInvalid(aNamePlayer) || UT.Code.stringInvalid(aEntity)) return new ChatComponentText("Death Message lacks names of involved Players");
		aNamePlayer = aNamePlayer.trim(); aNameEntity = aNameEntity.trim();
		if (aNamePlayer.equalsIgnoreCase("CrazyJ84") || aNamePlayer.equalsIgnoreCase("CrazyJ1984")) {
			if (aNameEntity.equalsIgnoreCase("Bear989jr")) return new ChatComponentText("<"+ EnumChatFormatting.LIGHT_PURPLE+"Mrs. Crazy"+EnumChatFormatting.WHITE + "> Sorry "+EnumChatFormatting.RED+"Junior"+EnumChatFormatting.WHITE);
			if (aNameEntity.equalsIgnoreCase("Bear989Sr")) return new ChatComponentText("<"+EnumChatFormatting.LIGHT_PURPLE+"Mrs. Crazy"+EnumChatFormatting.WHITE + "> Hush it!, "+EnumChatFormatting.RED+"Bear"+EnumChatFormatting.WHITE+"!");
		}
		if (aNamePlayer.equalsIgnoreCase("Bear989Sr") || aNamePlayer.equalsIgnoreCase("Bear989jr")) {
			//
		}
		return getDeathMessage(aPlayer, aEntity, aNamePlayer, aNameEntity, aMessage);
	}
	
	public static IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity, String aNamePlayer, String aNameEntity, String aMessage) {
		if (UT.Code.stringInvalid(aMessage)) return new EntityDamageSource(aPlayer instanceof EntityPlayer ? "player" : "mob", aPlayer).func_151519_b(aEntity);
		return new ChatComponentText(aMessage.replace("[KILLER]", EnumChatFormatting.GREEN+aNamePlayer+EnumChatFormatting.WHITE).replace("[VICTIM]", EnumChatFormatting.RED+aNameEntity+EnumChatFormatting.WHITE));
	}
}
