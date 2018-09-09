package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeam3 extends BlockBaseBeamFlammable {
	public BlockTreeBeam3(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, Textures.BlockIcons.BEAMS_3);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+ ".4.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+ ".8.name", "Greatwood Beam");
		LH.add(getUnlocalizedName()+".12.name", "Greatwood Beam");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+ ".5.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+ ".9.name", "Silverwood Beam");
		LH.add(getUnlocalizedName()+".13.name", "Silverwood Beam");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+ ".6.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+".10.name", "Skyroot Beam");
		LH.add(getUnlocalizedName()+".14.name", "Skyroot Beam");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+ ".7.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+".11.name", "Darkwood Beam");
		LH.add(getUnlocalizedName()+".15.name", "Darkwood Beam");
	}
}