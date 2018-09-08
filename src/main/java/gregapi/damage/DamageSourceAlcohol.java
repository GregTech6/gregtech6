package gregapi.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

/**
 * @author Gregorius Techneticies
 */
public class DamageSourceAlcohol extends DamageSource {
	public DamageSourceAlcohol() {
		super("alcohol");
		setDamageBypassesArmor();
		setDamageIsAbsolute();
	}
	
	@Override
    public IChatComponent func_151519_b(EntityLivingBase aTarget) {
		return new ChatComponentText(EnumChatFormatting.RED+aTarget.getCommandSenderName()+EnumChatFormatting.WHITE + " died from alcohol poisoning");
    }
}