package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.SFX;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverTextureSimple extends AbstractCoverDefault {
	public final ITexture mTexture;
	public final String mSound;
	
	public CoverTextureSimple(ITexture aTexture) {
		this(aTexture, null);
	}
	public CoverTextureSimple(ITexture aTexture, String aSound) {
		mTexture = aTexture;
		mSound = aSound;
	}
	
	@Override public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {if (aPlayer != null) UT.Sounds.send(aData.mTileEntity.getWorld(), mSound == null ? SFX.IC_WRENCH : mSound, 1.0F, -1.0F, aData.mTileEntity.getCoords());}
	@Override public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {UT.Sounds.send(aTileEntity.getWorld(), mSound == null ? SFX.MC_BREAK : mSound, 1.0F, -1.0F, aTileEntity.getCoords());}
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTexture;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return F;}
}