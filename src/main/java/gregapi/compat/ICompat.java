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

package gregapi.compat;

import cpw.mods.fml.common.event.*;
import gregapi.code.ArrayListNoNulls;

import java.util.Collection;


public interface ICompat {
	public static final Collection<ICompat> COMPAT_CLASSES = new ArrayListNoNulls<>();
	
	public void onPreLoad       (FMLPreInitializationEvent aEvent);
	public void onLoad          (FMLInitializationEvent aEvent);
	public void onPostLoad      (FMLPostInitializationEvent aEvent);
	public void onServerStarting(FMLServerStartingEvent aEvent);
	public void onServerStarted (FMLServerStartedEvent aEvent);
	public void onServerStopping(FMLServerStoppingEvent aEvent);
	public void onServerStopped (FMLServerStoppedEvent aEvent);
	public void onIDChanging    (FMLModIdMappingEvent aEvent);
}
