package gregapi.wooddict;

import static gregapi.data.CS.*;

import java.util.Set;

import gregapi.code.HashSetNoNulls;
import gregapi.data.CS.PlankData;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IconContainerCopied;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class PlankEntry {
	public final Set<WoodEntry> mWoodEntries = new HashSetNoNulls();
	public final Set<BeamEntry> mBeamEntries = new HashSetNoNulls();
	public ItemStack mPlank, mSlab, mStair, mStick;
	public OreDictMaterial mMaterialPlank = MT.Wood;
	public int mPlankIconIndex, mStickCountHand = 1, mStickCountSaw = 2, mStickCountLathe = 2;
	
	public PlankEntry(ItemStack aPlank) {
		this(aPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, int aPlankIconIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab) {
		this(aPlank, aSlab, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, int aPlankIconIndex) {
		this(aPlank, aSlab, ST.make(Blocks.oak_stairs, 1, 0), aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair) {
		this(aPlank, aSlab, aStair, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, int aPlankIconIndex) {
		this(aPlank, aSlab, aStair, MT.Wood, aPlankIconIndex);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank) {
		this(aPlank, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick) {
		this(aPlank, aSlab, aMaterialPlank, aPlankIconIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		this(aPlank, aSlab, ST.make(Blocks.oak_stairs, 1, 0), aMaterialPlank, aPlankIconIndex, aStick, aStickCountHand, aStickCountSaw, aStickCountLathe);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank) {
		this(aPlank, aSlab, aStair, aMaterialPlank, 0);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIconIndex, OP.stick.mat(aMaterialPlank, 1));
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick) {
		this(aPlank, aSlab, aStair, aMaterialPlank, aPlankIconIndex, aStick, 1, 2, 2);
	}
	public PlankEntry(ItemStack aPlank, ItemStack aSlab, ItemStack aStair, OreDictMaterial aMaterialPlank, int aPlankIconIndex, ItemStack aStick, int aStickCountHand, int aStickCountSaw, int aStickCountLathe) {
		if (ST.invalid(aPlank)) return;
		mPlank = ST.amount(1, aPlank);
		mStair = ST.amount(1, aStair);
		mSlab = ST.amount(1, aSlab);
		mStick = ST.amount(1, aStick);
		mStickCountHand = aStickCountHand;
		mStickCountSaw = aStickCountSaw;
		mStickCountLathe = aStickCountLathe;
		mMaterialPlank = aMaterialPlank;
		mPlankIconIndex = aPlankIconIndex;
		
		if (mPlankIconIndex > 0 && ST.invalid(PlankData.PLANKS[mPlankIconIndex])) {
			PlankData.PLANKS[mPlankIconIndex] = ST.amount(1, mPlank);
			PlankData.PLANK_ICONS[mPlankIconIndex] = new IconContainerCopied(ST.block(mPlank), ST.meta(mPlank), SIDE_ANY);
		}
		
		WoodDictionary.PLANKS.put(mPlank, this);
		
		OreDictManager.INSTANCE.setItemData_(mPlank, new OreDictItemData(mMaterialPlank, U));
		OreDictManager.INSTANCE.setItemData (mSlab , new OreDictItemData(mMaterialPlank, U2));
		OreDictManager.INSTANCE.setItemData (mStair, new OreDictItemData(mMaterialPlank, U4*3));
	}
}
