package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Place_Path extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Place_Path(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || !IL.EtFu_Path.exists() || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		if (aWorld.getBlock(aX, aY, aZ) == Blocks.grass && (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer))) {
			UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
			aWorld.setBlock(aX, aY, aZ, IL.EtFu_Path.block());
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.path", "Creates Paths on Grass on Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (IL.EtFu_Path.exists()) aList.add(LH.get("gt.behaviour.path"));
		return aList;
	}
}