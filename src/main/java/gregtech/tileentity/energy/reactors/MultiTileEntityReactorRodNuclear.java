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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodNuclear extends MultiTileEntityReactorRodBase {
	public long mDurability = 0;
	public int mNeutronSelf = 128, mNeutronOther = 128, mNeutronDiv = 8, mNeutronOptimum = 128;
	public short mDepleted = -1;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mDurability = aNBT.getLong(aNBT.hasKey(NBT_DURABILITY) ? NBT_DURABILITY : NBT_MAXDURABILITY);
		if (aNBT.hasKey(NBT_NUCLEAR_SELF )) mNeutronSelf    = aNBT.getInteger(NBT_NUCLEAR_SELF );
		if (aNBT.hasKey(NBT_NUCLEAR_OTHER)) mNeutronOther   = aNBT.getInteger(NBT_NUCLEAR_OTHER);
		if (aNBT.hasKey(NBT_NUCLEAR_DIV  )) mNeutronDiv     = aNBT.getInteger(NBT_NUCLEAR_DIV  );
		if (aNBT.hasKey(NBT_NUCLEAR_OPTI )) mNeutronOptimum = aNBT.getInteger(NBT_NUCLEAR_OPTI);
		if (aNBT.hasKey(NBT_VALUE        )) mDepleted       = aNBT.getShort(NBT_VALUE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_DURABILITY, mDurability);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN   + "Remaining: " + LH.Chat.WHITE + mDurability          + LH.Chat.WHITE  + "00" + LH.Chat.CYAN   + " Neutrons");
		aList.add(LH.Chat.GREEN  + "Emission: "  + LH.Chat.WHITE + mNeutronOther        + LH.Chat.PURPLE + " Neutrons/t");
		// Tool tip commented out because always the same as the emission right now, so unused.
		// aList.add(LH.Chat.GREEN  + "Self: "      + LH.Chat.WHITE + mNeutronSelf         + LH.Chat.PURPLE + " Neutrons/t");
		aList.add(LH.Chat.GREEN  + "Optimum: "   + LH.Chat.WHITE + mNeutronOptimum      + LH.Chat.PURPLE + " Neutrons/t");
		aList.add(LH.Chat.YELLOW + "Factor: "    + LH.Chat.WHITE + "1/" + mNeutronDiv);
		if (mNeutronDiv <= 4) aList.add(LH.Chat.RED + "This fuel is" + LH.Chat.BLINKING_RED + " critical");
		aList.add(LH.Chat.DGRAY  + "Used in Nuclear Reactor Core");
	}
	
	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		int tNeutronDiv = FL.Coolant_IC2.is(aReactor.mTanks[0]) ? mNeutronDiv * 2 : mNeutronDiv;
		int tNeutronSelf = FL.Coolant_IC2.is(aReactor.mTanks[0]) ? mNeutronSelf * 4 : mNeutronSelf;
		int tNeutronOther = FL.Coolant_IC2.is(aReactor.mTanks[0]) ? mNeutronOther * 4 : mNeutronOther;

		aReactor.mNeutronCounts[aSlot] += tNeutronSelf;
		int tEmission = tNeutronOther + (int)UT.Code.divup(aReactor.oNeutronCounts[aSlot]-tNeutronSelf, tNeutronDiv);
		// Only called every second, so cost for 20 ticks times 5 outputs (self + 4 sides) but divided by 100 because 1 durability = 100 neutrons, so 100/100 = 1
		int tEfficiencyFactor = (int)(tEmission * Math.min(Math.abs(-(1d/(double)mNeutronOptimum) * ((double)tEmission - (double)mNeutronOptimum)) + 0.5d , 1d));
		mDurability = tEfficiencyFactor > mDurability ? -1 : mDurability - tEfficiencyFactor;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return tEmission;
	}
	
	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot];
		if (mDurability <= 0) {
			ST.meta(aStack, mDepleted);
			ST.nbt(aStack, null);
		}
		return T;
	}
	
	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}
	
	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, T), BlockTextureDefault.get(sOverlays[1], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, T), BlockTextureDefault.get(sOverlays[0], aActive ? UNCOLOURED : MT.Pb.fRGBaSolid));}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.nuclear";}
}
