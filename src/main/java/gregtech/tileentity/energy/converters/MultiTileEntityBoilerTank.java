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

package gregtech.tileentity.energy.converters;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_RemovedByPlayer;
import gregapi.code.TagData;
import gregapi.data.BI;
import gregapi.data.CS.GarbageGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBoilerTank extends TileEntityBase09FacingSingle implements ITileEntityEnergy, ITileEntityFunnelAccessible, ITileEntityGibbl, ITileEntityEnergyDataCapacitor, IFluidHandler, IMTE_RemovedByPlayer, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	protected byte mBarometer = 0, oBarometer = 0;
	protected short mEfficiency = 10000, mCoolDownResetTimer = 128;
	protected long mEnergy = 0, mCapacity = 640000, mOutput = 6400;
	protected TagData mEnergyTypeAccepted = TD.Energy.HU;
	protected FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(4000), new FluidTankGT(64000)};
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_VISUAL)) mBarometer = aNBT.getByte(NBT_VISUAL);
		if (aNBT.hasKey(NBT_CAPACITY)) mCapacity = aNBT.getLong(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_CAPACITY_SU)) mTanks[1].setCapacity(aNBT.getLong(NBT_CAPACITY_SU));
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mOutput = aNBT.getLong(NBT_OUTPUT_SU);
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		if (mEfficiency != 10000) aNBT.setShort(NBT_EFFICIENCY, mEfficiency);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.CONVERTS_FROM_X)        + " 1 L " + FL.name(FluidRegistry.WATER, T) + " " + LH.get(LH.CONVERTS_TO_Y) + " 160 L " + FL.name(FL.Steam.make(0), T) + " " + LH.get(LH.CONVERTS_USING_Z) + " 80 " + mEnergyTypeAccepted.getLocalisedNameShort());
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT)           + ": " + Chat.WHITE + (mOutput/STEAM_PER_EU)                        + " " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort()   + Chat.WHITE + "/t ("+LH.get(LH.FACE_ANY)+")");
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mCapacity                                     + " " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort()   + Chat.WHITE);
		aList.add(Chat.RED      + LH.get(LH.ENERGY_OUTPUT)          + ": " + Chat.WHITE + UT.Code.units(mOutput, 10000, mEfficiency, F) + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE + "/t ("+LH.get(LH.FACE_TOP)+")");
		aList.add(Chat.RED      + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mCapacity                                     + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE);
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_WATER_PURE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_EXPLOSION_STEAM));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DECALCIFY_CHISEL));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		
		if (aIsServerSide) {
			// Convert Water to Steam
			long tConversions = Math.min(mTanks[1].capacity() / 2560, Math.min(mEnergy / 80, mTanks[0].amount()));
			if (tConversions > 0) {
				mTanks[0].remove(tConversions);
				if (rng(10) == 0 && mEfficiency > 5000 && !FL.distw(mTanks[0])) {
					mEfficiency -= tConversions;
					if (mEfficiency < 5000) mEfficiency = 5000;
				}
				mTanks[1].setFluid(FL.Steam.make(mTanks[1].amount() + UT.Code.units(tConversions, 10000, mEfficiency * 160, F)));
				mEnergy -= tConversions * 80;
				mCoolDownResetTimer = 128;
			}
			
			// Remove Steam and Heat during the process of cooling down.
			if (mCoolDownResetTimer-- <= 0) {
				mCoolDownResetTimer = 0;
				mEnergy -= (mOutput * 4) / STEAM_PER_EU;
				GarbageGT.trash(mTanks[1], mOutput * 4);
				if (mEnergy <= 0) {
					mEnergy = 0;
					mCoolDownResetTimer = 128;
				}
			}
			
			long tAmount = mTanks[1].amount() - mTanks[1].capacity() / 2;
			
			// Emit Steam
			if (tAmount > 0) FL.move(mTanks[1], getAdjacentTank(SIDE_UP), Math.min(tAmount > mTanks[1].capacity() / 4 ? mOutput * 2 : mOutput, tAmount));
			
			// Set Barometer
			mBarometer = (byte)UT.Code.scale(mTanks[1].amount(), mTanks[1].capacity(), 31, F);
			
			// Well the Boiler gets structural Damage when being too hot, or when being too full of Steam.
			if (mEnergy > mCapacity || mTanks[1].isFull()) {
				explode(F);
			}
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_plunger)) return GarbageGT.trash(mTanks[0]);
		if (aTool.equals(TOOL_chisel)) {
			int rResult = 10000 - mEfficiency;
			if (rResult > 0) {
				if (mBarometer > 15) {
					explode(F);
				} else {
					if (mEnergy+mTanks[1].amount()/STEAM_PER_EU > 2000) UT.Entities.applyHeatDamage(aPlayer, (mEnergy+mTanks[1].amount()/2) / 2000.0F);
					mTanks[1].setEmpty();
					mEfficiency = 10000;
					mEnergy = 0;
					return rResult;
				}
			}
			return 0;
		}
		
		if (aTool.equals(TOOL_thermometer)) {
			if (aChatReturn != null) aChatReturn.add("Stored Heat Units: " + mEnergy + " / " + mCapacity + " HU");
			return 10000;
		}
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (mEfficiency < 10000) {
					aChatReturn.add("Calcification: " + LH.percent(10000 - mEfficiency) + "%");
				} else {
					aChatReturn.add("No Calcification in this Boiler");
				}
				aChatReturn.add(mTanks[0].content("WARNING: NO WATER!!!"));
			}
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public boolean removedByPlayer(World aWorld, EntityPlayer aPlayer, boolean aWillHarvest) {
		if (mBarometer > 4 && isServerSide() && !UT.Entities.isCreative(aPlayer)) explode(T);
		return worldObj.setBlockToAir(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void onExploded(Explosion aExplosion) {
		super.onExploded(aExplosion);
		if (isServerSide() && mBarometer > 4) explode(T);
	}
	
	@Override
	public void explode(boolean aInstant) {
		explode(aInstant, Math.max(1, Math.sqrt(mTanks[1].amount()) / 100.0));
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mBarometer = UT.Code.bind5(mBarometer);
		return mBarometer != oBarometer || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oBarometer = mBarometer;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mBarometer = (byte)(aData&31);
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return FL.water(aFluid) ? mTanks[0].fill(aFluid, aDoFill) : 0;
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]]), aSide!=mFacing?null:BlockTextureDefault.get(BI.BAROMETER), aSide!=mFacing?null:BlockTextureDefault.get(BI.BAROMETER_SCALE[mBarometer], CA_RED_64)) : null;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mEnergy+mTanks[1].amount()/STEAM_PER_EU > 2000) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, (mEnergy+mTanks[1].amount()/2) / 2000.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[2], PX_P[2], PX_P[2], PX_N[2], PX_N[2], PX_N[2]);}
	
	@Override public byte getVisualData() {return mBarometer;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {mEnergy += Math.abs(aAmount * aSize); mCoolDownResetTimer = (short)Math.max(mCoolDownResetTimer, 32);} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return mOutput/2;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mOutput/2;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mCapacity : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return SIDES_BOTTOM_HORIZONTAL[aSide] && FL.water(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public long getGibblValue(byte aSide) {return mTanks[1].amount();}
	@Override public long getGibblMax(byte aSide) {return mTanks[1].capacity();}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/side")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/side")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.tank.boiler_steam";}
}
