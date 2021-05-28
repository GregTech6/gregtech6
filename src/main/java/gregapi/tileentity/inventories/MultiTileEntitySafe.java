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

package gregapi.tileentity.inventories;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetPlayerRelativeBlockHardness;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.LH;
import gregapi.gui.ContainerClientDefault;
import gregapi.gui.ContainerCommonDefault;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntitySafe extends TileEntityBase09FacingSingle implements IMTE_GetPlayerRelativeBlockHardness {
	public String mDungeonLootName = "";
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.dungeonloot")) mDungeonLootName = aNBT.getString("gt.dungeonloot");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (UT.Code.stringValid(mDungeonLootName)) aNBT.setString("gt.dungeonloot", mDungeonLootName);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (UT.Code.stringValid(mDungeonLootName)) aList.add(LH.Chat.BLINKING_CYAN + "Contains Loot of " + LH.Chat.WHITE + LH.get("loot." + mDungeonLootName));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	protected void generateDungeonLoot() {
		if (isServerSide() && UT.Code.stringValid(mDungeonLootName)) {
			for (int i = 0, j = getSizeInventory(); i < j; i++) if (!slotHas(i)) slot(i, ChestGenHooks.getOneItem(mDungeonLootName, RNGSUS));
			mDungeonLootName = "";
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aSide != mFacing) return F;
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) {
			generateDungeonLoot();
			openGUI(aPlayer);
		}
		return T;
	}
	
	@Override
	public boolean breakBlock() {
		generateDungeonLoot();
		return super.breakBlock();
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {
		if (showInCreative()) {
			if (D1) for (String tLoot : new String[] {"mineshaftCorridor", "pyramidDesertyChest", "pyramidJungleChest", "pyramidJungleDispenser", "strongholdCorridor", "strongholdLibrary", "strongholdCrossing", "villageBlacksmith", "bonusChest", "dungeonChest"}) aList.add(aBlock.mMultiTileEntityRegistry.getItem(aID, UT.NBT.makeString("gt.dungeonloot", tLoot)));
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
		aWorld.playSoundEffect(aX+0.5, aY+0.5, aZ+0.5, Blocks.anvil.stepSound.func_150496_b(), (Blocks.anvil.stepSound.getVolume()+1)/2, Blocks.anvil.stepSound.getPitch()*0.8F);
		return F;
	}
	
	@Override public boolean allowCovers(byte aSide) {return aSide != mFacing;}
	
	// Inventory Stuff
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int getInventoryStackLimit() {return 64;}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public void onExploded(Explosion aExplosion) {setToAir();}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
}
