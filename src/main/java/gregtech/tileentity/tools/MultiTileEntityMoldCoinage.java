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

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.machines.ITileEntityAnvil;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.tileentity.placeables.MultiTileEntityCoin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMoldCoinage extends TileEntityBase07Paintable implements ITileEntityAnvil, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips, IMTE_SyncDataShort {
	protected boolean mIsUnique = F;
	protected short mDisplayedMetal = -1, oDisplayedMetal = -1;
	protected boolean[][][] mShape = new boolean[2][16][16];
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		for (int i = 0; i < mShape[0].length; i++) mShape[0][i] = UT.Code.getBitsS(aNBT.getShort("gt.coin.shape.0."+i));
		for (int i = 0; i < mShape[1].length; i++) mShape[1][i] = UT.Code.getBitsS(aNBT.getShort("gt.coin.shape.1."+i));
		mIsUnique = aNBT.getBoolean("gt.coin.unique");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, "gt.coin.unique", mIsUnique);
		for (int i = 0; i < mShape[0].length; i++) aNBT.setShort("gt.coin.shape.0."+i, (short)UT.Code.getBits(mShape[0][i]));
		for (int i = 0; i < mShape[1].length; i++) aNBT.setShort("gt.coin.shape.1."+i, (short)UT.Code.getBits(mShape[1][i]));
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, "gt.coin.unique", mIsUnique);
		for (int i = 0; i < mShape[0].length; i++) aNBT.setShort("gt.coin.shape.0."+i, (short)UT.Code.getBits(mShape[0][i]));
		for (int i = 0; i < mShape[1].length; i++) aNBT.setShort("gt.coin.shape.1."+i, (short)UT.Code.getBits(mShape[1][i]));
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MOLD_COINAGE));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MOLD_SELECT) + " (Doesn't work right now)");
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (ST.invalid(slot(0))) {
				mDisplayedMetal = 0;
			} else {
				OreDictItemData tData = OM.data_(slot(0));
				if (tData != null && tData.mMaterial != null) {
					mDisplayedMetal = tData.mMaterial.mMaterial.mID;
				}
			}
		}
	}
	
	@Override public boolean attachCoversFirst(byte aSide) {return F;}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP_HORIZONTAL[aSide]) {
			ItemStack tOutputStack = slot(0);
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (tOutputStack == null) {
				OreDictItemData tData = OM.anyassociation(aStack);
				if (tData != null && tData.mPrefix == OP.plateTiny) {
					if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
					slot(0, ST.amount(1, aStack));
					UT.Sounds.send(SFX.MC_CLICK, this, F);
					return T;
				}
			} else {
				if (UT.Inventories.addStackToPlayerInventory(aPlayer, tOutputStack, F)) slotKill(0);
			}
		}
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_hammer) && SIDES_TOP[aSide]) {
			if (aRemainingDurability >= 2000) {
				OreDictItemData tData = OM.anyassociation(slot(0));
				if (tData != null && tData.mPrefix == OP.plateTiny) {
					slot(0, MultiTileEntityCoin.getCoin(slot(0).stackSize, tData.mMaterial.mMaterial, mIsUnique, mShape));
					return 2000;
				}
				return 0;
			}
			UT.Sounds.send(SFX.MC_BREAK, this, F);
			return aRemainingDurability;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mDisplayedMetal != oDisplayedMetal;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplayedMetal = mDisplayedMetal;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(T, UT.Code.toByteS(mDisplayedMetal, 0), UT.Code.toByteS(mDisplayedMetal, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		return getClientDataPacketShort(F, mDisplayedMetal);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplayedMetal = UT.Code.combine(aData[0], aData[1]);
		if (aData.length >= 5) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
		return T;
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mDisplayedMetal = aData;
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 7;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]); return T;}
		if (aRenderPass == 1) {box(aBlock, PX_P[ 4], PX_P[12], PX_P[ 4], PX_N[ 4], PX_N[ 2], PX_N[ 4]); return T;}
		if (aRenderPass == 2) {box(aBlock, PX_P[ 5], PX_P[11], PX_P[ 5], PX_N[ 5], PX_N[ 3], PX_N[ 5]); return T;}
		
		if (aRenderPass == 3) {box(aBlock, PX_P[11], PX_P[12], PX_P[ 5], PX_N[ 5], PX_N[ 2], PX_N[ 5]); return T;}
		if (aRenderPass == 4) {box(aBlock, PX_P[ 5], PX_P[12], PX_P[11], PX_N[ 5], PX_N[ 2], PX_N[ 5]); return T;}
		if (aRenderPass == 5) {box(aBlock, PX_P[ 5], PX_P[12], PX_P[ 5], PX_N[11], PX_N[ 2], PX_N[ 5]); return T;}
		if (aRenderPass == 6) {box(aBlock, PX_P[ 5], PX_P[12], PX_P[ 5], PX_N[ 5], PX_N[ 2], PX_N[11]); return T;}
		return F;
	}
	
	public static IIconContainer
	sTextureTop     = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/colored/top"),
	sTextureBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/colored/bottom"),
	sTextureSide    = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/colored/side"),
	sTextureHole    = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/colored/hole"),
	sOverlayTop     = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/overlay/top"),
	sOverlayBottom  = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/overlay/bottom"),
	sOverlaySide    = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/overlay/side"),
	sOverlayHole    = new Textures.BlockIcons.CustomIcon("machines/tools/mold_coinage/overlay/hole");
	
	private ITexture mTextureHole, mTextureSides[] = new ITexture[3], mTextureContent;
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0 && aSide == 0) {
			boolean tGlow = mMaterial.contains(TD.Properties.GLOWING);
			
			mTextureHole     = BlockTextureMulti.get(BlockTextureDefault.get(sTextureHole       , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayHole));
			mTextureSides[0] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom     , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayBottom));
			mTextureSides[1] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop        , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlayTop));
			mTextureSides[2] = BlockTextureMulti.get(BlockTextureDefault.get(sTextureSide       , mRGBa, F, tGlow, F, F), BlockTextureDefault.get(sOverlaySide));
			if (mDisplayedMetal != 0 && UT.Code.exists(mDisplayedMetal, OreDictMaterial.MATERIAL_ARRAY)) {
				OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[mDisplayedMetal];
				mTextureContent = BlockTextureDefault.get(tMaterial, OP.blockSolid);
			} else {
				mTextureContent = null;
			}
		}
		
		if (aRenderPass == 1) return SIDES_TOP[aSide]?mTextureHole:mTextureSides[FACES_TBS[aSide]];
		if (aRenderPass == 2) return mTextureContent;
		if (aRenderPass == 3) return aSide == SIDE_X_NEG ? mTextureSides[FACES_TBS[aSide]] : null;
		if (aRenderPass == 4) return aSide == SIDE_Z_NEG ? mTextureSides[FACES_TBS[aSide]] : null;
		if (aRenderPass == 5) return aSide == SIDE_X_POS ? mTextureSides[FACES_TBS[aSide]] : null;
		if (aRenderPass == 6) return aSide == SIDE_Z_POS ? mTextureSides[FACES_TBS[aSide]] : null;
		
		return mTextureSides[FACES_TBS[aSide]];
	}
	
	@Override public boolean isAnvil(byte aSide) {return SIDES_TOP[aSide];}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 4], PX_N[ 0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_N[ 4]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int getInventoryStackLimit() {return 1;}
	
	private static final int[] ACCESSIBLE_SLOTS = new int[] {0};
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {OreDictItemData tData = OM.anyassociation(aStack     ); return tData != null && tData.mPrefix == OP.plateTiny;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {OreDictItemData tData = OM.anyassociation(slot(aSlot)    ); return tData == null || tData.mPrefix != OP.plateTiny;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.mold.coins";}
}
