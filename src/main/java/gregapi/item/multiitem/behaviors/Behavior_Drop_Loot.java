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

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Drop_Loot extends AbstractBehaviorDefault {
	public String[] mLoots;
	
	public Behavior_Drop_Loot(String... aLoots) {
		mLoots = aLoots;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add("Rightclick this on a Block to loot");
		return aList;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!aWorld.isRemote && ST.use(aPlayer, T, aStack)) {
			for (String tLoot : mLoots) ST.drop(aWorld, aX+OFFX[aSide]+0.5, aY+OFFY[aSide]+0.5, aZ+OFFZ[aSide]+0.5, ChestGenHooks.getOneItem(tLoot, RNGSUS));
			if (aPlayer != null) UT.Sounds.send(SFX.MC_DIG_CLOTH, aPlayer);
			return T;
		}
		return F;
	}
}
