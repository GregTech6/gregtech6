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

import gregapi.code.TagData;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.SFX;
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
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class MultiTileEntityEngineSteam extends TileEntityBase09FacingSingle implements ITileEntityAdjacentOnOff, ITileEntityEnergyFluxHandler, ITileEntityRunningActively, IFluidHandler {
	/** The Array containing the different Engine State Colours from Blue over Green to Red */
	public static final int sEngineColors[] = {0x0000ff, 0x0011ee, 0x0022dd, 0x0033cc, 0x0044bb, 0x0055aa, 0x006699, 0x007788, 0x008877, 0x009966, 0x00aa55, 0x00bb44, 0x00cc33, 0x00dd22, 0x00ee11, 0x00ff00, 0x00ff00, 0x11ee00, 0x22dd00, 0x33cc00, 0x44bb00, 0x55aa00, 0x669900, 0x778800, 0x887700, 0x996600, 0xaa5500, 0xbb4400, 0xcc3300, 0xdd2200, 0xee1100, 0xff0000};
	
	public static final int STEAM_PER_WATER = 200;
	
	protected boolean mEmitsEnergy = F, mStopped = F, mActive = F, oActive = F;
	protected byte mState = 0, oState = 0, mPiston = 0;
	protected short mEfficiency = 10000;
	protected long mEnergy = 0, mCapacity = 640000, mOutput = 64;
	protected TagData mEnergyTypeEmitted = TD.Energy.KU;
	protected FluidTankGT mTank = new FluidTankGT(640);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_VISUAL)) mState = aNBT.getByte(NBT_VISUAL);
		if (aNBT.hasKey(NBT_PISTON)) mPiston = aNBT.getByte(NBT_PISTON);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		if (aNBT.hasKey(NBT_CAPACITY)) mCapacity = aNBT.getLong(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_ACTIVE_ENERGY)) mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		if (aNBT.hasKey(NBT_OUTPUT)) mOutput = aNBT.getLong(NBT_OUTPUT);
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
		mTank.readFromNBT(aNBT, NBT_TANK+"."+0).setCapacity(STEAM_PER_WATER * mOutput * 2);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		aNBT.setByte(NBT_VISUAL, mState);
		aNBT.setByte(NBT_PISTON, mPiston);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
		aNBT.setShort(NBT_EFFICIENCY, mEfficiency);
		mTank.writeToNBT(aNBT, NBT_TANK+"."+0);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.CONVERTS_FROM_X)        + " " + STEAM_PER_WATER + " L " + FL.name(FL.Steam.make(0), T) + " " + LH.get(LH.CONVERTS_TO_Y) + " " + (STEAM_PER_WATER / STEAM_PER_EU) + " " + mEnergyTypeEmitted.getLocalisedNameShort());
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT)           + ": " + Chat.WHITE + UT.Code.units(mOutput*STEAM_PER_EU, mEfficiency*2, 10000, F) + " - " + UT.Code.units(mOutput*2*STEAM_PER_EU, mEfficiency, 10000, F)   + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE + "/t ("+LH.get(LH.FACE_BACK)+")");
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mTank.capacity()                                               + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE);
		aList.add(Chat.RED      + LH.get(LH.ENERGY_OUTPUT)          + ": " + Chat.WHITE + (mOutput/2) + " - " + (mOutput*2)                              + " " + mEnergyTypeEmitted.getChatFormat()  + mEnergyTypeEmitted.getLocalisedNameShort()    + Chat.WHITE + "/t ("+LH.get(LH.FACE_FRONT)+")");
		aList.add(Chat.RED      + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mCapacity                                                      + " " + mEnergyTypeEmitted.getChatFormat()  + mEnergyTypeEmitted.getLocalisedNameShort()    + Chat.WHITE);
		aList.add(Chat.ORANGE   + LH.get(LH.EMITS_USED_STEAM) + " ("+LH.get(LH.FACE_SIDES)+", 80%)");
		aList.add(LH.getToolTipRedstoneFluxEmit(mEnergyTypeEmitted));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (mActive && aTimer%(32-mState)==0) {
			mPiston+=1; mPiston&=3;
		//  if (!aIsServerSide && (mPiston&2)==0) UT.Sounds.play(SFX.MC_FIZZ, 5, 0.25F, 0.5F, getCoords());
		}
		
		if (aIsServerSide) {
			// Convert Steam to Energy
			if (!mStopped) {
				long tConversions = mTank.amount() / STEAM_PER_WATER;
				if (tConversions > 0) {
					mEnergy += UT.Code.units(tConversions * STEAM_PER_WATER / STEAM_PER_EU, 10000, mEfficiency, F);
					mTank.remove(tConversions * STEAM_PER_WATER);
					FluidStack tDistilledWater = FL.DistW.make(tConversions);
					for (byte tDir : FACING_SIDES[mFacing]) {
						if (tDistilledWater.amount <= 0) break;
						tDistilledWater.amount -= FL.fill(getAdjacentTileEntity(tDir), tDistilledWater.copy(), T);
					}
					GarbageGT.trash(tDistilledWater);
				}
			}
			
			// Set State
			if (SERVER_TIME % 20 == 0) mState = (byte)Math.min(31, UT.Code.scale(mEnergy, mCapacity, 32, F));
			
			// Set the output depending on how "hot" the state of the Engine is.
			long tOutput = (mOutput * (mState + 1)) / 16;
			
			// Checks if this Engine is supposed to be active.
			mActive = (!mStopped && mEnergy > tOutput && tOutput * 2 > mOutput);
			mEmitsEnergy = F;
			
			// Emit Energy
			if (mActive) {
				mEmitsEnergy = (ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeEmitted, mPiston > 1 ? -tOutput : tOutput, 1, this) > 0);
				mEnergy -= tOutput;
				if (mTimer % 600 == 5) doDefaultStructuralChecks();
			}
			
			// Well the Engine stops if it has too much Steam and just vents everything, unless it freshly entered the State.
			if (mEnergy >= mCapacity) {
				mEnergy = mCapacity - 1;
				if (mState > 30) {
					mStopped = T;
					mTank.setEmpty();
					UT.Sounds.send(SFX.MC_FIZZ, this);
				} else {
					mState = 31;
				}
			}
			
			// Release the Energy when inactive.
			if (mStopped && mEnergy > 0) mEnergy = Math.max(0, mEnergy - Math.max(1, mCapacity / 64));
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_softhammer)) {mStopped = !mStopped; return 10000;}
		
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (mStopped) {
					aChatReturn.add("Stopped");
				} else {
					if (mActive) {
						aChatReturn.add("Running");
					} else {
						aChatReturn.add("Capable of Running");
					}
				}
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mState = UT.Code.bind5(mState);
		return mActive != oActive || mState != oState || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oState = mState;
		oActive = mActive;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mPiston = (byte)(aData & 3);
		mActive = ((aData & 4) != 0);
		mState = (byte)((aData >>> 3) & 31);
	}
	
	@Override public byte getVisualData() {return (byte)((mPiston&3) | (mActive?4:0) | (mState << 3));}
	
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?0.5F:0.25F;}
	@Override public boolean isSideSolid2           (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	@Override public boolean allowCovers            (byte aSide) {return ALONG_AXIS[aSide][mFacing];}
	
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActive;}
	@Override public boolean getStateRunningActively() {return mEmitsEnergy;}
	@Override public boolean setAdjacentOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	@Override public boolean getStateOnOff() {return !mStopped;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && super.isEnergyEmittingTo(aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return mOutput;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted ? mCapacity : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return !mStopped && aSide == OPOS[mFacing] && FL.Steam.is(aFluidToFill) ? mTank : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTank.AS_ARRAY;}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 7;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case 0: return box(aBlock, PX_P[                          1], PX_P[                       1], PX_P[                       1], PX_N[                       1], PX_N[                       1], PX_N[                       1]);
		case 1: return box(aBlock, PX_P[                          0], PX_P[                       0], PX_P[                       0], PX_N[SIDES_AXIS_X[mFacing]?14: 0], PX_N[SIDES_AXIS_Y[mFacing]?14: 0], PX_N[SIDES_AXIS_Z[mFacing]?14: 0]);
		case 2: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]?14: 0], PX_P[SIDES_AXIS_Y[mFacing]?14: 0], PX_P[SIDES_AXIS_Z[mFacing]?14: 0], PX_N[                        0], PX_N[                       0], PX_N[                       0]);
		case 3: return box(aBlock, PX_P[SIDES_AXIS_Y[mFacing]? 0: 6], PX_P[SIDES_AXIS_Z[mFacing]? 0: 6], PX_P[SIDES_AXIS_X[mFacing]? 0: 6], PX_N[SIDES_AXIS_Y[mFacing]? 0: 6], PX_N[SIDES_AXIS_Z[mFacing]? 0: 6], PX_N[SIDES_AXIS_X[mFacing]? 0: 6]);
		case 4: return box(aBlock, PX_P[SIDES_AXIS_Z[mFacing]? 0: 6], PX_P[SIDES_AXIS_X[mFacing]? 0: 6], PX_P[SIDES_AXIS_Y[mFacing]? 0: 6], PX_N[SIDES_AXIS_Z[mFacing]? 0: 6], PX_N[SIDES_AXIS_X[mFacing]? 0: 6], PX_N[SIDES_AXIS_Y[mFacing]? 0: 6]);
		case 5: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0: 4], PX_P[SIDES_AXIS_Y[mFacing]? 0: 4], PX_P[SIDES_AXIS_Z[mFacing]? 0: 4], PX_N[SIDES_AXIS_X[mFacing]? 0: 4], PX_N[SIDES_AXIS_Y[mFacing]? 0: 4], PX_N[SIDES_AXIS_Z[mFacing]? 0: 4]);
		case 6: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0: 3], PX_P[SIDES_AXIS_Y[mFacing]? 0: 3], PX_P[SIDES_AXIS_Z[mFacing]? 0: 3], PX_N[SIDES_AXIS_X[mFacing]? 0: 3], PX_N[SIDES_AXIS_Y[mFacing]? 0: 3], PX_N[SIDES_AXIS_Z[mFacing]? 0: 3]);
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case 0: return ALONG_AXIS[aSide][mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[3], mRGBa), BlockTextureDefault.get(sOverlays[3]));
		case 1: case 2: return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?0:aSide==OPOS[mFacing]?1:2], mRGBa), BlockTextureDefault.get(sOverlays[aSide==mFacing?0:aSide==OPOS[mFacing]?1:2]));
		case 3: return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[ALONG_AXIS_1[aSide][mFacing]?4:5], mRGBa), BlockTextureDefault.get(sOverlays[ALONG_AXIS_1[aSide][mFacing]?4:5]));
		case 4: return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[ALONG_AXIS_2[aSide][mFacing]?4:5], mRGBa), BlockTextureDefault.get(sOverlays[ALONG_AXIS_2[aSide][mFacing]?4:5]));
		case 5: return aSide==OPOS [mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[6], sEngineColors[mState]), BlockTextureDefault.get(sOverlays[6]));
		case 6: return ALONG_AXIS[aSide][mFacing]?null:BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[7], mRGBa), BlockTextureDefault.get(sOverlays[7]));
		}
		return null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/cage"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/pipe_side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/pipe"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/engine"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/colored/engine_hull")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/cage"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/pipe_side"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/pipe"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/engine"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_steam/overlay/engine_hull")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.engine.kinetic_steam";}
	/*
	public static class RenderEngine extends TileEntitySpecialRenderer {
		
		private static final float[] angleMap = new float[6];

		static {
			angleMap[ForgeDirection.EAST.ordinal()] = (float) -Math.PI / 2;
			angleMap[ForgeDirection.WEST.ordinal()] = (float) Math.PI / 2;
			angleMap[ForgeDirection.UP.ordinal()] = 0;
			angleMap[ForgeDirection.DOWN.ordinal()] = (float) Math.PI;
			angleMap[ForgeDirection.SOUTH.ordinal()] = (float) Math.PI / 2;
			angleMap[ForgeDirection.NORTH.ordinal()] = (float) -Math.PI / 2;
		}

		private ModelBase model = new ModelBase() {
			//
		};

		private ModelRenderer box;
		private ModelRenderer trunk;
		private ModelRenderer movingBox;
		private ModelRenderer chamber;

		private ResourceLocation baseTexture;
		private ResourceLocation chamberTexture;
		private ResourceLocation trunkTexture;

		public RenderEngine() {
			box = new ModelRenderer(model, 0, 1);
			box.addBox(-8F, -8F, -8F, 16, 4, 16);
			box.rotationPointX = 8;
			box.rotationPointY = 8;
			box.rotationPointZ = 8;

			trunk = new ModelRenderer(model, 1, 1);
			trunk.addBox(-4F, -4F, -4F, 8, 12, 8);
			trunk.rotationPointX = 8F;
			trunk.rotationPointY = 8F;
			trunk.rotationPointZ = 8F;

			movingBox = new ModelRenderer(model, 0, 1);
			movingBox.addBox(-8F, -4, -8F, 16, 4, 16);
			movingBox.rotationPointX = 8F;
			movingBox.rotationPointY = 8F;
			movingBox.rotationPointZ = 8F;

			chamber = new ModelRenderer(model, 1, 1);
			chamber.addBox(-5F, -4, -5F, 10, 2, 10);
			chamber.rotationPointX = 8F;
			chamber.rotationPointY = 8F;
			chamber.rotationPointZ = 8F;
		}

		public RenderEngine(ResourceLocation baseTexture, ResourceLocation chamberTexture, ResourceLocation trunkTexture) {
			this();
			this.baseTexture = baseTexture;
			this.chamberTexture = chamberTexture;
			this.trunkTexture = trunkTexture;
			field_147501_a = TileEntityRendererDispatcher.instance;
		}

		public void inventoryRender(double x, double y, double z, float f, float f1) {
			render(0.25F, ForgeDirection.UP, baseTexture, chamberTexture, trunkTexture, x, y, z);
		}

		@Override
		public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
			if (tileentity != null) {
//              render(tileentity.progress, tileentity.orientation, tileentity.getBaseTexture(), tileentity.getChamberTexture(), tileentity.getTrunkTexture(tileentity.getEnergyStage()), x, y, z);
			}
		}

		private void render(float progress, ForgeDirection orientation, ResourceLocation baseTexture, ResourceLocation chamberTexture, ResourceLocation trunkTexture, double x, double y, double z) {
			GL11.glPushMatrix();
			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glColor3f(1, 1, 1);

			GL11.glTranslatef((float) x, (float) y, (float) z);

			float step;

			if (progress > 0.5) {
				step = 7.99F - (progress - 0.5F) * 2F * 7.99F;
			} else {
				step = progress * 2F * 7.99F;
			}

			float translatefact = step / 16;

			float[] angle = {0, 0, 0};
			float[] translate = {orientation.offsetX, orientation.offsetY, orientation.offsetZ};

			switch (orientation) {
				case EAST:
				case WEST:
				case DOWN:
					angle[2] = angleMap[orientation.ordinal()];
					break;
				case SOUTH:
				case NORTH:
				default:
					angle[0] = angleMap[orientation.ordinal()];
					break;
			}

			box.rotateAngleX = angle[0];
			box.rotateAngleY = angle[1];
			box.rotateAngleZ = angle[2];

			trunk.rotateAngleX = angle[0];
			trunk.rotateAngleY = angle[1];
			trunk.rotateAngleZ = angle[2];

			movingBox.rotateAngleX = angle[0];
			movingBox.rotateAngleY = angle[1];
			movingBox.rotateAngleZ = angle[2];

			chamber.rotateAngleX = angle[0];
			chamber.rotateAngleY = angle[1];
			chamber.rotateAngleZ = angle[2];

			float factor = (float) (1.0 / 16.0);

			bindTexture(baseTexture);

			box.render(factor);

			GL11.glTranslatef(translate[0] * translatefact, translate[1] * translatefact, translate[2] * translatefact);
			movingBox.render(factor);
			GL11.glTranslatef(-translate[0] * translatefact, -translate[1] * translatefact, -translate[2] * translatefact);

			bindTexture(chamberTexture);

			float chamberf = 2F / 16F;

			for (int i = 0; i <= step + 2; i += 2) {
				chamber.render(factor);
				GL11.glTranslatef(translate[0] * chamberf, translate[1] * chamberf, translate[2] * chamberf);
			}

			for (int i = 0; i <= step + 2; i += 2) {
				GL11.glTranslatef(-translate[0] * chamberf, -translate[1] * chamberf, -translate[2] * chamberf);
			}

			bindTexture(trunkTexture);

			trunk.render(factor);

			GL11.glPopAttrib();
			GL11.glPopMatrix();
		}
	}*/
}
