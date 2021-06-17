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

package gregtech.worldgen.nether;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenRacks extends WorldgenObject {
	@SafeVarargs
	public WorldgenRacks(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextBoolean() || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return F;
		for (int i = 0; i < 16; i++) {
			int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
			for (int tY = aRandom.nextInt(WD.bedrock(aWorld, tX, 255, tZ) ? 200 : 80)+47, tH = Math.max(0, tY-40); tY > tH; tY--) {
				Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
				if (tContact.getMaterial().isLiquid() || tContact == Blocks.farmland) break;
				if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) continue;
				if (tContact.getMaterial() != Material.grass && tContact.getMaterial() != Material.ground && tContact.getMaterial() != Material.sand && tContact.getMaterial() != Material.rock) continue;
				if (WD.easyRep(aWorld, tX, tY+1, tZ)) {
					switch(aRandom.nextInt(24)) {
					case  0: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.gem.mat(MT.NetherQuartz, 1)), F, T); break;
					case  1: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.gem.mat(MT.Glowstone   , 1)), F, T); break;
					case  2: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, (aRandom.nextInt(4)==0?OP.oreRaw:OP.rockGt).mat(MT.AncientDebris, 1)), F, T); break;
					case  3: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.nether_brick ? (aRandom.nextInt(4)==0?OP.oreRaw:OP.rockGt).mat(MT.AncientDebris, 1) : OP.gem   .mat(MT.NetherQuartz, 1)), F, T); break;
					case  4: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.nether_brick ? (aRandom.nextInt(4)==0?OP.oreRaw:OP.rockGt).mat(MT.AncientDebris, 1) : OP.gem   .mat(MT.Glowstone   , 1)), F, T); break;
					case  5: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.nether_brick ? (aRandom.nextInt(4)==0?OP.oreRaw:OP.rockGt).mat(MT.AncientDebris, 1) : OP.rockGt.mat(MT.Obsidian    , 1)), F, T); break;
					case  6: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.Gloomstone  , 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case  7: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.Gloomstone  , 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case  8: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.NetherQuartz, 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case  9: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.NetherQuartz, 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case 10: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.NetherQuartz, 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case 11: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.soul_sand || IL.NeLi_SoulSoil.equal(tContact) || IL.NePl_SoulSoil.equal(tContact) ? OP.gem.mat(MT.NetherQuartz, 1) : ST.make(Items.flint, 1, 0)), F, T); break;
					case 12: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(MT.Obsidian     , 1)), F, T); break;
					case 13: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(MT.STONES.Basalt, 1)), F, T); break;
					case 14: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(MT.STONES.Basalt, 1)), F, T); break;
					case 15: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(MT.STONES.Basalt, 1)), F, T); break;
					case 16: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 17: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 18: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 19: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 20: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 21: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 22: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					case 23: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, tContact == Blocks.gravel || IL.NeLi_Gravel.equal(tContact) ? ST.make(Items.flint, 1, 0) : OP.rockGt.mat(MT.STONES.Blackstone, 1)), F, T); break;
					default: tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, null, F, T); break;
					}
				}
				break;
			}
		}
		return T;
	}
}
