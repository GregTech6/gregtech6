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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.GT6_Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class Behavior_FlintAndTinder extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer != null && SIDES_VALID[aSide] && !(aPlayer instanceof FakePlayer) && WD.obstructed(aWorld, aX, aY, aZ, aSide)) return F;
		List<String> tChatReturn = new ArrayListNoNulls<>();
		long tDamage = 5000;
		if (MultiItemTool.getPrimaryMaterial(aStack).mToolDurability <= 1) {
			tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer != null && aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		} else if (MultiItemTool.getPrimaryMaterial(aStack).containsAny(TD.Properties.FLAMMABLE, TD.Properties.BURNING) && RNGSUS.nextInt(100) < UT.Code.bind(1, 100, GT6_Main.gt_proxy.mFlintChance+(100-GT6_Main.gt_proxy.mFlintChance)/2)) {
			tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer != null && aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		} else if (RNGSUS.nextInt(100) < UT.Code.bind(1, 100, GT6_Main.gt_proxy.mFlintChance)) {
			tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer != null && aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		}
		UT.Entities.sendchat(aPlayer, tChatReturn, F);
		if (aWorld.isRemote) return F;
		((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(Math.max(10000, tDamage), 10000, 100, T), aPlayer, F);
		UT.Sounds.send(aWorld, SFX.MC_IGNITE, 1.0F, 1.0F, aX, aY, aZ);
		return T;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		return T;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer.worldObj.isRemote) return F;
		if (aEntity instanceof EntityCreeper) {
			((MultiItemTool)aItem).doDamage(aStack, 100, aPlayer, F);
			UT.Sounds.send(aPlayer.worldObj, SFX.MC_IGNITE, 1.0F, 1.0F, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
			((EntityCreeper)aEntity).func_146079_cb();
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.flintandtinder", "Ignites a Fire with a Chance of ");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (MultiItemTool.getPrimaryMaterial(aStack).mToolDurability <= 1) {
			aList.add(LH.get("gt.behaviour.flintandtinder") + "100%");
		} else if (MultiItemTool.getPrimaryMaterial(aStack).containsAny(TD.Properties.FLAMMABLE, TD.Properties.BURNING)) {
			aList.add(LH.get("gt.behaviour.flintandtinder") + UT.Code.bind(1, 100, GT6_Main.gt_proxy.mFlintChance+(100-GT6_Main.gt_proxy.mFlintChance)/2) + "%");
		} else {
			aList.add(LH.get("gt.behaviour.flintandtinder") + UT.Code.bind(1, 100, GT6_Main.gt_proxy.mFlintChance) + "%");
		}
		return aList;
	}
}
