package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Tool extends AbstractBehaviorDefault {
	public final String mToolName, mSoundName;
	public final long mDamage;
	public final boolean mOnItemUseReturn;
	
	public Behavior_Tool(String aToolName, String aSoundName, long aDamage, boolean aOnItemUseReturn) {
		mToolName = aToolName;
		mSoundName = aSoundName;
		mDamage = aDamage;
		mOnItemUseReturn = aOnItemUseReturn;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
//		if (aPlayer != null && SIDES_VALID[aSide] && !(aPlayer instanceof FakePlayer) && UT.Worlds.isSideObstructed(aWorld, aX, aY, aZ, aSide)) return !aWorld.isRemote;
		List<String> tChatReturn = new ArrayListNoNulls();
		long tDamage = IBlockToolable.Util.onToolClick(mToolName, Long.MAX_VALUE, (aItem instanceof MultiItemTool ? ((MultiItemTool)aItem).getHarvestLevel(aStack, mToolName) : 1), aPlayer, tChatReturn, aPlayer==null?null:aPlayer.inventory, aPlayer!=null&&aPlayer.isSneaking(), aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
		UT.Entities.sendchat(aPlayer, tChatReturn, F);
		if (tDamage > 0) {
    		if (mDamage > 0) if (aPlayer == null || !UT.Entities.hasInfiniteItems(aPlayer)) ((MultiItemTool)aItem).doDamage(aStack, UT.Code.units(tDamage, 10000, mDamage, T), aPlayer);
    		if (mSoundName != null) UT.Sounds.send(aWorld, mSoundName, 1.0F, 1.0F, aX, aY, aZ);
    		return !aWorld.isRemote;
    	}
    	return F;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
    	return mOnItemUseReturn;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.Chat.DGRAY + LH.get(TOOL_LOCALISER_PREFIX + mToolName, "Unknown") + "   " + LH.Chat.GRAY + LH.get(TOOL_TOOLTIP_PREFIX + mToolName, ""));
		return aList;
	}
}