/**
 * Copyright (c) 2023 GregTech-6 Team
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

import java.util.ArrayList;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class Drops {
	public final Item mDropNormal, mDropSilkTouch, mDropFortune, mDropSilkFortune;
	public final boolean mFortunable, mPreferSilk;
	public final int mExpBase, mExpRandom;
	
	@Deprecated public Drops(Item  aDropNormal) {this(aDropNormal, aDropNormal, aDropNormal, aDropNormal, F, F, 0, 0);}
	@Deprecated public Drops(Block aDropNormal) {this(aDropNormal, aDropNormal, aDropNormal, aDropNormal, F, F, 0, 0);}
	@Deprecated public Drops(Block aDropNormal, Block aDropSilkTouch) {this(aDropNormal, aDropSilkTouch, aDropNormal, aDropSilkTouch, F, F, 0, 0);}
	@Deprecated public Drops(Item  aDropNormal, Block aDropSilkTouch) {this(aDropNormal, aDropSilkTouch, aDropNormal, aDropSilkTouch, F, F, 0, 0);}
	@Deprecated public Drops(Block aDropNormal, Item  aDropSilkTouch) {this(aDropNormal, aDropSilkTouch, aDropNormal, aDropSilkTouch, F, F, 0, 0);}
	@Deprecated public Drops(Item  aDropNormal, Item  aDropSilkTouch) {this(aDropNormal, aDropSilkTouch, aDropNormal, aDropSilkTouch, F, F, 0, 0);}
	
	public Drops(int aExpBase, int aExpRandom) {this(null, null, null, null, F, F, aExpBase, aExpRandom);}
	public Drops(Object aDropNormal, Object aDropSilkTouch, Object aDropFortune) {this(aDropNormal, aDropSilkTouch, aDropFortune, aDropFortune, T, F, 0, 0);}
	public Drops(Object aDropNormal, Object aDropSilkTouch, Object aDropFortune, int aExpBase, int aExpRandom) {this(aDropNormal, aDropSilkTouch, aDropFortune, aDropFortune, T, F, aExpBase, aExpRandom);}
	public Drops(Object aDropNormal, Object aDropSilkTouch, Object aDropFortune, Object aDropSilkFortune, boolean aFortunable, boolean aPreferSilk, int aExpBase, int aExpRandom) {
		mDropNormal      = aDropNormal      instanceof Block ? ST.item((Block)aDropNormal     ) : (Item)aDropNormal     ;
		mDropSilkTouch   = aDropSilkTouch   instanceof Block ? ST.item((Block)aDropSilkTouch  ) : (Item)aDropSilkTouch  ;
		mDropFortune     = aDropFortune     instanceof Block ? ST.item((Block)aDropFortune    ) : (Item)aDropFortune    ;
		mDropSilkFortune = aDropSilkFortune instanceof Block ? ST.item((Block)aDropSilkFortune) : (Item)aDropSilkFortune;
		mFortunable      = aFortunable;
		mPreferSilk      = aPreferSilk;
		mExpBase         = Math.max(0, aExpBase);
		mExpRandom       = Math.max(0, aExpRandom);
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, int aFortune, boolean aSilkTouch) {
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		if (aTileEntity instanceof PrefixBlockTileEntity) return getDrops(aBlock, aWorld, aX, aY, aZ, aBlock.getMetaDataValue(aTileEntity), aTileEntity, aFortune, aSilkTouch);
		return ST.arraylist();
	}
	
	public ArrayList<ItemStack> getDrops(PrefixBlock aBlock, World aWorld, int aX, int aY, int aZ, short aMetaData, TileEntity aTileEntity, int aFortune, boolean aSilkTouch) {
		ArrayListNoNulls<ItemStack> rList = ST.arraylist();
		rList.add(ST.update(ST.make(aFortune>0?aSilkTouch?mDropFortune:mDropSilkFortune:aSilkTouch?mDropSilkTouch:mDropNormal, mPreferSilk&&aSilkTouch?1:mFortunable?1+RNGSUS.nextInt(aFortune+1):1, aMetaData, aTileEntity instanceof PrefixBlockTileEntity?((PrefixBlockTileEntity)aTileEntity).mItemNBT:null)));
		return rList;
	}
	
	public int getExp(PrefixBlock aBlock) {
		return mExpBase + RNGSUS.nextInt(1+mExpRandom);
	}
}
