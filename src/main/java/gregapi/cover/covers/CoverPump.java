package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.MultiTileEntityPipeFluid;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class CoverPump extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IFluidHandler);}
	
	public final int mThroughput;
	
	public CoverPump(int aThroughput) {
		mThroughput = aThroughput;
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aData.mTileEntity instanceof MultiTileEntityPipeFluid) aData.visual(aCoverSide, (short)1);
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.visual(aSide, (short)(aData.mVisuals[aSide] == 0 || aData.mTileEntity instanceof MultiTileEntityPipeFluid ? 1 : 0));
			return 1000;
		}
		return 0;
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && !aData.mStopped && SERVER_TIME % 20 == 5 && aData.mTileEntity instanceof IFluidHandler) {
			if (aData.mVisuals[aSide]==0) {
				UT.Fluids.move(new DelegatorTileEntity(aData.mTileEntity, aSide), aData.mTileEntity.getAdjacentTank(aSide), mThroughput);
			} else {
				UT.Fluids.move(aData.mTileEntity.getAdjacentTank(aSide), new DelegatorTileEntity(aData.mTileEntity, aSide), mThroughput);
			}
		}
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Transfers " + mThroughput + " L/sec");
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return aData.mVisuals[aSide]==0?sTextureOut:sTextureIn;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? BlockTextureMulti.get(sTextureBase, aData.mVisuals[aSide]==0?sTextureOut:sTextureIn) : aSide == OPPOSITES[aTextureSide] ? BlockTextureMulti.get(sTextureBase, aData.mVisuals[aSide]!=0?sTextureOut:sTextureIn) : sTextureBase;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBase;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean interceptFluidFill(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToFill) {return aData.mVisuals[aSide]==0;}
	@Override public boolean interceptFluidDrain(byte aCoverSide, CoverData aData, byte aSide, FluidStack aFluidToDrain) {return aData.mVisuals[aSide]!=0;}
	
	public static final ITexture
	sTextureBase = BlockTextureDefault.get("machines/covers/pump/base"),
	sTextureIn = BlockTextureDefault.get("machines/covers/pump/in"),
	sTextureOut = BlockTextureDefault.get("machines/covers/pump/out");
}