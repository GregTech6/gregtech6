/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodNuclear extends MultiTileEntityReactorRodBase {
	public long mDurability = 0;
	public int mNeutronSelf = 128, mNeutronOther = 128, mNeutronDiv = 8, mNeutronMax = 128;
	public short mDepleted = -1;
	public boolean mModerated = F, oModerated = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mDurability = aNBT.getLong(aNBT.hasKey(NBT_DURABILITY) ? NBT_DURABILITY : NBT_MAXDURABILITY);
		if (aNBT.hasKey(NBT_NUCLEAR_SELF    )) mNeutronSelf  = aNBT.getInteger(NBT_NUCLEAR_SELF );
		if (aNBT.hasKey(NBT_NUCLEAR_OTHER   )) mNeutronOther = aNBT.getInteger(NBT_NUCLEAR_OTHER);
		if (aNBT.hasKey(NBT_NUCLEAR_DIV     )) mNeutronDiv   = aNBT.getInteger(NBT_NUCLEAR_DIV  );
		if (aNBT.hasKey(NBT_NUCLEAR_MAX     )) mNeutronMax   = aNBT.getInteger(NBT_NUCLEAR_MAX);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD     )) mModerated    = aNBT.getBoolean(NBT_NUCLEAR_MOD);
		if (aNBT.hasKey(NBT_NUCLEAR_MOD+".o")) oModerated    = aNBT.getBoolean(NBT_NUCLEAR_MOD+".o");
		if (aNBT.hasKey(NBT_VALUE           )) mDepleted     = aNBT.getShort(NBT_VALUE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD, mModerated);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD+".o", oModerated);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD, mModerated);
		UT.NBT.setBoolean(aNBT, NBT_NUCLEAR_MOD+".o", oModerated);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN + "The " + LH.Chat.GREEN +  "Emission" + LH.Chat.CYAN + " describes how many Neutrons are emitted to adjacent Rods");
		aList.add(LH.Chat.CYAN + "The " + LH.Chat.GREEN +  "Self" + LH.Chat.CYAN + " describes how many Neutrons are put onto this Rod");
		aList.add(LH.Chat.CYAN + "The " + LH.Chat.GREEN +  "Maximum" + LH.Chat.CYAN + " describes how many Neutrons can be on this Rod while lasting the advertised duration");
		aList.add(LH.Chat.CYAN + "A greater " + LH.Chat.YELLOW +  "Factor" + LH.Chat.CYAN + " means the Rod emits more extra Neutrons for the amount of Neutrons on it");
		if (mModerated || oModerated) aList.add(LH.Chat.DBLUE + "This Fuel is " + LH.Chat.WHITE + "Moderated");
		aList.add(LH.Chat.CYAN + "Remaining: " + LH.Chat.WHITE + (mDurability / 120000) + LH.Chat.CYAN + " Minutes");

		ReactorCoolant coolant = ReactorCoolant.getCoolantAtIdx(CLIENT_TIME / 100);
		aList.add(LH.Chat.CYAN + "When used with " + coolant.mCoolant.getLocalizedName() + ": ");
		aList.add(LH.Chat.GREEN + "Emission: " + LH.Chat.WHITE + coolant.getEmission(mNeutronOther) + LH.Chat.PURPLE + " Neutrons/t");
		aList.add(LH.Chat.GREEN + "Self: " + LH.Chat.WHITE + coolant.getSelf(mNeutronSelf) + LH.Chat.PURPLE + " Neutrons/t");
		aList.add(LH.Chat.GREEN + "Maximum: " + LH.Chat.WHITE + coolant.getMax(mNeutronMax) + LH.Chat.PURPLE + " Neutrons/t");
		aList.add(LH.Chat.YELLOW + "Factor: " + LH.Chat.WHITE + ReactorCoolant.fractionToString(coolant.getFactor(mNeutronDiv)));
		if (coolant.mModerates) {
			aList.add(LH.Chat.GREEN + "Fuel rods will be " + LH.Chat.WHITE + "Moderated");
		}
		if (coolant.mHeatDivisor != 1) {
			aList.add(LH.Chat.GREEN + ReactorCoolant.fractionToString(1.0 / coolant.mHeatDivisor) + " the Heat per Neutron");
		}
		int criticalThreshold = coolant.getCriticalThreshold();
		if (mNeutronDiv == criticalThreshold) { // The neutron count goes up linearly; warn the user
			aList.add(LH.Chat.RED + "This Fuel is" + LH.Chat.BLINKING_RED + " Critical");
		} else if (mNeutronDiv < criticalThreshold) { // The neutron count goes up exponentially
			aList.add(LH.Chat.RED + "This Fuel is" + LH.Chat.BLINKING_RED + " Supercritical");
		}
	}
	
	@Override
	// Gets called every 20 Ticks. It emits the Self neutrons to its own slot and returns how many neutrons are emitted to neighbouring slots
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		ReactorCoolant coolant = ReactorCoolant.getCoolant(aReactor.inputTank.getFluid());

		// Without coolant the rod still reacts on its base stats, so the reactor melts down.
		int tNeutronOther = coolant == null ? mNeutronOther : coolant.getEmission(mNeutronOther);
		int tNeutronSelf = coolant == null ? mNeutronSelf : coolant.getSelf(mNeutronSelf);
		double tNeutronFactor = coolant == null ? 1.0 / mNeutronDiv : coolant.getFactor(mNeutronDiv);

		aReactor.addNeutrons(aSlot, tNeutronSelf);
		double tEmission = tNeutronOther + Math.max(aReactor.getNeutronCount(aSlot)-tNeutronSelf, 0) * tNeutronFactor;
		return UT.Code.bindInt((long)Math.ceil(tEmission));
	}
	
	@Override
	// Gets called every Tick.
	public boolean performReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.addHeat(aReactor.getNeutronCount(aSlot));
		int tNeutronMax = getReactorRodNeutronMaximum(aReactor, aSlot, aStack);

		ReactorCoolant coolant = ReactorCoolant.getCoolant(aReactor.inputTank.getFluid());
		if (coolant != null && coolant.mModerates) {
			mModerated = oModerated = T;
		}
		long tDurabilityLoss = aReactor.getNeutronCount(aSlot) <= tNeutronMax ? 100 : UT.Code.divup(400L * aReactor.getNeutronCount(aSlot), tNeutronMax);
		if (oModerated) tDurabilityLoss *= 4;
		mDurability = tDurabilityLoss > mDurability ? -1 : mDurability - tDurabilityLoss;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));

		if (mDurability <= 0) {
			depleteRod(aReactor, aStack);
		}
		return T;
	}

	public void depleteRod(MultiTileEntityReactorCore aReactor, ItemStack aStack) {
		ST.meta(aStack, mDepleted);
		ST.nbt(aStack, null);
		aReactor.updateClientData();
	}
	
	@Override
	// Gets called every 20 Ticks.
	public int emitToAndReflectFrom(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		if (aModerated) {
			mModerated = T;
			UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		}
		aReactor.addNeutrons(aSlot, aNeutrons);
		return 0;
	}

	@Override
	public int getReactorRodNeutronMaximum(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		ReactorCoolant coolant = ReactorCoolant.getCoolant(aReactor.inputTank.getFluid());
		if (coolant == null) {
			return mNeutronMax;
		} else {
			return coolant.getMax(mNeutronMax);
		}
	}

	@Override
	public boolean isModerated(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		return oModerated;
	}

	@Override
	public void updateModeration(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		oModerated = mModerated;
		mModerated = F;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
	}

	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, T), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, T), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.nuclear";}
}
