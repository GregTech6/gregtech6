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

package gregapi.item.multiitem.energy;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnergyStatDebug implements IItemEnergy {
	public static final EnergyStatDebug INSTANCE = new EnergyStatDebug();
	
	// Say yes to everything
	@Override public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting) {return T;}
	@Override public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {return aAmount;}
	@Override public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {return aAmount;}
	@Override public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse) {return T;}
	@Override public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {return aStack;}
	@Override public long getEnergyStored(TagData aEnergyType, ItemStack aStack) {return 4000000000000000000L;}
	@Override public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack) {return 8000000000000000000L;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack) {return 1;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack) {return 1;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack) {return 4000000000000000000L;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack) {return 4000000000000000000L;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack) {return Long.MAX_VALUE;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack) {return Long.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(ItemStack aStack) {return TD.Energy.ALL;}
	@Override public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {return T;}
	@Override public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize) {return T;}
}
