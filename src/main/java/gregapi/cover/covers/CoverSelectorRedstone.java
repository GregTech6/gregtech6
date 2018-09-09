package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverSelectorRedstone extends AbstractCoverAttachmentSelector {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aCoverSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(aData.mTileEntity.getRedstoneIncoming(aCoverSide)));
	}
	
	@Override
	public void onBlockUpdate(byte aCoverSide, CoverData aData) {
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aCoverSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(aData.mTileEntity.getRedstoneIncoming(aCoverSide)));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[UT.Code.bind4(aData.mVisuals[aSide])]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/redstoneselector/0", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/1", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/2", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/3", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/4", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/5", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/6", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/7", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/8", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/9", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/10", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/11", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/12", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/13", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/14", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/redstoneselector/underlay");
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/redstoneselector/base");
}