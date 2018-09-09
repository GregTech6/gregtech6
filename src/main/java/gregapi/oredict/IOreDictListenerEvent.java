package gregapi.oredict;

import gregapi.data.MT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictListenerEvent {
	/** Any Event will buffered to be called after the Unification has been set, so you can access all the Unification things, regardless of when you register your Listener! */
	public void onOreRegistration(OreDictRegistrationContainer aEvent);
	
	public static class OreDictRegistrationContainer {
		public final OreDictPrefix mPrefix;
		public final OreDictMaterial mMaterial;
		public final String mOreDictName, mModName;
		public final ItemStack mStack;
		public final OreRegisterEvent mEvent;
		/** If something else hasn't already been registered under the same Name before. Useful for preventing duplicate Crafting Recipes. */
		public final boolean mNotAlreadyRegisteredName;
		
		public OreDictRegistrationContainer(OreDictPrefix aPrefix, OreDictMaterial aMaterial, String aOreDictName, ItemStack aStack, OreRegisterEvent aEvent, String aModName, boolean aNotAlreadyRegisteredName) {
			mPrefix = aPrefix;
			mMaterial = (aMaterial==null?MT.NULL:aMaterial);
			mOreDictName = aOreDictName;
			mModName = aModName;
			mStack = aStack.copy();
			mEvent = aEvent;
			mNotAlreadyRegisteredName = aNotAlreadyRegisteredName;
		}
	}
}