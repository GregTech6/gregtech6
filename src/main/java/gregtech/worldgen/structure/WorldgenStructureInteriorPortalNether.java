package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorPortalNether extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		WorldgenStructureInteriorPortal.generate(aWorld, aRandom, aChunkX, aChunkZ, aData);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Hunting_Blaze")), "s", (short)22));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Tool_MatchBox_Full.get(1)), "s", (short)31));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.netherrack, 4, 0)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.ghast_tear, 4, 0)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.glowstone, 4, 0)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.blaze_rod, 4, 0)), "s", (short)41));
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+ 1, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+2, aChunkZ+ 1, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+14, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+2, aChunkZ+14, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setRandomBricks(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+ 2, aData, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+13, aData, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS], aRandom);
			}
			
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 7, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 8, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 7, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 8, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 9, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+ 1, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+2, aChunkZ+ 1, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+14, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ i, aData.mOffsetY+2, aChunkZ+14, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setRandomBricks(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+ 2, aData, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+ i, aData.mOffsetY+1, aChunkZ+13, aData, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS], aRandom);
			}
			
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 7, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 8, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 9, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 6, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 7, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 8, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 9, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ i, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ i, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ i, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ i, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setRandomBricks(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ i, aData, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ i, aData, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS], aRandom);
			}
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+5, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+5, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+5, aChunkZ+ 2, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+5, aChunkZ+ 2, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ i, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ i, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ i, Blocks.soul_sand, 0, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ i, Blocks.nether_wart, aRandom.nextInt(4), 2);
				setRandomBricks(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ i, aData, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ i, aData, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS], aRandom);
			}
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+5, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+5, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+5, aChunkZ+13, Blocks.obsidian, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+5, aChunkZ+13, Blocks.obsidian, 0, 2);
		}
		return T;
	}
}