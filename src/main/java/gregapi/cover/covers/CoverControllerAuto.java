package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningPossible;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerAuto extends AbstractCoverAttachmentController {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntityRunningPossible) || !aData.mTileEntity.canTick() || super.interceptCoverPlacement(aCoverSide, aData, aPlayer);}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/autoswitch/base"), sTextureForeground = BlockTextureDefault.get("machines/covers/autoswitch/circuit");
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return (aData.mTileEntity instanceof ITileEntityRunningPossible && ((ITileEntityRunningPossible)aData.mTileEntity).getStateRunningPossible()) || (aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively());
	}
}