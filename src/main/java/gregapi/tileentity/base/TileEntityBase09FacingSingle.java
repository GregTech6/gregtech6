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

package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.CS.ItemsGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase09FacingSingle extends TileEntityBase08Directional implements IMTE_OnPlaced, IMTE_AddToolTips {
	public byte mFacing = getDefaultSide();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_FACING)) mFacing = aNBT.getByte(NBT_FACING);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_FACING, mFacing);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (getFacingTool() != null) aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_FACING_PRE) + LH.get(TOOL_LOCALISER_PREFIX + getFacingTool(), "Unknown") + LH.get(LH.TOOL_TO_SET_FACING_POST));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (getFacingTool() != null && aTool.equals(getFacingTool())) {byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ); if (getValidSides()[aTargetSide]) {byte oFacing = mFacing; mFacing = aTargetSide; updateClientData(); causeBlockUpdate(); onFacingChange(oFacing); return 10000;}}
		return 0;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mFacing = useSidePlacementRotation(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)?useInversePlacementRotation(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)?getValidSides()[OPPOSITES[aSide]]?OPPOSITES[aSide]:getDefaultSide():getValidSides()[aSide]?aSide:getDefaultSide():(useInversePlacementRotation(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)?UT.Code.getOppositeSideForPlayerPlacing(aPlayer, mFacing, getValidSides()):UT.Code.getSideForPlayerPlacing(aPlayer, mFacing, getValidSides()));
		onFacingChange(SIDE_UNKNOWN);
		checkCoverValidity();
		doEnetUpdate();
		return T;
	}
	
	@Override public byte getDirectionData() {return (byte)(mFacing & 7);}
	@Override public void setDirectionData(byte aData) {mFacing = (byte)(aData & 7);}
	@Override public String getFacingTool() {return TOOL_wrench;}
	@Override public short getFacing() {return mFacing;}
	@Override public void setFacing(short aFacing) {setPrimaryFacing(UT.Code.side(aFacing));}
	@Override public boolean wrenchCanSetFacing(EntityPlayer aPlayer, int aSide) {return TOOL_wrench.equals(getFacingTool()) && getValidSides()[aSide] && (aPlayer == null || !ItemsGT.SPECIAL_CASE_TOOLS.contains(aPlayer.getHeldItem(), T));}
	@Override public boolean isConnectedWrenchingOverlay(ItemStack aStack, byte aSide) {return aSide == mFacing;}
	
	public void setPrimaryFacing(byte aFacing) {if (isClientSide() || aFacing == mFacing) return; byte oFacing = mFacing; mFacing = aFacing; updateClientData(); causeBlockUpdate(); onFacingChange(oFacing); checkCoverValidity(); doEnetUpdate(); if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);}
	
	// Stuff to Override
	public byte getDefaultSide() {return SIDE_UP;}
	public boolean[] getValidSides() {return SIDES_VALID;}
	public void onFacingChange(byte aPreviousFacing) {/**/}
	public boolean useSidePlacementRotation() {return F;}
	public boolean useSidePlacementRotation(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return useSidePlacementRotation();}
	public boolean useInversePlacementRotation() {return F;}
	public boolean useInversePlacementRotation(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return useInversePlacementRotation();}
}
