package gregapi.oredict;

import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictItemDataOverrideItem {
	/** Gets an overridden Value for the OreDict Item Data. */
	public OreDictItemData getOreDictItemData(ItemStack aStack);
}