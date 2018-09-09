package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.data.CS.BlocksGT;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorPool extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		for (int tX = 3; tX <= 12; tX++) for (int tZ = 3; tZ <= 12; tZ++) {
			if ((tX == 3 || tX == 12 || tZ == 3 || tZ == 12)) {
				setColored(aWorld, aChunkX+tX, aData.mOffsetY  , aChunkZ+tZ, aData, aRandom);
				setColored(aWorld, aChunkX+tX, aData.mOffsetY-1, aChunkZ+tZ, aData, aRandom);
				setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY-2, aChunkZ+tZ, aData, aRandom);
			} else {
				setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY-3, aChunkZ+tZ, aData, aRandom);
				setColored(aWorld, aChunkX+tX, aData.mOffsetY-2, aChunkZ+tZ, aData, aRandom);
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY  , aChunkZ+tZ, Blocks.water, 0, 2);
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY-1, aChunkZ+tZ, Blocks.water, 0, 2);
				if (aRandom.nextBoolean()) setBlock(aWorld, aChunkX+tX, aData.mOffsetY+1, aChunkZ+tZ, BlocksGT.Glowtus, aRandom.nextInt(16), 0);
			}
		}
		
		short tChestType = (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3));
		
		if (aRandom.nextBoolean()) aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, tChestType, UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_POS), T, T);
		if (aRandom.nextBoolean()) aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, tChestType, UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_NEG), T, T);
		if (aRandom.nextBoolean()) aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, tChestType, UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_POS), T, T);
		if (aRandom.nextBoolean()) aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, tChestType, UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_NEG), T, T);
		return T;
	}
}