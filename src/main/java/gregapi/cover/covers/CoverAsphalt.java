package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.data.CS.SFX;
import gregapi.render.ITexture;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverAsphalt extends CoverTextureSimple {
	public CoverAsphalt(ITexture aTexture) {
		super(aTexture, SFX.MC_DIG_ROCK);
	}
	
	@Override
	public boolean onWalkOver(byte aCoverSide, CoverData aData, Entity aEntity) {
		if ((aEntity.motionX != 0 || aEntity.motionZ != 0) && !aEntity.isInWater() && !aEntity.isWet() && !aEntity.isSneaking()) {aEntity.motionX *= 1.3; aEntity.motionZ *= 1.3;}
		return T;
	}
}