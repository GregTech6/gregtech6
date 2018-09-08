package gregapi.tileentity.multiblocks;

import java.util.Collection;

import gregapi.code.TagData;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockEnergyDataCapacitor extends ITileEntityMultiBlockController {
	public long getEnergyStored							(MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergyCapacity						(MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public boolean isEnergyCapacitorType				(MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public Collection<TagData> getEnergyCapacitorTypes	(MultiTileEntityMultiBlockPart aPart, byte aSide);
}