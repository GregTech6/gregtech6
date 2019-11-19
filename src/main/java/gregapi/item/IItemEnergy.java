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

package gregapi.item;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.data.IL;
import gregapi.data.TD;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * Interface for Items with some kind of Charge.
 */
public interface IItemEnergy {
	// for tampering with the charge of an Object.
	// 
	// The World and Coordinate Parameters are there in case something should happen when tampering with the Item.
	//   This could be used for remote re-chargable Items.
	// The Inventory Parameters are for the Inventory the Item is inside.
	//   If it is inside a Player, then please always add the Player Inventory.
	//   If this is inside a TileEntity, then you are not required to add the IInventory, even if it has an Inventory.
	
	/**
	 * You do not have to check for this Function, this is only for things like Slot insertion Conditions and similar.
	 * @param aEnergyType The Type of Energy
	 * @param aEmitting if it is asked emit this Energy Type, otherwise it is asked to accept this Energy Type.
	 * @return if this Item has anything to do with this Type of Energy, depending on insert or extract request. The returning Value must be constant for this Item.
	 */
	public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting);
	
	/**
	 * Gets all the Types of Energy, which are relevant to this Item.
	 * 
	 * @return any Type of Energy that is related to this Item. Use java.util.Collections.EMPTY_LIST if you don't have any Energy Types on this Item/Meta/NBT.
	 */
	public Collection<TagData> getEnergyTypes(ItemStack aStack);
	
	/**
	 * Charges an Energy Storage. The Coordinates represent the position of wherever the Item is supposed to be.
	 * @param aEnergyType The Type of Energy
	 * @param aWorld if the Coordinates passed are valid then this must be != null. if this is null, then the Position is unknown.
	 * @param aInventory the Inventory the Item is in. May be null.
	 * @param aDoInject if this is supposed to increase the internal Energy. true = Yes, this is a normal Operation. false = No, this is just a simulation.
	 * @return amount of used aAmount.
	 */
	public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject);
	
	public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize);
	
	/**
	 * Decharges an Energy Storage. The Coordinates represent the position of wherever the Item is supposed to be.
	 * @param aEnergyType The Type of Energy
	 * @param aWorld if the Coordinates passed are valid then this must be != null. if this is null, then the Position is unknown.
	 * @param aDoExtract if this is supposed to decrease the internal Energy. true = Yes, this is a normal Operation. false = No, this is just a simulation.
	 * @return amount of taken aAmount.
	 */
	public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract);
	
	public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize);
	
	/**
	 * "canUse" and "use" together in one Function by using a Simulate Parameter.
	 * @param aEnergyType The Type of Energy
	 * @param aWorld if the Coordinates passed are valid then this must be != null. if this is null, then the Position is unknown.
	 * @param aDoUse if this is supposed to decrease the internal Energy. true = Yes, this is a normal Operation. false = No, this is just a simulation.
	 * @return true if this can use that much Energy.
	 */
	public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse);
	
	/**
	 * @param aEnergyType The Type of Energy.
	 * @return the ItemStack you used as a Parameter. This is for convenience.
	 */
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount);
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return amount of Energy stored.
	 */
	public long getEnergyStored(TagData aEnergyType, ItemStack aStack);
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return amount of Energy that can be stored. 0 if not accepting this Energy.
	 */
	public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack);
	
	
	// Parameters for the minimum Packet Sizes in order for things to actually work.
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The minimum Energy Packet Size for the INPUT of this Object.
	 */
	public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack);
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The minimum Energy Packet Size for the OUTPUT of this Object.
	 */
	public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack);
	
	
	// Parameters for the recommended Packet Sizes.
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The recommended Energy Packet Size for the INPUT of this Object.
	 */
	public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack);
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The recommended Energy Packet Size for the OUTPUT of this Object.
	 */
	public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack);
	
	
	// Parameters for the maximum Packet Sizes before bad shit happens.
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The maximum Energy Packet Size for the INPUT of this Object.
	 */
	public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack);
	
	/**
	 * @param aEnergyType The Type of Energy
	 * @return The maximum Energy Packet Size for the OUTPUT of this Object.
	 */
	public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack);
	
	/** Utility for the Energy Items */
	public static class Utility {
		public static long inject(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {
			if (ST.invalid(aStack)) return 0;
			if (aStack.getItem() instanceof IItemEnergy) return ((IItemEnergy)aStack.getItem()).doEnergyInjection(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoInject);
			if (aEnergyType == TD.Energy.EU && COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(aStack) && !IL.IC2_EnergyCrystal.equal(aStack, T, T) && !IL.IC2_LapotronCrystal.equal(aStack, T, T) && COMPAT_EU_ITEM.insidevolt(aStack, UT.Code.voltMin(aSize), UT.Code.voltMax(aSize)) && COMPAT_EU_ITEM.charge(aStack, aSize, aDoInject) > 0) return 1;
			return 0;
		}
		
		public static long extract(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {
			if (ST.invalid(aStack)) return 0;
			if (aStack.getItem() instanceof IItemEnergy) return ((IItemEnergy)aStack.getItem()).doEnergyExtraction(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoExtract);
			if (aEnergyType == TD.Energy.EU && COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(aStack) && !IL.IC2_EnergyCrystal.equal(aStack, T, T) && !IL.IC2_LapotronCrystal.equal(aStack, T, T) && COMPAT_EU_ITEM.provider(aStack) && COMPAT_EU_ITEM.insidevolt(aStack, UT.Code.voltMin(aSize), UT.Code.voltMax(aSize)) && COMPAT_EU_ITEM.decharge(aStack, aSize, aDoExtract) >= aSize) return 1;
			return 0;
		}
		
		public static boolean full(ItemStack aStack, boolean aDefault) {
			if (ST.invalid(aStack)) return aDefault;
			if (aStack.getItem() instanceof IItemEnergy) for (TagData tEnergyType : ((IItemEnergy)aStack.getItem()).getEnergyTypes(aStack)) if (((IItemEnergy)aStack.getItem()).getEnergyStored(tEnergyType, aStack) < ((IItemEnergy)aStack.getItem()).getEnergyCapacity(tEnergyType, aStack)) return F;
			if (COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(aStack) && COMPAT_EU_ITEM.stored(aStack) < COMPAT_EU_ITEM.capacity(aStack)) return F;
			return aDefault;
		}
		
		public static boolean empty(ItemStack aStack, boolean aDefault) {
			if (ST.invalid(aStack)) return aDefault;
			if (aStack.getItem() instanceof IItemEnergy) for (TagData tEnergyType : ((IItemEnergy)aStack.getItem()).getEnergyTypes(aStack)) if (((IItemEnergy)aStack.getItem()).getEnergyStored(tEnergyType, aStack) > 0) return F;
			if (COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(aStack) && COMPAT_EU_ITEM.stored(aStack) > 0) return F;
			return aDefault;
		}
	}
}
