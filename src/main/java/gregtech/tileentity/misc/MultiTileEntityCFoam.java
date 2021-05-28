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
import java.util.UUID;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetPlayerRelativeBlockHardness;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnRegistration;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFoamable;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCFoam extends TileEntityBase07Paintable implements ITileEntityFoamable, IMTE_GetPlayerRelativeBlockHardness, IMTE_AddToolTips, IMTE_OnPlaced, IMTE_OnRegistration {
	public boolean mFoamDried = F, mOwnable = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_FOAMDRIED)) mFoamDried = aNBT.getBoolean(NBT_FOAMDRIED);
		if (aNBT.hasKey(NBT_OWNABLE)) mOwnable = aNBT.getBoolean(NBT_OWNABLE);
		if (aNBT.hasKey(NBT_OWNER) && !OWNERSHIP_RESET) mOwner = UUID.fromString(aNBT.getString(NBT_OWNER));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_FOAMDRIED, mFoamDried);
		UT.NBT.setBoolean(aNBT, NBT_OWNABLE, mOwnable);
		if (mOwner != null) aNBT.setString(NBT_OWNER, mOwner.toString());
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_FOAMDRIED, mFoamDried);
		UT.NBT.setBoolean(aNBT, NBT_OWNABLE, mOwnable);
		return super.writeItemNBT2(aNBT);
	}
	
	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityCFoam INSTANCE;
	
	public static boolean setBlock(World aWorld, int aX, int aY, int aZ, byte aSide, EntityPlayer aPlayer, ItemStack aStack, short[] aRGB, boolean aOwned) {
		return MTE_REGISTRY.mBlock.placeBlock(aWorld, aX, aY, aZ, aSide, INSTANCE.getMultiTileEntityID(), UT.NBT.make(NBT_COLOR, UT.Code.getRGBInt(aRGB), NBT_PAINTED, T, NBT_OWNABLE, aOwned, NBT_OWNER, aOwned && aPlayer != null ? aPlayer.getUniqueID().toString() : null), T, F);
	}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (mOwnable) aList.add(Chat.ORANGE + LH.get(LH.OWNER_CONTROLLED));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide && aTimer >= 100 && !mFoamDried && rng(5900) == 0) {
			mFoamDried = T;
			updateClientData();
		}
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (mOwnable && aPlayer != null && !OWNERSHIP_RESET) mOwner = aPlayer.getUniqueID();
		return T;
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		return !mOwnable || !mFoamDried || super.allowInteraction(aEntity);
	}
	
	@Override
	public boolean dryFoam(byte aSide, Entity aPlayer) {
		if (mFoamDried || isClientSide()) return F;
		mFoamDried = T;
		updateClientData();
		return T;
	}
	
	@Override
	public boolean removeFoam(byte aSide, Entity aPlayer) {
		if (isClientSide() || !allowInteraction(aPlayer)) return F;
		worldObj.setBlock(xCoord, yCoord, zCoord, NB, 0, 3);
		return T;
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureDefault.get(mFoamDried?mOwnable?Textures.BlockIcons.CFOAM_HARDENED_OWNED:Textures.BlockIcons.CFOAM_HARDENED:mOwnable?Textures.BlockIcons.CFOAM_FRESH_OWNED:Textures.BlockIcons.CFOAM_FRESH, mRGBa) : null;}
	
	@Override public int getLightOpacity() {return mFoamDried ? LIGHT_OPACITY_MAX : LIGHT_OPACITY_WATER;}
	
	@Override public float getBlockHardness()        {return (mFoamDried?BlocksGT.CFoam:BlocksGT.CFoamFresh).getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return (mFoamDried?BlocksGT.CFoam:BlocksGT.CFoamFresh).getExplosionResistance(null);}
	
	@Override public byte getVisualData() {return (byte)((mFoamDried ? 1 : 0)|(mOwnable ? 2 : 0));}
	@Override public void setVisualData(byte aData) {mFoamDried = ((aData & 1) != 0); mOwnable = ((aData & 2) != 0);}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return mFoamDried;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return mFoamDried;}
	@Override public boolean isSideSolid2           (byte aSide) {return mFoamDried;}
	@Override public boolean isSealable2            (byte aSide) {return mFoamDried;}
	@Override public boolean hasFoam                (byte aSide) {return T;}
	@Override public boolean driedFoam              (byte aSide) {return mFoamDried;}
	@Override public boolean ownedFoam              (byte aSide) {return mOwnable;}
	@Override public boolean applyFoam              (byte aSide, Entity aPlayer, short[] aCFoamRGB, byte aVanillaColor, boolean aOwnable) {return F;}
	@Override public boolean addDefaultCollisionBoxToList() {return T;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean showInCreative() {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.cfoam.scaffold.wood";}
}
