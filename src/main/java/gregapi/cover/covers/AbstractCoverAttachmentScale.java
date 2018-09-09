package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentScale extends AbstractCoverAttachment {
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_cutter)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] ^ B[0]));
			if (aChatReturn != null) aChatReturn.add((aData.mVisuals[aSide] & B[0]) != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
			aData.mTileEntity.sendBlockUpdateFromCover();
			return 1000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] ^ B[1]));
			if (aChatReturn != null) aChatReturn.add((aData.mVisuals[aSide] & B[1]) != 0 ? "Redstone scales down" : "Redstone scales up");
			aData.mTileEntity.sendBlockUpdateFromCover();
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add((aData.mVisuals[aSide] & B[0]) != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
				aChatReturn.add((aData.mVisuals[aSide] & B[1]) != 0 ? "Redstone scales down" : "Redstone scales up");
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (aData.mVisuals[aCoverSide] & B[0]) != 0 ? getRedstoneOutWeak(aCoverSide, aData, aDefaultRedstone) : 0;
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (aData.mVisuals[aCoverSide] & B[1]) != 0 ? UT.Code.bind4(15-aData.mValues[aCoverSide]) : UT.Code.bind4(aData.mValues[aCoverSide]);
	}
	
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
}