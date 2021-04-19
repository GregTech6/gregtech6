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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.IconsGT;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.ITileEntityServerTickPre;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.util.CR;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPipeFluid extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, IFluidHandler, ITileEntityGibbl, ITileEntityTemperature, ITileEntityProgress, ITileEntityServerTickPre, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	public byte mLastReceivedFrom[] = ZL_BYTE, mRenderType = 0;
	public long mTemperature = DEF_ENV_TEMP, mMaxTemperature, mTransferredAmount = 0, mCapacity = 1000;
	public boolean mGasProof = F, mAcidProof = F, mPlasmaProof = F, mBlocking = T;
	public FluidTankGT[] mTanks = ZL_FT;
	
	/**
	 * Utility to quickly add a whole set of Fluid Pipes.
	 * May use up to 20 IDs, even if it is just 7 right now!
	 */
	public static void addFluidPipes(int aID, int aCreativeTabID, long aStat, boolean aGasProof, boolean aAcidProof, boolean aPlasmaProof, boolean aContactDamage, boolean aFlammable, boolean aRecipe, boolean aBlocking, MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aBlock, Class<? extends TileEntity> aClass, OreDictMaterial aMat) {
		addFluidPipes(aID, aCreativeTabID, aStat, aGasProof, aAcidProof, aPlasmaProof, aContactDamage, aFlammable, aRecipe, aBlocking, aRegistry, aBlock, aClass, (long)(aMat.mMeltingPoint * 1.25), aMat);
	}
	
	/**
	 * Utility to quickly add a whole set of Fluid Pipes.
	 * May use up to 20 IDs, even if it is just 7 right now!
	 */
	public static void addFluidPipes(int aID, int aCreativeTabID, long aStat, boolean aGasProof, boolean aAcidProof, boolean aPlasmaProof, boolean aContactDamage, boolean aFlammable, boolean aRecipe, boolean aBlocking, MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aBlock, Class<? extends TileEntity> aClass, long aMaxTemperature, OreDictMaterial aMat) {
		OreDictManager.INSTANCE.setTarget_(OP.pipeTiny     , aMat, aRegistry.add("Tiny " + aMat.getLocal() + " Fluid Pipe"     , "Fluid Pipes", aID   , aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 4], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat    , NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"sP ", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeSmall    , aMat, aRegistry.add("Small " + aMat.getLocal() + " Fluid Pipe"    , "Fluid Pipes", aID+ 1, aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 6], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 2L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{" P ", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeMedium   , aMat, aRegistry.add(aMat.getLocal() + " Fluid Pipe"               , "Fluid Pipes", aID+ 2, aCreativeTabID, aClass, aMat.mToolQuality, 32, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 8], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 6L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeLarge    , aMat, aRegistry.add("Large " + aMat.getLocal() + " Fluid Pipe"    , "Fluid Pipes", aID+ 3, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[12], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat*12L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeHuge     , aMat, aRegistry.add("Huge " + aMat.getLocal() + " Fluid Pipe"     , "Fluid Pipes", aID+ 4, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat*24L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateDouble.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeQuadruple, aMat, aRegistry.add("Quadruple " + aMat.getLocal() + " Fluid Pipe", "Fluid Pipes", aID+ 5, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 6L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 4), new Object[]{"PP" , "PP"        , 'P', OP.pipeMedium.dat(aMat)}), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeNonuple  , aMat, aRegistry.add("Nonuple " + aMat.getLocal() + " Fluid Pipe"  , "Fluid Pipes", aID+ 6, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 2L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 9), new Object[]{"PPP", "PPP", "PPP", 'P', OP.pipeSmall.dat(aMat)}), T, F, T);
		
		CR.shapeless(aRegistry.getItem(aID+2, 4), CR.DEF_NCC, new Object[] {aRegistry.getItem(aID+5)});
		CR.shapeless(aRegistry.getItem(aID+1, 9), CR.DEF_NCC, new Object[] {aRegistry.getItem(aID+6)});
	}
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.mtransfer")) mTransferredAmount = aNBT.getLong("gt.mtransfer");
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
		if (aNBT.hasKey(NBT_OPAQUE)) mBlocking = aNBT.getBoolean(NBT_OPAQUE);
		if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		if (aNBT.hasKey(NBT_TANK_CAPACITY)) mCapacity = aNBT.getLong(NBT_TANK_CAPACITY);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mMaxTemperature = aNBT.getLong(NBT_TEMPERATURE);
		if (aNBT.hasKey(NBT_TANK_COUNT)) {
			mTanks = new FluidTankGT[Math.max(1, aNBT.getInteger(NBT_TANK_COUNT))];
			mLastReceivedFrom = new byte[mTanks.length];
			for (int i = 0; i < mTanks.length; i++) {
				mTanks[i] = new FluidTankGT(aNBT, NBT_TANK+"."+i, mCapacity);
				mLastReceivedFrom[i] = aNBT.getByte("gt.mlast."+i);
			}
		} else {
			mTanks = new FluidTankGT(aNBT, NBT_TANK+"."+0, mCapacity).AS_ARRAY;
			mLastReceivedFrom = new byte[] {aNBT.getByte("gt.mlast.0")};
		}
		
		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			if (WD.even(this)) {
				GT_API_Proxy.SERVER_TICK_PRE.add(this);
			} else {
				GT_API_Proxy.SERVER_TICK_PR2.add(this);
			}
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mTanks.length; i++) {
			mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
			aNBT.setByte("gt.mlast."+i, mLastReceivedFrom[i]);
		}
		UT.NBT.setNumber(aNBT, "gt.mtransfer", mTransferredAmount);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_plunger)) return GarbageGT.trash(mTanks);
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (!isCovered(UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ))) {
				if (aChatReturn != null) {
					boolean tPipeEmpty = T;
					for (FluidTankGT tTank : mTanks) if (!tTank.isEmpty()) {
						aChatReturn.add(tTank.content());
						tPipeEmpty = F;
					}
					
					Set<MultiTileEntityPipeFluid>
					tDone = new HashSetNoNulls<>(F, this),
					tNow  = new HashSetNoNulls<>(F, this),
					tNext = new HashSetNoNulls<>(),
					tSwap;
					
					List<FluidTankGT> tFluids = new ArrayListNoNulls<>();
					
					while (T) {
						for (MultiTileEntityPipeFluid tPipe : tNow) {
							for (FluidTankGT tTank : tPipe.mTanks) if (!tTank.isEmpty()) {
								boolean temp = T;
								for (FluidTankGT tFluid : tFluids) if (tFluid.contains(tTank.get())) {
									tFluid.add(tTank.amount());
									temp = F;
									break;
								}
								if (temp) tFluids.add(new FluidTankGT().setFluid(tTank));
							}
							
							for (byte tSide : ALL_SIDES_VALID) if (tPipe.connected(tSide)) {
								DelegatorTileEntity<TileEntity> tDelegator = tPipe.getAdjacentTileEntity(tSide);
								if (tDelegator.mTileEntity instanceof MultiTileEntityPipeFluid) {
									if (tDone.add((MultiTileEntityPipeFluid)tDelegator.mTileEntity)) {
										tNext.add((MultiTileEntityPipeFluid)tDelegator.mTileEntity);
									}
								}
							}
						}
						if (tNext.isEmpty()) break;
						tSwap = tNow;
						tNow  = tNext;
						tNext = tSwap;
						tNext.clear();
					}
					
					if (tFluids.isEmpty()) {
						aChatReturn.add("=== This Fluid Pipe Network is empty ===");
					} else {
						if (tPipeEmpty) aChatReturn.add("This particular Pipe Segment is currently empty");
						aChatReturn.add("=== This Fluid Pipe Network contains: ===");
						for (FluidTankGT tFluid : tFluids) aChatReturn.add(tFluid.content());
					}
				}
				return mTanks.length;
			}
		}
		return 0;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.PIPE_STATS_BANDWIDTH) + UT.Code.makeString(mCapacity/2) + " L/t");
		aList.add(Chat.CYAN     + LH.get(LH.PIPE_STATS_CAPACITY) + UT.Code.makeString(mCapacity) + " L");
		if (mTanks.length > 1)
		aList.add(Chat.CYAN     + LH.get(LH.PIPE_STATS_AMOUNT) + mTanks.length);
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + mMaxTemperature + " K)");
		if (mGasProof       ) aList.add(Chat.ORANGE     + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof      ) aList.add(Chat.ORANGE     + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof    ) aList.add(Chat.ORANGE     + LH.get(LH.TOOLTIP_PLASMAPROOF));
		if (mContactDamage  ) aList.add(Chat.DRED       + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPre() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mHasToAddTimer) {
			if (WD.even(this)) {
				GT_API_Proxy.SERVER_TICK_PRE.add(this);
			} else {
				GT_API_Proxy.SERVER_TICK_PR2.add(this);
			}
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_PRE.remove(this);
		GT_API_Proxy.SERVER_TICK_PR2.remove(this);
		onUnregisterPre();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onServerTickPre(boolean aFirst) {
		mTransferredAmount = 0;
		
		DelegatorTileEntity<MultiTileEntityPipeFluid>[] tAdjacentPipes = new DelegatorTileEntity[6];
		DelegatorTileEntity<IFluidHandler>[] tAdjacentTanks = new DelegatorTileEntity[6];
		DelegatorTileEntity<TileEntity>[] tAdjacentOther = new DelegatorTileEntity[6];
		
		for (byte tSide : ALL_SIDES_VALID) if (canEmitFluidsTo(tSide)) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(tSide);
			if (tTileEntity != null) {
				if (tTileEntity.mTileEntity instanceof MultiTileEntityPipeFluid) {
					tAdjacentPipes[tSide] = new DelegatorTileEntity<>((MultiTileEntityPipeFluid)tTileEntity.mTileEntity, tTileEntity);
				} else if (tTileEntity.mTileEntity instanceof IFluidHandler) {
					tAdjacentTanks[tSide] = new DelegatorTileEntity<>((IFluidHandler)tTileEntity.mTileEntity, tTileEntity);
				} else {
					tAdjacentOther[tSide] = tTileEntity;
				}
			}
		}
		
		for (int i = 0; i < mTanks.length; i++) {
			FluidStack tFluid = mTanks[i].get();
			if (tFluid != null && tFluid.amount > 0) {
				mTemperature = FL.temperature(tFluid);
				if (!mGasProof && FL.gas(tFluid)) {
					mTransferredAmount += Math.min(8, mTanks[i].amount());
					GarbageGT.trash(mTanks[i], 8);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-2, -2, -2, +3, +3, +3))) UT.Entities.applyTemperatureDamage(tEntity, mTemperature, 2.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
				}
				if (!mPlasmaProof && FL.plasma(tFluid)) {
					mTransferredAmount += Math.min(64, mTanks[i].amount());
					GarbageGT.trash(mTanks[i], 64);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-2, -2, -2, +3, +3, +3))) UT.Entities.applyTemperatureDamage(tEntity, mTemperature, 2.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
				}
				if (!mAcidProof && FL.acid(tFluid)) {
					mTransferredAmount += Math.min(16, mTanks[i].amount());
					GarbageGT.trash(mTanks[i], 16);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 0.5F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-1, -1, -1, +2, +2, +2))) UT.Entities.applyChemDamage(tEntity, 2);} catch(Throwable e) {e.printStackTrace(ERR);}
					if (rng(100) == 0) {
						GarbageGT.trash(mTanks);
						setToAir();
						return;
					}
				}
			} else {
				long tEnvTemp = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
				if (mTemperature < tEnvTemp) mTemperature++; else if (mTemperature > tEnvTemp) mTemperature--;
			}
			
			if (mTemperature > mMaxTemperature) {
				setOnFire();
				if (rng(100) == 0) {
					GarbageGT.trash(mTanks);
					setToFire();
					return;
				}
			}
			
			if (mTanks[i].has()) distribute(mTanks[i], i, tAdjacentPipes, tAdjacentTanks, tAdjacentOther);
			
			mLastReceivedFrom[i] = 0;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void distribute(FluidTankGT aTank, int aIndex, DelegatorTileEntity<MultiTileEntityPipeFluid>[] aAdjacentPipes, DelegatorTileEntity<IFluidHandler>[] aAdjacentTanks, DelegatorTileEntity<TileEntity>[] aAdjacentOther) {
		// Top Priority is filling Cauldrons and other specialties.
		for (byte tSide : ALL_SIDES_VALID) if (aAdjacentOther[tSide] != null) {
			// Covers let distribution happen, right?
			if (isCovered(tSide) && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, aTank.get())) continue;
			
			Block tBlock = aAdjacentOther[tSide].getBlock();
			// Filling up Cauldrons from Vanilla. Yes I need to check for both to make this work. Some Mods override the Cauldron in a bad way.
			if ((tBlock == Blocks.cauldron || tBlock instanceof BlockCauldron) && aTank.has(334) && FL.water(aTank.get())) {
				switch(aAdjacentOther[tSide].getMetaData()) {
				case 0:
					if (aTank.drainAll(1000)) {aAdjacentOther[tSide].setMetaData(3); break;}
					if (aTank.drainAll( 667)) {aAdjacentOther[tSide].setMetaData(2); break;}
					if (aTank.drainAll( 334)) {aAdjacentOther[tSide].setMetaData(1); break;}
					break;
				case 1:
					if (aTank.drainAll( 667)) {aAdjacentOther[tSide].setMetaData(3); break;}
					if (aTank.drainAll( 334)) {aAdjacentOther[tSide].setMetaData(2); break;}
					break;
				case 2:
					if (aTank.drainAll( 334)) {aAdjacentOther[tSide].setMetaData(3); break;}
					break;
				}
			}
		}
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		// Compile all possible Targets into one List.
		List<DelegatorTileEntity> tTanks = new ArrayListNoNulls<>();
		List<FluidTankGT> tPipes = new ArrayListNoNulls<>();
		// Amount to check for Distribution
		long tAmount = aTank.amount();
		// Count all Targets. Also includes THIS for even distribution, thats why it starts at 1.
		int tTargetCount = 1;
		// Put Targets into Lists.
		for (byte tSide : ALL_SIDES_VALID) {
			// Don't you dare flow backwards!
			if (FACE_CONNECTED[tSide][mLastReceivedFrom[aIndex]]) continue;
			// Are we even connected to this Side? (Only gets checked due to the Cover check being slightly expensive)
			if (!canEmitFluidsTo(tSide)) continue;
			// Covers let distribution happen, right?
			if (isCovered(tSide) && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, aTank.get())) continue;
			// Is it a Pipe?
			if (aAdjacentPipes[tSide] != null) {
				// Check if the Pipe can be filled with this Fluid.
				FluidTankGT tTank = (FluidTankGT)aAdjacentPipes[tSide].mTileEntity.getFluidTankFillable(aAdjacentPipes[tSide].mSideOfTileEntity, aTank.get());
				if (tTank != null && tTank.amount() < aTank.amount()) {
					// Gah, I need to do reverse Indexing to make this work with the Parameters I have, because Cover Support...
					for (int i = 0; i < aAdjacentPipes[tSide].mTileEntity.mTanks.length; i++) if (aAdjacentPipes[tSide].mTileEntity.mTanks[i] == tTank) {
						aAdjacentPipes[tSide].mTileEntity.mLastReceivedFrom[i] |= SBIT[aAdjacentPipes[tSide].mSideOfTileEntity];
						break;
					}
					// Add to a random Position in the List.
					tPipes.add(rng(tPipes.size()+1), tTank);
					// For Balancing the Pipe Output.
					tAmount += tTank.amount();
					// One more Target.
					tTargetCount++;
				}
				// Done everything.
				continue;
			}
			// No Tank? Nothing to do then.
			if (aAdjacentTanks[tSide] == null) continue;
			// Check if the Tank can be filled with this Fluid.
			if (aAdjacentTanks[tSide].mTileEntity.fill(aAdjacentTanks[tSide].getForgeSideOfTileEntity(), aTank.make(1), F) > 0 || aAdjacentTanks[tSide].mTileEntity.fill(aAdjacentTanks[tSide].getForgeSideOfTileEntity(), aTank.get(Long.MAX_VALUE), F) > 0) {
				// Add to a random Position in the List.
				tTanks.add(rng(tTanks.size()+1), aAdjacentTanks[tSide]);
				// One more Target.
				tTargetCount++;
				// Done everything.
				continue;
			}
		}
		// No Targets? Nothing to do then.
		if (tTargetCount <= 1) return;
		// Amount to distribute normally.
		if (tAmount % tTargetCount == 0) tAmount /= tTargetCount; else {tAmount /= tTargetCount; tAmount++;}
		// Distribute to Pipes first.
		for (FluidTankGT tPipe : tPipes) mTransferredAmount += aTank.remove(tPipe.add(aTank.amount(tAmount-tPipe.amount()), aTank.get()));
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		// Distribute to Tanks afterwards.
		for (DelegatorTileEntity tTank : tTanks) mTransferredAmount += aTank.remove(FL.fill(tTank, aTank.get(tAmount), T));
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		// No Targets? Nothing to do then.
		if (tPipes.isEmpty()) return;
		// And then if there still is pressure, distribute to Pipes again.
		tAmount = (aTank.amount() - mCapacity/2) / tPipes.size();
		if (tAmount > 0) for (FluidTankGT tPipe : tPipes) mTransferredAmount += aTank.remove(tPipe.add(aTank.amount(tAmount), aTank.get()));
	}
	
	@Override
	public boolean breakBlock() {
		// Do the same thing Factorio does and just dump Fluid to adjacent connected things.
		for (byte tSide : ALL_SIDES_VALID) if (canEmitFluidsTo(tSide)) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			for (FluidTankGT tTank : mTanks) if (tTank.has()) {
				if (isCovered(tSide) && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, tTank.get())) continue;
				mTransferredAmount += FL.move(tTank, tDelegator);
			}
		}
		// And if that doesn't work, go to the trash!
		GarbageGT.trash(mTanks);
		// Drop, uh Inventory? Eh, it is a super Call that is needed regardless, just in case. 
		return super.breakBlock();
	}
	
	@Override
	public void onConnectionChange(byte aPreviousConnections) {
		for (byte tSide : ALL_SIDES_VALID) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
				((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
			}
		}
	}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return mBlocking;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mContactDamage && !mFoamDried && mTemperature > 320) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mTemperature / 100.0F));}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		if (SIDES_VALID[aSide] && !canAcceptFluidsFrom(aSide)) return null;
		for (FluidTankGT tTank : mTanks) if (tTank.contains(aFluidToFill)) return tTank;
		for (FluidTankGT tTank : mTanks) if (tTank.isEmpty()) return tTank;
		return null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (SIDES_VALID[aSide] && !canEmitFluidsTo(aSide)) return null;
		for (FluidTankGT tTank : mTanks) if (tTank.contains(aFluidToDrain)) return tTank;
		return null;
	}
	
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null || aFluid.amount <= 0) return 0;
		IFluidTank tTank = getFluidTankFillable(UT.Code.side(aDirection), aFluid);
		if (tTank == null) return 0;
		int rFilledAmount = tTank.fill(aFluid, aDoFill);
		if (aDoFill) {
			if (rFilledAmount > 0) updateInventory();
			// Gah, I need to do reverse Indexing to make this work with the Parameters I have, because Cover Support...
			for (int i = 0; i < mTanks.length; i++) if (mTanks[i] == tTank) {mLastReceivedFrom[i] |= SBIT[UT.Code.side(aDirection)]; break;}
		}
		return rFilledAmount;
	}
	
	@Override
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof IFluidHandler) {
			// Extenders should always be connectable.
			if (aDelegator.mTileEntity instanceof ITileEntityCanDelegate) return T;
			// Make sure at least one Tank exists at this Side to connect to.
			if (UT.Code.exists(0, ((IFluidHandler)aDelegator.mTileEntity).getTankInfo(aDelegator.getForgeSideOfTileEntity()))) return T;
			// Okay, nothing to do here.
			return F;
		}
		if (mCapacity >= 334) {
			Block tBlock = aDelegator.getBlock();
			// Yes I need to check for both to make this work. Some Mods override the Cauldron in a bad way.
			if (tBlock == Blocks.cauldron || tBlock instanceof BlockCauldron) return T;
		}
		return F;
	}
	
	public boolean canEmitFluidsTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptFluidsFrom                      (byte aSide) {return connected(aSide);}
	
	@Override public long getGibblValue                     (byte aSide) {long rAmount = 0; for (FluidTankGT tTank : mTanks) rAmount += tTank.amount(); return rAmount;}
	@Override public long getGibblMax                       (byte aSide) {return mCapacity * mTanks.length;}
	
	@Override public long getProgressValue                  (byte aSide) {return mTransferredAmount;}
	@Override public long getProgressMax                    (byte aSide) {return mCapacity * mTanks.length;}
	
	@Override public long getTemperatureValue               (byte aSide) {return mTemperature;}
	@Override public long getTemperatureMax                 (byte aSide) {return mMaxTemperature;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {BlockTextureDefault tBase = BlockTextureDefault.get(mMaterial, getIconIndexSide      (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa); switch(mRenderType) {case 1: return BlockTextureMulti.get(tBase, BlockTextureDefault.get(Textures.BlockIcons.PIPE_RESTRICTOR)); default: return tBase;}}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {BlockTextureDefault tBase = BlockTextureDefault.get(mMaterial, getIconIndexConnected (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa); switch(mRenderType) {case 1: return BlockTextureMulti.get(tBase, BlockTextureDefault.get(Textures.BlockIcons.PIPE_RESTRICTOR)); default: return tBase;}}
	
	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return IconsGT.INDEX_BLOCK_PIPE_SIDE;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return mTanks.length>=9?OP.pipeNonuple.mIconIndexBlock:mTanks.length>=4?OP.pipeQuadruple.mIconIndexBlock:aDiameter<0.37F?OP.pipeTiny.mIconIndexBlock:aDiameter<0.49F?OP.pipeSmall.mIconIndexBlock:aDiameter<0.74F?OP.pipeMedium.mIconIndexBlock:aDiameter<0.99F?OP.pipeLarge.mIconIndexBlock:OP.pipeHuge.mIconIndexBlock;}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.PIPE_FLUID.AS_LIST;}
	
	@Override public String getFacingTool() {return TOOL_wrench;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.connector.pipe.fluid";}
}
