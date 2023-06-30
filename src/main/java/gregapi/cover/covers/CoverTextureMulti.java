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
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.T;
import static gregapi.data.CS.TOOL_chisel;

/**
 * @author Gregorius Techneticies
 */
public class CoverTextureMulti extends AbstractCoverDefault {
	public final ITexture[] mTextures;
	public final boolean mHasCollide, mBackground;
	public final String mSound;
	
	public CoverTextureMulti(boolean aHasCollide, boolean aBackground, String aSound, String aFolder, int aAmount) {mSound = aSound; mHasCollide = aHasCollide; mBackground = aBackground; mTextures = new ITexture[aAmount]; for (int i = 0; i < mTextures.length; i++) mTextures[i] = BlockTextureDefault.get(aFolder+i);}
	public CoverTextureMulti(boolean aHasCollide, boolean aBackground, String aSound, ITexture... aTextures) {mSound = aSound; mHasCollide = aHasCollide; mBackground = aBackground; mTextures = aTextures;}
	public CoverTextureMulti(boolean aHasCollide, String aSound, String aFolder, int aAmount) {mSound = aSound; mHasCollide = aHasCollide; mBackground = T; mTextures = new ITexture[aAmount]; for (int i = 0; i < mTextures.length; i++) mTextures[i] = BlockTextureDefault.get(aFolder+i);}
	public CoverTextureMulti(boolean aHasCollide, String aSound, ITexture... aTextures) {mSound = aSound; mHasCollide = aHasCollide; mBackground = T; mTextures = aTextures;}
	public CoverTextureMulti(String aFolder, String aSound, int aAmount) {this(T, aSound, aFolder, aAmount);}
	public CoverTextureMulti(String aSound, ITexture... aTextures) {this(T, aSound, aTextures);}
	public CoverTextureMulti(boolean aHasCollide, boolean aBackground, String aFolder, int aAmount) {this(aHasCollide, aBackground, null, aFolder, aAmount);}
	public CoverTextureMulti(boolean aHasCollide, boolean aBackground, ITexture... aTextures) {this(aHasCollide, aBackground, null, aTextures);}
	public CoverTextureMulti(boolean aHasCollide, String aFolder, int aAmount) {this(aHasCollide, null, aFolder, aAmount);}
	public CoverTextureMulti(boolean aHasCollide, ITexture... aTextures) {this(aHasCollide, null, aTextures);}
	public CoverTextureMulti(String aFolder, int aAmount) {this(T, aFolder, aAmount);}
	public CoverTextureMulti(ITexture... aTextures) {this(T, aTextures);}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_chisel) && mTextures.length > 1) {
			aData.visual(aSide, (short)((aData.mVisuals[aSide] + 1) % mTextures.length));
			return 100;
		}
		return 0;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return mTextures[aData.mVisuals[aSide]%mTextures.length];}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return mBackground ? aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData)) : getCoverTextureSurface(aSide, aData);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return mBackground ? BACKGROUND_COVER : getCoverTextureSurface(aSide, aData);}
	
	@Override public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {if (aPlayer != null) UT.Sounds.send(aData.mTileEntity.getWorld(), mSound == null ? SFX.GT_SCREWDRIVER : mSound, 1.0F, 1.0F, aData.mTileEntity.getCoords());}
	@Override public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {UT.Sounds.send(aTileEntity.getWorld(), mSound == null ? SFX.MC_BREAK : mSound, 1.0F, -1.0F, aTileEntity.getCoords());}
	@Override public void getCollisions(byte aCoverSide, CoverData aData, AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {if (mHasCollide) super.getCollisions(aCoverSide, aData, aAABB, aList, aEntity);}
	@Override public boolean isDecorative(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {super.addToolTips(aList, aStack, aF3_H); if (mTextures.length > 1) aList.add(LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));}
}
