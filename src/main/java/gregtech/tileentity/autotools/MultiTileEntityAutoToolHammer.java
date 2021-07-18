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

package gregtech.tileentity.autotools;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.TagData;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityAnvil;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.items.tools.early.GT_Tool_HardHammer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MultiTileEntityAutoToolHammer extends TileEntityBase09FacingSingle implements ITileEntityEnergy {
	protected boolean mPullingBack = F;
	protected byte mQuality = 2, mSendSound = 0;
	protected long mEnergy = 0, mInput = 32;
	protected TagData mEnergyTypeAccepted = TD.Energy.KU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_STATE)) mPullingBack = aNBT.getBoolean(NBT_STATE);
		if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_QUALITY)) mQuality = aNBT.getByte(NBT_QUALITY);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_STATE, mPullingBack);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_AUTOHAMMER));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_QUALITY)        + ": " + Chat.WHITE + mQuality);
		LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, null, LH.get(LH.FACE_BACK), null);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mPullingBack && mEnergy > 0) {
				DelegatorTileEntity<TileEntity> tD = getAdjacentTileEntity(mFacing);
				if (IBlockToolable.Util.onToolClick(TOOL_hammer, mEnergy * 10, mQuality, null, null, null, F, null, tD.mWorld, tD.mSideOfTileEntity, tD.mX, tD.mY, tD.mZ, 0.5F, 0.5F, 0.5F) > 0) {
					mSendSound = 1;
				} else {
					if (!(tD.mTileEntity instanceof ITileEntityAnvil) || !((ITileEntityAnvil)tD.mTileEntity).isAnvil(tD.mSideOfTileEntity)) {
						Block tBlock = tD.mWorld.getBlock(tD.mX, tD.mY, tD.mZ);
						byte tMetaData = (byte)tD.mWorld.getBlockMetadata(tD.mX, tD.mY, tD.mZ);
						
						if (GT_Tool_HardHammer.INSTANCE.isMinableBlock(tBlock, tMetaData)) {
							mSendSound = 2;
							if (tBlock.getHarvestLevel(tMetaData) <= mQuality) {
								float tHardness = tBlock.getBlockHardness(tD.mWorld, tD.mX, tD.mY, tD.mZ);
								if (tHardness >= 0 && tHardness * 50 <= mEnergy) {
									List<ItemStack> tDrops = tBlock.getDrops(tD.mWorld, tD.mX, tD.mY, tD.mZ, tMetaData, 0);
									GT_Tool_HardHammer.INSTANCE.convertBlockDrops(tDrops, null, null, tBlock, Long.MAX_VALUE, tD.mX, tD.mY, tD.mZ, tMetaData, 0, F, null);
									tD.mWorld.func_147480_a(tD.mX, tD.mY, tD.mZ, F);
									for (ItemStack tStack : tDrops) ST.drop(tD.mWorld, tD.mX+0.5, tD.mY+0.5, tD.mZ+0.5, tStack);
									mSendSound = 1;
								}
							}
						}
					}
				}
				mEnergy = 0;
			}
		} else {
			if (mSendSound != 0) {
				UT.Sounds.play(mSendSound == 1 ? SFX.MC_ANVIL_USE : SFX.MC_ANVIL_BREAK, 20, 1.0F, xCoord, yCoord, zCoord);
				mSendSound = 0;
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mSendSound != 0 || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		mSendSound = 0;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mSendSound = aData;
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (aSize > 0) {
			if (aDoInject) {
				mPullingBack = F;
				mEnergy += aSize * aAmount;
				if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) explode(UT.Code.tierMax(aSize));
			}
		} else {
			if (aDoInject) {
				mPullingBack = T;
			}
		}
		return aAmount;
	}
	
	@Override public byte getDefaultSide() {return SIDE_DOWN;}
	@Override public byte getVisualData() {return mSendSound;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == OPOS[mFacing] && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return mInput / 8;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mInput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.autotool.hammer";}
}
