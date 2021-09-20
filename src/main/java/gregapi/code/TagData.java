/**
 * Copyright (c) 2021 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gregapi.data.LH;
import gregapi.oredict.OreDictMaterial;

/**
 * @author Gregorius Techneticies
 * 
 * Useful for tagging things. It could Tag anything.
 * Better than Strings for tagging Stuff since you can do an == Check rather than needing to use equals.
 */
@SuppressWarnings("rawtypes")
public final class TagData implements ICondition<ITagDataContainer> {
	private static final List<TagData> TAGS_INTERNAL = new ArrayList<>();
	public static final List<TagData> TAGS = new ArrayList<>();
	
	public final int mTagID;
	public final String mName;
	public String mChatFormat = "";
	
	@SuppressWarnings("unchecked")
	public final ICondition<OreDictMaterial> NOT = new ICondition.Not(this);
	
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
		return LH.get(getTranslatableNameLong(), mName);
	}
	
	public String getLocalisedChatNameLong() {
		return getChatFormat() + getLocalisedNameLong();
	}
	
	public String getTranslatableNameShort() {
		return "gt.td.short."+mName.toLowerCase();
	}
	
	public String getLocalisedNameShort() {
		return LH.get(getTranslatableNameShort(), mName);
	}
	
	public String getLocalisedChatNameShort() {
		return getChatFormat() + getLocalisedNameShort();
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
