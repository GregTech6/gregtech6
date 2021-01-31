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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverVent extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IFluidHandler);}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && aData.mTileEntity instanceof IFluidHandler && SERVER_TIME % 360 == 30+(60*aSide)) {
			if (WD.collectable_air(aData.mTileEntity.getWorld(), aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetY(aSide), aData.mTileEntity.getOffsetZ(aSide))) {
				switch(aData.mTileEntity.getWorld().provider.dimensionId) {
				case DIM_OVERWORLD: FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air       .make(256000), T); return;
				case DIM_NETHER   : FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_Nether.make(256000), T); return;
				case DIM_END      : FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_End   .make(256000), T); return;
				}
				String tBiome = aData.mTileEntity.getBiome(aData.mTileEntity.getOffsetX(aSide), aData.mTileEntity.getOffsetZ(aSide)).biomeName;
				if (BIOMES_SPACE.contains(tBiome)) return;
				if (BIOMES_END.contains(tBiome)) {
					FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_End.make(256000), T);
					return;
				}
				if (BIOMES_NETHER.contains(tBiome)) {
					FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air_Nether.make(256000), T);
					return;
				}
				FL.fill_((IFluidHandler)aData.mTileEntity, ALL_SIDES_THIS_AND_ANY[aSide], FL.Air.make(256000), T);
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		if (MD.GC.mLoaded) aList.add(LH.Chat.ORANGE + "Doesn't work on other Planets!");
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
	}
	
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureFront;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? sTextureFront : aSide == OPPOSITES[aTextureSide] ? sTextureBack : sTextureSides;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureSides;}
	
	public static final ITexture
	sTextureSides = BlockTextureDefault.get("machines/covers/vent/sides"),
	sTextureFront = BlockTextureDefault.get("machines/covers/vent/front"),
	sTextureBack = BlockTextureDefault.get("machines/covers/vent/back");
}
