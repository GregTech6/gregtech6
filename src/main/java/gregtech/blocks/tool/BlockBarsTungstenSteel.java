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

package gregtech.blocks.tool;

import gregapi.block.misc.BlockBaseBars;
import gregapi.data.LH;
import gregapi.data.MT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.world.IBlockAccess;

public class BlockBarsTungstenSteel extends BlockBaseBars {
	public BlockBarsTungstenSteel(String aNameInternal) {
		super(aNameInternal, MT.TungstenSteel, Material.iron, Block.soundTypeMetal);
		LH.add(getUnlocalizedName()+ ".0", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".1", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".2", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".3", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".4", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".5", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".6", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".7", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".8", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+ ".9", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".10", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".11", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".12", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".13", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".14", "Tungstensteel Bars");
		LH.add(getUnlocalizedName()+".15", "Tungstensteel Bars");
	}
	
	@Override public float getExplosionResistance(byte aMeta) {return 16;}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess aWorld, int aX, int aY, int aZ, Entity aEntity) {
		return !(aEntity instanceof EntityWither || aEntity instanceof EntityDragon);
	}
}
