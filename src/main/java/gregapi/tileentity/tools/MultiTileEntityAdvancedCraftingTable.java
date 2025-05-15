/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity.tools;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.gui.*;
import gregapi.item.IItemGTContainerTool;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityConnectedInventory;
import gregapi.tileentity.ITileEntityConnectedTank;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAdvancedCraftingTable extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityFunnelAccessible {
	public boolean mSyncGUI = F, mFlushMode = F, mBlocked16 = F, mBlocked36 = F, mFilter16 = F, mFilter36 = F, mDoSound = T, mUpdatedGrid = T;
	public String mGUITexture = RES_PATH_GUI + "machines/AdvancedCraftingTable.png";
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE+".16.blocked")) mBlocked16 = aNBT.getBoolean(NBT_MODE+".16.blocked");
		if (aNBT.hasKey(NBT_MODE+".36.blocked")) mBlocked36 = aNBT.getBoolean(NBT_MODE+".36.blocked");
		if (aNBT.hasKey(NBT_MODE+".16.filter")) mFilter16 = aNBT.getBoolean(NBT_MODE+".16.filter");
		if (aNBT.hasKey(NBT_MODE+".36.filter")) mFilter36 = aNBT.getBoolean(NBT_MODE+".36.filter");
		if (aNBT.hasKey(NBT_FLUSH)) mFlushMode = aNBT.getBoolean(NBT_FLUSH);
		if (CODE_CLIENT && aNBT.hasKey(NBT_GUI)) {
			mGUITexture = aNBT.getString(NBT_GUI);
			if (!mGUITexture.endsWith(".png")) mGUITexture += ".png";
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".16.blocked", mBlocked16);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".36.blocked", mBlocked36);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".16.filter", mFilter16);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".36.filter", mFilter36);
		UT.NBT.setBoolean(aNBT, NBT_FLUSH, mFlushMode);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mUpdatedGrid = T; // Just in case someone like Greg used NEI or something to delete all Slots or so.
		if (aPlayer != null) {
			aPlayer.triggerAchievement(AchievementList.openInventory);
			aPlayer.triggerAchievement(AchievementList.mineWood);
			aPlayer.triggerAchievement(AchievementList.buildWorkBench);
		}
		if (SIDES_TOP[aSide]) return !isServerSide() || openGUI(aPlayer, 0);
		if (ALONG_AXIS[aSide][mFacing]) return !isServerSide() || openGUI(aPlayer, 1);
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mUpdatedGrid = T; // Just in case someone like Greg used NEI or something to delete all Slots or so.
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_monkeywrench)) {
			if (SIDES_TOP[aSide]) {
				mBlocked16 = !mBlocked16;
				if (aChatReturn != null) aChatReturn.add("4x4-Automation-Access: " + (mBlocked16?"OFF":"ON"));
			} else {
				mBlocked36 = !mBlocked36;
				if (aChatReturn != null) aChatReturn.add("9x4-Automation-Access: " + (mBlocked36?"OFF":"ON"));
			}
			return 10000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			if (SIDES_TOP[aSide]) {
				mFilter16 = !mFilter16;
				if (aChatReturn != null) aChatReturn.add("4x4-Diversity-Filter: " + (mFilter16?"ON":"OFF"));
			} else {
				mFilter36 = !mFilter36;
				if (aChatReturn != null) aChatReturn.add("9x4-Diversity-Filter: " + (mFilter36?"ON":"OFF"));
			}
			return 10000;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		mDoSound = T;
		
		if (aIsServerSide) {
			if (mUpdatedGrid) {
				getCraftingOutput(F);
				mUpdatedGrid = F;
			}
			if (mInventoryChanged) {
				for (byte tSide : ALL_SIDES_VALID) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
						((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
					}
				}
			}
			refill();
			if (mFlushMode) {
				mFlushMode = F;
				for (int i : SLOTS_CRAFTING) if (slotHas(i) && !slotNull(i)) {
					mFlushMode = T;
					break;
				}
			}
		}
	}
	
	public void sortIntoTheInputSlots() {
		for (int i : SLOTS_CRAFTING) if (slotHas(i)) {
			slotNull(i);
			if (slotHas(i)) for (int j : SLOTS_STORAGE) if (ST.equal(slot(i), slot(j))) ST.move(this, i, j);
			if (slotHas(i)) for (int j : SLOTS_STORAGE) if (!slotHas(j)) ST.move(this, i, j);
		}
		mUpdatedGrid = T;
	}
	
	protected void refill() {
		//
	}
	
	public boolean setBluePrint(ItemStack aStack) {
		if (aStack == null) aStack = slot(30);
		if (!IL.Paper_Blueprint_Empty.equal(aStack, F, T)) return F;
		UT.NBT.setBlueprintCrafting(aStack,
		  slotHas(21) ? slot(21).getItem() instanceof IItemGTContainerTool ? ST.make(slot(21), (NBTTagCompound)null) : slot(21) : null
		, slotHas(22) ? slot(22).getItem() instanceof IItemGTContainerTool ? ST.make(slot(22), (NBTTagCompound)null) : slot(22) : null
		, slotHas(23) ? slot(23).getItem() instanceof IItemGTContainerTool ? ST.make(slot(23), (NBTTagCompound)null) : slot(23) : null
		, slotHas(24) ? slot(24).getItem() instanceof IItemGTContainerTool ? ST.make(slot(24), (NBTTagCompound)null) : slot(24) : null
		, slotHas(25) ? slot(25).getItem() instanceof IItemGTContainerTool ? ST.make(slot(25), (NBTTagCompound)null) : slot(25) : null
		, slotHas(26) ? slot(26).getItem() instanceof IItemGTContainerTool ? ST.make(slot(26), (NBTTagCompound)null) : slot(26) : null
		, slotHas(27) ? slot(27).getItem() instanceof IItemGTContainerTool ? ST.make(slot(27), (NBTTagCompound)null) : slot(27) : null
		, slotHas(28) ? slot(28).getItem() instanceof IItemGTContainerTool ? ST.make(slot(28), (NBTTagCompound)null) : slot(28) : null
		, slotHas(29) ? slot(29).getItem() instanceof IItemGTContainerTool ? ST.make(slot(29), (NBTTagCompound)null) : slot(29) : null
		);
		if (slotHas(31)) aStack.setStackDisplayName(slot(31).getDisplayName());
		ST.set(aStack, IL.Paper_Blueprint_Used.get(1), F, F);
		return T;
	}
	
	public ItemStack getCraftingOutput(boolean aAllowCache) {
		if (IL.Paper_Blueprint_Used.equal(slot(30), F, T)) {
			ItemStack[] tRecipe = UT.NBT.getBlueprintCrafting(slot(30));
			if (tRecipe != ZL_IS) for (int i = 0; i < tRecipe.length; i++) if (!slotHas(i+21)) slot(i+21, ST.amount(0, tRecipe[i]));
		} else if (IL.Circuit_Selector.equal(slot(30), T, T)) {
			int tDestination = SLOTS_CRAFTING[3];
			for (int i : SLOTS_CRAFTING) {
				slotNull(i);
				if (i == SLOTS_CRAFTING[3]) continue;
				if (slotHas(i)) ST.move(this, i, tDestination);
				if (slotHas(i)) for (int j : SLOTS_STORAGE) if (ST.equal(slot(i), slot(j))) ST.move(this, i, j);
				if (slotHas(i)) for (int j : SLOTS_STORAGE) if (!slotHas(j)) ST.move(this, i, j);
			}
			switch(ST.meta(slot(30))) {
			case  9:
				// Literally no other choice than filling all 9 Slots.
				if (!slotHas(SLOTS_CRAFTING[8])) slot(SLOTS_CRAFTING[8], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[7])) slot(SLOTS_CRAFTING[7], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[6])) slot(SLOTS_CRAFTING[6], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[2])) slot(SLOTS_CRAFTING[2], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[0])) slot(SLOTS_CRAFTING[0], ST.amount(0, slot(tDestination)));
				break;
			case  8:
				// Because of the original Furnace and Chest Recipes.
				if (!slotHas(SLOTS_CRAFTING[8])) slot(SLOTS_CRAFTING[8], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[7])) slot(SLOTS_CRAFTING[7], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[6])) slot(SLOTS_CRAFTING[6], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[2])) slot(SLOTS_CRAFTING[2], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[0])) slot(SLOTS_CRAFTING[0], ST.amount(0, slot(tDestination)));
				break;
			case  7:
				// Honestly, no reason for this specific choice, apart from avoiding the Armor Recipes.
				if (!slotHas(SLOTS_CRAFTING[7])) slot(SLOTS_CRAFTING[7], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[2])) slot(SLOTS_CRAFTING[2], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[0])) slot(SLOTS_CRAFTING[0], ST.amount(0, slot(tDestination)));
				break;
			case  6:
				// 3x2 because of the original Wall and Trapdoor Recipes.
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[2])) slot(SLOTS_CRAFTING[2], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[0])) slot(SLOTS_CRAFTING[0], ST.amount(0, slot(tDestination)));
				break;
			case  5:
				// Star Shape because X Shape isn't used often.
				if (!slotHas(SLOTS_CRAFTING[7])) slot(SLOTS_CRAFTING[7], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				break;
			case  4:
				// 2x2 because a lot of Recipes do this.
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[1])) slot(SLOTS_CRAFTING[1], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[0])) slot(SLOTS_CRAFTING[0], ST.amount(0, slot(tDestination)));
				break;
			case  3:
				// Horizontal because Slab Recipe
				if (!slotHas(SLOTS_CRAFTING[5])) slot(SLOTS_CRAFTING[5], ST.amount(0, slot(tDestination)));
				if (!slotHas(SLOTS_CRAFTING[4])) slot(SLOTS_CRAFTING[4], ST.amount(0, slot(tDestination)));
				break;
			default:
				// Vertical because Stick Recipe
				if (!slotHas(SLOTS_CRAFTING[6])) slot(SLOTS_CRAFTING[6], ST.amount(0, slot(tDestination)));
				break;
			}
		}
		ItemStack rStack = CR.getany(worldObj, aAllowCache, slot(21), slot(22), slot(23), slot(24), slot(25), slot(26), slot(27), slot(28), slot(29));
		if (OM.materialcontains(rStack, TD.Properties.EXPLODES_IN_NONVANILLA_CRAFTING_GRID)) explode(4);
		return slot(31, rStack);
	}
	
	public boolean canDoCraftingOutput() {
		if (!slotHas(31)) return F;
		for (ItemStack tStack : recipeContent()) if (tStack.stackSize > getAmountOf(tStack)) return F;
		return T;
	}
	
	protected int getAmountOf(ItemStack aStack) {
		int tAmount = 0;
		for (int i : SLOTS_CRAFTING) if (ST.equalTools(aStack, slot(i), F)) {
			tAmount+=slot(i).stackSize;
			if (tAmount >= SLOTS_CRAFTING.length) return tAmount;
		}
		for (int i : SLOTS_CONSUMPTION) if (ST.equalTools(aStack, slot(i), F)) {
			tAmount+=slot(i).stackSize;
			if (tAmount >= SLOTS_CRAFTING.length) return tAmount;
		}
		for (byte tSide : ALL_SIDES_VALID) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityConnectedInventory) {
				tAmount += ((ITileEntityConnectedInventory)tDelegator.mTileEntity).getAmountOfItemsInConnectedInventory(tDelegator.mSideOfTileEntity, aStack, SLOTS_CRAFTING.length-tAmount);
				if (tAmount >= SLOTS_CRAFTING.length) return tAmount;
			}
		}
		return tAmount;
	}
	
	protected ArrayList<ItemStack> recipeContent() {
		ArrayList<ItemStack> tList = new ArrayList<>();
		for (int i : SLOTS_CRAFTING) {
			if (slotHas(i)) {
				boolean temp = F;
				for (int j = 0; j < tList.size(); j++) {
					if (ST.equalTools(slot(i), tList.get(j), F)) {
						tList.get(j).stackSize++;
						temp = T;
						break;
					}
				}
				if (!temp) tList.add(ST.amount(1, slot(i)));
			}
		}
		return tList;
	}
	
	public ItemStack consumeMaterials(EntityPlayer aPlayer, ItemStack aHoldStack, boolean aSubsequentClick) {
		if (!slotHas(31)) return aHoldStack;
		
		if (aHoldStack != null) {
			if (!ST.equal(aHoldStack, slot(31))) {
				if (!aSubsequentClick && mDoSound) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
					mDoSound = F;
				}
				return aHoldStack;
			}
			if (aHoldStack.stackSize + slot(31).stackSize > aHoldStack.getMaxStackSize()) return aHoldStack;
			for (int i : SLOTS_CRAFTING) if (OM.is("gt:autocrafterinfinite", slot(i))) {
				if (!aSubsequentClick && mDoSound) {
					UT.Sounds.play(SFX.MC_HMM, 50, 1.0F, 1.0F, getCoords());
					mDoSound = F;
				}
				return aHoldStack;
			}
		}
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = getCoords();
		
		try {FMLCommonHandler.instance().firePlayerCraftingEvent(aPlayer, ST.copy(slot(31)), new InventoryCrafting(null, 3, 3));} catch(Throwable e) {e.printStackTrace(ERR);}
		
		ItemStack[] tRecipeStacks = {ST.amount(1, slot(21)), ST.amount(1, slot(22)), ST.amount(1, slot(23)), ST.amount(1, slot(24)), ST.amount(1, slot(25)), ST.amount(1, slot(26)), ST.amount(1, slot(27)), ST.amount(1, slot(28)), ST.amount(1, slot(29))};
		
		for (int j = 0; j < tRecipeStacks.length; j++) if (ST.valid(tRecipeStacks[j])) {
			boolean tNeeds = T;
			TOOL_SOUNDS = !aSubsequentClick && mDoSound && TOOL_SOUNDS_SETTING;
			ItemStack tContainer = ST.container(tRecipeStacks[j], F);
			TOOL_SOUNDS = F;
			// Contains itself, so it's an infinite use Container Item anyways.
			if (ST.equal(tRecipeStacks[j], tContainer, F)) continue;
			
			if (isServerSide()) for (byte tSide : ALL_SIDES_VALID) {
				DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
				if (tDelegator.mTileEntity instanceof ITileEntityConnectedInventory) {
					if (((ITileEntityConnectedInventory)tDelegator.mTileEntity).removeStackFromConnectedInventory(tDelegator.mSideOfTileEntity, ST.amount(1, tRecipeStacks[j]), T) >= 1) {
						tNeeds = F;
						// Try to put empty Containers into adjacent connectable Inventories.
						if (tContainer != null) for (byte tSide2 : ALL_SIDES_VALID) {
							tDelegator = getAdjacentTileEntity(tSide2);
							if (tDelegator.mTileEntity instanceof ITileEntityConnectedInventory) {
								int tRemoved = ((ITileEntityConnectedInventory)tDelegator.mTileEntity).addStackToConnectedInventory(tDelegator.mSideOfTileEntity, ST.copy(tContainer), T);
								tContainer.stackSize -= tRemoved;
								if (tContainer.stackSize < 1) {
									tContainer = null;
									break;
								}
							}
						}
						// Put empty Container Items away.
						if (tContainer != null) {
							for (int i : SLOTS_INPUT) {
								if (!slotHas(i)) {
									slot(i, tContainer);
									break;
								}
								if (ST.equal(tContainer, slot(i))) {
									if (tContainer.stackSize + slot(i).stackSize <= slot(i).getMaxStackSize()) {
										slot(i).stackSize += tContainer.stackSize;
										break;
									}
								}
							}
						}
						break;
					}
				}
			}
			
			// First take from the Slot that actually indicates the Item.
			if (tNeeds) if (ST.equalTools(tRecipeStacks[j], slot(SLOTS_CRAFTING[j]), F) && slot(SLOTS_CRAFTING[j]).stackSize > 1 && consumeSlot(SLOTS_CRAFTING[j])) continue;
			// Then take from the Grid but always leave one in each Slot.
			if (tNeeds) for (int i : SLOTS_CRAFTING   ) if (ST.equalTools(tRecipeStacks[j], slot(i), F) && slot(i).stackSize > 1 && consumeSlot(i)) {tNeeds = F; break;}
			// Then draw from the ready Slots, that way you do not have to refill them all the time, after crafting things you already have the Stuff ready for.
			if (tNeeds) for (int i : SLOTS_CONSUMPTION) if (ST.equalTools(tRecipeStacks[j], slot(i), F) && slot(i).stackSize > 0 && consumeSlot(i)) {tNeeds = F; break;}
			// And then pull from the Crafting Slots if needed, and mark the Grid as changed.
			if (tNeeds) for (int i : SLOTS_CRAFTING   ) if (ST.equalTools(tRecipeStacks[j], slot(i), F) && slot(i).stackSize > 0 && consumeSlot(i)) {tNeeds = F; mUpdatedGrid = T; break;}
		}
		
		if (aHoldStack == null) aHoldStack = ST.copy(slot(31)); else aHoldStack.stackSize += slot(31).stackSize;
		
		aHoldStack.onCrafting(worldObj, aPlayer, slot(31).stackSize);
		
		ST.check(aPlayer, aHoldStack);
		
		refill();
		
		MultiItemTool.LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
		
		TOOL_SOUNDS = TOOL_SOUNDS_SETTING;
		
		updateInventory();
		
		mSyncGUI = T;
		
		return aHoldStack;
	}
	
	public boolean consumeSlot(int aSlot) {
		ItemStack tContainer = ST.container(slot(aSlot), F);
		if (tContainer != null && tContainer.stackSize < 1) tContainer = null;
		
		if (isServerSide() && tContainer != null) {
			// Try to draw from adjacent connectable Tanks to refill Container Items.
			FluidStack tFluidContained = FL.getFluid(slot(aSlot), T);
			if (tFluidContained != null && ST.equal(slot(aSlot), FL.fill(tFluidContained, tContainer, F, T, F, T), F)) {
				for (byte tSide : ALL_SIDES_VALID) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityConnectedTank && ((ITileEntityConnectedTank)tDelegator.mTileEntity).removeFluidFromConnectedTank(tDelegator.mSideOfTileEntity, tFluidContained, T) >= tFluidContained.amount) {
						// Yay, we wont need to change anything inside the Slot!
						return T;
					}
				}
			}
			// Try to put empty Containers into adjacent connectable Inventories.
			for (byte tSide : ALL_SIDES_VALID) {
				DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
				if (tDelegator.mTileEntity instanceof ITileEntityConnectedInventory) {
					tContainer.stackSize -= ((ITileEntityConnectedInventory)tDelegator.mTileEntity).addStackToConnectedInventory(tDelegator.mSideOfTileEntity, ST.copy(tContainer), T);
					if (tContainer.stackSize < 1) {
						tContainer = null;
						break;
					}
				}
			}
		}
		
		// Consume the Item.
		if (tContainer == null || (tContainer.isItemStackDamageable() && tContainer.getItemDamage() >= tContainer.getMaxDamage())) {
			decrStackSize(aSlot, 1);
			return T;
		}
		if (slot(aSlot).stackSize == 1) {
			slot(aSlot, tContainer);
			return T;
		} 
		decrStackSize(aSlot, 1);
		for (int i : SLOTS_INPUT) {
			if (!slotHas(i)) {
				slot(i, tContainer);
				return T;
			}
			if (ST.equal(tContainer, slot(i))) {
				if (tContainer.stackSize + slot(i).stackSize <= slot(i).getMaxStackSize()) {
					slot(i).stackSize += tContainer.stackSize;
					return T;
				}
			}
		}
		return T;
	}
	
	// Inventory Stuff
	public static final int[] SLOTS              = new int[] {33};
	public static final int[] SLOTS_FLUSHING     = new int[] {33, 21, 22, 23, 24, 25, 26, 27, 28, 29};
	public static final int[] SLOTS_16           = new int[] {33,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15};
	public static final int[] SLOTS_16_FLUSHING  = new int[] {33, 21, 22, 23, 24, 25, 26, 27, 28, 29,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15};
	public static final int[] SLOTS_36           = new int[] {33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 63, 65, 66, 67, 68, 69, 70};
	public static final int[] SLOTS_36_FLUSHING  = new int[] {33, 21, 22, 23, 24, 25, 26, 27, 28, 29, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
	public static final int[] SLOTS_ALL          = new int[] {33,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 64, 63, 65, 66, 67, 68, 69, 70};
	public static final int[] SLOTS_ALL_FLUSHING = new int[] {33, 21, 22, 23, 24, 25, 26, 27, 28, 29,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
	
	public static final int[] SLOTS_CONSUMPTION  = new int[] {70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10,  9,  8,  7,  6,  5,  4,  3,  2,  1,  0};
	public static final int[] SLOTS_INPUT        = new int[] { 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
	public static final int[] SLOTS_STORAGE      = new int[] { 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70};
	public static final int[] SLOTS_CRAFTING     = new int[] {21, 22, 23, 24, 25, 26, 27, 28, 29};
	public static final int[] SLOTS_TOOLS        = new int[] {16, 17, 18, 19, 20};
	
	@Override public void setInventorySlotContents(int aSlot, ItemStack aStack)     {if (aSlot > 20 && aSlot <= 30 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContents(aSlot, aStack);}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack)  {if (aSlot > 20 && aSlot <= 30 && !ST.equal(aStack, slot(aSlot), F)) mUpdatedGrid = T; super.setInventorySlotContentsGUI(aSlot, aStack);}
	@Override public ItemStack decrStackSize(int aSlot, int aDecrement)             {if (aSlot > 20 && aSlot <= 30 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSize(aSlot, aDecrement);}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement)          {if (aSlot > 20 && aSlot <= 30 && aDecrement > 0) mUpdatedGrid = T; return super.decrStackSizeGUI(aSlot, aDecrement);}
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return mBlocked16 ? mBlocked36 ? mFlushMode?SLOTS_FLUSHING:SLOTS : mFlushMode?SLOTS_36_FLUSHING:SLOTS_36 : mBlocked36 ? mFlushMode?SLOTS_16_FLUSHING:SLOTS_16 : mFlushMode?SLOTS_ALL_FLUSHING:SLOTS_ALL;}
	@Override public boolean canDrop(int aInventorySlot) {return aInventorySlot < 31 || aInventorySlot > 32;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[71];}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return aSlot == 30 ? 1 : 64;}
	@Override public boolean allowCovers(byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide] && !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isItemValidForSlotGUI(int aSlot, ItemStack aStack) {return aSlot != 30 || ST.invalid(aStack) || IL.Paper_Blueprint_Empty.equal(aStack, F, T) || IL.Paper_Blueprint_Used.equal(aStack, F, T) || (IL.Circuit_Selector.equal(aStack, T, T) && UT.Code.inside(2, 9, ST.meta(aStack)));}
	
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (aSlot < 16) {
			if (mFilter16) for (int i =  0; i < 16; i++) if (ST.equalTools(aStack, slot(i), F)) return aSlot == i;
			return T;
		}
		if (aSlot >= 35 && aSlot < 71) {
			if (mFilter36) for (int i = 35; i < 71; i++) if (ST.equalTools(aStack, slot(i), F)) return aSlot == i;
			return T;
		}
		return F;
	}
	
	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		return aSlot == 33 || (mFlushMode && aSlot > 20 && aSlot < 30);
	}
	
	@Override
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		return aFluid != null && fill(aDirection, FL.make(aFluid, Integer.MAX_VALUE), F) > 0;
	}
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null || aFluid.amount <= 0) return 0;
		
		for (int i : SLOTS_TOOLS) {
			ItemStack tOutput = FL.fill(aFluid, slot(i), F, T, F, T);
			if (tOutput != null) {
				FluidStack tFluid = FL.getFluid(tOutput, T);
				if (tFluid != null) {
					if (slot(i).stackSize == 1) {
						if (aDoFill) {
							slot(i, tOutput);
							updateInventory();
							mSyncGUI = T;
						}
						return tFluid.amount * tOutput.stackSize;
					}
					for (int j : SLOTS_TOOLS) {
						if (!slotHas(j) || (ST.equal(tOutput, slot(j)) && slot(j).stackSize + tOutput.stackSize <= tOutput.getMaxStackSize())) {
							if (aDoFill) {
								decrStackSize(i, 1);
								if (slotHas(j)) slot(j).stackSize++; else slot(j, tOutput);
								updateInventory();
								mSyncGUI = T;
							}
							return tFluid.amount * tOutput.stackSize;
						}
					}
				}
			}
		}
		return 0;
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return fill(FORGE_DIR[aSide], aFluid, aDoFill);
	}
	
	@Override public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean aDoDrain) {return NF;}
	@Override public FluidStack drain(ForgeDirection aDirection, int aAmountToDrain, boolean aDoDrain) {return NF;}
	@Override public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {return F;}
	@Override public FluidTankInfo[] getTankInfo(ForgeDirection aDirection) {return L1_FLUIDTANKINFO_DUMMY;}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return aGUIID == 1 ? new ContainerClientDefault(   new ContainerCommonDefault(aPlayer.inventory, this, aGUIID, 35, 36)) : new MultiTileEntityGUIClientAdvancedCraftingTable(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return aGUIID == 1 ?                               new ContainerCommonDefault(aPlayer.inventory, this, aGUIID, 35, 36)  : new MultiTileEntityGUICommonAdvancedCraftingTable(aPlayer.inventory, this, aGUIID);}
	
	@Override public boolean needsToSyncEverything() {if (mSyncGUI) {mSyncGUI = F; return T;} return F;}
	
	@Override
	public boolean interceptClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift) {
		if (aGUIID != 0) return F;
		slotNull(aInvSlot);
		if (aInvSlot == 30 && !aRightclick && aShiftclick && setBluePrint(null)) return T;
		return aInvSlot == 31 || aInvSlot == 32;
	}
	
	@Override
	public ItemStack slotClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift) {
		if (aInvSlot == 31) {
			ItemStack tCraftedStack = getCraftingOutput(T), tStack;
			if (tCraftedStack != null) {
				if (aShiftclick) {
					if (aRightclick) {
						// SHIFT RIGHTCLICK
						for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
							if (aPlayer.inventory.mainInventory[i] == null || (ST.equal(tCraftedStack, aPlayer.inventory.mainInventory[i]) && tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize())) {
								for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && canDoCraftingOutput(); j++) {
									if (!ST.equal(tStack = getCraftingOutput(T), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
										return aPlayer.inventory.getItemStack();
									}
									aPlayer.inventory.mainInventory[i] = (consumeMaterials(aPlayer, aPlayer.inventory.mainInventory[i], i != 0 || j != 0));
								}
							}
						}
						return aPlayer.inventory.getItemStack();
					}
					// SHIFT LEFTCLICK
					for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
						if (aPlayer.inventory.mainInventory[i] == null || (ST.equal(tCraftedStack, aPlayer.inventory.mainInventory[i]) && tCraftedStack.stackSize + aPlayer.inventory.mainInventory[i].stackSize <= aPlayer.inventory.mainInventory[i].getMaxStackSize())) {
							boolean temp = F;
							for (int j = 0; j < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && canDoCraftingOutput(); j++) {
								if (!ST.equal(tStack = getCraftingOutput(T), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
									return aPlayer.inventory.getItemStack();
								}
								aPlayer.inventory.mainInventory[i] = (consumeMaterials(aPlayer, aPlayer.inventory.mainInventory[i], i != 0 || j != 0));
								temp = T;
							}
							if (temp) return aPlayer.inventory.getItemStack();
						}
					}
					return aPlayer.inventory.getItemStack();
				}
				if (aRightclick) {
					// RIGHTCLICK
					for (int i = 0; i < tCraftedStack.getMaxStackSize() / tCraftedStack.stackSize && canDoCraftingOutput(); i++) {
						if (!ST.equal(tStack = getCraftingOutput(T), tCraftedStack) || tStack.stackSize != tCraftedStack.stackSize) {
							return aPlayer.inventory.getItemStack();
						}
						aPlayer.inventory.setItemStack(consumeMaterials(aPlayer, aPlayer.inventory.getItemStack(), i != 0));
					}
					return aPlayer.inventory.getItemStack();
				}
				// LEFTCLICK
				if (canDoCraftingOutput()) aPlayer.inventory.setItemStack(consumeMaterials(aPlayer, aPlayer.inventory.getItemStack(), F));
				return aPlayer.inventory.getItemStack();
			}
			return null;
		}
		if (aInvSlot == 32) {
			if (aSlotIndex == 34) mFlushMode = T;
			if (aSlotIndex == 35) sortIntoTheInputSlots();
			return null;
		}
		return null;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide<2?aSide:aSide==mFacing?2:aSide==OPOS[mFacing]?3:4;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/craftingtables/advanced/overlay/side"),
	};
	
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.crafting.advanced";}
	
	public class MultiTileEntityGUICommonAdvancedCraftingTable extends ContainerCommon {
		public MultiTileEntityGUICommonAdvancedCraftingTable(InventoryPlayer aInventoryPlayer, MultiTileEntityAdvancedCraftingTable aTileEntity, int aGUIID) {
			super(aInventoryPlayer, aTileEntity, aGUIID);
		}
		
		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity, 30, 135, 28).setTooltip(LH.ADVCRAFTING_INSERT_BLUEPRINT, LH.Chat.WHITE));
			
			addSlotToContainer(new Slot_Normal(mTileEntity,  0,   7,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity,  1,  25,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity,  2,  43,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity,  3,  61,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity,  4,   7, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity,  5,  25, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity,  6,  43, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity,  7,  61, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity,  8,   7, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity,  9,  25, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, 10,  43, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, 11,  61, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, 12,   7, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, 13,  25, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, 14,  43, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, 15,  61, 62));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 16,  80,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, 17,  98,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, 18, 116,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, 19, 134,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, 20, 152,  8));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 21,  80, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity, 22,  98, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity, 23, 116, 28));
			addSlotToContainer(new Slot_Normal(mTileEntity, 24,  80, 46));
			addSlotToContainer(new Slot_Normal(mTileEntity, 25,  98, 46));
			addSlotToContainer(new Slot_Normal(mTileEntity, 26, 116, 46));
			addSlotToContainer(new Slot_Normal(mTileEntity, 27,  80, 64));
			addSlotToContainer(new Slot_Normal(mTileEntity, 28,  98, 64));
			addSlotToContainer(new Slot_Normal(mTileEntity, 29, 116, 64));
			
			addSlotToContainer(new Slot_Normal(mTileEntity, 33, 153, 28).setTooltip(LH.ADVCRAFTING_DROP_SLOT, LH.Chat.WHITE));
			addSlotToContainer(new Slot_Normal(mTileEntity, 34, 153, 64).setTooltip(LH.ADVCRAFTING_NEUTRAL_SLOT, LH.Chat.WHITE));
			
			addSlotToContainer(new Slot_Holo(mTileEntity, 31, 135, 64, F, F, 1));
			addSlotToContainer(new Slot_Holo(mTileEntity, 32, 153, 46, F, F, 1).setTooltip(LH.ADVCRAFTING_AUTOMATION_ACCESS, LH.Chat.WHITE));
			addSlotToContainer(new Slot_Holo(mTileEntity, 32, 135, 46, F, F, 1).setTooltip(LH.ADVCRAFTING_PUT_TO_STORAGE, LH.Chat.WHITE));
			
			return super.addSlots(aInventoryPlayer);
		}
		
		@Override
		public int getSlotCount() {
			return 33;
		}
		
		@Override
		public int getShiftClickSlotCount() {
			return 22;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientAdvancedCraftingTable extends ContainerClient {
		public MultiTileEntityGUIClientAdvancedCraftingTable(InventoryPlayer aInventoryPlayer, MultiTileEntityAdvancedCraftingTable aTileEntity, int aGUIID) {
			super(new MultiTileEntityGUICommonAdvancedCraftingTable(aInventoryPlayer, aTileEntity, aGUIID), aTileEntity.mGUITexture);
		}
	}
}
