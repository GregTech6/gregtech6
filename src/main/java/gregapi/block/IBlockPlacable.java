package gregapi.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface is used for World Generators, which want to place Blocks without too much special Code.
 */
public interface IBlockPlacable {
	/** Places the Block at this Location with the given MetaData and NBT (of an Item for example). The NBT Tag may be null! */
	public boolean placeBlock(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement);
}