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

package gregtech.blocks;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.block.IBlockOnWalkOver;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDiggable extends BlockBaseMeta implements IBlockOnWalkOver {
	public BlockDiggable(String aUnlocalised) {
		super(null, aUnlocalised, Material.ground, soundTypeGravel, 3, Textures.BlockIcons.DIGGABLES);
		LH.add(getUnlocalizedName()+ ".0.name", "Mud");
		LH.add(getUnlocalizedName()+ ".1.name", "Brown Clay");
		LH.add(getUnlocalizedName()+ ".2.name", "Turf");
		
		RM.generify(ST.make(this, 1, 1), ST.make(Blocks.clay, 1, 0));
		RM.add_smelting(ST.make(this, 1, 0), ST.make(Blocks.dirt, 1, 1));
		RM.add_smelting(ST.make(this, 1, 1), ST.make(Blocks.hardened_clay, 1, 0));
		OM.data(ST.make(this, 1, 1), MT.ClayBrown, U*4);
		OM.data(ST.make(this, 1, 2), MT.Peat, U);
		OM.reg(ST.make(this, 1, 1), "blockClay");
		
		BlocksGT.harvestableSpade.add(this);
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide, IPlantable aPlant) {
		if (aWorld.getBlockMetadata(aX, aY, aZ) == 1) return F;
		if (aPlant == Blocks.reeds || aPlant instanceof BlockBush) return T;
		EnumPlantType tType = aPlant.getPlantType(aWorld, aX, aY+1, aZ);
		return tType == EnumPlantType.Plains || tType == EnumPlantType.Water || tType == EnumPlantType.Desert || tType == EnumPlantType.Beach;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		switch(aMeta) {
		case  0: return new ArrayListNoNulls<>(F, IL.Mud_Ball.get(4));
		case  1: return new ArrayListNoNulls<>(F, IL.Clay_Ball_Brown.get(4));
		case  2: return new ArrayListNoNulls<>(F, OP.ingot.mat(MT.Peat, 1));
		default: return new ArrayListNoNulls<>(F, ST.make(this, 1, aMeta));
		}
	}
	
	@Override
	public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if (aWorld.getBlockMetadata(aX, aY, aZ) != 1) {aEntity.motionX *= 0.5; aEntity.motionZ *= 0.5;}
	}
	
	@Override public boolean useGravity      (int   aMeta) {return aMeta != 1;}
	@Override public boolean doesWalkSpeed   (short aMeta) {return aMeta != 1;}
	@Override public boolean doesPistonPush  (short aMeta) {return T;}
	@Override public boolean canCreatureSpawn(int   aMeta) {return aMeta < 3;}
	@Override public boolean isSealable      (int   aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool   (int   aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel     (int   aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.dirt.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.dirt.getExplosionResistance(null);}
}
