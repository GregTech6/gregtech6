package gregapi.tileentity;

import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityConnectedTank extends ITileEntityUnloadable {
	/** @return the amount of successfully added Fluid. */
	public int addFluidToConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyAddIfItAlreadyHasFluidsOfThatTypeOrIsDedicated);
	/** @return the amount of successfully removed Fluid. */
	public int removeFluidFromConnectedTank(byte aSide, FluidStack aFluid, boolean aOnlyRemoveIfItCanRemoveAllAtOnce);
	/** @return the amount of counted Fluid. */
	public long getAmountOfFluidInConnectedTank(byte aSide, FluidStack aFluid);
}