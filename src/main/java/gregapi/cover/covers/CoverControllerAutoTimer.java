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
	public final ITexture mTextureBackground, mTextureForeground;
	
	public CoverControllerAutoTimer(int aTime) {
		mTime = Math.max(11, aTime);
		mTextureBackground = BlockTextureDefault.get("machines/covers/autotimerswitch/"+mTime+"/base");
		mTextureForeground = BlockTextureDefault.get("machines/covers/autotimerswitch/"+mTime+"/circuit");
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? mTextureBackground : BlockTextureMulti.get(mTextureBackground, mTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return mTextureBackground;}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff((aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively()) || aTimer % mTime >= mTime - 10);
	}
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return T;
	}
}