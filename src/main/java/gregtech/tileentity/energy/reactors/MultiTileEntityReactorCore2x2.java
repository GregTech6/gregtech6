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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.MT;
import gregapi.item.IItemReactorRod;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorCore2x2 extends MultiTileEntityReactorCore implements ITileEntitySwitchableMode {
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onServerTickPost(boolean aFirst) {
		if (aFirst) {
			// It is == 19 because the Sensors react to == 0, so this is the realistic fastest a Sensor can display.
			if (SERVER_TIME % 20 == 19) {
				if (mStopped) {
					// Calls isReactorRodModerated to update moderation states
					isReactorRodModerated(0);
					isReactorRodModerated(1);
					isReactorRodModerated(2);
					isReactorRodModerated(3);
				} else {
					DelegatorTileEntity<MultiTileEntityReactorCore> tAdjacents[] = new DelegatorTileEntity[4], tAdjacent;
					DelegatorTileEntity
					tAdjacentTE = getAdjacentTileEntity(SIDE_Z_NEG); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[0] = tAdjacentTE;
					tAdjacentTE = getAdjacentTileEntity(SIDE_Z_POS); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[1] = tAdjacentTE;
					tAdjacentTE = getAdjacentTileEntity(SIDE_X_NEG); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[2] = tAdjacentTE;
					tAdjacentTE = getAdjacentTileEntity(SIDE_X_POS); if (tAdjacentTE.mTileEntity instanceof MultiTileEntityReactorCore && SIDES_HORIZONTAL[tAdjacentTE.mSideOfTileEntity]) tAdjacents[3] = tAdjacentTE;

					int tNeutronCount = getReactorRodNeutronEmission(0);
					boolean tModerated = isReactorRodModerated(0);
					if (tNeutronCount != 0 || tModerated) {
						mNeutronCounts[0] += getReactorRodNeutronReflection(1, tNeutronCount, tModerated);
						mNeutronCounts[0] += getReactorRodNeutronReflection(2, tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_Z_NEG-2]; if (tAdjacent != null) mNeutronCounts[0] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_X_NEG-2]; if (tAdjacent != null) mNeutronCounts[0] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
					}

					tNeutronCount = getReactorRodNeutronEmission(1);
					tModerated = isReactorRodModerated(1);
					if (tNeutronCount != 0 || tModerated) {
						mNeutronCounts[1] += getReactorRodNeutronReflection(0, tNeutronCount, tModerated);
						mNeutronCounts[1] += getReactorRodNeutronReflection(3, tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_Z_POS-2]; if (tAdjacent != null) mNeutronCounts[1] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_X_NEG-2]; if (tAdjacent != null) mNeutronCounts[1] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
					}

					tNeutronCount = getReactorRodNeutronEmission(2);
					tModerated = isReactorRodModerated(2);
					if (tNeutronCount != 0 || tModerated) {
						mNeutronCounts[2] += getReactorRodNeutronReflection(0, tNeutronCount, tModerated);
						mNeutronCounts[2] += getReactorRodNeutronReflection(3, tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_Z_NEG-2]; if (tAdjacent != null) mNeutronCounts[2] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_X_POS-2]; if (tAdjacent != null) mNeutronCounts[2] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
					}

					tNeutronCount = getReactorRodNeutronEmission(3);
					tModerated = isReactorRodModerated(3);
					if (tNeutronCount != 0 || tModerated) {
						mNeutronCounts[3] += getReactorRodNeutronReflection(1, tNeutronCount, tModerated);
						mNeutronCounts[3] += getReactorRodNeutronReflection(2, tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_Z_POS-2]; if (tAdjacent != null) mNeutronCounts[3] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S2103[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
						tAdjacent = tAdjacents[SIDE_X_POS-2]; if (tAdjacent != null) mNeutronCounts[3] += tAdjacent.mTileEntity.getReactorRodNeutronReflection(S0312[tAdjacent.mSideOfTileEntity], tNeutronCount, tModerated);
					}
				}
			}
		} else {
			long tCalc = UT.Code.divup((oNeutronCounts[0] = mNeutronCounts[0]) + (oNeutronCounts[1] = mNeutronCounts[1]) + (oNeutronCounts[2] = mNeutronCounts[2]) + (oNeutronCounts[3] = mNeutronCounts[3]), 256);

			// TODO Raycasting through Lead, Water and similar Blocks.
			if (tCalc > 0 && SERVER_TIME % 20 == 10) {
				for (EntityLivingBase tEntity : (ArrayList<EntityLivingBase>) worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - tCalc, yCoord - tCalc, zCoord - tCalc, xCoord + 1 + tCalc, yCoord + 1 + tCalc, zCoord + 1 + tCalc))) {
					int tStrength = UT.Code.bindInt((long)(tCalc - tEntity.getDistance(xCoord, yCoord, zCoord)));
					if (tStrength > 0) UT.Entities.applyRadioactivity(tEntity, (int)UT.Code.divup(tStrength, 10), tStrength);
				}
			}

			mRunning = (tCalc != 0);

			long tEnergy = mEnergy;

			if (getReactorRodNeutronReaction(0)) mRunning = T;
			if (getReactorRodNeutronReaction(1)) mRunning = T;
			if (getReactorRodNeutronReaction(2)) mRunning = T;
			if (getReactorRodNeutronReaction(3)) mRunning = T;

			oEnergy = mEnergy - tEnergy;

			if (mEnergy > 0) {
				boolean tIsExploding = F;
				if (FL.Coolant_IC2.is(mTanks[0])) {
					tEnergy = mEnergy / EU_PER_COOLANT;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Coolant_IC2_Hot.make(tEnergy))) {
						mEnergy -= EU_PER_COOLANT * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (FL.distw(mTanks[0])) {
					tEnergy = mEnergy / EU_PER_WATER;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Steam.make(tEnergy * STEAM_PER_WATER))) {
						mEnergy -= mTanks[0].remove(tEnergy) * EU_PER_WATER;
					} else tIsExploding = T;
				} else if (MT.Sn.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_TIN;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Molten_Tin.make(tEnergy))) {
						mEnergy -= EU_PER_TIN * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.Na.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_SODIUM;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Molten_Sodium.make(tEnergy))) {
						mEnergy -= EU_PER_SODIUM * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.HDO.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_SEMI_HEAVY_WATER;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Semi_Heavy_Water.make(tEnergy))) {
						mEnergy -= EU_PER_SEMI_HEAVY_WATER * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.D2O.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_HEAVY_WATER;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Heavy_Water.make(tEnergy))) {
						mEnergy -= EU_PER_HEAVY_WATER * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.T2O.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_TRITIATED_WATER;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Tritiated_Water.make(tEnergy))) {
						mEnergy -= EU_PER_TRITIATED_WATER * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.LiCl.mLiquid.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_LICL;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Molten_LiCl.make(tEnergy))) {
						mEnergy -= EU_PER_LICL * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.CO2.mGas.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_CO2;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Carbon_Dioxide.make(tEnergy))) {
						mEnergy -= EU_PER_CO2 * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (MT.He.mGas.isFluidEqual(mTanks[0].getFluid())) {
					tEnergy = mEnergy / EU_PER_HELIUM;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.Hot_Helium.make(tEnergy))) {
						mEnergy -= EU_PER_HELIUM * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (FL.Thorium_Salt.is(mTanks[0])) {
					tEnergy = mEnergy / EU_PER_THORIUM_SALT;
					if (mTanks[0].has(tEnergy) && mTanks[1].fillAll(FL.amount(MT.LiCl.mLiquid, tEnergy))) {
						mEnergy -= EU_PER_THORIUM_SALT * mTanks[0].remove(tEnergy);
					} else tIsExploding = T;
				} else if (mTanks[0].isEmpty()) {
					if (mEnergy > EU_PER_THORIUM_SALT) tIsExploding = T;
				}

				if (tIsExploding && !invempty()) {
					// TODO proper explosion.
					// explode(10); // TODO Keep commented out until Reactor System has been tested well enough.
					slotKill(0); slotKill(1); slotKill(2); slotKill(3);
					UT.Sounds.send(SFX.MC_EXPLODE, this);
					tCalc *= 2;
					for (EntityLivingBase tEntity : (ArrayList<EntityLivingBase>) worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - tCalc, yCoord - tCalc, zCoord - tCalc, xCoord + 1 + tCalc, yCoord + 1 + tCalc, zCoord + 1 + tCalc))) {
						UT.Entities.applyRadioactivity(tEntity, (int) UT.Code.divup(tCalc, 10), (int)tCalc);
					}
				}
			}
		}
	}

	@Override
	public int getReactorRodNeutronEmission(int aSlot) {
		if (!mStopped && (mMode & B[aSlot]) == 0 && slotHas(aSlot) && ST.item(slot(aSlot)) instanceof IItemReactorRod) return ((IItemReactorRod)ST.item(slot(aSlot))).getReactorRodNeutronEmission(this, aSlot, slot(aSlot));
		mNeutronCounts[aSlot] = 0;
		return 0;
	}

	@Override
	public boolean getReactorRodNeutronReaction(int aSlot) {
		if (SERVER_TIME % 20 == 18) mNeutronCounts[aSlot] -= oNeutronCounts[aSlot];
		if (!mStopped && (mMode & B[aSlot]) == 0 && slotHas(aSlot) && ST.item(slot(aSlot)) instanceof IItemReactorRod) return ((IItemReactorRod)ST.item(slot(aSlot))).getReactorRodNeutronReaction(this, aSlot, slot(aSlot));
		return F;
	}

	@Override
	public int getReactorRodNeutronReflection(int aSlot, int aNeutrons, boolean aModerated) {
		if (!mStopped && (mMode & B[aSlot]) == 0 && slotHas(aSlot) && ST.item(slot(aSlot)) instanceof IItemReactorRod)
			return ((IItemReactorRod) ST.item(slot(aSlot))).getReactorRodNeutronReflection(this, aSlot, slot(aSlot), aNeutrons, aModerated);
		return 0;
	}

	@Override
	public boolean isReactorRodModerated(int aSlot) {
		if (slotHas(aSlot) && ST.item(slot(aSlot)) instanceof IItemReactorRod) {
			boolean isModerated = ((IItemReactorRod) ST.item(slot(aSlot))).isModerated(this, aSlot, slot(aSlot));
			if (mStopped || (mMode & B[aSlot]) != 0) return false;
			return isModerated;
		}
		return false;
	}

	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;

		if (isClientSide()) return 0;

		if (aTool.equals(TOOL_pincers) && SIDES_TOP[aSide]) {
			int tSlot = aHitX < 0.5 ? aHitZ < 0.5 ? 0 : 1 : aHitZ < 0.5 ? 2 : 3;
			if (slotHas(tSlot) && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, slot(tSlot), T, worldObj, xCoord+0.5, yCoord+1.5, zCoord+0.5)) {
				slotKill(tSlot);
				updateClientData();
				return 10000;
			}
			return 0;
		}
		if (aTool.equals(TOOL_geigercounter)) {
			if (aChatReturn != null) {
				aChatReturn.add("Neutron Levels: " + oNeutronCounts[0] + "n; " + oNeutronCounts[1] + "n; " + oNeutronCounts[2] + "n; " + oNeutronCounts[3] + "n");
				aChatReturn.add(mStopped?"Reactor Block is OFF":"Reactor Block is ON");
			}
			return 10000;
		}
		return 0;
	}

	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide]) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (ST.item(aStack) instanceof IItemReactorRod && ((IItemReactorRod)ST.item_(aStack)).isReactorRod(aStack)) {
				int tSlot = aHitX < 0.5 ? aHitZ < 0.5 ? 0 : 1 : aHitZ < 0.5 ? 2 : 3;
				if (!slotHas(tSlot) && ST.use(aPlayer, aStack)) {
					slot(tSlot, ST.amount(1, aStack));
					mStopped = T;
					UT.Sounds.send(SFX.MC_CLICK, this);
					updateClientData();
				}
			}
		}
		return T;
	}

	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(), UT.Code.toByteS(FL.id_(mTanks[0]), 0), UT.Code.toByteS(FL.id_(mTanks[0]), 1)
		, UT.Code.toByteS(ST.id(slot(0)), 0), UT.Code.toByteS(ST.id(slot(0)), 1), UT.Code.toByteS(ST.meta(slot(0)), 0), UT.Code.toByteS(ST.meta(slot(0)), 1)
		, UT.Code.toByteS(ST.id(slot(1)), 0), UT.Code.toByteS(ST.id(slot(1)), 1), UT.Code.toByteS(ST.meta(slot(1)), 0), UT.Code.toByteS(ST.meta(slot(1)), 1)
		, UT.Code.toByteS(ST.id(slot(2)), 0), UT.Code.toByteS(ST.id(slot(2)), 1), UT.Code.toByteS(ST.meta(slot(2)), 0), UT.Code.toByteS(ST.meta(slot(2)), 1)
		, UT.Code.toByteS(ST.id(slot(3)), 0), UT.Code.toByteS(ST.id(slot(3)), 1), UT.Code.toByteS(ST.meta(slot(3)), 0), UT.Code.toByteS(ST.meta(slot(3)), 1)
		);
		return getClientDataPacketByte(aSendAll, getVisualData());
	}

	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		super.receiveDataByteArray(aData, aNetworkHandler);
		int i = 5;
		if (aData.length <= i) return T;
		mTanks[0].setFluid(FL.make(UT.Code.combine(aData[i++], aData[i++]), mTanks[0].getCapacity()));
		slot(0, ST.make(UT.Code.combine(aData[i++], aData[i++]), 1, UT.Code.combine(aData[i++], aData[i++])));
		slot(1, ST.make(UT.Code.combine(aData[i++], aData[i++]), 1, UT.Code.combine(aData[i++], aData[i++])));
		slot(2, ST.make(UT.Code.combine(aData[i++], aData[i++]), 1, UT.Code.combine(aData[i++], aData[i++])));
		slot(3, ST.make(UT.Code.combine(aData[i++], aData[i++]), 1, UT.Code.combine(aData[i++], aData[i++])));
		return T;
	}

	public ITexture mTextures[] = new ITexture[15];

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextures[ 0] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa), BlockTextureDefault.get(sOverlays[0]));
		mTextures[ 1] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa), BlockTextureDefault.get(sOverlays[1]));
		mTextures[ 2] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[2], mRGBa), BlockTextureDefault.get(sOverlays[2]));
		mTextures[ 3] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[3], mRGBa), BlockTextureDefault.get(sOverlays[3]));
		mTextures[ 4] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[4], mRGBa), BlockTextureDefault.get(sOverlays[4]));
		mTextures[ 5] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[5], mRGBa), BlockTextureDefault.get(sOverlays[5]));
		mTextures[10] = (mTanks[0].has() ? BlockTextureFluid.get(mTanks[0]) : null);
		ItemStack
		aStack = slot(0);
		if (ST.item(aStack) instanceof IItemReactorRod) {
			mTextures[ 6] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureSides(this, 0, aStack, !mStopped && (mMode & B[0]) == 0);
			mTextures[11] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureTop  (this, 0, aStack, !mStopped && (mMode & B[0]) == 0);
		} else {
			mTextures[ 6] = null;
			mTextures[11] = null;
		}
		aStack = slot(1);
		if (ST.item(aStack) instanceof IItemReactorRod) {
			mTextures[ 7] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureSides(this, 1, aStack, !mStopped && (mMode & B[1]) == 0);
			mTextures[12] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureTop  (this, 1, aStack, !mStopped && (mMode & B[1]) == 0);
		} else {
			mTextures[ 7] = null;
			mTextures[12] = null;
		}
		aStack = slot(2);
		if (ST.item(aStack) instanceof IItemReactorRod) {
			mTextures[ 8] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureSides(this, 2, aStack, !mStopped && (mMode & B[2]) == 0);
			mTextures[13] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureTop  (this, 2, aStack, !mStopped && (mMode & B[2]) == 0);
		} else {
			mTextures[ 8] = null;
			mTextures[13] = null;
		}
		aStack = slot(3);
		if (ST.item(aStack) instanceof IItemReactorRod) {
			mTextures[ 9] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureSides(this, 3, aStack, !mStopped && (mMode & B[3]) == 0);
			mTextures[14] = ((IItemReactorRod)ST.item_(aStack)).getReactorRodTextureTop  (this, 3, aStack, !mStopped && (mMode & B[3]) == 0);
		} else {
			mTextures[ 9] = null;
			mTextures[14] = null;
		}
		return 11;
	}

	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		return aRenderPass < 6 || (aRenderPass == 6 && slotHas(0)) || (aRenderPass == 7 && slotHas(1)) || (aRenderPass == 8 && slotHas(2)) || (aRenderPass == 9 && slotHas(3)) || (aRenderPass == 10 && mTanks[0].has());
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

		case  6: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_N[10], PX_P[17], PX_N[10]);
		case  7: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[10], PX_N[10], PX_P[17], PX_N[ 2]);
		case  8: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 2], PX_N[ 2], PX_P[17], PX_N[10]);
		case  9: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[10], PX_N[ 2], PX_P[17], PX_N[ 2]);

		case 10: return box(aBlock, PX_P[ 2]+PX_OFFSET, PX_P[ 2], PX_P[ 2]+PX_OFFSET, PX_N[ 2]-PX_OFFSET, PX_N[ 2], PX_N[ 2]-PX_OFFSET);
		}
		return F;
	}

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aRenderPass < 6 && !ALONG_AXIS[aRenderPass][aSide] ? null : aRenderPass == mFacing ? mTextures[4] : aRenderPass == mSecondFacing ? mTextures[5] : aRenderPass >= 6 || aRenderPass < 2 ? mTextures[SIDES_VERTICAL[aSide] && aRenderPass != 10 && aRenderPass > 1 ? aRenderPass+5 : aRenderPass] : mTextures[aRenderPass < 6 && isCovered((byte)aRenderPass) ? 3 : 2];
	}

	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face2")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face2")
	};
	
	@Override
	public void updateInventory() {
		super.updateInventory();
		updateClientData();
	}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[4];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return aSide == SIDE_DOWN || aSide == SIDE_TOP ? UT.Code.getAscendingArray(4) : ZL_INTEGER;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return (mStopped || (mMode & B[aSlot]) != 0) && aStack != null && !slotHas(aSlot) && ST.item(aStack) instanceof IItemReactorRod && ((IItemReactorRod)ST.item_(aStack)).isReactorRod(aStack);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return (mStopped || (mMode & B[aSlot]) != 0) && aStack != null;}
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";} // Yeah Naming Convention doesn't work on the first one I added, just imagine a ".2x2" at the end of this String.
}
