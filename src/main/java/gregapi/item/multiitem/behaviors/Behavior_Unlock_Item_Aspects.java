/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.item.multiitem.behaviors;

import cpw.mods.fml.common.registry.GameData;
import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.data.IL;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Unlock_Item_Aspects extends AbstractBehaviorDefault {
	public ModData[] mModIDs;
	
	public Behavior_Unlock_Item_Aspects(ModData... aModIDs) {
		mModIDs = aModIDs;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add("Rightclick this on a Block to learn Item Aspects");
		return aList;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!aWorld.isRemote) {
			if (aPlayer != null) {
				UT.Sounds.send(SFX.MC_HMM, aPlayer);
				if (COMPAT_TC != null) {
					boolean tScannedAnything = F;
					// Notify the GT Log File that someone started using this Item. Just in case someone abuses this to Lag a Server.
					OUT.println(aPlayer.getCommandSenderName() + " has used the Item '" + ST.make(aStack, (NBTTagCompound)null).getDisplayName() + "', which may or may not be lagging for a few minutes");
					// Tell the User that this is gonna Lag.
					UT.Entities.sendchat(aPlayer, "Unlocking this many Aspects will lag for a few minutes, if done for the first time");
					// Make sure all Aspects are discovered first.
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_0.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_1.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_2.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_3.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_4.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_5.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_6.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_7.get(1));
					COMPAT_TC.scan(aPlayer, IL.Paper_Magic_Research_8.get(1));
					// Prevent 16 Bit Integer Overflows because some Thaumcraft UIs use short instead of int...
					COMPAT_TC.validate();
					// Unlock all Aspects for Materials that match the Mods for this Behavior.
					for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_ARRAY) if (tMat != null) {
						for (ModData tMod : mModIDs) if (tMod == tMat.mOriginalMod) {
							for (ItemStackContainer tStack : tMat.mRegisteredItems) tScannedAnything |= COMPAT_TC.scan(aPlayer, tStack.toStack());
							break;
						}
					}
					// Prevent 16 Bit Integer Overflows because some Thaumcraft UIs use short instead of int...
					COMPAT_TC.validate();
					// Unlock all Aspects for Items that match the Mods for this Behavior.
					Iterator<Item> tIterator = GameData.getItemRegistry().iterator();
					while (tIterator.hasNext()) {
						ItemStack tStack = ST.make(tIterator.next(), 1, W);
						if (ST.valid(tStack)) {
							String tRegName = ST.regName(tStack);
							for (ModData tMod : mModIDs) if (tMod.owns(tRegName)) {
								tScannedAnything |= COMPAT_TC.scan(aPlayer, tStack);
								break;
							}
						}
					}
					// Prevent 16 Bit Integer Overflows because some Thaumcraft UIs use short instead of int...
					COMPAT_TC.validate();
					// Just in case you forgot to scan this Item first.
					COMPAT_TC.scan(aPlayer, aStack);
					// Send a Sound to indicate it is over.
					if (tScannedAnything) UT.Sounds.send(SFX.MC_XP, aPlayer);
					// Notify the GT Log File that someone used this Item. Just in case someone abuses this to Lag a Server.
					OUT.println(aPlayer.getCommandSenderName() + " is done using the Item '" + ST.make(aStack, (NBTTagCompound)null).getDisplayName() + "', the related Lag which may or may not have happened, is definitely over now");
				}
			}
			return T;
		}
		return F;
	}
}
