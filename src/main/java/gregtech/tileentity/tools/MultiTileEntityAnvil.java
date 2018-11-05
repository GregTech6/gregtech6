/**
 * Copyright (c) 2018 Gregorius Techneticies
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

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictMaterial;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAnvil extends TileEntityBase09FacingSingle implements IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public short mMaterialA = 0, mMaterialB = 0;
	public byte mStateA = 0, mStateB = 0;
	public RecipeMap mRecipes = RM.Anvil;
	public Recipe mLastRecipe = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_ANVIL_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			if (SIDES_TOP[aSide]) {
				// TODO Proper NEI Coordinates
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[SIDES_AXIS_Z[mFacing]?8:4] && tCoords[1] <= PX_P[SIDES_AXIS_X[mFacing]?8:4]) return T;
				
				
				// TODO Place/Remove Items when clicking.
			}
		} else {
			if (SIDES_TOP[aSide]) {
				// TODO Proper NEI Coordinates
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[SIDES_AXIS_Z[mFacing]?8:4] && tCoords[1] <= PX_P[SIDES_AXIS_X[mFacing]?8:4]) {
					mRecipes.openNEI();
					return T;
				}
			}
		}
		return T;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(), mStateA, mStateB, UT.Code.toByteS(mMaterialA, 0), UT.Code.toByteS(mMaterialA, 1), UT.Code.toByteS(mMaterialB, 0), UT.Code.toByteS(mMaterialB, 1));
		return super.getClientDataPacket(aSendAll);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		super.receiveDataByteArray(aData, aNetworkHandler);
		if (aData.length > 4) {
			mStateA = aData[5];
			mStateB = aData[6];
			mMaterialA = UT.Code.combine(aData[7], aData[ 8]);
			mMaterialB = UT.Code.combine(aData[9], aData[10]);
		}
		return T;
	}
	
	public ITexture mTextureAnvil, mTextureA, mTextureB;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureAnvil = BlockTextureDefault.get(mMaterial, OP.blockSolid.mIconIndexBlock, mMaterial.contains(TD.Properties.GLOWING));
		if (mMaterialA > 0 && OreDictMaterial.MATERIAL_ARRAY[mMaterialA] != null) mTextureA = BlockTextureDefault.get(OreDictMaterial.MATERIAL_ARRAY[mMaterialA], OP.blockSolid.mIconIndexBlock, OreDictMaterial.MATERIAL_ARRAY[mMaterialA].contains(TD.Properties.GLOWING));
		if (mMaterialB > 0 && OreDictMaterial.MATERIAL_ARRAY[mMaterialB] != null) mTextureB = BlockTextureDefault.get(OreDictMaterial.MATERIAL_ARRAY[mMaterialB], OP.blockSolid.mIconIndexBlock, OreDictMaterial.MATERIAL_ARRAY[mMaterialB].contains(TD.Properties.GLOWING));
		return 6;
	}
	
	@Override//TODO
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_P[13], PX_N[14]); return T;
		case  1: box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_P[13], PX_N[14]); return T;
		case  2: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[14], PX_P[13], PX_N[ 0]); return T;
		case  3: box(aBlock, PX_P[14], PX_P[ 0], PX_P[14], PX_N[ 0], PX_P[13], PX_N[ 0]); return T;
		case  4: box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_P[12], PX_N[ 0]); return T;
		case  5: box(aBlock, PX_N[ 0]-0.0001F, PX_P[12], PX_N[ 0]-0.0001F, PX_P[ 0]+0.0001F, PX_P[10], PX_P[ 0]+0.0001F); return T;
		case  6: box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 0], PX_N[ 0], PX_P[ 5], PX_N[ 0]); return T;
		case  7: box(aBlock, PX_P[ 2], PX_P[10]-0.0001F, PX_P[ 2], PX_N[ 2], PX_P[mMaterialA<0?16:14], PX_N[ 2]); return T;
		case  8: box(aBlock, PX_P[ 2], PX_P[ 5], PX_P[ 2], PX_N[ 2], PX_P[ 8], PX_N[ 2]); return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  3: return mTextureA;
		case  4: return mTextureB;
		case  5: return BI.nei();
		default: return mTextureAnvil;
		}
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_N[ 4]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[3];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return aSlot <  2 && mRecipes != null && mRecipes.containsInput(aStack, this, NI);}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == 2;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.anvil";}
}
