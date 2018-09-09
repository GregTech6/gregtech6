package gregapi.compat.buildcraft;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.code.TagData;
import gregapi.compat.CompatBase;
import gregapi.data.TD;


public class CompatBC extends CompatBase implements ICompatBC {
	public CompatBC() {
		TriggerBC_Energy_Capacity_Empty.class.getCanonicalName();
		TriggerBC_Energy_Capacity_Partial.class.getCanonicalName();
		TriggerBC_Energy_Capacity_NotFull.class.getCanonicalName();
		TriggerBC_Energy_Capacity_Full.class.getCanonicalName();
	}
	
	@Override
	public void onPostLoad(FMLPostInitializationEvent aEvent) {
		for (TagData tEnergyType : TD.Energy.ALL) {
			new TriggerBC_Energy_Capacity_Empty(tEnergyType);
			new TriggerBC_Energy_Capacity_Partial(tEnergyType);
			new TriggerBC_Energy_Capacity_NotFull(tEnergyType);
			new TriggerBC_Energy_Capacity_Full(tEnergyType);
		}
	}
}