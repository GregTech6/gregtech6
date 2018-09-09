package gregapi.compat.buildcraft;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import gregapi.code.TagData;
import gregapi.data.MD;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import net.minecraft.tileentity.TileEntity;

public class TriggerBC_Energy_Capacity_Partial extends TriggerBC {
	public final TagData mEnergyType;
	
	public TriggerBC_Energy_Capacity_Partial(TagData aEnergyType) {
		super(MD.GAPI.mID, aEnergyType.mName.toLowerCase()+".capacity.partial", "Is Partially Filled (" + aEnergyType.getLocalisedNameShort() + ")");
		mEnergyType = aEnergyType;
	}
	
	@Override
	public boolean isActive(TileEntity aTarget, byte aSideOfTileEntity, IStatementContainer aSource, IStatementParameter[] aParameters) {
		long tAmount = ((ITileEntityEnergyDataCapacitor)aTarget).getEnergyStored(mEnergyType, aSideOfTileEntity);
		return tAmount > 0 && tAmount < ((ITileEntityEnergyDataCapacitor)aTarget).getEnergyCapacity(mEnergyType, aSideOfTileEntity);
	}
	
	@Override
	public boolean isApplicable(TileEntity aTarget, byte aSideOfTileEntity) {
		return aTarget instanceof ITileEntityEnergyDataCapacitor && ((ITileEntityEnergyDataCapacitor)aTarget).isEnergyCapacitorType(mEnergyType, aSideOfTileEntity);
	}
}