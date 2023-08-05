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

package gregtech.tileentity.energy.generators;

import gregapi.code.TagData;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.TD;
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
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

public class MultiTileEntityMagicFieldAbsorber extends TileEntityBase09FacingSingle implements ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff {
	protected boolean mStopped = F, mActive = F, mCheck = T;
	protected long mOutput = 64;
	protected TagData mEnergyTypeEmitted = TD.Energy.TU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
	}
	
	static {
		LH.add("gt.tooltip.magicenergyabsorber", "Generates Power from Magical Trophies on top of it.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + LH.get("gt.tooltip.magicenergyabsorber"));
		aList.add(LH.Chat.RED + LH.get(LH.ENERGY_OUTPUT) + ": " + LH.Chat.WHITE + mOutput + LH.Chat.RAINBOW + " ?U" + LH.Chat.WHITE + "/t");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && !mStopped) {
			if ((mCheck || mBlockUpdated || aTimer % 600 == 5)) {
				boolean tActive = mActive;
				mCheck = F;
				mActive = F;
				mOutput = 64;
				Block tBlock = getBlockAtSide(SIDE_TOP);
				if (tBlock == Blocks.dragon_egg) {
					mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.QU;
				} else if (tBlock == Blocks.skull) {
					mActive = T; mOutput = 1; mEnergyTypeEmitted = TD.Energy.TU; // I can't forsee this getting OP as heck. XD
				} else if (IL.TF_Trophy.equal(tBlock)) {
					switch(tBlock.getDamageValue(worldObj, xCoord, yCoord+1, zCoord)) {
					case  1: mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.KU; break; // Naga
					case  2: mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.QU; break; // Lich
					default: mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.HU; break; // Hydra
					case  3: mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.LU; break; // Ur-Ghast
					case  4: mActive = T; mOutput = 64; mEnergyTypeEmitted = TD.Energy.CU; break; // Snow Queen
					}
				}
				if (tActive != mActive) updateClientData();
			}
			
			if (mActive) {
				if (mEnergyTypeEmitted == TD.Energy.KU) {
					Util.emitEnergyToNetwork(mEnergyTypeEmitted, aTimer % 128 < 64 ? -mOutput : mOutput, 1, this);
				} else if (TD.Energy.ALL_SIZE_IRRELEVANT.contains(mEnergyTypeEmitted)) {
					Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, mOutput, this);
				} else {
					Util.emitEnergyToNetwork(mEnergyTypeEmitted, mOutput, 1, this);
				}
			}
		}
	}
	
	@Override public void setVisualData(byte aData) {mActive = ((aData & 1) != 0);}
	@Override public byte getVisualData() {return (byte)(mActive?1:0);}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting;}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.ALL_GT;}
	
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mActive;}
	@Override public boolean setStateOnOff(boolean aOnOff) {if (mStopped && aOnOff) mCheck = T; mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM_HORIZONTAL;}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = SIDES_TOP[aSide]?4:aSide==mFacing?SIDES_HORIZONTAL[aSide]?0:2:SIDES_HORIZONTAL[aSide]?1:3;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActive?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/colored/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/colored/side"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/colored/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/colored/top"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay/side"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay/top"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay_active/side_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay_active/side"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay_active/bottom_facing"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/magicenergyabsorber/overlay_active/top"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.magicenergyabsorber";}
}
