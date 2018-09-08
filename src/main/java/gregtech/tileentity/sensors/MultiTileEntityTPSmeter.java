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
public class MultiTileEntityTPSmeter extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.tpsmeter", "Measures TPS of the whole Server");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.tpsmeter");}
	
	public long mTime = System.currentTimeMillis(), mCurrentTime = 2000;
	
	@Override
    public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && (getTickRate() < 2 || aTimer % getTickRate() == 0)) {
			long tTime = mTime;
			mTime = System.currentTimeMillis();
			mCurrentTime = (mTime - tTime > 0 ? (getTickRate() * 100000) / (mTime - tTime) : 2000);
		}
    	super.onTick2(aTimer, aIsServerSide);
	}
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		return mCurrentTime;
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return 2000;
	}
	
	@Override
	public long getTickRate() {
		return 20;
	}
	
	@Override public short[] getSymbolColor() {return CA_RED_255;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_CLOCK;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/tpsmeter/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.tpsmeter";}
}