package gregapi.oredict;

import gregapi.util.OM;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictRecyclableListener {
	public void onRecycleableRegistration(OreDictRecyclingContainer aEvent);
	
	public static class OreDictRecyclingContainer {
		public final OreDictItemData mItemData;
		public final ItemStack mStack;
		
		public OreDictRecyclingContainer(ItemStack aStack, OreDictItemData aItemData) {
			mItemData = aItemData;
			mStack = aStack.copy();
		}
		
		public OreDictRecyclingContainer(OreDictRecyclingContainer aItemData) {
			mItemData = OM.data_(aItemData.mStack);
			mStack = aItemData.mStack.copy();
		}
	}
}