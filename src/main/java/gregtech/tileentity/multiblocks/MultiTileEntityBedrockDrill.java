/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.TagData;
import gregapi.data.*;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.multiblocks.*;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.StoneLayer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBedrockDrill extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy, IMultiBlockFluidHandler, IFluidHandler {
	public long mEnergy = 0;
	public int mType = 0;
	public TagData mEnergyTypeAccepted = TD.Energy.RU;
	public FluidTankGT mTank = new FluidTankGT(16000);
	public final List<OreDictMaterial> mList = new ArrayListNoNulls<>();
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mType = aNBT.getInteger(NBT_VALUE);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		mTank.readFromNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_VALUE, mType);
		mTank.writeToNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public boolean checkStructure2() {
		if (yCoord < 5) return F;
		mList.clear();
		boolean tSuccess = T, tBedrock = T, tOverride = F;
		
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
			Block tBlock = getBlockOffset(i, -5, j);
			if (tBlock == BlocksGT.oreBedrock) {
				OreDictMaterialStack tMaterial = BlocksGT.oreBedrock.getMaterialAtSide(worldObj, xCoord+i, yCoord-5, zCoord+j, SIDE_TOP);
				mList.add(tMaterial.mMaterial); mList.add(tMaterial.mMaterial);
			} else if (tBlock == BlocksGT.oreSmallBedrock) {
				OreDictMaterialStack tMaterial = BlocksGT.oreSmallBedrock.getMaterialAtSide(worldObj, xCoord+i, yCoord-5, zCoord+j, SIDE_TOP);
				mList.add(tMaterial.mMaterial);
			} else if (IL.HBM_Bedrock_Coltan.equal(tBlock)) {
				// These generate as single Blocks as far as I saw.
				mList.add(MT.OREMATS.Coltan);
				mList.add(MT.OREMATS.Coltan);
				mList.add(MT.OREMATS.Columbite);
				mList.add(MT.OREMATS.Tantalite);
				tOverride = T;
			} else if (IL.HBM_Bedrock_Oil.equal(tBlock)) {
				// There is giant blobs of these, so I gotta make sure to not produce too much.
				mList.add(MT.Oilshale);
				tOverride = T;
			} else if (!WD.bedrock(tBlock)) {
				tBedrock = F;
			}
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -4, j, 18103, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -3, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -2, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if ((i == 0) != (j == 0)) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -1, j, 18026, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			} else {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, -1, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			}
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i,  0, j, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
		}
		return tSuccess && (tBedrock || tOverride);
	}
	
	static {
		LH.add("gt.tooltip.multiblock.bedrockdrill.1", "3x3 Base of Bedrock Mining Drill Heads centered ontop Bedrock Ores");
		LH.add("gt.tooltip.multiblock.bedrockdrill.2", "Full 3x4x3 of Dense Titanium Walls ontop");
		LH.add("gt.tooltip.multiblock.bedrockdrill.3", "Main top-center inside the Titanium Tower facing upwards");
		LH.add("gt.tooltip.multiblock.bedrockdrill.4", "Requires Lubricant, Power and a 3x3 place on Bedrock(-Ore) to mine");
		LH.add("gt.tooltip.multiblock.bedrockdrill.5", "Also works on HBM's Bedrock Ores, even if it's not a full 3x3 of Bedrock");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN          + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE         + LH.get("gt.tooltip.multiblock.bedrockdrill.1"));
		aList.add(Chat.WHITE         + LH.get("gt.tooltip.multiblock.bedrockdrill.2"));
		aList.add(Chat.WHITE         + LH.get("gt.tooltip.multiblock.bedrockdrill.3"));
		aList.add(Chat.WHITE         + LH.get("gt.tooltip.multiblock.bedrockdrill.4"));
		if (IL.HBM_Bedrock_Coltan.exists() || IL.HBM_Bedrock_Oil.exists())
		aList.add(Chat.BLINKING_CYAN + LH.get("gt.tooltip.multiblock.bedrockdrill.5"));
		aList.add(Chat.GREEN         + LH.get(LH.ENERGY_INPUT) + ": " + Chat.WHITE + "1024 to 4096 " + mEnergyTypeAccepted.getLocalisedChatNameShort() + Chat.WHITE + "/t (up to 32768 " + mEnergyTypeAccepted.getLocalisedChatNameShort() + Chat.WHITE + "/t total)");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return aX >= xCoord - 1 && aY >= yCoord - 5 && aZ >= zCoord - 1 && aX <= xCoord + 1 && aY <= yCoord && aZ <= zCoord + 1;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (slotHas(0)) {
				ST.move(delegator(SIDE_TOP), getAdjacentInventory(SIDE_TOP));
			}
			if (mEnergy >= 32768 && !slotHas(0) && checkStructure(F) && mTank.drainAll(100)) {
				mEnergy -= 32768;
				// Switch Stone Type randomly. The plus 1 is for the Vanilla Stone case.
				if (rng(1000) == 0) mType = rng(BlocksGT.stones.length+(IL.EtFu_Deepslate_Cobble.exists() ? 2 : 1));
				// a 0-18 of 128 Chance to be an Ore.
				int tSelector = rng(128);
				if (tSelector < mList.size()) {
					// Select an Ore Material.
					OreDictMaterial tMaterial = (rng(32) == 0 ? UT.Code.select(mList.get(tSelector), mList.get(tSelector).mByProducts) : mList.get(tSelector));
					if (tMaterial == ANY.Hexorium || ANY.Hexorium.mToThis.contains(tMaterial)) {
						switch (rng(20)) {
						case  0: case  1: case  2: case  3: case  4: case  5: case  6: case  7: case  8: case  9: tMaterial = MT.HexoriumWhite; break;
						case 10: case 11: case 12: case 13: case 14: case 15: case 16: tMaterial = MT.HexoriumBlack; break;
						case 17: tMaterial = MT.HexoriumRed; break;
						case 18: tMaterial = MT.HexoriumGreen; break;
						case 19: tMaterial = MT.HexoriumBlue; break;
						}
					}
					// Create an Ore Block fitting to the Stone Types of this Dimension.
					if (worldObj.provider.dimensionId == DIM_NETHER) {
						// Netherrack Ore.
						slot(0, ST.make((Block)BlocksGT.oreBrokenNetherrack, 1, tMaterial.mID));
					} else if (WD.dimERE(worldObj)) {
						// Erebus Umberstone Ore.
						Object tBlock = BlocksGT.stoneToBrokenOres.get(new ItemStackContainer(IL.ERE_Umberstone.get(1)));
						slot(0, ST.make((Block)(tBlock instanceof Block ? tBlock : BlocksGT.oreBroken), 1, tMaterial.mID));
					} else if (WD.dimATUM(worldObj)) {
						// Atum Limestone Ore.
						Object tBlock = BlocksGT.stoneToBrokenOres.get(new ItemStackContainer(IL.ATUM_Limestone.get(1)));
						slot(0, ST.make((Block)(tBlock instanceof Block ? tBlock : BlocksGT.oreBroken), 1, tMaterial.mID));
					} else if (WD.dimBTL(worldObj)) {
						// Betweenlands Stone Ores.
						Object tBlock = BlocksGT.stoneToBrokenOres.get(new ItemStackContainer((mType%2==0?IL.BTL_Pitstone:IL.BTL_Betweenstone).get(1)));
						slot(0, ST.make((Block)(tBlock instanceof Block ? tBlock : BlocksGT.oreBroken), 1, tMaterial.mID));
					} else if (mType < BlocksGT.stones.length) {
						// This might be the Overworld or some Overworld alike Dimension.
						slot(0, ST.make((Block)BlocksGT.ores_broken[mType], 1, tMaterial.mID));
					}
					if (ST.invalid(slot(0)) && mType%2==0 && IL.EtFu_Deepslate_Cobble.exists()) {
						// Make Deepslate Ore 50% of the time.
						slot(0, ST.make((Block)StoneLayer.DEEPSLATE.mOreBroken, 1, tMaterial.mID));
					}
					if (ST.invalid(slot(0))) {
						// Make Vanilla Stone Ore, if nothing else applies.
						slot(0, ST.make((Block)BlocksGT.oreBroken, 1, tMaterial.mID));
					}
				} else {
					// Select a Stone to generate.
					if (rng(1000) == 0) {
						// 0.1% Chance to get Bedrock Dust. Only really useful for the Byproducts it has, and Rotarycraft.
						slot(0, OP.dust.mat(MT.Bedrock, 1));
					} else if (worldObj.provider.dimensionId == DIM_NETHER) {
						// Netherrack.
						slot(0, ST.make(Blocks.netherrack, 1, 0));
					} else if (WD.dimERE(worldObj)) {
						// Erebus Umberstone.
						slot(0, IL.ERE_Umbercobble.get(1));
					} else if (WD.dimATUM(worldObj)) {
						// Atum Limestone.
						slot(0, IL.ATUM_Limecobble.get(1));
					} else if (WD.dimBTL(worldObj)) {
						// Betweenlands Stones.
						slot(0, (mType%2==0?IL.BTL_Pitstone:IL.BTL_Betweenstone).get(1));
					} else if (mType < BlocksGT.stones.length) {
						// This might be the Overworld or some Overworld alike Dimension.
						slot(0, ST.make(BlocksGT.stones[mType], 1, 1));
					}
					if (ST.invalid(slot(0)) && mType%2==0 && IL.EtFu_Deepslate_Cobble.exists()) {
						// Make Deepslate 50% of the time.
						slot(0, IL.EtFu_Deepslate_Cobble.get(1));
					}
					if (ST.invalid(slot(0))) {
						// Make Cobble, if nothing else applies.
						slot(0, ST.make(Blocks.cobblestone, 1, 0));
					}
				}
			}
		}
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
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add(mTank.content("WARNING: NO LUBRICANT!!!"));
	}
	
	@Override public byte getDefaultSide() {return SIDE_UP;}
	@Override public boolean[] getValidSides() {return SIDES_TOP;}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mEnergy > 40000) return 0;
		aSize = Math.abs(aSize);
		if (!aDoInject) return aAmount;
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {explode(6); return aAmount;}
		mEnergy += aAmount * aSize;
		return aAmount;
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return 4096;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1024;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return 4096;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? 40000 : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return FluidsGT.LUBRICANT.contains(aFluidToFill.getFluid().getName()) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTank.AS_ARRAY;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return FluidsGT.LUBRICANT.contains(aFluidToFill.getFluid().getName()) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTank.AS_ARRAY;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	private static final int[] ACCESSIBLE_SLOTS = new int[] {0};
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.drill.bedrock";}
}
