package gregapi.compat.computercraft;

import static gregapi.data.CS.*;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import gregapi.compat.CompatBase;
import gregapi.computer.IComputerizable;
import gregapi.computer.ICoverComputerizable;
import gregapi.computer.ITileEntityComputerizable;
import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.WD;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CompatCC extends CompatBase implements ICompatCC, IPeripheralProvider {
	public CompatCC() {
		ComputerCraftAPI.registerPeripheralProvider(this);
	}
	
	@Override
	public IPeripheral getPeripheral(World aWorld, int aX, int aY, int aZ, int aSide) {
		DelegatorTileEntity<TileEntity> aDelegator = WD.te(aWorld, aX, aY, aZ, (byte)aSide, F);
		if (SIDES_VALID[aDelegator.mSideOfTileEntity] && aDelegator.mTileEntity instanceof ITileEntityCoverable) {
			CoverData tData = ((ITileEntityCoverable)aDelegator.mTileEntity).getCoverData();
			if (tData != null && tData.mBehaviours[aDelegator.mSideOfTileEntity] instanceof ICoverComputerizable) return new ComputerizablePeripheral((ICoverComputerizable)tData.mBehaviours[aDelegator.mSideOfTileEntity], aDelegator);
		}
		return aDelegator.mTileEntity instanceof ITileEntityComputerizable ? new ComputerizablePeripheral((ITileEntityComputerizable)aDelegator.mTileEntity, aDelegator) : null;
	}
	
	public static class ComputerizablePeripheral implements IPeripheral {
		public final IComputerizable mComputerizable;
		public final String mType;
		public final DelegatorTileEntity<TileEntity> mDelegator;
		
		public ComputerizablePeripheral(IComputerizable aComputerizable, DelegatorTileEntity<TileEntity> aDelegator) {
			mComputerizable = aComputerizable;
			mDelegator = aDelegator;
			mType = aComputerizable.getComputerizableName(mDelegator);
		}
		
		@Override public String getType() {return mType;}
		@Override public String[] getMethodNames() {return mComputerizable.allComputerizableMethods(mDelegator);}
		@Override public Object[] callMethod(IComputerAccess aComputer, ILuaContext aContext, int aFunctionIndex, Object[] aArguments) throws LuaException, InterruptedException {return mComputerizable.callComputerizableMethod(mDelegator, aFunctionIndex, aArguments);}
		@Override public void attach(IComputerAccess aComputer) {/**/}
		@Override public void detach(IComputerAccess aComputer) {/**/}
		@Override public boolean equals(IPeripheral aOther) {return aOther == this || (aOther instanceof ComputerizablePeripheral && ((ComputerizablePeripheral)aOther).mComputerizable == mComputerizable && mDelegator.equalSideTileEntityAndCoords(((ComputerizablePeripheral)aOther).mDelegator));}
	}
}