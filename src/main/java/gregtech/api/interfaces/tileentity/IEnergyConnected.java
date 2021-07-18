/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.api.interfaces.tileentity;

import static gregapi.data.CS.*;

import gregapi.data.TD;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.WD;
import net.minecraft.tileentity.TileEntity;

@Deprecated
@SuppressWarnings("deprecation")
/** Required to exist in GT6 because Immersive Engineering crashes otherwise. Also there is that GT5U+GT6 Mod that basically needs this for Compat. */
public interface IEnergyConnected extends IColoredTileEntity, IHasWorldObjectAndCoords {
	public long injectEnergyUnits(byte aSide, long aVoltage, long aAmperage);
	public boolean inputEnergyFrom(byte aSide);
	public boolean outputsEnergyTo(byte aSide);
	
	public static class Util {
		/** Rewritten to use the superior GT6 Power System with its superior Compatibility. :P */
		public static final long emitEnergyToNetwork(long aVoltage, long aAmperage, IEnergyConnected aEmitter) {
			long rUsedAmperage = 0;
			for (byte aSideOutOf : ALL_SIDES_VALID) if (aEmitter.outputsEnergyTo(aSideOutOf)) {
				DelegatorTileEntity<TileEntity> tDelegator = WD.te(aEmitter.getWorld(), aEmitter.getOffsetX(aSideOutOf, 1), aEmitter.getOffsetY(aSideOutOf, 1), aEmitter.getOffsetZ(aSideOutOf, 1), OPOS[aSideOutOf], F);
				if (tDelegator.mTileEntity instanceof IColoredTileEntity && aEmitter.getColorization() >= 0) {
					byte tColor = ((IColoredTileEntity)tDelegator.mTileEntity).getColorization();
					if (tColor >= 0 && tColor != aEmitter.getColorization()) continue;
				}
				rUsedAmperage += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.EU, aVoltage, aAmperage-rUsedAmperage, aEmitter, tDelegator);
				if (rUsedAmperage >= aAmperage) break;
			}
			return rUsedAmperage;
		}
	}
}
