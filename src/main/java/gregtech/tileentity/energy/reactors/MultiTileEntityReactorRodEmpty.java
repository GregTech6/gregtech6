package gregtech.tileentity.energy.reactors;

import gregapi.data.LH;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

public class MultiTileEntityReactorRodEmpty extends MultiTileEntityReactorRodBase {
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(LH.Chat.DGRAY + "Used in Nuclear Reactor Core");
		aList.add(LH.Chat.CYAN + "Empty Reactor Rod, transparent to Neutrons.");
		aList.add(LH.Chat.CYAN + "Blocks Neutrons while switched off.");
	}

	@Override public boolean isTransparent(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return T;}

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.empty";}
}
