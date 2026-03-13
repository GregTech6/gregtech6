/**
 * Copyright (c) 2026 GregTech-6 Team
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Normal;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MultiTileEntityAutoToolMiner extends TileEntityBase09FacingSingle implements ITileEntityEnergyElectricityAcceptor, ITileEntitySwitchableOnOff {
	private static final int SLOT_TOOL = 0;
	private static final int SLOT_OUTPUT_START = 1;
	private static final int SLOT_OUTPUT_END = 9;
	private static final int[] SLOTS_ALL = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	private static final int[] SLOTS_OUTPUT = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	private static final int SCAN_BUDGET_PER_TICK = 512;

	private static final String NBT_RANGE_INTERNAL = "gt.miner.range";
	private static final String NBT_CHANCE_INTERNAL = "gt.miner.chance";
	private static final String NBT_SPEED_INTERNAL = "gt.miner.speedmult";
	private static final int ENERGY_MODE_TICKS = 40;
	private static final int ENERGY_MODE_BOOST_BPS = 1800;
	private static final int ENERGY_BUFFER_BONUS_MAX = 2000;
	private static final int HU_PROGRESS_MULTIPLIER = 2;
	private static final int KU_PROGRESS_MULTIPLIER = 4;

	public boolean mStopped = F;
	public long mEnergy = 0, mInput = 32;
	public long mProgress = 0, mProgressMax = 0;
	public int mRange = 4, mBaseChance = 5000;
	public int mSpeedMultiplier = 16;
	public int mTargetX = 0, mTargetY = 0, mTargetZ = 0;
	public int mScanDX = 0, mScanDZ = 0, mScanY = 0;
	public boolean mScanStarted = F;
	public TagData mEnergyTypeAccepted = TD.Energy.EU;
	public int mEUInputTicks = 0, mHUInputTicks = 0, mKUInputTicks = 0;
	public long oEnergy = -1, oProgress = -1, oProgressMax = -1;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mProgress = aNBT.getLong(NBT_PROGRESS);
		mProgressMax = aNBT.getLong(NBT_PROGRESS + ".max");
		if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		if (aNBT.hasKey(NBT_RANGE_INTERNAL)) mRange = (int)UT.Code.bind(1, 16, aNBT.getInteger(NBT_RANGE_INTERNAL));
		if (aNBT.hasKey(NBT_CHANCE_INTERNAL)) mBaseChance = (int)UT.Code.bind(0, 10000, aNBT.getInteger(NBT_CHANCE_INTERNAL));
		if (aNBT.hasKey(NBT_SPEED_INTERNAL)) mSpeedMultiplier = (int)UT.Code.bind(1, 64, aNBT.getInteger(NBT_SPEED_INTERNAL));
		if (aNBT.hasKey(NBT_TARGET_X) && aNBT.hasKey(NBT_TARGET_Y) && aNBT.hasKey(NBT_TARGET_Z)) {
			mTargetX = aNBT.getInteger(NBT_TARGET_X);
			mTargetY = aNBT.getInteger(NBT_TARGET_Y);
			mTargetZ = aNBT.getInteger(NBT_TARGET_Z);
		}
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS, mProgress);
		UT.NBT.setNumber(aNBT, NBT_PROGRESS + ".max", mProgressMax);
		aNBT.setInteger(NBT_TARGET_X, mTargetX);
		aNBT.setInteger(NBT_TARGET_Y, mTargetY);
		aNBT.setInteger(NBT_TARGET_Z, mTargetZ);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + "Mines Ores In Range With Inserted GT Tool");
		aList.add(Chat.CYAN + "Base Success: " + Chat.WHITE + mBaseChance + "/10000");
		aList.add(Chat.CYAN + "Range: " + Chat.WHITE + mRange);
		aList.add(Chat.CYAN + "Time Multiplier: " + Chat.WHITE + "x" + mSpeedMultiplier);
		aList.add(Chat.CYAN + "EU/HU/KU Each Active: " + Chat.WHITE + "Chance +18% Base");
		aList.add(Chat.CYAN + "HU Input (Bottom): " + Chat.WHITE + "Progress Energy x" + HU_PROGRESS_MULTIPLIER);
		aList.add(Chat.CYAN + "KU Input (Back): " + Chat.WHITE + "Progress Energy x" + KU_PROGRESS_MULTIPLIER);
		aList.add(Chat.CYAN + "Tool Durability Is Consumed On Success Only");
		LH.addEnergyToolTips(this, aList, TD.Energy.EU, null, LH.get(LH.FACE_ANY), null);
		LH.addEnergyToolTips(this, aList, TD.Energy.HU, null, LH.get(LH.FACE_BOTTOM), null);
		LH.addEnergyToolTips(this, aList, TD.Energy.KU, null, LH.get(LH.FACE_BACK), null);
		super.addToolTips(aList, aStack, aF3_H);
	}

	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (!aIsServerSide) return;
		if (mEUInputTicks > 0) mEUInputTicks--;
		if (mHUInputTicks > 0) mHUInputTicks--;
		if (mKUInputTicks > 0) mKUInputTicks--;

		if (slotHas(SLOT_OUTPUT_START) || slotHas(SLOT_OUTPUT_START + 1) || slotHas(SLOT_OUTPUT_START + 2) || slotHas(SLOT_OUTPUT_START + 3) || slotHas(SLOT_OUTPUT_START + 4) || slotHas(SLOT_OUTPUT_START + 5) || slotHas(SLOT_OUTPUT_START + 6) || slotHas(SLOT_OUTPUT_START + 7) || slotHas(SLOT_OUTPUT_END)) {
			ST.move(delegator(SIDE_TOP), getAdjacentInventory(SIDE_TOP));
		}

		if (mStopped) return;

		ItemStack tToolStack = slot(SLOT_TOOL);
		if (isDrillTool(tToolStack) && !isToolUsable(tToolStack)) {
			mStopped = T;
			mProgress = 0;
			mProgressMax = 0;
			return;
		}
		MultiItemTool tTool = getMiningTool(tToolStack);
		if (tTool == null) {
			mProgress = 0;
			mProgressMax = 0;
			return;
		}

		if (!hasValidTarget(tTool, tToolStack)) {
			if (!findNextTarget(tTool, tToolStack)) {
				mProgress = 0;
				mProgressMax = 0;
				return;
			}
			mProgress = 0;
			mProgressMax = 0;
		}

		if (mProgressMax <= 0) {
			Block tBlock = worldObj.getBlock(mTargetX, mTargetY, mTargetZ);
			int tMeta = worldObj.getBlockMetadata(mTargetX, mTargetY, mTargetZ);
			mProgressMax = calcMiningTicks(tTool, tToolStack, tBlock, tMeta);
		}

		if (mEnergy < mInput) return;
		mEnergy -= mInput;
		mProgress++;

		if (mProgress < mProgressMax) return;

		tryMineTarget(tTool, tToolStack);
		mProgress = 0;
		mProgressMax = 0;
	}

	private MultiItemTool getMiningTool(ItemStack aStack) {
		if (ST.invalid(aStack)) return null;
		if (!(aStack.getItem() instanceof MultiItemTool)) return null;
		if (ToolsGT.contains(TOOL_hammer, aStack)) return null;
		MultiItemTool tTool = (MultiItemTool)aStack.getItem();
		IToolStats tStats = tTool.getToolStats(aStack);
		if (tStats == null || !tStats.isMiningTool()) return null;
		if (!canMineOres(tTool, tStats, aStack)) return null;
		if (!tTool.isItemStackUsable(aStack)) return null;
		return tTool;
	}

	private boolean isToolUsable(ItemStack aStack) {
		if (ST.invalid(aStack)) return F;
		if (!(aStack.getItem() instanceof MultiItemTool)) return F;
		return ((MultiItemTool)aStack.getItem()).isItemStackUsable(aStack);
	}

	private boolean isDrillTool(ItemStack aStack) {
		if (ST.invalid(aStack)) return F;
		if (!(aStack.getItem() instanceof MultiItemTool)) return F;
		if (ToolsGT.contains(TOOL_drill, aStack)) return T;
		IToolStats tStats = ((MultiItemTool)aStack.getItem()).getToolStats(aStack);
		if (tStats == null) return F;
		String tName = tStats.getClass().getName().toLowerCase();
		return tName.contains("drill");
	}

	private boolean canMineOres(MultiItemTool aTool, IToolStats aStats, ItemStack aStack) {
		if (aStats.isMinableBlock(Blocks.iron_ore, (byte)0)) return T;
		return aTool.getDigSpeed(aStack, Blocks.iron_ore, 0) > 0;
	}

	private boolean hasValidTarget(MultiItemTool aTool, ItemStack aToolStack) {
		if (!worldObj.blockExists(mTargetX, mTargetY, mTargetZ)) return F;
		Block tBlock = worldObj.getBlock(mTargetX, mTargetY, mTargetZ);
		int tMeta = worldObj.getBlockMetadata(mTargetX, mTargetY, mTargetZ);
		if (!isOreBlock(tBlock)) return F;
		return aTool.getDigSpeed(aToolStack, tBlock, tMeta) > 0;
	}

	private boolean findNextTarget(MultiItemTool aTool, ItemStack aToolStack) {
		int tMaxY = Math.max(1, worldObj.getActualHeight());
		if (!mScanStarted) resetScanCursor();

		for (int tChecked = 0; tChecked < SCAN_BUDGET_PER_TICK; tChecked++) {
			if (mScanDX > mRange) {
				resetScanCursor();
				return F;
			}

			int tX = xCoord + mScanDX;
			int tY = mScanY;
			int tZ = zCoord + mScanDZ;

			if (worldObj.blockExists(tX, tY, tZ)) {
				Block tBlock = worldObj.getBlock(tX, tY, tZ);
				if (isOreBlock(tBlock)) {
					int tMeta = worldObj.getBlockMetadata(tX, tY, tZ);
					if (aTool.getDigSpeed(aToolStack, tBlock, tMeta) > 0) {
						mTargetX = tX;
						mTargetY = tY;
						mTargetZ = tZ;
						advanceScanCursor(tMaxY);
						return T;
					}
				}
			}

			advanceScanCursor(tMaxY);
		}
		return F;
	}

	private void resetScanCursor() {
		mScanDX = -mRange;
		mScanDZ = -mRange;
		mScanY = 0;
		mScanStarted = T;
	}

	private void advanceScanCursor(int aMaxY) {
		mScanY++;
		if (mScanY < aMaxY) return;
		mScanY = 0;
		mScanDZ++;
		if (mScanDZ <= mRange) return;
		mScanDZ = -mRange;
		mScanDX++;
	}

	private boolean isOreBlock(Block aBlock) {
		if (aBlock == NB || WD.bedrock(aBlock)) return F;
		String tClassName = aBlock.getClass().getName();
		if (tClassName != null && tClassName.toLowerCase().contains("ore")) return T;
		String tRegName = ST.regName(aBlock);
		return tRegName != null && tRegName.toLowerCase().contains("ore");
	}

	private long calcMiningTicks(MultiItemTool aTool, ItemStack aToolStack, Block aBlock, int aMeta) {
		float tHardness = aBlock.getBlockHardness(worldObj, mTargetX, mTargetY, mTargetZ);
		if (tHardness < 0) return 20;
		float tDigSpeed = Math.max(0.05F, aTool.getDigSpeed(aToolStack, aBlock, aMeta));
		if (isDrillTool(aToolStack)) tDigSpeed *= 2.0F;
		long tBaseTicks = Math.max(1L, UT.Code.roundUp((30.0D * tHardness) / tDigSpeed));
		return Math.max(1L, tBaseTicks * mSpeedMultiplier);
	}

	private void tryMineTarget(MultiItemTool aTool, ItemStack aToolStack) {
		if (!worldObj.blockExists(mTargetX, mTargetY, mTargetZ)) return;
		Block tBlock = worldObj.getBlock(mTargetX, mTargetY, mTargetZ);
		if (!isOreBlock(tBlock)) return;
		int tMeta = worldObj.getBlockMetadata(mTargetX, mTargetY, mTargetZ);

		List<ItemStack> tDrops = new ArrayList<>(tBlock.getDrops(worldObj, mTargetX, mTargetY, mTargetZ, tMeta, 0));
		if (tDrops.isEmpty()) return;
		if (!canFitAll(tDrops)) return;

		int tChance = getEffectiveChance();
		if (rng(10000) >= tChance) return;

		worldObj.func_147480_a(mTargetX, mTargetY, mTargetZ, F);
		for (ItemStack tDrop : tDrops) addToOutput(tDrop);

		long tDamage = getToolDamageCost(aTool, aToolStack, tBlock);
		aTool.doDamage(aToolStack, tDamage, null, T);
		if (!aTool.isItemStackUsable(aToolStack)) {
			if (isDrillTool(aToolStack)) {
				mStopped = T;
			} else {
				slotKill(SLOT_TOOL);
			}
		}
	}

	private int getEffectiveChance() {
		int tEnergyBonus = (int)UT.Code.bind(0, ENERGY_BUFFER_BONUS_MAX, (int)((mEnergy * ENERGY_BUFFER_BONUS_MAX) / Math.max(1L, mInput * 200L)));
		int tModeBoostBps = 0;
		if (mEUInputTicks > 0) tModeBoostBps += ENERGY_MODE_BOOST_BPS;
		if (mHUInputTicks > 0) tModeBoostBps += ENERGY_MODE_BOOST_BPS;
		if (mKUInputTicks > 0) tModeBoostBps += ENERGY_MODE_BOOST_BPS;
		int tChance = mBaseChance + tEnergyBonus + (int)((mBaseChance * (long)tModeBoostBps) / 10000L);
		return (int)UT.Code.bind(0, 10000, tChance);
	}

	private long getToolDamageCost(MultiItemTool aTool, ItemStack aToolStack, Block aBlock) {
		IToolStats tStats = aTool.getToolStats(aToolStack);
		if (tStats == null) return 100;
		double tHardness = Math.max(0.1D, aBlock.getBlockHardness(worldObj, mTargetX, mTargetY, mTargetZ));
		return Math.max(1L, UT.Code.roundUp(tStats.getToolDamagePerBlockBreak() * tHardness));
	}

	private boolean canFitAll(List<ItemStack> aDrops) {
		ItemStack[] tVirtual = new ItemStack[SLOT_OUTPUT_END - SLOT_OUTPUT_START + 1];
		for (int i = SLOT_OUTPUT_START; i <= SLOT_OUTPUT_END; i++) {
			ItemStack tStack = slot(i);
			tVirtual[i - SLOT_OUTPUT_START] = ST.valid(tStack) ? ST.copy(tStack) : null;
		}
		for (ItemStack tDrop : aDrops) {
			if (ST.invalid(tDrop)) continue;
			if (!addToVirtual(tVirtual, ST.copy(tDrop))) return F;
		}
		return T;
	}

	private boolean addToVirtual(ItemStack[] aInventory, ItemStack aStack) {
		if (ST.invalid(aStack)) return T;
		for (int i = 0; i < aInventory.length; i++) {
			ItemStack tStack = aInventory[i];
			if (ST.invalid(tStack)) continue;
			if (ST.equal(tStack, aStack, F)) {
				int tLimit = Math.min(getInventoryStackLimit(), tStack.getMaxStackSize());
				int tRoom = tLimit - tStack.stackSize;
				if (tRoom <= 0) continue;
				int tMove = Math.min(tRoom, aStack.stackSize);
				tStack.stackSize += tMove;
				aStack.stackSize -= tMove;
				if (aStack.stackSize <= 0) return T;
			}
		}
		for (int i = 0; i < aInventory.length; i++) {
			if (ST.invalid(aInventory[i])) {
				aInventory[i] = OM.get_(aStack);
				return T;
			}
		}
		return F;
	}

	private void addToOutput(ItemStack aStack) {
		if (ST.invalid(aStack)) return;
		ItemStack tRemain = OM.get_(aStack);
		for (int i = SLOT_OUTPUT_START; i <= SLOT_OUTPUT_END; i++) {
			ItemStack tSlot = slot(i);
			if (ST.invalid(tSlot) || !ST.equal(tSlot, tRemain, F)) continue;
			int tLimit = Math.min(getInventoryStackLimit(), tSlot.getMaxStackSize());
			int tRoom = tLimit - tSlot.stackSize;
			if (tRoom <= 0) continue;
			int tMove = Math.min(tRemain.stackSize, tRoom);
			tSlot.stackSize += tMove;
			tRemain.stackSize -= tMove;
			if (tRemain.stackSize <= 0) {
				updateInventory();
				return;
			}
		}
		for (int i = SLOT_OUTPUT_START; i <= SLOT_OUTPUT_END; i++) {
			if (!slotHas(i)) {
				slot(i, OM.get_(tRemain));
				updateInventory();
				return;
			}
		}
	}

	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (!acceptsEnergyType(aEnergyType) || !acceptsEnergyOnSide(aEnergyType, aSide)) return 0;
		if (aAmount <= 0) return 0;
		long tSize = Math.abs(aSize);
		if (tSize <= 0) return 0;
		if (tSize > getEnergySizeInputMax(aEnergyType, aSide)) {
			if (aDoInject) overcharge(tSize, aEnergyType);
			return aAmount;
		}
		long tBufferMax = mInput * 200;
		if (mEnergy >= tBufferMax) return 0;
		long tPerAmpEnergy = tSize * getProgressEnergyMultiplier(aEnergyType);
		if (tPerAmpEnergy <= 0) return 0;
		long tAccepted = Math.min(aAmount, Math.max(0, (tBufferMax - mEnergy) / tPerAmpEnergy));
		if (tAccepted <= 0) return 0;
		if (aDoInject) {
			mEnergy += tPerAmpEnergy * tAccepted;
			if (aEnergyType == TD.Energy.EU) mEUInputTicks = ENERGY_MODE_TICKS;
			if (aEnergyType == TD.Energy.HU) mHUInputTicks = ENERGY_MODE_TICKS;
			if (aEnergyType == TD.Energy.KU) mKUInputTicks = ENERGY_MODE_TICKS;
		}
		return tAccepted;
	}

	private int getProgressEnergyMultiplier(TagData aEnergyType) {
		if (aEnergyType == TD.Energy.HU) return HU_PROGRESS_MULTIPLIER;
		if (aEnergyType == TD.Energy.KU) return KU_PROGRESS_MULTIPLIER;
		return 1;
	}

	private boolean acceptsEnergyType(TagData aEnergyType) {
		return aEnergyType == TD.Energy.EU || aEnergyType == TD.Energy.HU || aEnergyType == TD.Energy.KU;
	}

	private boolean acceptsEnergyOnSide(TagData aEnergyType, byte aSide) {
		if (!acceptsEnergyType(aEnergyType)) return F;
		if (aSide == SIDE_ANY) return T;
		if (!SIDES_VALID[aSide]) return F;
		if (aEnergyType == TD.Energy.EU) return T;
		if (aEnergyType == TD.Energy.HU) return SIDES_BOTTOM[aSide];
		return aSide == OPOS[mFacing];
	}

	@Override public int getMinimumInventorySize() {return 10;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}

	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}

	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mFacing = UT.Code.getSideForPlayerPlacing(aPlayer, mFacing, SIDES_HORIZONTAL);
		onFacingChange(SIDE_UNKNOWN);
		checkCoverValidity();
		doEnetUpdate();
		return T;
	}

	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_monkeywrench)) {
			byte tTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (SIDES_HORIZONTAL[tTargetSide]) {
				setPrimaryFacing(tTargetSide);
				return 10000;
			}
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer aPlayer, int aSide) {
		return SIDES_HORIZONTAL[UT.Code.side(aSide)];
	}

	@Override
	public int[] getAccessibleSlotsFromSide2(byte aSide) {
		return SIDES_TOP[aSide] ? SLOTS_ALL : SLOTS_OUTPUT;
	}

	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (aSlot != SLOT_TOOL || !SIDES_TOP[aSide]) return F;
		if (!(aStack != null && aStack.getItem() instanceof MultiItemTool)) return F;
		return getMiningTool(aStack) != null;
	}

	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		return aSlot >= SLOT_OUTPUT_START;
	}

	@Override
	public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {
		if (aSlot != SLOT_TOOL) return F;
		return getMiningTool(aStack) != null;
	}

	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mEnergy != oEnergy || mProgress != oProgress || mProgressMax != oProgressMax;
	}

	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oEnergy = mEnergy;
		oProgress = mProgress;
		oProgressMax = mProgressMax;
	}

	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll,
			(byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(),
			UT.Code.toByteL(mEnergy, 0), UT.Code.toByteL(mEnergy, 1), UT.Code.toByteL(mEnergy, 2), UT.Code.toByteL(mEnergy, 3), UT.Code.toByteL(mEnergy, 4), UT.Code.toByteL(mEnergy, 5), UT.Code.toByteL(mEnergy, 6), UT.Code.toByteL(mEnergy, 7),
			UT.Code.toByteL(mProgress, 0), UT.Code.toByteL(mProgress, 1), UT.Code.toByteL(mProgress, 2), UT.Code.toByteL(mProgress, 3), UT.Code.toByteL(mProgress, 4), UT.Code.toByteL(mProgress, 5), UT.Code.toByteL(mProgress, 6), UT.Code.toByteL(mProgress, 7),
			UT.Code.toByteL(mProgressMax, 0), UT.Code.toByteL(mProgressMax, 1), UT.Code.toByteL(mProgressMax, 2), UT.Code.toByteL(mProgressMax, 3), UT.Code.toByteL(mProgressMax, 4), UT.Code.toByteL(mProgressMax, 5), UT.Code.toByteL(mProgressMax, 6), UT.Code.toByteL(mProgressMax, 7)
		);
	}

	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		if (aData.length >= 29) {
			mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
			setVisualData(aData[3]);
			setDirectionData(aData[4]);
			mEnergy = UT.Code.combine(aData[5], aData[6], aData[7], aData[8], aData[9], aData[10], aData[11], aData[12]);
			mProgress = UT.Code.combine(aData[13], aData[14], aData[15], aData[16], aData[17], aData[18], aData[19], aData[20]);
			mProgressMax = UT.Code.combine(aData[21], aData[22], aData[23], aData[24], aData[25], aData[26], aData[27], aData[28]);
		} else if (aData.length >= 24) {
			mEnergy = UT.Code.combine(aData[0], aData[1], aData[2], aData[3], aData[4], aData[5], aData[6], aData[7]);
			mProgress = UT.Code.combine(aData[8], aData[9], aData[10], aData[11], aData[12], aData[13], aData[14], aData[15]);
			mProgressMax = UT.Code.combine(aData[16], aData[17], aData[18], aData[19], aData[20], aData[21], aData[22], aData[23]);
		}
		return T;
	}

	@Override
	public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {
		return new MultiTileEntityGUIClientAutoToolMiner(aPlayer.inventory, this, aGUIID);
	}

	@Override
	public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {
		return new MultiTileEntityGUICommonAutoToolMiner(aPlayer.inventory, this, aGUIID);
	}

	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}

	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && acceptsEnergyType(aEnergyType);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mStopped) && acceptsEnergyOnSide(aEnergyType, aSide) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mInput;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, TD.Energy.EU, TD.Energy.HU, TD.Energy.KU);}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (SIDES_TOP[aSide]) return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[2], mRGBa), BlockTextureDefault.get(sOverlayOutputTop));
		if (SIDES_BOTTOM[aSide]) return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[2], mRGBa), BlockTextureDefault.get(sOverlayOutputBottom));
		int aIndex = aSide == mFacing ? 0 : aSide == OPOS[mFacing] ? 1 : 2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[aIndex]));
	}

	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/colored/side")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/autotools/hammer/overlay/side")
	};
	public static IIconContainer sOverlayOutputTop = new Textures.BlockIcons.CustomIcon("machines/automation/hopper/overlay/top");
	public static IIconContainer sOverlayOutputBottom = new Textures.BlockIcons.CustomIcon("machines/automation/hopper/overlay/bottom");

	public class MultiTileEntityGUICommonAutoToolMiner extends ContainerCommon {
		public MultiTileEntityGUICommonAutoToolMiner(InventoryPlayer aInventoryPlayer, MultiTileEntityAutoToolMiner aTileEntity, int aGUIID) {
			super(aInventoryPlayer, aTileEntity, aGUIID);
		}

		@Override
		public int addSlots(InventoryPlayer aInventoryPlayer) {
			addSlotToContainer(new Slot_Normal(mTileEntity, 0, 44, 35).setTooltip("Mining Tool", LH.Chat.WHITE));

			addSlotToContainer(new Slot_Normal(mTileEntity, 1, 98, 17).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 2, 116, 17).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 3, 134, 17).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 4, 98, 35).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 5, 116, 35).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 6, 134, 35).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 7, 98, 53).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 8, 116, 53).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, 9, 134, 53).setCanPut(F));

			return super.addSlots(aInventoryPlayer);
		}

		@Override public int getSlotCount() {return 10;}
		@Override public int getShiftClickSlotCount() {return 10;}
	}

	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientAutoToolMiner extends ContainerClient {
		public MultiTileEntityGUIClientAutoToolMiner(InventoryPlayer aInventoryPlayer, MultiTileEntityAutoToolMiner aTileEntity, int aGUIID) {
			super(new MultiTileEntityGUICommonAutoToolMiner(aInventoryPlayer, aTileEntity, aGUIID), RES_PATH_GUI + "chests/27.png");
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int aMouseX, int aMouseY) {
			fontRendererObj.drawString("Auto Tool Miner", 8, 5, 4210752);
			fontRendererObj.drawString("Tool", 26, 21, 4210752);
			fontRendererObj.drawString("Output", 102, 5, 4210752);

			long tMax = Math.max(1L, mProgressMax);
			int tPercent = (int)UT.Code.bind(0, 100, (int)((mProgress * 100L) / tMax));
			fontRendererObj.drawString("Progress: " + (mProgressMax <= 0 ? "-" : tPercent + "%"), 8, 57, 4210752);
			fontRendererObj.drawString("Energy: " + mEnergy + " EU", 8, 67, 4210752);
		}
	}

	@Override public String getTileEntityName() {return "gt.multitileentity.autotool.miner";}
}
