/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.compat.warpdrive;

import cr0s.warpdrive.api.IBlockTransformer;
import cr0s.warpdrive.api.ITransformation;
import cr0s.warpdrive.config.WarpDriveConfig;
import gregapi.block.multitileentity.example.MultiTileEntityChest;
import gregapi.compat.CompatBase;
import gregapi.cover.CoverData;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.MD;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import static gregapi.data.CS.*;

public class CompatWD extends CompatBase implements ICompatWD, IBlockTransformer {
	public CompatWD() {
		WarpDriveConfig.registerBlockTransformer(MD.GAPI.mID, this);
	}
	
	@Override
	public boolean isApplicable(Block aBlock, int aMeta, TileEntity aTileEntity) {
		// Anything with Covers, including all directional Blocks, and the Chest which happens to be special.
		return aTileEntity instanceof ITileEntityCoverable || aTileEntity instanceof MultiTileEntityChest;
	}
	
	@Override
	public int rotate(Block aBlock, int aMeta, NBTTagCompound aNBT, ITransformation aTransformation) {
		if (aTransformation.getRotationSteps() % 4 == 0) return aMeta;
		
		byte tConnections = aNBT.getByte(NBT_CONNECTION);
		CoverData tCovers = aNBT.hasKey(NBT_COVERS) ? CoverRegistry.coverdata(null, aNBT.getCompoundTag(NBT_COVERS)) : null;
		
		switch(aTransformation.getRotationSteps() % 4) {
		case  1:
			// 90° clockwise when viewed from top
			UT.NBT.setNumber(aNBT, NBT_FACING, ROTATE_090[aNBT.getByte(NBT_FACING) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC2NG, ROTATE_090[aNBT.getByte(NBT_FAC2NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC3NG, ROTATE_090[aNBT.getByte(NBT_FAC3NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_CONNECTION,
			(tConnections & ~60)
			| (FACE_CONNECTED[SIDE_NORTH][tConnections] ? SBIT_E : 0)
			| (FACE_CONNECTED[SIDE_SOUTH][tConnections] ? SBIT_W : 0)
			| (FACE_CONNECTED[SIDE_WEST ][tConnections] ? SBIT_N : 0)
			| (FACE_CONNECTED[SIDE_EAST ][tConnections] ? SBIT_S : 0)
			);
			if (tCovers != null) {
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mIDs       );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mMetas     );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mValues    );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mBehaviours);
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mIDs       );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mMetas     );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mValues    );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mBehaviours);
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mIDs       );
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mMetas     );
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mVisuals   );
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mValues    );
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mNBTs      );
			UT.Code.swap(SIDE_SOUTH, SIDE_NORTH, tCovers.mBehaviours);
			aNBT.setTag(NBT_COVERS, tCovers.writeToNBT());
			}
			return aMeta;
		case  2:
			// 180° clockwise when viewed from top
			UT.NBT.setNumber(aNBT, NBT_FACING, ROTATE_180[aNBT.getByte(NBT_FACING) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC2NG, ROTATE_180[aNBT.getByte(NBT_FAC2NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC3NG, ROTATE_180[aNBT.getByte(NBT_FAC3NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_CONNECTION,
			(tConnections & ~60)
			| (FACE_CONNECTED[SIDE_NORTH][tConnections] ? SBIT_S : 0)
			| (FACE_CONNECTED[SIDE_SOUTH][tConnections] ? SBIT_N : 0)
			| (FACE_CONNECTED[SIDE_WEST ][tConnections] ? SBIT_E : 0)
			| (FACE_CONNECTED[SIDE_EAST ][tConnections] ? SBIT_W : 0)
			);
			if (tCovers != null) {
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mIDs       );
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mMetas     );
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mVisuals   );
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mValues    );
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mNBTs      );
			UT.Code.swap(SIDE_NORTH, SIDE_SOUTH, tCovers.mBehaviours);
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mIDs       );
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mMetas     );
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mValues    );
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_WEST , SIDE_EAST , tCovers.mBehaviours);
			aNBT.setTag(NBT_COVERS, tCovers.writeToNBT());
			}
			return aMeta;
		case  3:
			// 270° clockwise when viewed from top
			UT.NBT.setNumber(aNBT, NBT_FACING, ROTATE_270[aNBT.getByte(NBT_FACING) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC2NG, ROTATE_270[aNBT.getByte(NBT_FAC2NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_FAC3NG, ROTATE_270[aNBT.getByte(NBT_FAC3NG) & 7]);
			UT.NBT.setNumber(aNBT, NBT_CONNECTION,
			(tConnections & ~60)
			| (FACE_CONNECTED[SIDE_NORTH][tConnections] ? SBIT_W : 0)
			| (FACE_CONNECTED[SIDE_SOUTH][tConnections] ? SBIT_E : 0)
			| (FACE_CONNECTED[SIDE_WEST ][tConnections] ? SBIT_S : 0)
			| (FACE_CONNECTED[SIDE_EAST ][tConnections] ? SBIT_N : 0)
			);
			if (tCovers != null) {
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mIDs       );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mMetas     );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mValues    );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_NORTH, SIDE_EAST , tCovers.mBehaviours);
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mIDs       );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mMetas     );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mValues    );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_SOUTH, SIDE_WEST , tCovers.mBehaviours);
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mIDs       );
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mMetas     );
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mVisuals   );
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mValues    );
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mNBTs      );
			UT.Code.swap(SIDE_EAST , SIDE_WEST , tCovers.mBehaviours);
			aNBT.setTag(NBT_COVERS, tCovers.writeToNBT());
			}
			return aMeta;
		default:
			// This Line is not supposed to ever be reachable.
			return aMeta;
		}
	}
	
	@Override public boolean isJumpReady(Block aBlock, int aMeta, TileEntity aTileEntity, StringBuilder aStringBuilder) {return T;}
	@Override public NBTBase saveExternals(World world, int aX, int aY, int aZ, Block aBlock, int aMeta, TileEntity aTileEntity) {return null;}
	@Override public void removeExternals(World world, int aX, int aY, int aZ, Block aBlock, int aMeta, TileEntity aTileEntity) {/**/}
	@Override public void restoreExternals(World world, int aX, int aY, int aZ, Block aBlock, int aMeta, TileEntity aTileEntity, ITransformation aTransformation, NBTBase aNBT) {/**/}
}
