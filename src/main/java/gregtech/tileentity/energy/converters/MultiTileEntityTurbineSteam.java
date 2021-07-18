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

package gregtech.tileentity.energy.converters;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.GarbageGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.TileEntityBase11Motor;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class MultiTileEntityTurbineSteam extends TileEntityBase11Motor implements IFluidHandler {
	public FluidTankGT mTank = new FluidTankGT();
	public long mSteamCounter = 0, mEnergyProducedNextTick = 0;
	public static final int STEAM_PER_WATER = 200;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ENERGY_SU)) mSteamCounter = aNBT.getLong(NBT_ENERGY_SU);
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mEnergyProducedNextTick = aNBT.getLong(NBT_OUTPUT_SU);
		mTank.readFromNBT(aNBT, NBT_TANK+"."+0);
		mTank.setCapacity(mConverter.mEnergyIN.mMax*4);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY_SU, mSteamCounter);
		UT.NBT.setNumber(aNBT, NBT_OUTPUT_SU, mEnergyProducedNextTick);
		mTank.writeToNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.ORANGE + LH.get(LH.EMITS_USED_STEAM) + " ("+LH.get(LH.FACE_SIDES)+", 80%)");
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_plunger)) return GarbageGT.trash(mTank);
		return 0;
	}
	
	@Override
	public void doConversion(long aTimer) {
		if (mEnergyProducedNextTick > 0) {
			mStorage.mEnergy += mEnergyProducedNextTick;
			mEnergyProducedNextTick = 0;
		} else if (mTank.has(getEnergySizeInputMin(mConverter.mEnergyIN.mType, SIDE_ANY) * 2)) {
			long tSteam = mTank.amount();
			mSteamCounter += tSteam;
			mStorage.mEnergy += tSteam / STEAM_PER_EU;
			mEnergyProducedNextTick += tSteam / STEAM_PER_EU;
			mTank.setEmpty();
			if (mSteamCounter >= STEAM_PER_WATER) {
				FluidStack tDistilledWater = FL.DistW.make(mSteamCounter / STEAM_PER_WATER);
				for (byte tDir : FACING_SIDES[mFacing]) {
					tDistilledWater.amount -= FL.fill(getAdjacentTank(tDir), tDistilledWater.copy(), T);
					if (tDistilledWater.amount <= 0) break;
				}
				GarbageGT.trash(tDistilledWater);
				mSteamCounter %= STEAM_PER_WATER;
			}
		}
		super.doConversion(aTimer);
	}
	
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?0.5F:0.25F;}
	@Override public boolean isSideSolid2           (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return T;}
	@Override public boolean allowCovers            (byte aSide) {return T;}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return isInput(aSide) && !mStopped && FL.steam(aFluidToFill) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return isOutput(aSide) ? null : mTank.AS_ARRAY;}
	
	@Override public void onWalkOver2(EntityLivingBase aEntity) {if (SIDES_TOP[mFacing] && mActivity.mState>0) {aEntity.rotationYaw=aEntity.rotationYaw+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1); aEntity.rotationYawHead=aEntity.rotationYawHead+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1);}}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?mCounterClockwise?(mConverter.mFast?sOverlaysActiveLF:sOverlaysActiveLS):(mConverter.mFast?sOverlaysActiveRF:sOverlaysActiveRS):sOverlays)[aIndex]));
	}
	
	@Override public boolean isInput (byte aSide) {return aSide == OPOS[mFacing];}
	@Override public boolean isOutput(byte aSide) {return aSide == mFacing;}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_BACK);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_FRONT);}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay/side"),
	}, sOverlaysActiveLS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_ls/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_ls/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_ls/side"),
	}, sOverlaysActiveLF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_lf/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_lf/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_lf/side"),
	}, sOverlaysActiveRS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rs/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rs/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rs/side"),
	}, sOverlaysActiveRF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rf/front"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rf/back"),
		new Textures.BlockIcons.CustomIcon("machines/turbines/rotation_steam/overlay_active_rf/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.turbines.rotation_steam";}
}
