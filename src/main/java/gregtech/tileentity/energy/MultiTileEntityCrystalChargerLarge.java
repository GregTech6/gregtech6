package gregtech.tileentity.energy;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import net.minecraft.block.Block;

public class MultiTileEntityCrystalChargerLarge extends MultiTileEntityCrystalCharger {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?0:1], mRGBa), BlockTextureDefault.get(sOverlays[mActiveState][aSide==mFacing?0:1]));
	}
	
	// Icons
	private static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_blinking/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.energystorages.crystal_laser_large";}
}