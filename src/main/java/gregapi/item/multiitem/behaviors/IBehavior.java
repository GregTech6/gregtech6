/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.TagData;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.multiitem.MultiItem;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBehavior<E extends Item> {
	public boolean onLeftClickEntity(E aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity);
	public boolean onRightClickEntity(E aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity);
	public boolean onItemUse(E aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ);
	public boolean onItemUseFirst(E aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ);
	public ItemStack onItemRightClick(E aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer);
	public List<String> getAdditionalToolTips(E aItem, List<String> aList, ItemStack aStack);
	public void onUpdate(E aItem, ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand);
	public boolean isItemStackUsable(E aItem, ItemStack aStack);
	public boolean canDispense(E aItem, IBlockSource aSource, ItemStack aStack);
	public ItemStack onDispense(E aItem, IBlockSource aSource, ItemStack aStack);
	public boolean hasProjectile(E aItem, TagData aProjectileType, ItemStack aStack);
	public EntityProjectile getProjectile(E aItem, TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ);
	public EntityProjectile getProjectile(E aItem, TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed);
	
	public abstract class AbstractBehaviorDefault implements IBehavior<MultiItem> {
		@Override public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {return F;}
		@Override public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {return F;}
		@Override public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {return F;}
		@Override public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {return F;}
		@Override public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {return aStack;}
		@Override public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {return aList;}
		@Override public void onUpdate(MultiItem aItem, ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {/**/}
		@Override public boolean isItemStackUsable(MultiItem aItem, ItemStack aStack) {return T;}
		@Override public boolean canDispense(MultiItem aItem, IBlockSource aSource, ItemStack aStack) {return F;}
		@Override public boolean hasProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack) {return F;}
		@Override public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {return null;}
		@Override public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {return null;}
		
		@Override
		public ItemStack onDispense(MultiItem aItem, IBlockSource aSource, ItemStack aStack) {
			EnumFacing enumfacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
			IPosition iposition = BlockDispenser.func_149939_a(aSource);
			ItemStack itemstack1 = aStack.splitStack(1);
			BehaviorDefaultDispenseItem.doDispense(aSource.getWorld(), itemstack1, 6, enumfacing, iposition);
			return aStack;
		}
	}
	
	@Deprecated public abstract class Behaviour_None extends AbstractBehaviorDefault {/**/}
}
