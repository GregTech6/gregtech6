package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeam;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam1FireProof extends BlockBaseBeam {
	public BlockTreeBeam1FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_1);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Oak Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Spruce Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Spruce Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Birch Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Birch Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Jungle Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Jungle Beam (Fireproof)");
	}
}