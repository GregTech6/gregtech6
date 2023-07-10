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

package gregtech.tileentity.placeables;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityStick extends TileEntityBase03MultiTileEntities implements ITileEntityQuickObstructionCheck, IMTE_CanEntityDestroy, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_OnNeighborBlockChange, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetFlammability, IMTE_GetFireSpreadSpeed {
	public static final ITexture mTexture = BlockTextureCopied.get(Blocks.log, SIDE_FRONT, 0);
	public float mMinX = PX_P[2], mMinZ = PX_P[7], mMaxX = PX_N[2], mMaxZ = PX_N[7];
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		Random tRandom = new Random(xCoord^yCoord^zCoord);
		if (tRandom.nextInt(1000000) < 500000) {
			mMinX = PX_P[tRandom.nextInt(15)];
			mMinZ = PX_P[tRandom.nextInt( 3)];
			mMaxX = mMinX+PX_P[ 2];
			mMaxZ = mMinZ+PX_P[14];
		} else {
			mMinX = PX_P[tRandom.nextInt( 3)];
			mMinZ = PX_P[tRandom.nextInt(15)];
			mMaxX = mMinX+PX_P[14];
			mMaxZ = mMinZ+PX_P[ 2];
		}
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return ST.arraylist(getDefaultStick(1+rng(1+aFortune)));
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return T;
		UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, getDefaultStick(1), T, worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5);
		playCollect();
		return setToAir();
	}
	
	@Override
	public void onNeighborBlockChange(World aWorld, Block aBlock) {
		if (isServerSide()) {
			if (!worldObj.getBlock(xCoord, yCoord-1, zCoord).isSideSolid(worldObj, xCoord, yCoord-1, zCoord, FORGE_DIR[SIDE_TOP])) {
				ST.drop(worldObj, getCoords(), getDefaultStick(1));
				setToAir();
				return;
			}
			for (byte tSide : ALL_SIDES_HORIZONTAL_UP) if (WD.liquid(getBlockAtSide(tSide))) {
				ST.drop(worldObj, getCoords(), getDefaultStick(1));
				setToAir();
				return;
			}
		}
	}
	
	public ItemStack getDefaultStick(int aAmount) {
		// Tell WAILA and the NEI Overlay that this is a normal Stick.
		if (worldObj == null || isClientSide()) return IL.Stick.get(aAmount);
		// Dimension specific Drops.
		if (WD.dimAETHER(worldObj)) return OP.stick.mat(rng(3) > 0 ? MT.Skyroot       : MT.WOODS.Dead    , aAmount);
		if (WD.dimERE   (worldObj)) return OP.stick.mat(rng(8) > 0 ? MT.WOODS.Dead    : MT.PetrifiedWood , aAmount);
		if (WD.dimBTL   (worldObj)) return OP.stick.mat(rng(3) > 0 ? MT.Weedwood      : MT.WOODS.Rotten  , aAmount);
		if (WD.dimATUM  (worldObj)) return OP.stick.mat(rng(4) > 0 ? MT.WOODS.Coconut : MT.WOODS.Dead    , aAmount);
		if (WD.dimTROPIC(worldObj)) return OP.stick.mat(rng(2) > 0 ? MT.WOODS.Coconut : MT.WOODS.Mahogany, aAmount);
		if (WD.dimALF   (worldObj)) return OP.stick.mat(rng(8) > 0 ? MT.Livingwood    : MT.Dreamwood     , aAmount);
		if (WD.dimTF    (worldObj)) return rng(16) > 0 ? IL.Stick.get(aAmount) : IL.TF_LiveRoot.get(aAmount);
		String tBiome = worldObj.getBiomeGenForCoords(xCoord, zCoord).biomeName.toLowerCase();
		// The order of checks matters because things like Ice Deserts are a thing.
		if (tBiome.contains("rainforest" )) return getStick(NI, aAmount);
		if (tBiome.contains("firefly"    )) return getStick(NI, aAmount);
		if (tBiome.contains("dark forest")) return getStick(OP.stick.mat(MT.WOODS.Towerwood , aAmount), aAmount);
		if (tBiome.contains("silver pine")) return getStick(OP.stick.mat(MT.WOODS.SilverPine, aAmount), aAmount);
		if (tBiome.contains("redwood"    )) return getStick(OP.stick.mat(MT.WOODS.Redwood   , aAmount), aAmount);
		if (tBiome.contains("cypress"    )) return getStick(OP.stick.mat(MT.WOODS.Cypress   , aAmount), aAmount);
		if (tBiome.contains("maple"      )) return getStick(OP.stick.mat(MT.WOODS.Maple     , aAmount), aAmount);
		if (tBiome.contains("tropic"     )) return getStick(OP.stick.mat(MT.WOODS.Coconut   , aAmount), aAmount);
		if (tBiome.contains("aspen"      )) return getStick(OP.stick.mat(MT.WOODS.Aspen     , aAmount), aAmount);
		if (tBiome.contains("autumn"     )) return getStick(OP.stick.mat(MT.WOODS.Autumn    , aAmount), aAmount);
		if (tBiome.contains("spruce"     )) return getStick(OP.stick.mat(MT.WOODS.Spruce    , aAmount), aAmount);
		if (tBiome.contains("taiga"      )) return getStick(OP.stick.mat(MT.WOODS.Spruce    , aAmount), aAmount);
		if (tBiome.contains("boreal"     )) return getStick(OP.stick.mat(MT.WOODS.Spruce    , aAmount), aAmount);
		if (tBiome.contains("birch"      )) return getStick(OP.stick.mat(MT.WOODS.Birch     , aAmount), aAmount);
		if (tBiome.contains("jungle"     )) return getStick(OP.stick.mat(MT.WOODS.Jungle    , aAmount), aAmount);
		if (tBiome.contains("savann"     )) return getStick(OP.stick.mat(MT.WOODS.Acacia    , aAmount), aAmount);
		if (tBiome.contains("roofed"     )) return getStick(OP.stick.mat(MT.WOODS.DarkOak   , aAmount), aAmount);
		if (tBiome.contains("dark oak"   )) return getStick(OP.stick.mat(MT.WOODS.DarkOak   , aAmount), aAmount);
		if (tBiome.contains("oak"        )) return getStick(OP.stick.mat(MT.WOODS.Oak       , aAmount), aAmount);
		if (tBiome.contains("alpine"     )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("pine"       )) return getStick(OP.stick.mat(MT.WOODS.Pine      , aAmount), aAmount);
		if (tBiome.contains("fire"       )) return          OP.stick.mat(MT.WOODS.Scorched  , aAmount);
		if (tBiome.contains("fir"        )) return getStick(OP.stick.mat(MT.WOODS.Fir       , aAmount), aAmount);
		if (tBiome.contains("volcan"     )) return          OP.stick.mat(MT.WOODS.Scorched  , aAmount);
		if (tBiome.contains("glacier"    )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("ice"        )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("cold"       )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("snow"       )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("frost"      )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("polar"      )) return          OP.stick.mat(MT.WOODS.Frozen    , aAmount);
		if (tBiome.contains("swamp"      )) return          OP.stick.mat(MT.WOODS.Mossy     , aAmount);
		if (tBiome.contains("marsh"      )) return          OP.stick.mat(MT.WOODS.Mossy     , aAmount);
		if (tBiome.contains("moor"       )) return          OP.stick.mat(MT.WOODS.Mossy     , aAmount);
		if (tBiome.contains("mire"       )) return          OP.stick.mat(MT.WOODS.Mossy     , aAmount);
		if (tBiome.contains("bog"        )) return          OP.stick.mat(MT.WOODS.Mossy     , aAmount);
		if (tBiome.contains("mesa"       )) return          OP.stick.mat(MT.WOODS.Dead      , aAmount);
		if (tBiome.contains("desert"     )) return          OP.stick.mat(MT.WOODS.Dead      , aAmount);
		if (tBiome.contains("sahara"     )) return          OP.stick.mat(MT.WOODS.Dead      , aAmount);
		if (tBiome.contains("waste"      )) return          OP.stick.mat(MT.WOODS.Dead      , aAmount);
		return getStick(NI, aAmount);
	}
	
	public ItemStack getStick(ItemStack aStack, int aAmount) {
		switch(rng(16)) {
		case  0: return OP.stick.mat(MT.WOODS.Dead  , aAmount);
		case  1: return OP.stick.mat(MT.WOODS.Mossy , aAmount);
		case  2: return OP.stick.mat(MT.WOODS.Rotten, aAmount);
		}
		return ST.valid(aStack) ? ST.amount(aAmount, aStack) : IL.Stick.get(aAmount);
	}
	
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] || SIDES_TOP_HORIZONTAL[aSide] ? mTexture : null;}
	
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ); return T;}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock,                                          mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool() {return box(                                         mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ);}
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
	@Override public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return 300;}
	@Override public int getFlammability(byte aSide, boolean aDefault) {return 300;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.stick";}
}
