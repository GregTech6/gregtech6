package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluid extends WorldgenBlob {
	public WorldgenFluid(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, List... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		Block tTargetedBlock = aWorld.getBlock(aX, aY, aZ);
		if (tTargetedBlock == mBlock && aWorld.getBlockMetadata(aX, aY, aZ) == mBlockMeta) {
			return T;
		}
		if (WD.bedrock(aWorld, aX, aY, aZ, tTargetedBlock)) {
			return aY >= 1 && aY <= 4 ? aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0) : doBedrockStuff(aWorld, aX, aY, aZ, aRandom);
		}
		if (tTargetedBlock == NB || tTargetedBlock.isAir(aWorld, aX, aY, aZ)) {
			return mAllowToGenerateinVoid && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		}
		if (tTargetedBlock == Blocks.dirt || tTargetedBlock == Blocks.soul_sand || WD.ore_stone(tTargetedBlock, (byte)aWorld.getBlockMetadata(aX, aY, aZ))) {
			return aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		}
		return F;
	}
	
	public boolean doBedrockStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		return F;
	}
}