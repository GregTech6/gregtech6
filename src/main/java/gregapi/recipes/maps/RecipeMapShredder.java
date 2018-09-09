package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapShredder extends RecipeMapSpecialSingleInput {
	public RecipeMapShredder(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	private List<Recipe> mBufferedDynamicRecipes = null;
	
	@Override
	public List<Recipe> getNEIAllRecipes() {
		List<Recipe> rList = super.getNEIAllRecipes();
		if (mBufferedDynamicRecipes == null) {
			mBufferedDynamicRecipes = new ArrayListNoNulls();
			for (OreDictMaterial tMaterial : OP.dust.mRegisteredMaterials) {
				for (ItemStackContainer tStack : tMaterial.mRegisteredItems) {
					mBufferedDynamicRecipes.add(getRecipeFor(tStack.toStack()));
				}
			}
		}
		rList.addAll(mBufferedDynamicRecipes);
		return rList;
	}
	
	@Override
	public List<Recipe> getNEIRecipes(ItemStack... aOutputs) {
		List<Recipe> rList = super.getNEIRecipes(aOutputs);
		for (ItemStack aOutput : aOutputs) {
			OreDictItemData aData = OM.anyassociation(aOutput);
			if (aData != null && aData.mPrefix.contains(TD.Prefix.DUST_BASED)) {
				for (ItemStackContainer tStack : aData.mMaterial.mMaterial.mRegisteredItems) {
					rList.add(getRecipeFor(tStack.toStack()));
				}
				break;
			}
		}
		return rList;
	}
	
	@Override
	protected Recipe getRecipeFor(ItemStack aInput) {
		OreDictItemData aData = OM.anydata(aInput);
		if (aData == null || (aData.mMaterial != null && aData.mMaterial.mMaterial.contains(TD.Atomic.ANTIMATTER)) || (aData.mPrefix != null && (UT.Fluids.getFluidForFilledItem(aInput, T) != null || aData.mPrefix.containsAny(TD.Prefix.DUST_BASED, TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_DIRTY) || !aData.mPrefix.contains(TD.Prefix.RECYCLABLE)))) return null;
		List<OreDictMaterialStack> tList = new ArrayListNoNulls();
		for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) if (tMaterial.mMaterial.mTargetPulver.mAmount > 0) OM.stack(UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetPulver.mAmount, F), tMaterial.mMaterial.mTargetPulver.mMaterial).addToList(tList);
		if (tList.isEmpty()) return null;
		ItemStack[] tOutputs = new ItemStack[Math.min(tList.size(), mOutputItemsCount)];
		int i = 0, tDuration = 0;
		for (OreDictMaterialStack tMaterial : tList) {
			tDuration += UT.Code.units(tMaterial.mAmount, U, 256*Math.max(1, tMaterial.mMaterial.mToolQuality+1), T);
			if (i < tOutputs.length) {
				ItemStack tStack = OM.dust(tMaterial);
				if (tStack != null) tOutputs[i++] = tStack;
			}
		}
		if (!UT.Code.exists(0, tOutputs)) return null;
		return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, aInput)}, tOutputs, null, null, ZL_FS, ZL_FS, Math.max(1, tDuration), 16, 0);
	}
}