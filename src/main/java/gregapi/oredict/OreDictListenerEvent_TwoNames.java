package gregapi.oredict;

import static gregapi.data.CS.*;

import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 * 
 * Used to process Non-Material based OreDict Stuff faster, like a String switch/case kind of a deal.
 */
public abstract class OreDictListenerEvent_TwoNames implements IOreDictListenerEvent {
	public final String mName1, mName2;
	
	public OreDictListenerEvent_TwoNames(Object aName1, Object aName2) {
		mName1 = aName1.toString();
		mName2 = aName2.toString();
	}
	
	public abstract void onOreRegistration(ItemStack aStack1, ItemStack aStack2);
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (mName1.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack : OreDictManager.getOres(mName2, F)) {
				onOreRegistration(aEvent.mStack, tStack);
			}
		} else {
			for (ItemStack tStack : OreDictManager.getOres(mName1, F)) {
				onOreRegistration(tStack, aEvent.mStack);
			}
		}
	}
}