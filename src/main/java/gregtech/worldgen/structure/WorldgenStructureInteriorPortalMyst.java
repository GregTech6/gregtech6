/**
 * Copyright (c) 2018 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorPortalMyst extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		WorldgenStructureInteriorPortal.generate(aWorld, aRandom, aChunkX, aChunkZ, aData);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)13));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)21));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)23));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)30));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)32));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)40));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)41));
		
		Block tCrystal = IL.Myst_Crystal.block();
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 7, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 8, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+2, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 7, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 8, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+5, aChunkZ+ 9, tCrystal, 0, 2);
			
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+5, aChunkZ+ 9, IL.Myst_Receptacle.block(), SIDE_X_NEG, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 7, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 8, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+2, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 9, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 6, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 7, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 8, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mOffsetY+5, aChunkZ+ 9, tCrystal, 0, 2);
			
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+5, aChunkZ+ 6, IL.Myst_Receptacle.block(), SIDE_X_POS, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+5, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+5, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+5, aChunkZ+ 2, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+5, aChunkZ+ 2, tCrystal, 0, 2);
			
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+5, aChunkZ+ 1, IL.Myst_Receptacle.block(), SIDE_Z_NEG, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)502, UT.NBT.make(null, NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+3, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+3, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+5, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mOffsetY+5, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mOffsetY+5, aChunkZ+13, tCrystal, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mOffsetY+5, aChunkZ+13, tCrystal, 0, 2);
			
			setBlock(aWorld, aChunkX+ 6, aData.mOffsetY+5, aChunkZ+14, IL.Myst_Receptacle.block(), SIDE_Z_POS, 2);
		}
		return T;
	}
}
