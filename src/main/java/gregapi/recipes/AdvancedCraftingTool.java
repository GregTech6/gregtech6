package gregapi.recipes;

import static gregapi.data.CS.*;

import gregapi.code.ICondition;
import gregapi.data.CS.ToolsGT;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
public class AdvancedCraftingTool extends ShapelessOreRecipe implements ICraftingRecipeGT {
	public final ICondition mCondition;
	public final OreDictPrefix mToolHead;
	public final short mToolID;
	public final MultiItemTool mTool;
	
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, ICondition.TRUE, MT.Steel);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, OreDictMaterial aHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, ICondition.TRUE, aHead);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, ICondition aCondition) {this(ToolsGT.sMetaTool, aToolID, aToolHead, aCondition, MT.Steel);}
	public AdvancedCraftingTool(long aToolID, OreDictPrefix aToolHead, ICondition aCondition, OreDictMaterial aHead) {this(ToolsGT.sMetaTool, aToolID, aToolHead, aCondition, aHead);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead) {this(aTool, aToolID, aToolHead, ICondition.TRUE, MT.Steel);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, OreDictMaterial aHead) {this(aTool, aToolID, aToolHead, ICondition.TRUE, aHead);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, ICondition aCondition) {this(aTool, aToolID, aToolHead, aCondition, MT.Steel);}
	public AdvancedCraftingTool(MultiItemTool aTool, long aToolID, OreDictPrefix aToolHead, ICondition aCondition, OreDictMaterial aHead) {
		super(aTool.getToolWithStats(UT.Code.bind15(aToolID), aHead, aHead.mHandleMaterial), aToolHead.dat(aHead).toString(), OP.stick.dat(aHead.mHandleMaterial).toString());
		mTool = aTool;
		mCondition = aCondition;
		mToolHead = aToolHead;
		mToolID = UT.Code.bind15(aToolID);
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		ItemStack tStack = null;
		OreDictMaterial rHead = null, rRod = null;
		for (int i = 0; i < aGrid.getSizeInventory(); i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null) return F;
				if (tData.mPrefix == OP.stick) {
					if (rRod != null) return F;
					rRod = tData.mMaterial.mMaterial;
				} else if (tData.mPrefix == mToolHead) {
					if (rHead != null) return F;
					rHead = tData.mMaterial.mMaterial;
				} else {
					return F;
				}
			}
		}
		return rHead != null && rRod != null && mCondition.isTrue(rHead) && (rHead.mHandleMaterial == rRod || rRod.mReRegistrations.contains(rHead.mHandleMaterial));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		ItemStack tStack = null;
		OreDictMaterial rHead = null, rRod = null;
		for (int i = 0; i < aGrid.getSizeInventory(); i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null) continue;
				if (tData.mPrefix == OP.stick) {
					rRod = tData.mMaterial.mMaterial;
				} else if (tData.mPrefix == mToolHead) {
					rHead = tData.mMaterial.mMaterial;
				}
			}
		}
		return mTool.getToolWithStats(mToolID, rHead, rRod);
	}
	
	@Override public boolean isRemovableByGT() {return F;}
	@Override public boolean isAutocraftableByGT() {return T;}
	@Override public int getRecipeSize() {return 2;}
}