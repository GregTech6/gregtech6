/**
 * Copyright (c) 2025 GregTech-6 Team
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
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.ITileEntityItemPipe;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class CoverFilterItem extends AbstractCoverAttachment {
	public CoverFilterItem() {}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		ItemStack tStack = ST.load(aStack.getTagCompound(), "gt.filter.item");
		if (ST.valid(tStack)) try {aList.add(LH.Chat.CYAN + tStack.getDisplayName());} catch(Throwable e) {aList.add(LH.Chat.BLINKING_RED + "ERROR, CANNOT DISPLAY ITEM NAME");}
		aList.add(LH.Chat.ORANGE + "Not NBT sensitive!");
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aCoverSide, (short)(aData.mVisuals[aCoverSide] == 0 ? 1 : 0));
			if (aChatReturn != null) aChatReturn.add(aData.mVisuals[aCoverSide] == 0 ? "Whitelist Filter" : "Blacklist Filter");
			return 1000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			if (aData.mNBTs[aCoverSide] != null) aData.mNBTs[aCoverSide].removeTag("gt.filter.item");
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add(aData.mVisuals[aCoverSide] == 0 ? "Whitelist Filter" : "Blacklist Filter");
				if (aData.mNBTs[aCoverSide] == null) {
					aChatReturn.add("Filter is empty!");
					aData.mNBTs[aCoverSide] = null;
				} else {
					ItemStack tStack = ST.load(aData.mNBTs[aCoverSide], "gt.filter.item");
					if (ST.invalid(tStack)) {
						aChatReturn.add("Filter is empty!");
						aData.mNBTs[aCoverSide] = null;
					} else {
						aChatReturn.add("Filters for: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + (ST.meta_(tStack) == W ? LH.Chat.GREEN + "Wildcard" : LH.Chat.CYAN + ST.meta_(tStack)));
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
			ItemStack tStack = ST.make(((EntityPlayer)aPlayer).getCurrentEquippedItem(), null, null);
			if (ST.valid(tStack)) {
				ItemStack tFilter = ST.load(aData.mNBTs[aCoverSide], "gt.filter.item");
				if (ST.invalid(tFilter)) {
					aData.mNBTs[aCoverSide] = ST.save("gt.filter.item", ST.make(ST.item_(tStack), 1, ST.meta_(tStack)));
					UT.Sounds.send(SFX.MC_CLICK, aData.mTileEntity);
					UT.Entities.sendchat(aPlayer, "Filters for: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + LH.Chat.CYAN + ST.meta_(tStack));
				} else if (ST.equal(tFilter, tStack, T)) {
					if (ST.meta_(tFilter) == W) {
						aData.mNBTs[aCoverSide] = ST.save("gt.filter.item", ST.make(ST.item_(tStack), 1, ST.meta_(tStack)));
						UT.Sounds.send(SFX.MC_CLICK, aData.mTileEntity);
						UT.Entities.sendchat(aPlayer, "Filters for: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + LH.Chat.CYAN + ST.meta_(tStack));
					} else {
						aData.mNBTs[aCoverSide] = ST.save("gt.filter.item", ST.make(ST.item_(tStack), 1, W));
						UT.Sounds.send(SFX.MC_CLICK, aData.mTileEntity);
						UT.Entities.sendchat(aPlayer, "Filters for: " + LH.Chat.CYAN + ST.regName(tStack) + LH.Chat.GRAY + " ; " + LH.Chat.GREEN + "Wildcard");
					}
				}
			}
		}
		return T;
	}
	
	@Override
	public boolean interceptItemInsert(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {
		if (aCoverSide != aSide) return F;
		if (aData.mStopped) return T;
		if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.item")) return aData.mVisuals[aCoverSide] == 0;
		return (aData.mVisuals[aCoverSide] == 0) != ST.equal(ST.load(aData.mNBTs[aCoverSide], "gt.filter.item"), aStack, T);
	}
	@Override
	public boolean interceptItemExtract(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {
		if (aCoverSide != aSide) return F;
		if (aData.mStopped) return T;
		if (aData.mNBTs[aCoverSide] == null || !aData.mNBTs[aCoverSide].hasKey("gt.filter.item")) return aData.mVisuals[aCoverSide] == 0;
		return (aData.mVisuals[aCoverSide] == 0) != ST.equal(ST.load(aData.mNBTs[aCoverSide], "gt.filter.item"), aStack, T);
	}
	@Override
	public boolean interceptConnect(byte aCoverSide, CoverData aData) {
		boolean rIntercept = aData.mTileEntity instanceof ITileEntityItemPipe && aData.mTileEntity.getAdjacentTileEntity(aCoverSide).mTileEntity instanceof ITileEntityItemPipe;
		if (rIntercept) ((ITileEntityItemPipe)aData.mTileEntity).disconnect(aCoverSide, T);
		return rIntercept;
	}
	@Override
	public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {
		return aData.mTileEntity instanceof ITileEntityItemPipe && aData.mTileEntity.getAdjacentTileEntity(aCoverSide).mTileEntity instanceof ITileEntityItemPipe;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return aData.mVisuals[aCoverSide]==0?sTextureNormal:sTextureInverted;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return ALONG_AXIS[aCoverSide][aTextureSide] ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aCoverSide]==0?sTextureNormal:sTextureInverted) : BACKGROUND_COVER;}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	public static final ITexture
	sTextureInverted = BlockTextureDefault.get("machines/covers/filteritem/inverted"),
	sTextureNormal = BlockTextureDefault.get("machines/covers/filteritem/normal");
}
