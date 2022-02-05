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
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies, Erik3003
 */
public class MultiTileEntityReactorRodModerator extends MultiTileEntityReactorRodBase {
	short mModeration = 0, oModeration = 0;

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN + "Reflects neutrons back times the number of fuel rods touching when active");
		aList.add(LH.Chat.CYAN + "Touching Fuel Rods become moderated and moderate touching Fuel Rods");
		aList.add(LH.Chat.CYAN + "Moderated Fuel Rods can't be used for Breeding and only last a quarter as long");
	}

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD  )) mModeration     = aNBT.getShort(NBT_NUCLEAR_MOD);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD+".o")) oModeration      = aNBT.getShort(NBT_NUCLEAR_MOD+".o");
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_NUCLEAR_MOD, mModeration);
		UT.NBT.setNumber(aNBT, NBT_NUCLEAR_MOD+".o", oModeration);
	}

	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_NUCLEAR_MOD, mModeration);
		UT.NBT.setNumber(aNBT, NBT_NUCLEAR_MOD+".o", oModeration);
		return super.writeItemNBT2(aNBT);
	}


	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return 0;
	}
	
	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		if (SERVER_TIME % 20 == 19) {
			oModeration = mModeration;
			mModeration = 0;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		}
		return F;
	}
	
	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		if (aNeutrons > 0) {
			mModeration++;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		}
		return oModeration * aNeutrons;
	}

	@Override
	public boolean isModerated(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return true;
	}

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.moderator";}
}
