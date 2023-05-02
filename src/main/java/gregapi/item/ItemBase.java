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

package gregapi.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.lang.LanguageHandler;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class ItemBase extends Item implements IItemProjectile, IItemUpdatable, IItemGT, IItemNoGTOverride {
	protected IIcon mIcon;
	protected final String mModID;
	protected final String mName, mTooltip;
	
	/**
	 * @param aUnlocalized The unlocalised Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 */
	public ItemBase(String aModID, String aUnlocalized, String aEnglish, String aEnglishTooltip) {
		super();
		if (GAPI.mStartedInit) throw new IllegalStateException("Items can only be initialised within preInit!");
		mName = aUnlocalized;
		mModID = aModID;
		LH.add(mName, aEnglish);
		if (UT.Code.stringValid(aEnglishTooltip)) LH.add(mTooltip = mName + ".tooltip_main", aEnglishTooltip); else mTooltip = null;
		ST.register(this, mName);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, new GT_Item_Dispense());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		try {
			if (getMaxDamage() > 0 && !getHasSubtypes()) aList.add((aStack.getMaxDamage() - getDamage(aStack)) + " / " + aStack.getMaxDamage());
			if (mTooltip != null) aList.add(LanguageHandler.translate(mTooltip, mTooltip));
			addAdditionalToolTips(aList, aStack, aF3_H);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
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
			return ((ItemBase)aStack.getItem()).onDispense(aSource, aStack);
		}
		
		@Override
		protected IProjectile getProjectileEntity(World aWorld, IPosition aPosition) {
			return null;
		}
	}
	
	@Override public boolean hasProjectile(TagData aProjectileType, ItemStack aStack) {return F;}
	@Override public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {return null;}
	@Override public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {return null;}
	@Override public final Item setUnlocalizedName(String aName) {return this;}
	@Override public String toString() {return mName;}
	@Override public final String getUnlocalizedName() {return mName;}
	@Override public String getUnlocalizedName(ItemStack aStack) {return getHasSubtypes()?mName+"."+ST.meta_(aStack):mName;}
	@Override public String getItemStackDisplayName(ItemStack aStack) {return StatCollector.translateToLocal(getUnlocalizedName(aStack));}
	@Override public final boolean getShareTag() {return T;} // just to be sure.
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aIconRegister) {mIcon = aIconRegister.registerIcon(mModID + ":" + mName);}
	@Override public IIcon getIconFromDamage(int aMeta) {return mIcon;}
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
