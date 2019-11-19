/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.data.ITileEntityWeight;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import gregapi.util.OM;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWeightometerHeavy extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.heavyweightometer", "Measures the weight of an Inventory (in Tons)");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.heavyweightometer");}
	
	public static final double MAX_WEIGHT = (B[16]-1)*1000.0;
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		double rWeightKG = 0;
		if (aDelegator.mTileEntity instanceof ITileEntityWeight) {
			rWeightKG = ((ITileEntityWeight)aDelegator.mTileEntity).getWeightValue(aDelegator.mSideOfTileEntity);
		} else if (aDelegator.mTileEntity instanceof IInventory) {
			if (aDelegator.mTileEntity instanceof ISidedInventory) {
				for (int i : ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity)) {
					rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
					if (rWeightKG >= MAX_WEIGHT) break;
				}
			} else for (int i = 0, j = ((IInventory)aDelegator.mTileEntity).getSizeInventory(); i < j; i++) {
				rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
				if (rWeightKG >= MAX_WEIGHT) break;
			}
		}
		if (rWeightKG >= MAX_WEIGHT) rWeightKG = MAX_WEIGHT;
		return (long)(rWeightKG/1000);
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return B[16]-1;
	}
	
	@Override public short[] getSymbolColor() {return CA_GRAY_192;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_TON;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/colored/front"),
	sTextureBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/colored/back"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/colored/side"),
	sOverlayFront   = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/overlay/front"),
	sOverlayBack    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/overlay/back"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/heavyweightometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.heavyweightometer";}
}
