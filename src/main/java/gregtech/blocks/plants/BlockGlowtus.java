package gregtech.blocks.plants;

import static gregapi.data.CS.*;

import gregapi.block.misc.BlockBaseLilyPad;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockGlowtus extends BlockBaseLilyPad {
	public BlockGlowtus(String aUnlocalised) {
		super(null, aUnlocalised, Material.plants, soundTypeGrass, 16, Textures.BlockIcons.GLOWTUS);
		for (int i = 0; i < 16; i++) LH.add(getUnlocalizedName()+"."+i+".name", DYE_NAMES[i] + " Glowtus");
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public int getLightValue() {return 15;}
}