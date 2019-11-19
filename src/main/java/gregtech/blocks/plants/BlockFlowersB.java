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

package gregtech.blocks.plants;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.misc.BlockBaseFlower;
import gregapi.data.CS.BlocksGT;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFlowersB extends BlockBaseFlower implements Runnable {
	public BlockFlowersB(String aUnlocalised) {
		super(null, aUnlocalised, 6, Textures.BlockIcons.FLOWERS_B);
		LH.add(getUnlocalizedName()+ ".0.name", "Big Sagebrush"); // Gold, Antimony, Arsenic
		LH.add(getUnlocalizedName()+ ".1.name", "Four Wing Saltbush"); // Gold, Antimony, Arsenic
		LH.add(getUnlocalizedName()+ ".2.name", "Desert Trumpet"); // Gold, color might vary depending on other Minerals close by
		LH.add(getUnlocalizedName()+ ".3.name", "Copper Flower Plant"); // Copper, Nickel
		LH.add(getUnlocalizedName()+ ".4.name", "Prince's Plume"); // Selenium
		LH.add(getUnlocalizedName()+ ".5.name", "Thompsons Locoweed"); // Uranium
		
		GT.mAfterInit.add(this);
		BlocksGT.FLOWERS.add(this);
		
		OM.data(ST.make(this, 1, 0), MT.Wood, U);
		OM.data(ST.make(this, 1, 1), MT.Wood, U);
		
		for (int i = 2; i < mMaxMeta; i++) OM.reg(ST.make(this, 1, i), "flower");
	}
	
	@Override
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		switch(aMeta) {
		case  0: aList.add("Indicates presence of an Arsenic Deposit nearby"); break;
		case  1: aList.add("Indicates presence of an Antimony Deposit nearby"); break;
		case  2: aList.add("Indicates presence of a Gold Deposit nearby"); break;
		case  3: aList.add("Indicates presence of a Copper Deposit nearby"); break;
		case  4: aList.add("Indicates presence of a Redstone Deposit nearby"); break;
		case  5: aList.add("Indicates presence of an Uranium Deposit nearby"); break;
		}
	}
	
	@Override
	public void run() {
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], OM.dust(MT.Pink));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Squeezer .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], OM.dust(MT.Purple));
		
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], OM.dust(MT.Pink));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], OM.dust(MT.Yellow));
		RM.Juicer   .addRecipe1(T, 16, 16, ST.make(this, 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], OM.dust(MT.Purple));
		
		CR.shaped(ST.make(Items.stick, 2, 0), CR.DEF_NAC_NCC, "s", "X", 'X', ST.make(this, 1, 0));
		CR.shaped(ST.make(Items.stick, 2, 0), CR.DEF_NAC_NCC, "k", "X", 'X', ST.make(this, 1, 0));
		CR.shaped(ST.make(Items.stick, 2, 0), CR.DEF_NAC_NCC, "s", "X", 'X', ST.make(this, 1, 1));
		CR.shaped(ST.make(Items.stick, 2, 0), CR.DEF_NAC_NCC, "k", "X", 'X', ST.make(this, 1, 1));
		
		CR.shapeless(ST.make(Items.stick, 1, 0), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 0)});
		CR.shapeless(ST.make(Items.stick, 1, 0), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 1)});
		CR.shapeless(OM.dust(MT.Yellow), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 2)});
		CR.shapeless(OM.dust(MT.Pink), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 3)});
		CR.shapeless(OM.dust(MT.Yellow), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 4)});
		CR.shapeless(OM.dust(MT.Purple), CR.DEF_NAC_NCC, new Object[] {ST.make(this, 1, 5)});
		
		RM.biomass(ST.make(this, 8, W));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(this, 1, 0), ST.make(Items.stick, 2, 0));
		RM.ic2_extractor(ST.make(this, 1, 1), ST.make(Items.stick, 2, 0));
		RM.ic2_extractor(ST.make(this, 1, 2), OM.dust(MT.Yellow, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 3), OM.dust(MT.Pink, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 4), OM.dust(MT.Yellow, U * 2));
		RM.ic2_extractor(ST.make(this, 1, 5), OM.dust(MT.Purple, U * 2));
		}
	}
	
	@Override
	public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {
		return WD.oxygen(aWorld, aX, aY, aZ) && aWorld.getBlock(aX, aY - 1, aZ).canSustainPlant(aWorld, aX, aY - 1, aZ, ForgeDirection.UP, (IPlantable)Blocks.cactus);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return EnumPlantType.Desert;
	}
}
