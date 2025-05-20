/**
 * Copyright (c) 2025 GregTech-6 Team
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
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;

import static gregapi.data.CS.F;

/**
 * @author Gregorius Techneticies
 */
public class DamageSourceCombat extends EntityDamageSource {
	private IChatComponent mDeathMessage;
	public boolean mBeheadingDamage = F;
	
	public DamageSourceCombat(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage) {this(aType, aPlayer, aDeathMessage, F);}
	public DamageSourceCombat(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage, boolean aBeheadingDamage) {
		super(aType, aPlayer);
		mBeheadingDamage = aBeheadingDamage;
		mDeathMessage = aDeathMessage;
	}
	
	@Override
	public IChatComponent func_151519_b(EntityLivingBase aTarget) {
		return mDeathMessage == null ? super.func_151519_b(aTarget) : mDeathMessage;
	}
}
