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

package gregtech.tileentity.misc;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetBlockHardness;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetExplosionResistance;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightOpacity;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IsSideSolid;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SyncDataByteArray;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.Sandwiches;
import gregapi.data.IL;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySandwich extends TileEntityBase03MultiTileEntities implements IMTE_SyncDataByteArray, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public ItemStack[] mStacks = new ItemStack[16];
	public byte[] mDisplay = {(byte)254, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255};
	public byte mSize = 2;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		for (int i = 0; i < mStacks.length; i++) mStacks[i] = ST.load(aNBT, "sandwich."+i);
		updateSandwich();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mStacks.length; i++) ST.save(aNBT, "sandwich."+i, mStacks[i]);
	}
	
	@Override
	public final NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		for (int i = 0; i < mStacks.length; i++) ST.save(aNBT, "sandwich."+i, ST.amount(1, mStacks[i]));
		return aNBT;
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<>();
		int tCount = 0;
		for (int i = 0; i < mStacks.length; i++) if (ST.valid(mStacks[i])) tCount++;
		if (tCount == 1) for (int i = 0; i < mStacks.length; i++) if (ST.valid(mStacks[i]) && ST.container(mStacks[i], T) == null) rList.add(mStacks[i]);
		if (rList.isEmpty()) {
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID());
			if (tRegistry != null) rList.add(tRegistry.getItem(getMultiTileEntityID(), mStacks[0].stackSize, writeItemNBT(UT.NBT.make())));
		}
		return rList;
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		if (ST.valid(aStack) && ST.valid(mStacks[0])) {
			int tStackSize = mStacks[0].stackSize;
			if (aStack.stackSize >= tStackSize) {
				Byte tID = Sandwiches.INGREDIENTS.get(aStack);
				if (tID != null && mSize + Sandwiches.INGREDIENT_MODEL_THICKNESS[UT.Code.unsignB(tID)] <= 16) {
					mStacks[mSize] = ST.amount(tStackSize, aStack);
					aStack.stackSize -= tStackSize;
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.mul(tStackSize, ST.container(mStacks[mSize], T)));
					updateSandwich();
					playCollect();
				}
			}
		}
		return T;
	}
	
	public void updateSandwich() {
		mSize = 0;
		for (int i = 0; i < mStacks.length; i++) {
			Byte tID = Sandwiches.INGREDIENTS.get(mStacks[i]);
			mDisplay[i] = (tID == null ? (byte)255 : tID);
			if (i == 0 && ST.invalid(mStacks[i])) {
				mStacks[i] = IL.Food_Toast_Sliced.get(1);
				mDisplay[i] = (byte)254;
				mSize = 2;
			}
			mSize += Sandwiches.INGREDIENT_MODEL_THICKNESS[UT.Code.unsignB(mDisplay[i])];
		}
		updateClientData();
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, mDisplay);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplay = aData;
		return T;
	}
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return (SIDES_VERTICAL[aSide] ? Sandwiches.INGREDIENT_TEXTURES_TOP : Sandwiches.INGREDIENT_TEXTURES_SIDES)[UT.Code.unsignB(mDisplay[aRenderPass/4])];
	}
	
	@Override
	public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {
		short tID = UT.Code.unsignB(mDisplay[aRenderPass/4]);
		if (tID == 255) return F;
		if (aRenderPass % 4 == 0) return T;
		if (tID < 150) return tID % 10 == 9;
		return F;
	}
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 64;
	}
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		short tID = UT.Code.unsignB(mDisplay[aRenderPass/4]);
		if (tID < 150) {
			switch (tID % 10) {
			case  2: return box(aBlock, PX_P[ 2], PX_P[aRenderPass/4], PX_P[ 2], PX_N[ 2], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 2]);
			case  3: return box(aBlock, PX_P[ 3], PX_P[aRenderPass/4], PX_P[ 3], PX_N[ 3], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 3]);
			case  9: switch (aRenderPass % 4) {
			default: return box(aBlock, PX_P[ 1], PX_P[aRenderPass/4], PX_P[ 1], PX_N[ 9], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 9]);
			case  1: return box(aBlock, PX_P[ 1], PX_P[aRenderPass/4], PX_P[ 9], PX_N[ 9], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 1]);
			case  2: return box(aBlock, PX_P[ 9], PX_P[aRenderPass/4], PX_P[ 1], PX_N[ 1], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 9]);
			case  3: return box(aBlock, PX_P[ 9], PX_P[aRenderPass/4], PX_P[ 9], PX_N[ 1], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 1]);
			}
			}
		}
		if (tID == 252 && aRenderPass >= 4) {
			tID = UT.Code.unsignB(mDisplay[aRenderPass/4-1]);
			if (tID < 150) {
				switch (tID % 10) {
				case  2: return box(aBlock, PX_P[ 2]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 2]-PX_OFFSET, PX_N[ 2]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 2]+PX_OFFSET);
				case  3: return box(aBlock, PX_P[ 3]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 3]-PX_OFFSET, PX_N[ 3]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 3]+PX_OFFSET);
				case  9: switch (aRenderPass % 4) {
				default: return box(aBlock, PX_P[ 1]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 1]-PX_OFFSET, PX_N[ 9]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 9]+PX_OFFSET);
				case  1: return box(aBlock, PX_P[ 1]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 9]-PX_OFFSET, PX_N[ 9]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 1]+PX_OFFSET);
				case  2: return box(aBlock, PX_P[ 9]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 1]-PX_OFFSET, PX_N[ 1]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 9]+PX_OFFSET);
				case  3: return box(aBlock, PX_P[ 9]-PX_OFFSET, PX_P[aRenderPass/4], PX_P[ 9]-PX_OFFSET, PX_N[ 1]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[ 1]+PX_OFFSET);
				}
				}
			}
			// Default Condiment
			return box(aBlock, PX_P[1]-PX_OFFSET, PX_P[aRenderPass/4]-PX_P[1]/2, PX_P[1]-PX_OFFSET, PX_N[1]+PX_OFFSET, PX_P[aRenderPass/4]+PX_P[1], PX_N[1]+PX_OFFSET);
		}
		// Default
		return box(aBlock, PX_P[1], PX_P[aRenderPass/4], PX_P[1], PX_N[1], PX_P[aRenderPass/4]+PX_P[Sandwiches.INGREDIENT_MODEL_THICKNESS[tID]], PX_N[1]);
	}
	
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[1], 0, PX_P[1], PX_N[1], PX_P[mSize], PX_N[1]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[1], 0, PX_P[1], PX_N[1], PX_P[mSize], PX_N[1]);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], 0, PX_P[1], PX_N[1], PX_P[mSize], PX_N[1]);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	@Override public boolean isSideSolid            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public float getBlockHardness() {return 0.25F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.sandwich";}
}
