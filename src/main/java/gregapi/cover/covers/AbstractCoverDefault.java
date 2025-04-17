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

package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.cover.ICover;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

/**
 * @author Gregorius Techneticies
 * 
 * A Default implementation that is used for all regular Covers.
 */
public abstract class AbstractCoverDefault implements ICover {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return F;}
	@Override public boolean interceptCoverRemoval(byte aCoverSide, CoverData aData, Entity aPlayer) {return F;}
	@Override public void onCoverLoaded(byte aCoverSide, CoverData aData) {/**/}
	@Override public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {if (aPlayer != null) UT.Sounds.send(SFX.GT_SCREWDRIVER, aData.mTileEntity);}
	@Override public void onCoverRemove(byte aCoverSide, CoverData aData, Entity aPlayer) {/**/}
	@Override public void onTickPre (byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {/**/}
	@Override public void onTickPost(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {/**/}
	@Override public boolean onCoverClickedLeft (byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean interceptClickLeft (byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return T;}
	@Override public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean interceptClickRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return T;}
	@Override public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return 0;}
	@Override public ItemStack getCoverItem(byte aCoverSide, CoverData aData) {return ST.make(aData.mIDs[aCoverSide], 1, aData.mMetas[aCoverSide], aData.mNBTs[aCoverSide]==null||aData.mNBTs[aCoverSide].hasNoTags()?null:aData.mNBTs[aCoverSide]);}
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return null;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return getCoverTextureSurface(aCoverSide, aData);}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return getCoverTextureSurface(aCoverSide, aData);}
	@Override public boolean isSolid(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean isOpaque(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean isSealable(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean isFullTexture(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean isDecorative(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean needsVisualsSaved(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean onWalkOver(byte aCoverSide, CoverData aData, Entity aEntity) {return T;}
	@Override public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {UT.Sounds.send(SFX.MC_BREAK, 1.0F, -1.0F, aTileEntity);}
	@Override public void onBlockUpdate(byte aCoverSide, CoverData aData) {/**/}
	@Override public void onStoppedUpdate(byte aCoverSide, CoverData aData, boolean aStopped) {/**/}
	@Override public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {aList.add(LH.Chat.DGRAY + LH.get(LH.COVER_TOOLTIP));}
	@Override public byte getRedstoneIn(byte aCoverSide, CoverData aData) {return UT.Code.bind4(aData.mTileEntity.getWorld().getIndirectPowerLevelTo(aData.mTileEntity.getOffsetX(aCoverSide), aData.mTileEntity.getOffsetY(aCoverSide), aData.mTileEntity.getOffsetZ(aCoverSide), aCoverSide));}
	@Override public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {return aDefaultRedstone;}
	@Override public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {return aDefaultRedstone;}
	
	@Override public Object getGUIServer(byte aCoverSide, CoverData aData, EntityPlayer aPlayer) {return null;}
	@Override public Object getGUIClient(byte aCoverSide, CoverData aData, EntityPlayer aPlayer) {return null;}
	
	@Override public float[] getCoverBounds(byte aCoverSide, CoverData aData) {return BOXES_COVERS[aCoverSide];}
	@Override public float[] getHolderBounds(byte aCoverSide, CoverData aData) {return BOXES_HOLDERS[aCoverSide];}
	
	@Override public void getCollisions(byte aCoverSide, CoverData aData, AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {aData.box(aAABB, aList, getCoverBounds(aCoverSide, aData));}
	
	@Override public boolean interceptConnect(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean interceptDisconnect(byte aCoverSide, CoverData aData) {return F;}
	
	@Override public boolean interceptItemInsert(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean interceptItemExtract(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean getAccessibleSlotsFromSideOverride(byte aCoverSide, CoverData aData, byte aSide) {return F;}
	@Override public boolean canInsertItemOverride(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItemOverride(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public int[] getAccessibleSlotsFromSide(byte aCoverSide, CoverData aData, byte aSide, int[] aDefault) {return aDefault;}
	@Override public boolean canInsertItem(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return T;}
	@Override public boolean canExtractItem(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return T;}
	
	@Override public boolean interceptFluidFill(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill) {return F;}
	@Override public boolean interceptFluidDrain(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain) {return F;}
	@Override public boolean getFluidTankFillableOverride(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill) {return F;}
	@Override public boolean getFluidTankDrainableOverride(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain) {return F;}
	@Override public boolean getFluidTanksOverride(byte aCoverSide, CoverData aData, byte aSide) {return F;}
	@Override public IFluidTank getFluidTankFillable(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill, IFluidTank aDefault) {return aDefault;}
	@Override public IFluidTank getFluidTankDrainable(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain, IFluidTank aDefault) {return aDefault;}
	@Override public IFluidTank[] getFluidTanks(byte aCoverSide, CoverData aData, byte aSide, IFluidTank[] aDefault) {return aDefault;}
	
	public static final ITexture BACKGROUND_COVER = BlockTextureDefault.get("machines/covers/base");
}
