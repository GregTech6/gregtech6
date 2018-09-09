package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;

public class Behavior_Plunger_Essentia extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Plunger_Essentia(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		if (aTileEntity instanceof IEssentiaTransport) {
			if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
				UT.Sounds.send(aWorld, SFX.IC_TRAMPOLINE, 1.0F, -1, aX, aY, aZ);
				for (ForgeDirection tDirection : ForgeDirection.VALID_DIRECTIONS) ((IEssentiaTransport)aTileEntity).takeEssentia(((IEssentiaTransport)aTileEntity).getEssentiaType(tDirection), ((IEssentiaTransport)aTileEntity).getEssentiaAmount(tDirection), tDirection);
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.plunger.essentia", "Clears Essentia from Containers and Tubes");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.plunger.essentia"));
		return aList;
	}
}