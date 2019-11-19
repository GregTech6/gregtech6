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

package gregapi.item.prefixitem;

import static gregapi.data.CS.*;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Optional;
import gregapi.code.ModData;
import gregapi.data.CS.ModIDs;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.UT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
@Optional.Interface(iface = "baubles.api.IBauble", modid = ModIDs.BAUBLES)
})
public class PrefixItemRing extends PrefixItem implements IBauble {
	public PrefixItemRing(ModData aMod, String aNameInternal, OreDictPrefix aPrefix) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, OreDictMaterial.MATERIAL_ARRAY);
	}
	
	public PrefixItemRing(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aMaterialList);
	}
	
	@Override
	public void onWornTick(ItemStack aStack, EntityLivingBase aPlayer) {
		if (aPlayer.ticksExisted % 120 == 0 && !UT.Entities.isInvincible(aPlayer)) {
			UT.Entities.applyRadioactivity(aPlayer, UT.Entities.getRadioactivityLevel(aStack), aStack.stackSize);
		}
	}
	
	@Optional.Method(modid = ModIDs.BAUBLES)
	@Override public BaubleType getBaubleType(ItemStack aStack) {return BaubleType.RING;}
	@Override public void onEquipped(ItemStack aStack, EntityLivingBase aPlayer) {/**/}
	@Override public void onUnequipped(ItemStack aStack, EntityLivingBase aPlayer) {/**/}
	@Override public boolean canEquip(ItemStack aStack, EntityLivingBase aPlayer) {return T;/*aStack != null && aStack.stackSize == 1;*/}
	@Override public boolean canUnequip(ItemStack aStack, EntityLivingBase aPlayer) {return T;}
}
