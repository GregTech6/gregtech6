/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.asm.transformers.minecraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/* This is a separate file so it class loads *while* minecraft loads,
   if we accessed world in the main transformer then we can miss out
   on the transformations.  Not an issue when accessing MC classes
   while transforming other mods though.
 */
public class Replacements {

	/** Zombies convert their Victim. */
	public static void EntityZombie_onKillEntity(Object aZombie, Object aVictim) {
		// Just ALWAYS convert Villagers, not only sometimes or when the stupid Difficulty Setting is right.
		if (aVictim instanceof EntityVillager) {
			EntityVillager aVillager = (EntityVillager)aVictim;
			World aWorld = aVillager.worldObj;
			// Yep, new Zombie Object.
			EntityZombie tZombieVillager = new EntityZombie(aWorld);
			// Location and Head need to point to the right places.
			tZombieVillager.copyLocationAndAnglesFrom(aVillager);
			// Do normal spawning Stuff.
			tZombieVillager.onSpawnWithEgg((IEntityLivingData)null);
			// Converting Villager Zombies back to Villagers would make it impossible to retrieve the Items, so don't pick up in the first place!
			tZombieVillager.setCanPickUpLoot(false);
			// Well yes, he clearly is a Villager Zombie.
			tZombieVillager.setVillager(true);
			// STAY THERE! DONT DESPAWN!
			tZombieVillager.func_110163_bv();
			// Convert to Child
			tZombieVillager.setChild(aVillager.isChild());
			// Transfer Name Tag
			tZombieVillager.setCustomNameTag(aVillager.getCustomNameTag());
			// Prevent duping Name Tags
			aVillager.setCustomNameTag("");
			// And put the new Zombie into the World!
			aWorld.spawnEntityInWorld(tZombieVillager);
			// With Sound ofcourse!
			aWorld.playAuxSFXAtEntity(null, 1016, (int)tZombieVillager.posX, (int)tZombieVillager.posY, (int)tZombieVillager.posZ, 0);
			// Villager? What Villager?
			aWorld.removeEntity(aVillager);
		}
	}
	
	public static void BlockStaticLiquid_updateTick(BlockStaticLiquid self, World world, int x, int y, int z, Random rand) {
		if (self.getMaterial() == Material.lava)
		{
			int l = rand.nextInt(3);
			int i1;

			for (i1 = 0; i1 < l; ++i1)
			{
				x += rand.nextInt(3) - 1;
				++y;
				z += rand.nextInt(3) - 1;
				Block block = world.getBlock(x, y, z);

				if (block.getMaterial() == Material.air)
				{
					if (
						BlockStaticLiquid_isFlammable(world, x - 1, y, z, ForgeDirection.EAST) ||
						BlockStaticLiquid_isFlammable(world, x + 1, y, z, ForgeDirection.WEST) ||
						BlockStaticLiquid_isFlammable(world, x, y, z - 1, ForgeDirection.SOUTH) ||
						BlockStaticLiquid_isFlammable(world, x, y, z + 1, ForgeDirection.NORTH) ||
						BlockStaticLiquid_isFlammable(world, x, y - 1, z, ForgeDirection.UP) ||
						BlockStaticLiquid_isFlammable(world, x, y + 1, z, ForgeDirection.DOWN))
					{
						world.setBlock(x, y, z, Blocks.fire);
						return;
					}
				}
				else if (block.getMaterial().blocksMovement())
				{
					return;
				}
			}

			if (l == 0)
			{
				i1 = x;
				int k1 = z;

				for (int j1 = 0; j1 < 3; ++j1)
				{
					x = i1 + rand.nextInt(3) - 1;
					z = k1 + rand.nextInt(3) - 1;

					if (world.isAirBlock(x, y + 1, z) && BlockStaticLiquid_isFlammable(world, x, y, z, ForgeDirection.UP))
					{
						world.setBlock(x, y + 1, z, Blocks.fire);
					}
				}
			}
		}
	}

	public static boolean BlockStaticLiquid_isFlammable(World world, int x, int y, int z, ForgeDirection dir) {
		return world.getBlock(x, y, z).isFlammable(world, x, y, z, dir);
	}

	public static boolean BlockStaticLiquid_isFlammable(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).isFlammable(world, x, y, z, ForgeDirection.UNKNOWN);
	}

	public static boolean EntityAICreeperSwell_shouldExecute(EntityCreeper swellingCreeper) {
		EntityLivingBase target = swellingCreeper.getAttackTarget();
		if(swellingCreeper.getCreeperState() > 0) return true;
		if(target == null) return false;
		double distSq = swellingCreeper.getDistanceSqToEntity(target);
		if(distSq >= 9.0) return false;
		// Do this last since it's the most 'work'
		double facing = Vec3
				.createVectorHelper(target.posX, target.posY, target.posZ)
				.subtract(Vec3.createVectorHelper(swellingCreeper.posX, swellingCreeper.posY, swellingCreeper.posZ))
				.normalize()
				.dotProduct(target.getLookVec());
		return facing >= -0.F;
	}
}
