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
import gregapi.code.TagData;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TC;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.item.IPrefixItem;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.listeners.IOreDictListenerItem;
import gregapi.recipes.AdvancedCrafting1ToY;
import gregapi.recipes.AdvancedCraftingXToY;
import gregapi.render.TextureSet;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.IEssentiaContainerItem;

/**
 * @author Gregorius Techneticies
 */
public final class OreDictPrefix implements IOreDictListenerEvent, ITagDataContainer<OreDictPrefix>, ICondition<ITagDataContainer<?>> {
	/** This List is sorted by the length of the Prefixes. */
	public static final List<OreDictPrefix> VALUES_SORTED = new ArrayListNoNulls<>();
	/** This is to prevent smaller Prefixes from breaking larger ones by just starting with the same few Characters the larger one starts with. */
	private static final List<OreDictPrefix> VALUES_SORTED_INTERNAL = new ArrayListNoNulls<>();
	/** The List of all Values in the order they have been added. not really sorted at all. */
	public static final List<OreDictPrefix> VALUES = new ArrayListNoNulls<>();
	/** The Map containing all Prefixes by their Name. */
	public static final Map<String, OreDictPrefix> sPrefixes = new HashMap<>();
	/** The Map containing all Prefixes by their Name. */
	public static final Map<String, OreDictPrefix> sParsed = new HashMap<>();
	
	private final Set<TagData> mTags = new HashSetNoNulls<>();
	private final Set<OreDictMaterial> mItemGeneratorBlackList = new HashSetNoNulls<>(), mIgnoredRegistrations = new HashSetNoNulls<>(), mItemGeneratorForced = new HashSetNoNulls<>();
	private final Set<IOreDictListenerEvent> mListenersOre = new HashSetNoNulls<>();
	public final Set<IOreDictListenerItem> mListenersItem = new HashSetNoNulls<>();
	public final String mNameInternal;
	public OreDictPrefix mTargetRegistration = this;
	public CreativeTabs mCreativeTab = null;
	
	public ItemStack mContainerItem = null;
	@SuppressWarnings("rawtypes")
	private ICondition mCondition = ICondition.TRUE;
	/** The Indices of the Icons inside the Texture Sets. -1 if it doesn't have a Set */
	public int mIconIndexBlock = -1, mIconIndexItem = -1;
	public byte mConfigStackSize = 64, mDefaultStackSize = 64, mMinimumStackSize = 1, mState = STATE_SOLID;
	public long mAmount = -1;
	public List<AdvancedCrafting1ToY> mShapelessManagersSingle = new ArrayListNoNulls<>();
	public List<AdvancedCraftingXToY> mShapelessManagers = new ArrayListNoNulls<>();
	public float mHeatDamage = 0.0F;
	public String mNameLocal, mMaterialPre, mMaterialPost, mNameCategory, mNameTextureSet;
	/** List of Prefixes which are familiar to this Prefix. Like "dust" having "dustSmall" and "dustTiny" and vice versa. Note that this per Default also contains the Prefix itself inside this Set. */
	public final Set<OreDictPrefix> mFamiliarPrefixes = new HashSetNoNulls<>();
	/** Secondary Materials of this Prefix. OreDictMaterialStacks are In Material Units */
	public final List<OreDictMaterialStack> mByProducts = new ArrayListNoNulls<>();
	/** List of ThaumCraft Aspects. */
	public final List<TC_AspectStack> mAspects = new ArrayListNoNulls<>(1);
	
	public static OreDictPrefix createPrefix(String aName) {
		if (GAPI.mStartedInit) throw new IllegalStateException("Prefixes have to be initialised in PreInit or earlier!");
		String tName = aName.replaceAll(" ", "").replaceAll("-", ""); // Auto-Replace all Spaces and Minuses.
		OreDictPrefix rPrefix = sPrefixes.get(tName);
		return rPrefix == null ? new OreDictPrefix(tName, aName) : rPrefix;
	}
	
	private OreDictPrefix(String aNameInternal, String aNameLocal) {
		mNameInternal = aNameInternal;
		mNameTextureSet = mNameInternal;
		mNameCategory = mNameLocal = aNameLocal;
		if (mNameInternal.contains("|") || mNameInternal.contains("*") || mNameInternal.contains(":") || mNameInternal.contains(".") || mNameInternal.contains("$")) throw new IllegalArgumentException("The Prefix Name contains invalid Characters!");
		if (mNameInternal.length() < 3) throw new IllegalArgumentException("A Prefix must have at least 3 Characters, otherwise it would break other Prefixes way to easily.");
		VALUES.add(this);
		if (VALUES_SORTED_INTERNAL.isEmpty()) {
			VALUES_SORTED_INTERNAL.add(this);
			VALUES_SORTED.add(this);
		} else for (int i = 0, j = VALUES_SORTED_INTERNAL.size(); i < j; i++) {
			if (mNameInternal.length() >= VALUES_SORTED_INTERNAL.get(i).mNameInternal.length()) {
				VALUES_SORTED_INTERNAL.add(i, this);
				VALUES_SORTED.add(i, this);
				break;
			}
		}
		sPrefixes.put(mNameInternal, this);
		addFamiliarPrefix(this);
	}
	
	public static OreDictPrefix get(String aOre) {
		OreDictPrefix rPrefix = sParsed.get(aOre);
		if (rPrefix != null) return rPrefix;
		if (sParsed.containsKey(aOre)) return null;
		for (int i = 0, j = VALUES_SORTED_INTERNAL.size(); i < j; i++) {
			rPrefix = VALUES_SORTED_INTERNAL.get(i);
			if (aOre.startsWith(rPrefix.mNameInternal)) {
				sParsed.put(aOre, rPrefix);
				return rPrefix;
			}
		}
		sParsed.put(aOre, null);
		return null;
	}
	
	/** Adds Identical Names which are getting re-registered to this Prefix */
	public OreDictPrefix addIdenticalNames(String... aNames) {
		for (String aName : aNames) createPrefix(aName).setRegistration(this);
		return this;
	}
	
	public OreDictPrefix addFamiliarPrefix(OreDictPrefix aPrefix) {
		mFamiliarPrefixes.add(aPrefix);
		return this;
	}
	
	/** The Re-Registration for the Ore Dictionary for invalid Prefixes */
	public OreDictPrefix setRegistration(OreDictPrefix aPrefix) {
		if (aPrefix != null) {
			mTargetRegistration = aPrefix;
			add(TD.Prefix.PREFIX_UNUSED);
		}
		return this;
	}
	
	public OreDictPrefix setMaterialStats(long aMaterialAmount) {
		add(TD.Prefix.MATERIAL_BASED);
		mAmount = aMaterialAmount;
		setStacksize(mAmount < U * 2 ? 64 : 64 / (mAmount / U));
		return this;
	}
	
	public OreDictPrefix setConfigStacksize(long aStacksize) {
		mConfigStackSize = (byte)UT.Code.bind_(mMinimumStackSize, 64, aStacksize);
		mDefaultStackSize = (byte)UT.Code.bind_(mMinimumStackSize, 64, aStacksize);
		return this;
	}
	
	public OreDictPrefix setStacksize(long aStacksize) {
		mDefaultStackSize = (byte)UT.Code.bind_(mMinimumStackSize, 64, aStacksize);
		return this;
	}
	
	public OreDictPrefix setStacksize(long aStacksize, long aMinimumStacksize) {
		mMinimumStackSize = (byte)UT.Code.bind_(1, 64, aMinimumStacksize);
		mDefaultStackSize = (byte)UT.Code.bind_(mMinimumStackSize, 64, aStacksize);
		return this;
	}
	
	public OreDictPrefix setMinStacksize(long aMinimumStacksize) {
		mMinimumStackSize = (byte)UT.Code.bind_(1, 64, aMinimumStacksize);
		mDefaultStackSize = (byte)UT.Code.bind_(mMinimumStackSize, 64, mDefaultStackSize);
		return this;
	}
	
	public static void applyAllStackSizes() {
		for (OreDictPrefix tPrefix : VALUES) tPrefix.applyStackSizes();
	}
	
	public OreDictPrefix applyStackSizes() {
		if (contains(TD.Prefix.PREFIX_UNUSED)) return this;
		if (this != OP.block && this != OP.stone) addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {if (!aEvent.mStack.getItem().isDamageable() && aEvent.mStack.getMaxStackSize() > 1 && !ST.isGT_(aEvent.mStack)) aEvent.mStack.getItem().setMaxStackSize(aEvent.mPrefix.mDefaultStackSize);}});
		
		Items.glass_bottle      .setMaxStackSize(OP.bottle.mDefaultStackSize);
		Items.potionitem        .setMaxStackSize(1);
		
		Items.record_11         .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_13         .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_blocks     .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_cat        .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_chirp      .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_far        .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_mall       .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_mellohi    .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_stal       .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_strad      .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_wait       .setMaxStackSize(OP.record.mDefaultStackSize);
		Items.record_ward       .setMaxStackSize(OP.record.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.packed_ice             ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.ice                    ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.soul_sand              ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.glowstone              ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.snow_layer             ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.snow                   ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.clay                   ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.redstone_lamp          ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.dirt                   ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.grass                  ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.mycelium               ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.gravel                 ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.sand                   ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.wool                   ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.melon_block            ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.pumpkin                ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.lit_pumpkin            ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.dispenser              ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.piston                 ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.sticky_piston          ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.crafting_table         ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.jukebox                ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.anvil                  ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.chest                  ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.trapped_chest          ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.noteblock              ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.mob_spawner            ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.bookshelf              ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.furnace                ).setMaxStackSize(OP.block.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.lit_furnace            ).setMaxStackSize(OP.block.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.sandstone              ).setMaxStackSize(OP.stone.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.obsidian               ).setMaxStackSize(OP.stone.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.stone                  ).setMaxStackSize(OP.stone.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.stone_slab             ).setMaxStackSize(OP.slab.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.double_stone_slab      ).setMaxStackSize(OP.slab.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.wooden_slab            ).setMaxStackSize(OP.slab.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.double_wooden_slab     ).setMaxStackSize(OP.slab.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.stone_brick_stairs     ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.brick_stairs           ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.nether_brick_stairs    ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.sandstone_stairs       ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.stone_stairs           ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.oak_stairs             ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.jungle_stairs          ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.birch_stairs           ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.spruce_stairs          ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.acacia_stairs          ).setMaxStackSize(OP.stair.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.dark_oak_stairs        ).setMaxStackSize(OP.stair.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.glass                  ).setMaxStackSize(OP.glass.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.stained_glass          ).setMaxStackSize(OP.glass.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.glass_pane             ).setMaxStackSize(OP.paneGlass.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.stained_glass_pane     ).setMaxStackSize(OP.paneGlass.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.hardened_clay          ).setMaxStackSize(OP.stainedClay.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.stained_hardened_clay  ).setMaxStackSize(OP.stainedClay.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.cobblestone            ).setMaxStackSize(OP.stoneCobble.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.fence                  ).setMaxStackSize(OP.fence.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.fence_gate             ).setMaxStackSize(OP.fence.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.cobblestone_wall       ).setMaxStackSize(OP.fence.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.nether_brick_fence     ).setMaxStackSize(OP.fence.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.mossy_cobblestone      ).setMaxStackSize(OP.stoneMossy.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.stonebrick             ).setMaxStackSize(OP.stoneBricks.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.brick_block            ).setMaxStackSize(OP.stoneBricks.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.nether_brick           ).setMaxStackSize(OP.stoneBricks.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.sapling                ).setMaxStackSize(OP.treeSapling.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.leaves                 ).setMaxStackSize(OP.treeLeaves.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.leaves2                ).setMaxStackSize(OP.treeLeaves.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.log                    ).setMaxStackSize(OP.log.mDefaultStackSize);
		Item.getItemFromBlock(Blocks.log2                   ).setMaxStackSize(OP.log.mDefaultStackSize);
		
		Item.getItemFromBlock(Blocks.planks                 ).setMaxStackSize(OP.plank.mDefaultStackSize);
		return this;
	}
	
	public OreDictPrefix setCategoryName(String aCategoryName) {
		mNameCategory = aCategoryName;
		return this;
	}
	
	public OreDictPrefix setTextureSetName(String aTextureSetName) {
		mNameTextureSet = aTextureSetName;
		return this;
	}
	
	/** sets the State of things which are of this Prefix. 0 - 3 are from Solid to Plasma. Solid is Default. */
	public OreDictPrefix setState(long aState) {
		mState = UT.Code.bind2(aState);
		return this;
	}
	
	public OreDictPrefix setCondition(ICondition<?> aCondition) {
		mCondition = aCondition==null?ICondition.FALSE:aCondition;
		return this;
	}
	
	public OreDictPrefix setLocalItemName(String aPreMaterial, String aPostMaterial) {
		mMaterialPre = aPreMaterial;
		mMaterialPost = aPostMaterial;
		return this;
	}
	
	public OreDictPrefix setLocalPrefixName(String aLocalName) {
		mNameLocal = aLocalName;
		return this;
	}
	
	public OreDictPrefix ignoreMaterials(OreDictMaterial... aMaterials) {
		if (aMaterials != null) for (OreDictMaterial aMaterial : aMaterials) if (aMaterial != null && !mIgnoredRegistrations.contains(aMaterial)) mIgnoredRegistrations.add(aMaterial);
		return this;
	}
	
	public OreDictPrefix disableItemGeneration(OreDictMaterial... aMaterials) {
		if (aMaterials != null) for (OreDictMaterial aMaterial : aMaterials) if (aMaterial != null && !mItemGeneratorBlackList.contains(aMaterial)) mItemGeneratorBlackList.add(aMaterial);
		return this;
	}
	
	public OreDictPrefix forceItemGeneration(OreDictMaterial... aMaterials) {
		if (aMaterials != null) for (OreDictMaterial aMaterial : aMaterials) if (aMaterial != null && !mItemGeneratorForced.contains(aMaterial)) mItemGeneratorForced.add(aMaterial);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isGeneratingItem(OreDictMaterial aMaterial) {
		return aMaterial != null && (mItemGeneratorForced.contains(aMaterial) || (!mItemGeneratorBlackList.contains(aMaterial) && mCondition.isTrue(aMaterial)));
	}
	
	@SuppressWarnings("unchecked")
	public boolean canGenerateItem(OreDictMaterial aMaterial) {
		return aMaterial != null && (mItemGeneratorForced.contains(aMaterial) || mCondition.isTrue(aMaterial));
	}
	
	private final List<OreDictRegistrationContainer> mRegistrations = new ArrayListNoNulls<>();
	
	public OreDictPrefix addListener(IOreDictListenerEvent aListener) {
		if (GAPI.mStartedPostInit) addListenerInternal(aListener); else mBufferedListeners.add(aListener);
		return this;
	}
	
	private final Set<IOreDictListenerEvent> mBufferedListeners = new HashSetNoNulls<>();
	private void addListenerInternal(IOreDictListenerEvent aListener) {
		if (mListenersOre.add(aListener)) for (OreDictRegistrationContainer tEvent : mRegistrations) aListener.onOreRegistration(tEvent);
	}
	
	public void onPostLoad() {
		if (GAPI.mStartedPostInit) for (IOreDictListenerEvent aListener : mBufferedListeners) addListenerInternal(aListener);
	}
	
	public OreDictPrefix addListener(IOreDictListenerItem aListener) {
		mListenersItem.add(aListener);
		return this;
	}
	
	/**
	 * Adds the internal Name of this Prefix as Icon Name to all Texture Sets and sets its Icon Index to the Index of the Set.
	 * @param aModID the Mod responsible for adding the Icons (which are located in either assets\aModID.toLowerCase()\textures\items\materialicons\ or assets\aModID.toLowerCase()\textures\blocks\materialicons\).
	 */
	public OreDictPrefix addTextureSet(String aModID, boolean aIsItem) {
		if (aIsItem) mIconIndexItem = TextureSet.addToAll(aModID, aIsItem, mNameTextureSet); else mIconIndexBlock = TextureSet.addToAll(aModID, aIsItem, mNameTextureSet);
		return this;
	}
	
	/**
	 * Adds the internal Name of this Prefix as Icon Name to all Texture Sets and sets its Icon Index to the Index of the Set.
	 * @param aModID the Mod responsible for adding the Icons (which are located in either assets\aModID.toLowerCase()\textures\items\materialicons\ or assets\aModID.toLowerCase()\textures\blocks\materialicons\).
	 */
	public OreDictPrefix addTextureSet(ModData aModID, boolean aIsItem) {
		if (aIsItem) mIconIndexItem = TextureSet.addToAll(aModID.mID, aIsItem, mNameTextureSet); else mIconIndexBlock = TextureSet.addToAll(aModID.mID, aIsItem, mNameTextureSet);
		return this;
	}
	
	public OreDictPrefix addAspects(TC_AspectStack... aAspects) {
		for (TC_AspectStack tAspect : aAspects) tAspect.addToAspectList(mAspects);
		return this;
	}
	
	public OreDictPrefix aspects(TC aAspect1, long aAmount1) {
		return addAspects(TC.stack(aAspect1, aAmount1));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8, TC aAspect9, long aAmount9) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8), TC.stack(aAspect9, aAmount9));
	}
	public OreDictPrefix aspects(TC aAspect1, long aAmount1, TC aAspect2, long aAmount2, TC aAspect3, long aAmount3, TC aAspect4, long aAmount4, TC aAspect5, long aAmount5, TC aAspect6, long aAmount6, TC aAspect7, long aAmount7, TC aAspect8, long aAmount8, TC aAspect9, long aAmount9, TC aAspect0, long aAmount0) {
		return addAspects(TC.stack(aAspect1, aAmount1), TC.stack(aAspect2, aAmount2), TC.stack(aAspect3, aAmount3), TC.stack(aAspect4, aAmount4), TC.stack(aAspect5, aAmount5), TC.stack(aAspect6, aAmount6), TC.stack(aAspect7, aAmount7), TC.stack(aAspect8, aAmount8), TC.stack(aAspect9, aAmount9), TC.stack(aAspect0, aAmount0));
	}
	
	public ItemStack mat(OreDictMaterial aMaterial, long aStackSize) {
		return OreDictManager.INSTANCE.getStack(this, aMaterial, NI, aStackSize);
	}
	public ItemStack mat(OreDictMaterial aMaterial, long aStackSize, ItemStack aReplacement) {
		return OreDictManager.INSTANCE.getStack(this, aMaterial, aReplacement, aStackSize);
	}
	public ItemStack mat(OreDictMaterial aMaterial, ItemStack aReplacement, long aStackSize) {
		return OreDictManager.INSTANCE.getStack(this, aMaterial, aReplacement, aStackSize);
	}
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (aEvent.mMaterial == MT.NULL) {
			if (!contains(TD.Prefix.MATERIAL_BASED)) {
				if (COMPAT_TC != null && !(aEvent.mStack.getItem() instanceof IEssentiaContainerItem) && !(aEvent.mStack.getItem() instanceof MultiItemRandom) && !MD.MC.owns(aEvent.mRegName) && !MD.TC.owns(aEvent.mRegName)) {
					List<TC_AspectStack> tAspects = new ArrayListNoNulls<>();
					for (TC_AspectStack tAspect : mAspects) tAspect.addToAspectList(tAspects);
					COMPAT_TC.registerThaumcraftAspectsToItem(ST.amount(1, aEvent.mStack), tAspects, F);
				}
				for (IOreDictListenerEvent tListener : mListenersOre) {
					if (D2) ORD.println("Processing '" + aEvent.mOreDictName + "' with the Prefix '" + mNameInternal + "' and without Material at " + UT.Reflection.getClassName(tListener));
					tListener.onOreRegistration(aEvent);
				}
				mRegistrations.add(aEvent);
				if (!mRegisteredItems.contains(new ItemStackContainer(aEvent.mStack, W))) mRegisteredItems.add(new ItemStackContainer(aEvent.mStack));
			}
		} else {
			if (!mIgnoredRegistrations.contains(aEvent.mMaterial)) {
				if (!aEvent.mMaterial.contains(TD.Properties.INVALID_MATERIAL)) {
					if (COMPAT_TC != null && !(aEvent.mStack.getItem() instanceof IEssentiaContainerItem) && !(aEvent.mStack.getItem() instanceof MultiItemRandom) && !MD.MC.owns(aEvent.mRegName) && !MD.TC.owns(aEvent.mRegName)) {
						List<TC_AspectStack> tAspects = new ArrayListNoNulls<>();
						for (TC_AspectStack tAspect : mAspects) {
							if (tAspect.mAspect == TC.METALLUM && !aEvent.mMaterial.mHasMetallum) {
								// replacing Metallum with Ordo for non-metallic Stuff.
								TC.stack(TC.ORDO, tAspect.mAmount).addToAspectList(tAspects);
							} else {
								tAspect.addToAspectList(tAspects);
							}
						}
						if (mAmount >= U || mAmount < 0) for (TC_AspectStack tAspect : aEvent.mMaterial.mAspects) tAspect.addToAspectList(tAspects);
						COMPAT_TC.registerThaumcraftAspectsToItem(ST.amount(1, aEvent.mStack), tAspects, F);
					}
					for (IOreDictListenerEvent tListener : mListenersOre) {
						if (D2) ORD.println("Processing '" + aEvent.mOreDictName + "' with the Prefix '" + mNameInternal + "' and the Material '" + aEvent.mMaterial.mNameInternal + "' at " + UT.Reflection.getClassName(tListener));
						tListener.onOreRegistration(aEvent);
					}
					mRegistrations.add(aEvent);
				}
				aEvent.mMaterial.mRegisteredItems.add(new ItemStackContainer(aEvent.mStack));
				mRegisteredMaterials.add(aEvent.mMaterial);
				if (!mRegisteredItems.contains(new ItemStackContainer(aEvent.mStack, W))) mRegisteredItems.add(new ItemStackContainer(aEvent.mStack));
			}
		}
	}
	
	/** List of all valid Items, which are registered for this Prefix. */
	public final ItemStackSet<ItemStackContainer> mRegisteredItems = new ItemStackSet<>();
	public final List<IPrefixItem> mRegisteredPrefixItems = new ArrayListNoNulls<>();
	public final Set<OreDictMaterial> mRegisteredMaterials = new HashSetNoNulls<>();
	/** This is used to determine if any of the ItemStacks belongs to this Prefix. */
	public boolean contains(ItemStack... aStacks) {
		if (aStacks == null) return F;
		for (ItemStack aStack : aStacks) if (ST.valid(aStack) && mRegisteredItems.contains(aStack, T)) return T;
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
	
	@Override
	public OreDictPrefix add(TagData... aTags) {
		if (aTags != null) for (TagData aTag : aTags) mTags.add(aTag);
		return this;
	}
	
	@Override
	public boolean remove(TagData aTag) {
		return mTags.remove(aTag);
	}
	
	public OreDictItemData dat(OreDictMaterial aMaterial) {
		return new OreDictItemData(this, aMaterial);
	}
	
	/** @Deprecated just renamed the thing to be the above thing, but I will keep this for compat. */
	@Deprecated
	public OreDictItemData get(OreDictMaterial aMaterial) {
		return new OreDictItemData(this, aMaterial);
	}
	
	@Override
	public String toString() {
		return mNameInternal;
	}
	
	@Override
	public boolean isTrue(ITagDataContainer<?> aMaterial) {
		return aMaterial instanceof OreDictMaterial && canGenerateItem((OreDictMaterial)aMaterial);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public final ICondition<ITagDataContainer> NOT = new ICondition.Not(this);
}
