package gregtech.tileentity.extenders;

import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityExtenderController extends MultiTileEntityExtender implements ITileEntityRunningSuccessfully, ITileEntityProgress, ITileEntitySwitchableMode, ITileEntitySwitchableOnOff {
	@Override public String getTileEntityName() {return "gt.multitileentity.extender.controller";}
}