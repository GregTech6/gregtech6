package gregapi.oredict;

/**
 * @author Gregorius Techneticies
 * 
 * Because parsing the Prefix out of an Item takes unnecessary Time.
 */
public interface IOreDictOptimizedParsingItem {
	public OreDictMaterial getMaterial(int aMetaData);
	public OreDictPrefix getPrefix(int aMetaData);
}