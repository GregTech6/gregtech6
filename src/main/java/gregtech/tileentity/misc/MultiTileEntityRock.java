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
import java.util.Random;

import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityRock extends TileEntityBase03MultiTileEntities implements IMTE_CanEntityDestroy, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_OnToolClick, IMTE_OnNeighborBlockChange, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public ItemStack mRock;
	public ITexture mTexture;
	public float mMinX = PX_P[5], mMinZ = PX_P[5], mMaxX = PX_N[5], mMaxY = PX_P[2], mMaxZ = PX_N[5];
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		Random tRandom = new Random(xCoord^yCoord^zCoord);
		mMinX = PX_P[4 + tRandom.nextInt(4)];
		mMinZ = PX_P[4 + tRandom.nextInt(4)];
		mMaxX = PX_N[4 + tRandom.nextInt(4)];
		mMaxZ = PX_N[4 + tRandom.nextInt(4)];
		mMaxY = PX_P[1 + tRandom.nextInt(4)];
		
		mRock = ST.load(aNBT, NBT_VALUE);
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		ST.save(aNBT, NBT_VALUE, mRock);
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return new ArrayListNoNulls<>(F, mRock == null ? getDefaultRock(1+rng(1+aFortune)) : ST.amount(1+rng(1+aFortune), mRock));
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && aTool.equals(TOOL_magnifyingglass)) {
			if (aPlayer instanceof EntityPlayer && aSneaking) {
				UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, mRock == null ? getDefaultRock(1) : ST.amount(1, mRock), T, worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5);
				playCollect();
				setToAir();
				return 0;
			}
			if (aChatReturn == null) return 1;
			if (mRock == null) {
				if (worldObj.provider.dimensionId == -1)         {aChatReturn.add(LH.Chat.GRAY + "This is definitely a Rack"); return 1;}
				if (worldObj.provider.dimensionId ==  0)         {aChatReturn.add(LH.Chat.GRAY + "This is definitely a Rock"); return 1;}
				if (worldObj.provider.dimensionId == +1)         {aChatReturn.add(LH.Chat.GRAY + "There is definitely an End"); return 1;}
				if (WD.dimAETHER(worldObj))                      {aChatReturn.add(LH.Chat.GRAY + "Holy $#!T, it's a Rock.."); return 1;}
				if (WD.dimALF   (worldObj))                      {aChatReturn.add(LH.Chat.GRAY + "Wait that Rock is alive?!"); return 1;}
				if (WD.dimTROPIC(worldObj))                      {aChatReturn.add(LH.Chat.GRAY + "Seems to be a Chunk o'Head"); return 1;}
				if (BIOMES_MOON.contains(getBiome().biomeName))  {aChatReturn.add(LH.Chat.GRAY + "This is definitely not made of Cheese"); return 1;}
				if (BIOMES_MARS.contains(getBiome().biomeName))  {aChatReturn.add(LH.Chat.GRAY + "This is definitely from Mars"); return 1;}
				if (BIOMES_SPACE.contains(getBiome().biomeName)) {aChatReturn.add(LH.Chat.GRAY + "This is definitely a Space Rock"); return 1;}
				aChatReturn.add(LH.Chat.GRAY + "This definitely is a Rock");
				return 1;
			}
			if (OD.itemFlint.is_(mRock)) {aChatReturn.add(LH.Chat.GRAY + (APRIL_FOOLS||rng(1000)==0?"Flintstones, meet the Flintstones, they're the modern Stone Age family":"It's a Flint")); return 1;}
			OreDictItemData tData = OM.anydata_(mRock);
			if (tData != null && tData.hasValidMaterialData()) {
				if (tData.mMaterial.mMaterial == MT.MeteoricIron || tData.mMaterial.mMaterial == MT.Meteorite) {
					aChatReturn.add(LH.Chat.ORANGE + "Looks like it fell from the Sky!"); return 1;
				}
				if (tData.mMaterial.mMaterial == MT.AncientDebris) {
					aChatReturn.add(LH.Chat.PINK + "Looks quite old and broken"); return 1;
				}
				if (tData.mMaterial.mMaterial == MT.Stone) {
					aChatReturn.add(LH.Chat.GRAY + "This is definitely a Rock"); return 1;
				}
				if (tData.mMaterial.mMaterial == MT.Netherrack) {
					aChatReturn.add(LH.Chat.GRAY + "This is definitely a Rack"); return 1;
				}
				if (tData.mMaterial.mMaterial == MT.Endstone) {
					aChatReturn.add(LH.Chat.GRAY + "There is definitely an End"); return 1;
				}
				if (tData.mMaterial.mMaterial.contains(TD.Properties.GLOWING)) {
					aChatReturn.add(LH.Chat.YELLOW + "Glows a little"); return 1;
				}
				if (tData.mMaterial.mMaterial.contains(TD.Properties.STONE)) {
					aChatReturn.add(LH.Chat.GRAY + "This Rock consists out of " + LH.Chat.WHITE + tData.mMaterial.mMaterial.getLocal()); return 1;
				}
				aChatReturn.add(LH.Chat.GRAY + "This Rock is bearing " + LH.Chat.CYAN + tData.mMaterial.mMaterial.getLocal());
				return 1;
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, mRock == null ? getDefaultRock(1) : ST.amount(1, mRock), T, worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5);
		playCollect();
		return setToAir();
	}
	
	@Override
	public void onNeighborBlockChange(World aWorld, Block aBlock) {
		if (isServerSide() && !worldObj.getBlock(xCoord, yCoord-1, zCoord).isSideSolid(worldObj, xCoord, yCoord-1, zCoord, FORGE_DIR[SIDE_TOP])) {
			ST.drop(worldObj, getCoords(), mRock == null ? getDefaultRock(1) : ST.amount(1, mRock));
			setToAir();
		}
	}
	
	public ItemStack getDefaultRock(int aAmount) {
		// Tell WAILA and the NEI Overlay that this is a normal Rock.
		if (worldObj == null || isClientSide()) return OP.rockGt.mat(MT.Stone, aAmount);
		// Dimension and Biome specific Drops.
		if (worldObj.provider.dimensionId == -1) return OP.rockGt.mat(MT.Netherrack, aAmount);
		if (worldObj.provider.dimensionId ==  0) return OP.rockGt.mat(MT.Stone, aAmount);
		if (worldObj.provider.dimensionId == +1) return OP.rockGt.mat(MT.Endstone, aAmount);
		if (WD.dimAETHER(worldObj)) return OP.rockGt.mat(MT.STONES.Holystone, aAmount);
		if (WD.dimERE   (worldObj)) return OP.rockGt.mat(MT.STONES.Umber, aAmount);
		if (WD.dimBTL   (worldObj)) return OP.rockGt.mat(MT.STONES.Betweenstone, aAmount);
		if (WD.dimATUM  (worldObj)) return OP.rockGt.mat(MT.STONES.Limestone, aAmount);
		if (WD.dimTROPIC(worldObj)) return OP.rockGt.mat(MT.STONES.Basalt, aAmount);
		if (WD.dimALF   (worldObj)) return OP.rockGt.mat(MT.STONES.Livingrock, aAmount);
		if (WD.dimTF    (worldObj)) return OP.rockGt.mat(MT.Stone, aAmount);
		if (BIOMES_MOON .contains(getBiome().biomeName)) return OP.rockGt.mat(MT.STONES.MoonRock, aAmount);
		if (BIOMES_MARS .contains(getBiome().biomeName)) return OP.rockGt.mat(MT.STONES.MarsRock, aAmount);
		if (BIOMES_SPACE.contains(getBiome().biomeName)) return OP.rockGt.mat(MT.STONES.SpaceRock, aAmount);
		return OP.rockGt.mat(MT.Stone, aAmount);
	}
	
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] || SIDES_TOP_HORIZONTAL[aSide] ? mTexture : null;}
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		if (worldObj == null) {
			mTexture = BlockTextureCopied.get(Blocks.stone); return 1;
		}
		Block tBlock = getBlockAtSide(SIDE_BOTTOM);
		if (tBlock == BlocksGT.Diggables) {
			mTexture = BlockTextureCopied.get(BlocksGT.Kimberlite, SIDE_ANY, 0); return 1;
		}
		if (tBlock instanceof BlockStones || tBlock == Blocks.stone || tBlock == Blocks.end_stone || tBlock == Blocks.obsidian) {
			mTexture = BlockTextureCopied.get(tBlock, SIDE_ANY, 0); return 1;
		}
		if (tBlock == Blocks.netherrack || tBlock == Blocks.nether_brick || tBlock == Blocks.soul_sand) {
			mTexture = BlockTextureCopied.get(Blocks.netherrack, SIDE_ANY, 0); return 1;
		}
		if (tBlock == Blocks.sandstone || tBlock == Blocks.sand || IL.AETHER_Sand.equal(tBlock)) {
			mTexture = BlockTextureCopied.get(Blocks.sandstone, SIDE_FRONT, 0); return 1;
		}
		if (tBlock == Blocks.cobblestone || tBlock == Blocks.gravel) {
			mTexture = BlockTextureCopied.get(Blocks.cobblestone, SIDE_ANY, 0); return 1;
		}
		if (IL.NeLi_Gravel.equal(tBlock)) {
			mTexture = BlockTextureCopied.get(BlocksGT.GraniteBlack, SIDE_ANY, 0); return 1;
		}
		
		if (worldObj.provider.dimensionId == -1) {mTexture = BlockTextureCopied.get(Blocks.netherrack); return 1;}
		if (worldObj.provider.dimensionId ==  0) {mTexture = BlockTextureCopied.get(Blocks.stone); return 1;}
		if (worldObj.provider.dimensionId == +1) {mTexture = BlockTextureCopied.get(Blocks.end_stone); return 1;}
		if (WD.dimTF(worldObj))                  {mTexture = BlockTextureCopied.get(Blocks.stone); return 1;}
		if (WD.dimERE(worldObj))                 {mTexture = BlockTextureCopied.get(Blocks.stone, SIDE_ANY, 0, 0x907050, F, F, F); return 1;}
		if (WD.dimBTL(worldObj))                 {mTexture = BlockTextureCopied.get(Blocks.stone, SIDE_ANY, 0, 0x308030, F, F, F); return 1;}
		if (WD.dimALF(worldObj))                 {mTexture = BlockTextureCopied.get(BlocksGT.Marble); return 1;}
		if (WD.dimATUM(worldObj))                {mTexture = BlockTextureCopied.get(BlocksGT.Limestone); return 1;}
		if (WD.dimAETHER(worldObj))              {mTexture = BlockTextureCopied.get(BlocksGT.Andesite); return 1;}
		if (WD.dimTROPIC(worldObj))              {mTexture = BlockTextureCopied.get(BlocksGT.Basalt); return 1;}
		
		if (BIOMES_SPACE.contains(getBiome().biomeName)) {
			if (tBlock.getMaterial() == Material.rock) {mTexture = BlockTextureCopied.get(tBlock, getMetaDataAtSide(SIDE_BOTTOM)); return 1;}
			mTexture = BlockTextureCopied.get(Blocks.obsidian); return 1;
		}
		mTexture = BlockTextureCopied.get(Blocks.stone);
		return 1;
	}
	
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock,mMinX, 0, mMinZ, mMaxX, mMaxY, mMaxZ); return T;}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock,                                         mMinX, 0, mMinZ, mMaxX, mMaxY, mMaxZ);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool() {return box(                                        mMinX, 0, mMinZ, mMaxX, mMaxY, mMaxZ);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	@Override public boolean isSideSolid            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean canEntityDestroy(Entity aEntity) {return !(aEntity instanceof EntityDragon);}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public float getBlockHardness() {return 0.25F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.rock";}
}
