package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityChronometer extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.chronometer", "Measures Time of Day");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.chronometer");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		return (((aDelegator.mWorld.getWorldTime()+6000)%24000)*60)/1000;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return 1440;
	}
	
	@Override public short[] getSymbolColor() {return CA_GREEN_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_CLOCK;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/chronometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.chronometer";}
}