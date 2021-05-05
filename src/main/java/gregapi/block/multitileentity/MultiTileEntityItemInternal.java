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

package gregapi.block.multitileentity;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.ModIDs;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.item.CreativeTab;
import gregapi.item.IItemColorableRGB;
import gregapi.item.IItemEnergy;
import gregapi.item.IItemGT;
import gregapi.item.IItemNoGTOverride;
import gregapi.item.IItemReactorRod;
import gregapi.item.IItemRottable;
import gregapi.item.IItemUpdatable;
import gregapi.oredict.IOreDictItemDataOverrideItem;
import gregapi.oredict.OreDictItemData;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityFoamable;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.util.OM;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.tileentity.energy.reactors.MultiTileEntityReactorCore;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;
import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import vazkii.botania.api.item.IFlowerPlaceable;
import vazkii.botania.api.subtile.SubTileEntity;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = ModIDs.APC)
, @Optional.Interface(iface = "ic2.api.item.ISpecialElectricItem", modid = ModIDs.IC2)
, @Optional.Interface(iface = "ic2.api.item.IElectricItemManager", modid = ModIDs.IC2)
, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.item.IItemElectric", modid = ModIDs.GC)
, @Optional.Interface(iface = "vazkii.botania.api.item.IFlowerPlaceable", modid = ModIDs.BOTA)
})
public class MultiTileEntityItemInternal extends ItemBlock implements squeek.applecore.api.food.IEdible, IItemReactorRod, IItemUpdatable, IItemColorableRGB, IOreDictItemDataOverrideItem, IItemGT, IItemNoGTOverride, IFluidContainerItem, ISpecialElectricItem, IElectricItemManager, IItemEnergy, IItemElectric, IItemRottable, IFlowerPlaceable {
	public final MultiTileEntityBlockInternal mBlock;
	
	public MultiTileEntityItemInternal(Block aBlock) {
		super(aBlock);
		setMaxDamage(0);
		setHasSubtypes(T);
		mBlock = (MultiTileEntityBlockInternal)aBlock;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetItemName) return ((IMTE_GetItemName)tTileEntityContainer.mTileEntity).getItemName(aStack, super.getItemStackDisplayName(aStack));
		return super.getItemStackDisplayName(aStack);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer == null) {aList.add("INVALID ITEM! THIS IS A BUG IF ACQUIRED IN A LEGIT WAY!"); return;}
		if (tTileEntityContainer.mTileEntity instanceof IMTE_AddToolTips) try {((IMTE_AddToolTips)tTileEntityContainer.mTileEntity).addToolTips(aList, aStack, aF3_H);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (tTileEntityContainer.mTileEntity instanceof IMTE_GetFlammability ? ((IMTE_GetFlammability)tTileEntityContainer.mTileEntity).getFlammability(SIDE_ANY, tTileEntityContainer.mBlock.getMaterial().getCanBurn()) > 0 : tTileEntityContainer.mBlock.getMaterial().getCanBurn()) aList.add(LH.Chat.RED + LH.get(LH.TOOLTIP_FLAMMABLE));
		if (tTileEntityContainer.mTileEntity instanceof ITileEntityCoverable) {
			CoverData tCoverData = ((ITileEntityCoverable)tTileEntityContainer.mTileEntity).getCoverData();
			if (tCoverData != null) for (byte tSide : ALL_SIDES_VALID) if (tCoverData.mBehaviours[tSide] != null) {aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_UNCOVER_CROWBAR)); break;}
		}
		if (tTileEntityContainer.mTileEntity instanceof IMTE_GetExplosionResistance) {
			float tResistance = ((IMTE_GetExplosionResistance)tTileEntityContainer.mTileEntity).getExplosionResistance();
			if (tResistance >= 4) aList.add(LH.getToolTipBlastResistance(mBlock, tResistance));
		}
		aList.add(LH.getToolTipHarvest(tTileEntityContainer.mBlock.getMaterial(), tTileEntityContainer.mBlock.getHarvestTool(tTileEntityContainer.mBlockMetaData), tTileEntityContainer.mBlock.getHarvestLevel(tTileEntityContainer.mBlockMetaData)));
		// Remove all Nulls and fix eventual Formatting mistakes. No longer needed because the Tooltip Event fixes it
		//for (int i = 0, j = aList.size(); i < j; i++) if (aList.get(i) == null) {aList.remove(i--); j--;} else aList.set(i, LH.Chat.GRAY + aList.get(i) + LH.Chat.RESET_TOOLTIP);
	}
	
	public int onDespawn(EntityItem aEntity, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_OnDespawn) try {return ((IMTE_OnDespawn)tTileEntityContainer.mTileEntity).onDespawn(aEntity, aStack);} catch(Throwable e) {e.printStackTrace(ERR);}
		return 0;
	}
	
	@Override
	public int getEntityLifespan(ItemStack aStack, World aWorld) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetLifeSpan) try {return ((IMTE_GetLifeSpan)tTileEntityContainer.mTileEntity).getLifeSpan(aWorld, aStack);} catch(Throwable e) {e.printStackTrace(ERR);}
		return super.getEntityLifespan(aStack, aWorld);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {
		if (aTab instanceof CreativeTab) {
			for (MultiTileEntityClassContainer tClass : mBlock.mMultiTileEntityRegistry.mRegistrations) if (!tClass.mHidden || SHOW_HIDDEN_ITEMS) if (((CreativeTab)aTab).mMetaData == tClass.mCreativeTabID) if (!(tClass.mCanonicalTileEntity instanceof IMTE_GetSubItems) || ((IMTE_GetSubItems)tClass.mCanonicalTileEntity).getSubItems(mBlock, aItem, aTab, aList, tClass.mID)) aList.add(mBlock.mMultiTileEntityRegistry.getItem(tClass.mID));
		} else {
			for (MultiTileEntityClassContainer tClass : mBlock.mMultiTileEntityRegistry.mRegistrations) if (!tClass.mHidden || SHOW_HIDDEN_ITEMS) if (!(tClass.mCanonicalTileEntity instanceof IMTE_GetSubItems) || ((IMTE_GetSubItems)tClass.mCanonicalTileEntity).getSubItems(mBlock, aItem, aTab, aList, tClass.mID)) aList.add(mBlock.mMultiTileEntityRegistry.getItem(tClass.mID));
		}
	}
	
	@Override
	public CreativeTabs[] getCreativeTabs() {
		return mBlock.mMultiTileEntityRegistry.mCreativeTabs.values().toArray(new CreativeTabs[mBlock.mMultiTileEntityRegistry.mCreativeTabs.size()]);
	}
	
	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aY < 0 || aY > aWorld.getHeight()) return F;
		
		try {
			Block tClickedBlock = aWorld.getBlock(aX, aY, aZ);
			if (tClickedBlock instanceof BlockSnow && (aWorld.getBlockMetadata(aX, aY, aZ) & 7) < 1) {
				aSide = SIDE_TOP;
			} else if (tClickedBlock != Blocks.vine && tClickedBlock != Blocks.tallgrass && tClickedBlock != Blocks.deadbush && !tClickedBlock.isReplaceable(aWorld, aX, aY, aZ)) {
				aX += OFFSETS_X[aSide]; aY += OFFSETS_Y[aSide]; aZ += OFFSETS_Z[aSide];
			}
			Block tReplacedBlock = aWorld.getBlock(aX, aY, aZ);
			
			if (!tReplacedBlock.isReplaceable(aWorld, aX, aY, aZ) || !mBlock.canReplace(aWorld, aX, aY, aZ, aSide, aStack)) return F;
			if (aStack.stackSize == 0 || (aPlayer != null && !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack))) return F;
			
			MultiTileEntityContainer aMTEContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aWorld, aX, aY, aZ, aStack);
			
			if (aMTEContainer != null
			&& (aPlayer == null || aPlayer.isSneaking() || !(aMTEContainer.mTileEntity instanceof IMTE_OnlyPlaceableWhenSneaking) || !((IMTE_OnlyPlaceableWhenSneaking)aMTEContainer.mTileEntity).onlyPlaceableWhenSneaking())
			&& ((aMTEContainer.mTileEntity instanceof IMTE_IgnorePlayerCollisionWhenPlacing && ((IMTE_IgnorePlayerCollisionWhenPlacing)aMTEContainer.mTileEntity).ignorePlayerCollisionWhenPlacing(aStack, aPlayer, aWorld, aX, aY, aZ, (byte)aSide, aHitX, aHitY, aHitZ)) || aWorld.checkNoEntityCollision(AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1)))
			&& (!(aMTEContainer.mTileEntity instanceof IMTE_CanPlace) || ((IMTE_CanPlace)aMTEContainer.mTileEntity).canPlace(aStack, aPlayer, aWorld, aX, aY, aZ, (byte)aSide, aHitX, aHitY, aHitZ))
			&& aWorld.setBlock(aX, aY, aZ, aMTEContainer.mBlock, 15-aMTEContainer.mBlockMetaData, 2)) {
				
				// That is some complicated Bullshit I have to do to make my MTEs work right.
				((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(F);
				WD.te (aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, F);
				WD.set(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, 0, F);
				((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(T);
				WD.te (aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, T);
				
				try {
					if (!(aMTEContainer.mTileEntity instanceof IMTE_OnPlaced) || ((IMTE_OnPlaced)aMTEContainer.mTileEntity).onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, (byte)aSide, aHitX, aHitY, aHitZ)) {
						aWorld.playSoundEffect(aX+0.5, aY+0.5, aZ+0.5, aMTEContainer.mBlock.stepSound.func_150496_b(), (aMTEContainer.mBlock.stepSound.getVolume()+1) / 2, aMTEContainer.mBlock.stepSound.getPitch()*0.8F);
					}
				} catch(Throwable e) {e.printStackTrace(ERR);}
				try {
					if (aMTEContainer.mTileEntity instanceof IMTE_HasMultiBlockMachineRelevantData) {
						if (((IMTE_HasMultiBlockMachineRelevantData)aMTEContainer.mTileEntity).hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, F);
					}
				} catch(Throwable e) {e.printStackTrace(ERR);}
				try {
					if (!aWorld.isRemote) {
						aWorld.notifyBlockChange(aX, aY, aZ, tReplacedBlock);
						aWorld.func_147453_f(aX, aY, aZ, aMTEContainer.mBlock);
					}
				} catch(Throwable e) {e.printStackTrace(ERR);}
				try {
					if (aMTEContainer.mTileEntity instanceof ITileEntity) {
						((ITileEntity)aMTEContainer.mTileEntity).onTileEntityPlaced();
					}
				} catch(Throwable e) {e.printStackTrace(ERR);}
				try {
					aWorld.func_147451_t(aX, aY, aZ);
				} catch(Throwable e) {e.printStackTrace(ERR);}
				
				aStack.stackSize--;
				return T;
			}
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		return F;
	}
	
	@Override
	public void updateItemStack(ItemStack aStack) {
		MultiTileEntityClassContainer tContainer = mBlock.mMultiTileEntityRegistry.getClassContainer(aStack);
		if (tContainer == null) return;
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemUpdatable) {
			((IItemUpdatable)tTileEntityContainer.mTileEntity).updateItemStack(aStack);
		}
	}
	@Override
	public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		MultiTileEntityClassContainer tContainer = mBlock.mMultiTileEntityRegistry.getClassContainer(aStack);
		if (tContainer == null) return;
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemUpdatable) {
			((IItemUpdatable)tTileEntityContainer.mTileEntity).updateItemStack(aStack, aWorld, aX, aY, aZ);
		}
	}
	
	@Override
	public int getItemStackLimit(ItemStack aStack) {
		MultiTileEntityClassContainer tContainer = mBlock.mMultiTileEntityRegistry.getClassContainer(aStack);
		if (tContainer == null) return 1;
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetMaxStackSize) {
			return UT.Code.bindStack(((IMTE_GetMaxStackSize)tTileEntityContainer.mTileEntity).getMaxStackSize(aStack, tContainer.mStackSize));
		}
		return tContainer.mStackSize;
	}
	
	@Override
	public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_OnCrafted) {
			updateItemStack(aStack);
			((IMTE_OnCrafted)tTileEntityContainer.mTileEntity).onCrafted(aPlayer, aWorld, aStack);
		}
		updateItemStack(aStack);
	}
	
	@Override
	public OreDictItemData getOreDictItemData(ItemStack aStack) {
		List<OreDictItemData> rList = new ArrayListNoNulls<>(F, OM.data(aStack));
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		// Yes I keep Covers a special case, less chances for fuck ups.
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof ITileEntityCoverable) {
			CoverData tCoverData = ((ITileEntityCoverable)tTileEntityContainer.mTileEntity).getCoverData();
			if (tCoverData != null) for (byte tSide : ALL_SIDES_VALID) rList.add(OM.anydata(tCoverData.getCoverItem(tSide)));
		}
		// Same for foamed Blocks.
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof ITileEntityFoamable && ((ITileEntityFoamable)tTileEntityContainer.mTileEntity).hasFoam(SIDE_ANY)) {
			rList.add(new OreDictItemData(MT.ConstructionFoam, U));
			if (((ITileEntityFoamable)tTileEntityContainer.mTileEntity).ownedFoam(SIDE_ANY)) rList.add(new OreDictItemData(MT.Pd, U4));
		}
		// General case for Custom additional OreDictItemData.
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetOreDictItemData) {
			rList = ((IMTE_GetOreDictItemData)tTileEntityContainer.mTileEntity).getOreDictItemData(rList);
		}
		return rList.isEmpty() ? null : rList.size() > 1 ? new OreDictItemData(rList) : rList.get(0);
	}
	
	@Override
	public FluidStack getFluid(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IFluidContainerItem) {
			FluidStack rFluid = ((IFluidContainerItem)tTileEntityContainer.mTileEntity).getFluid(aStack);
			updateItemStack(aStack);
			return rFluid;
		}
		return NF;
	}
	
	@Override
	public int getCapacity(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IFluidContainerItem) {
			int rCapacity = ((IFluidContainerItem)tTileEntityContainer.mTileEntity).getCapacity(aStack);
			updateItemStack(aStack);
			return rCapacity;
		}
		return 0;
	}
	
	@Override
	public int fill(ItemStack aStack, FluidStack aFluid, boolean aDoFill) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IFluidContainerItem) {
			int tFilled = ((IFluidContainerItem)tTileEntityContainer.mTileEntity).fill(aStack, aFluid, aDoFill);
			updateItemStack(aStack);
			return tFilled;
		}
		return 0;
	}
	
	@Override
	public FluidStack drain(ItemStack aStack, int aMaxDrain, boolean aDoDrain) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IFluidContainerItem) {
			FluidStack rFluid = ((IFluidContainerItem)tTileEntityContainer.mTileEntity).drain(aStack, aMaxDrain, aDoDrain);
			updateItemStack(aStack);
			return rFluid;
		}
		return NF;
	}
	
	@Override
	public boolean canRecolorItem(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemColorableRGB) {
			return ((IItemColorableRGB)tTileEntityContainer.mTileEntity).canRecolorItem(aStack);
		}
		return F;
	}
	
	@Override
	public boolean recolorItem(ItemStack aStack, int aRGB) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemColorableRGB) {
			if (((IItemColorableRGB)tTileEntityContainer.mTileEntity).recolorItem(aStack, aRGB)) {
				updateItemStack(aStack);
				return T;
			}
		}
		return F;
	}
	
	@Override
	public boolean canDecolorItem(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemColorableRGB) {
			return ((IItemColorableRGB)tTileEntityContainer.mTileEntity).canDecolorItem(aStack);
		}
		return F;
	}
	
	@Override
	public boolean decolorItem(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemColorableRGB) {
			if (((IItemColorableRGB)tTileEntityContainer.mTileEntity).decolorItem(aStack)) {
				updateItemStack(aStack);
				return T;
			}
		}
		return F;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_OnItemUseFirst) {
			boolean rReturn = ((IMTE_OnItemUseFirst)tTileEntityContainer.mTileEntity).onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, (byte)aSide, hitX, hitY, hitZ);
			updateItemStack(aStack);
			return rReturn;
		}
		return F;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_OnItemRightClick) {
			ItemStack rStack = ((IMTE_OnItemRightClick)tTileEntityContainer.mTileEntity).onItemRightClick(this, aStack, aWorld, aPlayer);
			if (aStack != rStack) updateItemStack(aStack);
			updateItemStack(rStack);
			return rStack;
		}
		return aStack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetMaxItemUseDuration) {
			int rDuration = ((IMTE_GetMaxItemUseDuration)tTileEntityContainer.mTileEntity).getMaxItemUseDuration(this, aStack);
			updateItemStack(aStack);
			return rDuration;
		}
		return 0;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetItemUseAction) {
			EnumAction rAction = ((IMTE_GetItemUseAction)tTileEntityContainer.mTileEntity).getItemUseAction(this, aStack);
			updateItemStack(aStack);
			return rAction;
		}
		return EnumAction.none;
	}
	
	@Override
	public ItemStack onEaten(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_OnEaten) {
			ItemStack rStack = ((IMTE_OnEaten)tTileEntityContainer.mTileEntity).onEaten(this, aStack, aWorld, aPlayer);
			if (aStack != rStack) updateItemStack(aStack);
			updateItemStack(rStack);
			return rStack;
		}
		return aStack;
	}
	
	@Override
	@Optional.Method(modid = ModIDs.APC)
	public squeek.applecore.api.food.FoodValues getFoodValues(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IMTE_GetFoodValues) return ((IMTE_GetFoodValues)tTileEntityContainer.mTileEntity).getFoodValues(this, aStack);
		return null;
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemRottable) return ((IItemRottable)tTileEntityContainer.mTileEntity).getRotten(aStack);
		return IItemRottable.RottingUtil.rotting(aStack, this);
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemRottable) return ((IItemRottable)tTileEntityContainer.mTileEntity).getRotten(aStack, aWorld, aX, aY, aZ);
		return IItemRottable.RottingUtil.rotting(aStack, this);
	}
	
	
	@Override
	public boolean isReactorRod(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).isReactorRod(aStack);
		return F;
	}

	@Override
	public boolean isModerated(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).isModerated(aReactor, aSlot, aStack);
		return false;
	}

	@Override
	public void updateModeration(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod)
			((IItemReactorRod)tTileEntityContainer.mTileEntity).updateModeration(aReactor, aSlot, aStack);
	}

	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodNeutronEmission(aReactor, aSlot, aStack);
		return 0;
	}
	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodNeutronReaction(aReactor, aSlot, aStack);
		return F;
	}
	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodNeutronReflection(aReactor, aSlot, aStack, aNeutrons, aModerated);
		return 0;
	}
	@Override
	public int getReactorRodNeutronMaximum(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodNeutronMaximum(aReactor, aSlot, aStack);
		return 0;
	}
	@Override
	public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodTextureSides(aReactor, aSlot, aStack, aActive);
		return BlockTextureCopied.get(Blocks.cobblestone);
	}
	@Override
	public ITexture getReactorRodTextureTop(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemReactorRod) return ((IItemReactorRod)tTileEntityContainer.mTileEntity).getReactorRodTextureTop(aReactor, aSlot, aStack, aActive);
		return BlockTextureCopied.get(Blocks.cobblestone);
	}
	
	
	@Override
	public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).isEnergyType(aEnergyType, aStack, aEmitting);
		return F;
	}
	@Override
	public Collection<TagData> getEnergyTypes(ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergyTypes(aStack);
		return Collections.emptyList();
	}
	@Override
	public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).doEnergyInjection(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoInject);
		return 0;
	}
	@Override
	public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).canEnergyInjection(aEnergyType, aStack, aSize);
		return F;
	}
	@Override
	public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).doEnergyExtraction(aEnergyType, aStack, aSize, aAmount, aInventory, aWorld, aX, aY, aZ, aDoExtract);
		return 0;
	}
	@Override
	public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).canEnergyExtraction(aEnergyType, aStack, aSize);
		return F;
	}
	@Override
	public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).useEnergy(aEnergyType, aStack, aEnergyAmount, aPlayer, aInventory, aWorld, aX, aY, aZ, aDoUse);
		return F;
	}
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).setEnergyStored(aEnergyType, aStack, aAmount);
		return aStack;
	}
	@Override
	public long getEnergyStored(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergyStored(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergyCapacity(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeInputMin(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeOutputMin(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeInputRecommended(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeOutputRecommended(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeInputMax(aEnergyType, aStack);
		return 0;
	}
	@Override
	public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack) {
		MultiTileEntityContainer tTileEntityContainer = mBlock.mMultiTileEntityRegistry.getNewTileEntityContainer(aStack);
		if (tTileEntityContainer != null && tTileEntityContainer.mTileEntity instanceof IItemEnergy) return ((IItemEnergy)tTileEntityContainer.mTileEntity).getEnergySizeOutputMax(aEnergyType, aStack);
		return 0;
	}
	
	@Override
	public double charge   (ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
		if (aCharge < V[aTier = UT.Code.bind4(aTier)]) return 0;
		return V[aTier] * doEnergyInjection (TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	@Override
	public double discharge(ItemStack aStack, double aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aBatteryAlike, boolean aSimulate) {
		if (aCharge < V[aTier = UT.Code.bind4(aTier)]) return 0;
		return V[aTier] * doEnergyExtraction(TD.Energy.EU, aStack, V[aTier], (long)(aCharge / V[aTier]), null, null, 0, 0, 0, !aSimulate);
	}
	
	@Override
	public float discharge(ItemStack aStack, float aEnergy, boolean aDoExtract) {
		if (aEnergy <= 0) return 0;
		long tMaxOut = getEnergySizeOutputMax(TD.Energy.EU, aStack);
		if (!canEnergyExtraction(TD.Energy.EU, aStack, tMaxOut)) return 0;
		long tAmount = UT.Code.bind(1, tMaxOut, (long)(aEnergy / EnergyConfigHandler.IC2_RATIO));
		return useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, F) && useEnergy(TD.Energy.EU, aStack, tAmount, null, null, null, 0, 0, 0, T) ? tAmount * EnergyConfigHandler.IC2_RATIO : 0;
	}
	
	@Optional.Method(modid = ModIDs.IC2 ) @Override public IElectricItemManager getManager(ItemStack aStack) {return this;} // We are our own Manager
	@Optional.Method(modid = ModIDs.BOTA) @Override public Block getBlockToPlaceByFlower(ItemStack aStack, SubTileEntity aFlower, int aX, int aY, int aZ) {return null;}
	@Optional.Method(modid = ModIDs.BOTA) @Override public void onBlockPlacedByFlower(ItemStack aStack, SubTileEntity aFlower, int aX, int aY, int aZ) {/**/}
	
	@Override public boolean func_150936_a(World aWorld, int aX, int aY, int aZ, int aSide, EntityPlayer aPlayer, ItemStack aStack) {return T;}
	@Override public String getToolTip(ItemStack aStack) {return null;} // This has its own ToolTip Handler, no need to let the IC2 Handler screw us up at this Point
	@Override public void chargeFromArmor(ItemStack aStack, EntityLivingBase aPlayer) {/**/}
	@Override public float getElectricityStored(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack) * EnergyConfigHandler.IC2_RATIO;}
	@Override public float getMaxElectricityStored(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack) * EnergyConfigHandler.IC2_RATIO;}
	@Override public void setElectricity(ItemStack aStack, float joules) {/**/}
	@Override public float recharge(ItemStack aStack, float aEnergy, boolean aDoInject) {return 0;}
	@Override public float getTransfer(ItemStack aStack) {return 0;}
	@Override public int getTierGC(ItemStack aStack) {return 1;}
	@Override public double getCharge(ItemStack aStack) {return getEnergyStored(TD.Energy.EU, aStack);}
	@Override public boolean canUse(ItemStack aStack, double aAmount) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, null, null, null, 0, 0, 0, F);}
	@Override public boolean use(ItemStack aStack, double aAmount, EntityLivingBase aPlayer) {return useEnergy(TD.Energy.EU, aStack, (long)aAmount, aPlayer, null, null, 0, 0, 0, T);}
	@Override public Item getChargedItem(ItemStack itemStack) {return this;}
	@Override public Item getEmptyItem(ItemStack itemStack) {return this;}
	@Override public boolean canProvideEnergy(ItemStack aStack) {return T;}
	@Override public double getMaxCharge(ItemStack aStack) {return getEnergyCapacity(TD.Energy.EU, aStack);}
	@Override public double getTransferLimit(ItemStack aStack) {return getEnergySizeInputRecommended(TD.Energy.EU, aStack);}
	@Override public int getTier(ItemStack aStack) {return UT.Code.tierMax(getEnergySizeInputMax(TD.Energy.EU, aStack));}
	@Override public final String getUnlocalizedName() {return mBlock.mMultiTileEntityRegistry.mNameInternal;}
	@Override public final String getUnlocalizedName(ItemStack aStack) {return mBlock.mMultiTileEntityRegistry.mNameInternal+"."+getDamage(aStack);}
	@Override public final boolean hasContainerItem(ItemStack aStack) {return getContainerItem(aStack) != null;}
	@Override public ItemStack getContainerItem(ItemStack aStack) {return null;}
	@Override public boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {return F;}
	@Override public int getSpriteNumber() {return 0;}
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aRegister) {/**/}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	@Override public int getItemEnchantability() {return 0;}
	@Override public final boolean getShareTag() {return T;} // just to be sure.
}
