package gregtech.old;

import gregapi.block.MaterialMachines;
import gregapi.data.LH;
import net.minecraft.util.IIcon;

public class GT_Block_Casings3 extends GT_Block_Casings_Abstract {
	public GT_Block_Casings3() {
        super("gt.blockcasings3", MaterialMachines.instance);
//      for (byte i = 0; i < 16; i++) Textures.BlockIcons.CASING_BLOCKS[i+32] = new BlockTextureCopied(this, 6, i);
		LH.add(getUnlocalizedName()+ ".0.name", "Yellow Stripes Block");
		LH.add(getUnlocalizedName()+ ".1.name", "Yellow Stripes Block");
		LH.add(getUnlocalizedName()+ ".2.name", "Radioactive Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".3.name", "Bio Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".4.name", "Explosion Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".5.name", "Fire Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".6.name", "Acid Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".7.name", "Magic Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".8.name", "Frost Hazard Sign Block");
		LH.add(getUnlocalizedName()+ ".9.name", "Noise Hazard Sign Block");
		LH.add(getUnlocalizedName()+".10.name", "Grate Casing");
		LH.add(getUnlocalizedName()+".11.name", "Vent Casing");
		LH.add(getUnlocalizedName()+".12.name", "Radiation Proof Casing");
		LH.add(getUnlocalizedName()+".13.name", "Bronze Firebox Casing");
		LH.add(getUnlocalizedName()+".14.name", "Steel Firebox Casing");
		LH.add(getUnlocalizedName()+".15.name", "Tungstensteel Firebox Casing");
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMeta) {/*
		switch(aMeta) {
		case  0: return Textures.BlockIcons.MACHINE_CASING_STRIPES_A              				.getIcon(0);
		case  1: return Textures.BlockIcons.MACHINE_CASING_STRIPES_B               				.getIcon(0);
		case  2: return Textures.BlockIcons.MACHINE_CASING_RADIOACTIVEHAZARD       				.getIcon(0);
		case  3: return Textures.BlockIcons.MACHINE_CASING_BIOHAZARD               				.getIcon(0);
		case  4: return Textures.BlockIcons.MACHINE_CASING_EXPLOSIONHAZARD         				.getIcon(0);
		case  5: return Textures.BlockIcons.MACHINE_CASING_FIREHAZARD              				.getIcon(0);
		case  6: return Textures.BlockIcons.MACHINE_CASING_ACIDHAZARD              				.getIcon(0);
		case  7: return Textures.BlockIcons.MACHINE_CASING_MAGICHAZARD             				.getIcon(0);
		case  8: return Textures.BlockIcons.MACHINE_CASING_FROSTHAZARD             				.getIcon(0);
		case  9: return Textures.BlockIcons.MACHINE_CASING_NOISEHAZARD             				.getIcon(0);
		case 10: return Textures.BlockIcons.MACHINE_CASING_GRATE                   				.getIcon(0);
		case 11: return Textures.BlockIcons.MACHINE_CASING_VENT                   				.getIcon(0);
		case 12: return Textures.BlockIcons.MACHINE_CASING_RADIATIONPROOF         				.getIcon(0);
		case 13: return aSide > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_BRONZE       	.getIcon(0) : Textures.BlockIcons.MACHINE_BRONZEPLATEDBRICKS				.getIcon(0);
		case 14: return aSide > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_STEEL        	.getIcon(0) : Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL				.getIcon(0);
		case 15: return aSide > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_TUNGSTENSTEEL	.getIcon(0) : Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL	.getIcon(0);
		}*/
		return null;
	}
}