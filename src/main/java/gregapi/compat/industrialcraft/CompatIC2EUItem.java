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

package gregapi.compat.industrialcraft;

import gregapi.compat.CompatBase;
import gregapi.util.ST;
import gregapi.util.UT;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class CompatIC2EUItem extends CompatBase implements ICompatIC2EUItem {
	public CompatIC2EUItem() {
		ic2.api.item.IElectricItem.class.getCanonicalName();
		ic2.api.item.ElectricItem.class.getCanonicalName();
	}
	
	@Override
	public long charge(ItemStack aStack, long aAmount, int aTier, boolean aIgnoreLimit, boolean aDoCharge) {
		if (ic2.api.item.ElectricItem.manager == null) return 0;
		int tTier = ((ic2.api.item.IElectricItem)aStack.getItem()).getTier(aStack);
		if (tTier < 0 || tTier == aTier || aTier == Integer.MAX_VALUE) {
			if (!aIgnoreLimit && tTier >= 0) aAmount = (int)Math.min(aAmount, V[Math.max(0, Math.min(V.length-1, tTier))]);
			if (aAmount > 0) {
				long rAmount = (long)Math.max(0, ic2.api.item.ElectricItem.manager.charge(aStack, aAmount, tTier, T, !aDoCharge));
				return rAmount;
			}
		}
		return 0;
	}
	
	@Override
	public long decharge(ItemStack aStack, long aAmount, int aTier, boolean aIgnoreLimit, boolean aDoDecharge, boolean aIgnoreDischargability) {
		if (ic2.api.item.ElectricItem.manager == null) return 0;
		int tTier = ((ic2.api.item.IElectricItem)aStack.getItem()).getTier(aStack);
		if (tTier < 0 || tTier == aTier || aTier == Integer.MAX_VALUE) {
			if (!aIgnoreLimit && tTier >= 0) aAmount = (int)Math.min(aAmount, V[Math.max(0, Math.min(V.length-1, tTier))]);
			if (aAmount > 0) {
				long rAmount = (long)Math.max(0, ic2.api.item.ElectricItem.manager.discharge(aStack, aAmount + (aAmount * 4 > aTier ? aTier : 0), tTier, T, !aIgnoreDischargability, !aDoDecharge));
				return rAmount;
			}
		}
		return 0;
	}
	
	@Override public long charge(ItemStack aStack, long aAmount, boolean aDoCharge) {return aAmount > 0 && ic2.api.item.ElectricItem.manager != null ? (long)Math.max(0, ic2.api.item.ElectricItem.manager.charge(aStack, aAmount, Integer.MAX_VALUE, T, !aDoCharge)) : 0;}
	@Override public long decharge(ItemStack aStack, long aAmount, boolean aDoDecharge) {return aAmount > 0 && ic2.api.item.ElectricItem.manager != null ? (long)Math.max(0, ic2.api.item.ElectricItem.manager.discharge(aStack, aAmount, Integer.MAX_VALUE, T, F, !aDoDecharge)) : 0;}
	@Override public byte tier(ItemStack aStack) {return UT.Code.bind4(((ic2.api.item.IElectricItem)aStack.getItem()).getTier(aStack));}
	@Override public boolean insidevolt(ItemStack aStack, long aMinVolt, long aMaxVolt) {byte tTier = tier(aStack); return UT.Code.inside(aMinVolt, aMaxVolt, VMIN[tTier]) || UT.Code.inside(aMinVolt, aMaxVolt, VMAX[tTier]);}
	@Override public boolean provider(ItemStack aStack) {return ((ic2.api.item.IElectricItem)aStack.getItem()).canProvideEnergy(aStack);}
	@Override public boolean is(ItemStack aStack) {return ST.valid(aStack) && aStack.getItem() instanceof ic2.api.item.IElectricItem && ((IElectricItem)aStack.getItem()).getTier(aStack) < Integer.MAX_VALUE;}
	@Override public boolean is(ItemStack aStack, byte aTier) {return ST.valid(aStack) && aStack.getItem() instanceof ic2.api.item.IElectricItem && ((IElectricItem)aStack.getItem()).getTier(aStack) == aTier;}
	@Override public long stored(ItemStack aStack) {return ic2.api.item.ElectricItem.manager != null ? (long)Math.max(0, ic2.api.item.ElectricItem.manager.discharge(aStack, Long.MAX_VALUE, Integer.MAX_VALUE, T, F, T)) : 0;}
	@Override public long capacity(ItemStack aStack) {return (long) ((ic2.api.item.IElectricItem)aStack.getItem()).getMaxCharge(aStack);}
}
