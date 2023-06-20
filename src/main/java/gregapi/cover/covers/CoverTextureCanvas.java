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

package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.CS.*;
import gregapi.data.LH;
import gregapi.render.BlockTextureCopied;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class CoverTextureCanvas extends AbstractCoverDefault {
	public final ITexture mTexture;
	
	public CoverTextureCanvas(ITexture aTexture) {
		mTexture = aTexture;
	}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aCover != null && aCover.hasTagCompound()) aData.visual(aSide, (short)((aCover.getTagCompound().getInteger(NBT_CANVAS_BLOCK) << 4) | (aCover.getTagCompound().getInteger(NBT_CANVAS_META) & 15)));
		if (aPlayer != null) UT.Sounds.send(aData.mTileEntity.getWorld(), SFX.MC_DIG_CLOTH, 1.0F, -1.0F, aData.mTileEntity.getCoords());
	}
	
	@Override public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {UT.Sounds.send(aTileEntity.getWorld(), SFX.MC_DIG_CLOTH, 1.0F, -1.0F, aTileEntity.getCoords());}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (aStack != null && aStack.hasTagCompound() && aStack.getTagCompound().hasKey(NBT_CANVAS_BLOCK)) {
			aList.add(LH.Chat.CYAN + "Block Image: " + ST.names(ST.make(Block.getBlockById(aStack.getTagCompound().getInteger(NBT_CANVAS_BLOCK)), 1, aStack.getTagCompound().getInteger(NBT_CANVAS_META) & 15)));
		}
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return aData.mVisuals[aSide] == 0 ? null : BlockTextureCopied.get(Block.getBlockById((aData.mVisuals[aSide] >>> 4) & 4095), SIDE_ANY, aData.mVisuals[aSide] & 15);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? mTexture : BlockTextureMulti.get(mTexture, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return mTexture;}
	@Override public boolean isSealable(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean isDecorative(byte aCoverSide, CoverData aData) {return T;}
	
	public static final ITexture sCanvas = BlockTextureDefault.get("machines/covers/canvas");
}
