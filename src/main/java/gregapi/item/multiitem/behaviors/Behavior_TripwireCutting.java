package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_TripwireCutting extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_TripwireCutting(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer.worldObj.isRemote) return F;
		if (aWorld.getBlock(aX, aY, aZ) == Blocks.tripwire) {
			if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
		        int aMeta = aWorld.getBlockMetadata(aX, aY, aZ) | 8;
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMeta, 4);
		        if (Blocks.tripwire.removedByPlayer(aWorld, aPlayer, aX, aY, aZ, T)) {
		        	Blocks.tripwire.onBlockDestroyedByPlayer(aWorld, aX, aY, aZ, aMeta);
		        	Blocks.tripwire.harvestBlock(aWorld, aPlayer, aX, aY, aZ, aMeta);
		        	UT.Sounds.send(aWorld, SFX.MC_SHEARS, 1.0F, 1.0F, aX, aY, aZ);
		        }
			}
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.tripwirecutting", "Can cut Tripwires by Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.tripwirecutting"));
		return aList;
	}
}