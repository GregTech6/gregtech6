package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentSelector extends AbstractCoverAttachment {
	@Override
	public void onCoverRemove(byte aSide, CoverData aData, Entity aPlayer) {
		super.onCoverRemove(aSide, aData, aPlayer);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode((byte)0);
	}
}