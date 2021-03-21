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

package gregtech.tileentity.tools;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase10Attachment;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntityCrucible;
import gregapi.tileentity.machines.ITileEntityMold;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFaucet extends TileEntityBase10Attachment implements ITileEntityMold, IMTE_IgnorePlayerCollisionWhenPlacing {
	private static double HEAT_RESISTANCE_BONUS = 1.25;
	
	protected boolean mAcidProof = F, mAutoPull = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE)) mAutoPull = aNBT.getBoolean(NBT_MODE);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_MODE, mAutoPull);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT));
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + getMoldMaxTemperature() + " K)");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mAutoPull ? SERVER_TIME % 50 == 5 : (mBlockUpdated && hasRedstoneIncoming())) {
				DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mFacing);
				if (tDelegator.mTileEntity instanceof ITileEntityCrucible) {
					((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(this, tDelegator.mSideOfTileEntity, mFacing);
				}
			}
		}
	}
	
	@Override
	public boolean isMoldInputSide(byte aSide) {
		return aSide == mFacing;
	}
	
	@Override
	public long getMoldMaxTemperature() {
		return (long)(mMaterial.mMeltingPoint * HEAT_RESISTANCE_BONUS);
	}
	
	@Override
	public long getMoldRequiredMaterialUnits() {
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(SIDE_BOTTOM);
		if (tDelegator.mTileEntity instanceof ITileEntityMold) return ((ITileEntityMold)tDelegator.mTileEntity).getMoldRequiredMaterialUnits();
		if (tDelegator.mTileEntity instanceof MultiTileEntityBathingPot || tDelegator.mTileEntity instanceof MultiTileEntityMixingBowl) return U;
		return 0;
	}
	
	@Override
	public long fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide) {
		if (aSide != mFacing || aMaterial == null || aMaterial.mMaterial == null || (!mAcidProof && aMaterial.mMaterial.contains(TD.Properties.ACID))) return 0;
		if (aTemperature > getMoldMaxTemperature()) {
			UT.Sounds.send(SFX.MC_FIZZ, this);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.flowing_lava, 1, 3);
		}
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(SIDE_BOTTOM);
		while (tDelegator.mY > 0 && (tDelegator.mTileEntity instanceof MultiTileEntityFaucet || (!(tDelegator.mTileEntity instanceof MultiTileEntityBathingPot || tDelegator.mTileEntity instanceof MultiTileEntityMixingBowl || tDelegator.mTileEntity instanceof ITileEntityMold) && !WD.hasCollide(tDelegator.mWorld, tDelegator.mX, tDelegator.mY, tDelegator.mZ)))) {
			tDelegator = WD.te(tDelegator.mWorld, tDelegator.mX, tDelegator.mY-1, tDelegator.mZ, SIDE_TOP, F);
		}
		if (tDelegator.mTileEntity instanceof ITileEntityMold) return ((ITileEntityMold)tDelegator.mTileEntity).fillMold(aMaterial, aTemperature, tDelegator.mSideOfTileEntity);
		if (tDelegator.mTileEntity instanceof MultiTileEntityBathingPot || tDelegator.mTileEntity instanceof MultiTileEntityMixingBowl) {
			if (aMaterial.mAmount >= U) {
				FluidStack tFluid = aMaterial.mMaterial.liquid(U, F);
				if (FL.Error.is(tFluid)) return 0;
				if (FL.fillAll(tDelegator, tFluid, T)) return U;
			}
			return 0;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mFacing);
			if (tDelegator.mTileEntity instanceof ITileEntityCrucible) ((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(this, tDelegator.mSideOfTileEntity, mFacing);
		}
		return T;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_softhammer)) {
			mAutoPull = F;
			if (aChatReturn != null) aChatReturn.add("Crucible Auto-Input: REDSTONE");
			return 10000;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			mAutoPull = !mAutoPull;
			if (aChatReturn != null) aChatReturn.add(mAutoPull ? "Crucible Auto-Input: AUTOMATIC" : "Crucible Auto-Input: REDSTONE");
			return 10000;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING));
		return 3;
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 0], PX_N[ 6], PX_N[14], PX_N[12]); return T;
			default        : box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[12], PX_N[ 6], PX_N[14], PX_N[ 0]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 6], PX_N[12], PX_N[14], PX_N[ 6]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 1], PX_P[ 6], PX_N[ 0], PX_N[14], PX_N[ 6]); return T;
			}
		case 1:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[ 0], PX_N[10], PX_N[10], PX_N[12]); return T;
			default        : box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[12], PX_N[10], PX_N[10], PX_N[ 0]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 5], PX_N[12], PX_N[10], PX_N[10]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 2], PX_P[ 5], PX_N[ 0], PX_N[10], PX_N[10]); return T;
			}
		case 2:
			switch(mFacing) {
			case SIDE_Z_NEG: box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 0], PX_N[ 5], PX_N[10], PX_N[12]); return T;
			default        : box(aBlock, PX_P[10], PX_P[ 2], PX_P[12], PX_N[ 5], PX_N[10], PX_N[ 0]); return T;
			case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[10], PX_N[12], PX_N[10], PX_N[ 5]); return T;
			case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 2], PX_P[10], PX_N[ 0], PX_N[10], PX_N[ 5]); return T;
			}
		}
		return T;
	}
	
	private ITexture mTexture;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aSide != mFacing || aShouldSideBeRendered[aSide] ? mTexture : null;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_Z_NEG: return box(PX_P[ 5], PX_P[ 1], PX_P[ 0], PX_N[ 5], PX_N[10], PX_N[12]);
		default        : return box(PX_P[ 5], PX_P[ 1], PX_P[12], PX_N[ 5], PX_N[10], PX_N[ 0]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 1], PX_P[ 5], PX_N[12], PX_N[10], PX_N[ 5]);
		case SIDE_X_POS: return box(PX_P[12], PX_P[ 1], PX_P[ 5], PX_N[ 0], PX_N[10], PX_N[ 5]);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		switch(mFacing) {
		case SIDE_Z_NEG: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 0], PX_N[ 5], PX_N[10], PX_N[12]); break;
		default        : box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[12], PX_N[ 5], PX_N[10], PX_N[ 0]); break;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 5], PX_N[12], PX_N[10], PX_N[ 5]); break;
		case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 1], PX_P[ 5], PX_N[ 0], PX_N[10], PX_N[ 5]); break;
		}
	}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.smeltery.drain";}
}
