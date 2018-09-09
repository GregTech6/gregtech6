package gregapi.compat.industrialcraft;

import gregapi.compat.ICompat;
import net.minecraft.item.ItemStack;

public interface ICompatIC2EUItem extends ICompat {
	public long charge(ItemStack aStack, long aAmount, boolean aDoCharge);
	public long charge(ItemStack aStack, long aAmount, int aTier, boolean aIgnoreLimit, boolean aDoCharge);
	public long decharge(ItemStack aStack, long aAmount, boolean aDoDecharge);
	public long decharge(ItemStack aStack, long aAmount, int aTier, boolean aIgnoreLimit, boolean aDoDecharge, boolean aIgnoreDischargability);
	public byte tier(ItemStack aStack);
	public boolean insidevolt(ItemStack aStack, long aMinVolt, long aMaxVolt);
	public boolean provider(ItemStack aStack);
	public boolean is(ItemStack aStack);
	public boolean is(ItemStack aStack, byte aTier);
	public long stored(ItemStack aStack);
	public long capacity(ItemStack aStack);
}