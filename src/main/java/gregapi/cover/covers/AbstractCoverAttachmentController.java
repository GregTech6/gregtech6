package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentController extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableOnOff);}
	
	@Override
	public void onCoverRemove(byte aCoverSide, CoverData aData, Entity aPlayer) {
		super.onCoverRemove(aCoverSide, aData, aPlayer);
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(T);
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onBlockUpdate(byte aCoverSide, CoverData aData) {
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onTickPre(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	public abstract boolean getStateOnOff(byte aCoverSide, CoverData aData);
}