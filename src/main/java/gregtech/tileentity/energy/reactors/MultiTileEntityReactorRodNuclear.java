/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.tileentity.energy.reactors;

import static gregapi.data.CS.*;

import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorRodNuclear extends MultiTileEntityReactorRodBase {
	@Override
	public int getReactorRodNeutronEmission(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mNeutronCounts[aSlot] += 128;
		return 128 + (int)UT.Code.divup(aReactor.oNeutronCounts[aSlot], 8); // For 128 it goes up to 1280 if surrounded, or 512 each in a 2x2
	}
	
	@Override
	public boolean getReactorRodNeutronReaction(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {
		aReactor.mEnergy += aReactor.oNeutronCounts[aSlot];
		// TODO ROD DURABILITY
		return T;
	}
	
	@Override
	public int getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons) {
		aReactor.mNeutronCounts[aSlot] += aNeutrons;
		return 0;
	}
	
	@Override public ITexture getReactorRodTextureSides(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[1], mRGBa, T), BlockTextureDefault.get(sOverlays[1]));}
	@Override public ITexture getReactorRodTextureTop  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack) {return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[0], mRGBa, T), BlockTextureDefault.get(sOverlays[0]));}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.rods.nuclear";}
}
