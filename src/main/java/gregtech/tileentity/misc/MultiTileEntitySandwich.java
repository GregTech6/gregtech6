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

import java.util.List;

import enviromine.handlers.EM_StatusManager;
import enviromine.trackers.EnviroDataTracker;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityItemInternal;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.Sandwiches;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.food.FoodStatFluid;
import gregapi.item.multiitem.food.IFoodStat;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import squeek.applecore.api.food.FoodValues;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySandwich extends TileEntityBase03MultiTileEntities implements IMTE_SyncDataByteArray, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetMaxStackSize, IMTE_OnlyPlaceableWhenSneaking, IMTE_CanPlace, IMTE_OnItemRightClick, IMTE_AddToolTips, IMTE_GetFoodValues, IMTE_OnEaten, IMTE_GetItemUseAction, IMTE_GetMaxItemUseDuration, IItemRottable {
	public ItemStack[] mStacks = new ItemStack[16];
	public byte[] mDisplay = {(byte)254, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255, (byte)255};
	public byte mSize = 2;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		for (int i = 0; i < mStacks.length; i++) mStacks[i] = ST.load(aNBT, "sandwich."+i);
		
		// Default Sandwich
		if (!UT.Code.containsSomething(mStacks)) {
			mStacks[11] = IL.Food_Toast_Sliced.get(1);
			mStacks[10] = IL.Food_Pickle_Sliced.get(1);
			mStacks[ 9] = ST.make(ItemsGT.BOTTLES, 1, 1020); // TODO Mustard instead of Mayo
			mStacks[ 8] = IL.Food_Tomato_Sliced.get(1);
			mStacks[ 7] = ST.make(ItemsGT.BOTTLES, 1, 3101);
			mStacks[ 6] = IL.Food_Onion_Sliced.get(1);
			mStacks[ 5] = IL.Food_Cheese_Sliced.get(1);
			mStacks[ 4] = IL.Food_Cucumber_Sliced.get(1); // TODO Replace with Lettuce
			mStacks[ 2] = IL.Food_Meat.get(1);
			mStacks[ 0] = IL.Food_Toast_Sliced.get(1);
		}
		
		// Form the Sandwich Model
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
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (MD.APC.mLoaded) {
			for (ItemStack tStack : mStacks) if (ST.valid(tStack)) aList.add(1, LH.Chat.GRAY + tStack.getDisplayName());
		} else {
			aList.add(1, LH.Chat.RED + "Food: " + getTotalFood() + " - Saturation: " + getTotalSaturation());
			for (ItemStack tStack : mStacks) if (ST.valid(tStack)) aList.add(2, LH.Chat.GRAY + tStack.getDisplayName());
		}
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<>();
		int tCount = 0;
		for (int i = 0; i < mStacks.length; i++) if (ST.valid(mStacks[i])) tCount++;
		if (tCount == 1) if (ST.valid(mStacks[0]) && ST.container(mStacks[0], T) == null) rList.add(mStacks[0]);
		if (rList.isEmpty()) {
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID());
			if (tRegistry != null) rList.add(tRegistry.getItem(getMultiTileEntityID(), mStacks[0].stackSize, writeItemNBT(UT.NBT.make())));
		}
		return rList;
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		updateSandwich();
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		if (ST.valid(aStack) && ST.valid(mStacks[0]) && !ST.equal(getTopIngredient(), aStack)) {
			int tStackSize = mStacks[0].stackSize;
			if (aStack.stackSize >= tStackSize) {
				Byte tID = Sandwiches.INGREDIENTS.get(aStack);
				if (tID != null && mSize + Sandwiches.INGREDIENT_MODEL_THICKNESS[UT.Code.unsignB(tID)] <= 16) {
					mStacks[mSize] = ST.amount(tStackSize, aStack);
					ST.use(aPlayer, aStack, tStackSize);
					UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.mul(tStackSize, ST.container(mStacks[mSize], T)));
					updateSandwich();
					playCollect();
					return T;
				}
			}
		}
		return T;
	}
	
	public int getTotalFood() {
		int rFood = 0;
		for (ItemStack aStack : mStacks) if (ST.valid(aStack)) rFood += Math.max(1, ST.food(aStack));
		return rFood;
	}
	public float getTotalSaturation() {
		float rSaturation = 0;
		for (ItemStack aStack : mStacks) if (ST.valid(aStack)) rSaturation = Math.max(rSaturation, ST.saturation(aStack));
		return rSaturation + 0.5F;
	}
	public float getTotalHydration() {
		float rHydration = 0;
		for (ItemStack aStack : mStacks) if (ST.valid(aStack)) rHydration += ST.hydration(aStack);
		return rHydration;
	}
	
	public ItemStack getTopIngredient() {
		for (int i = mStacks.length-1; i >= 0; i--) if (ST.valid(mStacks[i])) return mStacks[i];
		return null;
	}
	
	public void updateSandwich() {
		if (worldObj == null || isServerSide()) {
			for (byte i = 0; i < mStacks.length; i++) {
				Byte tID = Sandwiches.INGREDIENTS.get(mStacks[i]);
				mDisplay[i] = (tID == null ? (byte)255 : tID);
			}
			updateSandwichSize();
			updateClientData();
		}
	}
	public void updateSandwichSize() {
		mSize = 0;
		for (byte i = 0; i < mDisplay.length; i++) if (mDisplay[i] != (byte)255) {
			mSize = (byte)UT.Code.bind(0, 16, i + Sandwiches.INGREDIENT_MODEL_THICKNESS[UT.Code.unsignB(mDisplay[i])]);
		}
	}
	
	@Override
	public boolean canPlace(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		return WD.block(aWorld, aX, aY-1, aZ).isSideSolid(aWorld, aX, aY-1, aZ, FORGE_DIR[SIDE_TOP]);
	}
	
	@Override
	public ItemStack onItemRightClick(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (UT.Entities.isCreative(aPlayer) || aPlayer.getFoodStats().needFood()) {
			aPlayer.setItemInUse(aStack, Math.max(FoodStatFluid.INSTANCE.getFoodLevel(aStack.getItem(), aStack, null) * 8, 32));
			return aStack;
		}
		return aStack;
	}
	
	@Override
	public int getMaxItemUseDuration(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		return Math.max(getTotalFood() * 8, 32);
	}
	
	@Override
	public EnumAction getItemUseAction(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		return EnumAction.eat;
	}
	
	@Override
	public ItemStack onEaten(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (MD.APC.mLoaded) {
			aPlayer.getFoodStats().func_151686_a((ItemFood)UT.Reflection.callConstructor("squeek.applecore.api.food.ItemFoodProxy", 0, null, T, aStack.getItem()), aStack);
		} else {
			aPlayer.getFoodStats().addStats(getTotalFood(), getTotalSaturation());
		}
		
		if (!aWorld.isRemote && MD.ENVM.mLoaded) {
			try {
				Object tTracker = EM_StatusManager.lookupTracker(aPlayer);
				if (tTracker != null && ((EnviroDataTracker)tTracker).bodyTemp >= 0) {
					float tHydration = getTotalHydration();
					if (tHydration > 0) ((EnviroDataTracker)tTracker).hydrate(tHydration); else if (tHydration < 0) ((EnviroDataTracker)tTracker).dehydrate(-tHydration);
				}
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
		}
		
		boolean temp = T;
		for (ItemStack tStack : mStacks) if (ST.valid(tStack) && ST.item_(tStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)ST.item_(tStack)).mFoodStats.get(ST.meta_(tStack));
			if (tStat != null) {
				tStat.onEaten(aItem, tStack, aPlayer, F, temp);
				temp = F;
			}
		}
		
		ST.use(aPlayer, aStack, 1);
		return aStack;
	}
	
	@Override
	public FoodValues getFoodValues(MultiTileEntityItemInternal aItem, ItemStack aStack) {
		return new squeek.applecore.api.food.FoodValues(getTotalFood(), getTotalSaturation());
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		return IL.ENVM_Rotten_Food.get(aStack.stackSize);
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		return IL.ENVM_Rotten_Food.get(aStack.stackSize);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketByteArray(aSendAll, mDisplay);
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		System.arraycopy(aData, 0, mDisplay, 0, 16);
		updateSandwichSize();
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
		return aRenderPass % 4 == 0 || UT.Code.unsignB(Sandwiches.INGREDIENT_MODEL_IDS[tID]) == 14;
	}
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 64;
	}
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		short tID = UT.Code.unsignB(mDisplay[aRenderPass/4]), tModel = UT.Code.unsignB(Sandwiches.INGREDIENT_MODEL_IDS[tID]), tThickness = Sandwiches.INGREDIENT_MODEL_THICKNESS[tID];
		if (tModel == 252 && aRenderPass >= 4) {
			aRenderPass /= 4;
			float tOffset = (16-aRenderPass) * 0.0005F;
			switch(UT.Code.unsignB(Sandwiches.INGREDIENT_MODEL_IDS[UT.Code.unsignB(mDisplay[aRenderPass-1])])) {
			case   2: case   3: case  14: case 252:
			return box(aBlock, PX_P[ 2]-tOffset,                   PX_P[1]/2, PX_P[ 2]-tOffset, PX_N[ 2]+tOffset, PX_P[aRenderPass+tThickness], PX_N[ 2]+tOffset);
			default :
			return box(aBlock, PX_P[ 1]-tOffset, PX_P[aRenderPass]-PX_P[1]/2, PX_P[ 1]-tOffset, PX_N[ 1]+tOffset, PX_P[aRenderPass+tThickness], PX_N[ 1]+tOffset);
			}
		}
		
		switch(tModel) {
		case   1: return box(aBlock, PX_P[ 1]  , PX_P[aRenderPass/4], PX_P[ 1]  , PX_N[ 1]          , PX_P[aRenderPass/4+tThickness], PX_N[ 1]          );
		case   2: return box(aBlock, PX_P[ 2]  , PX_P[aRenderPass/4], PX_P[ 2]  , PX_N[ 2]          , PX_P[aRenderPass/4+tThickness], PX_N[ 2]          );
		case   3: return box(aBlock, PX_P[ 3]  , PX_P[aRenderPass/4], PX_P[ 3]  , PX_N[ 3]          , PX_P[aRenderPass/4+tThickness], PX_N[ 3]          );
		case  14: switch (aRenderPass % 4) {
		default : return box(aBlock, PX_P[ 1]  , PX_P[aRenderPass/4], PX_P[ 1]  , PX_N[ 9]          , PX_P[aRenderPass/4+tThickness], PX_N[ 9]          );
		case   1: return box(aBlock, PX_P[ 1]  , PX_P[aRenderPass/4], PX_P[ 9]  , PX_N[ 9]          , PX_P[aRenderPass/4+tThickness], PX_N[ 1]          );
		case   2: return box(aBlock, PX_P[ 9]  , PX_P[aRenderPass/4], PX_P[ 1]  , PX_N[ 1]          , PX_P[aRenderPass/4+tThickness], PX_N[ 9]          );
		case   3: return box(aBlock, PX_P[ 9]  , PX_P[aRenderPass/4], PX_P[ 9]  , PX_N[ 1]          , PX_P[aRenderPass/4+tThickness], PX_N[ 1]          );
		}
		case 252: return box(aBlock, PX_P[ 1]  , PX_P[aRenderPass/4], PX_P[ 1]  , PX_N[ 1]          , PX_P[aRenderPass/4+tThickness], PX_N[ 1]          );
		case 253: return box(aBlock, PX_P[ 1]/2, PX_P[aRenderPass/4], PX_P[ 1]/2, PX_N[ 1]+PX_P[1]/2, PX_P[aRenderPass/4+tThickness], PX_N[ 1]+PX_P[1]/2);
		case 254: return box(aBlock, PX_P[ 1]/2, PX_P[aRenderPass/4], PX_P[ 1]/2, PX_N[ 1]+PX_P[1]/2, PX_P[aRenderPass/4+tThickness], PX_N[ 1]+PX_P[1]/2);
		default : return box(aBlock, PX_P[ 1]  , PX_P[aRenderPass/4], PX_P[ 1]  , PX_N[ 1]          , PX_P[aRenderPass/4+tThickness], PX_N[ 1]          );
		}
	}
	
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[1], 0, PX_P[1], PX_N[1], PX_P[Math.max(1, mSize)], PX_N[1]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[1], 0, PX_P[1], PX_N[1], PX_P[Math.max(1, mSize)], PX_N[1]);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[1], 0, PX_P[1], PX_N[1], PX_P[Math.max(1, mSize)], PX_N[1]);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	@Override public boolean isSideSolid            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean onlyPlaceableWhenSneaking()         {return T;}
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return 16;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public float getBlockHardness() {return 0.25F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.sandwich";}
}
