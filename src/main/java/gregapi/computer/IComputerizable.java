package gregapi.computer;

import gregapi.tileentity.delegate.DelegatorTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 * 
 * Note: ALL the Methods can and will be called at any time by another Thread. Nothing here is synched to World Ticks.
 */
public interface IComputerizable {
	/** @return a unique name for this device. All lowercase without spaces. null if you don't want functionality. */
	public String		getComputerizableName		(DelegatorTileEntity<TileEntity> aDelegator);
	/** @return an Array of all the things getComputerizableArgs can output. */
	public String[]		allComputerizableArgs		(DelegatorTileEntity<TileEntity> aDelegator);
	/** @return an Array of all the things getComputerizableHelp can output. */
	public String[]		allComputerizableHelps		(DelegatorTileEntity<TileEntity> aDelegator);
	/** @return an Array of all the things getComputerizableMethod can output. */
	public String[]		allComputerizableMethods	(DelegatorTileEntity<TileEntity> aDelegator);
	/** @return an Array of all the things getComputerizableReturn can output. */
	public Class<?>[]	allComputerizableReturns	(DelegatorTileEntity<TileEntity> aDelegator);
	/** @return a String Description of the Arguments you are expecting. */
	public String		getComputerizableArgs		(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex);
	/** @return a String Description of the Function itself. */
	public String		getComputerizableHelp		(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex);
	/** @return a String Name of the Function at this Index. */
	public String		getComputerizableMethod		(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex);
	/** @return a Class of the Return Type to expect. */
	public Class<?>		getComputerizableReturn		(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex);
	/** The Function Call itself. The TileEntity implementing IComputerizable is always the one inside aDelegator. aDelegator is only there for Singletons like Covers, that require more Data access. */
	public Object[]		callComputerizableMethod	(DelegatorTileEntity<TileEntity> aDelegator, int aFunctionIndex, Object[] aArguments);
}