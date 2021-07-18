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

package gregtech.blocks.stone;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/** Vanilla Style GT6 Stone Ores, mainly used for Twilight Forest, so the Ore Magnet doesn't pull glitched Ores. */
public class BlockVanillaOresA extends BlockBaseMeta {
	public static byte[] HARVEST_LEVELS = {0, 0, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 0, 3, 0};
	public static int[] BURN_LEVELS = {30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0};
	public static float[] HARDNESS_LEVELS = {0.5F, 0.5F, 1.5F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.5F, 1.0F, 1.5F, 1.5F, 1.5F, 0.5F, 2.0F, 0.5F};
	public static OreDictMaterial[] ORE_MATERIALS = {MT.S, MT.Apatite, MT.Ruby, MT.Amber, MT.Amethyst, MT.OREMATS.Galena, MT.OREMATS.Tetrahedrite, MT.OREMATS.Cassiterite, MT.OREMATS.Cooperite, MT.OREMATS.Pentlandite, MT.OREMATS.Scheelite, MT.TiO2, MT.OREMATS.Bastnasite, MT.Graphite, MT.OREMATS.Pitchblende, MT.OREMATS.Borax};
	
	public BlockVanillaOresA(String aUnlocalised) {
		super(null, aUnlocalised, Material.rock, soundTypeStone, ORE_MATERIALS.length, Textures.BlockIcons.VANILLA_ORES_A);
		LH.add(getUnlocalizedName()+  ".0.name", "Sulfur Ore"      );
		LH.add(getUnlocalizedName()+  ".1.name", "Apatite Ore"     );
		LH.add(getUnlocalizedName()+  ".2.name", "Ruby Ore"        );
		LH.add(getUnlocalizedName()+  ".3.name", "Amber Ore"       );
		LH.add(getUnlocalizedName()+  ".4.name", "Amethyst Ore"    );
		LH.add(getUnlocalizedName()+  ".5.name", "Galena Ore"      );
		LH.add(getUnlocalizedName()+  ".6.name", "Tetrahedrite Ore");
		LH.add(getUnlocalizedName()+  ".7.name", "Cassiterite Ore" );
		LH.add(getUnlocalizedName()+  ".8.name", "Sheldonite Ore"  );
		LH.add(getUnlocalizedName()+  ".9.name", "Pentlandite Ore" );
		LH.add(getUnlocalizedName()+ ".10.name", "Scheelite Ore"   );
		LH.add(getUnlocalizedName()+ ".11.name", "Rutile Ore"      );
		LH.add(getUnlocalizedName()+ ".12.name", "Bastnasite Ore"  );
		LH.add(getUnlocalizedName()+ ".13.name", "Graphite Ore"    );
		LH.add(getUnlocalizedName()+ ".14.name", "Pitchblende Ore" );
		LH.add(getUnlocalizedName()+ ".15.name", "Borax Ore"       );
		
		for (int i = 0; i < maxMeta(); i++) OM.reg(ST.make(this, 1, i), OP.oreVanillastone.dat(ORE_MATERIALS[i]));
		
		if (COMPAT_IC2 != null) {
		COMPAT_IC2.valuable(this,  0, 1);
		COMPAT_IC2.valuable(this,  1, 1);
		COMPAT_IC2.valuable(this,  2, 4);
		COMPAT_IC2.valuable(this,  3, 4);
		COMPAT_IC2.valuable(this,  4, 4);
		COMPAT_IC2.valuable(this,  5, 2);
		COMPAT_IC2.valuable(this,  6, 2);
		COMPAT_IC2.valuable(this,  7, 2);
		COMPAT_IC2.valuable(this,  8, 5);
		COMPAT_IC2.valuable(this,  9, 3);
		COMPAT_IC2.valuable(this, 10, 3);
		COMPAT_IC2.valuable(this, 11, 3);
		COMPAT_IC2.valuable(this, 12, 3);
		COMPAT_IC2.valuable(this, 13, 2);
		COMPAT_IC2.valuable(this, 14, 5);
		COMPAT_IC2.valuable(this, 15, 1);
		}
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("miner", ST.make(this, 1, W));
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		return new ArrayListNoNulls<>(F, OP.oreRaw.mat(ORE_MATERIALS[aMeta], aFortune>0?1+RNGSUS.nextInt(aFortune+1):1));
	}
	
	@Override
	public int getExpDrop(IBlockAccess aWorld, int aMeta, int aFortune) {
		switch(aMeta) {
		case  0: case  1:          return 0+RNGSUS.nextInt(3);
		case  2: case  3: case  4: return 3+RNGSUS.nextInt(5);
		default:                   return 2+RNGSUS.nextInt(4);
		}
	}
	
	@Override public boolean useGravity(byte aMeta) {return F;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canSilkHarvest(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return HARVEST_LEVELS[aMeta];}
	@Override public int getFlammability(byte aMeta) {return BURN_LEVELS[aMeta];}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * HARDNESS_LEVELS[WD.meta(aWorld, aX, aY, aZ)];}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.stone.getExplosionResistance(null);}
}
