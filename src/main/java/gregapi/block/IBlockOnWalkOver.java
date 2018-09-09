package gregapi.block;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockOnWalkOver {
	public void onWalkOver(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ);
}