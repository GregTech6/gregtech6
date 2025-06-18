/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregtech.worldgen;

import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOresBedrock;
import gregtech.tileentity.misc.MultiTileEntityFluidSpring;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluidSpring extends WorldgenObject {
	public final int mMeta, mProbability, mIndicatorType;
	public final FluidStack mSpringFluid;
	public final Block mBlock;
	
	@SafeVarargs public WorldgenFluidSpring(String aName, boolean aDefault, Block aBlock, int aMeta, int aProbability, FluidStack aSpringFluid, List<WorldgenObject>... aLists) {this(aName, aDefault, aBlock, aMeta, aProbability, 0, aSpringFluid, aLists);}
	@SafeVarargs public WorldgenFluidSpring(String aName, boolean aDefault, Block aBlock, int aMeta, int aProbability, int aIndicatorType, FluidStack aSpringFluid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mSpringFluid   = aSpringFluid;
		mBlock         = ST.valid(aBlock)?aBlock:Blocks.water;
		mMeta          = UT.Code.bind4(aMeta);
		mIndicatorType = aIndicatorType;
		mProbability   = getConfigFile().get(mCategory, "Probability", aProbability);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (GENERATING_SPECIAL || !WorldgenOresBedrock.GENERATED_NO_BEDROCK_ORE || !WorldgenOresBedrock.CAN_GENERATE_BEDROCK_ORE || aRandom.nextInt(mProbability) != 0) return F;
		
		WorldgenOresBedrock.CAN_GENERATE_BEDROCK_ORE = F;
		
		Block tBlock = WD.block(aWorld, aMinX+8, 0, aMinZ+8);
		if (tBlock != BlocksGT.oreBedrock && tBlock != BlocksGT.oreSmallBedrock && !WD.bedrock(tBlock)) return F;
		
		tBlock = (aDimType == DIM_NETHER ? Blocks.netherrack : IL.EtFu_Deepslate.block());
		if (ST.invalid(tBlock)) tBlock = Blocks.stone;
		
		for (int i = 0; i <= 6; i++) for (int tX = aMinX+i; tX <= aMaxX-i; tX++) for (int tZ = aMinZ+i; tZ <= aMaxZ-i; tZ++) {
			if (!WD.opq(aWorld, tX, i+1, tZ, F, T)) aWorld.setBlock(tX, i+1, tZ, tBlock, 0, 0);
			
			if (i > 0) aWorld.setBlock(tX, i, tZ, mBlock, mMeta, 0);
			
			if (mSpringFluid != null && i > 2 && aRandom.nextInt(16) == 0 && WD.bedrock(aWorld, tX, 0, tZ)) {
				MultiTileEntityFluidSpring.setBlock(aWorld, tX, 0, tZ, mSpringFluid);
			}
		}
		
		switch (mIndicatorType) {
		// Yellow or Brown Grass.
		case  1: case  2: case  3:
			int tMinHeight = Math.min(aWorld.getHeight()-2, WD.waterLevel(aWorld)-1)
			,   tMaxHeight = Math.min(aWorld.getHeight()-1, tMinHeight * 2 + 16);
			for (int i = 0; i < 6; i++) {
				int tX = aMinX+4+aRandom.nextInt(8), tZ = aMinZ+4+aRandom.nextInt(8);
				for (int tY = tMaxHeight; tY > tMinHeight; tY--) {
					Block tContact = aWorld.getBlock(tX, tY, tZ);
					if (tContact.getMaterial().isLiquid() || tContact == Blocks.farmland) break;
					if (!tContact.isOpaqueCube() || tContact.isWood(aWorld, tX, tY, tZ) || tContact.isLeaves(aWorld, tX, tY, tZ)) continue;
					if (!BlocksGT.plantableGrass.contains(tContact)) break;
					for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++) if (aRandom.nextBoolean()) if (BlocksGT.plantableGrass.contains(aWorld.getBlock(tX+a, tY, tZ+b))) {
						WD.set(aWorld, tX+a, tY, tZ+b, BlocksGT.Grass, mIndicatorType == 3 ? 0 : 3+mIndicatorType, 0);
					}
					break;
				}
			}
			break;
		default:
			break;
		}
		
		return T;
	}
}
