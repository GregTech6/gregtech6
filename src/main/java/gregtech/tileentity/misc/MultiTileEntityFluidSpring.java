/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.FL;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase04MultiTileEntities;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityFluidSpring extends TileEntityBase04MultiTileEntities implements IMTE_OnRegistration, ITileEntitySurface, IMTE_IsSideSolid, IMTE_GetExplosionResistance, IMTE_GetBlockHardness, IMTE_GetLightOpacity, IMTE_SyncDataShort {
	public FluidStack mFluid = FL.Water.make(1);
	public boolean mActive = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.spring")) mFluid = FL.load(aNBT, "gt.spring");
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		FL.save(aNBT, "gt.spring", mFluid);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
	}
	
	@Override
	public final NBTTagCompound writeItemNBT(NBTTagCompound aNBT) {
		aNBT = super.writeItemNBT(aNBT);
		FL.save(aNBT, "gt.spring", mFluid);
		return aNBT;
	}
	
	public static MultiTileEntityRegistry MTE_REGISTRY = null;
	public static MultiTileEntityFluidSpring INSTANCE;
	
	public static boolean setBlock(World aWorld, int aX, int aY, int aZ, FluidStack aSpring) {
		return MTE_REGISTRY.mBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_UP, INSTANCE.getMultiTileEntityID(), UT.NBT.make("gt.spring", aSpring), T, F);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return getClientDataPacketShort(aSendAll, (short)mFluid.getFluid().getID());
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mFluid = FL.make(FL.fluid(aData), 600);
		return T;
	}
	
	@Override
	public void onRegistration(MultiTileEntityRegistry aRegistry, short aID) {
		INSTANCE = this;
		MTE_REGISTRY = aRegistry;
	}
	
	@Override
	public void onTick(long aTimer, boolean aIsServerSide) {
		super.onTick(aTimer, aIsServerSide);
		if (mFluid.amount <= 0) mFluid.amount = 600;
		if (aIsServerSide) {
			boolean tProduce = F;
			if (mActive) {
				tProduce = (rng(mFluid.amount) == 0);
			} else if (SERVER_TIME % 20 == 1 && !WD.liquid(getBlockAtSide(SIDE_UP))) {
				tProduce = mActive = T;
			}
			if (tProduce) {
				Block tBlock = FL.BLOCKS.get(mFluid.getFluid().getName()), tAbove = getBlockAtSide(SIDE_UP);
				if (ST.invalid(tBlock)) tBlock = mFluid.getFluid().getBlock();
				if (ST.valid(tBlock)) {
					if (tBlock instanceof BlockFluidFinite) {
						if (tAbove == tBlock) {
							worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, UT.Code.bind4(getMetaDataAtSide(SIDE_UP)+8), 3);
							tBlock.updateTick(worldObj, xCoord, yCoord+1, zCoord, RNGSUS);
						} else if (WD.liquid(tAbove) || tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
							worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 7, 3);
							tBlock.updateTick(worldObj, xCoord, yCoord+1, zCoord, RNGSUS);
						}
					} else {
						if (tAbove == tBlock) {
							if (getMetaDataAtSide(SIDE_UP) == 0) {
								for (byte tSide : ALL_SIDES_HORIZONTAL) {
									tAbove = getBlock(xCoord+OFFX[tSide], yCoord+1, zCoord+OFFZ[tSide]);
									if (tAbove == tBlock) {
										if (0 != getMetaData(xCoord+OFFX[tSide], yCoord+1, zCoord+OFFZ[tSide])) {
											worldObj.setBlock(xCoord+OFFX[tSide], yCoord+1, zCoord+OFFZ[tSide], tBlock, 0, 3);
											break;
										}
									} else if (tAbove.isAir(worldObj, xCoord+OFFX[tSide], yCoord+1, zCoord+OFFZ[tSide])) {
										worldObj.setBlock(xCoord+OFFX[tSide], yCoord+1, zCoord+OFFZ[tSide], tBlock, 0, 3);
										break;
									}
								}
							} else {
								worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
							}
						} else if (WD.liquid(tAbove) || tAbove.isAir(worldObj, xCoord, yCoord+1, zCoord)) {
							worldObj.setBlock(xCoord, yCoord+1, zCoord, tBlock, 0, 3);
						}
					}
				}
			}
		}
	}
	
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureFluid.get(mFluid), BlockTextureDefault.get(Textures.BlockIcons.FLUID_SPRING)) : null;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_MAX;}
	
	@Override public float getExplosionResistance2() {return Blocks.bedrock.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return -1;}
	
	@Override public boolean isSurfaceSolid         (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque        (byte aSide) {return T;}
	@Override public boolean isSideSolid            (byte aSide) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.fluid.spring";}
}
