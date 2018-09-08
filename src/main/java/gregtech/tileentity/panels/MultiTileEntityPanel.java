package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_CanPlace;
import gregapi.tileentity.notick.TileEntityBase03MultiTileEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityPanel extends TileEntityBase03MultiTileEntities implements IMTE_CanPlace {
	@Override public boolean canPlace(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock, PX_P[0], PX_P[0], PX_P[7], PX_N[0], PX_N[0], PX_N[7]); return T;}
	@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
}