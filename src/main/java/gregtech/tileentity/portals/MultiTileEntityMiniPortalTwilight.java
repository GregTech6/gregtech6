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
import gregapi.data.MD;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalTwilight extends MultiTileEntityMiniPortal {
	public static List<MultiTileEntityMiniPortal>
	sListTwilightSide = new ArrayListNoNulls<>(),
	sListWorldSide  = new ArrayListNoNulls<>();
	
	@Override public List<MultiTileEntityMiniPortal> getPortalListA() {return sListWorldSide;}
	@Override public List<MultiTileEntityMiniPortal> getPortalListB() {return sListTwilightSide;}
	
	static {
		LH.add("gt.tileentity.portal.twilight.tooltip.1", "Only works between the Twilight Forest and the Overworld!");
		LH.add("gt.tileentity.portal.twilight.tooltip.2", "Margin of Error to still work: 512 Meters.");
		LH.add("gt.tileentity.portal.twilight.tooltip.3", "Requires any Diamond for activation");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.twilight.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.twilight.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get("gt.tileentity.portal.twilight.tooltip.3"));
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (MD.TF.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortal tTarget : sListTwilightSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord, tZDifference = zCoord-tTarget.zCoord;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (WD.dimTF(worldObj)) {
				long tShortestDistance = 512*512;
				for (MultiTileEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord, tZDifference = tTarget.zCoord-zCoord;
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
		if (MD.TF.mLoaded && worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
				for (MultiTileEntityMiniPortal tPortal : sListTwilightSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (WD.dimTF(worldObj)) {
				if (!sListTwilightSide.contains(this)) sListTwilightSide.add(this);
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
			if (ST.valid(aStack) && aStack.stackSize > 0 && OM.is_("gemAnyDiamond", aStack)) {
				setPortalActive();
				if (mTarget != null) UT.Entities.sendchat(aPlayer, "X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
				worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, xCoord, yCoord, zCoord));
			}
		}
		return T;
	}
	
	@Override public float getBlockHardness() {return Blocks.grass.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.grass.getExplosionResistance(null);}
	
	public ITexture sTwilightPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, UNCOLOURED, F, T, T), sTwilightPortalFrame = BlockTextureCopied.get(Blocks.grass, SIDE_TOP, 0, DYE_Green, F, F, F), sTwilightPortalInactive = BlockTextureCopied.get(Blocks.water, SIDE_TOP, 0, UNCOLOURED, F, F, F);
	@Override public ITexture getPortalTexture() {return sTwilightPortal;}
	@Override public ITexture getFrameTexture() {return sTwilightPortalFrame;}
	@Override public ITexture getInactiveTexture() {return sTwilightPortalInactive;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.portal.twilight";}
}
