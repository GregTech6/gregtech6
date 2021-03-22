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

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBasin extends MultiTileEntityMold {
	@Override
	public OreDictPrefix getMoldRecipe(int aShape) {
		return OP.blockSolid;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MOLD) + " " + getMoldRecipe(0).mNameLocal);
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
		if (mAcidProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + getMoldMaxTemperature() + " K)");
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TAKE_PINCERS));
	}
	
	@Override
	public boolean isMoldInputSide(byte aSide) {
		return SIDES_TOP[aSide];
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (SIDES_TOP[aSide]) {
			if (isServerSide()) pickUpItem(aPlayer, T);
			return T;
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {if (aChatReturn != null) aChatReturn.add("Temperature: " + mTemperature + "K"); return 10000;}
		if (aTool.equals(TOOL_pincers)) {
			if (aPlayer instanceof EntityPlayer && SIDES_TOP[aSide] && pickUpItem((EntityPlayer)aPlayer, F)) {
				return 2000;
			}
		}
		if (aTool.equals(TOOL_chisel) || aTool.equals(TOOL_softhammer) || aTool.equals(TOOL_magnifyingglass) || aTool.equals(TOOL_wrench) || aTool.equals(TOOL_monkeywrench) || aTool.equals(TOOL_screwdriver) || aTool.equals(TOOL_rotator)) {
			return 0;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(T, UT.Code.toByteS(mDisplay, 0), UT.Code.toByteS(mDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		super.getRenderPasses2(aBlock, aShouldSideBeRendered);
		return 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[15], PX_N[ 0], PX_N[ 0]); return T;
		case  1: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[15]); return T;
		case  2: box(aBlock, PX_P[15], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case  3: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[15], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case  4: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[15], PX_N[ 0]); return T;
		case  5: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 1], PX_N[ 0]); return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: case  2: return SIDES_AXIS_Z[aSide]||aSide==SIDE_BOTTOM?null:mTexture;
		case  1: case  3: return SIDES_AXIS_X[aSide]||aSide==SIDE_BOTTOM?null:mTexture;
		case  4: return SIDES_VERTICAL[aSide]?mTexture:null;
		case  5: return SIDES_TOP[aSide]?mTextureMolten:null;
		}
		return mTexture;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 2], PX_P[ 2], PX_P[ 2], PX_N[ 2], PX_N[ 2], PX_N[ 2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);}
	
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		box(aAABB, aList, PX_P[14], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[14], PX_N[ 1], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[14], PX_N[ 1], PX_N[ 1]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[ 1], PX_N[14]);
		box(aAABB, aList, PX_P[ 1], PX_P[ 1], PX_P[ 1], PX_N[ 1], PX_N[14], PX_N[ 1]);
	}
	
	@Override public float getSurfaceSize           (byte aSide) {return 1.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 1.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_BOTTOM_HORIZONTAL[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.basin";}
}
