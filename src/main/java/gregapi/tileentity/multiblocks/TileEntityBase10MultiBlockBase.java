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

package gregapi.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.GT_API;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 * 
 * Some Defaults for MultiBlock Machines.
 */
public abstract class TileEntityBase10MultiBlockBase extends TileEntityBase09FacingSingle implements ITileEntityMultiBlockController {
	public boolean mStructureChanged = F, mStructureOkay = F;
	
	public IIconContainer[] mTextures = L6_IICONCONTAINER, mTexturesFront = L6_IICONCONTAINER;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE+".str")) mStructureOkay = aNBT.getBoolean(NBT_STATE+".str");
		
		if (CODE_CLIENT) {
			if (GT_API.sBlockIcons == null && aNBT.hasKey(NBT_TEXTURE)) {
				String tTextureName = aNBT.getString(NBT_TEXTURE);
				mTextures = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored/top"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored/side"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay/top"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay/side")
				};
				mTexturesFront = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored_front/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored_front/top"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/colored_front/side"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay_front/bottom"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay_front/top"),
				new Textures.BlockIcons.CustomIcon("machines/multiblockmains/"+tTextureName+"/overlay_front/side")
				};
			} else {
				TileEntity tCanonicalTileEntity = MultiTileEntityRegistry.getCanonicalTileEntity(getMultiTileEntityRegistryID(), getMultiTileEntityID());
				if (tCanonicalTileEntity instanceof TileEntityBase10MultiBlockBase) {
					mTextures = ((TileEntityBase10MultiBlockBase)tCanonicalTileEntity).mTextures;
					mTexturesFront = ((TileEntityBase10MultiBlockBase)tCanonicalTileEntity).mTexturesFront;
				}
			}
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STATE+".str", mStructureOkay);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		checkStructure(T);
	}
	
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		super.onTickFirst2(aIsServerSide);
		if (aIsServerSide) checkStructure(T);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mTimer % 600 == 5) {
				if (!checkStructure(F)) checkStructure(T);
				doDefaultStructuralChecks();
			}
		}
	}
	
	@Override
	public long onToolClickMultiBlock(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ, ChunkCoordinates aFrom) {
		return onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) onMagnifyingGlass(aChatReturn);
			return 1;
		}
		return 0;
	}
	
	public void onMagnifyingGlass(List<String> aChatReturn) {
		if (checkStructure(F)) {
			onMagnifyingGlass2(aChatReturn);
		} else {
			if (checkStructure(T)) {
				aChatReturn.add("Structure did form just now!");
			} else {
				aChatReturn.add("Structure did not form!");
			}
		}
	}
	
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add("Structure is formed already!");
	}
	
	@Override
	public boolean checkStructure(boolean aForceReset) {
		if (isClientSide()) return mStructureOkay;
		if ((mStructureChanged || aForceReset) && mStructureOkay != checkStructure2()) {
			mStructureOkay = !mStructureOkay;
			updateClientData();
		}
		mStructureChanged = F;
		return mStructureOkay;
	}
	
	@Override public void onFacingChange(byte aPreviousFacing) {onStructureChange();}
	@Override public final byte getDirectionData() {return (byte)((mFacing & 7) | (mStructureOkay ? 8 : 0));}
	@Override public final void setDirectionData(byte aData) {mFacing = (byte)(aData & 7); mStructureOkay = ((aData & 8) != 0);}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3])) : null;
	}
	
	@Override public boolean doDefaultStructuralChecks() {return T;}
	@Override public boolean canDrop(int aSlot) {return T;}
	
	@Override public void onStructureChange() {mStructureChanged = T;}
	
	public abstract boolean checkStructure2();
	
	@Override protected IFluidTank getFluidTankFillable     (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return getFluidTankFillable2(aSide, aFluidToFill);}
	@Override protected IFluidTank getFluidTankDrainable    (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return getFluidTankDrainable2(aSide, aFluidToDrain);}
	@Override protected IFluidTank[] getFluidTanks          (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getFluidTanks2(aSide);}
	
	public int[] getAccessibleSlotsFromSide                 (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getAccessibleSlotsFromSide2(aSide);}
	public boolean canInsertItem                            (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canInsertItem2(aSlot, aStack, aSide);}
	public boolean canExtractItem                           (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canExtractItem2(aSlot, aStack, aSide);}
	public int getSizeInventory                             (MultiTileEntityMultiBlockPart aPart) {return getSizeInventory();}
	public ItemStack getStackInSlot                         (MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlot(aSlot);}
	public ItemStack decrStackSize                          (MultiTileEntityMultiBlockPart aPart, int aSlot, int aDecrement) {return decrStackSize(aSlot, aDecrement);}
	public ItemStack getStackInSlotOnClosing                (MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlotOnClosing(aSlot);}
	public void setInventorySlotContents                    (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {setInventorySlotContents(aSlot, aStack);}
	public String getInventoryName                          (MultiTileEntityMultiBlockPart aPart) {return getInventoryName();}
	public boolean hasCustomInventoryName                   (MultiTileEntityMultiBlockPart aPart) {return hasCustomInventoryName();}
	public int getInventoryStackLimit                       (MultiTileEntityMultiBlockPart aPart) {return getInventoryStackLimit();}
	public void markDirty                                   (MultiTileEntityMultiBlockPart aPart) {markDirty();}
	public boolean isUseableByPlayer                        (MultiTileEntityMultiBlockPart aPart, EntityPlayer aPlayer) {return isUseableByPlayer(aPlayer);}
	public void openInventory                               (MultiTileEntityMultiBlockPart aPart) {openInventory();}
	public void closeInventory                              (MultiTileEntityMultiBlockPart aPart) {closeInventory();}
	public boolean isItemValidForSlot                       (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {return isItemValidForSlot(aSlot, aStack);}
}
