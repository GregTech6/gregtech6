package gregapi.item;

import gregapi.code.TagData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IItemProjectile {
	/** @return if this Item has an Arrow Entity */
	public boolean hasProjectile(TagData aProjectileType, ItemStack aStack);
	/** @return an Arrow Entity to be spawned. If null then this is not an Arrow. Note: Other Projectiles still extend EntityArrow */
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ);
	/** @return an Arrow Entity to be spawned. If null then this is not an Arrow. Note: Other Projectiles still extend EntityArrow */
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed);
	
	/** Class for being able to set the ItemStack when launching the Projectile. And for de-obfuscation of Parameters. */
	public static abstract class EntityProjectile extends EntityArrow {
		public EntityProjectile(World aWorld) {
			super(aWorld);
		}
	    public EntityProjectile(World aWorld, double aX, double aY, double aZ) {
	        super(aWorld, aX, aY, aZ);
	    }
	    public EntityProjectile(World aWorld, EntityLivingBase aShootingEntity, EntityLivingBase aWhateverThatIs, float aSpeed, float aPrecision) {
	        super(aWorld, aShootingEntity, aWhateverThatIs, aSpeed, aPrecision);
	    }
	    public EntityProjectile(World aWorld, EntityLivingBase aShootingEntity, float aSpeed) {
	    	super(aWorld, aShootingEntity, aSpeed);
	    }
	    
		public abstract void setProjectileStack(ItemStack aStack);
	}
}