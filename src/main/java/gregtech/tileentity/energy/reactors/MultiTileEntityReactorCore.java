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

package gregtech.tileentity.energy.reactors;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.item.IItemReactorRod;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.render.*;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityReactorCore extends TileEntityBase10FacingDouble implements ITileEntityServerTickPost, IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityRunningActively, ITileEntitySwitchableOnOff, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	/// Number of neutrons that were received in each slot.
	protected int[] neutronCountsIn;
	/// Number of neutrons that are to be transmitted for each slot.
	protected int[] neutronCountsOut;
	/// Heat internal to the Reactor Core. Neutrons are converted into heat by reactor rods.
	protected long storedHeat = 0;
	/// Heat (HU) produced in the last tick, as shown by the Thermometer.
	protected long heatProduced = 0;

	/// Bitmask determining which slots can be active. Can be modified by e.g. covers.
	public byte mMode = 0;
	public boolean mRunning = F, mStopped = F;
	public FluidTankGT inputTank = new FluidTankGT(64000);
	public FluidTankGT outputTank = new FluidTankGT(64000).setCapacity(FL.Steam.mName, 64000L*STEAM_PER_WATER);

	public abstract int numSlots();
	public int slotIdx(int aSlot) { return aSlot % numSlots(); }
	public abstract int slotActivated(float aHitX, float aHitY, float aHitZ);

	public MultiTileEntityReactorCore() {
		this.neutronCountsIn = new int[numSlots()];
		this.neutronCountsOut = new int[numSlots()];
	}

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mMode = aNBT.getByte(NBT_MODE);
		storedHeat = aNBT.getLong(NBT_ENERGY);
		mRunning = aNBT.getBoolean(NBT_ACTIVE);
		mStopped = aNBT.getBoolean(NBT_STOPPED);
		inputTank.readFromNBT(aNBT, NBT_TANK+".0");
		outputTank.readFromNBT(aNBT, NBT_TANK+".1");
		for (int i = 0; i < numSlots(); i++) {
			neutronCountsIn[i] = aNBT.getInteger(NBT_VALUE+".m."+i);
			neutronCountsOut[i] = aNBT.getInteger(NBT_VALUE+".o."+i);
		}

		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_MODE, mMode);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, storedHeat);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mRunning);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		inputTank.writeToNBT(aNBT, NBT_TANK+".0");
		outputTank.writeToNBT(aNBT, NBT_TANK+".1");
		for (int i = 0; i < numSlots(); i++) {
			UT.NBT.setNumber(aNBT, NBT_VALUE+".m."+i, neutronCountsIn[i]);
			UT.NBT.setNumber(aNBT, NBT_VALUE+".o."+i, neutronCountsOut[i]);
		}
	}
	
	static {
		LH.add("gt.tooltip.reactorcore.1", "Reactor Slots can be accessed from the Top and Bottom Side.");
		LH.add("gt.tooltip.reactorcore.2", "Accessing a reactor slot is only possible when the slot or the reactor is off.");
		LH.add("gt.tooltip.reactorcore.3", "Primary Facing Emits Hot Coolant.");
		LH.add("gt.tooltip.reactorcore.4", "Secondary Facing Emits Cold Coolant when over half full.");
		LH.add("gt.tooltip.reactorcore.5", "Choice of Coolant can have special Effects.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.2"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.3"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.4"));
		aList.add(Chat.GREEN    + LH.get("gt.tooltip.reactorcore.5"));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TAKE_PINCERS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_GEIGER_COUNTER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_THERMOMETER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (mHasToAddTimer) {
				GT_API_Proxy.SERVER_TICK_POST.add(this);
				GT_API_Proxy.SERVER_TICK_PO2T.add(this);
				mHasToAddTimer = F;
			}
			if (inputTank.overHalf() && mSecondFacing != mFacing) FL.move(inputTank, getAdjacentTank(mSecondFacing), inputTank.amount() - inputTank.capacity() / 2);
			if (outputTank.has()) FL.move(outputTank, getAdjacentTank(mFacing));
			
			if (inputTank.check()) updateClientData();
		}
	}

	protected abstract void updateAdjacentCores();

	@Override
	public void onServerTickPost(boolean aFirst) {
		if (aFirst) {
			// It is == 19 because the Sensors react to == 0, so this is the realistic fastest a Sensor can display.
			if (SERVER_TIME % 20 == 19 && !mStopped) {
				updateAdjacentCores();
			}
		} else {
			// Also runs while stopped, so rods stop being moderated once nothing moderates them anymore.
			if (SERVER_TIME % 20 == 19) {
				for (int i = 0; i < numSlots(); i++) {
					updateReactorRodModeration(i);
				}
			}

			long tRadiation = 0;
			for (int i = 0; i < numSlots(); i++) {
				tRadiation += (neutronCountsOut[i] = neutronCountsIn[i]);
			}
			tRadiation = UT.Code.divup(tRadiation, 256);

			// TODO Raycasting through Lead, Water and similar Blocks.
			if (tRadiation > 0 && SERVER_TIME % 20 == 10) {
				// Apply radiation
				for (Object tEntity : worldObj.loadedEntityList) if (tEntity instanceof EntityLivingBase) {
					if (Math.abs(xCoord - ((EntityLivingBase)tEntity).posX) > 200) continue;
					if (Math.abs(zCoord - ((EntityLivingBase)tEntity).posZ) > 200) continue;
					int tStrength = UT.Code.bindInt((long)(tRadiation - ((EntityLivingBase)tEntity).getDistance(xCoord, yCoord, zCoord)));
					if (tStrength > 0) UT.Entities.applyRadioactivity((EntityLivingBase)tEntity, (int)UT.Code.divup(tStrength, 10), tStrength);
				}
			}

			mRunning = (tRadiation != 0);

			long oldHeat = storedHeat;
			for (int i = 0; i < numSlots(); i++) {
				// In here, the neutrons may actually be converted to heat, mutating storedHeat.
				if (performReactorRodNeutronReaction(i)) {
					mRunning = T;
				}
			}

			ReactorCoolant coolant = ReactorCoolant.getCoolant(inputTank.getFluid());
			// Some coolants generate less heat per neutron. Only this tick's heat is divided; the remainder amount already is converted by the coolant.
			if (coolant != null) storedHeat = coolant.divideGeneratedHeat(storedHeat - oldHeat) + oldHeat;
			heatProduced = storedHeat - oldHeat;

			if (storedHeat > 0) { // Convert heat (HU) to hot coolant.
				boolean tExploding = F;
				if (coolant != null) {
					long litersNeeded = storedHeat / coolant.mHUPerLiter; // div down here. The remainder should stay as storedHeat.
					if (inputTank.has(litersNeeded) && outputTank.fillAll(FL.mul(coolant.mHeatant, litersNeeded))) {
						inputTank.remove(litersNeeded);
						storedHeat -= litersNeeded * coolant.mHUPerLiter;
					} else {
						tExploding = T;
					}
				} else {
					// The tank only accepts coolants, so it is empty. Leftover heat alone doesn't blow it up, only heat produced this tick.
					tExploding = heatProduced > 0;
				}
				if (tExploding && !invempty()) explode(tRadiation);
			}
		}
	}

	protected void explode(long aRadiation) {
		// TODO proper explosion.
		// super.explode(10); // TODO Keep commented out until Reactor System has been tested well enough.
		for (int i = 0; i < numSlots(); i++) {
			slotKill(i);
		}
		UT.Sounds.send(SFX.MC_EXPLODE, this, F);
		storedHeat = 0;
		for (Object tEntity : worldObj.loadedEntityList) if (tEntity instanceof EntityLivingBase) {
			if (Math.abs(xCoord - ((EntityLivingBase)tEntity).posX) > 500) continue;
			if (Math.abs(zCoord - ((EntityLivingBase)tEntity).posZ) > 500) continue;
			int tStrength = UT.Code.bindInt((long)(aRadiation * 2 - ((EntityLivingBase)tEntity).getDistance(xCoord, yCoord, zCoord)));
			if (tStrength > 0) UT.Entities.applyRadioactivity((EntityLivingBase)tEntity, (int)UT.Code.divup(tStrength, 10), tStrength);
		}
		updateClientData();
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_POST.remove(this);
		GT_API_Proxy.SERVER_TICK_PO2T.remove(this);
		onUnregisterPost();
	}
	
	/** Safety limit in half blocks for how far a neutron beam walks through transparent slots. Not a gameplay limit, just a guard against runaway walks. */
	public static final int NEUTRON_RANGE = 256;

	/** @return the length of one slot along the travel axis, in half blocks. */
	public abstract int slotLength();
	/** @return the slot a beam travelling towards aSide enters this core at, coming in at the lateral sub-coordinate aSubSide. */
	public abstract int slotOfEntry(byte aSide, int aSubSide);
	/** @return the next slot of this core behind aSlot when travelling towards aSide, or -1 if the beam leaves this core. */
	public abstract int slotBehind(byte aSide, int aSlot);

	public int addNeutrons(int aSlot, int aAmount) {
		neutronCountsIn[slotIdx(aSlot)] += aAmount;
		return aAmount;
	}

	public int getNeutronCount(int aSlot) {
		return neutronCountsOut[slotIdx(aSlot)];
	}

	public void addHeat(long aAmount) {
		storedHeat += aAmount;
	}

	public IItemReactorRod getRodAt(int aSlot) {
		return getRodAt(aSlot, false);
	}

	public IItemReactorRod getRodAt(int aSlot, boolean includeOff) {
		if (!slotHas(aSlot) || (!includeOff && (mStopped || (mMode & B[aSlot]) != 0)))
			return null;
		Item item = ST.item(slot(aSlot));
		if (item instanceof IItemReactorRod)
			return (IItemReactorRod)item;
		else
			return null;
	}

	/** @return if neutrons travel straight through aSlot instead of interacting with it. Only an empty slot is transparent while switched off. */
	public boolean slotIsTransparent(int aSlot) {
		if (!slotHas(aSlot)) return T;                     // Nothing in there to stop the beam.
		if (mStopped || (mMode & B[aSlot]) != 0) return F; // A switched off rod blocks the beam.
		IItemReactorRod rod = getRodAt(aSlot);
		return rod != null && rod.isTransparent(this, aSlot, slot(aSlot));
	}

	public boolean isReactorRodModerated(int aSlot) {
		int tSlot = slotIdx(aSlot);
		IItemReactorRod rod = getRodAt(tSlot);
		return rod != null ? rod.isModerated(this, aSlot, slot(tSlot)) : F;
	}

	protected void updateReactorRodModeration(int aSlot) {
		int tSlot = slotIdx(aSlot);
		IItemReactorRod rod = getRodAt(tSlot, true);
		if (rod != null) rod.updateModeration(this, aSlot, slot(tSlot));
	}

	/// Returns the amount of neutrons emitted to its neighbours by a rod in this slot, if any. Updates the neutrons in the slot according to the Self stat if it's a fuel rod.
	public int getReactorRodNeutronEmission(int aSlot) {
		int tSlot = slotIdx(aSlot);
		IItemReactorRod rod = getRodAt(tSlot);
		if (rod != null) return rod.getReactorRodNeutronEmission(this, aSlot, slot(tSlot));
		neutronCountsIn[tSlot] = 0;
		return 0;
	}

	/// Perform the reaction, changing neutrons to heat, decreasing fuel, and prepare the reactor for the next run if needed.
	protected boolean performReactorRodNeutronReaction(int aSlot) {
		int tSlot = slotIdx(aSlot);
		if (SERVER_TIME % 20 == 18) neutronCountsIn[tSlot] -= neutronCountsOut[tSlot];
		IItemReactorRod rod = getRodAt(tSlot);
		if (rod != null) return rod.performReactorRodNeutronReaction(this, aSlot, slot(tSlot));

		return F;
	}
	
	/** @return the adjacent Reactor Core on aSide, or null if there is no properly aligned one. */
	public MultiTileEntityReactorCore getAdjacentCore(byte aSide) {
		DelegatorTileEntity<TileEntity> tAdjacent = getAdjacentTileEntity(aSide);
		return tAdjacent.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacent.mSideOfTileEntity] ? (MultiTileEntityReactorCore)tAdjacent.mTileEntity : null;
	}
	
	/** Sends aNeutrons into aSlot of this core, travelling towards aSide. @return the reflected neutrons. */
	protected int emitNeutronsAt(byte aSide, int aSlot, int aSubSide, int aNeutrons, boolean aModerated, int aRange) {
		if (aRange <= 0) return 0;
		if (slotIsTransparent(aSlot)) return emitNeutronsPast(aSide, aSlot, aSubSide, aNeutrons, aModerated, aRange - slotLength());
		IItemReactorRod rod = getRodAt(aSlot);
		if (rod == null) return 0; // A switched off rod, or an item that is not a rod, blocks the beam.
		return rod.emitToAndReflectFrom(this, aSlot, slot(aSlot), aNeutrons, aModerated);
	}
	
	/** Continues a beam behind aSlot towards aSide, into the next slot of this core or into the adjacent core. @return the reflected neutrons. */
	protected int emitNeutronsPast(byte aSide, int aSlot, int aSubSide, int aNeutrons, boolean aModerated, int aRange) {
		int tSlot = slotBehind(aSide, aSlot);
		if (tSlot >= 0) return emitNeutronsAt(aSide, tSlot, aSubSide, aNeutrons, aModerated, aRange);
		MultiTileEntityReactorCore tCore = getAdjacentCore(aSide);
		return tCore == null ? 0 : tCore.emitNeutronsAt(aSide, tCore.slotOfEntry(aSide, aSubSide), aSubSide, aNeutrons, aModerated, aRange);
	}

	/** Emits aNeutrons from aSlot of this core towards aSide, at the lateral sub-coordinate aSubSide. @return the reflected neutrons. */
	public int emitNeutronsFrom(byte aSide, int aSlot, int aSubSide, int aNeutrons, boolean aModerated) {
		return emitNeutronsPast(aSide, aSlot, aSubSide, aNeutrons, aModerated, NEUTRON_RANGE);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;

		switch (aTool) {
			case TOOL_softhammer:
				mStopped = !mStopped;
				if (aChatReturn != null) {
					aChatReturn.add(mStopped ? "Reactor Block is OFF" : "Reactor Block is ON");
				}
				return 10000;
			case TOOL_thermometer:
				if (aChatReturn != null) {
					aChatReturn.add("Heat Levels: " + (heatProduced <= 0 ? "None" : heatProduced + " HU"));
				}
				return 10000;
			case TOOL_magnifyingglass:
				if (aChatReturn != null) {
					aChatReturn.add("Input: " + inputTank.content());
					aChatReturn.add("Output: " + outputTank.content());
					aChatReturn.add(mStopped ? "Reactor Block is OFF" : "Reactor Block is ON");
				}
				return 1;
			case TOOL_geigercounter:
				if (aChatReturn != null) {
					StringBuilder builder = new StringBuilder("Neutron Levels: ");
					for (int i = 0; i < numSlots(); i++) {
						builder.append(neutronCountsOut[i]);
						builder.append('n');
						if (i < numSlots() - 1) {
							builder.append("; ");
						}
					}
					aChatReturn.add(builder.toString());
					aChatReturn.add(mStopped?"Reactor Block is OFF":"Reactor Block is ON");
				}
				return 10000;
			case TOOL_pincers:
				if (!SIDES_TOP[aSide]) return 0;
				int tSlot = slotActivated(aHitX, aHitY, aHitZ);
				if (slotHas(tSlot) && ST.give(aPlayer, slot(tSlot), T, worldObj, xCoord+0.5, yCoord+1.5, zCoord+0.5)) {
					slotKill(tSlot);
					updateClientData();
					return 10000;
				}
				return 0;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide]) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (ST.item(aStack) instanceof IItemReactorRod && ((IItemReactorRod)ST.item_(aStack)).isReactorRod(aStack)) {
				int tSlot = slotActivated(aHitX, aHitY, aHitZ);
				if (!slotHas(tSlot) && ST.use(aPlayer, aStack)) {
					slot(tSlot, ST.amount(1, aStack));
					mStopped = T;
					UT.Sounds.send(SFX.MC_CLICK, this, F);
					updateClientData();
				}
			}
		}
		return T;
	}

	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (!aSendAll) return getClientDataPacketByte(aSendAll, getVisualData());

		ByteArrayOutputStream packetArray = new ByteArrayOutputStream();

		try {
			packetArray.write(new byte[]{
					(byte) UT.Code.getR(mRGBa), (byte) UT.Code.getG(mRGBa), (byte) UT.Code.getB(mRGBa),
					getVisualData(), getDirectionData(),
					UT.Code.toByteS(FL.id_(inputTank), 0), UT.Code.toByteS(FL.id_(inputTank), 1)
			});
		} catch (IOException ignored) {}

		for (int i = 0; i < numSlots(); i++) {
			for (int j = 0; j < 2; j++) {
				packetArray.write(UT.Code.toByteS(ST.id(slot(i)), j));
			}
			for (int j = 0; j < 2; j++) {
				packetArray.write(UT.Code.toByteS(ST.meta(slot(i)), j));
			}
		}

		return getClientDataPacketByteArray(aSendAll, packetArray.toByteArray());
	}

	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		super.receiveDataByteArray(aData, aNetworkHandler);
		int i = 5;
		if (aData.length <= i) return T;
		inputTank.setFluid(FL.make(UT.Code.combine(aData[i++], aData[i++]), inputTank.getCapacity()));
		for (int j = 0; j < numSlots(); j++) {
			slot(j, ST.make(UT.Code.combine(aData[i++], aData[i++]), 1, UT.Code.combine(aData[i++], aData[i++])));
		}
		return T;
	}

	public ITexture[] mTextures = new ITexture[15];

	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < 6 || (aRenderPass == 6 && inputTank.has())) return T;
		for (int i = 0; i < numSlots(); i++) {
			if (aRenderPass == 7 + i && slotHas(i)) return T;
		}
		return F;
	}

	protected abstract IIconContainer[] getColoredIcons();
	protected abstract IIconContainer[] getOverlayIcons();

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		IIconContainer[] sColoreds = getColoredIcons();
		IIconContainer[] sOverlays = getOverlayIcons();
		mTextures[0] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa), BlockTextureDefault.get(sOverlays[0]));
		mTextures[1] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa), BlockTextureDefault.get(sOverlays[1]));
		mTextures[2] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[2], mRGBa), BlockTextureDefault.get(sOverlays[2]));
		mTextures[3] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[3], mRGBa), BlockTextureDefault.get(sOverlays[3]));
		mTextures[4] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[4], mRGBa), BlockTextureDefault.get(sOverlays[4]));
		mTextures[5] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[5], mRGBa), BlockTextureDefault.get(sOverlays[5]));
		mTextures[6] = (inputTank.has() ? BlockTextureFluid.get(inputTank) : null);

		for (int i = 0; i < numSlots(); i++) {
			ItemStack aStack = slot(i);
			if (ST.item(aStack) instanceof IItemReactorRod) {
				mTextures[7+i] = ((IItemReactorRod) ST.item_(aStack)).getReactorRodTextureSides(this, i, aStack, !mStopped && (mMode & B[i]) == 0);
				mTextures[11+i] = ((IItemReactorRod) ST.item_(aStack)).getReactorRodTextureTop(this, i, aStack, !mStopped && (mMode & B[i]) == 0);
			} else {
				mTextures[7+i] = null;
				mTextures[11+i] = null;
			}
		}
		return 11;
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
			case SIDE_X_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]);
			case SIDE_Y_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]);
			case SIDE_X_POS: return box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case SIDE_Y_POS: return box(aBlock, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case SIDE_Z_POS: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case 6: return box(aBlock, PX_P[ 2]+PX_OFFSET, PX_P[ 2], PX_P[ 2]+PX_OFFSET, PX_N[ 2]-PX_OFFSET, PX_N[ 2], PX_N[ 2]-PX_OFFSET);
		}
		return F;
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < 6 && !ALONG_AXIS[aRenderPass][aSide]) return null;

		if (aRenderPass == mFacing) return mTextures[4];
		if (aRenderPass == mSecondFacing) return mTextures[5];

		if (aRenderPass >= 6 || aRenderPass < 2) {
			int textureIndex = aRenderPass;
			if (SIDES_VERTICAL[aSide] && aRenderPass != 6 && aRenderPass > 1) {
				textureIndex += 4;
			}
			return mTextures[textureIndex];
		}

		return mTextures[isCovered((byte)aRenderPass) ? 3 : 2];
	}
	
	protected byte oVisual = 0;
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return oVisual != getVisualData() || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oVisual = getVisualData();
	}
	
	@Override
	public void setVisualData(byte aData) {
		mStopped = ((aData & 32) != 0);
		mRunning = ((aData & 16) != 0);
		mMode = (byte)(aData & 15);
	}
	
	@Override public byte getVisualData() {return (byte)(mStopped?mRunning?mMode|48:mMode|32:mRunning?mMode|16:mMode);}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public byte getDefaultSecondSide() {return SIDE_BOTTOM;}

	@Override public void updateTanks() {/**/}
	@Override public void updateInventory() {super.updateInventory(); updateClientData();}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return ReactorCoolant.isCoolant(aFluidToFill) ? inputTank : null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return outputTank;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return new IFluidTank[] {inputTank, outputTank};
	}

	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		if (!ReactorCoolant.isCoolant(aFluid)) return 0;
		return inputTank.fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		return (outputTank.has() ? outputTank : inputTank).drain(aMaxDrain, aDoDrain);
	}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mRunning) {UT.Entities.applyHeatDamage(aEntity, 5); UT.Entities.applyRadioactivity(aEntity, 3, 1);}}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], PX_P[1], PX_P[1], PX_N[1], PX_N[1], PX_N[1]);}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public boolean isSurfaceOpaque2(byte aSide) {return SIDES_VERTICAL[aSide];}

	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[numSlots()];}

	@Override
	public int[] getAccessibleSlotsFromSide2(byte aSide) {
		return aSide == SIDE_DOWN || aSide == SIDE_TOP ? UT.Code.getAscendingArray(numSlots()) : ZL_INTEGER;
	}

	@Override
	public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {
		if (!mStopped && (mMode & B[aSlot]) == 0) return F;
		if (aSlot >= numSlots()) return F;
		return aStack != null && !slotHas(aSlot) &&
			ST.item(aStack) instanceof IItemReactorRod && ((IItemReactorRod)ST.item_(aStack)).isReactorRod(aStack);
	}

	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (!mStopped && (mMode & B[aSlot]) == 0) return F;
		if (aSlot >= numSlots()) return F;
		return aStack != null;
	}

	@Override public int getInventoryStackLimit() {return 1;}
	
	@Override public boolean getStateRunningPassively() {return mRunning;}
	@Override public boolean getStateRunningPossible() {return mRunning;}
	@Override public boolean getStateRunningActively() {return mRunning;}
	@Override public boolean setStateOnOff(boolean aOnOff) {return mStopped = !aOnOff;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	public byte setStateMode(byte aMode) {return mMode = aMode;}
	public byte getStateMode() {return mMode;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";}
}
