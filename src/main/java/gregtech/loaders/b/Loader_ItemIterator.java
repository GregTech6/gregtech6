/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.loaders.b;

import static gregapi.data.CS.*;

import java.util.Iterator;

import gregapi.data.IL;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class Loader_ItemIterator implements Runnable {
	@Override
	public void run() {
		boolean tCheckCans = (IL.IC2_Food_Can_Empty.exists() && IL.IC2_Food_Can_Filled.exists());
		
		if (tCheckCans) {
		RM.Canner.addRecipe2(T, 16, 64, ST.make(Items.rotten_flesh , 1, W), IL.IC2_Food_Can_Empty.get( 4), IL.IC2_Food_Can_Spoiled  .get( 4, IL.IC2_Food_Can_Filled.get(4)));
		RM.Canner.addRecipe2(T, 16, 32, ST.make(Items.spider_eye   , 1, W), IL.IC2_Food_Can_Empty.get( 2), IL.IC2_Food_Can_Poisonous.get( 2, IL.IC2_Food_Can_Filled.get(2)));
		RM.Canner.addRecipe2(T, 16, 32, IL.Food_Potato_Poisonous   .get(1), IL.IC2_Food_Can_Empty.get( 2), IL.IC2_Food_Can_Poisonous.get( 2, IL.IC2_Food_Can_Filled.get(2)));
		RM.Canner.addRecipe2(T, 16, 96, ST.make(Items.cake         , 1, W), IL.IC2_Food_Can_Empty.get(12), IL.IC2_Food_Can_Filled   .get(12));
		RM.Canner.addRecipe2(T, 16, 48, ST.make(Items.mushroom_stew, 1, W), IL.IC2_Food_Can_Empty.get( 6), IL.IC2_Food_Can_Filled   .get( 6), ST.make(Items.bowl, 1, 0));
		}
		
		@SuppressWarnings("rawtypes")
		Iterator tIterator = Item.itemRegistry.iterator();
		
		Object tObject;
		String tName;
		
		while (tIterator.hasNext()) if ((tObject = tIterator.next()) instanceof Item && !ST.isGT((Item)tObject)) {
			Item tItem = (Item)tObject;
			Block tBlock = ST.block(tItem);
			
			// Hide all those stupid Double Slabs from NEI...
			if (tBlock instanceof BlockSlab && tBlock.isOpaqueCube()) ST.hide(tBlock);
			
			if ((tName = tItem.getUnlocalizedName()) != null) {
				if (tCheckCans && tItem instanceof ItemFood && tItem != IL.IC2_Food_Can_Filled.item() && tItem != IL.IC2_Food_Can_Spoiled.item()) {
					int tFoodValue = ((ItemFood)tItem).func_150905_g(ST.make(tItem, 1, 0));
					if (tFoodValue > 0) RM.Canner.addRecipe2(T, 16, 16*tFoodValue, ST.make(tItem, 1, W), IL.IC2_Food_Can_Empty.get(tFoodValue), IL.IC2_Food_Can_Filled.get(tFoodValue), ST.container(ST.make(tItem, 1, 0), T));
				}
				
				// TODO: Gah, too lazy to install those Mods again to do it proper, and those are not Registry Names, that I could just plop in somewhere else, so welp...
				
				if (tName.equals("item.ItemSensorLocationCard") || tName.equals("item.ItemEnergySensorLocationCard") || tName.equals("item.ItemEnergyArrayLocationCard") || tName.equals("item.ItemTextCard")) {
					RM.unpack(ST.make(tItem, 1, W), IL.Circuit_Basic.get(2));
				}
				if (tName.equals("item.ItemTimeCard")) {
					RM.unpack(ST.make(tItem, 1, W), IL.Circuit_Basic.get(1));
				}
				if (tName.equals("item.ccprintout")) {
					OM.reg_(OD.paperWritten, ST.make(tItem, 1, 0));
					OM.reg_(OD.paperWritten, ST.make(tItem, 1, 1));
					OM.reg_(OD.bookWritten , ST.make(tItem, 1, 2));
				}
			}
		}
	}
}
