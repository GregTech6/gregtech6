package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import gregapi.util.WD;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorChamber;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityThermometer extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.thermometer", "Measures Temperature (In Kelvin)");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.thermometer");}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityTemperature) return ((ITileEntityTemperature)aDelegator.mTileEntity).getTemperatureValue(aDelegator.mSideOfTileEntity);
		try {
			if (aDelegator.mTileEntity instanceof IReactor) return ((IReactor)aDelegator.mTileEntity).getHeat() / 5;
			if (aDelegator.mTileEntity instanceof IReactorChamber) {
				IReactor tReactor = ((IReactorChamber)aDelegator.mTileEntity).getReactor();
				return tReactor == null ? 0 : tReactor.getHeat() / 5;
			}
		} catch(Throwable e) {/**/}
		return WD.temperature(aDelegator.mWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ);
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		if (aDelegator.mTileEntity instanceof ITileEntityTemperature) return ((ITileEntityTemperature)aDelegator.mTileEntity).getTemperatureMax  (aDelegator.mSideOfTileEntity);
		try {
			if (aDelegator.mTileEntity instanceof IReactor) return ((IReactor)aDelegator.mTileEntity).getMaxHeat() / 5;
			if (aDelegator.mTileEntity instanceof IReactorChamber) {
				IReactor tReactor = ((IReactorChamber)aDelegator.mTileEntity).getReactor();
				return tReactor == null ? 0 : tReactor.getMaxHeat() / 5;
			}
		} catch(Throwable e) {/**/}
		return 0;
	}
	
	@Override public short[] getSymbolColor() {return CA_RED_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_KELVIN;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/thermometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.thermometer";}
}