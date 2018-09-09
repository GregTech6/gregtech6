package gregtech.worldgen.erebus;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenErebusRocks extends WorldgenObject {
	public WorldgenErebusRocks(String aName, boolean aDefault, List... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return F;
		for (int i = 0, j = 1+aRandom.nextInt(2); i < j; i++) {
			int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
			for (int tY = 60+aRandom.nextInt(40); tY > 0; tY--) {
				Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
				if (tContact.getMaterial().isLiquid()) break;
				if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) continue;
				if (tContact.getMaterial() != Material.grass && tContact.getMaterial() != Material.ground && tContact.getMaterial() != Material.sand) continue;
				if (WD.easyRep(aWorld, tX, tY+1, tZ)) tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32757, aRandom.nextInt(3)!=0?ST.save(UT.NBT.make(), NBT_VALUE, UT.Code.select(null, OP.rockGt.mat(MT.PetrifiedWood, 1), OP.rockGt.mat(MT.Gneiss, 1), ST.make(Items.flint, 1, 0))):UT.NBT.make(), F, T);
				break;
			}
		}
		return T;
	}
}