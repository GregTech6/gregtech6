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

package gregtech.blocks.tree;

import static gregapi.data.CS.*;

import java.util.Random;

import gregapi.block.tree.BlockBaseSapling;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockTreeSaplingCD extends BlockBaseSapling {
	public BlockTreeSaplingCD(String aUnlocalised) {
		super(null, aUnlocalised, Material.plants, soundTypeGrass, 1, Textures.BlockIcons.SAPLINGS_CD);
		LH.add(getUnlocalizedName()+ ".0.name", "Blue Spruce Sapling");
	//  LH.add(getUnlocalizedName()+ ".1.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".2.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".3.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".4.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".5.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".6.name", " Sapling");
	//  LH.add(getUnlocalizedName()+ ".7.name", " Sapling");
		LH.add(getUnlocalizedName()+ ".8.name", "Blue Spruce Sapling");
	//  LH.add(getUnlocalizedName()+ ".9.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".10.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".11.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".12.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".13.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".14.name", " Sapling");
	//  LH.add(getUnlocalizedName()+".15.name", " Sapling");
		
		for (int i = 0; i < maxMeta(); i++) {
			OM.reg(ST.make(this, 1, i), OP.treeSapling);
			OM.reg(ST.make(this, 1, i+8), OP.treeSapling);
		}
	}
	
	@Override
	public boolean grow(World aWorld, int aX, int aY, int aZ, byte aMeta, Random aRandom) {
		int tMaxHeight = 0;
		switch(aMeta & 7) {
		case 0:
			tMaxHeight = getMaxHeight(aWorld, aX, aY, aZ, 14);
			if (tMaxHeight < 14) return F;
			tMaxHeight = aY+tMaxHeight-aRandom.nextInt(3);
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) if (i != 0 || j != 0) if (!canPlaceTree(aWorld, aX+i, tMaxHeight-5, aZ+j)) return F;
			if (aWorld.isRemote) return T;
			WD.set(aWorld, aX, aY, aZ, BlocksGT.LogC, 0, 3);
			
			for (int tY = aY+1; tY < tMaxHeight; tY++) placeTree(aWorld, aX, tY, aZ, BlocksGT.LogC, 0);
			
			placeTree(aWorld, aX  , tMaxHeight  , aZ  , BlocksGT.Leaves_CD, 8);
			placeTree(aWorld, aX  , tMaxHeight+1, aZ  , BlocksGT.Leaves_CD, 8);
			placeTree(aWorld, aX+1, tMaxHeight-1, aZ  , BlocksGT.Leaves_CD, 8);
			placeTree(aWorld, aX-1, tMaxHeight-1, aZ  , BlocksGT.Leaves_CD, 8);
			placeTree(aWorld, aX  , tMaxHeight-1, aZ+1, BlocksGT.Leaves_CD, 8);
			placeTree(aWorld, aX  , tMaxHeight-1, aZ-1, BlocksGT.Leaves_CD, 8);
			
			for (int i = -5; i <= 5; i++) for (int j = -5; j <= 5; j++) if (i != 0 || j != 0) for (int k = 1; k+3 <= tMaxHeight; k++) {
				if (i*i + j*j < k*k*0.4) placeTree(aWorld, aX+i, tMaxHeight-k, aZ+j, BlocksGT.Leaves_CD, 8);
			}
			return T;
		}
		return F;
	}
}
