/**
 * Copyright (c) 2019 Gregorius Techneticies
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
import java.util.Random;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_RandomDisplayTick;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.render.BlockTextureCopied;
import gregapi.render.ITexture;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMiniPortalNether extends MultiTileEntityMiniPortal implements IMTE_RandomDisplayTick {
	public static List<MultiTileEntityMiniPortalNether>
	sListNetherSide = new ArrayListNoNulls<>(),
	sListWorldSide  = new ArrayListNoNulls<>();
	
	static {
		LH.add("gt.tileentity.portal.nether.tooltip.1", "Only works between the Nether and the Overworld with a x8 Distance Factor!");
		LH.add("gt.tileentity.portal.nether.tooltip.2", "Margin of Error to still work: 128 Meters.");
	}
	
	@Override
	public void addToolTips2(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.nether.tooltip.1"));
		aList.add(Chat.CYAN     + LH.get("gt.tileentity.portal.nether.tooltip.2"));
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_IGNITE_FIRE));
	}
	
	@Override
	public void randomDisplayTick(Random aRandom) {
		if (mActive) for (int i = 0; i < 4; ++i) worldObj.spawnParticle("portal", xCoord + aRandom.nextFloat(), yCoord + aRandom.nextFloat(), zCoord + aRandom.nextFloat(), (aRandom.nextFloat() - 0.5D) * 0.5D, (aRandom.nextFloat() - 0.5D) * 0.5D, (aRandom.nextFloat() - 0.5D) * 0.5D);
	}
	
	@Override
	public void findTargetPortal() {
		mTarget = null;
		if (worldObj != null && isServerSide()) {
			if (worldObj.provider.dimensionId == DIM_OVERWORLD) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortalNether tTarget : sListNetherSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = xCoord-tTarget.xCoord*8, tZDifference = zCoord-tTarget.zCoord*8;
					long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
					if (tTempDist < tShortestDistance) {
						tShortestDistance = tTempDist;
						mTarget = tTarget;
					} else if (tTempDist == tShortestDistance && (mTarget == null || Math.abs(tTarget.yCoord-yCoord) < Math.abs(mTarget.yCoord-yCoord))) {
						mTarget = tTarget;
					}
				}
			} else if (worldObj.provider.dimensionId == DIM_NETHER) {
				long tShortestDistance = 128*128;
				for (MultiTileEntityMiniPortalNether tTarget : sListWorldSide) if (tTarget != this && !tTarget.isDead()) {
					long tXDifference = tTarget.xCoord-xCoord*8, tZDifference = tTarget.zCoord-zCoord*8;
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
				for (MultiTileEntityMiniPortalNether tPortal : sListNetherSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else if (worldObj.provider.dimensionId == DIM_NETHER) {
				if (!sListNetherSide.contains(this)) sListNetherSide.add(this);
				for (MultiTileEntityMiniPortalNether tPortal : sListWorldSide) tPortal.findTargetPortal();
				findTargetPortal();
			} else {
				setPortalInactive();
			}
		}
	}
	
	@Override
	public void removeThisPortalFromLists() {
		if (sListWorldSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListNetherSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
		if (sListNetherSide.remove(this)) for (MultiTileEntityMiniPortal tPortal : sListWorldSide) if (tPortal.mTarget == this) tPortal.findTargetPortal();
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_igniter)) {
			if (mActive) setPortalInactive(); else setPortalActive();
			if (mTarget != null && aChatReturn != null) aChatReturn.add("X: " + mTarget.xCoord + "   Y: " + mTarget.yCoord + "   Z: " + mTarget.zCoord);
			return 10000;
		}
		if (aTool.equals(TOOL_extinguisher)) {
			if (mActive) setPortalInactive();
			return 10000;
		}
		return super.onToolClick(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override public float getBlockHardness() {return Blocks.obsidian.getBlockHardness(worldObj, xCoord, yCoord, zCoord);}
	@Override public float getExplosionResistance2() {return Blocks.obsidian.getExplosionResistance(null);}
	
	public ITexture sNetherPortal = BlockTextureCopied.get(Blocks.portal, SIDE_ANY, 0, UNCOLOURED, F, T, T), sNetherPortalFrame = BlockTextureCopied.get(Blocks.obsidian, SIDE_ANY, 0);
	@Override public ITexture getPortalTexture() {return sNetherPortal;}
	@Override public ITexture getFrameTexture() {return sNetherPortalFrame;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.portal.nether";}
}
