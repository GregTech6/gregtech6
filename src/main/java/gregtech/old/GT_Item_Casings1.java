package gregtech.old;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Item_Casings1 extends GT_Item_Casings_Abstract {
    public GT_Item_Casings1(Block par1) {
        super(par1);
    }
    
    @Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
    	super.addInformation(aStack, aPlayer, aList, aF3_H);
    	switch(getDamage(aStack)) {
    	case 12: aList.add(mCoil01Tooltip); break;
    	case 13: aList.add(mCoil02Tooltip); break;
    	case 14: aList.add(mCoil03Tooltip); break;
    	}
    }
}