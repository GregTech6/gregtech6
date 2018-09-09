package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase08Barrel;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBarrelPlastic extends TileEntityBase08Barrel {
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.tank.barrel.plastic";}
}