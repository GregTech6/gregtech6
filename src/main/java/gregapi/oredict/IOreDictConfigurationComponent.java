package gregapi.oredict;

import gregapi.code.ArrayListNoNulls;

/**
 * @author Gregorius Techneticies
 * 
 * The reason why this is an Interface is, that you can create more powerful implementations of this. For example one could add another Interface that returns the Atomic binding Configuration of this Molecule.
 */
public interface IOreDictConfigurationComponent {
	/**
	 * Gets a List of all Molecules inside one Material Unit of this Material. The Material Stack Amounts are all in Material Units.
	 * 
	 * For example the Alloy Electrum consists of 50% Gold and 50% Silver, and is created by half a Unit of Gold and half a Unit of Silver.
	 * This means it has to return a MaterialStack of half a Unit of Gold and a MaterialStack of half a Unit Silver inside a Non-Null-ArrayList.
	 * "return new ArrayListNoNulls<OreDictMaterialStack>(false, MT.stack(MT.Au, CS.U / 2), MT.stack(MT.Ag, CS.U / 2))"
	 */
	public ArrayListNoNulls<OreDictMaterialStack> getComponents();
	
	public ArrayListNoNulls<OreDictMaterialStack> getUndividedComponents();
	
	/**
	 * @return a Number which can, when multiplied with the Material Amounts inside the List, give only whole Amounts of Material.
	 */
	public long getCommonDivider();
}