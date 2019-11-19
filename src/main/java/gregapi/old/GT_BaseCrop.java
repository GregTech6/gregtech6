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

package gregapi.old;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.util.ST;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class GT_BaseCrop extends CropCard {
	private String mName = "", mDiscoveredBy = "Gregorius Techneticies", mAttributes[];
	private int mTier = 0, mMaxSize = 0, mAfterHarvestSize = 0, mHarvestSize = 0, mStats[] = new int[5];
	private ItemStack mDrop = null, mSpecialDrops[] = null;
	
	public static ArrayList<GT_BaseCrop> sCropList = new ArrayListNoNulls<>();
	
	/**
	 * To create new Crops
	 * @param aCropName Name of the Crop
	 * @param aDiscoveredBy The one who discovered the Crop
	 * @param aDrop The Item which is dropped by the Crop. must be != null
	 * @param aBaseSeed Baseseed to plant this Crop. null == crossbreed only
	 * @param aTier tier of the Crop. forced to be >= 1
	 * @param aMaxSize maximum Size of the Crop. forced to be >= 3
	 * @param aGrowthSpeed how fast the Crop grows. if < 0 then its set to Tier*300
	 * @param aHarvestSize the size the Crop needs to be harvested. forced to be between 2 and max size
	 */
	public GT_BaseCrop(String aCropName, String aDiscoveredBy, ItemStack aDrop, ItemStack[] aSpecialDrops, ItemStack aBaseSeed, int aTier, int aMaxSize, int aGrowthSpeed, int aAfterHarvestSize, int aHarvestSize, int aStatChemical, int aStatFood, int aStatDefensive, int aStatColor, int aStatWeed, String[] aAttributes) {
		mName = aCropName.toLowerCase().replaceAll(" ", "");
		if (aDiscoveredBy != null && !aDiscoveredBy.equals("")) mDiscoveredBy = aDiscoveredBy;
		if (aDrop != null) {
			LH.add("gt.crop."+mName, aCropName);
			mDrop = ST.copy(aDrop);
			mSpecialDrops = aSpecialDrops;
			mTier = Math.max(1, aTier);
			mMaxSize = Math.max(3, aMaxSize);
//          mGrowthSpeed = aGrowthSpeed>0?aGrowthSpeed:mTier*300;
			mHarvestSize = Math.min(Math.max(aHarvestSize, 2), mMaxSize);
			mAfterHarvestSize = Math.min(Math.max(aAfterHarvestSize, 1), mMaxSize-1);
			mStats[0] = aStatChemical;
			mStats[1] = aStatFood;
			mStats[2] = aStatDefensive;
			mStats[3] = aStatColor;
			mStats[4] = aStatWeed;
			mAttributes = aAttributes;
			Crops.instance.registerCrop(this);
			if (aBaseSeed != null) Crops.instance.registerBaseSeed(aBaseSeed, this, 1, 1, 1, 1);
			sCropList.add(this);
		}
	}
	
	@Override
	public String displayName() {
		return LH.get("gt.crop."+mName);
	}
	
	@Override
	public byte getSizeAfterHarvest(ICropTile crop) {
		return (byte)mAfterHarvestSize;
	}
	
	@Override
	public String[] attributes() {
		return mAttributes;
	}
	
	@Override
	public String discoveredBy() {
		return mDiscoveredBy;
	}
	
	@Override
	public final boolean canGrow(ICropTile aCrop) {
		return aCrop.getSize()  < maxSize();
	}
	
	@Override
	public final boolean canBeHarvested(ICropTile aCrop) {
		return aCrop.getSize() >= mHarvestSize;
	}
	
	@Override
	public boolean canCross(ICropTile aCrop) {
		return aCrop.getSize() + 2 > maxSize();
	}
	
	@Override
	public int stat(int n) {
		if (n < 0 || n >= mStats.length) return 0;
		return mStats[n];
	}
	
	@Override
	public String name() {
		return mName;
	}
	
	@Override
	public int tier() {
		return mTier;
	}
	
	@Override
	public int maxSize() {
		return mMaxSize;
	}
	
	@Override
	public ItemStack getGain(ICropTile aCrop) {
		int tDrop = 0;
		if (mSpecialDrops != null && (tDrop = RNGSUS.nextInt(mSpecialDrops.length+4)) < mSpecialDrops.length && mSpecialDrops[tDrop] != null) {
			return ST.copy(mSpecialDrops[tDrop]);
		}
		return ST.copy(mDrop);
	}
	
	@Override
	public boolean rightclick(ICropTile aCrop, EntityPlayer aPlayer) {
		if (!canBeHarvested(aCrop)) return false;
		return aCrop.harvest(aPlayer==null?false:aPlayer instanceof EntityPlayerMP);
	}
	
	@Override
	public int getOptimalHavestSize(ICropTile crop) {
		return maxSize();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerSprites(IIconRegister iconRegister) {
		textures = new IIcon[maxSize()];
		for (int i = 1; i <= textures.length; i++) textures[i - 1] = iconRegister.registerIcon(RES_PATH_BLOCK + "crop/"+name()+"/"+i);
	}
}
