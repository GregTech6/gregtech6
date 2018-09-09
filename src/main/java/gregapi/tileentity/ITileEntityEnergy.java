package gregapi.tileentity;

import java.util.Collection;

import gregapi.code.TagData;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 * 
 * I know this belongs into the gregapi.tileentity.energy package, so you better use the moved Interface that is actually in that package, because this one is just a compatibility crash prevention.
 */
@Deprecated
public interface ITileEntityEnergy extends ITileEntityUnloadable {
	public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting);
	public Collection<TagData> getEnergyTypes(byte aSide);
	public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical);
	public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical);
	public long doEnergyInjection(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject);
	public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize);
	public long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract);
	public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize);
	public long getEnergySizeInputMin(TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide);
	public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide);
	public long getEnergySizeInputMax(TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide);
	public static class Util {
		public static final long emitEnergyToNetwork(TagData aEnergyType, long aSize, long aAmount, ITileEntityEnergy aEmitter) {return gregapi.tileentity.energy.ITileEntityEnergy.Util.emitEnergyToNetwork(aEnergyType, aSize, aAmount, aEmitter);}
		public static final long emitEnergyToSide(TagData aEnergyType, byte aSideOutOf, long aSize, long aAmount, TileEntity aEmitter) {return gregapi.tileentity.energy.ITileEntityEnergy.Util.emitEnergyToSide(aEnergyType, aSideOutOf, aSize, aAmount, aEmitter);}
		public static final long insertEnergyInto(TagData aEnergyType, byte aSideInto, long aSize, long aAmount, Object aEmitter, TileEntity aReceiver) {return gregapi.tileentity.energy.ITileEntityEnergy.Util.insertEnergyInto(aEnergyType, aSideInto, aSize, aAmount, aEmitter, aReceiver);}
	}
}