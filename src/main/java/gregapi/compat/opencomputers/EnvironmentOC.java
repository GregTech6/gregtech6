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

import gregapi.computer.IComputerizable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.tileentity.TileEntity;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class EnvironmentOC extends ManagedEnvironment implements ManagedPeripheral, NamedBlock {
	public final DelegatorTileEntity<TileEntity> mDelegator;
	public final IComputerizable mDeviceType;
	public final String mDeviceName;
	public final String[] mDeviceMethods;
	public final List<String> mDeviceMethodList;
	
	public EnvironmentOC(IComputerizable aDeviceType, DelegatorTileEntity<TileEntity> aDelegator) {
		mDelegator = aDelegator;
		mDeviceType = aDeviceType;
		mDeviceName = mDeviceType.getComputerizableName(mDelegator);
		mDeviceMethods = mDeviceType.allComputerizableMethods(mDelegator);
		mDeviceMethodList = Arrays.asList(mDeviceMethods);
		setNode(Network.newNode(this, Visibility.Network).create());
	}
	
	@Override
	public Object[] invoke(String aMethod, Context aContext, Arguments aArgs) {
		int aIndex = mDeviceMethodList.indexOf(aMethod);
		Object[] tArgs = aArgs.toArray();
		for (int i = 0; i < tArgs.length; i++) if (tArgs[i] instanceof byte[]) tArgs[i] = new String((byte[]) tArgs[i], StandardCharsets.UTF_8);
		return mDeviceType.callComputerizableMethod(mDelegator, aIndex, tArgs);
	}
	
	@Override
	public String[] methods() {
		return mDeviceMethods;
	}
	
	@Override
	public String preferredName() {
		return mDeviceName;
	}
	
	@Override
	public int priority() {
		return 0;
	}
}
