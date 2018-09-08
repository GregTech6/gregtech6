package gregapi.cover.covers;

import gregapi.code.TagData;
import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.util.UT;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverScaleEnergy extends AbstractCoverAttachmentScale {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof ITileEntityEnergyDataCapacitor);}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntityEnergyDataCapacitor && !((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacitorTypes(aSide).isEmpty()) {
			TagData tEnergyType = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacitorTypes(aSide).iterator().next();
			long tStored = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyStored(tEnergyType, aSide), tCapacity = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacity(tEnergyType, aSide);
			byte tNewValue = UT.Code.bind4(tStored <= 0 || tCapacity <= 0 ? 0 : tStored >= tCapacity ? 15 : 14-(int)Math.max(0, Math.min(13, ((tCapacity-tStored)*14L) / tCapacity)));
			if (aData.mValues[aSide] != tNewValue) {
				aData.value(aSide, tNewValue);
				aData.mTileEntity.sendBlockUpdateFromCover();
			}
		}
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/energyredstone/base"), sTextureForeground = BlockTextureDefault.get("machines/covers/energyredstone/circuit");
}