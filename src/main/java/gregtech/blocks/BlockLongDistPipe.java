package gregtech.blocks;

import static gregapi.data.CS.*;

import gregapi.block.misc.BlockBaseMachineUpdate;
import gregapi.data.LH;
import gregapi.render.IIconContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockLongDistPipe extends BlockBaseMachineUpdate {
	public final long[] mTemperatures;
	
	public BlockLongDistPipe(String aUnlocalised, IIconContainer[] aIcons, long[] aTemperatures) {
		super(null, aUnlocalised, Material.iron, soundTypeMetal, 5, aIcons, ~0);
		mTemperatures = aTemperatures;
		LH.add(aUnlocalised+".0.name" , "Long Distance Item Pipeline");
		for (int i = 1; i < mMaxMeta; i++) LH.add(aUnlocalised+"."+i+".name" , "Long Distance Fluid Pipeline ("+mTemperatures[i]+" K)");
	}
	
	@Override public String getHarvestTool(int aMeta) {return TOOL_wrench;}
	@Override public int getHarvestLevel(int aMeta) {return 3;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return T;}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.iron_block.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 20;}
	@Override public float getExplosionResistance(Entity aEntity) {return 20;}
	@Override public float getExplosionResistance(int aMeta) {return 20;}
}