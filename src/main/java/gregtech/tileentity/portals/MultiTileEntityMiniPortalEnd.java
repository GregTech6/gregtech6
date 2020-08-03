/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregtech.tileentity.portals;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalEnd extends MultiTileEntityMiniPortal {
	public static List<MultiTileEntityMiniPortal>
	sListEndSide = new ArrayListNoNulls<>(),
	sListWorldSide  = new ArrayListNoNulls<>();
	
	@Override public List<MultiTileEntityMiniPortal> getPortalListA() {return sListWorldSide;}
	@Override public List<MultiTileEntityMiniPortal> getPortalListB() {return sListEndSide;}
	
	static {
		LH.add("gt.tileentity.portal.end.tooltip.1", "Only works between the End and the Overworld with a x128 Distance Factor!");
		LH.add("gt.tileentity.portal.end.tooltip.2", "Margin of Error to still work: 512 Meters.");
		LH.add("gt.tileentity.portal.end.tooltip.3", "Requires Ender Eye for activation");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.end.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.end.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get("gt.tileentity.portal.end.tooltip.3"));
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortal tTarget : sListEndSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord*128, tZDifference = zCoord-tTarget.zCoord*128;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (worldObj.provider.dimensionId == DIM_END) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord*128, tZDifference = tTarget.zCoord-zCoord*128;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			}
		}
	}
	
	@Override
	public void addThisPortalToLists() {
		if (worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
				for (MultiTileEntityMiniPortal tPortal : sListEndSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (worldObj.provider.dimensionId == DIM_END) {
				if (!sListEndSide.contains(this)) sListEndSide.add(this);
				for (MultiTileEntityMiniPortal tPortal : sListWorldSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else {
				setPortalInactive();
			}
		}
	}
	
	@Override
	public boolean onBlockActivated2(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			ItemStack aStack = aPlayer.inventory.getCurrentItem();
			if (ST.valid(aStack) && aStack.stackSize > 0 && OM.is_("gemEnderEye", aStack)) {
				setPortalActive();
				if (mTarget != null) UT.Entities.sendchat(aPlayer, "X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
				
			}
		}
		return T;
	}
	
	@Override public float getBlockHardness() {return Blocks.end_stone.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.end_stone.getExplosionResistance(null);}
	
	public ITexture sEndPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, DYE_Black, F, T, T), sEndPortalFrame = BlockTextureCopied.get(Blocks.end_portal_frame, SIDE_TOP, 0);
	@Override public ITexture getPortalTexture() {return sEndPortal;}
	@Override public ITexture getFrameTexture() {return sEndPortalFrame;}
	
	
	@Override public String getTileEntityName() {return "gt.multitileentity.portal.end";}
}
