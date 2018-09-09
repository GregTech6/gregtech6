package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Worldgen_Debugger extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Worldgen_Debugger();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;
		for (int tX = (aX&~15)-32, eX = (aX&~15)+16+32; tX < eX; tX++) for (int tZ = (aZ&~15)-32, eZ = (aZ&~15)+16+32; tZ < eZ; tZ++) for (int tY = 1; tY < 250; tY++) WD.set(aWorld, tX, tY, tZ, NB, 0, 2);
		return T;
	}
	
	static {
		LH.add("gt.behaviour.worldgendebug", "Testing Tool for executing Code based on Coordinates");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.worldgendebug"));
		return aList;
	}
}