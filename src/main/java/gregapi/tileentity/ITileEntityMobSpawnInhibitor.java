/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity;


import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityMobSpawnInhibitor extends ITileEntityUnloadable, ITileEntityErrorable {
	public boolean inhibitMobSpawn(LivingSpawnEvent.CheckSpawn aEvent, World aWorld, int aX, int aY, int aZ);
	public void onUnregisterInhibitor();
}
