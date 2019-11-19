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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import java.util.Iterator;
import java.util.Map.Entry;

import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.util.OM;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class Loader_Recipes_Furnace implements Runnable {
	public static boolean RUNNING = F;
	
	@Override public void run() {
		// Just outright remove all Furnace Recipes, that both Input and Output OreDicted Stuff at the same time, we add the proper ones below anyways.
		@SuppressWarnings("unchecked")
		Iterator<Entry<ItemStack, ItemStack>> tIterator = FurnaceRecipes.smelting().getSmeltingList().entrySet().iterator();
		while (tIterator.hasNext()) {
			Entry<ItemStack, ItemStack> tEntry = tIterator.next();
			OreDictItemData tData1 = OM.anydata(tEntry.getKey());
			if (tData1 != null && tData1.hasValidPrefixMaterialData() && tData1.mMaterial.mMaterial.mID > 0) {
				OreDictItemData tData2 = OM.anydata(tEntry.getValue());
				if (tData2 != null && tData2.hasValidPrefixMaterialData() && tData2.mMaterial.mMaterial.mID > 0) {
					tIterator.remove();
				}
			}
		}
		
		RUNNING = T;
		OP.scrapGt               .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dust                  .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dustSmall             .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dustTiny              .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dustImpure            .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dustPure              .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.dustRefined           .addListener(new Listener_Furnace_Smelting( -1, F));
		OP.rockGt                .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.rawOreChunk           .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.chunk                 .addListener(new Listener_Furnace_Smelting(U*2, T));
		OP.rubble                .addListener(new Listener_Furnace_Smelting(U*2, T));
		OP.pebbles               .addListener(new Listener_Furnace_Smelting(U*2, T));
		OP.cluster               .addListener(new Listener_Furnace_Smelting(U*2, T));
		OP.crushed               .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.crushedTiny           .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.crushedPurified       .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.crushedPurifiedTiny   .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.crushedCentrifuged    .addListener(new Listener_Furnace_Smelting( -1, T));
		OP.crushedCentrifugedTiny.addListener(new Listener_Furnace_Smelting( -1, T));
		RUNNING = F;
	}
	
	public static class Listener_Furnace_Smelting implements IOreDictListenerEvent {
		private final long mTargetAmount;
		private final boolean mExp;
		
		public Listener_Furnace_Smelting(long aTargetAmount, boolean aExp) {
			mTargetAmount = aTargetAmount;
			mExp = aExp;
		}
		
		@Override
		public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (aEvent.mMaterial.contains(TD.Processing.FURNACE)) {
				long aTargetAmount = UT.Code.units(UT.Code.units(aEvent.mMaterial.mTargetSmelting.mAmount, U, aEvent.mMaterial.mTargetSmelting.mMaterial.mTargetSolidifying.mAmount, F), U, mTargetAmount<0?aEvent.mPrefix.mAmount:mTargetAmount, F);
				RM.add_smelting(aEvent.mStack, OM.ingot(aEvent.mMaterial.mTargetSmelting.mMaterial.mTargetSolidifying.mMaterial, aTargetAmount), mExp ? UT.Code.units(aTargetAmount, U, aEvent.mMaterial.mToolQuality+1, T) : 0, !RUNNING);
			}
		}
	}
}
