package gregapi.wooddict;

import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class SaplingEntry {
	public ItemStack mSapling;
	public WoodEntry mWoodEntry;
	public LeafEntry mLeafEntry;
	public OreDictMaterial mMaterialSapling = MT.Wood;
	
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood) {
		if (ST.invalid(mSapling)) return;
		mSapling = ST.amount(1, aSapling);
		
		mWoodEntry = aWood;
		mWoodEntry.mSaplingEntries.add(this);
		mMaterialSapling = mWoodEntry.mMaterialWood;
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
	}
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood, ItemStack aLeaf) {
		if (ST.invalid(mSapling) || ST.invalid(aLeaf)) return;
		mSapling = ST.amount(1, aSapling);
		
		mLeafEntry = new LeafEntry(aLeaf);
		mLeafEntry.mSaplingEntries.add(this);
		mWoodEntry = aWood;
		mWoodEntry.mSaplingEntries.add(this);
		mMaterialSapling = mWoodEntry.mMaterialWood;
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
	}
	public SaplingEntry(ItemStack aSapling, WoodEntry aWood, LeafEntry aLeaf) {
		if (ST.invalid(mSapling)) return;
		mSapling = ST.amount(1, aSapling);
		
		mLeafEntry = aLeaf;
		mLeafEntry.mSaplingEntries.add(this);
		mWoodEntry = aWood;
		mWoodEntry.mSaplingEntries.add(this);
		mMaterialSapling = mWoodEntry.mMaterialWood;
		
		WoodDictionary.SAPLINGS.put(mSapling, this);
	}
}
