/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregtech.blocks.tool;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.misc.BlockBaseMachineUpdate;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLongDistWire extends BlockBaseMachineUpdate {
	public final byte[] mTiers;
	
	public BlockLongDistWire(String aUnlocalised, IIconContainer[] aIcons, byte[] aTiers) {
		super(null, aUnlocalised, Material.iron, soundTypeCloth, 16, aIcons, ~0);
		mTiers = aTiers;
		for (byte i = 0; i < 16; i++) LH.add(aUnlocalised+"."+i, "Long Distance Electric Wire ("+VN[mTiers[i]]+")");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_VOLTAGE) + (VMAX[mTiers[aMeta]]) + " " + TD.Energy.EU.getLocalisedNameShort() + " (" + VN[mTiers[aMeta]] + ")");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_AMPERAGE) + "UNLIMITED");
		aList.add(Chat.CYAN + LH.get(LH.WIRE_STATS_LOSS) + "0.125 " + TD.Energy.EU.getLocalisedNameShort() + "/m");
	}
	
	@Override public int getFlammability(byte aMeta) {return 150;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 150;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_cutter;}
	@Override public int getHarvestLevel(int aMeta) {return 3;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return T;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 15;}
	@Override public float getExplosionResistance(Entity aEntity) {return 15;}
	@Override public float getExplosionResistance(byte aMeta) {return 15;}
}
