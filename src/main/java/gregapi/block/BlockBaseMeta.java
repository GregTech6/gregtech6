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

package gregapi.block;

import java.util.List;

import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseMeta extends BlockBaseSealable {
	public IIconContainer[] mIcons;
	/** For Creative Subsets, not actually important. */
	private final byte mMaxMeta;
	
	public BlockBaseMeta(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType);
		mMaxMeta = (byte)UT.Code.bind(1, 16, aMaxMeta);
		mIcons = aIcons;
	}
	
	@Override public byte maxMeta() {return mMaxMeta;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[aMeta % mIcons.length].getIcon(0);}
	@SuppressWarnings("unchecked") @Override public void getSubBlocks(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {for (int i = 0; i < maxMeta(); i++) aList.add(ST.make(aItem, 1, i));}
}
