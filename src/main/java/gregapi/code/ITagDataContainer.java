package gregapi.code;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * @author Gregorius Techneticies
 * 
 * C is the Class which implements this Interface.
 */
public interface ITagDataContainer<C> {
	/**
	 * @return if the Tag is inside the List.
	 */
	public boolean contains(TagData aTag);
	
	/**
	 * @return if all those Tags are inside the List.
	 */
	public boolean containsAll(TagData... aTags);
	
	/**
	 * @return if all those Tags are inside the List.
	 */
	public boolean containsAll(Collection<TagData> aTags);
	
	/**
	 * @return The ISubTagContainer you called this Function on, for convenience.
	 */
	public C add(TagData... aTags);
	
	/**
	 * @return if the Tag was there before it has been removed.
	 */
	public boolean remove(TagData aTag);
	
	/**
	 * This simple Class is an example on how to implement the Functions of this Interface
	 */
	public static final class BasicTagDataContainer implements ITagDataContainer<BasicTagDataContainer> {
		private final Set<TagData> mTags = new HashSetNoNulls();
		
		@Override
		public boolean contains(TagData aTag) {
			return mTags.contains(aTag);
		}
		
		@Override
		public boolean containsAll(TagData... aTags) {
			return mTags.containsAll(Arrays.asList(aTags));
		}
		
		@Override
		public boolean containsAll(Collection<TagData> aTags) {
			return mTags.containsAll(aTags);
		}
		
		@Override
		public BasicTagDataContainer add(TagData... aTags) {
			if (aTags != null) for (TagData aTag : aTags) mTags.add(aTag);
			return this;
		}
		
		@Override
		public boolean remove(TagData aTag) {
			return mTags.remove(aTag);
		}
	}
}