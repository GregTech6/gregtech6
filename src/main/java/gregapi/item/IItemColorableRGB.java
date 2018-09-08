package gregapi.item;

import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IItemColorableRGB {
	public boolean canRecolorItem(ItemStack aStack);
	public boolean recolorItem(ItemStack aStack, int aRGB);
	public boolean canDecolorItem(ItemStack aStack);
	public boolean decolorItem(ItemStack aStack);
}