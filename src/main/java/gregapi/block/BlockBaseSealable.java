package gregapi.block;

import gregapi.compat.galacticraft.IBlockSealable;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseSealable extends BlockBase implements IBlockSealable {
	public BlockBaseSealable(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType);
	}
	
	@Override public boolean isSealed(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return isSealable(aWorld.getBlockMetadata(aX, aY, aZ), (byte)(UT.Code.side(aDirection) ^ 1));}
}