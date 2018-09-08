package gregapi.block;

import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface can be used to get the Block with all of its Data in ItemStack form without breaking it into pieces, like harvesting would.
 */
public interface IBlockRetrievable {
	/** Gets the ItemStack representing this Block. Normally one could just check for getDrops, but this Interface is there for cases where the Block drops more than this Item or drops an NBT Item */
	public ItemStack getItemStackFromBlock(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide);
}