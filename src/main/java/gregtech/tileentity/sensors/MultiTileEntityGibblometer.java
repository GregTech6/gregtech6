package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGibblometer extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.gibblometer", "Measures Compression (In Gibbl)");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.gibblometer");}
	
	@Override public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {if (aDelegator.mTileEntity instanceof ITileEntityGibbl) return ((ITileEntityGibbl)aDelegator.mTileEntity).getGibblValue(aDelegator.mSideOfTileEntity) / 1000; return 0;}
	@Override public long getCurrentMax  (DelegatorTileEntity<TileEntity> aDelegator) {if (aDelegator.mTileEntity instanceof ITileEntityGibbl) return ((ITileEntityGibbl)aDelegator.mTileEntity).getGibblMax  (aDelegator.mSideOfTileEntity) / 1000; return 0;}
	
	@Override public short[] getSymbolColor() {return CA_YELLOW_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_GIBBL;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/gibblometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.gibblometer";}
}