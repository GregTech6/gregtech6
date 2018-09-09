package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeam;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeamBFireProof extends BlockBaseBeam {
	public BlockTreeBeamBFireProof(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, 4, Textures.BlockIcons.BEAMS_B);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Hazel Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".8.name", "Hazel Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".12.name", "Hazel Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Cinnamon Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".9.name", "Cinnamon Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".13.name", "Cinnamon Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Palm Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".6.name", "Palm Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".10.name", "Palm Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".14.name", "Palm Beam (Fireproof)");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Rainbowood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".11.name", "Rainbowood Beam (Fireproof)");
		LH.add(getUnlocalizedName()+".15.name", "Rainbowood Beam (Fireproof)");
	}
}