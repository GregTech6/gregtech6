package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureFluid;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase10FluidContainerSyncSmall;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMeasuringPot extends TileEntityBase10FluidContainerSyncSmall {
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return mTank.getFluidAmount() <= 0 ? 5 : 6;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: box(aBlock, PX_P[ 4], PX_P[ 1], PX_P[ 5], PX_N[11], PX_N[ 8], PX_N[ 5]); return T;
		case  1: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[ 4], PX_N[ 5], PX_N[ 8], PX_N[11]); return T;
		case  2: box(aBlock, PX_P[11], PX_P[ 1], PX_P[ 5], PX_N[ 4], PX_N[ 8], PX_N[ 5]); return T;
		case  3: box(aBlock, PX_P[ 5], PX_P[ 1], PX_P[11], PX_N[ 5], PX_N[ 8], PX_N[ 4]); return T;
		case  4: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[15], PX_N[ 5]); return T;
		case  5: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 5], PX_N[ 5], PX_N[ 9], PX_N[ 5]); return T;
		}
		return F;
	}
	
	public static IIconContainer
	sTextureSides		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/sides"),
	sTextureInsides		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/insides"),
	sTextureTop			= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/top"),
	sTextureBottom		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/colored/bottom"),
	sOverlaySides		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/sides"),
	sOverlayInsides		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/insides"),
	sOverlayTop			= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/top"),
	sOverlayBottom		= new Textures.BlockIcons.CustomIcon("machines/tanks/measuring_pot/overlay/bottom");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aSide) {
		case SIDE_X_NEG: return aRenderPass == 2 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		case SIDE_X_POS: return aRenderPass == 0 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_NEG: return aRenderPass == 3 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Z_POS: return aRenderPass == 1 ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides, mRGBa), BlockTextureDefault.get(sOverlaySides));
		case SIDE_Y_NEG: return aRenderPass != 4 || aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom)) : null;
		case SIDE_Y_POS: return aRenderPass == 5 ? BlockTextureFluid.get(mTank) : BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop));
		}
		return null;
	}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);}
	
	@Override public float getSurfaceDistance(byte aSide) {return SIDES_VERTICAL[aSide]?0.0F:PX_P[ 4];}
	
	@Override public boolean canWaterCrops() {return T;}
	@Override public boolean canPickUpFluids() {return T;}
	@Override public boolean canFillWithRain() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.measuring.pot";}
}