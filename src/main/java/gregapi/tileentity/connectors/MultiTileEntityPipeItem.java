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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackSet;
import gregapi.code.TagData;
import gregapi.cover.CoverData;
import gregapi.cover.covers.CoverFilterItem;
import gregapi.data.ANY;
import gregapi.data.CS.IconsGT;
import gregapi.data.CS.ToolsGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.ITileEntityServerTickPre;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityHopper;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPipeItem extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityProgress, ITileEntityItemPipe, ITileEntityServerTickPre {
	public long mTransferredItems = 0, mStepSize = 1;
	public byte mLastReceivedFrom = SIDE_UNDEFINED, oLastReceivedFrom = SIDE_UNDEFINED, mRenderType = 0, mDisabledOutputs = 0, mDisabledInputs = 0;
	public boolean mBlocking = T;
	
	/**
	 * Utility to quickly add a whole set of Item Pipes.
	 * May use up to 25 IDs, even if it is just 6 right now!
	 */
	public static void addItemPipes(int aID, int aCreativeTabID, long aStepSize, int aInvSize, boolean aRecipe, boolean aBlocking, MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aBlock, Class<? extends TileEntity> aClass, OreDictMaterial aMat) {
		OreDictManager.INSTANCE.setTarget_(OP.pipeMedium               , aMat, aRegistry.add(aMat.getLocal() + " Item Pipe"                        , "Item Pipes", aID+ 2, aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[ 8], NBT_PIPESIZE, aStepSize      , NBT_INV_SIZE, aInvSize  , NBT_OPAQUE, T), aRecipe?new Object[]{"PPP", "wzh"       , 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeLarge                , aMat, aRegistry.add("Large " + aMat.getLocal() + " Item Pipe"             , "Item Pipes", aID+ 3, aCreativeTabID, aClass, aMat.mToolQuality, 32, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[12], NBT_PIPESIZE, aStepSize /   2, NBT_INV_SIZE, aInvSize*2, NBT_OPAQUE, T), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateCurved.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeHuge                 , aMat, aRegistry.add("Huge " + aMat.getLocal() + " Item Pipe"              , "Item Pipes", aID+ 4, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 0, NBT_DIAMETER, PX_P[16], NBT_PIPESIZE, aStepSize /   4, NBT_INV_SIZE, aInvSize*4, NBT_OPAQUE, T), aRecipe?new Object[]{"PPP", "wzh", "PPP", 'P', OP.plateDouble.dat(aMat)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeRestrictiveMedium    , aMat, aRegistry.add("Restrictive " + aMat.getLocal() + " Item Pipe"       , "Item Pipes", aID+ 5, aCreativeTabID, aClass, aMat.mToolQuality, 64, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 1, NBT_DIAMETER, PX_P[ 8], NBT_PIPESIZE, aStepSize * 100, NBT_INV_SIZE, aInvSize  , NBT_OPAQUE, T), aRecipe?new Object[]{" h ", "RPR", " R ", 'P', OP.pipeMedium.dat(aMat), 'R', OP.ring.dat(ANY.Steel)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeRestrictiveLarge     , aMat, aRegistry.add("Restrictive Large " + aMat.getLocal() + " Item Pipe" , "Item Pipes", aID+ 6, aCreativeTabID, aClass, aMat.mToolQuality, 32, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 1, NBT_DIAMETER, PX_P[12], NBT_PIPESIZE, aStepSize *  50, NBT_INV_SIZE, aInvSize*2, NBT_OPAQUE, T), aRecipe?new Object[]{"hR ", "RPR", " R ", 'P', OP.pipeLarge.dat(aMat), 'R', OP.ring.dat(ANY.Steel)}:ZL), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.pipeRestrictiveHuge      , aMat, aRegistry.add("Restrictive Huge " + aMat.getLocal() + " Item Pipe"  , "Item Pipes", aID+ 7, aCreativeTabID, aClass, aMat.mToolQuality, 16, aBlock, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 7.0F, NBT_RESISTANCE, 7.0F, NBT_COLOR, UT.Code.getRGBInt(aMat.fRGBaSolid), NBT_PIPERENDER, 1, NBT_DIAMETER, PX_P[16], NBT_PIPESIZE, aStepSize *  25, NBT_INV_SIZE, aInvSize*4, NBT_OPAQUE, T), aRecipe?new Object[]{" h ", "RPR", "RRR", 'P', OP.pipeHuge.dat(aMat), 'R', OP.ring.dat(ANY.Steel)}:ZL), T, F, T);
		// Continue with ID 10!
	}
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_OPAQUE)) mBlocking = aNBT.getBoolean(NBT_OPAQUE);
		if (aNBT.hasKey("gt.olast")) oLastReceivedFrom = aNBT.getByte("gt.olast");
		if (aNBT.hasKey("gt.mlast")) mLastReceivedFrom = aNBT.getByte("gt.mlast");
		if (aNBT.hasKey(NBT_INPUT)) mDisabledInputs = aNBT.getByte(NBT_INPUT);
		if (aNBT.hasKey(NBT_OUTPUT)) mDisabledOutputs = aNBT.getByte(NBT_OUTPUT);
		if (aNBT.hasKey("gt.mtransfer")) mTransferredItems = aNBT.getLong("gt.mtransfer");
		if (aNBT.hasKey(NBT_PIPESIZE)) mStepSize = aNBT.getLong(NBT_PIPESIZE);
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
		
		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_PRE.add(this);
			GT_API_Proxy.SERVER_TICK_PR2.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte("gt.olast", oLastReceivedFrom);
		aNBT.setByte("gt.mlast", mLastReceivedFrom);
		aNBT.setByte(NBT_INPUT, mDisabledInputs);
		aNBT.setByte(NBT_OUTPUT, mDisabledOutputs);
		UT.NBT.setNumber(aNBT, "gt.mtransfer", mTransferredItems);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.PIPE_STATS_STEPSIZE) + mStepSize);
		aList.add(Chat.CYAN + LH.get(LH.PIPE_STATS_BANDWIDTH) + getPipeCapacity());
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_INPUT_MONKEY_WRENCH));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_OUTPUT_MONKEY_WRENCH));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_monkeywrench)) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (FACE_CONNECTED[aTargetSide][mDisabledInputs]) {
				if (FACE_CONNECTED[aTargetSide][mDisabledOutputs]) {
					mDisabledInputs  ^= B[aTargetSide];
					mDisabledOutputs ^= B[aTargetSide];
				} else {
					mDisabledOutputs ^= B[aTargetSide];
				}
			} else {
				if (FACE_CONNECTED[aTargetSide][mDisabledOutputs]) {
					mDisabledInputs  ^= B[aTargetSide];
					mDisabledOutputs ^= B[aTargetSide];
				} else {
					mDisabledOutputs ^= B[aTargetSide];
				}
			}
			if (aChatReturn != null) {
				aChatReturn.add(FACE_CONNECTED[aTargetSide][mDisabledInputs ]?"Accepting from selected Side disabled":"Accepting from selected Side enabled");
				aChatReturn.add(FACE_CONNECTED[aTargetSide][mDisabledOutputs]?"Emitting to selected Side disabled"   :"Emitting to selected Side enabled");
			}
			return 2500;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (!isCovered(aTargetSide)) {
				if (aChatReturn != null) {
					aChatReturn.add(FACE_CONNECTED[aTargetSide][mDisabledInputs ]?"Accepting from selected Side disabled":"Accepting from selected Side enabled");
					aChatReturn.add(FACE_CONNECTED[aTargetSide][mDisabledOutputs]?"Emitting to selected Side disabled"   :"Emitting to selected Side enabled");
				}
				return 1;
			}
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPre() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_PRE.add(this);
			GT_API_Proxy.SERVER_TICK_PR2.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_PRE.remove(this);
		GT_API_Proxy.SERVER_TICK_PR2.remove(this);
		onUnregisterPre();
	}
	
	@Override
	public void onServerTickPre(boolean aFirst) {
		if (aFirst) {
			if (SERVER_TIME % 20 == 0) mTransferredItems = 0;
		} else if (SERVER_TIME % 10 == 0) {
			if (oLastReceivedFrom == mLastReceivedFrom) {
				boolean tUpdate = F;
				ArrayList<ITileEntityItemPipe> tPipeList = new ArrayListNoNulls<>();
				for (boolean temp = T; temp && UT.Code.containsSomething(getInventory()) && pipeCapacityCheck();) {
					temp = F;
					tPipeList.clear();
					for (ITileEntityItemPipe tTileEntity : UT.Code.sortByValuesAcending(ITileEntityItemPipe.Util.scanPipes(this, new HashMap<ITileEntityItemPipe, Long>(), 0, F, F)).keySet()) {
						if (temp) break;
						tPipeList.add(tTileEntity);
						while (!temp && UT.Code.containsSomething(getInventory()) && tTileEntity.sendItemStack(this)) {
							tUpdate = T;
							for (ITileEntityItemPipe tPipe : tPipeList) if (!tPipe.incrementTransferCounter(1)) temp = T;
						}
					}
				}
				if (tUpdate) {
					DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mLastReceivedFrom);
					if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
						((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
					}
				}
			}
			
			if (!UT.Code.containsSomething(getInventory())) mLastReceivedFrom = SIDE_UNDEFINED;
			oLastReceivedFrom = mLastReceivedFrom;
		}
	}
	
	@Override
	public boolean insertItemStackIntoTileEntity(Object aSender, byte aSide) {
		if (!FACE_CONNECTED[aSide][mDisabledOutputs] && canEmitItemsTo(aSide, aSender)) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide);
			if (ST.canConnect(tDelegator) && !(tDelegator.mTileEntity instanceof TileEntityBase09Connector)) {
				if ((!(tDelegator.mTileEntity instanceof TileEntityHopper) && !(tDelegator.mTileEntity instanceof TileEntityDispenser)) || getMetaDataAtSide(aSide) != tDelegator.mSideOfTileEntity) {
					// special cases for the win...
					CoverData tCovers = getCoverData();
					if (tCovers != null && tCovers.mBehaviours[aSide] instanceof CoverFilterItem && tCovers.mNBTs[aSide] != null) {
						ItemStack tStack = ST.load(tCovers.mNBTs[aSide], "gt.filter.item");
						if (ST.valid(tStack)) return ST.move(new DelegatorTileEntity<>((TileEntity)aSender, SIDE_ANY), tDelegator, new ItemStackSet<>(tStack), F, F, tCovers.mVisuals[aSide] != 0, T, 64, 1, 64, 1) > 0;
					}
					// well normal case is this.
					return ST.move(new DelegatorTileEntity<>((TileEntity)aSender, SIDE_ANY), tDelegator) > 0;
				}
			}
		}
		return F;
	}
	
	@Override public boolean incrementTransferCounter(long aIncrement) {mTransferredItems += aIncrement; return pipeCapacityCheck();}
	@Override public boolean sendItemStack(Object aSender) {if (pipeCapacityCheck()) for (byte i = 0, j = (byte)rng(6); i < 6; i++) if (insertItemStackIntoTileEntity(aSender, (byte)((i+j)%6))) return T; return F;}
	
	@Override public boolean pipeCapacityCheck() {return mTransferredItems <= 0 || getPipeContent() < getMaxPipeCapacity();}
	@Override public long getStepSize() {return mStepSize;}
	
	protected long getPipeContent() {return mTransferredItems;}
	protected long getMaxPipeCapacity() {return Math.max(1, getPipeCapacity());}
	protected long getPipeCapacity() {return getSizeInventory();}
	
	@Override
	public void onConnectionChange(byte aPreviousConnections) {
		for (byte tSide : ALL_SIDES_VALID) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) {
				((ITileEntityAdjacentInventoryUpdatable)tDelegator.mTileEntity).adjacentInventoryUpdated(tDelegator.mSideOfTileEntity, this);
			}
		}
	}
	
	protected int[] ACCESSIBLE_SLOTS;
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return mBlocking;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {if (!connected(aSide) || FACE_CONNECTED[aSide][mDisabledInputs]) return F; if (!UT.Code.containsSomething(getInventory())) mLastReceivedFrom = aSide; return mLastReceivedFrom == aSide && slot(aSlot) == null;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return SIDES_INVALID[aSide] || connected(aSide);}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {ItemStack[] rStack = super.getDefaultInventory(aNBT); ACCESSIBLE_SLOTS = new int[rStack.length]; for (int i = 0; i < ACCESSIBLE_SLOTS.length; i++) ACCESSIBLE_SLOTS[i] = i; return rStack;}
	
	@Override public boolean canEmitItemsTo                 (byte aSide, Object aSender) {return (aSender != this || aSide != mLastReceivedFrom) && connected(aSide);}
	@Override public boolean canAcceptItemsFrom             (byte aSide, Object aSender) {return connected(aSide);}
	
	@Override public boolean canConnect                     (byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {return aDelegator.mTileEntity instanceof ISidedInventory ? aDelegator.mTileEntity instanceof ITileEntityCanDelegate || ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity).length > 0 : ST.canConnect(aDelegator);}
	
	@Override public long getProgressValue                  (byte aSide) {return getPipeContent()*64;}
	@Override public long getProgressMax                    (byte aSide) {return getMaxPipeCapacity()*64;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {BlockTextureDefault tBase = BlockTextureDefault.get(mMaterial, getIconIndexSide      (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa); switch(mRenderType) {case 1: return BlockTextureMulti.get(tBase, BlockTextureDefault.get(Textures.BlockIcons.PIPE_RESTRICTOR)); default: return tBase;}}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {BlockTextureDefault tBase = BlockTextureDefault.get(mMaterial, getIconIndexConnected (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa); switch(mRenderType) {case 1: return BlockTextureMulti.get(tBase, BlockTextureDefault.get(Textures.BlockIcons.PIPE_RESTRICTOR)); default: return tBase;}}
	
	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return IconsGT.INDEX_BLOCK_PIPE_SIDE;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return aDiameter<0.37F?OP.pipeTiny.mIconIndexBlock:aDiameter<0.49F?OP.pipeSmall.mIconIndexBlock:aDiameter<0.74F?OP.pipeMedium.mIconIndexBlock:aDiameter<0.99F?OP.pipeLarge.mIconIndexBlock:OP.pipeHuge.mIconIndexBlock;}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.PNEUMATIC_ITEM.AS_LIST;}
	
	@Override public String getFacingTool() {return TOOL_wrench;}
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return super.isUsingWrenchingOverlay(aStack, aSide) || ToolsGT.contains(TOOL_monkeywrench, aStack);}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.connector.pipe.item";}
}
