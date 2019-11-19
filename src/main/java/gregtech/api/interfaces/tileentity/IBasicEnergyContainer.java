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

package gregtech.api.interfaces.tileentity;

@Deprecated
@SuppressWarnings("deprecation")
/** Required to exist in GT6 because Immersive Engineering crashes otherwise. Also there is that GT5U+GT6 Mod that basically needs this for Compat. */
public interface IBasicEnergyContainer extends IEnergyConnected {
	public boolean isUniversalEnergyStored(long aEnergyAmount);
	public long getUniversalEnergyStored();
	public long getUniversalEnergyCapacity();
	public long getOutputAmperage();
	public long getOutputVoltage();
	public long getInputAmperage();
	public long getInputVoltage();
	public boolean decreaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooLessEnergy);
	public boolean increaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooMuchEnergy);
	public boolean drainEnergyUnits(byte aSide, long aVoltage, long aAmperage);
	public long getAverageElectricInput();
	public long getAverageElectricOutput();
	public long getStoredEU();
	public long getEUCapacity();
	public long getStoredSteam();
	public long getSteamCapacity();
	public boolean increaseStoredSteam(long aEnergy, boolean aIgnoreTooMuchEnergy);
}
