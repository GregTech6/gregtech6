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

package gregtech.tileentity.energy.transformers;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_HasMultiBlockMachineRelevantData;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.tool.BlockLongDistWire;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;

public class MultiTileEntityLongDistanceTransformer extends TileEntityBase09FacingSingle implements IMTE_HasMultiBlockMachineRelevantData, ITileEntityCanDelegate, ITileEntityMachineBlockUpdateable, ITileEntityRunningActively, ITileEntityEnergyElectricityAcceptor, ITileEntitySwitchableOnOff {
	protected boolean mWasteEnergy = F, mStopped = F, mActive = F;
	protected long mInput = 0, mOutput = 0, mThroughput = 0, mActiveData = 0, mDistance = 0;
	protected byte mActiveState = 0;
	protected TagData mEnergyTypeAccepted = TD.Energy.EU;
	protected TagData mEnergyTypeEmitted = TD.Energy.EU;
	protected MultiTileEntityLongDistanceTransformer mTarget = null, mSender = null;
	protected ChunkCoordinates mTargetPos = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_WASTE_ENERGY)) mWasteEnergy = aNBT.getBoolean(NBT_WASTE_ENERGY);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) {mActiveData = aNBT.getLong(NBT_ACTIVE_DATA);}
		if (aNBT.hasKey(NBT_INPUT)) {mInput = aNBT.getLong(NBT_INPUT);}
		if (aNBT.hasKey(NBT_OUTPUT)) {mOutput = aNBT.getLong(NBT_OUTPUT);}
		if (aNBT.hasKey(NBT_DISTANCE)) {mDistance = aNBT.getLong(NBT_DISTANCE);}
		if (aNBT.hasKey(NBT_THROUGHPUT)) {mThroughput = aNBT.getLong(NBT_THROUGHPUT);}
		if (aNBT.hasKey(NBT_TARGET)) {mTargetPos = new ChunkCoordinates(UT.Code.bindInt(aNBT.getLong(NBT_TARGET_X)), UT.Code.bindInt(aNBT.getLong(NBT_TARGET_Y)), UT.Code.bindInt(aNBT.getLong(NBT_TARGET_Z)));}
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mTargetPos != null && mTarget != this) {
		UT.NBT.setBoolean(aNBT, NBT_TARGET, T);
		UT.NBT.setNumber(aNBT, NBT_TARGET_X, mTargetPos.posX);
		UT.NBT.setNumber(aNBT, NBT_TARGET_Y, mTargetPos.posY);
		UT.NBT.setNumber(aNBT, NBT_TARGET_Z, mTargetPos.posZ);
		UT.NBT.setNumber(aNBT, NBT_DISTANCE, mDistance);
		UT.NBT.setNumber(aNBT, NBT_THROUGHPUT, mThroughput);
		}
		UT.NBT.setNumber(aNBT, NBT_ACTIVE_DATA, mActiveData);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, mEnergyTypeEmitted, getLocalisedInputSide(), getLocalisedOutputSide());
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_softhammer)) {
			scanWires(F);
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (mSender != null && !mSender.isDead() && mSender.mTarget == this) {
					aChatReturn.add("Is the Target");
					aChatReturn.add("Sender is at: X: " + mSender.xCoord + " Y: " + mSender.yCoord + " Z: " + mSender.zCoord);
				} else {
					aChatReturn.add(checkTarget() ? "Has Target" : "Has no loaded Target");
					if (mTargetPos != null) aChatReturn.add("Target should be around: X: " + mTargetPos.posX + " Y: " + mTargetPos.posY + " Z: " + mTargetPos.posZ);
				}
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		byte tActiveState = mActiveState;
		if (mStopped) {
			mActiveState = 0;
		} else if ((mTarget != null && (mTarget.mSender == null || mTarget.mSender.isDead() || mTarget.mSender.mTarget == null || mTarget.mSender.mTarget.isDead())) && (mTargetPos != null && (mTarget == null || mTarget.isDead() || !worldObj.blockExists(mTargetPos.posX, mTargetPos.posY, mTargetPos.posZ)))) {
			mActiveState = 3;
		} else {
			mActiveData <<= 1;
			if (mActive) mActiveData |= 1;
			if (mActiveData == ~0L) {
				mActiveState = 1;
			} else if (mActiveData == 0) {
				mActiveState = 0;
			} else {
				mActiveState = 2;
			}
		}
		mActive = F;
		return tActiveState != mActiveState || super.onTickCheck(aTimer);
	}
	
	public boolean checkTarget() {
		if (mStopped || isClientSide()) return F;
		if (mTargetPos == null || mDistance <= 0 || mThroughput <= 0) {
			scanWires(F);
		} else if (mTarget == null || mTarget.isDead()) {
			mTarget = null;
			if (worldObj.blockExists(mTargetPos.posX, mTargetPos.posY, mTargetPos.posZ)) {
				TileEntity tTileEntity = WD.te(worldObj, mTargetPos, T);
				if (tTileEntity instanceof MultiTileEntityLongDistanceTransformer) {
					mTarget = (MultiTileEntityLongDistanceTransformer)tTileEntity;
				} else {
					if (tTileEntity != null) mTargetPos = null;
				}
			}
		}
		if (mTarget == null || mTarget == this) return F;
		if (mTarget.mSender == null || mTarget.mSender.isDead() || mTarget.mSender.mTarget == null || mTarget.mSender.mTarget.isDead()) mTarget.mSender = this;
		return mTarget.mSender == this;
	}
	
	private void scanWires(boolean aBurnWires) {
		if (mSender != null && !mSender.isDead() && mSender.mTarget == this) return;
		mIgnoreUnloadedChunks = F;
		mTargetPos = getCoords();
		mTarget = this;
		mSender = null;
		mDistance = 0;
		mThroughput = 0;
		Block aBlock = getBlockAtSide(OPOS[mFacing]);
		byte aMetaData = getMetaDataAtSide(OPOS[mFacing]);
		if (aBlock instanceof BlockLongDistWire) {
			mThroughput = VMAX[((BlockLongDistWire)aBlock).mTiers[aMetaData]];
			HashSetNoNulls<ChunkCoordinates>
			tNewChecks  = new HashSetNoNulls<>(),
			tOldChecks  = new HashSetNoNulls<>(F, getCoords()),
			tToCheck    = new HashSetNoNulls<>(F, getOffsetN(mFacing, 1)),
			tWires      = new HashSetNoNulls<>();
			
			mDistance = -1;
			while (!tToCheck.isEmpty()) {
				mDistance++;
				for (ChunkCoordinates aCoords : tToCheck) {
					if (getBlock(aCoords) == aBlock && getMetaData(aCoords) == aMetaData) {
						tWires.add(aCoords);
						ChunkCoordinates tCoords;
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX + 1, aCoords.posY, aCoords.posZ))) tNewChecks.add(tCoords);
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX - 1, aCoords.posY, aCoords.posZ))) tNewChecks.add(tCoords);
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY + 1, aCoords.posZ))) tNewChecks.add(tCoords);
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY - 1, aCoords.posZ))) tNewChecks.add(tCoords);
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY, aCoords.posZ + 1))) tNewChecks.add(tCoords);
						if (tOldChecks.add(tCoords = new ChunkCoordinates(aCoords.posX, aCoords.posY, aCoords.posZ - 1))) tNewChecks.add(tCoords);
						if (aBurnWires) {
							WD.burn(worldObj, aCoords, T, F);
							worldObj.setBlock(aCoords.posX, aCoords.posY, aCoords.posZ, Blocks.fire, 0, 3);
						}
					} else {
						TileEntity tTileEntity = getTileEntity(aCoords);
						if (tTileEntity != this && tTileEntity instanceof MultiTileEntityLongDistanceTransformer) {
							if (tWires.contains(((MultiTileEntityLongDistanceTransformer)tTileEntity).getOffset(((MultiTileEntityLongDistanceTransformer)tTileEntity).mFacing, 1))) {
								mTarget = (MultiTileEntityLongDistanceTransformer)tTileEntity;
								mTargetPos = mTarget.getCoords();
								mIgnoreUnloadedChunks = T;
								return;
							}
							tOldChecks.remove(aCoords);
						}
					}
				}
				tToCheck.clear();
				tToCheck.addAll(tNewChecks);
				tNewChecks.clear();
			}
		}
		mIgnoreUnloadedChunks = T;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mStopped) return 0;
		byte aSign = 1;
		if (aSize < 0) {aSize = -aSize; aSign = -1;}
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overcharge(aSize, aEnergyType);
			return aAmount;
		}
		if (!checkTarget()) return 0;
		if (aSize > mThroughput) {
			if (aDoInject) scanWires(T);
			return aAmount;
		}
		aSize = Math.max(0, aSize - Math.max(64, mDistance/8));
		if (aSize <= 0 || aSize < mTarget.getEnergySizeOutputMin(mTarget.mEnergyTypeEmitted, aSide)) {
			return 0;
		}
		if (aSize > mTarget.getEnergySizeOutputMax(mTarget.mEnergyTypeEmitted, aSide)) {
			if (aDoInject) mTarget.overcharge(aSize, aEnergyType);
			return aAmount;
		}
		if (aDoInject) {
			long tConsumed = ITileEntityEnergy.Util.emitEnergyToNetwork(mTarget.mEnergyTypeEmitted, aSize*aSign, aAmount, mTarget);
			mTarget.mActive = mActive = (mActive || tConsumed > 0);
			return tConsumed;
		}
		return aAmount;
	}
	
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || checkTarget()) && (SIDES_INVALID[aSide] || isInput (aSide)) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                                    (SIDES_INVALID[aSide] || isOutput(aSide)) && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mEnergyTypeAccepted, mEnergyTypeEmitted);}
	
	@Override public double getDemandedEnergy() {return checkTarget() ? super.getDemandedEnergy() : 0;}
	
	// Stuff to Override
	
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mActive && ((mSender != null && !mSender.isDead() && mSender.mTarget == this) || (mTarget != null && !mTarget.isDead()));}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public byte getVisualData() {return mActiveState;}
	@Override public void setVisualData(byte aData) {mActiveState = aData;}
	
	public String getLocalisedInputSide () {return LH.get(LH.FACE_FRONT);}
	public String getLocalisedOutputSide() {return LH.get(LH.FACE_BACK);}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? aEnergyType == mEnergyTypeEmitted : aEnergyType == mEnergyTypeAccepted;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	public boolean isInput (byte aSide) {return aSide == mFacing;}
	public boolean isOutput(byte aSide) {return aSide == OPOS[mFacing];}
	
	@Override public void onCoordinateChange() {super.onCoordinateChange(); mTargetPos = null; mSender = null;}
	@Override public void onMachineBlockUpdate(ChunkCoordinates aCoords, Block aBlock, byte aMeta, boolean aRemoved) {if (aBlock instanceof BlockLongDistWire) {mTargetPos = null; mSender = null;}}
	@Override public boolean hasMultiBlockMachineRelevantData() {return T;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isExtender(byte aSide) {return F;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[mActiveState][aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_blinking/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_blinking/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_unloaded/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_unloaded/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/longdistancetransformer_electric/overlay_unloaded/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.longdistancetransformers.transformer_electric";}
}
