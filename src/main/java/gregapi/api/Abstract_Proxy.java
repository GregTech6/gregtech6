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
	public void onProxyBeforePreInit		(Abstract_Mod aMod, FMLPreInitializationEvent	aEvent) {/**/}
	public void onProxyBeforeInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent) {/**/}
	public void onProxyBeforePostInit		(Abstract_Mod aMod, FMLPostInitializationEvent	aEvent) {/**/}
    public void onProxyBeforeServerStarting	(Abstract_Mod aMod, FMLServerStartingEvent		aEvent) {/**/}
    public void onProxyBeforeServerStarted	(Abstract_Mod aMod, FMLServerStartedEvent		aEvent) {/**/}
    public void onProxyBeforeServerStopping	(Abstract_Mod aMod, FMLServerStoppingEvent		aEvent) {/**/}
    public void onProxyBeforeServerStopped	(Abstract_Mod aMod, FMLServerStoppedEvent		aEvent) {/**/}
    
	public void onProxyAfterPreInit			(Abstract_Mod aMod, FMLPreInitializationEvent	aEvent) {/**/}
	public void onProxyAfterInit			(Abstract_Mod aMod, FMLInitializationEvent		aEvent) {/**/}
	public void onProxyAfterPostInit		(Abstract_Mod aMod, FMLPostInitializationEvent	aEvent) {/**/}
    public void onProxyAfterServerStarting	(Abstract_Mod aMod, FMLServerStartingEvent		aEvent) {/**/}
    public void onProxyAfterServerStarted	(Abstract_Mod aMod, FMLServerStartedEvent		aEvent) {/**/}
    public void onProxyAfterServerStopping	(Abstract_Mod aMod, FMLServerStoppingEvent		aEvent) {/**/}
    public void onProxyAfterServerStopped	(Abstract_Mod aMod, FMLServerStoppedEvent		aEvent) {/**/}
}