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

package gregapi.block.metatype;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.render.IIconContainer;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStones extends BlockMetaType implements IOreDictListenerEvent, IBlockToolable, Runnable {
	public static final boolean[]
	  MOSSY     = {F,F,T,F,F,T,F,F,F,F,F,F,F,F,F,F}
	, SEALABLE  = {F,F,F,T,F,T,T,T,T,T,T,T,T,T,T,T}
	, SPAWNABLE = {T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F}
	, PLANTABLE = {T,T,T,F,T,T,F,F,F,F,F,F,F,F,F,F}
	;
	
	public static final byte
	STONE =  0, SMOTH =  7,
	COBBL =  1, MCOBL =  2,
	BRICK =  3, CRACK =  4, MBRIK =  5, SBRIK = 12, QBRIK = 15,
	CHISL =  6, WINDA = 13, WINDB = 14,
	RNFBR =  8, RSTBR =  9,
	TILES = 10, STILE = 11;
	
	public static final byte[]
	  CHISEL_MAPPINGS = {SMOTH, COBBL, MCOBL, CRACK, COBBL, MCOBL, CHISL, CHISL, RNFBR, RSTBR, STILE, STILE, STILE, WINDB, WINDA, STILE}
	;
	
	public final OreDictMaterial mMaterial;
	@SuppressWarnings("rawtypes")
	public final ItemStackSet[] mEqualBlocks = {new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(), new ItemStackSet(),};
	
	public BlockStones(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, IIconContainer[] aIcons) {
		super(aItemClass, aVanillaMaterial == null ? Material.rock : aVanillaMaterial, aVanillaSoundType == null ? soundTypeStone : aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, 16, aIcons == null ? new IIconContainer[] {
		  new Textures.BlockIcons.CustomIcon("stones/"+aName+"/STONE")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/COBBLE")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/COBBLE_MOSSY")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS_CRACKED")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS_MOSSY")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS_CHISELED")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/SMOOTH")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS_REINFORCED")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/BRICKS_REDSTONE")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/TILES")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/SMALL_TILES")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/SMALL_BRICKS")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/WINDMILL_TILES_A")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/WINDMILL_TILES_B")
		, new Textures.BlockIcons.CustomIcon("stones/"+aName+"/SQUARE_BRICKS")
		} : aIcons);
		
		mMaterial = (aMaterial == null ? ANY.Stone : aMaterial);
		
		OP.crafting.addListener(this);
		GAPI_POST.mAfterInit.add(this);
		
		if (aDefaultLocalised != null) {
			LH.add(getUnlocalizedName()+".0.name", aDefaultLocalised);
			LH.add(getUnlocalizedName()+".1.name", aDefaultLocalised+" Cobblestone");
			LH.add(getUnlocalizedName()+".2.name", "Mossy "+aDefaultLocalised+" Cobblestone");
			LH.add(getUnlocalizedName()+".3.name", aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".4.name", "Cracked "+aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".5.name", "Mossy "+aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".6.name", "Chiseled "+aDefaultLocalised);
			LH.add(getUnlocalizedName()+".7.name", "Smooth "+aDefaultLocalised);
			LH.add(getUnlocalizedName()+".8.name", "Reinforced "+aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".9.name", "Redstoned "+aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".10.name", aDefaultLocalised+" Tiles");
			LH.add(getUnlocalizedName()+".11.name", "Small "+aDefaultLocalised+" Tiles");
			LH.add(getUnlocalizedName()+".12.name", "Small "+aDefaultLocalised+" Bricks");
			LH.add(getUnlocalizedName()+".13.name", aDefaultLocalised+" Windmill Tiles A");
			LH.add(getUnlocalizedName()+".14.name", aDefaultLocalised+" Windmill Tiles B");
			LH.add(getUnlocalizedName()+".15.name", aDefaultLocalised+" Square Bricks");
		}
		
		OM.data(ST.make(this, 1, RNFBR), new OreDictItemData(mMaterial, U, ANY.Iron, OP.stick.mAmount));
		OM.data(ST.make(this, 1, RSTBR), new OreDictItemData(mMaterial, U, MT.Redstone, OP.dust.mAmount));
		
		if (mMaterial != ANY.Stone) {
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, STONE));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, COBBL));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, MCOBL));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, BRICK));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, CRACK));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, MBRIK));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, CHISL));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, SMOTH));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, TILES));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, STILE));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, SBRIK));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, WINDA));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, WINDB));
			OM.reg_(OP.stone, mMaterial, ST.make(this, 1, QBRIK));
			
			OM.reg_(OP.stoneSmooth  , mMaterial, ST.make(this, 1, STONE));
			OM.reg_(OP.stoneCobble  , mMaterial, ST.make(this, 1, COBBL));
			OM.reg_(OP.stoneMossy   , mMaterial, ST.make(this, 1, MCOBL));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, BRICK));
			OM.reg_(OP.stoneCracked , mMaterial, ST.make(this, 1, CRACK));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, MBRIK));
			OM.reg_(OP.stoneChiseled, mMaterial, ST.make(this, 1, CHISL));
			OM.reg_(OP.stonePolished, mMaterial, ST.make(this, 1, SMOTH));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, TILES));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, STILE));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, SBRIK));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, WINDA));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, WINDB));
			OM.reg_(OP.stoneBricks  , mMaterial, ST.make(this, 1, QBRIK));
		}
		
		OM.reg_(OP.stoneSmooth     , ST.make(this, 1, STONE));
		OM.reg_(OP.stoneCobble     , ST.make(this, 1, COBBL));
		OM.reg_(OP.stoneCobble     , ST.make(this, 1, MCOBL));
		OM.reg_(OP.stoneMossy      , ST.make(this, 1, MCOBL));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, BRICK));
		OM.reg_(OP.stoneCracked    , ST.make(this, 1, CRACK));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, CRACK));
		OM.reg_(OP.stoneMossyBricks, ST.make(this, 1, MBRIK));
		OM.reg_(OP.stoneMossy      , ST.make(this, 1, MBRIK));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, MBRIK));
		OM.reg_(OP.stoneChiseled   , ST.make(this, 1, CHISL));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, CHISL));
		OM.reg_(OP.stonePolished   , ST.make(this, 1, SMOTH));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, TILES));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, STILE));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, SBRIK));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, WINDA));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, WINDB));
		OM.reg_(OP.stoneBricks     , ST.make(this, 1, QBRIK));
		
		OM.reg_(OP.cobblestone     , ST.make(this, 1, COBBL));
		OM.reg_(OP.stone           , ST.make(this, 1, STONE));
		
		for (int i = 0; i < maxMeta(); i++) mEqualBlocks[i].add(ST.make(this, 1, i));
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockStones(aItemClass, aVanillaMaterial == null ? Material.rock : aVanillaMaterial, aVanillaSoundType == null ? soundTypeStone : aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockStones(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial == null ? Material.rock : aVanillaMaterial, aVanillaSoundType == null ? soundTypeStone : aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		mMaterial = (aMaterial == null ? ANY.Stone : aMaterial);
		
		if (aDefaultLocalised != null) {
			LH.add(getUnlocalizedName()+".0.name", aDefaultLocalised+" Slab");
			LH.add(getUnlocalizedName()+".1.name", aDefaultLocalised+" Cobblestone Slab");
			LH.add(getUnlocalizedName()+".2.name", "Mossy "+aDefaultLocalised+" Cobblestone Slab");
			LH.add(getUnlocalizedName()+".3.name", aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".4.name", "Cracked "+aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".5.name", "Mossy "+aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".6.name", "Chiseled "+aDefaultLocalised+" Slab");
			LH.add(getUnlocalizedName()+".7.name", "Smooth "+aDefaultLocalised+" Slab");
			LH.add(getUnlocalizedName()+".8.name", "Reinforced "+aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".9.name", "Redstoned "+aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".10.name", aDefaultLocalised+" Tiles Slab");
			LH.add(getUnlocalizedName()+".11.name", "Small "+aDefaultLocalised+" Tiles Slab");
			LH.add(getUnlocalizedName()+".12.name", "Small "+aDefaultLocalised+" Bricks Slab");
			LH.add(getUnlocalizedName()+".13.name", aDefaultLocalised+" Windmill Tiles A Slab");
			LH.add(getUnlocalizedName()+".14.name", aDefaultLocalised+" Windmill Tiles B Slab");
			LH.add(getUnlocalizedName()+".15.name", aDefaultLocalised+" Square Bricks Slab");
		}
		OM.data(ST.make(this, 1, STONE), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, COBBL), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, MCOBL), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, BRICK), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, CRACK), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, MBRIK), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, CHISL), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, SMOTH), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, RNFBR), new OreDictItemData(mMaterial, U2, ANY.Iron, OP.stick.mAmount / 2));
		OM.data(ST.make(this, 1, RSTBR), new OreDictItemData(mMaterial, U2, MT.Redstone, OP.dust.mAmount / 2));
		OM.data(ST.make(this, 1, TILES), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, STILE), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, SBRIK), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, WINDA), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, WINDB), new OreDictItemData(mMaterial, U2));
		OM.data(ST.make(this, 1, QBRIK), new OreDictItemData(mMaterial, U2));
		
		for (int i = 0; i < maxMeta(); i++) mEqualBlocks[i].add(ST.make(this, 1, i));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		if ((!MD.NePl.mLoaded && !MD.NeLi.mLoaded) || (mMaterial != MT.Basalt && mMaterial != MT.Blackstone)) {
			RM.pack(rockGt.mat(mMaterial, 4), ST.make(this, 1, COBBL));
			CR.shaped(ST.make(this, 1, COBBL), CR.DEF, "XX", "XX", 'X', OP.rockGt.dat(mMaterial));
		}
		
		CR.shaped(gearGtSmall.mat(     mMaterial, 1), CR.DEF_NAC, "X ", " f", 'X', OP.stone.dat(mMaterial));
		CR.shaped(ST.make(Blocks.stone_stairs, 1, 0), CR.DEF_MIR, " X", "XX", 'X', OP.rockGt.dat(mMaterial)); // TODO Stairs
		CR.shaped(ST.make(mSlabs[0]      , 1, COBBL), CR.DEF    , "  ", "XX", 'X', OP.rockGt.dat(mMaterial));
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[STONE]) {
		if (FL.Mana_TE.exists())
		RM.Bath         .addRecipe1(T,  0, 16                  , tStack.toStack(), FL.Mana_TE.make(1), NF, ST.make(this, 1, CHISL));
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, SMOTH));
		CR.shaped(ST.make(this, 4, BRICK), CR.DEF_NAC, "XX", "XX", 'X', tStack.toStack());
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(mMaterial, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(mMaterial, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Ingot       .get(0), ST.make(this, 1, BRICK));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Block       .get(0), ST.make(this, 1, STONE));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.amount(4, tStack.toStack()), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.amount(6, tStack.toStack()), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(mMaterial, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(mMaterial, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Ingot       .get(0), ST.make(this, 1, BRICK));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Block       .get(0), ST.make(this, 1, STONE));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.amount(4, tStack.toStack()), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.amount(6, tStack.toStack()), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(mMaterial, 1));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[COBBL]) {
		if (FL.Mana_TE.exists())
		RM.Bath         .addRecipe1(T,  0, 16                  , tStack.toStack(), FL.Mana_TE.make(1), NF, ST.make(this, 1, MCOBL));
		RM.Hammer       .addRecipe1(T, 16, 16,  8000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.cobblestone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shaped(ST.make(mSlabs[0]          , 4, COBBL), CR.DEF    , "  " , "XX" , 'X', tStack.toStack());
		CR.shaped(ST.make(Blocks.stone_stairs    , 4, 0), CR.DEF_MIR, " X" , "XX" , 'X', tStack.toStack()); // TODO Stairs
		CR.shaped(ST.make(Blocks.cobblestone_wall, 6, 0), CR.DEF_MIR, "XXX", "XXX", 'X', tStack.toStack()); // TODO Walls
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(mMaterial, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(mMaterial, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Ingot       .get(0), ST.make(this, 1, BRICK));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Block       .get(0), ST.make(this, 1, STONE));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.amount(4, tStack.toStack()), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.amount(6, tStack.toStack()), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, F, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(mMaterial, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(mMaterial, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Ingot       .get(0), ST.make(this, 1, BRICK));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Block       .get(0), ST.make(this, 1, STONE));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.amount(2, tStack.toStack()), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.amount(3, tStack.toStack()), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.amount(4, tStack.toStack()), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.amount(1, tStack.toStack()), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(mMaterial, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.amount(6, tStack.toStack()), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(mMaterial, 1));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[MCOBL]) {
		RM.Hammer       .addRecipe1(T, 16, 16,  8000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.mossy_cobblestone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[BRICK]) {
		if (FL.Mana_TE.exists())
		RM.Bath         .addRecipe1(T,  0, 16                  , tStack.toStack(), FL.Mana_TE.make(1), NF, ST.make(this, 1, MBRIK));
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stonebrick, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shaped(ST.make(this, 1, CRACK), CR.DEF_NAC, "h" , "X" , 'X', tStack.toStack());
		CR.shaped(ST.make(this, 1, RNFBR), CR.DEF_MIR, "Se", "X ", 'X', tStack.toStack(), 'S', OP.stick.dat(ANY.Iron));
		CR.shaped(ST.make(this, 1, RSTBR), CR.DEF_NAC, "Dh", "X ", 'X', tStack.toStack(), 'D', OD.itemRedstone);
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[CRACK]) {
		RM.Hammer       .addRecipe1(T, 16, 16,  7000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stonebrick, 1, 1));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[MBRIK]) {
		RM.Hammer       .addRecipe1(T, 16, 16,  7000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stonebrick, 1, 2));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[CHISL]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stonebrick, 1, 3));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, COBBL));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shaped(ST.make(this, 1, CHISL), CR.DEF_NAC, "y" , "X" , 'X', tStack.toStack());
		CR.shaped(ST.make(this, 4, BRICK), CR.DEF_NAC, "XX", "XX", 'X', tStack.toStack());
		CR.shaped(ST.make(this, 2, TILES), CR.DEF_NAC, "X" , "X" , 'X', tStack.toStack());
		CR.shaped(ST.make(this, 2, STILE), CR.DEF_NAC, "XX"      , 'X', tStack.toStack());
		CR.shaped(ST.make(this, 2, SBRIK), CR.DEF_NAC, "X ", " X", 'X', tStack.toStack());
		CR.shaped(ST.make(this, 2, WINDA), CR.DEF_NAC, " X", "X ", 'X', tStack.toStack());
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[RNFBR]) {
		RM.Hammer       .addRecipe1(T, 16, 16,  7000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 64+mHarvestLevel* 64, tStack.toStack(), ST.make(this, 1, COBBL), OM.dust(MT.Fe, OP.stick.mAmount));
		RM.Shredder     .addRecipe1(T, 16, 64+mHarvestLevel* 64, tStack.toStack(), OP.dust.mat(mMaterial, 1), OM.dust(MT.Fe, OP.stick.mAmount));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[RSTBR]) {
		RM.Hammer       .addRecipe1(T, 16, 16,  7000           , tStack.toStack(), OP.rockGt.mat(mMaterial, 4));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK), OM.dust(MT.Redstone));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1), OM.dust(MT.Redstone));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[TILES]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shapeless(ST.make(this, 1, QBRIK), CR.DEF_NAC, new Object[] {tStack.toStack()});
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[STILE]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SBRIK]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[WINDA]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shapeless(ST.make(this, 1, WINDB), CR.DEF_NAC, new Object[] {tStack.toStack()});
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[WINDB]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shapeless(ST.make(this, 1, WINDA), CR.DEF_NAC, new Object[] {tStack.toStack()});
		}
		
		for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[QBRIK]) {
		RM.Hammer       .addRecipe1(T, 16, 16                  , tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Crusher      .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), ST.make(this, 1, CRACK));
		RM.Shredder     .addRecipe1(T, 16, 16+mHarvestLevel* 16, tStack.toStack(), OP.dust.mat(mMaterial, 1));
		RM.generify(tStack.toStack(), ST.make(Blocks.stone, 1, 0));
		RM.add_smelting(tStack.toStack(), ST.make(this, 1, STONE));
		CR.shapeless(ST.make(this, 1, TILES), CR.DEF_NAC, new Object[] {tStack.toStack()});
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_White])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 64, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, CHISL));
			}
		}
		
		
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Red])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, TILES));
			}
		}
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Green])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, STILE));
			}
		}
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Blue])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, WINDA));
			}
		}
		
		
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Cyan])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, BRICK));
			}
		}
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Magenta])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, SBRIK));
			}
		}
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Yellow])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, WINDB));
			}
		}
		
		if (aEvent.mOreDictName.equals(DYE_OREDICTS_LENS[DYE_INDEX_Pink])) {
			for (ItemStackContainer tStack : (ItemStackSet<ItemStackContainer>)mEqualBlocks[SMOTH]) {
				RM.LaserEngraver.addRecipe2(T, 16, 16, tStack.toStack(), ST.amount(0, aEvent.mStack), ST.make(this, 1, QBRIK));
			}
		}
	}
	
	@Override
	public int getItemStackLimit(ItemStack aStack) {
		switch (ST.meta_(aStack)) {
		case  0: return UT.Code.bindStack(OP.stone           .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  1: return UT.Code.bindStack(OP.stoneCobble     .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  2: return UT.Code.bindStack(OP.stoneMossy      .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  4: return UT.Code.bindStack(OP.stoneCracked    .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  5: return UT.Code.bindStack(OP.stoneMossyBricks.mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  6: return UT.Code.bindStack(OP.stoneChiseled   .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		case  7: return UT.Code.bindStack(OP.stonePolished   .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		default: return UT.Code.bindStack(OP.stoneBricks     .mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));
		}
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aTool.equals(TOOL_prospector)) return aMeta == STONE && ToolCompat.prospectStone(this, aMeta, aQuality, aChatReturn, aWorld, aSide, aX, aY, aZ) ? 10000 : 0;
		if (aTool.equals(TOOL_chisel) && !aSneaking && CHISEL_MAPPINGS[aMeta & 15] != aMeta) {
			aWorld.setBlockMetadataWithNotify(aX, aY, aZ, CHISEL_MAPPINGS[aMeta & 15], 3);
			return mOctantcount * 1250;
		}
		if (aTool.equals(TOOL_file) && !aSneaking) {
			switch(aMeta) {
			case STONE: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, SMOTH, 3); return mOctantcount * 1250; // Stone to Smooth
			case BRICK: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, SBRIK, 3); return mOctantcount * 1250; // Bricks to Small Bricks
			case TILES: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, STILE, 3); return mOctantcount * 1250; // Tile to Small Tile
			case SBRIK: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, STILE, 3); return mOctantcount * 1250; // Small Bricks to Small Tile
			case WINDA: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, WINDB, 3); return mOctantcount * 1250;
			case WINDB: aWorld.setBlockMetadataWithNotify(aX, aY, aZ, WINDA, 3); return mOctantcount * 1250;
			}
		}
		if (aTool.equals(TOOL_drill) && !aSneaking) {
			if (mBlock == this && aPlayer instanceof EntityPlayer && aMeta == BRICK) {
				for (int i = 0; i < ((EntityPlayer)aPlayer).inventory.mainInventory.length; i++) {
					int tIndex = ((EntityPlayer)aPlayer).inventory.mainInventory.length-i-1;
					ItemStack tStack = ((EntityPlayer)aPlayer).inventory.mainInventory[tIndex];
					if (OM.is("stickAnyIronOrSteel", tStack)) {
						if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, RNFBR, 3)) {
							ST.use(aPlayer, tIndex, tStack);
							return mOctantcount * 1250;
						}
						break;
					}
				}
			}
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {
		switch(WD.meta(aWorld, aX, aY, aZ)) {
		case RNFBR: return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier * 2;
		default   : return Blocks.stone.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier;
		}
	}
	
	@Override
	public float getExplosionResistance(byte aMeta) {
		switch(aMeta) {
		case RNFBR: return Blocks.stone.getExplosionResistance(null) * mResistanceMultiplier * 2;
		default   : return Blocks.stone.getExplosionResistance(null) * mResistanceMultiplier;
		}
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!aWorld.isRemote && WD.burning(aWorld, aX, aY, aZ)) switch(WD.meta(aWorld, aX, aY, aZ)) {
		case MCOBL: aWorld.setBlock(aX, aY, aZ, this, COBBL, 3); break;
		case MBRIK: aWorld.setBlock(aX, aY, aZ, this, BRICK, 3); break;
		}
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide, IPlantable aPlant) {
		return PLANTABLE[WD.meta(aWorld, aX, aY, aZ)] && aPlant.getPlantType(aWorld, aX+aSide.offsetX, aY+aSide.offsetY, aZ+aSide.offsetZ) == EnumPlantType.Cave;
	}
	
	static {
		LH.add("gt.tooltip.stone.mushroom.yes", "Mushrooms can spread to this rough Stone");
		LH.add("gt.tooltip.stone.mushroom.no", "Mushrooms cant spread to smooth Stones!");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		super.addInformation(aStack, aMeta, aPlayer, aList, aF3_H);
		if (PLANTABLE[aMeta]) {
			aList.add(LH.Chat.GREEN + LH.get("gt.tooltip.stone.mushroom.yes"));
		} else {
			aList.add(LH.Chat.ORANGE + LH.get("gt.tooltip.stone.mushroom.no"));
		}
	}
	
	@Override public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {return new ArrayListNoNulls<>(F, ST.make(this, 1, mBlock == this && aMeta == STONE ? COBBL : aMeta));}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return SEALABLE[aMeta] && super.isSealable(aMeta, aSide);}
	@Override public int isProvidingWeakPower(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return WD.meta(aWorld, aX, aY, aZ) == RSTBR ? 15 : 0;}
	@Override public boolean shouldCheckWeakPower(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return mBlock == this && WD.meta(aWorld, aX, aY, aZ) != RSTBR;}
	@Override public void onNeighborBlockChange2(World aWorld, int aX, int aY, int aZ, Block aBlock) {if (MOSSY[WD.meta(aWorld, aX, aY, aZ)] && WD.burning(aWorld, aX, aY, aZ)) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate(aWorld));}
	@Override public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {if (MOSSY[WD.meta(aWorld, aX, aY, aZ)] && WD.burning(aWorld, aX, aY, aZ)) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate(aWorld));}
	@Override public int tickRate(World aWorld) {return 100;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return mBlock == this && SPAWNABLE[aMeta];}
	@Override public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return MOSSY[WD.meta(aWorld, aX, aY, aZ)];}
	@Override public int getFireSpreadSpeed(byte aMeta) {return MOSSY[aMeta]?3000:0;}
	@Override public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return MOSSY[WD.meta(aWorld, aX, aY, aZ)];}
	@Override public boolean isReplaceableOreGen(World aWorld, int aX, int aY, int aZ, Block aTarget) {return (aTarget == this || (aY <= 6 && aTarget == Blocks.stone)) && WD.meta(aWorld, aX, aY, aZ) == STONE;}
}
