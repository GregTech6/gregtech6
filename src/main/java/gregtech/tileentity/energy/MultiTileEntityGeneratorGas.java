package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorGas extends MultiTileEntityGeneratorLiquid implements ITileEntityAdjacentOnOff {
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		return mRecipes.containsInput(aFluidToFill, this, NI) && UT.Fluids.gas(aFluidToFill) ? mTank : null;
	}
	
	@Override public boolean setAdjacentOnOff(boolean aOnOff) {if (mBurning && !aOnOff) mBurning = F; return mBurning;}
	@Override public boolean setStateOnOff(boolean aOnOff) {if (mBurning && !aOnOff) mBurning = F; return mBurning;}
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