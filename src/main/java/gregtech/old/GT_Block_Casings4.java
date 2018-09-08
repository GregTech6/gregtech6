package gregtech.old;

import gregapi.block.MaterialMachines;
import gregapi.data.LH;
import net.minecraft.util.IIcon;

public class GT_Block_Casings4 extends GT_Block_Casings_Abstract {
	public GT_Block_Casings4() {
        super("gt.blockcasings4", MaterialMachines.instance);
//      for (byte i = 0; i < 16; i++) Textures.BlockIcons.CASING_BLOCKS[i+48] = new BlockTextureCopied(this, 6, i);
		LH.add(getUnlocalizedName()+ ".0.name", "Robust Tungstensteel Casing"); 
		LH.add(getUnlocalizedName()+ ".1.name", "Clean Stainless Steel Casing");
		LH.add(getUnlocalizedName()+ ".2.name", "Stable Titanium Casing"); 
		LH.add(getUnlocalizedName()+ ".3.name", "Titanium Firebox Casing"); /*
		LH.add(getUnlocalizedName()+ ".4.name", ""); 
		LH.add(getUnlocalizedName()+ ".5.name", ""); 
		LH.add(getUnlocalizedName()+ ".6.name", ""); 
		LH.add(getUnlocalizedName()+ ".7.name", ""); 
		LH.add(getUnlocalizedName()+ ".8.name", ""); 
		LH.add(getUnlocalizedName()+ ".9.name", ""); 
		LH.add(getUnlocalizedName()+".10.name", ""); 
		LH.add(getUnlocalizedName()+".11.name", ""); 
		LH.add(getUnlocalizedName()+".12.name", ""); 
		LH.add(getUnlocalizedName()+".13.name", ""); 
		LH.add(getUnlocalizedName()+".14.name", ""); 
		LH.add(getUnlocalizedName()+".15.name", ""); */
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMeta) {/*
		switch(aMeta) {
		case  0: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  1: return Textures.BlockIcons.MACHINE_CASING_CLEAN_STAINLESSSTEEL			.getIcon(0);
		case  2: return Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM				.getIcon(0);
		case  3: return aSide > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_TITANIUM	.getIcon(0) : Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM	.getIcon(0);
		case  4: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  5: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  6: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  7: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  8: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case  9: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 10: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 11: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 12: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 13: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 14: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		case 15: return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL			.getIcon(0);
		}*/
		return null;
	}
}