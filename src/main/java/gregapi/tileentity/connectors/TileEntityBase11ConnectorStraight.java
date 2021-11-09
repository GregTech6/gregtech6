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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.render.ITexture;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase11ConnectorStraight extends TileEntityBase10ConnectorRendered {
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		if (worldObj == null && !hasCovers()) mConnections = (byte)(SBIT_S|SBIT_N);
		return mFoam && !mFoamDried ? 2 : 1;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 1) return aShouldSideBeRendered[aSide] ? getTextureCFoam   (aSide, mConnections, mDiameter, aRenderPass) : null;
		return mFoamDried ?          aShouldSideBeRendered[aSide] ? getTextureCFoamDry(aSide, mConnections, mDiameter, aRenderPass) : null : mConnections == 0 || (mDiameter >= 1.0F && connected(aSide)) ? getTextureConnected(aSide, mConnections, mDiameter, aRenderPass) : getTextureSide(aSide, mConnections, mDiameter, aRenderPass);
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		return !mFoamDried && aRenderPass == 0 && mDiameter < 1.0F && box(aBlock
		,    FACE_CONNECTED[SIDE_X_NEG][mConnections] ? -getConnectorLength(SIDE_X_NEG, getAdjacentTileEntity(SIDE_X_NEG, F, F)) : (1.0F-mDiameter)/2.0F
		,    FACE_CONNECTED[SIDE_Y_NEG][mConnections] ? -getConnectorLength(SIDE_Y_NEG, getAdjacentTileEntity(SIDE_Y_NEG, F, F)) : (1.0F-mDiameter)/2.0F
		,    FACE_CONNECTED[SIDE_Z_NEG][mConnections] ? -getConnectorLength(SIDE_Z_NEG, getAdjacentTileEntity(SIDE_Z_NEG, F, F)) : (1.0F-mDiameter)/2.0F
		, 1-(FACE_CONNECTED[SIDE_X_POS][mConnections] ?  getConnectorLength(SIDE_X_POS, getAdjacentTileEntity(SIDE_X_POS, F, F)) : (1.0F-mDiameter)/2.0F)
		, 1-(FACE_CONNECTED[SIDE_Y_POS][mConnections] ?  getConnectorLength(SIDE_Y_POS, getAdjacentTileEntity(SIDE_Y_POS, F, F)) : (1.0F-mDiameter)/2.0F)
		, 1-(FACE_CONNECTED[SIDE_Z_POS][mConnections] ?  getConnectorLength(SIDE_Z_POS, getAdjacentTileEntity(SIDE_Z_POS, F, F)) : (1.0F-mDiameter)/2.0F)
		);
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	@Override public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {if (!addDefaultCollisionBoxToList()) box(aAABB, aList, FACE_CONNECTED[SIDE_X_NEG][mConnections] ? 0 : (1.0F-mDiameter)/2.0F, FACE_CONNECTED[SIDE_Y_NEG][mConnections] ? 0 : (1.0F-mDiameter)/2.0F, FACE_CONNECTED[SIDE_Z_NEG][mConnections] ? 0 : (1.0F-mDiameter)/2.0F, FACE_CONNECTED[SIDE_X_POS][mConnections] ? 1 : 1-(1.0F-mDiameter)/2.0F, FACE_CONNECTED[SIDE_Y_POS][mConnections] ? 1 : 1-(1.0F-mDiameter)/2.0F, FACE_CONNECTED[SIDE_Z_POS][mConnections] ? 1 : 1-(1.0F-mDiameter)/2.0F);}
	
	// Makes sure the Axles are going actually straight.
	@Override public boolean connect(byte aSide, boolean aNotify) {
		for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[aSide]) if (connected(tSide) && !disconnect(tSide, T)) return F;
		return super.connect(aSide, aNotify);
	}
}
