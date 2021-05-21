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

package gregtech.tileentity.inventories;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityConnectedInventory;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.inventories.MultiTileEntityMassStorage;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityStorageInserter extends TileEntityBase07Paintable implements ITileEntityConnectedInventory {
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (!UT.Entities.isPlayer(aPlayer)) return T;
		ArrayListNoNulls<MultiTileEntityMassStorage> tList = new ArrayListNoNulls<>();
		int tX = getOffsetX(aSide), tY = getOffsetY(aSide), tZ = getOffsetZ(aSide);
		boolean tDirectionsToGo[] = new boolean[] {T,T,T,T}, tOnlyHand = (aPlayer.inventory.getCurrentItem() != null);
		for (int i = 0; i <= 6 && checkColumn(aPlayer, tX, --tY, tZ, tList, tOnlyHand); i++) if (i == 6) return T;
		for (int i = 0; i < 50 && UT.Code.containsBoolean(T, tDirectionsToGo); i++) {
			if (tOnlyHand && aPlayer.inventory.getCurrentItem() == null) break;
			if (tDirectionsToGo[0] && checkColumn(aPlayer, tX+i, tY, tZ  , tList, tOnlyHand)) tDirectionsToGo[0] = F;
			if (tOnlyHand && aPlayer.inventory.getCurrentItem() == null) break;
			if (tDirectionsToGo[1] && checkColumn(aPlayer, tX-i, tY, tZ  , tList, tOnlyHand)) tDirectionsToGo[1] = F;
			if (tOnlyHand && aPlayer.inventory.getCurrentItem() == null) break;
			if (tDirectionsToGo[2] && checkColumn(aPlayer, tX  , tY, tZ+i, tList, tOnlyHand)) tDirectionsToGo[2] = F;
			if (tOnlyHand && aPlayer.inventory.getCurrentItem() == null) break;
			if (tDirectionsToGo[3] && checkColumn(aPlayer, tX  , tY, tZ-i, tList, tOnlyHand)) tDirectionsToGo[3] = F;
		}
		for (MultiTileEntityMassStorage tTileEntity : tList) {
			if (tOnlyHand && aPlayer.inventory.getCurrentItem() == null) break;
			tryInsert(aPlayer, tTileEntity, tOnlyHand);
		}
		ST.update(aPlayer);
		return T;
	}
	
	public boolean checkColumn(EntityPlayer aPlayer, int aX, int aY, int aZ, ArrayListNoNulls<MultiTileEntityMassStorage> aList, boolean aOnlyHand) {
		if (!WD.floor(worldObj, aX, aY, aZ)) return T;
		boolean temp = T;
		for (int i = 1; i < 8; i++) {
			Block tBlock = WD.block(worldObj, aX, aY+i, aZ);
			if (tBlock.getMaterial() != Material.carpet && WD.hasCollide(worldObj, aX, aY+i, aZ, tBlock)) break;
			for (byte tSide : ALL_SIDES_HORIZONTAL) {
				TileEntity tTileEntity = getTileEntity(aX+OFFSETS_X[tSide], aY+i, aZ+OFFSETS_Z[tSide]);
				if (tTileEntity instanceof MultiTileEntityStorageInserter) {
					temp = F;
				} else if (tTileEntity instanceof MultiTileEntityMassStorage) {
					if (((MultiTileEntityMassStorage)tTileEntity).mFacing == OPPOSITES[tSide]) {
						temp = F;
						if (((MultiTileEntityMassStorage)tTileEntity).slotHas(1)) {
							tryInsert(aPlayer, (MultiTileEntityMassStorage)tTileEntity, aOnlyHand);
							if (aOnlyHand && aPlayer.inventory.getCurrentItem() == null) return temp;
						} else {
							aList.add((MultiTileEntityMassStorage)tTileEntity);
						}
					}
				}
			}
		}
		return temp;
	}
	
	public void tryInsert(EntityPlayer aPlayer, MultiTileEntityMassStorage aStorage, boolean aOnlyHand) {
		if (aPlayer.inventory.getCurrentItem() != null) aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem] = aStorage.insertItems(aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem], T);
		if (!aOnlyHand) for (int i = 9; i < aPlayer.inventory.mainInventory.length; i++) {
			if (aPlayer.inventory.mainInventory[i] != null && !ST.nonautoinsert(aPlayer.inventory.mainInventory[i]) && (aStorage.slotHas(1) || aPlayer.inventory.mainInventory[i].getMaxStackSize() > 1)) {
				aPlayer.inventory.mainInventory[i] = aStorage.insertItems(aPlayer.inventory.mainInventory[i], F);
			}
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColored, mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlay)) : null;
	}
	
	@Override public boolean canDrop(int aSlot) {return T;}
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	// Icons
	public static IIconContainer
	sColored = new Textures.BlockIcons.CustomIcon("machines/massstorage/inserter/colored/sides"),
	sOverlay = new Textures.BlockIcons.CustomIcon("machines/massstorage/inserter/overlay/sides");
	
	@Override public String getTileEntityName() {return "gt.multitileentity.storage.inserter";}
}
