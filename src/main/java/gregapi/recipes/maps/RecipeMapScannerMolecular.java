package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapScannerMolecular extends RecipeMap {
	public RecipeMapScannerMolecular(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (rRecipe != null || aInputs == null || aInputs.length < 2 || aInputs[0] == null || aInputs[1] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		
		ItemStack tUSB = null, tScanned = null;
		for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
			if (ST.invalid(tUSB) && OM.is_(OD_USB_STICKS[3], aInput)) tUSB = aInput; else tScanned = aInput;
			if (ST.valid(tUSB) && ST.valid(tScanned)) {
				assert tScanned != null; // just to make eclipse shut the fuck up
				OreDictItemData aData = OM.anydata_(tScanned);
				if (aData.mPrefix != null && aData.mMaterial != null && aData.mMaterial.mMaterial.mID > 0 && aData.mPrefix.contains(TD.Prefix.SCANNABLE)) {
					rRecipe = new Recipe(F, F, F, new ItemStack[] {ST.amount(Math.max(1, U / aData.mMaterial.mAmount), tScanned), ST.amount(1, tUSB)}, new ItemStack[] {ST.amount(1, tUSB)}, null, null, null, null, (aData.mMaterial.mMaterial.mProtons+aData.mMaterial.mMaterial.mNeutrons) * 512, 512, 0);
					if (!rRecipe.mOutputs[0].hasTagCompound()) rRecipe.mOutputs[0].setTagCompound(UT.NBT.make());
					rRecipe.mOutputs[0].getTagCompound().setTag(NBT_USB_DATA, UT.NBT.makeShort(NBT_REPLICATOR_DATA, aData.mMaterial.mMaterial.mID));
					rRecipe.mOutputs[0].getTagCompound().setByte(NBT_USB_TIER, (byte)3);
					return rRecipe;
				}
				return rRecipe;
			}
		}
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return super.containsInput(aStack, aTileEntity, aSpecialSlot) || OM.is(OD_USB_STICKS[3], aStack) || OM.prefixcontains(aStack, TD.Prefix.SCANNABLE);}
}