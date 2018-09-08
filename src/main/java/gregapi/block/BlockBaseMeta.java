package gregapi.block;

import java.util.List;

import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseMeta extends BlockBaseSealable {
	public IIconContainer[] mIcons;
	/** For Creative Subsets, not actually important. */
	public final byte mMaxMeta;
	
	public BlockBaseMeta(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType);
		mMaxMeta = (byte)(UT.Code.bind4(aMaxMeta-1)+1);
		mIcons = aIcons;
	}
	
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[aMeta % mIcons.length].getIcon(0);}
	@Override public void getSubBlocks(Item aItem, CreativeTabs aTab, List aList) {for (int i = 0; i < mMaxMeta; i++) aList.add(ST.make(aItem, 1, i));}
}