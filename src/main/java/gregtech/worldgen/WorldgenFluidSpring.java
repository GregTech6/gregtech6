package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import gregapi.worldgen.WorldgenFluid;
import gregtech.tileentity.misc.MultiTileEntityFluidSpring;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluidSpring extends WorldgenFluid {
	public final FluidStack mSpringFluid;
	public final int mSpringChance;
	
	public WorldgenFluidSpring(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, FluidStack aSpringFluid, int aSpringChance, List... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
		mSpringFluid = aSpringFluid;
		mSpringChance = aSpringChance;
	}
	
	@Override
	public boolean doBedrockStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (aY == 0 && mSpringFluid != null && aRandom.nextInt(mSpringChance) == 0 && MultiTileEntityFluidSpring.setBlock(aWorld, aX, aY, aZ, mSpringFluid) && aWorld.setBlock(aX, aY+1, aZ, mBlock, mBlockMeta, 0)) {
//			DEB.println("Generated Spring for " + UT.Fluids.name(mSpringFluid, T) + " at X:" + aX + "; Z:" + aZ);
			return T;
		}
		return F;
	}
}