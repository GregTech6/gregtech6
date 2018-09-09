package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi.tileentity.energy.TileEntityBase11Motor;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;

public class MultiTileEntityMotorFlux extends TileEntityBase11Motor implements ITileEntityEnergyFluxHandler {
	@Override public void onWalkOver2(EntityLivingBase aEntity) {if (SIDES_TOP[mFacing] && mActivity.mState>0) {aEntity.rotationYaw=aEntity.rotationYaw+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1); aEntity.rotationYawHead=aEntity.rotationYawHead+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1);}}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?mCounterClockwise?(mConverter.mFast?sOverlaysActiveLF:sOverlaysActiveLS):(mConverter.mFast?sOverlaysActiveRF:sOverlaysActiveRS):sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay/side"),
	}, sOverlaysActiveLS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_ls/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_ls/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_ls/side"),
	}, sOverlaysActiveLF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_lf/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_lf/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_lf/side"),
	}, sOverlaysActiveRS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rs/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rs/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rs/side"),
	}, sOverlaysActiveRF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rf/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rf/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_flux/overlay_active_rf/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.motors.rotation_flux";}
}