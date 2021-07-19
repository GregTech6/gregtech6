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

package gregtech.blocks;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.recipes.maps.RecipeMapCrucible;
import gregapi.render.BlockTextureCopied;
import gregapi.util.OM;
import gregapi.util.ST;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockSands extends BlockBaseMeta {
	public BlockSands(String aUnlocalised) {
		super(null, aUnlocalised, Material.sand, soundTypeSand, 3, Textures.BlockIcons.SANDS);
		LH.add(getUnlocalizedName()+ ".0.name", "Black Sand");
		LH.add(getUnlocalizedName()+ ".1.name", "Basaltic Black Sand");
		LH.add(getUnlocalizedName()+ ".2.name", "Granitic Black Sand");
		
		MT.OREMATS.Magnetite          .mTextureDust = BlockTextureCopied.get(this, SIDE_TOP, 0);
		MT.OREMATS.BasalticMineralSand.mTextureDust = BlockTextureCopied.get(this, SIDE_TOP, 1);
		MT.OREMATS.GraniticMineralSand.mTextureDust = BlockTextureCopied.get(this, SIDE_TOP, 2);
		
		OM.reg(ST.make(this, 1, 0), OP.blockDust.dat(MT.OREMATS.Magnetite          ));
		OM.reg(ST.make(this, 1, 1), OP.blockDust.dat(MT.OREMATS.BasalticMineralSand));
		OM.reg(ST.make(this, 1, 2), OP.blockDust.dat(MT.OREMATS.GraniticMineralSand));
		
		for (byte i = 0; i < maxMeta(); i++) {
			((RecipeMapCrucible)RM.CrucibleSmelting).getRecipeFor(ST.make(this, 1, i));
		}
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("digger", ST.make(this, 1, W));
	}
	
	@Override public boolean useGravity(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.sand.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.sand.getExplosionResistance(null);}
}
