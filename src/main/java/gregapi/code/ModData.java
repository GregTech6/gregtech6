package gregapi.code;

import java.util.Set;

import cpw.mods.fml.common.Loader;

/**
 * @author Gregorius Techneticies
 */
public final class ModData implements ICondition<ITagDataContainer> {
	public static final Set<ModData> MODS = new HashSetNoNulls();
	
	public boolean mLoaded;
	
	public final String mID, mName;
	
	public ModData(String aID, String aName) {
		mID = aID;
		mName = aName;
		mLoaded = Loader.isModLoaded(mID);
		MODS.add(this);
	}
	
	@Override
	public String toString() {
		return mID;
	}
	
	@Override
	public boolean isTrue(ITagDataContainer aObject) {
		return mLoaded;
	}
	
	@Override
	public boolean equals(Object aObject) {
		return aObject instanceof ModData && ((ModData)aObject).mID.equals(mID);
	}
	
	@Override
	public int hashCode() {
		return mID.hashCode();
	}
}