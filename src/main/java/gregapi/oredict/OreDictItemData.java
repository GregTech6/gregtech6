/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.oredict;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class OreDictItemData {
	public boolean mBlackListed = F;
	public boolean mBlocked = F;
	public boolean mUseVanillaDamage = F;
	public ItemStack mUnificationTarget = null;
	
	/** The OreDictPrefix if there is one assigned to this. */
	public final OreDictPrefix mPrefix;
	/** The OreDictMaterialStack containing the Main Material of this Item. The Amount is in Material Units (U) */
	public final OreDictMaterialStack mMaterial;
	/** The OreDictMaterialStack containing the remaining Byproduct Materials of this Item. The Amount is in Material Units (U). */
	public final OreDictMaterialStack[] mByProducts;
	
	public OreDictItemData(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		mPrefix = aPrefix;
		mMaterial = aMaterial==null?null:OM.stack(aMaterial, aPrefix.mAmount);
		mByProducts = aPrefix.mByProducts.isEmpty()?ZL_MS:aPrefix.mByProducts.toArray(ZL_MS);
	}
	
	public OreDictItemData(OreDictMaterialStack aMaterial, OreDictMaterialStack... aByProducts) {
		mPrefix = null;
		mMaterial = aMaterial==null?null:aMaterial.clone();
		mBlackListed = T;
		if (aByProducts == null) {
			mByProducts = ZL_MS;
		} else {
			OreDictMaterialStack[] tByProducts = aByProducts.length<1?ZL_MS:new OreDictMaterialStack[aByProducts.length];
			int j = 0;
			for (int i = 0; i < aByProducts.length; i++) if (aByProducts[i] != null) tByProducts[j++] = aByProducts[i].clone();
			mByProducts = j>0?new OreDictMaterialStack[j]:ZL_MS;
			for (int i = 0; i < mByProducts.length; i++) mByProducts[i] = tByProducts[i];
		}
	}
	
	public OreDictItemData(OreDictMaterial aMaterial, OreDictPrefix aAmount, OreDictMaterialStack... aByProducts) {
		this(OM.stack(aMaterial, aAmount), aByProducts);
	}
	public OreDictItemData(OreDictMaterial aMaterial, long aAmount, OreDictMaterialStack... aByProducts) {
		this(OM.stack(aMaterial, aAmount), aByProducts);
	}
	
	public OreDictItemData(OreDictMaterial aMaterial, OreDictPrefix aAmount, OreDictMaterial aByProduct, OreDictPrefix aByProductAmount) {
		this(OM.stack(aMaterial, aAmount), OM.stack(aByProduct, aByProductAmount));
	}
	public OreDictItemData(OreDictMaterial aMaterial, OreDictPrefix aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		this(OM.stack(aMaterial, aAmount), OM.stack(aByProduct, aByProductAmount));
	}
	public OreDictItemData(OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, OreDictPrefix aByProductAmount) {
		this(OM.stack(aMaterial, aAmount), OM.stack(aByProduct, aByProductAmount));
	}
	public OreDictItemData(OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount) {
		this(OM.stack(aMaterial, aAmount), OM.stack(aByProduct, aByProductAmount));
	}
	
	public OreDictItemData(OreDictMaterial aMaterial, long aAmount, OreDictMaterial aByProduct, long aByProductAmount, OreDictMaterial aByProduct2, long aByProductAmount2) {
		this(OM.stack(aMaterial, aAmount), OM.stack(aByProduct, aByProductAmount), OM.stack(aByProduct2, aByProductAmount2));
	}
	
	public OreDictItemData(Collection<OreDictItemData> aData) {
		this(aData.toArray(ZL_OREDICTITEMDATA));
	}
	
	public OreDictItemData(OreDictItemData... aData) {
		mPrefix = null;
		mBlackListed = T;
		
		ArrayList<OreDictMaterialStack> aList = new ArrayListNoNulls<>(), rList = new ArrayListNoNulls<>();
		
		for (OreDictItemData tData : aData) if (tData != null) {
			if (tData.hasValidMaterialData() && tData.mMaterial.mAmount > 0) aList.add(tData.mMaterial.clone());
			for (OreDictMaterialStack tMaterial : tData.mByProducts) if (tMaterial.mAmount > 0) aList.add(tMaterial.clone());
		}
		
		for (OreDictMaterialStack aMaterial : aList) {
			boolean temp = T;
			for (OreDictMaterialStack tMaterial : rList) if (aMaterial.mMaterial == tMaterial.mMaterial) {
				tMaterial.mAmount += aMaterial.mAmount;
				temp = F; break;
			}
			if (temp) rList.add(aMaterial.clone());
		}
		
		Collections.sort(rList, new Comparator<OreDictMaterialStack>() {@Override public int compare(OreDictMaterialStack a, OreDictMaterialStack b) {return a.mAmount == b.mAmount ? 0 : a.mAmount > b.mAmount ? -1 : +1;}});
		
		if (rList.isEmpty()) {
			mMaterial = null;
		} else {
			mMaterial = rList.get(0);
			rList.remove(0);
		}
		
		mByProducts = rList.toArray(ZL_MS);
	}
	
	public boolean hasValidPrefixMaterialData() {
		return mPrefix != null && mMaterial != null;
	}
	
	public boolean hasValidPrefixData() {
		return mPrefix != null;
	}
	
	public boolean hasValidMaterialData() {
		return mMaterial != null;
	}
	
	/** Utility Function for getting a List containing both, the Main Material and all the Byproduct Materials. The Amount is in Material Units (U). */
	public List<OreDictMaterialStack> getAllMaterialStacks() {
		ArrayListNoNulls<OreDictMaterialStack> rList = new ArrayListNoNulls<>(mByProducts.length + 1);
		if (hasValidMaterialData()) rList.add(mMaterial);
		rList.addAll(Arrays.asList(mByProducts));
		return rList;
	}
	
	/** Utility Function for getting a Byproduct Material at a certain Index, if it exists. The Amount is in Material Units (U). */
	public OreDictMaterialStack getByProduct(int aIndex) {
		return aIndex>=0&&aIndex<mByProducts.length?mByProducts[aIndex]:null;
	}
	
	public ItemStack getStack(long aAmount) {
		return mUnificationTarget == null ? mPrefix.mat(mMaterial.mMaterial, aAmount) : ST.amount(aAmount, mUnificationTarget);
	}
	
	@Override
	public String toString() {
		if (mPrefix == null || mMaterial == null) return "";
		return mPrefix.mNameInternal + mMaterial.mMaterial.mNameInternal;
	}
	
	public OreDictItemData setUseVanillaDamage() {
		mUseVanillaDamage = T;
		return this;
	}
	
	public static OreDictItemData copy(OreDictItemData aData) {
		return aData == null ? null : aData.copy();
	}
	
	public OreDictItemData copy() {
		OreDictItemData rData = mPrefix == null ? new OreDictItemData(mMaterial, mByProducts) : new OreDictItemData(mPrefix, mMaterial.mMaterial);
		rData.mUnificationTarget = mUnificationTarget;
		rData.mUseVanillaDamage = mUseVanillaDamage;
		rData.mBlackListed = mBlackListed;
		rData.mBlocked = mBlocked;
		return rData;
	}
}
