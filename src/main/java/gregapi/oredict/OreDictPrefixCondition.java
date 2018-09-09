package gregapi.oredict;

import static gregapi.data.CS.*;

import gregapi.code.ICondition;
import gregapi.code.TagData;

/**
 * @author Gregorius Techneticies
 * 
 * A Collection of Classes, which check for certain Prefix Conditions.
 */
public class OreDictPrefixCondition {
	public static ICondition<OreDictPrefix> tag		(TagData... aTags)		{return new TagDataContainsAll(aTags);}
	public static ICondition<OreDictPrefix> tagnor	(TagData... aTags)		{return new TagDataContainsNone(aTags);}
	
	private static class TagDataContainsAll implements ICondition<OreDictPrefix> {
		private final TagData[] mTags;
		public TagDataContainsAll(TagData... aTags) {mTags = aTags;}
		@Override public boolean isTrue(OreDictPrefix aPrefix) {return aPrefix.containsAll(mTags);}
	}
	
	private static class TagDataContainsNone implements ICondition<OreDictPrefix> {
		private final TagData[] mTags;
		public TagDataContainsNone(TagData... aTags) {mTags = aTags;}
		@Override public boolean isTrue(OreDictPrefix aPrefix) {for (TagData tTag : mTags) if (aPrefix.contains(tTag)) return F; return T;}
	}
}