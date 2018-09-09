package gregapi.random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * @author Gregorius Techneticies
 * 
 * Contains simple Utility Functions based just on the World of the Implementor.
 */
public interface IHasWorld {
	public World getWorld();
	
	public boolean isServerSide();
    public boolean isClientSide();
    
    public int rng(int aRange);
    public int getRandomNumber(int aRange);
    
	public TileEntity getTileEntity(int aX, int aY, int aZ);
	public TileEntity getTileEntity(ChunkCoordinates aCoords);
	
    public Block getBlock(int aX, int aY, int aZ);
    public Block getBlock(ChunkCoordinates aCoords);
    
	public byte getMetaData(int aX, int aY, int aZ);
	public byte getMetaData(ChunkCoordinates aCoords);
	
	public byte getLightLevel(int aX, int aY, int aZ);
	public byte getLightLevel(ChunkCoordinates aCoords);
	
	public boolean getOpacity(int aX, int aY, int aZ);
	public boolean getOpacity(ChunkCoordinates aCoords);
	
	public boolean getSky(int aX, int aY, int aZ);
	public boolean getSky(ChunkCoordinates aCoords);
	
	public boolean getRain(int aX, int aY, int aZ);
	public boolean getRain(ChunkCoordinates aCoords);
	
	public boolean getAir(int aX, int aY, int aZ);
	public boolean getAir(ChunkCoordinates aCoords);
	
    public BiomeGenBase getBiome(int aX, int aZ);
    public BiomeGenBase getBiome(ChunkCoordinates aCoords);
}