/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregapi.tileentity.connectors;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetPlayerRelativeBlockHardness;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFoamable;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase10ConnectorRendered extends TileEntityBase09Connector implements ITileEntityFoamable, IMTE_GetPlayerRelativeBlockHardness, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState {
	public float mDiameter = 1.0F;
	public boolean mTransparent = F, mIsGlowing = F, mContactDamage = F, mFoam = F, mFoamDried = F, mOwnable = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DIAMETER)) mDiameter = Math.max(PX_P[2], Math.min(PX_N[0], (float)aNBT.getDouble(NBT_DIAMETER)));
		if (aNBT.hasKey(NBT_TRANSPARENT)) mTransparent = aNBT.getBoolean(NBT_TRANSPARENT);
		if (aNBT.hasKey(NBT_CONTACTDAMAGE)) mContactDamage = aNBT.getBoolean(NBT_CONTACTDAMAGE);
		if (aNBT.hasKey(NBT_FOAMDRIED)) mFoamDried = aNBT.getBoolean(NBT_FOAMDRIED);
		if (aNBT.hasKey(NBT_FOAMED)) mFoam = aNBT.getBoolean(NBT_FOAMED);
		if (aNBT.hasKey(NBT_OWNABLE)) mOwnable = aNBT.getBoolean(NBT_OWNABLE);
		if (aNBT.hasKey(NBT_OWNER) && !OWNERSHIP_RESET) mOwner = UUID.fromString(aNBT.getString(NBT_OWNER));
		mIsGlowing = mMaterial.contains(TD.Properties.GLOWING);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_FOAMED, mFoam);
		UT.NBT.setBoolean(aNBT, NBT_FOAMDRIED, mFoamDried);
		UT.NBT.setBoolean(aNBT, NBT_OWNABLE, mOwnable);
		if (mOwner != null) aNBT.setString(NBT_OWNER, mOwner.toString());
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_FOAMED, mFoam);
		UT.NBT.setBoolean(aNBT, NBT_FOAMDRIED, mFoamDried);
		UT.NBT.setBoolean(aNBT, NBT_OWNABLE, mOwnable);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (mOwnable) aList.add(Chat.ORANGE + LH.get(LH.OWNER_CONTROLLED));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide && aTimer >= 100 && mFoam && !mFoamDried && rng(5900) == 0) {
			mFoamDried = T;
			updateClientData();
		}
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		if (worldObj == null && !hasCovers()) mConnections = (byte)(SBIT_S|SBIT_N);
		return mFoamDried || mDiameter >= 1.0F ? 1 : mFoam ? 8 : mConnections == 0 ? 1 : 7;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			if (mFoamDried || mDiameter >= 1.0F) return F;
			box(aBlock, (1.0F-mDiameter)/2.0F, (1.0F-mDiameter)/2.0F, (1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F);
			return T;
		}
		// TODO: I need to add the old optimizations back somehow.
		// Even though this Version is way more modular and can adjust to stuff much easier, it does also look bad when rendered with ambient occlusion.
		if (aRenderPass <= 6) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity((byte)(aRenderPass - 1), F, F);
			float tDiameter = getConnectorDiameter((byte)(aRenderPass - 1), tDelegator), tLength = getConnectorLength((byte)(aRenderPass - 1), tDelegator);
			switch(aRenderPass - 1) {
			case SIDE_X_NEG: box(aBlock, 0-tLength                  ,   (1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   ); return T;
			case SIDE_Y_NEG: box(aBlock,   (1.0F-tDiameter)/2.0F    , 0-tLength                 ,   (1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   ); return T;
			case SIDE_Z_NEG: box(aBlock,   (1.0F-tDiameter)/2.0F    ,   (1.0F-tDiameter)/2.0F   , 0-tLength                 , 1-(1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   ); return T;
			case SIDE_X_POS: box(aBlock, 1-(1.0F-tDiameter)/2.0F    ,   (1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   , 1+tLength                 , 1-(1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   ); return T;
			case SIDE_Y_POS: box(aBlock,   (1.0F-tDiameter)/2.0F    , 1-(1.0F-tDiameter)/2.0F   ,   (1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   , 1+tLength                 , 1-(1.0F-tDiameter)/2.0F   ); return T;
			case SIDE_Z_POS: box(aBlock,   (1.0F-tDiameter)/2.0F    ,   (1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   , 1-(1.0F-tDiameter)/2.0F   , 1+tLength                 ); return T;
			}
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 7) return aShouldSideBeRendered[aSide] ? getTextureCFoam(aSide, mConnections, mDiameter, aRenderPass) : null;
		if (aRenderPass == 0) return mFoamDried ? aShouldSideBeRendered[aSide] ? getTextureCFoamDry(aSide, mConnections, mDiameter, aRenderPass) : null : mConnections == 0 || (mDiameter >= 1.0F && connected(aSide)) ? getTextureConnected(aSide, mConnections, mDiameter, aRenderPass) : getTextureSide(aSide, mConnections, mDiameter, aRenderPass);
		return aSide == OPOS[aRenderPass-1] ? null : aSide == aRenderPass-1 ? aShouldSideBeRendered[aSide] ? getTextureConnected(aSide, mConnections, mDiameter, aRenderPass) : null : getTextureSide(aSide, mConnections, mDiameter, aRenderPass);
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return aRenderPass == 0 || aRenderPass == 7 || connected((byte)(aRenderPass-1));}
	
	@Override public int getLightOpacity() {return mFoamDried ? LIGHT_OPACITY_MAX : mTransparent ? mDiameter >= 1.0F ? LIGHT_OPACITY_WATER : mDiameter > 0.5F ? LIGHT_OPACITY_LEAVES : LIGHT_OPACITY_NONE : mDiameter >= 1.0F ? LIGHT_OPACITY_MAX : mDiameter > 0.5F ? LIGHT_OPACITY_WATER : LIGHT_OPACITY_LEAVES;}
	@Override public boolean ignorePlayerCollisionWhenPlacing(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return !mFoam && mDiameter < 1.0F;}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (mOwnable && aPlayer != null && !OWNERSHIP_RESET) mOwner = aPlayer.getUniqueID();
		return super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		return !mOwnable || !mFoamDried || super.allowInteraction(aEntity);
	}
	
	@Override
	public boolean applyFoam(byte aSide, Entity aPlayer, short[] aCFoamRGB, byte aVanillaColor, boolean aOwned) {
		if (mDiameter >= 1.0F || mFoam || mFoamDried || isClientSide() || !allowInteraction(aPlayer)) return F;
		mFoam = T; mFoamDried = F; mIsPainted = T; mOwnable = aOwned;
		if (mOwnable && aPlayer != null && !OWNERSHIP_RESET) mOwner = aPlayer.getUniqueID();
		mRGBa = UT.Code.getRGBInt(aCFoamRGB);
		updateClientData();
		return T;
	}
	
	@Override
	public boolean dryFoam(byte aSide, Entity aPlayer) {
		if (!mFoam || mFoamDried || isClientSide()) return F;
		mFoam = T; mFoamDried = T;
		updateClientData();
		return T;
	}
	
	@Override
	public boolean removeFoam(byte aSide, Entity aPlayer) {
		if (!mFoam || !mFoamDried || isClientSide() || !allowInteraction(aPlayer)) return F;
		mFoam = F; mFoamDried = F; mOwnable = F; mOwner = null;
		unpaint();
		updateClientData();
		return T;
	}
	
	@Override public float getExplosionResistance2() {return Math.max(mFoam ? (mFoamDried?BlocksGT.CFoam:BlocksGT.CFoamFresh).getExplosionResistance(null) : 0, super.getExplosionResistance2());}
	
	@Override
	public byte getDirectionData() {
		return (byte)(((byte)(mConnections & 63)) | ((byte)((mFoamDried ? mOwnable : mFoam) ? 64 : 0)) | ((byte)(mFoamDried ? 128 : 0)));
	}
	
	@Override
	public void setDirectionData(byte aData) {
		mConnections = (byte)(aData & 63);
		mFoamDried = ((aData & (byte)128) != 0);
		if (mFoamDried) {
			mOwnable = ((aData & 64) != 0);
			mFoam = T;
		} else {
			mOwnable = (mOwner != null);
			mFoam = ((aData & 64) != 0);
		}
	}
	
	@Override public int getFireSpreadSpeed         (byte aSide, boolean aDefault) {return mFoam ? 0 : super.getFireSpreadSpeed(aSide, aDefault);}
	@Override public int getFlammability            (byte aSide, boolean aDefault) {return mFoam ? 0 : super.getFlammability   (aSide, aDefault);}
	@Override public float getSurfaceSize           (byte aSide) {return mFoamDried ? 1.0F : mDiameter;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return mDiameter;}
	@Override public float getSurfaceDistance       (byte aSide) {return mFoamDried || connected(aSide)?0.0F:(1.0F-mDiameter)/2.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return mFoamDried ||  mDiameter == 1.0F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return mFoamDried || (mDiameter == 1.0F && !mTransparent);}
	@Override public boolean isSideSolid2           (byte aSide) {return mFoamDried ||  mDiameter == 1.0F;}
	@Override public boolean isSealable2            (byte aSide) {return mFoamDried;}
	@Override public boolean usePipePlacementMode   (byte aSide) {return T;}
	@Override public boolean hasFoam                (byte aSide) {return mFoam;}
	@Override public boolean driedFoam              (byte aSide) {return mFoam && mFoamDried;}
	@Override public boolean ownedFoam              (byte aSide) {return mFoam && mOwnable;}
	@Override public boolean addDefaultCollisionBoxToList() {return mDiameter >= 1.0F || mFoamDried;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return mContactDamage && !mFoamDried ? box(PX_P[2], PX_P[2], PX_P[2], PX_N[2], PX_N[2], PX_N[2]) : super.getCollisionBoundingBoxFromPool();}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		if (!addDefaultCollisionBoxToList()) {
			byte tSide;                                                                                                                                                                                                              box(aAABB, aList,   (1.0F-mDiameter)/2.0F,   (1.0F-mDiameter)/2.0F,   (1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F, 1-(1.0F-mDiameter)/2.0F);
			if (connected(tSide = SIDE_X_NEG)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList, 0-tLength              ,   (1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F);}
			if (connected(tSide = SIDE_Y_NEG)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList,   (1.0F-tDiameter)/2.0F, 0-tLength              ,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F);}
			if (connected(tSide = SIDE_Z_NEG)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList,   (1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 0-tLength              , 1-(1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F);}
			if (connected(tSide = SIDE_X_POS)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList, 1-(1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 1+tLength              , 1-(1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F);}
			if (connected(tSide = SIDE_Y_POS)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F, 1+tLength              , 1-(1.0F-tDiameter)/2.0F);}
			if (connected(tSide = SIDE_Z_POS)) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide, F, F); float tDiameter = getConnectorDiameter(tSide, tDelegator), tLength = mContactDamage ? -PX_P[2] : 0; box(aAABB, aList,   (1.0F-tDiameter)/2.0F,   (1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F, 1-(1.0F-tDiameter)/2.0F, 1+tLength              );}
		}
	}
	
	@Override
	public float[] shrunkBox() {
		if (mFoam || mDiameter >= 1.0F || hasCovers()) return PX_BOX;
		float tDiameter = (1.0F-mDiameter)/2.0F;
		return new float[] {connected(SIDE_X_NEG) ? 0 : tDiameter, connected(SIDE_Y_NEG) ? 0 : tDiameter, connected(SIDE_Z_NEG) ? 0 : tDiameter, connected(SIDE_X_POS) ? 1 : 1-tDiameter, connected(SIDE_Y_POS) ? 1 : 1-tDiameter, connected(SIDE_Z_POS) ? 1 : 1-tDiameter};
	}
	
	public float getConnectorLength(byte aConnectorSide, DelegatorTileEntity<TileEntity> aDelegator) {
		float rLength = (mDiameter != 1.0F && hasCovers() && mCovers.mBehaviours[aConnectorSide] != null ? mCovers.mBehaviours[aConnectorSide].showsConnectorFront(aConnectorSide, mCovers) ? +0.001F : -0.001F : 0);
		if (aDelegator.mTileEntity instanceof ITileEntitySurface) {
			float tDistance = ((ITileEntitySurface)aDelegator.mTileEntity).getSurfaceDistance(aDelegator.mSideOfTileEntity);
			if (tDistance > 0) return Math.max(rLength, tDistance);
		}
		// TODO check for regular Collision Box.
		return rLength;
	}
	
	public float getConnectorDiameter(byte aConnectorSide, DelegatorTileEntity<TileEntity> aDelegator) {
		float rDiameter = mDiameter;
		if (aDelegator.mTileEntity instanceof ITileEntitySurface) rDiameter = ((ITileEntitySurface)aDelegator.mTileEntity).getSurfaceSizeAttachable(aDelegator.mSideOfTileEntity);
		// Connect with normal Size when the target cannot connect and ensure that minimum and maximum size are properly done.
		if (rDiameter <= 0.0F || rDiameter > mDiameter) rDiameter = mDiameter; else if (rDiameter < PX_P[2]) rDiameter = PX_P[2];
		return rDiameter;
	}
	
	public ITexture getTextureSide      (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mMaterial, getIconIndexSide       (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa);}
	public ITexture getTextureConnected (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mMaterial, getIconIndexConnected  (aSide, aConnections, aDiameter, aRenderPass), mIsGlowing, mRGBa);}
	public ITexture getTextureCFoam     (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mOwnable?Textures.BlockIcons.CFOAM_FRESH_OWNED:Textures.BlockIcons.CFOAM_FRESH, mRGBa);}
	public ITexture getTextureCFoamDry  (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(mOwnable?Textures.BlockIcons.CFOAM_HARDENED_OWNED:Textures.BlockIcons.CFOAM_HARDENED, mRGBa);}
	
	public int getIconIndexSide         (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return IconsGT.INDEX_BLOCK_PIPE_SIDE;}
	public int getIconIndexConnected    (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return aDiameter<0.37F?OP.pipeTiny.mIconIndexBlock:aDiameter<0.49F?OP.pipeSmall.mIconIndexBlock:aDiameter<0.74F?OP.pipeMedium.mIconIndexBlock:aDiameter<0.99F?OP.pipeLarge.mIconIndexBlock:OP.pipeHuge.mIconIndexBlock;}
}
