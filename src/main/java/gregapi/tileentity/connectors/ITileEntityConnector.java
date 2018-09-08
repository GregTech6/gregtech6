package gregapi.tileentity.connectors;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.tileentity.ITileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityConnector extends ITileEntity {
	/**
	 * @return if aSide is connected.
	 */
	public boolean connected(byte aSide);
	
	/**
	 * Connects the Connector with whatever is adjacent to it on that Side.
	 * @param aNotify used to notify the thing on the other Side about the new Connection Status of this Connector
	 * @return true if it is connected to aSide now.
	 */
	public boolean connect(byte aSide, boolean aNotify);
	
	/**
	 * Disconnects the Connector from whatever is adjacent to it on that Side.
	 * @param aNotify used to notify the thing on the other Side about the new Connection Status of this Connector
	 * @return true if it is no longer connected to aSide now.
	 */
	public boolean disconnect(byte aSide, boolean aNotify);
	
	/**
	 * @return a Collection containing all Connector Types this one can connect to.
	 */
	public Collection<TagData> getConnectorTypes(byte aSide);
}