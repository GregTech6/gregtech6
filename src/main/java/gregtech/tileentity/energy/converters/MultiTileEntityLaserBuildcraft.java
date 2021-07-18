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

import buildcraft.api.power.ILaserTarget;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.EnergyCompat;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase10EnergyConverter;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class MultiTileEntityLaserBuildcraft extends TileEntityBase10EnergyConverter implements ITileEntityAdjacentOnOff, ITileEntityEnergyElectricityAcceptor, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	static {LH.add("gt.tooltip.assemblylaser", "Place directly ontop of the Laser powered BC Device");}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.tooltip.assemblylaser"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void doConversion(long aTimer) {
		if (EnergyCompat.BC_LASER) {
			long tOutput = UT.Code.units(mStorage.mEnergy, mConverter.mEnergyIN.mRec, mConverter.mEnergyOUT.mRec, F);
			
			DelegatorTileEntity<TileEntity> tLaser = getAdjacentTileEntity(mFacing, F, F);
			
			mActivity.mActive = (tOutput >= mConverter.mEnergyOUT.mMin && tLaser.mTileEntity instanceof ILaserTarget && ((ILaserTarget)tLaser.mTileEntity).requiresLaserEnergy());
			
			if (mActivity.mActive) {
				if (tOutput > mConverter.mEnergyOUT.mMax && mConverter.mLimitConsumption) tOutput = mConverter.mEnergyOUT.mMax;
				if (tOutput > mConverter.mEnergyOUT.mMax) {
					overload(mStorage.mEnergy, mConverter.mEnergyOUT.mType);
				} else {
					((ILaserTarget)tLaser.mTileEntity).receiveLaserEnergy(UT.Code.bindInt(tOutput));
				}
				mStorage.mEnergy = Math.max(0, mStorage.mEnergy-mConverter.mEnergyIN.mMax);
				mConverter.mEmitsEnergy = T;
				mConverter.mCanEmitEnergy = T;
			}
		}
	}
	
	@Override public byte getDefaultSide() {return SIDE_UP;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM;}
	
	@Override public boolean isInput (byte aSide) {return aSide == OPOS[mFacing];}
	@Override public boolean isOutput(byte aSide) {return aSide == mFacing;}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_BACK);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_FRONT);}
	
	@Override public float getSurfaceSize           (byte aSide) {return aSide == OPOS[mFacing]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return aSide == OPOS[mFacing]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return aSide ==      mFacing ?0.5F:0.0F;}
	@Override public boolean isSideSolid2           (byte aSide) {return aSide == OPOS[mFacing];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return aSide == OPOS[mFacing];}
	@Override public boolean allowCovers            (byte aSide) {return aSide == OPOS[mFacing];}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[mFacing==SIDE_X_NEG?8:0], PX_P[mFacing==SIDE_Y_NEG?8:0], PX_P[mFacing==SIDE_Z_NEG?8:0], PX_N[mFacing==SIDE_X_POS?8:0], PX_N[mFacing==SIDE_Y_POS?8:0], PX_N[mFacing==SIDE_Z_POS?8:0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool()  {return box(PX_P[mFacing==SIDE_X_NEG?8:0], PX_P[mFacing==SIDE_Y_NEG?8:0], PX_P[mFacing==SIDE_Z_NEG?8:0], PX_N[mFacing==SIDE_X_POS?8:0], PX_N[mFacing==SIDE_Y_POS?8:0], PX_N[mFacing==SIDE_Z_POS?8:0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)   {box(aBlock,PX_P[mFacing==SIDE_X_NEG?8:0], PX_P[mFacing==SIDE_Y_NEG?8:0], PX_P[mFacing==SIDE_Z_NEG?8:0], PX_N[mFacing==SIDE_X_POS?8:0], PX_N[mFacing==SIDE_Y_POS?8:0], PX_N[mFacing==SIDE_Z_POS?8:0]);}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 5;
	}
	
	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		return mActivity.mState != 0 || aRenderPass < 2;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case 0: box(aBlock, PX_P[mFacing==SIDE_X_NEG?8:0], PX_P[mFacing==SIDE_Y_NEG?8:0], PX_P[mFacing==SIDE_Z_NEG?8:0], PX_N[mFacing==SIDE_X_POS?8:0], PX_N[mFacing==SIDE_Y_POS?8:0], PX_N[mFacing==SIDE_Z_POS?8:0]); return T;
		case 1: box(aBlock, PX_P[4], PX_P[4], PX_P[4], PX_N[4], PX_N[4], PX_N[4]); return T;
		case 2: box(aBlock, mFacing==SIDE_X_NEG?-0.99F:PX_P[5], mFacing==SIDE_Y_NEG?-0.99F:PX_P[5], mFacing==SIDE_Z_NEG?-0.99F:PX_P[5], mFacing==SIDE_X_POS?+1.99F:PX_N[5], mFacing==SIDE_Y_POS?+1.99F:PX_N[5], mFacing==SIDE_Z_POS?+1.99F:PX_N[5]); return T;
		case 3: box(aBlock, mFacing==SIDE_X_NEG?-0.99F:PX_P[6], mFacing==SIDE_Y_NEG?-0.99F:PX_P[6], mFacing==SIDE_Z_NEG?-0.99F:PX_P[6], mFacing==SIDE_X_POS?+1.99F:PX_N[6], mFacing==SIDE_Y_POS?+1.99F:PX_N[6], mFacing==SIDE_Z_POS?+1.99F:PX_N[6]); return T;
		case 4: box(aBlock, mFacing==SIDE_X_NEG?-0.99F:PX_P[7], mFacing==SIDE_Y_NEG?-0.99F:PX_P[7], mFacing==SIDE_Z_NEG?-0.99F:PX_P[7], mFacing==SIDE_X_POS?+1.99F:PX_N[7], mFacing==SIDE_Y_POS?+1.99F:PX_N[7], mFacing==SIDE_Z_POS?+1.99F:PX_N[7]); return T;
//      case 3: box(aBlock, mFacing==SIDE_X_POS?+1.99F:PIXELS_NEG[6], mFacing==SIDE_Y_POS?+1.99F:PIXELS_NEG[6], mFacing==SIDE_Z_POS?+1.99F:PIXELS_NEG[6], mFacing==SIDE_X_NEG?-0.99F:PIXELS_POS[6], mFacing==SIDE_Y_NEG?-0.99F:PIXELS_POS[6], mFacing==SIDE_Z_NEG?-0.99F:PIXELS_POS[6]); return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  0: int tIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2; return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[tIndex], mRGBa), BlockTextureDefault.get(sOverlays[tIndex]));
		case  1: if (aSide!=OPOS[mFacing]) return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?3:4], MT.Diamond.fRGBaSolid), BlockTextureDefault.get(sOverlays[aSide==mFacing?3:4]));
		default: if (!ALONG_AXIS[aSide][mFacing]) return BlockTextureDefault.get(sLasers[aRenderPass-2], MT.Diamond.fRGBaSolid);
		}
		return null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/colored/side"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/colored/laser_front"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/colored/laser_side")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/overlay/side"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/overlay/laser_front"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/overlay/laser_side")
	}, sLasers[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/laser3"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/laser2"),
		new Textures.BlockIcons.CustomIcon("machines/lasers/laser_buildcraft/laser1")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.lasers.buildcraft_electric";}
}
