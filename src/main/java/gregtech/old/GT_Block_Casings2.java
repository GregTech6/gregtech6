/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.old;

import gregapi.block.MaterialMachines;
import gregapi.data.LH;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class GT_Block_Casings2 extends GT_Block_Casings_Abstract {
	public GT_Block_Casings2() {
		super("gt.blockcasings2", MaterialMachines.instance);
//      for (byte i = 0; i < 16; i++) Textures.BlockIcons.CASING_BLOCKS[i+16] = new BlockTextureCopied(this, 6, i);
		LH.add(getUnlocalizedName()+ ".0.name", "Solid Steel Machine Casing");
		LH.add(getUnlocalizedName()+ ".1.name", "Frost Proof Machine Casing");
		LH.add(getUnlocalizedName()+ ".2.name", "Bronze Gear Box Casing");
		LH.add(getUnlocalizedName()+ ".3.name", "Steel Gear Box Casing");
		LH.add(getUnlocalizedName()+ ".4.name", "Titanium Gear Box Casing");
		LH.add(getUnlocalizedName()+ ".5.name", "Tungstensteel Gear Box Casing");
		LH.add(getUnlocalizedName()+ ".6.name", "Processor Machine Casing");
		LH.add(getUnlocalizedName()+ ".7.name", "Data Drive Machine Casing");
		LH.add(getUnlocalizedName()+ ".8.name", "Containment Field Machine Casing");
		LH.add(getUnlocalizedName()+ ".9.name", "Assembler Machine Casing");
		LH.add(getUnlocalizedName()+".10.name", "Pump Machine Casing");
		LH.add(getUnlocalizedName()+".11.name", "Motor Machine Casing");
		LH.add(getUnlocalizedName()+".12.name", "Bronze Pipe Machine Casing");
		LH.add(getUnlocalizedName()+".13.name", "Steel Pipe Machine Casing");
		LH.add(getUnlocalizedName()+".14.name", "Titanium Pipe Machine Casing");
		LH.add(getUnlocalizedName()+".15.name", "Tungstensteel Pipe Machine Casing");
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMeta) {/*
		switch(aMeta) {
		case  0: return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL.getIcon(0);
		case  1: return Textures.BlockIcons.MACHINE_CASING_FROST_PROOF.getIcon(0);
		case  2: return Textures.BlockIcons.MACHINE_CASING_GEARBOX_BRONZE.getIcon(0);
		case  3: return Textures.BlockIcons.MACHINE_CASING_GEARBOX_STEEL.getIcon(0);
		case  4: return Textures.BlockIcons.MACHINE_CASING_GEARBOX_TITANIUM.getIcon(0);
		case  5: return Textures.BlockIcons.MACHINE_CASING_GEARBOX_TUNGSTENSTEEL.getIcon(0);
		case  6: return Textures.BlockIcons.MACHINE_CASING_PROCESSOR.getIcon(0);
		case  7: return Textures.BlockIcons.MACHINE_CASING_DATA_DRIVE.getIcon(0);
		case  8: return Textures.BlockIcons.MACHINE_CASING_CONTAINMENT_FIELD.getIcon(0);
		case  9: return Textures.BlockIcons.MACHINE_CASING_ASSEMBLER.getIcon(0);
		case 10: return Textures.BlockIcons.MACHINE_CASING_PUMP.getIcon(0);
		case 11: return Textures.BlockIcons.MACHINE_CASING_MOTOR.getIcon(0);
		case 12: return Textures.BlockIcons.MACHINE_CASING_PIPE_BRONZE.getIcon(0);
		case 13: return Textures.BlockIcons.MACHINE_CASING_PIPE_STEEL.getIcon(0);
		case 14: return Textures.BlockIcons.MACHINE_CASING_PIPE_TITANIUM.getIcon(0);
		case 15: return Textures.BlockIcons.MACHINE_CASING_PIPE_TUNGSTENSTEEL.getIcon(0);
		}*/
		return null;
	}
	
	@Override
	public float getExplosionResistance(Entity aTNT, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {
		return aWorld.getBlockMetadata(aX, aY, aZ) == 8 ? Blocks.bedrock.getExplosionResistance(aTNT) : super.getExplosionResistance(aTNT, aWorld, aX, aY, aZ, eX, eY, eZ);
	}
}
