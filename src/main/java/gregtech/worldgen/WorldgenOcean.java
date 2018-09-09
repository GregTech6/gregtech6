package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOcean extends WorldgenObject {
	public int mHeight = 62;
	
	public WorldgenOcean(String aName, boolean aDefault, List... aLists) {
		super(aName, aDefault, aLists);
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", mHeight);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_OCEAN.contains(tName)) {temp = F; break;}
		if (temp) return F;
		final ExtendedBlockStorage[] tStorages = aChunk.getBlockStorageArray();
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
			boolean tPlacedNone = T;
			for (int tY = mHeight; tY > 0; tY--) {
				final ExtendedBlockStorage tStorage = tStorages[tY >> 4];
				if (tStorage == null) continue;
				final Block tBlock = tStorage.getBlockByExtId(tX, tY & 15, tZ);
				if (tBlock.isOpaqueCube()) break;
				if (tBlock == NB || tBlock == BlocksGT.Ocean || tBlock.isAir(aWorld, aMinX+tX, tY, aMinZ+tZ)) continue;
				if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
					tStorage.func_150818_a(tX, tY & 15, tZ, BlocksGT.Ocean);
					tStorage.setExtBlockMetadata(tX, tY & 15, tZ, 0);
					if (tPlacedNone) {
						aWorld.scheduleBlockUpdate(aMinX+tX, tY, aMinZ+tZ, BlocksGT.Ocean, 10+RNGSUS.nextInt(90));
						tPlacedNone = F;
					}
					temp = T;
				}
			}
		}
		return temp;
	}
}