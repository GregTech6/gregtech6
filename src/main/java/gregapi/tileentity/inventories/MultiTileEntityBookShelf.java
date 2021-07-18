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

package gregapi.tileentity.inventories;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetEnchantPowerBonus;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.PlankData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MD;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.dummies.DummyInventory;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.network.packets.PacketItemStackChat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBookShelf extends TileEntityBase09FacingSingle implements ITileEntityBookShelf, IMTE_GetEnchantPowerBonus, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public byte mDisplay[] = new byte[28], mRedstoneDelay = 0;
	
	public IIconContainer mShelfIcon = Textures.BlockIcons.RENDERING_ERROR;
	
	public String mDungeonLootNameFront = "", mDungeonLootNameBack = "";
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.dungeonloot.front")) mDungeonLootNameFront = aNBT.getString("gt.dungeonloot.front");
		if (aNBT.hasKey("gt.dungeonloot.back")) mDungeonLootNameBack = aNBT.getString("gt.dungeonloot.back");
		if (aNBT.hasKey(NBT_REDSTONE)) mRedstoneDelay = aNBT.getByte(NBT_REDSTONE);
		if (aNBT.hasKey(NBT_TEXTURE)) {
			short tShelfID = aNBT.getShort(NBT_TEXTURE);
			if (UT.Code.exists(tShelfID, PlankData.PLANK_ICONS)) mShelfIcon = PlankData.PLANK_ICONS[tShelfID];
		}
		if (mShelfIcon == null || mShelfIcon == Textures.BlockIcons.RENDERING_ERROR) mShelfIcon = mMaterial.mTextureSetsBlock.get(OP.casingMachine.mIconIndexBlock);
		for (int i = 0; i < 28; i++) {
			Byte tID = (slotHas(i)?BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i))):null);
			if ((tID == null || tID == 0) && slotHas(i)) tID = BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i), W));
			mDisplay[i] = (tID==null?0:tID);
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (UT.Code.stringValid(mDungeonLootNameFront)) aNBT.setString("gt.dungeonloot.front", mDungeonLootNameFront);
		if (UT.Code.stringValid(mDungeonLootNameBack)) aNBT.setString("gt.dungeonloot.back", mDungeonLootNameBack);
		if (mRedstoneDelay != 0) aNBT.setByte(NBT_REDSTONE, mRedstoneDelay);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.NO_GUI_CLICK_TO_INTERACT));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean breakBlock() {
		generateDungeonLoot();
		return super.breakBlock();
	}
	
	protected void generateDungeonLoot() {
		if (isServerSide()) {
			if (UT.Code.stringValid(mDungeonLootNameFront)) try {
				DummyInventory tDummyInventory = new DummyInventory(14);
				WeightedRandomChestContent.generateChestContents(RNGSUS, ChestGenHooks.getItems(mDungeonLootNameFront, RNGSUS), tDummyInventory, ChestGenHooks.getCount(mDungeonLootNameFront, RNGSUS));
				for (ItemStack tStack : tDummyInventory.mInventory) {
					int tSlot = rng(14);
					if (!slotHas(tSlot)) {
						if (BooksGT.BOOK_REGISTER.containsKey(tStack, T)) {
							slot(tSlot, ST.amount(1, tStack));
						} else {
							slot(tSlot, rng(4)!=0?ST.make(Items.book, 1, 0):ST.make(MD.LOSTBOOKS, "randomBook", 1, 0, Items.book));
						}
					}
				}
				mDungeonLootNameFront = "";
				updateInventory();
			} catch(Throwable e) {e.printStackTrace(ERR);}
			if (UT.Code.stringValid(mDungeonLootNameBack)) try {
				DummyInventory tDummyInventory = new DummyInventory(14);
				WeightedRandomChestContent.generateChestContents(RNGSUS, ChestGenHooks.getItems(mDungeonLootNameBack, RNGSUS), tDummyInventory, ChestGenHooks.getCount(mDungeonLootNameBack, RNGSUS));
				for (ItemStack tStack : tDummyInventory.mInventory) {
					int tSlot = 14+rng(14);
					if (!slotHas(tSlot)) {
						if (BooksGT.BOOK_REGISTER.containsKey(tStack, T)) {
							slot(tSlot, ST.amount(1, tStack));
						} else {
							slot(tSlot, rng(4)!=0?ST.make(Items.book, 1, 0):ST.make(MD.LOSTBOOKS, "randomBook", 1, 0, Items.book));
						}
					}
				}
				mDungeonLootNameBack = "";
				updateInventory();
			} catch(Throwable e) {e.printStackTrace(ERR);}
		}
	}
	
	@Override
	public float getEnchantPowerBonus() {
		float tPoints = 0;
		for (int i = 0; i < 28; i++) if (slotHas(i)) {
			if (BooksGT.BOOKS_ENCHANTED.contains(slot(i), T)) {tPoints += 2; continue;}
			if (BooksGT.BOOKS_NORMAL   .contains(slot(i), T)) {tPoints += 1; continue;}
		}
		return (mMaterial.contains(TD.Properties.MAGICAL) ? 1 : 0) + tPoints/12.0F;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (aTimer % 300 == 0 && (UT.Code.stringValid(mDungeonLootNameFront) || UT.Code.stringValid(mDungeonLootNameBack)) && !worldObj.getEntitiesWithinAABB(EntityPlayer.class, box(-32, -32, -32, +33, +33, +33)).isEmpty()) generateDungeonLoot();
			
			if (mRedstoneDelay > 0) if (--mRedstoneDelay == 0) causeBlockUpdate();
			
			if (mInventoryChanged) {
				for (int i = 0; i < 28; i++) {
					Byte tID = (slotHas(i)?BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i))):null);
					if ((tID == null || tID == 0) && slotHas(i)) tID = BooksGT.BOOK_REGISTER.get(new ItemStackContainer(slot(i), W));
					if (tID == null) tID = (byte)0;
					if (tID != mDisplay[i]) {mDisplay[i] = tID; updateClientData();}
				}
			}
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if (aTool.equals(TOOL_magnifyingglass) && aPlayer instanceof EntityPlayerMP) {
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
			if (tCoords[0] >= PX_P[1] && tCoords[0] <= PX_N[1] && tCoords[1] >= PX_P[1] && tCoords[1] <= PX_N[1]) {
				int tIndex = -1;
				if (aSide == mFacing            ) tIndex = (tCoords[1] < PX_P[8]? 6:13)-(int)UT.Code.bind_(0, 6, (long)(8*(tCoords[0]-PX_P[1])));
				if (aSide == OPOS[mFacing] ) tIndex = (tCoords[1] < PX_P[8]?20:27)-(int)UT.Code.bind_(0, 6, (long)(8*(tCoords[0]-PX_P[1])));
				if (tIndex >= 0 && slotHas(tIndex)) {
					NW_API.sendToPlayer(new PacketItemStackChat(slot(tIndex)), (EntityPlayerMP)aPlayer);
					return 1;
				}
			}
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) generateDungeonLoot();
		float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
		if (tCoords[0] >= PX_P[1] && tCoords[0] <= PX_N[1] && tCoords[1] >= PX_P[1] && tCoords[1] <= PX_N[1]) {
			if (aSide == mFacing) {
				if (isServerSide()) switchBooks(aPlayer, (tCoords[1] < PX_P[8]? 6:13)-(int)UT.Code.bind_(0, 6, (long)(8*(tCoords[0]-PX_P[1]))));
				return T;
			}
			if (aSide == OPOS[mFacing]) {
				if (isServerSide()) switchBooks(aPlayer, (tCoords[1] < PX_P[8]?20:27)-(int)UT.Code.bind_(0, 6, (long)(8*(tCoords[0]-PX_P[1]))));
				return T;
			}
		}
		return F;
	}
	
	private boolean switchBooks(EntityPlayer aPlayer, int aSlot) {
		if (slotHas(aSlot)) {
			if (!aPlayer.isSneaking()) {
				if (OD.button.is(slot(aSlot))) {
					mRedstoneDelay = 120;
					causeBlockUpdate();
					playClick();
					return T;
				}
				if (OD.lever.is(slot(aSlot)) || ST.equal(slot(aSlot), Blocks.redstone_torch)) {
					if (mRedstoneDelay == 0) mRedstoneDelay = -1; else mRedstoneDelay = 0;
					causeBlockUpdate();
					playClick();
					return T;
				}
			}
			if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(aSlot), T)) {
				slotKill(aSlot);
				updateInventory();
				playCollect();
				return T;
			}
			return F;
		}
		ItemStack tStack = aPlayer.getCurrentEquippedItem();
		if (ST.valid(tStack) && BooksGT.BOOK_REGISTER.containsKey(tStack, T)) {
			slot(aSlot, ST.amount(1, tStack));
			tStack.stackSize--;
			updateInventory();
			playCollect();
			return T;
		}
		return F;
	}
	
	@Override
	public byte isProvidingWeakPower2(byte aSide) {
		return (byte)(mRedstoneDelay == 0 ? 0 : 15);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData(), mDisplay[0], mDisplay[1], mDisplay[2], mDisplay[3], mDisplay[4], mDisplay[5], mDisplay[6], mDisplay[7], mDisplay[8], mDisplay[9], mDisplay[10], mDisplay[11], mDisplay[12], mDisplay[13], mDisplay[14], mDisplay[15], mDisplay[16], mDisplay[17], mDisplay[18], mDisplay[19], mDisplay[20], mDisplay[21], mDisplay[22], mDisplay[23], mDisplay[24], mDisplay[25], mDisplay[26], mDisplay[27]);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setDirectionData(aData[3]);
		for (int i = 0; i < 28; i++) mDisplay[i] = aData[i+4];
		return T;
	}
	
	@Override
	public boolean isCoverSurface(byte aSide, int aRenderpass) {
		return aRenderpass == 0 && super.isCoverSurface(aSide);
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 35;
	}
	
	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		return aRenderPass<7||(mDisplay[aRenderPass-7]!=0&&aShouldSideBeRendered[aRenderPass<21?mFacing:OPOS[mFacing]]);
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return F;
		case  1: box(aBlock, PX_P[                         0], PX_P[                         0], PX_P[                         0], PX_N[                         0], PX_N[                        15], PX_N[                         0]); return T;
		case  2: box(aBlock, PX_P[                         0], PX_P[                        15], PX_P[                         0], PX_N[                         0], PX_N[                         0], PX_N[                         0]); return T;
		case  3: box(aBlock, PX_P[                         0], PX_P[                         1], PX_P[                         0], PX_N[SIDES_AXIS_Z[mFacing]?15:0], PX_N[                         1], PX_N[SIDES_AXIS_X[mFacing]?15:0]); return T;
		case  4: box(aBlock, PX_P[SIDES_AXIS_Z[mFacing]?15:0], PX_P[                         1], PX_P[SIDES_AXIS_X[mFacing]?15:0], PX_N[                         0], PX_N[                         1], PX_N[                         0]); return T;
		case  5: box(aBlock, PX_P[SIDES_AXIS_X[mFacing]? 7:1], PX_P[                         1], PX_P[SIDES_AXIS_Z[mFacing]? 7:1], PX_N[SIDES_AXIS_X[mFacing]? 7:1], PX_N[                         1], PX_N[SIDES_AXIS_Z[mFacing]? 7:1]); return T;
		case  6: box(aBlock, PX_P[                         1], PX_P[                         7], PX_P[                         1], PX_N[                         1], PX_N[                         7], PX_N[                         1]); return T;
		default:
			aRenderPass-=7;
			switch(mFacing) {
			case SIDE_X_POS:
				if (aRenderPass <  7) {                box(aBlock, PX_P[               9], PX_P[               9], PX_N[15-aRenderPass*2], PX_N[               2], PX_N[               1], PX_N[13-aRenderPass*2]); return T;}
				if (aRenderPass < 14) {aRenderPass%=7; box(aBlock, PX_P[               9], PX_P[               1], PX_N[15-aRenderPass*2], PX_N[               2], PX_N[               9], PX_N[13-aRenderPass*2]); return T;}
				if (aRenderPass < 21) {aRenderPass%=7; box(aBlock, PX_P[               2], PX_P[               9], PX_N[ 3+aRenderPass*2], PX_N[               9], PX_N[               1], PX_N[ 1+aRenderPass*2]); return T;}
									   aRenderPass%=7; box(aBlock, PX_P[               2], PX_P[               1], PX_N[ 3+aRenderPass*2], PX_N[               9], PX_N[               9], PX_N[ 1+aRenderPass*2]); return T;
			case SIDE_X_NEG:
				if (aRenderPass <  7) {                box(aBlock, PX_P[               2], PX_P[               9], PX_P[13-aRenderPass*2], PX_N[               9], PX_N[               1], PX_P[15-aRenderPass*2]); return T;}
				if (aRenderPass < 14) {aRenderPass%=7; box(aBlock, PX_P[               2], PX_P[               1], PX_P[13-aRenderPass*2], PX_N[               9], PX_N[               9], PX_P[15-aRenderPass*2]); return T;}
				if (aRenderPass < 21) {aRenderPass%=7; box(aBlock, PX_P[               9], PX_P[               9], PX_P[ 1+aRenderPass*2], PX_N[               2], PX_N[               1], PX_P[ 3+aRenderPass*2]); return T;}
									   aRenderPass%=7; box(aBlock, PX_P[               9], PX_P[               1], PX_P[ 1+aRenderPass*2], PX_N[               2], PX_N[               9], PX_P[ 3+aRenderPass*2]); return T;
			case SIDE_Z_POS:
				if (aRenderPass <  7) {                box(aBlock, PX_N[ 3+aRenderPass*2], PX_P[               9], PX_P[               9], PX_N[ 1+aRenderPass*2], PX_N[               1], PX_N[               2]); return T;}
				if (aRenderPass < 14) {aRenderPass%=7; box(aBlock, PX_N[ 3+aRenderPass*2], PX_P[               1], PX_P[               9], PX_N[ 1+aRenderPass*2], PX_N[               9], PX_N[               2]); return T;}
				if (aRenderPass < 21) {aRenderPass%=7; box(aBlock, PX_N[15-aRenderPass*2], PX_P[               9], PX_P[               2], PX_N[13-aRenderPass*2], PX_N[               1], PX_N[               9]); return T;}
									   aRenderPass%=7; box(aBlock, PX_N[15-aRenderPass*2], PX_P[               1], PX_P[               2], PX_N[13-aRenderPass*2], PX_N[               9], PX_N[               9]); return T;
			case SIDE_Z_NEG:
				if (aRenderPass <  7) {                box(aBlock, PX_P[ 1+aRenderPass*2], PX_P[               9], PX_P[               2], PX_P[ 3+aRenderPass*2], PX_N[               1], PX_N[               9]); return T;}
				if (aRenderPass < 14) {aRenderPass%=7; box(aBlock, PX_P[ 1+aRenderPass*2], PX_P[               1], PX_P[               2], PX_P[ 3+aRenderPass*2], PX_N[               9], PX_N[               9]); return T;}
				if (aRenderPass < 21) {aRenderPass%=7; box(aBlock, PX_P[13-aRenderPass*2], PX_P[               9], PX_P[               9], PX_P[15-aRenderPass*2], PX_N[               1], PX_N[               2]); return T;}
									   aRenderPass%=7; box(aBlock, PX_P[13-aRenderPass*2], PX_P[               1], PX_P[               9], PX_P[15-aRenderPass*2], PX_N[               9], PX_N[               2]); return T;
			}
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass <  7) {
			if (ALONG_AXIS[aSide][mFacing] && !aShouldSideBeRendered[aSide]) return null;
			switch(aRenderPass) {
			case 0: return ALONG_AXIS[aSide][mFacing] || !aShouldSideBeRendered[aSide] ? null : BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING));
			case 1: return ALONG_AXIS[aSide][mFacing] || SIDES_TOP[aSide]              ? BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING)) : null;
			case 2: return ALONG_AXIS[aSide][mFacing] || SIDES_BOTTOM[aSide]           ? BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING)) : null;
			case 3: return aSide==(SIDES_AXIS_Z[mFacing]?SIDE_X_NEG:SIDE_Z_NEG)        ? null : BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING));
			case 4: return aSide==(SIDES_AXIS_Z[mFacing]?SIDE_X_POS:SIDE_Z_POS)        ? null : BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING));
			case 5: return ALONG_AXIS[aSide][mFacing]                                  ? BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING)) : null;
			case 6: return ALONG_AXIS[aSide][mFacing] || SIDES_VERTICAL[aSide]         ? BlockTextureDefault.get(mShelfIcon, mRGBa, mMaterial.contains(TD.Properties.GLOWING)) : null;
			}
		}
		if (SIDES_VERTICAL[aSide] || aSide==(aRenderPass<21?OPOS[mFacing]:mFacing)) return null;
		
		// TODO: optimise more?
		
		return (ALONG_AXIS[aSide][mFacing]?BooksGT.BOOK_TEXTURES_BACK:BooksGT.BOOK_TEXTURES_SIDE)[UT.Code.unsignB(mDisplay[aRenderPass-7])];
	}
	
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool() {return box(PX_P[SIDES_AXIS_X[mFacing]?2:0], 0, PX_P[SIDES_AXIS_Z[mFacing]?2:0], PX_N[SIDES_AXIS_X[mFacing]?2:0], 1, PX_N[SIDES_AXIS_Z[mFacing]?2:0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[SIDES_AXIS_X[mFacing]?2:0], 0, PX_P[SIDES_AXIS_Z[mFacing]?2:0], PX_N[SIDES_AXIS_X[mFacing]?2:0], 1, PX_N[SIDES_AXIS_Z[mFacing]?2:0]);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isSideSolid2           (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean allowCovers            (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isShelfFace            (byte aSide) {return  ALONG_AXIS[aSide][mFacing];}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	// Inventory Stuff
	@Override public int getInventoryStackLimit() {return 1;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return 1;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[28];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public ItemStack getDefaultStack(int aSlot) {return ST.make(Items.book, 1, 0);}
	
	private static final int[] ACCESSIBLE_SLOTS = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return slotHas(aSlot)                                                 && !ST.equal(slot(aSlot), Blocks.cobblestone) && !ST.equal(slot(aSlot), Blocks.redstone_torch) && !OD.lever.is(slot(aSlot)) && !OD.button.is(slot(aSlot));}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return !slotHas(aSlot) && BooksGT.BOOK_REGISTER.containsKey(aStack, T) && !ST.equal(slot(aSlot), Blocks.cobblestone) && !ST.equal(slot(aSlot), Blocks.redstone_torch) && !OD.lever.is(slot(aSlot)) && !OD.button.is(slot(aSlot));}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.shelf.books";}
}
