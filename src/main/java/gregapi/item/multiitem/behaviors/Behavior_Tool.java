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

package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Tool extends AbstractBehaviorDefault {
	public final String mToolName, mSoundName;
	public final long mDamage;
	public final boolean mOnItemUseReturn, mRandomPitch;
	public final float mPitch;
	
	/** Certain Sounds need a bit of pitch variation to them, so I decided to put a <tt>High Quality Video Game Rip</tt> into my Tools. */
	public static final float[] _7_GRAND_DAD_ = {1.0F, 0.8F, 1.0F, 0.9F, 0.9F, 0.8F, 1.0F, 0.9F, 0.8F, 0.8F, 0.8F, 0.9F, 0.7F, 0.8F, 0.9F, 1.0F, 0.8F, 1.0F, 0.9F, 0.9F, 0.8F, 1.0F, 0.9F, 0.8F, 0.8F, 0.8F, 0.9F, 0.7F, 0.9F, 0.7F};
	public int PITCH_INDEX = -1;
	
	public Behavior_Tool(String aToolName) {this(aToolName, null, 0, T, F, 1.0F);}
	public Behavior_Tool(String aToolName, boolean aOnItemUseReturn) {this(aToolName, null, 0, aOnItemUseReturn, F, 1.0F);}
	public Behavior_Tool(String aToolName, long aDamage, boolean aOnItemUseReturn) {this(aToolName, null, aDamage, aOnItemUseReturn, F, 1.0F);}
	public Behavior_Tool(String aToolName, String aSoundName, long aDamage, boolean aOnItemUseReturn) {this(aToolName, aSoundName, aDamage, aOnItemUseReturn, F, 1.0F);}
	public Behavior_Tool(String aToolName, String aSoundName, long aDamage, boolean aOnItemUseReturn, boolean aRandomPitch) {this(aToolName, aSoundName, aDamage, aOnItemUseReturn, aRandomPitch, 1.0F);}
	public Behavior_Tool(String aToolName, String aSoundName, long aDamage, boolean aOnItemUseReturn, boolean aRandomPitch, float aPitch) {
		mToolName = aToolName;
		mSoundName = aSoundName;
		mDamage = aDamage;
		mOnItemUseReturn = aOnItemUseReturn;
		mRandomPitch = aRandomPitch;
		mPitch = aPitch;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
//      if (aPlayer != null && SIDES_VALID[aSide] && !(aPlayer instanceof FakePlayer) && UT.Worlds.isSideObstructed(aWorld, aX, aY, aZ, aSide)) return !aWorld.isRemote;
		List<String> tChatReturn = new ArrayListNoNulls<>();
		long tDamage = IBlockToolable.Util.onToolClick(mToolName, Long.MAX_VALUE, (aItem instanceof MultiItemTool ? ((MultiItemTool)aItem).getHarvestLevel(aStack, mToolName) : 1), aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer!=null&&aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		UT.Entities.sendchat(aPlayer, tChatReturn, F);
		if (tDamage > 0) {
			if (mDamage > 0) ((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(tDamage, 10000, mDamage, T), aPlayer, F);
			if (mSoundName != null) UT.Sounds.send(aWorld, mSoundName, 1.0F, mRandomPitch ? _7_GRAND_DAD_[PITCH_INDEX = ((PITCH_INDEX + 1) % _7_GRAND_DAD_.length)] : mPitch, aX, aY, aZ);
			return !aWorld.isRemote;
		}
		return F;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return mOnItemUseReturn;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.Chat.DGRAY + LH.get(TOOL_LOCALISER_PREFIX + mToolName, "Unknown") + "   " + LH.Chat.GRAY + LH.get(TOOL_TOOLTIP_PREFIX + mToolName, ""));
		return aList;
	}
}
