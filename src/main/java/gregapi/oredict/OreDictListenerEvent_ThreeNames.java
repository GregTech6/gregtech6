package gregapi.oredict;

import static gregapi.data.CS.*;

import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 * 
 * Used to process Non-Material based OreDict Stuff faster, like a String switch/case kind of a deal.
 */
public abstract class OreDictListenerEvent_ThreeNames implements IOreDictListenerEvent {
	public final String mName1, mName2, mName3;
	
	public OreDictListenerEvent_ThreeNames(String aName1, String aName2, String aName3) {
		mName1 = aName1;
		mName2 = aName2;
		mName3 = aName3;
	}
	
	public abstract void onOreRegistration(ItemStack aStack1, ItemStack aStack2, ItemStack aStack3);
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (mName1.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack2 : OreDictManager.getOres(mName2, F)) {
				for (ItemStack tStack3 : OreDictManager.getOres(mName3, F)) {
					onOreRegistration(aEvent.mStack, tStack2, tStack3);
				}
			}
		} else if (mName2.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack1 : OreDictManager.getOres(mName1, F)) {
				for (ItemStack tStack3 : OreDictManager.getOres(mName3, F)) {
					onOreRegistration(tStack1, aEvent.mStack, tStack3);
				}
			}
		} else {
			for (ItemStack tStack1 : OreDictManager.getOres(mName1, F)) {
				for (ItemStack tStack2 : OreDictManager.getOres(mName2, F)) {
					onOreRegistration(tStack1, tStack2, aEvent.mStack);
				}
			}
		}
	}
}