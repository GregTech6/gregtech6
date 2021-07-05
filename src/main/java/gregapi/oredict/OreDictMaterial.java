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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ICondition;
import gregapi.code.ITagDataContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.code.ModData;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
import gregapi.data.CS.IconsGT;
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TC;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.lang.LanguageHandler;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.oredict.configurations.OreDictConfigurationComponent;
import gregapi.oredict.listeners.IOreDictListenerItem;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.render.TextureSet;
import gregapi.util.OM;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public final class OreDictMaterial implements ITagDataContainer<OreDictMaterial>, ICondition<OreDictMaterial> {
	public static final Map<String, OreDictMaterial> MATERIAL_MAP = new HashMap<>();
	/** The Amount of the Stack represents how many Liters make up one Unit. */
	public static final Map<String, OreDictMaterialStack> FLUID_MAP = new HashMap<>();
	public static final OreDictMaterial[] MATERIAL_ARRAY = new OreDictMaterial[32767];
	public static final Set<OreDictMaterial> ALLOYS = new HashSetNoNulls<>();
	public static int sHashID = 0;
	private static final Set<String> INVALID_STRINGS_TO_START_A_MATERIAL_NAME = new HashSetNoNulls<>(Arrays.asList("Mul", "Div", "Rich", "Poor", "Raw", "Impure", "Pure", "Dirty", "Refined", "Tiny", "Small", "Normal", "Medium", "Large", "Huge", "Dense", "Alloy", "Head", "Tool", "Helmet", "Chestplate", "Leggings", "Boots", "Centrifuged", "Purified", "Quintuple", "Quadruple", "Triple", "Double", "Hot", "Uncut", "Polished", "Chipped", "Flawed", "Flawless", "Exquisite", "Gt", "Long", "Plasma", "Gas", "Liquid", "Solid", "Gem", "Dust", "Ingot", "Plate", "Block", "Leaves", "Sapling", "Mossy", "Brick", "Crack", "Chisel", "Broken", "Compact", "Curve", "Mixed", "Mixable"));
	
	/**
	 * If a Material with the same Name but without ID (-1) exists it will be replaced automatically, regardless of registration order.
	 * <BR><BR>
	 * In case there are two Materials with the same Name but a different ID, the one added later wins and overrides the spot in the HashMap.
	 * If this happens, then the earlier Material gets a Registration Target in order to let MetaItems work properly, even when they get overridden.
	 * That way there is no Danger to the World Save in having two or more Instances of the same Material with a different ID. But it wastes space, so try to avoid that!
	 * <BR><BR>
	 * Please avoid having the same ID for two different Materials. This would cause some nasty collisions. So choose one of the following ID Ranges, which has not been taken already! Also maybe your Name is already Listed, you should check for that too. If you need a Range to be assigned to, then you just ask me.
	 * <BR><BR>
	 * I have to use an ID based System, as Item MetaData is the only proper way to differentiate between Items reliably within all Mods.
	 * 
	 * <BR><BR> My ID Range, do not touch unless you are me.
	 * <BR>[    0: 9999] Gregorius Techneticies (And this API)
	 * 
	 * <BR><BR>The large Ranges (given to people, who I expect to add many new Materials)
	 * 
	 * <BR>[10000:10999] OvermindDL1
	 * <BR>[11000:11999] VirMan
	 * <BR>[12000:12999] EmperorHadriusIII
	 * <BR>[13000:13999] Qwertygiy
	 * <BR>[14000:14999] Lauren
	 * <BR>[15000:15999] Yuesha / GT6U
	 * <BR>[16000:16999] the next one who asks me (do not use unless I personally tell you to use this larger Range)
	 * <BR>[17000:17999] Free
	 * <BR>[18000:18999] Free
	 * <BR>[19000:19999] Free
	 * 
	 * <BR><BR>The medium Ranges (given to people, who I expect to add some Materials, but not that many)
	 * 
	 * <BR>[20000:20499] BloodAsp
	 * <BR>[20500:20999] Axelandre42
	 * <BR>[21000:21499] IceFrezze
	 * <BR>[21500:21999] the next one who asks me (do not use unless I personally tell you to use this medium Range)
	 * <BR>[22000:22499] Free
	 * <BR>[22500:22999] Free
	 * <BR>[23000:23499] Free
	 * <BR>[23500:23999] Free
	 * <BR>[24000:24499] Free
	 * <BR>[24500:24999] Free
	 * <BR>[25000:25499] Free
	 * <BR>[25500:25999] Free
	 * <BR>[26000:26499] Free
	 * <BR>[26500:26999] Free
	 * <BR>[27000:27499] Free
	 * <BR>[27500:27999] Free
	 * <BR>[28000:28499] Free
	 * <BR>[28500:28999] Free
	 * <BR>[29000:29499] Free
	 * <BR>[29500:29999] Free
	 * 
	 * <BR><BR>The smaller Ranges (given to people, who I expect to not add many new Materials)
	 * 
	 * <BR>[30000:30099] LinusPhoenix
	 * <BR>[30100:30199] ZL123
	 * <BR>[30200:30299] Mr10Movie
	 * <BR>[30300:30399] 28Smiles
	 * <BR>[30400:30499] Immibis
	 * <BR>[30500:30599] Mine_Sasha
	 * <BR>[30600:30699] Alkalus
	 * <BR>[30700:30799] Briareos81
	 * <BR>[30800:30899] SuperCoder79
	 * <BR>[30900:30999] the next one who asks me (do not use unless I personally tell you to use this smaller Range)
	 * <BR>[31000:31099] Free
	 * <BR>[31100:31199] Free
	 * <BR>[31200:31299] Free
	 * <BR>[31300:31399] Free
	 * <BR>[31400:31499] Free
	 * <BR>[31500:31599] Free
	 * <BR>[31600:31699] Free
	 * <BR>[31700:31799] Free
	 * <BR>[31800:31899] Free
	 * <BR>[31900:31999] Free
	 * 
	 * <BR><BR> The ID Range that is reserved for MineTweaker and alike Stuff, so people can create their own Materials if they desire to do so.
	 * <BR>[32000:32765] Those are Reserved for Custom Materials.
	 * 
	 * <BR><BR> Due to being from the Example Mod, I would not use that ID, as everyone who just blindly copypastes that Mod like an idiot, uses this ID.
	 * <BR>[32766] That one ID that is used for the Example Mod.
	 * 
	 * @param aID the MetaData an Item of this Material would have.
	 * <BR>Everything below 8000 is reserved for Chemical Elements/Anti-Elements and their Isotopes.
	 * <BR>Everything between [8000 and 9999] is reserved for GregTech itself (or the API itself), just to prevent Conflicts.
	 * <BR>This means every ID you insert must have at least 5 Digits!
	 * @param aNameOreDict the OreDictionary Name of this Material. If it contains Spaces or Minuses, those will be removed for the OreDictionary Name. The OreDict-Name will get capitalised!
	 */
	public static OreDictMaterial createMaterial(int aID, String aNameOreDict, String aLocalName) {
		aID = (aID < 0 || aID >= MATERIAL_ARRAY.length || aID == W ? -1 : aID);
		// Replace all Spaces and Minuses, and capitalise the String.
		aNameOreDict = UT.Code.capitalise(aNameOreDict.replaceAll(" ", "").replaceAll("-", "").replaceAll("'", "").replaceAll("/", ""));
		// That would cause really bad shit to happen.
		if (aNameOreDict.isEmpty())
		throw new IllegalArgumentException("This OreDict Name is not usable, due to being an empty String, after stripping all the minuses and spaces.");
		// Those Characters are used to declare private Prefixes, it is not a good Idea to use them as Material Name at all, since they would get ignored by my System automatically.
		if (aNameOreDict.contains("|") || aNameOreDict.contains("*") || aNameOreDict.contains(":") || aNameOreDict.contains(".") || aNameOreDict.contains("$"))
		throw new IllegalArgumentException("The Material Name contains at least one of the following five invalid Characters '|', '*', ':', '.' or '$'");
		// if the ID is 0 or greater, then check the Name of the Material a bit more precise, to prevent shit from happening.
		if (aID >= 0) {
			if (GAPI.mStartedInit)
			throw new IllegalStateException("Materials with a valid ID have to be initialised in PreInit or earlier!");
			
			if (Ch_N.contains(aNameOreDict.charAt(0)))
			throw new IllegalArgumentException("The OreDict Name '"+aNameOreDict+"' is not suitable for a valid Material. Choose a different one, which doesn't happen to start with a Numeral. You can always set the Local Name to your liking, but the internal Name must always be a proper one.");
			
			if (INVALID_STRINGS_TO_START_A_MATERIAL_NAME.contains(aNameOreDict))
			throw new IllegalArgumentException("The OreDict Name '"+aNameOreDict+"' is not suitable for a valid Material. Choose a different one, which doesn't happen to be a blacklisted Adjective. You can always set the Local Name to your liking, but the internal Name must always be a proper one.");
			
			for (String tInvalidString : INVALID_STRINGS_TO_START_A_MATERIAL_NAME) if (aNameOreDict.startsWith(tInvalidString))
			throw new IllegalArgumentException("The OreDict Name '"+aNameOreDict+"' is not suitable for a valid Material, as it conflicts with OreDict Prefixes. A better Name for your Material would be '"+UT.Code.capitalise(aNameOreDict.replaceFirst(tInvalidString, ""))+tInvalidString+"' with the '"+tInvalidString+"' at the end of the Material Name instead of the beginning."); 
		}
		OreDictMaterial rMaterial1 = MATERIAL_MAP.get(aNameOreDict);
		if (rMaterial1 == null) return new OreDictMaterial((short)aID, aNameOreDict, aLocalName);
		if (aID < 0) return rMaterial1;
		if (rMaterial1.mID == aID) {
			ERR.println("NOTICE: Two Materials used the same ID: " + aID + " - Names: " + aNameOreDict + " and " + rMaterial1.mNameInternal);
			return rMaterial1;
		}
		OreDictMaterial rMaterial2 = new OreDictMaterial((short)aID, aNameOreDict, aLocalName);
		rMaterial1.setRegistration(rMaterial2);
		return rMaterial2;
	}
	/** Used by the OreDictManager to automatically generate an Invalid Material from a Name, with a safeguard in case the Material actually exists already. */
	public static OreDictMaterial createAutoInvalidMaterial(String aNameOreDict) {
		OreDictMaterial rMaterial = createMaterial(-1, aNameOreDict, aNameOreDict);
		if (rMaterial.mID < 0) rMaterial.put(TD.Properties.INVALID_MATERIAL, TD.Properties.UNUSED_MATERIAL, TD.Properties.AUTO_BLACKLIST, TD.Properties.AUTO_MATERIAL);
		return rMaterial;
	}
	
	public static OreDictMaterial get(String aMaterialName, OreDictMaterial aDefault) {
		OreDictMaterial rMaterial = MATERIAL_MAP.get(aMaterialName);
		return rMaterial == null ? aDefault : get(rMaterial);
	}
	public static OreDictMaterial get(String aMaterialName) {
		return get(aMaterialName, MT.NULL);
	}
	public static OreDictMaterial get(long aMaterialID, OreDictMaterial aDefault) {
		if (aMaterialID < 0 || aMaterialID >= MATERIAL_ARRAY.length || aMaterialID == W) return aDefault;
		OreDictMaterial rMaterial = MATERIAL_ARRAY[(int)aMaterialID];
		return rMaterial == null ? aDefault : get(rMaterial);
	}
	public static OreDictMaterial get(long aMaterialID) {
		return get(aMaterialID, MT.NULL);
	}
	public static OreDictMaterial get(OreDictMaterial aMaterial) {
		while (aMaterial != aMaterial.mTargetRegistration) aMaterial = aMaterial.mTargetRegistration;
		return aMaterial;
	}
	
	/** The Index of this Material inside the Array. Negative for "Not in the Array" and therefore also for "Not Unificatable", 0 is the NULL Material so a > 0 check could be useful for you. */
	public final short mID;
	/** The HashCode for this Material. Fully independent from any ID this Material would be assigned to, UNLIKE ITEMS. */
	private final int mHashID;
	/** The OreDictionary Name of this Material */
	public final String mNameInternal;
	/** The localised Name for this Material including Spaces and Stuff. It Defaults to the internal Name (if you have Spaces inside the Internal Name then those will be included in the Local Name but not in the final internal Name). */
	public String mNameLocal;
	/** The Name of the Mod, which initially created this Material. */
	public ModData mOriginalMod = null;
	/** The Book from the Dictionaries about Materials. */
	public ItemStack mDictionaryBook = null;
	/** The Description inside the Material Dictionaries. */
	public String mDescription[] = null, mTooltipChemical = null;
	/** The amount of crushed Ores you get from an Ore Block. */
	public byte mOreMultiplier = 1, mOreProcessingMultiplier = 1;
	/** The time 1 Unit of this Material takes to burn in a vanilla Furnace. */
	public long mFurnaceBurnTime = 0;
	/** The Types Tools allowed, 0 = No Tools, 1 = Flint/Stone/Wood Tools, 2 = Early Tools, 3 = Advanced Tools */
	public byte mToolTypes = 0;
	/** The Quality of the Material as Tool Material (ranges from 0 to 15) */
	public byte mToolQuality = 0;
	/** The Durability of the Material in Tool Form */
	public long mToolDurability = 0;
	/** The Speed of the Material as mining Material */
	public float mToolSpeed = 1.0F;
	public float mHeatDamage = 0.0F;
	/** If this Material is hidden */
	public boolean mHidden = F;
	/** If this Material contains the Metallum Aspect. */
	public boolean mHasMetallum = F;
	/** g/cm^3 of this Material at Room Temperature. 0 Means that it is not determined. */
	public double mGramPerCubicCentimeter = 1.0;
	/** The Colors of this Material in its 4 different states. Any change to these 4 final Arrays will be reflected in the Color of the Material at that state. */
	public final short[] mRGBaSolid = new short[] {255,255,255,255}, mRGBaLiquid = new short[] {255,255,255,255}, mRGBaGas = new short[] {255,255,255,255}, mRGBaPlasma = new short[] {255,255,255,255};
	public final short[][] mRGBa = new short[][] {mRGBaSolid, mRGBaLiquid, mRGBaGas, mRGBaPlasma};
	/** Do not modify these Colors for effects! They are supposed to be final! */
	public final short[] fRGBaSolid = new short[] {255,255,255,255}, fRGBaLiquid = new short[] {255,255,255,255}, fRGBaGas = new short[] {255,255,255,255}, fRGBaPlasma = new short[] {255,255,255,255};
	public final short[][] fRGBa = new short[][] {fRGBaSolid, fRGBaLiquid, fRGBaGas, fRGBaPlasma};
	/** The energetic boundaries of this Material, with somewhat reasonable Defaults for a Solid. Data in Kelvin. */
	public long mMeltingPoint = 1000, mBoilingPoint = 3000, mPlasmaPoint = 10000;
	/** The Atomic Values of one Molecule of this Naterial, Defaults to Technetium. If the Element is Antimatter, then mProtons means Antiprotons and mElectrons means Positrons */
	public long mNeutrons = 55, mProtons = 43, mElectrons = 43, mMass = mNeutrons + mProtons;
	/** The Texture Sets for the Materials. */
	public List<IIconContainer> mTextureSetsBlock = TextureSet.SET_NONE[0].mList, mTextureSetsItems = TextureSet.SET_NONE[1].mList;
	/** The Texture used for placing in Molds, Basins and Anvils. Overridden by Stone, Lava, Glass and the likes. */
	public ITexture mTextureSolid = null, mTextureSmooth = null, mTextureMolten = null, mTextureDust = null, mTextureGem = null;
	/** List of ThaumCraft Aspects. */
	public final List<TC_AspectStack> mAspects = new ArrayListNoNulls<>(1);
	/** The List of Components this Material is made of */
	public IOreDictConfigurationComponent mComponents = null;
	/** The List of Materials this Material should register as too. This is for adding itself automatically to "OreDictionary List"-Materials such as "AnyCopper" or "AnyBronze" for example */
	public final Set<OreDictMaterial> mReRegistrations = new HashSetNoNulls<>(), mToThis = new HashSetNoNulls<>();
	/** The List of Materials this Material is outputting as Byproducts.*/
	public final List<OreDictMaterial> mByProducts = new ArrayListNoNulls<>();
	/** The List of Materials which have this as Alloy Component.*/
	public final Set<OreDictMaterial> mAlloyComponentReferences = new HashSetNoNulls<>();
	/** The List of Alloy Recipes which can create this Material. */
	public final List<IOreDictConfigurationComponent> mAlloyCreationRecipes = new ArrayListNoNulls<>();
	/** List of Achievements you get for creating an instance of this Material. */
	public final List<Achievement> mAchievementsForCreation = new ArrayListNoNulls<>();
	/** Contains the most useful Prefix made of 1 Unit for this Material. */
	public int mPriorityPrefixIndex = 0;
	/** Contains the most useful Prefix made of 1 Unit for this Material. */
	public OreDictPrefix mPriorityPrefix = null;
	/** The Material which is the target for Re-Registration. */
	public OreDictMaterial mTargetRegistration = this;
	/** The Material which is the target for selecting the preferred Tool Handle. */
	public OreDictMaterial mHandleMaterial = this;
	
	/** The Targets for certain kinds of Processing for this Material. */
	public OreDictMaterialStack
	mTargetCrushing     = OM.stack(this, U),
	mTargetPulver       = OM.stack(this, U),
	mTargetSmelting     = OM.stack(this, U),
	mTargetSolidifying  = OM.stack(this, U),
	mTargetSmashing     = OM.stack(this, U),
	mTargetCutting      = OM.stack(this, U),
	mTargetWorking      = OM.stack(this, U),
	mTargetForging      = OM.stack(this, U),
	mTargetBurning      = OM.stack(this, 0), // The remaining Material when being burned. Used for getting the Ashes.
	mTargetBending      = OM.stack(this, U),
	mTargetCompressing  = OM.stack(this, U);
	
	/** The Materials targetting this for certain kinds of Processing. */
	public final Set<OreDictMaterial>
	mTargetedCrushing     = new HashSetNoNulls<>(F, this),
	mTargetedPulver       = new HashSetNoNulls<>(F, this),
	mTargetedSmelting     = new HashSetNoNulls<>(F, this),
	mTargetedSolidifying  = new HashSetNoNulls<>(F, this),
	mTargetedSmashing     = new HashSetNoNulls<>(F, this),
	mTargetedCutting      = new HashSetNoNulls<>(F, this),
	mTargetedWorking      = new HashSetNoNulls<>(F, this),
	mTargetedForging      = new HashSetNoNulls<>(F, this),
	mTargetedBurning      = new HashSetNoNulls<>(F, this),
	mTargetedBending      = new HashSetNoNulls<>(F, this),
	mTargetedCompressing  = new HashSetNoNulls<>(F, this);
	
	/**  */
	public long mLiquidUnit = U, mGasUnit = U, mPlasmaUnit = U;
	/** References to this Materials Fluid States. The amount of the FluidStack equals one Material Unit. It is usually either 144 or 1000, but other amounts are possible. Use "Util.translateUnits" for easy Math. */
	public FluidStack mLiquid, mGas, mPlasma;
	/** The Tags for this Material */
	private final Set<TagData> mTags = new HashSetNoNulls<>();
	/** Stores the Tool and Armor Enchants */
	public final List<ObjectStack<Enchantment>> mEnchantmentTools = new ArrayListNoNulls<>(1), mEnchantmentArmors = new ArrayListNoNulls<>(1);
	
	private OreDictMaterial(short aID, String aNameInternal, String aNameLocal) {
		mID = aID;
		mNameInternal = aNameInternal;
		mNameLocal = aNameLocal;
		MATERIAL_MAP.put(mNameInternal, this);
		if (mID >= 0) MATERIAL_ARRAY[mID] = this;
		mHashID = sHashID++;
	}
	
	/** Sets the localised Name of this Material */
	public OreDictMaterial setLocal(String aNameLocal) {
		mNameLocal = aNameLocal == null ? mNameInternal : aNameLocal;
		return this;
	}
	
	/** Gets the localised Name of this Material, but only after the Init-Phase (since Materials are added during preInit). Otherwise defaults to the Name set by setLocal. */
	public String getLocal() {
		return LanguageHandler.translate("gt.material." + mNameInternal, mNameLocal);
	}
	
	/** Sets the original Mod of this Material */
	public OreDictMaterial setOriginalMod(ModData aOriginalMod) {
		mOriginalMod = aOriginalMod == null ? mOriginalMod : aOriginalMod;
		return this;
	}
	
	/** Sets the original Mod of this Material */
	public OreDictMaterial setOriginalMod(String aModID, String aModName) {
		mOriginalMod = new ModData(aModID, aModName);
		return this;
	}
	
	/** Adds Identical Names which are getting re-registered to this Material. returns this Material, not the newly created ones. */
	public OreDictMaterial addIdenticalNames(String... aNames) {
		for (String aName : aNames) addReRegistrations(createMaterial(-1, aName, aName).setRegistration(this));
		return this;
	}
	
	/** Adds additional Names which this Material is getting re-registered to. For example "AnyCopper" would be something "Copper" and "AnnealedCopper" are getting re-registered to, to make them interchangeable in some Recipes. */
	public OreDictMaterial addReRegistrations(OreDictMaterial... aMaterials) {
		for (OreDictMaterial aMaterial : aMaterials) if (mReRegistrations.add(aMaterial)) aMaterial.mToThis.add(this);
		return this;
	}
	
	/** Adds this Materials Name as an additional Name the passed Materials are getting re-registered to. For example "AnyCopper" would be something "Copper" and "AnnealedCopper" are getting re-registered to, to make them interchangeable in some Recipes. */
	public OreDictMaterial addReRegistrationToThis(OreDictMaterial... aMaterials) {
		for (OreDictMaterial aMaterial : aMaterials) aMaterial.addReRegistrations(this);
		return this;
	}
	
	/** The Re-Registration for the Ore Dictionary for invalid Materials */
	public OreDictMaterial setRegistration(OreDictMaterial aMaterial) {
		mTargetRegistration = aMaterial == null ? this : aMaterial.mTargetRegistration;
		put(TD.Properties.INVALID_MATERIAL);
		return this;
	}
	
	public OreDictMaterial hide() {
		mHidden = T;
		return this;
	}
	
	public OreDictMaterial hide(boolean aHidden) {
		mHidden = aHidden;
		return this;
	}
	
	public OreDictMaterial visName(String... aOreDictNames) {
		for (String tName : aOreDictNames) OreDictManager.INSTANCE.addVisibilityTrigger(tName, this);
		return this;
	}
	
	public OreDictMaterial visPrefix(String... aOreDictNames) {
		for (String tName : aOreDictNames) OreDictManager.INSTANCE.addVisibilityTrigger(tName + mNameInternal, this);
		return this;
	}
	
	public OreDictMaterial visDefault(OreDictMaterial... aMaterials) {
		if (mOriginalMod != null && mOriginalMod != MD.GAPI && mOriginalMod.mLoaded) return this;
		hide();
		visName("ore"+mNameInternal, "dust"+mNameInternal, "gem"+mNameInternal, "ingot"+mNameInternal, "plate"+mNameInternal, "stick"+mNameInternal);
		for (OreDictMaterial tMaterial : aMaterials) visName("ore"+tMaterial.mNameInternal, "dust"+tMaterial.mNameInternal, "gem"+tMaterial.mNameInternal, "ingot"+tMaterial.mNameInternal, "plate"+tMaterial.mNameInternal, "stick"+tMaterial.mNameInternal);
		return this;
	}
	
	public OreDictMaterial lens(byte aColor) {
		put(TD.ItemGenerator.LENSES);
		OreDictManager.INSTANCE.addReRegistration("lens"+mNameInternal, "craftingLens"+DYE_OREDICTS_POST[aColor]);
		return this;
	}
	
	public OreDictMaterial alloyCentrifuge() {
		return put(TD.Processing.CENTRIFUGE).alloySimple();
	}
	public OreDictMaterial alloyElectrolyzer() {
		return put(TD.Processing.ELECTROLYSER).alloySimple();
	}
	public OreDictMaterial alloySimple() {
		return put(TD.Compounds.ALLOY, TD.Compounds.DECOMPOSABLE, TD.Processing.CRUCIBLE_ALLOY);
	}
	
	public OreDictMaterial alloyCentrifuge(long aMelt) {
		return put(TD.Processing.CENTRIFUGE).alloySimple(aMelt);
	}
	public OreDictMaterial alloyElectrolyzer(long aMelt) {
		return put(TD.Processing.ELECTROLYSER).alloySimple(aMelt);
	}
	public OreDictMaterial alloySimple(long aMelt) {
		return put(TD.Compounds.ALLOY, TD.Compounds.DECOMPOSABLE, TD.Processing.CRUCIBLE_ALLOY).heat(aMelt);
	}
	
	public OreDictMaterial alloyCentrifuge(long aMelt, long aBoil) {
		return put(TD.Processing.CENTRIFUGE).alloySimple(aMelt, aBoil);
	}
	public OreDictMaterial alloyElectrolyzer(long aMelt, long aBoil) {
		return put(TD.Processing.ELECTROLYSER).alloySimple(aMelt, aBoil);
	}
	public OreDictMaterial alloySimple(long aMelt, long aBoil) {
		return put(TD.Compounds.ALLOY, TD.Compounds.DECOMPOSABLE, TD.Processing.CRUCIBLE_ALLOY).heat(aMelt, aBoil);
	}
	
	public OreDictMaterial alloyCentrifuge(OreDictMaterial aHeat) {
		return put(TD.Processing.CENTRIFUGE).alloySimple(aHeat);
	}
	public OreDictMaterial alloyElectrolyzer(OreDictMaterial aHeat) {
		return put(TD.Processing.ELECTROLYSER).alloySimple(aHeat);
	}
	public OreDictMaterial alloySimple(OreDictMaterial aHeat) {
		return put(TD.Compounds.ALLOY, TD.Compounds.DECOMPOSABLE, TD.Processing.CRUCIBLE_ALLOY).heat(aHeat);
	}
	
	public OreDictMaterial addAlloyingRecipe(IOreDictConfigurationComponent aConfiguration) {
		ALLOYS.add(this);
		for (OreDictMaterialStack tMaterial : aConfiguration.getUndividedComponents()) {
			if (tMaterial.mMaterial != MT.Air) {
				if (mMeltingPoint >= tMaterial.mMaterial.mBoilingPoint) mMeltingPoint = Math.max(C+20, tMaterial.mMaterial.mBoilingPoint-20);
				if (mMeltingPoint >= tMaterial.mMaterial.mBoilingPoint) ERR.println("The Alloy '" + mNameInternal + "' cannot be created due to the Melting Point being higher than the Boiling Point of its Component '" + tMaterial.mMaterial.mNameInternal + "'");
			}
			tMaterial.mMaterial.mAlloyComponentReferences.add(this);
		}
		mAlloyCreationRecipes.add(aConfiguration);
		return this;
	}
	
	/** Sets the Molecule Configuration or Components of this Material. Calculates the Average of the MainStats and sets them. */
	public OreDictMaterial setMoleculeConfiguration(IOreDictConfigurationComponent aComponents) {
		mComponents = aComponents;
		double tDivider = 0, tProtons = 0, tElectrons = 0, tNeutrons = 0, tMass = 0, tGramPerCubicCentimeter = 0, tMeltingPoint = 0, tBoilingPoint = 0, tPlasmaPoint = 0;
		for (OreDictMaterialStack tMaterial : aComponents.getComponents()) tDivider += tMaterial.mAmount;
		for (OreDictMaterialStack tMaterial : aComponents.getComponents()) {
			tProtons                += (tMaterial.mMaterial.mProtons                * tMaterial.mAmount) / UD;
			tElectrons              += (tMaterial.mMaterial.mElectrons              * tMaterial.mAmount) / UD;
			tNeutrons               += (tMaterial.mMaterial.mNeutrons               * tMaterial.mAmount) / UD;
			tMass                   += (tMaterial.mMaterial.mMass                   * tMaterial.mAmount) / UD;
			tGramPerCubicCentimeter += (tMaterial.mMaterial.mGramPerCubicCentimeter * tMaterial.mAmount) / UD;
			tMeltingPoint           += (tMaterial.mMaterial.mMeltingPoint           * tMaterial.mAmount) / tDivider;
			tBoilingPoint           += (tMaterial.mMaterial.mBoilingPoint           * tMaterial.mAmount) / tDivider;
			tPlasmaPoint            += (tMaterial.mMaterial.mPlasmaPoint            * tMaterial.mAmount) / tDivider;
		}
		mProtons = (long)tProtons;
		mElectrons = (long)tElectrons;
		mNeutrons = (long)tNeutrons;
		mMass = (long)tMass;
		mMeltingPoint = Math.max(1, (long)tMeltingPoint);
		mBoilingPoint = Math.max(mMeltingPoint+1, (long)tBoilingPoint);
		mPlasmaPoint = Math.max(mBoilingPoint+1, (long)tPlasmaPoint);
		mGramPerCubicCentimeter = tGramPerCubicCentimeter;
		
		if (!contains(TD.Compounds.APPROXIMATE) && containsAny(TD.Processing.UUM, TD.Compounds.DECOMPOSABLE)) {
			mTooltipChemical = "";
			ArrayListNoNulls<OreDictMaterialStack> tComponents = aComponents.getUndividedComponents();
			if (tComponents.size() == 1 && tComponents.get(0).mAmount == U) {
				mTooltipChemical = tComponents.get(0).mMaterial.mTooltipChemical;
			} else for (OreDictMaterialStack tMaterial : tComponents) {
				if (UT.Code.stringValid(tMaterial.mMaterial.mTooltipChemical) && !tMaterial.mMaterial.contains(TD.Compounds.APPROXIMATE)) {
					if (tMaterial.mMaterial.contains(TD.Atomic.ELEMENT) || tMaterial.mMaterial.mComponents == null || (tMaterial.mMaterial.mComponents.getUndividedComponents().size() == 1 && tMaterial.mMaterial.mComponents.getComponents().get(0).mAmount == U)) {
						mTooltipChemical += tMaterial.mMaterial.mTooltipChemical;
					} else {
						mTooltipChemical += "("+tMaterial.mMaterial.mTooltipChemical+")";
					}
				} else {
					mTooltipChemical += "("+tMaterial.mMaterial.mNameLocal+")";
				}
				if (tMaterial.mAmount > U) {
					if (tMaterial.mMaterial.mComponents == null) {
						mTooltipChemical += NUM_SUB[(int)UT.Code.bind(0, NUM_SUB.length-1, tMaterial.mAmount / U)];
					} else if ((tMaterial.mAmount / U) % tMaterial.mMaterial.mComponents.getCommonDivider() == 0) {
						if ((tMaterial.mAmount / U) / tMaterial.mMaterial.mComponents.getCommonDivider() > 1) {
							mTooltipChemical += NUM_SUB[(int)UT.Code.bind(0, NUM_SUB.length-1, (tMaterial.mAmount / U) / tMaterial.mMaterial.mComponents.getCommonDivider())];
						} else {
							// nothing to add in this case.
						}
					} else {
						mTooltipChemical += NUM_SUB[(int)UT.Code.bind(0, NUM_SUB.length-1, tMaterial.mAmount / U)] + "," + NUM_SUB[(int)UT.Code.bind(0, NUM_SUB.length-1, tMaterial.mMaterial.mComponents.getCommonDivider())];
					}
				}
			}
		}
		return this;
	}
	
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1) {
		if (aCommonDivider == 0) {
			long tAmount = aAmount1;
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2) {
		if (aCommonDivider == 0) {
			long tAmount = aAmount1+aAmount2;
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3) {
		if (aCommonDivider == 0) {
			long tAmount = aAmount1+aAmount2+aAmount3;
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2), OM.stack(aMaterial3, aAmount3)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4) {
		if (aCommonDivider == 0) {
			long tAmount = aAmount1+aAmount2+aAmount3+aAmount4;
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2), OM.stack(aMaterial3, aAmount3), OM.stack(aMaterial4, aAmount4)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5) {
		if (aCommonDivider == 0) {
			long tAmount = aAmount1+aAmount2+aAmount3+aAmount4+aAmount5;
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2), OM.stack(aMaterial3, aAmount3), OM.stack(aMaterial4, aAmount4), OM.stack(aMaterial5, aAmount5)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6) {
		long tAmount = aAmount1+aAmount2+aAmount3+aAmount4+aAmount5+aAmount6;
		if (aCommonDivider == 0) {
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2), OM.stack(aMaterial3, aAmount3), OM.stack(aMaterial4, aAmount4), OM.stack(aMaterial5, aAmount5), OM.stack(aMaterial6, aAmount6)));
	}
	public OreDictMaterial setMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6, OreDictMaterial aMaterial7, long aAmount7) {
		long tAmount = aAmount1+aAmount2+aAmount3+aAmount4+aAmount5+aAmount6+aAmount7;
		if (aCommonDivider == 0) {
			aCommonDivider = tAmount / U;
			if (tAmount % U != 0) ERR.println("WARNING: Material '"+mNameInternal+"' has an Amount of " + tAmount + " Components and automatically generates a divider, that is leaving a tiny rest after the division, breaking some Material Amounts. Manual setting of Variables is required.");
		}
		return setMoleculeConfiguration(new OreDictConfigurationComponent(aCommonDivider, OM.stack(aMaterial1, aAmount1), OM.stack(aMaterial2, aAmount2), OM.stack(aMaterial3, aAmount3), OM.stack(aMaterial4, aAmount4), OM.stack(aMaterial5, aAmount5), OM.stack(aMaterial6, aAmount6), OM.stack(aMaterial7, aAmount7)));
	}
	
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1) {
		if (aMaterial1.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM) && aMaterial3.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM) && aMaterial3.contains(TD.Processing.UUM) && aMaterial4.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM) && aMaterial3.contains(TD.Processing.UUM) && aMaterial4.contains(TD.Processing.UUM) && aMaterial5.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM) && aMaterial3.contains(TD.Processing.UUM) && aMaterial4.contains(TD.Processing.UUM) && aMaterial5.contains(TD.Processing.UUM) && aMaterial6.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6);
	}
	public OreDictMaterial uumMcfg(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6, OreDictMaterial aMaterial7, long aAmount7) {
		if (aMaterial1.contains(TD.Processing.UUM) && aMaterial2.contains(TD.Processing.UUM) && aMaterial3.contains(TD.Processing.UUM) && aMaterial4.contains(TD.Processing.UUM) && aMaterial5.contains(TD.Processing.UUM) && aMaterial6.contains(TD.Processing.UUM) && aMaterial7.contains(TD.Processing.UUM)) {
			put(TD.Processing.UUM);
		} else {
			ERR.println("WARNING: " + mNameInternal + " has a UUM Config with impossible Materials.");
		}
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6, aMaterial7, aAmount7);
	}
	
	// Yes it is spelled Aloy because of being Four Letters long, for alignment reasons.
	
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6).alloyCentrifuge();
	}
	public OreDictMaterial setAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6, OreDictMaterial aMaterial7, long aAmount7) {
		return setMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6, aMaterial7, aAmount7).alloyCentrifuge();
	}
	
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6).alloyCentrifuge();
	}
	public OreDictMaterial uumAloy(long aCommonDivider, OreDictMaterial aMaterial1, long aAmount1, OreDictMaterial aMaterial2, long aAmount2, OreDictMaterial aMaterial3, long aAmount3, OreDictMaterial aMaterial4, long aAmount4, OreDictMaterial aMaterial5, long aAmount5, OreDictMaterial aMaterial6, long aAmount6, OreDictMaterial aMaterial7, long aAmount7) {
		return uumMcfg(aCommonDivider, aMaterial1, aAmount1, aMaterial2, aAmount2, aMaterial3, aAmount3, aMaterial4, aAmount4, aMaterial5, aAmount5, aMaterial6, aAmount6, aMaterial7, aAmount7).alloyCentrifuge();
	}
	
	@Deprecated public OreDictMaterial setTooltip(String aTooltip) {mTooltipChemical = aTooltip; return this;}
	
	public OreDictMaterial tooltip(String aTooltip) {
		mTooltipChemical = aTooltip;
		return this;
	}
	
	public OreDictMaterial handle(OreDictMaterial aHandle) {
		mHandleMaterial = aHandle;
		return this;
	}
	
	public OreDictMaterial setAllToTheOutputOf(OreDictMaterial aMaterial) {
		if (aMaterial == null) aMaterial = this;
		setPulver     (aMaterial.mTargetPulver     .mMaterial, aMaterial.mTargetPulver     .mAmount);
		setSmelting   (aMaterial.mTargetSmelting   .mMaterial, aMaterial.mTargetSmelting   .mAmount);
		setSolidifying(aMaterial.mTargetSolidifying.mMaterial, aMaterial.mTargetSolidifying.mAmount);
		setSmashing   (aMaterial.mTargetSmashing   .mMaterial, aMaterial.mTargetSmashing   .mAmount);
		setCutting    (aMaterial.mTargetCutting    .mMaterial, aMaterial.mTargetCutting    .mAmount);
		setWorking    (aMaterial.mTargetWorking    .mMaterial, aMaterial.mTargetWorking    .mAmount);
		setForging    (aMaterial.mTargetForging    .mMaterial, aMaterial.mTargetForging    .mAmount);
		setBurning    (aMaterial.mTargetBurning    .mMaterial, aMaterial.mTargetBurning    .mAmount);
		setBending    (aMaterial.mTargetBending    .mMaterial, aMaterial.mTargetBending    .mAmount);
		setCompressing(aMaterial.mTargetCompressing.mMaterial, aMaterial.mTargetCompressing.mAmount);
		return this;
	}
	
	public OreDictMaterial setAllToTheOutputOf(OreDictMaterial aMaterial, long aMultiplier, long aDivider) {
		if (aMaterial == null) aMaterial = this;
		setPulver     (aMaterial.mTargetPulver     .mMaterial,(aMaterial.mTargetPulver     .mAmount * aMultiplier) / aDivider);
		setSmelting   (aMaterial.mTargetSmelting   .mMaterial,(aMaterial.mTargetSmelting   .mAmount * aMultiplier) / aDivider);
		setSolidifying(aMaterial.mTargetSolidifying.mMaterial,(aMaterial.mTargetSolidifying.mAmount * aMultiplier) / aDivider);
		setSmashing   (aMaterial.mTargetSmashing   .mMaterial,(aMaterial.mTargetSmashing   .mAmount * aMultiplier) / aDivider);
		setCutting    (aMaterial.mTargetCutting    .mMaterial,(aMaterial.mTargetCutting    .mAmount * aMultiplier) / aDivider);
		setWorking    (aMaterial.mTargetWorking    .mMaterial,(aMaterial.mTargetWorking    .mAmount * aMultiplier) / aDivider);
		setForging    (aMaterial.mTargetForging    .mMaterial,(aMaterial.mTargetForging    .mAmount * aMultiplier) / aDivider);
		setBurning    (aMaterial.mTargetBurning    .mMaterial,(aMaterial.mTargetBurning    .mAmount * aMultiplier) / aDivider);
		setBending    (aMaterial.mTargetBending    .mMaterial,(aMaterial.mTargetBending    .mAmount * aMultiplier) / aDivider);
		setCompressing(aMaterial.mTargetCompressing.mMaterial,(aMaterial.mTargetCompressing.mAmount * aMultiplier) / aDivider);
		setCrushing   (aMaterial.mTargetCrushing   .mMaterial,(aMaterial.mTargetCrushing   .mAmount * aMultiplier) / aDivider);
		return this;
	}
	
	/** The result of trying to ore process it, if you want to disable ore processing, then set the Amount to 0. If aMaterial == null it will choose the previous Material instead, which is usually "this". */
	public OreDictMaterial setCrushing(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetCrushing.mMaterial.mTargetedCrushing.remove(this);
		mTargetCrushing = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedCrushing.add(this);
		return this;
	}
	
	/** The result of trying to pulverise it, if you want to disable pulverising, then set the Amount to 0. If aMaterial == null it will choose the previous Material instead, which is usually "this". */
	public OreDictMaterial setPulver(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetPulver.mMaterial.mTargetedPulver.remove(this);
		mTargetPulver = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedPulver.add(this);
		return this;
	}
	
	/** The result of trying to smelt it, if you want to disable smelting, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setSmelting(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetSmelting.mMaterial.mTargetedSmelting.remove(this);
		mTargetSmelting = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedSmelting.add(this);
		if (aAmount > 0) put(TD.Processing.MELTING);
		return this;
	}
	
	/** The result of cooling it down, if you want to disable cooling down, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setSolidifying(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetSolidifying.mMaterial.mTargetedSolidifying.remove(this);
		mTargetSolidifying = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedSolidifying.add(this);
		return this;
	}
	
	/** The result of trying to smash it, if you want to disable smashing, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setSmashing(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetSmashing.mMaterial.mTargetedSmashing.remove(this);
		mTargetSmashing = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedSmashing.add(this);
		return this;
	}
	
	/** The result of trying to cut it, if you want to disable cutting, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setCutting(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetCutting.mMaterial.mTargetedCutting.remove(this);
		mTargetCutting = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedCutting.add(this);
		return this;
	}
	
	/** The result of trying to craft with it, if you want to disable working, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setWorking(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetWorking.mMaterial.mTargetedWorking.remove(this);
		mTargetWorking = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedWorking.add(this);
		return this;
	}
	
	/** The result of trying to forge it, if you want to disable forging, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setForging(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetForging.mMaterial.mTargetedForging.remove(this);
		mTargetForging = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedForging.add(this);
		return this;
	}
	
	/** The result of trying to burn it (Ashes for example), if you want to disable burning, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setBurning(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetBurning.mMaterial.mTargetedBurning.remove(this);
		mTargetBurning = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedBurning.add(this);
		return this;
	}
	
	/** The result of trying to bend it, if you want to disable bending, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setBending(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetBending.mMaterial.mTargetedBending.remove(this);
		mTargetBending = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedBending.add(this);
		return this;
	}
	
	/** The result of trying to compress it, if you want to disable compressing, then set the Amount to 0. If aMaterial == null it will choose previous Material instead, which is usually "this". */
	public OreDictMaterial setCompressing(OreDictMaterial aMaterial, long aAmount) {
		if (aMaterial == null) aMaterial = this;
		mTargetCompressing.mMaterial.mTargetedCompressing.remove(this);
		mTargetCompressing = OM.stack(aMaterial, aAmount);
		aMaterial.mTargetedCompressing.add(this);
		return this;
	}
	
	@Deprecated public OreDictMaterial setQuality(float aToolSpeed, long aToolDurability, long aToolQuality) {return qual(3, aToolSpeed, aToolDurability, aToolQuality);}
	
	public OreDictMaterial qual(long aHarvestLevel) {return qual(mToolTypes, mToolSpeed, mToolDurability, aHarvestLevel);}
	public OreDictMaterial qual(float aSpeed, long aDurability, long aQuality) {return qual(3, aSpeed, aDurability, aQuality);}
	/** Sets the Tool Quality of this Material. */
	public OreDictMaterial qual(long aType, double aSpeed, long aDurability, long aQuality) {
		mToolTypes = UT.Code.bind2(aType);
		mToolDurability = Math.max(1, aDurability);
		mToolQuality = UT.Code.bind4(aQuality);
		mToolSpeed = (float)aSpeed;
		if (aType > 0) put(TD.Properties.HAS_TOOL_STATS, TD.ItemGenerator.PARTS, TD.ItemGenerator.STICKS, TD.ItemGenerator.PLATES);
		if (aType < 3) put(TD.Properties.NO_ADVANCED_TOOLS);
		return this;
	}
	
	@Deprecated public OreDictMaterial setMeltingPoint(long aMeltingPoint) {return heat(aMeltingPoint);}
	/** Sets the energetic Stats of this Material. Everything is measured in Kelvin. */
	public OreDictMaterial heat(long aMeltingPoint) {
		mMeltingPoint = aMeltingPoint;
		mBoilingPoint = mMeltingPoint * 2;
		mPlasmaPoint = mBoilingPoint * 100;
		return this;
	}
	
	@Deprecated public OreDictMaterial setStatsEnergetic(long aMeltingPoint, long aBoilingPoint) {return heat(aMeltingPoint, aBoilingPoint);}
	/** Sets the energetic Stats of this Material. Everything is measured in Kelvin. */
	public OreDictMaterial heat(long aMeltingPoint, long aBoilingPoint) {
		if (aMeltingPoint > aBoilingPoint) throw new IllegalArgumentException("The Melting Point cannot be above the Boiling Point.");
		mMeltingPoint = aMeltingPoint;
		mBoilingPoint = aBoilingPoint;
		mPlasmaPoint = aBoilingPoint * 100;
		return this;
	}
	
	@Deprecated public OreDictMaterial setStatsEnergetic(long aMeltingPoint, long aBoilingPoint, long aPlasmaPoint) {return heat(aMeltingPoint, aBoilingPoint, aPlasmaPoint);}
	/** Sets the energetic Stats of this Material. Everything is measured in Kelvin. */
	public OreDictMaterial heat(long aMeltingPoint, long aBoilingPoint, long aPlasmaPoint) {
		if (aMeltingPoint > aBoilingPoint) throw new IllegalArgumentException("The Melting Point cannot be above the Boiling Point.");
		if (aBoilingPoint > aPlasmaPoint) throw new IllegalArgumentException("The Boiling Point cannot be above the Plasmafication Point.");
		mMeltingPoint = aMeltingPoint;
		mBoilingPoint = aBoilingPoint;
		mPlasmaPoint = aPlasmaPoint;
		return this;
	}
	
	/** Sets the atomic and energetic Stats of this Element. */
	public OreDictMaterial setStats(long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter) {
		heat(aMeltingPoint, aBoilingPoint);
		mProtons = aProtonsAndElectrons;
		mElectrons = aProtonsAndElectrons;
		mNeutrons = aNeutrons;
		mMass = aProtonsAndElectrons + aNeutrons;
		mGramPerCubicCentimeter = aGramPerCubicCentimeter;
		return this;
	}
	
	/** Sets the atomic Stats of this Material. */
	public OreDictMaterial setStatsElement(long aProtons, long aElectrons, long aNeutrons, long aAdditionalMass, double aGramPerCubicCentimeter) {
		mProtons = aProtons;
		mElectrons = aElectrons;
		mNeutrons = aNeutrons;
		mMass = aProtons + aNeutrons + aAdditionalMass;
		mGramPerCubicCentimeter = aGramPerCubicCentimeter;
		return this;
	}
	
	public ITexture getTextureSolid() {
		if (mTextureSolid == null) mTextureSolid = getTextureSolid(mRGBaSolid, F);;
		return mTextureSolid;
	}
	public ITexture getTextureSolid(int aRGBA, boolean aEnableAO) {
		return getTextureSolid(UT.Code.getRGBaArray(aRGBA), aEnableAO);
	}
	public ITexture getTextureSolid(short[] aRGBA, boolean aEnableAO) {
		return BlockTextureDefault.get(this, OP.blockSolid, aRGBA, contains(TD.Properties.GLOWING), aEnableAO);
	}
	
	public ITexture getTextureSmooth() {
		if (mTextureSmooth == null) mTextureSmooth = mTextureSolid;
		if (mTextureSmooth == null) mTextureSmooth = getTextureSmooth(mRGBaSolid, F);
		return mTextureSmooth;
	}
	public ITexture getTextureSmooth(int aRGBA, boolean aEnableAO) {
		return getTextureSmooth(UT.Code.getRGBaArray(aRGBA), aEnableAO);
	}
	public ITexture getTextureSmooth(short[] aRGBA, boolean aEnableAO) {
		return getTextureSolid(aRGBA, aEnableAO);
	}
	
	public ITexture getTextureMolten() {
		if (mTextureMolten == null) mTextureMolten = getTextureMolten(mRGBaLiquid, F);;
		return mTextureMolten;
	}
	public ITexture getTextureMolten(int aRGBA, boolean aEnableAO) {
		return getTextureMolten(UT.Code.getRGBaArray(aRGBA), aEnableAO);
	}
	public ITexture getTextureMolten(short[] aRGBA, boolean aEnableAO) {
		return BlockTextureDefault.get(this, IconsGT.INDEX_BLOCK_MOLTEN, aRGBA, T, aEnableAO);
	}
	
	public ITexture getTextureDust() {
		if (mTextureDust == null) mTextureDust = getTextureDust(mRGBaSolid, F);;
		return mTextureDust;
	}
	public ITexture getTextureDust(int aRGBA, boolean aEnableAO) {
		return getTextureDust(UT.Code.getRGBaArray(aRGBA), aEnableAO);
	}
	public ITexture getTextureDust(short[] aRGBA, boolean aEnableAO) {
		return BlockTextureDefault.get(this, OP.blockDust, aRGBA, contains(TD.Properties.GLOWING), aEnableAO);
	}
	
	public ITexture getTextureGem() {
		if (mTextureGem == null) mTextureGem = getTextureGem(mRGBaSolid, F);
		return mTextureGem;
	}
	public ITexture getTextureGem(int aRGBA, boolean aEnableAO) {
		return getTextureGem(UT.Code.getRGBaArray(aRGBA), aEnableAO);
	}
	public ITexture getTextureGem(short[] aRGBA, boolean aEnableAO) {
		return BlockTextureDefault.get(this, OP.blockGem, aRGBA, contains(TD.Properties.GLOWING), aEnableAO);
	}
	
	/** Sets the TextureSets for this Material, first Parameter = Block Icons, second Parameter = Item Icons. */
	public OreDictMaterial setTextures(TextureSet... aSets) {
		mTextureSetsBlock = aSets[0].mList;
		mTextureSetsItems = aSets[1].mList;
		return this;
	}
	
	/** Sets the Color of this Material. Note: Whenever I use 256 as Parameter it of course goes back to 255 via bindByte, this is just a marker for me if I did not assign a Color to a Material. */
	public OreDictMaterial setRGBa(long aR, long aG, long aB, long aA) {
		mRGBaSolid[0] = mRGBaLiquid[0] = mRGBaGas[0] = mRGBaPlasma[0] = UT.Code.bind8(aR);
		mRGBaSolid[1] = mRGBaLiquid[1] = mRGBaGas[1] = mRGBaPlasma[1] = UT.Code.bind8(aG);
		mRGBaSolid[2] = mRGBaLiquid[2] = mRGBaGas[2] = mRGBaPlasma[2] = UT.Code.bind8(aB);
		mRGBaSolid[3] = mRGBaLiquid[3] = mRGBaGas[3] = mRGBaPlasma[3] = UT.Code.bind8(aA);
		
		fRGBaSolid[0] = fRGBaLiquid[0] = fRGBaGas[0] = fRGBaPlasma[0] = UT.Code.bind8(aR);
		fRGBaSolid[1] = fRGBaLiquid[1] = fRGBaGas[1] = fRGBaPlasma[1] = UT.Code.bind8(aG);
		fRGBaSolid[2] = fRGBaLiquid[2] = fRGBaGas[2] = fRGBaPlasma[2] = UT.Code.bind8(aB);
		fRGBaSolid[3] = fRGBaLiquid[3] = fRGBaGas[3] = fRGBaPlasma[3] = UT.Code.bind8(aA);
		return this;
	}
	
	/** Sets the Color of this Material */
	public OreDictMaterial setRGBaSolid(long aR, long aG, long aB, long aA) {
		mRGBaSolid[0] = UT.Code.bind8(aR);
		mRGBaSolid[1] = UT.Code.bind8(aG);
		mRGBaSolid[2] = UT.Code.bind8(aB);
		mRGBaSolid[3] = UT.Code.bind8(aA);
		
		fRGBaSolid[0] = UT.Code.bind8(aR);
		fRGBaSolid[1] = UT.Code.bind8(aG);
		fRGBaSolid[2] = UT.Code.bind8(aB);
		fRGBaSolid[3] = UT.Code.bind8(aA);
		return this;
	}
	
	/** Sets the Color of this Material */
	public OreDictMaterial setRGBaLiquid(long aR, long aG, long aB, long aA) {
		mRGBaLiquid[0] = UT.Code.bind8(aR);
		mRGBaLiquid[1] = UT.Code.bind8(aG);
		mRGBaLiquid[2] = UT.Code.bind8(aB);
		mRGBaLiquid[3] = UT.Code.bind8(aA);
		
		fRGBaLiquid[0] = UT.Code.bind8(aR);
		fRGBaLiquid[1] = UT.Code.bind8(aG);
		fRGBaLiquid[2] = UT.Code.bind8(aB);
		fRGBaLiquid[3] = UT.Code.bind8(aA);
		return this;
	}
	
	/** Sets the Color of this Material */
	public OreDictMaterial setRGBaGas(long aR, long aG, long aB, long aA) {
		mRGBaGas[0] = UT.Code.bind8(aR);
		mRGBaGas[1] = UT.Code.bind8(aG);
		mRGBaGas[2] = UT.Code.bind8(aB);
		mRGBaGas[3] = UT.Code.bind8(aA);
		
		fRGBaGas[0] = UT.Code.bind8(aR);
		fRGBaGas[1] = UT.Code.bind8(aG);
		fRGBaGas[2] = UT.Code.bind8(aB);
		fRGBaGas[3] = UT.Code.bind8(aA);
		return this;
	}
	
	/** Sets the Color of this Material */
	public OreDictMaterial setRGBaPlasma(long aR, long aG, long aB, long aA) {
		mRGBaPlasma[0] = UT.Code.bind8(aR);
		mRGBaPlasma[1] = UT.Code.bind8(aG);
		mRGBaPlasma[2] = UT.Code.bind8(aB);
		mRGBaPlasma[3] = UT.Code.bind8(aA);
		
		fRGBaPlasma[0] = UT.Code.bind8(aR);
		fRGBaPlasma[1] = UT.Code.bind8(aG);
		fRGBaPlasma[2] = UT.Code.bind8(aB);
		fRGBaPlasma[3] = UT.Code.bind8(aA);
		return this;
	}
	
	public OreDictMaterial steal(OreDictMaterial aStatsToCopy) {
		heat(aStatsToCopy);
		stealStatsElement(aStatsToCopy);
		qual(aStatsToCopy);
		return this;
	}
	
	public OreDictMaterial stealLooks(OreDictMaterial aStatsToCopy) {
		mTextureSetsItems = aStatsToCopy.mTextureSetsItems;
		mTextureSetsBlock = aStatsToCopy.mTextureSetsBlock;
		for (byte i = 0; i < 4; i++) {
			mRGBa       [i] = aStatsToCopy.mRGBa[i];
			mRGBaSolid  [i] = aStatsToCopy.mRGBaSolid[i];
			mRGBaLiquid [i] = aStatsToCopy.mRGBaLiquid[i];
			mRGBaGas    [i] = aStatsToCopy.mRGBaGas[i];
			mRGBaPlasma [i] = aStatsToCopy.mRGBaPlasma[i];
			
			fRGBa       [i] = aStatsToCopy.fRGBa[i];
			fRGBaSolid  [i] = aStatsToCopy.fRGBaSolid[i];
			fRGBaLiquid [i] = aStatsToCopy.fRGBaLiquid[i];
			fRGBaGas    [i] = aStatsToCopy.fRGBaGas[i];
			fRGBaPlasma [i] = aStatsToCopy.fRGBaPlasma[i];
		}
		return this;
	}
	
	@Deprecated public OreDictMaterial stealStatsEnergetic(OreDictMaterial aStatsToCopy) {return heat(aStatsToCopy);}
	public OreDictMaterial heat(OreDictMaterial aStatsToCopy) {
		return heat(aStatsToCopy.mMeltingPoint, aStatsToCopy.mBoilingPoint, aStatsToCopy.mPlasmaPoint);
	}
	
	@Deprecated public OreDictMaterial stealQuality(OreDictMaterial aStatsToCopy) {return qual(aStatsToCopy);}
	public OreDictMaterial qual(OreDictMaterial aStatsToCopy) {
		return qual(aStatsToCopy.mToolTypes, aStatsToCopy.mToolSpeed, aStatsToCopy.mToolDurability, aStatsToCopy.mToolQuality);
	}
	
	public OreDictMaterial stealStatsElement(OreDictMaterial aStatsToCopy) {
		mProtons                = aStatsToCopy.mProtons;
		mElectrons              = aStatsToCopy.mElectrons;
		mNeutrons               = aStatsToCopy.mNeutrons;
		mMass                   = aStatsToCopy.mMass;
		mGramPerCubicCentimeter = aStatsToCopy.mGramPerCubicCentimeter;
		return this;
	}
	
	public OreDictMaterial setDensity(double aGramPerCubicCentimeter) {
		mGramPerCubicCentimeter = aGramPerCubicCentimeter;
		return this;
	}
	
	public OreDictMaterial addAspects(TC_AspectStack... aAspects) {
		for (TC_AspectStack tAspect : aAspects) {
			if (tAspect.mAspect == TC.METALLUM) mHasMetallum = T;
			tAspect.addToAspectList(mAspects);
		}
		return this;
	}
	
	public OreDictMaterial aspects_met_rad(long aMetallum, long aRadio) {
		return addAspects(TC.stack(TC.METALLUM, aMetallum), TC.stack(TC.RADIO, aRadio));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1) {
		return addAspects(TC.stack(aAspect1, aAmount1));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8, TC aAspect9, long aAmount9) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8), TC.stack(aAspect9, aAmount9));
	}
	public OreDictMaterial aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8, TC aAspect9, long aAmount9, TC aAspect0, long aAmount0) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8), TC.stack(aAspect9, aAmount9), TC.stack(aAspect0, aAmount0));
	}
	
	public OreDictMaterial setOreMultiplier(int aMultiplier) {
		mOreMultiplier = (byte)Math.max(1, aMultiplier);
		return this;
	}
	
	public OreDictMaterial setFurnaceBurnTime(long aValue) {
		mFurnaceBurnTime = Math.max(0, aValue);
		return this;
	}
	
	public OreDictMaterial addEnchantmentForTools(Enchantment aEnchantment, int aEnchantmentLevel) {
		mEnchantmentTools.add(new ObjectStack<>(aEnchantment, aEnchantmentLevel));
		return this;
	}
	
	public OreDictMaterial addEnchantmentForArmors(Enchantment aEnchantment, int aEnchantmentLevel) {
		mEnchantmentArmors.add(new ObjectStack<>(aEnchantment, aEnchantmentLevel));
		return this;
	}
	
	public void addOreByProducts(OreDictMaterial... aMaterials) {
		mByProducts.addAll(Arrays.asList(aMaterials));
	}
	
	/** Sets the Liquid State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial liquid(FluidStack aFluidStack, long aUnit) {
		if (aFluidStack != null) {
			mLiquid = aFluidStack.copy();
			mLiquidUnit = aUnit;
			FLUID_MAP.put(aFluidStack.getFluid().getName(), OM.stack(this, UT.Code.units(aFluidStack.amount, aUnit, U, T)));
		}
		return this;
	}
	
	/** Sets the Gaseous State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial gas(FluidStack aFluidStack, long aUnit) {
		if (aFluidStack != null) {
			mGas = aFluidStack.copy();
			mGasUnit = aUnit;
			FLUID_MAP.put(aFluidStack.getFluid().getName(), OM.stack(this, UT.Code.units(aFluidStack.amount, aUnit, U, T)));
		}
		return this;
	}
	
	/** Sets the Plasma State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial plasma(FluidStack aFluidStack, long aUnit) {
		if (aFluidStack != null) {
			mPlasma = aFluidStack.copy();
			mPlasmaUnit = aUnit;
			FLUID_MAP.put(aFluidStack.getFluid().getName(), OM.stack(this, UT.Code.units(aFluidStack.amount, aUnit, U, T)));
		}
		return this;
	}
	
	/** Sets the Liquid State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial liquid(FluidStack aFluidStack) {
		return liquid(aFluidStack, mLiquidUnit);
	}
	
	/** Sets the Gaseous State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial gas(FluidStack aFluidStack) {
		return gas(aFluidStack, mGasUnit);
	}
	
	/** Sets the Plasma State of this Material. It is advised to have either 144 or 1000 as Fluid Amount. */
	public OreDictMaterial plasma(FluidStack aFluidStack) {
		return plasma(aFluidStack, mPlasmaUnit);
	}
	
	/** Gets the Fluid of this Material at a certain Temperature. */
	public FluidStack fluid(long aMaterialAmount, boolean aRoundUp) {
		return fluid(DEF_ENV_TEMP, aMaterialAmount, aRoundUp);
	}
	
	/** Gets the Fluid of this Material at a certain Temperature. */
	public FluidStack fluid(long aTemperature, long aMaterialAmount, boolean aRoundUp) {
		if (aTemperature >= mPlasmaPoint ) return plasma(aMaterialAmount, aRoundUp);
		if (aTemperature >= mBoilingPoint) return gas   (aMaterialAmount, aRoundUp);
		if (aTemperature >= mMeltingPoint) return liquid(aMaterialAmount, aRoundUp);
		return FL.Error.make(1000);
	}
	
	/** Gets the Liquid State of this Material. */
	public FluidStack liquid(long aMaterialAmount, boolean aRoundUp) {
		if (mLiquid == null) return FL.Error.make(1);
		FluidStack rFluid = mLiquid.copy();
		rFluid.amount = (int)UT.Code.units(aMaterialAmount, mLiquidUnit, rFluid.amount, aRoundUp);
		return rFluid;
	}
	
	/** Gets the Gaseous State of this Material. */
	public FluidStack gas(long aMaterialAmount, boolean aRoundUp) {
		if (mGas == null) return FL.Error.make(1);
		FluidStack rFluid = mGas.copy();
		rFluid.amount = (int)UT.Code.units(aMaterialAmount, mGasUnit, rFluid.amount, aRoundUp);
		return rFluid;
	}
	
	/** Gets the Plasma State of this Material. */
	public FluidStack plasma(long aMaterialAmount, boolean aRoundUp) {
		if (mPlasma == null) return FL.Error.make(1);
		FluidStack rFluid = mPlasma.copy();
		rFluid.amount = (int)UT.Code.units(aMaterialAmount, mPlasmaUnit, rFluid.amount, aRoundUp);
		return rFluid;
	}
	
	/**
	 * DO NOT USE THIS!!! GREG USE ONLY!!!
	 * Sets the Priority Prefix of this Material.
	 */
	public OreDictMaterial setPriorityPrefix(int aIndex) {
		mPriorityPrefixIndex = aIndex;
		if (!mNameInternal.startsWith("Clay") && !"Gravel".equalsIgnoreCase(mNameInternal)) switch (mPriorityPrefixIndex) {
		case 0: break;
		case 1: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockGem"  +mNameInternal); break;
		case 2: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockDust" +mNameInternal); break;
		case 3: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockIngot"+mNameInternal); break;
		case 4: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockIngot"+mNameInternal); break;
		case 5: break;
		}
		return this;
	}
	
	/**
	 * Sets the Priority Prefix of this Material.
	 * 0 = Not Selected
	 * 1 = Gem
	 * 2 = Dust
	 * 3 = Ingot
	 * 4 = Plate
	 * 5 = Gem Plate
	 */
	public OreDictMaterial setPriorityPrefix(int aIndex, OreDictPrefix aPrefix) {
		mPriorityPrefixIndex = aIndex;
		if (!mNameInternal.startsWith("Clay")) switch (mPriorityPrefixIndex) {
		case 0: break;
		case 1: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockGem"  +mNameInternal); break;
		case 2: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockDust" +mNameInternal); break;
		case 3: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockIngot"+mNameInternal); break;
		case 4: OreDictManager.INSTANCE.addReRegistrationWithReversal("block"+mNameInternal, "blockIngot"+mNameInternal); break;
		case 5: break;
		}
		mPriorityPrefix = aPrefix;
		return this;
	}
	
	/** Gets the amount of Neutrons per Unit of Molecule */
	public long getNeutrons() {
		return mNeutrons;
	}
	
	/** Gets the amount of Protons per Unit of Molecule */
	public long getProtons() {
		return mProtons;
	}
	
	/** Gets the amount of Electrons per Unit of Molecule */
	public long getElectrons() {
		return mElectrons;
	}
	
	/** Gets the Mass of one Unit of Molecule */
	public long getMass() {
		return mMass;
	}
	
	/** Gets the Weight of this Material in Kilogramme, depending on the Amount of Material passed. */
	public double getWeight(long aAmount) {
		// Extended Math:
		// 9 Material-Units = 1 Cubic Meter.
		// 1000 g    = 1 kg
		// 1000 cm^3 = 1 dm^3
		// 1000 dm^3 = 1  m^3
		// ( g/cm^3 * aAmount * 1000 * 1000) / (Material-Unit * 9 * 1000)
		// ( g/ m^3 * aAmount              ) / (Material-Unit * 9 * 1000)
		// (kg/ m^3 * aAmount              ) / (Material-Unit * 9       )
		// (kg/ m^3 * aAmount * 0.111111111) /  Material-Unit
		return (mGramPerCubicCentimeter * 111.111111 * aAmount) / U;
	}
	
	@Override
	public String toString() {
		return mNameInternal;
	}
	
	@Override
	public int hashCode() {
		return mHashID;
	}
	
	public final List<IOreDictListenerItem> mListenersItem = new ArrayListNoNulls<>();
	
	public boolean addListener(IOreDictListenerItem aListener) {
		if (mListenersItem.contains(aListener)) return false;
		mListenersItem.add(aListener);
		return true;
	}
	
	/** List of all valid Items, which are registered for this Material. */
	public final ItemStackSet<ItemStackContainer> mRegisteredItems = new ItemStackSet<>();
	/** This is used to determine if any of the ItemStacks belongs to this Material. */
	public boolean contains(ItemStack... aStacks) {
		if (aStacks == null) return F;
		for (ItemStack aStack : aStacks) if (mRegisteredItems.contains(aStack, T)) return T;
		return F;
	}
	
	@Override
	public boolean contains(TagData aTag) {
		return mTags.contains(aTag);
	}
	
	public boolean containsAny(TagData... aTags) {
		for (TagData aTag : aTags) if (mTags.contains(aTag)) return T;
		return F;
	}
	
	@Override
	public boolean containsAll(TagData... aTags) {
		return mTags.containsAll(Arrays.asList(aTags));
	}
	
	@Override
	public boolean containsAll(Collection<TagData> aTags) {
		return mTags.containsAll(aTags);
	}
	
	public OreDictMaterial put(TagData... aObjects) {return add(aObjects);}
	@SuppressWarnings("rawtypes")
	public OreDictMaterial put(Object... aObjects) {
		if (aObjects != null) for (Object aObject : aObjects) if (aObject != null) {
			if (aObject.getClass().isArray()) {
				put((Object[])aObject);
				continue;
			}
			if (aObject instanceof TagData) {
				mTags.add((TagData)aObject);
				continue;
			}
			if (aObject instanceof ModData) {
				setOriginalMod((ModData)aObject);
				continue;
			}
			if (aObject instanceof String) {
				addIdenticalNames((String)aObject);
				continue;
			}
			if (aObject instanceof Number) {
				setFurnaceBurnTime(((Number)aObject).longValue());
				continue;
			}
			if (aObject instanceof OreDictMaterial) {
				addReRegistrations((OreDictMaterial)aObject);
				continue;
			}
			if (aObject instanceof Achievement) {
				mAchievementsForCreation.add((Achievement)aObject);
				continue;
			}
			if (aObject instanceof Iterable) for (Object aIterated : (Iterable)aObject) put(aIterated);
		}
		return this;
	}
	
	/** Additional Function for convenience. */
	public OreDictMaterial add(TagData[] aTags1, TagData... aTags2) {
		put(aTags1);
		put(aTags2);
		return this;
	}
	
	@Override
	public OreDictMaterial add(TagData... aTags) {
		if (aTags != null) for (TagData aTag : aTags) mTags.add(aTag);
		return this;
	}
	
	@Override
	public boolean remove(TagData aTag) {
		return mTags.remove(aTag);
	}
	
	@Override
	public boolean isTrue(OreDictMaterial aObject) {
		return aObject == this;
	}
	@SuppressWarnings({"rawtypes", "unchecked"})
	public final ICondition<ITagDataContainer> NOT = new ICondition.Not(this);
}
