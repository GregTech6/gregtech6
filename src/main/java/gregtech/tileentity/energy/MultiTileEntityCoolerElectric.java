package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase11Twotypes;
import net.minecraft.block.Block;

public class MultiTileEntityCoolerElectric extends TileEntityBase11Twotypes implements ITileEntityEnergyElectricityAcceptor {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_electric/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.cooler.cryo_electric";}
}