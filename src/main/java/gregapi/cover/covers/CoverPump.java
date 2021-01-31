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
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.MultiTileEntityPipeFluid;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverPump extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IFluidHandler);}
	
	public final int mThroughput;
	
	public CoverPump(int aThroughput) {
		mThroughput = aThroughput;
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aData.mTileEntity instanceof MultiTileEntityPipeFluid) aData.visual(aCoverSide, (short)1);
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] == 0 || aData.mTileEntity instanceof MultiTileEntityPipeFluid ? 1 : 0));
			return 1000;
		}
		return 0;
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && SERVER_TIME % 20 == 5 && aData.mTileEntity instanceof IFluidHandler) {
			long tThroughput = mThroughput, tMoved = 1;
			if (aData.mVisuals[aSide]==0) {
				while (tMoved > 0 && tThroughput > 0) tThroughput -= (tMoved = FL.move(aData.delegator(aSide), aData.mTileEntity.getAdjacentTank(aSide), tThroughput));
			} else {
				while (tMoved > 0 && tThroughput > 0) tThroughput -= (tMoved = FL.move(aData.mTileEntity.getAdjacentTank(aSide), aData.delegator(aSide), tThroughput));
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Transfers " + mThroughput + " L/sec");
		aList.add(LH.Chat.ORANGE + "Doesn't do Fluid Blocks! Use Drain for that instead!");
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return aData.mVisuals[aSide]==0?sTextureOut:sTextureIn;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aSide]==0?sTextureOut:sTextureIn) : aSide == OPPOSITES[aTextureSide] ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aSide]!=0?sTextureOut:sTextureIn) : BACKGROUND_COVER;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean interceptFluidFill (byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluid) {return aCoverSide == aSide && aData.mVisuals[aSide]==0;}
	@Override public boolean interceptFluidDrain(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluid) {return aCoverSide == aSide && aData.mVisuals[aSide]!=0;}
	
	public static final ITexture
	sTextureIn = BlockTextureDefault.get("machines/covers/pump/in"),
	sTextureOut = BlockTextureDefault.get("machines/covers/pump/out");
}
