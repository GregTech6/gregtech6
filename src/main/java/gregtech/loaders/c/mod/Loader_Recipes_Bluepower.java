package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;


public class Loader_Recipes_Bluepower implements Runnable {
	@Override
	public void run() {if (MD.BP.mLoaded) {OUT.println("GT_Mod: Doing BP Recipes.");
		for (OreDictMaterial tMat : ANY.Iron.mToThis) RM.Press.addRecipeX(T, 16, 256, new ItemStack[] {(tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat, 1), OP.foil.mat(MT.Zn, 2)}, ST.make(MD.BP, "zincplate", 2, 0));
		
		CR.shaped(ST.make(MD.BP, "iron_wire"		, 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Fe));
		CR.shaped(ST.make(MD.BP, "copper_wire"		, 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Cu));
		CR.shaped(ST.make(MD.BP, "silicon_wafer"	, 4, 0), CR.DEF_REV_NCC, "X ", " s", 'X', OP.plateGem.dat(ANY.Si));
		CR.shaped(ST.make(MD.BP, "red_doped_wafer"	, 4, 0), CR.DEF_REV_NCC, "X ", " s", 'X', OP.plateGem.dat(MT.RedstoneAlloy));
		CR.shaped(ST.make(MD.BP, "blue_doped_wafer"	, 4, 0), CR.DEF_REV_NCC, "X ", " s", 'X', OP.plateGem.dat(MT.TeslatineAlloy));
		CR.shaped(ST.make(MD.BP, "blue_doped_wafer"	, 4, 0), CR.DEF_NCC    , "X ", " s", 'X', OP.plateGem.dat(MT.NikolineAlloy));
	}}
}