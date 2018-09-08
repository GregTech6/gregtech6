package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeamB extends BlockBaseBeamFlammable {
	public BlockTreeBeamB(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, Textures.BlockIcons.BEAMS_B);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Hazel Beam");
		LH.add(getUnlocalizedName()+ ".4.name", "Hazel Beam");
		LH.add(getUnlocalizedName()+ ".8.name", "Hazel Beam");
		LH.add(getUnlocalizedName()+".12.name", "Hazel Beam");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Cinnamon Beam");
		LH.add(getUnlocalizedName()+ ".5.name", "Cinnamon Beam");
		LH.add(getUnlocalizedName()+ ".9.name", "Cinnamon Beam");
		LH.add(getUnlocalizedName()+".13.name", "Cinnamon Beam");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Palm Beam");
		LH.add(getUnlocalizedName()+ ".6.name", "Palm Beam");
		LH.add(getUnlocalizedName()+".10.name", "Palm Beam");
		LH.add(getUnlocalizedName()+".14.name", "Palm Beam");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Rainbowood Beam");
		LH.add(getUnlocalizedName()+ ".7.name", "Rainbowood Beam");
		LH.add(getUnlocalizedName()+".11.name", "Rainbowood Beam");
		LH.add(getUnlocalizedName()+".15.name", "Rainbowood Beam");
	}
}