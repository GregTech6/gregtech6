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

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import gregapi.code.ArrayListNoNulls;
import gregapi.compat.ICompat;
import gregapi.util.CR;
import gregapi.util.UT;

/**
 * @author Gregorius Techneticies
 * 
 * Base Class used for all my Mods.
 * Can also be used for GT-API based Mods such as Addons ;)
 */
public abstract class Abstract_Mod {
	public static final List<Abstract_Mod> MODS_USING_GT_API = new ArrayListNoNulls<>();
	
	/** Contains the amount of GT API Mods. Better than doing a constant List size check. */
	public static int sModCountUsingGTAPI = 0;
	
	/** Contains the amount of Phases the GT API Mods have already ran through. */
	public static int
	sStartedPreInit         = 0, sFinishedPreInit           = 0,
	sStartedInit            = 0, sFinishedInit              = 0,
	sStartedPostInit        = 0, sFinishedPostInit          = 0,
	sFinalized              = 0;
	
	/** List of all Configuration Files for Auto-Saving. */
	public static final List<Runnable> sConfigs = new ArrayListNoNulls<>();
	
	// ------------------------------ non-static stuff ------------------------------
	
	/** Contains the Proxy Class of this Mod. Can be null if there is no Proxy. */
	public Abstract_Proxy mProxy;
	
	/** Contains the Phases the Mod has already ran through. */
	public boolean
	mStartedPreInit         = F, mFinishedPreInit           = F,
	mStartedInit            = F, mFinishedInit              = F,
	mStartedPostInit        = F, mFinishedPostInit          = F,
	mFinalized              = F;
	
	/** Contains the amount of Server Start/Stop Phases the Mod has already ran through. */
	public int
	mStartedServerStarting  = 0, mFinishedServerStarting    = 0,
	mStartedServerStarted   = 0, mFinishedServerStarted     = 0,
	mStartedServerStopping  = 0, mFinishedServerStopping    = 0,
	mStartedServerStopped   = 0, mFinishedServerStopped     = 0;
	
	/** Event Lists where you can hook into the loading order of the Code, without having to care much about regular Mod load order. Note, that these Lists will be cleared and then set to null, right after they got executed once, in order to clean up some RAM. */
	public List<Runnable>
	mBeforePreInit          = new ArrayListNoNulls<>(), mAfterPreInit        = new ArrayListNoNulls<>(),
	mBeforeInit             = new ArrayListNoNulls<>(), mAfterInit           = new ArrayListNoNulls<>(),
	mBeforePostInit         = new ArrayListNoNulls<>(), mAfterPostInit       = new ArrayListNoNulls<>(),
	mFinalize               = new ArrayListNoNulls<>();
	
	/** Event Lists where you can hook into the loading order of the Code, without having to care much about regular Mod load order. */
	public final List<Runnable>
	mBeforeServerStarting   = new ArrayListNoNulls<>(), mAfterServerStarting = new ArrayListNoNulls<>(),
	mBeforeServerStarted    = new ArrayListNoNulls<>(), mAfterServerStarted  = new ArrayListNoNulls<>(),
	mBeforeServerStopping   = new ArrayListNoNulls<>(), mAfterServerStopping = new ArrayListNoNulls<>(),
	mBeforeServerStopped    = new ArrayListNoNulls<>(), mAfterServerStopped  = new ArrayListNoNulls<>();
	
	public final List<ICompat>
	mCompatClasses          = new ArrayListNoNulls<>();
	
	public Abstract_Mod() {
		sModCountUsingGTAPI++;
		MODS_USING_GT_API.add(this);
	}
	
	/** Return the Mod ID */
	public abstract String getModID();
	/** Return the Mod Name */
	public abstract String getModName();
	/** Used for logging purposes. */
	public abstract String getModNameForLog();
	/** Return the actual Proxy. Note: DO NOT RETURN mProxy! */
	public abstract Abstract_Proxy getProxy();
	/** Called on PreInit */
	public abstract void onModPreInit2(FMLPreInitializationEvent aEvent);
	/** Called on Init */
	public abstract void onModInit2(FMLInitializationEvent aEvent);
	/** Called on PostInit */
	public abstract void onModPostInit2(FMLPostInitializationEvent aEvent);
	/** Called on Server Start, NEVER DO ANYTHING LAGGY HERE!!! */
	public abstract void onModServerStarting2(FMLServerStartingEvent aEvent);
	/** Called after Server Start, NEVER DO ANYTHING LAGGY HERE!!! */
	public abstract void onModServerStarted2(FMLServerStartedEvent aEvent);
	/** Called on Server Stop */
	public abstract void onModServerStopping2(FMLServerStoppingEvent aEvent);
	/** Called after Server Stop */
	public abstract void onModServerStopped2(FMLServerStoppedEvent aEvent);
	
	/** Called after the Last GT PreInit Phase happened. */
	public void onModFinalPreInit(FMLPreInitializationEvent aEvent) {/**/}
	/** Called after the Last GT Init Phase happened. */
	public void onModFinalInit(FMLInitializationEvent aEvent) {/**/}
	/** Called after the Last GT PostInit Phase happened. */
	public void onModFinalPostInit(FMLPostInitializationEvent aEvent) {/**/}
	
	
	@Override public String toString() {return getModID();}
	
	public void loadRunnables(String aName, List<Runnable> aList) {
		if (aList.isEmpty()) return;
		UT.LoadingBar.start(aName, aList.size());
		for (Runnable tRunnable : aList) {
			String tString = tRunnable.toString();
			UT.LoadingBar.step(UT.Code.stringValid(tString)?tString:"UNNAMED");
			try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
		}
		UT.LoadingBar.finish();
	}
	
	public void loadRunnables(List<Runnable> aList) {
		for (Runnable tRunnable : aList) try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	// Just add Calls to these from within your Mods load phases.
	
	public void onModPreInit(FMLPreInitializationEvent aEvent) {
		if (mStartedPreInit) return;
		try {
			mProxy = getProxy();
			OUT.println(getModNameForLog() + ": ======================");
			ORD.println(getModNameForLog() + ": ======================");
			
			loadRunnables("Before PreInit", mBeforePreInit); mBeforePreInit.clear(); mBeforePreInit = null;
			
			OUT.println(getModNameForLog() + ": PreInit-Phase started!");
			ORD.println(getModNameForLog() + ": PreInit-Phase started!");
			
			mStartedPreInit = T;
			sStartedPreInit++;
			if (mProxy != null) mProxy.onProxyBeforePreInit(this, aEvent);
			onModPreInit2(aEvent);
			if (mProxy != null) mProxy.onProxyAfterPreInit(this, aEvent);
			sFinishedPreInit++;
			mFinishedPreInit = T;
			
			OUT.println(getModNameForLog() + ": PreInit-Phase finished!");
			ORD.println(getModNameForLog() + ": PreInit-Phase finished!");
			
			if (!mCompatClasses.isEmpty()) {
				UT.LoadingBar.start("Loading Compat (PreInit)", mCompatClasses.size());
				for (ICompat tCompat : mCompatClasses) {
					String tString = tCompat.toString();
					UT.LoadingBar.step(UT.Code.stringValid(tString)?tString:"UNNAMED");
					try {tCompat.onPreLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
				}
				UT.LoadingBar.finish();
			}
			
			loadRunnables("After PreInit", mAfterPreInit); mAfterPreInit.clear(); mAfterPreInit = null;
			
			loadRunnables("Saving Configs", sConfigs);
			
			if (sFinishedPreInit >= sModCountUsingGTAPI) for (Abstract_Mod tMod : MODS_USING_GT_API) try {tMod.onModFinalPreInit(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
			
			OUT.println(getModNameForLog() + ": =======================");
			ORD.println(getModNameForLog() + ": =======================");
		} catch(Throwable e) {
			loadRunnables("Saving Configs after Exception!", sConfigs);
			e.printStackTrace(ERR);
			throw new RuntimeException(e);
		}
	}
	
	public void onModInit(FMLInitializationEvent aEvent) {
		if (mStartedInit) return;
		try {
			OUT.println(getModNameForLog() + ": ===================");
			ORD.println(getModNameForLog() + ": ===================");
			
			loadRunnables("Before Init", mBeforeInit); mBeforeInit.clear(); mBeforeInit = null;
			
			OUT.println(getModNameForLog() + ": Init-Phase started!");
			ORD.println(getModNameForLog() + ": Init-Phase started!");
			
			mStartedInit = T;
			sStartedInit++;
			if (mProxy != null) mProxy.onProxyBeforeInit(this, aEvent);
			onModInit2(aEvent);
			if (mProxy != null) mProxy.onProxyAfterInit(this, aEvent);
			sFinishedInit++;
			mFinishedInit = T;
			
			OUT.println(getModNameForLog() + ": Init-Phase finished!");
			ORD.println(getModNameForLog() + ": Init-Phase finished!");
			
			if (!mCompatClasses.isEmpty()) {
				UT.LoadingBar.start("Loading Compat (Init)", mCompatClasses.size());
				for (ICompat tCompat : mCompatClasses) {
					String tString = tCompat.toString();
					UT.LoadingBar.step(UT.Code.stringValid(tString)?tString:"UNNAMED");
					try {tCompat.onLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
				}
				UT.LoadingBar.finish();
			}
			
			loadRunnables("After Init", mAfterInit); mAfterInit.clear(); mAfterInit = null;
			
			loadRunnables("Saving Configs", sConfigs);
			
			if (sFinishedInit >= sModCountUsingGTAPI) for (Abstract_Mod tMod : MODS_USING_GT_API) try {tMod.onModFinalInit(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
			
			OUT.println(getModNameForLog() + ": ====================");
			ORD.println(getModNameForLog() + ": ====================");
		} catch(Throwable e) {
			loadRunnables("Saving Configs after Exception!", sConfigs);
			e.printStackTrace(ERR);
			throw new RuntimeException(e);
		}
	}
	
	public void onModPostInit(FMLPostInitializationEvent aEvent) {
		if (mStartedPostInit) return;
		try {
			OUT.println(getModNameForLog() + ": =======================");
			ORD.println(getModNameForLog() + ": =======================");
			
			loadRunnables("Before PostInit", mBeforePostInit); mBeforePostInit.clear(); mBeforePostInit = null;
			
			OUT.println(getModNameForLog() + ": PostInit-Phase started!");
			ORD.println(getModNameForLog() + ": PostInit-Phase started!");
			
			mStartedPostInit = T;
			sStartedPostInit++;
			if (mProxy != null) mProxy.onProxyBeforePostInit(this, aEvent);
			onModPostInit2(aEvent);
			if (mProxy != null) mProxy.onProxyAfterPostInit(this, aEvent);
			sFinishedPostInit++;
			mFinishedPostInit = T;
			
			OUT.println(getModNameForLog() + ": PostInit-Phase finished!");
			ORD.println(getModNameForLog() + ": PostInit-Phase finished!");
			
			if (!mCompatClasses.isEmpty()) {
				UT.LoadingBar.start("Loading Compat (PostInit)", mCompatClasses.size());
				for (ICompat tCompat : mCompatClasses) {
					String tString = tCompat.toString();
					UT.LoadingBar.step(UT.Code.stringValid(tString)?tString:"UNNAMED");
					try {tCompat.onPostLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
				}
				UT.LoadingBar.finish();
			}
			
			loadRunnables("After PostInit", mAfterPostInit); mAfterPostInit.clear(); mAfterPostInit = null;
			
			loadRunnables("Finalize", mFinalize); mFinalize.clear(); mFinalize = null;
			
			if (sFinishedPostInit >= sModCountUsingGTAPI) for (Abstract_Mod tMod : MODS_USING_GT_API) try {tMod.onModFinalPostInit(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
			
			sFinalized++;
			mFinalized = T;
			
			if (sFinalized >= sModCountUsingGTAPI) {
				CR.stopBuffering();
			}
			
			loadRunnables("Saving Configs", sConfigs);
			
			OUT.println(getModNameForLog() + ": ========================");
			ORD.println(getModNameForLog() + ": ========================");
		} catch(Throwable e) {
			loadRunnables("Saving Configs after Exception!", sConfigs);
			e.printStackTrace(ERR);
			throw new RuntimeException(e);
		}
	}
	
	public void onModServerStarting(FMLServerStartingEvent aEvent) {
		loadRunnables(mBeforeServerStarting);
		mStartedServerStarting++;
		if (mProxy != null) mProxy.onProxyBeforeServerStarting(this, aEvent);
		onModServerStarting2(aEvent);
		for (ICompat tCompat : mCompatClasses) try {tCompat.onServerStarting(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (mProxy != null) mProxy.onProxyAfterServerStarting(this, aEvent);
		mFinishedServerStarting++;
		loadRunnables(mAfterServerStarting);
	}
	
	public void onModServerStarted(FMLServerStartedEvent aEvent) {
		loadRunnables(mBeforeServerStarted);
		mStartedServerStarted++;
		if (mProxy != null) mProxy.onProxyBeforeServerStarted(this, aEvent);
		onModServerStarted2(aEvent);
		for (ICompat tCompat : mCompatClasses) try {tCompat.onServerStarted(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (mProxy != null) mProxy.onProxyAfterServerStarted(this, aEvent);
		mFinishedServerStarted++;
		loadRunnables(mAfterServerStarted);
	}
	
	public void onModServerStopping(FMLServerStoppingEvent aEvent) {
		loadRunnables(mBeforeServerStopping);
		mStartedServerStopping++;
		if (mProxy != null) mProxy.onProxyBeforeServerStopping(this, aEvent);
		onModServerStopping2(aEvent);
		for (ICompat tCompat : mCompatClasses) try {tCompat.onServerStopping(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (mProxy != null) mProxy.onProxyAfterServerStopping(this, aEvent);
		mFinishedServerStopping++;
		loadRunnables(mAfterServerStopping);
	}
	
	public void onModServerStopped(FMLServerStoppedEvent aEvent) {
		loadRunnables(mBeforeServerStopped);
		mStartedServerStopped++;
		if (mProxy != null) mProxy.onProxyBeforeServerStopped(this, aEvent);
		onModServerStopped2(aEvent);
		for (ICompat tCompat : mCompatClasses) try {tCompat.onServerStopped(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (mProxy != null) mProxy.onProxyAfterServerStopped(this, aEvent);
		mFinishedServerStopped++;
		loadRunnables(mAfterServerStopped);
	}
}
