package gregapi.block;

import gregapi.oredict.OreDictMaterialStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface is used for determining the Chemical Compound at a certain Side of this Block for reacting with it.
 */
public interface IBlockMaterial {
	/** Gets the Material on this Side of the Block, including its amount in Material Units. */
	public OreDictMaterialStack getMaterialAtSide(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide);
	/** Removes the specified Material from that Side. return true if it was successful. */
	public boolean removeMaterialFromSide(World aWorld, int aX, int aY, int aZ, byte aSide, OreDictMaterialStack aMaterial);
}