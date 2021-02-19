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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.Optional;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.ModIDs;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.item.ItemBase;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.energy.EnergyStatDebug;
import gregapi.lang.LanguageHandler;
import gregapi.util.ST;
import gregapi.util.UT;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 * 
 * For Custom Items.
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
})
public abstract class MultiItem extends ItemBase implements IItemEnergy {
	public final HashMap<Short, ArrayList<IBehavior<MultiItem>>> mItemBehaviors = new HashMap<>();
	
	public final HashMap<Short, Long[]> mFluidContainerStats = new HashMap<>();
	
	/**
	 * Creates the Item using these Parameters.
	 * @param aUnlocalized The Unlocalized Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 * @param aGeneratedPrefixList The OreDict Prefixes you want to have generated.
	 */
	public MultiItem(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized, "Generated Item", null);
		setHasSubtypes(T);
		setMaxDamage(0);
	}
	
	/**
	 * Adds a special Item Behaviour to the Item.
	 * 
	 * Note: the boolean Behaviours sometimes won't be executed if another boolean Behaviour returned true before.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to add it to. [0 - 32765]
	 * @param aBehavior the Click Behavior you want to add.
	 * @return the Item itself for convenience in constructing.
	 */
	public MultiItem addItemBehavior(int aMetaValue, IBehavior<MultiItem> aBehavior) {
		if (aMetaValue < 0 || aMetaValue >= 32766 || aBehavior == null) return this;
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)aMetaValue);
		if (tList == null) {
			tList = new ArrayListNoNulls<>();
			mItemBehaviors.put((short)aMetaValue, tList);
		}
		tList.add(aBehavior);
		return this;
	}
	
	@Override
	public boolean hasProjectile(TagData aProjectileType, ItemStack aStack) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) if (tBehavior.hasProjectile(this, aProjectileType, aStack)) return T;
		return super.hasProjectile(aProjectileType, aStack);
	}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) {
			EntityProjectile rArrow = tBehavior.getProjectile(this, aProjectileType, aStack, aWorld, aX, aY, aZ);
			if (rArrow != null) return rArrow;
		}
		return super.getProjectile(aProjectileType, aStack, aWorld, aX, aY, aZ);
	}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) {
			EntityProjectile rArrow = tBehavior.getProjectile(this, aProjectileType, aStack, aWorld, aEntity, aSpeed);
			if (rArrow != null) return rArrow;
		}
		return super.getProjectile(aProjectileType, aStack, aWorld, aEntity, aSpeed);
	}
	
	@Override
	public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) if (tBehavior.canDispense(this, aSource, aStack)) return tBehavior.onDispense(this, aSource, aStack);
		return super.onDispense(aSource, aStack);
	}
	
	@Override
	public boolean isItemStackUsable(ItemStack aStack) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) if (!tBehavior.isItemStackUsable(this, aStack)) return F;
		return super.isItemStackUsable(aStack);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack aStack, EntityPlayer aPlayer, EntityLivingBase aEntity) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) try {
			if (tBehavior.onRightClickEntity(this, aStack, aPlayer, aEntity)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) try {
			if (tBehavior.onLeftClickEntity(this, aStack, aPlayer, aEntity)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	
	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		if (MD.BbLC.owns(aWorld, aX, aY, aZ)) return F;
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) try {
			if (tBehavior.onItemUse(this, aStack, aPlayer, aWorld, aX, aY, aZ, UT.Code.side(aSide), hitX, hitY, hitZ)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		if (MD.BbLC.owns(aWorld, aX, aY, aZ)) return F;
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) try {
			if (tBehavior.onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, UT.Code.side(aSide), hitX, hitY, hitZ)) {
				if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
				return T;
			}
			if (aStack.stackSize <= 0) {
				aPlayer.destroyCurrentEquippedItem();
				return F;
			}
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return F;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		useEnergy(TD.Energy.EU, aStack, 0, aPlayer, null, null, 0, 0, 0, T);
		isItemStackUsable(aStack);
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) try {
			aStack = tBehavior.onItemRightClick(this, aStack, aWorld, aPlayer);
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		return aStack;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		isItemStackUsable(aStack);
		
		String tKey = getUnlocalizedName(aStack) + ".tooltip", tString = LanguageHandler.translate(tKey, tKey);
		if (UT.Code.stringValid(tString) && !tKey.equals(tString)) aList.add(tString);
		
		IItemEnergy tEnergyStats = getEnergyStats(aStack);
		if (tEnergyStats != null) {
			if (tEnergyStats instanceof EnergyStatDebug) {
				aList.add(LH.Chat.RAINBOW_SLOW + "Works as Infinite Energy Battery" + EnumChatFormatting.GRAY);
			} else {
				for (TagData tEnergyType : tEnergyStats.getEnergyTypes(aStack)) {
					long tCapacity = tEnergyStats.getEnergyCapacity(tEnergyType, aStack);
					aList.add(LH.Chat.WHITE + UT.Code.makeString(Math.min(tCapacity, tEnergyStats.getEnergyStored(tEnergyType, aStack))) + " / " + UT.Code.makeString(tCapacity) + " " + tEnergyType.getChatFormat() + tEnergyType.getLocalisedNameShort() + LH.Chat.WHITE + " - Size: " + tEnergyStats.getEnergySizeInputRecommended(tEnergyType, aStack) + EnumChatFormatting.GRAY);
				}
			}
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats != null && tStats[0] > 0) {
			FluidStack tFluid = getFluidContent(aStack);
			aList.add(LH.Chat.BLUE + ((tFluid==null?"No Fluids Contained":FL.name(tFluid, T))) + LH.Chat.GRAY);
			aList.add(LH.Chat.BLUE + ((tFluid==null?0:tFluid.amount) + "L / " + tStats[0] + "L") + LH.Chat.GRAY);
		}
		
		addAdditionalToolTips(aList, aStack, aF3_H);
		
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) aList = tBehavior.getAdditionalToolTips(this, aList, aStack);
	}
	
	@Override
	public void onUpdate(ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {
		ArrayList<IBehavior<MultiItem>> tList = mItemBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) tBehavior.onUpdate(this, aStack, aWorld, aPlayer, aTimer, aIsInHand);
	}
	
	public FluidStack getFluid(ItemStack aStack) {return getFluidContent(aStack);}
	
	public int getCapacity(ItemStack aStack) {
		Long[] tStats = getFluidContainerStats(aStack);
		return tStats==null?0:(int)Math.max(0, tStats[0]);
	}
	
	public int fill(ItemStack aStack, FluidStack aFluid, boolean doFill) {
		if (aStack == null || aStack.stackSize != 1) return 0;
		
		ItemStack tStack = FL.fill(aFluid, aStack, F, F, F, F);
		if (tStack != null) {
			aStack.setItemDamage(ST.meta_(tStack));
			aStack.func_150996_a(tStack.getItem());
			return FL.getFluid(tStack, F).amount;
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0 || aFluid == null || aFluid.amount <= 0 || !isAllowedToFill(aStack, aFluid)) return 0;
		
		FluidStack tFluid = getFluidContent(aStack);
		
		if (tFluid == null) {
			if (aFluid.amount <= tStats[0]) {
				if (doFill) {
					setFluidContent(aStack, aFluid);
				}
				return aFluid.amount;
			}
			if (doFill) {
				tFluid = aFluid.copy();
				tFluid.amount = (int)(long)tStats[0];
				setFluidContent(aStack, tFluid);
			}
			return (int)(long)tStats[0];
		}
		
		if (!tFluid.isFluidEqual(aFluid)) return 0;
		
		int space = (int)(long)tStats[0] - tFluid.amount;
		if (aFluid.amount <= space) {
			if (doFill) {
				tFluid.amount += aFluid.amount;
				setFluidContent(aStack, tFluid);
			}
			return aFluid.amount;
		}
		if (doFill) {
			tFluid.amount = (int)(long)tStats[0];
			setFluidContent(aStack, tFluid);
		}
		return space;
	}
	
	public boolean isAllowedToFill(ItemStack aStack, FluidStack aFluid) {return T;}
	
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		if (aStack == null || aStack.stackSize != 1) return null;
		
		FluidStack tFluid = FL.getFluid(aStack, F);
		if (tFluid != null && aMaxDrain >= tFluid.amount) {
			if (aDoDrain) {
				ItemStack tStack = ST.container(aStack, F);
				if (tStack == null) {
					aStack.stackSize = 0;
					return tFluid;
				}
				aStack.setItemDamage(ST.meta_(tStack));
				aStack.func_150996_a(tStack.getItem());
			}
			return tFluid;
		}
		
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0) return null;
		
		tFluid = getFluidContent(aStack);
		if (tFluid == null) return null;
		
		if (tFluid.amount < aMaxDrain) aMaxDrain = tFluid.amount;
		if (aDoDrain) {
			tFluid.amount -= aMaxDrain;
			setFluidContent(aStack, tFluid);
		}
		
		FluidStack tDrained = tFluid.copy();
		tDrained.amount = aMaxDrain;
		return tDrained;
	}
	
	public FluidStack getFluidContent(ItemStack aStack) {
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats == null || tStats[0] <= 0) return FL.getFluid(aStack, F);
		return FL.load(aStack.getTagCompound(), "gt.fluidcontent");
	}
	
	public void setFluidContent(ItemStack aStack, FluidStack aFluid) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make(); else tNBT.removeTag("gt.fluidcontent");
		if (aFluid != null && aFluid.amount > 0) FL.save(tNBT, "gt.fluidcontent", aFluid);
		UT.NBT.set(aStack, tNBT);
		isItemStackUsable(aStack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack aStack) {
		if (aStack.hasTagCompound() && aStack.getTagCompound().hasKey(NBT_ENERGY)) return 1;
		Long[] tStats = getFluidContainerStats(aStack);
		if (tStats != null) return (int)(long)tStats[1];
		return UT.Code.bindStack(getDefaultStackLimit(aStack));
	}
	
	public int getDefaultStackLimit(ItemStack aStack) {return 64;}
	
	public double charge   (ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
		if (aCharge < V[aTier = UT.Code.bind4(aTier)]) return 0;
		return V[aTier] * doEnergyInjection (TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	public double discharge(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aBatteryAlike, boolean aSimulate) {
		if (aCharge < V[aTier = UT.Code.bind4(aTier)]) return 0;
		return V[aTier] * doEnergyExtraction(TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	@Override
	public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return F;
		return tStats.isEnergyType(aEnergyType, aStack, aEmitting);
	}
	
	@Override
	public long doEnergyInjection (TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.doEnergyInjection (aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoInject);
	}
	
	@Override
	public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.doEnergyExtraction(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoExtract);
	}
	
	@Override
	public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return F;
		return tStats.useEnergy(aEnergyType, aStack, aEnergyAmount, aPlayer, aInventory, aWorld, aX, aY, aZ, aDoUse);
	}
	
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return aStack;
		return tStats.setEnergyStored(aEnergyType, aStack, aAmount);
	}
	
	@Override
	public long getEnergyStored(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergyStored(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergyCapacity(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputMin(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputMin(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputRecommended(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputRecommended(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeInputMax(aEnergyType, aStack);
	}
	
	@Override
	public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return 0;
		return tStats.getEnergySizeOutputMax(aEnergyType, aStack);
	}
	
	@Override
	public Collection<TagData> getEnergyTypes(ItemStack aStack) {
		IItemEnergy tStats = getEnergyStats(aStack);
		if (tStats == null) return Collections.emptyList();
		return tStats.getEnergyTypes(aStack);
	}
	
	@Override
	public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {
		IItemEnergy tStats = getEnergyStats(aStack);
		return tStats != null && tStats.canEnergyInjection(aEnergyType, aStack, aSize);
	}
	
	@Override
	public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize) {
		IItemEnergy tStats = getEnergyStats(aStack);
		return tStats != null && tStats.canEnergyExtraction(aEnergyType, aStack, aSize);
	}
	
	public float discharge(ItemStack aStack, float aEnergy, boolean aDoExtract) {
		if (aEnergy <= 0) return 0;
		long tMaxOut = getEnergySizeOutputMax(TD.Energy.EU, aStack);
		if (!canEnergyExtraction(TD.Energy.EU, aStack, tMaxOut)) return 0;
		long tAmount = UT.Code.bind(1, tMaxOut, (long)(aEnergy / EnergyConfigHandler.IC2_RATIO));
		return useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, F) && useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, T) ? tAmount * EnergyConfigHandler.IC2_RATIO : 0;
	}
	
	public String getToolTip(ItemStack aStack) {return null;} // This has its own ToolTip Handler, no need to let the IC2 Handler screw us up at this Point
	public Item getChargedItem(ItemStack itemStack) {return this;}
	public Item getEmptyItem(ItemStack itemStack) {return this;}
	public int getTier(ItemStack aStack) {return UT.Code.tierMax(getEnergySizeInputMax(TD.Energy.EU, aStack));}
	@Override public int getItemEnchantability() {return 0;}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	public boolean canProvideEnergy(ItemStack aStack) {return T;}
	public double getMaxCharge(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack);}
	public double getTransferLimit(ItemStack aStack) {return getEnergySizeInputRecommended(TD.Energy.EU, aStack);}
	public double getCharge(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack);}
	public boolean canUse(ItemStack aStack, double aAmount) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, null, null, null, 0, 0, 0, F);}
	public boolean use(ItemStack aStack, double aAmount, EntityLivingBase aPlayer) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, aPlayer, null, null, 0, 0, 0, T);}
	public void chargeFromArmor(ItemStack aStack, EntityLivingBase aPlayer) {/* No longer in this Part of the Code, its in the EnergyStats now.*/}
	public float getElectricityStored(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack) * EnergyConfigHandler.IC2_RATIO;}
	public float getMaxElectricityStored(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack) * EnergyConfigHandler.IC2_RATIO;}
	public void setElectricity(ItemStack aStack, float joules) {/**/}
	public float recharge(ItemStack aStack, float aEnergy, boolean aDoInject) {return 0;}
	public float getTransfer(ItemStack aStack) {return 0;}
	public int getTierGC(ItemStack aStack) {return 1;}
	
	public abstract IItemEnergy getEnergyStats(ItemStack aStack);
	public abstract Long[] getFluidContainerStats(ItemStack aStack);
}
