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

import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.data.IL;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;

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
				UT.Entities.sendchat(aPlayer, "This may lag a few seconds");
				UT.Sounds.send(SFX.MC_HMM, aPlayer);
				if (COMPAT_TC != null && ST.use(aPlayer, T, aStack)) {
					UT.Sounds.send(SFX.MC_XP, aPlayer);
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
							for (ItemStackContainer tStack : tMat.mRegisteredItems) COMPAT_TC.scan(aPlayer, tStack.toStack());
							break;
						}
					}
					// Prevent 16 Bit Integer Overflows because some Thaumcraft UIs use short instead of int...
					COMPAT_TC.validate();
					// Unlock all Aspects for Items that match the Mods for this Behavior.
					for (List tList : ThaumcraftApi.objectTags.keySet()) if (!tList.isEmpty()) {
						ItemStack tStack = null;
						if (tList.get(0) instanceof Item) {
							tStack = ST.make_((Item) tList.get(0), 1, tList.size() > 1 && tList.get(1) instanceof Number ? ((Number)tList.get(1)).shortValue() : 0);
						} else if (tList.get(0) instanceof Block) {
							tStack = ST.make_((Block)tList.get(0), 1, tList.size() > 1 && tList.get(1) instanceof Number ? ((Number)tList.get(1)).shortValue() : 0);
						}
						if (ST.valid(tStack)) {
							String tRegName = ST.regName(tStack);
							for (ModData tMod : mModIDs) if (tMod.owns(tRegName)) {
								COMPAT_TC.scan(aPlayer, tStack);
								break;
							}
						}
					}
					// Prevent 16 Bit Integer Overflows because some Thaumcraft UIs use short instead of int...
					COMPAT_TC.validate();
					// Just in case you forgot to scan this Item first.
					COMPAT_TC.scan(aPlayer, aStack);
				}
			}
			return T;
		}
		return F;
	}
}
