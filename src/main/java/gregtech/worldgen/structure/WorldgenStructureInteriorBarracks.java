package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.data.FL;
import gregapi.fluid.FluidTankGT;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorBarracks extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) if ((tX <= 4 || tX >= 11) && (tZ <= 4 || tZ >= 11)) {
			setBlock(aWorld, aChunkX+tX, aData.mOffsetY+1, aChunkZ+tZ, Blocks.carpet, aData.mColorInversed, 2);
		}
		for (int tY = 1; tY <=  6; tY++) {
			for (int tX = 1; tX <= 14; tX++) if (tX <= 3 || tX >= 12) {
				setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+10, aData, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS], aRandom);
			}
			for (int tZ = 1; tZ <= 14; tZ++) if (tZ <= 3 || tZ >= 12) {
				setRandomBricks(aWorld, aChunkX+ 5, aData.mOffsetY+tY, aChunkZ+tZ, aData, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG], aRandom);
				setRandomBricks(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+tZ, aData, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS], aRandom);
			}
			
			setSmoothBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mOffsetY+tY, aChunkZ+ 4, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mOffsetY+tY, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+10, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mOffsetY+tY, aChunkZ+10, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mOffsetY+tY, aChunkZ+11, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+ 4, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+10, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+11, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+10, aData, aRandom);
			
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 5, Blocks.iron_door, 1, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 5, Blocks.iron_door, 8, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 5, Blocks.iron_door, 1, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 5, Blocks.iron_door, 9, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+10, Blocks.iron_door, 3, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+10, Blocks.iron_door, 9, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+10, Blocks.iron_door, 3, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+10, Blocks.iron_door, 8, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+ 6, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+ 6, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+ 9, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+ 9, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 4, Blocks.stone_pressure_plate, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 4, Blocks.stone_pressure_plate, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+11, Blocks.stone_pressure_plate, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+11, Blocks.stone_pressure_plate, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 1, Blocks.bed,10, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 2, Blocks.bed, 2, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+13, Blocks.bed, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+14, Blocks.bed, 8, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 1, Blocks.bed,10, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 2, Blocks.bed, 2, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+13, Blocks.bed, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+14, Blocks.bed, 8, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 4, Blocks.crafting_table, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+11, Blocks.crafting_table, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 4, Blocks.crafting_table, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+11, Blocks.crafting_table, 0, 2);
		}
		
		FluidStack[] tDrinks = new FluidStack[] {NF, NF, NF, NF, NF, FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Vodka.make(250), FL.Mead.make(250), FL.Whiskey_GlenMcKenner.make(250), FL.Wine_Grape_Purple.make(250)};
		
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 4, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+11, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 4, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+11, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
		
		
		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};
		
		NBTTagList
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[0]) {aData.mGeneratedKeys[0] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[0]), "s", (short)aRandom.nextInt(28)));}
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 3010, UT.NBT.make(null, "gt.dungeonloot"		, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[0]), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 7110, UT.NBT.make(null, "gt.dungeonloot.front"	, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		if (aRandom.nextBoolean()) setCoins(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[1]) {aData.mGeneratedKeys[1] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[1]), "s", (short)aRandom.nextInt(28)));}
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 3010, UT.NBT.make(null, "gt.dungeonloot"		, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[1]), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 7110, UT.NBT.make(null, "gt.dungeonloot.front"	, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		if (aRandom.nextBoolean()) setCoins(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[3]) {aData.mGeneratedKeys[3] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[3]), "s", (short)aRandom.nextInt(28)));}
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 3010, UT.NBT.make(null, "gt.dungeonloot"		, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[3]), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 7110, UT.NBT.make(null, "gt.dungeonloot.front"	, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		if (aRandom.nextBoolean()) setCoins(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[4]) {aData.mGeneratedKeys[4] = T; tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[4]), "s", (short)aRandom.nextInt(28)));}
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 3010, UT.NBT.make(null, "gt.dungeonloot"		, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[4]), T, T);
		aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 7110, UT.NBT.make(null, "gt.dungeonloot.front"	, UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		if (aRandom.nextBoolean()) setCoins(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
		
		return T;
	}
}