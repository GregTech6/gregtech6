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

package gregapi.block.prefixblock;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.*;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.item.IFlowerPlaceable;
import vazkii.botania.api.subtile.SubTileEntity;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "vazkii.botania.api.item.IFlowerPlaceable", modid = ModIDs.BOTA)
})
public class PrefixBlockItem extends ItemBlock implements IItemUpdatable, IPrefixItem, IItemGT, IItemNoGTOverride, IFlowerPlaceable {
	public final PrefixBlock mBlock;
	
	public PrefixBlockItem(Block aBlock) {
		super(aBlock);
		setMaxDamage(0);
		setHasSubtypes(T);
		mBlock = (PrefixBlock)aBlock;
		mBlock.mPrefix.mRegisteredPrefixItems.add(this);
		
		if ((SHOW_HIDDEN_PREFIXES || !mBlock.mPrefix.contains(TD.Creative.HIDDEN)) && (SHOW_ORE_BLOCK_PREFIXES || "gt.meta.ore.normal.default".equalsIgnoreCase(mBlock.mNameInternal) || !mBlock.mPrefix.contains(TD.Prefix.ORE) || mBlock.mPrefix.contains(TD.Prefix.STORAGE_BASED))) {
			if (mBlock.mPrefix.mCreativeTab == null) mBlock.mPrefix.mCreativeTab = new CreativeTab(mBlock.mPrefix.mNameInternal, mBlock.mPrefix.mNameCategory, this, W);
			mBlock.setCreativeTab(mBlock.mPrefix.mCreativeTab);
			setCreativeTab(mBlock.mPrefix.mCreativeTab);
		} else {
			mBlock.setCreativeTab(CreativeTabs.tabBlock);
			setCreativeTab(CreativeTabs.tabBlock);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void getSubItems(Item var1, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		if (!mBlock.mHidden && (SHOW_HIDDEN_PREFIXES || !mBlock.mPrefix.contains(TD.Creative.HIDDEN)) && (SHOW_ORE_BLOCK_PREFIXES || mBlock == BlocksGT.ore || !mBlock.mPrefix.contains(TD.Prefix.ORE) || mBlock.mPrefix.contains(TD.Prefix.STORAGE_BASED))) for (int i = 0; i < mBlock.mMaterialList.length; i++) if (mBlock.mPrefix.isGeneratingItem(mBlock.mMaterialList[i])) if (SHOW_HIDDEN_MATERIALS || !mBlock.mMaterialList[i].mHidden) {
			ItemStack tStack = ST.make(this, 1, i);
			updateItemStack(tStack);
			if (ST.meta_(tStack) == i) aList.add(tStack);
		}
		if (aList.isEmpty()) ST.hide(this);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ, int aMeta) {
		if (mBlock.placeBlock(aWorld, aX, aY, aZ, (byte)aSide, ST.meta_(aStack), aStack.getTagCompound(), T, F)) {
			if (aWorld.getBlock(aX, aY, aZ) == field_150939_a) {
				field_150939_a.onBlockPlacedBy(aWorld, aX, aY, aZ, aPlayer, aStack);
				field_150939_a.onPostBlockPlaced(aWorld, aX, aY, aZ, ST.meta_(aStack));
			}
			return T;
		}
		UT.Entities.sendchat(aPlayer, "Cannot place Block in this Environment!");
		return F;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		if (aRenderPass == 0) {
			short aMetaData = ST.meta_(aStack);
			if (UT.Code.exists(aMetaData, mBlock.mMaterialList)) return UT.Code.getRGBInt(mBlock.mMaterialList[aMetaData].mRGBa[mBlock.mPrefix.mState]);
		}
		return UNCOLORED;
	}
	
	@Override
	public final String getUnlocalizedName(ItemStack aStack) {
		short aMetaData = ST.meta_(aStack);
		if (aMetaData == W) return mBlock.mNameInternal+"."+W;
		if (UT.Code.exists(aMetaData, mBlock.mMaterialList)) return "oredict." + mBlock.mPrefix.dat(mBlock.mMaterialList[aMetaData]).toString();
		return mBlock.getUnlocalizedName();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		super.addInformation(aStack, aPlayer, aList, aF3_H);
		if (mBlock.mSpawnProof) aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_SPAWNPROOF));
		
		if (MD.GC.mLoaded) {
			if (mBlock.mPrefix == OP.blockSolid) {
				aList.add(LH.Chat.GREEN  + LH.get(LH.TOOLTIP_SEALABLE_ANY));
			} else if (mBlock.isOpaqueCube()) {
				aList.add(LH.Chat.ORANGE + LH.get(LH.TOOLTIP_SEALABLE_BUGGED));
			}
		}
		
		if (mBlock.mGravity) aList.add(LH.Chat.ORANGE + LH.get(LH.TOOLTIP_GRAVITY));
		OreDictMaterial aMaterial = mBlock.getMetaMaterial(getDamage(aStack));
		aList.add(LH.getToolTipBlastResistance(field_150939_a, mBlock.mBaseResistance * (1+mBlock.getHarvestLevel(aMaterial==null?0:aMaterial.mToolQuality))));
		while (aList.remove(null));
	}
	
	@Override
	public OreDictItemData getOreDictItemData(ItemStack aStack) {
		if (mBlock.mPrefix != null && UT.Code.exists(ST.meta_(aStack), mBlock.mMaterialList)) return new OreDictItemData(mBlock.mPrefix, mBlock.mMaterialList[ST.meta_(aStack)]);
		return null;
	}
	
	@Override public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {updateItemStack(aStack);}
	@Override public void updateItemStack(ItemStack aStack) {
		if (mBlock.mMaterialList != OreDictMaterial.MATERIAL_ARRAY) return;
		int aMeta = ST.meta_(aStack);
		if (UT.Code.exists(aMeta, mBlock.mMaterialList)) {
			OreDictMaterial aMaterial = mBlock.mMaterialList[aMeta];
			if (aMeta != aMaterial.mTargetRegistration.mID) ST.meta_(aStack, aMaterial.mTargetRegistration.mID);
		}
	}
	
	@Optional.Method(modid = ModIDs.BOTA) @Override public Block getBlockToPlaceByFlower(ItemStack aStack, SubTileEntity aFlower, int aX, int aY, int aZ) {return null;}
	@Optional.Method(modid = ModIDs.BOTA) @Override public void onBlockPlacedByFlower(ItemStack aStack, SubTileEntity aFlower, int aX, int aY, int aZ) {/**/}
	
	@Override public final String getUnlocalizedName() {return mBlock.getUnlocalizedName();}
	@Override public String getItemStackDisplayName(ItemStack aStack) {return StatCollector.translateToLocal(getUnlocalizedName(aStack));}
	@Override public final boolean hasContainerItem(ItemStack aStack) {return getContainerItem(aStack) != null;}
	@Override public ItemStack getContainerItem(ItemStack aStack) {return null;}
	@Override public boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {return F;}
	@Override public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {updateItemStack(aStack);}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	@Override public int getItemEnchantability() {return 0;}
	@Override public int getItemStackLimit(ItemStack aStack) {return mBlock.mPrefix.mDefaultStackSize;}
	@Override public OreDictMaterial getMaterial(int aMetaData) {return UT.Code.exists(aMetaData, mBlock.mMaterialList) ? mBlock.mMaterialList[aMetaData] : null;}
	@Override public OreDictPrefix getPrefix(int aMetaData) {return mBlock.mPrefix;}
	/*
	@Override @Optional.Method(modid = ModIDs.TC) public void setAspects(ItemStack aStack, AspectList aAspectList) {}
	
	@Override @Optional.Method(modid = ModIDs.TC)
	public AspectList getAspects(ItemStack aStack) {
		List<TC_AspectStack> rAspects = new ArrayListNoNulls<TC_AspectStack>();
		for (TC_AspectStack tAspect : mBlock.mPrefix.mAspects) tAspect.addToAspectList(rAspects);
		OreDictMaterial aMaterial = (UT.Code.exists(aStack), mBlock.mMaterialList) ? mBlock.mMaterialList[ST.meta(aStack)] : null);
		if (aMaterial != null && (mBlock.mPrefix.mAmount >= U || mBlock.mPrefix.mAmount < 0)) for (TC_AspectStack tAspect : aMaterial.mAspects) tAspect.addToAspectList(rAspects);
		
		for (TC_AspectStack tAspect : rAspects) tAspect.mAmount = Math.min(10, tAspect.mAmount);
		return (AspectList)GT_API.sCompatTC.getAspectList(rAspects);
	}
	*/
}
