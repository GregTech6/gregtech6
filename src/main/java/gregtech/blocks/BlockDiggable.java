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

package gregtech.blocks;

import static gregapi.data.CS.*;

import java.util.ArrayList;

import gregapi.block.BlockBaseMeta;
import gregapi.block.IBlockOnWalkOver;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.old.Textures;
import gregapi.render.BlockTextureCopied;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.WD;
import mods.railcraft.common.carts.EntityTunnelBore;
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
	public static boolean[] IS_CLAY = {F,T,F,T,T,T,T,F,F,F,F,F,F,F,F,F};
	
	public BlockDiggable(String aUnlocalised) {
		super(null, aUnlocalised, Material.ground, soundTypeGravel, 7, Textures.BlockIcons.DIGGABLES);
		LH.add(getUnlocalizedName()+ ".0.name", "Mud");
		LH.add(getUnlocalizedName()+ ".1.name", "Brown Clay");
		LH.add(getUnlocalizedName()+ ".2.name", "Turf");
		LH.add(getUnlocalizedName()+ ".3.name", "Red Clay");
		LH.add(getUnlocalizedName()+ ".4.name", "Yellow Clay");
		LH.add(getUnlocalizedName()+ ".5.name", "Blue Clay");
		LH.add(getUnlocalizedName()+ ".6.name", "White Clay");
		
		MT.UNUSED.Mud  .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 0);
		MT.ClayBrown   .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 1);
		MT.Peat        .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 2);
		MT.ClayRed     .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 3);
		MT.Bentonite   .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 4);
		MT.Palygorskite.mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 5);
		MT.Kaolinite   .mTextureSolid = BlockTextureCopied.get(this, SIDE_TOP, 6);
		
		RM.generify(ST.make(this, 1, 1), ST.make(Blocks.clay, 1, 0));
		RM.generify(ST.make(this, 1, 3), ST.make(Blocks.clay, 1, 0));
		RM.generify(ST.make(this, 1, 4), ST.make(Blocks.clay, 1, 0));
		RM.generify(ST.make(this, 1, 5), ST.make(Blocks.clay, 1, 0));
		RM.generify(ST.make(this, 1, 6), ST.make(Blocks.clay, 1, 0));
		RM.add_smelting(ST.make(this, 1, 0), ST.make(Blocks.dirt         , 1, 1), F, F, F);
		RM.add_smelting(ST.make(this, 1, 1), ST.make(Blocks.hardened_clay, 1, 0), F, F, T);
		RM.add_smelting(ST.make(this, 1, 3), ST.make(Blocks.hardened_clay, 1, 0), F, F, T);
		RM.add_smelting(ST.make(this, 1, 4), ST.make(Blocks.hardened_clay, 1, 0), F, F, T);
		RM.add_smelting(ST.make(this, 1, 5), ST.make(Blocks.hardened_clay, 1, 0), F, F, T);
		RM.add_smelting(ST.make(this, 1, 6), ST.make(Blocks.hardened_clay, 1, 0), F, F, T);
		OM.data(ST.make(this, 1, 1), MT.ClayBrown, U*4);
		OM.data(ST.make(this, 1, 2), MT.Peat, U*4);
		OM.data(ST.make(this, 1, 3), MT.ClayRed, U*4);
		OM.data(ST.make(this, 1, 4), MT.Bentonite, U*4);
		OM.data(ST.make(this, 1, 5), MT.Palygorskite, U*4);
		OM.data(ST.make(this, 1, 6), MT.Kaolinite, U*4);
		OM.reg(ST.make(this, 1, 1), OD.blockClay);
		OM.reg(ST.make(this, 1, 3), OD.blockClay);
		OM.reg(ST.make(this, 1, 4), OD.blockClay);
		OM.reg(ST.make(this, 1, 5), OD.blockClay);
		OM.reg(ST.make(this, 1, 6), OD.blockClay);
		
		BlocksGT.harvestableSpade.add(this);
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		if (COMPAT_FR  != null) COMPAT_FR.addToBackpacks("digger", ST.make(this, 1, W));
	}
	
	@Override
	public boolean canSustainPlant(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide, IPlantable aPlant) {
		if (IS_CLAY[WD.meta(aWorld, aX, aY, aZ)]) return F;
		if (aPlant == Blocks.reeds || aPlant instanceof BlockBush) return T;
		EnumPlantType tType = aPlant.getPlantType(aWorld, aX+aSide.offsetX, aY+aSide.offsetY, aZ+aSide.offsetZ);
		return tType == EnumPlantType.Plains || tType == EnumPlantType.Water || tType == EnumPlantType.Desert || tType == EnumPlantType.Beach;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
		switch(aMeta) {
		case  0: return new ArrayListNoNulls<>(F, IL.Mud_Ball.get(4));
		case  1: return new ArrayListNoNulls<>(F, IL.Clay_Ball_Brown.get(4));
		case  2: return new ArrayListNoNulls<>(F, OP.ingot.mat(MT.Peat, 4));
		case  3: return new ArrayListNoNulls<>(F, IL.Clay_Ball_Red.get(4));
		case  4: return new ArrayListNoNulls<>(F, IL.Clay_Ball_Yellow.get(4));
		case  5: return new ArrayListNoNulls<>(F, IL.Clay_Ball_Blue.get(4));
		case  6: return new ArrayListNoNulls<>(F, IL.Clay_Ball_White.get(4));
		default: return new ArrayListNoNulls<>(F, ST.make(this, 1, aMeta));
		}
	}
	
	@Override
	public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if (doesWalkSpeed(WD.meta(aWorld, aX, aY, aZ))) {aEntity.motionX *= 0.5; aEntity.motionZ *= 0.5;}
	}
	
	@Override public boolean useGravity      (byte aMeta) {return !IS_CLAY[aMeta];}
	@Override public boolean doesWalkSpeed   (byte aMeta) {return !IS_CLAY[aMeta];}
	@Override public boolean doesPistonPush  (byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return T;}
	@Override public boolean isSealable      (byte aMeta, byte aSide) {return F;}
	@Override public String getHarvestTool   (int  aMeta) {return TOOL_shovel;}
	@Override public int getHarvestLevel     (int  aMeta) {return 0;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.dirt.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(byte aMeta) {return Blocks.dirt.getExplosionResistance(null);}
}
