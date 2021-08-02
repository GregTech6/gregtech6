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

package gregapi.item.multiitem;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enviromine.handlers.EM_StatusManager;
import enviromine.trackers.EnviroDataTracker;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackSet;
import gregapi.code.TagData;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ICover;
import gregapi.data.CS.ModIDs;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.energy.EnergyStatDebug;
import gregapi.item.multiitem.food.IFoodStat;
import gregapi.item.prefixitem.PrefixItem;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 * 
 * For Custom Items.
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = ModIDs.APC)
, @Optional.Interface(iface = "ic2.api.item.IItemReactorPlanStorage", modid = ModIDs.IC2C)
, @Optional.Interface(iface = "ic2.api.item.IBoxable", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
})
public abstract class MultiItemRandom extends MultiItem implements Runnable, squeek.applecore.api.food.IEdible, ic2.api.item.IBoxable, ic2.api.item.IItemReactorPlanStorage, ISpecialElectricItem, IElectricItemManager, IItemElectric {
	public final BitSet mEnabledItems = new BitSet(32767);
	public final BitSet mVisibleItems = new BitSet(32767);
	public final IIcon[][] mIconList = new IIcon[32767][1];
	
	public final HashMap<Short, IFoodStat> mFoodStats = new HashMap<>();
	public final HashMap<Short, IItemEnergy> mElectricStats = new HashMap<>();
	public final HashMap<Short, Short> mBurnValues = new HashMap<>();
	
	/**
	 * Creates the Item using these Parameters.
	 * @param aUnlocalized The unlocalised Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 */
	public MultiItemRandom(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		// Execute after all the other things. This is to ensure that MultiItems are created after PrefixItems.
		GAPI.mBeforeInit.add(this);
	}
	
	/**
	 * Add your Items here, and not within the Constructor.
	 * This gets called after all the PrefixItems and PrefixBlocks have been registered to the OreDict, what is during the @Init-Phase of the regular API.
	 */
	public abstract void addItems();
	
	private boolean mAllowedToAddItems = F;
	
	@Override
	public final void run() {
		mAllowedToAddItems = T;
		addItems();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		if (tStat != null && (UT.Entities.isCreative(aPlayer) || aPlayer.getFoodStats().needFood() || tStat.alwaysEdible(this, aStack, aPlayer))) aPlayer.setItemInUse(aStack, getMaxItemUseDuration(aStack));
		return super.onItemRightClick(aStack, aWorld, aPlayer);
	}
	
	protected short mLastID = W;
	public ItemStack last() {return ST.make(this, 1, mLastID);}
	public ItemStack next() {return ST.make(this, 1, mLastID+1);}
	
	/**
	 * This adds a Custom Item.
	 * @param aID The Id of the assigned Item [0 - 32766]
	 * @param aEnglish The Default Localised Name of the created Item
	 * @param aToolTip The Default ToolTip of the created Item, you can also insert null for having no ToolTip
	 * @param aFoodBehavior The Food Value of this Item. Can be null aswell. Just a convenience thing.
	 * @param aRandomData The OreDict Names you want to give the Item. Also used for TC Aspects and some other things.
	 * @return An ItemStack containing the newly created Item.
	 */
	@SuppressWarnings("unchecked")
	public final ItemStack addItem(int aID, String aEnglish, String aToolTip, Object... aRandomData) {
		if (aToolTip == null) aToolTip = "";
		if (mAllowedToAddItems && aID >= 0 && aID < 32767 && aID != W) {
			mLastID = (short)aID;
			ItemStack aStack = ST.make(this, 1, aID);
			if (UT.Code.stringValid(aEnglish)) {
				mEnabledItems.set(aID);
				mVisibleItems.set(aID);
				LH.add(getUnlocalizedName(aStack) + ".name", aEnglish);
				LH.add(getUnlocalizedName(aStack) + ".tooltip", aToolTip);
			}
			List<TC_AspectStack> tAspects = new ArrayListNoNulls<>();
			// Important Stuff to do first
			for (Object tRandomData : aRandomData) if (tRandomData instanceof TagData) {
				if (tRandomData == TD.Creative.HIDDEN           ) {mVisibleItems.set(aID, F); continue;}
				if (tRandomData == TD.Properties.AUTO_BLACKLIST ) {OM.blacklist_(aStack); continue;}
			}
			// now check for the rest
			for (Object tRandomData : aRandomData) if (tRandomData != null) {
				boolean tUseOreDict = T;
				if (tRandomData instanceof ItemStackSet) {
					((ItemStackSet<?>)tRandomData).add(aStack);
					continue;
				}
				if (tRandomData instanceof TagData) {
					continue;
				}
				if (tRandomData instanceof Number) {
					setBurnValue(aID, ((Number)tRandomData).intValue());
					continue;
				}
				if (tRandomData instanceof IFoodStat) {
					setFoodBehavior(aID, (IFoodStat)tRandomData);
					if (IL.IC2_Food_Can_Empty.exists() && IL.IC2_Food_Can_Filled.exists() && getContainerItem(aStack) == null) {
						int tFoodValue = ((IFoodStat)tRandomData).getFoodLevel(this, aStack, null);
						if (tFoodValue > 0) RM.Canner.addRecipe2(T, 16, tFoodValue * 16, aStack, IL.IC2_Food_Can_Empty.get(tFoodValue), ((IFoodStat)tRandomData).isRotten(this, aStack, null)?IL.IC2_Food_Can_Spoiled.get(tFoodValue, IL.IC2_Food_Can_Filled.get(tFoodValue)):IL.IC2_Food_Can_Filled.get(tFoodValue));
					}
					tUseOreDict = F;
				}
				if (tRandomData instanceof ICover) {
					CoverRegistry.put(aStack, (ICover)tRandomData);
					tUseOreDict = F;
				}
				if (tRandomData instanceof IBehavior) {
					addItemBehavior(aID, (IBehavior<MultiItem>)tRandomData);
					tUseOreDict = F;
				}
				if (tRandomData instanceof IItemEnergy) {
					setElectricStats(aID, (IItemEnergy)tRandomData);
					tUseOreDict = F;
				}
				if (tRandomData instanceof IItemContainer) {
					((IItemContainer)tRandomData).set(aStack);
					tUseOreDict = F;
				}
				if (tRandomData instanceof TC_AspectStack) {
					((TC_AspectStack)tRandomData).addToAspectList(tAspects);
					continue;
				}
				if (tRandomData instanceof OreDictItemData) {
					if (((OreDictItemData)tRandomData).hasValidPrefixMaterialData()) {
						OM.reg(aStack, tRandomData);
						ItemStack tStack = ((OreDictItemData)tRandomData).getStack(1);
						// Priority over autogenerated PrefixItems, but not over the hardcoded Compatibility Targets.
						if (ST.invalid(tStack) || tStack.getItem() instanceof PrefixItem) {
							OreDictManager.INSTANCE.setTarget_(((OreDictItemData)tRandomData).mPrefix, ((OreDictItemData)tRandomData).mMaterial.mMaterial, aStack);
							continue;
						}
					}
					OreDictManager.INSTANCE.addItemData_(aStack, (OreDictItemData)tRandomData);
					continue;
				}
				if (tRandomData instanceof FluidStack) {
					tRandomData = new FluidContainerData((FluidStack)tRandomData, ST.copy_(aStack), getContainerItem(aStack), T);
				//  if (((FluidContainerData)tRandomData).emptyContainer != null)
				//  RM.Canner.addRecipe1(T, 16, Math.max(((FluidContainerData)tRandomData).fluid.amount / 64, 16), ((FluidContainerData)tRandomData).emptyContainer, ((FluidContainerData)tRandomData).fluid, NF, ((FluidContainerData)tRandomData).filledContainer);
				//  RM.Canner.addRecipe1(T, 16, Math.max(((FluidContainerData)tRandomData).fluid.amount / 64, 16), ((FluidContainerData)tRandomData).filledContainer, NF, ((FluidContainerData)tRandomData).fluid, ST.container(((FluidContainerData)tRandomData).filledContainer, F));
					FL.reg((FluidContainerData)tRandomData, T, F);
					continue;
				}
				if (tRandomData instanceof FluidContainerData) {
				//  if (((FluidContainerData)tRandomData).emptyContainer != null)
				//  RM.Canner.addRecipe1(T, 16, Math.max(((FluidContainerData)tRandomData).fluid.amount / 64, 16), ((FluidContainerData)tRandomData).emptyContainer, ((FluidContainerData)tRandomData).fluid, NF, ((FluidContainerData)tRandomData).filledContainer);
				//  RM.Canner.addRecipe1(T, 16, Math.max(((FluidContainerData)tRandomData).fluid.amount / 64, 16), ((FluidContainerData)tRandomData).filledContainer, NF, ((FluidContainerData)tRandomData).fluid, ST.container(((FluidContainerData)tRandomData).filledContainer, F));
					FL.reg((FluidContainerData)tRandomData, T, F);
					continue;
				}
				if (tRandomData instanceof Runnable) {
					GAPI.mAfterPostInit.add((Runnable)tRandomData);
					tUseOreDict = F;
				}
				if (tUseOreDict) {
					OM.reg(tRandomData, aStack);
					continue;
				}
			}
			if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(aStack, tAspects, F);
			
			return ST.update(ST.make(this, 1, aID));
		}
		return null;
	}
	
	/**
	 * Sets a Food Behavior for the Item.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32766]
	 * @param aFoodBehavior the Food Behavior you want to add.
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItemRandom setFoodBehavior(int aMetaValue, IFoodStat aFoodBehavior) {
		if (aMetaValue < 0 || aMetaValue >= W) return this;
		if (aFoodBehavior == null) mFoodStats.remove((short)aMetaValue); else mFoodStats.put((short)aMetaValue, aFoodBehavior);
		return this;
	}
	
	/**
	 * Sets the Furnace Burn Value for the Item.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32766]
	 * @param aValue 200 = 1 Burn Process = 500 EU, max = 32767 (that is 81917.5 EU)
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItemRandom setBurnValue(int aMetaValue, int aValue) {
		if (aMetaValue < 0 || aMetaValue >= W || aValue < 0) return this;
		if (aValue == 0) mBurnValues.remove((short)aMetaValue); else mBurnValues.put((short)aMetaValue, aValue>Short.MAX_VALUE?Short.MAX_VALUE:(short)aValue);
		return this;
	}
	
	/**
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32766]
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItemRandom setElectricStats(int aMetaValue, IItemEnergy aStats) {
		if (aMetaValue < 0 || aMetaValue >= W) return this;
		if (aStats == null) mElectricStats.remove((short)aMetaValue); else {
			mElectricStats.put((short)aMetaValue, aStats);
			if (!(aStats instanceof EnergyStatDebug)) mIconList[aMetaValue] = Arrays.copyOf(mIconList[aMetaValue], Math.max(9, mIconList[aMetaValue].length));
		}
		return this;
	}
	
	/**
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32766]
	 * @param aMaxCharge Maximum Charge. (if this is == 0 it will remove the Electric Behavior)
	 * @param aTransferLimit Transfer Limit.
	 * @param aTier The electric Tier.
	 * @param aSpecialData If this Item has a Fixed Charge, like a SingleUse Battery (if > 0).
	 * Use -1 if you want to make this Battery chargeable (the use and canUse Functions will still discharge if you just use this)
	 * Use -2 if you want to make this Battery dischargeable.
	 * Use -3 if you want to make this Battery charge/discharge-able.
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItemRandom setFluidContainerStats(int aMetaValue, long aCapacity, long aStacksize) {
		if (aMetaValue < 0 || aMetaValue >= W) return this;
		if (aCapacity < 0) mFluidContainerStats.remove((short)aMetaValue); else mFluidContainerStats.put((short)aMetaValue, new Long[] {aCapacity, Math.max(1, aStacksize)});
		return this;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack aStack) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		return tStat == null ? 0 : Math.max(tStat.getFoodLevel(this, aStack, null) * 8, 16);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack aStack) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		return tStat == null ? EnumAction.none : tStat.getFoodAction(this, aStack);
	}
	
	@Override
	public ItemStack onEaten(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		if (tStat != null) {
			
			int tFoodLevel = tStat.getFoodLevel(this, aStack, aPlayer);
			float tSaturationLevel = tStat.getSaturation(this, aStack, aPlayer);
			
			if (tFoodLevel * tSaturationLevel > 0) {
				if (tStat.useAppleCoreFunctionality(this, aStack, aPlayer) && MD.APC.mLoaded) {
					aPlayer.getFoodStats().func_151686_a((ItemFood)UT.Reflection.callConstructor("squeek.applecore.api.food.ItemFoodProxy", 0, null, T, this), aStack);
				} else {
					aPlayer.getFoodStats().addStats(tFoodLevel, tSaturationLevel);
				}
			}
			
			if (!aWorld.isRemote && MD.ENVM.mLoaded) {
				try {
					float tTemperature = tStat.getTemperature(this, aStack, aPlayer) - C, tHydration = tStat.getHydration(this, aStack, aPlayer);
					Object tTracker = EM_StatusManager.lookupTracker(aPlayer);
					if (tTracker != null && ((EnviroDataTracker)tTracker).bodyTemp >= 0) {
						((EnviroDataTracker)tTracker).bodyTemp += (tTemperature - ((EnviroDataTracker)tTracker).bodyTemp) * tStat.getTemperatureEffect(this, aStack, aPlayer);
						if (tHydration > 0) ((EnviroDataTracker)tTracker).hydrate(tHydration); else if (tHydration < 0) ((EnviroDataTracker)tTracker).dehydrate(-tHydration);
					}
				} catch(Throwable e) {
					e.printStackTrace(ERR);
				}
			}
			tStat.onEaten(this, aStack, aPlayer, T, T);
		}
		return aStack;
	}
	
	@Override
	public IItemEnergy getEnergyStats(ItemStack aStack) {
		return mElectricStats.get(ST.meta_(aStack));
	}
	
	@Override
	public Long[] getFluidContainerStats(ItemStack aStack) {
		return mFluidContainerStats.get(ST.meta_(aStack));
	}
	
	@Override
	@Optional.Method(modid = ModIDs.APC)
	public squeek.applecore.api.food.FoodValues getFoodValues(ItemStack aStack) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		if (tStat == null) return null;
		int tFoodLevel = tStat.getFoodLevel(this, aStack, null);
		return tFoodLevel > 0 ? new squeek.applecore.api.food.FoodValues(tFoodLevel, tStat.getSaturation(this, aStack, null)) : null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item aItem, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		if (aItem == this) for (int i = 0, j = mEnabledItems.length(); i < j; i++) if (mVisibleItems.get(i) || (SHOW_HIDDEN_ITEMS && mEnabledItems.get(i))) {
			IItemEnergy tStats = mElectricStats.get((short)i);
			if (tStats == null || tStats instanceof EnergyStatDebug) {
				ItemStack tStack = ST.make(this, 1, i);
				isItemStackUsable(tStack);
				aList.add(tStack);
			} else {
				ItemStack
				tStack = ST.make(this, 1, i);
				isItemStackUsable(tStack);
				aList.add(tStack);
				tStack = ST.make(this, 1, i);
				for (TagData tEnergyType : tStats.getEnergyTypes(tStack)) tStats.setEnergyStored(tEnergyType, tStack, tStats.getEnergyCapacity(tEnergyType, tStack));
				isItemStackUsable(tStack);
				aList.add(tStack);
			}
		}
		if (aList.isEmpty()) ST.hide(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		for (short aMeta = 0, tMaxMeta = (short)mEnabledItems.length(); aMeta < tMaxMeta; aMeta++) if (mEnabledItems.get(aMeta)) {
			for (byte k = 1; k < mIconList[aMeta].length; k++) {
				mIconList[aMeta][k] = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/" + aMeta + "/" + k);
			}
			mIconList[aMeta][0] = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/" + aMeta);
		}
	}
	
	@Override
	public IIcon getIconIndex(ItemStack aStack) {
		short aMetaData = ST.meta_(aStack);
		if (!UT.Code.exists(aMetaData, mIconList)) return Textures.ItemIcons.RENDERING_ERROR.getIcon(0);
		IItemEnergy tStats = mElectricStats.get(aMetaData);
		if (tStats != null && mIconList[aMetaData].length > 1) {
			TagData tEnergyType = tStats.getEnergyTypes(aStack).iterator().next();
			long tStored = tStats.getEnergyStored(tEnergyType, aStack), tCapacity = tStats.getEnergyCapacity(tEnergyType, aStack);
			if (tStored <= 0) return mIconList[aMetaData][1];
			if (tStored >= tCapacity) return mIconList[aMetaData][8];
			return mIconList[aMetaData][7-(int)Math.max(0, Math.min(5, ((tCapacity-tStored)*6L) / tCapacity))];
		}
		return mIconList[aMetaData][0];
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		return getIconIndex(aStack);
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
		return getIcon(aStack, aRenderPass);
	}
	
	@Override
	public IIcon getIconFromDamage(int aMetaData) {
		return UT.Code.exists(aMetaData, mIconList) ? mIconList[aMetaData][0] : Textures.ItemIcons.RENDERING_ERROR.getIcon(0);
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		return UT.Code.exists(aMetaData, mIconList) ? mIconList[aMetaData][0] : Textures.ItemIcons.RENDERING_ERROR.getIcon(0);
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
		if (tStat != null) tStat.addAdditionalToolTips(this, aList, aStack, aF3_H);
	}
	
	@Override
	public boolean canBeStoredInToolbox(ItemStack aStack) {
		return mElectricStats.get(ST.meta(aStack)) != null;
	}
	
	@Override
	public boolean isPlanStorage(ItemStack aStack) {
		return OM.is(OD_USB_STICKS[2], aStack);
	}
	
	@Override
	public boolean setSetup(ItemStack aStack, String aSetup) {
		if (OM.is(OD_USB_STICKS[2], aStack)) {
			if (!aStack.hasTagCompound()) aStack.setTagCompound(UT.NBT.make());
			aStack.getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeString(UT.NBT.makeString(NBT_REACTOR_SETUP_NAME, ""+aSetup.hashCode()), NBT_REACTOR_SETUP, aSetup));
			aStack.getTagCompound().setByte(NBT_USB_TIER, (byte)2);
			return T;
		}
		return F;
	}
	
	@Override
	public void setPlanName(ItemStack aStack, String aName) {
		aStack.getTagCompound().getCompoundTag(NBT_USB_DATA).setString(NBT_REACTOR_SETUP_NAME, aName);
	}
	
	@Override
	public boolean hasSetup(ItemStack aStack) {
		return OM.is(OD_USB_STICKS[2], aStack) && aStack.hasTagCompound() && aStack.getTagCompound().getCompoundTag(NBT_USB_DATA).hasKey(NBT_REACTOR_SETUP);
	}
	
	@Override
	public String getSetup(ItemStack aStack) {
		return aStack.getTagCompound().getCompoundTag(NBT_USB_DATA).getString(NBT_REACTOR_SETUP);
	}
	
	@Override @Optional.Method(modid = ModIDs.IC2)
	public IElectricItemManager getManager(ItemStack aStack) {return this;}
}
