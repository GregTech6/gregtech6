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

package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import gregapi.block.IPrefixBlock;
import gregapi.block.metatype.BlockStones;
import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.material.Material;

public class GT_Tool_JackHammer_HV_No_Ores extends GT_Tool_JackHammer_HV {
	public GT_Tool_JackHammer_HV_No_Ores(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (aBlock instanceof BlockSilverfish || BlocksGT.harvestableJackhammer.contains(aBlock)) return T;
		if (aBlock instanceof BlockStones) return aMetaData < 3;
		if (aBlock instanceof IPrefixBlock) return F;
		return WD.stone(aBlock, aMetaData) && aBlock.getMaterial() == Material.rock;
	}
}
