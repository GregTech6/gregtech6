/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerAutoTimer extends AbstractCoverAttachmentController {
	public final int mTime;
	public final ITexture mTextureForeground;
	
	public CoverControllerAutoTimer(int aTime) {
		mTime = Math.max(11, aTime);
		mTextureForeground = BlockTextureDefault.get("machines/covers/autotimerswitch/"+mTime+"/circuit");
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, mTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff((aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively()) || aTimer % mTime >= mTime - 10);
	}
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return T;
	}
}
