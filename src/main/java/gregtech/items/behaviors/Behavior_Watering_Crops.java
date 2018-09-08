package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import ic2.api.crops.ICropTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class Behavior_Watering_Crops extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Watering_Crops();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		FluidStack mFluid = ((IFluidContainerItem)aItem).getFluid(aStack);
		if (UT.Fluids.water(mFluid)) {
			TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, F);
			try {if (tTileEntity instanceof ICropTile) {
				int tHydration = ((ICropTile)tTileEntity).getHydrationStorage();
				int tDrained = Math.min((200-tHydration)/10, mFluid.amount);
				if (tDrained > 0) {
					((IFluidContainerItem)aItem).drain(aStack, tDrained, T);
					((ICropTile)tTileEntity).setHydrationStorage(tHydration + tDrained*10);
					UT.Sounds.send(aWorld, SFX.MC_LIQUID_WATER, 1.0F, 1.0F, aX, aY, aZ);
				}
				return T;
			}} catch(Throwable e) {/**/}
		}
		return F;
	}
}