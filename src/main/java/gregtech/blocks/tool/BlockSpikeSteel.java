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
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSpikeSteel extends BlockBaseSpike {
	public BlockSpikeSteel(String aNameInternal) {
		super(aNameInternal, MT.BlueSteel, MT.RedSteel); // If you were looking for the regular Steel Spike, that one is with the Sharp Spikes.
		LH.add(getUnlocalizedName()+ ".0.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".1.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".2.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".3.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".4.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".5.name" , "Blue Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".6.name" , "Blue Steel Block Spike");
		LH.add(getUnlocalizedName()+ ".7.name" , "Falling Blue Steel Spike Block");
		LH.add(getUnlocalizedName()+ ".8.name" , "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".9.name" , "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".10.name", "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".11.name", "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".12.name", "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".13.name", "Red Steel Wall Spike");
		LH.add(getUnlocalizedName()+ ".14.name", "Red Steel Block Spike");
		LH.add(getUnlocalizedName()+ ".15.name", "Falling Red Steel Spike Block");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		aList.add(LH.Chat.ORANGE + "Deals above average Damage to anything touching it!");
		aList.add(LH.Chat.ORANGE + "Doesn't work on Iron Golems.");
		
		if ((aMeta & 7) >= 6) {
			aList.add(LH.Chat.CYAN + "Works in all Directions, but only does half the Wall Spikes Damage!");
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		int aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aEntity instanceof EntityLivingBase && !(aEntity instanceof EntityIronGolem)) {
			aEntity.attackEntityFrom(DamageSources.getSpikeDamage(), TFC_DAMAGE_MULTIPLIER * ((aMeta & 7) < 6 ?  8.0F :  4.0F));
		}
	}
}
