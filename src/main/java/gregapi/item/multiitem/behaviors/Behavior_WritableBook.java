package gregapi.item.multiitem.behaviors;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_WritableBook extends AbstractBehaviorDefault {
	public static final Behavior_WritableBook INSTANCE = new Behavior_WritableBook();
	
    @Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
    	aPlayer.displayGUIBook(ST.make(Items.writable_book, 1, 0, aStack.getTagCompound()));
    	return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
    }
    
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		String tTitle = UT.NBT.getBookTitle(aStack);
		if (UT.Code.stringValid(tTitle)) {
			aList.add(LH.Chat.CYAN + tTitle);
		} else {
			aList.add(LH.Chat.CYAN + "Untitled");
		}
		return aList;
	}
}