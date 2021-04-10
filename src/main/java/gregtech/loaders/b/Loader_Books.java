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

package gregtech.loaders.b;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ItemsGT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Books implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_Mod: Registering Books.");
		
		String tPage = "";
		List<String> tBook = new ArrayListNoNulls<>();
		
		UT.Books.createWrittenBook("Manual_Punch_Cards", "Punch Card Manual V0.0", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 1), new String[] {
		  "This Manual will explain the Functionality of the Punch Cards, once they are fully implemented. And no, they won't be like the IRL Punch Cards. This is just a current Idea Collection."
		, "(i1&&i2)?o1=15:o1=0;=10"
		, "ignore all Whitespace Characters, use Long for saving the Numbers"
		, "&& || ^^ & | ^ ! ++ -- + - % / // * ** << >> >>> < > <= >= == !=  ~ ( ) ?: , ; ;= ;=X; = i0 i1 i2 i3 i4 i5 o0 o1 o2 o3 o4 o5 v0 v1 v2 v3 v4 v5 v6 v7 v8 v9 m0 m1 m2 m3 m4 m5 m6 m7 m8 m9 A B C D E F"
		, "'0' = false, 'everything but 0' = true, '!' turns '0' into '1' and everything else into '0'"
		, "',' is just a separator for multiple executed Codes in a row."
		, "';' means that the Program waits until the next tick before continuing. ';=10' and ';=10;' both mean that it will wait 10 Ticks instead of 1. And ';=0' or anything < 0 will default to 0."
		, "If the '=' Operator is used within Brackets, it returns the value the variable has been set to."
		, "The Program saves the Char Index of the current Task, the 10 Variables (which reset to 0 as soon as the Program Loop stops), the 10 Member Variables and the remaining waiting Time in its NBT."
		, "A = 10, B = 11, C = 12, D = 13, E = 14, F = 15, just for Hexadecimal Space saving, since Redstone has only 4 Bits."
		, "For implementing Loops you just need 1 Punch Card per Loop, these Cards can restart once they are finished, depending on how many other Cards there are in the Program Loop you inserted your Card into, since it will process them procedurally."
		, "A Punch Card Processor can run up to four Loops, each with the length of seven Punch Cards, parallel."
		, "Why does the Punch Card need Ink to be made, you ask? Because the empty one needs to have some lines on, and the for the punched one it prints the Code to execute in a 'human' readable format on the Card."
		});
		
		UT.Books.createWrittenBook("Manual_Microwave", "Microwave Oven Manual", "Kitchen Industries", ST.make(ItemsGT.BOOKS, 1, 2), new String[] {
		  "Congratulations, you inserted a random seemingly empty Book into the Microwave and these Letters appeared out of nowhere."
		, "You just got a Microwave Oven and asked yourself 'why do I even need it?'. It's simple, the Microwave can cook for just 128 EU and at an insane speed. Not even a normal E-furnace can do it that fast and cheap!"
		, "This is the cheapest and fastest way to cook for you. That is why the Microwave Oven can be found in almost every Kitchen (see www.youwannabuyakitchen.ly)."
		, "Long time exposure to Microwaves can cause Cancer, but we doubt Steve lives long enough to die because of that."
		, "Do not insert any Metals. It might result in an Explosion."
		, "Do not dry Animals with it. It will result in a Hot Dog, no matter which Animal you put into it."
		, "Do not insert inflammable Objects. The Oven will catch on Fire."
		, "Do not insert Explosives such as Eggs. Just don't."
		});
		
		UT.Books.createWrittenBook("Manual_Printer", "Scanner & Printer Manual", "GregTech Electrics", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "Congratulations on your acquisition of a new Scanner and/or Printer. You will find this Manual that you received with your purchase very useful. Just remember that if you lose it, you can just let the Printer create a new one."
		, "First of all you need to power your Scanner and Printer with Electricity on their back side. Just plug them into the sockets of their respective Voltage. Next you will need either an USB Cable or an USB Stick to connect them."
		, "Any USB Stick will do, Images and Text Files are not that large and won't require more than USB 1.0, even though you can ofcourse use higher USB Tiers aswell, thanks to downwards compatibility."
		, "Now you need to refill your Printers Ink reserves, depending on what Job you want to do with it. It only accepts the Chemical Dyes for this purpose. Black = Text, CMYK for Images, Blue or White for Blueprints."
		, "Having done all of that it is time to scan what you need to scan inside the Scanner. Just plug in the USB and insert the Book/Canvas/Blueprint etc. you want to scan. If you insert Blocks it will scan their Surface aswell."
		, "After a successful scan, it will eject the eventual USB Stick and the scanned Object for you. Now you can insert the Data and the desired type of Paper/Canvas into the Printer to let it print your products."
		, "The Printer can do a few more print jobs than just canvas and books. NEI will help you with all the possible Recipes."
		});
		
		UT.Books.createWrittenBook("Manual_Steam", "Steam Manual", "GregTech Thermodynamics", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "This Manual is about the proper operation of a Boiler, in order to not blow yourself up. And yes there are many people who get blown up by improper operation of a Boiler."
		, "First of all you need to make sure that the Boiler you use fits the Burning Box you place below. The Tooltips will hint you the proper values you can use. The Boiler itself does accept heat from all directions, not just the Bottom."
		, "Then you need to fill the Boiler with Water on the Sides, so that you can generate Steam with it. Placing a Funnel on it and rightclicking that Funnel with a Water Bucket works for filling aswell."
		, "But if you use regular Water instead of distilled Water, then you will end up with calcification and a constant decrease of Steam-Creation-Efficiency. No risk of explosion due to that though, since it just makes less Steam per Water."
		, "Inserting Water into an empty hot Boiler, it will rapidly increase the Pressure maybe causing it to explode, depending on how much heat and water you insert at once."
		, "If a Boiler stores too much Heat without being able to convert Water into Steam, then it will simply melt and break, leaving you with a giant mess."
		, "Steam Turbines and Engines emit distilled Water to their Sides, which makes it possible to simply cycle the distilled Water back into the Boiler for having less calcification going on."
		, "The Boilers have a Barometer on their Front, their ideal State is if the Line is inside the dark green Area, so in the middle of the Barometer itself. The closer it is to the red Area, the closer it is to exploding."
		, "Steam Engines work best if they are in green Mode, if they are too blue then they have not enough Steam (look for Boiler Calcification if it happens after a while of running normally)."
		, "If the Engines are too Red then they will release the Steam and stop running (no Explosions, just a waste of Steam and a fizzing noise)."
		});
		
		UT.Books.createWrittenBook("Manual_Random", "Tips & Tricks with GregTech Six", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "This Book contains random Information that might be useful sometime. Read it whenever you have Issues. Maybe it has the Solution."
		, "The first Hint is obvious: ALWAYS. READ. THE. ENTIRE. TOOLTIP. FIRST! The Tooltips often contain the Information you need, but not many people think about reading them. They usually contain all the Stats of the Machine."
		, "If a Machine doesn't process a certain Recipe (but does others), then you are not supplying enough Power. This is ALWAYS the Issue. The Tier of a Machine determines how much more Energy it consumes, than the normal Recipe."
		, "The Tiers of Machines are as follows: anything between 16 and 64 Generic Units per Tick is Tier ONE\nTier 2: 65-256\nTier 3: 257-1024\nTier 4: 1025-4096\nTier 5: 4097-16192"
		, "If the Tier of the Machine you are using is in a higher Range than the Tier of the Recipe (see its GU/t) it will multiply the required Input GU/t by 4 and half the Processing time, making the Recipe twice as expensive."
		, "And yes, 64 GU/t is STILL TIER ONE, you seriously have to insert that much into a Tier 1 Machine! It is often possible to use the Motor/Heater/etc of a higher Tier on the lowest Power Input to get the GU/t."
		, "GT6 Electric Power is widely compatible with all Mods that use the IC² API, such as:\nIC², IC²-Classic, IC²-Addons, Railcraft, Forestry and more!"
		, "Things emitting a Voltage less than 1024 EU/t are even capable of powering the non-IC² Machines of the following Mods:\nGalacticraft and it's Addons, Applied Energistics, Immersive Engineering, OpenModularTurrets, TechGuns and even GT5U!"
		, "Burning Boxes of any kind are always a Fire Hazard, make sure to only have Fireproof Stuff around them! The only ways to turn those Boxes off are placing a solid Block in front of them or the Fire Extinguishers."
		, "You want to move Water or get Milk, but don't have an Iron Bucket? Try making a Wooden Bucket for that Task, it's cheaper, even if it only works for a small sortiment Liquids."
		, "If you need a Printer Manual, then just insert Chemical Black Dye and an empty vanilla Book into it and apply Energy. It will instantly print that Manual."
		, "The Bathing Pot and the Bath can dye Leather Armor using Liquid Dyes.\nYou can wash Ores early on by throwing them into a filled vanilla Cauldron. Also GT Pipes can fill Cauldrons with Water."
		, "Space out Machines a little in order to be able to automate them later without causing a huge Clusterfuck. Always have enough space for 2 Blocks inbetween each Machine, if not 3! Disregard the eventual Loss of Power, it's not worth it."
		});
		
		UT.Books.createWrittenBook("Manual_Portal_TF", "Benimatics Twilight Forest", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 5), new String[] {
		  "This Books existence means that you found a GregTech 6 Dungeon with a Twilight Forest Portal inside of it, congratulations. If you don't know how to light the Portal to go on the Adventure, don't worry, in here are some instructions."
		, "Step 1: You see the 4x4 of Grass Blocks with 12 Flowers growing on it and a 2x2 Pool of Water in the middle? That is going to be the Twilight Forest Portal."
		, "Step 2: All you need to do to activate that Portal, is throwing a Diamond into that Water Pool, and you are ready to go. Yep, it is that simple. I did all the work for you and summoned the Blocks of the Portal in the right configuration."
		});
		
		String tAlexGryllsIntro = "Hi, I'm Alex Grylls, Adventurer and experienced Hunter of the several Mobs you encounter in our World. In this Guide I will explain you easy Methods to hunt for wild Mobs in their natural Habitats.";
		
		
		UT.Books.createWrittenBook("Manual_Hunting_Creeper", "Hunting Guide for Creepers", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Creepers\n===================\nCreepers are sneaky, suicidal, leafy sticks on 4 short Legs, which will blow up when they are close by. They drop Gunpowder and will not deal any damage aside from the explosion when you let them come close."
		, "Creepers\n===================\nIf a Creeper is struck by Lightning it will become supercharged and will explode with a much larger force. Creepers in general when shot by a Skeleton, will drop Music Discs, which are usable for the Jukebox."
		, "Creepers\n===================\nIn order to detonate, a Creeper needs to see the Player, due to this it is always recommended to take the high Ground as you can hide behind the Cliff you are standing on more easily."
		, "Creepers\n===================\nWater is also a good place to melee a Creeper, as knockback will get it out of explosion Range, and even if it explodes it will not cause Damage to the ground, due to the Water absorbing the shock."
		, "Creepers\n===================\nFor Melee Combat in general it is adviced to have Weapons with a strong Knockback so you don't need to sprint to knock the Creeper out of the Detonation Zone. You can also just shoot an Arrow first and then melee."
		, "Creepers\n===================\nLast but not least, you can always just drop Water between you and the Creeper, it will slow it down considerable and if its about to explode anyways, then it won't damage the Terrain."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Skeleton", "Hunting Guide for Skeletons", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Skeletons\n===================\nSkeletons, usually named after Fonts and armed with Bow and Arrows. They are good at aiming, some of them are even capable of picking up dropped Weapons and Armor."
		, "Skeletons\n===================\nIf they somehow kill Creepers, those Creepers will drop Music Discs. Killing a Skeleton on long Range will yield the Sniper Duel Achievement aswell."
		, "Skeletons\n===================\nIn order to not have a bad time with Skeletons, you need to either keep a Safe distance from them and shoot them from far away, or to quickly engage them with Melee Attacks, Speed is useful for this purpose."
		, "Skeletons\n===================\nWhen you charge at a Skeleton, strave slightly to the right or left in order to have less Arrows hit you, any way to instantly kill them with one blow, will greatly reduce the chance of more Arrows flying at you."
		, "Skeletons\n===================\nIf you have a Sword then blocking with it will reduce the Damage of Arrows. This can easily be used even during Melee to not get that large amounts of Damage, even if you get hit."
		, "Skeletons\n===================\nBacking a Skeleton into a Corner, by pushing it or knocking it back for example, will result in the Arrow getting Stuck in the Wall in a glitchy fashion and therefore not flying at you or damaging you."
		, "Skeletons\n===================\nWalls of any kind are useful, but what really helps (if you are prepared) is Sugar Canes / Reeds as they can block their view on you, while letting you shoot through."
		, "Skeletons\n===================\nYou can also use Saplings and (sadistically) Bonemeal to instantly create a small Forest as a Shield in the Grasslands."
		, "Skeletons\n===================\nBeing in Water makes you an easy Target for the Skeleton, since you are slow and constantly get knocked back. But the Skeleton being in Water can be worse as it bounces up and down without losing accuracy!"
		, "Skeletons\n===================\nBut if both you and the Skeleton are in deep water then diving below the surface and attacking the Skeleton from below is the best advise I can give, as the Skeletons often miss at steep angles."
		, "Skeletons\n===================\nIn some cases it is possible to just drop a Stone or a Wooden Sword for the Skeleton to pick up, they will drop their Bow in this case. However, this does not work on every Skeleton!"
		, "Skeletons\n===================\nSkeletons are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. The Smite Enchant will also help a lot."
		, "Skeletons\n===================\nDue to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. Since Skeletons shoot Arrows, it is a good Idea to wear Armor with Projectile Protection on it."
		, "Skeletons\n===================\nIt is always a good Idea to just make Skeletons attack each other or other Mobs in order to make the attacked Mob angry at them (instead of you), causing them to kill each other."
		, "Spider Jockeys\n===================\nEver saw a Skeleton Ride a Spider? Yeah its a terrifying picture. The speed and mobility of a Spider and a 'Turret' on its back."
		, "Spider Jockeys\n===================\nTo fight them you can use easy tricks, like making the Spider crawl up a wall into the Roof, crushing the Skeleton that rides it in the process."
		, "Spider Jockeys\n===================\nIn general you can apply the Strategies for both the Skeletons and the Spiders, to this combo. It's always the best solution to just shoot them from far away though."
		, "Spider Jockeys\n===================\nIf you have a Bow then go for the Skeleton first, if you have to Melee then go for the Spider. Alternatively you can wait for the Sun to burn the Skeleton before engaging."
		, "Wither Skeletons\n===================\nWither Skeletons are just like normal Skeletons with a Stone Sword, that instead of Arrows will drop regular Coal. They also are 3 Blocks tall and can absorb your Health."
		, "Wither Skeletons\n===================\nWhile Witherskeletons behave like any other humanoid Melee Mob, they are relatively fast and less stupid than Zombie Pigmen. If you want to know if you can handle one, practise on Zombie Pigmen first."
		, "Wither Skeletons\n===================\nDue to being about 3m tall, just like Endermen, you can essentially hide behind a 2m tall Roof and grind them into Ashes, uhh I mean Coal."
		, "Wither Skeletons\n===================\nIn Melee Situations, walking backwards and swinging is the best way to fight them without getting yourself harmed, but watch out for Fire and Lava behind you."
		, "Wither Skeletons\n===================\nAn Iron Golem is a good Idea to have with you, just take Iron Blocks and a Pumpkin with you. The Iron Golem will turn every hostile Mob in the area into scraps."
		, "Wither Skeletons\n===================\nThose black Skeletons spawn alongside regular Skeletons with Bow & Arrow inside a Nether Fortress, and they are the only really aggressive Mob there, aside from Blazes."
		, "Wither Skeletons\n===================\nIf you end up fighting both a Wither Skeleton and a Blaze, the best is to hide outside the Range of the Blaze and trying to fight the Skeleton first, or to bring the Skeleton between the Blaze and yourself."
		, "Wither Skeletons\n===================\nSince most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Wither Skeletons\n===================\nThe Heads, which are dropped by every 40th Wither Skeleton, can be used to summon the Wither, but the Wither is being talked about in another Book."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Zombie", "Hunting Guide for Zombies", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Zombies\n===================\nZombies, the most common Mob in Minecraft. Zombies always attack with Melee Damage and they can wield Weapons and wear Armor."
		, "Zombies\n===================\nZombies are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. However, this does not apply to Baby Zombies!"
		, "Zombies\n===================\nDue to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. The Smite Enchant is a good way to cause a lot of damage on them."
		, "Zombies\n===================\nDo not underestimate their Melee Damage and try to keep a safe distance while attacking them, make sure they dont hit you when you Melee them yourself."
		, "Zombies\n===================\nWhile wearing a Helmet they are immune to the Sun, that is until the Helmet loses all of its durability and breaks as it gets damaged when worn by a Zombie in Sunlight."
		, "Zombies\n===================\nZombies are attracted by Villagers, you can use them to distract them. If you want them to murder that Village, that is."
		, "Zombies\n===================\nThey can detect you from very far distances and will therefore end up swarming you from all directions, if you are not careful."
		, "Zombies\n===================\nBuild yourself a small fortification if you are in the wild. Zombies luckily cannot break Blocks so they won't be able to attack you if you build it right."
		, "Zombies\n===================\nSince Zombies normally group up on you due to coincedential pathfinding, it is well advised to just throw Splash Potions of Regeneration or Healing at them, since they are damaged by those."
		, "Zombies\n===================\nAs always, Bow and Arrows can help you killing Zombies, it is a good Idea to use golden Arrows or other Smite Arrows if possible. But usually there are too many of them to kill all."
		, "Zombies\n===================\nUse Knockback on Zombies even if not needed, otherwise there might be too many Zombies grouping up at you if you are surrounded, so keep them at distance."
		, "Zombies\n===================\nKeep Zombies in the Water, they are almost helpless there and can't shoot you like Skeletons would. They are swimming much slower than you do."
		, "Zombies\n===================\nIron Golems are made for killing Zombies, they might be a viable option for your purposes."
		, "Zombies\n===================\nThe official Mojang term for Zombies wearing golden Armor is 'Zom-Bling'."
		, "Baby Zombies\n===================\nAnnoying little Bastards would be a better Name for them, not only that they are immune to Sunlight, they are also very fast and hard to hit!"
		, "Baby Zombies\n===================\nIf you didn't need the Anti Zombie Strategies before, then now you probably need to use them, since those little things can ruin your day."
		, "Baby Zombies\n===================\nIf you have the option, go for the Water, they aren't fast there. Other than that, mow your lawn and then aim low and hit them as fast as you can!"
		, "Chicken Jockeys\n===================\nThought it couldn't get any worse? Chickens are faster than those Baby Zombies and now they actually ride the Chickens!"
		, "Chicken Jockeys\n===================\nShoot the Chicken, yes get rid of it as fast as possible, you won't be able to Melee it as the Zombie is in the way, but you can try to shoot it!"
		, "Chicken Jockeys\n===================\nOther than that? Run for your life and try to either find a pond of water or use a Water Bucket on them, maybe you get it to dismount by doing so!"
		, "Zombie Pigmen\n===================\nZombie Pigmen, usually wielding a Golden Sword and dropping Gold Nuggets. The only pieceful Zombie, well peaceful until a Player harms it."
		, "Zombie Pigmen\n===================\nIf a Player does any damage to a Zombie Pigman, all the other Pegmen get angry and try to kill you, not only are they pretty fast, but they also do lots of Damage!"
		, "Zombie Pigmen\n===================\nZombie Pigmen are immune to Fire and the Sun, if you happen to find one in the Overworld (Pig struck by Lightning, or fresh from a Portal) it is usually safe to kill."
		, "Zombie Pigmen\n===================\nThe easiest Solution is not to harm them, but it always can happen that you accidentially do harm to them, or you mine the Ores of that Nether Ores Mod."
		, "Zombie Pigmen\n===================\nIn that case, pillar up or dig yourself in, as fast as possible! You don't want to die instantly and drop all your Inventory into the Nether."
		, "Zombie Pigmen\n===================\nOnce you have done that, make yourself a comfortable spot to attack the Pigmen from a space where they cant attack you."
		, "Zombie Pigmen\n===================\nSince most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Zombie Pigmen\n===================\nAs they are undead like normal Zombies aswell, Splash Potions of Regeneration and Healing can deal great damage to them, while leaving yourself unharmed."
		, "Zombie Villagers\n===================\nThey behave just like normal Zombies, even though they look much worse, the only difference is that you can cure them to get Villagers, what will be explained in the following Pages."
		, "Zombie Villagers\n===================\nFirst of all, you need a good place to actually cure that Zombie without other Zombies turning the Villager right back into the undead Green."
		, "Zombie Villagers\n===================\nFor that you need to know, that the Transformation back into a Villager for some reason goes by faster if you use vanilla Iron Bars for the Cage."
		, "Zombie Villagers\n===================\nOnce you got the Cage, there are multiple Options on the Item you use to cure the Villager Zombie. There are multiple options."
		, "Zombie Villagers\n===================\nSplash Potions of Weakness + Golden Apples are one way. Holy Water or the rainbow colored Cure-All Pill are other alternatives. Get the Item of your choice and continue."
		, "Zombie Villagers\n===================\nNow you need to somehow lure the Zombie Villager into your Cage. Don't worry, the Villager won't remember that you punched them while they were a Zombie."
		, "Zombie Villagers\n===================\nA fishing Rod can also work wonders on moving the Zombie into the right directions. Punching them into a pit and closing it off, will work aswell."
		, "Zombie Villagers\n===================\nIf your Cage is outside (or improvised), then make sure the Sun won't burn the Villager before you can heal them. Add a Roof or something to your Cage, once you caught them."
		, "Zombie Villagers\n===================\nSo, now that you got the Villager into a Cage, you can heal them. Once you have done that, transport the Villager somewhere else, maybe with Rails and Minecarts."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Spider", "Hunting Guide for Spiders", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Spiders\n===================\nSpiders, octo-legged, climbing, pastry selling Arthropods, that can jump at you. Often accompanied by a random Potion Effect, which doesn't ever run out, making them invisible, resistant, regenerating or faster."
		, "Spiders\n===================\nThey are fast, usually faster than you unless you run. They can also climb to a certain extend, so Walls won't help much, however Spiders can easily get stuck on ceilings, where they could fall down on you!"
		, "Spiders\n===================\nThe normal way to beat them is already very effective, just melee or shoot them. They have less Health than other Mobs. The Bane of Arthropods Enchant is very effective on them."
		, "Spiders\n===================\nSpiders will jump/pounce at you when they are close, if you time it right then hitting them will knock them back farther than usual. Also always have the high Ground when fighting Spiders as otherwise they'll jump you."
		, "Spiders\n===================\nIf you are in a Desert, then look out for some tsundere Plants called 'Cactus', plural being 'Cactii', Spiders apparently love climbing them, damaging themselves in the process."
		, "Spiders\n===================\nAt daytime, Spiders are tired and usually not aggressive. If you attack them then and with critical hits, then they will die by the surprise and won't damage you."
		, "Spiders\n===================\nRunning is the best thing to do when fighting Spiders, not running away, I mean running at them or keeping a safe distance during combat. Spiders are immune to Poison, so splashing them with it isn't effective at all."
		, "Spiders\n===================\nThey get stuck at overhangs, meaning if you build a pillar with 4 overhang blocks (one at each side) Spiders won't get up and you can damage them from ontop of that pillar."
		, "Spiders\n===================\ndue to being 2m in diameter, hiding in a 1m tight spot is a good idea when fighting regular Spiders. This doesn't work on the poinsonous Cave Spiders though."
		, "Cave Spiders\n===================\nCave Spiders are just 1m in diameter and they inject poison when they melee you. They normally only spawn inside Mineshafts, since there are Spawners for them over there."
		, "Cave Spiders\n===================\nThe Spawners are surrounded by lots and lots of Webs, those webs will slow you down, so take your Sword or Silk Touch Shears and slice those Webs away, also useful if you need String."
		, "Cave Spiders\n===================\nCave Spiders often get Stuck at the wooden Pillars or hide in the ceiling at those, so be careful where you walk as they will fall down at you very surprisingly despite making lots of noise."
		, "Cave Spiders\n===================\nDue to them being poisonous, Milk or any sort of Antidote Pill are useful Items to have on your Hotbar. Also hiding from them by closing off Paths is very needed sometimes."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_End", "Hunting Guide for the End", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Endermen\n===================\nEndermen, 3m tall, black, purple eyed Teleporters with a weakness for Water and Silver (Disjunction Enchant), that can take some Blocks away from the World."
		, "Endermen\n===================\nUnder normal circumstances Endermen just mind their Business and don't care what you are doing, but they do not like getting stared at, to the point that they will attack you if you do so."
		, "Endermen\n===================\nIf you still want to look at them without them attacking you, then wear a carved Pumpkin as Helmet. But you are hunting them, probably for the Pearls, so turn to the next Page."
		, "Endermen\n===================\nSince Endermen don't usually attack you, I presume you just want their Pearls, and for that an Enderman Trap is a good Idea."
		, "Endermen\n===================\nThe simplest Method is just standing below a 2m tall ceiling, so they cannot get to you. And then stare at every Enderman around to let them rage at you and attack you."
		, "Endermen\n===================\nAnother way would be the use of Water Buckets to shield yourself off. Also they can only teleport distances of at least 16m btw, no shorter teleports are possible for them."
		, "Endermen\n===================\nAs you may know, normal Arrows are pretty much worthless against them, that is why you should use Arrows with Disjunction on it (Silver), in order to prevent them from teleporting."
		, "Endermen\n===================\nIf you encounter an angry Enderman, then try to run into the closest Forest, the low Trees are perfect hiding Spots, and they cant teleport to you if they are too close to you already."
		, "Endermen\n===================\nMelee attacks only work out if you look at their feet while swinging, as they will teleport away when you stare at them, since staring at them would freeze them into place."
		, "Endermen\n===================\nThe Weakness Potion Effect is capable of preventing an Enderman from Teleporting, make use of that and splash them. (Note, that it is GregTech adding that Feature!)"
		, "Ender Dragon\n===================\nThe Ender Dragon is essentially the Queen of the Endermen. She flies around between Obsidian Pillars with Magical Ender Crystals, that will regenerate her Health. Her attack pattern is Kamikaze Melee."
		, "Ender Dragon\n===================\nShe will obliterate every normal Block (except Endstone, Bedrock and Obsidian) when flying through it. She is immune to Knockback and Fire, and weak to the Disjunction Enchant found in Silver."
		, "Ender Dragon\n===================\nThe first thing you have to do is making sure you don't get knocked into the Void. Building Walls will only help if you have Obsidian with you, since she can't break it, unlike everything else you can bring."
		, "Ender Dragon\n===================\nOnce you are safe on the Main Island, you have to go and get rid of the Ender Crystals. They can easily be destroyed by Snowballs and Arrows. Do not melee those Crystals as they deal a huge explosion damage."
		, "Ender Dragon\n===================\nDue to the Kamikaze Ram Attack, the Ender Dragon can be fought in Melee aswell as with Bow and Arrow. Use Weapons with the Disjunction Enchant and get yourself some Strength Buff in order to deal more melee."
		, "Ender Dragon\n===================\nDuring the Fight it can happen that you stare at Endermen by accident, in order to prevent that you could either wear a Pumpkin as Hat and press F1, or just try to only look at a slight upwards angle."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Blaze", "Hunting Guide for Blazes and Ghasts", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Blazes\n===================\nBlazes are floating, fireball shooting, yellow Heads with valuable fiery Rods rotating around them. Those Rods only drop if they are killed by a living thing, making them rare. They also deal Melee Fire Damage too."
		, "Blazes\n===================\nAs they shoot Fireballs, they can be hard to get close to. My advice is either just shooting them with a Bow or being very creative and using a Fishing Rod to reel them in for Melee Combat."
		, "Blazes\n===================\nYou can also just hide behind a Wall and wait for them to come close, and then strike and hide again, so they don't get a chance to fire back. Building such a Wall between you and the Blazes is also a good Idea."
		, "Blazes\n===================\nFire Resistance is a nice Effect you should somehow have on yourself when you fight them, it makes you nearly immune to Blazes overall. Also possible is the use of Iron Golems, what not many think about doing."
		, "Blazes\n===================\nSnowballs are normally useless against Mobs but in case of Blazes they do cause some severe Damage and are easier throw than you can shoot a bow. Snow Golems wouldn't survive in the Nether but they would work too."
		, "Blazes\n===================\nUsing Wolves or Golems can lead to them knowing Blazes down the Platforms, meaning you might not get drops, or they will now attack you from a distance, only do this for Areas that are surrounded by Netherrack."
		, "Blazes\n===================\nBlazes can also be damaged by Water Buckets, sure they won't drop anything, and Water Buckets don't work in the Nether, but this Information might be useful if you got some way to get Water over there."
		, "Ghasts\n===================\nGhasts, flying, ghost alike, crying Tentacle Monsters with explosive Projectiles. Their Tears are hard to get due to them being airborne and the Nether being all like 'The Floor is Lava!'."
		, "Ghasts\n===================\nTheir Projectiles can literally be punched back at them kinda like a Baseball, what will yield the 'Return to Sender' Achievement. What is a Baseball, you ask? I don't know either."
		, "Ghasts\n===================\nPunching their Fireballs back at them can be hard to do, especially with Lag, but Arrows can deflect the fireballs aswell, meaning you can shoot Arrows at the Fireballs as a defense."
		, "Ghasts\n===================\nIn general any form of ranged Attack is advised, because it happens very rarely, that a Ghastis close enough to the ground to actually melee it."
		, "Ghasts\n===================\nIt can happen very often that you are unprepared and a Ghast shows up trying to blow you into smithereens. In that case, run away and hide, first."
		, "Ghasts\n===================\nAfter you are done finding a hiding Spot, look at where exactly the Ghast is right now, so you can stay out of its Range. Then either continue your business or shoot the Ghast."
		, "Ghasts\n===================\nIf you are going to deflect a Ghast Blast the melee way, then use a Sword for that, as it gives you a slightly increased Range. This is especially useful if you cant use a Bow."
		, "Ghasts\n===================\nGhasts are the normal kind of Mob, they are neither Undead nor Spiders, there is no special Enchant against them, and Potions work normally on them."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Witch", "Hunting Guide for Witches", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Witches\n===================\nWitches are uglier Villagers with Potion Skills. When damaged they will drink a Potion of Regeneration or Fire Resistance depending on the source of the Damage. They sometimes drop the **most recently used** Potion."
		, "Witches\n===================\nTheir attacks are Splash Potions of Poison and Harming. Since they use Fire Resistance Potions 'BURN THE WITCH!!!' is not an attack option for you. Drowning doesn't work either, as they use Water Breathing Potions."
		, "Witches\n===================\nBow and Arrow are ofcourse a good option, but if you have to melee then do it fast and run straight at the Witch to hit them. This will cause the Witch to try healing themself instead of attacking you."
		, "Witches\n===================\nA Bucket of Water can give you the needed distance from the Witch in order to attack them with ranged Weapons. But watch out, if you are too far away, the Witch may use Speed Potions to be able to catch up with you."
		, "Witches\n===================\nIn swampy Areas there is the possibility to find Witch Huts, those Huts may be very small but only Witches can spawn in there, meaning a Mob Spawning Area built around it will only spawn Witches!"
		, "Witches\n===================\nWitches drop Empty Bottles, Sticks, Redstone, Glowstone, Sugar, Spider Eyes, Gunpowder and Potions. This makes them a valuable Mob to farm and also a way to get Glowstone and Potions without the Nether."
		});
		
//      The Wither is a tough mob to fight.
//      Having more health than the Ender Dragon or iron golems, the Wither can be quite hard to fight if one is not prepared and taken the necessary precautions.
//      First of all, Withers do not naturally spawn.
//      You must get 4 soul sand from a Nether fortress and 3 wither skeleton skulls.
//      Wither skeleton skulls are dropped by wither skeletons when killed, however, they have only a 2.5% chance of dropping it.
//      You should use a looting enchantment sword if possible.
//      Once you get your three skulls and four soul sand, find a place to spawn the wither.
//      To spawn the wither, one needs to place the soul sand in a t-shaped arrangement (exactly like an iron golem's iron block arrangement).
//      Then, the three wither skulls are placed on the top of each of the three soul sand blocks that are on the top layer.
//      Beware however, once you place the last wither skeleton skull to complete the wither spawning arrangement, the wither will start to spawn.
//      We will come back to this spawning process later. 
//      Supplies needed to fight the wither depend on strategy.
//      The most strategic way requires these items:
//      A full set of diamond armor (with a high protection enchantment, thorns may be useful but is not necessary),
//      A sword with either sharpness or smite (fire aspect does not work on the wither),
//      A bow with Power IV or V, punch, and infinity, Obviously an arrow (or many if you don't have the infinity enchantment),
//      some instant health II splash potions, some strength II potions, and one or two enchanted golden apples.
//      You will see how all of these will help in the following combat strategy.
//      Again, this is one of the infinitely possible combat strategies there are for fighting the wither.
//      Make sure you are an ample distance from your house before spawning the wither.
//      This mob has the potential to be incredibly destructive.
//      When you make the arrangement for spawning the wither correctly as said above, you should see a mob appear that is black and has three heads.
//      Also you will notice a boss health bar much similar to the Ender Dragon's health bar.
//      You will also see the bar gradually filling up.
//      At this time, get your sword, bow, apples, and potions in hand and equip your armor.
//      You don't have much time.
//      Run away as fast as you can and be a good distance away from the wither as the health bar fills up.
//      When the bar is full, the wither lets out an explosion that destroys some of the terrain around it.
//      You are now ready to start fighting. The wither attacks any mob that is not undead.
//      When the wither starts to search for prey, it will look for any mob that meets its criteria for killing.
//      Your strategy right now should be to use your bow and shoot arrows at him.
//      If you hit him, he might see you and charge at you.
//      He will shoot blue and black colored skulls that look like one of his many heads.
//      If these projectiles hit you, they will deal a good amount of damage.
//      Damage done is dependent on the difficulty.
//      On normal, if one is hit with a wither skull, the wither effect may be inflicted.
//      Wither is basically the same as poison except you can be killed if your health bar is low and turns your health bar black for its duration so it is slightly hard to see how much health you have.
//      These skulls will deal terrain damage if they hit the ground.
//      The rare, blue skull is prone to destroying blocks with a much higher block resistance, including obsidian.
//      Continue to follow the pattern of shoot, flee, and repeat.
//      Keep doing this to decrease his health.
//      Use the health potions when necessary.
//      When the health reaches half, you will notice a somewhat of a force field around it.
//      The force field causes the wither to take less damage and makes it immune to arrows.
//      At this point, your bow is rendered useless.
//      Consume a golden apple and a strength potion and you have no choice but to charge in.
//      That way, the Wither is within melee range.
//      The wither will do no damage to you while you have regen from the apple.
//      You will also do much more damage because of the strength potion.
//      Hopefully, You can kill the wither in one go with the apple and potion.
//      It should be enough.
//      If you are successful in killing the wither, the wither will explode.
//      The wither drops a small amount of experience and a single Nether star.
//      This Netherstar can be used to make a most desired beacon block.
		
		//-----
		
		tBook.clear();
		tBook.add("This Manual is about common and uncommon Tools and how to use them. It contains a List of every normal Tool that you might not know the true purpose of.");
		
		tBook.add("Swords"              +"\n===================\nIt's a Sword, it deals Damage and cuts things. It can harvest Leaves, Webs and Wool Blocks.");
		tBook.add("Knifes"              +"\n===================\nThey cut Food, Sticks, Rubber, Saplings into Sticks, Bark of a Tree, and similar things and are a somewhat usable early weapon. It can harvest all the things a Sword can harvest too.");
		tBook.add("Butchery Knifes"     +"\n===================\nHas a Looting Modifier on it per Default, but has a slow swing and low Damage.");
		
		tBook.add("Clubs"               +"\n===================\nSometimes kindof expensive, but it works like a Hammer and deals lots of Damage, while having a larger cooldown time after a hit. It can break Rocky Blocks, but it will crush them.");
		
		tBook.add("Axes"                +"\n===================\nGood in chopping down Trees, bad in chopping down Planks or other wooden things.");
		tBook.add("Double Axes"         +"\n===================\nStill good for chopping on Trees, but also very effective as a Weapon and has 50% more Durability than the normal Axe, while requiring 66.666% more Material to create.");
		tBook.add("Saws"                +"\n===================\nBad for chopping Trees, but can easily cut wooden things like Planks.");
		tBook.add("Chainsaws"           +"\n===================\nGood for pretty much all purposes, can chop Trees, saw Planks and is a very nice Weapon. It can specifically be used to spam click Creepers to death easily.");
		tBook.add("Buzzsaws"            +"\n===================\nThis Type of Saw is dedicated to Hand Crafting and cannot harvest any Blocks. It is cheaper to use this than a Chainsaw for crafting purposes.");
		
		tBook.add("Pickaxes"            +"\n===================\nMines Blocks like a normal Pickaxe usually does.");
		tBook.add("Construction Picks"  +"\n===================\nIt mines man-made Blocks much faster, but is slow on Natural Rocks.");
		tBook.add("Gem tipped Pickaxes" +"\n===================\nIn an exchange for a lot of Durability, you can put flawed Gems onto your Raw Steel Pickaxe Head, to give it the Quality of a Gem Tool. Amber, Sapphires and Diamonds are best for this.");
		tBook.add("JackHammers"         +"\n===================\nThis thing mines Blocks and crushes them like a Hammer at an insane Speed. Not much to say about it.");
		tBook.add("Mining Drills"       +"\n===================\nIt can mine and shovel things at once and is the perfect universal mining Tool.");
		
		tBook.add("Shovels"             +"\n===================\nMines Blocks like a normal Shovel usually does.");
		tBook.add("Spades"              +"\n===================\nA Spade has conditional Silk-Touch for certain Blocks, such as Dirt, Grass, Mycelium, Clay, Mud, Snow and Podzol, but it is slower than a normal Shovel and doesn't work on Sand.");
		tBook.add("Plows"               +"\n===================\nThe Plow harvests a 3x3x3 Area of Snow Layers and similar Blocks at once.");
		tBook.add("Universal Spades"    +"\n===================\nShovel, Saw, Sword and Crowbar at once. Very versatile, but that goes at the cost of Durability and Speed.");
		
		tBook.add("Hammers"             +"\n===================\nCrushing Blocks and Smithery are the most common usages of this Tool. It doesn't do much else, but it can be used in Combat.");
		tBook.add("Soft Hammers"        +"\n===================\nThese Hammers are mainly used to turn Machines ON and OFF. They can even turn Redstone Lamps and certain Rails ON without having to have a Redstone Signal close to them.");
		tBook.add("Wrenches"            +"\n===================\nAdjusts the primary Facing of most Machines, sometimes it can be used to toggle Modes aswell.");
		tBook.add("Monkey Wrenches"     +"\n===================\nAdjusts the secondary Facing of most Machines, often it can be used to toggle Modes aswell.");
		tBook.add("Electric Wrenches"   +"\n===================\nThey are a combination of the normal Wrench and the Monkey Wrench and can easily switch between them via Modes. Sneak Rightclick on a non-wrenchable Block to switch.");
		
		tBook.add("Files"               +"\n===================\nSharpens things and can comically harvest Iron Bars way faster than anything else.");
		tBook.add("Chisels"             +"\n===================\nCan put Molds into Shape and change the looks of some Stones.");
		tBook.add("Bending Cylinders"   +"\n===================\nTurns Plates into Curved Plates and is used for similar Metal Working procedures");
		
		tBook.add("Crowbars"            +"\n===================\nOpens Crates and can be used on Rails and Minecarts");
		tBook.add("Pincers"             +"\n===================\nUsed to pick up hot things from Molds and Basins, so you don't have to wait for them to cool down before picking them up.");
		tBook.add("Plungers"            +"\n===================\nEmpties Pipes and Fluid Tanks by either voiding the Fluid or dropping the ItemStack that is stuck in them. Works on Thaumic Conduits aswell.");
		tBook.add("Scoops"              +"\n===================\nCatches Butterflies, Bees and Bumblebees, also needed to harvest Bumble Hives and Bee Hives.");
		
		tBook.add("Hoes"                +"\n===================\nThey till the ground, what do you expect? I'm not gonna explain vanilla Tools in great detail.");
		tBook.add("Branch Cutters\nTrimmers"+"\n===================\nCuts Saplings out of Leaves with high precision, like a Grafter.");
//      tBook.add("Sickles"             +"\n===================\n");
		tBook.add("Senses"              +"\n===================\nHarvests a 3x3x3 Area of Crops, Flowers and Tall Grass.");
		
		tBook.add("Rolling Pins"        +"\n===================\nFlattens Dough and Clay for cooking and crafting purposes.");
		tBook.add("Scissors"            +"\n===================\nCan cut Tripwires on Rightclick, shear Sheep and harvest Wool-ish/Cloth-ish Blocks.");
		tBook.add("Screwdrivers"        +"\n===================\nDrives Screws and such. The most common usage is adjusting Modes on Blocks and Covers. They can rotate Comparators and Repeaters.");
		tBook.add("Wire Cutters"        +"\n===================\nThey harvest Wires. Another usage is to adjust Wires and in some cases Covers aswell.");
		tBook.add("Hand Mixers"         +"\n===================\nThese are just so you don't consume your Hunger Bar when operating the Mixing Bowl.");
		tBook.add("Hand Drills"         +"\n===================\nThose Drill Holes in Surfaces. If you have Dynamite Sticks or Iron/Steel Rods with you, you can stick the Dynamite into Walls or reinforce Bricks and Concrete.");
		tBook.add("Flint and Tinder"    +"\n===================\nSets things on Fire and can light anything that requires ignition. But it might take a few tries to do so.");
		tBook.add("Magnifying Glasses"  +"\n===================\nWith this you can look at the Details of a LOT of things in GregTech, just put a Lens of any kind on a Stick and you are good to go for a long time.");
		
		tBook.add("Pocket Multitool"    +"\n===================\nA Multi-Tool that can be switched to the most common purposes. See it as a Swiss Army Knife.");
		
		UT.Books.createWrittenBook("Manual_Tools", "Tool Index", "GITF (Gregorius Industrial Tool Factory)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		
		tBook.clear();
		tBook.add("This is the Manual of the Smelting Crucible and related Objects. It will explain how to use the Smelting Crucible and the Molds properly, so that you can start smelting things which cannot me molten inside a regular Furnace.");
		tBook.add("If you need to know how heavy an Object is or what Materials it consists out of, just use the good old vanilla F3+H Method to enable advanced Tooltips, so you can see the most relevant Data about the Object.");
		tBook.add("Step 1: Heat Source" +"\n===================\nYou need a Heat Source in order to heat up your Crucible to the desired Temperature. Possible Heat Sources include Burning Boxes, Electric Heaters, Laser Heaters and Arc Heaters.");
		tBook.add("Step 2: Crucible"    +"\n===================\nThe choice of Crucible Material is very important. The Crucible can melt too at a certain Temperature, which is usually higher than the actual Melting Point of the Crucible itself.");
		tBook.add("Step 2: Crucible"    +"\n===================\nNow place the Crucible of your choice at the Output Facing of the Heat Source. The Burning Box for example only emits to its Top Side, so you need to place the Crucible above it.");
		tBook.add("Step 3: The Molds"   +"\n===================\nYou probably want to cast the molten Metal into a Shape, so you need to place a Mold horizontally adjacent to the Crucible. As you may have noticed the Mold itself doesn't have a Shape.");
		tBook.add("Step 3: The Molds"   +"\n===================\nThis is why you need a Chisel in order to chisel the proper Shape into the Mold by using it in World. The most important Shapes are Listed in the last Pages of this Book.");
		tBook.add("Step 3: The Molds"   +"\n===================\nIf you somehow fail to put a correct Shape into the Mold, the End Result will be as many Nuggets as you have chiseled out places.");
		tBook.add("Step 4: Smelting"    +"\n===================\nNow we get to smelting. Just throw the things you want to smelt into the Crucible, or use a Hopper above the Crucible to fill it. And turn up the Heat until you reach the melting Point.");
		tBook.add("Step 4: Smelting"    +"\n===================\nUnless your Heat Source has some kind of Temperature regulation, you may need to shut it off once the Temperature reaches the Melting Point or else you will vaporate the Metals or worse.");
		tBook.add("Step 5: Shaping"     +"\n===================\nNow just click the Side of the top of the Mold where the Crucible is next to, in order to fill it with its Metals. Warning: The Mold itself can melt too if it is made of the wrong Material.");
		tBook.add("Step 5: Shaping"     +"\n===================\nWait for the Metal to cool down, but note that you should not touch or take out the Metal while it is hot or else you will burn yourself unless you wear a Hazmat Suit or something.");
		tBook.add("Mold Shapes"         +"\n===================\nThe following is a List of Shapes you can chisel the Mold into. The position of the Shape on the Mold is NOT important, you can rotate and mirror it too\nX = Chiseled\nO = Not Chiseled");
		tBook.add("Ingot"               +"\n===================\nXXXOO\nXXXOO\nXXXOO\nXXXOO\nXXXOO\n===================\n1.000 Units required");
		tBook.add("Chunk"               +"\n===================\nXXOOO\nXXOOO\nOOOOO\nOOOOO\nOOOOO\n===================\n0.250 Units required");
		tBook.add("Plate"               +"\n===================\nXXXXX\nXXXXX\nXXXXX\nXXXXX\nXXXXX\n===================\n1.000 Units required");
		tBook.add("Tiny Plate"          +"\n===================\nOOOOO\nOXXXO\nOXXXO\nOXXXO\nOOOOO\n===================\n0.111 Units required");
		tBook.add("Bolt"                +"\n===================\nXXOOO\nOOOOO\nOOOOO\nOOOOO\nOOOOO\n===================\n0.125 Units required");
		tBook.add("Rod"                 +"\n===================\nOOOOO\nOOOOO\nXXXXX\nOOOOO\nOOOOO\n===================\n0.500 Units required");
		tBook.add("Long Rod"            +"\n===================\nXOOOO\nOXOOO\nOOXOO\nOOOXO\nOOOOX\n===================\n1.000 Units required");
		tBook.add("Item Casing"         +"\n===================\nXXXOX\nXXXOX\nXXXOX\nOOOOX\nXXXOO\n===================\n0.500 Units required");
		tBook.add("Ring"                +"\n===================\nOOOOO\nOXXXO\nOXOXO\nOXXXO\nOOOOO\n===================\n0.250 Units required");
		tBook.add("Gear"                +"\n===================\nXOXOX\nOXXXO\nXXOXX\nOXXXO\nXOXOX\n===================\n4.000 Units required");
		tBook.add("Small Gear"          +"\n===================\nOXOXO\nXXXXX\nOXOXO\nXXXXX\nOXOXO\n===================\n1.000 Units required");
		tBook.add("Sword"               +"\n===================\nOOXOO\nOXXXO\nOXXXO\nOXXXO\nOXXXO\n===================\n2.000 Units required");
		tBook.add("Pickaxe"             +"\n===================\nOOOOO\nOXXXO\nXOOOX\nOOOOO\nOOOOO\n===================\n3.000 Units required");
		tBook.add("Spade"               +"\n===================\nOXXXO\nOXXXO\nOXXXO\nOXOXO\nOOOOO\n===================\n1.000 Units required");
		tBook.add("Shovel"              +"\n===================\nOOXOO\nOXXXO\nOXXXO\nOXXXO\nOOOOO\n===================\n1.000 Units required");
		tBook.add("Universal Spade"     +"\n===================\nOOXOO\nOXXXO\nOXXOO\nOXXXO\nOOOOO\n===================\n1.000 Units required");
		tBook.add("Axe"                 +"\n===================\nOOOOO\nOXXXO\nOXXXO\nOXOOO\nOOOOO\n===================\n3.000 Units required");
		tBook.add("Double Axe"          +"\n===================\nOOOOO\nXXXXX\nXXXXX\nXOOOX\nOOOOO\n===================\n5.000 Units required");
		tBook.add("Saw"                 +"\n===================\nOOOOO\nXXXXX\nXXXXX\nOOOOO\nOOOOO\n===================\n2.000 Units required");
		tBook.add("Hammer"              +"\n===================\nXXXOO\nXXXOO\nXOXOO\nXXXOO\nXXXOO\n===================\n6.000 Units required");
		tBook.add("File"                +"\n===================\nOXXXO\nOXXXO\nOXXXO\nOOXOO\nOOXOO\n===================\n1.500 Units required");
		tBook.add("Screwdriver"         +"\n===================\nOOOOO\nOOXOO\nOOXOO\nOOXOO\nOOXOO\n===================\n1.000 Units required");
		tBook.add("Chisel"              +"\n===================\nOXXXO\nOOXOO\nOOXOO\nOOXOO\nOOXOO\n===================\n1.500 Units required");
		tBook.add("Arrow"               +"\n===================\nOOOOO\nOOXOO\nOOXOO\nOXXXO\nOOOOO\n===================\n0.250 Units required");
		tBook.add("Hoe"                 +"\n===================\nOOOOO\nOOXXO\nOXXXO\nOOOOO\nOOOOO\n===================\n2.000 Units required");
		tBook.add("Sense/Scythe"        +"\n===================\nOOOOO\nOXXXX\nXXXXX\nOOOOO\nOOOOO\n===================\n3.000 Units required");
		tBook.add("Plow"                +"\n===================\nXXXXX\nXXXXX\nXXXXX\nXXXXX\nOOXOO\n===================\n4.000 Units required");
		tBook.add("Builder's Wand"      +"\n===================\nOOXOO\nXXXXX\nOXXXO\nOXOXO\nOOOOO\n===================\n1.000 Units required");
		
		UT.Books.createWrittenBook("Manual_Smeltery", "Smelting Crucible Manual", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		//-----
		
		tBook.clear();
		tBook.add("This Book Contains Information about every Alloy, which can be created by using a Smelting Crucible.\n===================\nIn order to make an Alloy you need to reach the Melting Point of the Alloy itself.");
		tBook.add("And you need to reach the Melting Point of all but one of its Components. You can ofcourse also melt all of the Components, but you are free to 'not melt' one of the Components.");
		
		for (OreDictMaterial tMat : OreDictMaterial.ALLOYS) {
			for (IOreDictConfigurationComponent tComponents : tMat.mAlloyCreationRecipes) {
				tPage="Alloy:\n"+tMat.getLocal()+"\n===================\nMelting: "+tMat.mMeltingPoint+" K\nBoiling: "+tMat.mBoilingPoint+" K\n===================\nComponents per "+tComponents.getCommonDivider() + "\n";
				for (OreDictMaterialStack tMaterial : tComponents.getUndividedComponents()) tPage += (tMaterial.mAmount / U)+" "+tMaterial.mMaterial.getLocal()+"\n";
				tBook.add(tPage);
			}
		}
		
		UT.Books.createWrittenBook("Manual_Alloys", "Book of Alloys, Smeltery Edition ("+(tBook.size()-2)+")", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		//-----
		
		tBook.clear();
		
		for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_ARRAY) {
			if (tMat != null && !tMat.mHidden && tMat.contains(TD.Atomic.ELEMENT) && !tMat.contains(TD.Atomic.ANTIMATTER)) {
				tBook.add(tMat.getLocal()+"\n"+tMat.mProtons+"/"+tMat.mNeutrons+"\n===================\nID: "+(tMat.mID<0?"NONE":tMat.mID)+"\nMelting: "+tMat.mMeltingPoint+" K\nBoiling: "+tMat.mBoilingPoint+" K\nPlasma: "+tMat.mPlasmaPoint+" K\n===================\nDensity:\n"+(tMat.mGramPerCubicCentimeter == 0 ? "???" : tMat.mGramPerCubicCentimeter)+" g/cm3\n"+tMat.getWeight(U)+" kg/unit\n===================\n");
			}
		}
		
		UT.Books.createWrittenBook("Manual_Elements", "Book of Periods ("+tBook.size()+")", "GCC (Gregorius Chemical Consortium)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		//-----
		
		UT.Books.createWrittenBook("Manual_Extenders", "Extenders & Filters", "GAD (Gregorius Automation Distributions)", ST.make(ItemsGT.BOOKS, 1, 32000), new String[] {
		  "This Manual covers Extenders as well as Filters. It will explain the usage of those Devices in Detail, so that you can use them easily. "
		, "First a Declaration of Faces any Filter and Extender have. The Main Facing is the Facing you can set using the Wrench and which has 4 Dots around the center Pipe."
		, "The Secondary Facing is the one with the outwards pointing Arrows, that is set using the Monkey Wrench. And the other Sides of the Extender/Filter are the Neutral Facings."
		, "Now that this is clear, we can go for the Functionality. Basically ANYTHING that 'interacts' (* explained later) with the Extender through a Neutral or Secondary Facing will get redirected to the TileEntity at the Main Facing."
		, "This means that the Main Facing is usually (but not always!) the Output Side, if you want to insert Stuff through the Neutral Facings or the Secondary one. So attach the Main Facing to the TileEntity you want to extend or have filtering for."
		, "Now to the Secondary Facing: As you know the 5 Facings redirect their TileEntity access to the Main Facing, but the Main Facing itself can redirect all the TileEntity accesses on its Side to the TileEntity on the secondary Facing too."
		, "This means that if you have Input and Output on the same Side of the TileEntity you attach it to, that you can redirect the Output into one Direction using the Secondary Facing, while the Neutral Facings fill the TileEntity on the Main Facing."
		, "The redirect from Main->Secondary is NOT filtered, only the Neutral&Secondary->Main interactions are filtered! I hope this explanation is good enough to actually use the Filters properly."
		, "Extenders and Filters cannot be used adjacent to each other in the sense of 'chained together', this means you have to have something, like a Pipe, between an Extender/Filter and another Extender/Filter, unless they are parallel ofcourse."
		, "Now to explain what 'interact' actually means. A Hopper pulls Items from everything it 'interacts' with above, and pushes things to everything it 'interacts' with below. This means the direction an Item travels is irrelevant to the Extender."
		, "So if you put a Hopper below an Extender, the Hopper will pull Items from whichever TileEntity the Extender is connected to. And if you put the Hopper above the Extender, it will push into the TileEntity the Extender is attached to."
		, "Extenders are basically a both-way Detour Sign for anything that tries to interact with them in some way shape or form, as if the TileEntity you attached them to is now two Blocks in Size instead of one."
		, "They do NOT do anything by themselves at all, they just sit there pretending to be whatever TileEntity you attached them to. So do not expect them to pull or push Items around or something dumb like that."
		});
		
		//-----
		
		for (int i = 1; i < OreDictMaterial.MATERIAL_ARRAY.length; i++) if (OreDictMaterial.MATERIAL_ARRAY[i] != null && (!OreDictMaterial.MATERIAL_ARRAY[i].mHidden || OreDictMaterial.MATERIAL_ARRAY[i].mDescription != null)) {
			ItemStack tStack = UT.Books.addMaterialDictionary(OreDictMaterial.MATERIAL_ARRAY[i]);
			
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.gem               .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.dust              .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.ingot             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plate             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plateGem          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.crushedCentrifuged.dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.bucket            .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			CR.shaped(tStack, CR.DEF_NAC_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.chemtube          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
		}
	}
}
