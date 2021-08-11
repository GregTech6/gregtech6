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

package gregapi.cover;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.render.ITexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;


/**
 * @author Gregorius Techneticies
 * 
 * Interfaces to use Cover Functionality.
 */
public interface ICover {
	/**
	 * Called when the Cover got successfully loaded.
	 * @param aPlayer CAN be null!
	 */
	public void onCoverLoaded(byte aCoverSide, CoverData aData);
	
	/**
	 * Called when the Cover got successfully placed.
	 * @param aPlayer CAN be null!
	 */
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover);
	
	/**
	 * Called when the Cover got successfully placed.
	 * @param aPlayer CAN be null!
	 */
	public void onCoverRemove(byte aCoverSide, CoverData aData, Entity aPlayer);
	
	/**
	 * @param aData CAN be null!
	 * @param aPlayer CAN be null!
	 * @return true to prevent that the cover gets placed.
	 */
	public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer);
	
	/**
	 * @param aPlayer CAN be null!
	 * @return true to prevent that the cover gets removed.
	 */
	public boolean interceptCoverRemoval(byte aCoverSide, CoverData aData, Entity aPlayer);
	
	/**
	 * @param aPlayer CAN be null!
	 * @return true to prevent that a Connector can connect.
	 */
	public boolean interceptConnect(byte aCoverSide, CoverData aData);
	
	/**
	 * @param aPlayer CAN be null!
	 * @return true to prevent that a Connector can disconnect.
	 */
	public boolean interceptDisconnect(byte aCoverSide, CoverData aData);
	
	/**
	 * Called before the Block does its own Tick. So for example before it checks a Recipe or something.
	 */
	public void onTickPre(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate);
	
	/**
	 * Called after the Block does its own Tick. So for example after it has processed a Recipe or something.
	 */
	public void onTickPost(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate);
	
	/**
	 * Called when the Cover got clicked.
	 * @param aPlayer CAN be null!
	 * @return true to intercept clicking the Side of the normal Block and cause the click animation
	 */
	public boolean onCoverClickedLeft(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ);
	
	/**
	 * Called when the Cover got clicked.
	 * @param aPlayer CAN be null!
	 * @return true to intercept clicking the Side of the normal Block without the click animation.
	 */
	public boolean interceptClickLeft(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ);
	
	/**
	 * Called when the Cover got clicked.
	 * @param aPlayer CAN be null!
	 * @return true to intercept clicking the Side of the normal Block and cause the click animation
	 */
	public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ);
	
	/**
	 * Called when the Cover got clicked.
	 * @param aPlayer CAN be null!
	 * @return true to intercept clicking the Side of the normal Block without the click animation.
	 */
	public boolean interceptClickRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ);
	
	/**
	 * Relaying the Tool Clicks to Covers.
	 */
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ);
	
	/**
	 * Gets called after a Cover gets removed via Crowbar.
	 */
	public void onAfterCrowbar(ITileEntityCoverable aTileEntity);
	
	/**
	 * Gets called when a Block Update happens.
	 */
	public void onBlockUpdate(byte aCoverSide, CoverData aData);
	
	/**
	 * Gets called when a Controller Cover stops the Covers on this Block.
	 */
	public void onStoppedUpdate(byte aCoverSide, CoverData aData, boolean aStopped);
	
	/**
	 * Tooltip List for this Cover Type.
	 */
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H);
	
	/**
	 * Called when some Entity walks over this Cover. Obviously aCoverSide is always SIDE_TOP aka 1.
	 */
	public boolean onWalkOver(byte aCoverSide, CoverData aData, Entity aEntity);
	
	/**
	 * Gets the Item that drops when removing the Cover.
	 */
	public ItemStack getCoverItem(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if this side is Solid
	 */
	public boolean isSolid(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if this side is Opaque
	 */
	public boolean isOpaque(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if this side is a Full Texture
	 */
	public boolean isFullTexture(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if this side is Sealable
	 */
	public boolean isSealable(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if this side is showing the Front face of a Pipe if attached to it. (Client Side Call)
	 */
	public boolean showsConnectorFront(byte aCoverSide, CoverData aData);
	
	/**
	 * @return if you need to save the visual state of this Cover to NBT because the visuals contain crucial information.
	 */
	public boolean needsVisualsSaved(byte aCoverSide, CoverData aData);
	
	public byte getRedstoneIn(byte aCoverSide, CoverData aData);
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone);
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone);
	
	public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData);
	public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide);
	public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide);
	
	public Object getGUIServer(byte aCoverSide, CoverData aData, EntityPlayer aPlayer);
	public Object getGUIClient(byte aCoverSide, CoverData aData, EntityPlayer aPlayer);
	
	public float[] getCoverBounds (byte aCoverSide, CoverData aData);
	public float[] getHolderBounds(byte aCoverSide, CoverData aData);
	
	public void getCollisions(byte aCoverSide, CoverData aData, AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity);
	
	public static final float[][] BOXES_COVERS  = new float[][] {{PX_P[ 0]          , PX_P[ 0], PX_P[ 0]          , PX_N[ 0]          , PX_N[14], PX_N[ 0]          }, {PX_P[ 0]          , PX_P[14], PX_P[ 0]          , PX_N[ 0]          , PX_N[ 0], PX_N[ 0]          }, {PX_P[ 0]          , PX_P[ 0]          , PX_P[ 0], PX_N[ 0]          , PX_N[ 0]          , PX_N[14]}, {PX_P[ 0]          , PX_P[ 0]          , PX_P[14], PX_N[ 0]          , PX_N[ 0]          , PX_N[ 0]}, {PX_P[ 0], PX_P[ 0]          , PX_P[ 0]          , PX_N[14], PX_N[ 0]          , PX_N[ 0]          }, {PX_P[14], PX_P[ 0]          , PX_P[ 0]          , PX_N[ 0], PX_N[ 0]          , PX_N[ 0]          }};
	public static final float[][] BOXES_HOLDERS = new float[][] {{PX_P[ 7]+PX_OFFSET, PX_P[ 0], PX_P[ 7]+PX_OFFSET, PX_N[ 7]-PX_OFFSET, PX_N[ 8], PX_N[ 7]-PX_OFFSET}, {PX_P[ 7]+PX_OFFSET, PX_P[ 8], PX_P[ 7]+PX_OFFSET, PX_N[ 7]-PX_OFFSET, PX_N[ 0], PX_N[ 7]-PX_OFFSET}, {PX_P[ 7]+PX_OFFSET, PX_P[ 7]+PX_OFFSET, PX_P[ 0], PX_N[ 7]-PX_OFFSET, PX_N[ 7]-PX_OFFSET, PX_N[ 8]}, {PX_P[ 7]+PX_OFFSET, PX_P[ 7]+PX_OFFSET, PX_P[ 8], PX_N[ 7]-PX_OFFSET, PX_N[ 7]-PX_OFFSET, PX_N[ 0]}, {PX_P[ 0], PX_P[ 7]+PX_OFFSET, PX_P[ 7]+PX_OFFSET, PX_N[ 8], PX_N[ 7]-PX_OFFSET, PX_N[ 7]-PX_OFFSET}, {PX_P[ 8], PX_P[ 7]+PX_OFFSET, PX_P[ 7]+PX_OFFSET, PX_N[ 0], PX_N[ 7]-PX_OFFSET, PX_N[ 7]-PX_OFFSET}};
	
	public boolean interceptItemInsert(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	public boolean interceptItemExtract(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	public boolean getAccessibleSlotsFromSideOverride(byte aCoverSide, CoverData aData, byte aSide);
	public boolean canInsertItemOverride(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	public boolean canExtractItemOverride(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	public int[] getAccessibleSlotsFromSide(byte aCoverSide, CoverData aData, byte aSide, int[] aDefault);
	public boolean canInsertItem(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	public boolean canExtractItem(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide);
	
	public boolean interceptFluidFill(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill);
	public boolean interceptFluidDrain(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain);
	public boolean getFluidTankFillableOverride(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill);
	public boolean getFluidTankDrainableOverride(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain);
	public boolean getFluidTanksOverride(byte aCoverSide, CoverData aData, byte aSide);
	public IFluidTank getFluidTankFillable(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill, IFluidTank aDefault);
	public IFluidTank getFluidTankDrainable(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain, IFluidTank aDefault);
	public IFluidTank[] getFluidTanks(byte aCoverSide, CoverData aData, byte aSide, IFluidTank[] aDefault);
}
