package gregapi.item;

import static gregapi.data.CS.*;

import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * @author Gregorius Techneticies
 */
public interface IItemRottable {
	public ItemStack getRotten(ItemStack aStack);
	public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ);
	
	public static class RottingUtil {
		public static ItemStack rotting(ItemStack aStack) {
			if (aStack.getItem() == Items.milk_bucket) return IL.ENVM_Spoiled_Milk_Bucket.exists()?IL.ENVM_Spoiled_Milk_Bucket.get(aStack.stackSize):ST.make(Items.bucket, aStack.stackSize, 0);
			if (aStack.getItem() instanceof IItemRottable) return ((IItemRottable)aStack.getItem()).getRotten(aStack);
			if (aStack.getItem() instanceof IFluidContainerItem) return rotting(aStack, (IFluidContainerItem)aStack.getItem());
			return aStack;
		}
		
		public static ItemStack rotting(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
			if (aStack.getItem() == Items.milk_bucket) return IL.ENVM_Spoiled_Milk_Bucket.exists()?IL.ENVM_Spoiled_Milk_Bucket.get(aStack.stackSize):ST.make(Items.bucket, aStack.stackSize, 0);
			if (aStack.getItem() instanceof IItemRottable) return ((IItemRottable)aStack.getItem()).getRotten(aStack, aWorld, aX, aY, aZ);
			if (aStack.getItem() instanceof IFluidContainerItem) return rotting(aStack, (IFluidContainerItem)aStack.getItem());
			return aStack;
		}
		
		public static ItemStack rotting(ItemStack aStack, IFluidContainerItem aItem) {
			FluidStack tFluid = aItem.getFluid(aStack);
			if (tFluid != null) {
				if (FL.Milk_Spoiled.is(tFluid) || FL.Rotten_Drink.is(tFluid) || FL.Dirty_Water.is(tFluid) || FL.Swampwater.is(tFluid) || FL.Stagnant_Water.is(tFluid)) {
					//
				} else if (FluidsGT.WATER.contains(tFluid.getFluid().getName())) {
					aItem.drain(aStack, Integer.MAX_VALUE, T);
					aItem.fill(aStack, FL.Dirty_Water.make(tFluid.amount), T);
				} else if (FluidsGT.MILK.contains(tFluid.getFluid().getName())) {
					aItem.drain(aStack, Integer.MAX_VALUE, T);
					aItem.fill(aStack, FL.Milk_Spoiled.make(tFluid.amount), T);
				} else if (FluidsGT.FOOD.contains(tFluid.getFluid().getName())) {
					aItem.drain(aStack, Integer.MAX_VALUE, T);
					aItem.fill(aStack, FL.Rotten_Drink.make(tFluid.amount), T);
				}
			}
			return aStack;
		}
	}
}