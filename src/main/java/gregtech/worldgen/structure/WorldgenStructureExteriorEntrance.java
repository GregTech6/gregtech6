package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureExteriorEntrance extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		WorldgenStructureExteriorPillar.generate(aWorld, aRandom, aChunkX, aChunkZ, aData);
		
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) for (int tY = 0; tY <= 7; tY++) {
			if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15 || tY == 0 || tY == 7) {
				if ((tX == 2 || tX == 6 || tX == 9 || tX == 13) && (tZ == 2 || tZ == 6 || tZ == 9 || tZ == 13)) {
					if (tY == 0) {
						setChiseledStone(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					} else if (tY == 7) {
						if (!((tX == 6 || tX == 9) && (tZ == 6 || tZ == 9))) {
							setLampBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom, +1);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				} else {
					if (tY == 0) {
						setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					} else if (tY == 7) {
						setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					} else {
						setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				}
			} else {
				setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			}
		}
		
		int tHeight = aData.mOffsetY+10;
		for (int eY = aWorld.getHeight()-32, tAirAmount = 0; tHeight < eY && tAirAmount < 100; tHeight++) {
			tAirAmount = 0;
			for (int tX = aChunkX + 3, eX = tX + 10; tX < eX; tX++) for (int tZ = aChunkZ + 3, eZ = tZ + 10; tZ < eZ; tZ++) {
				Block tBlock = WD.block(aWorld, tX, tHeight, tZ);
				if (WD.easyRep(aWorld, tX, tHeight, tZ, tBlock) || tBlock.isWood(aWorld, tX, tHeight, tZ)) tAirAmount++;
			}
		}
		
		if ((tHeight - (aData.mOffsetY+1)) % 5 != 0) tHeight += 5 - ((tHeight - (aData.mOffsetY+1)) % 5);
		
		for (int tY = aData.mOffsetY+7; tY <= tHeight; tY++) for (int tX = 3; tX <= 12; tX++) for (int tZ = 3; tZ <= 12; tZ++) {
			if (tX >= 6 && tX <= 9 && tZ >= 6 && tZ <= 9 && (tX == 6 || tZ == 6 || tX == 9 || tZ == 9)) {
				if ((tY - aData.mOffsetY) % 4 == 0) {
					setColored(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setSmoothBlock(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				}
			} else if (tX == 3 || tZ == 3 || tX == 12 || tZ == 12) {
				if (tY == tHeight-1) {
					setStoneTiles(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setRandomBricks(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				}
			} else {
				setAirBlock(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
			}
		}
		
		for (int tY = aData.mOffsetY+1; tY <= aData.mOffsetY+6; tY++) for (int tX = 6; tX <= 9; tX++) for (int tZ = 6; tZ <= 9; tZ++) {
			if (tX == 6 || tZ == 6 || tX == 9 || tZ == 9) {
				if ((tY - aData.mOffsetY) % 4 == 0) {
					setColored(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setSmoothBlock(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				}
			}
		}
		
		setAirBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
		setAirBlock(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		
		int tOffsetY = aData.mOffsetY - 5;
		
		while (tOffsetY + 10 < tHeight) {
			tOffsetY += 5;
			
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 2, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 2, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 2, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 2, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 3, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 3, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 3, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 3, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+ 9, tOffsetY+ 3, aChunkZ+10, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, tOffsetY+ 3, aChunkZ+11, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, tOffsetY+ 4, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, tOffsetY+ 4, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, tOffsetY+ 4, aChunkZ+10, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, tOffsetY+ 4, aChunkZ+11, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 6, tOffsetY+ 5, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 6, tOffsetY+ 5, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+10, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+11, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+10, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+11, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			
			
			
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 2, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 2, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 2, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 2, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 3, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 3, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 4, tOffsetY+ 3, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, tOffsetY+ 3, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+ 6, tOffsetY+ 3, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 6, tOffsetY+ 3, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, tOffsetY+ 4, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, tOffsetY+ 4, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, tOffsetY+ 4, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, tOffsetY+ 4, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, tOffsetY+ 5, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, tOffsetY+ 5, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
			setSmoothBlock(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		}
		
		setStoneTiles(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 4, tOffsetY+ 5, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+ 5, tOffsetY+ 5, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		
		setStoneTiles(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+10, tOffsetY+ 5, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		setStoneTiles(aWorld, aChunkX+11, tOffsetY+ 5, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1], aRandom);
		
		for (int tY = Math.max(aData.mOffsetY+8, tHeight-2); tY <= tHeight+2; tY++) for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			if (tX == 2 || tZ == 2 || tX == 13 || tZ == 13) {
				if (tY == tHeight+1) {
					setColored(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setRandomBricks(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
				}
			} else {
				if (tY >= tHeight) setAirBlock(aWorld, aChunkX+tX, tY, aChunkZ+tZ, aData, aRandom);
			}
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+ 5, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+ 6, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+ 7, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+ 8, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+0, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+1, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+3, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+ 5, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+ 6, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+15, aData.mOffsetY+4, aChunkZ+10, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+ 5, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+ 6, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+ 7, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+ 8, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+0, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+1, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+ 5, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+ 6, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+ 7, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+ 8, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+3, aChunkZ+10, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+ 5, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+ 6, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+ 9, aData, aRandom);
			setColored		(aWorld, aChunkX+ 0, aData.mOffsetY+4, aChunkZ+10, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 6, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 7, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 8, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 9, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+0, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+3, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+4, aChunkZ+15, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 6, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 7, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 8, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 9, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+0, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 7, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 8, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setAirBlock		(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+3, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 5, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
			setColored		(aWorld, aChunkX+10, aData.mOffsetY+4, aChunkZ+ 0, aData, aRandom);
		}
		
		return T;
	}
}