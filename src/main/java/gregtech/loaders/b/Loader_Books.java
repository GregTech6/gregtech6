/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.code.ArrayListNoNulls;
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

import java.util.List;

import static gregapi.data.CS.*;

@SuppressWarnings("ALL")
public class Loader_Books implements Runnable {
	@Override
	public void run() {
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
		, "The Tiers of Machines are as follows: anything between 16 and 64 Generic Units per Tick is Tier ONE¶Tier 2: 65-256¶Tier 3: 257-1024¶Tier 4: 1025-4096¶Tier 5: 4097-16192"
		, "If the Tier of the Machine you are using is in a higher Range than the Tier of the Recipe (see its GU/t) it will multiply the required Input GU/t by 4 and halve the Processing time, making the Recipe twice as expensive."
		, "And yes, 64 GU/t is STILL TIER ONE, you seriously have to insert that much into a Tier 1 Machine! It is often possible to use the Motor/Heater/etc of a higher Tier on the lowest Power Input to get the GU/t."
		, "GT6 Electric Power is widely compatible with all Mods that use the IC² API, such as:¶IC², IC²-Classic, IC²-Addons, Railcraft, Forestry and more!"
		, "Things emitting a Voltage less than 1024 EU/t are even capable of powering the non-IC² Machines of the following Mods:¶Galacticraft and it's Addons, Applied Energistics, Immersive Engineering, OpenModularTurrets, TechGuns and even GT5U!"
		, "Burning Boxes of any kind are always a Fire Hazard, make sure to only have Fireproof Stuff around them! The only ways to turn those Boxes off are placing a solid Block in front of them or the Fire Extinguishers."
		, "You want to move Water or get Milk, but don't have an Iron Bucket? Try making a Wooden Bucket for that Task, it's cheaper, even if it only works for a small sortiment Liquids."
		, "If you need a Printer Manual, then just insert Chemical Black Dye and an empty vanilla Book into it and apply Energy. It will instantly print that Manual."
		, "The Bathing Pot and the Bath can dye Leather Armor and GT6 Blocks using Liquid Dyes.¶You can wash Ores early on by throwing them into a filled vanilla Cauldron. Also GT Pipes with a cap of 334L and higher can fill Cauldrons with Water."
		, "Space out Machines a little in order to be able to automate them later without causing a huge Clusterfuck. Always have enough space for 2 Blocks inbetween each Machine, if not 3! Disregard the eventual Loss of Power, it's not worth it."
		});
		
		UT.Books.createWrittenBook("Manual_Portal_TF", "The Twilight Forest Guide", "Gregorius Techneticies", ST.make(ItemsGT.BOOKS, 1, 1005), new String[] {
		  "If you found this Book next to a Twilight Forest Portal, but you don't know how to light the Portal to go on the Adventure, don't worry, in here are some instructions. If found inside the Twilight Forest, you can probably skip 3 Pages."
		, "Step 1: You see the 4x4 of some type of Dirt, Grass or Mud with 12 Flowers growing on it and a 2x2 Pool of Water in the middle? That is going to be the Twilight Forest Portal."
		, "Step 2: All you need to do to activate that Portal, is throwing a Diamond into that Water Pool, and you are ready to go. Yep, it is that simple. If the Water is purple and swirly, the Portal is already active."
		, "Step 3: So, now that you have a Portal to the Twilight Forest, all you need to do is jump in. You don't even need Materials to prepare or anything."
		, "Once you are inside of the Twilight Forest you can gather up Materials from the ground as usual. A good recommendation would be to find a Firefly Forest and grab a bunch of the Firefly Jars there, because they are blocklike Torches."
		, "The Obsidian Pillars you see spread all over the place have a Block of Lapis Lazuli at their Top, it is sometimes easier to get that then to go mining. These Pillars also have Ravens around them, if you need a Feather for a Magic Map Focus."
		, "Lakes are just boring Bodies of Water, not even Squids spawn inside of them, due to the Twilight Forest being of a too low Y Level to do so. I guess the Lake is a good place to go fishing or build some IC2 Water Power Generator."
		, "The Mushroom Fortress is currently not in use. No Loot or anything in there, not even Staircases or Ladders, you could make a Base inside of it though."
		, "The Questing Ram is an Animal you can find in the middle of a Biome full of Rainbow Trees. If you click it with one of each type of Wool it will reward you with the Crumble Horn, a somewhat useful Mining Tool, and some Blocks of Resources."
		, "Druid Huts contain a Skeleton Druid Spawner, which is a renewable source of Torchberries, and a Netherrack Block that is lit on Fire. The Roof is made of Petrified Wood and therefore not flammable. There is no other Loot in there."
		, "In general all Loot Chests in Twilight Forest can be picked up wholesale as long as you have never opened them before. That way you can bring them home without cluttering your Inventory and then open them there safely."
		, "If you find small ruined Buildings, it is well advised to break their wooden Floor and dig a three block deep hole to see if there is a hidden Chest in the Basement. It is a 50:50 chance for it to exist."
		, "Water Wells can be found all over the place, it is important to note that some Wells have a Chest hidden at their Bottom. Only the Wells that have a 1-by-1 of Water can randomly have such a Chest, the ones that are 2-by-2 will never have it."
		, "Hollow Trees can contain multiple Chests accompanied by Spider Spawners. The tiny Spiders and their noise make it easy to find, and even early Tools kill them in one hit. Bigger Hollow Trees tend to have more Chests in their Foliage."
		, "Hedge Mazes are the next logical Step to find some Loot. First look from above where all the Rooms are, then rush for each of them, axe all the Chests, and run. Most of the Mobs in there are not hard to beat."
		, "Ironwood is easy as you only need some Hematite/Magnetite, Gold, and some Liveroot you get from picking up Sticks. A Mortar, a Mixing Bowl, a Furnace and Stone Anvils with Stone Hammers are all you need to make basic Equipment of it."
		, "Hollow Hills are something you can try once you are all armored up, nab some Chests from one of those. Beware it is full of very deadly and explosive Mobs, so you need to be constantly on the run and hide behind the many Stalagmites."
		, "Steeleaf may have low durability, but it is one of the Materials you can easily get good Fortune and Looting Enchanted Tools and Arrows from, and has the Item-Auto-Collecting Ability for many GT Tools. But it will turn into regular Steel if melted."
		, "Naga Arena¶===================¶This is the first Boss you need to defeat. All you need is 8 solid Blocks, a Bow and a few Stacks of Arrows. Run into the Arena and let the Naga spawn, then immediately pillar up 8 Blocks and attack with the Bow."
		, "Naga Arena¶===================¶The Naga will almost definitely kill you in Melee Combat so using ranged Weapons is the best way. And those Pillars that are already in the Arena are a good hint that you should get up and out of Melee Range."
		, "Naga Arena¶===================¶If against better judgement you wish to engage the Naga in Melee Combat, you better get well enchanted Armor. Also walk around the Naga Arena once to make sure all Chunks are generated and Worldgen Lag wont kill you."
		, "Naga Arena¶===================¶The Naga Trophy you get can be used to produce KU through a Magic Field Absorber. Naga Scales can be used to make Chestplates and Leggings, but are worthless otherwise. You are able to get into Lich Towers now."
		, "Lich Tower¶===================¶In order to enter this place you might need to kill a Naga first and touch its Trophy. The Lich Tower has one big Double Helix Central Staircase with Bridges inbetween that contain typical Dungeon Mob Spawners."
		, "Lich Tower¶===================¶Those Mob Spawners can be lit up by putting two Firefly Jars or similar Light Sources 1 horizontal Block away from them on opposite ends. These Spawners can create Zombies, Skeletons or various Spiders."
		, "Lich Tower¶===================¶There will not only be Spawner Mobs but also special Mobs that spawn normally. The special one in this case being a flying Tome, that drops Paper for each hit. Have fun punching it for maximized Paper drops."
		, "Lich Tower¶===================¶At the Ground Level of the Tower is four Side Buildings, which likely contain quite a bit of Loot Chests, you should try getting into each of those by breaking into the Walls from the Outside."
		, "Lich Tower¶===================¶The Lich is located at the top of the Staircase, and has FIVE Shields that need to be destroyed before taking Damage. To destroy a Shield you need reflect their Projectiles at them (nearly impossible in Multiplayer!)."
		, "Lich Tower¶===================¶An alternative Method for destroying a Shield is hitting it with 1.5+ Hearts of any Armor penetrating Damage. For example Smite Bullets, Pickaxes, Falling Damage and Splash Potions of Healing (but NOT Regeneration!)."
		, "Lich Tower¶===================¶Using things like Withering Damage to attack the Lich works only when all Shields are down. Damage Immunities include: Fire, Poison, Drowning, Harming Potions and anything else Undead are immune to."
		, "Lich Tower¶===================¶Zombies and Shadowclones of the Lich will be summoned occassionally to distract you from the real Target. Avoid hitting the Shadowclones, but do kill the Zombies as they will swarm you otherwise."
		, "Lich Tower¶===================¶The Lich Trophy you get can be used to produce QU through a Magic Field Absorber. The Scepter will either allow you to drain Life or to summon helpful Zombies. They can be reloaded in the crafting Grid."
		, "Minoshroom Maze¶===================¶Each Fire Swamp is surrounded by four regular Swamps, which each contain a Maze full of Minotaurs, Slimes, annoying Insects, Loot and sometimes flooded with Swamp Water. Kill a Lich first before going here."
		, "Minoshroom Maze¶===================¶Minotaurs will charge at you very quickly and from any angle. To easily get rid of the. use a Weapon with the Werebane Enchantment, which works on them and weakens them drastically."
		, "Minoshroom Maze¶===================¶Maze Slimes behave like the normal ones and are just as vulnerable to the Dissolving Enchantment. If you have Thaumcraft, use the Fire Wand Focus for the tiny leftover Slimes, to save on your Weapons durability."
		, "Minoshroom Maze¶===================¶Around the Entrance are a lot of Vines, harvest them with a Knife and craft Ropes out of them, which you can then use to get down the Hole. Bring extra Rope for going from the Upper Level to the Basement Level!"
		, "Minoshroom Maze¶===================¶Try to follow the universal Rules of Maze traversal, which you could train over at the Hedge Maze. Placing Torches always on the righthand side Walls helps you find the way back easily."
		, "Minoshroom Maze¶===================¶You may be able to find a Maze Map Focus in one of the Loot Chests. Use it to craft a Maze Map and rightclick it while you stand on the ground of the Basement Level. Dont waste it on the Upper Level!"
		, "Minoshroom Maze¶===================¶The Maze Map works everywhere, even your Base should you choose so. You can also upgrade an empty Maze Map into an Ore Map and go mining with it. Though if you got Ore Magnets you wont really need that."
		, "Minoshroom Maze¶===================¶There is a few Rooms with Mob Spawners and Loot Chests inside the Pillars. Other Loot Chests can be found in Dead Ends, just beware that those are likely TNT trapped with more than one trigger!"
		// Attacking the Minoshroom will result in breaking all Fences around it, making the Bow Method completely useless, unless you replace the Fences with actual Blocks. The Cheese would still work if it wasn't for GT6. This Line will stay to troll people.
		, "Minoshroom Maze¶===================¶The Room with the Minoshroom is at the Basement Level and blocked off by Fences. Just break a Fence out of the middle and shoot the Minoshroom with a Bow and a bunch of Arrows. Four Loot Chests await you inside."
		, "Minoshroom Maze¶===================¶Among the drops of the Minoshroom are Meef Stroganoff, a useless Food Item, and a +1 Diamond Axe that supposedly deals +3.5 Hearts of Extra Damage when you charge at Enemies (may be bugged, test it yourself)."
		, "Minoshroom Vault¶===================¶There is a secret Vault full of high value Loot Chests hidden in the Maze. It occupies 3 by 3 grid spaces worth of Basement Level Maze with very thick Walls. With the Maze Map you can easily find it."
		, "Minoshroom Vault¶===================¶The Vault Room on the Basement Level is trapped, try digging at Floor Height and with an Autocollecting Pickaxe to avoid the Wooden Pressure Plates triggering the TNT next to the Chests."
		, "Hydra Cave¶===================¶Once you have killed the Minoshroom, you can go into the Fire Swamp in the center of the Swamplands to find the Hydra Cave, the home of the aforementioned Hydra. Though there is only Ores to be found there, no Loot."
		, "Hydra Cave¶===================¶Trying to attack the Hydra can be tricky, because if you are too far away from it, all ranged Damage you deal will be nullified, and when you are close enough it will spew explosives at you."
		, "Hydra Cave¶===================¶In order to deal damage to it, you need to hit the Heads while their Mouth happens to be open. It will always rotate towards you, so try to avoid having the Hydra kite you away, while all other Heads attack!"
		, "Hydra Cave¶===================¶It has a high damage Bite Attack instant killing you if you dont wear Protection enchanted Armor, and a Fire Breathing Attack for which you should drink a Fire Resistance Potion ahead of time."
		, "Hydra Cave¶===================¶To engage in Melee, you should wear strong Armor with at least one high Level regular Protection Enchantment, drink a Fire Resistance Potion and a Regeneration Potion, and get a Sword or Axe with Sharpness."
		, "Hydra Cave¶===================¶The Hydra Trophy you get can be used to produce HU through a Magic Field Absorber. Its Blood can be used to imbue Steels including Steeleaf with a Fiery Aura, enabling Auto-Smelt on Tools and incinerating opponents!"
		, "Knight Stronghold¶===================¶Time for the Dark Forest, there is four Knightly Strongholds in there. They are underground, but you need to find the Entrance in the Forest and put one of your Trophies onto its pedestal to open it up."
		, "Knight Stronghold¶===================¶You should bring Ropes to the Stronghold as not all Areas inside of it are connected with Stairs. You will also need something that can get rid of Obsidian easily as the Tomb is encased in it."
		, "Knight Stronghold¶===================¶The Mobs include: Helmet Crabs, Two Babies in a Trenchcoat wielding a Spear can be split in two by attacking from the back, The Sapper throwing TNT at you, And little Kids spinning Spikeballs around."
		, "Knight Stronghold¶===================¶Speaking of Spikeball, you can craft one of those too from a lot of Knightmetal Ingots. Later there is even an upgrade to it to 'annihilate' your Enemies and the Terrain even more!"
		, "Knight Stronghold¶===================¶There is a few 3 by 3 structures made of Stone Brick Stair Blocks. Remove one of those Stairs to reveal a trapped Loot Chest hidden inside of it. They are often hidden behind actual Staircases too."
		, "Knight Stronghold¶===================¶Search the Stronghold until you find an Obsidian Wall visible behind some Iron Bars. Break through the Obsidian Wall into the Tomb behind it. The Phantoms will spawn once you enter."
		, "Knight Stronghold¶===================¶A lot of times the Tombs are completely disconnected from the Entrance too, because the whole Stronghold does not generate well. Just dig around a little to see if something is behind a Wall."
		, "Knight Stronghold¶===================¶The Phantoms will only track you down a limited distance and then go back to the Tomb, so you can bait them out and kill them one by one. They look more scary than they actually are."
		, "Knight Stronghold¶===================¶They throw a LOT of Weapons at you, but they only really distract from their melee attacks. Phantoms do NOT count as Undead for the sake of the Smite Enchantment and Splash Potions of Harming."
		, "Knight Stronghold¶===================¶Make sure to NOT place anything in the middle of the Tomb, such as Torches, because if the spot where the Loot Chest is supposed to be is blocked, it will not spawn!"
		, "Knight Stronghold¶===================¶It is possible for there to be more than one Tomb, and every Tomb can be connected on four Sides, so breaking through the other Walls from the inside of the Tomb may unearth more of the Stronghold!"
		, "Knight Stronghold¶===================¶Killing the Phantoms will cause Mobs to no longer spawn inside the Stronghold, giving you an easier time to find more Tombs in there. However sometimes listening for hostile Mobs can point you places."
		, "Knight Stronghold¶===================¶Weapons and Armor of the Phantoms are not special or worthwhile, so dont bother. The Knightmetal Weapons supposedly deal +1 Heart of Extra Damage to the opponent (may be bugged, test it yourself)."
		, "Dark Tower¶===================¶Found in the center of the Dark Forest, the Dark Tower is the most complex Dungeon of the Twilight Forest in this Minecraft Version. You better bring a few Enderpearls and Ropes with you, just in case."
		, "Dark Tower¶===================¶Getting to the Tower is honestly easiest if you just walk ontop of the Dark Forest itself, way less Bushes and Trees blocking your way. Not to mention you can easily see the Dark Tower from a distance."
		, "Dark Tower¶===================¶If you just wanna cheat and pillar up to the Roof of the Tower, beware of the big Rooftop Ghasts that will likely knock you off the Pillar and to your Death. So maybe you should try take the normal Entrance."
		, "Dark Tower¶===================¶To enter the Tower go to one of the Red marked Doors and rightclick it. These Doors are nice and you could harvest some of them for your Base if you would like. There is also crafting Recipes for them."
		, "Dark Tower¶===================¶Many of the Segments of this Tower have Anti-Builders in them, which prevent you from placing Blocks and will replace any broken Blocks. But they have a slight delay, enough to get into Fences for example."
		, "Dark Tower¶===================¶There is a lot of burning Holes in the Tower, for which you will have to place Blocks or Ropes, or throw Enderpearls to get past those. Destroying any Anti-Builders you find is highly recommended."
		, "Dark Tower¶===================¶Some Segments have Carminite Builders with Levers. Flip the Lever and look into the direction you want to go, and it will slowly place a Bridge of Blocks going there. You could harvest them for use with your Base."
		, "Dark Tower¶===================¶Blaze Spawners can be spawnblocked by just putting eight Firefly Jars on their exact Y-Level in a Ring one Block away from their Pillar. So two Blocks above ground, barely enough space to walk under the Jars."
		, "Dark Tower¶===================¶Locked Doors are found between Major Tower Segments. In order to open them you need to find four Tower Keys and rightclick each Corner with one of them. Or just break the Doorframe or something if you wanna cheat."
		, "Dark Tower¶===================¶Tower Keys are found in the Knightmetal Chests of the smaller Side Towers. You can just harvest the whole Chest and the Key will drop out of it anyways, even if the Chest Loot has not been generated yet."
		, "Dark Tower¶===================¶Golem and Spider Spawners are often found inside the Side Towers. Trapdoor Setups and partially broken Redstone Contraptions can be found too, as well as many Wooden and Metal Loot Chests."
		, "Dark Tower¶===================¶Ghastlings can be found in various places around the Tower, usually they will not shoot you until you stare at them. Other than that they are basically Ghasts."
		, "Dark Tower¶===================¶Towerwood Borers infest the entire Towers Walls, their Borer Essence can be used in a few Crafting Recipes related to the Devices you can find around the Dark Tower."
		// My Book isn't gonna spoil what this fun Device will do to you. XD
		, "Dark Tower¶===================¶At the Top Segment of the Tower you will see Boxes with Pistons for pushing Redstone Blocks into them. When you push all four Redstone Blocks, the Box will turn into Gold and Diamonds ready for you to harvest."
		, "Dark Tower¶===================¶To fight the Ur-Ghast I recommend using a Stack of high quality Arrows and an Enchanted Bow. If you have access to the Implosion Enchantment by using things like Gem Arrows, it works great on any type of Ghasts."
		, "Dark Tower¶===================¶There is a 'special' other way to beat the Ur-Ghast using one of the four Ghost Busters References, whenever they have been charged enough by killing the small Ghasts around, and the Ur-Ghast is directly above it."
		, "Dark Tower¶===================¶The Ur-Ghast Trophy you get can be used to produce LU through a Magic Field Absorber. Make sure you got the Achievement for collecting the Trophy! The Fiery Tears can be used on Steels just like Fiery Blood."
		, "Yeti Cave¶===================¶Deep in the Wintery Forest you will find the Yeti Cave, complete with iced Floors and a herd of Yetis at each of the four Entrances. In the center the Alpha Yeti will wait for you to get close or attack him."
		, "Yeti Cave¶===================¶The Alpha Yeti himself is quite the Cry-Baby, you can kill him with a Flame Bow very easily, even when he is immune to Arrows, and whenever you hit him he will throw a tantrum and make Ice fall from the ceiling."
		, "Yeti Cave¶===================¶Should he grapple you like a Pinchbeetle, just hit him until he gives you up and lets you down, so you can run around and desert him."// never gonna give a fuck, never gonna care about...
		, "Yeti Cave¶===================¶The Yetis drop Fur which can be used for Armors, or you just take a Knife to the Crafting Grid and remove the Fur to make Leather out of it instead. The Ice Bombs can be fun to throw at unsuspecting Yetis too."
		, "Aurora Tower¶===================¶A Blue-Green Tower is located ontop of the Glacier. It is a straight forward Dungeon with a little bit of Parkour. Look at the ceiling of each Room for 'hidden' Chests."
		, "Aurora Tower¶===================¶Three kinds of Mobs spawn inside of it, frosty Armored Ghosts, 'Ice Blazes' which drop Blizz Rods, and corrupt 'Ice Blazes' which explode after you kill them changing the Terrain in a weird way."
		, "Aurora Tower¶===================¶While climbing the insides of the Tower you will eventually end up finding a Room with an Ice ceiling, where you have to break through the ceiling multiple times to get up to the Snow Queen."
		, "Aurora Tower¶===================¶On the topmost Ice Layer you will find the Snow Queen. She is floating ontop of a platform of Ice. Hitting that floating Platform will do no damage to her at all. You need to hit her from the top."
		, "Aurora Tower¶===================¶There will be one or two 'Ice Blazes' she summons every now and then. They do mostly melee damage. She will move up and down a lot in attempt to dodge attacks, and with the Ice Platform block attacks too."
		, "Aurora Tower¶===================¶She has a Frost Breath that deals only Damage without any Side Effects. While breathing at you, you can hit her to make her stop the attack, though it's not really all that easy to do before she stops on her own."
		, "Aurora Tower¶===================¶The Snow Queen Trophy you get can be used to produce CU through a Magic Field Absorber. She drops a Tri-Bow or a Seeker-Bow, both of which are fun Weapons. The Ender-Bow wont teleport you when using Looting Arrows."
		, "Troll Caves¶===================¶Up in the Highlands are the Troll Caves, a place full of various vanilla Mobs and the namesake of the Caves, Trolls. The Walls are lined with 'Troll Steinn', an ugly Block that indicates presence or lack of Light."
		, "Troll Caves¶===================¶Many Plants unique to these Caves are located within, such as huge Mushglooms and Troll Berries, the latter of which will randomly ripen when in contact with Light, resulting in Torchberries and therefore more Light."
		, "Troll Caves¶===================¶The Trolls themselves hit like Golems, they also throw things at you. On death they cause Troll Berries to ripen, lighting up the Area. Also 2.5% to drop Magical Beans. They avoid Sunlight but are not hurt by it."
		, "Cloud Giants¶===================¶Far above the Highlands floats a Cloud. Use the Magical Beans from the Troll Caves on the Uberous Soil beneath that Cloud to spawn a huge Bean Stalk to get up there comfortably. Could also just pillar up though."
		, "Cloud Giants¶===================¶Up on the big walkable Cloud you will find a giant Oak Tree and a giant small Hut, where at least two Giants live, who will respawn in that Hut every once in a while. Ranged Weapons are advisable against them."
		, "Cloud Giants¶===================¶The entire Area may seem like everything is moving slower or laggier, but this is just an optical Illusion, everything still moves at normal speed. The Giants only seem slow because their movement didn't scale up."
		, "Cloud Giants¶===================¶You can get a Giant Sword or a Giant Pickaxe from defeating a Giant. The Pickaxe can be used to mine any of the giant Blocks, such as the giant Leaves, giant Logs, giant Obsidian and ofcourse giant Cobblestone."
		, "Cloud Giants¶===================¶It is also possible to use those giant Blocks to craft more Giant Pickaxes and Giant Swords. Also the Boxinator is capable of merging 64 Blocks into one of the giant Blocks using the Giant Pickaxe as free catalyst."
		, "Troll Vault¶===================¶The giant Cube of Obsidian contains the Lamp of Cinders and vanilla Endgame Loot such as Ancient Debris and Wither Skeleton Skulls. At this Version of Minecraft it's the final worthwhile Loot of Twilight Forest."
		, "Thorn Castle¶===================¶With the Lamp of Cinders you can either burn the Thorns or use it as an infinite Lighter. The Castle itself is unfinished, so there is not much to do there. There might be Ancient Debris below the Castle."
		, "If you combine various Trophies you got on your Journey, you will receive the Orb of Annihilation. It will grant any Holder all Progression Achievements of Twilight Forest. Just make sure that when you use the thing that you are NOT in your Base."
		, "Placing a Snow Queen Trophy (or any of the earlier placeable Trophies) somewhere, will let anyone who rightclicks the Trophy receive all related Progression Achievements as if they collected it, so that they can enter the various Biomes."
		, "This should conclude all the Stuff about Twilight Forest that seems noteworthy, aswell as most of the changes I made to improve the gameplay and add variety.¶-------------------¶Have Fun! ^^"
		});
		
		UT.Books.createWrittenBook("Manual_Enchantments", "The vexing World of Enchantments", "Evoker Number 42", ST.make(ItemsGT.BOOKS, 1, 10), new String[] {
		  "I cant believe how hard it is to find Information on these ancient Enchantments! But here I am, finally having collected everything there is to know about this Magic!"
		, "Enchanting Table¶===================¶You need one of these Tables, typically made of 2 Diamonds, 4 Obsidian and a Book. Then you can use your Magical Power and sometimes Lapis Lazuli to put random Enchantments onto Tools and Books!"
		, "Book Shelves¶===================¶To increase the Power of these previously mentioned random Enchantments, you merely need any Book Shelves filled with Books. Can even be some fancy Metal Shelves! The Books can even be on the backside!"
		, "Iron Anvils¶===================¶But random Enchantments cant just be removed from Tools. The Anvil will help with that, since it lets you put Enchantments that are in Books onto your Tools and even give them a Name!"
		, "Grindstone¶===================¶There supposedly is Grindstones that can remove Enchantments, but I have never seen any of those! Must be like those Brains in Jars I keep hearing about!"
		, "Magical Power¶===================¶You can acquire this by killing the many Creatures in this World, by cooking Food or messing with Ores! This is quite the Experience to be had, which is why many people call it XP or EXP!"
		, "List¶===================¶What follows is a List of the Enchantments that I know of and their Effects."
		, "Sharpness¶===================¶Levels 1 to 5+¶Deals 0.625 Hearts of Extra Damage per Level and works on pretty much everything!"
		, "Wrecking¶===================¶Levels 1 to 5+¶IDeals 0.75 Hearts of Extra Damage per Level and works on pretty much everything! Can normally only be applied to Railcraft Crowbars."
		, "Smite¶===================¶Levels 1 to 5+¶Deals 1.25 Hearts of Extra Damage per Level and works on Undead such as: Skeletons, Zombies, Drowned, Husks, Wraiths, Mummies and Liches! Smite Bullets can even penetrate a Lich Barrier!"
		, "Bane of Arthropods¶===================¶Levels 1 to 5+¶Deals 1.25 Hearts of Extra Damage per Level and works on things that have 6 or more Legs, like those pesky Spiders, Mites, Insects or Silverfish!"
		, "Implosion¶===================¶Levels 1 to 5+¶IDeals 1.5 Hearts of Extra Damage per Level and works on any Creepers! Can normally only be applied to Railcraft Crowbars. It often also hurts other explosive Mobs like Ghasts!"
		, "Dissolving¶===================¶Levels 1 to 5+¶Inflicts Weakness and Poison to any slimey Creatures it hits!"
		, "Disjunction¶===================¶Levels 1 to 5+¶Inflicts Weakness and Poison to any Ender Creatures it hits! Weakened Endermen cant Teleport anymore, making Projectiles with this Enchantment invaluable in deposing them!"
		, "Werebane¶===================¶Levels 1 to 5+¶Inflicts Withering and Poison to any Werecreatures and similar that it hits! Works on Minotaurs too!"
		, "Knockback¶===================¶Levels 1 to 2+¶This will yeet anyone you hit with it, decreasing your own horizontal velocity by 40% in the process, which is only noticeable midair. Its Level increases by one while sprinting."
		, "Fire Aspect¶===================¶Levels 1 to 2+¶Not only does this Enchantment set the Enemy ablaze for 4 Seconds per Level, sometimes even cooking their drops and at Level 3 it can also smelt any Block it harvests blazingly fast!"
		, "Looting¶===================¶Levels 1 to 3+¶Increases the amount of drops you get from Mobs. It may increase the total drops themselves or the chance that you get some rare drop at all."
		, "Fortune¶===================¶Levels 1 to 3+¶Increases the amount of drops you get from Blocks. It may increase the total drops themselves or the chance that you get some rare drop at all. Works on Ores, Crops, Gravel, and Glowstone."
		, "Silk Touch¶===================¶Only 1 Level¶Will make sure that the Block you harvest with the proper Tool, will drop itself instead of what it would normally drop otherwise. Small Ores get a better chance for bigger Gems."
		, "Infinity¶===================¶Levels 1+¶Bows will be able to shoot Infinite Arrows as long as you have one Arrow on you. Guns will have a 50%, 66%, 75%, 80%, 84% or higher chance to not consume Ammo based on the Level."
		, "Power¶===================¶Levels 1 to 5+¶Arrows shot will deal 25 percent more damage per Level. Bullets shot will fly faster and therefore have a higher piercing rate and higher Damage at longer range."
		, "Flame¶===================¶Levels 1+¶Your Ranged Weapon will set its Projectile on Fire, dealing Fire Damage similar to or combined with Fire Aspect. You can ignite TNT and some other things with it too."
		, "Punch¶===================¶Levels 1 to 2+¶Your Ranged Weapon will yeet anyone you hit with its Projectile about +3 additional Blocks per Level."
		, "Luck of the Sea¶===================¶Levels 1 to 3+¶Increases the chance of fishing up Treasure, decreases the chance of fishing up Junk, slightly decreases the chance of fishing up an actual Fish."
		, "Lure¶===================¶Levels 1 to 3+¶Reduces wait time for a Bite by 5 Seconds per Level up to Level 5, Level 6 or higher will instead make the Fishing Rod useless for fishing."
		, "Protection¶===================¶Levels 1 to 4+¶Reduces amount of overall Damage taken by 4 Percent per Level per Armor Piece, it caps at a combined 80 Percent Reduction, which includes all the other Protection Enchantment Types!"
		, "Fire Protection¶===================¶Levels 1 to 4+¶Reduces amount of Fire Damage taken by 8 Percent per Level per Armor Piece, it caps with Protection. It will reduce time on Fire by 15 Percent per best uncombined Level."
		, "Blast Protection¶===================¶Levels 1 to 4+¶Reduces amount of Explosion Damage taken by 8 Percent per Level per Armor Piece, it caps with Protection. It will reduce Explosion Knockback by 15 Percent per best uncombined Level."
		, "Projectile Protection¶===================¶Levels 1 to 4+¶Reduces amount of Ranged Damage taken by 8 Percent per Level per Armor Piece, it caps with Protection. Be aware that Explosive Projectiles may only be reduced partially!"
		, "Feather Falling¶===================¶Levels 1 to 4+¶Reduces amount of Enderpearl and Falling Damage taken by 12 Percent per Level per Armor Piece, it caps with Protection. Does not work if you are crashing into a Wall though."
		, "Thorns¶===================¶Levels 1 to 3+¶Each individual piece of Armor has the chance of 15 Percent per Level to deal between 0.5 and 2 Hearts of Damage to any Melee or Ranged Attacker. The Armor Piece will lose some more Durability though."
		, "Respiration¶===================¶Levels 1 to 3+¶Each Level adds 15 seconds worth of Oxygen underwater, which makes it twice, thrice or quarce the normal time. It also reduces Drowning Damage with a chance of 50%, 66% or 75% respectively."
		, "Aqua Affinity¶===================¶Only 1 Level¶Makes it so you can mine at normal Speed while standing underwater instead of five times slower. You do still have to stand on the ground though or else you are slowed down by five times."
		, "Efficiency¶===================¶Levels 1 to 5+¶Tools with this Enchantment get a flat Bonus to the Mining Speed of Level squared plus 1, so +2, +5, +10, +17 or +26. Tool Materials never have this Enchantment because it would be pointless."
		, "Unbreaking¶===================¶Levels 1 to 3+¶Tools with this Enchantment on average last longer by +100% per Level. Armors will only last +25%, +34% or +43% longer. Tool Materials never have this Enchantment because it would be pointless."
		});
		
		
		String tAlexGryllsIntro = "Hi, I'm Alex Grylls, Adventurer and experienced Hunter of the several Mobs you encounter in our World. In this Guide I will explain you easy Methods to hunt for wild Mobs in their natural Habitats.";
		
		UT.Books.createWrittenBook("Manual_Hunting_Creeper", "Hunting Guide for Creepers", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Creepers¶===================¶Creepers are sneaky, suicidal, leafy sticks on 4 short Legs, which will blow up when they are close by. They drop Gunpowder and will not deal any damage aside from the explosion when you let them come close."
		, "Creepers¶===================¶If a Creeper is struck by Lightning it will become supercharged and will explode with a much larger force. Creepers in general when shot by a Skeleton, will drop Music Discs, which are usable for the Jukebox."
		, "Creepers¶===================¶In order to detonate, a Creeper needs to see the Player, due to this it is always recommended to take the high Ground as you can hide behind the Cliff you are standing on more easily."
		, "Creepers¶===================¶Water is also a good place to melee a Creeper, as knockback will get it out of explosion Range, and even if it explodes it will not cause Damage to the ground, due to the Water absorbing the shock."
		, "Creepers¶===================¶For Melee Combat in general it is adviced to have Weapons with a strong Knockback so you don't need to sprint to knock the Creeper out of the Detonation Zone. You can also just shoot an Arrow first and then melee."
		, "Creepers¶===================¶The Implosion Enchantment does a great deal of Damage to Creepers. It can however only be gotten by enchanting Crowbars, or by using Tool Materials that have the Enchantment on them normally."
		, "Creepers¶===================¶You can manually ignite Creepers with Flint and Steel or a Lighter, and Ammunition with the Fire Aspect Enchantment will ignite Creepers at a distance if Implosion is not also on the same Arrow or Bullet."
		, "Creepers¶===================¶Last but not least, you can always just drop Water between you and the Creeper, it will slow it down considerably and if its about to explode anyways, then it won't damage the Terrain."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Skeleton", "Hunting Guide for Skeletons", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Skeletons¶===================¶Skeletons, usually named after Fonts and armed with Bow and Arrows. They are good at aiming, some of them are even capable of picking up dropped Weapons and Armor."
		, "Skeletons¶===================¶If they somehow kill Creepers, those Creepers will drop Music Discs. Killing a Skeleton on long Range will yield the Sniper Duel Achievement aswell."
		, "Skeletons¶===================¶In order to not have a bad time with Skeletons, you need to either keep a Safe distance from them and shoot them from far away, or to quickly engage them with Melee Attacks, Speed is useful for this purpose."
		, "Skeletons¶===================¶When you charge at a Skeleton, strave slightly to the right or left in order to have less Arrows hit you, any way to instantly kill them with one blow, will greatly reduce the chance of more Arrows flying at you."
		, "Skeletons¶===================¶If you have a Sword then blocking with it will reduce the Damage of Arrows. This can easily be used even during Melee to not get that large amounts of Damage, even if you get hit."
		, "Skeletons¶===================¶Backing a Skeleton into a Corner, by pushing it or knocking it back for example, will result in the Arrow getting Stuck in the Wall in a glitchy fashion and therefore not flying at you or damaging you."
		, "Skeletons¶===================¶Walls of any kind are useful, but what really helps (if you are prepared) is Sugar Canes / Reeds as they can block their view on you, while letting you shoot through."
		, "Skeletons¶===================¶You can also use Saplings and (sadistically) Bonemeal to instantly create a small Forest as a Shield in the Grasslands."
		, "Skeletons¶===================¶Being in Water makes you an easy Target for the Skeleton, since you are slow and constantly get knocked back. But the Skeleton being in Water can be worse as it bounces up and down without losing accuracy!"
		, "Skeletons¶===================¶But if both you and the Skeleton are in deep water then diving below the surface and attacking the Skeleton from below is the best advise I can give, as the Skeletons often miss at steep angles."
		, "Skeletons¶===================¶In some cases it is possible to just drop a Stone or a Wooden Sword for the Skeleton to pick up, they will drop their Bow in this case. However, this does not work on every Skeleton!"
		, "Skeletons¶===================¶Skeletons are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. The Smite Enchant will also help a lot."
		, "Skeletons¶===================¶Due to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. Since Skeletons shoot Arrows, it is a good Idea to wear Armor with Projectile Protection on it."
		, "Skeletons¶===================¶It is always a good Idea to just make Skeletons attack each other or other Mobs in order to make the attacked Mob angry at them (instead of you), causing them to kill each other."
		, "Spider Jockeys¶===================¶Ever saw a Skeleton Ride a Spider? Yeah its a terrifying picture. The speed and mobility of a Spider and a 'Turret' on its back."
		, "Spider Jockeys¶===================¶To fight them you can use easy tricks, like making the Spider crawl up a wall into the Roof, crushing the Skeleton that rides it in the process."
		, "Spider Jockeys¶===================¶In general you can apply the Strategies for both the Skeletons and the Spiders, to this combo. It's always the best solution to just shoot them from far away though."
		, "Spider Jockeys¶===================¶If you have a Bow then go for the Skeleton first, if you have to Melee then go for the Spider. Alternatively you can wait for the Sun to burn the Skeleton before engaging."
		, "Wither Skeletons¶===================¶Wither Skeletons are just like normal Skeletons with a Stone Sword, that instead of Arrows will drop regular Coal. They also are 3 Blocks tall and can absorb your Health."
		, "Wither Skeletons¶===================¶While Witherskeletons behave like any other humanoid Melee Mob, they are relatively fast and less stupid than Zombie Pigmen. If you want to know if you can handle one, practise on Zombie Pigmen first."
		, "Wither Skeletons¶===================¶Due to being about 3m tall, just like Endermen, you can essentially hide behind a 2m tall Roof and grind them into Ashes, uhh I mean Coal."
		, "Wither Skeletons¶===================¶In Melee Situations, walking backwards and swinging is the best way to fight them without getting yourself harmed, but watch out for Fire and Lava behind you."
		, "Wither Skeletons¶===================¶An Iron Golem is a good Idea to have with you, just take Iron Blocks and a Pumpkin with you. The Iron Golem will turn every hostile Mob in the area into scraps."
		, "Wither Skeletons¶===================¶Those black Skeletons spawn alongside regular Skeletons with Bow & Arrow inside a Nether Fortress, and they are the only really aggressive Mob there, aside from Blazes."
		, "Wither Skeletons¶===================¶If you end up fighting both a Wither Skeleton and a Blaze, the best is to hide outside the Range of the Blaze and trying to fight the Skeleton first, or to bring the Skeleton between the Blaze and yourself."
		, "Wither Skeletons¶===================¶Since most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Wither Skeletons¶===================¶The Heads, which are dropped by every 40th Wither Skeleton, can be used to summon the Wither, but the Wither is being talked about in another Book."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Zombie", "Hunting Guide for Zombies", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Zombies¶===================¶Zombies, the most common Mob in Minecraft. Zombies always attack with Melee Damage and they can wield Weapons and wear Armor."
		, "Zombies¶===================¶Zombies are grouped amoung the Undead Mobs, meaning they can be damaged by Sunlight, since it sets them on Fire. However, this does not apply to Baby Zombies!"
		, "Zombies¶===================¶Due to being undead, Healing Splash Potions deal damage to them, while poisoning ones heal them. The Smite Enchant is a good way to cause a lot of damage on them."
		, "Zombies¶===================¶Do not underestimate their Melee Damage and try to keep a safe distance while attacking them, make sure they don't hit you when you Melee them yourself."
		, "Zombies¶===================¶While wearing a Helmet they are immune to the Sun, that is until the Helmet loses all of its durability and breaks as it gets damaged when worn by a Zombie in Sunlight."
		, "Zombies¶===================¶Zombies are attracted by Villagers, you can use them to distract them. If you want them to murder that Village, that is."
		, "Zombies¶===================¶They can detect you from very far distances and will therefore end up swarming you from all directions, if you are not careful."
		, "Zombies¶===================¶Build yourself a small fortification if you are in the wild. Zombies luckily cannot break Blocks so they won't be able to attack you if you build it right."
		, "Zombies¶===================¶Since Zombies normally group up on you due to coincedential pathfinding, it is well advised to just throw Splash Potions of Regeneration or Healing at them, since they are damaged by those."
		, "Zombies¶===================¶As always, Bow and Arrows can help you killing Zombies, it is a good Idea to use golden Arrows or other Smite Arrows if possible. But usually there are too many of them to kill all."
		, "Zombies¶===================¶Use Knockback on Zombies even if not needed, otherwise there might be too many Zombies grouping up at you if you are surrounded, so keep them at distance."
		, "Zombies¶===================¶Keep Zombies in the Water, they are almost helpless there and can't shoot you like Skeletons would. They are swimming much slower than you do."
		, "Zombies¶===================¶Iron Golems are made for killing Zombies, they might be a viable option for your purposes."
		, "Zombies¶===================¶The official Mojang term for Zombies wearing golden Armor is 'Zom-Bling'."
		, "Baby Zombies¶===================¶Annoying little Bastards would be a better Name for them, not only that they are immune to Sunlight, they are also very fast and hard to hit!"
		, "Baby Zombies¶===================¶If you didn't need the Anti Zombie Strategies before, then now you probably need to use them, since those little things can ruin your day."
		, "Baby Zombies¶===================¶If you have the option, go for the Water, they aren't fast there. Other than that, mow your lawn and then aim low and hit them as fast as you can!"
		, "Chicken Jockeys¶===================¶Thought it couldn't get any worse? Chickens are faster than those Baby Zombies and now they actually ride the Chickens!"
		, "Chicken Jockeys¶===================¶Shoot the Chicken, yes get rid of it as fast as possible, you won't be able to Melee it as the Zombie is in the way, but you can try to shoot it!"
		, "Chicken Jockeys¶===================¶Other than that? Run for your life and try to either find a pond of water or use a Water Bucket on them, maybe you get it to dismount by doing so!"
		, "Zombie Pigmen¶===================¶Zombie Pigmen, usually wielding a Golden Sword and dropping Gold Nuggets. The only pieceful Zombie, well peaceful until a Player harms it."
		, "Zombie Pigmen¶===================¶If a Player does any damage to a Zombie Pigman, all the other Pegmen get angry and try to kill you, not only are they pretty fast, but they also do lots of Damage!"
		, "Zombie Pigmen¶===================¶Zombie Pigmen are immune to Fire and the Sun, if you happen to find one in the Overworld (Pig struck by Lightning, or fresh from a Portal) it is usually safe to kill."
		, "Zombie Pigmen¶===================¶The easiest Solution is not to harm them, but it always can happen that you accidentially do harm to them, or you mine the Ores of that Nether Ores Mod."
		, "Zombie Pigmen¶===================¶In that case, pillar up or dig yourself in, as fast as possible! You don't want to die instantly and drop all your Inventory into the Nether."
		, "Zombie Pigmen¶===================¶Once you have done that, make yourself a comfortable spot to attack the Pigmen from a space where they cant attack you."
		, "Zombie Pigmen¶===================¶Since most of the times you are in the Nether with these Mobs, placing a Nether Portal close by to lure Mobs inside it, by hiding behind it, might be a good Idea (if you don't plan to use that Portal later)."
		, "Zombie Pigmen¶===================¶As they are undead like normal Zombies aswell, Splash Potions of Regeneration and Healing can deal great damage to them, while leaving yourself unharmed."
		, "Zombie Villagers¶===================¶They behave just like normal Zombies, even though they look much worse, the only difference is that you can cure them to get Villagers, what will be explained in the following Pages."
		, "Zombie Villagers¶===================¶First of all, you need a good place to actually cure that Zombie without other Zombies turning the Villager right back into the undead Green."
		, "Zombie Villagers¶===================¶For that you need to know, that the Transformation back into a Villager for some reason goes by faster if you use vanilla Iron Bars for the Cage."
		, "Zombie Villagers¶===================¶Once you got the Cage, there are multiple Options on the Item you use to cure the Villager Zombie. There are multiple options."
		, "Zombie Villagers¶===================¶Splash Potions of Weakness + Golden Apples are one way. Glass Bottles of Holy Water can replace that Golden Apple. The rainbow colored Cure-All Pill is an alternative that does not need Weakness."
		, "Zombie Villagers¶===================¶Now you need to somehow lure the Zombie Villager into your Cage. Don't worry, the Villager won't remember that you punched them while they were a Zombie."
		, "Zombie Villagers¶===================¶A fishing Rod can also work wonders on moving the Zombie into the right directions. Punching them into a pit and closing it off, will work aswell."
		, "Zombie Villagers¶===================¶If your Cage is outside (or improvised), then make sure the Sun won't burn the Villager before you can heal them. Add a Roof or something to your Cage, once you caught them."
		, "Zombie Villagers¶===================¶So, now that you got the Villager into a Cage, you can heal them. Once you have done that, transport the Villager somewhere else, maybe with Rails and Minecarts."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Spider", "Hunting Guide for Spiders", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Spiders¶===================¶Spiders, octo-legged, climbing, pastry selling Arthropods, that can jump at you. Often accompanied by a random Potion Effect, which doesn't ever run out, making them invisible, resistant, regenerating or faster."
		, "Spiders¶===================¶They are fast, usually faster than you unless you run. They can also climb to a certain extend, so Walls won't help much, however Spiders can easily get stuck on ceilings, where they could fall down on you!"
		, "Spiders¶===================¶The normal way to beat them is already very effective, just melee or shoot them. They have less Health than other Mobs. The Bane of Arthropods Enchant is very effective on them."
		, "Spiders¶===================¶Spiders will jump/pounce at you when they are close, if you time it right then hitting them will knock them back farther than usual. Also always have the high Ground when fighting Spiders as otherwise they'll jump you."
		, "Spiders¶===================¶If you are in a Desert, then look out for some tsundere Plants called 'Cactus', plural being 'Cactii', Spiders apparently love climbing them, damaging themselves in the process."
		, "Spiders¶===================¶At daytime, Spiders are tired and usually not aggressive. If you attack them then and with critical hits, then they will die by the surprise and won't damage you."
		, "Spiders¶===================¶Running is the best thing to do when fighting Spiders, not running away, I mean running at them or keeping a safe distance during combat. Spiders are immune to Poison, so splashing them with it isn't effective at all."
		, "Spiders¶===================¶They get stuck at overhangs, meaning if you build a pillar with 4 overhang blocks (one at each side) Spiders won't get up and you can damage them from ontop of that pillar."
		, "Spiders¶===================¶due to being 2m in diameter, hiding in a 1m tight spot is a good idea when fighting regular Spiders. This doesn't work on the poinsonous Cave Spiders though."
		, "Cave Spiders¶===================¶Cave Spiders are just 1m in diameter and they inject poison when they melee you. They normally only spawn inside Mineshafts, since there are Spawners for them over there."
		, "Cave Spiders¶===================¶The Spawners are surrounded by lots and lots of Webs, those webs will slow you down, so take your Sword or Silk Touch Shears and slice those Webs away, also useful if you need String."
		, "Cave Spiders¶===================¶Cave Spiders often get Stuck at the wooden Pillars or hide in the ceiling at those, so be careful where you walk as they will fall down at you very surprisingly despite making lots of noise."
		, "Cave Spiders¶===================¶Due to them being poisonous, Milk or any sort of Antidote Pill are useful Items to have on your Hotbar. Also hiding from them by closing off Paths is very needed sometimes."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_End", "Hunting Guide for the End", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Endermen¶===================¶Endermen, 3m tall, black, purple eyed Teleporters with a weakness for Water and Silver (Disjunction Enchant), that can take some Blocks away from the World."
		, "Endermen¶===================¶Under normal circumstances Endermen just mind their Business and don't care what you are doing, but they do not like getting stared at, to the point that they will attack you if you do so."
		, "Endermen¶===================¶If you still want to look at them without them attacking you, then wear a carved Pumpkin as Helmet. But you are hunting them, probably for the Pearls, so turn to the next Page."
		, "Endermen¶===================¶Since Endermen don't usually attack you, I presume you just want their Pearls, and for that an Enderman Trap is a good Idea."
		, "Endermen¶===================¶The simplest Method is just standing below a 2m tall ceiling, so they cannot get to you. And then stare at every Enderman around to let them rage at you and attack you."
		, "Endermen¶===================¶Another way would be the use of Water Buckets to shield yourself off. Also they can only teleport distances of at least 16m btw, no shorter teleports are possible for them."
		, "Endermen¶===================¶As you may know, normal Arrows are pretty much worthless against them, that is why you should use Arrows with Disjunction on it (Silver), in order to prevent them from teleporting."
		, "Endermen¶===================¶If you encounter an angry Enderman, then try to run into the closest Forest, the low Trees are perfect hiding Spots, and they cant teleport to you if they are too close to you already."
		, "Endermen¶===================¶Melee attacks only work out if you look at their feet while swinging, as they will teleport away when you stare at them, since staring at them would freeze them into place."
		, "Endermen¶===================¶The Weakness Potion Effect is capable of preventing an Enderman from Teleporting, make use of that and splash them. (Note, that it is GregTech adding that Feature!)"
		, "Ender Dragon¶===================¶The Ender Dragon is essentially the Queen of the Endermen. She flies around between Obsidian Pillars with Magical Ender Crystals, that will regenerate her Health. Her attack pattern is Kamikaze Melee."
		, "Ender Dragon¶===================¶She will obliterate every normal Block (except Endstone, Bedrock and Obsidian) when flying through it. She is immune to Knockback and Fire, and weak to the Disjunction Enchant found in Silver."
		, "Ender Dragon¶===================¶The first thing you have to do is making sure you don't get knocked into the Void. Building Walls will only help if you have Obsidian with you, since she can't break it, unlike everything else you can bring."
		, "Ender Dragon¶===================¶Once you are safe on the Main Island, you have to go and get rid of the Ender Crystals. They can easily be destroyed by Snowballs and Arrows. Do not melee those Crystals as they deal a huge explosion damage."
		, "Ender Dragon¶===================¶Due to the Kamikaze Ram Attack, the Ender Dragon can be fought in Melee aswell as with Bow and Arrow. Use Weapons with the Disjunction Enchant and get yourself some Strength Buff in order to deal more melee."
		, "Ender Dragon¶===================¶During the Fight it can happen that you stare at Endermen by accident, in order to prevent that you could either wear a Pumpkin as Hat and press F1, or just try to only look at a slight upwards angle."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Blaze", "Hunting Guide for Blazes and Ghasts", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Blazes¶===================¶Blazes are floating, fireball shooting, yellow Heads with valuable fiery Rods rotating around them. Those Rods only drop if they are killed by a living thing, making them rare. They also deal Melee Fire Damage too."
		, "Blazes¶===================¶As they shoot Fireballs, they can be hard to get close to. My advice is either just shooting them with a Bow or being very creative and using a Fishing Rod to reel them in for Melee Combat."
		, "Blazes¶===================¶You can also just hide behind a Wall and wait for them to come close, and then strike and hide again, so they don't get a chance to fire back. Building such a Wall between you and the Blazes is also a good Idea."
		, "Blazes¶===================¶Fire Resistance is a nice Effect you should somehow have on yourself when you fight them, it makes you nearly immune to Blazes overall. Also possible is the use of Iron Golems, what not many think about doing."
		, "Blazes¶===================¶Snowballs are normally useless against Mobs but in case of Blazes they do cause some severe Damage and are easier throw than you can shoot a bow. Snow Golems wouldn't survive in the Nether but they would work too."
		, "Blazes¶===================¶Using Wolves or Golems can lead to them knowing Blazes down the Platforms, meaning you might not get drops, or they will now attack you from a distance, only do this for Areas that are surrounded by Netherrack."
		, "Blazes¶===================¶Blazes can also be damaged by Water Buckets, sure they won't drop anything, and Water Buckets don't work in the Nether, but this Information might be useful if you got some way to get Water over there."
		, "Ghasts¶===================¶Ghasts, flying, ghost alike, crying Tentacle Monsters with explosive Projectiles. Their Tears are hard to get due to them being airborne and the Nether being all like 'The Floor is Lava!'."
		, "Ghasts¶===================¶Their Projectiles can literally be punched back at them kinda like a Baseball, what will yield the 'Return to Sender' Achievement. What is a Baseball, you ask? I don't know either."
		, "Ghasts¶===================¶Punching their Fireballs back at them can be hard to do, especially with Lag, but Arrows can deflect the fireballs aswell, meaning you can shoot Arrows at the Fireballs as a defense."
		, "Ghasts¶===================¶In general any form of ranged Attack is advised, because it happens very rarely, that a Ghast is close enough to the ground to actually melee it. The Implosion Enchantment works well on them too."
		, "Ghasts¶===================¶It can happen very often that you are unprepared and a Ghast shows up trying to blow you into smithereens. In that case, run away and hide, first."
		, "Ghasts¶===================¶After you are done finding a hiding Spot, look at where exactly the Ghast is right now, so you can stay out of its Range. Then either continue your business or shoot the Ghast."
		, "Ghasts¶===================¶If you are going to deflect a Ghast Blast the melee way, then use a Sword for that, as it gives you a slightly increased Range. This is especially useful if you cant use a Bow."
		, "Ghasts¶===================¶Ghasts are the normal kind of Mob, they are neither Undead nor Spiders, there is no special Enchant against them, and Potions work normally on them."
		});
		
		UT.Books.createWrittenBook("Manual_Hunting_Witch", "Hunting Guide for Witches", "Alex Grylls, Survival Specialist", ST.make(ItemsGT.BOOKS, 1, 3), new String[] {tAlexGryllsIntro
		, "Witches¶===================¶Witches are uglier Villagers with Potion Skills. When damaged they will drink a Potion of Regeneration or Fire Resistance depending on the source of the Damage. They sometimes drop the **most recently used** Potion."
		, "Witches¶===================¶Their attacks are Splash Potions of Poison and Harming. Since they use Fire Resistance Potions 'BURN THE WITCH!!!' is not an attack option for you. Drowning doesn't work either, as they use Water Breathing Potions."
		, "Witches¶===================¶Bow and Arrow are ofcourse a good option, but if you have to melee then do it fast and run straight at the Witch to hit them. This will cause the Witch to try healing themself instead of attacking you."
		, "Witches¶===================¶A Bucket of Water can give you the needed distance from the Witch in order to attack them with ranged Weapons. But watch out, if you are too far away, the Witch may use Speed Potions to be able to catch up with you."
		, "Witches¶===================¶In swampy Areas there is the possibility to find Witch Huts, those Huts may be very small but only Witches can spawn in there, meaning a Mob Spawning Area built around it will only spawn Witches!"
		, "Witches¶===================¶Witches drop Empty Bottles, Sticks, Redstone, Glowstone, Sugar, Spider Eyes, Gunpowder and Potions. This makes them a valuable Mob to farm and also a way to get Glowstone and Potions without the Nether."
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
		
		tBook.add("Swords"              +"¶===================¶It's a Sword, it deals Damage and cuts things. It can harvest Leaves, Webs and Wool Blocks.");
		tBook.add("Knifes"              +"¶===================¶They cut Food, Sticks, Rubber, Saplings into Sticks, Bark of a Tree, and similar things and are a somewhat usable early weapon. It can harvest all the things a Sword can harvest too.");
		tBook.add("Butchery Knifes"     +"¶===================¶Has a Looting Modifier on it per Default, but has a slow swing and low Damage.");
		
		tBook.add("Clubs"               +"¶===================¶Sometimes kindof expensive, but it works like a Hammer and deals lots of Damage, while having a larger cooldown time after a hit. It can break Rocky Blocks, but it will crush them.");
		
		tBook.add("Axes"                +"¶===================¶Good in chopping down Trees, bad in chopping down Planks or other wooden things.");
		tBook.add("Double Axes"         +"¶===================¶Still good for chopping on Trees, but also very effective as a Weapon and has 50% more Durability than the normal Axe, while requiring 66.666% more Material to create.");
		tBook.add("Saws"                +"¶===================¶Bad for chopping Trees, but can easily cut wooden things like Planks.");
		tBook.add("Chainsaws"           +"¶===================¶Good for pretty much all purposes, can chop Trees, saw Planks and is a very nice Weapon. It can specifically be used to spam click Creepers to death easily.");
		tBook.add("Buzzsaws"            +"¶===================¶This Type of Saw is dedicated to Hand Crafting and cannot harvest any Blocks. It is cheaper to use this than a Chainsaw for crafting purposes.");
		
		tBook.add("Pickaxes"            +"¶===================¶Mines Blocks like a normal Pickaxe usually does.");
		tBook.add("Construction Picks"  +"¶===================¶It mines man-made Blocks much faster, but is slow on Natural Rocks.");
		tBook.add("Gem tipped Pickaxes" +"¶===================¶In an exchange for a lot of Durability, you can put flawed Gems onto your Raw Steel Pickaxe Head, to give it the Quality of a Gem Tool. Amber, Sapphires and Diamonds are best for this.");
		tBook.add("JackHammers"         +"¶===================¶This thing mines Blocks and crushes them like a Hammer at an insane Speed. Not much to say about it.");
		tBook.add("Mining Drills"       +"¶===================¶It can mine and shovel things at once and is the perfect universal mining Tool.");
		
		tBook.add("Shovels"             +"¶===================¶Mines Blocks like a normal Shovel usually does.");
		tBook.add("Spades"              +"¶===================¶A Spade has conditional Silk-Touch for certain Blocks, such as Dirt, Grass, Mycelium, Clay, Mud, Snow and Podzol, but it is slower than a normal Shovel and doesn't work on Sand.");
		tBook.add("Plows"               +"¶===================¶The Plow harvests a 3x3x3 Area of Snow Layers and similar Blocks at once.");
		tBook.add("Universal Spades"    +"¶===================¶Shovel, Saw, Sword and Crowbar at once. Very versatile, but that goes at the cost of Durability and Speed.");
		
		tBook.add("Hammers"             +"¶===================¶Crushing Blocks and Smithery are the most common usages of this Tool. It doesn't do much else, but it can be used in Combat.");
		tBook.add("Soft Hammers"        +"¶===================¶These Hammers are mainly used to turn Machines ON and OFF. They can even turn Redstone Lamps and certain Rails ON without having to have a Redstone Signal close to them.");
		tBook.add("Wrenches"            +"¶===================¶Adjusts the primary Facing of most Machines, sometimes it can be used to toggle Modes aswell.");
		tBook.add("Monkey Wrenches"     +"¶===================¶Adjusts the secondary Facing of most Machines, often it can be used to toggle Modes aswell.");
		tBook.add("Electric Wrenches"   +"¶===================¶They are a combination of the normal Wrench and the Monkey Wrench and can easily switch between them via Modes. Sneak Rightclick on a non-wrenchable Block to switch.");
		
		tBook.add("Files"               +"¶===================¶Sharpens things and can comically harvest Iron Bars way faster than anything else.");
		tBook.add("Chisels"             +"¶===================¶Can put Molds into Shape and change the looks of some Stones.");
		tBook.add("Bending Cylinders"   +"¶===================¶Turns Plates into Curved Plates and is used for similar Metal Working procedures");
		
		tBook.add("Crowbars"            +"¶===================¶Opens Crates and can be used on Rails and Minecarts");
		tBook.add("Pincers"             +"¶===================¶Used to pick up hot things from Molds and Basins, so you don't have to wait for them to cool down before picking them up.");
		tBook.add("Plungers"            +"¶===================¶Empties Pipes and Fluid Tanks by either voiding the Fluid or dropping the ItemStack that is stuck in them. Works on Thaumic Conduits aswell.");
		tBook.add("Scoops"              +"¶===================¶Catches Butterflies, Bees and Bumblebees, also needed to harvest Bumble Hives and Bee Hives.");
		
		tBook.add("Hoes"                +"¶===================¶They till the ground, what do you expect? I'm not gonna explain vanilla Tools in great detail.");
		tBook.add("Branch Cutters¶Trimmers"+"¶===================¶Cuts Saplings out of Leaves with high precision, like a Grafter.");
//      tBook.add("Sickles"             +"¶===================¶");
		tBook.add("Senses"              +"¶===================¶Harvests a 3x3x3 Area of Crops, Flowers and Tall Grass.");
		
		tBook.add("Rolling Pins"        +"¶===================¶Flattens Dough and Clay for cooking and crafting purposes.");
		tBook.add("Scissors"            +"¶===================¶Can cut Tripwires on Rightclick, shear Sheep and harvest Wool-ish/Cloth-ish Blocks.");
		tBook.add("Screwdrivers"        +"¶===================¶Drives Screws and such. The most common usage is adjusting Modes on Blocks and Covers. They can rotate Comparators and Repeaters.");
		tBook.add("Wire Cutters"        +"¶===================¶They harvest Wires. Another usage is to adjust Wires and in some cases Covers aswell.");
		tBook.add("Hand Mixers"         +"¶===================¶These are just so you don't consume your Hunger Bar when operating the Mixing Bowl.");
		tBook.add("Hand Drills"         +"¶===================¶Those Drill Holes in Surfaces. If you have Dynamite Sticks or Iron/Steel Rods with you, you can stick the Dynamite into Walls or reinforce Bricks and Concrete.");
		tBook.add("Flint and Tinder"    +"¶===================¶Sets things on Fire and can light anything that requires ignition. But it might take a few tries to do so.");
		tBook.add("Magnifying Glasses"  +"¶===================¶With this you can look at the Details of a LOT of things in GregTech, just put a Lens of any kind on a Stick and you are good to go for a long time.");
		
		tBook.add("Pocket Multitool"    +"¶===================¶A Multi-Tool that can be switched to the most common purposes. See it as a Swiss Army Knife.");
		
		UT.Books.createWrittenBook("Manual_Tools", "Tool Index", "GITF (Gregorius Industrial Tool Factory)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		
		tBook.clear();
		tBook.add("This is the Manual of the Smelting Crucible and related Objects. It will explain how to use the Smelting Crucible and the Molds properly, so that you can start smelting things which cannot me molten inside a regular Furnace.");
		tBook.add("If you need to know how heavy an Object is or what Materials it consists out of, just use the good old vanilla F3+H Method to enable advanced Tooltips, so you can see the most relevant Data about the Object.");
		tBook.add("Step 1: Heat Source" +"¶===================¶You need a Heat Source in order to heat up your Crucible to the desired Temperature. Possible Heat Sources may include Burning Boxes, Electric Heaters, Laser Heaters and Arc Heaters.");
		tBook.add("Optional: Air Source"+"¶===================¶In case you have to supply Air for the Crucible (for Steel), you need to point an Engine into it, which will act as a Fan. For Multiblock Crucibles the Engine has to be at the Bottom Row.");
		tBook.add("Step 2: Crucible"    +"¶===================¶The choice of Crucible Material is very important. The Crucible can melt too at a certain Temperature, which is usually higher than the actual Melting Point of the Crucible itself.");
		tBook.add("Step 2: Crucible"    +"¶===================¶Now place the Crucible of your choice at the Output Facing of the Heat Source. The Burning Box for example only emits to its Top Side, so you need to place the Crucible above it.");
		tBook.add("Step 3: The Molds"   +"¶===================¶You probably want to cast the molten Metal into a Shape, so you need to place a Mold horizontally adjacent to the Crucible. As you may have noticed the Mold itself doesn't have a Shape.");
		tBook.add("Step 3: The Molds"   +"¶===================¶This is why you need a Chisel in order to chisel the proper Shape into the Mold by using it in World. The most important Shapes are Listed in the last Pages of this Book.");
		tBook.add("Step 3: The Molds"   +"¶===================¶If you somehow fail to put a correct Shape into the Mold, the End Result will be as many Nuggets as you have chiseled out places.");
		tBook.add("Step 4: Smelting"    +"¶===================¶Now we get to smelting. Just throw the things you want to smelt into the Crucible, or use a Hopper above the Crucible to fill it. And turn up the Heat until you reach the melting Point.");
		tBook.add("Step 4: Smelting"    +"¶===================¶Unless your Heat Source has some kind of Temperature regulation, you may need to shut it off once the Temperature reaches the Melting Point or else you will vaporate the Metals or worse.");
		tBook.add("Step 5: Shaping"     +"¶===================¶Now just click the Side of the top of the Mold where the Crucible is next to, in order to fill it with its Metals. Warning: The Mold itself can melt too if it is made of the wrong Material.");
		tBook.add("Step 5: Shaping"     +"¶===================¶Wait for the Metal to cool down, but note that you should not touch or take out the Metal while it is hot or else you will burn yourself unless you wear a Hazmat Suit or something.");
		tBook.add("Mold Shapes"         +"¶===================¶The following is a List of Shapes you can chisel the Mold into. The position of the Shape on the Mold is NOT important, you can rotate and mirror it too¶X = Chiseled¶O = Not Chiseled");
		tBook.add("Ingot"               +"¶===================¶XXXOO¶XXXOO¶XXXOO¶XXXOO¶XXXOO¶===================¶1.000 Units required");
		tBook.add("Chunk"               +"¶===================¶XXOOO¶XXOOO¶OOOOO¶OOOOO¶OOOOO¶===================¶0.250 Units required");
		tBook.add("Plate"               +"¶===================¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶===================¶1.000 Units required");
		tBook.add("Tiny Plate"          +"¶===================¶OOOOO¶OXXXO¶OXXXO¶OXXXO¶OOOOO¶===================¶0.111 Units required");
		tBook.add("Bolt"                +"¶===================¶XXOOO¶OOOOO¶OOOOO¶OOOOO¶OOOOO¶===================¶0.125 Units required");
		tBook.add("Rod"                 +"¶===================¶OOOOO¶OOOOO¶XXXXX¶OOOOO¶OOOOO¶===================¶0.500 Units required");
		tBook.add("Long Rod"            +"¶===================¶XOOOO¶OXOOO¶OOXOO¶OOOXO¶OOOOX¶===================¶1.000 Units required");
		tBook.add("Item Casing"         +"¶===================¶XXXOX¶XXXOX¶XXXOX¶OOOOX¶XXXOO¶===================¶0.500 Units required");
		tBook.add("Ring"                +"¶===================¶OOOOO¶OXXXO¶OXOXO¶OXXXO¶OOOOO¶===================¶0.250 Units required");
		tBook.add("Gear"                +"¶===================¶XOXOX¶OXXXO¶XXOXX¶OXXXO¶XOXOX¶===================¶4.000 Units required");
		tBook.add("Small Gear"          +"¶===================¶OXOXO¶XXXXX¶OXOXO¶XXXXX¶OXOXO¶===================¶1.000 Units required");
		tBook.add("Sword"               +"¶===================¶OOXOO¶OXXXO¶OXXXO¶OXXXO¶OXXXO¶===================¶2.000 Units required");
		tBook.add("Pickaxe"             +"¶===================¶OOOOO¶OXXXO¶XOOOX¶OOOOO¶OOOOO¶===================¶3.000 Units required");
		tBook.add("Spade"               +"¶===================¶OXXXO¶OXXXO¶OXXXO¶OXOXO¶OOOOO¶===================¶1.000 Units required");
		tBook.add("Shovel"              +"¶===================¶OOXOO¶OXXXO¶OXXXO¶OXXXO¶OOOOO¶===================¶1.000 Units required");
		tBook.add("Universal Spade"     +"¶===================¶OOXOO¶OXXXO¶OXXOO¶OXXXO¶OOOOO¶===================¶1.000 Units required");
		tBook.add("Axe"                 +"¶===================¶OOOOO¶OXXXO¶OXXXO¶OXOOO¶OOOOO¶===================¶3.000 Units required");
		tBook.add("Double Axe"          +"¶===================¶OOOOO¶XXXXX¶XXXXX¶XOOOX¶OOOOO¶===================¶5.000 Units required");
		tBook.add("Saw"                 +"¶===================¶OOOOO¶XXXXX¶XXXXX¶OOOOO¶OOOOO¶===================¶2.000 Units required");
		tBook.add("Hammer"              +"¶===================¶XXXOO¶XXXOO¶XOXOO¶XXXOO¶XXXOO¶===================¶6.000 Units required");
		tBook.add("File"                +"¶===================¶OXXXO¶OXXXO¶OXXXO¶OOXOO¶OOXOO¶===================¶1.500 Units required");
		tBook.add("Screwdriver"         +"¶===================¶OOOOO¶OOXOO¶OOXOO¶OOXOO¶OOXOO¶===================¶1.000 Units required");
		tBook.add("Chisel"              +"¶===================¶OXXXO¶OOXOO¶OOXOO¶OOXOO¶OOXOO¶===================¶1.500 Units required");
		tBook.add("Arrow"               +"¶===================¶OOOOO¶OOXOO¶OOXOO¶OXXXO¶OOOOO¶===================¶0.125 Units required");
		tBook.add("Hoe"                 +"¶===================¶OOOOO¶OOXXO¶OXXXO¶OOOOO¶OOOOO¶===================¶2.000 Units required");
		tBook.add("Sense/Scythe"        +"¶===================¶OOOOO¶OXXXX¶XXXXX¶OOOOO¶OOOOO¶===================¶3.000 Units required");
		tBook.add("Plow"                +"¶===================¶XXXXX¶XXXXX¶XXXXX¶XXXXX¶OOXOO¶===================¶4.000 Units required");
		tBook.add("Builder's Wand"      +"¶===================¶OOXOO¶XXXXX¶OXXXO¶OXOXO¶OOOOO¶===================¶1.000 Units required");
		
		UT.Books.createWrittenBook("Manual_Smeltery", "Smelting Crucible Manual", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		//-----
		
		tBook.clear();
		tBook.add("This Book Contains Information about every Alloy, which can be created by using a Smelting Crucible.¶===================¶In order to make an Alloy you need to reach the Melting Point of the Alloy itself.");
		tBook.add("And you need to reach the Melting Point of all but one of its Components. You can ofcourse also melt all of the Components, but you are free to 'not melt' one of the Components.");
		tBook.add("In case you have to supply Air for the Crucible (for Steel), you need to point an Engine into it, which will act as a Fan. For Multiblock Crucibles the Engine has to be at the Bottom Row.");
		
		for (OreDictMaterial tMat : OreDictMaterial.ALLOYS) {
			for (IOreDictConfigurationComponent tComponents : tMat.mAlloyCreationRecipes) {
				tPage="Alloy:¶"+tMat.getLocal()+"¶===================¶Melting: "+tMat.mMeltingPoint+" K¶Boiling: "+tMat.mBoilingPoint+" K¶===================¶Components per "+tComponents.getCommonDivider() + "¶";
				for (OreDictMaterialStack tMaterial : tComponents.getUndividedComponents()) tPage += (tMaterial.mAmount / U)+" "+tMaterial.mMaterial.getLocal()+"¶";
				tBook.add(tPage);
			}
		}
		
		UT.Books.createWrittenBook("Manual_Alloys", "Book of Alloys, Smeltery Edition ("+(tBook.size()-2)+")", "GMWI (Gregorius Metal Working Industries)", ST.make(ItemsGT.BOOKS, 1, tBook.size()>50?32001:32000), tBook.toArray(ZL_STRING));
		
		//-----
		
		tBook.clear();
		
		for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_ARRAY) {
			if (tMat != null && !tMat.mHidden && tMat.contains(TD.Atomic.ELEMENT) && !tMat.contains(TD.Atomic.ANTIMATTER)) {
				tBook.add(tMat.getLocal()+"¶"+tMat.mProtons+"/"+tMat.mNeutrons+"¶===================¶ID: "+(tMat.mID<0?"NONE":tMat.mID)
				+"¶Melting: "+tMat.mMeltingPoint+" K¶Boiling: "+tMat.mBoilingPoint+" K¶Plasma: "+tMat.mPlasmaPoint
				+" K¶===================¶Density:¶"+(tMat.mGramPerCubicCentimeter == 0 ? "???" : tMat.mGramPerCubicCentimeter)+" g/cm3¶"+tMat.getWeight(U)+" kg/unit¶===================¶");
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
		
		UT.Books.createWrittenBook("Manual_Reactors", "Fission Reactor Manual", "Apature Atomics", ST.make(ItemsGT.BOOKS, 1, 32005), new String[] {
		"¶¶===================¶Introduction and Safety Instructions¶===================¶",
		"[Transcript]¶Congratulations [Insert employee name] for graduating in nuclear physics!¶You are now fit for your job at Apature Atomics as a reactor operator.",
		"First, I'll introduce you to what you'll be operating, hopefully without unplanned explosions:¶The core of any fission reactor, the 2x2 nuclear reactor core.",
		"Some of our engineers would argue that it is just a metal box with four pistons to push the rods either in or up, that we've gone overboard with the processing power on these puppies, that four damn wires would have been sufficient.",
		"As if anyone would trust four damn wires to prevent total nuclear destruction, it is probably even not in their best interest to do so anyway. That is why in our reactor cores, every single rod controller is sentient.",
		"It is therefore in their best interest to not explode, as I would then get very angry at them and banish them to 'reactor rod controller'-hell!",
		"That is what we told them anyway, such a place does obviously not exist, we don't have money to fund every stupid thing. They would go to robot hell instead to be eternally incinerated and rebuild while we gather useful data.",
		"Turns out those fuckers would never insert the reactor rods at all, making the reactors perfectly safe, but also quite useless, so we overwritten their control of the rods to instead be controlled with four wires.",
		"Any rumors you might have heard of 1x1 reactor cores are just that, rumors, we would of course never build such a thing because it is obvious that the rod controller would go mad without three other ones to entertain them.",
		"In the unlikely case you might stumble upon such a core, which won't happen, if they existed we would have already send them to robot hell were they would reside in constant agony, which they don't because they don't exist.",
		"Anyway, safety, I'm obliged to give you this information, so I'll go over it quickly: Wear you radiation hazard suit at all times. Do not explode the reactor core. Do not explode. Do not cause a nuclear meltdown.",
		"Do not deny causing a nuclear meltdown in case of a nuclear meltdown. Do not touch the running reactor. Do not break the running reactor. Do not lick the running reactor. Do not let flamingos get near the reactor.",
		"Do not eat the nuclear fuel. Do not cook on the nuclear reactor. In case of tumors, do eat the nuclear fuel. That should be everything you need to know, now go at it and make some science!",
		"¶¶===================¶Reactor Operation Basics¶===================¶",
		"The Nuclear Reactor Core (2x2) has slots for four reactor rods. Rods inserted into the reactor will only directly interact with directly adjacent reactor rods, also including adjacent slots on different, but adjacent, reactor core blocks.",
		"Rods can be manually inserted into a slot by right clicking it with the rod. Right clicking a slot with Pincers will pull out the reactor rod inside the slot.",
		"The reactor rods can also be automatically inserted on the top side when the reactor block is off. It is also possible to automatically extract them from the bottom side when the block is off.",
		"The reactor can be manually toggled on or off with a soft hammer. A redstone machine switch will allow controlling this with redstone. While the reactor is off, the reactor will behave as if it doesn't contain any rods.",
		"Individual rods can also be toggled on or off to behave as if the slot were empty. This can be done with either the redstone or manual selector.",
		"The reactor core has two internal tanks to store 64_000 liters of cold and hot coolant. Different coolant can have different effects on the inserted reactor rods. Cold coolant can be inserted from any side.",
		"Hot coolant will be automatically output on the red facing. If the cold coolant tank is more than half full, cold coolant will be automatically output on the blue facing. The red facing can be moved with a wrench and the blue one with a monkey wrench.",
		"While the reactor is on, Neutrons can be present on active reactor rods. The neutrons might interact with the reactor rods in different ways.",
		"Generally, the number of neutrons is directly output as HU into the reactor each tick. Neutrons only last for one second and will therefore be updated only every second.",
		"Neutron counts can be manually measured by right-clicking the reactor core with a Geiger Counter. The Geiger Counter Sensor can also be attached to the reactor core to measure the sum of all neutrons on the reactor rods.",
		"100% of the percentage modes of the sensor refers to the sum of neutron maximums of all fuel rods inside the reactor core. A reactor will also emit radiation with a ranged based on the highest number of neutrons on any reactor rod.",
		"Building the reactor far away from any settlements and wearing a radiation hazard suit is strongly advised. Any reactor core will explode when heat gets added without coolant being present.",
		"Should new heat energy from neutrons be added when the reactor is out of coolant, the reactor will violently disintegrate.",
		"Should there be no space for the produced hot coolant remaining in the reactor, the reactor will be undergo rapid unplanned disassembly.",
		"¶¶===================¶Guide to Reactor Rods¶===================¶",
		"Empty Reactor Rod¶===================¶Does nothing when inside a reactor. Doesn't accept neutrons.",
		"Fuel Rod¶===================¶Fuel rods emit Neutrons onto themselves and adjacent reactor rods. They have several stats that vary based on the material of the rod and the coolant that is used in the reactor.",
		"The Self stat describes how many Neutrons the rod outputs onto its own slot.¶The Emission stat describes how many Neutrons it outputs onto each adjacent reactor rod.",
		"The Factor stat describes how many additional neutrons it will output onto adjacent reactor rods based on how many neutrons are on itself.",
		"A bigger Factor (1/3 > 1/32) means more additional neutrons are output per neutron on the fuel rod.¶The Maximum stat describes how many neutrons can be on this fuel rod before it will be losing duration faster.",
		"When over the maximum, the fuel rod will be losing duration at minimum four times faster, scaling linearly with the amount of neutrons over the maximum.",
		"Moderated fuel rods will make adjacent active fuel rods also moderated. Moderated fuel rods will deplete four times faster and can't put neutrons onto breeder rods.",
		"When the remaining duration of the fuel rod runs out, it will convert to a Depleted Fuel Rod.",
		"Depleted Fuel Rod¶===================¶Does nothing when inside a reactor. Doesn't accept neutrons. Can be centrifuged to obtain some fuel and some higher tier fission fuel back.",
		"Absorber Rod¶===================¶Absorber rods accept neutrons from adjacent fuel rods. Each neutron on an absorber rod gets converted to two HU instead of just one.",
		"Reflector Rod¶===================¶Return any neutrons put onto them directly to their source. They therefore will never have any neutron on them.",
		"Moderator Rod¶===================¶Like reflector rods they return neutrons directly back to their source, but the number of neutrons they return gets multiplied by the number of fuel rods that emit neutrons onto them.",
		"They therefore can reflect up to four times as many neutrons back and will never have any neutron on them. Moderator rods also moderate adjacent fuel rods,",
		"making them deplete four times faster and unable to put neutrons onto breeder rods. Moderated fuel rods will also make adjacent active fuel rods moderated.",
		"Breeder Rod¶===================¶Each tick, the number of neutrons on this rod get subtracted from the neutrons needed. If the needed neutrons reach zero, the rod will turn into an enriched rod.",
		"Breeder rods will not accept neutrons from moderated fuel rods. The loss stat gets subtracted from each number of neutrons emitted onto this rod,",
		"meaning the loss applies to the neutrons coming from any fuel rod emitting onto it and not just once for the breeder rod.",
		"¶¶===================¶Guide to Reactor Coolants¶===================¶",
		"Distilled Water¶===================¶Converts directly into steam, doesn't need a heat exchanger. 80 HU turns 1L of Distilled Water into 160L of Steam. Moderates any fuel rods inside. No fuel rod stat changes.",
		"Semi-heavy Water¶===================¶40 HU turns 1L of Semi-heavy Water into 1L of Hot Semi-heavy Water. Moderates any fuel rods inside. No fuel rod stat changes.",
		"Heavy Water¶===================¶50 HU turns 1L of Heavy Water into 1L of Hot Heavy Water. Moderates any fuel rods inside. Divides maximum stat of fuel rods by 8.",
		"Tritiated Water¶===================¶60 HU turns 1L of Tritiated Water into 1L of Hot Tritiated Water. Moderates any fuel rods inside. Divides maximum stat of fuel rods by 16.",
		"Industrial Coolant¶===================¶20 HU turns 1L of Industrial Coolant into 1L of Industrial Heatant. Multiplies self stat of fuel rods by 4.",
		"Multiplies emission stat of fuel rods by 4. Multiplies factor stat of fuel rods by 2.",
		"Carbon Dioxide¶===================¶20 HU turns 1L of Carbon Dioxide into 1L of Hot Carbon Dioxide. Multiplies self stat of fuel rods by 3.",
		"Helium¶===================¶30 HU turns 1L of Helium into 1L of Hot Helium. Divides emission stat of fuel rods by 2.",
		"Molten Lithium Chloride¶===================¶15 HU turns 1L of Molten Lithium Chloride into 1L of Hot Molten Lithium Chloride. Multiplies self stat of fuel rods by 5. Divides emission stat of fuel rods by 2. Increases maximum stat of fuel rods by 25%",
		"Molten Tin¶===================¶40 HU turns 1L of Molten Tin into 1L of Hot Molten Tin. Neutrons only convert only to a third of the HU. Decreases divisor of factor stat of fuel rods by 1.",
		"Molten Sodium¶===================¶30 HU turns 1L of Molten Sodium into 1L of Hot Molten Sodium. Neutrons only convert only to a sixth of the HU. Decreases divisor of factor stat of fuel rods by 1.",
		"Molten Thorium Salt¶===================¶2_560_000 HU turns 1L of Molten Thorium Salt into 1L of Molten Lithium Chloride. Sets self stat of fuel rods to 0. Divides emission stat of fuel rods by 2. Multiplies maximum stat of fuel rods by 4.",
		"¶¶===================¶Stable Reactor Design Guidelines¶===================¶",
		"Stable nuclear fission reactors, also commonly called subcritical reactors, are a design of fission reactor that have no critical fuel rods. This means that the neutron count and thus heat output doesn't constantly increase while the reactor is running.",
		"The advantages of stable reactors are:¶- No external control required¶- Stable neutron/HU output¶- Increased Safety¶- Easy to design¶- Function with any nuclear fuel",
		"Disadvantages of stable reactors are:¶- Not very fuel efficient¶- Only really usable as power generating reactors¶- Generally bigger than comparable critical reactors",
		"A reactor rod can be considered critical when the number of neutrons reflected back onto the rod times the factor of the rod is greater or equal to the emission of the fuel rod.",
		"This means that a rod is critical if the number of reflectors around it times the factor is greater or equal to 1. So a fuel rod with a factor of 1/3 needs at least 3 adjacent reflector rods to be critical.",
		"Therefore it is advised to use Industrial Coolant as a reactor coolant for stable reactors. Industrial Coolant decreases the factor of fuel rods, making it impossible for any fuel rod to go critical without moderator rods.",
		"It also increases the self and emission stat which is really advantageous in stable reactors, as it means more Neutrons and therefore more efficiency.",
		"You can generally adhere to these four rules to get your stable reactor to the best efficiency:¶If the fuel used has a factor lesser than 1/8, surrounding fuel rods with absorber rods yields the greatest HU output.",
		"If the fuel used has a factor of 1/8, surrounding your fuel rods with absorber, reflector or other fuel rods yields to the exact same HU output.",
		"If the fuel used has a factor greater than 1/8, surrounding your fuel rods with reflectors or other fuel rods yields the greatest HU output.",
		"Don't use water based coolant or moderator rods in stable reactors, as they massively decrease the duration and therefore efficiency of fuel rods by moderating them without any real benefit in stable reactor designs.",
		"¶¶===================¶Critical Reactor Design Guidelines¶===================¶",
		"Critical nuclear fission reactors, also commonly called supercritical reactors, are a design of fission reactor that have critical fuel rods. This means that the neutron count and thus heat output constantly increases while the reactor is running.",
		"The advantages of critical reactors are:¶- Greater efficiency¶- Completely configurable neutron output¶- Generally smaller than comparable stable reactors¶- Able to work as burner or breeder reactors",
		"Disadvantages of critical reactors are:¶- Need external control¶- Constantly fluctuation HU output¶- Harder to design¶- Decreased safety¶- Need fuel with higher factors",
		"A reactor rod can be considered critical when the number of neutrons reflected back onto the rod times the factor of the rod is greater or equal to the emission of the fuel rod.",
		"This means that a rod is critical if the number of reflectors around it times the factor is greater or equal to 1. So a fuel rod with a factor of 1/3 needs at least 3 adjacent reflector rods to be critical.",
		"Since the neutron count on the fuel rod will be indefinitely increasing, you need to control the reaction so you won't output more power than you can handle.",
		"Geiger counter sensors allow such control in an automatic fashion when combined with redstone machine switches or selectors.",
		"So when the neutron count gets too high you want to turn a reactor rod off to make the fuel rod subcritical again, making the neutron count shrink.",
		"For the greatest efficiency you want your neutron count on the fuel rod stay beneath the maximum stat of that fuel rod at all times, while coming as close to it as you can.",
		"You'd also want the neutron count to shrink as little as possible when controlling the reactor, so your average HU output stays as high as possible.",
		"Therefore you need a fuel with a factor of at least 1/4 to be able to build a critical reactor without the use of moderator rods.",
		"The easiest to acquire fuel that fulfills this condition is Uranium 235. You can get it by processing Uraninite. When using moderator rods the fuel can have a factor down to 1/16, so using Uranium 238 is possible.",
		"¶¶===================¶Burner Reactor Design Guidelines¶===================¶",
		"Burner Reactors are a type of nuclear fission design, with the purpose of burning through fuel rods as quickly as possible, allowing the depleted fuel rods to be processed for better nuclear fuel materials.",
		"There are two ways to make fuel rod depletion significantly quicker:¶- Moderating the fuel rod¶- Running above the neutron maximum",
		"A fuel rod can be moderated by either by having it placed in reactor cooled with Distilled/Semi-Heavy/Heavy/Tritiated Water or placing it next to a moderator rod or moderated fuel rod. When moderated, the fuel rod will deplete four times faster.",
		"When having a neutron count greater than the maximum stat of the fuel rod on the fuel rod, it will also deplete four times faster, stacking with moderation to 16 times faster.",
		"The more you go over the maximum, the quicker the fuel rod will deplete, having a neutron count twice as high as the maximum on the rod will make it deplete twice as fast yet again, which would be stacking with moderation to 32 times faster depletion.",
		"Neutron counts 17 times the maximum would therefore make the fuel rod deplete 17 times faster times, times 4 because of going over the maximum,",
		"so 68 times faster, times 4 if the rod is moderated, so 272 times faster than an unmoderated rod with neutron counts below the neutron maximum.",
		"Instead of significantly increasing the neutron count on the fuel rod, it is also possible to lower the neutron maximum to take advantage of this effect.",
		"This can be easily done by using either Heavy or Tritiated Water, which lower the maximum stat by 8 and 16 times respectively and additionally also moderate all fuel rods because they are water based.",
		"To achieve really high neutron counts, it is advisable to utilize a critical reactor design, though it is also feasible to run a burner reactor as a stable design.",
		"Cobalt 60, while being almost useless as a reactor fuel itself, is really easy to burn into Thorium, which is much more useful.",
		"¶¶===================¶Breeder Reactor Design Guidelines¶===================¶",
		"Breeder reactors have the goal of turning breeder rods into enriched rods. They allow getting really good nuclear fuel from lesser, more abundant fuels, like Thorium and Uranium 238.",
		"However while similar to burner reactors, they are much harder to build, requiring a non-moderated, critical reactor design. Breeder rods have a stat called loss. It describes how many neutrons are subtracted from any amount of neutrons put onto it.",
		"So when you have a breeder rod with a loss of 500 neutrons and try to emit 300 neutrons onto it from a single adjacent fuel rod, the breeder rod receive no neutrons, which means that the breeding process won't be advanced.",
		"When emitting 300 neutrons from another adjacent fuel rod onto the same breeder rod, now essentially emitting 300 neutrons twice onto it, there will still be no neutrons on the breeder rod, as the loss is applied to each amount of neutrons.",
		"This means that high neutron outputs from a single fuel rod are required, which make it infeasible to run a non-critical reactor design as a breeder.",
		"Emitting 900 neutrons onto the same breeder rod with a loss of 500 neutrons would mean that 400 neutrons would end up on the breeder rod, meaning that they would be added to the process of turning into an enriched rod each tick.",
		"Another fuel rod emitting 900 neutrons onto the same breeder rod, so essentially 900 neutrons twice, would therefore mean 800 neutrons on the breeder rod.",
		"Since the breeder rod essentially needs to be adjacent to a critical fuel rod, the fuel rod only has three sides remaining for reflectors, which means it can only go critical if its factor is 1/3 or greater.",
		"Moderator rods are obviously not usable here, since breeder rods can't accept neutrons from moderated fuel rods. This means either fuels with a higher factor like Plutonium are required or a coolant that raises the factor needs to be used.",
		"Molten metal coolants like Molten Tin or Molten Sodium are therefore best for breeder reactors, since they raise the factor of fuel rods inside,",
		"making breeding possible with more common fuel materials like Uranium 235 or allowing better fuel materials to breed more breeder rods at once.",
		"They also have the advantage of lowering the HU output of the neutrons, which means higher neutron counts are possible with less cooling.",
		"Emitting higher neutrons counts onto the breeder rods amounts to a quicker and also more efficient breeding process, as the neutron loss would be applied over a shorter time, resulting in fewer neutrons lost in total.",
		"¶¶===================¶Thorium Salt Reactor Design Guidelines¶===================¶",
		"Thorium Salt is a really special kind of coolant. In fact, it can't even be considered a coolant, as it doesn't get converted into an energy carrying liquid.",
		"Instead it can be seen more of as a fuel itself, since it turns into Molten Lithium Chloride, meaning that essentially the thorium inside the thorium salt gets used up as a fuel.",
		"Thorium salt also depletes really slowly into Molten Lithium Chloride, only for every 2_560_000 neutrons one liter of Thorium Salt gets turned into Molten Lithium Chloride.",
		"The main advantage of using Thorium Salt is how it effects the stats of any fuel rod inside, massively boosting them.",
		"However, since power can't be extracted from the Thorium Salt, since it doesn't turn into an energy carrying liquid, you can't simply use Thorium Salt alone in any reactor design.",
		"A reactor design utilizing Thorium Salt needs to be using at least one other, additional coolants instead.",
		"The reactor core filled with Thorium Salt would house the fuel rods,  while adjacent reactor cores filled with another coolant would capture the neutrons emitted from that reactor with neutron absorber rods.",
		"Since the Thorium Salt main advantage is how it massively raises the maximum stat of fuel rods inside, it generally only really makes sense as a critical reactor design.",
		"Critical Thorium Salt reactors are the most efficient power generating reactors possible, but also among the hardest to build.",
		"¶¶===================¶Advanced Fission Reactors¶===================¶",
		"This chapter will give you some tips and the mathematical formulas to build the most advanced and efficient reactors possible.",
		"Calculating the neutron emission¶===================¶A fuel rod will emit more neutrons onto its neighbors when it has a higher neutron count on it.",
		"The exact amount of neutrons any fuel rod would emit onto one adjacent neighbor can be calculated like this:¶¶e_n = e + ((n – s) * f)",
		"e_n: Neutrons emitted onto neighbor¶e: Emission stat of the fuel rod¶n: Neutron count on the fuel rod¶s: Self stat of the fuel rod¶f: Factor of the fuel rod",
		"Calculating the depletion rate of a fuel rod¶===================¶A fuel rod usually has a depletion rate of one and will therefore last as long as the time on the tooltip suggests.",
		"However, when having a greater amount neutrons on the fuel rod than the neutron maximum, the depletion rates gets multiplied by this factor:¶¶f = 4 * n / m",
		"f: Depletion rate multiplier¶n: Neutron count on the fuel rod¶m: Maximum stat of the fuel rod",
		"Furthermore, having the fuel rod moderated will additionally multiply its depletion rate by a factor of 4. Divide the time remaining stat of the fuel rod by the calculated depletion rate to get the real time until depletion.",
		"Tips and Tricks¶===================¶You can build reactor designs using multiple coolants. For critical reactors using moderator rods it is smarter to control one adjacent fuel rod,",
		"rather than the moderator rod itself to control the neutron counts. Robot arms allow for precise automation of reactor cores.¶Filters can be used for easier automation of burner and breeder reactors.",
		"A higher self stat is bad in critical reactor designs, as contributes to the maximum without effecting the neutron emission.¶The emission stat is mostly irrelevant for efficiency in critical reactor designs.",
		"Setting the geiger counter sensor to hexadecimal mode is useful when trying to control neutron counts greater than 9_999. In hexadecimal mode you can compare up to 65_535 neutrons."
		});
		
		//-----
		
		for (int i = 1; i < OreDictMaterial.MATERIAL_ARRAY.length; i++) {
			if (OreDictMaterial.MATERIAL_ARRAY[i] != null && OreDictMaterial.MATERIAL_ARRAY[i].mID == i && (!OreDictMaterial.MATERIAL_ARRAY[i].mHidden || OreDictMaterial.MATERIAL_ARRAY[i].mDescription != null)) {
				ItemStack tStack = UT.Books.addMaterialDictionary(OreDictMaterial.MATERIAL_ARRAY[i]);
				
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.gem               .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.dust              .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.ingot             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plate             .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.plateGem          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.crushedCentrifuged.dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.bucket            .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
				CR.shaped(tStack, CR.DEF_NCC, "lX ", "XBX", " X ", 'B', ST.make(Items.writable_book, 1, W), 'X', OP.chemtube          .dat(OreDictMaterial.MATERIAL_ARRAY[i]));
			}
		}
	}
}
