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

package gregapi.block.behaviors;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.prefixblock.PrefixBlock;
import gregapi.block.prefixblock.PrefixBlockTileEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class Drops {
	private final Item mDropNormal, mDropSilkTouch, mDropFortune, mDropSilkFortune;
	private final boolean mFortunable, mPreferSilk;
	
	public Drops(Item  aDropNormal) {this(aDropNormal, aDropNormal, aDropNormal, aDropNormal, F, F);}
	public Drops(Block aDropNormal) {this(ST.item(aDropNormal), ST.item(aDropNormal), ST.item(aDropNormal), ST.item(aDropNormal), F, F);}
	public Drops(Block aDropNormal, Block aDropSilkTouch) {this(ST.item(aDropNormal), ST.item(aDropSilkTouch), ST.item(aDropNormal), ST.item(aDropSilkTouch), F, F);}
	public Drops(Item  aDropNormal, Block aDropSilkTouch) {this(aDropNormal, ST.item(aDropSilkTouch), aDropNormal, ST.item(aDropSilkTouch), F, F);}
	public Drops(Block aDropNormal, Item  aDropSilkTouch) {this(ST.item(aDropNormal), aDropSilkTouch, ST.item(aDropNormal), aDropSilkTouch, F, F);}
	public Drops(Item  aDropNormal, Item  aDropSilkTouch) {this(aDropNormal, aDropSilkTouch, aDropNormal, aDropSilkTouch, F, F);}
	
	public Drops(Object aDropNormal, Object aDropSilkTouch, Object aDropFortune) {this(aDropNormal, aDropSilkTouch, aDropFortune, aDropFortune, T, F);}
	public Drops(Object aDropNormal, Object aDropSilkTouch, Object aDropFortune, Object aDropSilkFortune, boolean aFortunable, boolean aPreferSilk) {
		mDropNormal      = aDropNormal      instanceof Block ? ST.item((Block)aDropNormal     ) : (Item)aDropNormal     ;
		mDropSilkTouch   = aDropSilkTouch   instanceof Block ? ST.item((Block)aDropSilkTouch  ) : (Item)aDropSilkTouch  ;
		mDropFortune     = aDropFortune     instanceof Block ? ST.item((Block)aDropFortune    ) : (Item)aDropFortune    ;
		mDropSilkFortune = aDropSilkFortune instanceof Block ? ST.item((Block)aDropSilkFortune) : (Item)aDropSilkFortune;
		mFortunable      = aFortunable;
		mPreferSilk      = aPreferSilk;
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, int aFortune, boolean aSilkTouch) {
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		if (aTileEntity instanceof PrefixBlockTileEntity) return getDrops(aBlock, aWorld, aX, aY, aZ, aBlock.getMetaDataValue(aTileEntity), aTileEntity, aFortune, aSilkTouch);
		return new ArrayListNoNulls<>();
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, short aMetaData, TileEntity aTileEntity, int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = new ArrayListNoNulls<>();
		rList.add(ST.update(ST.make(aFortune>0?aSilkTouch?mDropFortune:mDropSilkFortune:aSilkTouch?mDropSilkTouch:mDropNormal, mPreferSilk&&aSilkTouch?1:mFortunable?1+RNGSUS.nextInt(aFortune+1):1, aMetaData, aTileEntity instanceof PrefixBlockTileEntity?((PrefixBlockTileEntity)aTileEntity).mItemNBT:null)));
		return rList;
	}
}
