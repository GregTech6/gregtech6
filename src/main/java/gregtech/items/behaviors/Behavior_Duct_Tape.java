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

package gregtech.items.behaviors;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Duct_Tape extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses, mQuality;
	
	public Behavior_Duct_Tape(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aQuality, long aUses) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses;
		mQuality = aQuality;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aStack.stackSize != 1 || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		
		boolean rOutput = F;
		
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		long tUses = tNBT.getLong("gt.remaining");
		
		if (ST.equal(aStack, mFull, T)) {
			aStack.func_150996_a(mUsed.getItem());
			ST.meta_(aStack, ST.meta_(mUsed));
			tUses = mUses;
		}
		if (ST.equal(aStack, mUsed, T)) {
			long tUsed = tape(aWorld, aX, aY, aZ, aSide, UT.Entities.hasInfiniteItems(aPlayer)?mUses:tUses, aPlayer, aStack, aHitX, aHitY, aHitZ);
			if (tUsed > 0) {
				UT.Sounds.send(SFX.MC_DIG_CLOTH, aWorld, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses -= tUsed;
				rOutput = T;
			}
		}
		tNBT.removeTag("gt.remaining");
		if (tUses > 0) UT.NBT.setNumber(tNBT, "gt.remaining", tUses);
		UT.NBT.set(aStack, tNBT);
		
		if (tUses <= 0) {
			if (mEmpty == null) {
				aStack.stackSize--;
			} else {
				aStack.func_150996_a(mEmpty.getItem());
				ST.meta_(aStack, ST.meta_(mEmpty));
			}
		}
		return rOutput;
	}
	
	public long tape(World aWorld, int aX, int aY, int aZ, byte aSide, long aUses, EntityPlayer aPlayer, ItemStack aStack, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer == null || SIDES_INVALID[aSide] || aPlayer instanceof FakePlayer || !WD.obstructed(aWorld, aX, aY, aZ, aSide)) {
			List<String> tChatReturn = new ArrayListNoNulls<>();
			long tDamage = IBlockToolable.Util.onToolClick(TOOL_ducttape, aUses, mQuality, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer!=null&&aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
			UT.Entities.sendchat(aPlayer, tChatReturn, F);
			return tDamage;
		}
		return 0;
	}
	
	static {
		LH.add("gt.behaviour.ducttape.tooltip", "Can fix anything!*");
		LH.add("gt.behaviour.ducttape.uses", "Remaining Uses:");
		LH.add("gt.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.ducttape.tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt.remaining"));
		aList.add(LH.get("gt.behaviour.ducttape.uses") + " " + UT.Code.makeString(tRemaining));
		aList.add(LH.get("gt.behaviour.unstackable"));
		return aList;
	}
}
