package gregapi.tileentity;

import net.minecraft.item.ItemStack;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityConnectedInventory extends ITileEntityUnloadable {
	/** @return the amount of successfully added Items. */
	public int addStackToConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated);
	/** @return the amount of successfully removed Items. */
	public int removeStackFromConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyRemoveIfItCanRemoveAllAtOnce);
	/** @return the amount of counted Items. */
	public long getAmountOfItemsInConnectedInventory(byte aSide, ItemStack aStack, long aStopCountingAtThisNumber);
}