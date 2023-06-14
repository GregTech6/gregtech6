/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class CoverData {
	public short mIDs[], mMetas[], mVisuals[], mValues[];
	public boolean mVisualsToSync[] = new boolean[] {F,F,F,F,F,F}, mStopped = F;
	public NBTTagCompound mNBTs[];
	public ICover mBehaviours[] = new ICover[6];
	public ITileEntityCoverable mTileEntity;
	
	public CoverData(ITileEntityCoverable aTileEntity) {
		mIDs = new short[6]; mMetas = new short[6]; mVisuals = new short[6]; mValues = new short[6]; mNBTs = new NBTTagCompound[6];
		mTileEntity = aTileEntity;
	}
	
	public CoverData(short[] aIDs, short[] aMetas, short[] aVisuals, short[] aValues, NBTTagCompound[] aNBTs, boolean aStopped, ITileEntityCoverable aTileEntity) {
		mVisuals = aVisuals; mValues = aValues; mNBTs = aNBTs;
		for (int i = 0; i < mNBTs.length;i++) if (mNBTs[i] != null && mNBTs[i].hasNoTags()) mNBTs[i] = null;
		setIDs(aIDs, aMetas);
		mStopped = aStopped;
		mTileEntity = aTileEntity;
		if (mTileEntity != null) try {for (byte tSide : ALL_SIDES_VALID) if (mBehaviours[tSide] != null) mBehaviours[tSide].onCoverLoaded(tSide, this);} catch(Throwable e) {e.printStackTrace(ERR); mTileEntity.setError("Cover Loaded:" + e);}
	}
	
	public CoverData(ITileEntityCoverable aTileEntity, NBTTagCompound aNBT) {
		this( new short[] {aNBT.getShort("a"), aNBT.getShort("b"), aNBT.getShort("c"), aNBT.getShort("d"), aNBT.getShort("e"), aNBT.getShort("f")}
			, new short[] {aNBT.getShort("g"), aNBT.getShort("h"), aNBT.getShort("i"), aNBT.getShort("j"), aNBT.getShort("k"), aNBT.getShort("l")}
			, new short[] {aNBT.getShort("m"), aNBT.getShort("n"), aNBT.getShort("o"), aNBT.getShort("p"), aNBT.getShort("q"), aNBT.getShort("r")}
			, new short[] {aNBT.getShort("0"), aNBT.getShort("1"), aNBT.getShort("2"), aNBT.getShort("3"), aNBT.getShort("4"), aNBT.getShort("5")}
			, new NBTTagCompound[] {aNBT.getCompoundTag("s"), aNBT.getCompoundTag("t"), aNBT.getCompoundTag("u"), aNBT.getCompoundTag("v"), aNBT.getCompoundTag("w"), aNBT.getCompoundTag("x")}
			, aNBT.getBoolean("y")
			, aTileEntity);
	}
	
	public NBTTagCompound writeToNBT() {return writeToNBT(UT.NBT.make(), T);}
	public NBTTagCompound writeToNBT(NBTTagCompound aNBT, boolean aIncludeVisuals) {
		byte i = 0;
		if (mIDs[  i] != 0) {
			aNBT.setShort("a", mIDs[i]);
			if (mMetas[i] != 0) aNBT.setShort("g", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("0", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("s", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("m", mVisuals[i]);
		}
		if (mIDs[++i] != 0) {
			aNBT.setShort("b", mIDs[i]);
			if (mMetas[i] != 0) aNBT.setShort("h", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("1", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("t", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("n", mVisuals[i]);
		}
		if (mIDs[++i] != 0) {
			aNBT.setShort("c", mIDs[i]);
			if (mMetas[i] != 0) aNBT.setShort("i", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("2", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("u", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("o", mVisuals[i]);
		}
		if (mIDs[++i] != 0) {
			aNBT.setShort("d", mIDs[i]);
			if (mMetas[i] != 0) aNBT.setShort("j", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("3", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("v", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("p", mVisuals[i]);
		}
		if (mIDs[++i] != 0) {
			aNBT.setShort("e", mIDs[i]);
			if (mMetas[i] != 0) aNBT.setShort("k", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("4", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("w", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("q", mVisuals[i]);
		}
		if (mIDs[++i] != 0) {
			aNBT.setShort("f", mIDs[i]); 
			if (mMetas[i] != 0) aNBT.setShort("l", mMetas[i]);
			if (mValues[i] != 0) aNBT.setShort("5", mValues[i]);
			if (mNBTs[i] != null && !mNBTs[i].hasNoTags()) aNBT.setTag("x", mNBTs[i]);
			if (mVisuals[i] != 0 && (aIncludeVisuals || (mBehaviours[i] != null && mBehaviours[i].needsVisualsSaved(i, this)))) aNBT.setShort("r", mVisuals[i]);
		}
		if (mStopped) aNBT.setBoolean("y", mStopped);
		return aNBT;
	}
	
	public CoverData setIDs(short[] aIDs, short[] aMetas) {
		mIDs = aIDs; mMetas = aMetas;
		for (byte tSide : ALL_SIDES_VALID) if (mIDs[tSide] == 0) mBehaviours[tSide] = null; else mBehaviours[tSide] = CoverRegistry.get(mIDs[tSide], mMetas[tSide]);
		return this;
	}
	
	public CoverData set(byte aSide, ItemStack aStack) {
		return aStack == null ? set(aSide, (short)0, (short)0, null) : set(aSide, ST.id(aStack), ST.meta_(aStack), aStack.getTagCompound());
	}
	
	public CoverData set(byte aSide, short aID, short aMeta, NBTTagCompound aNBT) {
		mIDs[aSide] = aID; mMetas[aSide] = aMeta;
		if (aID == 0) mBehaviours[aSide] = null; else mBehaviours[aSide] = CoverRegistry.get(aID, aMeta);
		mNBTs[aSide] = (NBTTagCompound)(aNBT==null||aNBT.hasNoTags()?null:aNBT.copy());
		return this;
	}
	
	public CoverData value(byte aSide, short aValue) {
		return value(aSide, aValue, F);
	}
	public CoverData value(byte aSide, short aValue, boolean aBlockUpdate) {
		if (mValues[aSide] != aValue) {
			mValues[aSide]  = aValue;
			if (aBlockUpdate) mTileEntity.sendBlockUpdateFromCover();
		}
		return this;
	}
	
	public CoverData visual(byte aSide, short aVisual) {
		return visual(aSide, aVisual, F);
	}
	public CoverData visual(byte aSide, short aVisual, boolean aBlockUpdate) {
		if (mVisuals[aSide] != aVisual) {
			mVisuals[aSide]  = aVisual;
			mVisualsToSync[aSide] = T;
			mTileEntity.updateCoverVisuals();
			if (aBlockUpdate) mTileEntity.sendBlockUpdateFromCover();
		}
		return this;
	}
	
	public boolean setStopped(boolean aStopped) {
		if (aStopped == mStopped) return F;
		mStopped = aStopped;
		for (byte tSide : ALL_SIDES_VALID) if (mBehaviours[tSide] != null) mBehaviours[tSide].onStoppedUpdate(tSide, this, mStopped);
		return T;
	}
	
	public boolean onBlockUpdate() {
		for (byte tSide : ALL_SIDES_VALID) if (mBehaviours[tSide] != null) mBehaviours[tSide].onBlockUpdate(tSide, this);
		return T;
	}
	
	public ItemStack getCoverItem(byte aSide) {
		return mIDs[aSide] == 0 ? null : mBehaviours[aSide] == null ? ST.make(mIDs[aSide], 1, mMetas[aSide], mNBTs[aSide]==null||mNBTs[aSide].hasNoTags()?null:mNBTs[aSide]) : mBehaviours[aSide].getCoverItem(aSide, this);
	}
	
	public DelegatorTileEntity<ITileEntityCoverable> delegator(byte aSide) {
		return new DelegatorTileEntity<>(mTileEntity, aSide);
	}
	
	public boolean requiresSync() {
		return UT.Code.containsBoolean(T, mVisualsToSync);
	}
	
	public void resetSync() {
		for (int i = 0; i < mVisualsToSync.length; i++) mVisualsToSync[i] = F;
	}
	
	public void tickPre (long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		try {for (byte tSide : ALL_SIDES_VALID) if (mBehaviours[tSide] != null) mBehaviours[tSide].onTickPre (tSide, this, aTimer, aIsServerSide, aReceivedBlockUpdate, aReceivedInventoryUpdate);} catch(Throwable e) {e.printStackTrace(ERR); mTileEntity.setError("Cover Pre Tick - "  + (aIsServerSide?"Serverside: ":"Clientside: ") + e);}
	}
	
	public void tickPost(long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		try {for (byte tSide : ALL_SIDES_VALID) if (mBehaviours[tSide] != null) mBehaviours[tSide].onTickPost(tSide, this, aTimer, aIsServerSide, aReceivedBlockUpdate, aReceivedInventoryUpdate);} catch(Throwable e) {e.printStackTrace(ERR); mTileEntity.setError("Cover Post Tick - " + (aIsServerSide?"Serverside: ":"Clientside: ") + e);}
	}
	
	public AxisAlignedBB box(double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {return AxisAlignedBB.getBoundingBox(mTileEntity.getX()+aMinX, mTileEntity.getY()+aMinY, mTileEntity.getZ()+aMinZ, mTileEntity.getX()+aMaxX, mTileEntity.getY()+aMaxY, mTileEntity.getZ()+aMaxZ);}
	public AxisAlignedBB box(double[] aBox) {return AxisAlignedBB.getBoundingBox(mTileEntity.getX()+aBox[0], mTileEntity.getY()+aBox[1], mTileEntity.getZ()+aBox[2], mTileEntity.getX()+aBox[3], mTileEntity.getY()+aBox[4], mTileEntity.getZ()+aBox[5]);}
	public AxisAlignedBB box(float[] aBox) {return AxisAlignedBB.getBoundingBox(mTileEntity.getX()+aBox[0], mTileEntity.getY()+aBox[1], mTileEntity.getZ()+aBox[2], mTileEntity.getX()+aBox[3], mTileEntity.getY()+aBox[4], mTileEntity.getZ()+aBox[5]);}
	public AxisAlignedBB box() {return AxisAlignedBB.getBoundingBox(mTileEntity.getX(), mTileEntity.getY(), mTileEntity.getZ(), mTileEntity.getX()+1, mTileEntity.getY()+1, mTileEntity.getZ()+1);}
	
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {
		AxisAlignedBB tBox = box(aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, double[] aBox) {
		AxisAlignedBB tBox = box(aBox[0], aBox[1], aBox[2], aBox[3], aBox[4], aBox[5]);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, float[] aBox) {
		AxisAlignedBB tBox = box(aBox[0], aBox[1], aBox[2], aBox[3], aBox[4], aBox[5]);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList) {
		AxisAlignedBB tBox = box(0, 0, 0, 1, 1, 1);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aBox, AxisAlignedBB aAABB, List<AxisAlignedBB> aList) {
		return aBox != null && aBox.intersectsWith(aAABB) && aList.add(aBox);
	}
}
