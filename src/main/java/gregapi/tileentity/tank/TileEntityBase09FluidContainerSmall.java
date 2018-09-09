package gregapi.tileentity.tank;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase09FluidContainerSmall extends TileEntityBase08FluidContainer implements ITileEntityQuickObstructionCheck, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	
	@Override public float getSurfaceSize			(byte aSide) {return 0.0F;}
	@Override public float getSurfaceSizeAttachable	(byte aSide) {return 0.0F;}
	@Override public float getSurfaceDistance		(byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid  		(byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2 		(byte aSide) {return F;}
	@Override public boolean isSideSolid2			(byte aSide) {return F;}
	@Override public boolean allowCovers			(byte aSide) {return F;}
	@Override public boolean attachCoversFirst		(byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt	(byte aSide) {return F;}
}