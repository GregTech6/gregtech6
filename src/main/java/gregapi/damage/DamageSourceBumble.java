package gregapi.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

/**
 * @author Gregorius Techneticies
 */
public class DamageSourceBumble extends DamageSource {
	public DamageSourceBumble() {
		super("bumble");
		setDamageBypassesArmor();
	}
	
	@Override
    public IChatComponent func_151519_b(EntityLivingBase aTarget) {
		return new ChatComponentText(EnumChatFormatting.RED+aTarget.getCommandSenderName()+EnumChatFormatting.WHITE + " was allergic to Bumblebees");
    }
}