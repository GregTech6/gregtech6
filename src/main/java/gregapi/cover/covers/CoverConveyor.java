package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.MultiTileEntityPipeItem;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class CoverConveyor extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IInventory);}
	
	public final int mTiming;
	
	public CoverConveyor(int aTiming) {
		mTiming = Math.max(1, aTiming);
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aData.mTileEntity instanceof MultiTileEntityPipeItem) aData.visual(aCoverSide, (short)1);
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] == 0 || aData.mTileEntity instanceof MultiTileEntityPipeItem ? 1 : 0));
			return 1000;
		}
		return 0;
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && SERVER_TIME % mTiming == 0 && !aData.mStopped && aData.mTileEntity instanceof IInventory) {
			DelegatorTileEntity<TileEntity> tDelegator = aData.mTileEntity.getAdjacentTileEntity(aSide);
			UT.Inventories.moveOneItemStack(aData.mVisuals[aSide]==0?aData.mTileEntity:tDelegator.mTileEntity, aData.mVisuals[aSide]!=0?aData.mTileEntity:tDelegator, aData.mVisuals[aSide]==0?aSide:tDelegator.mSideOfTileEntity, aData.mVisuals[aSide]!=0?aSide:tDelegator.mSideOfTileEntity);
		}
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Transfers a Stack every " + (mTiming==1?"Tick":mTiming+" Ticks"));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return aData.mVisuals[aCoverSide]==0?sTextureOut:sTextureIn;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return aCoverSide == aTextureSide ? BlockTextureMulti.get(sTextureBase, aData.mVisuals[aCoverSide]==0?sTextureOut:sTextureIn) : aCoverSide == OPPOSITES[aTextureSide] ? BlockTextureMulti.get(sTextureBase, aData.mVisuals[aCoverSide]!=0?sTextureOut:sTextureIn) : sTextureBase;}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return sTextureBase;}
	@Override public boolean needsVisualsSaved(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean interceptItemInsert(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return aData.mVisuals[aSide]==0;}
	@Override public boolean interceptItemExtract(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return aData.mVisuals[aSide]!=0;}
	
	public static final ITexture
	sTextureBase = BlockTextureDefault.get("machines/covers/conveyor/base"),
	sTextureIn = BlockTextureDefault.get("machines/covers/conveyor/in"),
	sTextureOut = BlockTextureDefault.get("machines/covers/conveyor/out");
}