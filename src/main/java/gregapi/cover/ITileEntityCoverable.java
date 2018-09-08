package gregapi.cover;

import gregapi.tileentity.ITileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityCoverable extends ITileEntity {
	public boolean setCoverItem(byte aSide, ItemStack aStack, Entity aPlayer, boolean aForce, boolean aBlockUpdate);
	public ItemStack getCoverItem(byte aSide);
	public CoverData getCoverData();
	public void openCoverGUI(byte aSide, EntityPlayer aPlayer);
	public void receiveBlockUpdateFromCover();
	public void sendBlockUpdateFromCover();
	public boolean canTick();
}