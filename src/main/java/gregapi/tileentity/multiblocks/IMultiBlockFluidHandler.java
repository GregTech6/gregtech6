package gregapi.tileentity.multiblocks;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockFluidHandler extends ITileEntityMultiBlockController {
	public int fill						(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluid, boolean aDoFill);
	public FluidStack drain				(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluid, boolean aDoDrain);
	public FluidStack drain				(MultiTileEntityMultiBlockPart aPart, byte aSide, int aAmountToDrain, boolean aDoDrain);
	public boolean canFill				(MultiTileEntityMultiBlockPart aPart, byte aSide, Fluid aFluid);
	public boolean canDrain				(MultiTileEntityMultiBlockPart aPart, byte aSide, Fluid aFluid);
	public FluidTankInfo[] getTankInfo	(MultiTileEntityMultiBlockPart aPart, byte aSide);
}