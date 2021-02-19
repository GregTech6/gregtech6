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

import java.util.Iterator;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityRemoteActivateable;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityDynamite extends TileEntityBase09FacingSingle implements ITileEntityRemoteActivateable, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public byte mCountDown = 0, mFortune = 0;
	public boolean mSunk = F;
	public long mMaxExplosionResistance = 10;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE)) mCountDown = aNBT.getByte(NBT_STATE);
		if (aNBT.hasKey(NBT_MODE)) mSunk = aNBT.getBoolean(NBT_MODE);
		if (aNBT.hasKey(NBT_QUALITY)) mMaxExplosionResistance = aNBT.getLong(NBT_QUALITY);
		if (aNBT.hasKey(NBT_FORTUNE)) mFortune = aNBT.getByte(NBT_FORTUNE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_STATE, mCountDown);
		UT.NBT.setBoolean(aNBT, NBT_MODE, mSunk);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.TOOLTIP_BLASTPOWER) + Chat.WHITE + mMaxExplosionResistance);
		aList.add(Chat.CYAN     + LH.get(LH.TOOLTIP_BLASTRANGE) + Chat.WHITE + "3x3x3");
		if (mFortune > 0)
		aList.add(Chat.CYAN     + LH.get(LH.TOOLTIP_BLASTFORTUNE) + Chat.WHITE + mFortune);
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE) + " (" + LH.get(LH.FACE_FRONT) + ")");
		aList.add(Chat.RED      + LH.get(LH.TOOLTIP_EXPLOSIVE));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_igniter       ) && mCountDown == 0 && WD.oxygen(worldObj, xCoord, yCoord, zCoord) ) {mCountDown = 100; updateClientData(); UT.Sounds.send(worldObj, SFX.MC_TNT_IGNITE , 1.0F, 0.5F, getCoords()); return 10000;}
		if (aTool.equals(TOOL_extinguisher  ) && mCountDown != 0                                                ) {mCountDown =   0; updateClientData(); UT.Sounds.send(worldObj, SFX.MC_FIZZ       , 1.0F, 0.5F, getCoords()); return 10000;}
		return 0;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBlockUpdated || aTimer == 2) {
				if (hasRedstoneIncoming() || WD.burning(worldObj, xCoord, yCoord, zCoord)) remoteActivate();
				if (mSunk && !WD.ore_stone(getBlockAtSide(OPPOSITES[mFacing]), getMetaDataAtSide(OPPOSITES[mFacing]))) {mSunk = F; updateClientData();}
			}
			if (mCountDown > 0 && --mCountDown <= 0) explode();
		}
	}
	
	@Override
	public void setVisualData(byte aData) {
		mCountDown = (byte)(aData >>> 1);
		mSunk = ((aData & 1) != 0);
	}
	
	@Override public byte getVisualData() {return (byte)((mCountDown<<1)|(mSunk?1:0));}
	
	private boolean mDontDrop = F;
	
	@Override
	public void explode() {
		mDontDrop = T;
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		Explosion tExplosion = mSunk ? new DynamiteExplosion(worldObj, getOffsetXN(mFacing)+0.5, getOffsetYN(mFacing)+0.5, getOffsetZN(mFacing)+0.5, mMaxExplosionResistance, mFortune) : new DynamiteExplosion(worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5, mMaxExplosionResistance, mFortune);
		tExplosion.doExplosionA();
		tExplosion.doExplosionB(T);
	}
	
	@Override
	public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch) {
		return mDontDrop ? new ArrayListNoNulls<ItemStack>() : super.getDrops(aFortune, aSilkTouch);
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		box(aBlock, PX_P[SIDE_X_NEG==mFacing?mSunk?14:0:SIDE_X_POS==mFacing?0:5], PX_P[SIDE_Y_NEG==mFacing?mSunk?14:0:SIDE_Y_POS==mFacing?0:5], PX_P[SIDE_Z_NEG==mFacing?mSunk?14:0:SIDE_Z_POS==mFacing?0:5], PX_N[SIDE_X_POS==mFacing?mSunk?14:0:SIDE_X_NEG==mFacing?0:5], PX_N[SIDE_Y_POS==mFacing?mSunk?14:0:SIDE_Y_NEG==mFacing?0:5], PX_N[SIDE_Z_POS==mFacing?mSunk?14:0:SIDE_Z_NEG==mFacing?0:5]);
		return T;
	}
	
	public static final IIconContainer
	sTextureFront       = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored/front"),
	sTextureBack        = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored/back"),
	sTextureSide        = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored/side"),
	sOverlayFront       = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay/front"),
	sOverlayBack        = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay/back"),
	sOverlaySide        = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay/side"),
	sTextureFrontActive = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored_active/front"),
	sTextureBackActive  = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored_active/back"),
	sTextureSideActive  = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/colored_active/side"),
	sOverlayFrontActive = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay_active/front"),
	sOverlayBackActive  = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay_active/back"),
	sOverlaySideActive  = new Textures.BlockIcons.CustomIcon("machines/tools/dynamite/overlay_active/side");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aSide == mFacing) return BlockTextureMulti.get(BlockTextureDefault.get(mCountDown != 0 ? sTextureFrontActive : sTextureFront, mRGBa, F, F, F, F), BlockTextureDefault.get(mCountDown != 0 ? sOverlayFrontActive : sOverlayFront));
		if (aSide == OPPOSITES[mFacing]) return BlockTextureMulti.get(BlockTextureDefault.get(mCountDown != 0 ? sTextureBackActive : sTextureBack, mRGBa, F, F, F, F), BlockTextureDefault.get(mCountDown != 0 ? sOverlayBackActive : sOverlayBack));
		return BlockTextureMulti.get(BlockTextureDefault.get(mCountDown != 0 ? sTextureSideActive : sTextureSide, mRGBa, F, F, F, F), BlockTextureDefault.get(mCountDown != 0 ? sOverlaySideActive : sOverlaySide));
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[SIDE_X_NEG==mFacing?mSunk?14:0:SIDE_X_POS==mFacing?0:5], PX_P[SIDE_Y_NEG==mFacing?mSunk?14:0:SIDE_Y_POS==mFacing?0:5], PX_P[SIDE_Z_NEG==mFacing?mSunk?14:0:SIDE_Z_POS==mFacing?0:5], PX_N[SIDE_X_POS==mFacing?mSunk?14:0:SIDE_X_NEG==mFacing?0:5], PX_N[SIDE_Y_POS==mFacing?mSunk?14:0:SIDE_Y_NEG==mFacing?0:5], PX_N[SIDE_Z_POS==mFacing?mSunk?14:0:SIDE_Z_NEG==mFacing?0:5]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[SIDE_X_NEG==mFacing?mSunk?14:0:SIDE_X_POS==mFacing?0:5], PX_P[SIDE_Y_NEG==mFacing?mSunk?14:0:SIDE_Y_POS==mFacing?0:5], PX_P[SIDE_Z_NEG==mFacing?mSunk?14:0:SIDE_Z_POS==mFacing?0:5], PX_N[SIDE_X_POS==mFacing?mSunk?14:0:SIDE_X_NEG==mFacing?0:5], PX_N[SIDE_Y_POS==mFacing?mSunk?14:0:SIDE_Y_NEG==mFacing?0:5], PX_N[SIDE_Z_POS==mFacing?mSunk?14:0:SIDE_Z_NEG==mFacing?0:5]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[SIDE_X_NEG==mFacing?mSunk?14:0:SIDE_X_POS==mFacing?0:5], PX_P[SIDE_Y_NEG==mFacing?mSunk?14:0:SIDE_Y_POS==mFacing?0:5], PX_P[SIDE_Z_NEG==mFacing?mSunk?14:0:SIDE_Z_POS==mFacing?0:5], PX_N[SIDE_X_POS==mFacing?mSunk?14:0:SIDE_X_NEG==mFacing?0:5], PX_N[SIDE_Y_POS==mFacing?mSunk?14:0:SIDE_Y_NEG==mFacing?0:5], PX_N[SIDE_Z_POS==mFacing?mSunk?14:0:SIDE_Z_NEG==mFacing?0:5]);}
	
	@Override public void onExploded(Explosion aExplosion) {mDontDrop = T; super.onExploded(aExplosion); explode();}
	@Override public boolean remoteActivate() {if (mCountDown > 20 || mCountDown == 0) {mCountDown = 20; updateClientData();} return F;}
	
	@Override public float getSurfaceSize           (byte aSide) {return 0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean useSidePlacementRotation       () {return T;}
	@Override public boolean useInversePlacementRotation    () {return F;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.dynamite";}
	
	public static class DynamiteExplosion extends Explosion {
		public World mWorld;
		public float mMaxExplosionResistance;
		public byte mFortune;
		
		public DynamiteExplosion(World aWorld, double aX, double aY, double aZ, float aMaxExplosionResistance, byte aFortune) {
			super(aWorld, null, aX, aY, aZ, 1);
			mMaxExplosionResistance = aMaxExplosionResistance;
			mFortune = aFortune;
			mWorld = aWorld;
		}
		
		@Override
		@SuppressWarnings({"unchecked", "rawtypes"})
		public void doExplosionA() {
			for (int tX = UT.Code.roundDown(explosionX) - 1; tX <= UT.Code.roundDown(explosionX) + 1; tX++) for (int tY = UT.Code.roundDown(explosionY) - 1; tY <= UT.Code.roundDown(explosionY) + 1; tY++) for (int tZ = UT.Code.roundDown(explosionZ) - 1; tZ <= UT.Code.roundDown(explosionZ) + 1; tZ++) {
				Block tBlock = mWorld.getBlock(tX, tY, tZ);
				if (tBlock.getExplosionResistance(exploder, mWorld, tX, tY, tZ, explosionX, explosionY, explosionZ) <= mMaxExplosionResistance) affectedBlockPositions.add(new ChunkPosition(tX, tY, tZ));
			}
			List tList = mWorld.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox(explosionX - 2, explosionY - 2, explosionZ - 2, explosionX + 2, explosionY + 2, explosionZ + 2));
			net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(mWorld, this, tList, explosionSize);
			DamageSource tSource = DamageSource.setExplosionSource(this);
			for (Object tEntity : tList) if (!(tEntity instanceof EntityItem)) ((Entity)tEntity).attackEntityFrom(tSource, 2*mMaxExplosionResistance);
			explosionSize = 1F;
		}
		
		@Override
		@SuppressWarnings("rawtypes")
		public void doExplosionB(boolean aEffects) {
			mWorld.playSoundEffect(explosionX, explosionY, explosionZ, "random.explode", 4.0F, (1.0F + (RNGSUS.nextFloat() - RNGSUS.nextFloat()) * 0.2F) * 0.7F);
			if (explosionSize >= 2.0F && isSmoking) {
				mWorld.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1, 0, 0);
			} else {
				mWorld.spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1, 0, 0);
			}
			Iterator iterator;
			ChunkPosition tCoords;
			int i, j, k;
			Block tBlock;
			
			if (isSmoking) {
				iterator = affectedBlockPositions.iterator();
				while (iterator.hasNext()) {
					tCoords = (ChunkPosition)iterator.next();
					i = tCoords.chunkPosX;
					j = tCoords.chunkPosY;
					k = tCoords.chunkPosZ;
					tBlock = mWorld.getBlock(i, j, k);
					
					if (aEffects) {
						double d0 = (i + RNGSUS.nextFloat()), d1 = (j + RNGSUS.nextFloat()), d2 = (k + RNGSUS.nextFloat()), d3 = d0 - explosionX, d4 = d1 - explosionY, d5 = d2 - explosionZ, d6 = Math.sqrt(d3*d3 + d4*d4 + d5*d5);
						d3 /= d6; d4 /= d6; d5 /= d6;
						double d7 = 0.5 / (d6 / explosionSize + 0.1);
						d7 *= (RNGSUS.nextFloat() * RNGSUS.nextFloat() + 0.3);
						d3 *= d7; d4 *= d7; d5 *= d7;
						mWorld.spawnParticle("explode", (d0 + explosionX) / 2, (d1 + explosionY) / 2, (d2 + explosionZ) / 2, d3, d4, d5);
						mWorld.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
					}
					if (tBlock.getMaterial() != Material.air) {
						byte tMeta = WD.meta(mWorld, i, j, k);
						tBlock.onBlockExploded(mWorld, i, j, k, this);
						if (tBlock.canDropFromExplosion(this)) tBlock.dropBlockAsItemWithChance(mWorld, i, j, k, tMeta, 1, mFortune);
					}
				}
			}
		}
	}
}
