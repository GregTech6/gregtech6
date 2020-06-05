/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.oredict.listeners;

import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.lang.LanguageHandler;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.listeners.IOreDictListenerItem.OreDictListenerItem;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class OreDictListenerItem_Rocks extends OreDictListenerItem {
	public OreDictListenerItem_Rocks() {
		LH.add("gt.behaviour.rocks", "Indicates occurence of ");
	}
	
	@Override
	public String getListenerToolTip(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return aMaterial == MT.MeteoricIron || aMaterial == MT.Meteorite || aMaterial == MT.AncientDebris || aMaterial == MT.Ambrosium || aMaterial == MT.Glowstone ? null : LanguageHandler.translate("gt.behaviour.rocks", "Indicates occurence of ") + (aMaterial.contains(TD.Properties.STONE)?LH.Chat.WHITE:LH.Chat.YELLOW) + aMaterial.mNameLocal;
	}
}
