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

package gregapi.oredict.event;

import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictListenerEvent {
	/** Any Event will buffered to be called after the Unification has been set, so you can access all the Unification things, regardless of when you register your Listener! */
	public void onOreRegistration(OreDictRegistrationContainer aEvent);
	
	public static class OreDictRegistrationContainer {
		public final OreDictPrefix mPrefix;
		public final OreDictMaterial mMaterial;
		public final String mOreDictName, mModName;
		public final ItemStack mStack;
		public final OreRegisterEvent mEvent;
		/** If something else hasn't already been registered under the same Name before. Useful for preventing duplicate Crafting Recipes. */
		public final boolean mNotAlreadyRegisteredName;
		
		public OreDictRegistrationContainer(OreDictPrefix aPrefix, OreDictMaterial aMaterial, String aOreDictName, ItemStack aStack, OreRegisterEvent aEvent, String aModName, boolean aNotAlreadyRegisteredName) {
			mPrefix = aPrefix;
			mMaterial = (aMaterial==null?MT.NULL:aMaterial);
			mOreDictName = aOreDictName;
			mModName = aModName;
			mStack = aStack.copy();
			mEvent = aEvent;
			mNotAlreadyRegisteredName = aNotAlreadyRegisteredName;
		}
	}
}
