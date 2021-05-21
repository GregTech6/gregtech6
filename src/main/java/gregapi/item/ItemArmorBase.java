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

package gregapi.item;

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.IArmorApiarist;
import gregapi.GT_API;
import gregapi.data.CS.ModIDs;
import gregapi.data.LH;
import gregapi.lang.LanguageHandler;
import gregapi.oredict.OreDictItemData;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import ic2.api.item.IMetalArmor;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "ic2.api.item.IMetalArmor", modid = ModIDs.IC2),
  @Optional.Interface(iface = "forestry.api.apiculture.IArmorApiarist", modid = ModIDs.FR)
})
public class ItemArmorBase extends ItemArmor implements IItemUpdatable, IItemGT, IItemNoGTOverride, ISpecialArmor, IMetalArmor, IArmorApiarist {
	protected IIcon mIcon;
	protected final String mModID;
	protected final String mName, mTooltip;
	
	public int mEnchantability;
	public boolean mMetalArmor = F, mBeeArmor = F;
	public String mArmorTexture, mArmorName;
	
	/**
	 * @param aUnlocalized The unlocalised Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 */
	public ItemArmorBase(String aModID, String aUnlocalized, String aEnglish, String aEnglishTooltip, String aArmorName, int aSlot, int[] aShields, int aDurability, int aDamageReduction, int aEnchantability, boolean aMetalArmor, boolean aBeeArmor, Object... aRecipe) {
		super(EnumHelper.addArmorMaterial("armor."+aUnlocalized, aDurability, aShields, aEnchantability), GT_API.api_proxy.addArmor(TEX_DIR_ARMOR+aArmorName), aSlot);
		if (GAPI.mStartedInit) throw new IllegalStateException("Items can only be initialised within preInit!");
		mName = aUnlocalized;
		mModID = aModID;
		mArmorTexture = mModID+":"+TEX_DIR_ARMOR+aArmorName+"/"+armorType+".png";
		mArmorName = aArmorName;
		mEnchantability = aEnchantability;
		mMetalArmor = aMetalArmor;
		mBeeArmor = aBeeArmor;
		LH.add(mName + ".name", aEnglish);
		setMaxDamage(aDurability);
		setCreativeTab(CreativeTabs.tabCombat);
		if (UT.Code.stringValid(aEnglishTooltip)) LH.add(mTooltip = mName + ".tooltip_main", aEnglishTooltip); else mTooltip = null;
		ST.register(this, mName);
		if (aRecipe != null && aRecipe.length > 0) {
			CR.shaped(ST.make(this, 1, 0), CR.DEF_REV_NCC, aRecipe);
			OreDictItemData tData = OM.data(ST.make(this, 1, 0));
			if (tData != null) tData.setUseVanillaDamage();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		if (getMaxDamage() > 0 && !getHasSubtypes()) aList.add((aStack.getMaxDamage() - getDamage(aStack)) + " / " + aStack.getMaxDamage());
		if (mTooltip != null) aList.add(LanguageHandler.translate(mTooltip, mTooltip));
		addAdditionalToolTips(aList, aStack, aF3_H);
		while (aList.remove(null));
	}
	
	protected void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		//
	}
	
	public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
		EnumFacing enumfacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(aSource);
		ItemStack itemstack1 = aStack.splitStack(1);
		BehaviorDefaultDispenseItem.doDispense(aSource.getWorld(), itemstack1, 6, enumfacing, iposition);
		return aStack;
	}
	
	public static class GT_Item_Dispense extends BehaviorProjectileDispense {
		@Override
		public ItemStack dispenseStack(IBlockSource aSource, ItemStack aStack) {
			return ((ItemArmorBase)aStack.getItem()).onDispense(aSource, aStack);
		}
		
		@Override
		protected IProjectile getProjectileEntity(World aWorld, IPosition aPosition) {
			return null;
		}
	}
	
	@Override public String getArmorTexture(ItemStack aStack, Entity aEntity, int aSlot, String aType) {return mArmorTexture;}
	@Override public ArmorProperties getProperties(EntityLivingBase aPlayer, ItemStack aStack, DamageSource aSource, double aDamage, int aSlot) {return aSource.isUnblockable() ? new ArmorProperties(0, 0, 0) : new ArmorProperties(0, damageReduceAmount / 25.0, getMaxDamage() + 1 - aStack.getItemDamage());}
	@Override public int getArmorDisplay(EntityPlayer aPlayer, ItemStack aStack, int aSlot) {return getArmorMaterial().getDamageReductionAmount(aSlot);}
	@Override public void damageArmor(EntityLivingBase aEntity, ItemStack aStack, DamageSource aSource, int aDamage, int aSlot) {aStack.damageItem(aDamage, aEntity);}
	@Override public boolean isMetalArmor(ItemStack aStack, EntityPlayer aPlayer) {return mMetalArmor;}
	@Override public boolean protectEntity(EntityLivingBase aPlayer, ItemStack aArmor, String aCause, boolean doProtect) {return mBeeArmor;}
	@Override @SuppressWarnings("deprecation") public boolean protectPlayer(EntityPlayer aPlayer, ItemStack aArmor, String aCause, boolean doProtect) {return mBeeArmor;}
	@Override public int getColorFromItemStack(ItemStack aStack, int aRenderpass) {return UNCOLORED;}
	@Override public boolean requiresMultipleRenderPasses() {return F;}
	@Override public boolean hasColor(ItemStack aStack) {return F;}
	@Override public int getColor(ItemStack aStack) {return -1;}
	@Override public void removeColor(ItemStack aStack) {/**/}
	@Override public void func_82813_b(ItemStack aStack, int aDyeIndex) {/**/}
	@Override public boolean getIsRepairable(ItemStack aStack1, ItemStack aStack2) {return F;}
	@Override public int getItemEnchantability() {return mEnchantability;}
	@Override public final Item setUnlocalizedName(String aName) {return this;}
	@Override public String toString() {return mName;}
	@Override public final String getUnlocalizedName() {return mName;}
	@Override public String getUnlocalizedName(ItemStack aStack) {return getHasSubtypes()?mName+"."+getDamage(aStack):mName;}
	@Override public final boolean getShareTag() {return T;} // just to be sure.
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aIconRegister) {mIcon = aIconRegister.registerIcon(mModID + ":" + "armor/" + mArmorName + "/" + armorType);}
	@Override public IIcon getIconFromDamage(int aMeta) {return mIcon;}
	@Override public IIcon getIconFromDamageForRenderPass(int aMeta, int aRenderpass) {return mIcon;}
	@Override public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {isItemStackUsable(aStack);}
	@Override public ItemStack getContainerItem(ItemStack aStack) {return null;}
	@Override public boolean hasContainerItem(ItemStack aStack) {return getContainerItem(aStack) != null;}
	@Override public boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {return F;}
	@Override public void updateItemStack(ItemStack aStack) {isItemStackUsable(aStack);}
	@Override public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {updateItemStack(aStack);}
	@Override public boolean doesSneakBypassUse(World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {return T;}
	public boolean isItemStackUsable(ItemStack aStack) {return T;}
	public ItemStack make(long aMetaData) {return ST.make(this, 1, aMetaData);}
	public ItemStack make(long aAmount, long aMetaData) {return ST.make(this, aAmount, aMetaData);}
}
