package gregapi.cover;

import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class CoverRegistry {
	public static ItemStackMap<ItemStackContainer, ICover> COVERS = new ItemStackMap();
	
	public static ICover get(short aID, short aMetaData) {
		return COVERS.get(aID, aMetaData);
	}
	
	public static ICover get(ItemStack aStack) {
		return aStack==null?null:get(ST.id(aStack), ST.meta(aStack));
	}
	
	public static void put(ItemStackContainer aStack, ICover aCover) {
		if (aStack != null && ST.valid(aStack.toStack())) COVERS.put(aStack, aCover);
	}
	
	public static void put(ItemStack aStack, ICover aCover) {
		put(new ItemStackContainer(aStack), aCover);
	}
	
	public static void put(Item aItem, long aMetaData, ICover aCover) {
		put(new ItemStackContainer(aItem, 1, aMetaData), aCover);
	}
	
	public static void put(Block aBlock, long aMetaData, ICover aCover) {
		put(new ItemStackContainer(aBlock, 1, aMetaData), aCover);
	}
	
	public static CoverData coverdata(ITileEntityCoverable aTileEntity, NBTTagCompound aNBT) {
		return aNBT == null ? new CoverData(aTileEntity) : new CoverData(aTileEntity, aNBT);
	}
}