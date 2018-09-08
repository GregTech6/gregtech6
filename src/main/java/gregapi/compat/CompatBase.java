package gregapi.compat;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

public abstract class CompatBase implements ICompat {
	@Override public void onPreLoad				(FMLPreInitializationEvent aEvent) {/**/}
	@Override public void onLoad				(FMLInitializationEvent aEvent) {/**/}
	@Override public void onPostLoad			(FMLPostInitializationEvent aEvent) {/**/}
	@Override public void onServerStarting		(FMLServerStartingEvent aEvent) {/**/}
	@Override public void onServerStarted		(FMLServerStartedEvent aEvent) {/**/}
	@Override public void onServerStopping		(FMLServerStoppingEvent aEvent) {/**/}
	@Override public void onServerStopped		(FMLServerStoppedEvent aEvent) {/**/}
	@Override public void onIDChanging			(FMLModIdMappingEvent aEvent) {/**/}
}