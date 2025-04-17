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

package gregtech.tileentity.multiblocks;

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityTank3x3x3 extends MultiTileEntityTank {
	static {
		LH.add("gt.tooltip.multiblock.tank3x3x3.1", "3x3x3 Hollow of the corresponding Walls made of this Material");
		LH.add("gt.tooltip.multiblock.tank3x3x3.2", "This Block centered on Side/Top/Bottom and facing outwards");
		LH.add("gt.tooltip.multiblock.tank3x3x3.3", "Auto-Emits Fluids from the Main Block if not against Gravity");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean checkStructure2(ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory) {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (getAir(tX+i, tY+j, tZ+k)) worldObj.setBlockToAir(tX+i, tY+j, tZ+k); else tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, mTankWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID, aCoordinates, aPlayer, aInventory)) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && checkStructure(F)) {
			FluidStack tFluid = mTank.getFluid();
			if (tFluid != null && tFluid.amount > 0) {
				if (FL.temperature(mTank) >= mMaterial.mMeltingPoint && meltdown()) return;
				
				if (!mMagicProof && FL.magic(tFluid)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
					int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
					for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
						if (rng(3) == 0) WD.set(worldObj, tX+i, tY+j, tZ+k, FL.gas(tFluid) ? IL.TC_Flux_Gas.block() : IL.TC_Flux_Goo.block(), IL.TC_Flux_Goo.exists() ? 7 : 0, 3);
					}
					WD.set(worldObj, xCoord, yCoord, zCoord, FL.gas(tFluid) ? IL.TC_Flux_Gas.block() : IL.TC_Flux_Goo.block(), IL.TC_Flux_Goo.exists() ? 7 : 0, 3);
					return;
				}
				if (!mAcidProof && FL.acid(mTank)) {
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
					GarbageGT.trash(mTank);
					int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
					for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
						if (rng(3) == 0) worldObj.setBlockToAir(tX+i, tY+j, tZ+k);
					}
					setToAir();
					return;
				}
				if (!mPlasmaProof && FL.plasma(mTank)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
				} else
				if (!mGasProof && FL.gas(mTank)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
				} else
				if (!allowFluid(tFluid)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(SFX.MC_FIZZ, this, F);
				} else
				if (SIDES_HORIZONTAL[mFacing] || FL.gas(mTank) || (FL.lighter(tFluid)?SIDES_TOP:SIDES_BOTTOM)[mFacing]) {
					if (FL.move(mTank, getAdjacentTileEntity(mFacing)) > 0) updateInventory();
				}
			}
		}
	}
	
	public boolean meltdown() {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
			WD.burn(worldObj, tX+i, tY+j, tZ+k, F, F);
			if (rng(4) == 0) worldObj.setBlock(tX+i, tY+j, tZ+k, Blocks.fire, 0, 3);
		}
		if (FL.lava(mTank) && mTank.drainAll(1000)) worldObj.setBlock(tX, tY, tZ, Blocks.flowing_lava, 0, 3);
		GarbageGT.trash(mTank);
		setToFire();
		return T;
	}
}
