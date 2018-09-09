package gregapi.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityTreeHole extends ITileEntityUnloadable {
	public boolean hasResin(byte aSide);
	public boolean extractResin(byte aSide);
	public ItemStack getResinItem(byte aSide);
	public FluidStack getResinFluid(byte aSide);
}