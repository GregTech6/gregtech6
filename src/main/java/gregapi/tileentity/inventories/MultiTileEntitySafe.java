package gregapi.tileentity.inventories;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetPlayerRelativeBlockHardness;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSubItems;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityContainer;
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
public abstract class MultiTileEntitySafe extends TileEntityBase09FacingSingle implements IMTE_GetPlayerRelativeBlockHardness, IMTE_GetSubItems {
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
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		if (aStack.getTagCompound() != null && aStack.getTagCompound().hasKey("gt.dungeonloot")) aList.add("Dungeon Loot: "+aStack.getTagCompound().getString("gt.dungeonloot"));
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
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List aList, short aID) {
		if (!SHOW_HIDDEN_MATERIALS && mMaterial.mHidden) return F;
		if (D1) for (String tLoot : new String[] {"mineshaftCorridor", "pyramidDesertyChest", "pyramidJungleChest", "pyramidJungleDispenser", "strongholdCorridor", "strongholdLibrary", "strongholdCrossing", "villageBlacksmith", "bonusChest", "dungeonChest"}) aList.add(aBlock.mMultiTileEntityRegistry.getItem(aID, UT.NBT.makeString("gt.dungeonloot", tLoot)));
		return T;
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
	@Override public void onExploded(Explosion aExplosion) {worldObj.setBlockToAir(xCoord, yCoord, zCoord);}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this);}
}