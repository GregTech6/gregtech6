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

public class Behavior_Place_Paddy extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Place_Paddy(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || !IL.GrC_Paddy.exists() || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		if (aWorld.getBlock(aX, aY, aZ) == Blocks.farmland && (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer))) {
			UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
			aWorld.setBlock(aX, aY, aZ, IL.GrC_Paddy.block(), aWorld.getBlockMetadata(aX, aY, aZ), 3);
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.paddy", "Creates Paddies on Farmland on Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (IL.GrC_Paddy.exists()) aList.add(LH.get("gt.behaviour.paddy"));
		return aList;
	}
}