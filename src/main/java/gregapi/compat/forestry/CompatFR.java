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

package gregapi.compat.forestry;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import forestry.api.farming.Farmables;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import forestry.core.utils.vect.Vect;
import forestry.farming.logic.CropBlock;
import gregapi.block.ItemBlockBase;
import gregapi.block.tree.BlockBaseSapling;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.compat.CompatBase;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CompatFR extends CompatBase implements ICompatFR, IFarmable {
	public static ItemStackSet<ItemStackContainer> mWindfalls = new ItemStackSet<>();
	
	@Override
	public void onPostLoad(FMLPostInitializationEvent aEvent) {
		Farmables.farmables.get("farmArboreal").add(this);
	}
	
	@Override
	public void addWindfall(ItemStack aStack) {
		if (ST.valid(aStack)) mWindfalls.add(aStack);
	}
	
	@Override
	public boolean isSaplingAt(World aWorld, int aX, int aY, int aZ) {
		return aWorld.getBlock(aX, aY, aZ) instanceof BlockBaseSapling;
	}
	
	@Override
	public ICrop getCropAt(World aWorld, int aX, int aY, int aZ) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		return aBlock.isWood(aWorld, aX, aY, aZ) ? new CropBlock(aWorld, aBlock, aWorld.getBlockMetadata(aX, aY, aZ), new Vect(aX, aY, aZ)) : null;
	}
	
	@Override
	public boolean isGermling(ItemStack aStack) {
		return aStack.getItem() instanceof ItemBlockBase && ((ItemBlockBase)aStack.getItem()).mPlaceable instanceof BlockBaseSapling;
	}
	
	@Override
	public boolean isWindfall(ItemStack aStack) {
		return mWindfalls.contains(aStack, T);
	}
	
	@Override
	public boolean plantSaplingAt(EntityPlayer aPlayer, ItemStack aSeed, World aWorld, int aX, int aY, int aZ) {
		return aSeed.copy().tryPlaceItemIntoWorld(aPlayer, aWorld, aX, aY - 1, aZ, SIDE_UP, 0, 0, 0);
	}
}
