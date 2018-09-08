package gregapi.block.behaviors;

import java.util.ArrayList;

import gregapi.block.prefixblock.PrefixBlock;
import gregapi.code.ArrayListNoNulls;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class Drops_None extends Drops {
	public Drops_None() {
		super((Item)null);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, short aMetaData, TileEntity aTileEntity, int aFortune, boolean aSilkTouch) {
		return new ArrayListNoNulls();
    }
}