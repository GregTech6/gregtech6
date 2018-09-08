package gregapi.block.tree;

import gregapi.block.BlockBaseMeta;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseTree extends BlockBaseMeta {
	public BlockBaseTree(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
	}
	
	public abstract int getLeavesRangeSide(byte aMeta);
	public abstract int getLeavesRangeYPos(byte aMeta);
	public abstract int getLeavesRangeYNeg(byte aMeta);
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta) {
		int tRangeSide = getLeavesRangeSide((byte)aMeta)+1, tRangeYNeg = getLeavesRangeYNeg((byte)aMeta)+1, tRangeYPos = getLeavesRangeYPos((byte)aMeta)+1;
		if (!aWorld.isRemote && aWorld.checkChunksExist(aX - tRangeSide, aY - tRangeYNeg, aZ - tRangeSide, aX + tRangeSide, aY + tRangeYPos, aZ + tRangeSide)) {
			tRangeSide--; tRangeYNeg--; tRangeYPos--;
			for (int i = -tRangeSide; i <= tRangeSide; ++i) for (int j = -tRangeYNeg; j <= tRangeYPos; ++j) for (int k = -tRangeSide; k <= tRangeSide; ++k) {
				Block tBlock = aWorld.getBlock(aX + i, aY + j, aZ + k);
				if (tBlock.isLeaves(aWorld, aX + i, aY + j, aZ + k)) tBlock.beginLeavesDecay(aWorld, aX + i, aY + j, aZ + k);
			}
		}
	}
}