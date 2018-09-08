package gregtech.old;

import gregapi.block.MaterialMachines;
import gregapi.data.LH;
import net.minecraft.util.IIcon;

public class GT_Block_Casings1 extends GT_Block_Casings_Abstract {
	public GT_Block_Casings1() {
        super("gt.blockcasings", MaterialMachines.instance);
//      for (byte i = 0; i < 16; i++) Textures.BlockIcons.CASING_BLOCKS[i] = new BlockTextureCopied(this, 6, i);
		LH.add(getUnlocalizedName()+ ".0.name", "ULV Machine Casing");
		LH.add(getUnlocalizedName()+ ".1.name", "LV Machine Casing");
		LH.add(getUnlocalizedName()+ ".2.name", "MV Machine Casing");
		LH.add(getUnlocalizedName()+ ".3.name", "HV Machine Casing");
		LH.add(getUnlocalizedName()+ ".4.name", "EV Machine Casing");
		LH.add(getUnlocalizedName()+ ".5.name", "IV Machine Casing");
		LH.add(getUnlocalizedName()+ ".6.name", "LuV Machine Casing");
		LH.add(getUnlocalizedName()+ ".7.name", "ZPM Machine Casing");
		LH.add(getUnlocalizedName()+ ".8.name", "UV Machine Casing");
		LH.add(getUnlocalizedName()+ ".9.name", "MAX Machine Casing");
		LH.add(getUnlocalizedName()+".10.name", "Bronze Plated Bricks");
		LH.add(getUnlocalizedName()+".11.name", "Heat Proof Machine Casing");
		LH.add(getUnlocalizedName()+".12.name", "Cupronickel Coil Block");
		LH.add(getUnlocalizedName()+".13.name", "Kanthal Coil Block");
		LH.add(getUnlocalizedName()+".14.name", "Nichrome Coil Block");
		LH.add(getUnlocalizedName()+".15.name", "Superconducting Coil Block");
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMeta) {/*
		if (aMeta >= 0 && aMeta < 16) {
			switch(aMeta) {
			case 10: return Textures.BlockIcons.MACHINE_BRONZEPLATEDBRICKS.getIcon(0);
			case 11: return Textures.BlockIcons.MACHINE_HEATPROOFCASING.getIcon(0);
			case 12: return Textures.BlockIcons.MACHINE_COIL_CUPRONICKEL.getIcon(0);
			case 13: return Textures.BlockIcons.MACHINE_COIL_KANTHAL.getIcon(0);
			case 14: return Textures.BlockIcons.MACHINE_COIL_NICHROME.getIcon(0);
			case 15: return Textures.BlockIcons.MACHINE_COIL_SUPERCONDUCTOR.getIcon(0);
			default:
				if (aSide == 0) return Textures.BlockIcons.MACHINECASINGS_BOTTOM[aMeta].getIcon(0);
				if (aSide == 1) return Textures.BlockIcons.MACHINECASINGS_TOP[aMeta].getIcon(0);
				return Textures.BlockIcons.MACHINECASINGS_SIDE[aMeta].getIcon(0);
			}
		}*/
		return null;
	}
	/*
    @Override
    public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return aWorld.getBlockMetadata(aX, aY, aZ) > 9 ? super.colorMultiplier(aWorld, aX, aY, aZ) : (Dyes.MACHINE_METAL.mRGBa[0] << 16) | (Dyes.MACHINE_METAL.mRGBa[1] << 8) | Dyes.MACHINE_METAL.mRGBa[2];
    }*/
}