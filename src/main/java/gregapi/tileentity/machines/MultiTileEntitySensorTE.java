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

package gregapi.tileentity.machines;

import gregapi.GT_API_Proxy;
import gregapi.computer.ITileEntityComputerizable;
import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.render.IIconContainer;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntitySensorTE extends MultiTileEntitySensor implements ITileEntityComputerizable, ITileEntityServerTickPost {
	public static final byte
	  MODE_COUNT    = 8
	  
	, MODE_DISPLAY  = 0
	, MODE_PERCENT  = 1
	, MODE_GREATER  = 2
	, MODE_EQUAL    = 3
	, MODE_SMALLER  = 4
	, MODE_SCALE    = 5
	, MODE_FULL     = 6
	, MODE_NOT_FULL = 7
	;
	
	public static final int MAX_AVERAGING_VALUES = Short.MAX_VALUE;
	
	public int[] mValues = new int[1];
	public int mIndex = 0, mCurrentValue = 0, mCurrentMax = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mCurrentMax = aNBT.getInteger("gt.sensor.max");
		mCurrentValue = aNBT.getInteger("gt.sensor.value");
		mIndex = aNBT.getInteger("gt.sensor.index");
		mValues = aNBT.getIntArray("gt.sensor.array");
		if (mValues.length < 1) mValues = new int[1];
		
		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, "gt.sensor.max", mCurrentMax);
		UT.NBT.setNumber(aNBT, "gt.sensor.value", mCurrentValue);
		UT.NBT.setNumber(aNBT, "gt.sensor.index", mIndex);
		aNBT.setIntArray("gt.sensor.array", mValues);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT2(aNBT);
		if (mIndex != 0) aNBT.setInteger("gt.sensor.index", mIndex);
		if (mValues.length > 1) aNBT.setIntArray("gt.sensor.array", new int[mValues.length]);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_PO2T.remove(this);
		onUnregisterPost();
	}
	
	@Override
	public void onServerTickPost(boolean aFirst) {
		if (getTickRate() < 2 || SERVER_TIME % getTickRate() == 0) {
			mIndex = ((mIndex + 1) % mValues.length);
			mDisplayedNumber = mSetNumber = UT.Code.bind16(mSetNumber);
			
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mSecondFacing);
			if (tDelegator.mTileEntity instanceof MultiTileEntityMultiBlockPart) {
				if (((MultiTileEntityMultiBlockPart)tDelegator.mTileEntity).mTarget != null) {
					tDelegator = new DelegatorTileEntity<>((TileEntity)((MultiTileEntityMultiBlockPart)tDelegator.mTileEntity).mTarget, tDelegator.mSideOfTileEntity);
				}
			}
			
			mValues[mIndex] = UT.Code.bindInt(getCurrentValue(tDelegator));
			mCurrentValue = (mValues.length == 1 ? mValues[0] : UT.Code.averageInts(mValues));
			mCurrentMax = UT.Code.bindInt(getCurrentMax(tDelegator));
			byte tRedstone = mRedstone;
			
			switch (mMode & 127) {
			case MODE_DISPLAY   : mDisplayedNumber = UT.Code.bindInt(mCurrentValue); break;
			case MODE_GREATER   : tRedstone = (byte)(mCurrentValue >  mSetNumber?15:0); break;
			case MODE_EQUAL     : tRedstone = (byte)(mCurrentValue == mSetNumber?15:0); break;
			case MODE_SMALLER   : tRedstone = (byte)(mCurrentValue <  mSetNumber?15:0); break;
			case MODE_SCALE     : tRedstone = (byte)UT.Code.scale(mCurrentValue, mSetNumber, 15, F); break;
			case MODE_PERCENT   : tRedstone = (byte)UT.Code.scale(mDisplayedNumber = (mCurrentMax > 0 ? UT.Code.bindInt((mCurrentValue * 100L) / mCurrentMax) : 0), 100L, 15, F); break;
			case MODE_FULL      : tRedstone = (byte)(mCurrentValue >= mCurrentMax?15: 0); break;
			case MODE_NOT_FULL  : tRedstone = (byte)(mCurrentValue >= mCurrentMax? 0:15); break;
			}
			
			tRedstone = UT.Code.bind4(tRedstone);
			
			if (tRedstone != mRedstone) {
				mRedstone = tRedstone;
				causeBlockUpdate();
			}
			
			mDisplayedNumber = UT.Code.unsignS((short)mDisplayedNumber);
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide == mFacing) {
			if (isServerSide()) {
				if ((mMode & 127) != MODE_DISPLAY && (mMode & 127) != MODE_PERCENT && (mMode & 127) != MODE_FULL && (mMode & 127) != MODE_NOT_FULL) {
					float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
					if (mMode < 0) {
						if (tCoords[1] >= PX_P[ 6] && tCoords[1] <= PX_P[ 8]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber-=256; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber+=256; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
						}
						if (tCoords[1] >= PX_P[ 9] && tCoords[1] <= PX_P[11]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber-= 16; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber+= 16; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
						}
						if (tCoords[1] >= PX_P[12] && tCoords[1] <= PX_P[14]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber--   ; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber++   ; playClick(); mSetNumber = UT.Code.bind16(mSetNumber); return T;}
						}
					} else {
						if (tCoords[1] >= PX_P[ 6] && tCoords[1] <= PX_P[ 8]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber-=100; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber+=100; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
						}
						if (tCoords[1] >= PX_P[ 9] && tCoords[1] <= PX_P[11]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber-= 10; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber+= 10; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
						}
						if (tCoords[1] >= PX_P[12] && tCoords[1] <= PX_P[14]) {
							if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber--   ; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
							if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {oDisplayedNumber = Short.MIN_VALUE; mSetNumber++   ; playClick(); mSetNumber = (int)UT.Code.bind(0, 9999, mSetNumber); return T;}
						}
					}
				}
			}
			return T;
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		long tDamage = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (tDamage > 0) return tDamage;
		if (aTool.equals(TOOL_screwdriver)) {
			if (hasHitDisplay(aSide, aHitX, aHitY, aHitZ)) {
				mMode ^= B[7];
				updateClientData();
				return 10000;
			}
			
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
			if (tCoords[1] >= PX_P[ 6] && tCoords[1] <= PX_P[ 8]) {
				if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {if (mMode < 0) mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length - 256)]; else mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length - 100)]; playClick(); if (aChatReturn != null) aChatReturn.add("Averaging over: " + mValues.length + " Values"); return 1000;}
				if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {if (mMode < 0) mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length + 256)]; else mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length + 100)]; playClick(); if (aChatReturn != null) aChatReturn.add("Averaging over: " + mValues.length + " Values"); return 1000;}
			}
			if (tCoords[1] >= PX_P[ 9] && tCoords[1] <= PX_P[11]) {
				if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {if (mMode < 0) mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length - 16)]; else mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length - 10)]; playClick(); if (aChatReturn != null) aChatReturn.add("Averaging over: " + mValues.length + " Values"); return 1000;}
				if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {if (mMode < 0) mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length + 16)]; else mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length + 10)]; playClick(); if (aChatReturn != null) aChatReturn.add("Averaging over: " + mValues.length + " Values"); return 1000;}
			}
			if (tCoords[1] >= PX_P[12] && tCoords[1] <= PX_P[14]) {
				if (tCoords[0] >= PX_P[ 9] && tCoords[0] <= PX_P[11]) {mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length - 1)]; playClick(); if (aChatReturn != null) aChatReturn.add(mValues.length < 2 ? "Averaging Disabled" : "Averaging over: " + mValues.length + " Values"); return 1000;}
				if (tCoords[0] >= PX_P[12] && tCoords[0] <= PX_P[14]) {mValues = new int[(int)UT.Code.bind_(1, MAX_AVERAGING_VALUES, mValues.length + 1)]; playClick(); if (aChatReturn != null) aChatReturn.add(mValues.length < 2 ? "Averaging Disabled" : "Averaging over: " + mValues.length + " Values"); return 1000;}
			}
			
			
			if (mMode < 0) {
				mMode &=~B[7];
				mMode = (byte)((mMode+1) % MODE_COUNT);
				mMode |= B[7];
			} else {
				mMode = (byte)((mMode+1) % MODE_COUNT);
			}
			
			if (mRedstone != 0) {mRedstone = 0; causeBlockUpdate();}
			updateClientData();
			return 10000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			mCurrentValue = mCurrentMax = mIndex = mDisplayedNumber = mSetNumber = mMode = mRedstone = 0;
			mValues = new int[] {0};
			causeBlockUpdate();
			updateClientData();
			return 10000;
		}
		return 0;
	}
	
	@Override
	public IIconContainer getCharacterIcon(int aIndex) {
		if (aIndex == 0) {
			switch(mMode & 127) {
			case MODE_FULL      : return BI.CHAR_EQUAL;
			case MODE_NOT_FULL  : return BI.CHAR_SMALLER;
			case MODE_GREATER   : return BI.CHAR_GREATER;
			case MODE_EQUAL     : return BI.CHAR_EQUAL;
			case MODE_SMALLER   : return BI.CHAR_SMALLER;
			case MODE_SCALE     : return BI.CHAR_SCALE;
			default: return mMode < 0 ? BI.CHAR_HEX : BI.decimalDigit(mDisplayedNumber, 4);
			}
		}
		if ((mMode & 127) == MODE_FULL || (mMode & 127) == MODE_NOT_FULL) {
			switch(aIndex) {
			case 1: return BI.CHAR_1;
			case 2: return BI.CHAR_0;
			case 3: return BI.CHAR_0;
			case 4: return BI.CHAR_PERCENT;
			}
		}
		if (aIndex == 5) return (mMode & 127) == MODE_PERCENT ? BI.CHAR_PERCENT : getSymbolIcon();
		return mMode < 0 ? BI.hexadecimalDigit(mDisplayedNumber, 4-aIndex) : BI.decimalDigit(mDisplayedNumber, 4-aIndex);
	}
	
	@Override
	public short[] getCharacterColor(int aIndex) {
		return aIndex == 5 && (mMode & 127) != MODE_PERCENT ? getSymbolColor() : (mMode & 127) == MODE_FULL || (mMode & 127) == MODE_NOT_FULL ? CA_RED_192 : CA_WHITE;
	}
	
	public long getTickRate() {
		return 1;
	}
	
	public abstract IIconContainer getSymbolIcon();
	public abstract short[] getSymbolColor();
	public abstract long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator);
	public abstract long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator);
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	public static final String[] METHODS = {"getval", "getmax"}, ARGS = {"void", "void"}, HELPS = {"gets the value the sensor is reading for the object it is connected to", "gets the maximum value the sensor has for the object it is connected to"};
	public static final Class<?>[] RETURNS = {int.class, int.class};
	
	@Override public String     getComputerizableName       (DelegatorTileEntity<TileEntity> aDelegator) {return "gt_sensor";}
	@Override public String[]   allComputerizableArgs       (DelegatorTileEntity<TileEntity> aDelegator) {return ARGS;}
	@Override public String[]   allComputerizableHelps      (DelegatorTileEntity<TileEntity> aDelegator) {return HELPS;}
	@Override public String[]   allComputerizableMethods    (DelegatorTileEntity<TileEntity> aDelegator) {return METHODS;}
	@Override public Class<?>[] allComputerizableReturns    (DelegatorTileEntity<TileEntity> aDelegator) {return RETURNS;}
	@Override public String     getComputerizableArgs       (DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex) {return ARGS[aFunctionIndex];}
	@Override public String     getComputerizableHelp       (DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex) {return HELPS[aFunctionIndex];}
	@Override public String     getComputerizableMethod     (DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex) {return METHODS[aFunctionIndex];}
	@Override public Class<?>   getComputerizableReturn     (DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex) {return RETURNS[aFunctionIndex];}
	
	@Override
	public Object[] callComputerizableMethod(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex, Object[] aArguments) {
		return new Object[] {aFunctionIndex == 1 ? mCurrentMax : mCurrentValue};
	}
}
