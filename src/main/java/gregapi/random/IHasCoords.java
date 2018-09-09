package gregapi.random;

import net.minecraft.util.ChunkCoordinates;

/**
 * @author Gregorius Techneticies
 * 
 * Contains simple Utility Functions based just on the Coordinates of the Implementor.
 */
public interface IHasCoords {
	public int getX();
	public int getY();
	public int getZ();
	
	public int getOffsetX (byte aSide);
    public int getOffsetY (byte aSide);
    public int getOffsetZ (byte aSide);
    
	public int getOffsetX (byte aSide, int aMultiplier);
    public int getOffsetY (byte aSide, int aMultiplier);
    public int getOffsetZ (byte aSide, int aMultiplier);
    
	public int getOffsetXN(byte aSide);
    public int getOffsetYN(byte aSide);
    public int getOffsetZN(byte aSide);
    
	public int getOffsetXN(byte aSide, int aMultiplier);
    public int getOffsetYN(byte aSide, int aMultiplier);
    public int getOffsetZN(byte aSide, int aMultiplier);
    
    /** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getCoords();
    /** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getOffset (byte aSide, int aMultiplier);
    /** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getOffsetN(byte aSide, int aMultiplier);
}