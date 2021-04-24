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

package gregapi.api;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

/**
 * @author Gregorius Techneticies
 * 
 * Base Proxy used for all my Mods.
 */
public abstract class Abstract_Proxy {
	public void onProxyBeforePreInit        (Abstract_Mod aMod, FMLPreInitializationEvent   aEvent) {/**/}
	public void onProxyBeforeInit           (Abstract_Mod aMod, FMLInitializationEvent      aEvent) {/**/}
	public void onProxyBeforePostInit       (Abstract_Mod aMod, FMLPostInitializationEvent  aEvent) {/**/}
	/** NEVER DO ANYTHING LAGGY HERE!!! */
	public void onProxyBeforeServerStarting (Abstract_Mod aMod, FMLServerStartingEvent      aEvent) {/**/}
	/** NEVER DO ANYTHING LAGGY HERE!!! */
	public void onProxyBeforeServerStarted  (Abstract_Mod aMod, FMLServerStartedEvent       aEvent) {/**/}
	public void onProxyBeforeServerStopping (Abstract_Mod aMod, FMLServerStoppingEvent      aEvent) {/**/}
	public void onProxyBeforeServerStopped  (Abstract_Mod aMod, FMLServerStoppedEvent       aEvent) {/**/}
	
	public void onProxyAfterPreInit         (Abstract_Mod aMod, FMLPreInitializationEvent   aEvent) {/**/}
	public void onProxyAfterInit            (Abstract_Mod aMod, FMLInitializationEvent      aEvent) {/**/}
	public void onProxyAfterPostInit        (Abstract_Mod aMod, FMLPostInitializationEvent  aEvent) {/**/}
	/** NEVER DO ANYTHING LAGGY HERE!!! */
	public void onProxyAfterServerStarting  (Abstract_Mod aMod, FMLServerStartingEvent      aEvent) {/**/}
	/** NEVER DO ANYTHING LAGGY HERE!!! */
	public void onProxyAfterServerStarted   (Abstract_Mod aMod, FMLServerStartedEvent       aEvent) {/**/}
	public void onProxyAfterServerStopping  (Abstract_Mod aMod, FMLServerStoppingEvent      aEvent) {/**/}
	public void onProxyAfterServerStopped   (Abstract_Mod aMod, FMLServerStoppedEvent       aEvent) {/**/}
}
