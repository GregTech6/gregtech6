package gregapi.block.prefixblock;

import static gregapi.data.CS.*;

import gregapi.network.INetworkHandler;
import gregapi.network.packets.data.PacketSyncDataName;
import gregapi.network.packets.data.PacketSyncDataShort;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.IRenderedBlockObjectSideCheck;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityScheduledUpdate;
import gregapi.tileentity.ITileEntitySpecificPlacementBehavior;
import gregapi.tileentity.ITileEntitySynchronising;
import gregapi.tileentity.base.TileEntityBase01Root;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

/**
 * @author Gregorius Techneticies
 */
public class PrefixBlockTileEntity extends TileEntityBase01Root implements IRenderedBlockObject, IRenderedBlockObjectSideCheck, ITileEntityScheduledUpdate, ITileEntitySynchronising, ITileEntitySpecificPlacementBehavior {
	public short mMetaData = W;
	public boolean mBlocked = T;
	public NBTTagCompound mItemNBT = null;
	
	public PrefixBlockTileEntity() {super(F);}
	
	@Override
	public void onScheduledUpdate() {
		if (!(mBlocked = WD.visOcc(worldObj, xCoord, yCoord, zCoord, F, T))) {
			NW_API.sendToAllPlayersInRange(new PacketSyncDataShort(getCoords(), mMetaData), worldObj, getCoords());
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToAllPlayersInRange(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), worldObj, getCoords());
		}
	}
	
	@Override
	public void onAdjacentBlockChange(int aX, int aY, int aZ) {
		if (!worldObj.isRemote && mBlocked) {
			mBlocked = F;
			NW_API.sendToAllPlayersInRange(new PacketSyncDataShort(getCoords(), mMetaData), worldObj, getCoords());
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToAllPlayersInRange(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), worldObj, getCoords());
		}
	}
	
	@Override
	public void sendUpdateToPlayer(EntityPlayerMP aPlayer) {
		if (!(mBlocked = WD.visOcc(worldObj, xCoord, yCoord, zCoord, T, T))) {
			NW_API.sendToPlayer(new PacketSyncDataShort(getCoords(), mMetaData), aPlayer);
			if (mItemNBT != null && mItemNBT.hasKey("display")) NW_API.sendToPlayer(new PacketSyncDataName(getCoords(), mItemNBT.getCompoundTag("display").getString("Name")), aPlayer);
		}
	}
	
	private ITexture mTexture;
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (mTexture == null) mTexture = (aBlock instanceof PrefixBlock ? ((PrefixBlock)aBlock).getTexture(mMetaData, worldObj != null) : null);
		return mTexture;
	}
	
	@Override public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {return F;}
	@Override public boolean renderBlock(Block aBlock, RenderBlocks aRenderer) {return F;}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
	@Override public void readFromNBT(NBTTagCompound aNBT) {super.readFromNBT(aNBT); mMetaData = aNBT.getShort("m"); if (aNBT.hasKey("gt.nbt.drop")) mItemNBT = aNBT.getCompoundTag("gt.nbt.drop");}
	@Override public void writeToNBT(NBTTagCompound aNBT) {super.writeToNBT(aNBT); aNBT.setShort("m", mMetaData); if (mItemNBT != null && !mItemNBT.hasNoTags()) aNBT.setTag("gt.nbt.drop", mItemNBT);}
	@Override public void processPacket(INetworkHandler aNetworkHandler) {/**/}
	@Override public Packet getDescriptionPacket() {return null;}
	@Override public Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return null;}
	@Override public Object getGUIServer(int aGUIID, EntityPlayer aPlayer) {return null;}
}