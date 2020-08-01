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

package gregtech.blocks;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockVanillaOresA extends BlockBaseMeta {
	public static byte[] HARVEST_LEVELS = {0, 0, 2, 1, 1, 1, 1, 2, 1, 2, 2, 2, 0, 3, 1, 0};
	public static int[] BURN_LEVELS = {100, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0};
	public static float[] HARDNESS_LEVELS = {0.5F, 0.5F, 1.5F, 1.0F, 1.0F, 1.0F, 1.0F, 1.5F, 1.0F, 1.5F, 1.5F, 1.5F, 0.5F, 2.0F, 1.0F, 0.5F};
	
	// Vanilla Style GT6 Stone Ores, mainly for Twilight Forest.
	public BlockVanillaOresA(String aUnlocalised) {
		super(null, aUnlocalised, Material.rock, soundTypeStone, 16, Textures.BlockIcons.VANILLA_ORES_A);
		LH.add(getUnlocalizedName()+  ".0.name", "Sulfur Ore");
		LH.add(getUnlocalizedName()+  ".1.name", "Apatite Ore");
		LH.add(getUnlocalizedName()+  ".2.name", "Ruby Ore");
		LH.add(getUnlocalizedName()+  ".3.name", "Amber Ore");
		LH.add(getUnlocalizedName()+  ".4.name", "Galena Ore");
		LH.add(getUnlocalizedName()+  ".5.name", "Tetrahedrite Ore");
		LH.add(getUnlocalizedName()+  ".6.name", "Cassiterite Ore");
		LH.add(getUnlocalizedName()+  ".7.name", "Sheldonite Ore");
		LH.add(getUnlocalizedName()+  ".8.name", "Pentlandite Ore");
		LH.add(getUnlocalizedName()+  ".9.name", "Scheelite Ore");
		LH.add(getUnlocalizedName()+ ".10.name", "Rutile Ore");
		LH.add(getUnlocalizedName()+ ".11.name", "Bastnasite Ore");
		LH.add(getUnlocalizedName()+ ".12.name", "Graphite Ore");
		LH.add(getUnlocalizedName()+ ".13.name", "Pitchblende Ore");
		LH.add(getUnlocalizedName()+ ".14.name", "Fluorite Ore");
		LH.add(getUnlocalizedName()+ ".15.name", "Borax Ore");
		
		OM.reg(ST.make(this, 1, 0), OP.oreVanillastone.dat(MT.S));
		OM.reg(ST.make(this, 1, 1), OP.oreVanillastone.dat(MT.Apatite));
		OM.reg(ST.make(this, 1, 2), OP.oreVanillastone.dat(MT.Ruby));
		OM.reg(ST.make(this, 1, 3), OP.oreVanillastone.dat(MT.Amber));
		OM.reg(ST.make(this, 1, 4), OP.oreVanillastone.dat(MT.OREMATS.Galena));
		OM.reg(ST.make(this, 1, 5), OP.oreVanillastone.dat(MT.OREMATS.Tetrahedrite));
		OM.reg(ST.make(this, 1, 6), OP.oreVanillastone.dat(MT.OREMATS.Cassiterite));
		OM.reg(ST.make(this, 1, 7), OP.oreVanillastone.dat(MT.OREMATS.Cooperite));
		OM.reg(ST.make(this, 1, 8), OP.oreVanillastone.dat(MT.OREMATS.Pentlandite));
		OM.reg(ST.make(this, 1, 9), OP.oreVanillastone.dat(MT.OREMATS.Scheelite));
		OM.reg(ST.make(this, 1,10), OP.oreVanillastone.dat(MT.TiO2));
		OM.reg(ST.make(this, 1,11), OP.oreVanillastone.dat(MT.OREMATS.Bastnasite));
		OM.reg(ST.make(this, 1,12), OP.oreVanillastone.dat(MT.Graphite));
		OM.reg(ST.make(this, 1,13), OP.oreVanillastone.dat(MT.OREMATS.Pitchblende));
		OM.reg(ST.make(this, 1,14), OP.oreVanillastone.dat(MT.CaF2));
		OM.reg(ST.make(this, 1,15), OP.oreVanillastone.dat(MT.OREMATS.Borax));
	}
	
	@Override public boolean useGravity(byte aMeta) {return F;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return HARVEST_LEVELS[aMeta];}
	@Override public int getFlammability(byte aMeta) {return BURN_LEVELS[aMeta];}
	@Override public int getFireSpreadSpeed(byte aMeta) {return BURN_LEVELS[aMeta];}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * HARDNESS_LEVELS[WD.meta(aWorld, aX, aY, aZ)];}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.stone.getExplosionResistance(null);}
}
