package gregtech.items.tools.electric;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi.item.multiitem.behaviors.Behavior_Tool;

public class GT_Tool_MonkeyWrench_LV extends GT_Tool_Wrench_LV {
	
	public GT_Tool_MonkeyWrench_LV(int aSwitchIndex) {
		super(aSwitchIndex);
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_monkeywrench, SFX.IC_WRENCH, 100, !canBlock()));
		aItem.addItemBehavior(aID, new Behavior_Switch_Metadata(mSwitchIndex));
	}
}