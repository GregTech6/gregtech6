/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.entities;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class Override_Drops {
	public static void handleDrops(EntityLivingBase aDead, String aClass, List<EntityItem> aDrops, int aLooting, boolean aBurn, boolean aPlayerKill) {
		if (UT.Code.stringInvalid(aClass) || "EntityTFLichMinion".equalsIgnoreCase(aClass) || "EntitySkeletonBoss".equalsIgnoreCase(aClass)) return;
		final boolean aSpace = aClass.startsWith("entityevolved") || aClass.startsWith("entityalien");
		boolean tReplaceIron = aClass.startsWith("entitygaia");
		
		int tRandomNumber = RNGSUS.nextInt(Math.max(36, 144-aLooting*3)), tIntestinesAmount = 0;
		
		if (aDead instanceof EntityAnimal && aDead.isChild()) {
			tReplaceIron = T;
		} else if ("ZombieFarmer".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			ItemStack tGrass = UT.Code.select(IL.Grass, IL.Grass, IL.Grass_Dry, IL.Grass_Moldy, IL.Grass_Rotten).get(1);
			
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?ST.copy(tGrass):OP.rockGt.mat(MT.Stone, 1)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?ST.copy(tGrass):IL.Stick.get(1)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?ST.copy(tGrass):IL.Mud_Ball.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?ST.copy(tGrass):IL.Tool_Matches.get(1)));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(OP.toolHeadHoe.mat(MT.Bronze, 1)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, OP.toolHeadSpade.mat(MT.Bronze, 1)
			, OP.toolHeadSpade.mat(MT.Bronze, 1)
			, OP.toolHeadSpade.mat(MT.Bronze, 1)
			, OP.toolHeadSpade.mat(MT.Bronze, 1)
			, OP.toolHeadHoe.mat(MT.Bronze, 1)
			, OP.toolHeadHoe.mat(MT.Bronze, 1)
			, OP.toolHeadAxe.mat(MT.Bronze, 1)
			, OP.toolHeadPlow.mat(MT.Bronze, 1)
			, OP.toolHeadSense.mat(MT.Bronze, 1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Veggie_6.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fruit_2.get(1+RNGSUS.nextInt(2))
			)));
			}
			
			}
		} else if ("ZombieMiner".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?OP.rockGt.mat(MT.Stone, 1):ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.Stick.get(1)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, OP.rockGt.mat(RNGSUS.nextBoolean()?MT.Ag:MT.Au, 1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(OP.toolHeadPickaxe.mat(MT.Bronze, 1)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, OP.toolHeadShovel.mat(MT.Bronze, 1)
			, OP.toolHeadShovel.mat(MT.Bronze, 1)
			, OP.toolHeadShovel.mat(MT.Bronze, 1)
			, OP.toolHeadShovel.mat(MT.Bronze, 1)
			, OP.toolHeadPickaxe.mat(MT.Bronze, 1)
			, OP.toolHeadPickaxe.mat(MT.Bronze, 1)
			, OP.toolHeadChisel.mat(MT.Bronze, 1)
			, OP.toolHeadHammer.mat(MT.Bronze, 1)
			, OP.toolHeadFile.mat(MT.Bronze, 1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Meat_5.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fish_4.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Chum_4.get(1+RNGSUS.nextInt(2))
			, IL.Dynamite.get(1+RNGSUS.nextInt(6))
			)));
			}
			
			}
		} else if ("ZombieSoldier".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.Stick.get(1)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, IL.Mud_Ball.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtSmall.mat(MT.Steel, 1+RNGSUS.nextInt(2))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Invar_Empty.get(1)
			, IL.Tool_Lighter_Invar_Full.get(1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Rotten_1.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(2))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_MatchBox_Full.get(1))
			)));
			}
			
			}
		} else if ("ZombiePigmanSoldier".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			@SuppressWarnings("rawtypes")
			List tList = aDead.worldObj.getEntitiesWithinAABBExcludingEntity(aDead, aDead.boundingBox.expand(32, 32, 32));
			for (int i = 0; i < tList.size(); i++) if (tList.get(i) instanceof EntityPlayer) {for (int j = 0; j < tList.size(); j++) if (tList.get(j) instanceof EntityPigZombie) ((EntityPigZombie)tList.get(j)).attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)tList.get(i)), 0); break;}
			
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?OP.rockGt.mat(MT.Netherrack, 1):ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, Items.bone, 1, 0));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, OP.rockGt.mat(MT.Au, 1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtMedium.mat(MT.Steel, 1+RNGSUS.nextInt(2))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Invar_Empty.get(1)
			, IL.Tool_Lighter_Invar_Full.get(1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Rotten_1.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(2))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_MatchBox_Full.get(1))
			)));
			}
			
			}
		} else if ("SkeletonSoldier".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, Items.bone, 1, 0));
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtSmall.mat(MT.Steel, 1+RNGSUS.nextInt(2))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Milk.get(1+RNGSUS.nextInt(3))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Rotten_1.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(2))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_MatchBox_Full.get(1))
			)));
			}
			
			}
		} else if ("Bandit".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.Stick.get(1)));
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtSmall.mat(MT.Steel, 1+RNGSUS.nextInt(2))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_Lighter_Plastic_Broken.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fruit_2.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fish_4.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Meat_5.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Veggie_6.get(1+RNGSUS.nextInt(2))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_MatchBox_Full.get(1))
			)));
			}
			
			}
		} else if ("ArmySoldier".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtMedium.mat(MT.Steel, 1+RNGSUS.nextInt(2))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_MatchBox_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Invar_Full.get(1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fruit_2.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Fish_4.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Meat_5.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Veggie_6.get(1+RNGSUS.nextInt(3))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_MatchBox_Full.get(1))
			)));
			}
			
			}
		} else if ("Commando".equalsIgnoreCase(aClass) || "StormTrooper".equalsIgnoreCase(aClass) || "Outcast".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtLarge.mat(MT.Steel, 1)));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Lighter_Invar_Full.get(1)
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(2))
			, IL.Food_Can_Fruit_2.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Bread_3.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Fish_4.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Meat_5.get(1+RNGSUS.nextInt(3))
			, IL.Food_Can_Veggie_6.get(1+RNGSUS.nextInt(3))
			, IL.Compass_North.get(1)
			, IL.Pill_Iodine.get(1)
			, IL.Duct_Tape.get(1, IL.Tool_Lighter_Plastic_Full.get(1))
			)));
			}
			
			}
		} else if ("DictatorDave".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, OP.bulletGtSmall.mat(MT.Steel, 1+RNGSUS.nextInt(8))));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.KEYS_FANCY[RNGSUS.nextInt(IL.KEYS_FANCY.length)].getWithName(1, "Fancy Useless Key")));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.Compass_North.get(1)));
			if (RNGSUS.nextInt( 4) == 0) aDrops.add(ST.entity(aDead, (RNGSUS.nextBoolean()?IL.Tool_Lighter_Platinum_Empty:IL.Tool_Lighter_Platinum_Full).get(1)));
			}
		} else if ("PsychoSteve".equalsIgnoreCase(aClass)) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 3) == 0) aDrops.add(ST.entity(aDead, IL.Stick.get(1)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1+RNGSUS.nextInt(4))));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, IL.Dynamite.get(1+RNGSUS.nextInt(8))));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(ST.make(Items.name_tag, 1, 0)
			, IL.KEYS_CHEAP[RNGSUS.nextInt(IL.KEYS_CHEAP.length)].getWithName(1, "Random Useless Key")
			, IL.Tool_Lighter_Plastic_Full.get(1)
			, IL.Tool_Remote_Activator.get(1)
			, IL.Food_Ice_Cream_Bear.get(1+RNGSUS.nextInt(4))
			, IL.Food_Can_Chum_4.get(1+RNGSUS.nextInt(4))
			, IL.Bottle_Beer.get(1+RNGSUS.nextInt(6))
			, IL.Pill_Red.get(1+RNGSUS.nextInt(4))
			, IL.Pill_Blue.get(1+RNGSUS.nextInt(4))
			, IL.Compass_Death.get(1)
			)));
			}
			
			}
		} else if ("CyberDemon".equalsIgnoreCase(aClass)) {// TODO
			tReplaceIron = T;
		} else if ("SuperMutantBasic".equalsIgnoreCase(aClass)) {// TODO
			tReplaceIron = T;
		} else if ("SuperMutantHeavy".equalsIgnoreCase(aClass)) {// TODO
			tReplaceIron = T;
		} else if ("SuperMutantElite".equalsIgnoreCase(aClass)) {// TODO
			tReplaceIron = T;
		} else if (aDead instanceof EntityPigZombie) {
			tReplaceIron = T;
			
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, OP.stick.mat(MT.WOODS.Crimson, 1)));
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?OP.rockGt.mat(MT.Netherrack, 1):ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, Items.bone, 1, 0));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.rockGt.mat(MT.Au, 1)));
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, IL.Tool_Matches.get(1)));
			}
		} else if (aDead instanceof EntityZombie) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			if (RNGSUS.nextInt( 2) == 0) aDrops.add(ST.entity(aDead, RNGSUS.nextBoolean()?OP.rockGt.mat(aSpace?MT.STONES.SpaceRock:MT.Stone, 1):aSpace?OP.rockGt.mat(MT.MeteoricIron, 1):ST.make(Items.flint, 1, 0)));
			if (RNGSUS.nextInt( 5) == 0) aDrops.add(ST.entity(aDead, aSpace?OP.stick.mat(MT.Plastic, 1):IL.Stick.get(1)));
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, aSpace?OP.scrapGt.mat(MT.Plastic, 1):IL.Mud_Ball.get(1)));
			if (RNGSUS.nextInt(20) == 0) aDrops.add(ST.entity(aDead, aSpace?OP.nugget.mat(MT.MeteoricIron, 1):IL.Tool_Matches.get(1)));
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(IL.Food_Pomeraisins
			, IL.Food_Raisins_Green
			, IL.Food_Raisins_Purple
			, IL.Food_Raisins_White
			, IL.Food_Raisins_Red
			, IL.Food_Pomeraisins
			).get(1)));
			}
			
			if (((EntityZombie)aDead).isVillager()) for (int i = 0, j = 1+RNGSUS.nextInt(3); i < j; i++) switch(RNGSUS.nextInt(20)) {
			case  0: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Creeper")));  break;
			case  1: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Skeleton"))); break;
			case  2: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Zombie")));   break;
			case  3: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Spider")));   break;
			case  4: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_End")));      break;
			case  5: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Blaze")));    break;
			case  6: aDrops.add(ST.entity(aDead, ST.book("Manual_Hunting_Witch")));    break;
			case  7: aDrops.add(ST.entity(aDead, ST.book("Manual_Elements")));         break;
			case  8: aDrops.add(ST.entity(aDead, ST.book("Manual_Alloys")));           break;
			case  9: aDrops.add(ST.entity(aDead, ST.book("Manual_Smeltery")));         break;
			case 10: aDrops.add(ST.entity(aDead, ST.book("Manual_Extenders")));        break;
			case 11: aDrops.add(ST.entity(aDead, ST.book("Manual_Printer")));          break;
			case 12: aDrops.add(ST.entity(aDead, ST.book("Manual_Steam")));            break;
			case 13: aDrops.add(ST.entity(aDead, ST.book("Manual_Random")));           break;
			default: aDrops.add(ST.entity(aDead, ST.book(UT.Books.MATERIAL_DICTIONARIES.get(RNGSUS.nextInt(UT.Books.MATERIAL_DICTIONARIES.size()))))); break;
			}
			}
		} else if (aDead instanceof EntitySpider) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(IL.Food_Cookie_Chocolate_Raisins
			, IL.Food_Cookie_Raisins
			, IL.Food_Cookie_Chocolate_Raisins
			).getWithName(1, aSpace?"Space Spider Cookie":"Spider Cookie")));
			}
			
			}
		} else if (aDead instanceof EntitySkeleton) {
			tReplaceIron = T;
			
			if (aPlayerKill) {
			
			if (tRandomNumber == 0) {
			aDrops.add(ST.entity(aDead, UT.Code.select(IL.Bottle_Milk.get(1)
			, IL.Bottle_Milk.get(1)
			, IL.Bottle_Milk.get(1)
			, IL.Bottle_Milk.get(1)
			, ST.update(aSpace?OP.arrowGtPlastic.mat(MT.MeteoricIron, 1):OP.arrowGtWood.mat(MT.Bronze, 1))
			, ST.update(aSpace?OP.arrowGtPlastic.mat(MT.MeteoricIron, 1):OP.arrowGtWood.mat(MT.Bronze, 1))
			, ST.update(aSpace?OP.arrowGtPlastic.mat(MT.MeteoricSteel, 1):OP.arrowGtWood.mat(MT.Bronze, 1))
			, ST.update(aSpace?OP.arrowGtPlastic.mat(MT.MeteoricSteel, 1):OP.arrowGtWood.mat(MT.DamascusSteel, 1))
			)));
			}
			
			}
		} else if (aClass.equalsIgnoreCase("EntityHoglin")) {
			tReplaceIron = T;
		} else if (aClass.equalsIgnoreCase("EntityZoglin")) {
			tReplaceIron = T;
		} else if (aClass.equalsIgnoreCase("EntityStrider")) {
			tReplaceIron = T;
		} else if (aClass.equalsIgnoreCase("EntityTFWraith")) {
			tReplaceIron = T;
			if (RNGSUS.nextInt(10) == 0) aDrops.add(ST.entity(aDead, OP.dust.mat(MT.Ectoplasm, 1)));
		} else if (aClass.equalsIgnoreCase("MoCEntityBoar")) {
			tReplaceIron = T;
			int tAmount = 1+RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?Items.cooked_porkchop:Items.porkchop, 1, 0));
		} else if (aClass.equalsIgnoreCase("MoCEntityDeer")) {
			tReplaceIron = T;
			ItemStack tRaw    = IL.TF_Venison_Raw   .get(1, ST.make(MD.HaC, "venisonrawItem"   , 1, 0));
			ItemStack tCooked = IL.TF_Venison_Cooked.get(1, ST.make(MD.HaC, "venisoncookedItem", 1, 0));
			if (tRaw != null && tCooked != null) {
				int tAmount = 1+RNGSUS.nextInt(3);
				if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
				while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?tCooked:tRaw));
			}
		} else if (aClass.equalsIgnoreCase("MoCEntityOstrich")) {
			tReplaceIron = T;
			int tAmount = 1+RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, Items.feather, 1, 0));
		} else if (aClass.equalsIgnoreCase("MoCEntityHorse")) {
			tReplaceIron = T;
			int tAmount = 2+RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Horse_Cooked.get(1):IL.Food_Horse_Raw.get(1)));
		} else if (aClass.equalsIgnoreCase("EntityTFQuestRam")) {
			tReplaceIron = T;
			int tAmount = 24+RNGSUS.nextInt(8);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting*8+1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Mutton_Cooked.get(1):IL.Food_Mutton_Raw.get(1)));
		} else if (aClass.equalsIgnoreCase("EntityWarg") || aClass.equalsIgnoreCase("EntityHellhound") || aClass.equalsIgnoreCase("MoCEntityWWolf") || aClass.equalsIgnoreCase("EntityTFMistWolf") || aClass.equalsIgnoreCase("EntityTFWinterWolf")) {
			tReplaceIron = T;
			int tAmount = 1+RNGSUS.nextInt(4);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_DogMeat_Cooked.get(1):IL.Food_DogMeat_Raw.get(1)));
		} else if (aDead instanceof EntityHorse) {
			tReplaceIron = T;
			int tAmount = 1+RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			if (RNGSUS.nextInt(Math.max(1, 10-(int)(((EntityHorse)aDead).getHorseJumpStrength()*10.0))) == 0) tAmount += 1+RNGSUS.nextInt(aLooting + 1)/2;
			if (RNGSUS.nextInt(Math.max(1, 30-(int)(((EntityHorse)aDead).getMaxHealth()))) == 0) tAmount += 1+RNGSUS.nextInt(aLooting + 1)/2;
			switch(((EntityHorse)aDead).getHorseType()) {
			case 0: while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Horse_Cooked.get(1):IL.Food_Horse_Raw.get(1))); break;
			case 1: while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Donkey_Cooked.get(1):IL.Food_Donkey_Raw.get(1))); break;
			case 2: while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Mule_Cooked.get(1):IL.Food_Mule_Raw.get(1))); break;
			case 3: while (tAmount-->0) aDrops.add(ST.entity(aDead, ST.make(Items.rotten_flesh, 1, 0))); break;
			case 4: while (tAmount-->0) aDrops.add(ST.entity(aDead, ST.make(Items.bone, 1, 0))); break;
			}
		} else if (aDead instanceof EntityWolf) {
			tReplaceIron = T;
			int tAmount = RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_DogMeat_Cooked.get(1):IL.Food_DogMeat_Raw.get(1)));
		} else if (aClass.equalsIgnoreCase("EntityAerbunny")) {
			tReplaceIron = T;
		} else if (aClass.equalsIgnoreCase("EntitySheepuff")) {
			tReplaceIron = T;
			int tAmount = RNGSUS.nextInt(3);
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Mutton_Cooked.get(1):IL.Food_Mutton_Raw.get(1)));
		} else if (aDead instanceof EntitySheep) {
			tReplaceIron = T;
			if (!MD.EtFu.mLoaded && !MD.GaSu.mLoaded) {
			int tAmount = RNGSUS.nextInt(3);
			if (MD.HaC.mLoaded) tAmount--;
			if (aLooting > 0) tAmount += RNGSUS.nextInt(aLooting + 1);
			while (tAmount-->0) aDrops.add(ST.entity(aDead, aBurn?IL.Food_Mutton_Cooked.get(1):IL.Food_Mutton_Raw.get(1)));
			}
		}
		
		for (EntityItem tEntity : aDrops) {ItemStack tStack = tEntity.getEntityItem(); if (ST.valid(tStack)) {
			// Replace some of the Arrows with Headless Arrows.
			if (ST.item_(tStack) == Items.arrow && RNGSUS.nextInt(aLooting * 2 + 4) < 3) {
				ST.set(tStack, OP.arrowGtWood.mat(MT.Empty, 1), F, F);
			}
			// Replace Iron and Steel with Lead.
			if (tReplaceIron) {
				if (OM.is("plateAnyIronOrSteel", tStack)) {
					ST.set(tStack, OP.plate.mat(MT.Pb, 1), F, F);
				} else
				if (OM.is("ingotAnyIronOrSteel", tStack)) {
					ST.set(tStack, OP.ingot.mat(MT.Pb, 1), F, F);
				} else
				if (OM.is("chunkGtAnyIronOrSteel", tStack)) {
					ST.set(tStack, OP.chunkGt.mat(MT.Pb, 1), F, F);
				} else
				if (OM.is("nuggetAnyIronOrSteel", tStack)) {
					ST.set(tStack, OP.nugget.mat(MT.Pb, 1), F, F);
				}
			}
			// Give Meat more variety! :D
			if (!OD.listAllmeatsubstitute.is(tStack)) {
				if (RNGSUS.nextInt(3) == 0 && (OM.is("listAllmeatraw", tStack) || OM.is("listAllmeatcooked", tStack))) tIntestinesAmount++;
				if (ST.item_(tStack) == Items.porkchop) {
					switch(tRandomNumber%3) {
					case 0: ST.set(tStack, (aBurn?IL.Food_Ham_Cooked:IL.Food_Ham_Raw).get(1), F, F); break;
					case 1: ST.set(tStack, (aBurn?IL.Food_Bacon_Cooked:IL.Food_Bacon_Raw).get(UT.Code.bindStack(tStack.stackSize * (3+RNGSUS.nextInt(3)))), T, F); break;
					}
				} else
				if (ST.item_(tStack) == Items.cooked_porkchop) {
					switch(tRandomNumber%3) {
					case 0: ST.set(tStack, IL.Food_Ham_Cooked.get(1), F, F); break;
					case 1: ST.set(tStack, IL.Food_Bacon_Cooked.get(UT.Code.bindStack(tStack.stackSize * (3+RNGSUS.nextInt(3)))), T, F); break;
					}
				} else
				if (OM.is("listAllbeefraw", tStack)) {
					switch(tRandomNumber%3) {
					case 0: ST.set(tStack, (aBurn?IL.Food_Rib_Cooked:IL.Food_Rib_Raw).get(1), F, F); break;
					case 1: ST.set(tStack, (aBurn?IL.Food_RibEyeSteak_Cooked:IL.Food_RibEyeSteak_Raw).get(1), F, F); break;
					}
				} else
				if (OM.is("listAllbeefcooked", tStack)) {
					switch(tRandomNumber%3) {
					case 0: ST.set(tStack, IL.Food_Rib_Cooked.get(1), F, F); break;
					case 1: ST.set(tStack, IL.Food_RibEyeSteak_Cooked.get(1), F, F); break;
					}
				} else
				if (OM.is("listAllhorseraw", tStack) || OM.is("listAllvenisonraw", tStack)) {
					switch(tRandomNumber%2) {
					case 0: ST.set(tStack, (aBurn?IL.Food_Rib_Cooked:IL.Food_Rib_Raw).get(1), F, F); break;
					}
				} else
				if (OM.is("listAllhorsecooked", tStack) || OM.is("listAllvenisoncooked", tStack)) {
					switch(tRandomNumber%2) {
					case 0: ST.set(tStack, IL.Food_Rib_Cooked.get(1), F, F); break;
					}
				}
			}
			
			tEntity.setEntityItemStack(tStack);
			tRandomNumber++;
		}}
		
		while (tIntestinesAmount-->0) aDrops.add(ST.entity(aDead, IL.Food_Scrap_Meat.get(1)));
		
		if (aDead instanceof EntityLiving && ((EntityLiving)aDead).isNoDespawnRequired() && ((EntityLiving)aDead).hasCustomNameTag()) {
			aDrops.add(ST.entity(aDead, ST.make(Items.name_tag, 1, 0, ((EntityLiving)aDead).getCustomNameTag())));
		}
	}
}
