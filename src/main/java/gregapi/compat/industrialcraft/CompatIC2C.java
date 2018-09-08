package gregapi.compat.industrialcraft;

import static gregapi.data.CS.*;

import gregapi.compat.CompatBase;
import gregapi.data.MD;
import gregapi.item.IItemGT;
import gregapi.util.ST;
import ic2.api.info.IC2Classic;
import ic2.api.item.IWrenchHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CompatIC2C extends CompatBase implements IWrenchHandler {
	public CompatIC2C() {
		// IC2C Wrench Handler Registration due to it automatically overriding my Wrenches otherwise.
		if (MD.IC2C.mLoaded) IC2Classic.registerWrenchHandler(this);
	}
	
	@Override public boolean supportsItem(ItemStack aWrench) {return ST.valid(aWrench) && aWrench.getItem() instanceof IItemGT;}
	@Override public boolean canWrench(ItemStack aWrench, int aX, int aY, int aZ, EntityPlayer aPlayer) {return F;}
	@Override public void useWrench(ItemStack aWrench, int aX, int aY, int aZ, EntityPlayer aPlayer) {/**/}
}