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
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.BI;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.RM;
import gregapi.fluid.FluidTankGT;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityConnectedTank;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityJuicer extends TileEntityBase07Paintable implements ITileEntityQuickObstructionCheck, IFluidHandler, ITileEntityConnectedTank, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips {
	protected short mDisplay = 0, oDisplay = -1;
	protected RecipeMap mRecipes = RM.Juicer;
	protected Recipe mLastRecipe = null;
	protected FluidTankGT[] mTanks = ZL_FT;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
		
		mTanks = new FluidTankGT[mRecipes.mOutputFluidCount];
		for (int i = 0; i < mTanks.length; i++) mTanks[i] = new FluidTankGT(1000000).readFromNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_JUICER_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if (aTool.equals(TOOL_plunger)) {
			updateInventory();
			for (FluidTankGT tTank : mTanks) {long rAmount = GarbageGT.trash(tTank, 1000); if (rAmount > 0) return rAmount;}
		}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mDisplay = 0;
			for (FluidTankGT tTank : mTanks) if (tTank.has()) {
				mDisplay = (short)(tTank.getFluid().getFluidID()+1);
				break;
			}
		}
	}
	
	protected boolean canOutput(Recipe aRecipe) {
		for (int i = 0; i < mTanks.length && i < aRecipe.mFluidOutputs.length; i++) if (mTanks[i].has()) {
			if (aRecipe.mNeedsEmptyOutput || (aRecipe.mFluidOutputs[i] != null && (!mTanks[i].contains(aRecipe.mFluidOutputs[i]) || FL.temperature(aRecipe.mFluidOutputs[i]) >= mMaterial.mMeltingPoint - 100 || FL.lighter(aRecipe.mFluidOutputs[i]) || mTanks[i].has(Math.max(1000, 1+aRecipe.mFluidOutputs[i].amount))))) {
				return F;
			}
		}
		return T;
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.getCurrentEquippedItem();
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[4] && tCoords[1] <= PX_P[4]) return T;
			}
			
			if (!UT.Entities.isPlayer(aPlayer)) return T;
			
			Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], NI, ZL_FS, aStack);
			if (tRecipe != null) {
				if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
				if (canOutput(tRecipe) && tRecipe.isRecipeInputEqual(T, F, ZL_FS, aStack)) {
					for (ItemStack tStack : tRecipe.getOutputs()) UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					FluidStack[] tOutputFluids = tRecipe.getFluidOutputs();
					for (int i = 0; i < mTanks.length && i < tOutputFluids.length; i++) mTanks[i].fill(tOutputFluids[i], T);
					aPlayer.addExhaustion(tRecipe.getAbsoluteTotalPower() / 10000.0F);
					UT.Sounds.send(worldObj, SFX.MC_SLIME_BIG, 1.0F, 1.0F, getCoords());
					return T;
				}
			}
			
			ItemStack tStack = null;
			if (aStack != null) for (FluidTankGT tTank : mTanks) if ((tStack = FL.fill(tTank, ST.amount(1, aStack), T, T, T, T)) != null) {
				aStack.stackSize--;
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
				return T;
			}
		} else {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[4] && tCoords[1] <= PX_P[4]) {
					mRecipes.openNEI();
					return T;
				}
			}
		}
		return T;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mDisplay != oDisplay;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplay = mDisplay;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(mDisplay, 0), UT.Code.toByteS(mDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(mDisplay, 0), UT.Code.toByteS(mDisplay, 1));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		if (aData.length > 1) mDisplay = UT.Code.combine(aData[0], aData[1]);
		if (aData.length > 4) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
		return T;
	}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return null;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (aFluidToDrain == null) {
			for (int i = 0; i < mTanks.length; i++) if (mTanks[i].has()) return mTanks[i];
		} else {
			for (int i = 0; i < mTanks.length; i++) if (mTanks[i].contains(aFluidToDrain)) return mTanks[i];
		}
		return null;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return mTanks;
	}
	
	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mTanks);
		return super.breakBlock();
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 8;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[12], PX_N[ 2]); return T;
		case  1: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[12]); return T;
		case  2: box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]); return T;
		case  3: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[12], PX_N[ 2], PX_N[12], PX_N[ 2]); return T;
		case  4: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[15], PX_N[ 2]); return T;
		case  5: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[13], PX_N[ 2]); return T;
		case  6: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 9], PX_N[ 6]); return T;
		case  7: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[12]+0.001F, PX_N[12]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/bottom"),
	sTextureMiddleTop   = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/middletop"),
	sTextureMiddleSide  = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/colored/middleside"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/bottom"),
	sOverlayMiddleTop   = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/middletop"),
	sOverlayMiddleSide  = new Textures.BlockIcons.CustomIcon("machines/tools/juicer/overlay/middleside");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  2: return SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  1: return SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  3: return SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  4: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop    , mRGBa), BlockTextureDefault.get(sOverlayTop    )):SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom)):null;
		case  5:
			if (mDisplay <= 0 || SIDE_TOP != aSide) return null;
			Fluid tFluid = FL.fluid(mDisplay-1);
			return tFluid == null ? BlockTextureCopied.get(Blocks.water, SIDE_ANY, 0, UNCOLOURED, F, F, F) : BlockTextureFluid.get(FL.make(tFluid, 1000));
		case  6: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureMiddleTop, mRGBa), BlockTextureDefault.get(sOverlayMiddleTop)):SIDE_BOTTOM == aSide ? null : BlockTextureMulti.get(BlockTextureDefault.get(sTextureMiddleSide, mRGBa), BlockTextureDefault.get(sOverlayMiddleSide));
		case  7: return SIDE_TOP    == aSide?BI.nei():null;
		}
		return null;
	}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		box(aAABB, aList, PX_P[12], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[12], PX_N[ 2], PX_N[12], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[12], PX_N[ 2]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[12]);
		box(aAABB, aList, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[15], PX_N[ 2]);
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?0.5F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_P[12]:PX_P[2];}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	// Inventory Stuff
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated) {
		return 0;
	}
	
	@Override
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if (aFluid == NF) return 0;
		for (FluidTankGT tTank : mTanks) if (tTank.contains(aFluid)) if (tTank.has(aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return (int)tTank.remove(aFluid.amount);
		return 0;
	}
	
	@Override
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid) {
		if (aFluid == NF) return 0;
		long rAmount = 0;
		for (FluidTankGT tTank : mTanks) if (tTank.contains(aFluid)) rAmount += tTank.amount();
		return rAmount;
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.juicer";}
}
