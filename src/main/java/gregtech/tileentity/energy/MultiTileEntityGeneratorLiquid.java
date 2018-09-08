package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.code.TagData;
import gregapi.data.FM;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorLiquid extends TileEntityBase09FacingSingle implements IFluidHandler, ITileEntityTapAccessible, ITileEntityEnergy, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	private static int FLAME_RANGE = 2;
	
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mRate = 1;
	protected boolean mBurning = F, oBurning = F;
	protected TagData mEnergyTypeEmitted = TD.Energy.HU;
	protected RecipeMap mRecipes = FM.Burn;
	protected Recipe mLastRecipe = null;
	protected FluidTankGT mTank = new FluidTankGT(1000);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mBurning = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_OUTPUT)) mRate = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_FUELMAP));
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTank.setCapacity(UT.Code.bindInt(mRate * 10));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mBurning);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN		+ LH.get(LH.RECIPES)		+ ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		LH.addEnergyToolTips(this, aList, null, mEnergyTypeEmitted, null, LH.get(LH.FACE_TOP));
		aList.add(Chat.ORANGE	+ LH.get(LH.REQUIREMENT_AIR_IN_FRONT));
		aList.add(Chat.ORANGE	+ LH.get(LH.REQUIREMENT_IGNITE_FIRE) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.ORANGE	+ LH.get(LH.NO_GUI_CLICK_TO_INVENTORY) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.DRED		+ LH.get(LH.HAZARD_FIRE) + " ("+(FLAME_RANGE+1)+"m)");
		aList.add(Chat.DRED		+ LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY	+ LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBurning) {
				if (mEnergy > 0) {
					mEnergy -= Math.max(1, Math.max(mRate / 2, ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, 1, Math.min(mRate, mEnergy), this)));
					if (mEfficiency < 1 || rng(mEfficiency) == 0) {
						WD.fire(worldObj, xCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), yCoord-1+rng(2+FLAME_RANGE), zCoord-FLAME_RANGE+rng(2*FLAME_RANGE+1), T);
					}
				}
				if (mEnergy < mRate * 2) {
					WD.fire(worldObj, getOffset(mFacing, 1), T);
					if (!WD.hasCollide(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing)) && !getBlockAtSide(mFacing).getMaterial().isLiquid() && WD.oxygen(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing))) {
						Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, T, Long.MAX_VALUE, NI, new FluidStack[] {mTank.getFluid()}, ZL_IS);
						
						if (tRecipe != null) {
							if (tRecipe.isRecipeInputEqual(T, F, new FluidStack[] {mTank.getFluid()}, ZL_IS)) {
								mLastRecipe = tRecipe;
								mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
								if (mTank.getFluidAmount() > 0) {
									while (mEnergy < mRate * 2 && tRecipe.isRecipeInputEqual(T, F, new FluidStack[] {mTank.getFluid()}, ZL_IS)) {
										mEnergy += UT.Code.units(Math.abs(tRecipe.mEUt * tRecipe.mDuration), 10000, mEfficiency, F);
										if (mTank.getFluidAmount() == 0) {mTank.setFluid(NF); break;}
									}
								} else {
									mTank.setFluid(NF);
								}
							} else {
								mTank.setFluid(NF);
							}
						}
					}
				}
			}
			if (mEnergy <= 0) {
				mEnergy = 0;
				mBurning = F;
			}
		} else {
			if (mBurning && rng(5) == 0) spawnBurningParticles(xCoord+0.5+OFFSETS_X[mFacing]*0.55+(SIDES_AXIS_X[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F), yCoord+RNGSUS.nextFloat()*0.375F, zCoord+0.5+OFFSETS_Z[mFacing]*0.55+(SIDES_AXIS_Z[mFacing]?0:RNGSUS.nextFloat()*0.6F-0.3F));
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_igniter		) && (aSide == mFacing || aPlayer == null)) {mBurning = T; return 10000;}
		if (aTool.equals(TOOL_extinguisher	) && (aSide == mFacing || aPlayer == null)) {mBurning = F; return 10000;}
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				FluidStack tFluid = mTank.getFluid();
				if (tFluid == null) {
					aChatReturn.add("Box is empty");
				} else {
					aChatReturn.add("Contains: " + tFluid.amount + " L of " + UT.Fluids.name(tFluid, T) + " (" + (UT.Fluids.gas(tFluid) ? "Gaseous" : "Liquid") + ")");
				}
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mBurning != oBurning || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oBurning = mBurning;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mBurning = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)(mBurning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_SOUTH;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) && !UT.Fluids.gas(aFluidToFill) ? mTank : null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mBurning ? null : mTank;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return new IFluidTank[] {mTank};
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTank.drain(aMaxDrain, aDoDrain);
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mBurning) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mRate / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, 0, 0, 1, 0.875, 1);}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return ZL_IS;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return SIDES_TOP[aSide] && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return Math.min(mRate, mEnergy);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return mRate;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean getStateRunningPassively() {return mBurning;}
	@Override public boolean getStateRunningPossible() {return mBurning;}
	@Override public boolean getStateRunningActively() {return mBurning;}
	
	protected void spawnBurningParticles(double aX, double aY, double aZ) {
		worldObj.spawnParticle("smoke", aX, aY, aZ, 0, 0, 0);
		worldObj.spawnParticle("flame", aX, aY, aZ, 0, 0, 0);
	}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_liquid/overlay_active/back")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.burning_liquid";}
}