package gregapi.tileentity.machines;

import gregapi.oredict.OreDictMaterialStack;
import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityMold extends ITileEntityUnloadable {
	/** DO NOT MODIFY THE RETURNED ARRAY! */
	public boolean	isMoldInputSide(byte aSide);
	/** The Maximum Temperature this Mold can accept without melting itself */
	public long		getMoldMaxTemperature();
	/** The Amount of Material required to fill the Mold completely */
	public long		getMoldRequiredMaterialUnits();
	/** The first Parameter can also have a Material Amount higher or lower than the actually required Amount, usually only in case it is done manually. It returns the amount of Material subtracted from the passed MaterialStack */
	public long		fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide);
}