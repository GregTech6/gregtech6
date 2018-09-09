package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.util.UT;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverScaleProgress extends AbstractCoverAttachmentScale {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof ITileEntityProgress);}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntityProgress) {
			long tProgress = ((ITileEntityProgress)aData.mTileEntity).getProgressValue(aSide), tMax = ((ITileEntityProgress)aData.mTileEntity).getProgressMax(aSide);
			byte tNewValue = UT.Code.bind4(tProgress <= 0 || tMax <= 0 ? 0 : tProgress >= tMax ? 15 : 14-(int)Math.max(0, Math.min(13, ((tMax-tProgress)*14L) / tMax)));
			if (aData.mValues[aSide] != tNewValue) {
				aData.value(aSide, tNewValue);
				aData.mTileEntity.sendBlockUpdateFromCover();
			}
		}
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/progressredstone/base"), sTextureForeground = BlockTextureDefault.get("machines/covers/progressredstone/circuit");
}