package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenGlowtus extends WorldgenObject {
	public WorldgenGlowtus(String aName, boolean aDefault, List... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(2) != 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_JUNGLE.contains(tName)) {temp = F; break;}
		if (temp) return F;
		for (int i = 0; i < 16; i++) {
			int tX = aRandom.nextInt(16), tZ = aRandom.nextInt(16);
			for (int tY = 70; tY > 0; tY--) if (aChunk.getBlock(tX, tY, tZ).getMaterial() == Material.water) {
				WD.set(aChunk, tX, tY+1, tZ, BlocksGT.Glowtus, i);
				break;
			}
		}
		return T;
	}
}