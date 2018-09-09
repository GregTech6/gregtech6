package gregapi.gui;

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.LH;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.tileentity.machines.MultiTileEntityBasicMachine;
import gregapi.util.UT;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerCommonBasicMachine extends ContainerCommon {
	private RecipeMap mRecipes;
	
	public ContainerCommonBasicMachine(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, RecipeMap aRecipes) {
		super(aInventoryPlayer, aTileEntity);
		mRecipes = aRecipes;
	}
	
	@Override
	public int addSlots(InventoryPlayer aPlayerInventory) {
		int tIndex = 0;
		
		mRecipes = ((MultiTileEntityBasicMachine)mTileEntity).mRecipes;
		
		switch (mRecipes.mInputItemsCount) {
		case  0:
			break;
		case  1:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>6? 7:25));
			break;
		case  2:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>6? 7:25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>6? 7:25));
			break;
		case  3:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, mRecipes.mInputFluidCount>6? 7:25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>6? 7:25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>6? 7:25));
			break;
		case  4:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3?25:34));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3?25:34));
			break;
		case  5:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3?25:34));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3?25:34));
			break;
		case  6:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3? 7:16));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, mRecipes.mInputFluidCount>3?25:34));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, mRecipes.mInputFluidCount>3?25:34));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, mRecipes.mInputFluidCount>3?25:34));
			break;
		case  7:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			break;
		case  8:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 43));
			break;
		case  9:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 43));
			break;
		case 10:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 61));
			break;
		case 11:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 61));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 61));
			break;
		default:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53,  7));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 25));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 43));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 17, 61));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 35, 61));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 53, 61));
			break;
		}
		
		switch (mRecipes.mOutputItemsCount) {
		case  0:
			break;
		case  1:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			break;
		case  2:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			break;
		case  3:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, mRecipes.mOutputFluidCount>6? 7:25).setCanPut(F));
			break;
		case  4:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			break;
		case  5:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			break;
		case  6:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, mRecipes.mOutputFluidCount>3? 7:16).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, mRecipes.mOutputFluidCount>3?25:34).setCanPut(F));
			break;
		case  7:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			break;
		case  8:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 43).setCanPut(F));
			break;
		case  9:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 43).setCanPut(F));
			break;
		case 10:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 61).setCanPut(F));
			break;
		case 11:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 61).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 61).setCanPut(F));
			break;
		default:
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143,  7).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 25).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 43).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 107, 61).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 125, 61).setCanPut(F));
			addSlotToContainer(new Slot_Normal(mTileEntity, tIndex++, 143, 61).setCanPut(F));
			break;
		}
		
		addSlotToContainer(mRecipes.getSpecialSlot(mTileEntity, tIndex++, 80, 43));
		
		for (int i = 0; i < mRecipes.mInputFluidCount ; i++) addSlotToContainer(new Slot_Render(mTileEntity, tIndex++,  53 - (i%3)*18, 63 - (i/3)*18).setTooltip("You can extract using a Tap or Nozzle", LH.Chat.WHITE));
		for (int i = 0; i < mRecipes.mOutputFluidCount; i++) addSlotToContainer(new Slot_Render(mTileEntity, tIndex++, 107 + (i%3)*18, 63 - (i/3)*18).setTooltip("You can extract using a Tap or Nozzle", LH.Chat.WHITE));
		
		return super.addSlots(aPlayerInventory);
	}
	
	public short mProgressBar = 0;
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (ICrafting tUpdate : (List<ICrafting>)crafters) {
			if (((MultiTileEntityBasicMachine)mTileEntity).mSuccessful) {
				tUpdate.sendProgressBarUpdate(this, 0, Short.MAX_VALUE);
			} else if (((MultiTileEntityBasicMachine)mTileEntity).mMaxProgress > 0) {
				tUpdate.sendProgressBarUpdate(this, 0, (short)UT.Code.units(Math.min(((MultiTileEntityBasicMachine)mTileEntity).mMaxProgress, ((MultiTileEntityBasicMachine)mTileEntity).mProgress), ((MultiTileEntityBasicMachine)mTileEntity).mMaxProgress, Short.MAX_VALUE, T));
			} else {
				tUpdate.sendProgressBarUpdate(this, 0, -1);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int aIndex, int aValue) {
		super.updateProgressBar(aIndex, aValue);
		switch (aIndex) {
		case 0: mProgressBar = (short)aValue; break;
		}
	}
	
	@Override public int getStartIndex() {return 0;}
	@Override public int getSlotCount() {return mRecipes.mInputItemsCount + mRecipes.mOutputItemsCount + (mRecipes.getSpecialSlot(mTileEntity, 0, 80, 43)!=null?1:0);}
	@Override public int getShiftClickStartIndex() {return 0;}
	@Override public int getShiftClickSlotCount() {return mRecipes.mInputItemsCount;}
}