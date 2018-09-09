package gregapi.old;

import gregapi.data.MD;
import gregapi.item.ItemBase;

/**
 * This is just a basic Tool, which has normal durability and could break Blocks.
 */
public class GT_Tool_Item extends ItemBase {
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed) {
		this(aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, -1, -1);
	}
	
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID) {
		this(aUnlocalized, aEnglish, aTooltip, aMaxDamage, aEntityDamage, aSwingIfUsed, aChargedGTID, aDisChargedGTID, 0, 0.0F);
	}
	
	public GT_Tool_Item(String aUnlocalized, String aEnglish, String aTooltip, int aMaxDamage, int aEntityDamage, boolean aSwingIfUsed, int aChargedGTID, int aDisChargedGTID, int aToolQuality, float aToolStrength) {
		super(MD.GT.mID, aUnlocalized, aEnglish, aTooltip);
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
		setNoRepair();
		setFull3D();
	}
}