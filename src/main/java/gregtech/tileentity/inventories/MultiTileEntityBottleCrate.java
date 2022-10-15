/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.tileentity.inventories;

import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.code.ItemStackContainer;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBottleCrate extends TileEntityBase09FacingSingle implements IMTE_SetBlockBoundsBasedOnState, IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public short mDisplay[] = new short[9];
	
	public IIconContainer mIcon = Textures.BlockIcons.RENDERING_ERROR;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_TEXTURE)) {
			short tShelfID = aNBT.getShort(NBT_TEXTURE);
			if (UT.Code.exists(tShelfID, PlankData.PLANK_ICONS)) mIcon = PlankData.PLANK_ICONS[tShelfID];
		}
		if (mIcon == null || mIcon == Textures.BlockIcons.RENDERING_ERROR) mIcon = mMaterial.mTextureSetsBlock.get(OP.casingMachine.mIconIndexBlock);
		for (int i = 0; i < mDisplay.length; i++) {
			//TODO
			mDisplay[i] = (short)-i;
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.NO_GUI_CLICK_TO_INTERACT));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override//TODO
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mInventoryChanged) {
			for (int i = 0; i < mDisplay.length; i++) {
				Byte tID = (slotHas(i)?BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i))):null);
				if ((tID == null || tID == 0) && slotHas(i)) tID = BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i), W));
				if (tID == null) tID = (byte)0;
				if (tID != mDisplay[i]) {mDisplay[i] = tID; updateClientData();}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!SIDES_TOP[aSide]) return F;
		if (aHitX >= PX_P[1] && aHitX <= PX_N[1] && aHitZ >= PX_P[1] && aHitZ <= PX_N[1]) {
			if (isServerSide()) swapBottles(aPlayer, (aHitX<PX_P[5]+PX_P[1]/2?0:aHitX<PX_P[10]+PX_P[1]/2?1:2) + (aHitZ<PX_P[5]+PX_P[1]/2?0:aHitZ<PX_P[10]+PX_P[1]/2?3:6));
			return T;
		}
		return F;
	}
	
	private boolean swapBottles(EntityPlayer aPlayer, int aSlot) {
		if (slotHas(aSlot)) {
			if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(aSlot), T)) {
				slotKill(aSlot);
				updateInventory();
				playCollect();
				return T;
			}
			return F;
		}
		ItemStack tStack = aPlayer.getCurrentEquippedItem();
		if (ST.valid(tStack) && canInsertItem2(aSlot, tStack, SIDE_TOP)) {
			slot(aSlot, ST.copy(tStack));
			tStack.stackSize = 0;
			updateInventory();
			playCollect();
			return T;
		}
		return F;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData(), UT.Code.toByteS(mDisplay[0], 0), UT.Code.toByteS(mDisplay[0], 1), UT.Code.toByteS(mDisplay[1], 0), UT.Code.toByteS(mDisplay[1], 1), UT.Code.toByteS(mDisplay[2], 0), UT.Code.toByteS(mDisplay[2], 1), UT.Code.toByteS(mDisplay[3], 0), UT.Code.toByteS(mDisplay[3], 1), UT.Code.toByteS(mDisplay[4], 0), UT.Code.toByteS(mDisplay[4], 1), UT.Code.toByteS(mDisplay[5], 0), UT.Code.toByteS(mDisplay[5], 1), UT.Code.toByteS(mDisplay[6], 0), UT.Code.toByteS(mDisplay[6], 1), UT.Code.toByteS(mDisplay[7], 0), UT.Code.toByteS(mDisplay[7], 1), UT.Code.toByteS(mDisplay[8], 0), UT.Code.toByteS(mDisplay[8], 1));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setDirectionData(aData[3]);
		//TODO for (int i = 0; i < mDisplay.length; i++) mDisplay[i] = UT.Code.combine(aData[i*2+4], aData[i*2+5]);
		return T;
	}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {mTexture = BlockTextureDefault.get(mIcon, mRGBa); return 36;}
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < 9) return T;
		short tDisplay = mDisplay[(aRenderPass-9)/3];
		if (tDisplay == 0) return F;
		if ((aRenderPass-9)%3==0) {
			if (tDisplay == Short.MIN_VALUE) return F;
			mTextureFluid = (tDisplay < 0 ? BlockTextureFluid.get(FL.fluid(-tDisplay-1)) : UT.Code.exists(tDisplay, OreDictMaterial.MATERIAL_ARRAY)?OreDictMaterial.MATERIAL_ARRAY[tDisplay].getTextureMolten():null);
		}
		return T;
	}
	
	private ITexture mTexture, mTextureFluid;
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return box(aBlock, PX_P[                          1], PX_P[                          0], PX_P[                          1], PX_N[                          1], PX_N[                         15], PX_N[                          1]);
		case  1: return box(aBlock, PX_P[                          1], PX_P[                          1], PX_P[                          5], PX_N[                          1], PX_N[SIDES_AXIS_X[mFacing]?12:11], PX_N[                         10]);
		case  2: return box(aBlock, PX_P[                          1], PX_P[                          1], PX_P[                         10], PX_N[                          1], PX_N[SIDES_AXIS_X[mFacing]?12:11], PX_N[                          5]);
		case  3: return box(aBlock, PX_P[                          5], PX_P[                          1], PX_P[                          1], PX_N[                         10], PX_N[SIDES_AXIS_X[mFacing]?11:12], PX_N[                          1]);
		case  4: return box(aBlock, PX_P[                         10], PX_P[                          1], PX_P[                          1], PX_N[                          5], PX_N[SIDES_AXIS_X[mFacing]?11:12], PX_N[                          1]);
		case  5: return box(aBlock, PX_P[                          0], PX_P[                          0], PX_P[                          0], PX_N[SIDES_AXIS_X[mFacing]? 0:15], PX_N[                          8], PX_N[SIDES_AXIS_X[mFacing]?15: 0]);
		case  6: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0:15], PX_P[                          0], PX_P[SIDES_AXIS_X[mFacing]?15: 0], PX_N[                          0], PX_N[                          8], PX_N[                          0]);
		case  7: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 0: 1], PX_P[                          5], PX_P[SIDES_AXIS_X[mFacing]? 1: 0], PX_N[SIDES_AXIS_X[mFacing]?15: 1], PX_N[                          9], PX_N[SIDES_AXIS_X[mFacing]? 1:15]);
		case  8: return box(aBlock, PX_P[SIDES_AXIS_X[mFacing]?15: 1], PX_P[                          5], PX_P[SIDES_AXIS_X[mFacing]? 1:15], PX_N[SIDES_AXIS_X[mFacing]? 0: 1], PX_N[                          9], PX_N[SIDES_AXIS_X[mFacing]? 1: 0]);
		
		case  9: return box(aBlock, PX_P[ 1]+PX_OFFSET*2, PX_P[ 1], PX_P[ 1]+PX_OFFSET*2, PX_P[ 5]-PX_OFFSET*2, PX_P[12], PX_P[ 5]-PX_OFFSET*2);
		case 10: return box(aBlock, PX_P[ 1]+PX_OFFSET  , PX_P[ 1], PX_P[ 1]+PX_OFFSET  , PX_P[ 5]-PX_OFFSET  , PX_P[13], PX_P[ 5]-PX_OFFSET  );
		case 11: return box(aBlock, PX_P[ 2]            , PX_P[13], PX_P[ 2]            , PX_P[ 4]            , PX_P[16], PX_P[ 4]            );
		case 12: return box(aBlock, PX_P[ 6]+PX_OFFSET*2, PX_P[ 1], PX_P[ 1]+PX_OFFSET*2, PX_P[10]-PX_OFFSET*2, PX_P[12], PX_P[ 5]-PX_OFFSET*2);
		case 13: return box(aBlock, PX_P[ 6]+PX_OFFSET  , PX_P[ 1], PX_P[ 1]+PX_OFFSET  , PX_P[10]-PX_OFFSET  , PX_P[13], PX_P[ 5]-PX_OFFSET  );
		case 14: return box(aBlock, PX_P[ 7]            , PX_P[13], PX_P[ 2]            , PX_P[ 9]            , PX_P[16], PX_P[ 4]            );
		case 15: return box(aBlock, PX_P[11]+PX_OFFSET*2, PX_P[ 1], PX_P[ 1]+PX_OFFSET*2, PX_P[15]-PX_OFFSET*2, PX_P[12], PX_P[ 5]-PX_OFFSET*2);
		case 16: return box(aBlock, PX_P[11]+PX_OFFSET  , PX_P[ 1], PX_P[ 1]+PX_OFFSET  , PX_P[15]-PX_OFFSET  , PX_P[13], PX_P[ 5]-PX_OFFSET  );
		case 17: return box(aBlock, PX_P[12]            , PX_P[13], PX_P[ 2]            , PX_P[14]            , PX_P[16], PX_P[ 4]            );
		case 18: return box(aBlock, PX_P[ 1]+PX_OFFSET*2, PX_P[ 1], PX_P[ 6]+PX_OFFSET*2, PX_P[ 5]-PX_OFFSET*2, PX_P[12], PX_P[10]-PX_OFFSET*2);
		case 19: return box(aBlock, PX_P[ 1]+PX_OFFSET  , PX_P[ 1], PX_P[ 6]+PX_OFFSET  , PX_P[ 5]-PX_OFFSET  , PX_P[13], PX_P[10]-PX_OFFSET  );
		case 20: return box(aBlock, PX_P[ 2]            , PX_P[13], PX_P[ 7]            , PX_P[ 4]            , PX_P[16], PX_P[ 9]            );
		case 21: return box(aBlock, PX_P[ 6]+PX_OFFSET*2, PX_P[ 1], PX_P[ 6]+PX_OFFSET*2, PX_P[10]-PX_OFFSET*2, PX_P[12], PX_P[10]-PX_OFFSET*2);
		case 22: return box(aBlock, PX_P[ 6]+PX_OFFSET  , PX_P[ 1], PX_P[ 6]+PX_OFFSET  , PX_P[10]-PX_OFFSET  , PX_P[13], PX_P[10]-PX_OFFSET  );
		case 23: return box(aBlock, PX_P[ 7]            , PX_P[13], PX_P[ 7]            , PX_P[ 9]            , PX_P[16], PX_P[ 9]            );
		case 24: return box(aBlock, PX_P[11]+PX_OFFSET*2, PX_P[ 1], PX_P[ 6]+PX_OFFSET*2, PX_P[15]-PX_OFFSET*2, PX_P[12], PX_P[10]-PX_OFFSET*2);
		case 25: return box(aBlock, PX_P[11]+PX_OFFSET  , PX_P[ 1], PX_P[ 6]+PX_OFFSET  , PX_P[15]-PX_OFFSET  , PX_P[13], PX_P[10]-PX_OFFSET  );
		case 26: return box(aBlock, PX_P[12]            , PX_P[13], PX_P[ 7]            , PX_P[14]            , PX_P[16], PX_P[ 9]            );
		case 27: return box(aBlock, PX_P[ 1]+PX_OFFSET*2, PX_P[ 1], PX_P[11]+PX_OFFSET*2, PX_P[ 5]-PX_OFFSET*2, PX_P[12], PX_P[15]-PX_OFFSET*2);
		case 28: return box(aBlock, PX_P[ 1]+PX_OFFSET  , PX_P[ 1], PX_P[11]+PX_OFFSET  , PX_P[ 5]-PX_OFFSET  , PX_P[13], PX_P[15]-PX_OFFSET  );
		case 29: return box(aBlock, PX_P[ 2]            , PX_P[13], PX_P[12]            , PX_P[ 4]            , PX_P[16], PX_P[14]            );
		case 30: return box(aBlock, PX_P[ 6]+PX_OFFSET*2, PX_P[ 1], PX_P[11]+PX_OFFSET*2, PX_P[10]-PX_OFFSET*2, PX_P[12], PX_P[15]-PX_OFFSET*2);
		case 31: return box(aBlock, PX_P[ 6]+PX_OFFSET  , PX_P[ 1], PX_P[11]+PX_OFFSET  , PX_P[10]-PX_OFFSET  , PX_P[13], PX_P[15]-PX_OFFSET  );
		case 32: return box(aBlock, PX_P[ 7]            , PX_P[13], PX_P[12]            , PX_P[ 9]            , PX_P[16], PX_P[14]            );
		case 33: return box(aBlock, PX_P[11]+PX_OFFSET*2, PX_P[ 1], PX_P[11]+PX_OFFSET*2, PX_P[15]-PX_OFFSET*2, PX_P[12], PX_P[15]-PX_OFFSET*2);
		case 34: return box(aBlock, PX_P[11]+PX_OFFSET  , PX_P[ 1], PX_P[11]+PX_OFFSET  , PX_P[15]-PX_OFFSET  , PX_P[13], PX_P[15]-PX_OFFSET  );
		case 35: return box(aBlock, PX_P[12]            , PX_P[13], PX_P[12]            , PX_P[14]            , PX_P[16], PX_P[14]            );
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass < 9) return mTexture;
		if (SIDES_BOTTOM[aSide]) return null;
		switch ((aRenderPass-9)%3) {
		default: return mTextureFluid;
		case  1: return SIDES_TOP[aSide] ? Textures.BlockIcons.BOTTLECRATE_BOTTLE_TOP.mTexture : Textures.BlockIcons.BOTTLECRATE_BOTTLE_SIDES.mTexture;
		case  2: return SIDES_TOP[aSide] ? Textures.BlockIcons.BOTTLECRATE_BOTTLE_CAP.mTexture : Textures.BlockIcons.BOTTLECRATE_BOTTLE_SIDES.mTexture;
		}
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[0],PX_P[0],PX_P[0],PX_N[0],PX_N[6],PX_N[0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[0],PX_P[0],PX_P[0],PX_N[0],PX_P[6],PX_N[0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)   {box(aBlock,PX_P[0],PX_P[0],PX_P[0],PX_N[0],PX_N[8],PX_N[0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return 0;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?0.5F:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[mDisplay.length];}
	@Override public boolean canDrop (int aSlot) {return F;}
	@Override public boolean keepSlot(int aSlot) {return T;}
	@Override public ItemStack getDefaultStack(int aSlot) {return IL.Bottle_Empty.get(1);}
	
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return T;}//TODO
	
	@Override public String getTileEntityName() {return "gt.multitileentity.crate.bottles";}
}
