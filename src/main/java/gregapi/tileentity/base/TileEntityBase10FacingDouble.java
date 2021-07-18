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

import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.CS.ToolsGT;
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
public abstract class TileEntityBase10FacingDouble extends TileEntityBase09FacingSingle {
	public byte mSecondFacing = getDefaultSecondSide();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_FACING + ".2nd")) mSecondFacing = aNBT.getByte(NBT_FACING + ".2nd");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_FACING + ".2nd", mSecondFacing);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (getFacingTool() != null)
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_FACING_PRE ) + LH.get(TOOL_LOCALISER_PREFIX + getFacingTool()  , "Unknown"      ) + LH.get(LH.TOOL_TO_SET_FACING_POST ));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_FACING2_PRE) + LH.get(TOOL_LOCALISER_PREFIX + TOOL_monkeywrench, "Monkey Wrench") + LH.get(LH.TOOL_TO_SET_FACING2_POST));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (getFacingTool() != null && aTool.equals(getFacingTool())) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (getValidSides()[aTargetSide]) {
				byte oFacing = mFacing, oSecondFacing = mSecondFacing;
				mFacing = aTargetSide;
				if (!getValidSecondSides()[mSecondFacing]) {
					mSecondFacing = OPOS[mFacing];
					if (!getValidSecondSides()[mSecondFacing]) for (byte tSide : ALL_SIDES_VALID) {
						mSecondFacing = tSide;
						if (getValidSecondSides()[mSecondFacing]) break;
					}
				}
				updateClientData();
				causeBlockUpdate();
				onFacingChange(oFacing, oSecondFacing);
				checkCoverValidity();
				doEnetUpdate();
				if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
				return 10000;
			}
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (getValidSecondSides()[aTargetSide]) {
				byte oSecondFacing = mSecondFacing;
				mSecondFacing = aTargetSide;
				updateClientData();
				causeBlockUpdate();
				onFacingChange(mFacing, oSecondFacing);
				checkCoverValidity();
				doEnetUpdate();
				if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
				return 10000;
			}
		}
		return 0;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mFacing       = (useInversePlacementRotation(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)?UT.Code.getOppositeSideForPlayerPlacing(aPlayer, mFacing      , getValidSides      ()):UT.Code.getSideForPlayerPlacing        (aPlayer, mFacing      , getValidSides      ()));
		mSecondFacing = (useInversePlacementRotation(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)?UT.Code.getSideForPlayerPlacing        (aPlayer, mSecondFacing, getValidSecondSides()):UT.Code.getOppositeSideForPlayerPlacing(aPlayer, mSecondFacing, getValidSecondSides()));
		onFacingChange(SIDE_UNKNOWN, SIDE_UNKNOWN);
		checkCoverValidity();
		doEnetUpdate();
		return T;
	}
	
	public void onFacingChange(byte aPreviousFacing, byte aPreviousSecondFacing) {onFacingChange(aPreviousFacing);}
	
	@Override public byte getDirectionData() {return (byte)(mFacing | (mSecondFacing << 3));}
	@Override public void setDirectionData(byte aData) {mFacing = (byte)(aData & 7); mSecondFacing = (byte)((aData >>> 3) & 7);}
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return super.isUsingWrenchingOverlay(aStack, aSide) || ToolsGT.contains(TOOL_monkeywrench, aStack);}
	@Override public boolean isConnectedWrenchingOverlay(ItemStack aStack, byte aSide) {return aSide == mFacing || aSide == mSecondFacing;}
	
	// Stuff to Override
	public byte getDefaultSecondSide() {return OPOS[getDefaultSide()];}
	public boolean[] getValidSecondSides() {return getValidSides();}
}
