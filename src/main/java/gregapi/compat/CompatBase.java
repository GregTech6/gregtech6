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

public abstract class CompatBase implements ICompat {
	@Override public void onPreLoad       (FMLPreInitializationEvent aEvent) {/**/}
	@Override public void onLoad          (FMLInitializationEvent aEvent) {/**/}
	@Override public void onPostLoad      (FMLPostInitializationEvent aEvent) {/**/}
	@Override public void onServerStarting(FMLServerStartingEvent aEvent) {/**/}
	@Override public void onServerStarted (FMLServerStartedEvent aEvent) {/**/}
	@Override public void onServerStopping(FMLServerStoppingEvent aEvent) {/**/}
	@Override public void onServerStopped (FMLServerStoppedEvent aEvent) {/**/}
	@Override public void onIDChanging    (FMLModIdMappingEvent aEvent) {/**/}
}
