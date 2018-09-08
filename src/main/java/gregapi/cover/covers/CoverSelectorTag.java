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
public class CoverSelectorTag extends AbstractCoverAttachmentSelector {
	public final byte mMode;
	
	public CoverSelectorTag(byte aMode) {mMode = UT.Code.bind4(aMode);}
	
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(mMode);
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(mMode);
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[mMode]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/selectortag/0", T)
		, BlockTextureDefault.get("machines/covers/selectortag/1", T)
		, BlockTextureDefault.get("machines/covers/selectortag/2", T)
		, BlockTextureDefault.get("machines/covers/selectortag/3", T)
		, BlockTextureDefault.get("machines/covers/selectortag/4", T)
		, BlockTextureDefault.get("machines/covers/selectortag/5", T)
		, BlockTextureDefault.get("machines/covers/selectortag/6", T)
		, BlockTextureDefault.get("machines/covers/selectortag/7", T)
		, BlockTextureDefault.get("machines/covers/selectortag/8", T)
		, BlockTextureDefault.get("machines/covers/selectortag/9", T)
		, BlockTextureDefault.get("machines/covers/selectortag/10", T)
		, BlockTextureDefault.get("machines/covers/selectortag/11", T)
		, BlockTextureDefault.get("machines/covers/selectortag/12", T)
		, BlockTextureDefault.get("machines/covers/selectortag/13", T)
		, BlockTextureDefault.get("machines/covers/selectortag/14", T)
		, BlockTextureDefault.get("machines/covers/selectortag/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/selectortag/underlay");
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/selectortag/base");
}