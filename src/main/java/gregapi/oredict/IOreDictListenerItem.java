package gregapi.oredict;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictListenerItem {
	public ItemStack onTickWorld		(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityItem aItem);
    public ItemStack onClickRight		(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer);
	public void onTickPlayer			(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer, int aIndex);
	public void onTickInventory			(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, IInventory aInventory, int aIndex);
	
	/** Returns null if it doesn't provide a ToolTip for this Behaviour. */
	public String getListenerToolTip	(ItemStack aStack);
	
	/** Contains a Default implementation in case only one of the Events is actually needed. Extend this Class rather than the Interface, for more API-Security. */
	public abstract class OreDictListenerItem implements IOreDictListenerItem {
		@Override
		public ItemStack onTickWorld(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityItem aItem) {
			return aStack;
		}
		
		@Override
		public ItemStack onClickRight(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer) {
			return aStack;
		}
		
		@Override
		public void onTickPlayer(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer, int aIndex) {
			//
		}
		
		@Override
		public void onTickInventory(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, IInventory aInventory, int aIndex) {
			//
		}
		
		@Override
		public String getListenerToolTip(ItemStack aStack) {
			return null;
		}
	}
}