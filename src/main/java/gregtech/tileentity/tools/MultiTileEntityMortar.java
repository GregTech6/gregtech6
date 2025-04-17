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

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.*;
import gregapi.data.CS.*;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMortar extends TileEntityBase07Paintable implements ITileEntityQuickObstructionCheck, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips {
	protected RecipeMap mRecipes = RM.Mortar;
	protected Recipe mLastRecipe = null;
	public byte mStyle = 0;
	
	public static final OreDictMaterial MORTAR_MATERIALS[] = {ANY.Steel, MT.Netherite, ANY.Sapphire, ANY.Diamond, ANY.Amethyst};
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
		if (aNBT.hasKey(NBT_DESIGN)) mStyle = aNBT.getByte(NBT_DESIGN);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MORTAR_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[4] && tCoords[1] <= PX_P[4]) return T;
			}
			
			if (!UT.Entities.isPlayer(aPlayer)) return T;
			
			Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], NI, ZL_FS, aStack);
			if (tRecipe != null) {
				if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
				if (tRecipe.isRecipeInputEqual(T, F, ZL_FS, aStack)) {
					for (ItemStack tStack : tRecipe.getOutputs()) UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					aPlayer.addExhaustion(tRecipe.getAbsoluteTotalPower() / 250.0F);
					UT.Sounds.send(SFX.MC_DIG_ROCK, this, F);
					return T;
				}
			}
		} else {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[4] && tCoords[1] <= PX_P[4]) {
					mRecipes.openNEI();
					return T;
				}
			}
		}
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 7;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[10], PX_N[ 2]); return T;
		case  1: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[12]); return T;
		case  2: box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[ 2]); return T;
		case  3: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[12], PX_N[ 2], PX_N[10], PX_N[ 2]); return T;
		case  4: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[15], PX_N[ 2]); return T;
		case  5: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 7], PX_N[ 6]); return T;
		case  6: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[10]+0.001F, PX_N[12]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/bottom"),
	sTextureMiddleTop   = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/middletop"),
	sTextureMiddleSide  = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/colored/middleside"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/bottom"),
	sOverlayMiddleTop   = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/middletop"),
	sOverlayMiddleSide  = new Textures.BlockIcons.CustomIcon("machines/tools/mortar/overlay/middleside");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  2: return SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  1: return SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  3: return SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  4: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop    , mRGBa), BlockTextureDefault.get(sOverlayTop    )):SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom)):null;
		case  5: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureMiddleTop, MORTAR_MATERIALS[mStyle % MORTAR_MATERIALS.length].fRGBaSolid), BlockTextureDefault.get(sOverlayMiddleTop)):SIDE_BOTTOM == aSide ? null : BlockTextureMulti.get(BlockTextureDefault.get(sTextureMiddleSide, MORTAR_MATERIALS[mStyle % MORTAR_MATERIALS.length].fRGBaSolid), BlockTextureDefault.get(sOverlayMiddleSide));
		case  6: return SIDE_TOP    == aSide?BI.nei():null;
		}
		return null;
	}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		box(aAABB, aList, PX_P[12], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[12], PX_N[ 2], PX_N[10], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[10], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[12]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[15], PX_N[ 2]);
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[ 2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[ 2]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[10], PX_N[ 2]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_P[10]:PX_P[2];}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	// Inventory Stuff
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.mortar";}
}
