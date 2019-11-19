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

package gregtech.tileentity.energy.generators;

import static gregapi.data.CS.*;

import gregapi.data.FL;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorGas extends MultiTileEntityGeneratorLiquid implements ITileEntityAdjacentOnOff {
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) && FL.gas(aFluidToFill) ? mTank : null;
	}
	
	@Override public boolean setAdjacentOnOff(boolean aOnOff) {if (mBurning && !aOnOff) {mBurning = F; mCooldown = 0;} return mBurning;}
	@Override public boolean setStateOnOff(boolean aOnOff) {if (mBurning && !aOnOff) {mBurning = F; mCooldown = 0;} return mBurning;}
	@Override public boolean getStateOnOff() {return mBurning;}
	
	@Override
	protected void spawnBurningParticles(double aX, double aY, double aZ) {
		//
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	@SuppressWarnings("hiding")
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_gas/overlay_active/back")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.burning_gas";}
}
