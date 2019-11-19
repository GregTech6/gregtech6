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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Cropnalyzer extends AbstractBehaviorDefault {
	public static final Behavior_Cropnalyzer INSTANCE = new Behavior_Cropnalyzer();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			ArrayList<String> tList = new ArrayListNoNulls<>();
			long tUsedEnergy = getCropScan(tList, aWorld, aX, aY, aZ);
			if (tUsedEnergy <= 0) return F;
			if (aItem.useEnergy(TD.Energy.EU, aStack, tUsedEnergy, aPlayer, aPlayer.inventory, aWorld, aX, aY, aZ, T)) UT.Entities.sendchat(aPlayer, tList, F);
			return T;
		}
		UT.Sounds.play(SFX.IC_SCANNER, 20, 1.0F, aX, aY, aZ);
		return aPlayer instanceof EntityPlayerMP;
	}
	
	static {
		LH.add("gt.behaviour.cropnalyzer", "Can scan Crops in World");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.cropnalyzer"));
		return aList;
	}
	
	public long getCropScan(ArrayList<String> aList, World aWorld, int aX, int aY, int aZ) {
		if (aList == null || !MD.IC2.mLoaded) return 0;
		
		ArrayList<String> rList = new ArrayListNoNulls<>();
		long rEUAmount = 0;
		
		TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		
		if (tTileEntity instanceof ic2.api.crops.ICropTile) {
			rList.add("--- X: " + aX + " Y: " + aY + " Z: " + aZ + " ---");
			if (((ic2.api.crops.ICropTile)tTileEntity).getScanLevel() < 4) {
				rEUAmount = V[6];
				((ic2.api.crops.ICropTile)tTileEntity).setScanLevel((byte)4);
			} else {
				rEUAmount = V[3];
			}
			rList.add("Type -- Name: " + LH.get(((ic2.api.crops.ICropTile)tTileEntity).getCrop().displayName())
					+ "   Growth: " + ((ic2.api.crops.ICropTile)tTileEntity).getGrowth()
					+ "   Gain: " + ((ic2.api.crops.ICropTile)tTileEntity).getGain()
					+ "   Resistance: " + ((ic2.api.crops.ICropTile)tTileEntity).getResistance()
					);
			rList.add("Plant -- Fertilizer: " + ((ic2.api.crops.ICropTile)tTileEntity).getNutrientStorage()
					+ "   Water: " + ((ic2.api.crops.ICropTile)tTileEntity).getHydrationStorage()
					+ "   Weed-Ex: " + ((ic2.api.crops.ICropTile)tTileEntity).getWeedExStorage()
			//      + "   Scan-Level: " + ((ic2.api.crops.ICropTile)tTileEntity).getScanLevel()
					);
			rList.add("Environment -- Nutrients: " + ((ic2.api.crops.ICropTile)tTileEntity).getNutrients()
					+ "   Humidity: " + ((ic2.api.crops.ICropTile)tTileEntity).getHumidity()
					+ "   Air-Quality: " + ((ic2.api.crops.ICropTile)tTileEntity).getAirQuality()
					);
			String tString = "";
			for (String tAttribute : ((ic2.api.crops.ICropTile)tTileEntity).getCrop().attributes()) tString += ", " + tAttribute;
			rList.add("Attributes:" + tString.replaceFirst(",", ""));
			rList.add("Discovered by: " + ((ic2.api.crops.ICropTile)tTileEntity).getCrop().discoveredBy());
			
		}
		aList.addAll(rList);
		return rEUAmount;
	}
}
