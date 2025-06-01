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

package gregtech.tileentity.tools;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase11AttachmentSmall;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrank extends TileEntityBase11AttachmentSmall implements ITileEntityEnergy {
	public boolean mActive = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.NO_GUI_CLICK_TO_INTERACT));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			boolean oActive = mActive;
			mActive = F;
			if (oActive) for (EntityPlayer tPlayer : UT.Entities.getPlayersWithLastTarget(this)) {
				mActive = T;
				if (ITileEntityEnergy.Util.emitEnergyToSide(TD.Energy.RU, mFacing, -UT.Code.divup(8L*UT.Entities.pot2Strength(tPlayer), UT.Entities.pot1Weakness(tPlayer)), UT.Entities.pot1Haste(tPlayer), this) > 0) UT.Entities.exhaust(tPlayer, 0.025);
				tPlayer.swingItem();
			}
			// Don't check for Villagers while Players operate the Crank.
			if (!mActive) {
				List<EntityVillager> tList = new ArrayListNoNulls<>();
				worldObj.getChunkFromBlockCoords(xCoord, zCoord).getEntitiesOfTypeWithinAAAB(EntityVillager.class, box(), tList, null);
				for (EntityVillager tVillager : tList) if (UT.Code.roundDown(tVillager.posY+tVillager.getEyeHeight()) == yCoord && UT.Code.roundDown(tVillager.posX) == xCoord && UT.Code.roundDown(tVillager.posZ) == zCoord) {
					mActive = T;
					ITileEntityEnergy.Util.emitEnergyToSide(TD.Energy.RU, mFacing, -UT.Code.divup(8L*UT.Entities.pot2Strength(tVillager), UT.Entities.pot1Weakness(tVillager)), UT.Entities.pot1Haste(tVillager), this);
					// Multiple Players can use one Crank but multiple Villagers cannot (Collision Lag prevention)
					break;
				}
			}
			// Update Client State and Redstone State.
			if (mActive != oActive) {
				updateClientData();
				causeBlockUpdate();
			}
		} else {
			if (mActive && WD.random(this, 20, CLIENT_TIME)) UT.Sounds.play(SFX.MC_MINECART, 1, 0.1F, getCoords());
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			mActive = T;
			updateClientData();
			causeBlockUpdate();
			UT.Sounds.send(SFX.MC_MINECART, 0.5F, this, F);
		}
		// TODO Might do something if attached to a Pipe to open/close it or something.
		return T;
	}
	
	@Override
	public byte isProvidingWeakPower2(byte aSide) {
		return (byte)(aSide == OPOS[mFacing] && mActive ? 15 : 0);
	}
	
	@Override
	public byte isProvidingStrongPower2(byte aSide) {
		return (byte)(aSide == OPOS[mFacing] && mActive ? 15 : 0);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureFront = BlockTextureMulti.get(BlockTextureDefault.get(mActive?sTextureFrontSpin:sTextureFront, mRGBa), BlockTextureDefault.get(mActive?sOverlayFrontSpin:sOverlayFront));
		mTextureBacks = BlockTextureDefault.get(mActive?Textures.BlockIcons.AXLE_COUNTERCLOCKWISE:Textures.BlockIcons.AXLE, mRGBa);
		mTextureSides = BlockTextureDefault.get(mActive?Textures.BlockIcons.AXLE_CLOCKWISE       :Textures.BlockIcons.AXLE, mRGBa);
		return 2;
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	
	public static IIconContainer
	sTextureFront     = new Textures.BlockIcons.CustomIcon("machines/tools/crank/colored/front"),
	sTextureFrontSpin = new Textures.BlockIcons.CustomIcon("machines/tools/crank/colored/frontspin"),
	sOverlayFront     = new Textures.BlockIcons.CustomIcon("machines/tools/crank/overlay/front"),
	sOverlayFrontSpin = new Textures.BlockIcons.CustomIcon("machines/tools/crank/overlay/frontspin");
	
	private ITexture mTextureFront, mTextureBacks, mTextureSides;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return mFacing == aSide ? mTextureBacks : mFacing == OPOS[aSide] ? mTextureFront : mTextureSides;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0)
		return box(aBlock,
		  PX_P[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_NEG ? 1 : 13 : 2]
		, PX_P[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_NEG ? 1 : 13 : 2]
		, PX_P[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_NEG ? 1 : 13 : 2]
		, PX_N[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_POS ? 1 : 13 : 2]
		, PX_N[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_POS ? 1 : 13 : 2]
		, PX_N[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_POS ? 1 : 13 : 2]
		);
		return box(aBlock,
		  PX_P[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_NEG ? 0 : 15 : 5]
		, PX_P[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_NEG ? 0 : 15 : 5]
		, PX_P[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_NEG ? 0 : 15 : 5]
		, PX_N[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_POS ? 0 : 15 : 5]
		, PX_N[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_POS ? 0 : 15 : 5]
		, PX_N[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_POS ? 0 : 15 : 5]
		);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		return box(
		  PX_P[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_NEG ? 0 : 13 : 2]
		, PX_P[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_NEG ? 0 : 13 : 2]
		, PX_P[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_NEG ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_POS ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_POS ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_POS ? 0 : 13 : 2]
		);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		box(aBlock,
		  PX_P[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_NEG ? 0 : 13 : 2]
		, PX_P[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_NEG ? 0 : 13 : 2]
		, PX_P[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_NEG ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_X[mFacing] ? mFacing == SIDE_X_POS ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_Y[mFacing] ? mFacing == SIDE_Y_POS ? 0 : 13 : 2]
		, PX_N[SIDES_AXIS_Z[mFacing] ? mFacing == SIDE_Z_POS ? 0 : 13 : 2]
		);
	}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == TD.Energy.RU;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return aSide == mFacing && aEnergyType == TD.Energy.RU;}
	@Override public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return mActive ? 16 : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return 16;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 16;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return 16;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.RU.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public byte getDirectionData() {return (byte)((mFacing&7) | (mActive?8:0));}
	@Override public void setDirectionData(byte aData) {mActive = ((aData&8)!=0); mFacing = (byte)(aData & 7);}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.crank";}
}
