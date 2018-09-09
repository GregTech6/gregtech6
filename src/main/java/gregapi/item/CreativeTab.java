package gregapi.item;

import gregapi.data.LH;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author Gregorius Techneticies
 */
public class CreativeTab extends CreativeTabs {
	public final Item mItem;
	public final short mMetaData;
	
	public CreativeTab(String aName, String aLocal, Item aItem, short aMetaData) {
		super(aName);
		LH.add("itemGroup." + aName, aLocal);
		mItem = aItem;
		mMetaData = aMetaData;
	}
	
	@Override
	public Item getTabIconItem() {
		return mItem;
	}
	
    @Override
	public int func_151243_f() {
        return mMetaData;
    }
}
