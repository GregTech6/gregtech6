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

package gregtech.blocks.stone;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import gregapi.worldgen.StoneLayer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockRockOres extends BlockBaseMeta {
	public static byte[] HARVEST_LEVELS = {0, 0, 1, 1, 2, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	public static int[] BURN_LEVELS = {100, 100, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static float[] HARDNESS_LEVELS = {0.5F, 0.5F, 1.0F, 1.0F, 2.0F, 0.5F, 0.5F, 1.0F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F};
	
	public BlockRockOres(String aUnlocalised) {
		super(null, aUnlocalised, Material.rock, soundTypeStone, 8, Textures.BlockIcons.ROCK_ORES);
		LH.add(getUnlocalizedName()+ ".0.name", "Anthracite Coal");
		LH.add(getUnlocalizedName()+ ".1.name", "Lignite Coal");
		LH.add(getUnlocalizedName()+ ".2.name", "Salt");
		LH.add(getUnlocalizedName()+ ".3.name", "Rock Salt");
		LH.add(getUnlocalizedName()+ ".4.name", "Bauxite");
		LH.add(getUnlocalizedName()+ ".5.name", "Oil Shale");
		LH.add(getUnlocalizedName()+ ".6.name", "Gypsum");
		LH.add(getUnlocalizedName()+ ".7.name", "Milky Quartz");
		
		OM.reg(ST.make(this, 1, 0), OP.oreDense.dat(MT.Coal));
		OM.reg(ST.make(this, 1, 1), OP.oreDense.dat(MT.Lignite));
		OM.reg(ST.make(this, 1, 2), OP.oreDense.dat(MT.NaCl));
		OM.reg(ST.make(this, 1, 3), OP.oreDense.dat(MT.KCl));
		OM.reg(ST.make(this, 1, 4), OP.oreDense.dat(MT.OREMATS.Bauxite));
		OM.reg(ST.make(this, 1, 5), OP.oreDense.dat(MT.Oilshale));
		OM.reg(ST.make(this, 1, 6), OP.oreDense.dat(MT.OREMATS.Gypsum));
		OM.reg(ST.make(this, 1, 7), OP.oreDense.dat(MT.MilkyQuartz));
		
		if (COMPAT_IC2 != null) {
		COMPAT_IC2.valuable(this,  0, 1);
		COMPAT_IC2.valuable(this,  1, 1);
		COMPAT_IC2.valuable(this,  2, 1);
		COMPAT_IC2.valuable(this,  3, 1);
		COMPAT_IC2.valuable(this,  4, 2);
		COMPAT_IC2.valuable(this,  5, 2);
		COMPAT_IC2.valuable(this,  6, 1);
		COMPAT_IC2.valuable(this,  7, 2);
		COMPAT_IC2.valuable(this,  8, 0);
		COMPAT_IC2.valuable(this,  9, 0);
		COMPAT_IC2.valuable(this, 10, 0);
		COMPAT_IC2.valuable(this, 11, 0);
		COMPAT_IC2.valuable(this, 12, 0);
		COMPAT_IC2.valuable(this, 13, 0);
		COMPAT_IC2.valuable(this, 14, 0);
		COMPAT_IC2.valuable(this, 15, 0);
		}
		
		StoneLayer.LAYERS.add(new StoneLayer(this, 0, MT.Coal));
		StoneLayer.LAYERS.add(new StoneLayer(this, 1, MT.Lignite));
		StoneLayer.LAYERS.add(new StoneLayer(this, 2, MT.NaCl));
		StoneLayer.LAYERS.add(new StoneLayer(this, 3, MT.KCl));
		StoneLayer.LAYERS.add(new StoneLayer(this, 4, MT.OREMATS.Bauxite));
		StoneLayer.LAYERS.add(new StoneLayer(this, 5, MT.Oilshale));
		StoneLayer.LAYERS.add(new StoneLayer(this, 6, MT.OREMATS.Gypsum));
		StoneLayer.LAYERS.add(new StoneLayer(this, 7, MT.MilkyQuartz));
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
