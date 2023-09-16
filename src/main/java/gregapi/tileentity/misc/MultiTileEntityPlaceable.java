/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.tileentity.misc;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.MT;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityPlaceable extends TileEntityBase03MultiTileEntities implements IMTE_SyncDataByteArray, IMTE_CanEntityDestroy, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public ItemStack mStack;
	public ITexture mTextureSides, mTextureTop;
	public OreDictMaterial mMaterial = MT.Empty;
	public byte mSize = 1;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		mStack = ST.load(aNBT, NBT_VALUE);
		if (ST.valid(mStack)) {
			mSize = UT.Code.bindStack(ST.size(mStack));
			OreDictItemData tData = OM.anydata(mStack);
			if (tData != null && tData.nonemptyMaterial()) mMaterial = tData.mMaterial.mMaterial;
		}
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		ST.save(aNBT, NBT_VALUE, mStack);
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return ST.arraylist(mStack);
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		if (ST.invalid(mStack) || mStack.stackSize <= 0) return setToAir();
		if (ST.equal(aStack, mStack)) {
			if (mStack.stackSize >= 64) return T;
			if (mStack.stackSize + aStack.stackSize > 64) {
				aStack.stackSize -= (64-mStack.stackSize);
				mStack.stackSize = 64;
				mSize = ST.size(mStack);
				updateClientData();
				playCollect();
				return T;
			}
			mStack.stackSize += aStack.stackSize;
			mSize = ST.size(mStack);
			updateClientData();
			aStack.stackSize = 0;
			playCollect();
			return T;
		}
		if (UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.amount(1, mStack), T, worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5)) {
			MultiTileEntityPlaceable tSelected = this;
			for (int i = 1; i < 255; i++) {
				TileEntity tTileEntity = getTileEntityAtSideAndDistance(SIDE_UP, i);
				if (tTileEntity instanceof MultiTileEntityPlaceable && ST.equal(mStack, ((MultiTileEntityPlaceable)tTileEntity).mStack)) {
					tSelected = (MultiTileEntityPlaceable)tTileEntity;
				} else break;
			}
			tSelected.playCollect();
			if (--tSelected.mStack.stackSize <= 0) return tSelected.setToAir();
			tSelected.mSize = ST.size(tSelected.mStack);
			tSelected.updateClientData();
			return T;
		};
		return T;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(mMaterial.mID, 0), UT.Code.toByteS(mMaterial.mID, 1), mSize);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mMaterial = OreDictMaterial.MATERIAL_ARRAY[UT.Code.bind15(UT.Code.combine(aData[0], aData[1]))];
		mSize = aData[2];
		return T;
	}
	
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return SIDES_HORIZONTAL[aSide] ? mTextureSides : mTextureTop;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	@Override public boolean isSideSolid            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean canEntityDestroy(Entity aEntity) {return !(aEntity instanceof EntityDragon);}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_LEAVES;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public float getBlockHardness() {return 0.25F;}
}
