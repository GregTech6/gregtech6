/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.TagData;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Gun extends AbstractBehaviorDefault {
	public static Behavior_Gun BULLETS_SMALL  = new Behavior_Gun(TD.Projectiles.BULLET_SMALL);
	public static Behavior_Gun BULLETS_MEDIUM = new Behavior_Gun(TD.Projectiles.BULLET_MEDIUM);
	public static Behavior_Gun BULLETS_LARGE  = new Behavior_Gun(TD.Projectiles.BULLET_LARGE);
	
	public final TagData mBulletType;
	
	public Behavior_Gun(TagData aBulletType) {
		mBulletType = aBulletType;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get(LH.WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD));
		return aList;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (aPlayer instanceof EntityPlayerMP) {
			if (aPlayer.isSneaking()) {
				UT.Sounds.send(SFX.MC_FIREWORK_BLAST, 20, 1.0F, aPlayer);
			} else {
				UT.Sounds.send(SFX.MC_FIREWORK_BLAST_FAR, 20, 1.0F, aPlayer);
			}
		}
		return aStack;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer instanceof EntityPlayerMP) {
			// TODO: Just hit the Entity directly for +2 Hearts Bonus Damage.
			if (aPlayer.isSneaking()) {
				UT.Sounds.send(SFX.MC_FIREWORK_LARGE, 20, 1.0F, aPlayer);
			} else {
				UT.Sounds.send(SFX.MC_FIREWORK_LARGE_FAR, 20, 1.0F, aPlayer);
			}
			return T;
		}
		return T;
	}
}
