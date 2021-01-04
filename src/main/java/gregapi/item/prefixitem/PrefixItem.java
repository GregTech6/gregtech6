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

package gregapi.item.prefixitem;

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ModData;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.CreativeTab;
import gregapi.item.IItemGT;
import gregapi.item.IItemNoGTOverride;
import gregapi.item.IItemUpdatable;
import gregapi.item.IPrefixItem;
import gregapi.lang.LanguageHandler;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class PrefixItem extends Item implements Runnable, IItemUpdatable, IPrefixItem, IItemGT, IItemNoGTOverride {
	public final String mNameInternal;
	public final OreDictPrefix mPrefix;
	public final OreDictMaterial[] mMaterialList;
	
	public ItemStack mContainerItem = null;
	
	/** The Sound played when crafting with this Item */
	public String mCraftingSound = null;
	
	public PrefixItem(ModData aMod, String aNameInternal, OreDictPrefix aPrefix) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, OreDictMaterial.MATERIAL_ARRAY);
	}
	
	/**
	 * @param aModIDOwner the ID of the owning Mod. DO NOT INSERT ANY GREGTECH MODID!!!
	 * @param aModIDTextures the ID of the Texture providing Mod (for the "ModID:TextureName" thing)
	 * @param aNameInternal the internal Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 * @param aPrefix the OreDictPrefix corresponding to this Item.
	 */
	public PrefixItem(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterial... aMaterialList) {
		super();
		mPrefix = aPrefix;
		mPrefix.mRegisteredPrefixItems.add(this);
		mNameInternal = aNameInternal;
		mMaterialList = (aMaterialList.length > 0 ? aMaterialList : OreDictMaterial.MATERIAL_ARRAY);
		if (mMaterialList[0] != MT.Empty) throw new IllegalArgumentException("The first element of the custom Material List has to be MT.Empty for technical reasons!");
		
		setMaxDamage(0);
		setHasSubtypes(T);
		GameRegistry.registerItem(this, mNameInternal, aModIDOwner);
		
		mPrefix.addTextureSet(aModIDTextures, T);
		LH.add("oredict." + mPrefix.dat(MT.Empty).toString() + ".name", getLocalName(mPrefix, MT.Empty));
		LH.add(mNameInternal+"."+W+".name", "Any Sub-Item of this one"); // Local Name for the WildcardItem Variant.
		mPrefix.mRegisteredItems.add(this); // this optimizes some processes by decreasing the size of the Set.
		
		if (SHOW_HIDDEN_PREFIXES || !mPrefix.contains(TD.Creative.HIDDEN)) {
			if (mPrefix.mCreativeTab == null) mPrefix.mCreativeTab = new CreativeTab(mPrefix.mNameInternal, mPrefix.mNameCategory, this, W);
			setCreativeTab(mPrefix.mCreativeTab);
		} else {
			setCreativeTab(CreativeTabs.tabMisc);
		}
		
		// Execute before all the other things. This is to ensure that PrefixItems are created before MultiItems.
		GAPI.mBeforeInit.add(0, this);
	}
	
	/** This ensures, that all Materials are registered at the time this Item registers to the OreDictionary. */
	@Override
	public void run() {
		boolean tUnificationAllowed = (mPrefix.contains(TD.Prefix.UNIFICATABLE) && !mPrefix.contains(TD.Prefix.UNIFICATABLE_RECIPES));
		for (short i = 0; i < mMaterialList.length; i++) if (mMaterialList[i] != null && mPrefix.isGeneratingItem(mMaterialList[i])) {
			ItemStack tStack = ST.make(this, 1, i);
			ST.update_(tStack);
			LH.add("oredict." + mPrefix.dat(mMaterialList[i]).toString() + ".name", getLocalName(mPrefix, mMaterialList[i]));
			if (tUnificationAllowed) OreDictManager.INSTANCE.addTarget_(mPrefix, mMaterialList[i], tStack); else OreDictManager.INSTANCE.registerOre_(mPrefix, mMaterialList[i], tStack);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void getSubItems(Item var1, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		if ((SHOW_HIDDEN_PREFIXES || !mPrefix.contains(TD.Creative.HIDDEN)) && (SHOW_ORE_BLOCK_PREFIXES || this == BlocksGT.ore || !mPrefix.contains(TD.Prefix.ORE))) for (int i = 0; i < mMaterialList.length; i++) if (mPrefix.isGeneratingItem(mMaterialList[i])) if (SHOW_HIDDEN_MATERIALS || !mMaterialList[i].mHidden) {
			ItemStack tStack = OM.get_(ST.make(this, 1, i));
			if (tStack.getItem() == this) {
				updateItemStack(tStack);
				if (ST.meta_(tStack) == i) aList.add(tStack);
			}
		}
		if (aList.isEmpty()) ST.hide(this);
	}
	
	@Override public int getSpriteNumber() {return 1;}
	@Override public int getRenderPasses(int metadata) {return 2;}
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aIconRegister) {/**/}
	@Override public boolean requiresMultipleRenderPasses() {return mPrefix.mIconIndexItem >= 0;}
	@Override public IIcon getIconIndex(ItemStack aStack) {return getIconFromDamageForRenderPass(ST.meta_(aStack), 0);}
	@Override public IIcon getIconFromDamage(int aMetaData) {return getIconFromDamageForRenderPass(aMetaData, 0);}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass) {return getIconFromDamageForRenderPass(ST.meta_(aStack), aRenderPass);}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {return getIconFromDamageForRenderPass(ST.meta_(aStack), aRenderPass);}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		if (mPrefix.mIconIndexItem >= 0) {
			if (UT.Code.exists(aMetaData, mMaterialList) && mMaterialList[aMetaData].mTextureSetsItems != null)
			return mMaterialList[aMetaData] .mTextureSetsItems.get(mPrefix.mIconIndexItem).getIcon(aRenderPass);
			return MT.NULL                  .mTextureSetsItems.get(mPrefix.mIconIndexItem).getIcon(aRenderPass);
		}
		return null;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		if (aRenderPass == 0) {
			short aMetaData = ST.meta_(aStack);
			if (UT.Code.exists(aMetaData, mMaterialList)) return UT.Code.getRGBInt(mMaterialList[aMetaData].mRGBa[mPrefix.mState]);
		}
		return 16777215;
	}
	
	@Override
	public final String getUnlocalizedName(ItemStack aStack) {
		short aMetaData = ST.meta_(aStack);
		if (aMetaData == W) return mNameInternal+"."+W;
		if (UT.Code.exists(aMetaData, mMaterialList)) return "oredict." + mPrefix.dat(mMaterialList[aMetaData]).toString();
		return mNameInternal;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		if (ST.equal(aStack, mContainerItem, T)) return null;
		if (mCraftingSound != null) UT.Sounds.play(mCraftingSound, 20, 1.0F);
		return mContainerItem != null ? ST.amount(1, mContainerItem) : mPrefix.mContainerItem != null ? ST.amount(1, mContainerItem = mPrefix.mContainerItem) : null;
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack aStack) {
		if (mPrefix.mAmount >= U && (mPrefix.contains(TD.Prefix.GEM_BASED) || mPrefix.contains(TD.Prefix.INGOT_BASED))) {
			short aMetaData = ST.meta_(aStack);
			return UT.Code.exists(aMetaData, mMaterialList) && (mMaterialList[aMetaData].contains(TD.Properties.VALUABLE) || ANY.Iron.mToThis.contains(mMaterialList[aMetaData]));
		}
		return F;
	}
	
	@Override public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {updateItemStack(aStack);}
	@Override public void updateItemStack(ItemStack aStack) {
		if (mMaterialList != OreDictMaterial.MATERIAL_ARRAY) return;
		int aMeta = ST.meta_(aStack);
		if (UT.Code.exists(aMeta, mMaterialList)) {
			OreDictMaterial aMaterial = mMaterialList[aMeta];
			if (aMeta != aMaterial.mTargetRegistration.mID) ST.meta_(aStack, aMaterial.mTargetRegistration.mID);
			if (!mPrefix.isGeneratingItem(aMaterial.mTargetRegistration)) ST.set(aStack, mPrefix.mat(aMaterial.mTargetRegistration, 1), F, F);
		}
	}
	
	@Override
	public float getSmeltingExperience(ItemStack aStack) {
		return mPrefix == OP.gem ? 1.0F : 0.0F;
	}
	
	@Override public String toString() {return mNameInternal;}
	@Override public final String getUnlocalizedName() {return mNameInternal;}
	@Override public final Item setUnlocalizedName(String aName) {return this;}
	@Override public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {/**/}
	@Override public final boolean hasContainerItem(ItemStack aStack) {return getContainerItem(aStack) != null;}
	@Override public boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {return F;}
	@Override public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {updateItemStack(aStack);}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	@Override public int getItemEnchantability() {return 0;}
	@Override public int getItemStackLimit(ItemStack aStack) {return mPrefix.mDefaultStackSize;}
	@Override public OreDictItemData getOreDictItemData(ItemStack aStack) {return UT.Code.exists(ST.meta_(aStack), mMaterialList) ? new OreDictItemData(mPrefix, mMaterialList[ST.meta_(aStack)]) : null;}
	@Override public OreDictMaterial getMaterial(int aMetaData) {return UT.Code.exists(aMetaData, mMaterialList) ? mMaterialList[aMetaData] : null;}
	@Override public OreDictPrefix getPrefix(int aMetaData) {return mPrefix;}
	@Override @SuppressWarnings("deprecation") public boolean hasEffect(ItemStack aStack) {return F;}
	@Override public boolean hasEffect(ItemStack aStack, int aRenderPass) {return F;}
	
	/*
	@Override @Optional.Method(modid = ModIDs.TC) public void setAspects(ItemStack aStack, AspectList aAspectList) {}
	
	@Override @Optional.Method(modid = ModIDs.TC)
	public AspectList getAspects(ItemStack aStack) {
		List<TC_AspectStack> rAspects = new ArrayListNoNulls<TC_AspectStack>();
		for (TC_AspectStack tAspect : mPrefix.mAspects) tAspect.addToAspectList(rAspects);
		OreDictMaterial aMaterial = (UT.Code.exists(aStack), mMaterialList) ? mMaterialList[ST.meta(aStack)] : null);
		if (aMaterial != null && (mPrefix.mAmount >= U || mPrefix.mAmount < 0)) for (TC_AspectStack tAspect : aMaterial.mAspects) tAspect.addToAspectList(rAspects);
		
		for (TC_AspectStack tAspect : rAspects) tAspect.mAmount = Math.min(10, tAspect.mAmount);
		return (AspectList)GT_API.sCompatTC.getAspectList(rAspects);
	}
	*/
	
	/** @return the Local Name for this Item depending on Prefix and Material. */
	public String getLocalName(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		return LanguageHandler.getLocalName(aPrefix, aMaterial);
	}
}
