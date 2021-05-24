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

package gregtech.tileentity.tools;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Map.Entry;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.BI;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.SFX;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySiftingTable extends TileEntityBase07Paintable implements IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips {
	public short mDisplayedInput = 0, mDisplayedOutput = 0, oDisplayedInput = -1, oDisplayedOutput = -1;
	public byte mState = 0, oState = 0, mClickCount = 0;
	public RecipeMap mRecipes = RM.Sifting;
	public Recipe mLastRecipe = null;

	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE)) mState = aNBT.getByte(NBT_STATE);
		if (aNBT.hasKey(NBT_PROGRESS)) mClickCount = aNBT.getByte(NBT_PROGRESS);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
	}

	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_STATE, mState);
		aNBT.setByte(NBT_PROGRESS, mClickCount);
	}

	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_SIFTER_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mDisplayedInput = 0;
			mDisplayedOutput = 0;
			mState &= ~B[0];
			ItemStack tStack = slot(0);
			if (ST.valid(tStack)) {
				mState |= B[0];
				if (ST.equal(tStack, Blocks.gravel      , W)) {mDisplayedInput = -1;} else
				if (ST.equal(tStack, Blocks.dirt        , 0)) {mDisplayedInput = -2;} else
				if (ST.equal(tStack, Blocks.dirt        , 1)) {mDisplayedInput = -3;} else
				if (ST.equal(tStack, Blocks.dirt        , 2)) {mDisplayedInput = -4;} else
				if (ST.equal(tStack, Blocks.sand        , 0)) {mDisplayedInput = -5;} else
				if (ST.equal(tStack, Blocks.sand        , 1)) {mDisplayedInput = -6;} else
				if (ST.equal(tStack, Blocks.grass       , W)) {mDisplayedInput = -7;} else
				if (ST.equal(tStack, BlocksGT.Grass     , W)) {mDisplayedInput = -7;} else
				if (ST.equal(tStack, Blocks.mycelium    , W)) {mDisplayedInput = -8;} else
				if (ST.equal(tStack, Blocks.soul_sand   , W)) {mDisplayedInput = -9;} else
				if (ST.equal(tStack, BlocksGT.Diggables , W)) {mDisplayedInput =-10;} else
				if (ST.equal(tStack, BlocksGT.Sands     , W)) {mDisplayedInput =-11;} else
				if (IL.AETHER_Sand                  .equal(tStack, T, T)) {mDisplayedInput = -5;} else
				if (IL.RH_Sand_Magnetite            .equal(tStack, F, T)) {mDisplayedInput =-11;} else
				if (IL.RH_Sand_Magnetite            .equal(tStack, T, T)) {mDisplayedInput =- 5;} else
				if (IL.TROPIC_Sand_Black            .equal(tStack, F, T)) {mDisplayedInput =-11;} else
				if (IL.TROPIC_Sand_Black            .equal(tStack, T, T)) {mDisplayedInput = -5;} else
				if (IL.PFAA_Sands                   .equal(tStack, T, T)) {mDisplayedInput =-11;} else
				if (IL.NePl_SoulSoil                .equal(tStack, T, T)) {mDisplayedInput = -9;} else
				if (IL.NeLi_SoulSoil                .equal(tStack, T, T)) {mDisplayedInput = -9;} else
				if (IL.NeLi_Gravel                  .equal(tStack, T, T)) {mDisplayedInput =-11;} else
				if (IL.EtFu_Gravel                  .equal(tStack, F, T)) {mDisplayedInput = -1;} else
				if (IL.EtFu_Dirt                    .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Mud                      .equal(tStack, F, T)) {mDisplayedInput =-10;} else
				if (IL.BoP_Quicksand                .equal(tStack, F, T)) {mDisplayedInput = -5;} else
				if (IL.BoP_Sand_Hard                .equal(tStack, F, T)) {mDisplayedInput = -5;} else
				if (IL.BoP_Grass_Endstone           .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Loamy              .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Long               .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Netherrack         .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Origin             .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Sandy              .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Silty              .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Grass_Smoldering         .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.BoP_Coarse_Loamy             .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Coarse_Sandy             .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Coarse_Silty             .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Dirt_Hard                .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Dirt_Dried               .equal(tStack, F, T)) {mDisplayedInput = -3;} else
				if (IL.BoP_Dirt_Loamy               .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.BoP_Dirt_Sandy               .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.BoP_Dirt_Silty               .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Alfisol              .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Andisol              .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Gelisol              .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Histosol             .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Inceptisol           .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Mollisol             .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Dirt_Oxisol               .equal(tStack, F, T)) {mDisplayedInput = -2;} else
				if (IL.EB_Grass_Alfisol             .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Andisol             .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Gelisol             .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Histosol            .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Inceptisol          .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Mollisol            .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				if (IL.EB_Grass_Oxisol              .equal(tStack, F, T)) {mDisplayedInput = -7;} else
				{
					OreDictItemData tData = OM.data_(tStack);
					if (tData == null || tData.mMaterial == null) {
						mDisplayedInput = -1;
					} else {
						mDisplayedInput = tData.mMaterial.mMaterial.mID;
					}
				}
			}
			mState &= ~B[1];
			for (int i = 1; i < 13; i++) if (ST.valid(tStack = slot(i))) {
				mState |= B[1];
				if (ST.equal(tStack, Blocks.gravel      , W)) {mDisplayedOutput = -1;} else
				if (ST.equal(tStack, Blocks.dirt        , 0)) {mDisplayedOutput = -2;} else
				if (ST.equal(tStack, Blocks.dirt        , 1)) {mDisplayedOutput = -3;} else
				if (ST.equal(tStack, Blocks.dirt        , 2)) {mDisplayedOutput = -4;} else
				if (ST.equal(tStack, Blocks.sand        , 0)) {mDisplayedOutput = -5;} else
				if (ST.equal(tStack, Blocks.sand        , 1)) {mDisplayedOutput = -6;} else
				if (ST.equal(tStack, Blocks.grass       , W)) {mDisplayedOutput = -7;} else
				if (ST.equal(tStack, BlocksGT.Grass     , W)) {mDisplayedOutput = -7;} else
				if (ST.equal(tStack, Blocks.mycelium    , W)) {mDisplayedOutput = -8;} else
				if (ST.equal(tStack, Blocks.soul_sand   , W)) {mDisplayedOutput = -9;} else
				if (ST.equal(tStack, BlocksGT.Diggables , W)) {mDisplayedOutput =-10;} else
				if (ST.equal(tStack, BlocksGT.Sands     , W)) {mDisplayedOutput =-11;} else
				if (IL.AETHER_Sand                  .equal(tStack, T, T)) {mDisplayedOutput = -5;} else
				if (IL.RH_Sand_Magnetite            .equal(tStack, F, T)) {mDisplayedOutput =-11;} else
				if (IL.RH_Sand_Magnetite            .equal(tStack, T, T)) {mDisplayedOutput = -5;} else
				if (IL.TROPIC_Sand_Black            .equal(tStack, F, T)) {mDisplayedOutput =-11;} else
				if (IL.TROPIC_Sand_Black            .equal(tStack, T, T)) {mDisplayedOutput = -5;} else
				if (IL.PFAA_Sands                   .equal(tStack, T, T)) {mDisplayedOutput =-11;} else
				if (IL.NePl_SoulSoil                .equal(tStack, T, T)) {mDisplayedOutput = -9;} else
				if (IL.NeLi_SoulSoil                .equal(tStack, T, T)) {mDisplayedOutput = -9;} else
				if (IL.NeLi_Gravel                  .equal(tStack, T, T)) {mDisplayedOutput =-11;} else
				if (IL.EtFu_Gravel                  .equal(tStack, F, T)) {mDisplayedOutput = -1;} else
				if (IL.EtFu_Dirt                    .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Mud                      .equal(tStack, F, T)) {mDisplayedOutput =-10;} else
				if (IL.BoP_Quicksand                .equal(tStack, F, T)) {mDisplayedOutput = -5;} else
				if (IL.BoP_Sand_Hard                .equal(tStack, F, T)) {mDisplayedOutput = -5;} else
				if (IL.BoP_Grass_Endstone           .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Loamy              .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Long               .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Netherrack         .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Origin             .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Sandy              .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Silty              .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Grass_Smoldering         .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.BoP_Coarse_Loamy             .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Coarse_Sandy             .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Coarse_Silty             .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Dirt_Hard                .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Dirt_Dried               .equal(tStack, F, T)) {mDisplayedOutput = -3;} else
				if (IL.BoP_Dirt_Loamy               .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.BoP_Dirt_Sandy               .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.BoP_Dirt_Silty               .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Alfisol              .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Andisol              .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Gelisol              .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Histosol             .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Inceptisol           .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Mollisol             .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Dirt_Oxisol               .equal(tStack, F, T)) {mDisplayedOutput = -2;} else
				if (IL.EB_Grass_Alfisol             .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Andisol             .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Gelisol             .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Histosol            .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Inceptisol          .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Mollisol            .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				if (IL.EB_Grass_Oxisol              .equal(tStack, F, T)) {mDisplayedOutput = -7;} else
				{
					OreDictItemData tData = OM.data_(tStack);
					if (tData == null || tData.mMaterial == null) {
						mDisplayedOutput = -1;
					} else {
						mDisplayedOutput = tData.mMaterial.mMaterial.mID;
					}
				}
				break;
			}
			
			if (aTimer % 5 == 0 && (mState & B[2]) != 0) {
				mState &= ~B[2];
				for (Entry<EntityPlayer, ChunkCoordinates> tEntry : PLAYER_LAST_CLICKED.entrySet()) {
					if (getCoords().equals(tEntry.getValue()) && tEntry.getKey().getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5) <= 64) {
						mState |= B[2];
						
						boolean temp = T;
						for (int i = 1; i < 13; i++) if (slotHas(i)) {temp = F; break;}
						ItemStack aStack = slot(0);
						
						if (temp && (++mClickCount >= 8 || UT.Entities.hasInfiniteItems(tEntry.getKey()))) {
							mClickCount = 0;
							Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], null, ZL_FS, aStack);
							if (tRecipe == null) {
								for (int i = 1; i < 13; i++) if (addStackToSlot(i, aStack)) {slotKill(0); break;}
							} else {
								if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
								if (tRecipe.isRecipeInputEqual(T, F, ZL_FS, ST.array(aStack))) {
									if (aStack.stackSize <= 0) slotKill(0);
									ItemStack[] tOutputs = tRecipe.getOutputs();
									for (int i = 0, j = Math.min(tOutputs.length, 12); i < j; i++) addStackToSlot(i+1, tOutputs[i]);
									tEntry.getKey().addExhaustion(tRecipe.getAbsoluteTotalPower() / 5000.0F);
									tEntry.getKey().swingItem();
								}
							}
						}
					}
				}
			}
		} else {
			if (aTimer % 5 == 0 && (mState & B[2]) != 0) {
				UT.Sounds.play(SFX.MC_DIG_SAND, 5, 1.0F, 1.0F, getCoords());
			}
		}
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[2] && tCoords[1] <= PX_P[2]) return T;
				if (slotHas(0)) {
					mState |= B[2];
				} else {
					mClickCount = 0;
					if (canInsertItem2(0, aPlayer.inventory.getCurrentItem(), aSide)) ST.move(aPlayer.inventory, this, aPlayer.inventory.currentItem, 0);
				}
			} else {
				for (int i = 1; i < 13; i++) UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, slotTake(i), F, worldObj, xCoord+0.5, yCoord+1, zCoord+0.5);
			}
		} else {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[2] && tCoords[1] <= PX_P[2]) {
					mRecipes.openNEI();
					return T;
				}
			}
		}
		return T;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mState != oState || mDisplayedOutput != oDisplayedOutput || mDisplayedInput != oDisplayedInput;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oState = mState;
		oDisplayedInput  = mDisplayedInput;
		oDisplayedOutput = mDisplayedOutput;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll)                             return getClientDataPacketByteArray(aSendAll, mState, UT.Code.toByteS(mDisplayedOutput, 0), UT.Code.toByteS(mDisplayedOutput, 1), UT.Code.toByteS(mDisplayedInput, 0), UT.Code.toByteS(mDisplayedInput, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		if (mDisplayedInput  != oDisplayedInput ) return getClientDataPacketByteArray(aSendAll, mState, UT.Code.toByteS(mDisplayedOutput, 0), UT.Code.toByteS(mDisplayedOutput, 1), UT.Code.toByteS(mDisplayedInput, 0), UT.Code.toByteS(mDisplayedInput, 1));
		if (mDisplayedOutput != oDisplayedOutput) return getClientDataPacketByteArray(aSendAll, mState, UT.Code.toByteS(mDisplayedOutput, 0), UT.Code.toByteS(mDisplayedOutput, 1));
		return getClientDataPacketByte(aSendAll, mState);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mState = aData[0];
		if (aData.length > 2) mDisplayedOutput = UT.Code.combine(aData[1], aData[2]);
		if (aData.length > 4) mDisplayedInput  = UT.Code.combine(aData[3], aData[4]);
		if (aData.length > 7) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[5]), UT.Code.unsignB(aData[6]), UT.Code.unsignB(aData[7])});
		return T;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mState = aData;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		boolean tGlow = mMaterial.contains(TD.Properties.GLOWING);
		
		mTextureLegs   = BlockTextureMulti.get(BlockTextureDefault.get(sTextureLegs  , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayLegs));
		mTextureGrid   = BlockTextureMulti.get(BlockTextureDefault.get(sTextureGrid  , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayGrid));
		mTextureBorder = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBorder, mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayBorder));
		mTexturePlate  = BlockTextureMulti.get(BlockTextureDefault.get(sTexturePlate , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayPlate));
		
		mTextureInput  = BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_64, F);
		mTextureOutput = BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_64, F);
		
		if (mDisplayedInput != 0) {
			if (UT.Code.exists(mDisplayedInput, OreDictMaterial.MATERIAL_ARRAY)) {
				OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[mDisplayedInput];
				mTextureInput = BlockTextureDefault.get(tMaterial, OP.blockDust.mIconIndexBlock, tMaterial.contains(TD.Properties.GLOWING));
			} else if (mDisplayedInput < 0) {
				switch(mDisplayedInput) {
				case  -1: mTextureInput  = BlockTextureCopied.get(Blocks.gravel         , SIDE_ANY, 0); break;
				case  -2: mTextureInput  = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 0); break;
				case  -3: mTextureInput  = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 1); break;
				case  -4: mTextureInput  = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 2); break;
				case  -5: mTextureInput  = BlockTextureCopied.get(Blocks.sand           , SIDE_ANY, 0); break;
				case  -6: mTextureInput  = BlockTextureCopied.get(Blocks.sand           , SIDE_ANY, 1); break;
				case  -7: mTextureInput  = BlockTextureCopied.get(Blocks.grass          , SIDE_ANY, 0, new short[] {106, 170,  64, 255}, F, F, F); break;
				case  -8: mTextureInput  = BlockTextureCopied.get(Blocks.mycelium       , SIDE_ANY, 0); break;
				case  -9: mTextureInput  = BlockTextureCopied.get(Blocks.soul_sand      , SIDE_ANY, 0); break;
				case -10: mTextureInput  = BlockTextureCopied.get(BlocksGT.Diggables    , SIDE_ANY, 0); break;
				case -11: mTextureInput  = BlockTextureCopied.get(BlocksGT.Sands        , SIDE_ANY, 0); break;
				}
			}
		}
		if (mDisplayedOutput != 0) {
			if (UT.Code.exists(mDisplayedOutput, OreDictMaterial.MATERIAL_ARRAY)) {
				OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[mDisplayedOutput];
				mTextureOutput = BlockTextureDefault.get(tMaterial, OP.blockDust.mIconIndexBlock, tMaterial.contains(TD.Properties.GLOWING));
			} else if (mDisplayedOutput < 0) {
				switch(mDisplayedOutput) {
				case  -1: mTextureOutput = BlockTextureCopied.get(Blocks.gravel         , SIDE_ANY, 0); break;
				case  -2: mTextureOutput = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 0); break;
				case  -3: mTextureOutput = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 1); break;
				case  -4: mTextureOutput = BlockTextureCopied.get(Blocks.dirt           , SIDE_ANY, 2); break;
				case  -5: mTextureOutput = BlockTextureCopied.get(Blocks.sand           , SIDE_ANY, 0); break;
				case  -6: mTextureOutput = BlockTextureCopied.get(Blocks.sand           , SIDE_ANY, 1); break;
				case  -7: mTextureOutput = BlockTextureCopied.get(Blocks.grass          , SIDE_ANY, 0, new short[] {106, 170,  64, 255}, F, F, F); break;
				case  -8: mTextureOutput = BlockTextureCopied.get(Blocks.mycelium       , SIDE_ANY, 0); break;
				case  -9: mTextureOutput = BlockTextureCopied.get(Blocks.soul_sand      , SIDE_ANY, 0); break;
				case -10: mTextureOutput = BlockTextureCopied.get(BlocksGT.Diggables    , SIDE_ANY, 0); break;
				case -11: mTextureOutput = BlockTextureCopied.get(BlocksGT.Sands        , SIDE_ANY, 0); break;
				}
			}
		}
		
		return 9;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_P[13], PX_N[14]); return T;
		case  1: box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_P[13], PX_N[14]); return T;
		case  2: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[14], PX_P[13], PX_N[ 0]); return T;
		case  3: box(aBlock, PX_P[14], PX_P[ 0], PX_P[14], PX_N[ 0], PX_P[13], PX_N[ 0]); return T;
		case  4: box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_P[12], PX_N[ 0]); return T;
		case  5: box(aBlock, PX_N[ 0]-0.0001F, PX_P[12], PX_N[ 0]-0.0001F, PX_P[ 0]+0.0001F, PX_P[10], PX_P[ 0]+0.0001F); return T;
		case  6: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 0], PX_N[ 0], PX_P[ 5], PX_N[ 0]); return T;
		case  7: box(aBlock, PX_P[ 2], PX_P[10]-0.0001F, PX_P[ 2], PX_N[ 2], PX_P[mDisplayedInput<0?16:14], PX_N[ 2]); return T;
		case  8: box(aBlock, PX_P[ 2], PX_P[ 5], PX_P[ 2], PX_N[ 2], PX_P[ 8], PX_N[ 2]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureLegs    = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/colored/legs"),
	sTextureGrid    = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/colored/grid"),
	sTextureBorder  = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/colored/border"),
	sTexturePlate   = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/colored/plate"),
	sOverlayLegs    = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/overlay/legs"),
	sOverlayGrid    = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/overlay/grid"),
	sOverlayBorder  = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/overlay/border"),
	sOverlayPlate   = new Textures.BlockIcons.CustomIcon("machines/tools/sifting_table/overlay/plate");
	
	private ITexture mTextureLegs, mTextureGrid, mTextureBorder, mTexturePlate, mTextureInput, mTextureOutput;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) return SIDES_TOP[aSide]?BlockTextureMulti.get(mTextureLegs, BI.nei()):mTextureLegs;
		if (aRenderPass <  4) return mTextureLegs;
		if (aRenderPass <  6) return SIDES_VERTICAL[aSide]?mTextureGrid:mTextureBorder;
		return aRenderPass == 6 ? mTexturePlate : aRenderPass == 7 ? (mState & B[0]) != 0 ? mTextureInput : null : (mState & B[1]) != 0 ? SIDES_TOP_HORIZONTAL[aSide] ? mTextureOutput : null : null;
	}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}

	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}

	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_N[ 4]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}

	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[13];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int getInventoryStackLimit() {return 1;}

	private static final int[] ACCESSIBLE_SLOTS = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

	@Override
	public int[] getAccessibleSlotsFromSide2(byte aSide) {
		return ACCESSIBLE_SLOTS;
	}

	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aSlot == 0 && mRecipes != null && mRecipes.containsInput(aStack, this, NI) && !OP.crushedPurifiedTiny.contains(aStack);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot != 0;}

	@Override public String getTileEntityName() {return "gt.multitileentity.sifter.table";}
}
