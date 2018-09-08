package gregapi.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;

/**
 * @author Gregorius Techneticies
 */
public class DamageSourceCombat extends EntityDamageSource {
	private IChatComponent mDeathMessage;
	
	public DamageSourceCombat(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage) {
		super(aType, aPlayer);
		mDeathMessage = aDeathMessage;
	}
	
	@Override
    public IChatComponent func_151519_b(EntityLivingBase aTarget) {
		return mDeathMessage == null ? super.func_151519_b(aTarget) : mDeathMessage;
    }
}