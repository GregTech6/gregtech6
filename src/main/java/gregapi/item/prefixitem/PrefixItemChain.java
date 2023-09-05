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

import baubles.api.BaubleType;
import cpw.mods.fml.common.Optional;
import gregapi.code.ModData;
import gregapi.data.CS.ModIDs;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class PrefixItemChain extends PrefixItemBauble {
	public PrefixItemChain(ModData aMod, String aNameInternal, OreDictPrefix aPrefix) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, OreDictMaterial.MATERIAL_ARRAY);
	}
	
	public PrefixItemChain(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aMaterialList);
	}
	
	@Optional.Method(modid = ModIDs.BAUBLES)
	@Override public BaubleType getBaubleType(ItemStack aStack) {return BaubleType.AMULET;}
}
