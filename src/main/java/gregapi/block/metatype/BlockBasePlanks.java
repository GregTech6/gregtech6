package gregapi.block.metatype;

import static gregapi.data.CS.*;

import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBasePlanks extends BlockMetaType {
	public BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons);
	}
	
	protected BlockBasePlanks(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aNameInternal, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aNameInternal, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_axe;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.planks.getBlockHardness(aWorld, aX, aY, aZ) * mHardnessMultiplier;}
	@Override public float getExplosionResistance(int aMeta) {return Blocks.planks.getExplosionResistance(null) * mResistanceMultiplier;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.plank.mDefaultStackSize * (mBlock.mBlock == mBlock ? 1 : 2));}
	@Override public boolean canCreatureSpawn(int aMeta) {return F;}
    @Override public boolean isSealable(int aMeta, byte aSide) {return F;}
}