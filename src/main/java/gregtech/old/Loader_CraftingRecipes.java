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

package gregtech.old;

import static gregapi.data.CS.*;
import static gregapi.util.CR.*;

import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_CraftingRecipes implements Runnable {
	@Override
	public void run() {
		/*
		ItemStack tStack;
		
		tStack = GT_Utility.getWrittenBook("GregTech Manual I (Edition 5)", "Gregorius Techneticies", new String[] {
				  "So, this is probably your first time using a Product of GregTech Intergalactical, so you might ask yourself where to begin with? This World is very complex, and you may want to aquire a few basic Resources first."
				, "You need many Resources to start with, as there are: Wood, Sticky Resin or Slimeballs, Sand, Clay, Silk, Food, Iron, Copper, Tin, Flint, Redstone, Coal (or another good Fuel for the Oven) and a bunch of Cobblestones would also be very useful."
				, "All Resources aquired? Good, then cook your Ores, Food and Slimeballs in your Stone Oven, and build a Bed so that you can rest. Then you might need a Mortar for the production of Bronze Dust. Bronze is much better than wasting Iron."
				, "So, now that you have a set of Tools, you can begin to make an Iron Oven out of your old Stone Oven. Smelting Sticky Resin into Rubber is no longer possible, you need to extract the Rubber from the Resin and then smelt the resulting Rubber Pulp."
				, "You might noticed that you need Tools to make Plates from Ingots, and to sharpen Edges, in order to make most of the Metal Items. So craft yourself a Hammer from 6 Ingots and a stick, and then make 2 Plates and put them on a stick to create a File."
				, "This File is used to create Tools, which need a sharp Edge on a Side, like Swords, Pickaxes, Shovels, Axes and Hoes. The Metal Tools are all crafted in said way. Only Tools made of Gems, or the Wood and Stone Tools don't need this to be crafted."
				, "You can ofcourse use Tools made of Flint for now, what saves you a lot of Metal in the beginning. I would recommend you to use two Plates to make a Saw, so you can increase the yield of Planks and Sticks you get from a Log of Wood."
				, "Now you have to get some Bronze going, before you can make any Machine. So either grab yourself a Mortar and make your Copper and Tin Ingots into Dust, or use the Hammer to crush Copper, Tin, Tetrahedrite or Cassiterite Ore into Dust."
				, "Mix these Dusts at a 3-Copper to 1-Tin ratio and you will get Bronze. Tetrahedrite is not that pure, so I would recommend you to smelt it into Copper before using it. Smelt your Bronze Dust into Bronze and we can finally begin to make a Machine."
				, "The Boiler is a simple Machine, which makes Steam out of Water, what will then power your other basic Machines. You need a Wrench to assemble most Machines, what is another 6 Ingots in a 'Y' Shape. The Boiler needs Water, so you need to get some."
				, "It accepts only Coal, Charcoal and Coal Coke as Fuel. Coal Blocks will not fit into the Boiler! In case you somehow found Steel somewhere, you should use it instead of Bronze to craft the Boiler. But now we need a Steam consumer first."
				, "There are Steam powered Versions of Compressor, Extractor, Furnace and Alloy Smelter, as well as a Sturdy Grinder (increases Ore yield slightly) and a Steam powered Forge Hammer (Hammer without durability problems). Build your Workshop with them."
				, "The Steam Machines have a special Steam Output, that Output can be turned using a Wrench. Never obstruct that Output with ANYTHING, or the Machine will refuse to work! And also never stand right in front of that Output as hot Steam comes out of it."
				, "Running a Steam Pipe into said Output is the most common mistake people do, as that is an Output and not an Input. But now back to the Workshop: You need a ton of Steel, so you need to make a Bronze Blast Furnace. That needs 100 Bronze Plates."
				, "You need 32 Bronze Plated Bricks, each of them needs 3 Bronze Plates and 1 Brick Block, and then you need the 1 Block where you insert the Iron and the Coal (or Coal Coke) to be smolten into Steel, what needs 4 Plates and 4 Brick Blocks more."
				, "One of these Blast Furnii (The official plural of Furnace) might not be enough. You should build another one right next to it. Since you can share the Outer walls of the Blast Furnii you need only 64 more Bronze Plates for the second one."
				, "Once you got enough Steel you can finally begin to make electric Machines. All that took you a few Days I guess, but you have finally reached the electric Tier, and you can be proud of it, that you got so far."
				, "A very big Note: The Bronze Blast Furnace does NOT need Steam Power. Even though it should be very obvious, since it is Coal powered and Pipes don't even connect to it, I needed to mention this, because some people actually tried that."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.BONUS_CHEST               , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.DUNGEON_CHEST             , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING       , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  20));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("GregTech Manual II", "Gregorius Techneticies", new String[] {
				  "You have your Basic Workshop and want to do amazing things with it? You wan't to build your first Fusion Reactor? Well, this is the wrong Book for that, but we are working on it. Before that we definetly need better ways of making Circuitry."
				, "The Assembling Machine is perfectly suited for that. It can create Circuitry much cheaper than a normal Steve could do. If you don't have the needed Materials then go mining, and come back when you have crafted the Assembling Machine."
				, "This Device cuts down your Copper Cable Costs and gives you the possibility to use more advanced Materials for Circuitry. The Assembler might need some time for that, but it's the quality which counts. Well, and you get more quantity."
				, "You got it? Now build a Wiremill to make more Cables from Copper than you usually do by handcrafting. This increases your efficiency in producing Machines extremly. And people still complain about being too expensive..."
				, "And now we can go to some of the advanced Functions of these Devices. They can automatically output their Content to adjacent Inventories, but not only that, they can also output their contained Energy to adjacent Machines!"
				, "you just need to wrench the output Facing into the correct direction, and keep the first two Buttons inside the GUI enabled. You can also upgrade Macerator, Extractor, Compressor and E-Furnace, using a Conveyor Module to get these abilities."
				, "Now we go for the Alloy Smelter. Have you been tired of macerating, crafting and smelting Metals to get Alloys? This Machine is the perfect way to get Alloys in just one step! Just put your Ingots into it, and it will smelt them together."
				, "The Printing Factory, is a perfect Way of printing your Papers. This Book was printed using such a Machine. It can not only copy Books or create Maps, it can also make Paper from Reeds or Wood Pulp, and is also capable of Coloring your Wool!"
				, "Also interessting are Industrial Electrolyzer and Industrial Centrifuge. They are capable of seperating the components of several Dusts or Liquids. The Centrifuge can work with pumped Lava to produce several nice Metals from it."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.BONUS_CHEST               , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.DUNGEON_CHEST             , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING       , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  15));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("GregTech Machine Safety (Edition 2)", "Gregorius Techneticies", new String[] {
				  "As you probably know, every electric Machine can be quite fragile under certain circumstances. This Manual explains why your Machines could explode or burn down your Workshop, and how to prevent it."
				, "1. Do not use a Pickaxe or something else, what is not a Wrench, to dismantle an electric Machine. This will cause either an Explosion or a just broken Machine if you are very lucky."
				, "2. Keep your Machines away from any explosives! Every electric Machine will cause a chain reaction of Explosions when being broken like that."
				, "3. Keep your Machines away from Fire. The are inflammable and will explode, if exposed too long to adjacent Fires."
				, "4. Do not place your Machines outside. They must have a Roof or Rain and Thunder Storms will set them on Fire. A Cover ontop and on the Sides of an electric Machine is sufficient to prevent that, so your Solar Panels are automatically safe."
				, "5. Make sure, that the Maximum Input Voltage of a Machine is not exceeded. That will cause an immidiate Explosion."
				, "6. Do not let your Machines explode. If that happens, the Machine will overcharge the adjacent Wiring, what will cause all connected Machines to explode, unless they can receive 8192EU/p."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.BONUS_CHEST               , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING       , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  20));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Cover up!", "Gregorius Techneticies", new String[] {
				  "Have you ever wondered how the Cover System works? This Book is about how to Cover your Machines up properly. But what does a Cover do, except fancy looking? And how to install Covers? And what If you want to remove the Cover?"
				, "Usually every regular Plate like Advanced Alloy, Iron, Lapis or Bronze Plates can be used as Cover. Even Covers of other Companies can be supported as well. To place a Cover, just get one and rightclick the Machine with it."
				, "Covers can do many things such as improving Machines (see Upgrade Dictionary for Details), but their main purpose is preventing interactions between adjacent Blocks."
				, "To remove a Cover, you need a Blue Crowbar or something similar, like a Red Crowbar for example. Just use the Crowbar on it, to get it back. If you want to adjust Upgrade Covers, you need a Screwdriver. Just screw around until you are set."
				, "If you are low on Materials, but really need a Cover, then just take your Screwdriver and unscrew the outer hull of the Machine, turn it around, screw it back on and viola, you got a Cover for free."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Greg-OS Manual I", "Gregorius Techneticies", new String[] {
				  "Redstone Circuits are the main Component of a well running Base. The GregTech Redstone Circuit Block is ideal for processing Logic. It can compute everything! From a simple and consistent Timer to the Combo Lock on your Door!"
				, "It has a very simple interface. You just need to push as few Buttons, and you are good to go. Just don't forget to push the Start-Button right under the Energy Flow Button, after you adjusted your Configuration."
				, "Every Redstone Circuit has a diffrent Energy consumption. Yes, running this Device needs Energy, as indicated by the small blinking Lightning Bolt on the Screen. The needed Energy is just one Energy Unit per outputted Redstone Pulse."
				, "To switch between diffrent Redstone Circuits, just push the Button under the start Button. It looks like two sidewards pointing Arrows. The Lightning Bolt Button causes the Machine to output its Energy to the Block on its output Side."
				, "The four Buttons on the Left are controlling the System Parameters of the Redstone Circuit. You only need to right/leftclick them to adjust the Value. If you have an ItemStack in your Hand, then it copies a special Value into the selected Parameter."
				, "If you have something like an AND-Gate, and need to limit the Inputs for that, just place a Redstone preventing Cover on that Side. Any Cover will work, but since you need to power the Block from one Side you should use a special Cover for that."
				, "Said special Cover is the Redstone Preventor Cover, which can be attached by clicking twice with a Screwdriver on a Machine. That Cover looks Red and lets everything go through except Redstone Signals."
				, "It is always a good Idea to leave one Meter space between Circuits (that doesn't loose RS Strength), unless you directly connect the Output of a Circuit Block to the Input of another."
				, "The Player Detector, also called Player Proximity Sensor, is operating on a similar OS. The Strength of its Output Signal depends on the distance to the Configured Player, It allows you even to scan a piece of written Paper to select the target."
				, "The Redstone Display works also with said Operating System. It is specialized on showing you the incoming Redstone Signals. If you want to change its Format, just use a Screwdriver on its front, you can also 'paint' it with a Color of choice."
				, "The Redstone Controlled Lamp, which can also be painted, is glowing depending on the Strength of the incoming RS Signal. To make it constantly glow, just attach a Redstone Signalizer or a Redstone Torch to it."
				, "The Button Panel is the best choice of Inputting Redstone Signals. Depending on which Button you press it outputs a Signal from 0-15. With a Controller Cover, or a Rubber Hammer, you can make the Buttons sticky."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   3));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   5));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  20));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Greg-OS Manual II", "Gregorius Techneticies", new String[] {
				  "List of Logic Circuits"
				, "Basic Logic, Part I: This contains many simple Logic Gates. The first Six are basic Logic Gates. If you want to block any Inputs from it, then just screw a Redstone blocking Cover on said Side. These Gates take Input from all uncovered Sides."
				, "Basic Logic, Part II: Number Seven does 15 minus strongest Redstone. The following 6 Bit Gates are outputting what the binary Operators do. They take the strongest and the weakest Redstone Signal and compare them. Make sure to cover all unused Sides!"
				, "Basic Logic, Part III: The 14th Gate inverts the strongest incoming Restone Signal, using the Bitwise Inversion Operator '~'"
				, "BitAND: This is a special hardcoded BitAND. It does what a normal BitAND does and checks if the Output doesn't equal 0, to give a boolean Output. Very useful in combination with the Button Panel."
				, "Equals: Checks if the strongest Redstone Signal is equal to the set Value. Can also be inverted to check if it is unequal."
				, "Combination Lock: You need to apply 1 - 4 Redstone Signals of a certain Strength in a row to let this Pulse once. You can attach a Button Panel to this to get a Lock for your Door."
				, "Pulse Limiter: This is a Pulse Limiter, which outputs one Pulse regardless how long the Input Signal is. Also the output Pulse Length can be configured."
				, "Randomizer: Outputs Random Redstone Signals. The Interval to determine a new Random Number can be configured."
				, "Redstone Meter: This checks if a Signal is larger than the lower boundary and lower than the upper boundary. The Output can be Inverted."
				, "Repeater: This does what a normal Repeater does. It just delays an incoming Redstone Signal."
				, "Timer: This Gate is very useful. It pulses every X Ticks, and unlike it's extremly crappy Counterpart of Redpower it is CONSISTENT. It doesn't just loose its Timing, as soon as someone uses a Bed or Logs out. Also much more things can be configured."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   3));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   5));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  20));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Upgrade Dictionary V4.04p", "Gregorius Techneticies", new String[] {
				  "This Book lists all Items, which can be used as special Upgrade for GregTech Machines. As Books are distributed via Worldgen, this Book can be outdated on older Maps (see Version Number on the Title)."
				, "Reactor Vents of any kind can be used to increase the efficiency of all processing Machines. It only works if the Cover is not obstructed by a solid block. All Objects without Collision Box are not obstructing it (Torches, Buttons, Levers etc.)"
				, "The Drain can be used to extract Liquid Blocks right in front of it into the Block it is attached to. If it is attached on the top side of the Machine, it is also able to collect Rain Water. It doesnt work pointing downwards for obvious Reasons."
				, "A Solar Panel Cover does what a normal Solar Panel does, it powers the Machine with Solar Energy, when Sun is shining. This works only if the Panel is attached to the upwards pointing face of the Block, as the Sunlight shines straight downwards."
				, "Computer Screens have the spcial ability to make only the GUI (or other rightclick action) of a Block accessible. Anything else gets Blocked. It also looks good, if you want to hide your Machines without having a Crowbar in your hand."
				, "Valves are ideal for transferring Liquids between Machines. They transfer 1 Bucket per tick! The best pipes can only do 80 Millibuckets per tick! If the Machine with the Valve can consume Energy, then it will consume 10EU per Bucket."
				, "Conveyor Modules can transport Items from one Machine to another. Just attach them on the right side and they will pump out Items at 2EU per Item, if the Machine can consume Energy at all."
				, "The Liquid, Item, Energy and Progress Meters, are emitting Redstone depending on the amount of their respective measurement inside the attached Machine. The Energy Meter uses the largest of the 3 Capacities, so it also works for Steam/MJ Machines."
				, "Work Controller Covers are controlling if the Machine is allowed to work or not, depending on injected Redstone. These Covers can be used for almost every Machine, even for the Button Panel, as Key locking Method."
				, "Overclocker Upgrades, as you know them from Industrial Corp, can be used to speed up Progress in GregTech Machines as well, but more efficiently. Every Overclocker doubles the Speed but also quadruples the required Voltage."
				, "Transformer Upgrades are used to increase th Voltage which is being handled by a Machine in- and output wise. Also, in case of output, it splits the EU-Packets to four. Higher Voltages such as 2048 and 8192EU/p need an HV-Transformer Upgrade."
				, "Battery Upgrades of various kinds are increasing the Energy Storage of a Machine. Some Battery Upgrades, like the Lapotronic Energy Orb Upgrade, require a certain Voltage Tier for the Machine to be attached."
				, "The Pneumatic Generator Upgrade is capable of letting Machines consume Kinetic Energy from Engines, also called MJ (Michael Jacksons). This Upgrade does NOT let you output any of this Energy in form of Electricity!"
				, "Redstone Energy Cell Upgrades just let you store more kinetic Energy inside your Machine, if a Pneumatic Generator is installed."
				, "Heating Coils are capable of upgrading the Efficiency of all Furnace alike Devices, including the electric Blast Furnace, when using 4 Coils at once. Note, that you need to install the Coils in a certain order for it to work."
				, "Steam Engine Upgrades, let your Machines work on Steam directly. 2 Steam = 1 EU. Very useful if you have a Boiler running. If there is no Mod with Steam Engine, then you have to use a Gas Turbine in the Assembling Recipe."
				, "Steam Tank Upgrades just increase the amount of Steam the Machine can buffer by 64 Buckets (32000 EU)."
				, "Wireless Transmitter and Receiver are sending/receiving Redstone Signals Wireless and cross dimensional. A little warning: Chunk reloading causes it to not be stable for a second. Screwdriver for Frequency change, by clicking on the 4 Screws."
				, "Redstone Signalizers apply a constant Redstone Signal to a Side of a Machine. Signal can be changed via Screwdriver."
				, "Redstone Conductors take the applied Redstone Signal of a Machine and emit it on their Facing."
				, "Lock Upgrade. This makes a Machine private and unbreakable (except for explosions). Only the one who applied the Upgrade can interact with the Machine."
				, "Shutter Cover. This Cover blocks the Inventory/Tank/Redstone Side it is connected to, if a Controller Cover (required!) disables the Work Status of the Block it is connected to. This is very useful in conjunction with Item Pipes and Fluid Pipes ..."
				, " ... the Shutter can also be used to make Pipe Connections One-Way, when being set in the third or fourth Mode to allow only Input or only Output."
			});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,   5));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Crop Dictionary V3.03a", "Mr. Kenny", new String[] {
				  "This is a List of all Crops I stumbled upon my tests. Some mutations of them had quite dangerous properties, which almost killed me, so be aware and don't use experimental WeedEx, like I did."
				, "The generic Crops, like Pumpkins, Melons, Wheat, Sugar Canes, Cacti, Carrots, Potatoes, Nether Wart, Roses, Dandelions, Cocoa, Brown Mushooms and Red Mushrooms are not being further described here as they are commonly known."
				, "Weed. The most dangerous Plant. It grows on empty Crop Sticks, which are very likely used for cross breeding. This Plant will spread over to other non-Weed Plants and assimilate them in a bad way. Use WeedEx on empty sticks to protect them."
				, "Warning: Highly advanced Crops (fast growing ones) can behave like Weed but are immune to it as well. So you might want to make sure that your good Plants are seperated from nonresistent Plants."
				, "Hops. A plant primary used to brew Beer. Nothing much to say about it."
				, "Coffee. Well, it grows Coffee Beans, which can then be blended into Coffeee Powder to make Coffee."
				, "Indigo. This Plant is giving you Indigo Blossoms, which can either be used for Blue Dye or Plant Balls. Four of these can also be used to plant new Indigo Plants. They are not Redpower compatible."
				, "Flax. A Plant to give you just Strings. You can easyly get it via cross breeding. It is also not Redpower compatible."
				, "Stick Reed. That Reeds are sticky. Perfect for making Rubber from them."
				, "Terra Wart. It is like edible Milk. It cures all Poisons."
				, "Red Wheat. It's Red, it's Wheat, it drops Redstone Dust."
				, "Trollweed, uhh I mean Ferru. Grows Iron. Yes, real Iron. Only condition for that is having an Iron Ore Block under the tilled Farmland."
				, "Coppon. Cotton Candy, I mean Cotton Copper, is an easy way to get Copper from Plants."
				, "Tine. Name comes from Tin and Pine. This tiny Tree drops Tin Nuggets, yay. Perfect for Tinpinchers."
				, "Plumbilia. This Heavy Metal Plant seems to like Music. It drops very dense Lead Nuggets, instead of the Bass."
				, "Argentia. Silver. Well, at least it's something."
				, "Aurelia. GOLD!!! It looks like trollweed, but instead of Iron it is Gold. Same for the Ore Block under it."
				, "Oil Berries. These Vine alike Crops are dropping Oil Berries. Four Oil Berries can be put into a Cell to get an Oil Cell."
				, "Bobsyeruncleranks. Because Bob is your Uncle. Drops Emeralds and Emerald Dust."
				, "Withereed. Drops Coal. Real Coal, not that cheap Charcoal."
				, "Blazereed. I think a Blaze Spawner is better than this."
				, "Diareed. A Direwolfs way to get Diremonds."
				, "Eggplant. Seems that there is a Chicken hiding somewhere in your Field."
				, "Corium. This Leathery Plant lets you safe the Life of a Million innocent Cows, what practically ruins the Environment, due to the Methane output of these not so innocent Animals."
				, "Corpseplant. Plants vs. Zombies went a bit wrong. Maybe this Zombie Plant wants to infiltrate your Garden."
				, "Creeperweed. General Spaz experimented with Crops as well, to finally get rid of Etho. This Plant made it possible for him, to gather TNT without killing his good Friends."
				, "Enderbloom. Seems the Ender Dragoon, made it finally into cross breeding, and this Ender Flower is what he got."
				, "Meatrose. This is how to get Meat the beautiful Way, without needing to slaughter harmless Animals."
				, "Milkwart. Use a Bucket to harvest this Plant."
				, "Slimeplant. So it's a Tree, and it grows Slimeballs. A very bouncy Plant."
				, "Spidernip. Cats looo ...wait, this isn't Catnip... , Spiders looooove this Plant. They just can't stop making their Webs in there."
				, "Tearstalks. This Plant drops the probably rarest Item of all. Ghast Tears. These Tears are extremly rare, I guess even more rare than Nether Stars as they can't just be farmed, well, until now."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   3));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST  , GT_Utility.copy(tStack)                           , 1, 1,   5));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("GregTech Energy Systems V3.04b", "Gregorius Techneticies", new String[] {
				  "This Book explains Core Functionality of GregTech Machines. You might not understand it, but it can help you in some Cases."
				, "As you know, GregTech Machines can run on 4 diffrent Energy Systems. Energy Units and Universal Electricity are natively compatible without Upgrades and are stored in the Electric Capacity. MJ and Steam need Upgrades and have seperate Capacities."
				, "All those 3 Capacities store their Energy in Universal Energy Units. These Energy Units are basically the converted Version of MJ, Steam and UE into the EU-System. They are stored in 3 Diffrent Capacities (UE and EU share as they are both Electricity)"
				, "If a Machine now needs Energy, it first checks the Universal Capacity of the MJ-Units and tries to consume them. If that fails it checks the Capacity of Steam and tries to consume that. If they both failed, then it uses the Capacity for Electricity."
				, "As Machines usually check, if they even have enough Energy, before starting to work, they always check the one capacity, which has the largest amount of Energy. That includes the Energy Meter Cover, so you measure the largest Potential of the Machine."
				, "Almost every Cover Upgrade, Generator or similar Device produces Electricity. Electricity is the only Energy, which can be outputted by GregTech Machines. Everything else will be stuck inside the Machine until it gets consumed."
				, "In case of Steam, the Machine will be able to output it, but only if it is inside the regular Tank (when I happen to add Steam based Solar for example). The internal Steam Energy Tank can't output Steam."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.BONUS_CHEST               , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Microwave Oven Manual", "Kitchen Industries", new String[] {
				  "You just got a Microwave Oven and asked yourself 'why do I even need it?'. It's simple, the Microwave can cook for just 100 EU and at an insane speed. Not even a normal E-furnace can do it that fast and cheap!"
				, "This is the cheapest and fastest way to cook for you. That is why the Microwave Oven can be found in almost every Kitchen (see www.youwannabuyakitchen.ly)."
				, "Long time exposure to Microwaves can cause Cancer, but we doubt Steve lives long enough to die because of that."
				, "Do not insert any Metals. It might result in an Explosion."
				, "Do not dry Animals with it. It will result in a Hot Dog, no matter which Animal you put into it."
				, "Do not insert inflammable Objects. The Oven will catch on Fire."
				, "Do not insert Eggs. Just don't."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Turbine Manual", "Gregorius Techneticies", new String[] {
				  "This Book explains how to set up and run your advanced Gas or Steam Turbine. We are not responsible for any Damage done by this Book itself nor its content."
				, "First you need to craft the following things for a normal Turbine to Function: The Main Turbine Block, 28 Machine Casings (Gas = Reinforced, Steam = Standard), a Dynamo Hatch, an Input Hatch, an Output Hatch, a Turbine Rotor, "
				, "a bunch of different Tools, in case of the Gas Turbine (not needed for Steam Turbine) a Muffler Hatch, as every Machine, which burns things causes some unwanted Gases and finally a Maintenance Hatch"
				, "To begin the building, lay out the first 3x4 layer of Machine Casings on the ground, then place the Turbine Block facing outward in the middle of one of the two 3m wide Sides. Now 3 of the Casings should look like Rotors."
				, "Now grab your Dynamo Hatch and place it on the opposite 3m wide Side also facing outwards. And now the four corners of the Machine need also a Machine Casing. You should have a 2x3 inbetween the two 'Walls' you just created left by now."
				, "So, now place a 3x4 of Machine Casings ontop, at the 3rd Layer. The Turbine should now look like connected. There are 4 Spots left, place the Input, Output, Muffler (or another Machine Casing) and the Maintenance Hatch in the Spots facing outwards."
				, "When accessing the Turbine Block, it should now stop telling you, that the structure is incomplete (bottom Line of that Screen). Now go with a bunch of different Tools (Metal Hammer, Rubber Hammer, Screwdriver, Wrench, Soldering Iron and Crowbar)"
				, "to the Maintenance Hatch and access it. After that you grab the 6 Tools and rightclick the Slot with each of them in your Hand in the Maintenance GUI. Note that you need Soldering Tin/Lead in your Inventory to use the Soldering Iron."
				, "The Main Block should now tell you that you need to use the Rubber Hammer on it to (re)activate the Machine. The Rubber Hammer can enable and disable Machines. The Machine disables itself after something important broke."
				, "And we are now finally able to grab our Turbine Rotor and place it into the Turbine Block. You can repair the Rotor by just using Hammer and Wrench on it on the Workbench. Note that, if the Rotor breaks, the Turbine will explode, so"
				, "you must look at the Rotor from time to time to repair it or it will break your Turbine into pieces! But that usually takes a few Days of ruinning to happen. 100 Durability ^= guaranteed 1.3888 Hours of not breaking."
				, "So, now for the Maintenance. After a few Hours of running nonstop, your Turbine will get small Problems, which don't prevent it from running, these Problems just decrease Efficiency. Every Problem listed on the Screen does -10% Efficiency."
				, "To fix these Problems, just go to the Maintenance Hatch and click with the problem corresponding Tool on the Slot to repair. If all six possible runtime Problems happen, the Machine will auto-shutdown no matter what. No Explosion, it's just stopping."
				, "The Steam Turbine will consume 1600 Liters of Steam per tick and outputs up to 800EU/t at 100% Efficiency. It will also output 10 Liters of Water for these 1600 Liters of Steam, so that closed Boiler cycles are possible."
				, "If Railcrafts Steam Turbine Rotors are used, they will have 15000 Durability (they loose 2 Durability points each cycle) and the same Efficiency as the regular Steel Rotor, but it can't be repaired without using additional Steel."
				, "Carbon Rotors are having a 125% Efficiency and very low durability. This means that Steam Turbines using these Carbon Rotors will output a whopping 1000EU/t. You can't repair Carbon Rotors at all, so it's much like 'burning' the Coal. And it's risky."
				, "A large Gas Turbine is worth about 42 small Gas Turbines, and as the Turbines get much less Efficient, when not having enough Fuel, you should consider making a large Factory for Methane/Steam."
				, "Input and Output Slots are fully optional, you can place multiple ones of them or even none on the Machine. A Machine without Input couldn't process any Recipes, while a Machine without Output just voids all outputted Items and Liquids."
				, "A Turbine needs to have a 3x3 of Air Blocks in front of it, to Function properly, keep that in mind."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Thermal Boiler Manual", "Gregorius Techneticies", new String[] {
				  "This Book explains how to set up and run your Thermal Boiler. We are not responsible for any Damage done by this Book itself nor its content."
				, "First you need to craft the following things for a Thermal Boiler to Function: The Main Boiler Block, 20 Reinforced Machine Casings, two Input Hatches, two Output Hatches, a bunch of different Tools and a Maintenance Hatch."
				, "To begin the building, lay out the first 3x3 layer of Machine Casings on the ground (with a Hatch in the Middle), then place the Boiler Block facing outward in the middle of one of the 3m wide Sides."
				, "Now grab 3 other Hatches and place them on the remaining three 3m wide Sides also facing outwards. And now the four corners of the Machine need also a Machine Casing. There should only be a Hole left in the middle of the Cube."
				, "So, now place a 3x3 of Machine Casings ontop, at the 3rd Layer with the last Hatch in the middle facing outwards as well."
				, "When accessing the Boiler Block, it should now stop telling you, that the structure is incomplete (bottom Line of that Screen). Now go with a bunch of different Tools (Metal Hammer, Rubber Hammer, Screwdriver, Wrench, Soldering Iron and Crowbar)"
				, "to the Maintenance Hatch and access it. After that you grab the 6 Tools and rightclick the Slot with each of them in your Hand in the Maintenance GUI. Note that you need Soldering Tin/Lead in your Inventory to use the Soldering Iron."
				, "The Main Block should now tell you that you need to use the Rubber Hammer on it to (re)activate the Machine. The Rubber Hammer can enable and disable Machines. The Machine disables itself after something important broke."
				, "If you want to use Lava with this Device, then you should add a Lava Filter to extract additional Resources from the Lava. If the Filter breaks, the Machine won't explode like a Turbine would. If you use molten Salt, then you won't need a Filter."
				, "You will get Obsidian when processing Lava, however if a Filter is used, you will get sometimes an Ingot instead of a Block of Obsidian. When using molten Salt, you will get the Salt back."
				, "So, now for the Maintenance. After a few Hours of running nonstop, your Boiler will get small Problems, which don't prevent it from running, these Problems just decrease Efficiency. Every Problem listed on the Screen does -10% Efficiency."
				, "To fix these Problems, just go to the Maintenance Hatch and click with the problem corresponding Tool on the Slot to repair. If all six possible runtime Problems happen, the Machine will auto-shutdown no matter what. No Explosion, it's just stopping."
				, "The Thermal Boiler will produce 800 Liters of Steam per tick for about 5 or 6 Liters of Water per tick at reaching 100% Efficiency. In case of Lava it consumes 1666 Liters every Second."
				, "A Thermal Boiler is worth about 33 small Thermal Generators, and as the Boilers get much less Efficient, when not having enough Fuel, you should consider making a large Nether Pump for Lava, or a good Nuclear Reactor for molten Salt."
				, "Input and Output Slots are fully optional, you can place multiple ones of them or even none on the Machine. A Machine without Input couldn't process any Recipes, while a Machine without Output just voids all outputted Items and Liquids."
				, "It might be useful to use the Screwdriver on the Output Hatches to determine what is outputted where. Another Hint: You need exactly 2 Thermal Boilers to run a Steam Turbine properly."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Your Friend the Pipe", "Mario", new String[] {
				  "It's me Mario, and I will explain you how to use Pipes."
				, "First we will talk about the various Fluid Pipes. They are simple and easy to understand. You first put a Pump Module onto the Input of the Pipe and set it to import Mode, to let it suck Fluids out of your Tank. Then we lay a Pipe to our Target."
				, "When the Target is connected the Fluid will flow into it. The Pipe will take a few seconds to get at full Speed. If you want to measure how much is going through the Pipe, then put an Active Machine Detector on it and place a Redstone Display at it."
				, "Now we can go to the Pneumatic Item Pipes. Their use is simple, you put an Item into a Pipe using the Output of a Machine, an importing Conveyor Cover, a Hopper or a Dropper, and the Item will search for the closest valid targets to go in."
				, "These Pipes have a Capacity of X Stacks per second, depending on the Material and the Size of the Pipe. When a Pipe tries to route an Item, the capacity of all which are closer to the destination gets used even if it is not in the same direction."
				, "This can lead into Pipes being stuck if there is no target, so that they all junk up, so make always sure the Pipes are large enough and that there is always a final target, if you have multiple Inputs."
				, "You can put a Conveyor Cover onto the Pipe to suck Items out of an Inventory, when using Import Mode. To measure how much of the Capactiy is being used, use the Active Machine Detector."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Geology Vol. 1", "That Yipee yelling Geologist", new String[] {
				  "In this Book I explain to you how to find Ores, and where to find Ores under normal circumstances. I don't guarantee you to find anything after you read this Book, but your chances should be higher after that."
				, "First of all you need the proper equipment. You should have at least one Prospectors Hammer with you to find out if there are Ores close by. Just hammer onto regular Rock and you will see if there are traces of whatever Ore."
				, "But not only that, you can also hear if there is something behind this Rock, like Water, Lava or a Cave. Or Silverfish if you are unfortunate. If you find traces of the Ore you are looking for you should Dig out the Area around you to find it."
				, "As soon as you found the Ore, take a Sign, paint a Picture of whatever Ore you found on it, place it down, yell Yipee and send your Boss a Letter about you finding the Ore. That's how I do it, and I did my Job very well."
				, "But now about where to find the Ores in the normal World. Some Ores can only be found in certain Areas, they behave as follows:"
				, "Coal Ore: You can find it almost everywhere, in fact it is everywhere."
				, "Iron Ore: This can only be found Underground below Ocean Level."
				, "Gold Ore: It is usually found below Y-Level 32 deep Underground."
				, "Redstone Ore: Is usually deep Underground below Y-Level 16, but there are tons of it."
				, "Diamond Ore: You find it very deep Underground, below Y-Level 16."
				, "Emerald Ore: A very rare Ore, which can only be found in Mountainous Areas and below Y-Level 32 in form of single Blocks."
				, "Galena Ore: This Silver and Lead containing Ore can be found anywhere below Y-Level 32 in large but rare Clusters."
				, "Iridium Ore: Extremly rare. You can find it anywhere, but it is so rare that you won't find much of it."
				, "Ruby Ore: Found only in very dry Areas, like Deserts and Wastelands for example. It is as common as Emerald Ore is in Mountainous Areas."
				, "Sapphire Ore: It is like Ruby Ore as common as Emerald Ore, but Sapphires are found under Oceans."
				, "Bauxite Ore: It can be found above Y-Level 32 in Forests, Plains and other Areas with 'normal' Temperature. You will never find any Bauxite at Diamond Layer."
				, "Tetrahedrite Ore: Found above Y-Level 32 in Mountainous Biomes, Jungles and Swamps. I would go for the surface Layers, as there is even more Ore above Y-Level 70, so Mountainous areas are perfect."
				, "Cassiterite Ore: Found above Y-Level 32 in Mountainous Biomes, Icy Biomes and Mushroom Biomes. I would go for the surface Layers, as there is even more Ore above Y-Level 70, so Mountainous areas are perfect."
				, "Nickel Ore: Between Y-Level 10 and 32 and can only be found in Mountainous Areas."
				, "Sphaletire Ore: This can only be found under the Lava Lakes at Bedrock Level, and then there is only a small chance that you actually find it there. I heard that you can find tons of it in the Nether."
				, "Black Granite: Very hard Rock, but also very valuable, as it contains few amounts of additional Iridium, Cooperite and Tungstate. Not to mention its Blast Resistance and cool Black look."
				, "Red Granite: Very hard Rock, and kind of valuable, as it contains traces of Sphalerite, Cinnabar and Tetrahedrite. Not to mention, that this is Granite as well."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST         , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR        , GT_Utility.copy(tStack)                           , 1, 1,   2));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                           , 1, 1,  10));
		tStack = null;
		}
		
		tStack = GT_Utility.getWrittenBook("Printer Manual", "Gregorius Techneticies", new String[] {
				  "This Manual explains the different Functionalities the GregTech Printing Factory has built in. And there are LOTS of things this Device can do, and none of them is Documented via NEI."
				, "Slot 1 is the left one (available from the Top Facing when seperated), Slot 2 is the one directly next to it (available from the Side Facings), Slot 3 and 4 are the Outputs, and Slot 5 also known as the Copy Slot is the one in the bottom middle."
				, "1. Paper making: You can autocraft sheets of Paper by inserting Wood Pulp, Paper Chad or Sugar Canes (at a 1:1 instead of a 3:3 Ratio) into the first Slot. It just costs between 100 and 200 EU and takes 5 to 10 Seconds per Sheet."
				, "2. Storage Block Iterating: You don't like the look of IC2's Block of Copper? TE has a much better design, but is uncraftable? This can turn your Copper (and other Materials) Block into the TE (or other Mods) one for just 20 EU per Block."
				, "3. Producing Name Tags: The Recipe for that is secret, but the Printer can create Name Tags for 800 EU at 2EU/t each."
				, "4. Crafting empty Maps: Just insert 8 Paper into Slot 1, and 1 Compass into Slot 2, and for just 800 EU you get an empty Map in 20 Seconds."
				, "5. Binding empty Books: For that you need just to insert 3 Paper and one Leather into the Printer and after 20 Seconds you get a Book from it for just 800 EU."
				, "6. Coloring Items and Blocks: You know those Crafting Recipes, which have a dye surrounded by 8 Blocks to dye them? Or the ones which have just one Block and one Dye in the Grid? Those two Recipe Types can easyly be automated using the Printer."
				, "7. Making your own Money I: Using the Coinage Mold in the Copy Slot and placing Industrial Credits into Slot 1, creates unique Credits, which cannot be created by anyone else on the Server. This makes Credits a very useful currency."
				, "7. Making your own Money II: The security of the Credits is supplied by the Mold, don't loose it, since it is the only thing which can produce YOUR Credits. By renaming the Mold with an Anvil, the Printer can also Auto-Rename the Credits."
				, "7. Making your own Money III: If you somehow stole the Mold from another Player, then copy the Mold using your Printer, and place the original back where you found it. That way you can create his/her Money with your own Printers."
				, "8. Copying Books: You have a Book and Quill, or a Written Book, and want to copy it? Just put the Book into the Copy Slot and insert an empty Book and some kind of Black Ink for copying it and that for just 200 EU."
				, "9. Naming Items: Tired of using an Anvil millions of times, just to rename a few Stacks of Items? Why not just renaming one Piece of Paper, and putting it into the Copy Slot for renaming the Items you have put into Slot 1? Just needs 400 EU!"
				, "10. Copying Maps: Just put an empty Map into Slot 1, some Black Ink into Slot 2 and the Map you wanna copy into the Copy Slot. That might be an Ink more expensive than just copying it by Hand, but in case you need it automated it is very useful."
				});
		if (tStack != null) {
		addLoot(ChestGenHooks.DUNGEON_CHEST             , GT_Utility.copy(tStack)                               , 1, 1,   2);
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY        , GT_Utility.copy(tStack)                               , 1, 1,  10);
		tStack = null;
		}
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		CR.shaped(ST.mkic("reactorVent", 1), DEF, "AIA", "I I", "AIA", 'I', ST.make(Blocks.iron_bars, 1, 0), 'A', OP.plate.dat(MT.Al));
		CR.shapeless(ST.mkic("reactorPlatingExplosive", 1), DEF, new Object[] {ST.mkic("reactorPlating", 1), OP.plate.dat(MT.Pb)});
		
		CR.shaped(ST.mkic("glassFiberCableItem", 1), DEF, "GGG", "EDE", "GGG", 'G', OD.blockGlassColorless, 'D', OP.dust.dat(MT.Ag), 'E', IL.IC2_Energium_Dust.get(1));
		
		CR.delate(ST.mkic("lapotronCrystal", 1));
		CR.shaped(ST.mkic("lapotronCrystal", 1), DEF, "LCL", "LSL", "LCL", 'C', OD_CIRCUITS[3], 'S', ST.mkic("energyCrystal", 1, W), 'L', OP.dust.dat(MT.Lazurite));
		CR.shaped(ST.mkic("lapotronCrystal", 1), DEF, "LCL", "LSL", "LCL", 'C', OD_CIRCUITS[3], 'S', ST.mkic("energyCrystal", 1, W), 'L', OP.dust.dat(MT.Lapis));
		/*
		CR.shaped(ST.makeIC2("luminator", 16), DEFAULT, "RTR", "GHG", "GGG", 'H', OP.cell.dat(MT.He), 'T', OP.ingot.dat(MT.Sn), 'R', OP.ingot.dat(ANY.Fe), 'G', ST.make(Blocks.glass, 1, 0));
		CR.shaped(ST.makeIC2("luminator", 16), DEFAULT, "RTR", "GHG", "GGG", 'H', OP.cell.dat(MT.Hg), 'T', OP.ingot.dat(MT.Sn), 'R', OP.ingot.dat(ANY.Fe), 'G', ST.make(Blocks.glass, 1, 0));
		*/
		
		OUT.println("GT_Mod: Applying harder Recipes for several Blocks.");
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "beryliumreflector", T)) {
			if (CR.remout(ST.mkic("reactorReflectorThick", 1))) CR.shaped(ST.mkic("reactorReflectorThick", 1), DEF, " N ", "NBN", " N ", 'B', OP.plate.dat(MT.Be), 'N', ST.mkic("reactorReflector", 1));
		}
		
		if (OP.gear.mat(MT.Diamond, 1) != null) {
			ItemStack tStack = CR.get(OP.gear.mat(MT.Fe, 1), ST.make(Items.redstone, 1, 0), OP.gear.mat(MT.Fe, 1), OP.gear.mat(MT.Au, 1), OP.gear.mat(MT.Fe, 1), OP.gear.mat(MT.Au, 1), OP.gear.mat(MT.Diamond, 1), ST.make(Items.diamond_pickaxe, 1, 0), OP.gear.mat(MT.Diamond, 1));
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "quarry", T)) {
				CR.delate(tStack);
				CR.shaped(tStack, DEF, "ICI", "GIG", "DPD", 'C', OD_CIRCUITS[3], 'D', OP.gear.dat(ANY.Diamond), 'G', OP.gear.dat(MT.Au), 'I', OP.gear.dat(ANY.Steel), 'P', ST.mkic("diamondDrill", 1, W));
			}
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "quarry", F)) {
				CR.delate(tStack);
			}
		}
		
		OUT.println("GT_Mod: Applying Recipes for Tools");
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "nanosaber", T)) {
			if (CR.remout(ST.mkic("nanoSaber", 1))) CR.shaped(ST.mkic("nanoSaber", 1), DEF, "PI ", "PI ", "CLC", 'L', OP.battery.dat(MT.Master), 'I', ST.mkic("iridiumPlate", 1), 'P', OP.plate.dat(MT.Pt), 'C', OD_CIRCUITS[5]);
		}
		
		if (CR.remout(ST.mkic("diamondDrill"   , 1))) CR.shaped(ST.mkic("diamondDrill"   , 1), DEF, " D ", "DMD", "TAT", 'M', ST.mkic("miningDrill", 1, W), 'D', OP.gem.dat(MT.DiamondIndustrial), 'T', OP.plate.dat(MT.Ti), 'A', OD_CIRCUITS[3]);
		if (CR.remout(ST.mkic("miningDrill"    , 1))) CR.shaped(ST.mkic("miningDrill"    , 1), DEF, " S ", "SCS", "SBS", 'C', OD_CIRCUITS[1], 'B', OP.battery.dat(MT.Basic), 'S', ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "electricsteeltools", T)?OP.plate.dat(MT.StainlessSteel):OP.plate.dat(ANY.Fe));
		if (CR.remout(ST.mkic("chainsaw"       , 1))) CR.shaped(ST.mkic("chainsaw"       , 1), DEF, "BS ", "SCS", " SS", 'C', OD_CIRCUITS[1], 'B', OP.battery.dat(MT.Basic), 'S', ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "electricsteeltools", T)?OP.plate.dat(MT.StainlessSteel):OP.plate.dat(ANY.Fe));
		if (CR.remout(ST.mkic("electricHoe"    , 1))) CR.shaped(ST.mkic("electricHoe"    , 1), DEF, "SS ", " C ", " B ", 'C', OD_CIRCUITS[1], 'B', OP.battery.dat(MT.Basic), 'S', ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "electricsteeltools", T)?OP.plate.dat(MT.StainlessSteel):OP.plate.dat(ANY.Fe));
		if (CR.remout(ST.mkic("electricTreetap", 1))) CR.shaped(ST.mkic("electricTreetap", 1), DEF, " B ", "SCS", "S  ", 'C', OD_CIRCUITS[1], 'B', OP.battery.dat(MT.Basic), 'S', ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "electricsteeltools", T)?OP.plate.dat(MT.StainlessSteel):OP.plate.dat(ANY.Fe));
	}
}
