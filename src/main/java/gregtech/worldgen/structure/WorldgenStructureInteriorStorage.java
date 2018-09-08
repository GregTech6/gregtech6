package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.block.IPrefixBlock;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.MT;
import gregapi.fluid.FluidTankGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.UT;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorStorage extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		OreDictMaterial[] tGeneratedMaterials = {MT.Cu, MT.Cu, MT.Sn, MT.Bronze, MT.Fe, MT.Fe, MT.Fe, MT.Steel, MT.Steel, MT.StainlessSteel, MT.StainlessSteel, MT.DamascusSteel};
		IPrefixBlock[] tGeneratedCrates = {BlocksGT.crateGtDust, BlocksGT.crateGtPlate, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot};
		FluidStack[][] tFluids = {
		  {FL.Oil_Creosote.make(16000), FL.Oil_Seed.make(16000), FL.Lubricant.make(16000), FL.Glue.make(16000), FL.Latex.make(16000), FL.Holywater.make(16000), FL.Purple_Drink.make(16000)}
		, {FL.Oil_Creosote.make(32000), FL.Oil_Seed.make(32000), FL.Lubricant.make(32000), FL.Glue.make(32000), FL.Latex.make(32000), FL.Holywater.make(32000), FL.Purple_Drink.make(32000)}
		, {FL.Oil_Normal.make(64000), FL.Oil_Normal.make(64000), FL.Oil_Soulsand.make(64000), FL.Oil_Light.make(64000), FL.Oil_Medium.make(64000), FL.Oil_Heavy.make(64000), FL.Oil_ExtraHeavy.make(64000)}
		};
		short[] tIDs = new short[] {32714, 32734, 32716};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			if (aRandom.nextInt(2) == 0) {
				int tType = aRandom.nextInt(tIDs.length);
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) if (aRandom.nextInt(3) > 0) for (int k = 0; k < 4; k++) {
					aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+12+i, aData.mOffsetY+1+k, aChunkZ+ 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
					if (aRandom.nextInt(3) == 0) break;
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			if (aRandom.nextInt(2) == 0) {
				int tType = aRandom.nextInt(tIDs.length);
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) if (aRandom.nextInt(3) > 0) for (int k = 0; k < 4; k++) {
					aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1+i, aData.mOffsetY+1+k, aChunkZ+ 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
					if (aRandom.nextInt(3) == 0) break;
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			if (aRandom.nextInt(2) == 0) {
				int tType = aRandom.nextInt(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) if (aRandom.nextInt(3) > 0) for (int k = 0; k < 4; k++) {
					aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 7+i, aData.mOffsetY+1+k, aChunkZ+12+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
					if (aRandom.nextInt(3) == 0) break;
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			if (aRandom.nextInt(2) == 0) {
				int tType = aRandom.nextInt(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) if (aRandom.nextInt(3) > 0) for (int k = 0; k < 4; k++) {
					aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 7+i, aData.mOffsetY+1+k, aChunkZ+ 1+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
					if (aRandom.nextInt(3) == 0) break;
				}
			}
		}
		
		if (aRandom.nextInt(2) == 0) {
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+4, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+4, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
		}
		if (aRandom.nextInt(2) == 0) {
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+ 3, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+4, aChunkZ+ 1, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+4, aChunkZ+ 2, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
		}
		if (aRandom.nextInt(2) == 0) {
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+4, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+4, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
		}
		if (aRandom.nextInt(2) == 0) {
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+12, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+3, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+13, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+4, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
			if (aRandom.nextInt(2) == 0) tGeneratedCrates[aRandom.nextInt(tGeneratedCrates.length)].placeBlock(aWorld, aChunkX+14, aData.mOffsetY+4, aChunkZ+14, SIDE_UNKNOWN, tGeneratedMaterials[aRandom.nextInt(tGeneratedMaterials.length)].mID, null, T, T);
		}
		return T;
	}
}