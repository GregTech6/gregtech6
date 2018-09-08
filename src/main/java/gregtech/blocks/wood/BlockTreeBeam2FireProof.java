package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeam;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam2FireProof extends BlockBaseBeam {
	public BlockTreeBeam2FireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_2);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Acacia Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Acacia Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Acacia Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Acacia Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Dark Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Dark Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Dark Oak Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Dark Oak Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Rubber Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Rubber Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Rubber Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Rubber Wood Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Wood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Wood Beam (Fireproof)");
	}
}