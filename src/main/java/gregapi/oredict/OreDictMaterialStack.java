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

package gregapi.oredict;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.MT;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public final class OreDictMaterialStack implements Cloneable {
	public long mAmount;
	public OreDictMaterial mMaterial;
	
	public OreDictMaterialStack(OreDictMaterial aMaterial, long aAmount) {
		mMaterial = aMaterial==null?MT.NULL:aMaterial;
		mAmount = aAmount;
	}
	
	public OreDictMaterialStack copy(long aAmount) {
		return new OreDictMaterialStack(mMaterial, aAmount);
	}
	
	@Override
	public OreDictMaterialStack clone() {
		return new OreDictMaterialStack(mMaterial, mAmount);
	}
	
	public OreDictMaterialStack div(long aDivider) {
		return new OreDictMaterialStack(mMaterial, mAmount / aDivider);
	}
	
	public OreDictMaterialStack divup(long aDivider) {
		return new OreDictMaterialStack(mMaterial, UT.Code.divup(mAmount, aDivider));
	}
	
	public OreDictMaterialStack mul(long aMultiplier) {
		return new OreDictMaterialStack(mMaterial, aMultiplier * mAmount);
	}
	
	public OreDictMaterialStack div(long aMultiplier, long aDivider) {
		return new OreDictMaterialStack(mMaterial, (aMultiplier * mAmount) / aDivider);
	}
	
	public OreDictMaterialStack divup(long aMultiplier, long aDivider) {
		return new OreDictMaterialStack(mMaterial, UT.Code.divup(aMultiplier * mAmount, aDivider));
	}
	
	public double weight() {
		return mMaterial.getWeight(mAmount);
	}
	
	public boolean has(OreDictMaterial aMaterial) {
		return mMaterial == aMaterial && mAmount > 0;
	}
	
	@Override
	public boolean equals(Object aObject) {
		if (aObject == this || aObject == mMaterial) return T;
		if (aObject == null) return F;
		if (aObject instanceof OreDictMaterialStack) return ((OreDictMaterialStack)aObject).mMaterial == mMaterial && (mAmount < 0 || ((OreDictMaterialStack)aObject).mAmount < 0 || ((OreDictMaterialStack)aObject).mAmount == mAmount);
		return F;
	}
	
	@Override
	public String toString() {
		return mMaterial.toString() + " - " + mAmount;
	}
	
	@Override
	public int hashCode() {
		return mMaterial.hashCode();
	}
	
	public List<OreDictMaterialStack> addToList(List<OreDictMaterialStack> aList) {
		if (mAmount == 0) return aList;
		for (OreDictMaterialStack tMaterial : aList) if (tMaterial.mMaterial == mMaterial) {tMaterial.mAmount += mAmount; return aList;}
		aList.add(clone());
		return aList;
	}
	
	public NBTTagCompound save() {
		NBTTagCompound rNBT = UT.NBT.make();
		UT.NBT.setNumber(rNBT, "a", mAmount);
		if (mMaterial.mID < 0) {
			rNBT.setString("m", mMaterial.mNameInternal);
			return rNBT;
		}
		rNBT.setShort("i", mMaterial.mID);
		return rNBT;
	}
	
	public void save(String aTagName, NBTTagCompound aNBT) {
		aNBT.setTag(aTagName, save());
	}
	
	public static OreDictMaterialStack load(NBTTagCompound aNBT) {
		if (aNBT.hasKey("i")) return new OreDictMaterialStack(OreDictMaterial.MATERIAL_ARRAY[aNBT.getShort("i")], aNBT.getLong("a"));
		return new OreDictMaterialStack(OreDictMaterial.get(aNBT.getString("m")), aNBT.getLong("a"));
	}
	
	public static OreDictMaterialStack load(String aTagName, NBTTagCompound aNBT) {
		return load(aNBT.getCompoundTag(aTagName));
	}
	
	public static NBTTagCompound saveList(List<OreDictMaterialStack> aList) {
		NBTTagCompound rNBT = UT.NBT.make();
		if (aList == null) return rNBT;
		int l = 0;
		for (int i = 0, j = aList.size(); i < j; i++) {
			OreDictMaterialStack tStack = aList.get(i);
			if (tStack != null && tStack.mMaterial != MT.NULL) {
				rNBT.setTag(""+i, tStack.save());
				l++;
			}
		}
		rNBT.setInteger("size", l);
		return rNBT;
	}
	
	public static void saveList(String aTagName, NBTTagCompound aNBT, List<OreDictMaterialStack> aList) {
		aNBT.setTag(aTagName, saveList(aList));
	}
	
	public static List<OreDictMaterialStack> loadList(NBTTagCompound aNBT) {
		ArrayListNoNulls<OreDictMaterialStack> rList = new ArrayListNoNulls<>();
		if (aNBT == null) return rList;
		for (int i = 0, j = aNBT.getInteger("size"); i < j; i++) {
			OreDictMaterialStack tStack = load(""+i, aNBT);
			if (tStack.mMaterial != MT.NULL) rList.add(tStack);
		}
		return rList;
	}
	
	public static List<OreDictMaterialStack> loadList(String aTagName, NBTTagCompound aNBT) {
		return loadList(aNBT.getCompoundTag(aTagName));
	}
}
