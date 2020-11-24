/**
 * Copyright (c) 2020 GregTech-6 Team
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
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.fluid.FluidTankGT;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityConnectedTank;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMixingBowl extends TileEntityBase07Paintable implements IFluidHandler, ITileEntityConnectedTank, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_AddToolTips {
	protected short mDisplay = 0, oDisplay = -1;
	protected RecipeMap mRecipes = RM.Mixer;
	protected Recipe mLastRecipe = null;
	protected FluidTankGT[] mTanksInput = ZL_FT, mTanksOutput = ZL_FT;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_RECIPEMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));

		int tCapacity = 1000;
		if (aNBT.hasKey(NBT_TANK_CAPACITY)) tCapacity = UT.Code.bindInt(aNBT.getLong(NBT_TANK_CAPACITY));
		mTanksInput = new FluidTankGT[mRecipes.mInputFluidCount];
		for (int i = 0; i < mTanksInput.length; i++) mTanksInput[i] = new FluidTankGT(tCapacity).readFromNBT(aNBT, NBT_TANK+".in."+i);
		mTanksOutput = new FluidTankGT[mRecipes.mOutputFluidCount];
		for (int i = 0; i < mTanksOutput.length; i++) mTanksOutput[i] = new FluidTankGT(tCapacity).readFromNBT(aNBT, NBT_TANK+".out."+i);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mTanksInput .length; i++) mTanksInput [i].writeToNBT(aNBT, NBT_TANK+".in." +i);
		for (int i = 0; i < mTanksOutput.length; i++) mTanksOutput[i].writeToNBT(aNBT, NBT_TANK+".out."+i);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES) + ": " + Chat.WHITE + LH.get(mRecipes.mNameInternal));
		aList.add(Chat.CYAN     + LH.get(LH.RECIPES_MIXINGBOWL_USAGE));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT)   + " (" + LH.get(LH.FACE_TOP) + ")");
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if (SIDES_TOP[aSide] && aTool.equals(TOOL_mixer)) {
			ItemStack[] tInputItems  = ST.array(slot(0), slot(1), slot(2), slot(3), slot(4), slot(5));
			Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], NI, mTanksInput, tInputItems);
			if (tRecipe != null) {
				if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
				if (canOutput(tRecipe) && tRecipe.isRecipeInputEqual(T, F, mTanksInput, tInputItems)) {
					ItemStack [] tOutputItems  = tRecipe.getOutputs();
					FluidStack[] tOutputFluids = tRecipe.getFluidOutputs();
					for (int i = 0; i < mRecipes.mOutputItemsCount && i < tOutputItems.length; i++) addStackToSlot(i+6, tOutputItems[i]);
					for (int i = 0; i < mTanksOutput.length && i < tOutputFluids.length; i++) mTanksOutput[i].fill(tOutputFluids[i], T);
					removeAllDroppableNullStacks();
					return Math.max(1, UT.Code.divup(Math.max(1, tRecipe.mEUt) * Math.max(1, tRecipe.mDuration), 4));
				}
				return 0;
			}
		}
		if (aTool.equals(TOOL_plunger)) {
			updateInventory();
			for (FluidTankGT tTank : mTanksOutput) {long rAmount = GarbageGT.trash(tTank, 1000); if (rAmount > 0) return rAmount;}
			for (FluidTankGT tTank : mTanksInput ) {long rAmount = GarbageGT.trash(tTank, 1000); if (rAmount > 0) return rAmount;}
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				boolean temp = T;
				for (FluidTankGT tTank : mTanksInput) if (!tTank.isEmpty()) {
					temp = F;
					aChatReturn.add("Input: " + tTank.content());
				}
				for (FluidTankGT tTank : mTanksOutput) if (!tTank.isEmpty()) {
					temp = F;
					aChatReturn.add("Output: " + tTank.content());
				}
				if (temp) aChatReturn.add("Contains no Fluids");
			}
			return mTanksInput.length + mTanksOutput.length;
		}
		return 0;
	}

	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (SERVER_TIME % 600 == 10 && worldObj.isRaining() && getRainOffset(0, 1, 0)) {
				BiomeGenBase tBiome = getBiome();
				if (tBiome.rainfall > 0 && tBiome.temperature >= 0.2) {
					Block tInFront = getBlockAtSide(SIDE_TOP);
					if (!(tInFront instanceof BlockLiquid) && !(tInFront instanceof IFluidBlock) && !tInFront.isSideSolid(worldObj, xCoord, yCoord+1, zCoord, FORGE_DIR_OPPOSITES[SIDE_TOP]) && !tInFront.isSideSolid(worldObj, xCoord, yCoord+1, zCoord, FORGE_DIR[SIDE_TOP])) {
						FluidStack tWater = FL.Water.make((long)Math.max(1, tBiome.rainfall*200) * (worldObj.isThundering()?2:1));
						if (tWater != null) {
							IFluidTank tTank = getFluidTankFillable2(SIDE_TOP, tWater);
							if (tTank != null) tTank.fill(tWater, T);
						}
					}
				}
			}

			boolean tBreak = F;
			mDisplay = 0;
			for (FluidTankGT tTank : mTanksOutput) if (tTank.has()) {
				mDisplay = (short)(-2-tTank.getFluid().getFluidID());
				tBreak = T;
				break;
			}
			if (!tBreak) {
				for (FluidTankGT tTank : mTanksInput) if (tTank.has()) {
					mDisplay = (short)(-2-tTank.getFluid().getFluidID());
					tBreak = T;
					break;
				}
				if (!tBreak) {
					ItemStack tStack;
					for (int i = 0; i < 7; i++) if (ST.valid(tStack = slot(6-i))) {
						OreDictItemData tData = OM.data_(tStack);
						if (tData == null || tData.mMaterial == null) {
							mDisplay = -1;
						} else {
							mDisplay = tData.mMaterial.mMaterial.mID;
						}
						tBreak = T;
						break;
					}
				}
			}
		}
	}

	protected boolean canOutput(Recipe aRecipe) {
		if (slot(6) != null) {
			if (aRecipe.mNeedsEmptyOutput || (aRecipe.mOutputs.length > 0 && aRecipe.mOutputs[0] != null && (!ST.equal(slot(6), aRecipe.mOutputs[0], F) || slot(6).stackSize + aRecipe.mOutputs[0].stackSize > slot(6).getMaxStackSize()))) {
				return F;
			}
		}
		for (int i = 0; i < mTanksOutput.length && i < aRecipe.mFluidOutputs.length; i++) if (mTanksOutput[i].has()) {
			if (aRecipe.mNeedsEmptyOutput || (aRecipe.mFluidOutputs[i] != null && (!mTanksOutput[i].contains(aRecipe.mFluidOutputs[i]) || FL.temperature(aRecipe.mFluidOutputs[i]) >= mMaterial.mMeltingPoint - 100 || FL.lighter(aRecipe.mFluidOutputs[i]) || !FL.simple(aRecipe.mFluidOutputs[i]) || mTanksOutput[i].has(Math.max(1000, 1+aRecipe.mFluidOutputs[i].amount))))) {
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
				if (tCoords[0] <= PX_P[2] && tCoords[1] <= PX_P[2]) return T;
				if (!UT.Entities.isPlayer(aPlayer)) return T;
				if (ToolsGT.contains(TOOL_mixer, aStack)) return F;
				
				ItemStack[] tInputItems  = ST.array(slot(0), slot(1), slot(2), slot(3), slot(4), slot(5));
				Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, V[1], NI, mTanksInput, tInputItems);
				if (tRecipe != null) {
					if (tRecipe.mCanBeBuffered) mLastRecipe = tRecipe;
					if (canOutput(tRecipe) && tRecipe.isRecipeInputEqual(T, F, mTanksInput, tInputItems)) {
						ItemStack [] tOutputItems  = tRecipe.getOutputs();
						FluidStack[] tOutputFluids = tRecipe.getFluidOutputs();
						for (int i = 0; i < mRecipes.mOutputItemsCount && i < tOutputItems.length; i++) addStackToSlot(i+6, tOutputItems[i]);
						for (int i = 0; i < mTanksOutput.length && i < tOutputFluids.length; i++) mTanksOutput[i].fill(tOutputFluids[i], T);
						aPlayer.addExhaustion((tRecipe.mEUt * tRecipe.mDuration) / 250.0F);
						removeAllDroppableNullStacks();
						return T;
					}
				}
			}
			
			if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(6), T)) {
				slotKill(6);
				return T;
			}
			ItemStack tStack = ST.container(ST.amount(1, aStack), T);
			FluidStack tFluid = FL.getFluid(ST.amount(1, aStack), T);
			
			if (aStack != null && tFluid != null && FL.fillAll_(this, SIDE_ANY, tFluid, T)) {
				aStack.stackSize--;
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
				return T;
			}
			if (SIDES_TOP[aSide] && aHitX > PX_P[2] && aHitX < PX_N[2] && aHitZ > PX_P[2] && aHitZ < PX_N[2]) {
				if (aStack != null) for (byte i = 0; i < 6; i++) {
					if (ST.move(aPlayer.inventory, this, aPlayer.inventory.currentItem, i) > 0) return T;
				}
				if (aStack != null) for (FluidTankGT tTank : mTanksOutput) if ((tStack = FL.fill(tTank, ST.amount(1, aStack), T, T, T, T)) != null) {
					aStack.stackSize--;
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					return T;
				}
				if (aStack != null) for (FluidTankGT tTank : mTanksInput) if ((tStack = FL.fill(tTank, ST.amount(1, aStack), T, T, T, T)) != null) {
					aStack.stackSize--;
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					return T;
				}
			} else {
				if (aStack != null) for (FluidTankGT tTank : mTanksOutput) if ((tStack = FL.fill(tTank, ST.amount(1, aStack), T, T, T, T)) != null) {
					aStack.stackSize--;
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					return T;
				}
				if (aStack != null) for (FluidTankGT tTank : mTanksInput) if ((tStack = FL.fill(tTank, ST.amount(1, aStack), T, T, T, T)) != null) {
					aStack.stackSize--;
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
					return T;
				}
				if (aStack != null) for (byte i = 0; i < 6; i++) {
					if (ST.move(aPlayer.inventory, this, aPlayer.inventory.currentItem, i) > 0) return T;
				}
			}
			if (!slotHas(6)) for (int i = 0; i < 6; i++) if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(i), T)) {
				slotKill(i);
				return T;
			}
		} else {
			if (SIDES_TOP[aSide]) {
				float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
				if (tCoords[0] <= PX_P[2] && tCoords[1] <= PX_P[2]) {
					mRecipes.openNEI();
					return T;
				}
				if (mDisplay != 0) {
					if (mDisplay < -1) {
						UT.Sounds.play(SFX.MC_LIQUID_WATER, 5, 1.0F, 1.0F, getCoords());
					} else {
						UT.Sounds.play(SFX.MC_DIG_SAND, 5, 1.0F, 1.0F, getCoords());
					}
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
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].contains(aFluidToFill)) return mTanksInput[i];
		if (FL.temperature(aFluidToFill) >= mMaterial.mMeltingPoint - 100 || FL.lighter(aFluidToFill) || !FL.simple(aFluidToFill)) return null;
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].isEmpty()) return mTanksInput[i];
		return null;
	}

	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (aFluidToDrain == null) {
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].has()) return mTanksOutput[i];
		} else {
			for (int i = 0; i < mTanksOutput.length; i++) if (mTanksOutput[i].contains(aFluidToDrain)) return mTanksOutput[i];
		}
		return null;
	}

	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		IFluidTank[] rTanks = new IFluidTank[mTanksInput.length + mTanksOutput.length];
		for (int i = 0; i < mTanksInput .length; i++) rTanks[i] = mTanksInput[i];
		for (int i = 0; i < mTanksOutput.length; i++) rTanks[mTanksInput.length+i] = mTanksOutput[i];
		return rTanks;
	}

	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mTanksInput);
		GarbageGT.trash(mTanksOutput);
		return super.breakBlock();
	}

	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 7;
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 8], PX_N[ 0]);
		case  1: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[14]);
		case  2: return box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]);
		case  3: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 8], PX_N[ 0]);
		case  4: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);
		case  5: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 9], PX_N[ 0]);
		case  6: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 8]+0.001F, PX_N[14]);
		}
		return F;
	}

	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/bottom"),
	sTextureTableBottom = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/tablebottom"),
	sTextureTableSide   = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/colored/tableside"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/bottom"),
	sOverlayTableBottom = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/tablebottom"),
	sOverlayTableSide   = new Textures.BlockIcons.CustomIcon("machines/tools/mixing_bowl/overlay/tableside");

	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  2: return SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  1: return SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  3: return SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  4: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop    , mRGBa), BlockTextureDefault.get(sOverlayTop    )):SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom)):null;
		case  5:
			if (mDisplay == 0 || SIDE_TOP != aSide) return null;
			if (mDisplay < -1) {
				Fluid tFluid = FluidRegistry.getFluid(-mDisplay-2);
				return tFluid == null ? BlockTextureCopied.get(Blocks.water, SIDE_ANY, 0, UNCOLOURED, F, F, F) : BlockTextureFluid.get(FL.make(tFluid, 1000));
			}
			if (UT.Code.exists(mDisplay, OreDictMaterial.MATERIAL_ARRAY)) return BlockTextureDefault.get(OreDictMaterial.MATERIAL_ARRAY[mDisplay], OP.blockDust);
			return BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_128, F);
		case  6: return SIDE_TOP    == aSide?BI.nei():null;
		case  7: return SIDE_TOP    != aSide?SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTableBottom, mRGBa), BlockTextureDefault.get(sOverlayTableBottom)):BlockTextureMulti.get(BlockTextureDefault.get(sTextureTableSide, mRGBa), BlockTextureDefault.get(sOverlayTableSide)):null;
		}
		return null;
	}

	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		box(aAABB, aList, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]);
		box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 8], PX_N[ 0]);
		box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 8], PX_N[ 0]);
		box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[14]);
		box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[14], PX_N[ 0]);
	}

	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}

	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 8], PX_N[ 0]);}

	@Override public float getSurfaceSize           (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return SIDES_VERTICAL[aSide]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_N[ 8]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_BOTTOM[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}

	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[7];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}

	private static final int[] ACCESSIBLE_SLOTS = new int[] {0, 1, 2, 3, 4, 5, 6};

	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ACCESSIBLE_SLOTS;}

	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		if (aSlot >= 6) return F;
		for (int i = 0; i < 6; i++) if (ST.equal(aStack, slot(i), T)) return i == aSlot;
		return mRecipes.containsInput(aStack, this, NI);
	}

	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return aSlot >= 6;}

	@Override
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated) {
		return 0;
	}

	@Override
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if (aFluid == NF) return 0;
		for (FluidTankGT tTank : mTanksInput ) if (tTank.contains(aFluid)) if (tTank.has(aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return (int)tTank.remove(aFluid.amount);
		for (FluidTankGT tTank : mTanksOutput) if (tTank.contains(aFluid)) if (tTank.has(aOnlyRemoveIfItCanRemoveAllAtOnce ? aFluid.amount : 1)) return (int)tTank.remove(aFluid.amount);
		return 0;
	}

	@Override
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid) {
		if (aFluid == NF) return 0;
		long rAmount = 0;
		for (FluidTankGT tTank : mTanksInput ) if (tTank.contains(aFluid)) rAmount += tTank.amount();
		for (FluidTankGT tTank : mTanksOutput) if (tTank.contains(aFluid)) rAmount += tTank.amount();
		return rAmount;
	}

	@Override public String getTileEntityName() {return "gt.multitileentity.mixing.bowl";}
}
