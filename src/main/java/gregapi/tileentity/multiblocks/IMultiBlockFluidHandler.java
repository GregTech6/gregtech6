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

package gregapi.tileentity.multiblocks;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockFluidHandler extends ITileEntityMultiBlockController {
	public int fill                     (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluid, boolean aDoFill);
	public FluidStack drain             (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluid, boolean aDoDrain);
	public FluidStack drain             (MultiTileEntityMultiBlockPart aPart, byte aSide, int aAmountToDrain, boolean aDoDrain);
	public boolean canFill              (MultiTileEntityMultiBlockPart aPart, byte aSide, Fluid aFluid);
	public boolean canDrain             (MultiTileEntityMultiBlockPart aPart, byte aSide, Fluid aFluid);
	public FluidTankInfo[] getTankInfo  (MultiTileEntityMultiBlockPart aPart, byte aSide);
}
