/**
 * Copyright (c) 2019 Gregorius Techneticies
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

import java.util.Random;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.code.ArrayListNoNulls;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityStick extends TileEntityBase03MultiTileEntities implements ITileEntityQuickObstructionCheck, IMTE_CanEntityDestroy, IMTE_OnNeighborBlockChange, IMTE_GetBlockHardness, IMTE_IsSideSolid, IMTE_GetLightOpacity, IMTE_GetExplosionResistance, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetFlammability, IMTE_GetFireSpreadSpeed {
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
		return new ArrayListNoNulls<>(F, getDefaultStick(1+rng(1+aFortune)));
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
		if (isServerSide() && !worldObj.getBlock(xCoord, yCoord-1, zCoord).isSideSolid(worldObj, xCoord, yCoord-1, zCoord, FORGE_DIR[SIDE_TOP])) {
			ST.drop(worldObj, getCoords(), getDefaultStick(1));
			setToAir();
		}
	}
	
	public ItemStack getDefaultStick(int aAmount) {
		return WD.dimAETHER(worldObj) ? OP.stick.mat(MT.Skyroot, aAmount) : WD.dimBTL(worldObj) ? OP.stick.mat(MT.Weedwood, aAmount) : (WD.dimALF(worldObj) && rng(8) == 0) ? OP.stick.mat(MT.Dreamwood, aAmount) : ST.make(Items.stick, aAmount, 0);
	}
	
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] || SIDES_TOP_HORIZONTAL[aSide] ? mTexture : null;}
	
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock,    mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ); return T;}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock,                                             mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool() {return box(                                            mMinX, 0, mMinZ, mMaxX, PX_P[2], mMaxZ);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return F;}
	@Override public boolean isSideSolid            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean canEntityDestroy(Entity aEntity) {return !(aEntity instanceof EntityDragon);}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public float getExplosionResistance2() {return 0;}
	@Override public float getBlockHardness() {return 0.25F;}
	@Override public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return 300;}
	@Override public int getFlammability(byte aSide, boolean aDefault) {return 300;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.stick";}
}
