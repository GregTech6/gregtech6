/**
 * Copyright (c) 2023 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.compat.opencomputers;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import gregapi.compat.CompatBase;
import gregapi.computer.IComputerizable;
import gregapi.computer.ICoverComputerizable;
import gregapi.computer.ITileEntityComputerizable;
import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import gregapi.util.WD;
import li.cil.oc.api.Driver;
import li.cil.oc.api.driver.SidedBlock;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import static gregapi.data.CS.F;
import static gregapi.data.CS.SIDES_VALID;

public class CompatOC extends CompatBase implements ICompatOC, SidedBlock {
	public CompatOC() {/**/}
	
	@Override
	public void onLoad(FMLInitializationEvent event) {
		Driver.add(this);
	}
	
	@Override
	public boolean worksWith(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		return findPeripheral(aWorld, aX, aY, aZ, aSide) != null;
	}
	
	@Override
	public ManagedEnvironment createEnvironment(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		return new EnvironmentOC(findPeripheral(aWorld, aX, aY, aZ, aSide), WD.te(aWorld, aX, aY, aZ, UT.Code.side(aSide), F));
	}
	
	public IComputerizable findPeripheral(World aWorld, int aX, int aY, int aZ, ForgeDirection side) {
		DelegatorTileEntity<TileEntity> tDelegator = WD.te(aWorld, aX, aY, aZ, UT.Code.side(side), F);
		if (SIDES_VALID[tDelegator.mSideOfTileEntity] && tDelegator.mTileEntity instanceof ITileEntityCoverable) {
			CoverData tData = ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData();
			if (tData != null && tData.mBehaviours[tDelegator.mSideOfTileEntity] instanceof ICoverComputerizable) return (IComputerizable)tData.mBehaviours[tDelegator.mSideOfTileEntity];
		}
		return tDelegator.mTileEntity instanceof ITileEntityComputerizable ? (IComputerizable)tDelegator.mTileEntity : null;
	}
}
