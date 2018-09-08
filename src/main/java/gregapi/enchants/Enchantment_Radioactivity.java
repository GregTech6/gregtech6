package gregapi.enchants;

import gregapi.config.Config;
import gregapi.config.ConfigCategories;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.util.UT;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Enchantment_Radioactivity extends EnchantmentDamage {
	public static Enchantment_Radioactivity INSTANCE;
	
	public Enchantment_Radioactivity() {
		super(Config.addIDConfig(ConfigCategories.IDs.enchantments, "Radioactivity", 14), 0, -1);
		LH.add(getName(), "Radioactivity");
		MT.Cyanite			.addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.Yellorium		.addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.Blutonium		.addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Ludicrite		.addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 4);
		MT.Pu				.addEnchantmentForTools(this, 1).addEnchantmentForArmors(this, 1);
		MT.U_235			.addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Co_60			.addEnchantmentForTools(this, 2).addEnchantmentForArmors(this, 2);
		MT.Pu_241			.addEnchantmentForTools(this, 3).addEnchantmentForArmors(this, 3);
		MT.Pu_243			.addEnchantmentForTools(this, 3).addEnchantmentForArmors(this, 3);
		MT.Am_241			.addEnchantmentForTools(this, 4).addEnchantmentForArmors(this, 4);
		MT.Nq_528			.addEnchantmentForTools(this, 4).addEnchantmentForArmors(this, 4);
		MT.Nq_522			.addEnchantmentForTools(this, 5).addEnchantmentForArmors(this, 5);
		INSTANCE = this;
	}
	
    @Override
	public int getMinEnchantability(int aLevel) {
        return Integer.MAX_VALUE;
    }
    
    @Override
	public int getMaxEnchantability(int aLevel) {
        return 0;
    }
    
    @Override
	public int getMaxLevel() {
        return 5;
    }
    
    @Override
	public boolean canApply(ItemStack par1ItemStack) {
        return false;
    }
    
    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
    
    @Override
	public void func_151367_b(EntityLivingBase aHurtEntity, Entity aDamagingEntity, int aLevel) {
        UT.Entities.applyRadioactivity(aHurtEntity, aLevel, 1);
    }
    
    @Override
	public String getName() {
        return "enchantment.damage.radioactivity";
    }
}