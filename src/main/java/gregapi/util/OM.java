/**
 * Copyright (c) 2021 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.util;

import static gregapi.data.CS.*;

import gregapi.code.ModData;
import gregapi.code.TagData;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 * 
 * Utility for accessing the OreDictUnificator Data in a more short manner. The Short Name is for ease of overview and stands for "OredictManager".
 */
public class OM {
	public static ItemStack set(ItemStack aStack) {
		return OreDictManager.INSTANCE.setStack(T, aStack);
	}
	public static ItemStack set_(ItemStack aStack) {
		return OreDictManager.INSTANCE.setStack_(T, aStack);
	}
	
	public static ItemStack get(ItemStack aStack) {
		return OreDictManager.INSTANCE.getStack(T, aStack);
	}
	public static ItemStack get_(ItemStack aStack) {
		return OreDictManager.INSTANCE.getStack_(T, aStack);
	}
	
	/** @Deprecated Well not really deprecated but I use aPrefix.mat(aMaterial, aStackSize) instead */
	public static ItemStack get(OreDictPrefix aPrefix, OreDictMaterial aMaterial, long aStackSize) {
		return OreDictManager.INSTANCE.getStack(aPrefix, aMaterial, aStackSize);
	}
	
	/** @Deprecated Well not really deprecated but I use aPrefix.mat(aMaterial, aReplacement, aStackSize) instead */
	public static ItemStack get(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aReplacement, long aStackSize) {
		return OreDictManager.INSTANCE.getStack(aPrefix, aMaterial, aReplacement, aStackSize);
	}
	
	public static boolean is(Object aName, ItemStack aStack) {
		return OreDictManager.isItemStackInstanceOf(aStack, aName.toString());
	}
	public static boolean is_(Object aName, ItemStack aStack) {
		return OreDictManager.isItemStackInstanceOf_(aStack, aName.toString());
	}
	
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictItemData aData) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), aData);
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterialStack aMaterial, OreDictMaterialStack... aByProducts) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aByProducts));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterialStack... aByProducts) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, aByProducts));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, aByProduct, aByProductAmount));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount, OreDictMaterial aByProduct7, long aByProduct7Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount), OM.stack(aByProduct7, aByProduct7Amount)));
	}
	public static void data(ModData aModID, String aModItemName, int aStackSize, int aMetaData, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount, OreDictMaterial aByProduct7, long aByProduct7Amount, OreDictMaterial aByProduct8, long aByProduct8Amount) {
		data(ST.make(aModID, aModItemName, aStackSize, aMetaData), new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount), OM.stack(aByProduct7, aByProduct7Amount), OM.stack(aByProduct8, aByProduct8Amount)));
	}
	public static void dat2(ModData aModID, String aModItemName, int aStackSize, OreDictItemData aData) {
		dat2(ST.make(aModID, aModItemName, aStackSize, 0), aData);
	}
	public static void dat2(ModData aModID, String aModItemName, int aStackSize, OreDictMaterialStack aMaterial, OreDictMaterialStack... aByProducts) {
		dat2(ST.make(aModID, aModItemName, aStackSize, 0), aMaterial, aByProducts);
	}
	public static void dat2(ModData aModID, String aModItemName, int aStackSize, OreDictMaterial aMaterial, long aAmount, OreDictMaterialStack... aByProducts) {
		dat2(ST.make(aModID, aModItemName, aStackSize, 0), aMaterial, aAmount, aByProducts);
	}
	public static void dat2(ModData aModID, String aModItemName, int aStackSize, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		dat2(ST.make(aModID, aModItemName, aStackSize, 0), aMaterial, aAmount, aByProduct, aByProductAmount);
	}
	public static void data(ItemStack aStack, OreDictItemData aData) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, aData);
	}
	public static void data(ItemStack aStack, OreDictMaterialStack aMaterial, OreDictMaterialStack... aByProducts) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aByProducts));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterialStack... aByProducts) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, aByProducts));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, aByProduct, aByProductAmount));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount, OreDictMaterial aByProduct7, long aByProduct7Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount), OM.stack(aByProduct7, aByProduct7Amount)));
	}
	public static void data(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProduct2Amount, OreDictMaterial aByProduct3, long aByProduct3Amount, OreDictMaterial aByProduct4, long aByProduct4Amount, OreDictMaterial aByProduct5, long aByProduct5Amount, OreDictMaterial aByProduct6, long aByProduct6Amount, OreDictMaterial aByProduct7, long aByProduct7Amount, OreDictMaterial aByProduct8, long aByProduct8Amount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(aStack, new OreDictItemData(aMaterial, aAmount, OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProduct2Amount), OM.stack(aByProduct3, aByProduct3Amount), OM.stack(aByProduct4, aByProduct4Amount), OM.stack(aByProduct5, aByProduct5Amount), OM.stack(aByProduct6, aByProduct6Amount), OM.stack(aByProduct7, aByProduct7Amount), OM.stack(aByProduct8, aByProduct8Amount)));
	}
	public static void dat2(ItemStack aStack, OreDictItemData aData) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(ST.copyMeta(0, aStack), aData.setUseVanillaDamage());
	}
	public static void dat2(ItemStack aStack, OreDictMaterialStack aMaterial, OreDictMaterialStack... aByProducts) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(ST.copyMeta(0, aStack), new OreDictItemData(aMaterial, aByProducts).setUseVanillaDamage());
	}
	public static void dat2(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterialStack... aByProducts) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(ST.copyMeta(0, aStack), new OreDictItemData(aMaterial, aAmount, aByProducts).setUseVanillaDamage());
	}
	public static void dat2(ItemStack aStack, OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		if (ST.valid(aStack)) OreDictManager.INSTANCE.addItemData_(ST.copyMeta(0, aStack), new OreDictItemData(aMaterial, aAmount, aByProduct, aByProductAmount).setUseVanillaDamage());
	}
	
	public static OreDictItemData data(String aOre) {
		OreDictPrefix tPrefix = OreDictPrefix.get(aOre);
		if (tPrefix == null) return null;
		OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_MAP.get(aOre.replaceFirst(tPrefix.mNameInternal, ""));
		if (tMaterial == null) return null;
		return new OreDictItemData(tPrefix, tMaterial);
	}
	
	public static OreDictItemData data(ItemStack aStack) {
		return OreDictManager.INSTANCE.getItemData(aStack, F);
	}
	public static OreDictItemData data_(ItemStack aStack) {
		return OreDictManager.INSTANCE.getItemData_(aStack, F);
	}
	
	public static OreDictItemData anydata(ItemStack aStack) {
		return OreDictManager.INSTANCE.getItemData(aStack, T);
	}
	public static OreDictItemData anydata_(ItemStack aStack) {
		return OreDictManager.INSTANCE.getItemData_(aStack, T);
	}
	
	public static OreDictItemData data(ItemStack aStack, boolean aAllowOverride) {
		return OreDictManager.INSTANCE.getItemData(aStack, aAllowOverride);
	}
	public static OreDictItemData data_(ItemStack aStack, boolean aAllowOverride) {
		return OreDictManager.INSTANCE.getItemData_(aStack, aAllowOverride);
	}
	
	public static void association(ItemStack aStack, OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		OreDictManager.INSTANCE.addAssociation(aPrefix, aMaterial, aStack);
	}
	public static void association_(ItemStack aStack, OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		OreDictManager.INSTANCE.addAssociation_(aPrefix, aMaterial, aStack);
	}
	
	public static boolean reg(ItemStack aStack, OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		return OreDictManager.INSTANCE.registerOre(aPrefix, aMaterial, aStack);
	}
	public static boolean reg_(ItemStack aStack, OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		return OreDictManager.INSTANCE.registerOre_(aPrefix, aMaterial, aStack);
	}
	
	public static boolean reg(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return OreDictManager.INSTANCE.registerOre(aPrefix, aMaterial, aStack);
	}
	public static boolean reg_(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
		return OreDictManager.INSTANCE.registerOre_(aPrefix, aMaterial, aStack);
	}
	
	public static boolean reg(ItemStack aStack, Object aName) {
		return OreDictManager.INSTANCE.registerOre(aName, aStack);
	}
	public static boolean reg_(ItemStack aStack, Object aName) {
		return OreDictManager.INSTANCE.registerOre_(aName, aStack);
	}
	
	public static boolean reg(Object aName, ItemStack aStack) {
		return OreDictManager.INSTANCE.registerOre(aName, aStack);
	}
	public static boolean reg_(Object aName, ItemStack aStack) {
		return OreDictManager.INSTANCE.registerOre_(aName, aStack);
	}
	
	public static OreDictItemData association(ItemStack aStack) {
		return OreDictManager.INSTANCE.getAssociation(aStack, F);
	}
	public static OreDictItemData association_(ItemStack aStack) {
		return OreDictManager.INSTANCE.getAssociation_(aStack, F);
	}
	
	public static OreDictItemData anyassociation(ItemStack aStack) {
		return OreDictManager.INSTANCE.getAssociation(aStack, T);
	}
	public static OreDictItemData anyassociation_(ItemStack aStack) {
		return OreDictManager.INSTANCE.getAssociation_(aStack, T);
	}
	
	public static OreDictItemData association(ItemStack aStack, boolean aAllowOverride) {
		return OreDictManager.INSTANCE.getAssociation(aStack, aAllowOverride);
	}
	public static OreDictItemData association_(ItemStack aStack, boolean aAllowOverride) {
		return OreDictManager.INSTANCE.getAssociation_(aStack, aAllowOverride);
	}
	
	public static boolean prefixcontainsmaterialmatches(ItemStack aStack, OreDictMaterial aMaterial, TagData... aTags) {
		return prefixcontainsmaterialmatches(anydata(aStack), aMaterial, aTags);
	}
	public static boolean prefixcontainsmaterialmatches_(ItemStack aStack, OreDictMaterial aMaterial, TagData... aTags) {
		return prefixcontainsmaterialmatches(anydata_(aStack), aMaterial, aTags);
	}
	
	public static boolean prefixcontainsmaterialmatches(OreDictItemData aData, OreDictMaterial aMaterial, TagData... aTags) {
		return aData != null && aData.mPrefix != null && aData.mMaterial != null && (aMaterial == null || aData.mMaterial.mMaterial == aMaterial) && aData.mPrefix.containsAll(aTags);
	}
	
	public static boolean prefixcontains(ItemStack aStack, TagData... aTags) {
		return prefixcontains(anydata(aStack), aTags);
	}
	public static boolean prefixcontains_(ItemStack aStack, TagData... aTags) {
		return prefixcontains(anydata_(aStack), aTags);
	}
	
	public static boolean prefixcontains(OreDictItemData aData, TagData... aTags) {
		return aData != null && aData.mPrefix != null && aData.mPrefix.containsAll(aTags);
	}
	public static boolean prefixcontainsany(OreDictItemData aData, TagData... aTags) {
		return aData != null && aData.mPrefix != null && aData.mPrefix.containsAny(aTags);
	}
	
	public static boolean materialcontains(ItemStack aStack, TagData... aTags) {
		return materialcontains(anydata(aStack), aTags);
	}
	public static boolean materialcontains_(ItemStack aStack, TagData... aTags) {
		return materialcontains(anydata_(aStack), aTags);
	}
	public static boolean materialcontains(OreDictItemData aData, TagData... aTags) {
		return aData != null && aData.mMaterial != null && aData.mMaterial.mMaterial.containsAll(aTags);
	}
	public static boolean materialcontainsany(OreDictItemData aData, TagData... aTags) {
		return aData != null && aData.mMaterial != null && aData.mMaterial.mMaterial.containsAny(aTags);
	}
	
	public static boolean materialcontained(ItemStack aStack, OreDictMaterial... aMaterials) {
		return materialcontained(anydata(aStack), F, aMaterials);
	}
	public static boolean materialcontained_(ItemStack aStack, OreDictMaterial... aMaterials) {
		return materialcontained(anydata_(aStack), F, aMaterials);
	}
	public static boolean materialcontained(OreDictItemData aData, OreDictMaterial... aMaterials) {
		return materialcontained(aData, F, aMaterials);
	}
	public static boolean materialcontained(ItemStack aStack, boolean aReturnWhenNoData, OreDictMaterial... aMaterials) {
		return materialcontained(anydata(aStack), aReturnWhenNoData, aMaterials);
	}
	public static boolean materialcontained_(ItemStack aStack, boolean aReturnWhenNoData, OreDictMaterial... aMaterials) {
		return materialcontained(anydata_(aStack), aReturnWhenNoData, aMaterials);
	}
	public static boolean materialcontained(OreDictItemData aData, boolean aReturnWhenNoData, OreDictMaterial... aMaterials) {
		if (aData == null) return aReturnWhenNoData;
		for (OreDictMaterial aMaterial : aMaterials) for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) if (aMaterial == tMaterial.mMaterial) return T; return F;
	}
	
	public static void blacklist(ItemStack aStack) {
		OreDictManager.INSTANCE.addToBlacklist(aStack);
	}
	public static void blacklist_(ItemStack aStack) {
		OreDictManager.INSTANCE.addToBlacklist_(aStack);
	}
	
	public static long total(Iterable<OreDictMaterialStack> aList) {
		long rAmount = 0;
		for (OreDictMaterialStack tStack : aList) if (tStack != null && tStack.mMaterial != MT.NULL) rAmount += tStack.mAmount;
		return rAmount;
	}
	
	public static double weight(ItemStack aStack) {
		OreDictItemData tData = OM.anydata(aStack);
		return tData == null ? 0 : aStack.stackSize * weight(tData.getAllMaterialStacks());
	}
	public static double weight_(ItemStack aStack) {
		OreDictItemData tData = OM.anydata_(aStack);
		return tData == null ? 0 : aStack.stackSize * weight(tData.getAllMaterialStacks());
	}
	
	public static double weight(Iterable<OreDictMaterialStack> aList) {
		double rWeight = 0;
		for (OreDictMaterialStack tStack : aList) if (tStack != null && tStack.mMaterial != MT.NULL) rWeight += tStack.weight();
		return rWeight;
	}
	
	
	public static ItemStack crushed(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushed.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : crushedPurified(aMaterial, aStackSize);
	}
	public static ItemStack crushedTiny(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushedTiny.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : crushedPurifiedTiny(aMaterial, aStackSize);
	}
	public static ItemStack crushedPurified(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushedPurified.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : crushedCentrifuged(aMaterial, aStackSize);
	}
	public static ItemStack crushedPurifiedTiny(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushedPurifiedTiny.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : crushedCentrifugedTiny(aMaterial, aStackSize);
	}
	public static ItemStack crushedCentrifuged(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushedCentrifuged.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : OP.dust.mat(aMaterial, aStackSize);
	}
	public static ItemStack crushedCentrifugedTiny(OreDictMaterial aMaterial, long aStackSize) {
		ItemStack rStack = OP.crushedCentrifugedTiny.mat(aMaterial, aStackSize);
		return ST.valid(rStack) ? rStack : OP.dustTiny.mat(aMaterial, aStackSize);
	}
	
	
	public static ItemStack gem(OreDictMaterialStack aMaterial) {
		return aMaterial==null?null:gem(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack gem(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		return aMaterial==null?null:gem(aMaterial, aPrefix.mAmount);
	}
	
	
	public static ItemStack pulverize(OreDictMaterialStack aMaterial) {
		return aMaterial==null?null:pulverize(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack pulverize(OreDictMaterial aMaterial, long aMaterialAmount) {
		return dust(aMaterial.mTargetPulver.mMaterial, UT.Code.units(aMaterialAmount, U, aMaterial.mTargetPulver.mAmount, F));
	}
	
	public static ItemStack dust(OreDictMaterial aMaterial) {
		return aMaterial==null?null:dust(aMaterial, U);
	}
	
	public static ItemStack dust(OreDictMaterialStack aMaterial) {
		return aMaterial==null?null:dust(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack dust(OreDictMaterialStack aMaterial, long aMultiplier, long aDivider) {
		return aMaterial==null?null:dust(aMaterial.mMaterial, (aMaterial.mAmount * aMultiplier) / aDivider);
	}
	
	public static ItemStack dust(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		return aMaterial==null?null:dust(aMaterial, aPrefix.mAmount);
	}
	
	
	public static ItemStack ingot(OreDictMaterial aMaterial) {
		return aMaterial==null?null:ingot(aMaterial, U);
	}
	
	public static ItemStack ingot(OreDictMaterialStack aMaterial) {
		return aMaterial==null?null:ingot(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack ingot(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		return aMaterial==null?null:ingot(aMaterial, aPrefix.mAmount);
	}
	
	public static ItemStack solid(OreDictMaterialStack aMaterial) {
		return aMaterial==null?null:solid(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack solid(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		return aMaterial==null?null:solid(aMaterial, aPrefix.mAmount);
	}
	
	public static ItemStack ingotOrDust(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount <= 0 || aMaterial == null) return null;
		ItemStack rStack = ingot(aMaterial, aMaterialAmount);
		if (rStack == null) rStack = dust(aMaterial, aMaterialAmount);
		return rStack;
	}
	
	public static ItemStack ingotOrDust(OreDictMaterialStack aMaterial) {
		return aMaterial == null ? null : ingotOrDust(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack dustOrIngot(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount <= 0 || aMaterial == null) return null;
		ItemStack rStack = dust(aMaterial, aMaterialAmount);
		return rStack == null ? ingot(aMaterial, aMaterialAmount) : rStack;
	}
	
	public static ItemStack dustOrIngot(OreDictMaterialStack aMaterial) {
		return aMaterial == null ? null : dustOrIngot(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack solidOrDust(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount <= 0 || aMaterial == null) return null;
		ItemStack rStack = solid(aMaterial, aMaterialAmount);
		return rStack == null ? dust(aMaterial, aMaterialAmount) : rStack;
	}
	
	public static ItemStack solidOrDust(OreDictMaterialStack aMaterial) {
		return aMaterial == null ? null : solidOrDust(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack dustOrSolid(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount <= 0 || aMaterial == null) return null;
		ItemStack rStack = dust(aMaterial, aMaterialAmount);
		return rStack == null ? solid(aMaterial, aMaterialAmount) : rStack;
	}
	
	public static ItemStack dustOrSolid(OreDictMaterialStack aMaterial) {
		return aMaterial == null ? null : dustOrSolid(aMaterial.mMaterial, aMaterial.mAmount);
	}
	
	public static ItemStack gem(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount < U4 || aMaterial == null) return null;
									if (aMaterialAmount >= U * 72                                                           )   {ItemStack rStack = OP.blockGem     .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   /(U*9))); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U )  if (aMaterialAmount >= U * 32 || (((aMaterialAmount %  U   <= aMaterialAmount % (U2 )))))   {ItemStack rStack = OP.gem          .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   / U   )); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U2)  if (aMaterialAmount >= U * 16 || (((aMaterialAmount % (U2) <= aMaterialAmount % (U4 )))))   {ItemStack rStack = OP.gemFlawed    .mat(aMaterial, UT.Code.bindStack((aMaterialAmount*2)/ U   )); if (rStack != null) return rStack;}
		return OP.gemChipped.mat(aMaterial, UT.Code.bindStack((aMaterialAmount*4)/U));
	}
	
	public static ItemStack dust(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount < U72 || aMaterial == null) return null;
									if (aMaterialAmount >= U * 72                                                           )   {ItemStack rStack = OP.blockDust    .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   /(U*9))); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U )  if (aMaterialAmount >= U * 16 || (( aMaterialAmount % U == 0                          )))   {ItemStack rStack = OP.dust         .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   / U   )); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U4)  if (aMaterialAmount >= U *  8 || (((aMaterialAmount % (U4) <= aMaterialAmount % (U9 )))))   {ItemStack rStack = OP.dustSmall    .mat(aMaterial, UT.Code.bindStack((aMaterialAmount*4)/ U   )); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U9)  if (aMaterialAmount >= U      || (((aMaterialAmount % (U9) <= aMaterialAmount % (U72)))))   {ItemStack rStack = OP.dustTiny     .mat(aMaterial, UT.Code.bindStack((aMaterialAmount*9)/ U   )); if (rStack != null) return rStack;}
		return OP.dustDiv72.mat(aMaterial, UT.Code.bindStack((aMaterialAmount*72)/U));
	}
	
	public static ItemStack ingot(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount < U9 || aMaterial == null) return null;
									if (aMaterialAmount >= U * 72                                                           )   {ItemStack rStack = OP.blockIngot   .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   /(U*9))); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U )  if (aMaterialAmount >= U * 16 || (( aMaterialAmount % U == 0                          )))   {ItemStack rStack = OP.ingot        .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   / U   )); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U4)  if (aMaterialAmount >= U *  8 || (((aMaterialAmount % (U4) <= aMaterialAmount % (U9 )))))   {ItemStack rStack = OP.chunkGt      .mat(aMaterial, UT.Code.bindStack((aMaterialAmount*4)/ U   )); if (rStack != null) return rStack;}
		return OP.nugget.mat(aMaterial, UT.Code.bindStack((aMaterialAmount*9)/U));
	}
	
	public static ItemStack solid(OreDictMaterial aMaterial, long aMaterialAmount) {
		if (aMaterialAmount *  9 < U || aMaterial == null) return null;
									if (aMaterialAmount >= U * 72                                                           )   {ItemStack rStack = OP.blockSolid   .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   /(U*9))); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U )  if (aMaterialAmount >= U * 16 || (( aMaterialAmount % U == 0                          )))   {ItemStack rStack = OP.ingot        .mat(aMaterial, UT.Code.bindStack( aMaterialAmount   / U   )); if (rStack != null) return rStack;}
		if (aMaterialAmount >= U4)  if (aMaterialAmount >= U *  8 || (((aMaterialAmount % (U4) <= aMaterialAmount % (U9 )))))   {ItemStack rStack = OP.chunkGt      .mat(aMaterial, UT.Code.bindStack((aMaterialAmount*4)/ U   )); if (rStack != null) return rStack;}
		return OP.nugget.mat(aMaterial, UT.Code.bindStack((aMaterialAmount*9)/U));
	}
	
	public static OreDictMaterialStack stack(OreDictMaterial aMaterial, long aAmount) {return aMaterial==null?null:new OreDictMaterialStack(aMaterial, aAmount);}
	public static OreDictMaterialStack stack(long aAmount, OreDictMaterial aMaterial) {return aMaterial==null?null:new OreDictMaterialStack(aMaterial, aAmount);}
	public static OreDictMaterialStack stack(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {return aMaterial==null||aPrefix==null?null:new OreDictMaterialStack(aMaterial, aPrefix.mAmount);}
	public static OreDictMaterialStack stack(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {return aMaterial==null||aPrefix==null?null:new OreDictMaterialStack(aMaterial, aPrefix.mAmount);}
}
