package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.MultiTileEntityWireRedstoneInsulated;

/**
 * @author Gregorius Techneticies
 */
public class CoverRedstoneTorch extends AbstractCoverAttachmentTorch {
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return aData.mVisuals[aSide] == 0 ? sTextureFrontON : sTextureFrontOFF;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? aData.mVisuals[aSide] == 0 ? sTextureFrontON : sTextureFrontOFF : null;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? null : aData.mVisuals[aSide] == 0 ? sTextureSideON : sTextureSideOFF;}
	
	public static final ITexture
	sTextureFrontON		= BlockTextureDefault.get("machines/covers/redstonetorch/on/front"),
	sTextureSideON		= BlockTextureDefault.get("machines/covers/redstonetorch/on/side"),
	sTextureFrontOFF	= BlockTextureDefault.get("machines/covers/redstonetorch/off/front"),
	sTextureSideOFF		= BlockTextureDefault.get("machines/covers/redstonetorch/off/side");
	
	@Override
	public boolean condition(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		return ((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).mRedstone > 0;
	}
}