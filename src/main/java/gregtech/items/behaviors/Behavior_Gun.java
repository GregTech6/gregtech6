/**
 * Copyright (c) 2025 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.items.behaviors;

import com.mojang.authlib.GameProfile;
import gregapi.block.misc.BlockBaseBars;
import gregapi.block.misc.BlockBaseSpike;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.damage.DamageSources;
import gregapi.data.*;
import gregapi.enchants.Enchantment_EnderDamage;
import gregapi.item.IItemProjectile;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.tileentity.misc.MultiTileEntityGregOLantern;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import twilightforest.entity.boss.EntityTFLich;

import java.util.List;
import java.util.UUID;

import static gregapi.data.CS.*;

public class Behavior_Gun extends AbstractBehaviorDefault {
	public static Behavior_Gun BULLETS_SMALL  = new Behavior_Gun(TD.Projectiles.BULLET_SMALL , 1.00F, 10000, 16);
	public static Behavior_Gun BULLETS_MEDIUM = new Behavior_Gun(TD.Projectiles.BULLET_MEDIUM, 2.00F, 17500,  8);
	public static Behavior_Gun BULLETS_LARGE  = new Behavior_Gun(TD.Projectiles.BULLET_LARGE , 3.00F, 25000,  4);
	
	public final TagData mBulletType;
	public final long mPower;
	public final float mMagic;
	public final byte mAmmoPerMag;
	
	public Behavior_Gun(TagData aBulletType, float aMagic, long aPower, long aAmmoPerMag) {
		mBulletType = aBulletType;
		mMagic = aMagic;
		mPower = aPower;
		mAmmoPerMag = UT.Code.bindStack(aAmmoPerMag);
	}
	
	public boolean shoot(ItemStack aGun, ItemStack aBullet, EntityPlayer aPlayer) {
		// Making sure all Data is correct.
		aGun    = ST.update(aGun   , aPlayer);
		aBullet = ST.update(aBullet, aPlayer);
		// What's the Angle we are looking from and to?
		Vec3
		tDir = aPlayer.getLookVec(),
		tPos = Vec3.createVectorHelper(aPlayer.posX, aPlayer.posY + aPlayer.getEyeHeight(), aPlayer.posZ),
		tAim = tPos.addVector(tDir.xCoord * 200, tDir.yCoord * 200, tDir.zCoord * 200);
		// List all the Blocks that are on the way.
		List<ChunkCoordinates> aCoords = WD.line(tPos, tAim);
		// Gather random Information about the first Block.
		ChunkCoordinates oCoord = aCoords.get(0), aCoord = oCoord, nCoord = oCoord;
		Block oBlock = NB, aBlock = oBlock = WD.block(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		byte  oMeta  =  0, aMeta  = oMeta  = WD.meta (aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		// Are we shooting from under Water?
		boolean tWater = WD.liquid(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		// Bullet related Stats
		int tFireAspect = UT.NBT.getEnchantmentLevel(Enchantment.flame, aGun) + UT.NBT.getEnchantmentLevel(Enchantment.fireAspect, aBullet);
		
		// Make a List of all possible Targets.
		List tEntities = aPlayer.worldObj.getEntitiesWithinAABBExcludingEntity(aPlayer, AxisAlignedBB.getBoundingBox(Math.min(tPos.xCoord, tAim.xCoord)-2, Math.min(tPos.yCoord, tAim.yCoord)-2, Math.min(tPos.zCoord, tAim.zCoord)-2, Math.max(tPos.xCoord, tAim.xCoord)+2, Math.max(tPos.yCoord, tAim.yCoord)+2, Math.max(tPos.zCoord, tAim.zCoord)+2));
		List<Entity> tTargets = new ArrayListNoNulls<>();
		for (Object tEntity : tEntities) if (tEntity instanceof Entity) {
			AxisAlignedBB tBox = ((Entity)tEntity).boundingBox;
			if (tBox != null) {
				if (tEntity instanceof EntityEnderCrystal) tBox = tBox.getOffsetBoundingBox(0, 1.3, 0);
				if (tBox.calculateIntercept(tPos, tAim) != null) tTargets.add((Entity)tEntity);
			}
		}
		
		// Actually do the shooting now!
		long tPower = mPower + 2000L*UT.NBT.getEnchantmentLevel(Enchantment.power, aGun);
		for (int i = 1, ii = aCoords.size()-1; i < ii; i++) {
			
			if (tPower<=0) {
				// TODO Maybe drop the Round as an Item at ***oCoord***.
				if (tFireAspect > 2) WD.burn(aPlayer.worldObj, oCoord, T, T);
				return T;
			}
			
			oCoord = aCoords.get(i-1);
			aCoord = aCoords.get(i  );
			nCoord = aCoords.get(i+1);
			
			oBlock = aBlock;
			oMeta  = aMeta;
			
			aBlock = WD.block(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
			aMeta  = WD.meta (aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
			
			
			for (int j = 0; j < tTargets.size(); j++) {
				if (tTargets.get(j).getDistanceSq(aCoord.posX+0.5, aCoord.posY+0.5, aCoord.posZ+0.5) < tTargets.get(j).getDistanceSq(nCoord.posX+0.5, nCoord.posY+0.5, nCoord.posZ+0.5)) {
					if (hit(aGun, aBullet, aPlayer, tTargets.remove(j--), tPower, tDir)) {
						tPower-=10000;
						// If the bullet hits an Entity it should not possibly drop itself.
						if (tPower<=0) return T;
					}
				}
			}
			
			if (WD.liquid(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ)) {
				if (!tWater) {
					tWater = T;
					UT.Sounds.send(SFX.MC_LIQUID_SPLASH, aPlayer.worldObj, aCoord);
					// if high velocity break entirely, otherwise half the remaining power.
					if (tPower>10000) tPower=0; else tPower/=2;
				}
				continue;
			}
			
			tWater = F;
			
			if (aBlock instanceof BlockPumpkin || WD.te(aPlayer.worldObj, aCoord, T) instanceof MultiTileEntityGregOLantern) {
				if (RNGSUS.nextInt(3) == 0) {
					ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Blocks.pumpkin, 1, 0));
				} else {
					ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.pumpkin_seeds, 1+RNGSUS.nextInt(3), 0));
				}
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=3000;
				continue;
			}
			if (aBlock == Blocks.melon_block) {
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.melon      , 1+RNGSUS.nextInt(6), 0));
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.melon_seeds, 1+RNGSUS.nextInt(3), 0));
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=3000;
				continue;
			}
			if (aBlock == Blocks.cactus) {
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Blocks.cactus, 1, 0));
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=3000;
				continue;
			}
			if (aBlock == Blocks.cocoa) {
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, IL.Dye_Cocoa.get(1));
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=2000;
				continue;
			}
			if (aBlock == Blocks.wool || aBlock.getMaterial() == Material.carpet) {
				if (tFireAspect > 1) {
					WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
					WD.fire(aPlayer.worldObj, aCoord, F);
				}
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=4000;
				continue;
			}
			if (aBlock.getMaterial() == Material.glass || aBlock == Blocks.ice || aBlock == Blocks.redstone_lamp || aBlock == Blocks.lit_redstone_lamp) {
				OreDictItemData tData = OM.anydata(ST.make(aBlock, 1, aMeta));
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) {
					long tAmount = tMaterial.mAmount / OP.scrapGt.mAmount;
					while (tAmount-->0) {
						ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, OP.scrapGt.mat(tMaterial.mMaterial, 1));
					}
				}
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower-=2000;
				continue;
			}
			if (aBlock instanceof BlockFence || aBlock instanceof BlockFenceGate || aBlock == Blocks.web || aBlock == Blocks.mob_spawner || aBlock instanceof BlockPane || aBlock instanceof BlockRail || aBlock instanceof BlockTorch || aBlock instanceof BlockBaseBars || aBlock instanceof BlockBaseSpike || aBlock.getMaterial() == Material.cactus || aBlock.getMaterial() == Material.fire || aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.cloth || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.vine) {
				// Just ignore or assume the Player shot through them.
				tPower-=200;
				continue;
			}
			if (aBlock instanceof BlockStairs || WD.opq(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, T, F)) {
				UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
				tPower=0;
				continue;
			}
			if (aBlock.canCollideCheck(aMeta, F) || aBlock.canCollideCheck(aMeta, T)) {
				AxisAlignedBB tBox = aBlock.getCollisionBoundingBoxFromPool(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
				if (tBox != null && tBox.calculateIntercept(tPos, tAim) != null) {
					UT.Sounds.send(aBlock.stepSound.getBreakSound(), aPlayer.worldObj, aCoord);
					tPower=0;
					continue;
				}
			}
			// Well, just keep flying.
			tPower-=200;
		}
		return F;
	}
	
	public boolean hit(ItemStack aGun, ItemStack aBullet, EntityPlayer aPlayer, Entity aTarget, long aPower, Vec3 aDir) {
		try {
		// In case the Entity is Invulnerable.
		if (aTarget.isEntityInvulnerable()) return F;
		// Player specific immunities, and I guess friendly fire prevention too.
		if (aTarget instanceof EntityPlayer && (((EntityPlayer)aTarget).capabilities.disableDamage || !aPlayer.canAttackPlayer((EntityPlayer)aTarget))) return F;
		// Endermen require Disjunction Enchantment on the Bullet, or having a Weakness Potion Effect on them.
		if (aTarget instanceof EntityEnderman && ((EntityEnderman)aTarget).getActivePotionEffect(Potion.weakness) == null && UT.NBT.getEnchantmentLevel(Enchantment_EnderDamage.INSTANCE, aBullet) <= 0) for (int i = 0; i < 64; ++i) if (((EntityEnderman)aTarget).teleportRandomly()) return F;
		// EntityLivingBase, Ender Dragon and End Crystals only.
		if (!(aTarget instanceof EntityLivingBase || aTarget instanceof EntityDragonPart || aTarget instanceof EntityEnderCrystal)) return F;
	//  // To make Railcrafts Damage Enchantments work... // I later figured I'd just hardcode it in.
	//  MinecraftForge.EVENT_BUS.post(new AttackEntityEvent(aPlayer, aTarget));
		
		OreDictItemData tData = OM.anydata(aBullet);
		OreDictMaterial tGunMat = MultiItemTool.getPrimaryMaterial(aGun, MT.Steel);
		
		float
		tMassFactor = (tData!=null&&tData.nonemptyMaterial() ? (float)tData.mMaterial.weight() / 50.0F : 1),
		tSpeedFactor = Math.min(2.0F, aPower/5000.0F),
		tMagicDamage = (aTarget instanceof EntityLivingBase ? EnchantmentHelper.func_152377_a(aBullet, ((EntityLivingBase)aTarget).getCreatureAttribute()) : aTarget instanceof EntityDragonPart ? UT.NBT.getEnchantmentLevel(Enchantment_EnderDamage.INSTANCE, aBullet) : 0),
		tDamage = tSpeedFactor * Math.max(0, tGunMat.mToolQuality*0.5F + tMassFactor);
		int
		tImplosion  =      UT.NBT.getEnchantmentLevelImplosion(aBullet),
		tFireDamage = 4 * (UT.NBT.getEnchantmentLevel(Enchantment.flame, aGun) + UT.NBT.getEnchantmentLevel(Enchantment.fireAspect, aBullet)),
		tKnockback  =     (UT.NBT.getEnchantmentLevel(Enchantment.punch, aGun) + UT.NBT.getEnchantmentLevel(Enchantment.knockback , aBullet));
		
		if (tImplosion  > 0 && UT.Entities.isExplosiveCreature(aTarget)) tMagicDamage += 1.5F*tImplosion;
		if (tFireDamage > 0) aTarget.setFire(tFireDamage);
		
		EntityPlayer tPlayer = aPlayer;
		
		if (aTarget instanceof EntityPlayer) {
			// Guns are quite overkill against Players otherwise.
			tDamage /= 2; tMagicDamage /= 2;
		} else {
			// Bigger Bullets deal more Magic Damage just like they already do for Normal Damage, but not against Players.
			// The Reason I didn't just up the Enchantment Level like I did with Looting is because that would increase the Side Effects too.
			tMagicDamage *= mMagic;
			
			if (aPlayer.worldObj instanceof WorldServer) {
				if (UT.NBT.getEnchantmentLevel(Enchantment.looting, aBullet) > 0) {
					tPlayer = FakePlayerFactory.get((WorldServer)aPlayer.worldObj, new GameProfile(new UUID(0, 0), ((EntityLivingBase)aPlayer).getCommandSenderName()));
					tPlayer.inventory.currentItem = 0;
					tPlayer.inventory.setInventorySlotContents(0, aBullet);
					tPlayer.setPositionAndRotation(aPlayer.posX, aPlayer.posY, aPlayer.posZ, aPlayer.rotationYaw, aPlayer.rotationPitch);
					// Bypasses Twilight Forest Progression Checks. Yeah this is needed or else any Looting Bullet would do ZERO Damage.
					if (WD.dimTF(aPlayer.worldObj)) tPlayer.capabilities.isCreativeMode = T;
					tPlayer.setDead();
				}
			}
		}
		
		// To make Looting work at all...
		DamageSource tDamageSource = DamageSources.getCombatDamage("player", tPlayer, DamageSources.getDeathMessage(aPlayer, aTarget, (tData!=null&&tData.validMaterial() ? "[VICTIM] got killed by [KILLER] shooting a Bullet made of " + tData.mMaterial.mMaterial.getLocal() : "[VICTIM] got shot by [KILLER]")), F).setProjectile();
		// Extremely Fast Bullets will penetrate Armor. You need a Rifle with the Power Enchantment for this. A Power 5 Carbine at point-blank could do too though.
		if (aPower > 25000) tDamageSource.setDamageBypassesArmor();
		// Smite Bullets will break one Lich Shield each, in order to make this somewhat beatable in Multiplayer.
		if (MD.TF.mLoaded && aTarget instanceof EntityTFLich && UT.NBT.getEnchantmentLevel(Enchantment.smite, aBullet) > 0) tDamageSource.setDamageBypassesArmor();
		
		if (aTarget.attackEntityFrom(tDamageSource, (tDamage + tMagicDamage) * TFC_DAMAGE_MULTIPLIER)) {
			aTarget.hurtResistantTime = (aTarget instanceof EntityLivingBase ? ((EntityLivingBase)aTarget).maxHurtResistantTime : 20);
			if (aTarget instanceof EntityCreeper && tFireDamage > 0 && tImplosion <= 0) ((EntityCreeper)aTarget).func_146079_cb();
			if (tKnockback > 0) aTarget.addVelocity(aDir.xCoord * tKnockback * aPower / 50000.0, 0.05, aDir.zCoord * tKnockback * aPower / 50000.0);
			if (aTarget instanceof EntityLivingBase)
			UT.Enchantments.applyBullshitA((EntityLivingBase)aTarget, aPlayer, aBullet);
			UT.Enchantments.applyBullshitB(                  aPlayer, aTarget, aBullet);
			if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayerMP) ((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
			if (tMagicDamage > 0.0F) aPlayer.onEnchantmentCritical(aTarget);
			return T;
		}
		// Print Errors to the Log and send a Chat Message informing about its existence.
		} catch(Throwable e) {e.printStackTrace(ERR); UT.Entities.sendchat(aPlayer, "See gregtech.log for details: " + e.toString()); aTarget.setDead(); return T;}
		// Just pretend we miss the Target if it was in its Invulnerability Frames, this will end up hitting whatever is behind the Target instead.
		if (aTarget.hurtResistantTime > 0) return F;
		// It hits, but it doesn't seem to do anything.
		return T;
	}
	
//  @Override public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {onItemRightClick(aItem, aStack, aPlayer.worldObj, aPlayer); return T;}
	@Override public boolean onItemUse         (MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {onItemRightClick(aItem, aStack, aPlayer.worldObj, aPlayer); return T;}
	@Override public boolean onItemUseFirst    (MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {if (aWorld.isRemote) return F; onItemRightClick(aItem, aStack, aPlayer.worldObj, aPlayer); return T;}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aGun, World aWorld, EntityPlayer aPlayer) {
		// TODO Particles!
		if (!(aPlayer instanceof EntityPlayerMP)) return aGun;
		
		NBTTagCompound aNBT = UT.NBT.getOrCreate(aGun);
		ItemStack aBullet = ST.load(aNBT, NBT_AMMO);
		if (aPlayer.isSneaking()) {
			if (ST.invalid(aBullet) || aBullet.stackSize <= 0) {
				reloadGun(aGun, aPlayer, F);
				return aGun;
			}
			ST.give(aPlayer, aBullet);
			UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
			ST.save(aNBT, NBT_AMMO, NI);
			return aGun;
		}
		if (ST.invalid(aBullet) || aBullet.stackSize <= 0) {
			UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
			ST.save(aNBT, NBT_AMMO, NI);
			return aGun;
		}
		shoot(aGun, ST.amount(1, aBullet), aPlayer);
		UT.Sounds.send(SFX.MC_FIREWORK_BLAST_FAR, 128, aPlayer);
		if (!UT.Entities.hasInfiniteItems(aPlayer) && RNGSUS.nextInt(1+UT.NBT.getEnchantmentLevel(Enchantment.infinity, aGun)) == 0) {
			OreDictItemData tData = OM.anydata(aBullet);
			aBullet.stackSize--;
			ST.save(aNBT, NBT_AMMO, aBullet.stackSize > 0 ? aBullet : NI);
			for (OreDictMaterialStack tMat : tData.mByProducts) if (tMat.mAmount >= OP.scrapGt.mAmount && !tMat.mMaterial.containsAny(TD.Properties.EXPLOSIVE, TD.Properties.FLAMMABLE)) ST.give(aPlayer, OP.scrapGt.mat(tMat.mMaterial, tMat.mAmount/OP.scrapGt.mAmount));
		}
		((MultiItemTool)aItem).doDamage(aGun, 100, aPlayer, F);
		return aGun;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.Chat.CYAN + LH.get(LH.WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD));
		ItemStack aBullet = ST.load(UT.NBT.getNBT(aStack), NBT_AMMO);
		if (ST.valid(aBullet)) aList.add(LH.Chat.YELLOW + aBullet.getDisplayName() + LH.Chat._WHITE + aBullet.stackSize);
		return aList;
	}
	
	public boolean isProjectile(ItemStack aStack) {
		return ST.item(aStack) instanceof IItemProjectile && ((IItemProjectile)ST.item(aStack)).hasProjectile(mBulletType, aStack);
	}
	
	public boolean reloadGun(ItemStack aGun, EntityPlayer aPlayer, boolean aOnlyCheckHeld) {
		NBTTagCompound aNBT = UT.NBT.getOrCreate(aGun);
		ItemStack aBullet = ST.load(aNBT, NBT_AMMO);
		if (ST.valid(aBullet) && aBullet.stackSize > 0) return F;
		if (isProjectile(aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem])) {
			int tConsumed = Math.min(mAmmoPerMag, aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem].stackSize);
			UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
			ST.save(aNBT, NBT_AMMO, ST.amount(tConsumed, aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem]));
			aPlayer.inventory.decrStackSize(aPlayer.inventory.currentItem, tConsumed);
			ST.update(aPlayer);
			return T;
		}
		if (aOnlyCheckHeld) return F;
		for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) if (aPlayer.inventory.mainInventory[i] == aGun) {
			if (i < 27 && isProjectile(aPlayer.inventory.mainInventory[i+27])) {
			if (i < 18 && isProjectile(aPlayer.inventory.mainInventory[i+18])) {
			if (i <  9 && isProjectile(aPlayer.inventory.mainInventory[i+ 9])) {
				int tConsumed = Math.min(mAmmoPerMag, aPlayer.inventory.mainInventory[i+ 9].stackSize);
				UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
				ST.save(aNBT, NBT_AMMO, ST.amount(tConsumed, aPlayer.inventory.mainInventory[i+ 9]));
				aPlayer.inventory.decrStackSize(i+ 9, tConsumed);
				ST.update(aPlayer);
				return T;
			}
				int tConsumed = Math.min(mAmmoPerMag, aPlayer.inventory.mainInventory[i+18].stackSize);
				UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
				ST.save(aNBT, NBT_AMMO, ST.amount(tConsumed, aPlayer.inventory.mainInventory[i+18]));
				aPlayer.inventory.decrStackSize(i+18, tConsumed);
				ST.update(aPlayer);
				return T;
			}
				int tConsumed = Math.min(mAmmoPerMag, aPlayer.inventory.mainInventory[i+27].stackSize);
				UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
				ST.save(aNBT, NBT_AMMO, ST.amount(tConsumed, aPlayer.inventory.mainInventory[i+27]));
				aPlayer.inventory.decrStackSize(i+27, tConsumed);
				ST.update(aPlayer);
				return T;
			}
			break;
		}
		for (int i = aPlayer.inventory.mainInventory.length-1; i >= 0; i--) if (isProjectile(aPlayer.inventory.mainInventory[i])) {
			int tConsumed = Math.min(mAmmoPerMag, aPlayer.inventory.mainInventory[i].stackSize);
			UT.Sounds.send(SFX.MC_CLICK, 16, aPlayer);
			ST.save(aNBT, NBT_AMMO, ST.amount(tConsumed, aPlayer.inventory.mainInventory[i]));
			aPlayer.inventory.decrStackSize(i, tConsumed);
			ST.update(aPlayer);
			return T;
		}
		return F;
	}
}
