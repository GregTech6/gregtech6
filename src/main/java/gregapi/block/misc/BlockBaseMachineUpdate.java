package gregapi.block.misc;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.render.IIconContainer;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseMachineUpdate extends BlockBaseMeta {
	public BlockBaseMachineUpdate(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons, int aBitMask) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
		ITileEntityMachineBlockUpdateable.Util.registerMachineBlock(this, aBitMask);
	}
	
	@Override public void onBlockAdded2(World aWorld, int aX, int aY, int aZ)							{if (ITileEntityMachineBlockUpdateable.Util.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ)	)) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, new ChunkCoordinates(aX, aY, aZ), this, UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ)), F);}
	@Override public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData)	{if (ITileEntityMachineBlockUpdateable.Util.isMachineBlock(this, aMetaData								)) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, new ChunkCoordinates(aX, aY, aZ), this, UT.Code.bind4(aMetaData), T);}
	@Override public int getMobilityFlag() {return 2;}
}