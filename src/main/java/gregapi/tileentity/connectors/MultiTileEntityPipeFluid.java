/**
 * Copyright (c) 2020 GregTech-6 Team
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

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
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
	public byte mLastReceivedFrom = 0, mRenderType = 0;
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
		OreDictManager.INSTANCE.setTarget_(OP.pipeTiny             , aMat, aRegistry.add("Tiny " + aMat.getLocal() + " Fluid Pipe"         , "Fluid Pipes", aID   , aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 4], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat    , NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"sP ", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeSmall            , aMat, aRegistry.add("Small " + aMat.getLocal() + " Fluid Pipe"        , "Fluid Pipes", aID+ 1, aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 6], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 2L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{" P ", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeMedium           , aMat, aRegistry.add(aMat.getLocal() + " Fluid Pipe"                   , "Fluid Pipes", aID+ 2, aCreativeTabID, aClass, aMat.mToolQuality, 32, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 8], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 6L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeLarge            , aMat, aRegistry.add("Large " + aMat.getLocal() + " Fluid Pipe"        , "Fluid Pipes", aID+ 3, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[12], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat*12L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeHuge             , aMat, aRegistry.add("Huge " + aMat.getLocal() + " Fluid Pipe"         , "Fluid Pipes", aID+ 4, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat*24L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 1), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateDouble.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeQuadruple        , aMat, aRegistry.add("Quadruple " + aMat.getLocal() + " Fluid Pipe"    , "Fluid Pipes", aID+ 5, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 6L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 4), new Object[]{"PP" , "PP"        , 'P', OP.pipeMedium.dat(aMat)}), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeNonuple          , aMat, aRegistry.add("Nonuple " + aMat.getLocal() + " Fluid Pipe"      , "Fluid Pipes", aID+ 6, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 1.0F, NBT_RESISTANCE, 6.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_CONTACTDAMAGE, aContactDamage, NBT_TANK_CAPACITY, aStat* 2L, NBT_OPAQUE, T, NBT_GASPROOF, aGasProof, NBT_ACIDPROOF, aAcidProof, NBT_PLASMAPROOF, aPlasmaProof, NBT_FLAMMABILITY, aFlammable ? 150 : 0, NBT_TEMPERATURE, aMaxTemperature, NBT_TANK_COUNT, 9), new Object[]{"PPP", "PPP", "PPP", 'P', OP.pipeSmall.dat(aMat)}), T, F, T);
		
		CR.shapeless(aRegistry.getItem(aID+2, 4), CR.DEF_NCC, new Object[] {aRegistry.getItem(aID+5)});
		CR.shapeless(aRegistry.getItem(aID+1, 9), CR.DEF_NCC, new Object[] {aRegistry.getItem(aID+6)});
	}
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.mlast")) mLastReceivedFrom = aNBT.getByte("gt.mlast");
		if (aNBT.hasKey("gt.mtransfer")) mTransferredAmount = aNBT.getLong("gt.mtransfer");
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
		if (aNBT.hasKey(NBT_OPAQUE)) mBlocking = aNBT.getBoolean(NBT_OPAQUE);
		if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		if (aNBT.hasKey(NBT_TANK_CAPACITY)) mCapacity = aNBT.getLong(NBT_TANK_CAPACITY);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mMaxTemperature = aNBT.getLong(NBT_TEMPERATURE);
		if (aNBT.hasKey(NBT_TANK_COUNT)) {
			mTanks = new FluidTankGT[aNBT.getInteger(NBT_TANK_COUNT)];
			for (int i = 0; i < mTanks.length; i++) mTanks[i] = new FluidTankGT(mCapacity);
		} else {
			mTanks = new FluidTankGT(mCapacity).AS_ARRAY;
		}
		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
		
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
		aNBT.setByte("gt.mlast", mLastReceivedFrom);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
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
					boolean temp = T;
					for (FluidTankGT tTank : mTanks) if (!tTank.isEmpty()) {
						temp = F;
						aChatReturn.add(tTank.content());
					}
					if (temp) aChatReturn.add("Pipe is empty");
				}
				return mTanks.length;
			}
		}
		return 0;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.PIPE_STATS_BANDWIDTH) + (mCapacity/2) + " L/t");
		aList.add(Chat.CYAN     + LH.get(LH.PIPE_STATS_CAPACITY) + mCapacity + " L");
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
		
		for (FluidTankGT tTank : mTanks) {
			FluidStack tFluid = tTank.get();
			if (tFluid != null && tFluid.amount > 0) {
				mTemperature = FL.temperature(tFluid);
				if (!mGasProof && FL.gas(tFluid)) {
					mTransferredAmount += Math.min(8, tTank.amount());
					GarbageGT.trash(tTank, 8);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-2, -2, -2, +3, +3, +3))) UT.Entities.applyTemperatureDamage(tEntity, mTemperature, 2.0F);} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				}
				if (!mPlasmaProof && FL.plasma(tFluid)) {
					mTransferredAmount += Math.min(64, tTank.amount());
					GarbageGT.trash(tTank, 64);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-2, -2, -2, +3, +3, +3))) UT.Entities.applyTemperatureDamage(tEntity, mTemperature, 2.0F);} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				}
				if (!mAcidProof && FL.acid(tFluid)) {
					mTransferredAmount += Math.min(16, tTank.amount());
					GarbageGT.trash(tTank, 16);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 0.5F, getCoords());
					try {for (Entity tEntity : (List<Entity>)worldObj.getEntitiesWithinAABB(Entity.class, box(-1, -1, -1, +2, +2, +2))) UT.Entities.applyChemDamage(tEntity, 2);} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
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
			
			if (tTank.has()) distribute(tTank, tAdjacentPipes, tAdjacentTanks, tAdjacentOther);
		}
		
		mLastReceivedFrom = 0;
	}
	
	public void distribute(FluidTankGT aTank, DelegatorTileEntity<MultiTileEntityPipeFluid>[] aAdjacentPipes, DelegatorTileEntity<IFluidHandler>[] aAdjacentTanks, DelegatorTileEntity<TileEntity>[] aAdjacentOther) {
		// Top Priority is filling Cauldrons and other specialties.
		for (byte tSide : ALL_SIDES_VALID) if (aAdjacentOther[tSide] != null) {
			if (hasCovers() && mCovers.mBehaviours[tSide] != null && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, aTank.get())) {
				// Cover says no.
				continue;
			}
			if (aAdjacentOther[tSide].mTileEntity == null) {
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
		}
		
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		
		// Compile all possible Targets into one List.
		@SuppressWarnings("rawtypes")
		List<DelegatorTileEntity> tTargets = new ArrayListNoNulls<>();
		
		// Count all Targets. Also includes THIS for even distribution, thats why it starts at 1.
		int tTargetCount = 1, tAdjacentTankCount = 0, tAdjacentPipeCount = 0;
		
		for (byte tSide : ALL_SIDES_VALID) if (aAdjacentTanks[tSide] != null) {
			if (FACE_CONNECTED[aAdjacentTanks[tSide].mSideOfTileEntity][mLastReceivedFrom]) {
				// Do not return to Sender.
			} else if (hasCovers() && mCovers.mBehaviours[tSide] != null && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, aTank.get())) {
				// Cover says no.
			} else if (aAdjacentTanks[tSide].mTileEntity.fill(aAdjacentTanks[tSide].getForgeSideOfTileEntity(), aTank.make(1), F) > 0) {
				tTargets.add(aAdjacentTanks[tSide]);
				tAdjacentTankCount++;
				tTargetCount++;
			}
		}
		for (byte tSide : ALL_SIDES_VALID) if (aAdjacentPipes[tSide] != null) {
			if (FACE_CONNECTED[aAdjacentPipes[tSide].mSideOfTileEntity][mLastReceivedFrom] && aTank.amount() < 6) {
				// Do not return to Sender, if there is not much Fluid inside.
			} else if (hasCovers() && mCovers.mBehaviours[tSide] != null && mCovers.mBehaviours[tSide].interceptFluidDrain(tSide, mCovers, tSide, aTank.get())) {
				// Cover says no.
			} else {
				FluidTankGT tTank = (FluidTankGT)aAdjacentPipes[tSide].mTileEntity.getFluidTankFillable(aAdjacentPipes[tSide].mSideOfTileEntity, aTank.get());
				if (tTank != null && tTank.amount() < aTank.amount()) {
					tTargets.add(aAdjacentPipes[tSide]);
					tAdjacentPipeCount++;
					tTargetCount++;
				}
			}
		}
		
		// No Targets, nothing to do.
		if (tTargets.isEmpty()) return;
		
		// Distribute to Pipes first.
		if (tAdjacentPipeCount > 0) {
			long tAmount = aTank.amount();
			if (tAmount % tTargetCount == 0) tAmount /= tTargetCount; else {tAmount /= tTargetCount; tAmount++;}
			for (@SuppressWarnings("rawtypes") DelegatorTileEntity tTarget : tTargets) if (tTarget.mTileEntity instanceof MultiTileEntityPipeFluid) {
				FluidTankGT tTank = (FluidTankGT)((MultiTileEntityPipeFluid)tTarget.mTileEntity).getFluidTankFillable2(tTarget.mSideOfTileEntity, aTank.get());
				if (tTank != null) {
					long tMoved = aTank.remove(tTank.add(Math.min(aTank.amount(), tAmount), aTank.get()));
					if (tMoved > 0) {
						mTransferredAmount += tMoved;
						if (tMoved < 6) ((MultiTileEntityPipeFluid)tTarget.mTileEntity).mLastReceivedFrom |= SBIT[tTarget.mSideOfTileEntity];
					}
				}
			}
		}
		
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		
		// Distribute to Tanks afterwards.
		if (tAdjacentTankCount > 0) {
			long tAmount = aTank.amount();
			if (tAmount % tAdjacentTankCount == 0) tAmount /= tAdjacentTankCount; else {tAmount /= tAdjacentTankCount; tAmount++;}
			for (@SuppressWarnings("rawtypes") DelegatorTileEntity tTarget : tTargets) if (!(tTarget.mTileEntity instanceof MultiTileEntityPipeFluid)) {
				mTransferredAmount += aTank.remove(FL.fill_(tTarget, aTank.get(tAmount), T));
			}
		}
		
		// Check if we are empty.
		if (aTank.isEmpty()) return;
		
		// And then if there still is pressure, distribute to Pipes again.
		if (tAdjacentPipeCount > 0 && aTank.amount() > mCapacity/2) {
			long tAmount = (aTank.amount() - mCapacity/2) / tAdjacentPipeCount;
			for (@SuppressWarnings("rawtypes") DelegatorTileEntity tTarget : tTargets) if (tTarget.mTileEntity instanceof MultiTileEntityPipeFluid) {
				FluidTankGT tTank = (FluidTankGT)((MultiTileEntityPipeFluid)tTarget.mTileEntity).getFluidTankFillable2(tTarget.mSideOfTileEntity, aTank.get());
				if (tTank != null) {
					long tMoved = aTank.remove(tTank.add(Math.min(aTank.amount(), tAmount), aTank.get()));
					if (tMoved > 0) {
						mTransferredAmount += tMoved;
						if (tMoved < 6) ((MultiTileEntityPipeFluid)tTarget.mTileEntity).mLastReceivedFrom |= SBIT[tTarget.mSideOfTileEntity];
					}
				}
			}
		}
	}
	
	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mTanks);
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
		if (rFilledAmount > 0 && aDoFill) {
			mLastReceivedFrom |= SBIT[UT.Code.side(aDirection)];
			updateInventory();
		}
		return rFilledAmount;
	}
	
	public boolean canEmitFluidsTo                          (byte aSide) {return connected(aSide);}
	public boolean canAcceptFluidsFrom                      (byte aSide) {return connected(aSide);}
	
	@Override public boolean canConnect                     (byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {return (aDelegator.mTileEntity instanceof IFluidHandler ? aDelegator.mTileEntity instanceof ITileEntityCanDelegate || UT.Code.exists(0, ((IFluidHandler)aDelegator.mTileEntity).getTankInfo(aDelegator.getForgeSideOfTileEntity())) : mCapacity >= 334 && aDelegator.getBlock() instanceof BlockCauldron);}
	
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
