package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam1 extends BlockBaseBeamFlammable {
	public BlockTreeBeam1(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, Textures.BlockIcons.BEAMS_1);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Oak Beam");
		LH.add(getUnlocalizedName()+ ".4.name", "Oak Beam");
		LH.add(getUnlocalizedName()+ ".8.name", "Oak Beam");
		LH.add(getUnlocalizedName()+".12.name", "Oak Beam");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Spruce Beam");
		LH.add(getUnlocalizedName()+ ".5.name", "Spruce Beam");
		LH.add(getUnlocalizedName()+ ".9.name", "Spruce Beam");
		LH.add(getUnlocalizedName()+".13.name", "Spruce Beam");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Birch Beam");
		LH.add(getUnlocalizedName()+ ".6.name", "Birch Beam");
		LH.add(getUnlocalizedName()+".10.name", "Birch Beam");
		LH.add(getUnlocalizedName()+".14.name", "Birch Beam");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Jungle Beam");
		LH.add(getUnlocalizedName()+ ".7.name", "Jungle Beam");
		LH.add(getUnlocalizedName()+".11.name", "Jungle Beam");
		LH.add(getUnlocalizedName()+".15.name", "Jungle Beam");
	}
}