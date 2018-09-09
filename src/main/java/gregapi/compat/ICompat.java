package gregapi.compat;

import java.util.Collection;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import gregapi.code.ArrayListNoNulls;


public interface ICompat {
	public static final Collection<ICompat> COMPAT_CLASSES = new ArrayListNoNulls();
	
	public void onPreLoad			(FMLPreInitializationEvent aEvent);
	public void onLoad				(FMLInitializationEvent aEvent);
	public void onPostLoad			(FMLPostInitializationEvent aEvent);
	public void onServerStarting	(FMLServerStartingEvent aEvent);
	public void onServerStarted		(FMLServerStartedEvent aEvent);
	public void onServerStopping	(FMLServerStoppingEvent aEvent);
	public void onServerStopped		(FMLServerStoppedEvent aEvent);
	public void onIDChanging		(FMLModIdMappingEvent aEvent);
}