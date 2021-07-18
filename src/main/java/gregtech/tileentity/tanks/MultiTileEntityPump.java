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

package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPump extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff {
	protected boolean mStopped = F, mActive = F;
	protected long mEnergy = 0, mInput = 32, mActiveData = 0, mNextCheck = 0;
	protected byte mActiveState = 0, mExplosionPrevention = 0, mDir = 0;
	protected TagData mEnergyType = TD.Energy.RU;
	protected FluidTankGT mTank = new FluidTankGT();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) {mActiveData = aNBT.getLong(NBT_ACTIVE_DATA);}
		if (aNBT.hasKey(NBT_INPUT)) {mInput = aNBT.getLong(NBT_INPUT);}
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyType = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ACTIVE_DATA, mActiveData);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	static {
		LH.add("gt.tooltip.pump.1", "Usable to clear large Bodies of Fluid");
		LH.add("gt.tooltip.pump.2", "Not suitable for making infinite Fluid Sources!");
		LH.add("gt.tooltip.pump.3", "Use a Drain Cover for that instead!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.tooltip.pump.1"));
		LH.addEnergyToolTips(this, aList, mEnergyType, null, LH.get(LH.FACE_BACK), null);
		aList.add(Chat.ORANGE + LH.get("gt.tooltip.pump.2"));
		aList.add(Chat.ORANGE + LH.get("gt.tooltip.pump.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public ArrayList<ChunkCoordinates> mCheckList = new ArrayList<>();
	public LinkedList<ChunkCoordinates> mPumpList = new LinkedList<>();
	public HashSetNoNulls<ChunkCoordinates> mChecked = new HashSetNoNulls<>();
	public HashSetNoNulls<Block> mPumpedFluids = new HashSetNoNulls<>();
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			for (byte tSide : ALL_SIDES_VALID_BUT_AXIS[mFacing]) if (mTank.has()) FL.move(mTank, getAdjacentTileEntity(tSide)); else break;
			
			mNextCheck--;
			
			if (mStopped || mEnergy < 8192 || mTank.has()) {
				mActive = F;
			} else {
				mActive = T;
				if (mCheckList.isEmpty()) {
					if (mNextCheck < 0) {
						// Reset everything and add the Fluid Block in front of the Pump to the Lists.
						scanForFluid(getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing));
						// Next Reset should only happen in two and a half Minutes or so.
						mNextCheck = 3000;
					} else {
						mIgnoreUnloadedChunks = F;
						if (mPumpList.isEmpty()) {
							// We are done with this Y-Level, lets scan again in a second!
							if (mNextCheck > 20) mNextCheck = 20;
						} else if (!drainFluid(mPumpList.removeLast())) {
							// Something changed for some reason, lets scan again right away!
							mNextCheck = 0;
						}
						mIgnoreUnloadedChunks = T;
					}
				} else {
					// If the List still contains Elements, then scan the next Y Level for more Fluids.
					scanForFluid(getOffsetX(mFacing), getOffsetZ(mFacing));
					// Next Reset should only happen in two and a half Minutes or so.
					mNextCheck = 3000;
				}
			}
			
			if (mTimer % 600 == 5) if (mActiveData != 0) doDefaultStructuralChecks(); else if (mExplosionPrevention > 0) mExplosionPrevention--;
		}
	}
	
	private void scanForFluid(int aX, int aZ) {
		ChunkCoordinates[] tNeedsToBeChecked = mCheckList.toArray(ZL_COORDS);
		mCheckList.clear();
		
		for (ChunkCoordinates tPos : tNeedsToBeChecked) {
			if (mDir != 0 && mPumpedFluids.contains(getBlock(tPos.posX, tPos.posY + mDir, tPos.posZ))) {
				mPumpList = new LinkedList<>();
				mCheckList.clear();
				mChecked.clear();
				addToList(tPos.posX, tPos.posY + mDir, tPos.posZ);
				return;
			}
			if (tPos.posX < aX + 64) addToList(tPos.posX + 1, tPos.posY, tPos.posZ);
			if (tPos.posX > aX - 64) addToList(tPos.posX - 1, tPos.posY, tPos.posZ);
			if (tPos.posZ < aZ + 64) addToList(tPos.posX, tPos.posY, tPos.posZ + 1);
			if (tPos.posZ > aZ - 64) addToList(tPos.posX, tPos.posY, tPos.posZ - 1);
		}
	}
	
	private void scanForFluid(int aX, int aY, int aZ) {
		mPumpList = new LinkedList<>();
		mCheckList.clear();
		mChecked.clear();
		
		mPumpedFluids.clear();
		Block aBlock = getBlockAtSide(mFacing);
		if (WD.lava(aBlock)) {
			mPumpedFluids.add(Blocks.lava);
			mPumpedFluids.add(Blocks.flowing_lava);
			mDir = +1;
		} else
		if (WD.water(aBlock) || aBlock == BlocksGT.River || aBlock == BlocksGT.Ocean || aBlock == BlocksGT.Swamp) {
			mPumpedFluids.add(Blocks.water);
			mPumpedFluids.add(Blocks.flowing_water);
			mPumpedFluids.add(BlocksGT.River);
			mPumpedFluids.add(BlocksGT.Ocean);
			mPumpedFluids.add(BlocksGT.Swamp);
			mDir = +1;
		} else
		if (aBlock instanceof IFluidBlock) {
			mPumpedFluids.add(aBlock);
			mDir = (byte)(FL.lighter(((IFluidBlock)aBlock).getFluid()) ? -1 : +1);
		} else {
			if (mEnergy >= 8192) mEnergy -= 8192;
			return;
		}
		
		addToList(aX, aY, aZ);
	}
	
	private boolean addToList(int aX, int aY, int aZ) {
		ChunkCoordinates tCoordinate = new ChunkCoordinates(aX, aY, aZ);
		if (mChecked.add(tCoordinate)) {
			if (mPumpedFluids.contains(getBlock(aX, aY, aZ))) {
				mPumpList.add(tCoordinate);
				mCheckList.add(tCoordinate);
				return T;
			}
		}
		return F;
	}
	
	private boolean drainFluid(ChunkCoordinates aCoords) {
		Block aBlock = getBlock(aCoords);
		// Seems like someone removed or replaced a Fluid Block! Scan again!
		if (!mPumpedFluids.contains(aBlock)) return F;
		// Determine the Fluid that is produced.
		if (WD.water(aBlock) || aBlock == BlocksGT.River) {
			if (getMetaData(aCoords) == 0) mTank.setFluid(FL.Water.make(1000));
		} else if (WD.lava(aBlock)) {
			if (getMetaData(aCoords) == 0) mTank.setFluid(FL.Lava.make(1000));
		} else if (aBlock instanceof IFluidBlock) {
			mTank.setFluid(((IFluidBlock)aBlock).drain(worldObj, aCoords.posX, aCoords.posY, aCoords.posZ, F));
		}
		// Consume Energy based on Fluid Amount absorbed.
		mEnergy -= Math.max(16, UT.Code.units(mTank.amount(), 1000, 2048, T));
		// something prevented the setBlock Function! Scan again!
		if (!worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, NB, 0, 2)) return F;
		// If there is a Fluid Block above this one, clearly the Y-Level is off due to a recent Blockchange! Scan again!
		if (mPumpedFluids.contains(getBlock(aCoords.posX, aCoords.posY+mDir, aCoords.posZ))) return F;
		// Somehow this Block is completely surrounded by pumpable Fluid, this should not be possible unless it is the literal Cornercase! Scan again!
		return !(
		mPumpedFluids.contains(getBlock(aCoords.posX+1, aCoords.posY, aCoords.posZ  )) &&
		mPumpedFluids.contains(getBlock(aCoords.posX-1, aCoords.posY, aCoords.posZ  )) &&
		mPumpedFluids.contains(getBlock(aCoords.posX  , aCoords.posY, aCoords.posZ+1)) &&
		mPumpedFluids.contains(getBlock(aCoords.posX  , aCoords.posY, aCoords.posZ-1)));
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mActiveData <<= 1;
		if (mActive) mActiveData |= 1;
		byte tActiveState = mActiveState;
		if (mActiveData == 0 || mStopped) mActiveState = 0; else if (mActiveData == ~0L) mActiveState = 1; else mActiveState = 2;
		return tActiveState != mActiveState || super.onTickCheck(aTimer);
	}
	
	@Override
	public void setVisualData(byte aData) {
		mActiveState = aData;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTank;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTank.AS_ARRAY;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		aSize = Math.abs(aSize);
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overload(aSize, aEnergyType);
			return aAmount;
		}
		if (mEnergy >= 8192) return 0;
		long tInput = Math.min(8192 - mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
		if (aDoInject) mEnergy += tConsumed * aSize;
		return tConsumed;
	}
	
	public void overload(long aSize, TagData aEnergyType) {
		if (mExplosionPrevention < 100) {
			if (mTimer < 100) DEB.println("Machine overloaded on startup with: " + aSize + " " + aEnergyType.getLocalisedNameLong());
			mExplosionPrevention++;
			mEnergy = 0;
		} else {
			overcharge(aSize, aEnergyType);
		}
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && (SIDES_INVALID[aSide] || aSide == OPOS[mFacing]) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyType.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public void onFacingChange(byte aPreviousFacing) {mNextCheck = 1; mChecked.clear(); mCheckList.clear(); mPumpList.clear(); mPumpedFluids.clear();}
	
	@Override public boolean getStateRunningPossible() {return !mPumpList.isEmpty() || WD.liquid(getBlockAtSide(mFacing));}
	@Override public boolean getStateRunningPassively() {return mActiveData != 0;}
	@Override public boolean getStateRunningActively() {return mActiveData != 0 && !mPumpedFluids.isEmpty();}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public byte getVisualData() {return mActiveState;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get((mActiveState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/pump/rotation/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.pump.rotation";}
}
