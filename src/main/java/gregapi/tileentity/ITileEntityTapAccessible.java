package gregapi.tileentity;

import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityTapAccessible extends ITileEntityUnloadable {
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain);
	public FluidStack nozzleDrain(byte aSide, int aMaxDrain, boolean aDoDrain);
}