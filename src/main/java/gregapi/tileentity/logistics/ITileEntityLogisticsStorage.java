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

package gregapi.tileentity.logistics;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityLogisticsStorage extends ITileEntityLogistics {
	/** @return 0 = Disabled, 1 = Generic, 2 = Semi-Filtered, 3 = Filtered */
	public int getLogisticsPriorityFluid();
	/** @return 0 = Disabled, 1 = Generic, 2 = Semi-Filtered, 3 = Filtered */
	public int getLogisticsPriorityItem();
	
	public Fluid getLogisticsFilterFluid();
	public ItemStack getLogisticsFilterItem();
}
