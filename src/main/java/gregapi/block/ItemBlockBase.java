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

package gregapi.block;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.render.ITexture;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlockBase extends ItemBlock {
	public final IBlockBase mPlaceable;
	
	public ItemBlockBase(Block aBlock) {
		super(aBlock);
		mPlaceable = (IBlockBase)aBlock;
		setMaxDamage(0);
		setHasSubtypes(T);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		super.addInformation(aStack, aPlayer, aList, aF3_H);
		short aMeta = ST.meta_(aStack);
		mPlaceable.addInformation(aStack, aMeta, aPlayer, aList, aF3_H);
		if (field_150939_a.getCollisionBoundingBoxFromPool(aPlayer.worldObj, 0, 0, 0) != null) {
			if (mPlaceable.doesWalkSpeed(aMeta)) aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_WALKSPEED));
			if (mPlaceable.canCreatureSpawn(aMeta)) {
				if (ITexture.Util.OPTIFINE_LOADED && aMeta != 0 && !mPlaceable.canCreatureSpawn(0)) {
					aList.add(LH.Chat.BLINKING_RED + LH.get(Minecraft.getMinecraft().isSingleplayer() ? LH.TOOLTIP_SPAWNPROOF_SP_BUG    : LH.TOOLTIP_SPAWNPROOF_MP_BUG   ));
					aList.add(LH.Chat.BLINKING_RED + LH.get(LH.TOOLTIP_SPAWNPROOF_OPTIFINE));
				}
			} else {
				if (ITexture.Util.OPTIFINE_LOADED && aMeta != 0 &&  mPlaceable.canCreatureSpawn(0)) {
					aList.add(LH.Chat.BLINKING_RED + LH.get(Minecraft.getMinecraft().isSingleplayer() ? LH.TOOLTIP_SPAWNPROOF_SP_BROKEN : LH.TOOLTIP_SPAWNPROOF_MP_BROKEN));
					aList.add(LH.Chat.BLINKING_RED + LH.get(LH.TOOLTIP_SPAWNPROOF_OPTIFINE));
				} else {
					aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_SPAWNPROOF));
				}
			}
			if (MD.GC.mLoaded) {
				byte tCount = 0;
				for (byte tSide : ALL_SIDES_VALID) if (mPlaceable.isSealable(aMeta, tSide)) tCount++;
				if (tCount >= 6) {
					aList.add(LH.Chat.GREEN  + LH.get(LH.TOOLTIP_SEALABLE_ANY));
				} else if (field_150939_a.isOpaqueCube()) {
					aList.add(LH.Chat.ORANGE + LH.get(LH.TOOLTIP_SEALABLE_BUGGED));
				} else if (tCount > 0) {
					aList.add(LH.Chat.YELLOW + LH.get(LH.TOOLTIP_SEALABLE_SOME));
				}
			}
		}
		if (mPlaceable.useGravity(aMeta)) aList.add(LH.Chat.ORANGE + LH.get(LH.TOOLTIP_GRAVITY));
		if (mPlaceable.doesPistonPush(aMeta)) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_PISTONPUSHABLE));
		float tResistance = mPlaceable.getExplosionResistance(aMeta);
		if (tResistance > 0) aList.add(LH.getToolTipBlastResistance(field_150939_a, tResistance));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + field_150939_a.getHarvestTool(aMeta), "Unknown") + " (" + field_150939_a.getHarvestLevel(aMeta) + ")");
	}
	
	@Override public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {return mPlaceable.onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);}
	@Override public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {return mPlaceable.onItemUse(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);}
	@Override public IIcon getIconFromDamage(int aMeta) {return field_150939_a.getIcon(SIDE_TOP, aMeta);}
	@Override public String getUnlocalizedName(ItemStack aStack) {return mPlaceable.name(getDamage(aStack));}
	@Override public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ, int aMetaData) {return aWorld.setBlock(aX, aY, aZ, field_150939_a, aMetaData, 3);}
	@Override public int getItemStackLimit(ItemStack aStack) {return mPlaceable.getItemStackLimit(aStack);}
	@Override public int getMetadata(int aMeta) {return aMeta;}
	@Override public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {return mPlaceable.onItemRightClick(aStack, aWorld, aPlayer);}
}
