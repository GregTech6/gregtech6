package gregtech.tileentity.sensors;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.tileentity.data.ITileEntityWeight;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntitySensorTE;
import gregapi.util.OM;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWeightometerLight extends MultiTileEntitySensorTE {
	static {LH.add("gt.tooltip.sensor.lightweightometer", "Measures the weight of an Inventory (in Gramm)");}
	@Override public String getSensorDescription() {return LH.get("gt.tooltip.sensor.lightweightometer");}
	
	public static final double MAX_WEIGHT = (B[16]-1)/1000.0;
	
	@Override
	public long getCurrentValue(DelegatorTileEntity<TileEntity> aDelegator) {
		double rWeightKG = 0;
		if (aDelegator.mTileEntity instanceof ITileEntityWeight) {
			rWeightKG = ((ITileEntityWeight)aDelegator.mTileEntity).getWeightValue(aDelegator.mSideOfTileEntity);
		} else if (aDelegator.mTileEntity instanceof IInventory) {
			if (aDelegator.mTileEntity instanceof ISidedInventory) {
				for (int i : ((ISidedInventory)aDelegator.mTileEntity).getAccessibleSlotsFromSide(aDelegator.mSideOfTileEntity)) {
					rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
					if (rWeightKG >= MAX_WEIGHT) break;
				}
			} else for (int i = 0, j = ((IInventory)aDelegator.mTileEntity).getSizeInventory(); i < j; i++) {
				rWeightKG += OM.weight(((IInventory)aDelegator.mTileEntity).getStackInSlot(i));
				if (rWeightKG >= MAX_WEIGHT) break;
			}
		}
		if (rWeightKG >= MAX_WEIGHT) rWeightKG = MAX_WEIGHT;
		return (long)(rWeightKG*1000);
	}
	
	@Override
	public long getCurrentMax(DelegatorTileEntity<TileEntity> aDelegator) {
		return B[16]-1;
	}
	
	@Override public short[] getSymbolColor() {return CA_GRAY_192;}
	@Override public IIconContainer getSymbolIcon() {return BI.CHAR_GRAMM;}
	@Override public IIconContainer getTextureFront() {return sTextureFront;}
	@Override public IIconContainer getTextureBack () {return sTextureBack;}
	@Override public IIconContainer getTextureSide () {return sTextureSide;}
	@Override public IIconContainer getOverlayFront() {return sOverlayFront;}
	@Override public IIconContainer getOverlayBack () {return sOverlayBack;}
	@Override public IIconContainer getOverlaySide () {return sOverlaySide;}
	
	public static IIconContainer
	sTextureFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/colored/front"),
	sTextureBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/colored/back"),
	sTextureSide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/colored/side"),
	sOverlayFront	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/overlay/front"),
	sOverlayBack	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/overlay/back"),
	sOverlaySide	= new Textures.BlockIcons.CustomIcon("machines/redstone/sensors/lightweightometer/overlay/side");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.sensors.lightweightometer";}
}