package gregapi.tileentity;

import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityFunnelAccessible extends ITileEntityUnloadable {
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill);
}