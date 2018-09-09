package gregapi.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gregapi.data.LH;
import gregapi.lang.LanguageHandler;

/**
 * @author Gregorius Techneticies
 * 
 * Useful for tagging things. It could Tag anything.
 * Better than Strings for tagging Stuff since you can do an == Check rather than needing to use equals.
 */
public final class TagData implements ICondition<ITagDataContainer> {
	private static final List<TagData> TAGS_INTERNAL = new ArrayList();
	public static final List<TagData> TAGS = new ArrayList();
	
	public final int mTagID;
	public final String mName;
	public String mChatFormat = "";
	
	public final ICondition<ITagDataContainer> NOT = new ICondition.Not(this);
	
	public final List<TagData> AS_LIST = Collections.unmodifiableList(Arrays.asList(this));
	
	private TagData(String aName) {
		mTagID = TAGS_INTERNAL.size();
		mName = aName;
		TAGS_INTERNAL.add(this);
		TAGS.add(this);
	}
	
	public static TagData createTagData(String aName, String aLocalShort, String aLocalLong, String aChatFormat) {
		TagData rTagData = createTagData(aName, aLocalShort, aLocalLong);
		rTagData.mChatFormat = aChatFormat;
		return rTagData;
	}
	
	public static TagData createTagData(String aName, String aLocalShort, String aLocalLong) {
		TagData rTagData = createTagData(aName);
		LH.add(rTagData.getTranslatableNameShort(), aLocalShort);
		LH.add(rTagData.getTranslatableNameLong(), aLocalLong);
		return rTagData;
	}
	
	public static TagData createTagData(String aName, String aLocal) {
		return createTagData(aName, aLocal, aLocal);
	}
	
	public static TagData createTagData(String aName) {
		aName = aName.toUpperCase();
		for (TagData tSubTag : TAGS_INTERNAL) if (tSubTag.mName.equals(aName)) return tSubTag;
		return new TagData(aName);
	}
	
	public String getTranslatableNameLong() {
		return "gt.td.long."+mName.toLowerCase();
	}
	
	public String getLocalisedNameLong() {
		return LanguageHandler.translate(getTranslatableNameLong(), mName);
	}
	
	public String getTranslatableNameShort() {
		return "gt.td.short."+mName.toLowerCase();
	}
	
	public String getLocalisedNameShort() {
		return LanguageHandler.translate(getTranslatableNameShort(), mName);
	}
	
	public String getChatFormat() {
		return mChatFormat;
	}
	
	@Override
	public String toString() {
		return mName;
	}
	
	@Override
	public boolean isTrue(ITagDataContainer aObject) {
		return aObject.contains(this);
	}
	
	@Override
	public boolean equals(Object aObject) {
		return (aObject instanceof TagData && ((TagData)aObject).mTagID == mTagID);
	}
	
	@Override
	public int hashCode() {
		return mTagID;
	}
}