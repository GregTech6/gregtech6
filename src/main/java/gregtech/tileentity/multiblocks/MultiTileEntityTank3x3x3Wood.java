package gregtech.tileentity.multiblocks;

import gregapi.util.UT;
import net.minecraftforge.fluids.FluidStack;


/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityTank3x3x3Wood extends MultiTileEntityTank3x3x3 {
	@Override
	public boolean allowFluid(FluidStack aFluid) {
		return super.allowFluid(aFluid) && UT.Fluids.simple(aFluid);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.tank.wooden";}
}