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

package gregapi.oredict;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.code.ItemStackSet;
import gregapi.config.Config;
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.IPrefixItem;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.IOreDictListenerEvent.OreDictRegistrationContainer;
import gregapi.oredict.event.IOreDictListenerRecyclable;
import gregapi.oredict.event.IOreDictListenerRecyclable.OreDictRecyclingContainer;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerRegisterEvent;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

/**
 * @author Gregorius Techneticies
 * 
 * This Class is for filtering out the Private and Invalid OreDictionary Events and passing the proper ones to any interested User.
 * It also contains a lot of Utility, which was formerly inside the OreDictUnificator.
 */
public final class OreDictManager {
	public static final OreDictManager INSTANCE = new OreDictManager();
	
	/** Those Listeners can also be registered at certain Prefixes in order to only get their Events */
	private final Set<IOreDictListenerEvent> mGlobalOreDictListeners = new HashSetNoNulls<>();
	private final Set<OreDictRegistrationContainer> mGlobalRegistrations = new HashSetNoNulls<>();
	
	/** These Listeners always get notified if an Item gets a recyclable ItemData Tag attached to it. */
	private final Set<IOreDictListenerRecyclable> mRecyclableOreDictListeners = new HashSetNoNulls<>();
	private final Set<OreDictRecyclingContainer> mRecyclableRegistrations = new HashSetNoNulls<>();
	
	/** Put OreDict Strings which should be registered as something else, right here using addReRegistration */
	private final Map<String, Collection<String>> mReRegistrationMappings = new HashMap<>();
	/** Assign Item Data to certain OreDict Strings for recycling and similar. Used for plankWood and slabWood for example.*/
	private final Map<String, OreDictItemData> mStringToItemDataMappings = new HashMap<>();
	private final Map<String, Collection<OreDictMaterial>> mVisibilityTriggers = new HashMap<>();
	
	/** Lists all Names (with their Prefixes) which don't have a Material assigned to them. */
	private final Set<String> mUnknownMaterials = new HashSetNoNulls<>();
	/** Lists all Names which don't have a Prefix assigned to them. */
	private final Set<String> mUnknownNames = new HashSetNoNulls<>();
	/** Lists all Names which get automatically blacklisted. */
	private final Set<String> mAutoBlackListedNames = new HashSetNoNulls<>();
	/** Lists all Mods whiches Items get automatically blacklisted. Needed for TerraFirmaCraft. */
	private final Set<String> mAutoBlackListedMods = new HashSetNoNulls<>();
	/** Lists all Names which are definitely not unknown. */
	private final Set<String> mKnownNames = new HashSetNoNulls<>(F
	, "oreHeeInstabilityOrb", "oreHeeEndPowder", "oreHeeIgneousRock", "oreHeeStardust"
	, "rennetSource", "yeastBrewers", "yeastLager", "yeastBayanus", "yeastEthereal", "yeastOrigin"
	, "holystone", "darkStone", "whiteStone", "brightStone", "lavastone"
	, "mycelium", "podzol", "grass", "soulSand", "taintedSoil"
	, "snowLayer", "ice", "cloud"
	, "antiBlock", "transdimBlock"
	, "carpet", "pendant"
	, "tyrian", "arcaneAsh", "camoPaste", "burntQuartz", "KangarooPaw", "redstoneRoot", "pigment", "diamondShard", "eternalLifeEssence", "honeyDrop", "grubBee", "salmonRaw", "stringFluxed", "aquaRegia", "sludge", "lexicaBotania", "resourceTaint", "chainLink", "sulfuricAcid", "scribingTools", "bacon", "redalloyBundled", "bluestoneInsulated", "infusedteslatiteInsulated", "bluestoneBundled", "redalloyInsulated", "infusedteslatiteBundled", "universalCable", "laserReceptor", "laserEmitter", "laserFocus", "laserMirror", "mobEgg"
//  , "torchRedstoneActive"
	, "awesomeiteHammer", "awesomeCore"
	, "bPlaceholder", "bVial", "bRedString", "bEnderAirBottle", "bFlask", "brDeviceCyaniteProcessor", "prbackpack"
//  , "stonebrick", "stoneAndesiteBricks", "stoneDioriteChiseled", "stoneAndesiteChiseled", "stoneDioritePolished", "stoneAndesitePolished", "stoneDioriteBricks", "stoneBasaltChiseled", "stoneGraniteChiseled", "stoneBasaltBricks", "stoneBasaltPolished", "stoneGraniteBricks", "stoneGranitePolished"
//  , "stoneBrown", "stonePink", "stoneGreen", "stoneWhite", "stoneMagenta", "stoneBlue", "stoneOrange", "stonePurple", "stoneGray", "stoneYellow", "stoneLime", "stoneCyan", "stoneLightBlue", "stoneLightGray"
	, "diamondNugget", "gaiaIngot", "pebble", "treatedStick", "universalReactant", "matterDustRefined", "sourceVegetableOil", "matterDust", "drawerBasic"
	, "hempBrick", "hempBlock", "savehempBrick", "savehempBlock", "saveplatedHempBrick", "saveplatedHempBlock", "platedHempBrick", "platedHempBlock", "platedHemp", "savehemp", "saveplatedHemp"
	);
	/** Lists all Names which should not be processed due to technical Issues. */
	private final Set<String> mIgnoredNames = new HashSetNoNulls<>(F, "oreCompass", "oreBentonite", "oreFullersEarth", "oreKaolinite", "dustRefinedObsidian", "dustRefinedGlowstone", "oreNetherQuartz", "oreNetherite", "oreBasalticMineralSand", "oreLifeCrystal", "cropMaplesyrup", "oreTritanium", "oreDuranium", "plateLapis", "shardEntropy", "shardAir", "shardWater", "shardEarth", "shardFire", "shardOrder", "greggy_greg_do_please_kindly_stuff_a_sock_in_it", "halfSheetCylinderMetal", "halfSheetMetal", "quarterSheetMetal", "sheetCurvedFourMetal", "sheetCurvedOneMetal", "sheetCurvedThreeMetal", "sheetCurvedTwoMetal", "sheetCylinderMetal", "sheetMediumConeMetal", "sheetMetal", "sheetMicroConeMetal", "sheetMicroFinMetal", "sheetSmallConeMetal", "sheetSmallCylinderMetal", "sheetSmallFinMetal", "shirtSheetMetal", "rivetSheetMetal", "triangleSheetMetal");
	/** Lists all Names which have already been registered. Used for the OreDict Listeners, so that certain Recipes (such as Crafting) don't get registered twice.*/
	private final Set<String> mAlreadyRegisteredNames = new HashSetNoNulls<>();
	/** Used to check if Recipe Outputs accidentally contain uncopied OreDict Items. */
	private final Set<ItemStack> mAllRegisteredOres = new HashSetNoNulls<>();
	
	/** The Unification Config File */
	public Config mUnificationConfig;
	
	private boolean mIsRunningInIterationMode = F;
	
	private OreDictManager() {
		mIsRunningInIterationMode = T;
		for (String tOreName : OreDictionary.getOreNames()) for (ItemStack tOreStack : OreDictionary.getOres(tOreName, F)) onOreRegistration1(new OreRegisterEvent(tOreName, tOreStack));
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) onFluidContainerRegistration(new FluidContainerRegisterEvent(tData));
		mIsRunningInIterationMode = F;
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	/**
	 * This Function adds a global Listener for advanced OreDictionary Events.
	 * Unlike the Forge Event System, which replaced Eloraams better System, this Interface ensures, like the great old System, that also all the older Events, which have been called before your Listener-Registration, are getting passed onto your Listener.
	 */
	public void addListener(IOreDictListenerEvent aListener) {
		if (GAPI.mStartedPostInit) addListenerInternal(aListener); else mBufferedListeners1.add(aListener);
	}
	
	private Set<IOreDictListenerEvent> mBufferedListeners1 = new HashSetNoNulls<>();
	private void addListenerInternal(IOreDictListenerEvent aListener) {
		if (mGlobalOreDictListeners.add(aListener)) for (OreDictRegistrationContainer tEvent : mGlobalRegistrations) aListener.onOreRegistration(tEvent);
	}
	
	/**
	 * This Function adds a global Listener for Recycling Events. These Events let you create Reversal Recipes inside your Machines, and contain all the Components, which can be recycled out of the passed ItemStack.
	 * Unlike the Forge Event System, which replaced Eloraams better System, this Interface ensures, like the great old System, that also all the older Events, which have been called before your Listener-Registration, are getting passed onto your Listener.
	 */
	public void addListener(IOreDictListenerRecyclable aListener) {
		if (GAPI.mStartedPostInit) addListenerInternal(aListener); else mBufferedListeners2.add(aListener);
	}
	
	private Set<IOreDictListenerRecyclable> mBufferedListeners2 = new HashSetNoNulls<>();
	private void addListenerInternal(IOreDictListenerRecyclable aListener) {
		if (mRecyclableOreDictListeners.add(aListener)) for (OreDictRecyclingContainer tEvent : mRecyclableRegistrations) aListener.onRecycleableRegistration(new OreDictRecyclingContainer(tEvent));
	}
	
	public void onPostLoad() {
		if (GAPI.mStartedPostInit) {
			UT.LoadingBar.start("OreDict", 4);
			UT.LoadingBar.step("Unification Entries");
			registerUnificationEntries();
			UT.LoadingBar.step("Global Listeners");
			for (IOreDictListenerEvent aListener : mBufferedListeners1) addListenerInternal(aListener);
			mBufferedListeners1.clear();
			mBufferedListeners1 = null;
			UT.LoadingBar.step("Prefix Listeners");
			for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) tPrefix.onPostLoad();
			UT.LoadingBar.step("Recycling Listeners");
			for (IOreDictListenerRecyclable aListener : mBufferedListeners2) addListenerInternal(aListener);
			mBufferedListeners2.clear();
			mBufferedListeners2 = null;
			UT.LoadingBar.finish();
		}
	}
	
	public void fixStacksizes() {
		for (ItemStack tStack : mAllRegisteredOres) tStack.stackSize = 1;
	}
	
	/**
	 * Used to check if Recipe Outputs accidentally contain uncopied OreDict Items.
	 */
	public boolean isOreDictItem(ItemStack aStack) {
		return mAllRegisteredOres.contains(aStack);
	}
	
	/**
	 * Adds a re-registration for an Ore for with different Name.
	 */
	public boolean addReRegistrationWithReversal(Object aOreToReRegister, Object aAdditionalName) {
		// Why that Variable you say? Because the OR would kill the second call in its attempt to optimise the return value, and I don't want that second call to be killed.
		boolean rResult = addReRegistration(aOreToReRegister, aAdditionalName);
		return addReRegistration(aAdditionalName, aOreToReRegister) || rResult;
	}
	
	public boolean addAutoBlackListingForMod(Object aAutoBlackListedMod) {
		return mAutoBlackListedMods.add(aAutoBlackListedMod.toString());
	}
	
	public boolean addAutoBlackListing(Object aAutoBlackListedString) {
		addKnownName(aAutoBlackListedString);
		return mAutoBlackListedNames.add(aAutoBlackListedString.toString());
	}
	
	public boolean addKnownName(Object aOreDictName) {
		return mKnownNames.add(aOreDictName.toString());
	}
	
	public boolean addReRegistration(Object aOreToReRegister, Object aAdditionalName) {
		addKnownName(aOreToReRegister);
		addKnownName(aAdditionalName);
		if (aOreToReRegister.toString().equals(aAdditionalName.toString())) return F;
		Collection<String> tNames = mReRegistrationMappings.get(aOreToReRegister.toString());
		if (tNames == null) mReRegistrationMappings.put(aOreToReRegister.toString(), tNames = new HashSetNoNulls<>());
		if (tNames.add(aAdditionalName.toString())) {
			// You thought I would not have this Line, to make it possible to add Re-Registrations for already registered things, didn't you?
			for (ItemStack tOreStack : OreDictionary.getOres(aOreToReRegister.toString(), F)) registerOreSafe(aAdditionalName, tOreStack);
			return T;
		}
		return F;
	}
	
	public boolean triggerVisibility(Object aName) {
		Collection<OreDictMaterial> tVisibilityTriggers = mVisibilityTriggers.get(aName.toString());
		if (tVisibilityTriggers != null) for (OreDictMaterial tMaterial : tVisibilityTriggers) tMaterial.mHidden = F;
		return F;
	}
	
	public boolean addVisibilityTrigger(Object aName, OreDictMaterial aMaterial) {
		addKnownName(aName);
		Collection<OreDictMaterial> tNames = mVisibilityTriggers.get(aName.toString());
		if (tNames == null) mVisibilityTriggers.put(aName.toString(), tNames = new HashSetNoNulls<>());
		if (tNames.add(aMaterial)) {
			for (ItemStack tOreStack : OreDictionary.getOres(aName.toString(), F)) if (!(tOreStack.getItem() instanceof IPrefixItem)) aMaterial.mHidden = F;
			return T;
		}
		return F;
	}
	
	/**
	 * Assign Item Data to certain OreDict Strings for recycling and similar. Used for plankWood and slabWood for example.
	 */
	public boolean setAutomaticItemData(Object aOreDictName, OreDictItemData aData) {
		addKnownName(aOreDictName);
		mStringToItemDataMappings.put(aOreDictName.toString(), aData);
		for (ItemStack tStack : getOres(aOreDictName.toString(), F)) {
			addItemData_(tStack, OreDictItemData.copy(aData));
		}
		return T;
	}
	
	public OreDictItemData getAutomaticItemData(Object aOreDictName) {
		return OreDictItemData.copy(mStringToItemDataMappings.get(aOreDictName.toString()));
	}
	
	public Collection<String> getUnknownMaterials() {
		return new ArrayListNoNulls<>(mUnknownMaterials);
	}
	
	public Collection<String> getUnknownNames() {
		return new ArrayListNoNulls<>(mUnknownNames);
	}
	
	private static class OreDictEventContainer {
		protected final String mModID, mRegName;
		protected final OreRegisterEvent mEvent;
		
		protected OreDictEventContainer(String aModID, String aRegName, OreRegisterEvent aEvent) {
			mRegName = aRegName;
			mModID = aModID;
			mEvent = aEvent;
		}
	}
	
	private List<OreDictEventContainer> mBufferedRegistrations = new ArrayListNoNulls<>();
	
	/** Call from the GT_API Object in order to enable the Prefix/Material processing. */
	public void enableRegistrations() {
		if (GAPI.mStartedInit) {
			List<OreDictEventContainer> tBufferedRegistrations = mBufferedRegistrations;
			mBufferedRegistrations = null;
			// Yep, I used a Sampler to see why the hell this part lags so much, figures Mariculture eats most of the CPU in that.
			UT.LoadingBar.start(MD.MaCu.mLoaded ? "(Lags because Mariculture) OreDict" : "OreDict", tBufferedRegistrations.size());
			for (OreDictEventContainer tContainer : tBufferedRegistrations) {
				UT.LoadingBar.step(tContainer.mEvent.Name);
				onOreRegistration2(tContainer.mModID, tContainer.mRegName, tContainer.mEvent);
			}
			tBufferedRegistrations.clear();
			UT.LoadingBar.finish();
		}
	}
	
	/** Fluid Containers are not unificatable at all. Also I set Water Bottles to contain 0L of Water. */
	@SubscribeEvent
	public void onFluidContainerRegistration(FluidContainerRegisterEvent aFluidEvent) {
		if (aFluidEvent.data.filledContainer.getItem() == Items.potionitem && ST.meta_(aFluidEvent.data.filledContainer) == 0) aFluidEvent.data.fluid.amount = 0;
		addToBlacklist(aFluidEvent.data.emptyContainer);
		FL.set(aFluidEvent.data, F, F);
	}
	
	@SubscribeEvent
	public void onOreRegistration1(OreRegisterEvent aEvent) {
		ModContainer tContainer = Loader.instance().activeModContainer();
		String aModID = tContainer==null||mIsRunningInIterationMode?"UNKNOWN":tContainer.getModId();
		
		// I am very sure the OreDict actually checks for these cases, so I do not think this will ever trigger.
		if (aEvent.Ore == null) {ERR.println("ERROR: A NULL STACK from the Mod '" + aModID + "' has been registered to the OreDict as: " + aEvent.Name); return;}
		// I am very sure the OreDict actually checks for these cases, so I do not think this will ever trigger.
		if (aEvent.Ore.getItem() == null) {ERR.println("ERROR: A NULL ITEM from the Mod '" + aModID + "' has been registered to the OreDict as: " + aEvent.Name); return;}
		
		String aRegName = ST.regName(aEvent.Ore);
		
		// Yeah this definitely can happen, and I want to see it if any Mod fucks that one up, so I can potentially fix that..
		if (UT.Code.stringInvalid(aRegName)) {ERR.println("ERROR: " + aEvent.Ore.getItem().getClass() + " from the Mod '" + aModID + "' has been registered to the OreDict before being registered as an Item/Block with: " + aEvent.Name); return;}
		
		if (GT != null) {
			// Preventing Blizz, Blitz and Basalz Stuff from being registered wrongly to GT6.
			if (MD.TE_FOUNDATION.owns(aRegName, "material") && UT.Code.inside(1024, 1029, ST.meta_(aEvent.Ore)) && MD.TE_FOUNDATION.mID.equalsIgnoreCase(aModID)) return;
			// In order to fix a ThaumCraft Bug I have to ignore this registration under all circumstances. I registered it under the proper Name manually.
			// Note: This has been fixed on TC Side, so it can be removed in later MC versions.
			if (MD.TC  .owns(aRegName) &&  aEvent.Name.toLowerCase().endsWith("uicksilver")) return;
			// This Red/Redstone Alloy is violating two OreDict Materials at the same time with its Name and Composition, so I'm gonna keep it out of my System.
			if (MD.HBM .owns(aRegName) && (aEvent.Name.toLowerCase().endsWith("redalloy") || aEvent.Name.toLowerCase().endsWith("redstonealloy"))) return;
			// OreDictPrefix Conflict caused by Galacticraft fixing its OreDict Registrations a little bit late to use Plates instead of Compressed Stuff now.
			// Note: This can be removed in later MC Versions too, since Galacticraft either does not update or since it has already fixed itself by now.
			if (aRegName.length() >= 26 && aRegName.startsWith("Gala") && aEvent.Name.startsWith("plate")) {
				if (aRegName.equalsIgnoreCase("GalacticraftMars:item.itemBasicAsteroids")) return;
				if (aRegName.equalsIgnoreCase("GalaxySpace:item.CompressedPlates")) return;
				if (aRegName.equalsIgnoreCase("GalacticraftCore:item.basicItem")) return;
				if (aRegName.equalsIgnoreCase("GalacticraftMars:item.null")) return;
			}
			// Needed to fix a RotaryCraft value thing. Those are actually small piles of Dust.
			// I had to do this instead of convincing Reika, because it is only a GT balance Issue, that wouldn't exist without GT.
			// Basically those two things are outputted 4 times too much (1 Plank = 1 Pulp and not 4) and therefore would be small Piles of Dust and not regular ones.
			if (MD.RoC.owns(aRegName) && (aEvent.Name.equalsIgnoreCase("pulpWood") || aEvent.Name.equalsIgnoreCase("dustWood") || aEvent.Name.equalsIgnoreCase("dustWheat"))) {
				ItemStack tTargetStack = null, tFoundStack = null;
				// This iteration works btw only because RotaryCraft registers Stuff in PostInit instead of PreInit like it is supposed to.
				for (ItemStack tStack : OreDictionary.getOres(aEvent.Name, F)) if (ST.equal(tStack, aEvent.Ore)) tFoundStack = tStack; else tTargetStack = tStack;
				if (tTargetStack != null) {
					ST.set(tFoundStack, tTargetStack);
					ST.set(aEvent.Ore, tTargetStack);
				}
				return;
			}
		}
		
		// Fixing Thaumcraft checking for the wrong OreDict when chopping Wood with Golems. Oh and it doesn't check Wildcard either, so I'm gonna need to split that too.
		// Also there is a huge Issue within Thaumcraft itself that makes the whole OreDict check impossible, I fixed that in CompatTC.
		if (aEvent.Name.startsWith("log") && ST.block(aEvent.Ore) != NB) if (ST.meta_(aEvent.Ore) == W) for (int i = 0; i < 16; i++) registerOreSafe("woodLog", ST.copyMeta(i, aEvent.Ore)); else registerOreSafe("woodLog", aEvent.Ore);
		
		//ORD.println(aModID + " → " + aRegName + " → " + aEvent.Name);
		
		aEvent.Ore.stackSize = 1;
		
		mAllRegisteredOres.add(aEvent.Ore);
		
		if (!ST.isGT(aEvent.Ore)) triggerVisibility(aEvent.Name);
		
		if (aEvent.Name.contains(" ")) {
			registerOreSafe(aEvent.Name.replaceAll(" ", ""), aEvent.Ore);
		} else {
			if (!(mIgnoredNames.contains(aEvent.Name) || aEvent.Name.contains("|") || aEvent.Name.contains("*") || aEvent.Name.contains(":") || aEvent.Name.contains(".") || aEvent.Name.contains("$"))) {
				if (mBufferedRegistrations == null) {
					onOreRegistration2(aModID, aRegName, aEvent);
				} else {
					mBufferedRegistrations.add(new OreDictEventContainer(aModID, aRegName, aEvent));
				}
			}
			
			Collection<String> tReRegistrations = mReRegistrationMappings.get(aEvent.Name);
			if (tReRegistrations != null) for (String tName : tReRegistrations) registerOreSafe(tName, aEvent.Ore);
		}
		
		aEvent.Ore.stackSize = 1;
	}
	
	public void onOreRegistration2(String aModID, String aRegName, OreRegisterEvent aEvent) {
		OreDictPrefix aPrefix = null;
		OreDictMaterial aMaterial = null;
		
		if (aEvent.Ore.getItem() instanceof IOreDictOptimizedParsingItem) {
			aPrefix = ((IOreDictOptimizedParsingItem)aEvent.Ore.getItem()).getPrefix(ST.meta_(aEvent.Ore));
			aMaterial = ((IOreDictOptimizedParsingItem)aEvent.Ore.getItem()).getMaterial(ST.meta_(aEvent.Ore));
			if (aPrefix == null || aMaterial == null || !aEvent.Name.equals(aPrefix.mNameInternal+aMaterial.mNameInternal)) {aPrefix = null; aMaterial = null;}
		}
		
		if (mAutoBlackListedNames.contains(aEvent.Name) || mAutoBlackListedMods.contains(aModID)) addToBlacklist_(aEvent.Ore);
		
		if (aPrefix == null) aPrefix = OreDictPrefix.get(aEvent.Name);
		
		addItemData_(aEvent.Ore, OreDictItemData.copy(mStringToItemDataMappings.get(aEvent.Name)));
		
		boolean aNotAlreadyRegisteredName = mAlreadyRegisteredNames.add(aEvent.Name);
		
		if (aPrefix == null) {
			if (addKnownName(aEvent.Name)) mUnknownNames.add(aEvent.Name);
		} else {
			if (aPrefix != aPrefix.mTargetRegistration) {
				registerOreSafe(aEvent.Name.replaceFirst(aPrefix.mNameInternal, aPrefix.mTargetRegistration.mNameInternal), aEvent.Ore);
				return;
			}
			
			if (aPrefix.contains(TD.Prefix.UNIFICATABLE_RECIPES)) addToBlacklist_(aEvent.Ore);
			
			String tName = aEvent.Name.replaceFirst(aPrefix.mNameInternal, "");
			
			if (tName.length() > 0) {
				// This is a vanilla Forge Rule. I just make sure it gets applied. All Dyes no matter of which Colour have to be registered under the Name "dye" too.
				if (aPrefix == OP.dye) registerOreSafe("dye", aEvent.Ore);
				if (aPrefix == OP.flower) registerOreSafe("flower", aEvent.Ore);
				if (aPrefix == OP.plantGtFiber) registerOreSafe(OD.itemString, aEvent.Ore);
				
				if (aMaterial == null) aMaterial = OreDictMaterial.MATERIAL_MAP.get(tName);
				
				if (aPrefix == OP.ore || aPrefix.contains(TD.Prefix.MATERIAL_BASED)) {
					if (aMaterial == null) aMaterial = OreDictMaterial.createAutoInvalidMaterial(tName);
					if (aMaterial.contains(TD.Properties.AUTO_MATERIAL) && addKnownName(aEvent.Name)) mUnknownMaterials.add(aEvent.Name);
					if (aMaterial != aMaterial.mTargetRegistration) registerOreSafe(aPrefix.mNameInternal + aMaterial.mTargetRegistration.mNameInternal, aEvent.Ore);
					if (aMaterial.contains(TD.Properties.AUTO_BLACKLIST)) addToBlacklist_(aEvent.Ore);
					for (OreDictMaterial tReRegisteredMaterial : aMaterial.mReRegistrations) registerOreSafe(aPrefix.mNameInternal + tReRegisteredMaterial.mNameInternal, aEvent.Ore);
					if (!aMaterial.contains(TD.Properties.INVALID_MATERIAL)) {
						if (aPrefix == OP.rockGt && aMaterial.contains(TD.Properties.STONE)) registerOreSafe(OD.itemRock, aEvent.Ore);
						if (aPrefix != OP.ore && aPrefix.contains(TD.Prefix.STANDARD_ORE) && aMaterial.contains(TD.Properties.COMMON_ORE)) registerOreSafe(OP.ore.mNameInternal + aMaterial.mNameInternal, aEvent.Ore);
						if ((MD.TFC.mLoaded || MD.TFCP.mLoaded) && (aModID.equalsIgnoreCase(MD.TFC.mID) || aModID.equalsIgnoreCase(MD.TFCP.mID)) && aPrefix.contains(TD.Prefix.UNIFICATABLE)) {
							setTarget_(aPrefix, aMaterial, aEvent.Ore, T, T);
						} else if (aPrefix == OP.gem && MD.RH.mLoaded && aModID.equalsIgnoreCase(MD.RH.mID) && (!MD.ReC.mLoaded || aMaterial == MT.FluoriteBlack || !ANY.CaF2.mToThis.contains(aMaterial))) {
							setTarget_(aPrefix, aMaterial, aEvent.Ore, T, T);
						} else if (aPrefix == OP.ore) {
							addItemData_(aEvent.Ore, aPrefix.dat(aMaterial));
						} else if (aPrefix.contains(TD.Prefix.UNIFICATABLE)) {
							setTarget_(aPrefix, aMaterial, aEvent.Ore, F, T);
						}
					}
				}
			}
		}
		
		OreDictRegistrationContainer tRegistration = new OreDictRegistrationContainer(aPrefix, aMaterial, aEvent.Name, aEvent.Ore, aEvent, aModID, aRegName, aNotAlreadyRegisteredName);
		
		// Global Listeners. Those are usually direct Name->Recipe Systems, meaning they should have priority over Prefix based Stuff.
		for (IOreDictListenerEvent tListener : mGlobalOreDictListeners) tListener.onOreRegistration(tRegistration);
		
		// Prefix Stuff comes after Global Stuff
		if (aPrefix != null) aPrefix.onOreRegistration(tRegistration);
		
		mGlobalRegistrations.add(tRegistration);
	}
	
	// ================================================================ //
	// A lot of the Code was copy-pasted from the old OreDictUnificator //
	// ================================================================ //
	
	private final Map<String, ItemStack> sName2StackMap = new HashMap<>();
	private final Map<ItemStackContainer, OreDictItemData> sItemStack2DataMap = new ItemStackMap<>();
	private final ItemStackSet<ItemStackContainer> sNoUnificationSet = new ItemStackSet<>();
	private int isRegisteringOre = 0, isAddingOre = 0;
	
	/**
	 * The Blacklist just prevents the Item from being unificated into something else.
	 * Useful if you have things like the Industrial Diamond, which is better than regular Diamond, but also usable in absolutely all Diamond Recipes.
	 */
	public void addToBlacklist(ItemStack aStack) {
		if (ST.valid(aStack)) addToBlacklist_(aStack);
	}
	public void addToBlacklist_(ItemStack aStack) {
		if (sNoUnificationSet.add(new ItemStackContainer(aStack))) {
			OreDictItemData tData = getItemData_(aStack);
			if (tData != null) tData.mBlackListed = tData.mBlocked = T;
		}
	}
	
	public boolean isBlacklisted(ItemStack aStack) {
		return sNoUnificationSet.contains(aStack, T);
	}
	
	public boolean addTarget(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return setTarget(aPrefix, aMaterial, aStack, F, F, F);
	}
	public boolean addTarget_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return setTarget_(aPrefix, aMaterial, aStack, F, F, F);
	}
	
	public boolean setTarget(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return setTarget(aPrefix, aMaterial, aStack, T, F, T);
	}
	public boolean setTarget_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return setTarget_(aPrefix, aMaterial, aStack, T, F, T);
	}

	public boolean setTarget(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered) {
		return setTarget(aPrefix, aMaterial, aStack, aOverwrite, aAlreadyRegistered, T);
	}
	public boolean setTarget_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered) {
		return setTarget_(aPrefix, aMaterial, aStack, aOverwrite, aAlreadyRegistered, T);
	}
	
	public boolean setTarget(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered, boolean aIgnoreBlacklist) {
		if (aMaterial == null || aPrefix == null || ST.invalid(aStack) || ST.meta_(aStack) == W) return F;
		return setTarget_(aPrefix, aMaterial, aStack, aOverwrite, aAlreadyRegistered, aIgnoreBlacklist);
	}
	public boolean setTarget_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, boolean aOverwrite, boolean aAlreadyRegistered, boolean aIgnoreBlacklist) {
		isAddingOre++;
		(aStack=ST.amount(1, aStack)).setTagCompound(null);
		if (!aAlreadyRegistered) registerOre_(aPrefix.mNameInternal + aMaterial.mNameInternal, aStack);
		addAssociation_(aPrefix, aMaterial, aStack);
		if ((aIgnoreBlacklist || !isBlacklisted(aStack)) && (aOverwrite || ST.invalid(sName2StackMap.get(aPrefix.mNameInternal + aMaterial.mNameInternal)))) sName2StackMap.put(aPrefix.mNameInternal + aMaterial.mNameInternal, aStack);
		isAddingOre--;
		return T;
	}
	
	public ItemStack getFirstOre(Object aName, long aAmount) {
		if (UT.Code.stringInvalid(aName)) return null;
		ItemStack tStack = sName2StackMap.get(aName.toString());
		if (ST.valid(tStack)) return ST.amount(aAmount, tStack);
		return ST.size(aAmount, ST.copyFirst(getOres(aName, F).toArray()));
	}
	
	public ItemStack getStack(Object aName, long aAmount) {
		return getStack(aName, null, aAmount, T, T);
	}
	
	public ItemStack getStack(Object aName, ItemStack aReplacement, long aAmount) {
		return getStack(aName, aReplacement, aAmount, T, T);
	}
	
	public ItemStack getStack(OreDictPrefix aPrefix, OreDictMaterial aMaterial, long aAmount) {
		return getStack(aPrefix, aMaterial, null, aAmount);
	}
	
	public ItemStack getStack(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aReplacement, long aAmount) {
		if (GAPI.mStartedInit) return getStack(aPrefix.mNameInternal + aMaterial.mNameInternal, aReplacement, aAmount, F, F);
		ItemStack rStack = getStack(aPrefix.mNameInternal + aMaterial.mNameInternal, aReplacement, aAmount, F, F);
		if (rStack == null && aMaterial.mID >= 0 && aPrefix.isGeneratingItem(aMaterial) && !aPrefix.mRegisteredPrefixItems.isEmpty()) {
			return ST.make((Item)aPrefix.mRegisteredPrefixItems.get(0), aAmount, aMaterial.mID);
		}
		return rStack;
	}
	
	public ItemStack getStack(Object aName, ItemStack aReplacement, long aAmount, boolean aMentionPossibleTypos, boolean aNoInvalidAmounts) {
		if (aNoInvalidAmounts && aAmount < 1) return aReplacement;
		if (!sName2StackMap.containsKey(aName.toString()) && aMentionPossibleTypos) ERR.println("Unknown Key for Unification, Typo? " + aName);
		ItemStack rStack = sName2StackMap.get(aName.toString());
		if (rStack == null) rStack = getFirstOre(aName, aAmount);
		return rStack == null ? aReplacement : ST.amount(aAmount, rStack);
	}
	
	public ItemStack[] setStackArray(boolean aUseBlackList, ItemStack... aStacks) {
		for (int i = 0; i < aStacks.length; i++) aStacks[i] = getStack(aUseBlackList, aStacks[i]);
		return aStacks;
	}
	public ItemStack[] setStackArray_(boolean aUseBlackList, ItemStack... aStacks) {
		for (int i = 0; i < aStacks.length; i++) aStacks[i] = getStack_(aUseBlackList, aStacks[i]);
		return aStacks;
	}
	
	public ItemStack[] getStackArray(boolean aUseBlackList, Object... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = getStack(aUseBlackList, (ItemStack)aStacks[i]);
		return rStacks;
	}
	public ItemStack[] getStackArray_(boolean aUseBlackList, Object... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = getStack_(aUseBlackList, (ItemStack)aStacks[i]);
		return rStacks;
	}
	
	public ItemStack setStack(boolean aUseBlackList, ItemStack aStack) {
		if (ST.invalid(aStack)) return aStack;
		return setStack_(aUseBlackList, aStack);
	}
	public ItemStack setStack_(boolean aUseBlackList, ItemStack aStack) {
		ItemStack tStack = getStack_(aUseBlackList, aStack);
		if (tStack == null || ST.equal(aStack, tStack)) return aStack;
		aStack.func_150996_a(tStack.getItem());
		return ST.meta_(aStack, ST.meta_(tStack));
	}
	
	public ItemStack getStack(boolean aUseBlackList, ItemStack aStack) {
		if (ST.invalid(aStack)) return null;
		return getStack_(aUseBlackList, aStack);
	}
	public ItemStack getStack_(boolean aUseBlackList, ItemStack aStack) {
		OreDictItemData tAssociation = getAssociation_(aStack, F);
		ItemStack rStack = null;
		if (tAssociation == null || (aUseBlackList && tAssociation.mBlocked)) return ST.copy(aStack);
		if (tAssociation.mUnificationTarget == null) tAssociation.mUnificationTarget = sName2StackMap.get(tAssociation.toString());
		if (ST.invalid(rStack = ST.amount(aStack.stackSize, tAssociation.mUnificationTarget))) return ST.copy(aStack);
		rStack.setTagCompound(aStack.getTagCompound());
		return rStack;
	}
	
	public boolean equal(boolean aUseBlackList, ItemStack aUnificatableStack, ItemStack aStackToCheckAgainst) {
		if (ST.invalid(aUnificatableStack) || ST.invalid(aStackToCheckAgainst)) return F;
		return equal_(aUseBlackList, aUnificatableStack, aStackToCheckAgainst);
	}
	public boolean equal_(boolean aUseBlackList, ItemStack aUnificatableStack, ItemStack aStackToCheckAgainst) {
		if (ST.equal(aUnificatableStack, aStackToCheckAgainst, T)) return T;
		OreDictItemData tAssociation = getAssociation_(aUnificatableStack, T);
		if (tAssociation == null || (aUseBlackList && tAssociation.mBlocked)) return F;
		if (tAssociation.mUnificationTarget == null) tAssociation.mUnificationTarget = sName2StackMap.get(tAssociation.toString());
		return ST.valid(tAssociation.mUnificationTarget) && ST.equal(tAssociation.mUnificationTarget, aStackToCheckAgainst, T);
	}
	
	public boolean addItemData(ItemStack aStack, OreDictItemData aData) {
		if (ST.invalid(aStack)) return F;
		if (getItemData_(aStack) == null) return setItemData_(aStack, aData);
		return F;
	}
	public boolean addItemData_(ItemStack aStack, OreDictItemData aData) {
		if (getItemData_(aStack) == null && aData != null) return setItemData_(aStack, aData);
		return F;
	}
	
	public boolean setItemData(ItemStack aStack, OreDictItemData aData) {
		if (ST.invalid(aStack) || aData == null) return F;
		return setItemData_(aStack, aData);
	}
	public boolean setItemData_(ItemStack aStack, OreDictItemData aData) {
		OreDictItemData tData = getAssociation_(aStack, F);
		if (tData != null && tData.mMaterial.mMaterial != MT.Wood && tData.mMaterial.mMaterial != ANY.Wood) return F;
		if (aStack.stackSize > 1) {
			if (aData.mMaterial != null) aData.mMaterial.mAmount /= aStack.stackSize;
			for (OreDictMaterialStack tMaterial : aData.mByProducts) tMaterial.mAmount /= aStack.stackSize;
			aStack = ST.amount(1, aStack);
		}
		if (!aData.mBlackListed) aData.mBlackListed = isBlacklisted(aStack);
		if (!aData.mBlocked) aData.mBlocked = (aData.mBlackListed || ST.block(aStack) != NB || FL.getFluid(aStack, T) != null || (aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0));
		sItemStack2DataMap.put(new ItemStackContainer(aStack), aData);
		if (aData.hasValidMaterialData()) {
			long tValidMaterialAmount = aData.mMaterial.mMaterial.contains(TD.Processing.UNRECYCLABLE)?0:aData.mMaterial.mAmount>=0?aData.mMaterial.mAmount:U;
			for (OreDictMaterialStack tMaterial : aData.mByProducts) tValidMaterialAmount += tMaterial.mMaterial.contains(TD.Processing.UNRECYCLABLE)?0:tMaterial.mAmount>=0?tMaterial.mAmount:U;
			if (tValidMaterialAmount < U && COMPAT_IC2 != null) COMPAT_IC2.blacklist(aStack);
		}
		if (!aData.hasValidPrefixData() || aData.mPrefix.contains(TD.Prefix.RECYCLABLE)) {
			OreDictRecyclingContainer tRegistration = new OreDictRecyclingContainer(aStack, aData);
			for (IOreDictListenerRecyclable tListener : mRecyclableOreDictListeners) tListener.onRecycleableRegistration(tRegistration);
			mRecyclableRegistrations.add(tRegistration);
		}
		return T;
	}
	
	public OreDictItemData getItemData(ItemStack aStack) {
		return getItemData(aStack, F);
	}
	public OreDictItemData getItemData_(ItemStack aStack) {
		return getItemData_(aStack, F);
	}
	
	public OreDictItemData getItemData(ItemStack aStack, boolean aAllowOverride) {
		if (ST.invalid(aStack)) return null;
		return getItemData_(aStack, aAllowOverride);
	}
	public OreDictItemData getItemData_(ItemStack aStack, boolean aAllowOverride) {
		OreDictItemData rData = null;
		if (aAllowOverride) {
			OreDictItemData tData = null;
			NBTTagCompound tNBT = aStack.getTagCompound();
			if (tNBT != null && tNBT.hasKey(NBT_RECYCLING_MATS)) {
				List<OreDictMaterialStack> tList = OreDictMaterialStack.loadList(NBT_RECYCLING_MATS, tNBT);
				if (!tList.isEmpty()) rData = new OreDictItemData(tList.remove(0), tList.toArray(ZL_MS));
			}
			if (aStack.getItem() instanceof IOreDictItemDataOverrideItem) tData = ((IOreDictItemDataOverrideItem)aStack.getItem()).getOreDictItemData(aStack);
			if (tData != null) return rData != null ? new OreDictItemData(tData, rData) : tData;
			if (rData != null) return rData;
		}
		rData = sItemStack2DataMap.get(new ItemStackContainer(aStack));
		if (rData != null) return rData;
		rData = sItemStack2DataMap.get(new ItemStackContainer(aStack, W));
		if (rData != null) return rData;
		if (aAllowOverride && aStack.getItem().isDamageable()) {
			rData = sItemStack2DataMap.get(new ItemStackContainer(aStack, 0));
			if (rData != null && rData.mUseVanillaDamage) {
				OreDictMaterialStack[] tByProducts = new OreDictMaterialStack[rData.mByProducts.length];
				for (int i = 0; i < tByProducts.length; i++) tByProducts[i] = OM.stack(rData.mByProducts[i].mMaterial, UT.Code.units(aStack.getMaxDamage()-aStack.getItemDamage(), aStack.getMaxDamage(), rData.mByProducts[i].mAmount, F));
				rData = new OreDictItemData(OM.stack(rData.mMaterial.mMaterial, UT.Code.units(aStack.getMaxDamage()-aStack.getItemDamage(), aStack.getMaxDamage(), rData.mMaterial.mAmount, F)), tByProducts);
			}
		}
		return rData;
	}
	
	public boolean addAssociation(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		if (aPrefix == null || aMaterial == null || ST.invalid(aStack)) return F;
		return addAssociation_(aPrefix, aMaterial, aStack);
	}
	public boolean addAssociation_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		if (ST.meta_(aStack) == W) for (byte i = 0; i < 16; i++) setItemData_(ST.copyAmountAndMeta(1, i, aStack), new OreDictItemData(aPrefix, aMaterial));
		return setItemData_(aStack, new OreDictItemData(aPrefix, aMaterial));
	}
	
	public OreDictItemData getAssociation(ItemStack aStack, boolean aOverwrite) {
		if (ST.invalid(aStack)) return null;
		return getAssociation_(aStack, aOverwrite);
	}
	public OreDictItemData getAssociation_(ItemStack aStack, boolean aOverwrite) {
		OreDictItemData rData = getItemData_(aStack, aOverwrite);
		return rData != null && rData.hasValidPrefixMaterialData() && rData.mPrefix != OP.ore ? rData : null;
	}
	
	public static boolean isItemStackInstanceOf(ItemStack aStack, Object aName) {
		if (UT.Code.stringInvalid(aName) || ST.invalid(aStack)) return F;
		return isItemStackInstanceOf_(aStack, aName);
	}
	public static boolean isItemStackInstanceOf_(ItemStack aStack, Object aName) {
		for (ItemStack tOreStack : getOres(aName.toString(), F)) if (ST.equal(tOreStack, aStack, T)) return T;
		return F;
	}
	
	public static void registerOreSafe(Object aName, ItemStack aStack) {
		try {
			OreDictionary.registerOre(aName.toString(), aStack);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	public boolean registerOre(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		if (ST.invalid(aStack)) return F;
		return registerOre_(aPrefix, aMaterial, aStack);
	}
	public boolean registerOre_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return registerOre_(aPrefix.mNameInternal + aMaterial.mNameInternal, aStack);
	}
	
	public boolean registerOre(Object aName, ItemStack aStack) {
		if (UT.Code.stringInvalid(aName) || ST.invalid(aStack)) return F;
		return registerOre_(aName, aStack);
	}
	public boolean registerOre_(Object aName, ItemStack aStack) {
		if (Abstract_Mod.sStartedPostInit > 0) throw new IllegalStateException("Late OreDict Registration using GT OreDict Utility. Only @Init and @PreInit are allowed for this when you use this Function instead of the Forge one.");
		String tName = aName.toString();
		if (UT.Code.stringInvalid(tName)) return F;
		addKnownName(tName);
		List<ItemStack> tList = getOres(tName, F);
		for (int i = 0; i < tList.size(); i++) if (ST.equal(tList.get(i), aStack, T)) return F;
		isRegisteringOre++;
		OreDictionary.registerOre(tName, ST.amount(1, aStack));
		isRegisteringOre--;
		return T;
	}
	
	public boolean isRegisteringOres() {
		return isRegisteringOre > 0;
	}
	public boolean isAddingOres() {
		return isAddingOre > 0;
	}
	
	private void registerUnificationEntries() {
		for (OreDictItemData tPrefixMaterial : sItemStack2DataMap.values()) tPrefixMaterial.mUnificationTarget = null;
		for (OreDictRegistrationContainer tEvent : mGlobalRegistrations) {
			if (tEvent.mPrefix != null && tEvent.mMaterial != null && tEvent.mPrefix.contains(TD.Prefix.UNIFICATABLE) && !tEvent.mMaterial.contains(TD.Properties.INVALID_MATERIAL)) {
				addAssociation_(tEvent.mPrefix, tEvent.mMaterial, tEvent.mStack);
				setTarget_(tEvent.mPrefix, tEvent.mMaterial, tEvent.mStack, !(tEvent.mStack.getItem() instanceof IPrefixItem) && !tEvent.mModName.equals(MD.GAPI_POST.mID) && mUnificationConfig.get("specialunificationtargets." + tEvent.mModName, tEvent.mEvent.Name, F), T);
			}
		}
		Recipe.reInit();
	}
	
	/** @return a Copy of the OreDictionary.getOres() List */
	public static List<ItemStack> getOres(OreDictPrefix aPrefix, OreDictMaterial aMaterial, boolean aTransformWildcardBlocksTo16) {
		return getOres(aPrefix.mNameInternal + aMaterial.mNameInternal, aTransformWildcardBlocksTo16);
	}
	/** @return a Copy of the OreDictionary.getOres() List */
	public static List<ItemStack> getOres(OreDictPrefix aPrefix, OreDictMaterialStack aMaterial, boolean aTransformWildcardBlocksTo16) {
		return getOres(aPrefix.mNameInternal + aMaterial.mMaterial.mNameInternal, aTransformWildcardBlocksTo16);
	}
	
	/** @return a Copy of the OreDictionary.getOres() List */
	public static List<ItemStack> getOres(Object aOreName, boolean aTransformWildcardBlocksTo16) {
		String aName = aOreName==null?"":aOreName.toString();
		List<ItemStack> rList = new ArrayListNoNulls<>(), tList;
		if (UT.Code.stringValid(aName)) {
			if (aTransformWildcardBlocksTo16) {
				tList = OreDictionary.getOres(aName, F);
				for (ItemStack tStack : tList) {
					if (ST.meta_(tStack) == W && ST.block(tStack) != NB) {
						for (int i = 0; i < 16; i++) rList.add(ST.make(tStack.getItem(), 1, i));
					} else {
						rList.add(tStack);
					}
				}
			} else {
				rList.addAll(OreDictionary.getOres(aName, F));
			}
		}
		return rList;
	}
}
