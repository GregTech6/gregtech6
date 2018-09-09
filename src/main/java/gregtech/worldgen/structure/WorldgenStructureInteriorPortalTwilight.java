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
public class WorldgenStructureInteriorPortalTwilight extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		WorldgenStructureInteriorPortal.generate(aWorld, aRandom, aChunkX, aChunkZ, aData);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Portal_TF")), "s", (short)22));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.diamond, 1, 0)), "s", (short)31));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)41));
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 7, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 8, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 7, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 8, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 7, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 8, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 7, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 8, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			
			setFlower(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setFlower(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setFlower(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+ 4, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setFlower(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setFlower(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 7, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 8, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 7, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 8, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 7, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 8, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 6, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 7, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 8, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 9, Blocks.grass, 0, 2);
			
			setFlower(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setFlower(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setFlower(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+11, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
			setFlower(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			setFlower(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 7, aData, aRandom);
			setFlower(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 8, aData, aRandom);
			setFlower(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 2, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 2, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 2, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 2, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 3, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 3, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 3, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 3, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 4, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 4, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 4, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 4, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 5, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 5, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 5, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 5, Blocks.grass, 0, 2);
			
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 2, aData, aRandom);
			setFlower(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 2, aData, aRandom);
			setFlower(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 2, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 2, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 3, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 3, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 4, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 4, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			setFlower(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			setFlower(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+10, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+10, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+10, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+10, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+11, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+11, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+11, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+11, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+12, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+12, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+12, Blocks.water, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+12, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+13, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+13, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+13, Blocks.grass, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+13, Blocks.grass, 0, 2);
			
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setFlower(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setFlower(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+11, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+11, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+12, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+12, aData, aRandom);
			setFlower(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+13, aData, aRandom);
			setFlower(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+13, aData, aRandom);
			setFlower(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+13, aData, aRandom);
			setFlower(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+13, aData, aRandom);
		}
		return T;
	}
}