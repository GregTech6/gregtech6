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

package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

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

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluidSpring extends WorldgenObject {
	public final int mMeta, mProbability;
	public final FluidStack mSpringFluid;
	public final Block mBlock;
	
	@SafeVarargs
	public WorldgenFluidSpring(String aName, boolean aDefault, Block aBlock, int aMeta, int aProbability, FluidStack aSpringFluid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mSpringFluid = aSpringFluid;
		mBlock       = ST.valid(aBlock)?aBlock:Blocks.water;
		mMeta        = UT.Code.bind4(aMeta);
		mProbability = getConfigFile().get(mCategory, "Probability", aProbability);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (!WorldgenOresBedrock.GENERATED_NO_BEDROCK_ORE || !WorldgenOresBedrock.CAN_GENERATE_BEDROCK_ORE || aRandom.nextInt(mProbability) != 0) return F;
		if (GENERATING_SPECIAL) return F;
		WorldgenOresBedrock.CAN_GENERATE_BEDROCK_ORE = F;
		
		Block tDeepslate = (aDimType == DIM_NETHER ? Blocks.netherrack : IL.EtFu_Deepslate.block());
		if (ST.invalid(tDeepslate)) tDeepslate = Blocks.stone;
		
		for (int i = 0; i <= 6; i++) for (int tX = aMinX+i; tX <= aMaxX-i; tX++) for (int tZ = aMinZ+i; tZ <= aMaxZ-i; tZ++) {
			if (!WD.opq(aWorld, tX, i+1, tZ, F, T)) aWorld.setBlock(tX, i+1, tZ, tDeepslate, 0, 0);
			
			if (i > 0) aWorld.setBlock(tX, i, tZ, mBlock, mMeta, 0);
			
			if (mSpringFluid != null && i > 2 && aRandom.nextInt(16) == 0 && WD.bedrock(aWorld, tX, 0, tZ)) {
				MultiTileEntityFluidSpring.setBlock(aWorld, tX, 0, tZ, mSpringFluid);
			}
		}
		
		return T;
	}
}
