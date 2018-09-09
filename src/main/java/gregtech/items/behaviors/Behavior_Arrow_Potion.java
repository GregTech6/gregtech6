package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.code.TagData;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.multiitem.MultiItem;
import gregtech.entities.projectiles.EntityArrow_Potion;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Behavior_Arrow_Potion extends Behavior_Arrow {
	private final int[] mPotions;
	
	public Behavior_Arrow_Potion(float aSpeed, float aPrecision, int... aPotions) {
		super(EntityArrow_Potion.class, aSpeed, aPrecision);
		mPotions = aPotions;
	}
	
	public Behavior_Arrow_Potion(float aSpeed, float aPrecision, Enchantment aEnchantment, int aLevel, int... aPotions) {
		super(EntityArrow_Potion.class, aSpeed, aPrecision, aEnchantment, aLevel);
		mPotions = aPotions;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityLivingBase) for (int i = 3; i < mPotions.length; i+=4) if (RNGSUS.nextInt(100) < mPotions[i]) ((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(mPotions[i-3], mPotions[i-2], mPotions[i-1], false));
		return super.onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Potion rArrow = new EntityArrow_Potion(aWorld, aX, aY, aZ);
		rArrow.setProjectileStack(aStack);
		rArrow.setPotions(mPotions);
		return rArrow;
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Potion rArrow = new EntityArrow_Potion(aWorld, aEntity, aSpeed);
		rArrow.setProjectileStack(aStack);
		rArrow.setPotions(mPotions);
		return rArrow;
	}
}