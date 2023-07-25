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

package gregtech.tileentity.tools;

import gregapi.data.BI;
import gregapi.data.FL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.*;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBathingPotWood extends MultiTileEntityBathingPot {
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].contains(aFluidToFill)) return mTanksInput[i];
		if (FL.temperature(aFluidToFill) > mMaterial.mMeltingPoint || !FL.heavier(aFluidToFill) || !FL.simple(aFluidToFill) || FL.gas(aFluidToFill) || FL.acid(aFluidToFill) || FL.magic(aFluidToFill)) return null;
		for (int i = 0; i < mTanksInput.length; i++) if (mTanksInput[i].isEmpty()) return mTanksInput[i];
		return null;
	}
	
	@SuppressWarnings("hiding")
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/sides"),
	sTextureInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/insides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/top"),
	sTextureBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/bottom"),
	sTextureTableBottom = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/tablebottom"),
	sTextureTableSide   = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/colored/tableside"),
	sOverlaySides       = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/sides"),
	sOverlayInsides     = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/insides"),
	sOverlayTop         = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/top"),
	sOverlayBottom      = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/bottom"),
	sOverlayTableBottom = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/tablebottom"),
	sOverlayTableSide   = new Textures.BlockIcons.CustomIcon("machines/tools/bathing_pot_wood/overlay/tableside");
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case  0: return SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  2: return SIDE_X_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_X_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  1: return SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  3: return SIDE_Z_NEG  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureInsides, mRGBa), BlockTextureDefault.get(sOverlayInsides)):SIDE_Z_POS  == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureSides , mRGBa), BlockTextureDefault.get(sOverlaySides )):SIDE_TOP == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop, mRGBa), BlockTextureDefault.get(sOverlayTop)):null;
		case  4: return SIDE_TOP    == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTop    , mRGBa), BlockTextureDefault.get(sOverlayTop    )):SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureBottom, mRGBa), BlockTextureDefault.get(sOverlayBottom)):null;
		case  5:
			if (mDisplay == 0 || SIDE_TOP != aSide) return null;
			if (mDisplay < -1) {
				Fluid tFluid = FluidRegistry.getFluid(-mDisplay-2);
				if (tFluid == null) return BlockTextureCopied.get(Blocks.water, SIDE_ANY, 0, UNCOLOURED, F, F, F);
				return BlockTextureDefault.get(new IconContainerDefault(tFluid.getIcon()), tFluid.getColor());
			}
			if (UT.Code.exists(mDisplay, OreDictMaterial.MATERIAL_ARRAY)) return OreDictMaterial.MATERIAL_ARRAY[mDisplay].getTextureDust();
			return BlockTextureDefault.get(MT.NULL, OP.blockDust, CA_GRAY_128, F);
		case  6: return SIDE_TOP    == aSide?BI.nei():null;
		case  7: return SIDE_TOP    != aSide?SIDE_BOTTOM == aSide?BlockTextureMulti.get(BlockTextureDefault.get(sTextureTableBottom, mRGBa), BlockTextureDefault.get(sOverlayTableBottom)):BlockTextureMulti.get(BlockTextureDefault.get(sTextureTableSide, mRGBa), BlockTextureDefault.get(sOverlayTableSide)):null;
		}
		return null;
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.bathing.pot.wood";}
}
