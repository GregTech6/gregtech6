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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodProduct extends MultiTileEntityReactorRodBase {
	public short mBreeding = -1;
	public String mBreedingName = "";

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_VALUE           ))  mBreeding        = aNBT.getShort(NBT_VALUE);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN + "Emits half the Heat per Neutron on this Rod");
		aList.add(LH.Chat.CYAN + "Can be centrifuged to get valuable materials");
		if (mBreedingName.equals(""))  mBreedingName = ST.meta(aStack.copy(), mBreeding).getDisplayName();
		aList.add(LH.Chat.GREEN + "Breed from " + mBreedingName);
	}

	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot] / 2;
		return T;
	}

	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.product";}
}
