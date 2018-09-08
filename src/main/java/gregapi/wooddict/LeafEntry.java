package gregapi.wooddict;

import java.util.Set;

import gregapi.code.HashSetNoNulls;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class LeafEntry {
	public final Set<SaplingEntry> mSaplingEntries = new HashSetNoNulls();
	public ItemStack mLeaf;
	
	public LeafEntry(ItemStack aLeaf) {
		if (ST.invalid(aLeaf)) return;
		
		mLeaf = ST.amount(1, aLeaf);
		
		WoodDictionary.LEAVES.put(mLeaf, this);
	}
}
