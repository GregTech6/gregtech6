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

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SyncDataShort;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityDustFunnel extends TileEntityBase07Paintable implements ITileEntityAdjacentInventoryUpdatable, IMTE_AddToolTips, IMTE_SyncDataShort {
	public static final OreDictPrefix[] DUST_TYPES = {OP.blockDust, OP.dust, OP.dustSmall, OP.dustTiny, OP.dustDiv72};
	
	protected byte mMode = 0;
	protected short mDust = 0, oDust = -1;
	protected OreDictMaterialStack mContent = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_MATERIALS)) mContent = OreDictMaterialStack.load(NBT_MATERIALS, aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_MODE, mMode);
		if (mContent != null) mContent.save(NBT_MATERIALS, aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT.setByte(NBT_MODE, mMode);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_DUSTFUNNEL));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			boolean temp = (mInventoryChanged || aTimer % 100 == 0);
			
			slotNull(0);
			
			if (temp && !slotHas(0)) ST.move(getAdjacentInventory(SIDE_TOP), delegator(SIDE_TOP));
			
			if (slotHas(0) && (mContent == null || mContent.mAmount < DUST_TYPES[mMode].mAmount)) {
				OreDictItemData tData = OM.anydata(slot(0));
				if (OM.prefixcontainsmaterialmatches(tData, mContent == null || mContent.mMaterial == MT.NULL ? null : mContent.mMaterial, TD.Prefix.DUST_BASED)) {
					int tSize = (int)Math.min(slot(0).stackSize, UT.Code.divup(DUST_TYPES[mMode].mAmount - (mContent == null ? 0 : mContent.mAmount), tData.mMaterial.mAmount));
					mContent = OM.stack(tData.mMaterial.mMaterial, tData.mMaterial.mAmount * tSize + (mContent == null ? 0 : mContent.mAmount));
					decrStackSize(0, tSize);
					temp = T;
				}
			}
			
			mDust = (mContent != null && (mContent.mAmount > 0 || slotHas(1)) ? mContent.mMaterial.mID : 0);
			
			if (mContent != null && !slotHas(1) && mContent.mAmount >= DUST_TYPES[mMode].mAmount) {
				ItemStack tStack = DUST_TYPES[mMode].mat(mContent.mMaterial, 1);
				if (tStack != null) {
					slot(1, tStack);
					mContent.mAmount -= DUST_TYPES[mMode].mAmount;
					if (mContent.mAmount <= 0) mContent = null;
					temp = T;
				}
			}
			
			if (temp && slotHas(1)) ST.move(delegator(SIDE_BOTTOM), getAdjacentInventory(SIDE_BOTTOM));
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide] && canInsertItem2(0, aPlayer.inventory.getCurrentItem(), aSide)) ST.move(aPlayer.inventory, this, aPlayer.inventory.currentItem, 0);
		return T;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_monkeywrench)) {
			mMode = (byte)((DUST_TYPES.length + mMode + (aSneaking?-1:+1)) % DUST_TYPES.length);
			if (aChatReturn != null) aChatReturn.add("Outputs in the Size of " + DUST_TYPES[mMode].mNameLocal);
			updateClientData();
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add("Outputs in the Size of " + DUST_TYPES[mMode].mNameLocal);
			return 1;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || (mDust != oDust && isTopVisible());
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDust = mDust;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(mDust, 0), UT.Code.toByteS(mDust, 1), mMode, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		return getClientDataPacketShort(aSendAll, mDust);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDust = UT.Code.combine(aData[0], aData[1]);
		mMode = aData[2];
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4]), UT.Code.unsignB(aData[5])});
		return T;
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mDust = aData;
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 0], PX_P[ 8], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return T;
		case  1: box(aBlock, PX_P[ 2+mMode], PX_P[ 0], PX_P[ 2+mMode], PX_N[ 2+mMode], PX_N[ 8], PX_N[ 2+mMode]); return T;
		case  2: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_P[ 2], PX_P[ 8], PX_P[ 2]); return T;
		case  3: box(aBlock, PX_P[ 0], PX_P[ 0], PX_N[ 2], PX_P[ 2], PX_P[ 8], PX_N[ 0]); return T;
		case  4: box(aBlock, PX_N[ 2], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_P[ 8], PX_P[ 2]); return T;
		case  5: box(aBlock, PX_N[ 2], PX_P[ 0], PX_N[ 2], PX_N[ 0], PX_P[ 8], PX_N[ 0]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureTop     = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/colored/top"),
	sTextureSides   = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/colored/sides"),
	sTextureBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/colored/bottom"),
	sTextureHole    = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/colored/hole"),
	sOverlayTop     = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/overlay/top"),
	sOverlaySides   = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/overlay/sides"),
	sOverlayBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/overlay/bottom"),
	sOverlayHole    = new Textures.BlockIcons.CustomIcon("machines/tools/dust_funnel/overlay/hole");
	
	private ITexture mTextureSides, mTextureBottom, mTextureInput;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0 && aSide == 0) {
			boolean tGlow = mMaterial.contains(TD.Properties.GLOWING);
			
			mTextureSides         = BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides   , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlaySides));
			mTextureBottom        = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom  , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayBottom));
			
			if (mDust == 0) {
				mTextureInput     = BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop     , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayTop)     , BlockTextureDefault.get(sTextureHole, mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayHole));
			} else {
				if (UT.Code.exists(mDust, OreDictMaterial.MATERIAL_ARRAY)) {
					OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[mDust];
					mTextureInput = BlockTextureMulti.get(BlockTextureDefault.get(tMaterial, OP.blockDust), BlockTextureDefault.get(sTextureHole, mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayHole));
				} else {
					mTextureInput = BlockTextureMulti.get(BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_64, F), BlockTextureDefault.get(sTextureHole, mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayHole));
				}
			}
		}
		
		return SIDES_TOP[aSide] ? aRenderPass == 0 ? mTextureInput : null : SIDES_BOTTOM[aSide] ? mTextureBottom : mTextureSides;
	}
	
	public boolean isTopVisible() {
		return !hasCovers() || mCovers.mBehaviours[SIDE_TOP]==null || !mCovers.mBehaviours[SIDE_TOP].isOpaque(SIDE_TOP, mCovers);
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_TOP[aSide]?PX_P[16]:SIDES_BOTTOM[aSide]?PX_P[12-2*mMode]:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_TOP[aSide]?PX_P[12]:PX_P[12-2*mMode];}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_HORIZONTAL[aSide]?PX_P[2+mMode]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return SIDES_TOP[aSide];}
	
	// Inventory Stuff
	@Override public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity) {if (SIDES_VERTICAL[aSide]) updateInventory();}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[2];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int getInventoryStackLimit() {return 64;}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	
	private static final int[] ACCESSIBLE_SLOTS = new int[] {0, 1};
	
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (aSlot == 0 && !slotHas(0) && SIDES_TOP_HORIZONTAL[aSide]) {
			OreDictItemData tData = OM.anydata(aStack);
			return OM.prefixcontainsmaterialmatches(tData, mContent == null ? null : mContent.mMaterial, TD.Prefix.DUST_BASED) && DUST_TYPES[mMode].mat(tData.mMaterial.mMaterial, 1) != null;
		}
		return F;
	}
	
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot == 1;}
	
	@Override
	public boolean breakBlock() {
		if (isServerSide()) ST.drop(worldObj, getCoords(), OM.dust(mContent));
		return super.breakBlock();
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.funnel.dust";}
}
