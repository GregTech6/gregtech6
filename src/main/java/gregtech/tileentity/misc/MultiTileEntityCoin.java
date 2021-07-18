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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.config.ConfigCategories;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase04MultiTileEntities;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCoin extends TileEntityBase04MultiTileEntities implements IMTE_OnDespawn, IMTE_GetLifeSpan, IMTE_GetSubItems, IMTE_OnRegistration, IMTE_OnRegistrationClient, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_SetBlockBoundsBasedOnState, IMTE_AddCollisionBoxesToList, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_GetLightOpacity, IMTE_AddToolTips, IMTE_OnPlaced, IMTE_SyncDataByteArray {
	protected boolean mIsUnique = F;
	protected boolean[][][] mShape = new boolean[2][16][16];
	protected final byte[] mCoinStackSizes = new byte[16];
	protected OreDictMaterial mMaterial = MT.NULL;
	
	private static final byte COIN_STACKSIZE = 16;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		for (int i = 0; i < mShape[0].length; i++) mShape[0][i] = UT.Code.getBitsS(aNBT.getShort("gt.coin.shape.0."+i));
		for (int i = 0; i < mShape[1].length; i++) mShape[1][i] = UT.Code.getBitsS(aNBT.getShort("gt.coin.shape.1."+i));
		for (int i = 0; i < mCoinStackSizes.length; i++) mCoinStackSizes[i] = aNBT.getByte("gt.coin.stacksize."+i);
		mIsUnique = aNBT.getBoolean("gt.coin.unique");
		mMaterial = OreDictMaterial.get(aNBT.getString(NBT_MATERIAL));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, "gt.coin.unique", mIsUnique);
		aNBT.setString(NBT_MATERIAL, mMaterial.toString());
		for (int i = 0; i < mShape[0].length; i++) aNBT.setShort("gt.coin.shape.0."+i, (short)UT.Code.getBits(mShape[0][i]));
		for (int i = 0; i < mShape[1].length; i++) aNBT.setShort("gt.coin.shape.1."+i, (short)UT.Code.getBits(mShape[1][i]));
		for (int i = 0; i < mCoinStackSizes.length; i++) aNBT.setByte("gt.coin.stacksize."+i, mCoinStackSizes[i]);
	}
	
	@Override
	public NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		OreDictMaterialStack.saveList(NBT_RECYCLING_MATS, aNBT, Arrays.asList(OM.stack(mMaterial, U9)));
		UT.NBT.setBoolean(aNBT, "gt.coin.unique", mIsUnique);
		aNBT.setString(NBT_MATERIAL, mMaterial.toString());
		for (int i = 0; i < mShape[0].length; i++) aNBT.setShort("gt.coin.shape.0."+i, (short)UT.Code.getBits(mShape[0][i]));
		for (int i = 0; i < mShape[1].length; i++) aNBT.setShort("gt.coin.shape.1."+i, (short)UT.Code.getBits(mShape[1][i]));
		return aNBT;
	}
	
	public ItemStack getCoin(long aAmount, MultiTileEntityRegistry aRegistry, short aID) {
		return aRegistry == null ? null : aRegistry.getItem(aID, aAmount, writeItemNBT(UT.NBT.make()));
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<>();
		short tCoinAmount = 0;
		for (int i = 0; i < mCoinStackSizes.length; i++) tCoinAmount += mCoinStackSizes[i];
		while (tCoinAmount > 0) {
			byte tStackAmount = (byte)Math.min(64, tCoinAmount);
			rList.add(getCoin(tStackAmount, MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID()), getMultiTileEntityID()));
			tCoinAmount -= tStackAmount;
		}
		return rList;
	}
	
	public static Map<OreDictMaterial, ItemStack> COIN_MAP = new HashMap<>();
	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityCoin INSTANCE;
	
	public static ItemStack getCoin(int aAmount, OreDictMaterial aMaterial, boolean aIsUnique, boolean[][][] aShape) {
		INSTANCE.mShape = aShape;
		INSTANCE.mIsUnique = aIsUnique;
		INSTANCE.mMaterial = aMaterial;
		return INSTANCE.getCoin(aAmount, MTE_REGISTRY, INSTANCE.getMultiTileEntityID());
	}
	
	static {
		LH.add("gt.tooltip.coins.1", "Use the GregTech.cfg inside your Minecraft Root Directory");
		LH.add("gt.tooltip.coins.2", "to disable 3D Rendered Coins in World if they are too much");
		LH.add("gt.tooltip.coins.3", "Render Lag for your System.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + mMaterial.getLocal() + (mIsUnique?" (Unique)":""));
		aList.add(LH.Chat.DGRAY + LH.get("gt.tooltip.coins.1"));
		aList.add(LH.Chat.DGRAY + LH.get("gt.tooltip.coins.2"));
		aList.add(LH.Chat.DGRAY + LH.get("gt.tooltip.coins.3"));
	}
	
	@Override
	public void onTick(long aTimer, boolean aIsServerSide) {
		//
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide]) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem(), tStack = getCoin(1, MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID()), getMultiTileEntityID());
			int tIndex = (int)(Math.min(0.99F, Math.max(0, aHitX))*4)*4+(int)(Math.min(0.99F, Math.max(0, aHitZ))*4);
			if (tStack != null) {
				if (aStack == null) {
					if (mCoinStackSizes[tIndex] > 0 && (UT.Entities.hasInfiniteItems(aPlayer) || UT.Inventories.addStackToPlayerInventory(aPlayer, tStack, F))) {
						mCoinStackSizes[tIndex]--;
						updateClientData();
						playCollect();
					}
				} else {
					if (mCoinStackSizes[tIndex] < COIN_STACKSIZE && ST.equal(aStack, tStack)) {
						if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
						mCoinStackSizes[tIndex]++;
						updateClientData();
					}
				}
			}
			boolean temp = T;
			for (int i = 0; i < mCoinStackSizes.length; i++) if (mCoinStackSizes[i] > 0) {temp = F; break;}
			if (temp) worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
		return T;
	}
	
	@Override
	public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return F;
	}
	
	@Override
	public int getLifeSpan(World aWorld, ItemStack aStack) {
		return 200;
	}
	
	@Override
	public int onDespawn(EntityItem aEntity, ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null && !aEntity.worldObj.isRemote && aEntity.onGround) {
			if (aStack.stackSize > 0) for (byte tSide : ALL_SIDES_MIDDLE_DOWN) if (aStack.stackSize > 0) {
				TileEntity tTileEntity = WD.te(aEntity.worldObj, UT.Code.roundDown(aEntity.posX)+OFFX[tSide], UT.Code.roundDown(aEntity.posY)+OFFY[tSide], UT.Code.roundDown(aEntity.posZ)+OFFZ[tSide], T);
				if (tTileEntity instanceof MultiTileEntityCoin) {
					NBTTagCompound tNBT = ((MultiTileEntityCoin)tTileEntity).writeItemNBT(UT.NBT.make());
					if (tNBT.equals(aNBT)) {
						for (int i = 0; i < mCoinStackSizes.length; i++) {
							int tDifference = Math.min(aStack.stackSize, COIN_STACKSIZE - ((MultiTileEntityCoin)tTileEntity).mCoinStackSizes[i]);
							if (tDifference > 0) {
								aStack.stackSize -= tDifference;
								((MultiTileEntityCoin)tTileEntity).mCoinStackSizes[i] += tDifference;
							}
						}
						((MultiTileEntityCoin)tTileEntity).shuffle();
					}
				}
			}
			
			if (aStack.stackSize > 0) for (byte tSide : ALL_SIDES_MIDDLE_DOWN) if (aStack.stackSize > 0) {
				if (aEntity.worldObj.canPlaceEntityOnSide(MTE_REGISTRY.mBlock, UT.Code.roundDown(aEntity.posX)+OFFX[tSide], UT.Code.roundDown(aEntity.posY)+OFFY[tSide], UT.Code.roundDown(aEntity.posZ)+OFFZ[tSide], F, SIDE_TOP, aEntity, aStack)) {
					NBTTagCompound tNBT = (NBTTagCompound)aNBT.copy();
					int tUsedAmount = 0;
					for (int i = 0; i < mCoinStackSizes.length; i++) {
						int tDifference = Math.min(aStack.stackSize - tUsedAmount, COIN_STACKSIZE);
						if (tDifference > 0) {
							tUsedAmount += tDifference;
							tNBT.setByte("gt.coin.stacksize."+i, (byte)tDifference);
						}
					}
					if (tUsedAmount > 0 && MTE_REGISTRY.mBlock.placeBlock(aEntity.worldObj, UT.Code.roundDown(aEntity.posX)+OFFX[tSide], UT.Code.roundDown(aEntity.posY)+OFFY[tSide], UT.Code.roundDown(aEntity.posZ)+OFFZ[tSide], SIDE_UNKNOWN, INSTANCE.getMultiTileEntityID(), tNBT, T, F)) {
						aStack.stackSize-=tUsedAmount;
						TileEntity tTileEntity = WD.te(aEntity.worldObj, UT.Code.roundDown(aEntity.posX)+OFFX[tSide], UT.Code.roundDown(aEntity.posY)+OFFY[tSide], UT.Code.roundDown(aEntity.posZ)+OFFZ[tSide], T);
						if (tTileEntity instanceof MultiTileEntityCoin) ((MultiTileEntityCoin)tTileEntity).shuffle();
					}
				}
			}
		}
		return 200;
	}
	
	private void shuffle() {
		int tAmount = 0;
		for (int i = 0; i < mCoinStackSizes.length; i++) {
			tAmount += mCoinStackSizes[i];
			mCoinStackSizes[i] = 0;
		}
		while (tAmount > 0) {
			int tIndex = rng(mCoinStackSizes.length);
			if (mCoinStackSizes[tIndex] < COIN_STACKSIZE) {
				mCoinStackSizes[tIndex]++;
				tAmount--;
			}
		}
		updateClientData();
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		for (byte i = 0; i < mCoinStackSizes.length; i++) if (mCoinStackSizes[i] > 0) return T;
		mCoinStackSizes[(int)(Math.min(0.99F, Math.max(0, aHitX))*4)*4+(int)(Math.min(0.99F, Math.max(0, aHitZ))*4)] = 1;
		return T;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) {
			byte[] tPacket = new byte[82];
			for (int i = 0; i < 32; i++) tPacket[i   ] = UT.Code.toByteS((short)UT.Code.getBits(mShape[0][i/2]), i%2);
			for (int i = 0; i < 32; i++) tPacket[i+32] = UT.Code.toByteS((short)UT.Code.getBits(mShape[1][i/2]), i%2);
			for (int i = 0; i < 16; i++) tPacket[i+64] = mCoinStackSizes[i];
			tPacket[80] = UT.Code.toByteS(mMaterial.mID, 0);
			tPacket[81] = UT.Code.toByteS(mMaterial.mID, 1);
			return getClientDataPacketByteArray(aSendAll, tPacket);
		}
		return super.getClientDataPacket(aSendAll);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		for (int i = 0; i < 16; i++) mShape[0][i] = UT.Code.getBitsS(UT.Code.combine(aData[   i*2], aData[ 1+i*2]));
		for (int i = 0; i < 16; i++) mShape[1][i] = UT.Code.getBitsS(UT.Code.combine(aData[32+i*2], aData[33+i*2]));
		for (int i = 0; i < 16; i++) mCoinStackSizes[i] = aData[i+64];
		short tMaterial = UT.Code.combine(aData[80], aData[81]);
		if (UT.Code.exists(tMaterial, OreDictMaterial.MATERIAL_ARRAY)) mMaterial = OreDictMaterial.MATERIAL_ARRAY[tMaterial];
		return T;
	}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (OP.plateTiny.canGenerateItem(tMaterial) && !tMaterial.mHidden) {
			mMaterial = tMaterial;
			mIsUnique = T;
			mShape = new boolean[][][] {{
			{T,T,T,T,T,T,F,F,F,F,T,T,T,T,T,T},
			{T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T},
			{T,T,F,F,F,F,F,F,F,F,F,F,F,F,T,T},
			{T,T,F,F,F,F,F,T,T,F,F,F,F,F,T,T},
			{T,F,F,F,F,T,F,T,T,F,T,F,F,F,F,T},
			{T,F,F,F,T,T,T,T,T,T,T,T,F,F,F,T},
			{F,F,F,F,F,T,T,F,F,T,T,F,F,F,F,F},
			{F,F,F,T,T,T,F,F,F,F,T,T,T,F,F,F},
			{F,F,F,T,T,T,F,F,F,F,T,T,T,F,F,F},
			{F,F,F,F,F,T,T,F,F,T,T,F,F,F,F,F},
			{T,F,F,F,T,T,T,T,T,T,T,T,F,F,F,T},
			{T,F,F,F,F,T,F,T,T,F,T,F,F,F,F,T},
			{T,T,F,F,F,F,F,T,T,F,F,F,F,F,T,T},
			{T,T,F,F,F,F,F,F,F,F,F,F,F,F,T,T},
			{T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T},
			{T,T,T,T,T,T,F,F,F,F,T,T,T,T,T,T}
			}, {
			{T,T,T,T,T,T,F,F,F,F,T,T,T,T,T,T},
			{T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T},
			{T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T},
			{T,T,F,F,T,T,T,F,F,T,T,T,F,F,T,T},
			{T,F,F,T,T,F,T,F,F,T,F,T,T,F,F,T},
			{T,F,F,T,F,F,F,F,F,F,F,F,T,F,F,T},
			{F,F,T,T,T,F,F,T,T,F,F,T,T,T,F,F},
			{F,F,T,F,F,F,T,T,T,T,F,F,F,T,F,F},
			{F,F,T,F,F,F,T,T,T,T,F,F,F,T,F,F},
			{F,F,T,T,T,F,F,T,T,F,F,T,T,T,F,F},
			{T,F,F,T,F,F,F,F,F,F,F,F,T,F,F,T},
			{T,F,F,T,T,F,T,F,F,T,F,T,T,F,F,T},
			{T,T,F,F,T,T,T,F,F,T,T,T,F,F,T,T},
			{T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T},
			{T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T},
			{T,T,T,T,T,T,F,F,F,F,T,T,T,T,T,T}
			}};
			
			COIN_MAP.put(tMaterial, getCoin(1, aRegistry, aID));
		}
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {
		aList.addAll(COIN_MAP.values());
		return F;
	}
	
	private boolean mUseWorldRendering = F, mUseNormalRendering = T;
	private int mRenderPassIndex = 0;
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		int tPassAmount = 0;
		mRenderPassIndex = 0;
		mUseWorldRendering = F;
		mUseNormalRendering = ALLOW_3D_COINS_INV;
		for (byte i = 0; i < mCoinStackSizes.length; i++) {
			if (mCoinStackSizes[i] > 0) {
				if (!mUseWorldRendering) {
					mUseNormalRendering = ALLOW_3D_COINS;
					if (mUseNormalRendering) {
						mUseNormalRendering = F;
						for (boolean[][] tShape2 : mShape) {
							for (boolean[] tShape1 : tShape2) {
								for (boolean tValue : tShape1) {
									if (tValue) {mUseNormalRendering = T; break;}
								}
								if (mUseNormalRendering) break;
							}
							if (mUseNormalRendering) break;
						}
					}
				}
				mUseWorldRendering = T;
				tPassAmount+=(mUseNormalRendering?256:1);
			}
		}
		return mUseWorldRendering?tPassAmount:mUseNormalRendering?256:1;
	}
	
	private static final float DIST = 1 / 64.0F;
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (mUseWorldRendering) {
			if (mUseNormalRendering) {
				if (aRenderPass % 256 == 0) while (mCoinStackSizes[(aRenderPass+mRenderPassIndex)/256] <= 0) mRenderPassIndex += 256;
				aRenderPass += mRenderPassIndex;
				
				box(aBlock, 
				   (aRenderPass         /1024)/4.0F             +DIST *((aRenderPass%256)/16)
				,(mShape[0][(aRenderPass% 256)/16][aRenderPass%16]?DIST  :0)
				+(mShape[1][(aRenderPass% 256)/16][aRenderPass%16]?DIST*2:0)
				, ((aRenderPass%1024)   / 256)/4.0F             +DIST * (aRenderPass% 16)
				,  (aRenderPass         /1024)/4.0F     +DIST   +DIST *((aRenderPass%256)/16)
				, Math.max(0.0625F, mCoinStackSizes[aRenderPass/256]/(float)COIN_STACKSIZE)
				-(mShape[0][(aRenderPass% 256)/16][aRenderPass%16]?DIST  :0)
				-(mShape[1][(aRenderPass% 256)/16][aRenderPass%16]?DIST*2:0)
				, ((aRenderPass%1024)   / 256)/4.0F     +DIST   +DIST * (aRenderPass% 16)
				);
				return T;
			}
			
			while (mCoinStackSizes[aRenderPass+mRenderPassIndex] <= 0) mRenderPassIndex++;
			aRenderPass += mRenderPassIndex;
			
			box(aBlock, 
			  (aRenderPass/4)/4.0F
			, 0
			, (aRenderPass%4)/4.0F
			, (aRenderPass/4)/4.0F + 0.25F
			, mCoinStackSizes[aRenderPass]/(float)COIN_STACKSIZE
			, (aRenderPass%4)/4.0F + 0.25F
			);
			return T;
		}

		if (mUseNormalRendering) {
			box(aBlock, 
			  PX_P[aRenderPass/16]
			, PX_P[7]
			, PX_P[aRenderPass%16]
			, PX_P[1+(aRenderPass/16)]
			, PX_P[9]
			-(mShape[0][(aRenderPass%256)/16][aRenderPass%16]?DIST  :0)
			-(mShape[1][(aRenderPass%256)/16][aRenderPass%16]?DIST*2:0)
			, PX_P[1+(aRenderPass%16)]
			);
			return T;
		}
		box(aBlock, PX_P[0], PX_P[7], PX_P[0], PX_N[0], PX_P[9], PX_N[0]);
		return T;
	}
	
	private final ITexture[] mTextures = new ITexture[7];
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			if (!mUseNormalRendering && mUseWorldRendering) {
				mTextures[1] = BlockTextureDefault.get(Textures.BlockIcons.COIN_TOP     , mMaterial==null?UNCOLOURED:mMaterial.fRGBaSolid, F, F, T, !mUseNormalRendering);
				mTextures[0] = BlockTextureDefault.get(Textures.BlockIcons.COIN_BOTTOM  , mMaterial==null?UNCOLOURED:mMaterial.fRGBaSolid, F, F, T, !mUseNormalRendering);
			} else {
				mTextures[0] = mTextures[1] = BlockTextureDefault.get(Textures.BlockIcons.COIN, mMaterial==null?UNCOLOURED:mMaterial.fRGBaSolid, F, F, T, !mUseNormalRendering);
			}
			mTextures[2] = mTextures[3] = mTextures[4] = mTextures[5] = BlockTextureDefault.get(Textures.BlockIcons.COIN_SIDE, mMaterial==null?UNCOLOURED:mMaterial.fRGBaSolid, F, F, T, !mUseNormalRendering);
		}
		if (mUseNormalRendering) {
			int aX = (aRenderPass%256)/16, aZ = aRenderPass%16;
			int aHeight = 3-(mShape[0][aX][aZ]?1:0)-(mShape[1][aX][aZ]?2:0);
			if (aHeight <= 0) return null;
			switch(aSide) {
			case SIDE_X_NEG: if (aX >  0 && aHeight <= 3-(mShape[0][aX-1][aZ  ]?1:0)-(mShape[1][aX-1][aZ  ]?2:0)) return null; break;
			case SIDE_X_POS: if (aX < 15 && aHeight <= 3-(mShape[0][aX+1][aZ  ]?1:0)-(mShape[1][aX+1][aZ  ]?2:0)) return null; break;
			case SIDE_Z_NEG: if (aZ >  0 && aHeight <= 3-(mShape[0][aX  ][aZ-1]?1:0)-(mShape[1][aX  ][aZ-1]?2:0)) return null; break;
			case SIDE_Z_POS: if (aZ < 15 && aHeight <= 3-(mShape[0][aX  ][aZ+1]?1:0)-(mShape[1][aX  ][aZ+1]?2:0)) return null; break;
			}
		}
		return mTextures[aSide];
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 2], PX_P[ 2], PX_P[ 2], PX_N[ 2], PX_N[ 2], PX_N[ 2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);}
	
	@Override
	public void addCollisionBoxesToList(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		for (byte i = 0; i < 16; i++) if (mCoinStackSizes[i] > 0) box(aAABB, aList, PX_P[i/4]*4, 0, PX_P[i%4]*4, PX_P[i/4]*4+PX_P[4], mCoinStackSizes[i]/(float)COIN_STACKSIZE, PX_P[i%4]*4+PX_P[4]);
	}
	
	@Override public float getBlockHardness() {return 1;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	public static boolean ALLOW_3D_COINS = T, ALLOW_3D_COINS_INV = T;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRegistrationClient(MultiTileEntityRegistry aRegistry, short aID) {
		ALLOW_3D_COINS = ConfigsGT.CLIENT.get(ConfigCategories.general, "use3DCoins", ALLOW_3D_COINS);
		ALLOW_3D_COINS_INV = ConfigsGT.CLIENT.get(ConfigCategories.general, "use3DCoinsInv", ALLOW_3D_COINS_INV);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.coin";}
}
