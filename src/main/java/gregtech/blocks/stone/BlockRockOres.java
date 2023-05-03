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

package gregtech.blocks.stone;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import gregapi.worldgen.StoneLayer;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/** Dense Ores that typically generate in large Layers. */
public class BlockRockOres extends BlockBaseMeta {
	public static byte[] HARVEST_LEVELS = {0, 0, 1, 1, 2, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0};
	public static int[] BURN_LEVELS = {30, 30, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static float[] HARDNESS_LEVELS = {0.5F, 0.5F, 1.0F, 1.0F, 2.0F, 0.5F, 0.5F, 1.0F, 1.0F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F};
	public static OreDictMaterial[] ORE_MATERIALS = {MT.Coal, MT.Lignite, MT.NaCl, MT.KCl, MT.OREMATS.Bauxite, MT.Oilshale, MT.Gypsum, MT.MilkyQuartz, MT.NetherQuartz, MT.NULL, MT.NULL, MT.NULL, MT.NULL, MT.NULL, MT.NULL, MT.NULL};
	
	public BlockRockOres(String aUnlocalised) {
		super(null, aUnlocalised, Material.rock, soundTypeStone, 9, Textures.BlockIcons.ROCK_ORES);
		LH.add(getUnlocalizedName()+ ".0", "Anthracite Coal");
		LH.add(getUnlocalizedName()+ ".1", "Lignite Coal");
		LH.add(getUnlocalizedName()+ ".2", "Salt");
		LH.add(getUnlocalizedName()+ ".3", "Rock Salt");
		LH.add(getUnlocalizedName()+ ".4", "Bauxite");
		LH.add(getUnlocalizedName()+ ".5", "Oil Shale");
		LH.add(getUnlocalizedName()+ ".6", "Gypsum");
		LH.add(getUnlocalizedName()+ ".7", "Milky Quartz");
		LH.add(getUnlocalizedName()+ ".8", "Nether Quartz");
		
		for (int i = 0; i < maxMeta(); i++) OM.reg(ST.make(this, 1, i), OP.oreDense.dat(ORE_MATERIALS[i]));
		
		if (COMPAT_IC2 != null) {
		COMPAT_IC2.valuable(this,  0, 1);
		COMPAT_IC2.valuable(this,  1, 1);
		COMPAT_IC2.valuable(this,  2, 1);
		COMPAT_IC2.valuable(this,  3, 1);
		COMPAT_IC2.valuable(this,  4, 2);
		COMPAT_IC2.valuable(this,  5, 2);
		COMPAT_IC2.valuable(this,  6, 1);
		COMPAT_IC2.valuable(this,  7, 2);
		COMPAT_IC2.valuable(this,  8, 2);
		COMPAT_IC2.valuable(this,  9, 0);
		COMPAT_IC2.valuable(this, 10, 0);
		COMPAT_IC2.valuable(this, 11, 0);
		COMPAT_IC2.valuable(this, 12, 0);
		COMPAT_IC2.valuable(this, 13, 0);
		COMPAT_IC2.valuable(this, 14, 0);
		COMPAT_IC2.valuable(this, 15, 0);
		}
		
		BlocksGT.drillableDynamite.add(this);
		BlocksGT.harvestableJackhammer.add(this);
		
		StoneLayer.LAYERS.add(new StoneLayer(this, 0, ORE_MATERIALS[0]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 1, ORE_MATERIALS[1]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 2, ORE_MATERIALS[2]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 3, ORE_MATERIALS[3]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 4, ORE_MATERIALS[4]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 5, ORE_MATERIALS[5]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 6, ORE_MATERIALS[6]).setNoDeep());
		StoneLayer.LAYERS.add(new StoneLayer(this, 7, ORE_MATERIALS[7]).setNoDeep());
	//  StoneLayer.LAYERS.add(new StoneLayer(this, 8, ORE_MATERIALS[7]).setNoDeep()); Nope, Nether Quartz is not for the Overworld.
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("miner", ST.make(this, 1, W));
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		return new ArrayListNoNulls<>(F, OP.oreRaw.mat(ORE_MATERIALS[aMeta], aFortune>0?2+RNGSUS.nextInt(aFortune*2+2):2));
	}
	
	@Override
	public int getExpDrop(IBlockAccess aWorld, int aMeta, int aFortune) {
		return RNGSUS.nextInt(8) == 0 ? 1 : 0;
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
