package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.render.ITexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

/**
 * @author Gregorius Techneticies
 */
public class CoverCrafting extends CoverTextureMulti {
	public CoverCrafting(ITexture... aTextures) {
		super(T, aTextures);
	}
	
	public CoverCrafting(String aFolder, int aAmount) {
		super(T, aFolder, aAmount);
	}
	
	@Override
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			((EntityPlayerMP)aPlayer).getNextWindowId();
			((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(((EntityPlayerMP)aPlayer).currentWindowId, 1, "Crafting", 9, T));
			((EntityPlayerMP)aPlayer).openContainer = new ContainerWorkbench(((EntityPlayerMP)aPlayer).inventory, ((EntityPlayerMP)aPlayer).worldObj, aData.mTileEntity.getX(), aData.mTileEntity.getY(), aData.mTileEntity.getZ()) {@Override public boolean canInteractWith(EntityPlayer par1EntityPlayer) {return T;}};
			((EntityPlayerMP)aPlayer).openContainer.windowId = ((EntityPlayerMP)aPlayer).currentWindowId;
			((EntityPlayerMP)aPlayer).openContainer.addCraftingToCrafters(((EntityPlayerMP)aPlayer));
		}
		return T;
	}
	
	@Override public boolean isSealable(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
}