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

package gregapi.item.prefixitem;

import baubles.api.IBauble;
import cpw.mods.fml.common.Optional;
import gregapi.code.ModData;
import gregapi.data.CS.ModIDs;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IWarpingGear;

import static gregapi.data.CS.T;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "baubles.api.IBauble", modid = ModIDs.BAUBLES)
, @Optional.Interface(iface = "thaumcraft.api.IWarpingGear", modid = ModIDs.TC)
})
public abstract class PrefixItemBauble extends PrefixItem implements IBauble, IWarpingGear {
	public PrefixItemBauble(ModData aMod, String aNameInternal, OreDictPrefix aPrefix) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, OreDictMaterial.MATERIAL_ARRAY);
	}
	
	public PrefixItemBauble(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aMaterialList);
	}
	
	@Override
	public int getWarp(ItemStack aStack, EntityPlayer aPlayer) {
		OreDictMaterial tMat = getMaterial(ST.meta(aStack));
		return tMat != null && tMat.contains(TD.Properties.WARPING) ? 1 : 0;
	}
	
	@Override
	public void onWornTick(ItemStack aStack, EntityLivingBase aPlayer) {
		if (aPlayer.ticksExisted % 120 == 0 && !UT.Entities.isInvincible(aPlayer)) {
			UT.Entities.applyRadioactivity(aPlayer, UT.Entities.getRadioactivityLevel(aStack), aStack.stackSize);
		}
	}
	
	@Override public void onEquipped(ItemStack aStack, EntityLivingBase aPlayer) {/**/}
	@Override public void onUnequipped(ItemStack aStack, EntityLivingBase aPlayer) {/**/}
	@Override public boolean canEquip(ItemStack aStack, EntityLivingBase aPlayer) {return T;/*aStack != null && aStack.stackSize == 1;*/}
	@Override public boolean canUnequip(ItemStack aStack, EntityLivingBase aPlayer) {return T;}
}
