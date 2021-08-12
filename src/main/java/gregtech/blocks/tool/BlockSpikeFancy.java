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

package gregtech.blocks.tool;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.misc.BlockBaseSpike;
import gregapi.damage.DamageSources;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpikeFancy extends BlockBaseSpike {
	public BlockSpikeFancy(String aNameInternal) {
		super(aNameInternal, MT.Au, MT.Ag);
		LH.add(getUnlocalizedName()+ ".0.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".1.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".2.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".3.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".4.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".5.name" , "Gold Wall Spike");
		LH.add(getUnlocalizedName()+ ".6.name" , "Gold Block Spike");
		LH.add(getUnlocalizedName()+ ".7.name" , "Falling Gold Spike Block");
		LH.add(getUnlocalizedName()+ ".8.name" , "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".9.name" , "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".10.name", "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".11.name", "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".12.name", "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".13.name", "Silver Wall Spike");
		LH.add(getUnlocalizedName()+ ".14.name", "Silver Block Spike");
		LH.add(getUnlocalizedName()+ ".15.name", "Falling Silver Spike Block");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		if (aMeta < 8) {
			aList.add(LH.Chat.ORANGE + "Deals huge Damage to any Undead touching it!");
			aList.add(LH.Chat.ORANGE + "Does very low Damage to anything else!");
			aList.add(LH.Chat.ORANGE + "Doesn't work on Slimes and Iron Golems.");
		} else {
			aList.add(LH.Chat.ORANGE + "Deals huge Damage to any Enderman, Werewolf or Bear989Sr touching it!");
			aList.add(LH.Chat.ORANGE + "Does very low Damage to anything else!");
			aList.add(LH.Chat.ORANGE + "Doesn't work on Skeletons, Slimes and Iron Golems.");
		}
		if ((aMeta & 7) >= 6) {
			aList.add(LH.Chat.CYAN + "Works in all Directions, but only does half the Wall Spikes Damage!");
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aEntity instanceof EntityLivingBase) {
			if (aMeta < 8) {
				if (((EntityLivingBase)aEntity).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ? 20.0F : 10.0F));
				else if (!(aEntity instanceof EntityIronGolem || aEntity instanceof EntitySkeleton || aEntity instanceof EntitySlime))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ?  2.0F :  1.0F));
			} else {
				if (UT.Entities.isEnderCreature((EntityLivingBase)aEntity) || UT.Entities.isWereCreature((EntityLivingBase)aEntity))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ? 20.0F : 10.0F));
				else if (!(aEntity instanceof EntityIronGolem || aEntity instanceof EntitySkeleton || aEntity instanceof EntitySlime))
				aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ?  2.0F :  1.0F));
			}
		}
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess aWorld, int aX, int aY, int aZ, Entity aEntity) {
		return WD.meta(aWorld, aX, aY, aZ) < 8 ? !(aEntity instanceof EntityWither) : !(aEntity instanceof EntityDragon);
	}
}
