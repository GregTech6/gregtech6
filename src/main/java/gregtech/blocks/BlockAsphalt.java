package gregtech.blocks;

import static gregapi.data.CS.*;

import gregapi.block.IBlockOnWalkOver;
import gregapi.block.metatype.BlockColored;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.MT;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

public class BlockAsphalt extends BlockColored implements IBlockOnWalkOver {
	public BlockAsphalt(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.rock, soundTypeStone, aUnlocalised, "Asphalt", MT.Asphalt, 1.0F, 1.0F, 1, Textures.BlockIcons.ASPHALTS);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockAsphalt(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockAsphalt(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	@Override
	public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if ((aEntity.motionX != 0 || aEntity.motionZ != 0) && !aEntity.isInWater() && !aEntity.isWet() && !aEntity.isSneaking()) {
			double tSpeed = (mSide == SIDE_BOTTOM && aWorld.getBlock(aX, aY-1, aZ).slipperiness >= 0.8 ? 1.05 : 1.3);
			aEntity.motionX *= tSpeed; aEntity.motionZ *= tSpeed;
		}
	}
	
	@Override public boolean doesWalkSpeed(short aMeta) {return T;}
}