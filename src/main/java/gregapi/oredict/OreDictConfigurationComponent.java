package gregapi.oredict;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.util.OM;

/**
 * @author Gregorius Techneticies
 */
public class OreDictConfigurationComponent implements IOreDictConfigurationComponent {
	private final ArrayListNoNulls<OreDictMaterialStack> mList;
	private final ArrayListNoNulls<OreDictMaterialStack> mDividedList;
	public final long mCommonDivider;
	
	public OreDictConfigurationComponent(long aCommonDivider, OreDictMaterialStack... aComponents) {
		mCommonDivider = aCommonDivider;
		mList = new ArrayListNoNulls(F, (Object[])aComponents);
		mDividedList = new ArrayListNoNulls();
		for (OreDictMaterialStack tMaterial : mList) mDividedList.add(OM.stack(tMaterial.mMaterial, tMaterial.mAmount / mCommonDivider));
	}
	
	@Override
	public ArrayListNoNulls<OreDictMaterialStack> getComponents() {
		return mDividedList;
	}
	
	@Override
	public ArrayListNoNulls<OreDictMaterialStack> getUndividedComponents() {
		return mList;
	}
	
	@Override
	public long getCommonDivider() {
		return mCommonDivider;
	}
}