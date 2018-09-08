package gregtech.blocks.wood;

import gregapi.block.tree.BlockBaseBeamFlammable;
import gregapi.data.LH;
import gregapi.old.Textures;
import net.minecraft.block.material.Material;

public class BlockTreeBeamA extends BlockBaseBeamFlammable {
	public BlockTreeBeamA(String aUnlocalised) {
		super(null, aUnlocalised, Material.wood, soundTypeWood, Textures.BlockIcons.BEAMS_A);
		
		LH.add(getUnlocalizedName()+ ".0.name", "Rubber Beam");
		LH.add(getUnlocalizedName()+ ".4.name", "Rubber Beam");
		LH.add(getUnlocalizedName()+ ".8.name", "Rubber Beam");
		LH.add(getUnlocalizedName()+".12.name", "Rubber Beam");
		
		LH.add(getUnlocalizedName()+ ".1.name", "Maple Beam");
		LH.add(getUnlocalizedName()+ ".5.name", "Maple Beam");
		LH.add(getUnlocalizedName()+ ".9.name", "Maple Beam");
		LH.add(getUnlocalizedName()+".13.name", "Maple Beam");
		
		LH.add(getUnlocalizedName()+ ".2.name", "Willow Beam");
		LH.add(getUnlocalizedName()+ ".6.name", "Willow Beam");
		LH.add(getUnlocalizedName()+".10.name", "Willow Beam");
		LH.add(getUnlocalizedName()+".14.name", "Willow Beam");
		
		LH.add(getUnlocalizedName()+ ".3.name", "Blue Mahoe Beam");
		LH.add(getUnlocalizedName()+ ".7.name", "Blue Mahoe Beam");
		LH.add(getUnlocalizedName()+".11.name", "Blue Mahoe Beam");
		LH.add(getUnlocalizedName()+".15.name", "Blue Mahoe Beam");
	}
}