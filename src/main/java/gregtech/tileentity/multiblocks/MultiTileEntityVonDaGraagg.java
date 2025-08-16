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

import gregapi.GT_API_Proxy;
import gregapi.block.metatype.BlockStones;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.tileentity.ITileEntityMobSpawnInhibitor;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityVonDaGraagg extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityMobSpawnInhibitor, IMultiBlockEnergy {
	public long mEnergy = 0, mCurrentRange = 256;
	public TagData mEnergyTypeAccepted = TD.Energy.EU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
	}
	
	@Override
	public boolean checkStructure2(ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord-1, tZ = getOffsetZN(mFacing, 2);
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;
			
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.largeboiler.1", "TODO");
		LH.add("gt.tooltip.multiblock.largeboiler.2", "TODO");
		LH.add("gt.tooltip.multiblock.largeboiler.3", "TODO");
		LH.add("gt.tooltip.multiblock.largeboiler.4", "TODO");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.von.da.graag.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.von.da.graag.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.von.da.graag.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.von.da.graag.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		// TODO actual dimensions of Structure
		int tX = getOffsetXN(mFacing, 2), tY = yCoord-1, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 2 && aY >= tY && aZ >= tZ - 2 && aX <= tX + 2 && aY <= tY + 8 && aZ <= tZ + 2;
	}
	
	private boolean mHasToAddToList = T;
	@Override public void onUnregisterInhibitor() {mHasToAddToList = T;}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.MOB_SPAWN_INHIBITORS.remove(this);
		onUnregisterInhibitor();
	}
	
	@Override
	public boolean inhibitMobSpawn(LivingSpawnEvent.CheckSpawn aEvent, World aWorld, int aX, int aY, int aZ) {
		if (mCurrentRange <= 0 || aWorld != worldObj || Math.abs(aX - xCoord) > mCurrentRange || Math.abs(aZ - zCoord) > mCurrentRange) return F;
		// Allow wild Mobs to spawn on Mossy Cobblestone.
		for (int i = -5; i <= 5; i++) {
			Block tBlock = aWorld.getBlock(aX, aY+i, aZ);
			if (tBlock == Blocks.mossy_cobblestone) return F;
			if (tBlock instanceof BlockStones && aWorld.getBlockMetadata(aX, aY+i, aZ) == BlockStones.MCOBL) return F;
		}
		return T;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mHasToAddToList) {
				GT_API_Proxy.MOB_SPAWN_INHIBITORS.add(this);
				mHasToAddToList = F;
			}
			mCurrentRange = UT.Code.bind8(mStructureOkay ? mEnergy / 16 : 0);
			mEnergy = 0;
		}
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add("Square Radius: " + mCurrentRange + "m");
	}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {mEnergy += Math.abs(aAmount * aSize);} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return 4096-mEnergy;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return  256;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return 4096;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? 4096 : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.von.da.graagg";}
}
