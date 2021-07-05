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

package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.data.ITileEntityWeight;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.machines.ITileEntityCrucible;
import gregapi.tileentity.machines.ITileEntityMold;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.IMultiBlockInventory;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrucible extends TileEntityBase10MultiBlockBase implements ITileEntityCrucible, ITileEntityEnergy, ITileEntityWeight, ITileEntityTemperature, ITileEntityMold, ITileEntityServerTickPost, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy, IMultiBlockInventory, IMultiBlockFluidHandler, IFluidHandler {
	private static int GAS_RANGE = 5, FLAME_RANGE = 5;
	private static long MAX_AMOUNT = 16*3*3*3*U, KG_PER_ENERGY = 100;
	private static double HEAT_RESISTANCE_BONUS = 1.10;
	
	protected boolean mAcidProof = F, mMeltDown = F;
	protected byte mDisplayedHeight = 0, mCooldown = 100;
	protected short mDisplayedFluid = -1;
	protected long mEnergy = 0, mTemperature = DEF_ENV_TEMP, oTemperature = 0;
	protected List<OreDictMaterialStack> mContent = new ArrayListNoNulls<>();
	
	public short mWalls = 18002;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_DESIGN)) mWalls = aNBT.getShort(NBT_DESIGN);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_TEMPERATURE)) mTemperature = aNBT.getLong(NBT_TEMPERATURE);
		if (aNBT.hasKey(NBT_TEMPERATURE+".old")) oTemperature = aNBT.getLong(NBT_TEMPERATURE+".old");
		mContent = OreDictMaterialStack.loadList(NBT_MATERIALS, aNBT);
		mMeltDown = (mTemperature+100 > getTemperatureMax(SIDE_ANY));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setNumber(aNBT, NBT_TEMPERATURE, mTemperature);
		UT.NBT.setNumber(aNBT, NBT_TEMPERATURE+".old", oTemperature);
		OreDictMaterialStack.saveList(NBT_MATERIALS, aNBT, mContent);
	}
	
	@Override
	public boolean checkStructure2() {
		boolean tSuccess = T;
		
		if (getAir(xCoord, yCoord+1, zCoord)) worldObj.setBlockToAir(xCoord, yCoord+1, zCoord); else tSuccess = F;
		if (getAir(xCoord, yCoord+2, zCoord)) worldObj.setBlockToAir(xCoord, yCoord+2, zCoord); else tSuccess = F;
		
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 || j != 0) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 0, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 1, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_CRUCIBLE)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 2, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
		}
		
		if (tSuccess) for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 || j != 0) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 0, j, mWalls, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 1, j, mWalls, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.ONLY_CRUCIBLE)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 2, j, mWalls, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
		}
		
		return tSuccess;
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return aX >= xCoord - 1 && aY >= yCoord && aZ >= zCoord - 1 && aX <= xCoord + 1 && aY <= yCoord + 2 && aZ <= zCoord + 1;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.crucible.1", "3x3x3 Hollow of Walls with opening on Top.");
		LH.add("gt.tooltip.multiblock.crucible.2", "Main at Bottom-Center.");
		LH.add("gt.tooltip.multiblock.crucible.3", "Energy IN from Bottom Layer, Stuff IN from Top Layer.");
		LH.add("gt.tooltip.multiblock.crucible.4", "Molds usable at second Layer of Walls");
		LH.add("gt.tooltip.multiblock.crucible.5", "KU at Bottom Layer will turn into Air for Steelmaking");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.crucible.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.crucible.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.crucible.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.crucible.4"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.crucible.5"));
		aList.add(Chat.CYAN     + LH.get(LH.CONVERTS_FROM_X) + " 1 " + TD.Energy.HU.getLocalisedNameShort() + " " + LH.get(LH.CONVERTS_TO_Y) + " +1 K " + LH.get(LH.CONVERTS_PER_Z) + " "+ KG_PER_ENERGY + "kg (at least "+getEnergySizeInputMin(TD.Energy.HU, SIDE_ANY)+" Units per Tick required!)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + getTemperatureMax(SIDE_ANY) + " K)");
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_REMOVE_SHOVEL));
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_POST.remove(this);
		onUnregisterPost();
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onServerTickPost(boolean aFirst) {
		long tTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		
		if (!checkStructure(F)) {
			if (SERVER_TIME % 10 == 0) {if (mTemperature > tTemperature) mTemperature--; if (mTemperature < tTemperature) mTemperature++;}
			mTemperature = Math.max(mTemperature, Math.min(200, tTemperature));
			return;
		}
		
		if (!slotHas(0)) slot(0, WD.suck(worldObj, xCoord-0.5, yCoord+PX_P[2], zCoord-0.5, 2, 3, 2));
		
		ItemStack tStack = slot(0);
		
		if (ST.valid(tStack)) {
			OreDictItemData tData = OM.anydata_(tStack);
			if (tData == null) {
				slotTrash(0);
				UT.Sounds.send(SFX.MC_FIZZ, this);
			} else if (tData.mPrefix == null) {
				List<OreDictMaterialStack> tList = new ArrayListNoNulls<>();
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) if (tMaterial.mAmount > 0) tList.add(tMaterial.clone());
				if (addMaterialStacks(tList, tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix == OP.oreRaw) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier     )), tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix == OP.blockRaw) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier *  9)), tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix == OP.crateGtRaw) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier * 16)), tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix == OP.crateGt64Raw) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier * 64)), tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix.contains(TD.Prefix.STANDARD_ORE)) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier     )), tTemperature)) decrStackSize(0, 1);
			} else if (tData.mPrefix.contains(TD.Prefix.DENSE_ORE)) {
				if (addMaterialStacks(Arrays.asList(OM.stack(tData.mMaterial.mMaterial.mTargetCrushing.mMaterial, tData.mMaterial.mMaterial.mTargetCrushing.mAmount * tData.mMaterial.mMaterial.mOreMultiplier *  2)), tTemperature)) decrStackSize(0, 1);
			} else {
				List<OreDictMaterialStack> tList = new ArrayListNoNulls<>();
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) if (tMaterial.mAmount > 0) tList.add(tMaterial.clone());
				if (addMaterialStacks(tList, tTemperature)) decrStackSize(0, 1);
			}
		}
		
		Set<OreDictMaterial> tAlreadyCheckedAlloys = new HashSetNoNulls<>();
		
		OreDictMaterial tPreferredAlloy = null;
		IOreDictConfigurationComponent tPreferredRecipe = null;
		long tMaxConversions = 0;
		
		for (OreDictMaterialStack tMaterial : mContent) {
			if (mTemperature >= tMaterial.mMaterial.mMeltingPoint) {
				for (OreDictMaterial tAlloy : tMaterial.mMaterial.mAlloyComponentReferences) if (tAlreadyCheckedAlloys.add(tAlloy) && mTemperature >= tAlloy.mMeltingPoint) {
					for (IOreDictConfigurationComponent tAlloyRecipe : tAlloy.mAlloyCreationRecipes) {
						List<OreDictMaterialStack> tNeededStuff = new ArrayListNoNulls<>();
						for (OreDictMaterialStack tComponent : tAlloyRecipe.getUndividedComponents()) {
							tNeededStuff.add(OM.stack(tComponent.mMaterial, Math.max(1, tComponent.mAmount / U)));
						}
						
						if (!tNeededStuff.isEmpty()) {
							int tNonMolten = 0;
							
							boolean tBreak = F;
							long tConversions = Long.MAX_VALUE;
							for (OreDictMaterialStack tComponent : tNeededStuff) {
								if (mTemperature < tComponent.mMaterial.mMeltingPoint) tNonMolten++;
								
								tBreak = T;
								for (OreDictMaterialStack tContent : mContent) {
									if (tContent.mMaterial == tComponent.mMaterial) {
										tConversions = Math.min(tConversions, tContent.mAmount / tComponent.mAmount);
										tBreak = F;
										break;
									}
								}
								if (tBreak) break;
							}
							
							if (!tBreak && tNonMolten <= 1 && tConversions > 0) {
								if (tPreferredAlloy == null || tPreferredRecipe == null || tConversions * tAlloyRecipe.getCommonDivider() > tMaxConversions * tPreferredRecipe.getCommonDivider()) {
									tMaxConversions = tConversions;
									tPreferredRecipe = tAlloyRecipe;
									tPreferredAlloy = tAlloy;
								}
							}
						}
					}
				}
			}
		}
		
		if (tPreferredAlloy != null && tPreferredRecipe != null) {
			for (OreDictMaterialStack tComponent : tPreferredRecipe.getUndividedComponents()) {
				for (OreDictMaterialStack tContent : mContent) {
					if (tContent.mMaterial == tComponent.mMaterial) {
						tContent.mAmount -= UT.Code.units_(tMaxConversions, U, tComponent.mAmount, T);
						break;
					}
				}
			}
			OM.stack(tPreferredAlloy, tPreferredRecipe.getCommonDivider() * tMaxConversions).addToList(mContent);
		}
		
		List<OreDictMaterialStack> tToBeAdded = new ArrayListNoNulls<>();
		for (int i = 0; i < mContent.size(); i++) {
			OreDictMaterialStack tMaterial = mContent.get(i);
			if (tMaterial == null || tMaterial.mMaterial == MT.NULL || tMaterial.mMaterial == MT.Air || tMaterial.mAmount <= 0) {
				GarbageGT.trash(mContent.remove(i--));
			} else if (tMaterial.mMaterial.mGramPerCubicCentimeter <= WEIGHT_AIR_G_PER_CUBIC_CENTIMETER) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
			} else if (mTemperature >= tMaterial.mMaterial.mBoilingPoint || (mTemperature > C + 40 && tMaterial.mMaterial.contains(TD.Properties.FLAMMABLE) && !tMaterial.mMaterial.containsAny(TD.Properties.UNBURNABLE, TD.Processing.MELTING))) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
				if (tMaterial.mMaterial.mBoilingPoint >=  320) try {for (EntityLivingBase tLiving : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box(-GAS_RANGE, -1, -GAS_RANGE, GAS_RANGE+1, GAS_RANGE+1, GAS_RANGE+1))) UT.Entities.applyHeatDamage(tLiving, (tMaterial.mMaterial.mBoilingPoint - 300) / 25.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
				if (tMaterial.mMaterial.mBoilingPoint >= 2000) for (int j = 0, k = Math.max(1, UT.Code.bindInt((9 * tMaterial.mAmount) / U)); j < k; j++) WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), rng(3) != 0);
				if (tMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) explode(UT.Code.scale(tMaterial.mAmount, MAX_AMOUNT, 8, F));
				return;
			} else if (!mAcidProof && tMaterial.mMaterial.contains(TD.Properties.ACID)) {
				GarbageGT.trash(mContent.remove(i--));
				UT.Sounds.send(SFX.MC_FIZZ, this);
				setToAir();
				return;
			} else if (mTemperature >= tMaterial.mMaterial.mMeltingPoint && oTemperature <  tMaterial.mMaterial.mMeltingPoint) {
				mContent.remove(i--);
				OM.stack(tMaterial.mMaterial.mTargetSmelting.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSmelting.mAmount, F)).addToList(tToBeAdded);
			} else if (mTemperature <  tMaterial.mMaterial.mMeltingPoint && oTemperature >= tMaterial.mMaterial.mMeltingPoint) {
				mContent.remove(i--);
				OM.stack(tMaterial.mMaterial.mTargetSolidifying.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSolidifying.mAmount, F)).addToList(tToBeAdded);
			}
		}
		for (int i = 0; i < tToBeAdded.size(); i++) {
			OreDictMaterialStack tMaterial = tToBeAdded.get(i);
			if (tMaterial == null || tMaterial.mMaterial == MT.NULL || tMaterial.mMaterial == MT.Air || tMaterial.mAmount <= 0) {
				GarbageGT.trash(tToBeAdded.remove(i--));
			} else {
				tMaterial.addToList(mContent);
			}
		}
		
		double tWeight = mMaterial.getWeight(U*100);
		long tTotal = 0;
		OreDictMaterialStack tLightest = null;
		
		for (OreDictMaterialStack tMaterial : mContent) {
			if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
			tWeight += tMaterial.weight();
			tTotal += tMaterial.mAmount;
		}
		
		oTemperature = mTemperature;
		
		short tDisplayedFluid = mDisplayedFluid, tDisplayedHeight = mDisplayedHeight;
		mDisplayedHeight = (byte)UT.Code.scale(tTotal, MAX_AMOUNT, 255, F);
		mDisplayedFluid = (tLightest == null || tLightest.mMaterial.mMeltingPoint > mTemperature ? -1 : tLightest.mMaterial.mID);
		if (mDisplayedFluid != tDisplayedFluid || mDisplayedHeight != tDisplayedHeight) updateClientData();
		
		long tRequiredEnergy = 1 + (long)(tWeight / KG_PER_ENERGY), tConversions = mEnergy / tRequiredEnergy;
		
		if (mCooldown > 0) mCooldown--;
		
		if (tConversions != 0) {
			mEnergy -= tConversions * tRequiredEnergy;
			mTemperature += tConversions;
			mCooldown = 100;
		}
		
		if (mCooldown <= 0) {mCooldown = 10; if (mTemperature > tTemperature) mTemperature--; if (mTemperature < tTemperature) mTemperature++;}
		
		mTemperature = Math.max(mTemperature, Math.min(200, tTemperature));
		
		if (mTemperature > getTemperatureMax(SIDE_INSIDE)) {
			UT.Sounds.send(SFX.MC_FIZZ, this);
			if (mTemperature >=  320) try {for (EntityLivingBase tLiving : (List<EntityLivingBase>)worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box(-GAS_RANGE, -1, -GAS_RANGE, GAS_RANGE+1, GAS_RANGE+1, GAS_RANGE+1))) UT.Entities.applyHeatDamage(tLiving, (mTemperature - 300) / 25.0F);} catch(Throwable e) {e.printStackTrace(ERR);}
			for (int j = 0, k = UT.Code.bindInt(mTemperature / 25); j < k; j++) WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), rng(3) != 0);
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
				worldObj.setBlock(xCoord+i, yCoord  , zCoord+j, Blocks.flowing_lava, 1, 3);
				worldObj.setBlock(xCoord+i, yCoord+1, zCoord+j, Blocks.flowing_lava, 1, 3);
				worldObj.setBlock(xCoord+i, yCoord+2, zCoord+j, Blocks.flowing_lava, 1, 3);
			}
			return;
		}
		
		if (mMeltDown != (mTemperature+100 > getTemperatureMax(SIDE_ANY))) {
			mMeltDown = !mMeltDown;
			updateClientData();
		}
	}
	
	public boolean addMaterialStacks(List<OreDictMaterialStack> aList, long aTemperature) {
		if (checkStructure(F) && OM.total(mContent)+OM.total(aList) <= MAX_AMOUNT) {
			double tWeight1 = OM.weight(mContent)+mMaterial.getWeight(U*7), tWeight2 = OM.weight(aList);
			if (tWeight1+tWeight2 > 0) mTemperature = aTemperature + (mTemperature>aTemperature?+1:-1)*UT.Code.units(Math.abs(mTemperature - aTemperature), (long)(tWeight1+tWeight2), (long)tWeight1, F);
			for (OreDictMaterialStack tMaterial : aList) {
				if (mTemperature >= tMaterial.mMaterial.mMeltingPoint) {
					if (aTemperature <  tMaterial.mMaterial.mMeltingPoint) {
						OM.stack(tMaterial.mMaterial.mTargetSmelting.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSmelting.mAmount, F)).addToList(mContent);
					} else {
						tMaterial.addToList(mContent);
					}
				} else {
					if (aTemperature >= tMaterial.mMaterial.mMeltingPoint) {
						OM.stack(tMaterial.mMaterial.mTargetSolidifying.mMaterial, UT.Code.units_(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSolidifying.mAmount, F)).addToList(mContent);
					} else {
						tMaterial.addToList(mContent);
					}
				}
			}
			return T;
		}
		return F;
	}
	
	@Override
	public long getTemperatureValue(byte aSide) {
		return mTemperature;
	}
	
	@Override
	public long getTemperatureMax(byte aSide) {
		return (long)(mMaterial.mMeltingPoint * HEAT_RESISTANCE_BONUS);
	}
	
	@Override
	public boolean isMoldInputSide(byte aSide) {
		return SIDES_TOP[aSide] && checkStructure(F);
	}
	
	@Override
	public long getMoldMaxTemperature() {
		return getTemperatureMax(SIDE_INSIDE);
	}
	
	@Override
	public long getMoldRequiredMaterialUnits() {
		return 1;
	}
	
	@Override
	public long fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide) {
		if (isMoldInputSide(aSide)) {
			if (addMaterialStacks(Arrays.asList(aMaterial), aTemperature)) return aMaterial.mAmount;
			if (aMaterial.mAmount > U && addMaterialStacks(Arrays.asList(OM.stack(aMaterial.mMaterial, U)), aTemperature)) return U;
		}
		return 0;
	}
	
	@Override public double getWeightValue(byte aSide) {return OM.weight(mContent);}
	
	@Override
	public boolean breakBlock() {
		while (!mContent.isEmpty()) GarbageGT.trash(mContent.remove(0));
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 || j != 0) {
			ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 0, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN);
			ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 1, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_CRUCIBLE);
			ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 2, j, mWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID);
		}
		return super.breakBlock();
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!checkStructure(F)) return F;
		if (SIDES_TOP[aSide]) {
			if (isServerSide() && aPlayer != null) {
				ItemStack aStack = aPlayer.getCurrentEquippedItem();
				OreDictMaterialStack tLightest = null;
				for (OreDictMaterialStack tMaterial : mContent) if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
				
				if (slotHas(0)) {
					if (aStack == null) {
						aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, slotTake(0));
						if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
						return T;
					}
				} else {
					if (tLightest != null && mTemperature < tLightest.mMaterial.mMeltingPoint) {
						ItemStack tOutputStack = OP.scrapGt.mat(tLightest.mMaterial, 1);
						if (tOutputStack == null || tLightest.mAmount < OP.scrapGt.mAmount) {
							tLightest.mAmount = 0;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
						if (aStack == null) {
							aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, tOutputStack);
							tLightest.mAmount-=OP.scrapGt.mAmount;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
						if (ST.equal(aStack, tOutputStack) && aStack.stackSize < aStack.getMaxStackSize()) {
							aStack.stackSize++;
							tLightest.mAmount-=OP.scrapGt.mAmount;
							aPlayer.addExhaustion(0.1F);
							if (mTemperature > 40 + C) UT.Entities.applyHeatDamage(aPlayer, Math.min(10.0F, mTemperature / 100.0F));
							return T;
						}
					}
				}
				if (aStack != null) {
					FluidStack tFluid = FL.getFluid(ST.amount(1, aStack), T);
					if (tFluid == null) {
						if (tLightest != null && tLightest.mMaterial.mLiquid != null) {
							long tTemperature = FL.temperature(tLightest.mMaterial.mLiquid);
							if (mTemperature >= tLightest.mMaterial.mMeltingPoint && (tTemperature < 320 || mTemperature >= tTemperature)) {
								tFluid = tLightest.mMaterial.liquid(tLightest.mAmount, F);
								if (!FL.Error.is(tFluid)) {
									int tAmount = tFluid.amount;
									ItemStack tStack = FL.fill(tFluid, ST.amount(1, aStack), T, T, T, T);
									if (ST.valid(tStack)) {
										tLightest.mAmount -= UT.Code.units(tAmount - tFluid.amount, tLightest.mMaterial.mLiquid.amount, tLightest.mMaterial.mLiquidUnit, T);
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								}
							}
						}
					} else {
						if (!FL.gas(tFluid, T) && !FL.acid(tFluid)) {
							ItemStack tStack = ST.container(ST.amount(1, aStack), T);
							OreDictMaterialStack tFluidData = OreDictMaterial.FLUID_MAP.get(tFluid.getFluid().getName());
							if (tFluidData != null) {
								if (FL.equal(tFluidData.mMaterial.mLiquid, tFluid)) {
									if (addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(tFluidData.mMaterial, UT.Code.units(tFluid.amount, tFluidData.mMaterial.mLiquid.amount, tFluidData.mMaterial.mLiquidUnit, F))), UT.Code.bind(FL.temperature(tFluid), tFluidData.mMaterial.mMeltingPoint+25, tFluidData.mMaterial.mBoilingPoint-1))) {
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								} else {
									if (addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(tFluidData.mMaterial, UT.Code.units(tFluid.amount, tFluidData.mAmount, U, F))), UT.Code.bind(FL.temperature(tFluid), tFluidData.mMaterial.mMeltingPoint+25, tFluidData.mMaterial.mBoilingPoint-1))) {
										aStack.stackSize--;
										UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
										return T;
									}
								}
							}
						}
					}
				}
			}
			return T;
		}
		return F;
	}
	
	@Override
	public boolean fillMoldAtSide(ITileEntityMold aMold, byte aSide, byte aSideOfMold) {
		if (checkStructure(F)) for (OreDictMaterialStack tContent : mContent) if (tContent != null && mTemperature >= tContent.mMaterial.mMeltingPoint) {
			long tAmount = aMold.fillMold(tContent, mTemperature, aSideOfMold);
			if (tAmount > 0) {
				tContent.mAmount -= tAmount;
				return T;
			}
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {if (aChatReturn != null) aChatReturn.add("Temperature: " + mTemperature + "K"); return 10000;}
		if (aTool.equals(TOOL_shovel) && SIDES_TOP[aSide] && checkStructure(F) && aPlayer instanceof EntityPlayer) {
			OreDictMaterialStack tLightest = null;
			for (OreDictMaterialStack tMaterial : mContent) if (tLightest == null || tMaterial.mMaterial.mGramPerCubicCentimeter < tLightest.mMaterial.mGramPerCubicCentimeter) tLightest = tMaterial;
			if (tLightest != null && mTemperature < tLightest.mMaterial.mMeltingPoint) {
				if (tLightest.mAmount < OP.scrapGt.mAmount) {
					tLightest.mAmount = 0;
					((EntityPlayer)aPlayer).addExhaustion(0.1F);
					return 500;
				}
				ItemStack tOutputStack = OP.scrapGt.mat(tLightest.mMaterial, UT.Code.bindStack(tLightest.mAmount / OP.scrapGt.mAmount));
				if (tOutputStack == null) {
					tLightest.mAmount = 0;
					((EntityPlayer)aPlayer).addExhaustion(0.1F);
					return 500;
				}
				if (UT.Inventories.addStackToPlayerInventory((EntityPlayer)aPlayer, tOutputStack)) {
					((EntityPlayer)aPlayer).addExhaustion(0.1F * tOutputStack.stackSize);
					tLightest.mAmount -= OP.scrapGt.mAmount * tOutputStack.stackSize;
					return 1000 * tOutputStack.stackSize;
				}
				return 0;
			}
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		mTemperature = WD.envTemp(worldObj, xCoord, yCoord, zCoord);
		return T;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(T, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(), mDisplayedHeight, UT.Code.toByteS(mDisplayedFluid, 0), UT.Code.toByteS(mDisplayedFluid, 1), (byte)(mMeltDown ? 1 : 0));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplayedHeight = aData[5];
		mDisplayedFluid = UT.Code.combine(aData[6], aData[7]);
		if (aData.length >= 9) mMeltDown = (aData[8] != 0);
		return super.receiveDataByteArray(aData, aNetworkHandler);
	}
	
	public int mRenderedRGBA = UNCOLORED;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		short[] tRGBaArray = UT.Code.getRGBaArray(mRGBa);
		if (mMeltDown) {
			tRGBaArray[0] = UT.Code.bind8(tRGBaArray[0]*2+50);
			tRGBaArray[1] = UT.Code.bind8(tRGBaArray[1]*2+50);
			tRGBaArray[2] = UT.Code.bind8(tRGBaArray[2]/2+50);
		}
		mRenderedRGBA = UT.Code.getRGBaInt(tRGBaArray);
		
		if (UT.Code.exists(mDisplayedFluid, OreDictMaterial.MATERIAL_ARRAY)) {
			mTextureMolten = OreDictMaterial.MATERIAL_ARRAY[mDisplayedFluid].getTextureMolten();
		} else {
			mTextureMolten = BlockTextureDefault.get(MT.NULL, OP.blockRaw, CA_GRAY_64, F);
		}
		return 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (mStructureOkay) switch(aRenderPass) {
		case  0: box(aBlock,-0.999, 0.0,-0.999,-0.500, 3.000, 1.999); return T;
		case  1: box(aBlock,-0.999, 0.0,-0.999, 1.999, 3.000,-0.500); return T;
		case  2: box(aBlock, 1.500, 0.0,-0.999, 1.999, 3.000, 1.999); return T;
		case  3: box(aBlock,-0.999, 0.0, 1.500, 1.999, 3.000, 1.999); return T;
		case  4: box(aBlock,-0.999, 0.0,-0.999, 1.999, 1.125, 1.999); return T;
		case  5: box(aBlock,-0.999, 0.0,-0.999, 1.999, 1.125+(UT.Code.unsignB(mDisplayedHeight) / 150.0), 1.999); return T;
		}
		return T;
	}
	
	private ITexture mTextureMolten;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: case  2: return SIDES_AXIS_Z[aSide]||aSide==SIDE_BOTTOM?null:BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRenderedRGBA, T), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3], T));
		case  1: case  3: return SIDES_AXIS_X[aSide]||aSide==SIDE_BOTTOM?null:BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRenderedRGBA, T), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3], T));
		case  4: return SIDES_VERTICAL[aSide]?BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRenderedRGBA, T), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3], T)):null;
		case  5: return mDisplayedHeight != 0 && SIDES_TOP[aSide]?mTextureMolten:null;
		}
		return BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRenderedRGBA, T), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3], T));
	}
	
	@Override
	public void onWalkOver2(EntityLivingBase aEntity) {
		super.onWalkOver2(aEntity);
		
		if (mTemperature > 320 && UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mTemperature / 100.0F))) {
			if (!aEntity.isEntityAlive()) {
				if (aEntity instanceof EntityVillager || aEntity instanceof EntityWitch) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(2*U, MT.SoylentGreen)), C+37);
				} else if (aEntity instanceof EntitySnowman) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(4*U, MT.Snow)), C-10);
				} else if (aEntity instanceof EntityIronGolem) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(4*U, MT.Fe)), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntitySkeleton) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Bone), ((EntitySkeleton)aEntity).getSkeletonType() == 1 ? OM.stack(1*U, MT.Coal) : null), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntityZombie) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.MeatRotten)), WD.envTemp(worldObj, xCoord, yCoord, zCoord));
				} else if (aEntity instanceof EntityMooshroom || aEntity instanceof EntityCow || aEntity instanceof EntityHorse) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(3*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityPig || aEntity instanceof EntitySheep || aEntity instanceof EntityWolf || aEntity instanceof EntitySquid) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(2*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityChicken || aEntity instanceof EntityOcelot || aEntity instanceof EntitySpider || aEntity instanceof EntitySilverfish) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.MeatRaw)), C+37);
				} else if (aEntity instanceof EntityCreeper) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Gunpowder)), C+20);
				} else if (aEntity instanceof EntityEnderman) {
					addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.EnderPearl)), C+20);
				} else if (aEntity instanceof EntityPlayer) {
					if ("GregoriusT".equalsIgnoreCase(aEntity.getCommandSenderName())) for (int i = 0; i < 16; i++) addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(1*U, MT.Tc)), C+20);
				}
			}
		}
	}
	
	@Override public byte getDefaultSide() {return SIDE_UP;}
	@Override public boolean[] getValidSides() {return SIDES_NONE;}
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return UT.Code.getAscendingArray(1);}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return !slotHas(0);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public int getInventoryStackLimit() {return 64;}
	
	public static final List<TagData> ENERGYTYPES = new ArrayListNoNulls<>(F, TD.Energy.KU, TD.Energy.HU, TD.Energy.CU, TD.Energy.VIS_IGNIS);
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && ENERGYTYPES.contains(aEnergyType);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return ENERGYTYPES.contains(aEnergyType);}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return ENERGYTYPES.contains(aEnergyType);}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {if (aEnergyType == TD.Energy.KU) {if (aSize*aAmount > 0 && WD.oxygen(worldObj, xCoord, yCoord+1, zCoord)) addMaterialStacks(new ArrayListNoNulls<>(F, OM.stack(aSize*aAmount*U1000, MT.Air)), mTemperature);} else if (aEnergyType == TD.Energy.CU) mEnergy -= Math.abs(aAmount * aSize); else mEnergy += Math.abs(aAmount * aSize);} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return Long.MAX_VALUE - mEnergy;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return ENERGYTYPES;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.crucible";}
}
