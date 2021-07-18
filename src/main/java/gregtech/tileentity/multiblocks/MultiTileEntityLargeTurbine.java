/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import gregapi.code.TagData;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockConverter;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityLargeTurbine extends TileEntityBase11MultiBlockConverter implements IMultiBlockFluidHandler, IFluidHandler, ITileEntitySwitchableOnOff {
	public short mTurbineWalls = 18022;
	
	public static final IIconContainer mTextureActive   = new Textures.BlockIcons.CustomIcon("machines/multiblockmains/turbine_active");
	public static final IIconContainer mTextureInactive = new Textures.BlockIcons.CustomIcon("machines/multiblockmains/turbine");
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DESIGN)) mTurbineWalls = aNBT.getShort(NBT_DESIGN);
	}
	
	@Override
	public boolean checkStructure2() {
		int
		tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
		tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1),
		tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
		tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
		tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1),
		tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1),
		tOutX = getOffsetXN(mFacing, 3),
		tOutY = getOffsetYN(mFacing, 3),
		tOutZ = getOffsetZN(mFacing, 3);
		
		if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
			mEmitter = null;
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
				int tBits = 0;
				if (tX == tOutX && tY == tOutY && tZ == tOutZ) {
					tBits = MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT;
				} else {
					if (SIDES_AXIS_X[mFacing] && tX == xCoord || SIDES_AXIS_Y[mFacing] && tY == yCoord || SIDES_AXIS_Z[mFacing] && tZ == zCoord) {
						tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_FLUID : MultiTileEntityMultiBlockPart.ONLY_FLUID_IN);
					} else {
						tBits = MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_OUT;
					}
				}
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, mTurbineWalls, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 3 : 0, tBits)) tSuccess = F;
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	@Override
	public boolean allowCovers(byte aSide) {
		return aSide != mFacing;
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
		aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1) &&
		aY >= yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) &&
		aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1) &&
		aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1) &&
		aY <= yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) &&
		aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return mStructureOkay ? 2 : 1;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 1) switch(mFacing) {
		case SIDE_X_NEG: case SIDE_X_POS: return box(aBlock, -0.001, -0.999, -0.999,  1.001,  1.999,  1.999);
		case SIDE_Y_NEG: case SIDE_Y_POS: return box(aBlock, -0.999, -0.001, -0.999,  1.999,  1.001,  1.999);
		case SIDE_Z_NEG: case SIDE_Z_POS: return box(aBlock, -0.999, -0.999, -0.001,  1.999,  1.999,  1.001);
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aRenderPass == 0 ? super.getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered) : aSide != mFacing ? null : BlockTextureDefault.get(mActivity.mState > 0 ? mTextureActive : mTextureInactive);
	}
	
	public ITileEntityUnloadable mEmitter = null;
	
	@Override public TileEntity getEmittingTileEntity() {if (mEmitter == null || mEmitter.isDead()) {mEmitter = null; TileEntity tTileEntity = getTileEntityAtSideAndDistance(OPOS[mFacing], 3); if (tTileEntity instanceof ITileEntityUnloadable) mEmitter = (ITileEntityUnloadable)tTileEntity;} return mEmitter == null ? this : (TileEntity)mEmitter;}
	@Override public byte getEmittingSide() {return OPOS[mFacing];}
	@Override public boolean isInput (byte aSide) {return aSide == mFacing;}
	@Override public boolean isOutput(byte aSide) {return aSide == OPOS[mFacing];}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && mEnergyOUT.isType(aEnergyType);}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return F;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
}
