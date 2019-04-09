/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.block.fluid;

import static gregapi.data.CS.*;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.LH;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class BlockBaseFluid extends BlockFluidFinite {
	public final String mNameInternal;
	public final int mFlammability;
	public final Fluid mFluid;
	
	public BlockBaseFluid(String aNameInternal, Fluid aFluid, int aFlammability) {
		this(aNameInternal, aFluid, aFlammability, /*aFluid.isGaseous()?Material.air:*/aFluid.getTemperature()>500?Material.lava:Material.water);
	}
	
	public BlockBaseFluid(String aNameInternal, Fluid aFluid, int aFlammability, Material aMaterial) {
		super(aFluid, aMaterial);
		mFluid = aFluid;
		setResistance(30);
		mFlammability = aFlammability;
		setBlockName(mNameInternal = aNameInternal);
		GameRegistry.registerBlock(this, ItemBlock.class, mNameInternal);
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(this);
		LH.add(getLocalizedName()+".name", getLocalizedName()); // WAILA is retarded...
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		// Forge royally fucked up again. You check for MetaData FIRST and do the set Block to Air SECOND, like I demonstrate here!!!
		FluidStack rFluid = UT.Fluids.make(getFluid(), (aWorld.getBlockMetadata(aX, aY, aZ) + 1) * 125);
		if (aDoDrain) {
			aWorld.setBlockToAir(aX, aY, aZ);
			updateFluidBlocks(aWorld, aX, aY, aZ);
		}
		return rFluid;
	}
	
	public void updateFluidBlocks(World aWorld, int aX, int aY, int aZ) {
		for (int j = densityDir > 0 ? -1 : 0; j < (densityDir > 0 ? 1 : 2); j++) if (UT.Code.inside(0, aWorld.getHeight(), aY+j)) for (int i = -4; i < 5; i++) for (int k = -4; k < 5; k++) if (i != 0 || j != 0 || k != 0) {
			if (aWorld.getBlock(aX+i, aY+j, aZ+k) == this && aWorld.getBlockMetadata(aX+i, aY+j, aZ+k) > (j == 0 ? Math.abs(i)+Math.abs(j) : 0)) {
				aWorld.scheduleBlockUpdate(aX+i, aY+j, aZ+k, this, tickRate);
			}
		}
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		boolean changed = F;
		int quantaRemaining = aWorld.getBlockMetadata(aX, aY, aZ)+1;
		
		int prevRemaining = quantaRemaining;
		quantaRemaining = tryToFlowVerticallyInto(aWorld, aX, aY, aZ, quantaRemaining);
		
		if (quantaRemaining < 1) {
			updateFluidBlocks(aWorld, aX, aY, aZ);
			return;
		}
		if (quantaRemaining != prevRemaining) {
			changed = T;
			if (quantaRemaining == 1) {
				WD.setIfDiff(aWorld, aX, aY, aZ, this, quantaRemaining-1, 2);
				updateFluidBlocks(aWorld, aX, aY, aZ);
				return;
			}
		} else if (quantaRemaining == 1) {
			updateFluidBlocks(aWorld, aX, aY, aZ);
			return;
		}
		
		if (displaceIfPossible(aWorld, aX  , aY, aZ-1)) aWorld.setBlockToAir(aX  , aY, aZ-1);
		if (displaceIfPossible(aWorld, aX  , aY, aZ+1)) aWorld.setBlockToAir(aX  , aY, aZ+1);
		if (displaceIfPossible(aWorld, aX-1, aY, aZ  )) aWorld.setBlockToAir(aX-1, aY, aZ  );
		if (displaceIfPossible(aWorld, aX+1, aY, aZ  )) aWorld.setBlockToAir(aX+1, aY, aZ  );
		
		int tTotal = quantaRemaining, tCount = 1;
		
		int north = getQuantaValueBelow(aWorld, aX  , aY, aZ-1, quantaRemaining-1);
		int south = getQuantaValueBelow(aWorld, aX  , aY, aZ+1, quantaRemaining-1);
		int west  = getQuantaValueBelow(aWorld, aX-1, aY, aZ  , quantaRemaining-1);
		int east  = getQuantaValueBelow(aWorld, aX+1, aY, aZ  , quantaRemaining-1);
		
		if (north >= 0) {tCount++; tTotal += north;}
		if (south >= 0) {tCount++; tTotal += south;}
		if (west  >= 0) {tCount++; tTotal += west ;}
		if (east  >= 0) {tCount++; tTotal += east ;}
		
		if (tCount == 1) {
			if (changed) {
				WD.setIfDiff(aWorld, aX, aY, aZ, this, quantaRemaining-1, 2);
				updateFluidBlocks(aWorld, aX, aY, aZ);
			}
			return;
		}
		
		int tSpread = tTotal / tCount, tRemainder = tTotal % tCount;
		if (north >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew; --tRemainder;} tCount--;
			if (tNew != north) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX  , aY, aZ-1, this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX  , aY, aZ-1, this, tickRate);} else aWorld.setBlockToAir(aX  , aY, aZ-1);
		}
		if (south >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew; --tRemainder;} tCount--;
			if (tNew != south) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX  , aY, aZ+1, this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX  , aY, aZ+1, this, tickRate);} else aWorld.setBlockToAir(aX  , aY, aZ+1);
		}
		if (west >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew ; --tRemainder;} tCount--;
			if (tNew != west ) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX-1, aY, aZ  , this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX-1, aY, aZ  , this, tickRate);} else aWorld.setBlockToAir(aX-1, aY, aZ  );
		}
		if (east >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew ; --tRemainder;} tCount--;
			if (tNew != east ) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX+1, aY, aZ  , this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX+1, aY, aZ  , this, tickRate);} else aWorld.setBlockToAir(aX+1, aY, aZ  );
		}
		WD.setIfDiff(aWorld, aX, aY, aZ, this, tRemainder > 0 ? tSpread : tSpread - 1, 2);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return T;
		if (aBlock == this || aBlock.getMaterial() == Material.water || WD.visOpq(aBlock)) return F;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		return !(tTileEntity instanceof ITileEntitySurface && !((ITileEntitySurface)tTileEntity).isSurfaceOpaque(OPPOSITES[aSide]));
	}
	
	@Override public final String getUnlocalizedName() {return mFluid.getUnlocalizedName();}
	@Override public String getLocalizedName() {return LH.get(mFluid.getUnlocalizedName());}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return SIDES_VERTICAL[aSide]?mFluid.getStillIcon():mFluid.getFlowingIcon();}
	@Override @SideOnly(Side.CLIENT) public int getRenderColor(int aMeta) {return mFluid.getColor();}
	@Override @SideOnly(Side.CLIENT) public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {return mFluid.getColor();}
	@Override public boolean isAir(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean canDisplace(IBlockAccess aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.canDisplace(aWorld, aX, aY, aZ);}
	@Override public boolean displaceIfPossible(World aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.displaceIfPossible(aWorld, aX, aY, aZ);}
	@Override public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return mFlammability;}
	@Override public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return mFlammability;}
}
