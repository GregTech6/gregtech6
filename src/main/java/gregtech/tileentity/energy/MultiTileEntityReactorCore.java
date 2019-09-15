/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.GT_API_Proxy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorCore extends TileEntityBase10FacingDouble implements ITileEntityServerTickPost, IFluidHandler, ITileEntityTapAccessible, ITileEntityFunnelAccessible, ITileEntityRunningActively, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock {
	protected boolean mRunning = F, oRunning = F;
	protected FluidTankGT[] mTanks = {new FluidTankGT(16000), new FluidTankGT(16000)};
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mRunning = aNBT.getBoolean(NBT_ACTIVE);
		mTanks[0].readFromNBT(aNBT, NBT_TANK+".0");
		mTanks[1].readFromNBT(aNBT, NBT_TANK+".1");
		
		if (worldObj != null && isServerSide() && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mRunning);
		mTanks[0].writeToNBT(aNBT, NBT_TANK+".0");
		mTanks[1].writeToNBT(aNBT, NBT_TANK+".1");
	}
	
	static {
		LH.add("gt.tooltip.reactorcore.1", "Primary Facing Emits Hot Coolant.");
		LH.add("gt.tooltip.reactorcore.2", "Secondary Facing Emits Cold Coolant when nearly full.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.reactorcore.2"));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TAKE_PINCERS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	private boolean mHasToAddTimer = T;
	
	@Override public void onUnregisterPost() {mHasToAddTimer = T;}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && mHasToAddTimer) {
			GT_API_Proxy.SERVER_TICK_POST.add(this);
			GT_API_Proxy.SERVER_TICK_PO2T.add(this);
			mHasToAddTimer = F;
		}
	}
	
	@Override
	public void onCoordinateChange() {
		super.onCoordinateChange();
		GT_API_Proxy.SERVER_TICK_POST.remove(this);
		GT_API_Proxy.SERVER_TICK_PO2T.remove(this);
		onUnregisterPost();
	}
	
	// 0 and 1 are at SIDE_X_NEG
	// 0 and 2 are at SIDE_Z_NEG
	// 2 and 3 are at SIDE_X_POS
	// 1 and 3 are at SIDE_Z_POS
	
	@Override
	public void onServerTickPost(boolean aFirst) {
		if (aFirst) {
			// TODO EMIT NEUTRONS
			if (mTanks[0].has()) FL.move(delegator(mSecondFacing), getAdjacentTank(mSecondFacing));
			if (mTanks[1].has()) FL.move(delegator(mFacing      ), getAdjacentTank(mFacing      ));
		} else {
			// TODO REACT TO NEUTRONS
		}
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_pincers) && SIDES_TOP[aSide]) {
			int tSlot = aHitX < 0.5 ? aHitZ < 0.5 ? 0 : 1 : aHitZ < 0.5 ? 2 : 3;
			if (slotHas(tSlot) && UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, slot(tSlot), T, worldObj, xCoord+0.5, yCoord+1.5, zCoord+0.5)) {
				slotKill(tSlot);
				updateClientData();
				return 10000;
			}
			return 0;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add("Input: "  + mTanks[0].content());
				aChatReturn.add("Output: " + mTanks[1].content());
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && SIDES_TOP[aSide]) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (ST.valid(aStack)) {// TODO ONLY ALLOWED STACKS!!!
				int tSlot = aHitX < 0.5 ? aHitZ < 0.5 ? 0 : 1 : aHitZ < 0.5 ? 2 : 3;
				if (!slotHas(tSlot) && ST.use(aPlayer, aStack)) {
					slot(tSlot, ST.amount(1, aStack));
					UT.Sounds.send(SFX.MC_CLICK, this);
					updateClientData();
				}
			}
		}
		return T;
	}
	
	protected short oDisplay = -1;
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return mRunning != oRunning || FL.id_(mTanks[0]) != oDisplay || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplay = FL.id_(mTanks[0]);
		oRunning = mRunning;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		short tDisplay = FL.id_(mTanks[0]);
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa)
		, UT.Code.toByteS(ST.id(slot(0)), 0), UT.Code.toByteS(ST.id(slot(0)), 1), UT.Code.toByteS(ST.meta(slot(0)), 0), UT.Code.toByteS(ST.meta(slot(0)), 1)
		, UT.Code.toByteS(ST.id(slot(1)), 0), UT.Code.toByteS(ST.id(slot(1)), 1), UT.Code.toByteS(ST.meta(slot(1)), 0), UT.Code.toByteS(ST.meta(slot(1)), 1)
		, UT.Code.toByteS(ST.id(slot(2)), 0), UT.Code.toByteS(ST.id(slot(2)), 1), UT.Code.toByteS(ST.meta(slot(2)), 0), UT.Code.toByteS(ST.meta(slot(2)), 1)
		, UT.Code.toByteS(ST.id(slot(3)), 0), UT.Code.toByteS(ST.id(slot(3)), 1), UT.Code.toByteS(ST.meta(slot(3)), 0), UT.Code.toByteS(ST.meta(slot(3)), 1)
		);
		return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		if (aData.length > 1) {
			mTanks[0].setFluid(FL.make(UT.Code.combine(aData[0], aData[1]), mTanks[0].getCapacity()));
			if (aData.length > 4) {
				mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
				if (aData.length > 20) {
					slot(0, ST.make(UT.Code.combine(aData[ 5], aData[ 6]), 1, UT.Code.combine(aData[ 7], aData[ 8])));
					slot(1, ST.make(UT.Code.combine(aData[ 9], aData[10]), 1, UT.Code.combine(aData[11], aData[12])));
					slot(2, ST.make(UT.Code.combine(aData[13], aData[14]), 1, UT.Code.combine(aData[15], aData[16])));
					slot(3, ST.make(UT.Code.combine(aData[17], aData[18]), 1, UT.Code.combine(aData[19], aData[20])));
				}
			}
		}
		return T;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mRunning = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)(mRunning?1:0);}
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return FL.Coolant_IC2.is(aFluidToFill) ? mTanks[0] : null; // TODO MULTIPLE COOLANT TYPES
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return mTanks[1];
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTanks;
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		if (!FL.Coolant_IC2.is(aFluid)) return 0; // TODO MULTIPLE COOLANT TYPES
		updateInventory();
		return mTanks[0].fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		updateInventory();
		return mTanks[mTanks[1].has() ? 1 : 0].drain(aMaxDrain, aDoDrain);
	}
	
	public ITexture mTextures[] = new ITexture[11];
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextures[ 0] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa), BlockTextureDefault.get(sOverlays[0]));
		mTextures[ 1] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa), BlockTextureDefault.get(sOverlays[1]));
		mTextures[ 2] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[2], mRGBa), BlockTextureDefault.get(sOverlays[2]));
		mTextures[ 3] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[3], mRGBa), BlockTextureDefault.get(sOverlays[3]));
		mTextures[ 4] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[4], mRGBa), BlockTextureDefault.get(sOverlays[4]));
		mTextures[ 5] = BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[5], mRGBa), BlockTextureDefault.get(sOverlays[5]));
		mTextures[ 6] = (slotHas(0) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[6], DYE_INT_Red   , T), BlockTextureDefault.get(sOverlays[6])) : null);
		mTextures[ 7] = (slotHas(1) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[6], DYE_INT_Yellow, T), BlockTextureDefault.get(sOverlays[6])) : null);
		mTextures[ 8] = (slotHas(2) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[6], DYE_INT_Green , T), BlockTextureDefault.get(sOverlays[6])) : null);
		mTextures[ 9] = (slotHas(3) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[6], DYE_INT_Blue  , T), BlockTextureDefault.get(sOverlays[6])) : null);
		mTextures[10] = (mTanks[0].has() ? BlockTextureFluid.get(mTanks[0]) : null);
		return 11;
	}
	
	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		return aRenderPass < 6 || (aRenderPass == 6 && slotHas(0)) || (aRenderPass == 7 && slotHas(1)) || (aRenderPass == 8 && slotHas(2)) || (aRenderPass == 9 && slotHas(3)) || (aRenderPass == 10 && mTanks[0].has());
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case SIDE_X_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]);
		case SIDE_Y_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);
		case SIDE_Z_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]);
		case SIDE_X_POS: return box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		case SIDE_Y_POS: return box(aBlock, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		case SIDE_Z_POS: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		
		case  6: return box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[ 2], PX_N[10], PX_N[ 2], PX_N[10]); // 0 and 1 are at SIDE_X_NEG
		case  7: return box(aBlock, PX_P[ 2], PX_P[ 2], PX_P[10], PX_N[10], PX_N[ 2], PX_N[ 2]); // 0 and 2 are at SIDE_Z_NEG
		case  8: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 2], PX_N[ 2], PX_N[ 2], PX_N[10]); // 2 and 3 are at SIDE_X_POS
		case  9: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[10], PX_N[ 2], PX_N[ 2], PX_N[ 2]); // 1 and 3 are at SIDE_Z_POS
		
		case 10: return box(aBlock, PX_P[ 2]+PX_OFFSET, PX_P[ 2], PX_P[ 2]+PX_OFFSET, PX_N[ 2]-PX_OFFSET, PX_N[ 2], PX_N[ 2]-PX_OFFSET);
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass >= 6 || aRenderPass < 2)      return mTextures[aRenderPass];
		if (!ALONG_AXIS[aRenderPass][aSide])          return null;
		if (aRenderPass == mFacing)                   return mTextures[4];
		if (aRenderPass == mSecondFacing)             return mTextures[5];
		if (aRenderPass == aSide && isCovered(aSide)) return mTextures[3];
		return mTextures[2];
	}
	
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mRunning) {UT.Entities.applyHeatDamage(aEntity, 5); UT.Entities.applyRadioactivity(aEntity, 3, 1);}}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], PX_P[1], PX_P[1], PX_N[1], PX_N[1], PX_N[1]);}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[4];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override public boolean getStateRunningPassively() {return mRunning;}
	@Override public boolean getStateRunningPossible() {return mRunning;}
	@Override public boolean getStateRunningActively() {return mRunning;}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/face2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/colored/rod")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/face2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core/overlay/rod")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";}
}
