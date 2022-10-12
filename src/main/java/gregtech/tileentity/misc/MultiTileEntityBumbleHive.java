/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.tileentity.misc;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_CanEntityDestroy;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnBlockHarvested;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBumbleHive extends TileEntityBase07Paintable implements IMTE_AddToolTips, IMTE_CanEntityDestroy, IMTE_OnBlockHarvested {
	public boolean mDroppable = F;
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/colored/top"),
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/colored/side")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/overlay/top"),
		new Textures.BlockIcons.CustomIcon("nature/bumblehive/overlay/side")
	};
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_MEASURE_THERMOMETER));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_thermometer)) {
			if (aChatReturn != null) aChatReturn.add("Temperature: " + WD.envTemp(worldObj, xCoord, yCoord, zCoord) + "K - Humidity: " + getBiome().rainfall);
			return 1000;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;}
	
	@Override public float getExplosionResistance2(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ) {return Blocks.lit_pumpkin.getExplosionResistance(aExploder);}
	@Override public float getExplosionResistance2() {return Blocks.lit_pumpkin.getExplosionResistance(null);}
	@Override public float getBlockHardness() {return Blocks.lit_pumpkin.getBlockHardness(null, 0, 0, 0);}
	@Override public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return 300;}
	@Override public int getFlammability(byte aSide, boolean aDefault) {return 300;}
	
	@Override public boolean isSurfaceSolid     (byte aSide) {return T;}
	@Override public boolean isSurfaceOpaque2   (byte aSide) {return T;}
	@Override public boolean isSideSolid2       (byte aSide) {return T;}
	@Override public boolean allowCovers        (byte aSide) {return F;}
	
	@Override public boolean canEntityDestroy(Entity aEntity) {return !(aEntity instanceof EntityDragon);}
	
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override public void onBlockHarvested(int aMetaData, EntityPlayer aPlayer) {mDroppable = T;}
	@Override public boolean canDrop(int aSlot) {return mDroppable;}
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[9];}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.bumble.hive";}
}
