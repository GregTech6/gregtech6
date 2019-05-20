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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverFilterFluid extends AbstractCoverAttachment {
	public CoverFilterFluid() {}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		FluidStack tFluid = UT.Fluids.load(aStack.getTagCompound(), "gt.filter.fluid");
		if (tFluid != null && tFluid.getFluid() != null) aList.add(LH.Chat.CYAN + UT.Fluids.name(tFluid, T));
		aList.add(LH.Chat.ORANGE + "Not NBT sensitive!");
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aCoverSide, (short)(aData.mVisuals[aCoverSide] == 0 ? 1 : 0));
			if (aChatReturn != null) aChatReturn.add(aData.mVisuals[aCoverSide] == 0 ? "Normal Filter" : "Inverted Filter");
			return 1000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			aData.mNBTs[aCoverSide] = null;
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				if (aData.mNBTs[aCoverSide] == null) {
					aChatReturn.add("Filter is empty!");
					aData.mNBTs[aCoverSide] = null;
				} else {
					FluidStack tFluid = UT.Fluids.load(aData.mNBTs[aCoverSide], "gt.filter.fluid");
					if (tFluid == null) {
						aChatReturn.add("Filter is empty!");
						aData.mNBTs[aCoverSide] = null;
					} else {
						aChatReturn.add("Filters for: " + LH.Chat.CYAN + tFluid.getFluid().getName());
					}
				}
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public boolean onCoverClickedRight(byte aCoverSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer instanceof EntityPlayer && aData.mTileEntity.isServerSide()) {
			if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.fluid")) {
				ItemStack tStack = ((EntityPlayer)aPlayer).getCurrentEquippedItem();
				if (ST.valid(tStack)) {
					FluidStack tFluid = UT.Fluids.getFluidForFilledItem(tStack, T);
					if (tFluid != null && tFluid.getFluid() != null) {
						aData.mNBTs[aCoverSide] = UT.Fluids.save(null, "gt.filter.fluid", tFluid);
						UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_CLICK, 1, 1, aData.mTileEntity.getCoords());
						UT.Entities.sendchat(aPlayer, "Filters for: " + LH.Chat.CYAN + tFluid.getFluid().getName());
					}
				}
			}
		}
		return T;
	}
	
	@Override
	public boolean interceptFluidFill(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill) {
		if (aCoverSide != aSide) return F;
		if (aData.mStopped || aFluidToFill == null) return T;
		if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.fluid")) return aData.mVisuals[aCoverSide] == 0;
		return (aData.mVisuals[aCoverSide] == 0) != UT.Fluids.equal(UT.Fluids.load(aData.mNBTs[aCoverSide], "gt.filter.fluid"), aFluidToFill, T);
	}
	@Override
	public boolean interceptFluidDrain(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain) {
		if (aCoverSide != aSide) return F;
		if (aData.mStopped || aFluidToDrain == null) return T;
		if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.fluid")) return aData.mVisuals[aCoverSide] == 0;
		return (aData.mVisuals[aCoverSide] == 0) != UT.Fluids.equal(UT.Fluids.load(aData.mNBTs[aCoverSide], "gt.filter.fluid"), aFluidToDrain, T);
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return aData.mVisuals[aCoverSide]==0?sTextureNormal:sTextureInverted;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return ALONG_AXIS[aCoverSide][aTextureSide] ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aCoverSide]==0?sTextureNormal:sTextureInverted) : BACKGROUND_COVER;}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	public static final ITexture
	sTextureInverted = BlockTextureDefault.get("machines/covers/filterfluid/inverted"),
	sTextureNormal = BlockTextureDefault.get("machines/covers/filterfluid/normal");
}
