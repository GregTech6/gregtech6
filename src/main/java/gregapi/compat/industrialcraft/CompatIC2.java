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

package gregapi.compat.industrialcraft;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.compat.CompatBase;
import gregapi.config.ConfigCategories;
import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.cover.covers.CoverTextureCanvas;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import ic2.api.crops.Crops;
import ic2.api.event.RetextureEvent;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;
import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import static gregapi.data.CS.*;

public class CompatIC2 extends CompatBase implements ICompatIC2 {
	public CompatIC2() {
		// Checking if everything is available.
		valuable(Blocks.glowstone, 0, 1);
		valuable(Blocks.soul_sand, 0, 1);
		if (ic2.api.recipe.Recipes.scrapboxDrops == null) {/**/}
		if (ic2.api.recipe.Recipes.recyclerBlacklist == null) {/**/}
		if (ic2.api.recipe.Recipes.recyclerWhitelist == null) {/**/}
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void onPostLoad(FMLPostInitializationEvent aEvent) {
		for (Object tOre : BlocksGT.stoneToSmallOres .values()) valuable((Block)tOre, 2);
		for (Object tOre : BlocksGT.stoneToNormalOres.values()) valuable((Block)tOre, 3);
		for (Object tOre : BlocksGT.stoneToBrokenOres.values()) valuable((Block)tOre, 3);
		
		if (mToBlacklist != null) {
			for (ItemStackContainer tStack : mToBlacklist) ic2.api.recipe.Recipes.recyclerBlacklist.add((IRecipeInput)makeInput(tStack.toStack()));
			mToBlacklist.clear();
			mToBlacklist = null;
		}
		
		if (MD.AA.mLoaded) Crops.instance.registerBaseSeed(ST.make(MD.AA, "itemCoffeeBeans", 1, W), Crops.instance.getCropCard("IC2", "Coffee"), 1, 1, 1, 1);
	}
	
	@Override
	public void onServerStarted(FMLServerStartedEvent aEvent) {
		if (MD.AA.mLoaded) Ic2Items.coffeeBeans = ST.make(MD.AA, "itemCoffeeBeans", 1, 0);
	}
	
	@SubscribeEvent
	public void onRetextureEvent(RetextureEvent aEvent) {
		TileEntity tTileEntity = WD.te(aEvent.world, aEvent.x, aEvent.y, aEvent.z, T);
		if (tTileEntity instanceof ITileEntityCoverable) {
			CoverData tData = ((ITileEntityCoverable)tTileEntity).getCoverData();
			if (tData != null && tData.mBehaviours[aEvent.side] instanceof CoverTextureCanvas) {
				if (tData.mNBTs[aEvent.side] == null) tData.mNBTs[aEvent.side] = UT.NBT.make();
				tData.mNBTs[aEvent.side].setInteger(NBT_CANVAS_BLOCK, Block.getIdFromBlock(aEvent.referencedBlock));
				tData.mNBTs[aEvent.side].setInteger(NBT_CANVAS_META , aEvent.referencedMeta);
				tData.mBehaviours[aEvent.side].onCoverPlaced((byte)aEvent.side, tData, null, tData.getCoverItem((byte)aEvent.side));
				aEvent.applied = T;
			}
		}
	}
	
	@Override
	public boolean valuable(Block aBlock, int aMeta, int aValue) {
		if (aBlock == null || aBlock == NB) return F;
		ic2.core.IC2.addValuableOre((IRecipeInput)makeInput(ST.make(aBlock, 1, UT.Code.bind4(aMeta))), Math.max(1, aValue));
		return T;
	}
	@Override
	public boolean valuable(Block aBlock, int aValue) {
		if (aBlock == null || aBlock == NB) return F;
		for (byte i = 0; i < 16; i++) valuable(aBlock, i, aValue);
		return T;
	}
	
	@Override
	public ItemStack recycler(ItemStack aInput, int aScrapChance) {
		if (aInput == null || aScrapChance != 0 || ic2.api.recipe.Recipes.recyclerBlacklist.contains(aInput)) return null;
		return ic2.api.recipe.Recipes.recyclerWhitelist.isEmpty() || ic2.api.recipe.Recipes.recyclerWhitelist.contains(aInput)?IL.IC2_Scrap.get(1):null;
	}
	
	@Override
	public ItemStack scrapbox(ItemStack aBox) {
		return ic2.api.recipe.Recipes.scrapboxDrops.getDrop(aBox, F);
	}
	
	@Override
	public boolean scrapbox(float aChance, ItemStack aOutput) {
		if (ST.invalid(aOutput) || aChance <= 0) return F;
		aOutput = OM.get_(aOutput);
		aOutput.stackSize = 1;
		//if (Config.troll && !ST.equal(aOutput, ST.make(Items.wooden_hoe, 1, 0))) return F;
		aChance = (float)ConfigsGT.RECIPES.get(ConfigCategories.Machines.scrapboxdrops, aOutput, aChance);
		if (aChance <= 0) return F;
		ic2.api.recipe.Recipes.scrapboxDrops.addDrop(ST.copy(aOutput), aChance);
		return T;
	}
	
	private ItemStackSet<ItemStackContainer> mToBlacklist = ST.hashset();
	
	@Override
	public boolean blacklist(ItemStack aBlacklisted) {
		if (ST.invalid(aBlacklisted)) return F;
		if (ic2.api.recipe.Recipes.recyclerBlacklist == null) mToBlacklist.add(aBlacklisted); else ic2.api.recipe.Recipes.recyclerBlacklist.add((IRecipeInput)makeInput(aBlacklisted));
		return T;
	}
	
	@Override
	public boolean isExplosionWhitelisted(Block aBlock) {
		return aBlock != null && aBlock != NB && ic2.api.tile.ExplosionWhitelist.isBlockWhitelisted(aBlock);
	}
	
	@Override
	public void addToExplosionWhitelist(Block aBlock) {
		if (aBlock != null && aBlock != NB) ic2.api.tile.ExplosionWhitelist.addWhitelistedBlock(aBlock);
	}
	
	@Override public Object makeInput(ItemStack aStack) {return new RecipeInputItemStack(ST.copy(aStack), aStack.stackSize);}
	@Override public Object makeInput(String aOreDict, long aAmount) {return new RecipeInputOreDict(aOreDict, UT.Code.bindStack(aAmount));}
	@Override public Object makeOutput(NBTTagCompound aNBT, ItemStack... aStacks) {return new RecipeOutput(aNBT, aStacks);}
	@Override public boolean isReactorItem(ItemStack aStack) {try {return ST.valid(aStack) && aStack.getItem() instanceof ic2.api.reactor.IReactorComponent;} catch (Throwable e) {/*Do nothing*/} return F;}
}

