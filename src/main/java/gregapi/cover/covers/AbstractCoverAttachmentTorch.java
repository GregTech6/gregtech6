package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.SFX;
import gregapi.tileentity.connectors.MultiTileEntityWireRedstoneInsulated;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentTorch extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof MultiTileEntityWireRedstoneInsulated);}
	@Override public boolean interceptConnect(byte aCoverSide, CoverData aData) {return T;}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).connected(aSide)) ((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).disconnect(aSide, T);
		if (aPlayer != null) UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_DIG_WOOD, 1.0F, -1.0F, aData.mTileEntity.getCoords());
	}
	
	@Override
	public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {
		UT.Sounds.send(aTileEntity.getWorld(), SFX.MC_DIG_WOOD, 1.0F, -1.0F, aTileEntity.getCoords());
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (byte)(aData.mVisuals[aCoverSide] == 0 ? 15 : 0);
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (byte)(aData.mVisuals[aCoverSide] == 0 ? 15 : 0);
	}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof MultiTileEntityWireRedstoneInsulated) {
			if (condition(aSide, aData, aTimer, aIsServerSide, aReceivedBlockUpdate, aReceivedInventoryUpdate)) {
				if (aData.mVisuals[aSide] == 0) {
					aData.visual(aSide, (short)1);
					aData.mTileEntity.sendBlockUpdateFromCover();
				}
			} else {
				if (aData.mVisuals[aSide] != 0) {
					aData.visual(aSide, (short)0);
					aData.mTileEntity.sendBlockUpdateFromCover();
				}
			}
		}
	}
	
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean isSolid(byte aSide, CoverData aData) {return F;}
	
	@Override public float[] getCoverBounds(byte aCoverSide, CoverData aData) {return BOXES_TORCHES[aCoverSide];}
	@Override public float[] getHolderBounds(byte aCoverSide, CoverData aData) {return BOXES_TORCHES[aCoverSide];}
	
	public abstract boolean condition(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate);
	
	public static final float[][] BOXES_TORCHES = new float[][] {{PX_P[ 7], PX_P[ 0], PX_P[ 7], PX_N[ 7], PX_N[ 8], PX_N[ 7]}, {PX_P[ 7], PX_P[ 8], PX_P[ 7], PX_N[ 7], PX_N[ 0], PX_N[ 7]}, {PX_P[ 7], PX_P[ 7], PX_P[ 0], PX_N[ 7], PX_N[ 7], PX_N[ 8]}, {PX_P[ 7], PX_P[ 7], PX_P[ 8], PX_N[ 7], PX_N[ 7], PX_N[ 0]}, {PX_P[ 0], PX_P[ 7], PX_P[ 7], PX_N[ 8], PX_N[ 7], PX_N[ 7]}, {PX_P[ 8], PX_P[ 7], PX_P[ 7], PX_N[ 0], PX_N[ 7], PX_N[ 7]}};
}