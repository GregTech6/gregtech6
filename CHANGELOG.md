This is the most recent Changelog. It also contains some of the changes inside the >>>UPCOMING<<< Versions (most of the time). This is for 1.7.10 btw.


6.14.11: (Not released yet, released whenever it needs to be)
Nothing (I tend to only add finished Stuff to the Changelog).


6.14.10: (Not released yet, released whenever it needs to be)
Nothing (I tend to only add finished Stuff to the Changelog).


6.14.09: (Not released yet, released whenever it needs to be)
[NOTE] The Default Config Pack updated to disable Fastcrafts Chunk Culling, so that Ocean Floors are now rendering properly again. Set "enableCullingTweaks" to false in the "config/fastcraft.ini" File, if you experience this Issue.
[COMPAT] Aroma1997's Mining Dimension is now Compatible with my Worldgen.
[COMPAT] Et Futurum Requiem is now supported properly and up to date. Its Netherite Items and Ore Blocks are preferred over the ones of Netherite Plus. https://www.curseforge.com/minecraft/mc-mods/et-futurum-requiem/files
[FIXED] Biome dependent Stone LAyer Ores were not generating at all, due to me forgetting to Lowercase the Biome Names.
[FIXED] Infinite Worldgen Loop with Oceans, because I forgot to set the Block Update Flag to 2 during Worldgen, because ofcourse I left it to default when I fixed another Ocean related Bug...
[FIXED] Coal and Oil Shale Stone Layer Ore Blocks were flammable t othe point of burning up way too much. Now they are still flammable but Fire wont spread to them.
[FIXED] Railcraft Crowbars should now be able to remove Covers again, even if they dont use the overlay for technical reasons.
[FIXED] Applied Energistics Quartz Cutter in Advanced Crafting Tables.
[FIXED] Forestry Backpacks didn't work with Autocollecting Tools, because the Magnetic Autocollect did not throw an Item Collect Event.
[FIXED] Chainsaws used WAY too much durability for Treecapitation!
[FIXED] Pipes were not able to fill Molds at all due to some stupid Bug.
[FIXED] Worldgeneration in different Dimensions had the same RNG as on the Overworld. Now the Seed is being XOR-ed with the Dimension ID to prevent that. This has the convenient Side Effect of the Overworld staying the same as before, because it's ID is Zero.
[CHANGED] Barrels and Drums did not have the same Melting Points that Pipes have. I fixed that. Metal Drums now have 1.25 times more Heat Resistance, while Wooden Barrels and Plastic Cans got a properly assigned fixed Value, which in most cases should not make them burn up.
[CHANGED] Blue Steel is Red Steel now and vice versa. This should fix my ages old fuckup of the Recipes. Everything should still be the same as before when it comes to Stats, just the Color of the Material changed. If you used Matter Fabricators to make Red/Blue Steel for some reason, you may need to check the USB Stick with the Recipe.
[CHANGED] Oil and Natural Gas can now pass through Water. In the case of Oil this will result in Buildcraft alike Oil Fountains or puddles in the Ocean, should you be super lucky. If not, just dig a tunnel down and it happens too. XD
[ADDED] Railcrafts Firestones now work as Fuel in Solid Burning Boxes and can also light TNT, Burning Boxes, Coke Ovens and the likes. Please be aware that Burning Boxes need the Firestone to he hoppered in to work, since they otherwise work like Flint and Steel.
[ADDED] In a potentially failing attempt of fixing the Nichrome Issue in Stainless Steel, I added a Version of the Recipe that takes Nichrome instead of Invar, which is ofcourse in the proper ratios. I do not know how well that will work for people using the Crucible, but better than nothing. I did the same for Ultimet and Signalum too.
[ADDED] Barbecue Sauce (BBQ Sauce), because why not.
[ADDED] Axes, Saws and Chainsaws can now place Saplings too. They will still place Workbenches if Saplings cant be placed.
[ADDED] Tooltip for Covers that can be controlled with the Cover Controller Cover. In case of Covers where the Cover Controller Cover is required (such as Shutter Cover), it will be Cyan instead of Dark Gray to make it easier to see.
[ADDED] Config to make it possible to disable specific Ores in Stone Layers from generating.


6.14.08:
[FIXED] Some Issue where the World somehow doesn't load.
[ADDED] HSS-based Recipes for Railcraft Rails.


6.14.07:
[BROKEN] You might not be able to actually launch the Game on certain Computers. Nothing corrupting. ^^
[NOTE] I've been indirectly asked through bear, to make GT6 Tools compatible with "Progressive Automation", but it only accepts Vanilla alike Tools, meaning that will never really work. But my Wrench can harvest all of its Machines now at the very least.
[COMPAT] Per Fabrica Ad Astra (PFAA)
If CustomOreGen is not installed, PFAA still works, this means PFAA Blocks exist but they wont generate. Because of this likely intended thing, I will now generate the 25 PFAA Rock Types with my own Stone Layer System alongside the other Stuff, if PFAA is installed without CustomOreGen.
The PFAA Sands now have more appropriate Processing, and the PFAA Clay Balls now have fitting Material Data.
I made sure all Ores actually work. Realgar (Red Gem Ore made of Arsenic + Sulfur) did not have any Material Data.
[IMPROVED] Advanced Crafting Table now consumes Items in a better order. Also fixed a brand new Bug I caused earlier.
[FIXED] Magnesite and Magnesium Carbonate are now the same Material, like they should have been.
[FIXED] The Collect Sound for autocollecting Tools is now matching the vanilla Sound in Pitch and Volume.
[FIXED] Battery Boxes were not able to charge Tools unless a Battery was inside of them to make them able to accept Power. (their internal buffer would eventually reach zero when charging tools only)
[FIXED] Aqua Regia Processing Ores emitted too much Water, effectively turning Oxygen into Hydrogen.
[CHANGED] Air Vents now do 256000L per 360 ticks at a time, instead of only 16000. They have an Offset for ticking too now, so you can use multiple Vents on one Pipe/Drum/etc now.
[CHANGED] There is now more Galena Veins in the World, and visible on the Surface in Mountains.
[CHANGED] A bunch of Ores, that I deemed way too useless years ago, has been added back. None of them are required for anything, old Worlds are safe as always.
[ADDED] NEI Handler for putting Toolheads on Handles. I used the Machine Recipe Handler and Fake Recipes for that one.
[ADDED] Arsenic can now be used to make Poison and Harming Potions.
[ADDED] Crushed Purified Biotite can now be heated in the Dryer to make Argon Gas from it. It is mostly a Byproduct of other Ores like Asbestos though,
[ADDED] Gypsum, Trona and Mirabilite now have Recipes to create them by mixing some Chemicals with Water (They can be dried back too). Perlite now has a drying Recipe that turns it into Obsidian aswell.
[ADDED] Iron and Hydrochloric Acid now have a few more Chemical Reactions. This will improve Hematite extraction from Limonite. Oh and Limonite now smelts into Hematite, like it does in real Life, so it is no longer an easy Shortcut, like it was before.
[ADDED] Vanadiumsteel Fluid Pipe that can handle Acids and otherwise has the same Stats as Tungstensteel (which cannot handle Acids).
[ADDED] Porcelain Cup Recipe. Now you dont need to rely on Loot anymore to get those small Cups.
[ADDED] Two more variants of Black Sand. They are essentially identical in function when it comes to getting Iron, Vanadium and Gold, so it is not much of a change. They also came from PFAA and were dormant and only somewhat compatible until now.
[ADDED]
Bentonite Clay, Kaolinite Clay and Palygorskite Clay (try to say that 3 times fast), which are Yellow, White and Blue Clay.
The only reason I did this, is because I noticed PFAA added those, and they were long dormant in my Ore Materials Section (I did not think of them as Clays back then), so now I gave them Life!
Please take note that I changed the Electrolyzation Output of Clay, and that Vanilla Clay does no longer give Lithium. Only Brown Clay does.


6.14.06:
[NOTE] Hindsight is 2020, and I did fuckup my fix for the Water Generation Bug last year, so here we go with the first Release of 2021. XD
[FIXED] Rivers, Oceans and Swamps generated partially at XZ 0,0 to XZ 15,15 because I forgot an Offset. This also caused the first layer of those Waters to be at the wrong height.


6.14.05:
[BROKEN] DO NOT USE!
[FIXED] The Explosion Sound does no longer spam for Machines in the Rain and similar if you turned off those Explosions. Instead it waits a bit before it sends that Sound again.
[FIXED] Buildcraft Wrench Stuff can now be Sneak Rightclicked with an Electric GT6 Wrench without switching Modes, as long as the target is a TileEntity.
[FIXED] You know how you can open Pipes to Air Blocks? Well I added that functionality for Liquid and Gas Blocks too, so that is now fixed.
[CHANGED] All Fluorite Byproducts to use the more colorful Versions of Fluorite.
[CHANGED] Also changed the Byproducts of Naquadah, Trinium, Duranium, Tritanium and Dilithium containing Ores. Desh has related Byproducts to those three now.
[ADDED] A few more Recipes for Wooden Stuff like Rings, Item Casings and Stuff.
[ADDED] One more Icon to the Warning Sign Cover. It's that Checkerboard-ish Circle that is usually on Crashtest Dummies.
[ADDED] Builders Wand. Pretty much is doing what the ones of other Mods do too. It places Blocks! It can also destroy Thaumcraft Nodes like the Crowbar does. Why? Because it's a neat Easter Egg, and if a Node happens to be in the way while building, you can just go smack it with the Wand.
[ADDED] Clay Molds for everything that is mentioned in the Molds Book, that way you can actually craft them without having to magically know all the Chiseling Shapes for Molds.


6.14.04:
[BROKEN] DO NOT USE!
[NOTE] Released a tiny bit early due to testing GitHub Actions to Release GT6 instead of using a Solution on our own Server.
[NOTE] The URL leading to the Supporter Lists links to something that runs on a different Subdomain, so that it is easier to distinguish the traffic when using something like Pi-Hole as your DNS and Adblocker, as I happen to do since last Release. This probably doesn't matter to you though. The old Link is ofcourse still usable.
[IMPROVED] Ores that generate between two Stone Layers are now no longer stopped by other Mods equal Blocks (like comparing GT Marble with Chisel Marble, both should obviously count as Marble, which they did not before)
[IMPROVED] Basic Machine Input Tanks now have Capacities based off the actual Recipe Inputs. That way a single huge Recipe wont mess up automation of the many smaller ones.
[FIXED] Some minor Inconsistencies.
[FIXED] Insanely large Capacity Pipes had Integer Overflow Issues.
[FIXED] 1 Liter of Fluid sometimes remaining in Pipes.
[FIXED] Quadruple and Nonuple Pipes had flow direction Issues, because flow direction was per Block and not per Sub-Pipe.
[FIXED] Miniature Portals for the ExtraSimple fork of ExtraUtilities.
[FIXED] Durability Loss when Treecapitating using the GT6 Axe, which was using way wrong calculations.
[CHANGED] Wrenches from Buildcraft and similar Mods can now be crafted again, but if you use them for anything GT related they will instantly break without refunds.
[CHANGED] Flint and Tinder now has a higher chance of success in certain cases, where there is a specific Material used to craft it.


6.14.03:
[COMPAT] Some Hardcore Ender Expansion Stuff has Material Data now.
[FIXED] An Issue with Rendering on some peoples Worlds/Computers.
[FIXED] Colorless Sapphire Boule Recipe was producing at a wrong rate.
[CHANGED] Some End Worldgen.
[ADDED] Magic Research Papers to Library Loot. So far only useful for Thaumcraft. Getting all 9 of them and scanning them with the Thaumometer in order will unlock all Vanilla and GT Aspects. They can be turned into Knowledge Fragments too.


6.14.02:
[NOTE] The Default Config Pack updated to disable Netherlicious Quartz Generation and some other random things.
[NOTE] I would recommend updating Netherlicious to 2.2.0 or later, if you have it, by the way.
[FIXED] An Problem where GT-Fast-Leaf-Decay caused GT6 Trees to lose most of their Leaves when Non-GT6-Logs were harvested closeby.
[FIXED] An Issue with Trees generating in Snowy Biomes. Only affected the Bottom-most Log of Rubber Trees being misplaced by Snow Layers.
[FIXED] A lot about the Custom Gearbox was not always working like intended, especially not the internal Axle.
[FIXED] An Issue where some Machines did not output all Items at once somehow.
[FIXED] A few Fluid Containers like Et Futurum Glass Bottles (Lingering Potions), Railcraft Creosote Cells and several broken Buckets.
[FIXED] Charge Speed of GT6 Tools in Battery Boxes.
[FIXED] Battery Boxes now no longer accept Energy in large bursts. Small bursts may still happen, because I cant set the Energy-Consumption-per-Tick Limiter to a Value lower than what it can actually charge.
[FIXED] Recipe Replacer did not always know what a vanilla wooden Stick is, since I added a ton of different Wood Materials.
[FIXED] Sealed Barrels and Drums were still able to be filled or drained through Tap, Funnel and Item-Use.
[REMOVED] Some of the Tools that did not make proper sense like the Flint Club, Sword and Hoe.
[CHANGED] The Durability of Bronze Tools got buffed to be more than Wrought Iron, but less than Steel.
[CHANGED] Many GT6 Wood Tools are now properly craftable. Wooden Clubs are made by putting a Log or Beam on a Stick in a 2x2.
[CHANGED] Rotating Pillar like Blocks such as Logs or Beams, using a Soft Hammer or Wrench, can now also make them enter their "all sides covered" State. Useful for making custom Trees.
[CHANGED] The Ceramic Bowl (formerly known as Mixing Bowl) is now made with a Clay Bowl in a Furnace. I also removed the Red Dye requirement. This should fix the Chisel not really being available if you don't have Bronze (even though you likely need to make Bronze to make the Bowl in the first place). Wait was there a Lead Chisel? Well anyways this Recipe makes more sense regardless.
[CHANGED] The Recipes for Mortars, Juicers, Crucible Stuff, Taps and Funnels are now more similar to the Ceramic Bowl Recipe too. Mortars also only need one Ingot/Gem to craft the Pestle part now.
[CHANGED] Nozzles and Cap Nozzles made of Ceramic have now been replaced with Steel ones instead.
[CHANGED] HSLA Steel from Rotarycraft can now no longer be used to make "regular Steel" things (dedicated HSLA things still exist ofcourse), because HSLA is only worth half as much Iron as regular Steel. Yes I found out about the additional Output of that one Reika Blast Furnace Recipe.
[CHANGED] Stone Bumblebees now ALSO accept Cobblestone, Mossy Cobble and Mossy Bricks, and state so in the Tooltip.
[CHANGED] Hard and Soft Hammers aswell as Files, can no longer be direct crafted, and now require the typical Toolhead+Handle Recipe to be used. This is to reduce Crafting Recipe Count and because the direct Recipes dont take Handles into account.
[CHANGED] The Autocollect Effect of Magnetic Materials for Tools also applies for the Handles now. This is only really useful with a Basalz Rod as Tool Handle though, which now counts as Magnetic. Note, that this currently only works with the Toolhead + Rod Recipes, not the hardcoded ones.
[CHANGED] Blaze Powder can no longer be turned into Sulfur and Ashes using the Centrifuge. However, many Recipes that require Sulfur now also allow Blaze Powder directly. (The Pyrotheum Recipes still require Sulfur seperately!)
[CHANGED] Blaze Powder and its Thermal Expansion equivalents are now considered a Tiny Pile of Dust instead of a full Pile of Dust. All Recipes should have been adjusted to take this into account.
[ADDED] Basalz, Blizz and Blitz Powder can now be used for different ways to obtain some Potions in the Distillery.
[ADDED] Blaze/Blizz/Blitz/Basalz Bumblebees that produce Combs that yield their respective Rods and Dust, this makes it possible to obtain those Mob Drops in Peaceful (or in general since those TE Mobs are way too rare, and yes I know Blaze is already doable with regular Nether Bumbles). Spoiler alert do not continue reading if you wanna find out the combo yourself, it is the combination of the highest Tiers of YRATILIM&YTSORFXDNEXREHTENXDENOTS (you have to read those in reverse, and replace the X-es with Slashes).
[ADDED] Slimeballs made with Borax and Glue. Borax now also generates as small Ores and is byproduct of regular Salt (NaCl).
[ADDED] Certain Materials prevent Items from burning in Lava/Fire. Netherite, Blaze, Fiery Steel and Efrine being an example of Materials like that. It is enough to have even the tiniest Amount of such a Material in the Item to prevent it from burning in Lava. You could for example slap a Refactory Wax Cover on a GT6 Block and its fully Lava Proof whenever you drop it.
[ADDED] Basalt and Blackstone Generator Module since those two Rock Types are just as infinite as Stone.
[ADDED] Wooden Bucket Recipe with Gold Plate, to make it easier to get from the Nuggets in the Nether.
[ADDED] Soulsandstone and Red Sandstone can be used in the Grindstone now.
[ADDED] Efrine Drums, Cells, Pipes, Wires, Anvils and the typical Shelf Stuff. Also Netherite Wires at Aluminium Tier.
[ADDED] All Wires for 1x to 16x exist now, and not only 5 of the bundles. Does not include Cables!
[ADDED] Lumium can now be made in a Mixer with all variants of molten Glowstone. Same goes for Lumium Glass and Hardened Glass from Thermal Expansion.
[ADDED] Lumium Shelves and Stuff. Now there actually is a proper Glow in the Dark Material available. (remember it can be painted)
[ADDED] Gloomstone from Netherlicious as a Material, which can do pretty much anything Glowstone can do. Similar to GalaxySpace Glowstone Variants.
[ADDED] Netherquartz now generates in large flat Layers in the Nether. The old GT5 Veins and the Small Ores for Netherquartz wont generate anymore.
[ADDED] Sulfide Ore Crystals now generate on the Nether ceiling so that it is now possible to actually live in the Nether with most of GT6s Features. This is enough Resources and Byproducts for removing the old GT5 Style Veins from the Nether.
[ADDED] Netherite Version of the Mortar, because most usable Mortar Materials, including Iron, are too hard to come by in the Nether.
[ADDED] Magnalium Rail Set. Same Cart Speed and Explosion Resistance as the Steel Rail Set. Big Upgrade from Aluminium, which is basically garbage Tier.
[ADDED] Mining Tools which can place Torches, now can plug leaking Liquids like Lava with mostly non-valuable mined Blocks from your Inventory. Just rightclick either next to or into the Liquid Block that is coming at you.
[ADDED] Axes and Saws can now place Workbenches, when you click on something that isn't Wood or a Plant. Why Workbenches? Because Greg wants to free that one Hotbar Slot he always uses for Workbenches, that's why!
[ADDED] Nametagged Mobs that die, will drop their Nametags (the Tag will still have the Name on it). Note about named Mobs from certain Mods: If the Mob can still despawn, it will NOT drop a Nametag.
[ADDED] Replicator Recipes for some Food and other Organic Items, using Biomass + Charged/Neutral Matter (or Liquid IC2 UUM). Mostly for making GT6 and Vanilla things, which might otherwise be unobtainable in some cases. This should likely fix the unobtainable Ultimate Stew from Avaritia.
[ADDED]
Metal Scaffolds made of each of the Metal Shelf Set Materials.
These Scaffolds are NOT intended to be identical to any other Scaffolds you might know! They are NOT supposed to be exactly like IC2, Vanilla or any other Scaffolds!
Vertical Scaffolds contain a Ladder on the inside, which you can use to climb up through the Scaffolds Platform.
Sneaking ontop of the Scaffold Platform makes it possible to go down the Ladder, as long as you dont stand on the Ladder part itself.
Sideways Scaffolds need to be connected to at least one vertical Scaffold on the same Y-Level to stay in place.
You should use Steel Bars or similar as a Guard Rail, if you use the Scaffolds as a Catwalk.


6.14.01:
[IMPORTANT] If you use Factorization, use Asie's Version of it from here https://asie.pl/files/mods/Factorization/ (the one with the highest number, without "-dev" at the end of it). The original is broken and may crash with CoFH-Core.
[NOTE] Just a nice reminder because a bunch of Potion related Stuff happened this update: Ever since Version 6.05.34, using the Bathing Pot you can bath Food Items in a Potion to give the Food Item that Effect.
[COMPAT] Twilight Forest
Way better Ore Generation, and allowing Vanilla Ores again in there. GT5 Style Ore Generation is now completely gone from Twilight Forest. (Remember that Iron Ore is already having all its Recipes overridden by GT6)
In case the Ores look familiar to you, I grabbed and sometimes modified some Textures from previous GT Versions for this.
The 16 Vanilla-ish Ores that got added are what I think is the minimum viable amount of Ore Types that a GT6 World requires, if we account for the Byproducts.
I also added the 8 Rock-Layer-Ores in Blob form to there, getting the total count of new Ores up to 24.
Those specific 24 Ores are also not TileEntity based so the Ore Magnet and the Miner Tree work on those just fine.
Some Ores only generate above Y=40, meaning you have to find a Hollow Hill, Hydra Cave, Yeti Cave or the Twilight Highlands for them, and some unimportant ones are also Biome dependent in order for them to not be spammed everywhere.
Thaumcraft and Ars Magica will have their important Ores generate in the Enchanted Forest Biome when installed, even if their own Worldgen is disabled.
Netherite Plus will have its Ancient Debris spawn in the Deadrock near the final Twilight Castle, so you don't have to go to the Nether for it.
Bedrock Ores still exist in the Twilight Forest. I even fixed that the Indicator Flowers and Rocks were not generating all that often, due to the giant Leaf Canopy blocking the Generation-Ray-Trace from the Sky (which now ignores specifically Logs and Leaves).
Solar Panels in Twilight Forest will perpetually produce 4 EU/t all time, so exactly half of what they make in the Overworld during Daytime.
[COMPAT] OpenBlocks
The Drain Cover works like an XP Drain when you stand ontop of it and Sneak. It will also absorb stray XP Orbs in front of it. (1 XP = 20L of Liquid XP)
The Tap will give you one Level worth of XP when you rightclick it, as long as that much is available. Yes you have to click it 30 times if you wanna go all the way from level 0 to level 30.
The regular and multiblock Furnace Ovens should have a Liquid XP Output along with the normal Item Outputs. Note that the Liquid XP Output Tank has unlimited Capacity unlike the ones of other Machines, so you do not need to worry about the XP stopping the Furnace from operating. The Tap can be useful to extract XP Orbs on an Oven by the way.
[COMPAT] MineFactory Reloaded
The Drain Cover can work like a Sewer for Mob Essence from XP Orbs, but only if OpenBlocks is not installed. You can generify Liquid XP into Mob Essence and vice versa. (1 XP = 66.666L of Mob Essence, yes it is such a crummy number)
It will also work for Sewage from Adult Mobs, based on the Size of the Mob standing on it. Make sure their center is actually above the Drain, we dont want them to miss the Toilet do we?
[COMPAT] RotaryCraft
Tungsten Alloy and its direct Items now have Material Data. I think it got one of the most complicated Results in the Chemical Tooltip so far, closing in on the old Garnet Tooltips. XD
The Flakes from Ore Processing can now be processed in more Machines than just the Furnace (like the Shredder or Sifter for example). Do note that most of those Recipes will output Dusts instead of Ingots now for somewhat obvious reasons. (I cant really do anything against the "Aluminum Dust" that the Ore Extractor outputs since it isn't Flakes)
Also added Tungsten Alloy Drums, Anvils, Cells, Fluid Pipes, Storages, Shelves etc.
[COMPAT] Netherlicious
Netherlicious officially wont ever add Netherite, so the Netherite Plus Mod is still recommended for Netherite Items such as Armor, or the Twilight Forest Netherrite Ore Compat.
Foxfire Planks are now in the GT6 Wood Dictionary.
Crimson and Warped Wood Stuff from Netherite Plus are now overidden by Netherlicious Wood Stuff wherever applicable.
The Mushroom Logs can now properly be made into their Beam counterparts using GT6 Tools.
Its Food Stuff has more consistency with GT6 now.
There is also additional ways to make Potions.
Spectral Dew is a Type of "Nether Water" that can be used in many Recipes and even Boilers that require regular Water.
[FIXED] Minor Serverside Lag of GT6 Electric Transformers when any IC2 is installed. This should reduce the occurences of it quite a bit, at least with other GT6 TileEntities adjacent to the GT6 Transformer.
[FIXED] Unscanned Forestry Bees (and some other things) randomly became not properly stackable, whenever a GT6 thing storing them got unloaded and reloaded. (this wont fix any already done problems to the stackability)
[FIXED] A very minor Localisation Bug with Prospecting on Servers.
[FIXED] Some Wood Material Data for Erebus, Chisel and Netherite Plus.
[FIXED] Double Chests should now work with Pipes, Hoppers and similar.
[FIXED] The wrong Types of Honey and Milk were outputted by some Recipes.
[FIXED] Twilight Forests dependance on Naga Scales/Trophies, Lich Scepters/Trophies and Hydra Blood/Trophies to trigger their Progress Achievements. Now you ONLY need the Trophy and not that extra Item. Also you can just rightclick a placed Trophy to get its Achievement too.
[FIXED] Basic Machines were not outputting all their Fluids into Quadruple/Nonuple Pipes if one of the Fluids got Stuck.
[FIXED] Radiation from Reactors should now not be as high if you are further away from them.
[CHANGED] The Distillery no longer requires Blaze Rods. Netherwart is enough of a Gatekeeper for Potion Brewing.
[CHANGED] In order to make Potions in the Distillery you have to use Distilled Water now. Normal Water wont work anymore.
[CHANGED] Oil now burns in the Burning Box with the same total Power Value as "Fuel", due to being able to get way more Power with a Distillation Towers other additional Fuels later on. I also noticed Soulsand Oil did not have a burn Value so I added that too. You can also use the Distillery again for making Oil to Fuel and Lube at a loss.
[CHANGED] Battery Boxes now only charge/discharge once per second instead of once per tick. Internal Capacity of the Boxes themselves has been adjusted for this change of Rate.
[CHANGED] Certain Twilight Tools now have higher Durability so they aren't as "Single Use and Throw away" anymore as before. Applies to: Ore Magnet, Peacock Fan, Crumble Horn and Giant Pickaxe.
[CHANGED] Twilight Forests Lamp of Cinders works as infinite Flint and Tinder on GT6 Machines and TNT, but it will not spawn Fire Blocks. Can also be used in the modified vanilla Furnace Recipe without being deleted.
[CHANGED] Swords, Saws, Axes and similar made of Knightmetal or Fiery Steel can now be used to break Twilight Maze Hedges about 40 times faster too. But be sure to wear something protecting you from the 1.5 Hearts of "Cactus" Damage per broken Hedge. Also the Saw should be optimal for this since it autocollects Drops, or the Sense/Scythe due to the Area of Effect.
[CHANGED] Bastnasite now has Nikolite/Teslatite/Electrotine as a Byproduct if the corresponding Mod exists. It also has an Ore Drop Factor of x3 now.
[CHANGED] Bumblebees in Twilight Forest now have the same variety that the Overworld ones have.
[CHANGED] If you non-sneak rightclick Bedrock Ores you will get the overall Bedrock Drill Recipe List in NEI, making it easier to discover what their use is. (Does not apply to Bedrock Fluid Springs, obviously)
[CHANGED] If you set yourself on Fire and rightclick a GT6 Machine with your bare hand it will count as Igniting the Machine with a Flint and Tinder or similar.
[ADDED] Red Clay, which is generating in the Nether now. Look close to the big Lava Pools, it always generates at the same height.
[ADDED] Wood Pellets can be turned into Charcoal in the Coke Oven now, which doubles the Fuel Value.
[ADDED] All Basic Machines now have a Screwdriver Mode, which allows them to not accept more Items in their Input Slots once there is already a Stack inside. This makes precision inserting of Inputs using "Exact Mode Hoppers" and Item Pipes into a Bank of multiple Machines much easier than what you had to do before with Redstone, Shutters and Progress Sensors.
[ADDED] Thaumcraft Flowers and Saplings should now generate in certain Biomes in the Twilight Forest.
[ADDED] Recipe for the Name Tag. Yes that did not exist so far in GT6... I somehow forgot adding that one and thought I already did add it...
[ADDED] Transformation Powder can now be used to turn Mob Spawners into their Twilight Forest Variant and back. You will need 16 Powder at a time for it to work though. I also added some additional Mappings for Spawners just in case.
[ADDED] The Cube of Annihilation of Twilight Forest is now craftable using a bunch of different Trophies and Loot Items.
[ADDED] The 36 Trees from Harvestcraft to the Wood Dictionary. Yes I know they mostly use Vanilla Leaves and Logs. Maple and Cinnamon will have their GT6 Counterpart of Beams and Planks assigned.
[ADDED] Harvestcraft Crops to the List of things that can randomly spawn in the Farming Room in GT6 Dungeons.
[ADDED] Amethyst Mortar, because there is Situations where Amethysts/Ender-Amethysts are actually more common than Diamonds, Rubies and Sapphires.
[ADDED] Wooden Buckets for Soy Milk, Glue and Spectral Dew.
[ADDED] Bedrock Diamond Ore. About twice as rare as Bedrock Gold Ore. Also Pink Diamond is now a Byproduct of regular Diamond.


6.14.00:
[NOTE]
The Thaumic Fixer Mod by Chocohead is no longer needed nor compatible!
We have our own Version of it now, which also works with more than just ~3 specific Mods... He really fucked up that one...
This Version of the hack is also compatible with Reikas Mods, unlike Chocoheads.
[NOTE]
Bugs that might have happened to a few things, that I might have overlooked could exist, such as:
"Recipes for certain wooden Items being uncraftable, because of the general Wood Type overhaul"
"Some random thing might Crash when updating from 6.13 to 6.14 because the ASM Code is not tested with ALL the Mods" (I am very confident this wont happen though, just not confident enough to not mention it at the very least)
[COMPAT] CoFH-Core should now much less likely crash SinglePlayer Worlds on Startup with the FMLProxyPacket Race Condition. OvermindDL1 made an ASM® thingy to fix it, so my original brutal way isn't needed anymore.
[COMPAT] TerraFirmaCraft(Plus) Stuff should work slightly better now. TFC Sticks and Torches are now being accounted for when doing Recipe Outputs.
[IMPROVED] The Extender Manual now has a few more Lines to explain what Extenders are actually doing. I also added a Tooltip to Two-Faced Energy Converters (such as the Thermoelectric Cooler) to remind people that Universal Extenders do exist and can be used to literally cut Corners, when there is no "Thermal Conductor Wires" or similar available.
[FIXED] An Issue reading Configs on Apple based File Systems (certain Config Files were deleted). I did not think Apple could do a mix of the Windows way and the Linux way of handling Case Sensitivity in File Names to the point of causing this.
[FIXED] Fluid Tanks/Pipes with huge Capacities should no longer be limited to Integer.MAX_VALUE anymore.
[FIXED] Lava Blocks should no longer set NON-flammable things on Fire, just because they are made of Wood. (GT Fireproof Wood for example, Forestry Fireproof Wood is wrongly implemented sadly)
[FIXED] Some things I saw GT6U has fixed too, like TFC Damage Multipliers for Heat and Frost Damage.
[FIXED] Whoops, I made Titanium Invisible by accident.
[FIXED] Crash with IC2 when High Level Radiation Effects are applied, like the ones from Neutronium.
[FIXED] There was ~5 second long Lag Spikes, when certain Recipes were asked for, by the On-Demand Recipe System. Specifically Recipes with Selector Tags, Extruder Shapes or Fluids (Sawmill), aswell as Recipes with Empty Slots in the Anvil (Item Casings).
[FIXED] Drones just vanished instead of dieing during the Bumblebee Breeding Process in a Bumbliary. Now there actually is Corpses for the dead breeding Drones too.
[CHANGED] Certain Worldgen Features will no longer be present in the Overworld when TerraFirmaCraft is loaded.
[CHANGED] Made Pincers and the Plunger the right Tool to harvest the Dragon Egg and Dragon-Egg-like Mod Blocks. The Vanilla Dragon Egg still teleports though!
[CHANGED] Technically speaking, Shovels/Spades/Plows and such should be able to break Fire now, BUT due to the nature of how you click Fire to break it, that wont directly work. This is just a special case in case a Mod adds a Block that uses the Material "Fire", so that it can be harvested somehow.
[CHANGED] Crates now use Crowbar Harvesting to drop their Contents instead of Rightclicking with Crowbar. Also works with the Universal Spade.
[CHANGED] Crates can now be made with Wooden and Plastic Screws too. Iron based Screws were maybe a little bit of a too difficult requirement for this kind of thing.
[CHANGED] Pipe/Wire Selection Boxes and placement Behavior for the better.
[ADDED] Zeolite to Dungeon Loot Chests just to make sure people can find it by accident.
[ADDED] Pickaxes and Drills made of Knightmetal or Fiery Steel can now be used to break Twilight Maze Walls about 40 times faster. The Construction Pickaxe is faster than the regular Pickaxe in this case, since the Maze Bricks dont count as natural.
[ADDED] Tapes can now be put in Shelves. Remote Activators can also be put in Shelves now, but are hidden as a Book like most things you might not want people to notice, such as Levers or Keys.
[ADDED] The Shelf in Workshops in GT6 Dungeons now contains two Rolls of Tape, which is always enough to haul both of the Stone Mass Storages and then some.
[ADDED] Farming themed Room to GT6 Dungeons to showcase the Universal Flower Pot and a good use for Glowtus as Covering for the Water Hole in Farms.
[ADDED]
The Coconut Tree from GT6U got Backported, and has been given proper development. No longer a tall Hazel Tree Ripoff.
These Trees will grow at Beach Biomes, aswell as most Tropical and Oasis Biomes.
Due to the lower amount of Leaves, they have a higher Sapling rate than any other GT6 Tree to compensate.
You might have found those "Work in Progress" Saplings in random Chests in Dungeons and such, now they are all growable.
The dropped Coconuts behave just like other Mods Coconuts in GT6 Recipes, and if you didn't have such Mods, you can make Coconut Milk, Coconut Cream and similar now.
[ADDED]
Dedicated Wood Materials for many Types of Wood.
This will influence basically every common Wood ITEM. Ofcourse the Generifier works on all of them.
Sticks generated on the ground in the World can have different Materials like Rotten, Mossy or Dead Wood aswell as Biome specific Variants (Birch Forest Biome = Birch Sticks). They can be used like normal Sticks.
The NEI Tree Family GUI also got 6 more Slots now to display which amounts of Sticks/Pulp/Bark/Charcoal you can get from each Type of Tree.
Many Crates that contain Wood Planks now have this variety too. Some of these Crates can also be found in GT6 Dungeons in a new Pile that isn't the Metal Crates.
Forestry Stuff can now be Fire Proofed like the GT6 and Vanilla Logs/Beams/Planks. (Forgot to add those Recipes to my Stuff initially)


6.13.02:
[COMPAT] HBM Nuclear Tech has some more Compatiblity Data attached to it. Mainly Shelf Data. The Alloys it has are a little bit too weird.
[COMPAT] HEXCrafts Hexorium Ore is generated in all possible ways when installed, with RGB ones closer to Surface and Black and White ones closer to Bedrock. Also some Hexorium related Recipes added. Its Energized Monoliths can also be found as Decoration in GT Dungeons.
[FIXED] A weird Interaction with Robot Arms and Extenders that resulted in Extenders sometimes choosing the secondary Facing as destination for Items, even though the Items did not come from the promary Facing, including targetting things like GT6 Red Alloy Wires that dont even have Slots to put shit into, resulting in a Crash of the Block that has the Robot Arm.
[FIXED] Tooltips for Mob Spawn Proofing will now show it whenever Optifine breaks the mechanic in Singleplayer, because clearly removing Metadata support from World functions is a good Idea and "totally fixable" on my end...
[FIXED] GT6 Redstone Wiring sometimes checked multiple/infinite times per tick for Vanilla signals. This was leading to Game Freezes with Thermal Expansion.
[FIXED] Some Biomes from Enhanced Biomes were not counted as River for my Worldgen.
[FIXED] Skeletons were not shooting the right GT Arrows since a long time. Now I made an actual List of Arrows to shoot, instead of grabbing any of the existing Arrows. This also means no Radiation Arrow shooting Skeletons anymore.
[FIXED] Made Config Files always migrate to Lowercase File Names so random Garbage with Windows and older Config Files wont happen again.
[CHANGED] The Capsule and Cell Extruder Recipes were not really well coded, so I fixed them up. Should make it easier to work with for the GT6U Team too.
[CHANGED] Moderated Reactor Rods now lose durability twice as fast compared to what it was before.
[CHANGED] Molten Midasium is now able to turn Lead into Gold at a ratio of 1 Midasium + 4 Lead = 4 Gold. This is better than using a Generifier on Midasium to directly make it into Gold 1:1.
[ADDED] There is now small Rocks in the Nether, some of which contain tiny amounts of Ancient Debris, and are as rare as Meteorites on the Surface. Glowstone, NetherQuartz and Flint can be found in them too.
[ADDED] Config to disable the nutrition System for Sugar, Fat and Dehydration. Alcohol and Caffeine stay though, since those are definitely things people intentionally consume for buffs and such.
[ADDED] Netherite Drum, Fluid Pipes/Capsules and Anvil. Netherite also counts as Acid Proof now, due to its high Gold content. Also added Gold Fluid Pipes/Capsules while I was at it, and yes due to the code they CAN handle Aqua Regia.
[ADDED] Laser-O-Meter Sensor for Laser Fiber Wires.


6.13.01:
[NOTE] Sorry for letting this one take so long.
[NOTE] I got a report that Riverwater, Swampwater and Oceanwater cannot be scanned with the Thaumometer, I can not replicate that Issue though, so I guess it is either fixed or something is wrong with their Modpack.
[COMPAT] Netherite Plus got Compatibility now. It backports Netherite to 1.7.10. Soulfire Torch Placement by Tools, Record Shelving, Blackstone, Biomass, and the two Wood Types should all work in GregTech Stuff, also Netherite Ingots are made the Crucible way now instead of Crafting Debris with Gold Ingots. https://www.curseforge.com/minecraft/mc-mods/netheriteplus
[FIXED] Some Localisation Issues with Reika's Fluids, it should be fine now. It will work with all other Unlocalized Fluids too, so GT6 will display the most proper Fluid Name that it can display now.
[FIXED] Seems like QwerTech might have messed up the Recipes for putting Planks in a Bathing Pot to get Treated Wood. This Issue might be fixed now for Vanilla Planks.
[FIXED] The GT6 Axe was still slowed down on my end, when TreeCapitator was actually installed, resulting in a Double Slowdown.
[FIXED] Heat Exchangers and Diesel Motors were wasting Fuel in certain Situations. Typically when they were operated while constantly out of Fuel.
[FIXED] Failing to Load Save Files in Singleplayer when you already loaded another World before.
[FIXED] Pam's Harvestcraft Fishtrap Bait Recipe for Mixing Bowl had a lower amount of Output than intended.
[FIXED] Thaumcraft Golems were not chopping GT6 Logs, because it checks for the wrong OreDict String, and because it checks for Block IDs using Block Objects which is kinda screwed up, so I had to fix that too.
[FIXED] 7 Bits are definitely not enough to store Numbers that can go up to 12 Billion. Fusion Reactor Start Energy is now properly saved when unloaded.
[FIXED] Terrafirmacraft Plus not being detected as Terrafirmacraft.
[FIXED] Clay Ore that comes from Hematite Bedrock Drilling is now processable.
[FIXED] Some Ars Magica Unification things.
[FIXED] The way I overrode the Railcraft Plate Names was kinda jank so I made it use the GregTech Language File now.
[FIXED] JABBA Dollys can no longer pick up GT6 Shelves. Somehow they were still able to be picked up even though I prevented them from working on GT Blocks as a whole, ages ago.
[FIXED] Infinite Items Glitch caused by InvTweaks making 111 Stacks and NEI keeping those at an Infinite Level, causing Infinite Item Duplication, if a Stack with higher than its custom Maximum Size enters a GT6 GUI Slot, while the GUI is being kept open. Now if that happens I will just cap the Stacksize at 64, potentially deleting Items, but that is what you get for InvTweaks being screwed up.
[CHANGED] When Machines can process multiple Items in a row in one go, they wont process more than a Minutes worth of Stuff at a time, assuming maximum Power Input. This applies to things like the Crusher, Sifter, Compressor, Squeezer, Electrolyzer and Centrifuge for example.
[CHANGED] Bath Recipes that have an Output Stacksize of more than 1 now have their Outputs spread over the 6 Output Slots. This should make it easier to parallel process Ores in a Multiblock Bath. (I may or may not have slightly buffed the Outputs of some Ore Recipes too)
[CHANGED] Blastproofness Tooltip now also mentions Strong Dynamite Proofness and doesn't stop at TNT-Proof anymore.
[CHANGED] NEI Fuel Lists are now no longer listing the confusing and irrelevant EU/t Value.
[CHANGED] More Reactor Test Stuffs from Erik3003.
[CHANGED] Crucibles now glow when they are within 100 Kelvin of melting down.
[ADDED] Netherite things, such as Crucibles, Burning Boxes and all the Shelf Stuff. The Crucible Tier is same as Meteoric Steel. The good Netherite Tools are made by bathing Diamond in molten Netherite. The bad Netherite Tools are, well, not that good. Ancient Debris does generate in the Nether now as rare small Ore.
[ADDED] Infinite Bedrock Veins for Ancient Debris in the Nether. Relatively high Probability compared to all other Bedrock Veins. Will also exist without Netherrite Plus.
[ADDED] Ancient Debris aka "Netherite Scraps" can now be used in the Massfabricator to make liquid Enderpearl. Which then can be used in a Replicator to make it into an actual Enderpearl. Dont ask me what sense it makes, the Massfabricator does it, not me.
[ADDED] The HV Lightning Processor can be used to turn Blocks of Solid Netherite combined with a few other Ingredients into Netherstars.
[ADDED] Uranium Hexaflouride Processing Chain by Erik3003. And fixed by Greg, because the Maths of the Recipes were "Perfectly Balanced without Exploits", except for the obvious dupes of every single Recipe added.
[ADDED] Ownership Reset Config for people who locked themselves out of Stuff like Mechanical Safes in SinglePlayer.
[ADDED] Small Cinnabar Ore to the Nether.
[ADDED] Ars Magica Chimerite Ore when it is installed.
[ADDED] Steam can now be put in Chem Tubes.
[ADDED]
Erik3003s Nuclear Reactor changes made it into this Version.
He wrote a PDF as a Guide too, which I linked on the Downloads Page, and also here: https://gregtech.mechaenetia.com/1.7.10/gt6fission.pdf


6.13.00:
[BROKEN] DO NOT USE!


6.12.02:
[FIXED] TerraFirmaCraft Attack Damage Multiplier for GT6 Tools to be 80 instead of 10 and actually showing in the Tooltip.
[FIXED] Thaumcraft Aspects for Nuggets and Ingots now check if they are supposed to be Metallum, and if not, then they will replace it with Ordo.
[FIXED] Reactors blew up when no Coolant was in them while a minimal amount of Power was left over and they did not have any Rods.
[FIXED] IC2-Classic Watermills should now accept Ocean and River Water. This requires a pending update on the IC2-Classic Side aswell.
[FIXED] GT6 Tools should now work with Treecapitator installed as long as Treecapitator detects them as valid Tools.
[FIXED] A Rare NEI Recipe List Crash. (Concurrent Modification Exception when clicking Progress Bar)
[FIXED] Bedrock Ores did not generate Indicator Flowers and Indicator Rocks in Twilight Forest due to its Terrains normal Height of Y31 instead of Y63.
[FIXED] The Code that made Plates, Ingots and Rocks placeable did not check if the Stack was already a Block, meaning it just replaced things like Greatwood Planks or Silverwood Planks with the Stack-of-Plates Block.
[FIXED] Gearboxes sometimes breaking/overloading on World Load.
[FIXED] Some Issue with Coke Ovens and other Ignition based Basic Machines, hopefully.
[FIXED] Bumbliaries were lacking Tooltips.
[FIXED] Reactor Rods in general were not ON/OFF sensitive.
[FIXED] The Running-Possible Sensors now also return Redstone when the Output happens to be blocked at that moment.
[FIXED] Made Tooltips for Power Input/Output of Machines now have a special verbiage for a Minimum of "1 Unit per Tick" or less, by saying "up to MAX per Tick" instead.
[CHANGED] Coke Ovens now have 9 Output Slots due to the Recipe that outputs 5/6 Chunks of Coal Coke, so that each Coke Chunk gets 1 Slot.
[ADDED] Chili Crop which was missing.
[ADDED] Snow Golems can have their poop picked up by GT6 Hoppers automatically without constantly breaking a Snow Layer Block. Also works with Et Futurums silent replacement for Snow Golems, yes I luckily noticed that...
[ADDED] Reactors can use Distilled Water as Coolant, but that uses a different System so beware. Also it is not tested or finished yet! Also Reactor Explosions are disabled yet again until.
[ADDED] Geiger Counter Sensor courtesy of Erik3003.


6.12.01:
[FIXED] A doubled up Magnifying Glass Tooltip on the Large Heat Exchanger.
[FIXED] Large Heat Exchanger did not let Pipes connect to its Output.
[FIXED] The ON/OFF State of a Reactor while setting it up is more consistent now. Also Soft Hammers can turn it ON/OFF too now.
[FIXED] Crucible Crossings were rather directional when chained together.
[CHANGED] Purified and Refined Coal Ore in the Coke Oven will give 5 Chunks of Coke instead of 1 Gem.


6.12.00:
[FIXED] A long going Bug in the Steam Engines that made it impossible to run them at their upper Limit. I would still not recommend running them that fast but I cant tell you what not to do.
[CHANGED] Large Fermenter Auto-Emits Items and Fluids at different places now. Fluids stay at the same Position, Items are one block above.
[CHANGED] Reactors can explode now.
[CHANGED] Welder has 9 Input slots now. Because someone wanted to add GalaxySpace Recipes to it via MineTweaker.
[ADDED] NEI Recipes for GT6 Machines are more sorted now thanks to a Code submission by codewarrior.
[ADDED] Singleblock Diesel Engines for easier usage of Rotational Units. They run at 16, 32, 64, 128, 256 and 512 RU/t depending on Tier.
[ADDED] A Crossing for Crucibles so that more Molds can be around one Crucible without having to use limited Universal Extenders.
[ADDED] Squeezer Multiblock, even though it was not on the List. It was easy to just copy most of the Multiblock Shredder to do this, also it was kinda needed for the Multiblock Fermenter.
[ADDED] Multiblock Heat Exchanger. 16384 HU/t split evenly over the 8 Heat Transmitter Outputs, so cover them all up in Heat Accepting things or some large Heat Accepting Multiblock.


6.11.26:
[FIXED] The Ender Garbage System and some other things, did not save their contents properly when the Server was shut down because of Forge "resetting the Item and Block IDs to what they were when the Client was started" for no reason whatsoever.
[FIXED] You can no longer jumpstart a Fusion Reaction using a cheap Fuel just to run an expensive Fuel afterwards for nearly free.
[FIXED] Multiblock Baths, Coagulators and Centrifuges were conducting Logistics like the Logistics Core.
[FIXED] Part of the NEI Lag Spike Issue when clicking the Progress Bar for the Recipe List.
[CHANGED] Normal Electrolyzer, Centrifuge, Distillery and Dryer now can process multiple things in Parallel (ofcourse for increase of overall Duration).
[CHANGED] Adamantium has its Melting Point lowered by 200K to 5225K. Now it can actually be molten properly in the highest Tier non-Adamantium Crucible.
[ADDED] More Fusion Recipes in general. Not all are useful, but if you want to get rid of Helium, you can do that now.
[ADDED] Recipe for Vibranium using the Fusion Reactor and molten Adamantium with Beryllium-7. It's also a good working Fusion Fuel as in outputs lots of Tritium, Helium-3 and Helium.
[ADDED] Multiblock Fermenter. Should make Biomass Production much less bothersome.


6.11.25:
[CHANGED] Matter Fabricator Multiblock now uses Ventilation Units instead of Galvanized Steel Walls.
[ADDED] A few new Ore Processing Recipes to make getting Lithium-6 possible and Nuclear Ore Refinement more possible.
[ADDED] Fluid Display Items can now rightclick fill Blocks with their Fluid directly.
[ADDED]
Fusion Reactor. 
An Octagon fitting in a 19x19 Square. If you dont know what an Octagon is, the Sesame Street has you covered https://www.youtube.com/watch?v=_7jpz_55EdM
The Center has a Computer shaped Cube in it.
Its GUI has Instructions as to how to build it in simple 3 Pictures. The Tooltip states how much of each thing you need.
You need to power the thing with Laser Units in order to start it up, but once it is running you can turn off the Lasers. Laser will have to go into the "Glass" Windows of the Ring.
8192 EU/t Output on the Sides that have the Electric Interface on it.
There is no Auto-Input or Auto-Output Holes for Items or Fluids on the Reactor. You have to push/pull using external Devices.


6.11.24:
[FIXED] Taped Mass Storages were voiding Items when used with the Storage Inserter. I'm sorry for anyone losing Resources due to that.
[FIXED] Botania Ingot Shiftclicking on Beacon resulting in placing Ingot instead of summoning Spirits.
[CHANGED] Adamantium, Gaia, Bedrock-HSLA and Draconium Fluid Pipes now have a higher Capacity.
[CHANGED] Chemtubes can now be used for setting Fluid Filters.
[CHANGED]
The Matter Replicators are working 256 times faster/cheaper now, and Matter Fabricators are half as fast now (to offset the cheapness).
This means the actual Matter Fabrication is gonna be the Bottleneck now, and it also means that it's easier to produce on Demand, if you have enough Matter fabricated in advance.
I did this because Multiblock Matter Replicators don't make sense since they could only do one Material at a time. (And I also dont wanna deal with all the USB Blueprint Stuff for a Multiblock)
[CHANGED] Matter Fabricators and Matter Replicators now no longer require constant Power Input and can be interrupted without losing Progress. Same goes for Time Based Machines such as Bath, Coagulator, Coke Oven, Generifier and Autoclave.
[ADDED]
Matter Fabricator Multiblock.
Accepts any Power Input between 1 and 2097152 QU/t and has a 100% Efficiency. (Made to accept ZPM Levels of Quantum Power)
Made with a crapload of Lead (to shield against "radiation" from making the Matter), Osmium Coils and Quadcore CPUs.


6.11.23:
[NOTE] I'm sick since Thursday Evening, that's why this Release is so small. I hate going outside...
[FIXED] Dust Ores were able to be Crushed due to a derp in the Code. They were supposed to be Sifting Only.
[CHANGED] Gearboxes wont jam anymore if mismatching Power enters them, but they will WASTE most of it now when mismatching! Note that Power coming from the opposite rotation will STILL jam the Gears!


6.11.22:
[NOTE] Enabled Weather and Fire Related Explosions now. But not the Overcharge ones.
[COMPAT] Plantmegapack has had some Stuff done such as its Bamboo Types being Wood Types now.
[FIXED] Mode Selector Covers now update their visuals whenever a Block Update happens. This only ever happened if someone was dumb enough to use 2 Mode Selector Covers on the same Block.
[FIXED] GalaxySpace Ores were not recognized for removal.
[CHANGED] Nerfed ZPMs again to be 500 times less, and therefore exactly as much as they were pre-buff. I decided instead of realism the ZPM has to be a convenience.
[ADDED] ZPM Dechargers. One for QU and one for EU, so you have to decide if you just wanna use a ZPM for Massfabs, or if you also wanna use it for Electricity but then inefficiently convert from EU to QU.


6.11.21:
[COMPAT] Some MineFantasy II Stuff is now supported, but not much of it.
[COMPAT] Soil Types of Enhanced Biomes now have more individualised Sifting Recipes.
[COMPAT] Water Blocks from the Streams Mod count as Infinite now, just like River Blocks. Please be aware those Stream Blocks do spawn vanilla flowing Water around them which do NOT count.
[FIXED] Compatibility with Tinkers Gregworks for GT6. It broke whenever I renamed some Variables.
[FIXED] Lack of certain Types of Support/Recipes for Milky Quartz. Now it should be just as compatible with GT6 as Nether Quartz. (This does not mean decorative Blocks yet)
[FIXED] Rails are now no longer considered Furnace Fuel even though they have Wood in them. This caused Issues with the Railcraft Tunnelbore and should be fixed now.
[FIXED] Long Distance Electric Transformers are no longer infinitely producing Energy when IC2-Exp is installed. HOW THE FUCK DID THIS NEVER GET NOTICED!?!
[CHANGED] Lazurite and Sodalite can now be used to make IC2 Coolant too.
[CHANGED] I decided Niter is now a 50:50 mix of KNO3 and NaNO3, that way mixing those two together can be used to craft it if needed. Also KNO3 and NaNO3 Ores have now been replaced by Niter Ores. Should you want to separate KNO3 and NaNO3 for certain Chemical Recipes, use a Centrifuge. Most Mixing Bowl Recipes such as Gunpowder allow Niter too now.
[CHANGED] Dryer and Distillery are no longer subject to Overclocking Penalties.
[ADDED] NEI Handler for "Other Relations" which is dedicated for random helpful Information, such as "Air Vent -> Air" or "Drain -> Water".
[ADDED] Adamantium Gearboxes and Axles as a high Tier, so that it's never impossible to transmit large amounts of Power.


6.11.20:
[NOTE]
It has been over a year since I ran at "full Energy" due to Asthma. I'm glad that my motivation is finally back in full capacity, regardless of which project I'm working on!
Like look at all this crap I got done this week. I actually did things, instead of spending half the day in Bed and working only one or two days a week, instead of almost every day now!
[COMPAT] Electrical Age had some work done Itemwise. But not gonna do its Power System.
[COMPAT] Rotarycraft Dry Ice can be made with Freezer now.
[COMPAT] The Reactorcraft Fluorite Crystals are now OreDicted by Color and properly compatible in Recipes if that matters to anyone.
[IMPROVED] There is now Code in place to make sure OreDicted Items from other Mods are not deleted from GT6 Storages (Mass Storages, Compartment Drawers, Advanced Crafting Tables, GT Chests etc, not Player Inventories!) should you uninstall those other Mods. This will fix the underlying Issue of mandatory Unification for certain badly written Mods killing your well earned Items when you uninstall them.
[FIXED] Crucible Issue with Graphite and other things that just vanished when you inserted too much of them at once.
[CHANGED] Crucibles in Galacticraft Planets now require Oxygen above them in order to be able to get Air from KU to make Steel.
[CHANGED] Ruby now uses the same Texture Set that Sapphires have to make more clear that Rubies are technically Sapphires, and so they are less confused. Ofcourse I thought about Fools Rubies too. ;)
[CHANGED] Some Glass alike Gems now use a Slimeball alike Pearly Texture.
[ADDED] Coal, Charcoal, Coke etc. Bricks. They are Ingots, meaning Nuggets and Chunks also exist for them (meaning better Fuel regulation). Vanilla Coal and Charcoal can easily be handcrafted and generified to the more shapely Bricks and vice versa.
[ADDED] Ingots, Plates and Gem Plates can now be placed in World.
[ADDED] The 9 Colors of Fluorite aswell as the 6 Colors of Aventurine as small Ores.
[ADDED] Milky Quartz Rock Layer. Purpose being a Surface Level Quartz Ore so you dont need to go to the Nether. Totally not because Rockhounding has that Quartz Type and I did not add a Surface Quartz yet. (Quartzite does not count)


6.11.19:
[COMPAT] Wood and some Gems from the Rockhounding Mod are now supported.
[CHANGED] The Iron Recipes now require Carbon instead of Dark Ash (well a Carbon Version of those Recipes already existed before), and Carbon can be supplied by Coal and Charcoal now. This made Dark Ash much more useless, but also stops you from burning shit for the sake of getting Dark Ash. I looked up the IRL Recipes again and did not see the need to introduce a new Flux.
[ADDED] Hammers can now be placed on Anvils.
[ADDED] Pink Diamonds, Jasper and TigerEyes to Ore Gen in Stone Layers. Pink Diamonds are super rare and Jungle specific btw.


6.11.18:
[FIXED] A bunch of Multiblock Crucible Bugs.
[CHANGED] Steel is now made with Air instead of Carbon! In order to supply Air to the Crucible just slap an Engine at it and pump in Kinetic Units (KU).
[ADDED] Made Royal Jelly a Liquid now. Should make some Recipes work better. Royal Jelly can be used like Honey in a lot of Recipes, usually being 10 times more efficient in some way or another.


6.11.17:
[BUG] Alloying Stuff in the Multiblock Crucible can sometimes dupe Resources. Version is hidden due to that.
[FIXED] Long Distance Wires/Pipes now actually know when they are a Receiver when they are looked at with Magnifying Glass.
[ADDED]
3x3x3 Multiblock Crucible.
It is 100 Units in Size with 27*16=432 Units of Capacity.
The Heat Efficiency compared to a normal filled Crucible is about twice as good, as long as your Batches are large enough.
Due to me experimenting with Rendering and not wanting to go back on it (way too stupid effort to make it work properly), the Crucible will permanently glow in the Dark to prevent Light glitches, may have a bit of Z Fighting at the Top Corners, and might render Covers weirdly.


6.11.16:
[CHANGED] Bars Block now lets Items, XP Orbs and Projectiles (Arrows and such, somehow Enderpearls and Snowballs don't work) go through from all directions, making it more useful for Mob Grinders and physical Defenses.
[CHANGED] Cryo Distillation Tower now Outputs condensed Ice. Let's just assume that no matter where the Air comes from, there is at least a little bit of humidity. Also Dark Ash for Nether Air.
[ADDED] Recipe for Carbon Pipes in the Nanoscale Fabricator.
[ADDED] Tantalum Hafnium Carbide, Melting Point 4263K. Crucible Equipment, Burning Boxes, Pipes, Drums and Capsule Cell Containers included.


6.11.15:
[FIXED] Axles were not saving their Rotation Direction, leading to weird Low Power Issues after loading a Chunk.
[REMOVED] Dirty/Impure/Pure Piles of Dust do no longer generate Items unless they are from Stone. This will make existing Impure Piles useless, so get rid of them. (Note: I already removed them from all outputs, so you should actually not have any that stem from non-Stone anymore, except the ones from Mekanism if that is installed, but those stay too)
[CHANGED] Small Ores will now drop regular Dust instead of Impure Dust as a Stone Byproduct Pile. This means Impure Bedrock Dust should be the only Impure Pile of Dust to remain obtainable.
[CHANGED] Certus Quartz and Nether Quartz related Sifting Recipes are now more streamlined, and also dont output Charged Certus Quartz Dust anymore.
[CHANGED] The Ore Byproduct List now has the Indicator Rocks instead of Purified Dusts.
[CHANGED] Regular Distillation Tower now uses Nonuple Pipes instead of Gears in its Recipe.
[CHANGED] Both Distillation Towers now accept Energy Sideways if it comes into the Heat Acceptors.
[ADDED] Cryo Distillation Tower for separating Air into other Gasses. The old Centrifuge Recipe for that does no longer exist.
[ADDED] Large Autoclave. Can do 16 Processes at once.


6.11.14:
[FIXED] Mekanisms Fake Osmium being used in Mixer Alloying Recipes for Osmiridium instead of Real Osmium by accident.
[CHANGED] Mixer Recipes for Alloys now have a Selector Tag in them! (Also added a few useful shortcuts for Mixer Alloying thanks to that)
[CHANGED] Bark can now be used to make Paper and Dynamite.
[ADDED] Hand Drill for basically drilling Trees.
[ADDED] Geiger Counter for measuring Neutron Levels in Reactors.
[ADDED] Fluid Slot to the Welder because someone requested it. I don't have much use but maybe someone with MineTweaker wants to do something.
[ADDED] Liquid Methane from GalaxySpace should now be convertable to 643 Liters of regular Gaseous Methane.
[ADDED]
1x1 Reactor Core.
Yes it is possible to mix 1x1 and 2x2 Reactors with each other
But apart from saving Material on Neutron Reflectors and Absorbers its not THAT useful to mix them, because fuel Rods in a 1x1 Reactor will split their Neutron Emissions in half over the 2 adjacent Slots of the 2x2 Reactor.


6.11.13:
[BROKEN] DO NOT USE!


6.11.12:
[CHANGED] Chloroplatinic and Chloroauric Acids, and Stannic Chloride now have slightly different electrolyzation Recipes. Still not quite realistic from what I found out from comments afterwards, but definitely more convenient.
[ADDED] Vanilla Cobble Stairs and Slabs should be possible with GT6 Rock Types now in a 2x2 Grid. Please be aware that Cobble Slabs need to use the lower half of the 2x2 Grid to craft.
[ADDED] Ludicrite Rod from that one Reactor Mod, just forgot to add it. XD
[ADDED] Recipe for Frost Hazmat using Asbestos Plates.


6.11.11:
[ADDED] Recipes for Coolant when IC2-Exp is not installed. Also Coolant Recipes can be seen when looking up Recipes for the Reactor Core in NEI.
[ADDED] Indium as a Byproduct of Sphalerite and Chalcopyrite, because it is needed for Neutron Absorbers.
[ADDED]
Nuclear Reactor and all the relevant Rods.
So here is the thing, this Reactor is more me experimenting with Game Mechanics than actually being balanced and well thought out. So please experiment with it in Creative, and have Fun with it. ^^
The IC2-Type Coolant will be used for keeping this current Bbehavior, other Coolants might have different Mechanics in the future, this way I wont fuck over pre-existing Setups.
I had to nerf it multiple times and while the Math is very simple in the Code, it's still complicated in the real World, and found out the easiest way by literally experimenting around, but I will give it a shot at explaining:
Every Nuclear Fuel Rod emits X Neutrons to each of the 4 Rods around it, and Y Neutrons to itself. Thing is, the more Neutrons a Rod has (compared to its Self Neutrons), the more Neutrons it will emit too!
This would obviously lead to exponential growth, if I didn't divide the emitted extra Neutrons by a Number that is six or greater (also seen as "Factor" in the Tooltip).
The Fuel Rods have Tooltips for showing the IRL Time they have left and the Neutron related Stats.
Also available are Neutron Reflector Rods, which simply reflect the Neutrons sent outside by a Rod back onto the Rod itself.
And Neutron Absorber Rods that emit twice the Power per Neutron, compared to a Fuel Rod, but at the cost of not using the Neutrons to increase overall Output. I found that actually increases Fuel Efficiency in some cases even.
Depleted Fuel Rods will later be Centrifugable into more Byproducts than now. I just didn't research the appropriate Byproducts yet on Wikipedia.
As long as you have enough Coolant inside the Reactor to convert all the Heat into Hot Coolant, also called Heatant, it will never explode. Also make sure the Heatant doesn't build up either.
But please wear a Radiation Compatible Hazard Suit whenever you get close to it, because it does radiate every living being closeby depending on how powerful the Fuel is.
The Reactor Core can also easily be used like an RTG from IC2 if you use Low Power Fuels with it and place a Heat Exchanger right ontop of it, might even be useful as a Crucible Heater.
It can be turned ON/OFF by other Machines, it can also turn single Rods ON/OFF using Mode Switches.
Hot and Cold Coolant can be emitted from the Reactor Core using the Primary and Secondary Facing. This makes Reactor Building more convenient.
Rightclick on the Top to insert Rod, Pincers to extract Rods. Make sure the Reactor is OFF while inserting Stuffs or it will instantly start running.
Hint: It is possible to use Universal Extenders and Mini Portals to do all sorts of whacky bullshit with the Reactor.


6.11.10:
[FIXED] A Burning Box Issue in the previous broken Version.
[FIXED] A Bug that made any Burning Boxes and the Heat Exchanger consume half as much Fuel as they should. The Rate of Output was unaffected.
[FIXED] GT6 Bar Placement Code for the Catwalk/Cage Bars Block.
[FIXED] The Activity Detector for "Running Possible" did not work on Rotational Pumps.
[CHANGED] Tungsten Burning Boxes and Heat Exchangers now have 100% instead of 95% Efficiency.
[CHANGED] Note: Later nerfed back again! - Newly generated ZPMs now have 500 times more Energy (10^15 QU), due to me realising just how ridiculous Nuclear Energy is going to be in regards of Power with 36 000 000 000 HU per Unit of U-235 at Max Efficiency.
[CHANGED] I decided to give Lead a darker Color.
[ADDED] Diamond and Sapphire Mortars. Gating them behind Iron doesn't make that much sense.
[WIP] Nuclear Reactor Core. Not gonna be done this week... Took a bit too long to do, and I want to setup random new irl hardware first. XD


6.11.09:
[BROKEN] DO NOT USE!


6.11.08:
[BROKEN] DO NOT USE!


6.11.07:
[NOTE] The Default Config Pack updated to disable Pneumaticrafts crashy Oil Worldgen (still crashes sometimes though...) and its Update Checker.
[CHANGED] Fluid Tooltips now also show Temperature in Celsius in addition to Kelvin.
[ADDED] IC2 Coolant Fluids when IC2 isn't installed.
[ADDED] Lead Multiblock Walls.
[ADDED] Rocks and vanilla Sticks can now be placed on the Floor with Sneak Click.


6.11.06:
[NOTE] I know its not much but the Heat and some busy IRL Stuff got me, it should get colder next week. ;)
[CHANGED] Multiblock Wall Welding Recipes are now Selector Tag 10 instead of 4.
[ADDED] Mortarable Crushed Ores can now be Anvil Hammered into Dust.
[ADDED] A Bronze/Brass/BismuthBronze Compound Item that can be made with Nuggets, Scraps, Tiny Dusts in the Mixing Bowl.


6.11.05:
[COMPAT] Added some more Stuff for Infinity Ingots, Awakened Draconium and Desh to be used for in GT6.
[FIXED] A few less important things.
[ADDED] Logistics Tanks with 1 Million Liter Capacity. Also capable of storing pretty much anything, even Plasma and really hot Stuffs, due to having a Field Emitter in the Recipe.
[ADDED] Regulator Mode to Item Import and Item Export Covers. Use Wirecutter to set the exact Stacksize. Also works with the Generic Imports and Exports for Items.


6.11.04:
[COMPAT] The Lootbags Mod can now have its Bags unboxed by the Unboxinator.
[FIXED] A few less important things.
[CHANGED] The Unboxinator now has 12 Output Slots.
[ADDED] The Smelter can now turn Molten Copper into Molten Annealed Copper, and Molten Iron into Molten Wrought Iron.
[ADDED] Logistics Mass Storage, works like regular Mass Storage, but also conducts the Logistics Signal to adjacent Blocks like Logistics Wire, while connecting the Storage itself to the Logistics Network too.
[ADDED] Logistics CPU Display Covers that show the load of each Type of CPU Core so that you know what to upgrade next. They also emit Redstone based on CPU Load.


6.11.03:
[FIXED] Mass Storages were causing a graphical update every second regardless of their Number changing or not. That was a nice FPS and Network Lag...
[FIXED] Mode Selector Covers did not save their Modes when already attached to a Block that you just placed, despite them visually showing the Mode.
[CHANGED] The Distillation Tower now accepts more Power per tick. This should make everything able to run faster if so desired.
[CHANGED] You now get 10 times more Seed Oil from Seeds. The Forestry Ratio is just way too low. The Immersive Engineering Plant Oil from generification got adjusted too though!
[ADDED]
Logistics Core
I decided to make the Core Cheaper by making it possible to use Galvanized Walls instead of CPUs. You still need one of each (like just one Versatile one), but you wont need 27 Quadcore Units anymore.
You can also have multiple Logistics Cores for one Network if you really run out of CPU space or something.
Consumes 20EU/t + (1EU/t per CPU) regardless of if it does something or not, 1EU per moved Item (so 64EU per full Stack), and 1EU per moved 250L (it will round up to 1EU if it is less than 250L).
There is Logistics Wire specific Covers for marking things as Import/Export/Storage. These Covers can also be placed on the Logistics Core itself.
Each of these 3 Types of Marking has 3 additional Subtypes, Generic/FilteredFluid/FilteredItem. You will be able to Intercraft them easily.
When you attach a Generic Typed Storage or Export Cover to the Side of the Wire that faces a Mass Storage or GT6 Filter Block, it will count the Cover as Semi-Filtered for sake of Priorities.
If you use Miniature Portals to make the Logistics Network go into a different Dimension or simply out of Range, the Range at that Destination is always limited to one Block, so make as much out of that one Block as you can.
The Logistics Core will perform one Action per Second per Logic Core. An Action only counts if something has actually been moved.
Fluids will always be handeled first and Items last for each Action.
It is going to be able to perform the following Actions for now in this order:
 Generic  Import  -> Filtered Export
 Filtered Import  -> Filtered Export
 Generic  Import  -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
 Filtered Import  -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
 Generic  Import  -> Generic Export
 Filtered Import  -> Generic Export
 Generic  Import  -> Filtered Storage
 Filtered Import  -> Filtered Storage
 Generic  Import  -> Semi-Filtered Storage (Mass Storages for example)
 Filtered Import  -> Semi-Filtered Storage (Mass Storages for example)
 Generic  Import  -> Generic Storage
 Filtered Import  -> Generic Storage
 Generic  Storage -> Filtered Export
 Filtered Storage -> Filtered Export
 Generic  Storage -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
 Filtered Storage -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
 Generic  Storage -> Generic Export
 Filtered Storage -> Generic Export
 Generic  Storage -> Filtered Storage
 Generic  Storage -> Semi-Filtered Storage (Mass Storages for example)
 Unknown  Stuff   -> Dump Bus (that is where random Stuff goes, that got inserted by accident or so, has a dedicated Cover)
As you can see:
It will first try to take the most Generic Stuff and then try to put it into the most Filtered Target.
Direct Transactions are preferred over usage of Storage at all times.
Mass Storages count as Filtered Storages, but they are treated after all the other Filtered Storages.
The System does not like using Generic Storage and will defragment it into Filtered and Semi-Filtered Storages, when it has nothing better to do. It wont do Semi-Filtered Storage to Filtered Storage.
Should you have a Dump Bus and the System has absolutely nothing else to do, it will put everything that is not filtered anywhere into whatever is connected to it. (likely a Chest, or maybe even the Ender Garbage Bin but that would be a bit dumb, if you make changes to the System on a regular basis)
If you want to filter for more than one thing at a time, you will have to use the Filter Blocks and attach a Generic Export Bus to those.
The Priority of each Logistics Bus Cover can be adjusted with the Screwdriver if you dont wanna use the Default.


6.11.02:
[BROKEN] DO NOT USE!


6.11.01:
[BROKEN] DO NOT USE!


6.11.00:
[FIXED] Jackhammer not placing Torches anymore, the Mode Switch accidentially overrode it.
[CHANGED] Oil Shale can be used as Fuel now, it's only as efficient as non-coked Lignite, but it works. You get Stone as "Ashes" from burning it.
[CHANGED] Atlarus (Metallurgy) is now available through Bedrock Drill and UUM.
[ADDED] GT5Us three HSS Steels.
[ADDED] Osmiridium and Carborundum IV Wires.
[ADDED] Carborundum Coils (The Large Electric Oven can alternatively accept those too).
[ADDED] Roaster Recipes for Carbon Monoxide from Carbon Dioxide.
[ADDED] Railcraft H.S. Rails and Adv. Rails can now be made with Silver, Electrum or Platinum.
[WIP]
Working on the GT6 Version of an AE or Logistics System.
Note, that this isn't gonna be a Storage GUI or Crafting on Demand, this is purely for automation!
I hope I at least get the Basics done til Sunday.
You are going to need at least 12 Crystal Processors of varying Types to craft this thing, I would go and craft at least their Sockets in preparation if I were you. ;)
This thing is gonna be customizable, so which Processors you choose can determine what Type of Management it will do best/fastest.


//=== Version Number Jump due to changes in the Multiblock API ===//


6.10.28:
[NOTE] Having over 30°C three days in a row kinda did a number on me, so not much being done, at least programming wise, I did do some other Stuff in the Basement, where it's colder.
[CHANGED] All Sensors now run during the End of the Tick instead of the normal Tick, this should give much more consistent Results.
[ADDED] Tachometer Sensor for RU on Axles.


6.10.27:
[FIXED] Mini Portal Chunkloading Issues. Now I just validate the Targets more often.
[FIXED] Some Mining Tools were not able to harvest Silverfish properly.
[FIXED] A Bug that made the Dust Funnel way slower than intended. It essentially only converted one single Item per tick into Dust Percentage, instead of one Stack.
[FIXED] Some Redstone thing with the Mini Portals. They should now react faster to the other end being disabled or unloaded.
[CHANGED] The Higher Tier Smelters now use Carbon Crucibles instead of Tungsten Crucibles.
[CHANGED] Ender Air when centrifuged gives Krypton, Xenon and Radon now, instead of Helium, Neon and Argon.
[CHANGED] Dust Funnel wont do visual Updates anymore if an opaque Cover is ontop of it.
[ADDED] Titanium-Gold Alloy can now be mixed.
[ADDED] Ender Bumblebees can now produce Chorus Fruit, and Chorus Fruit can now be mixed with any type of Fire Potions to make Dragon Breath when Et Futurum is installed.
[ADDED] Jackhammer Mode to not break Ores while mining. Note that this does not apply to those huge Layers of Salt, Coal or Bauxite Ore and the likes, only regular Ores.
[ADDED] Wooden Buckets for Maple and Rainbow Sap. They can both be used to make Torches too now. The Glass Bottle Variants work too. Also String has no longer to be crafted into Wool to make those Torches.
[ADDED]
Nanoscale Fabricator, or Nanofab for short.
The Main Purpose of this Device is to create certain Graphene based Products such as Wires, Foils, Plates and Rotors out of Carbon Dust (see Sugar+Acid Recipe).
This makes the creation of several previously unavailable Graphene Products possible. Including the Carbon Crucibles and their Accessories (Yes I know those usually are Graphite, but this is more of a Game Balance than a Realism thing).
IC2 Carbon Fibers got way nerfed as they are now behind the Nanofab in the Tech Tree.
You will need Small Lasers in the Crafting Recipe and Electricity for this to work. Because of the precision required I wont let this thing run on LU, as that is more for large Scale Production like Welding.


6.10.26:
[NOTE] I'll probably have to reschedule the Release Cycle in some way, due to real life Stuff that takes away two days of each week from now on. Most likely Sunday/Monday. (Ended up being Monday because Build Server broke)
[COMPAT] The Mineralogy Mods Rock Types are now generated with my Stone Layer Generator. Note that I wouldn't recommend that Mod as it generates Sand, Sandstone and Gravel Layers that I cannot replace.
[FIXED] Universal Spade wasnt able to harvest all Rails like the Crowbar was.
[FIXED] You can now put Covers on the Top of Hoppers and Queue Hoppers.
[CHANGED] Ores from Ore-Layers, such as Salts, Coals or Bauxite, are now considered Dense Ores, effectively doubling the amount of Crushed Ore you get from them, and therefore halving the amount of time you need to spend mining at entire Layers.
[CHANGED] The Crusher now has 6 Output Slots instead of just 4, which should make the Dense Ores from Ore Layers better to configure.
[CHANGED] Germanium can now be used as substitute for Silicon in Circuit Recipes. Yes you can make Germanium Boules now.
[CHANGED] The First Tier of Crystallization Crucible now uses a Quartz Crucible in its Recipe instead of Iridium.
[ADDED] You can now generify any OreDict registered Fish to the original Vanilla Fish.
[ADDED] Tier 3 Crystallization Crucibles can now do colored Sapphire Boules. The Lathe can also make Boules into Long Rods now.


6.10.25:
[NOTE] Soul Forest is not going to get Compat, it's just too bad of a Mod... Putting Titanium Gems on Sticks to make Titanium Rods just overdid it for me, along with giving each item its own ID and not registering literally anything to the OreDict, not that I would want it considering all the exploits that would cause...
[COMPAT] Funky Locomotion should now accept GT6 Electricity just like it does RF. Don't be scared about wasting Power, those Motors just have a huge Storage of 64000 EU (= 256000 RF).
[COMPAT] Abyssalcraft Material Data has been added. And some similar standard Greg Compat Stuff such as using Spades on its Grass Types.
[FIXED] Fluid Densities did not match up with the Material Densities, but now they do.
[FIXED] Electrolyzation of Eudialite is now possible.
[ADDED]
Implosion Compressor
It works almost just like in ye olden Days. You insert Gem Dust, a Selector Tag and your Explosive of choice and BOOM it makes the Gem or Gem Plate you wanted.
There will ofcourse be Noise complaints as this thing definitely does an Explosion Sound anytime it processes something.
The Multiblock is a hollow 3x3x3 of Dense Tungstensteel Walls, while the Main Block is centered at the Bottom of whichever Side it is facing.
Explosives Supported are Vanilla TNT (8), IC2 ITNT (4), GT6 Dynamite (2) and GT6 Strong Dynamite (1).


6.10.24:
[FIXED] The Fluid Rendering Height Fix from 6.10.19 was slightly broken for other Mods. Now it works again.
[FIXED] Some Furnace Recipes being missing due to me accidentially removing most of my own Furnace Recipes in the previous Versions change. XD
[CHANGED] Smelting any Material based thing other than Dusts and Scraps in a Furnace should give EXP depending on the Tool Quality of the Material + 1.
[CHANGED] IC2-Exp "Pahoehoe Lava" will now give slightly more outputs when centrifuged, but also uses more Power.
[ADDED] The Generifier can now turn IC2 Steam and IC2 Superheated Steam into regular Steam. It will go by the Power Value instead of the Water Amount, so no Power is lost, but it might lead to an infinite Water Recipe Loop or at least a "Turn Power into Water" Loop.
[ADDED]
Heat Exchangers
Turns Lava into "Pahoehoe Lava" and generates 80 HU/L or 80000 HU/Block from it. Same goes for IC2 Hot Coolant to Coolant (20HU/L).
Be aware that IC2 Hot Coolant cannot be stored in GT6 Tanks, as it is a power conducting Fluid just like Steam!


6.10.23:
[IMPROVED] The Loading Step for Prefix Listeners is now much faster as I just casually kill off all MC Furnace Recipes that could potentially be wrong (dust to ingot, ore to ingot, ore to gem, etc) and then simply add the proper ones back later, so I do not have to check each and every Recipe individually.
[FIXED] Adamantium, Vibranium, Gravitonium and McGuffium were not UUM fabricateable. (if you ever happen to find samples of them ofcourse)
[CHANGED] Vitriol Electrolyzation Recipe. Now requires Selector Tag 1 instead of 0 and also needs Water, but it will output Sulfuric Acid directly.
[CHANGED] Mn + H2O to MnO2 Recipe now outputs Hydrogen.
[ADDED] The Lightning Processor can now produce H2O2, K2SO4 and Na2SO4.
[ADDED]
Duct Tape!
For the purpose of making Mass Storages carryable.
Note that depending on the amount of stored Items more durability is used in order to make the Mass Storage moveable.
There is multiple Tiers of Tape, the basic one having a Limit of 10000, Duct Tape having 100000 and "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84" having a Limit of 10 Million.
The Tapes act like Tools, similar to Sprays, so I might add a functionality for using Tape on other things.
You have to manually remove the Tape from a placed Mass Storage or Item Barrel again to use them. For this you can use Scissors or Knives.
Partial Dusts and Stuff will not be picked up and just drop on the Floor.


6.10.22:
[FIXED] Bushes were not working in Plant Pots.
[FIXED] Very few PNG Files were somehow "grayscale" instead of "grayscale+alpha" resulting in java somehow messing up Transparency, even though all Image Editors could still see the transparency.
[FIXED] Even fewer PNG Files were somehow much brighter than they were supposed to be, such as Gray colored Black Sand.
[CHANGED] Circuits now have Roman Numerals on the top left of their Texture making Tiers easier to see at a quick glance.
[CHANGED] Several Purified Ore Acid Bathing Recipes now output slightly more Stuff. Platinum Group Sludge got buffed too after I found out it gives 12 times less Sludge than other Methods of getting Iridium from the same Ore...
[ADDED] Prismarine Pylons to Deep Ocean Biomes in order to make Prismarine available.
[ADDED]
4 Shades of Grass (they are all a Shade of Green).
The Grass doesn't spread, get eaten, need Light, spawn Mobs nor change its color because Biome.
It is crafted with 8 pieces of regular Grass (use a Spade for Silk Touching it) and a Dye (Green, Lime, Black or Light Gray). Also possible in the Bathing Pot to save on Dyes.
[ADDED]
Item Retriever Cover.
Similar to the Item Filter Cover it can only Filter for 1 Item (or Filter for all but 1 Item).
Though you can just not Filter at all and make it take all Items stack by stack.
It can only be placed on Item Pipes, and will pull Items from Inventories connected to the Pipe Network.


6.10.21:
[IMPROVED]
Ran all PNG Files through a PNG Optimizer to reduce Filesize (optipng).
This reduced the total size of the GT6 Jar from 17.8 MiB to 15.7 MiB. (16.4 MiB after fixing the Issues next Version...)
There might be some broken Textures where Minecraft somehow does not recognize Transparency.
Please report any findings of this sort.
Found so far: The Number "1" on Sensors and Mass Storages, all crushed Ores, Berry Bushes and Rainbow Tree Leaves.
[FIXED] Fixed unavailability of HOP Graphite due to Coal Coke Dust not being Unificated.
[FIXED] Maricultures Natural Gas Bottles were not obtainable through GT6 Natural Gas.
[ADDED] Config for adding a minimum distance to 0,0 for the old type Worldgen Large Ores.
[ADDED] Universal Plant Pot. Basically a way to place any Type of Crop (except IC2 Crops because they are fucked up) ontop of a single decorative yet slightly difficult to craft Block.


6.10.20:
[FIXED] a Serverside Crash because Mojang accesses Render IDs for Pathfinding on the Server...


6.10.19:
[WARNING] IC2 Hazmat Suits wont protect against Heat, Frost or Bumblebees anymore. I now have my own Suits that are better "suit"ed.
[FIXED] GT6 Fluid Rendering Height, I also fixed it for a lot of Default Forge Fluids aswell, because the Default Rendering ID of them was easy to change.
[CHANGED] Al2O3 (Alumina) in Fluid form has now 504 Liters per Unit instead of 144 Liters. This is to make it divisible by 7.
[CHANGED] GT6 Fluid Springs for Oil and Natural Gas will now place more compressed 2000L Fluids per Block. In case of Oil the Fluid will turn into a short Fountain.
[CHANGED] Oil and Natural Gas Fluid Blocks are now more flammable, when coming in contact with Lava or Fire Blocks. (but still not explosive in any way, also still wont do this for Torches, that would be too cruel)
[ADDED] Redstone Conductor Covers, one of them takes Redstone in from the outside, the other emits whatever that Cover receives outwards. This should fix the Issue of not being able to Redstone Control Multiblocks by just adding those Covers to the Multiblock Walls to transmit Redstone through them.
[ADDED]
Hazard Suits that protect you from specific Hazards.
The Heat Hazmat doesn't do Lava and Fire yet.
The Bumblesuit does protect against Forestry Bees too.
The Recipes for the Bumblesuit and the Frost Protection Suit are not finished yet, though the Bumblesuit does have a Rubber Foil based Recipe until I do some woven cloth stuff.


6.10.18:
[IMPROVED] All Fluid Tanks in GT6 are now using Long instead of Integer. Everything should still work properly. I seriously hope it does! (I had to scan through a ton of code to make sure of it)
[FIXED] Rendering of Errored Blocks to no longer be invisible.
[FIXED] Sodium Persulfate Washing and Mercury Washing of certain Ores.
[FIXED] Fluid Pipes were treating non-matching Filters on adjacent Pipes like a valid target, but got stuck because they couldn't actually transfer the Fluid.
[CHANGED] Railcraft Recipe Lists have their Outputs Unificated now.
[CHANGED] Magnifying Glass now shows the Wrenching Overlay when held.
[ADDED] Press Recipes to make the other 3 types of Rails that Railcraft has.
[ADDED] Laser Engraver Recipe for turning Firestone Gems into Cut Firestone, so you dont need 4 Diamond Pickaxes to do that once you reach high Power Lasers and have a cyan Lens.
[ADDED] Welder Recipes for the Multiblock Wall Parts. The Simple ones can still be made with Wrench and Hammer, but the Dense ones require a Welder now.
[ADDED] The Crusher and Shredder Multiblocks now deal damage to people ontop of them while active.
[ADDED] 5x5x5 Metal Fluid Tanks. Work pretty much like the 3x3x3 ones.


6.10.17:
[FIXED] Item Filter Covers on Item Pipes did not work in inverted Mode.
[FIXED] Item Pipes and Wires with Covers on them shredder properly now.
[FIXED] Multiblock Sided IN/OUT Information Tooltips by simply removing them and adding that Info to the Multiblocks Construction Tooltip.
[ADDED] Potion Effects to Swampwater, Oil and Natural Gas when you dont wear an appropriate Hazmat Suit.


6.10.16:
[CHANGED] The Multi-Ingot to Plate, Foil, Item Casings, Small Spring and Ring Recipes in the Anvil now output Scraps in order to be less wasteful.
[ADDED] Configs to make Machines just break instead of Explode, when overcharged or getting in contact with weather. Boilers still explode though.
[ADDED]
Multiblock Sluice
Made mostly out of Titanium
Is 3x7x3 in Size.
Can Process 64 of a Recipe in Parallel.
Powered by RU, with 50% Efficiency though ofcourse no Overclocking Penalty.
[ADDED]
Multiblock Crusher
Made mostly out of Tungstensteel
Is 5x5x3 in Size
Can Process 64 of a Recipe in Parallel.
Powered by RU, with 50% Efficiency though ofcourse no Overclocking Penalty.
[ADDED]
Multiblock Shredder
Made mostly out of Tungstensteel
Is 5x5x3 in Size
Can Process 64 of a Recipe in Parallel.
Powered by RU, with 50% Efficiency though ofcourse no Overclocking Penalty.


6.10.15:
[FIXED] Minetweaker couldn't manipulate Loot Tables due to my Loot Chest Replacer.
[CHANGED] Shredder Recipes for Materials that the Mortar can handle are now 16 times faster (and therefore cheaper).
[CHANGED] Multiblock Mixer and Centrifuge can now be powered from the Top Center Position too. Note, that the Top Center is now also "Power Only" and wont accept Items or Fluids.
[ADDED] Invar Tanks, Drums and Large Boilers. Same Stats as the Stainless ones, but not Acid Proof. Less reason to complain about Stainless Steel being used EVERYWHERE. :P
[ADDED]
Large Electric Oven.
Made of 18 Invar Walls and 8 Nichrome Coil Blocks in an overall Sandwich shaped Hollow 3x3x3.
It can run up to 64 Recipes in Parallel, though at its maximum Input of 4096 EU/t it can only do 4 Recipes per tick overall (80 Smelts per second), so the 64 is more for performance like in many other Machines.
It has a 75% penalty to Efficiency due to the 50% of EU -> HU Conversion and due to the 50% I put on most Processing Multiblocks as offset to the lack of overclocking Penalty. It's still half as efficient as a regular HU powered Oven, when used with Electricity.
[ADDED]
Multiblock Bathing Vat.
The Size is 5x5x2, so 50 Blocks of total Volume.
Can process up to 64 Bathing Recipes at once without increasing the Duration at all, so it acts like 64 Baths working in Parallel.
It's not powered so no Overclocking Penalty or Input Stats.
[ADDED]
Multiblock Coagulator Array.
The Size is 5x5x2, so 50 Blocks of total Volume.
Can process up to 64 Coagulation Recipes at once without increasing the Duration at all, so it acts like 64 Coagulators working in Parallel.
It's not powered so no Overclocking Penalty or Input Stats.


6.10.14:
[CHANGED] Multiblock Centrifuge can now process 16 rounds in parallel. This should make the cheap Recipes that cap at 1 process per tick more viable.
[ADDED] Multiblock Mixer, which works similar to the Centrifuge and can also process up to 256 of the same Recipe at once. (64 or less if the Recipe consumes Items, because stacksizes)
[ADDED]
Multiblock Electrolyzer.
Accepts anything between 512 and 4096 EU.
It has a general 50% Efficiency penalty.
BUT it has the Overclocking penalty removed entirely.
Works almost exactly like the Multiblock Centrifuge, just with Electrolyzer Parts instead of Centrifuge Parts.
Runs on Electricity, and whenever it starts up, it will change the color of the upper "Tanks" randomly, with a selection between Red, Green, Blue, Cyan, Magenta and Yellow.


6.10.13:
[IMPROVED] The Crusher now uses the Recipe Handler System instead of being hardcoded for Ore Crushing. May need some testing if everything works still. Also it now uses 2 Slots for outputting the same Crushed Ore, so "cheaty" Ore Multipliers in the Config work twice as good!
[IMPROVED] The Shredder now also uses the Recipe Handler System for most Recipes, specifically the ones related to OreDict Prefixes, also needs some testing.
[ADDED] Configs to enable/disable GT6 Dungeon Loot, to the Worldgen Config File.


6.10.12:
[FIXED] Vertical Flow Crash caused by colliding two different Forge Finite Fluid Blocks. Now at least my own Forge based finite Fluids wont crash from this dumb Bug anymore.


6.10.11:
[NOTE] The Default Config Pack updated to disable IHLs annoying Worldgen.
[ADDED] Natural Gas Pockets to Worldgen, similar to the Oil ones. Also increased yield of newly generated Bedrock Oil Springs. And no, Torches wont make Natural Gas explode, while it would be realistic, it would also be impossible to go mining if I did that.


6.10.10:
[NOTE] That's about it... still trying to distract myself as much as possible (and yes that involves Factorio by now) and sleeping way too much. Did some Spring Cleaning so that's nice I guess. Probably gonna skip Patreon Post...
[ADDED] Voidmetal Drum, since it was missing.


6.10.09:
[NOTE]
The Copyright Directive just passed in its ENTIRETY.
The Internet is going to be dead in 2 years, if it isn't stopped last minute by Germany, France or Britain on April 15th (Sweden alone isn't enough!).
But considering Germany came up with all of this in order to buy French Votes for the Russian Gas Pipeline,
France itself is already preparing its Laws for Categorizing and Filtering a day after it has passed, before it's even through the Council,
And Britain wants to vote for it to then just blatantly blame it on Europe, like they already did in the Parliament.
I do not have high hopes...
If you are in Europe and want to keep a Free Internet, go vote in May for some Left/Socialist or Green Party, simply some Party that respects Human Rights. The Nazis and Conservatives on the Right are only going to destroy even more!
P.S. I'm already looking up alternatives for the Public Internet, like Freenet. Simply something that inherently cannot be censored, and is difficult to block unless P2P is blocked as a whole. Also thought about Tor but I'm not sure how safe that might be.
[FIXED] Power Conversion Rates for the Flux Dynamo now match the Electric Dynamo.
[FIXED] Anvils were not showing if there was an Item on them, if the Item did not have any valid ItemData.
[CHANGED] Prospecting no longer detects small Ores and now has a bigger Sample Density. Ores of normal Size are still found.
[ADDED] Antimony, Ultimet and Manganese Versions of Mass Storages, Shelves, Hoppers etc.
[ADDED] The Filter Blocks now accept Shiftclicks.


6.10.08:
[FIXED] Fluid Pipes were skipping the very first tick, resulting in Boilers slowly accumulating one tick worth of Steam over time, whenever the Chunk was loaded.
[FIXED] Red Alloy Wiring and similar were not able to connect to Covers that are on Pipes or Electric Wires.
[FIXED] The Pump Cover was not able to grab multiple different Fluids at the same tick, making the Quad-Pipe + Pump Combination unsuitable for the Gas Turbine. But now that should work.
[CHANGED] Tungsten Trioxide (WO3) now has an Ingot form so it is easier to get it out of the Crucible.
[ADDED] When you harvest Blocks using a Chisel they will be chiseled. This mainly only works for vanilla Stone, Stone Bricks and all GT6 Stones.
[ADDED] Recipes for the RF Powered Variants of Electric Energy Covnerters. Now it's no longer needed to MineTweaker them in. People can decide for themselves how balanced that is.
[ADDED]
Singleblock "EU->Other" and "RF->Other" Converters can now be adjusted with a Mode Selector Cover and similar.
This includes Heaters, Magnets, Coolers, Motors and Lasers.
Mode = 0 means Maximum, Mode = 1 means 15/16, 2 = 14/16, 8 = 1/2, 12 = 1/4, Modes above 12 may not be operable as they end up going below the Minimum Output of the Converter itself.
Should you not supply enough Power to reach the Cap that the Mode has set, the Mode wont do anything, so this only makes sense if you have too much Power going in and want to limit it.


6.10.07:
[NOTE]
Turns out, Article 13 was designed to intentionally kill off Youtube and Facebook.
Yet instead of doing that, it kills every Platform EXCEPT Youtube and Facebook, making them more powerful.
And they are fully aware about killing Freedom of Expression aswell.
https://twitter.com/Senficon/status/1105877337483169794
I'm not much into USA Stuff, but I heard the Senate over there starts to do something Article 13 alike too, so watch out for Trump trying to do that, because I don't care enough about that Continent to follow its News, I already have enough Drama as is in Europe.
[FIXED] Gas Turbine will now empty its Input Tank when there is less Fuel than needed for a full Recipe. This makes switching Fuels easier.
[CHANGED] Most Gas Fuels now give regular Water instead of distilled Water.
[ADDED]
Dedicated River Blocks for Worldgen.
This should fix some Issues, and also make Rivers slightly less dependant on the Biomes, like when Thaumcraft changes the Biome around them.
Riverwater Blocks will not contaminate adjacent Water, and can also not be contaminated by Swampwater.
They will create infinite Water Sources similar to Vanilla Water. Not that it would be needed, since all GT6 Methods of pulling Water will work without removing the Blocks.
Vanilla Buckets wont work because its near impossible to hack them in order to let them do their Job right.
But GT6 Wood Buckets, Jugs, Measuring Pots and similar, aswell as Vanilla Glass Bottles, will be able to work just fine.


6.10.06:
[FIXED] Issue where the Bars Block somehow glitched Blocks away by being placed inside them instead of on them. That was a nice Bedrock Remover. XD
[FIXED] Wooden Plank OreDict Issue when IC2-Exp is installed and removing "plankWood" Entries fro mthe OreDict for Blocks that have been registered using the Wildcard, such as Vanilla Wooden Planks.
[FIXED] Front Texture of the Multiblock Turbines. Now it's a full 3x3 Texture. But only when the Multiblock is fully formed.
[ADDED] Warning Sign Cover with currently 19 different possible Designs containing things to warn about.
[ADDED]
Gas Turbine Multiblock.
It turns Gaseous Fuels into Rotational Energy with 66.66% Efficiency, so it works just aswell as a Boiler and a Steam Turbine, but only for specific Gasses ofcourse.
But in addition to that, the Fuel is usually slightly more potent when used in a Gas Turbine, than when used in a Burning Box, so it works better overall while being cheaper too.
The Structure of the Multiblock is identical to the Steam Turbine, in fact you can upgrade the Steam Turbine Controller to be a Gas Turbine Controller with a few additional Items.
You have to get rid of the Exhaust Gasses and Liquids when running the Turbine, how you extract them is up to you. I used Pump Covers placed on Drums to do it in the Screenshot.
If you want to use this with a Bedrock Drill, remember you can downtransform the Power and also inject it into multiple Sides of the Drill.


6.10.05:
[NOTE]
Thank Speiger for me not spending Wednesday with Modding like I wanted, by reminding me of the existence of every damn Twitter Post about Article 11, 12 and 13.
I always try to stay away from drama whenever possible, and it's already enough for me to check https://juliareda.eu every time I check my frikkin E-mail (which is like 30 times a day or so), I don't want to obsessively go through the backlog of hourly updates, thank you very much...
That said, apparently Article 12 is just terrible as well, though it's more related to Publishers stealing even more money and rights from their Artists, and less related to the Internet itself.
Now we need to wait for the final vote near the end of March, so that's gonna be a bit more than 4 weeks of anxiety...
[FIXED] a Generifier Recipe Mess Up regarding generifying IC2 Biogas into Methane and back.
[IMPROVED]
Rotational Pump doesn't check for Fluids that often anymore if it is connected to a huge body of Fluid.
Also resets the internal Block List whenever it is Rotated with a Wrench.
And it doesn't iterate over all Blocks within the same Tick, instead it does one iteration step per tick.
It also does one Y-Level at a time, reducing Lag from scanning too huge amounts of Fluid at once, which for some reason lagged exponentially before.
I did test it at the bottom of a Deep Ocean and it worked fine there, so I am pretty sure this Fix worked.
[CHANGED] Quad Compartment Drawers now have a different Mode for Automation accessing them, if you use a Monkeywrench. The Left two Drawers are for example only available on the Left, the Top two Drawers only from Top, etc. Front and Back both have all 4 Drawers accessible. This makes it a bit easier to space Machines closer to each other.
[ADDED] Catalytic Cracking for Petrol and Fuel.
[ADDED] Distillation Tower Recipe for Biomass. The Distillery itself is better for pure Ethanol Output, while the Distillaton Tower is better for allround Output.
[ADDED] Recipes to turn Ethylene and Propylene into Plastic. I will not split up Polyethylene and Polypropylene into two different Materials, that just doesn't make any Gameplay sense with as little use as Plastic has.


6.10.04:
[NOTE]
https://juliareda.eu/2019/02/eu-copyright-final-text/
https://juliareda.eu/2019/02/council-worst-article-13/
Didn't think I could do something last week, just too much emotional devastation with this bullshit and waiting for someone to finally kill it...
I'm gonna go back to bed trying to skip time and distract myself until they finally made a decision...
There is btw a small Block of Text in my Game Plans about how I imagine Worldgen for the Game. It's the "The World" Paragraph.
[FIXED] Treated Planks Recipe in Bath consuming 0 Creosote, and some other Bugs with that same Recipe.
[CHANGED] The Filter Blocks can now be Inverted with the Screwdriver.
[CHANGED] The Recipe for CFoam + Scaffold = IC2 Reinforced Stone is now in the Dryer instead of the Bath.
[CHANGED] Quandrupled the Fuel Value of Natural Gas and Methane. Note, that I divided the amount of Methane from Biomass in a Fermenter by 4 aswell, same for the Generification of Methane into IC2 Biogas and back.
[ADDED] Steam Cracking of Propane and Butane into other Materials. Soon going to be needed for making Plastic. Butane is better for Plastics than Propane by the way.
[ADDED] CFoam Fluid can now be made into Blocks by first foaming it with Air in the Injector and then Drying it in the Dryer.
[ADDED]
Crates filled by the Boxinator now contain 64 Dusts/Ingots/Plates/Gems instead of 16. (unless you use Metal Chunks or Small Piles of Dust)
The old Crates that do have 16 will continue to exist in a slightly renamed way and be somewhat available as Loot in certain places, like they did before.
I added the 64-Crates instead of changing the 16-Crates, because there is an obvious exploitative Loophole people could cheat with (even if just by accident), and because I didn't want to make certain Loot even more ridiculous.
The Wooden Crates themselves stay as is, this is just a buff to make them more significant.


6.10.03:
[NOTE] Thought Article 13 was on pause for a few Months? WRONG!  https://juliareda.eu/2019/02/article-13-worse/  https://juliareda.eu/2019/02/terrorist-upload-filters/
[CHANGED] Internal GUI Code of the Advanced Crafting Table, needs some testing.
[ADDED] Cap Nozzle, which is the counterpart to the tiny Funnel, just for Gasses.
[ADDED] Molds and Crucibles now accept Cryo-Units (CU) to cool down faster. Why you would do that on a Crucible, I dont know, but for Molds and Basins this is great.
[ADDED] Gas Cylinders to GT6 Dungeons containing Propane or Helium.
[ADDED]
Button Panel back as a Cover.
It is a Selector Cover like the other Manual Selector, just more convenient.
There is multiple Designs: For example unmarked Buttons, Numeric Buttons, Hexadecimal Buttons, Bit Buttons, and a Keypad looking Set of Buttons (Numbers don't correlate to the Redstone Signal except for the 0 - 15 one!).
If there is a Controller Cover that can turn off other Covers, then it can prevent the Buttons from being pressed on demand. This also applies to the Manual Selector Cover.
The Panel will always have the Button appear Perma-Pressed that is corresponding to the Mode the attached Object is in.
If a second Mode Selector is attached to the same Block it can set which Button appears to be pressed.
The Button Panel itself can also be screwdrivered so that it automatically resets the Button to 0 after about 10 ticks, as if you pressed and released the Button like a normal person. ;)


6.10.02:
[NOTE] Got "sick" earlier this week, though unsure if that is counted as sick or an injury, wont eat certain types of Chocolate ever again because of that. At least I did not get any Blood Poisoning from that shit happening, and I'm fine again by now.
[FIXED] Tiny Covers should no longer intercept Tool Interactions.
[ADDED] Barometer Gas Cylinder, which is a Measuring Pot but for Gasses with a Capacity of 8000 Liters (because Compressed Gas)


6.10.01:
[ADDED] Turf, a Material generating similar to Black Sand that drops Peat when harvested. Found in Swampy Areas. No you cant actually make Peat like in Forestry, that is kinda unrealistic.
[ADDED] Twilight Forest now generates Clay, Turf and Black Sand.
[ADDED] Fluidized Bed Burning Boxes
Takes only specific Fuels like Charcoal/Coal/Coke/Peat and similar
Requires molten Calcite to be dumped in, this effort will result in triple the Fuel Value and half the Ashes.
Note, that it is half a Unit of Calcite per full Unit of whatever Fuel you insert, so high quality Fuels are better.
Outputs 4 times the HU/t the regular Burning Box Counterparts do, so the overall Burning Box Max Output is now 2048 HU/t, which should make one higher Tier of Large Boiler useable.


6.10.00:
[NOTE] First Step against Article 11 and 13 happened today! https://juliareda.eu/2019/01/copyright-hits_wall/
[COMPAT] Witchery needed some updated Compat things, because of all the GT6 Stuff I did since last time I added Compat for it, such as the Wood Registry.
[FIXED] Chisels no longer work while sneaking, so now the Pocket Multitool is no longer accidentially doing Stuff.
[FIXED] Placing Multiblock Parts next to an already formed Multiblock did not trigger a Force Reset for the Structural Checks. This was a problem for "expandable" Multiblocks like the Lightning Rod.
[FIXED] Tungstate Bathing in Hydrochloric Acid. (Maths Issue)
[CHANGED]
After this Version, the Version Checker should be a slight bit better automated, and no longer require me to upload a text file manually.
It will check if the Major Version Number matches with the recent one, and it will ignore Versions ending on ".00" for Notifications, because those usually contain big Changes that make everything less stable, and some Addons might not work at that time.
The Clientside Config for this can ofcourse be turned off and wont change due to this. Though I think the Notifications are sparse enough to just keep them on.
[ADDED] Rocks now have a Tooltip stating that there is their Ore closeby, making it easier to know that they indicate Ores in the first place.
[ADDED] Meat/Fish/SoylentGreen can now be used as an Ingredient for IC2 and Forestry Fertilizer. Doesn't matter if it is rotten, cooked or raw, all Variants work as long as they are ground up.
[ADDED] Phosphor containing Minerals such as Apatite can now be used for Flint and Tinder.
[ADDED] The Measuring Pot can now be Limited to certain Fluid Amounts by Rightclicking its Side with an empty Hand. (Sneaking changes the increments/decrements)


//=== Version Number Jump due to slight changes in API ===//


6.09.17:
[WARNING]
This Version needs excessive Testing for Item Movement related things in GT6, such as Item Pipes, Covers moving Items, Internal Inventory Management of the GT6 Crafting Tables and Hoppers, and more!
Be careful! I tested a lot of them for their basic Functionality and they seem to still work, but who knows!
[FIXED] Aluminium-Hydroxide to Alumina Smelting is now fixed and doesnt x2 Aluminium anymore. So it Outputs only half as much Alumina. That was a Maths Hiccup on my end back then.
[FIXED] Robot Arm Cover not working properly in certain cases. (Trying to fix this prompted me to use the Freetime I didn't waste on Factorio to actually fix the whole Inventory Item Movement System)
[FIXED] A bunch of Bugs inside the Advanced Crafting Table.


6.09.16:
[NOTE] Sorry but OvermindDL1 talked me into playing Factorio. Guess how THAT went. XD
[FIXED] A few tiny things.
[ADDED] Robot Arm Covers now insert ItemStacks into specified Slots on the Recipients Side.
[ADDED] You can now click the sides of an Anvil without a Hammer in order to "balance" the input ontop, if there is only one ItemStack.


6.09.15:
[NOTE] Merry Gregmas! :D
[FIXED] A few tiny things I stumbled upon while trying to work on the minor parts of my TODO List, just to finally get rid of those tasks. XD
[CHANGED] The Calculations for Biomass Production in the Fermenter got tweaked a little bit, so that every Recipe uses a Stacksize of 1, rather than some arbitrary Numbers that pop up because of uneven division. Also Golden Carrot Juice and Golden Apple Juices have a much higher Multiplier now.
[ADDED]
Lightning Rod Multiblock
A 3x3x5 Base and up to 100 Blocks worth of Rod Blocks (I did not want to enforce usage of Bedrock to Build Limit, so 100 is Max Efficiency)
Emitting almost 600 Million EU per Lightning Strike at LuV (32768 EU/t) with 1 to 16 Amps (yes that means it's never 0 Amps even if the Power goes nowhere, therefore wasting 32768 EU/t if you don't use the Power)
Working during Thunderstorms or Rain (at 10% in that case), without caring about the Biome it is in (so Deserts work too, even if it technically doesn't rain there, those Sandstorms can pick up quite some Charge).
Spamming these Rods is not a good Idea as it reduces their Efficiency if there is more than one Rod in a 512x512 Area centered around the Rod.
The Tip of the Rod has to be at Y >= 100 in order to work, so you always have to be touching the Sky in some way, also only Air Blocks allowed above it, so it cant be fully encased.
But you only need one Rod Block to make it work if you are up high enough (though that's a 1% per Rod Block Chance in that case), useful if you don't have that many Rod Blocks at the Beginning.
While the Rod is still containing Power, it will not let more Lightning Strikes happen to itself, as it is still charged.
And no you cant just spawn Lightning using other Mods to power this thing, that would be way too OP, but you can use the Forestry Rainmaker I guess.


6.09.14:
[COMPAT] Fossils and Archeology got a few things made more compatible. It's surprisingly compatible with the OreDict though, so I didnt have to do much with the Item Data or its Music Discs.
[COMPAT] I needed to take a look again at Highlands, because I did not register it's Trees to my Wood Dictionary yet. Glad that is over now.
[COMPAT] Added some Material Data to Candycraft Stuff.
[ADDED] NEI Recipe List for Saplings/Leaves -> Leaves/Logs/Beams/Planks/Stairs/Slabs using the GT6 Wood Dictionary.
[ADDED] Miniature CandyCraft Portal. Made of Candy Canes and activated via a piece of PEZ.


6.09.13:
[NOTE] Got the common cold or something and just want to be in bed all day... Release only contains very minor Bugfixes.


6.09.12:
[NOTE] Yes I know I did not get many different things done, I was very focussed on reworking some code.
[NOTE] The Default Config Pack updated again to disable Mekanism, Bluepower and Project Red Worldgen.
[COMPAT] Mekanism, Bluepower and Project Red got some Material Data, Ore Blocks and Bugfixes.
[API] Addons are now be able to create their own Rooms in GT6 Dungeons.
[ADDED] GT6 Dungeons now have GT6 Anvils in their Workshops.


6.09.11:
[IMPORTANT] European Copyright Bullshit gets worse and worse, please everybody yell at those damn politicians, who want to take away Freedom of Speech entirely! https://juliareda.eu/2018/11/third-trilogue/ and https://juliareda.eu/2018/11/youtube-article-13/
[COMPAT]
Fixed a few Parts of the Trainwreck that is Tropicraft. It is the shittiest Mod I have ever seen so far, and that says something...
I originally did not even want to call that "[COMPAT]", because "Fixing that they are too stupid to even make more than half of their own Crafting Recipes output the Item they are supposed to" is not what Compatibility is about...
But I mostly got through adding Material Data and Stuff to it, and ended up adding a few Minerals because of that as well.
[CHANGED] Zirconium Ore now exists as Zircon, meaning I nerfed that Stuff. :P
[CHANGED] Chainsaws now use Chains instead of Plates or Gem Plates for Crafting.
[CHANGED] Juice and Honey when used to make Biomass got buffed in order to be even remotely profitable to be used.
[IMPROVED] Removal of Crafting Recipes by Output has now less of a performance Impact. So faster Loading Times probably.
[ADDED] Tropicraft Miniature Portal. Made of Bamboo, Tropics Water and ofcourse it is activated by a Tropicraft Cocktail.
[ADDED]
My own Type of "Iron" Bars
Available Materials include: Wood, Steel, Titanium, Tungstensteel and Adamantium.
You can place the Bars kindof like Microblocks, but only in 4 directions so they work like Walls, but they only use MetaData so they can easily be pushed by Pistons!
Useful for: Guard Rails on your Catwalks and such, Making a Bird Cage (or one for any other Animal), Making a Jail Cell (Iron Doors align very well to those Bars!), Piston Doors and more!
Note: Right now there is this one stpid Vanilla Bug that just voids Rightclicks under certain Circumstances, I still need to look into that to fix it.


6.09.10:
[COMPAT] The Wastelands Mod has its Biomes Supported for spawning Sticks and Stuff.
[COMPAT] Alfheim (Botania Addon) got some Worldgen Fixes and Material Data.
[FIXED] GT6 Crashing with Alfheim installed while Thaumcraft is not installed, because Alfheim included some Thaumcraft API Files, and I did not check for all the ones I use, but only for SOME of them.
[FIXED] Air Blocks counting as Blocks that Plants can be placed on, due to a Mod Compat Derp on my End (aka: Any of the compatible Mods not being installed => Air Block got added to a HashSet => Trees spawn in the Sky)
[FIXED] Emeralds all of a sudden switching from Electrolyzing to Centrifuge Recipe in the previous Release, it's Electrolyzer again now.
[ADDED] Small Ores for Amazonite, Red Onyx and Black Onyx.
[ADDED] Enviromine Miniature Portal, is made of Black Granite and requires Exquisite Diamonds to be activated. It is literally just a plain Nether alike Cave Dimension for Overworld Ore Mining Purposes.
[ADDED] Alfheim Miniature Portal. Note that this is just a regular Miniature Portal, like all the others, this is not the Trade Portal!


6.09.09:
[NOTE] The Default Config Pack updated again to disable Enhanced Biomes Underground Worldgen.
[COMPAT] Voltz based Mods, maybe also Mekanism (not tested), are now able to be powered by GT6 EU with the proper Energy Ratios (1 EU = 10 J). Remember to only insert EU Packets that are 1024EU/t or smaller, also it could be wasteful to not use Ultra Low Voltage for things that run constantly, just like for Galacticraft. ;)
[COMPAT] Enhanced Biomes got checked for all the Stuff that could be Compatible, such as Rock Types, Wood Types and Biomes.
[BROKEN AND FIXED LATER] Trees started to generate floating in the Sky, I fixed that in the following Version.
[FIXED] Paint Slinger Lens not being required, to have Mana Bursts paint GT6 Machines, so any Mana Burst did it, even unpainted ones.
[ADDED] Ore Multiplier on things like Redstone and Lapis can now be altered in the Material Config.
[ADDED] Axes can now be used like Saws for making Slabs and Stairs.
[ADDED] Anvils now have the ability to be used as Bending Cylinders by hammering on their Sides instead of their Top.


6.09.08:
[CHANGED] Made Seed Oil and similar Plant Oils usable in the Recipes for the Wooden and the Bronze Gearboxes.
[ADDED] Chains, currently pretty much useless though. Now Qwertech doesn't need to add them anymore.
[ADDED]
Anvil for crafting Ingots into Double Ingots or Plates and that kinda Stuff.
Now it no longer uses Crafting Recipes for this purpose, instead you place 1 or 2 items on it and Hammer away at it.
There is variants made of Stone and Granite for Earlygame, and lots of different Metal Variants.
The Material you make the Anvil from will determine its Durability, the better the Material the more often you can use it, before it inevitably breaks into Scraps.
They are not Gravity affected like Vanilla Anvils, use Vanilla Anvils if you wanna dump them onto people.
Also not suitable for renaming or enchanting Stuff the Vanilla way.


6.09.07:
[CHANGED] GregTech Tools not made of Betweenlands Materials will lose 4 times the durability when breaking Blocks in the Betweenlands. Materials resistant to that Effect have a Tooltip.
[CHANGED] Wrenching a Gearbox now uses the Wrenching Sides rather than the Facing you click on.
[CHANGED] Custom Gearboxes now split the Power evenly over all Targets and can accept Power from multiple Sides as long as their absolute Speed is matching. Note, that you need to make a Tree Design for using more than one Gearbox, because Symmetry.
[CHANGED] The Sifting Table now no longer requires holding Rightclick pressed, this means you can now use Chat while sifting through Stuff. You still need to stand next to it and you are not allowed to click any other Block!
[ADDED] A Titanium-Gold Alloy that is more durable than Titanium itself.
[ADDED] A Converter Engine for Rotation Power to Kinetic Power. It works almost exactly like the Electromagnet with having 2 Outputs, but accepts RU and emits KU.


6.09.06:
[FIXED] Screwdriver Recipes being overwritten by Axle Recipes by accident.
[FIXED] Gearboxes and Axles were not being Paintable, because I accidentially grabbed the wrong RGB Color.
[FIXED] Gears attached to the Gearboxes did not count towards the contained Materials total for recycling them.
[ADDED] The Gibbl-O-Meter can now detect the total amount of Fluid inside any Basic Machine.
[ADDED] Wrench Overlay to show which Sides of a Custom Gearbox have Gears.
[ADDED] Transformer Gearboxes, they kinda work like the Electric Transformers, just with only one instead of 5 Outputs. It's a Planetary Gear by design.


6.09.05:
[FIXED] Vanilla Hoes wont till Coarse Dirt or Podzol anymore.
[FIXED] Berry Bushes will now automatically remove Snow Layers ontop of them during their first tick.
[FIXED] The Wrench/Cover Overlay Lines on Blocks now have a more visible Coloration and shouldnt vanish anymore either.
[CHANGED]
Axles will now consume one Packet of Energy to start rotating.
So the more Axles you have, the more Energy is going to be consumed when starting your Machinery.
They will also now pop onto the Floor instead of just vanishing into nothingness when overloaded.
[ADDED]
Custom Gearboxes
You can Wrench their Side while having a normal sized Gear of the same Material in your Inventory to put the Gear in.
Gears that are on adjacent Sides will automatically be interlocked, note that there is plenty of ways to do that wrong, just like in real Life.
The Monkeywrench will set the Direction the internal Axle is Facing or remove the internal Axles Functionality.
The Axle will ALWAYS be able to Rotate due to being connected via a Safety bearing, even if the Gears themselves are jammed.
It is possible to use Gearboxes to transmit power, but they are more expensive than any of the Axles of their Tier, even if you don't add any Gears.
When Gearboxes break due to overloading them, they will only break one of the Gears into Scraps but they will drop all Gears as Items and reset the internal Axle.


6.09.04:
[FIXED] Plenty of random tiny Bugs.
[ADDED]
Axles to transfer Rotational Units (RU)
They are going to be somewhat lossless (I still need to think of a good distance penalty).
It is pretty straight forward, literally, they can only go in a straight line. If you manage to somehow make an Axle even visually bend around a corner, that would be a Bug. (it wouldn't transfer power around the corner though)
There is no Gearboxes yet, lets see how long it takes for me to add those, maybe even next week.
[ADDED]
Light and Dark Prismarine Rock Types.
Including all the Stuff the usual Rock Types like Granite, Komatiite and such have.
And yes it is 2 different Types, with their own light and dark Prismarine Materials.
Currently only generate as a Part of GT6 Dungeons.


6.09.03:
[COMPAT] Botanias Paint Slinger Lens works for painting GT6 TileEntities now.
[COMPAT] Trees from Biomes o'Plenty and the Sakura Tree from the BambooMod are now Supported via the Wood Registry, and Sakura Planks can be used for Shelves and such now.
[FIXED] Dynamic Trees's Trees wont be Treecapitated twice anymore, so the harvesting slowdown from my end is removed.
[FIXED] Crafting Table Recipe from Better Beginnings did not use the OreDict Wooden Planks, so only the 6 vanilla ones were usable.
[FIXED] A few Recipes.
[CHANGED] The Wrench and Screwdriver Sounds a little regarding Tempo, Pitch and Echo.
[CHANGED] Liquid/Gas Burning Boxes now have a 25 tick cooldown whenever they actually run out of Fuel (but not when turned off any other way). During that Cooldown they will automatically ignite themselves whenever new Fuel is added, even if the Fuel is a different Type.
[CHANGED] Villager Zombies can now also drop Material Dictionaries.
[CHANGED] In addition to Crowbars you can now also use Screwdrivers to open GT6 Crates (also it's less durability eating). Not only that but now you also get the empty Crate back from this. (empty crates didnt exist when I originally made it, so guess why I forgot about it)
[ADDED] A way to turn Gravitite Gems and Dust into Enchanted Gravitite using the Injector. Later its gonna be a Magic Infuser instead of the Injector.
[ADDED] Skyroot Item and Fluid Barrels, they are just like Treated Wood Barrels in their Stats.
[ADDED] Draconium, Thaumium, Manasteel, Gaia and Syrmorite Drums. Also lots of Capsule Cell Container Materials aswell.
[ADDED] More Material Variety for Item and Fluid Pipes, including Fan Favourites such as the "Fake Osmium" Item Pipe made from Mekanism Osmium.
[ADDED]
Grass Paths like Et Futurum, but better and compatible with Biomes o' Plenty and Aether.
They also increase the Walk speed slightly (less than Asphalt!) and dont get destroyed when you place an opaque Block ontop of them.
Old Et Futurum Grass Paths will turn into GT6 ones once you walk over them, right before the walk speed calculations, so you also get the minimal movement speed bonus from converting them.
Mobs can also not spawn on these Paths.


6.09.02:
[NOTE] New Build System is in the testing, this Version is released in a new way, so expect the whatever, lol.
[FIXED] WAILA Block Names and Thaumometer scanning. Found the stupid Reason it didn't work right, so I was able to remove my provisorical fix from before.
[CHANGED] Moved the Debug Configs to the Clientside Config File.
[CHANGED] Supporter Lists are now less of a bitch for me to Update. Now I only need to change regular Text Files instead of Code Files.


6.09.01:
[NOTE]
A bunch of Dev Environment Issues have been fixed, but there still is some of them open.
I'm kinda stuck a little because I usually work after I wake up, while OvermindDL1 fixes up Dev things right before I go to Bed. (timezones and shit)
And I need certain things to be fixed before I can work properly again, otherwise I am kinda mentally blocked.
[FIXED] WAILA Block Names. Needs better fixing later.
[FIXED] Wood Registry messing up way to much. Lots of Recipes did not work because of it.
[CHANGED] Drains now produce 100 times more Water while it is raining.
[CHANGED] Railcraft Wooden Ties can now be made by bathing single already Treated Slabs (either IE or GT) in Creosote a second time. This is also way more efficient now, and allows for making Wooden Slabs into Treated Slabs.
[ADDED] Erebus Wood to the Wood Registry
[ADDED] Twilight Magical Wood to the Wood Registry, but I gave it only Vanilla Beams, because those 4 Trees are really uncommon and not worth an extra Beam and Plank Block.
[ADDED] Item and Fluid Barrels made of Shimmerwood, Greatwood and Silverwood.


6.09.00:
[NOTE] GregTech-6 is now OpenSource (LGPLv3) and on https://github.com/GregTech6/gregtech6 . Don't worry, I will still be working on GT6 myself, this is just an upgrade. ;D
[NOTE] Some GT6 Addons like QwerTech or GT Weapons Works will need to be recompiled in order to work with this Version.
[BROKEN] IC2 Sounds are currently not available due to going OpenSource, and IC2 obviously not being OpenSource. (I had to copy their Sound Files to make shit work, because they clearly didn't care to make things proper)
[BROKEN AND FIXED LATER] WAILA is somehow no longer able to display the Name of my Blocks. I wonder if it is because the WAILA API is inside the new Dev Environment, because I never did WAILA Compatibility at all.
[FIXED] Tons of minor Issues that required a massive amount of typing.
[FIXED] Treecapitation overall now also works when Aether is breaking it. Note, that this has nothing to do with the seperate Issue that Aether Trees cant be Treecapitated.
[IMPROVED] Performance in general. I kept one piece of old Code and that has wrecked a lot of Speed and RAM, but now it's fixed.
[ADDED] My own selfmade Wrench and Screwdriver Sounds.


//=== Version Number Jump because of going OpenSource at https://github.com/GregTech6/gregtech6 and some resulting refactoring - Don't worry, I will still be working on GT6 myself. ;D ===//


6.08.04:
[COMPAT] Aether
Enchanted Grass now works on GT6 Berry Bushes and doubles their Growth Speed.
Aether Enchanted Grass can now be harvested with a Spade, sadly it cannot work with regular Aether Grass...
I sadly wasn't able to fix Branch Cutter, Saw or Treecapitation for Skyroot based Trees. Aether still uses antiquated Code from like 3 or 4 major MC Versions ago, and I do not want to use ASM.
[FIXED] MineFactoryReloadeds weird Loading Order Missing Element issue.
[ADDED] Berry Bushes can finally grow on Types of Dirt and Grass that aren't vanilla.
[ADDED]
New Wood Registry, so that it is easier to handle different Mods Wood Types without causing a Clusterfuck (and yeah I am currently fixing the Clusterfuck that is Wood Handling.
Slabs and Stairs are handled way better now in regards of  Material Data and replacing their Crafting Recipes.
Stairs are now made with 3 Planks or 3 Slabs in an L Shape with a Saw in the remaining Corner, so you dont need a Crafting Table anymore, but you require a Saw now. Also you get the more logical amount of 4 Stairs for 3 Planks!
Forestry Willow Trees now have the same benefits in regards of Charcoal and Creosote, that GT6 Willow Trees have.
Binnies ExtraTrees Logs and Planks can now be used for GT6 Shelves, Panels and the likes.
Chisel Vanilla Wooden Planks now have way more Support for Recipes, so you don't always have to unchisel them for use.
Thaumcraft Greatwood and Silverwood are now their own Wood Materials and have their own Wood Beam Types.
Aether Skyroot and Twilight Darkwood now have their own Beam Types. Also the other 3 common Twilight Logs debark into their corresponding Vanilla Type Beams now.


6.08.03:
[COMPAT] Several Aether related things got adjusted and oredicted. Sadly I cannot get the Treecapitation working for Skyroot Trees. Same Issue as IC2 Rubber Trees, ancient never updated drop code.
[COMPAT] Skyroot and Weedwood Objects can now be recycled like Vanilla Stuff (Chests, Jukeboxes etc).
[CHANGED] Vanilla Iron Ore will now turn into a large amount of Hematite when crushed. No more cheaty Iron from Twilight Forest and such.


6.08.02:
[NOTE] The Default Config Pack updated to fix Enviromines noncompliance to Case Sensitive Filesystems.
[COMPAT] Lycanites and Atum
Geonachs and Stone Soldiers are now two times more vulnerable to Hammers and Clubs, just like other (Iron) Golems.
[COMPAT] The Betweenlands
GT6 Weapons made of Betweenlands Materials will no longer be damagecapped or auto-nerfed in the Betweenlands.
Weedwood Barrels can now also protect carried Liquids from rotting in the Betweenlands.
[COMPAT] The Aether
Skyroot is now a dedicated Wood Type similar to Weedwood.
Small Ores now generate in it. Note that none of the Sulfur containing Small Ores are up there.
Small Rocks now generate properly, as opposed to having the Default Overworld ones. The probability of finding a Meteorite is larger up there.
Ambrosium Torches can now be placed with GT Pickaxes, Shovels etc.
Added Miniature Aether Portal, 128m Error Margin and 1:1 Distance. Requires a GT6 Bottle of Holy Water to be activated.
[FIXED] Config File Error with /config/gregtech/gregtech.cfg
[ADDED] Crucible Sets for Umberstone, Livingrock, Holystone and Betweenstone.


6.08.01:
[NOTE] The Default Config Pack updated again to disable GalaxySpace Worldgen on the Overworld.
[NOTE] Moved everything over to Kubuntu. Also using Shell Scripts is waaay better than using Batch Files. I should have done that back on Windows, since it does have Powershell...
[FIXED] Did a minor Code Refactoring thanks to updating Eclipse to Photon.
[FIXED] IC2-Classic Iron -> Steel Exploit
[FIXED] Minor Code Issue inside the Prefix Filters readFromNBT Function. (it used the Prefix Parser instead of the way faster Prefix Map)
[FIXED] Major Code Issue that has caused about 2 frikkin Minutes of extra Startup Time! I made those 2mins into 4secs now, so yeah GT6 loads faster now. Hurray!
[IMPROVED] OreDictPrefix Parsing should take slightly less time now. Might reduce Loading Screen Times a little.
[ADDED] The Thermos Can can now protect Liquids from rotting in the Betweenlands. Now you can actually drink stuff there. ;)


6.08.00:
[DEV-NOTE] The Material Unit that is used as a base of the Material System is now a different Number, thats why major Version Number switch.
[ADDED] Tooltips for Fluid Display Items to show Fuel Values.


//=== Version Number Jump because 1. that Material Unit thing above and 2. I will post a Link to the new GregTech Forums afterwards. (And yes I do know that the Forums wont be frequented too much, but it's a nice experiment, before I start my own Game with that same Forum, for whenever I do that. The IC2 Forums are ofcourse still going to stay a valid place to talk to me. ^^) https://forum.mechaenetia.com ===//


6.07.24:
[NOTE] I might put GT6 on pause until the heat isnt killing me anymore. See https://www.patreon.com/posts/20373487 for more Details. Short Version: It should be fixed by the end of August.
[FIXED] IC2 Log Spam when its Enet Powers GT6 Machines that are already at full capacity.
[FIXED] A Mod Loading Order Issue that came up in a very recent Version.
[FIXED] Pressure Valve only worked every 2 ticks instead of every tick. (Was a leftover from the old Pipe implementation)
[CHANGED] Ocean Water Light Opacity got a new different Mechanic that makes only the topmost Ocean Blocks stop Light, while below the Sea Level it behaves like Air Light Opacity, aka none. Perfect for Underwater Bases, like in latest MC Versions.


6.07.23:
[NOTE] I was working on the Forums, going outside for way too many appointments, and also playing Sonic Mania Plus which is a great DLC. :D
[COMPAT] Immersive Engineering got a few new Recipes and Material Data, and you can now copy its Blueprints.
[COMPAT] Railcraft: added Shapeless Recipes for "GT Iron/Steel Gear + RC Tin Gear Bushing = RC Iron/Steel Gear" and "Railbeds back to 4 Ties". And also a Bathing Pot Recipe for Wooden Slabs + Creosote = Wooden Ties.
[FIXED] GT6 Dynamite Sticks blowing up the wrong way regarding drops and their Metadata.
[FIXED] Melting Point of Latex being insanely high. (it defaulted to 1000 Kelvin)
[CHANGED] Thaumcraft Aluminium Duplication now costs Lucrum instead of Ignis.
[CHANGED] The "Auto Redstone Machine Switch" Cover can now use Redstone Pulse Inputs as "produce one Process" Signal. A constant Redstone Signal will be "produce until Redstone turns off, but finish the last Process at least". Remember that certain Machines do multiple things in parallel, and they will still do that with this Cover regardless.
[CHANGED] When Railcraft is not installed, all Rails cost Treated Sticks instead of Regular Wooden Sticks.
[ADDED] Materials that can be used for Flint and Tinder. Mainly Gold, Cobalt, Nickel and Arsenic.


6.07.22:
[DEV-NOTE] The randomcode.jar updated to include Immersive Engineering and Botania Stuff.
[COMPAT] Biomes o'Plenty got some more Recipes and fixed GT6 Hoes not tilling Origin Grass or Long Grass.
[COMPAT] Immersive Engineering Machines should now directly accept GT6 Electric Power as RF. Similar to Galacticraft, Techguns, Applied Energistics and OpenModularTurrets Compat in previous Versions.
[COMPAT] GalaxySpace removed a ton of its Items in a recent Update, so I had to adjust to that... It's sad that all the cool types of Glowstone are gone now... I still detect and add compat to older Versions of it that dont have all the Items removed.
[COMPAT] Bluepower Wafers are doable using the Redstone Alloy and Teslatine Alloy Boules.
[COMPAT] Botanias Manasteel can now be made with Steel or Wrought Iron too.
[FIXED] Garbage Dump crashing itself.
[FIXED] Teslatite and Electrotine Fluids exist again after I removed them by accident.
[FIXED] Red Alloy Wires with Redstone Torches/Repeaters on, wont be connecting anymore when wirecutting them. Previously it wasted durability on connecting and then ended up disconnecting the tick afterwards.
[FIXED] Small Rocks wont spawn floating on Y = 1 if you play on a Skyblock Overworld, like the Garden of Glass setting in Botania.
[CHANGED] Spawn Zone Mob Spawn Protection does now no longer interfere with Skyblock Worlds.


6.07.21:
[NOTE] The Internet survived for now! Tune in the next few months for a probably less catastrophic version of that EU-Law!
[COMPAT] Biomes o'Plenty
Its 3 Rock Types (Limestone, Siltstone and Shale) are now generating with my Rock Layer Generator.
The different Dirt and Grass Types of it now have Sifting Recipes.
And I added some other convenience Recipes for it.
[FIXED] Some bad interactions between the Garbage Dump and Logistics Pipes.


6.07.20:
[NOTE] The Default Config Pack updated again, this time for disabling Update notifications for some 1.12 Mods that somehow get to 1.7.10 Clients.
[FIXED] Crucibles somehow spamming the Garbage with Scraps despite said Scraps not being voided.
[FIXED] Lag that probably caused the FMLProxyPacket Bug from GT6 Side got moved to yet another place. Maybe that fixes it, if it does, blame Speiger for telling me, if it doesn't, blame Bear for being too lucky and not crashing 4+ times in a row.
[FIXED] Not sure, but someone somewhen reported that using a Bathing Pot or Bath to paint a Machine or Pipe results in attached Covers being deleted. However I was not able to reproduce that and don't know where that person was, so fixed? I guess?
[FIXED] Vanilla Coarse Dirt Blocks should now actually drop themselves instead of regular Dirt, when harvested normally.
[CHANGED] Doors and Trapdoors no longer count as obstructing the Block they are next to, according to the GT6 System.
[CHANGED] Decreased the Despawn time for Apples, just like I did with Eggs and Feathers, because Growthcraft drops them everywhere.
[CHANGED] Redstone Gems can now be used directly for crafting basic Redstone related Stuff. No more direct need to Mortar it.
[CHANGED] Mixers (the Machine, not the Bowl) can now do multiple Recipes at a time. This should make some mixing Processes faster and higher Tier Mixers way more useful. Does not apply to Cryo or Burner Mixer.
[ADDED] New Texture Set for Redstone Gems and alike.
[ADDED] Botanias Livingrock can now be clubbed and crushed into Livingrock Rocks for making Level 2 Stone Tools with above average Durability out of it.
[ADDED] Bones can be hammered to 1 Bonemeal using Crafting Table. Worse than Mortar or Shredder ofcourse.


6.07.19:
[CHANGED] The Smelter now doesn't have the Overclocking Penalty anymore.
[CHANGED] Textures for Flint, Stone, Wood and Paper Scraps.
[ADDED] A Recipe for Ropes made of Hemp from Immersive Engineering.
[ADDED] A Config for disabling ZPMs spawning in GT6 Dungeons.


6.07.18:
[FIXED] Some utterly retarded Modpack Makers apparently make Lowercase Config File Names despite capitalisation being a thing in my Config File Names, resulting in their Modpacks being Linux and MacOS incompatible (a lot of other Mods do the same failing though, so it's only fixed for GT6 and its Addons). Now GT6 accepts both variants of Config Files and newly created ones will be lowercase (if there is no old capitalised ones).
[FIXED] Basic Machines have now a better check for if they can Output Fluids into their Slots.
[FIXED] GT6 Ores were generating in Villages by making Villages only have Smooth Sandstone instead of Normal Sandstone.
[FIXED] I "moved" the Recipe Unification Code from ServerStarted to ServerStarting, maybe that reduces the chance of that shitty FMLProxyPacket Bug by a little, since the Lag now happens at another Time. Bear still crashed with it, so its not a 100% fix.
[FIXED] Pick Block now roughly takes the Charge of the Battery you picked into account, instead of resulting in 0 Energy.
[REMOVED] The old Deprecated 2D EU Batteries now turn into Lead-Acid Batteries of their corresponding Tier, regardless of their previous Quality, all are gonna become Lead-Acid Batteries.
[ADDED] Extruder Recipe for Graphite Rods. Requires 512 HU/t.
[ADDED] The Boxinator can now handle Bricks and Netherbricks, and the Unboxinator can remove Books from Bookshelves.
[ADDED] Proper Support for Enviromines Cave Dimension.
[ADDED] Lithium Batteries that have 16 and 32 times the Capacity of the previous Level of Batteries.


6.07.17:
[FIXED] Rocks and Sticks will no longer be white Dots on a vanilla Map. They will now have the corresponding Stone and Wood Color.
[FIXED] Advanced Buttons gave inverted Chat Messages when changing their Glow Settings.
[FIXED] Fluid Pipes now finally work with the Plunger.
[FIXED] Ticking Order of Crucibles and Molds causing weird Issues in automated Smelteries at times.
[FIXED] Atum Sand Ores should now work properly in a Sifter.
[CHANGED] The Configs for showing and hiding Item Tooltips, Items in NEI, and similar got moved to the Clientside Only Config File.
[CHANGED] The Ender Garbage Dump and the Railcraft Admin Anchor now count as Debug Items for GT6. (Since the Admin Anchor can have a Crafting Recipe using the GT6 Config, it will not count as Debug Item when said Config is used)
[IMPROVED] Global Garbage now uses a Map for faster access of non-NBT-containing ItemStacks.
[ADDED] The Thaumometer can now be Shelved too.


6.07.16:
[NOTE] The Default Config Pack updated to contain disabled IC2 Classic Worldgen and disabled Railcraft Tanks (because their Code is broken and can wreck Servers).
[FIXED] Electric Cable/Wire Dupe Bug.
[FIXED] Zirconium Ingots not existing for some reason.
[FIXED] Prefix Filter voiding Items when shiftclicking Stuff into it, despite it not even being supposed to allow shiftclicking in the first place.
[FIXED] The Stone Layer Generator now actually checks if Chisel Marble and Limestone are enabled, before trying to generate Air Blocks, if they happen to be missing, despite Chisel being installed.
[CHANGED] Pressure Valves now work with the Global Garbage
[CHANGED] Most ways to void Fluids by breaking Mixing Bowls, Bathing Pots, Juicers, Basic Machines, Multiblock Tanks, Crucibles, Molds or Pipes (even if they break by themselves), will end up putting their Fluids into the Global Garbage now.
[ADDED] Despawned Items will now also end up in the Global Garbage.
[ADDED] Ropes
You can climb the Ropes like Ladders or Scaffolds.
They are similar to Ropes in Terraria as you can click the Rope in order to place down more Rope below (or how you stack up Scaffolds in IC2).
Ropes require a fully Opaque Block either above or next to them in order to not instantly pop off.
If you break the topmost Rope, all Ropes below will pop off, but drop at the feet of the Player who harvested them, instead of falling down into the Hole.
Crafting them is possible using Spider Silk, Plant Fibers (Loom only) and Dry Grass. This will require use of Scissors.
The Ropes can also be placed on the Floor for decorative reasons. They will not really have any function in that form, though they still work like a Slab shaped Ladder.


6.07.15:
[API] Made the Garbage Bin and Garbage Dump Storage accessible and also usable via API.
[COMPAT] Cooking for Blockheads has its Books now Shelvable. I cannot fully recommend that Mod though as it is exploitable (an Infinite Salt exploit, or removed Crafting Recipes still being there, for example), but it's still a nice to have with Harvestcraft.
[COMPAT] Better Records: URL and Multi Records are now Shelvable, and the normal URL Record is now actually visible in NEI, and craftable with other Mods Music Discs too, not just vanilla ones. Seems to be an interessting Mod, gonna List it in my Recommendeds. ^^
[COMPAT] Erebus has Bumblehives generating in it now.
[COMPAT] Twilight Forest has Bumblehives generating in it now.
[COMPAT] Atum has GT6 Ores and Desert Bumble Hives generating in it now.
[COMPAT] Botania Livingwood and Dreamwood are now way more compatible and usable for GT6 Item and Fluid Barrels.
[COMPAT] Betweenlands
Mortar Recipes for Plants got taken over to the GT6 Mortar.
GT6 Ores and Bumblehives generate in that Dimension too now.
Added Octine Crucible Set and Weedwood Barrels for both, Items and Fluids.
[FIXED] Harvestcraft Candle Crafting Recipes.
[FIXED] Not all Mortar Recipes were showing in NEI, when looking up Recipes outputting Dust.
[FIXED] Issues with the Global Garbage failing to save its Files.
[CHANGED] Global Trash (Ender Garbage Bin and Ender Garbage Dump)
Using the Plunger to delete Fluids from a Tank will cause the Fluid to go into the Global Trash instead of being entirely voided.
If a Mass Storage or Item Barrel with more than 512 Stacks inside it is broken, the previously voided surplus Stacks will now go into the Global Trash.
[CHANGED] If you rightclick any Bibliocraft thing with a GT6 Item or Tool, it wont do any of the Items or Tools Rightclick-Actions anymore.
[ADDED] Military Bumblebees that produce Combs that yield Bone Dust, they will not attack Players, but they can attack any Mob closeby, even Skeletons and Stuff that would be immune to normal Bumbles. Spoiler alert do not continue reading if you wanna find out the combo yourself, it is the combination of the highest Tiers of YDNAS&ELGNUJ (you have to read those in reverse).


6.07.14:
[NOTE] The Website now contains a Page where I listed some of the things that are useful for people who are starting new Worlds or Servers, it is linked in the install instructions. ^^
[FIXED] Some unlocalisable things.
[ADDED] Pigs can now be fed Apple Cores.
[ADDED] Extruder Shapes for Tiny Plates and Foils. Note, that the Foils one only works for things that the Clustermill cannot process!
[ADDED]
New Basic Machine, the Lightning Processor.
Used to combine Nitrogen and Oxygen into Nitrogen Monoxide, and now also to charge Certus Quartz and the TechGuns Battery.
Has the same Side Configuration as a Canning Machine.
Also make sure you get the right Voltage as some Recipes require a rather high one!


6.07.13:
[COMPAT] I fixed up some Galacticraft Loading Order Bullshit. Please check if your Galacticraft-Moon-GT6-Worldgen still works and if Solid Blocks of Metal and other GT6 Blocks are still Air Sealable.
[FIXED] Nozzle or Tap sometimes not working on Basic Machines that have a Gas and a Liquid stored at the same time.
[CHANGED] The Canning Machine can now also use the Output Tank for filling Empty Containers, should it be necessary.
[CHANGED] Fluid Burning Boxes can now be emptied with Taps and Nozzles.
[CHANGED] Wearing Radioactive Rings in Baubles Slots will cause Radiation Effects on you, just like having them in Inventory Slots.
[CHANGED] Sulfur Dioxide to Sulfur Trioxide Mixing now additionally consumes Air or Oxygen. There is no penalty of using Air instead of Oxygen.
[ADDED] NEI Fake Recipe List for the Bedrock Drill.
[ADDED] Pyrite, Limonite and similar "Iron containing Mineral"-containing Small Rocks can be used for Flint and Tinder now.
[ADDED] Amnesiac Bumblebees that produce Combs that yield Lubricant and Amnesic Wax. Spoiler alert do not continue reading if you wanna find out the combo yourself, it is the combination of SIVLEBMUB&CITUANBUS (you have to read those in reverse).
[ADDED]
Advanced Bumbliary.
Requires a Comb from a crossbred Bumblebee Species in order to be crafted, so you have to have some Bumblebee Experience before you can get this!
Extraction of Bumble Products can be automated with this variant.
Bumblebees only have a Range of 1 Block (so a 3x3x3 cube around the Hive) for collecting Flowers and such (so it is less laggy than the regular 7x7x7).
They only sting/attack inside a 5x5x5 cube area around the Bumbliary instead of a 9x9x9, so just stay more than 2 Blocks away and you're safe.
This Bumbliary has less Slots for Products, but since you can automate it, it's not that bad. Maybe I use the newly free-ed GUI Space for something else later down the line.
Bumblebees produce only at a quarter of their usual Speed when used here. Their Lifespan stays the same, so it is overall just much less productive, but it is place and forget, so it might be worth it.
The Bumbles in these Advanced Bumbliaries prefer very much to be left alone, resulting in a 5 MINUTE timer for breeding (instead of 1 Minute) when you dare to rightclick their Home! This makes it much more difficult to breed within this Block.


6.07.12:
[NOTE] I just found out that ExtraUtils lags the shit out of NEIs secondary Thread, so if NEI isn't reacting fast enough to your clicks, that might be the cause...
[DEV-NOTE] The randomcode.jar updated to include Baubles Stuff.
[COMPAT] The Betweenlands
Lots of Material Data for its Stuff has been added.
Its special Bedrock is now recognized by GT6 Stuff, such as Worldgen or the Bedrock Drill.
Most GT6 Food and Fluid Potions now spoil/rot inside the Betweenlands, like vanilla Food does.
[COMPAT] The Spade now has an actual list of Blocks it can Silk-Harvest. I added Botania Grass and Betweenlands Grass to this List too.
[FIXED] Machines that can produce a Recipe more than once did not properly check if the Output would actually fit in the Slot, resulting in Stacks with the size of for example 128, if the Output Slot was entirely empty beforehand. And you know what stacks of size 111 or higher can do with NEI installed.
[ADDED] Bedrock veins can now spawn properly in Atum, the Deep Dark and the Betweenlands.
[ADDED] GT6 Rings are now wearable. They have no Effect, but you can wear them. Basically a totally useless Feature to make sure I used the baubles API correctly.
[ADDED] The Worldgeneratable Nexus Building now has a raw Atum Portal in it too.
[ADDED]
100 More Sub-IDs for Wood Types that are usable in Bookshelves, because I kinda ran out of the 100 I had before.
The Dry, Mossy, Rotten and Frozen Planks can now be used for Shelves and Panels for example.
Same goes for Atums Palm Planks and several other Planks from the Betweenlands and Erebus.


6.07.11:
[NOTE] The Default Config Pack Updated to contain disabled Harvestcraft, Galacticraft and GalaxySpace Oregen. (I also disabled their Update Checkers per default)
[FIXED] The Recipe Replacer was slightly bugged for some Recipes. Thaumcraft Voidmetal Stuff should be recycleable now.
[FIXED] The Drills and all the other Auto-Item-Collecting and Magnetic Tools were "Silk Harvesting" Sideways GT6 Slabs instead of GT6 Slabs facing downwards, creating a royal mess in the Inventory when harvesting GT6 Slabs.
[CHANGED] Machines that require Kinetic Energy to run can now process multiple Items in parallel depending on their Tier, in order to counteract the Push/Pull Frequency of Engines.
[ADDED] Dirty Loot from Atum can now be washed in a GT6 Bathing Pot or Bath to clean it and retrieve the clean Item. (It's one of those Random Item things, like Scrapboxes)
[ADDED] Potassium Persulfate can now be used for refining Ores just like Sodium Persulfate.
[ADDED]
A ton of Miniature Portals, please test them yourself and report back any Problems you might find.
They default to the same Functionality as the Mini Nether Portals, where not mentioned.
Here a List of all of the Miniature Portals so far:
Nether Portal, Activation Item is anything that can light it with Fire, Coords 1:8 and Error Margin is 128m. All for comparision, the Miniature Nether Portal already existed before.
End Portal, Activation Item is Ender Eye, Coords 1:128 and Error Margin is 512m, because the End Island is small.
Twilight Portal (Twilight Forest), Activation Item is Diamond, Coords 1:1, Error Margin 512m, because the Twilight has a lot of locked off Areas.
Erebus Portal (Erebus Mod), Activation Item is a Gaean Gem or the Staff of Gaea (You wont get it back though!), Coords 1:1, Error Margin 128m.
Deep Dark Portal (Extra Utilities), Activation Item is Bedrockium Ingot, Coords 1:1, Error Margin 128m.
Last Millenium Portal (Extra Utilities), Activation Item is Bedrockium Ingot, Coords 1:128 and Error Margin is 512m, because the starting Area is small.
Atum Desert Portal (Atum), Activation Item is the Scarab, Coords 1:1, Error Margin 128m.
Betweenlands Portal (The Betweenlands), Activation Item is the Swamp Talisman (Don't worry, it will not spawn that giant Tree), Coords 1:1, Error Margin 128m.


6.07.10:
[COMPAT] TechGuns Mobs now got additional Drop coverage for GT6 items.
[FIXED] Twilight Forest Knightmetal Blocks not being craftable, due to not being in the OreDict. This made crafting the Ball and Chain impossible, which is a very fun Item as I just found out.
[FIXED] TechGuns Plastic Sheet not being the Unification Target for Plastic Plates, resulting in some things being uncraftable.
[CHANGED] The way Arrow Crafting works is now in 2 Steps (First Stick + Feathers, Then add an Arrowhead). This halves the amount of overall Arrow Crafting Recipes.
[CHANGED] Molds now store their Auto-Pull and Redstone Settings when harvested.
[CHANGED] A lot of earlygame Blocks now require Iron instead of Steel. You can still use Steel, but you don't require it anymore.
[ADDED] Bullet Items, because I saw that way too many duplicates ended up happening in Recipes, so I had to make my own currently unused Bullets.
[ADDED] Nickel-Cadmium Batteries (They are basically Alkaline Batteries in every aspect, except their Crafting).


6.07.09:
[NOTE] The Website now contains a Page where I listed some of the things for the Game I plan to do after I am done with GregTech-6. It is linked on the Frontpage. ^^
[FIXED] Several Minor Bugs.
[FIXED] Silverfish Blocks in Vanilla Strongholds are no longer replaced by Stone Layers.
[FIXED] Redstone Infinite Loops with GT6 Slabs and GT6 Red Alloy Wiring.
[REMOVED] You can no longer get Carbon from centrifuging or electrolyzing Ashes. Try Sulfuric Acid and Sugar if you really need that Stuff.
[CHANGED] Grass Bales now always rot when placed adjacent to any kind of Water Block.
[ADDED] Near Frozen Water can now be heated up in a Smelter.
[ADDED] Ashes can now be used for Fluid Dyes again. I once removed that because of a colliding Recipe, but there is no Recipe colliding anymore apparently. I wonder where that went.
[ADDED] Sulfur based Roasting Recipes that work with Air instead of Oxygen. They however have just an 80% Chance for each Output, compared to Oxygen!


6.07.08:
[FIXED] Liquid and Gas Burning Boxes are no longer stuck with minimal amounts of Fluid when running out of Fluid.
[REMOVED] The Crafting/Canning Recipes for the old deprecated 2D Batteries and 2D Energy Crystals. You can still recycle/un-can the old Batteries. 3D-Lead-Acid is at 2D-Lithiums current Tier so there is nothing to worry about.
[ADDED] Models for Batteries and also made them compatible with crafting Electric Tools.
[ADDED]
Alkaline Batteries with twice the Capacity of Lead-Acid Batteries.
Similar crafting to Lead-Acid, though much more complicated for the individual Button Cells.
[ADDED]
New Energy Crystals, made using the Autoclave.
There is 2 Types so far, Red Energium that is made of Sapphire and Redstone, and Cyan Energium, that is made of Sapphire and Nikolite/Teslatite/Electrotine.
The Cyan variant has twice the Capacity compared to the Red Variant.
Also works without IC2 installed, unlike before. IC2 Energy Crystals now have a slightly different Recipe.


6.07.07:
[WIP] I started working on Battery and Energy Crystal related Stuff. Everything Battery related will change in some way, mostly for the better.
[FIXED] Several Bugs.
[FIXED] Plastic Mass Storages had Dark Grey instead of White Text.
[FIXED] Some non-localisable Strings in a more or less good manner.
[ADDED] Thaumcrafts Taint and Flesh Blocks are now Sword Harvestable. Also Flesh, Tallow and Amber Blocks now work in the Boxinators.
[ADDED] Soft Hammer can now be used to reset Sensors, Hoppers and Queue Hoppers. This replaces that Shapeless Crafting Recipe I added a version earlier.
[ADDED] ULV Igniter.
[ADDED]
Lead-Acid Batteries for the first 5 Energy Tiers (ULV to EV) for testing the new concept.
Currently all of them use the same tiny Model, but that should change.
Canning Machine is not needed to make them.


6.07.06:
[FIXED] Shovels were not able to place Torches (forgot that in the Changelog earlier)
[FIXED] Sifting Recipes for Coal and alike did not use the Replacement Stacks in case Gem Types were missing.
[ADDED] A way to create Skystone from Applied Energistics, in case the Meteors are turned off (like in my Default Config Pack).


6.07.05:
[FIXED] Various minor Issues.
[FIXED] Cutting Tiny Silicon Plates from regular Silicon Plates was a higher Tier than cutting a Silicon Boule into Plates.
[FIXED] Boxinator Recipes for filling Crates were not working because of an accidential Recipe Conflict with a Recipe that puts 16 Empty Crates into one Empty crate to make one Crate of Wooden Planks. Now you cant crate Crates anymore that way. (Crates STILL count as Wooden Planks ofcourse - I won't remove that!)
[FIXED] Bottom Textures of Blocks being Z Mirrored. Most noticeable in the Sensors.
[CHANGED] Burning Boxes now deal up to 2.5 Hearts of Heat Damage if you try to remove Ashes while they are burning.
[ADDED] Shovels can now be used on Crucibles and Burning Boxes for Scraps and Ashes. Time for Bear Sr to smack Bear Jr for me adding it finally. XD
[ADDED] You can now take Items that are too large or too many for the Crucible back out, just like Scraps.


6.07.04:
[COMPAT] Magic Bees Addon for Forestry got some more Stuff handled regarding Centrifuge Recipes.
[FIXED] Any sort of Clientside 0:00 Duration Potion Effects. Damn vanilla Bugs, always get stuck on me...
[FIXED] Hoppers not saving their Exact-Insert Mode when harvested.
[CHANGED] I got rid of the automatic Iron and Tungstensteel Handle assignment Code for Tools. Now almost everything defaults to Wood or Plastic Handles unless specified directly otherwise.
[CHANGED] I reduced the Rock Spawn Chance from the Stone Layer Generator to 1/128 instead of 1/64.
[CHANGED] Small Rocks containing Meteorites or Flint now spawn in Deserts too.
[CHANGED] Redstone and Click based Controller and Mode Selector Covers no longer need a Ticking TileEntity to work, so you can attach them to Multiblock Parts too now, without having to use a Universal Extender.
[ADDED] Molds now allow Magnifying Glass to be used on them to see what their Shape Produces.
[ADDED] Covers can now have a Custom Sound Effect for removing them with a Crowbar. Some Covers are also usng that Feature now. Mainly the Panels and the Canvasses.
[ADDED]
Covers now have a dedicated boolean State for being ON and OFF inside the Cover Data of a TileEntity. Said State ONLY affects Covers and not the Machine they are attached on. It defaults to ON, ofcourse.
Affected by this new Functionality are the following already existing Covers: Pumps, Conveyors, Vents, Drains, Pressure Valves and the Filters (they wont let ANYTHING go through when turned off, so basically a Filtered Shutter Cover)
The Cover-Controller-Cover has been added in order to turn Redstone into an ON/OFF State.
It is possible to use the Screwdriver and other Tools on the Sides of the Controller in order to switch simple Modes on the up to 5 Covers next to it, to make stuff more convenient.
Removing a Controller Cover will set the State of the TileEntitys Covers to ON.
[ADDED]
Shutter Cover.
It prevents Items and Fluids from going through, and is also able to connect and disconnect Pipes, Cables and Wires aswell.
Controlled by the Cover-Controller-Cover, and also has an inverted Mode. (With a bit of trickery on the inverted Mode, you could replace Wirecutting and Wrenching Stuff with Screwdriving it instead)


6.07.03:
[COMPAT] Magic Bees Addon for Forestry got some Stuff handled. (Specifically Magic Wax Capsules)
[COMPAT] Grimoire Of Gaia got some Material Data Stuff fixed.
[COMPAT] Gany's Mods (Et Futurum counts as that too, because same Author)
Gany's Surface Basalt is now accepted as a Stone Layer. As with all Compat Stone Layers, there is significantly less of it than the GT6 Variant.
The 1.8 Stones that Et Futurum adds are apparently added by Gany's Surface too, so I had to take THAT into account aswell.
Its Mutton is now detected just like the Et Futurum Mutton (seriously, why is there Mutton in two of your Mods!?)
Some of Gany's Wood Stuff is now OreDict Supported, Chest and Workbench wise.
[FIXED] Silk Touch on GT6 Rock Types works now.
[FIXED] Capenters Blocks are now properly harvestable by GT6 Tools the way the Carpenters Blocks are intended to be harvested (so Pickaxe for stoned Blocks etc).
[FIXED] Boxinator Wood Plank Recipe not using enough Planks resulting in a Dupe Glitch.
[FIXED] IC2-Exp Fuel Rod Recycling to now give Zirconium. And it also has to happen in a Tier 3 GT6 Centrifuge now.
[FIXED] GT6 Block Explosions cannot harm Withers, Ender Dragons and most modded Bosses anymore.
[FIXED] TNT Blocks not being triggered by GT6 Tools that ignite Stuff.
[CHANGED] Fluid Pipes now have to be clicked with the Magnifying Glass in their middle, in order for the content to be displayed.
[IMPROVED] The Stone Layer Generator. It is a bit more optimized and it can now replace Cobblestone and Mossy Cobblestone appropriately, which is very useful for Taiga Biomes, Vanila Dungeons, RTG Worlds and those Thaumcraft Magical Biomes.
[ADDED] Villages now use GT6 Brick Types in their Buildings instead of Cobblestone.
[ADDED] The Generifier can now turn all GT6 Rock Types into their Vanilla equivalent, aka Cobblestone, Stone, Mossy Cobblestone and the Stone Bricks.
[ADDED]
Item and Fluid Filter Covers (btw you need the Aluminium Base Cover for those).
They can filter for one specific Item or Fluid, depending on what you rightclick them with.
Also placeable on non-Pipes, such as the Machines and (Multiblock-) Tanks themselves.
Soft Hammer will reset the Filter so you can set it again.
Screwdriver will Invert it to let everything BUT the specified thing pass through.
Magnifying Glass will just display what the thing Filters for, in unlocalised Form.
Note, that Item Pipe Routing is NOT affected by them, unless the Filter is right next to the Inventory that the Item is inserted into! (so it DOES work with Chests ofc)
They are especially useful for the Multi-Fluid-Pipes, as you no longer need an entire Filter Block to select the Fluid that gets inserted.


6.07.02:
[FIXED] Furnaces and other Vanilla things can now be made with GT6 Stones and Cobblestones.
[FIXED] Queue Hopper not sorting its internal Queue when disabled via Redstone.
[FIXED] Crowbar Cover Removals were sometimes causing a Crash.
[FIXED] Three more Clay related Recipes (Erebus Mirbrick, Thaumcraft Phials and Bleached Railcraft Clay), also improved the Handling of them.
[FIXED] Bedrock Ores generated always with vanilla Stone around them instead of the actual supposed Stone around them.
[FIXED] Long Wooden Sticks can now be made from Wooden Beams in the Lathe (was an oversight).
[CHANGED] Dungeon Loot: IC2 Iridium Shards got replaced with IC2 Scrapboxes. IC2 Iridium Ores got replaced with IC2 Iridium Shards.
[IMPROVED] Ocean and Swampwater are generating much more efficiently now. Maybe this fixed the weird all Water Blocks in a random Chunk get replaced by a random Block ID Bug, maybe not.
[ADDED] Underground Biomes Rock Types now generate inside GT6 Stone Layers if installed. You should turn off Underground Biomes Worldgen in its Config though (The Default Config Pack already does that). As with all Compat Stone Layers, there is significantly less of them than the GT6 Variant.
[ADDED] Materials for Gaia Spirit Ingots, Elven Dragonstone and Mana Diamonds from Botania. Also added a way to add a Rainbow special Effect to Materials.


6.07.01:
[NINJA-FIXED] Forgot to change the internal Version Number to make the Update Message shut up.
[FIXED] Drains not working properly in Rivers.
[FIXED] Basic Machines should now prefer to output Fluids in Output Slots that already contain their Fluid, as opposed to filling an empty Slot.
[CHANGED] The Localisation of "Empty Ore" to "Unknown Ore"
[CHANGED] The Texture Location for all GT6 Rock Types, to make it easier to copypaste and less of a clutter.
[IMPROVED] Stone Layer Generation got optimized in order to generate much faster.
[ADDED]
Some more Ores for the Rock Layers, such as Hematite.
All Gem Ore Veins that I added are Biome dependant (except Diamond ofc), since Gem Ores are supposed to be very rare, but all regular Ores don't care about Biomes at all.
For example Craponite spawns inside some Layers of Diorite inside Jungles between Y24 and Y48.
Small Gem Ores that are Biome independant still exist too ofcourse.
[ADDED] Stone Plates (produced in a Simple Extruder) made of the Materials corresponding to the GT6 Rock Types, will work as a Cover that can have any of the 16 Designs (technically its 15 due to the Redstone Bricks using the same Texture as the normal Bricks). Also works for vanilla Stone, even though with fewer Designs. Also Netherrack, Endstone an Obsidian but only with one Design.
[ADDED] Square Brick Design for Rock Types. This means I got all 16 Designs together and can finally start with making new Rock Types by recoloring old ones.
[ADDED] Oil Shale as a Rock Layer similar to the Coal, Salt and Bauxite Layers. In order to process it, you need to grind it into Dust and put it in a Coke Oven, that will yield 25 Liters of regular "Oil" and Stone Dust.
[ADDED] Komatiite as a Yellow Rock Type. It will now contain the Redstone/Cinnabar Veins instead of Marble.
[ADDED] Green Schist as (you guessed it) a Green Rock Type.
[ADDED] Blue Schist as (obviously) a Blue Rock Type.
[ADDED] Kimberlite as a darker Brown Rock Type.
[ADDED] Quartzite as a Pink Rock Type.



6.07.00:
[NOTE] You might want to skip this Version, as I plan to add even more Rock Types.
[FIXED] Item Pipes had tiny routing Issues, as they were resetting their Transferred Item Count more or less randomly depending on TileEntity Loading Order.
[FIXED] Pipes were giving Contact Damage regardless of the Heat of their content.
[FIXED] Twilight Forest Streams and Lakes were not counting as Rivers for the Drain Covers, nor meeting the Height Requirements due to the Dimension having a lower Ground Level. So it does work now.
[CHANGED] Hard Hammers and Clubs can break Mob Spawners 16 times faster than other Tools.
[ADDED] A Warning Message that the Default Config of CustomOreGen will screw over all GT6 Worldgen when installed without PFAA being installed too.
[ADDED] Thaumcraft Crystal Small Ores to Worldgen, enabled by default. May need to update the Config Pack after the release or just disable Thaumcrafts Shard Ores yourself.
[ADDED]
Stone Tiles and Stone Small Tiles as Design Variants for all GT6 Rocks.
They will be used in the GT6 Dungeons for Floors and Ceilings too now.
Also added Smaller Bricks and Windmill Tiles as additional new variants aswell.
[ADDED]
New Rock Typed Worldgen, as seen on the Screenshots on the Main Page / Gallery. Similar to Underground Biomes. There will be more Rock Types to come.
You can disable the Stone Layers and have the old Grid Worldgen back as it was before this Version too.
Don't worry the Worldgen isn't required for the Tech Tree, you can keep staying in your old Worlds, or decide to discover the new Areas.
There will be vanilla Stone amoung the Rock Layers to ensure that other Mods Ores can still generate. (because I will remove the ability of modded Ores, Gravel and Dirt to be able to spawn within GT6 Rocks, unless the Coordinates are Y=6 or below)
[ADDED]
New Rock Layer based Ore Generation. Ore will all generate differently now.
The old way of generating or finding Veins in a Grid is gone (for the Overworld, other Dimensions will keep the Grid System).
Small Ores from the old Worldgen will STAY THE SAME. Those won't be removed.
Sapphire and Emerald Veins will be the only Biome dependant Ores, and the Biome is mostly only needed to determine the Color/Impurity of the Gem.
There is a general 1 in 64 Chance of spawning a Small Rock above the Ground or inside a Cave, said Rock will be made of whatever Rock Type or Ore Type the Column last generated successfully (aka below it, if you dig straight down), even if that Ore was close to Bedrock.
New Ore Veins will generate in one of 3 possible Ways:
Special Ore Layers where all Stone is made of an Ore include: Coal, Lignite, Salt, Rocksalt and Bauxite. They behave like Ores for Crushing etc and are not made to be decorative.
Ores that generate in the middle of a specific Host Stone Layer, depending on Y-Level.
And Ores that generate when one Rock Layer is directly ontop of another Rock Layer, depending on Y-Level.


//=== There is a Config Pack containing quite a few good Defaults for other Mods on the Download Page now, mainly disabling Worldgen of Ores and disabling Enviromine Physics. Oh and Version Number jump due to improvements in Worldgen. ===//


6.06.11:
[FIXED] After Hours of Experimenting with OpenGL I finally fixed the Item Barrels causing Rendering Issues (like JABBA Barrels still do, because they forgot to re-enable GL_ALPHA_TEST), and fixed the GT6 Chests rendering their colored Parts strangely, when JABBA Barrels and similar things cause those Issues.
[FIXED] Bedrock Ore Flowers growing on the Moon despite no Oxygen.
[FIXED] Tools placing Torches weren't able to do so on Twilight Forest Grass. It was annoying to get through the Dark Forest without being able to properly place Torches in there. Oh also you can get Grass from cutting it with a Knife or so.
[CHANGED] GregTech now sees Vanilla Granite as a different Material than the 2 GregTech Granites.
[CHANGED] Tool Materials now have 4 Type Levels, 0 = No Tools (used if Blocks need to be harder to mine), 1 = Flint/Stone/Wood Tools (very earlygame Stuff), 2 = Tools that aren't complicated (like Saws or Pincers), 3 = All Tools including Electric ones. (this internally replaces the TagData for HAS_TOOL_STATS and NO_ADVANCED_TOOLS)
[CHANGED] Enchantment Power Bonus of GT6 Shelves is now based on 1/12ths instead of 1/14ths, making it possible to hide a Lever or Button inside, without losing on the Bookshelf Count for the Enchantment Table.
[CHANGED] Pyrite doesn't simply smelt into Iron anymore, you have to roast it now.
[CHANGED] If you sneak-rightclick a Rock with a Magnifying Glass you will pick it up instead, without losing durability on the Magnifying Glass.
[ADDED] Wooden Buckets and a Glass Bottle for Lubricant. They will prioritize outputting the RotaryCraft Variant if installed. The Bottle variant is Dungeon Loot too now.
[ADDED]
Rock Types for "Vanilla" Granite, Diorite and Andesite.
My Version of them has proper Cobblestone and Brick Variants!
And all of the Et Futurum, Chisel and Botania Rocks of those Types will drop the GT6 Cobblestone Variant (or the GT6 Stone variant if Silk Touched).
It does generate in World, both as Rock Blobs and in GT6 Dungeons.


6.06.10:
[FIXED] Some Tool clicking Issues with GT6 Blocks.
[REMOVED]
Most ways to obtain "Impure" or "Pure" piles of Dust.
They were a leftover thing from GT5 that I kept as a temporary way of making byproducts, but now that the Sluice and the Cauldron do all Byproducts, they aren't needed anymore.
They will stay for the Impure Stone Piles that Small Ores drop, and for that case I changed the drop rate to 100% instead, so they won't drop regular Stone Dust anymore.
Small Ores won't drop Impure Dusts for their Ore anymore, they will use the crushed Variant instead.
[CHANGED] Sifting Table now only takes 8 clicks instead of 20. It just took way too long even with my Rightclick holding Macro on my Mouse.
[CHANGED] More Materials are now Furnace Smeltable and considered simple enough for the Low Tier Extruder. Notable mentions are Red Alloy, Germanium and Aluminium (which is ofcourse still a bitch to get in the first place). Naquadah got a higher melting Point now though.
[ADDED] Centrifuge Recipes for Purified Ores into 3 of the possible Byproduct Types and a Pile of regular Dust.
[ADDED] A functioning Quad Beacon at the Center of the World, if the Config for it is enabled. It covers the Center 6x6 of Chunks properly.
[ADDED]
Bedrock Mining Drill for the infinite Bedrock Ores.
Made of roughly 35 Dense Titanium Walls and 9 Tungstensteel-Diamond Drillheads, because infinite Ores are not something balance takes lightly.
Consumes 1024 to 4096 RU/t (one of the four centered spots, or all of them if you wanna make it faster) with 32768 RU and 100 Liters of Lubricant per Process (any Side, Rotarycraft Variant usable too) to work.
Should be placed in a way that covers up most of the local Bedrock Ores.
Once active it will emit "Semi-Crushed-Ore-Ore-Stone-Block-Items-Of-Random-GT-Or-Vanilla-Background-Rock" through the Main Block at the Top.
Depending on the Ratio of Bedrock Ore to Small Bedrock Ore to Bedrock there will either be a new Ore Block or a random Cobblestone Block outputted per Process.
The Ore Block will have one of the five GT6 Stones or vanilla Stone as background Rock, and the Ore will be of the "broken" non-silktouched State. Cobblestone emitted will be of either of those six Rock Types too.
The Rock Type used for that, will change around every ~1000 Processes on average, to "simulate drilling through different Rock Layers". Except for the Nether, where all the Rock will be Netherrack related.
There is a Chance that instead of a Cobblestone Block you will get a Impure Pile of Bedrock Dust (Put that in a 64MU/t Magnetic Separator). I am sure this is far enough back in the Tech Tree to not mess with RotaryCraft too much.
As for where exactly to place this Bedrock Drill with a 3x3 Range, since Bedrock Veins have a 5x5 or possible Ore Spots: Regular Bedrock Ores count 2, Small Bedrock Ores count 1. The More Points, the better the Outcome.
Placing this Bedrock Drill just on a 3x3 of pure non-ore Bedrock will result in it just producing Rocks but no Ores. It will work for Bedrock Dust too.


6.06.09:
[DEV-NOTE] The randomcode.jar updated, but I think Addon Devs dont need to redownload it.
[FIXED] Garnet Sand, Quartz Sand and several other Ores had "null" Materials in their Components due to Classloading Orders, since the moment I made Rutile part of a type of Sapphire.
[FIXED] RTG is now compatible without crashes, even when GT6 Streets are enabled.
[FIXED] Wooden Stick Cutting being 32 RU/t instead of 16 RU/t on the Cutter.
[FIXED] Some Wooden Devices such as Reinforced Chests did not have the "Flammable!" Tooltip.
[FIXED] Ore Blocks inside Marble, Basalt or Limestone were slower to break than they should have been.
[CHANGED] The Nexus Building is different when no Streets Generate, and made of Stone Bricks instead of Concrete.
[CHANGED] Pincers now last 10 times longer by using less durability for Molds.
[ADDED] More Byproducts for Manganese related Ores such as Pyrolusite.
[ADDED] Cheap Barrel variants for Bismuth, Bronze and Brass (so not just Lead anymore).


6.06.08:
[CHANGED] Thaumcraft Nitor now counts as Torch for Tool based Torch placement and removal. This also makes it universally harvestable by GT6 Tools in Adventure Mode (previously required Crowbar).
[CHANGED] Et Futurum Coarse Dirt is now able to be turned into Mud by Swampwater.
[ADDED]
New Biome Diversity at the Center Area of the Map that can be enabled in the Config, just like the Streets.
It contains most Biome Specific Resources but pretty much no Ores.
It also contains all 5 current GT6 Rock Types, Both Colors of Clay, Hardened Clay, all 3 Types of Dirt, Sand, Red Sand, Black Sand, Snow, Ice, Packed Ice, Cactii, Rocks, Sticks, Flints, Flowers, Tall Grass, the first 4 Vanilla Tree Types, Cocoa, Pumpkins, Melons, Lily Pads, Glowtus and Mossy Cobblestone.
[ADDED]
Nexus Building at the Center of the Overworld (Disabled in Config per default), which contains inactive Portals to the Nether, the End and Twilight Forest.


6.06.07:
[FIXED] Coin Stacks that have more than 127 Coins did not drop anything when shoveled...
[CHANGED] Any Rightclick on a GT6 TileEntity with a Thaumometer will be ignored entirely, so you dont have to hold shift for scanning GT6 Stuff anymore.
[ADDED] Recycling Recipe for Glass Bottle to Glass Block in vanilla Furnace, and most Books and Papers are mortarable now, but you only get the Paper Chad off of it, not the other parts!.


6.06.06:
[NOTE] No, I will not sell my Soul to the Changelog!


6.06.05:
[COMPAT] The 7 Botania Quartzes get handled properly by my Machines and Recipes now.
[FIXED] Plenty of Bugs with: Wrenches in IC2-Classic, wrong Tooltips on Spikes, GT6 Cinnamon Bark harvesting without Pams installed, Glass/Sulfur not being Mortarable, and more.
[CHANGED] I just noticed that my "registering TechGuns Ammo as not auto-insertable to the Storage Inserter" was pretty much useless, since the Mod actually has an Ammo Inventory (which isn't and wont be scanned by said Storage Inserter), that I didn't think about when doing that. So that Registration will be undone for everything of TG.
[CHANGED] Mortar Recipes can now accept larger Items like Ingots, Plates and Gems if the Materials they are made of are Brittle! Coal, Salt, Redstone, Sulfur, Stone and similar count as Brittle.
[ADDED] Rubber Pipes. They are the only Pipes that can directly be "broken" by Hand (kindof like unscrewing a Rubber Hose). Also changed the Max Temperature on Wooden, Plastic (97°C) and Rubber Pipes, so Steam is no option for those ever! if you don't like their Black Color, then paint them. :P


6.06.04:
[COMPAT] Some remaining TechGuns Material Data and Recipes were done.
[FIXED] Crash Bug with the Thaumonomicon and Item Tooltips that have a Wildcard Metadata on them.
[FIXED] Fluid Tooltips now no longer state that Air is lighter than Air.
[FIXED] Prospecting not working on GT6 Rock Types... Don't know how I could have let that happen for such a long time... It definitely never worked in the first place...
[ADDED] Selector Tags 0-15 can be used as Mode-Selector-Cover now. They cant be switched to any other Mode without detaching them from the thing they are placed on! (otherwise the Manual Selector Cover would be obsolete)


6.06.03:
[COMPAT] Remaining Erebus Items got their Recipes and Stuff done. Most notable would be the Erebus Bug Repellant Spray Can that has a canning Machine Recipe with GT6 Spray Cans now, and said Empty Cans will be dropped whenever you use the Repellant, instead of being voided.
[COMPAT] Mo'Creatures Horses drop Horse Meat and its Logs and Planks are now registered in the OreDict.
[COMPAT] The Highlands Mod's Biomes and Items should be more compatible now, even though I cant believe how bad it was before I fixed a lot of those things... Its Ironwood Trees actually give small amounts of Liveroot Dust when shreddered too.
[COMPAT] TechGuns
RF Powered Devices will accept up to High Voltage Electric GT6 Power, just like Open Modular Turrets, Galacticraft or Applied Energistics.
Its Ammo Items are now recognized as Ammo by the Storage Inserter, meaning you won't just insert your not-in-hotbar Ammunition into a Wall of Mass Storages, when doing your normal Storage business, because that would be annoying.
The Undead Mobs of Tech Guns now drop similar GT6 Items as the Vanilla Zombies and Skeletons.
Boxing Machines can fill/empty Magazines with Bullets and such.
GT6 Crowbars can be used to break "Ore Clusters", just like the Tech Guns Crowbar. Note that they lose a lot of Durability for that!
[COMPAT-FAILED] HBM's Nuclear Tech
Could not be added Compatibility to, because it takes more than half an hour to load, when GregTech is installed (and the Lag is on HBMs Side, even though caused by the enormous amount of GT6 Items).
Note that I used a "Just GT6 Pack" for this that usually takes 2-3 minutes to load and added HBM to it.
And also NEI takes Minutes to load Recipes when I look up Recipes, meaning I can't check the Data at all with HBM installed.
And no, I didn't even run out of Memory, It only used 2 of the assigned 8 GB RAM, so that's not the Lag Source, it's likely the NEI Handler of HBMs that fucked up, or that HBM iterates over its entire Recipe List to find a fitting Recipe, what is utter garbage considering there is a thing called "HashMaps".
TL;DR; HMBs Nuclear Tech needs MASSIVE optimizations, or else I will simply not be able to add Compatibility for it, even though I want to. I saw that it still did updates 2 weeks ago, so not all hope is lost.
Edit: Okay I did add at least some Blast Resistance Tooltips to Blocks and compatibility with its Hazmat Suits, because that I can do without looking at Recipes in NEI.
[FIXED] Berry Bushes didn't work properly for some reason.
[FIXED] Wires and Pipes didn't properly connect to Non-GT6-Blocks per default when placing them directly against their Side.
[CHANGED] The Adv Crafting Table will now only craft all Items at once, if you Shift-Rightclick the Crafting Output Slot. That way it cant accidentially happen when you just Shiftclick Items out of the Grid. Also Shift-Leftclick now crafts only a Stack (kinda like regular rightclick) but dumps that one Stack into your Inventory, without it sticking to your Mouse.
[CHANGED] The Club will now make Vanilla Stone drop Stone Rocks the Hammer will continue dropping Gravel or Cobblestone in that case.
[CHANGED] Meteorite Rocks have a 1 in 12 chance to spawn instead of Flint Rocks, instead of a 1 in 16 chance, now.
[CHANGED] Ore Blocks that can generate in the Overworld will stay after you remove their corresponding Mod, such as Underground Biomes, PFAA, Et Futurum and Chisel. So you only need to replace their Rocks with Stone using MC Edit before removing them.
[ADDED] Air Vent Cover. It collects Air around it and inserts it into the Tanks/Pipes it is attached to. Doesn't work on Galacticrafts Planets for obvious Reasons. It will give slightly different Air depending on Dimension (namely Nether and End).


6.06.02:
[COMPAT]
I went through the List of Items Erebus adds, to see what they could be used for.
I did not expect THAT many potentially compatible Items, when looking for the Mod that adds Jade, that I saw it in one of Ethos Videos.
Damn, even needed to add Ore Generation Handling to it in order to make Umberstone properly Ore-Generateable.
And I fixed some of their Bucket related Fluid things too, as it uses the Biomes o' Plenty Honey instead of the Forestry Honey.
[NINJA-FIXED] Loot Chest Replacing didn't work properly.
[FIXED] Some Stone alike Gears were not craftable the new intended way (4 Rocks + 4 Stone Blocks), and they were not usable as Buildcraft alike Gears despite being made of Rock.
[CHANGED] You can no longer use Stone alike Blocks for crafting Hammers. The Clubs can still be made with Blocks.
[ADDED] Breaking the GT6 Cobblestones with a Hammer, Club or Jackhammer will give you Rocks that can be used for Basic Tools. Don't worry, the natural Smoothstone still drops Cobblestone, so you can still Jackhammer the Granites and Stuff.
[ADDED] There is now more earlygame Tools available. Bamboo and Petrified Wood can be used as Handles and more Rocks can be used as Toolhead.
[ADDED] Sanding Machine. It does the same Stuff that the Grindstone does, but in Machine Form.
[ADDED]
Long Distance Item Pipelines and Fluid Pipelines.
They are similar to Long Distance Wires, and they are also One-Way.
Unlike the Long Distance Wires, they don't have any Loss, since they don't really transport Energy.
The Transfer of Items and Fluids is instant, basically Teleportation, but lets just pretend it's going through that Dummy Pipe.
Nonuple and Quadruple Fluid Pipes work with the Long Distance Fluid Pipeline too.


6.06.01:
[FIXED] When Roads were enabled, all Vanilla and GT6 Surface things close to the X and Z Axis ceased to generate, regardless of being in the Overworld (where it is intended) or not (such as in the Nether, the End or Twilight).
[CHANGED] The Bedrock Ore Variety has increased a little, because I felt like it while assigning Flowers to the Bedrock Ores.
[ADDED] Config to turn off Death by the currently 5 Types of Food Poisoning. You will be left with Half a Heart though!
[ADDED] Config to disable overground (>= Y:50) Mob Spawns close to Spawn. It is defaulting to TRUE, so for about 144 Meters in each direction from Spawn there is no Mobs spawning.
[ADDED]
Special Flowers generating in Grassy or Deserty Terrains.
They indicate the Location of the rare Bedrock Ore Mining Spots.
Bedrock Ore mining Spots do have a bunch the regular Ore around them, so they are still useful.
If you want to produce more of these Flowers, you will have to use Bumblebees to create more of the ones you already found.
The Desert Flowers work for both, regular Flower Bumblebees and Desert Bumblebees.


6.06.00:
[IMPORTANT]
Fluid Pipes have their Tank Capacity halved, but they still transfer the same amount of Fluid per Tick.
DRAINS NEED TO BE ON PIPES TWICE THE SIZE TO WORK PROPERLY!!! (unless they are in an Ocean, Swamp or River)
This will also affect filling of vanilla Cauldrons (334L) and Advanced Worktables, as you need a Pipe twice the size for that now.
Don't worry, excess Fluid in Pipes on loading the World wont be voided, and it likely won't overflow anything either.
Yet still I verily recommend to turn off all Steam related things before the Update.
I have let my Test Bear run all his pipe connected Boiler Setups before and after the Update, to make sure nothing blows up on your end either.
[COMPAT] Fallen Meteors Mod
I checked it for OreDict related Information. I added 5 new Materials due to that.
The Magnetization Enchant of it is now applied to all Magnetic Materials of GT6 if it is installed.
[COMPAT] Railcrafts Implosion Enchant will now automatically be applied to a lot of Gems. Implosion = Extra Creeper Damage.
[FIXED] A Bug that crashed GT6 with Ars Magica.
[FIXED] Large Ore Veins were generating all over the Y Axis, having a different Y Coordinate for generating in each Chunk.
[CHANGED] Electrolyzer and Centrifuge decomposing Recipes will now use 14 instead of 16 as a Basis for GU/t.
[CHANGED]
Some Alloys can now have multiple Attack Enchants attached to them.
Black, Red and Blue Steel now have their Sharpness slightly nerfed due to that, since they got quite a few additional effects now.
Electrum now has Smite, Disjunction and Werebane for example.
Damascus Steel is still Sharpness 5.
[ADDED] Universal Spade can now place Torches
[ADDED] A more convenient Recipe for Dispensers, using Droppers as step inbetween, without having the unstackability of Bows make things a bitch.
[ADDED] Slime Fluid and Slime Bottles that can be used like Glue Bottles. It gives you a chance of Jump Boost.
[ADDED] Plastic Storage Boxes, that are like Mass Storages, but for 128, 256, 512 or 1024 Items at a time and way cheaper than even the Item Barrels, once you get Plastic.
[ADDED]
Optional Worldgenerated Asphalt Streets along the X and Z Axis.
I know from own experience that Roads are a Server Infrastructure thing nobody really wants to work on, so Worldgen it is. Maybe I can add Structures to this too later.
The Roads have Signs every 512 Meters, close to the Region File Borders. The Signs tell what Coordinates the 4 Region Files have at the 4-Chunk Border between the Signs and the U-Turn Section.
Once the Middle Crossing at 0, 0 is Generated, it will automatically set the Server Spawn Point to itself once (It won't try to do that anymore after the Crossing generated). The Compass may point to the old Spawn until you reload the Client.
They are disabled by Default because they could be seen as not only unfitting for the more Medieval/Fantasy Playstyles, but also because they are easily exploitable Sources of Asphalt and Concrete.
Note, that GT6 Dungeons do not spawn close to these Roads (at least 256 Meters away), so they won't help you find them at all!
GT6 Trees, Logs, Sticks, Rocks, Bushes, Glowtusses, Bumblehives, Black Sand and Clay Pits are at least 64 Meters away from the center of a Street Section to not interfere with the Road itself.
I know that Tunnels sometimes have Light Update Issues, but unlike with the GT6 Dungeons where I fixed them already, those Issues are pretty much unfixable for Roads, as I would have to cause an Infinite Worldgen Loop to make those Lights work! THAT's how Terrible Minecraft is!
With the Spawnpoint being at 0, 0 you have a 12x12 Area of Perma-Loaded Chunks centered around the Crossing to work with, when building a Spawn Area, so a 4 Chunk Wide Border around it. (it would be 16x16 Chunks if you count the Lazy-Loaded Chunks too)
If you are a Server Owner who wants to have these Streets Retrogenerating on their Server, all you have to do is setting the Config to true and then delete&regenerate all Chunks that have the X-Coordinate:0 and also the ones that have the Z-Coordinate:0, using MC-Edit or Speigers Pregenerator Mod. But be careful and ask first if there is people having Bases close to the Lines of the Axis.


//=== Version Number Jump because of Fluid Pipes changing a bit ===//


6.05.52:
[COMPAT] Open Modular Turrets Recipes got changed for:
Hard Walls. They now adopt a System similar to my Concrete and Reinforced Concrete by requiring Metal Bolts and the Hand Drill. The Tier 1 and 4 Variants can be crafted directly out of GT6 Concrete and Reinforced Concrete.
Fences. They require Fine Iron Wires for the Fence itself and Metal Bolts for the barbs of the Barbed Wire Part. I changed up the Tiering to be more GT6 than Vanilla, so the Fence Barb Colors may not match the Recipe anymore.
Turret Bases. They require a lot of Electric Components to be crafted now. The Wooden one just requires a Tier-0 Motor, and doesn't need a Battery, Circuit or Sensors, so your Handcrank Potato Cannons are still very earlygame.
Ammo has cheaper  and more realistic Recipes now.
[FIXED] Eggs now despawn after a Minute regardless of my Item Despawn Time Config. This is because Chickens would be spamming Item Entities on the ground, so see this as if the Eggs are rotting.
[ADDED] Blast Resistance Tooltips now say whether they can stand a Ghast Fireball, Creeper or TNT Blast or not.
[ADDED]
Wall Spikes that can be pushed by Pistons. (and you can't walk horizontally into ones placed on the floor, so you won't be damaged by accident all the time)
There is also an Omnidirectional Spike, but it only deals half the Damage in return.
And a Gravity Variant of the Omnidirectional Spike exists too!
Walking ontop of any Spikes will slow you down a lot, meaning escaping is a bit harder, also balancing ontop of Sideways Wall Spikes is slower due to that too.
Steel Spikes don't work on Skeletons, Slimes or Iron Golems.
Titanium Spikes work on all Mobs and deal twice the Damage of Steel Spikes.
Tungstensteel Spikes work on all Mobs and deal three times the Damage of Steel Spikes.
Gold, Silver, Copper and Lead Spikes deal four times the Damage of Steel Spikes when they are against a Mob that is weak to their Material, otherwise it's only minimal or no Damage in case of Iron Golems, Slimes or Skeletons (unless they are weak to it).
Adamantium Spikes deal ten times the Damage of Steel Spikes against every Mob touching them.
Placing Hoppers below the Spikes will work fine, as they have a Slab alike Box Shape for Collision.


6.05.51:
[COMPAT] Galacticraft Machines now also have the HV Limit before exploding.
[COMPAT] Open Modular Turrets Stuff
The Turret Bases can now accept GT6 EU directly if it is HV or less. If it is EV or more it will explode! SO BE WARNED!!!
I added Blast Resistance Tooltips to the Hard Walls, so that you know how strong they actually are. The Tier 1 Hard Wall doesn't even contain a Creeper or TNT Blast, you need Tier 2 or above for that.
[COMPAT] Made sure Lycanite Mobs things were compatible.
Fixed the "Pure Lava Bucket" Recipe duping Iron Buckets, by making it a Mixing Bowl Recipe for Lava + Ghast Tears instead.
Made GT6 Axes and Saws drop more Oak Logs when being used on Ents (Axes are better). The Amount of Logs depends on the amount of Hearts worth of Normal Non-Magic Damage you deal to it (regardless of how much Health it has left). It can go up to additional 64 Logs per swing.
Wargs drop Dog Meat now.
[FIXED] Bumblebees won't work in the Vacuum of Galacticraft Space now.
[FIXED] Fluid Display Icons from all Mods, that somehow fail to properly assign Fluid Icons, despite having a fully working Fluid Block. This fixes Pure Lava and Ooze from Lycanites for example.
[REMOVED] The Collision Box from Paper Covers, because it doesn't make sense that you can stand on it. Let's see how many people use Paper to protect themselves from Pipes and don't read this line.
[ADDED] Limestone Rock, it has the same properties and spawning as Marble, and the same Textures GT5 Concrete had. Might be useful if Calcite is needed.
[ADDED] Recipes to Un-Curve the Curved Plates with a Hammer or the Rolling Mill.
[ADDED] Wooden Panels, they are simply Wooden Covers, so you don't need to use Canvas for Wood Textures. Available in all Variants the Wooden Bookshelves are available as. Also requires an Iron or Steel Screw per Panel (Recipe gives 6 per craft).
[ADDED] Concrete, C-Foam and Asphalt Panels (also just Covers). The Asphalt ones will give you a speed boost, just like the Asphalt Blocks!
[ADDED] The Locker. It is basically an Armor Swapper, so you can put on Armors easily with it, without messing around with the Inventory, and without having an FPS Lag Source of an Armor Stand being around. Available in multiple Materials, just like Compartment Drawers for example. It works with the 4 Basic Activity Detectors as indicator of having Armor Parts.
[ADDED] The Charging Locker. It can Charge IC2 Armor. Make sure the Voltage is proper to charge the actual Armor. The "Processing" and "Success" Activity Detectors can check if all contained Armor Pieces are charged fully.


6.05.50:
[FIXED] Some things I noticed while writing the Patreon Post.
[ADDED] Small Vinteum Ore now generates automatically when Ars Magica is installed.
[ADDED] The Primal Mana Fluid from Thermal Foundation (can't be produced atm afaik), can be used to make certain Magical Materials, such as Astral Silver, Midasium, Mithril and Thaumium.


6.05.49:
[COMPAT] AE Devices can now accept GT6 EU directly if it is HV or less. If it is EV or more it will explode! SO BE WARNED!!!
[COMPAT] Thermal Expansion
Tectonic Petrotheum can now be used in the Sluice for washing Ore instead of Water. (This was SUPPOSED to be already possible, but there was a Bug, preventing that from working on my end).
Blizz, Blitz and Basalz Powder can now be created in the Injector with their respective Item and Fluid Redstone.
A lot (if not all) of Recipes for filling Energy Cells, Tesseracts, Plates and Conduits with Redstone/Glowstone/Ender/Cryotheum/Aerotheum are now doable in the GT6 Canning Machine AND inside the Injector aswell (for convenience, due to the Blitz/Blizz/Basalz Recipes being Injector Only).
In the newly added Recipes, Molten and Destabilized Redstone work just fine for doing the Job. Same goes for Resonant Ender and Molten Enderpearls.
Filling a vanilla Bucket with 1440L Molten Redstone or 576L Molten Enderpearls using any GT6 thing (Taps, Canners, Crucibles etc.), will make it turn into the TE-Bucket, making an easy conversion possible without relying on the Generifier.
Blizz, Blitz and Basalz Rods use the "craft = 1 powder, mortar = 2 powder, shredder = 4 powder"-System now.
[FIXED] GregTech Worldgen and Burning Box Fire Placement no longer overwrite Thaumcraft Nodes as if they were Air.
[FIXED] Applecore caused Water to have Food Value for some reason despite me having clearly declared a Food Value of Zero.
[CHANGED] IC2 Energy Storages and Transformers require the GT6 Variant in their Crafting Recipe.
[CHANGED] Barrels, Drums, Pots and other Fluid Containers can now be stacked when empty. The only ones Stackable when full are still the Capsule-Cell-Containers.
[CHANGED] A lot of Sluice, Bath and Magnetic Separator Recipes changed to make it possible to process singular Tiny Crushed and Purified Ores.
[ADDED] An example Recipe for each of the Handle based Tools.
[ADDED] A Compartment Drawer that has 4 Drawers with 36 regular Inventory Slots each, and is compatible with adjacent Advanced Workbenches. The Crafting Recipe needs overall 32 Units of a Metal (the Metals you can make the Chests of)
[ADDED]
Storage Inserter
It is SOMEWHAT like a Drawer Controller, but it only works for inserting Items BY HAND into a Wall Shaped Area of Mass Storages and/or Item Barrels.
The Wall of Mass Storages can be 50 Meters long in each direction, and 7 Meters tall counting from the Floor below it.
There are some quirks to scanning the Area, for example there has to be a Floor in front of the Wall of Mass Storages, and they aren't allowed to be obstructed nor to face the wrong way.
This whole thing is based on "simulating a person* running in front of the Wall and rightclicking every single Storage from bottom to top", so just make sure that THAT is possible to be done and it should work.
The direction the Insert will scan does depend on what side of it you click. It will essentially behave like "spawning a person* on the side you click" and then run that "person*" in all 4 compass directions trying to insert Stuff from bottom to top.
As Floor counts the following: Slabs/Stairs (any rotation possible, and most modded ones work too), Full Blocks and Blocks with a solid Top Side.
It will insert the held Item in the Hotbar when you click it, if it can.
If you click it with an Empty Hand, it will try to insert all Items in the 27 Slot Player Inventory, except things with a Max Stacksize of 1 (such as Tools), Ammunition (Arrows) and Tool-Rightclick-Placeable-Torches (because that would be annoying, if you have a Torch Item Barrel or something).
Placement for the Inserter is advised to be either as Part of the Storage Wall, or inside the Floor in front of the Storage Wall.
You can NOT, I repeat CANNOT, attach Pipes to the Storage Insert AT ALL and I will NOT add that Functionality to it!!!
(* = its not actually a person, this is just a metaphor, it only checks if the Blocks in front either don't have a Collision Box or are Carpets)


6.05.48:
[FIXED] Fluid Funnels now allow partial Amounts of Fluid to be transferred.
[ADDED] Nozzles, the Fluid Tap equivalent for Gasses.


6.05.47:
[FIXED] The Names of Railcraft Plates. No longer Confusion caused by "Tin Plates" and "item.lead.plate.name" or whatever that one was.
[CHANGED] Mud is now affected by Gravity, like Sand. Also Gravity is now mentioned in Block Tooltips if it applies.
[ADDED] Magnetic Separator Recipes for impure and pure Dusts, so you dont have to use purified-crushed Ores for it.
[ADDED] All GT6 Tools can now harvest Torches directly, and in that specific case they will always be "Magnetic" and won't lose any Durability in the Process (aka: Tool + Leftclick = Pick up a Torch from the Wall directly into your Inventory).
[ADDED] Treated Planks in Block Form, the Item variant got removed and will automatically turn into the Block Form in all GT6 Blocks and your Inventory. It can be crafted into the Immersive Engineering Variant and back if needed.
[ADDED]
Black Sand to Rivers that aren't adjacent to Oceans or Swamps.
It can be used similarily to Magnetite in a lot of Recipes, also using the Centrifuge you can get Ferrovanadium out of Black Sand, so no need to worry about getting your Vanadium Pentoxide.
The Sand generates similarily to the Clay Pits, but is only 2 Blocks deep. It is NOT Part of the Ore Vein Grid!!!
Magnetite Ore itself in any form will no longer generate, in favour of the new Black Sand.
I did increase the amount of Small Hematite Ores to compensate the new lack of small Magnetite Ores. You can be happy that you have 2 more Inventory slots free for Ores that way lol.
As for the Large Magnetite Veins (that includes Ferrovanadium too), they will no longer happen in new Terrain, meaning that all other Large Veins have a slightly higher chance of spawning now.


6.05.46:
[NOTE] I have hit the 2^16 Byte Limit of Code on MT.java yet again and needed to trim it down further in order to get GT6 to compile, this means large Code changes and some removals inside that File.
[FIXED] Several Tools being available for Stone despite not being intended to be like that.
[FIXED] Dust Funnels not dropping their partial Content when broken. They will no longer store it, making it possible to clear a Dust from the Dust Funnels.
[FIXED] Sealed Wood Pipes were unintentionally Gas Proof (back then it might have been intended but we have better solutions now). I fixed that by making them leak Gasses aswell, and buffing their Capacity from 50 to 75.
[CHANGED] Some Acids can no longer be electrolyzed into their Components anymore. Same goes for most Sulfur containing Chemicals.
[CHANGED] Fake 'Osmium' is now easier obtainable when Mekanism is installed. It can be processed in a Furnace too now.
[CHANGED] If a Material has a Fluid form and can be electrolyzed or centrifuged, you are going to have to use said Fluid form for that now, and can't use Dusts for it anymore.
[CHANGED] Tungsten Ore, if you happen to find any (due to other Mods or old GT6 Worldgen) will crush into 2x Scheelite instead, to ensure proper processing, while also giving a slight Bonus compared to actual Scheelite.
[CHANGED] Small Tungsten or Pinalite Ores of GT6 to Scheelite Ores. And added small Pyrolusite Ore with the same location and rarity as small Gold and Silver Ores.
[ADDED] Plastic Rods can now also be used instead of Wooden Sticks for most Tool Handles.
[ADDED] Made GC Rockets Recycleable.
[ADDED] Selenium as Byproduct of some Ores (I will likely use it for Batteries). I also added it into the Moon-Cheese Veins, since Selenium is literally named after the Moon.
[ADDED] Sodium Nitrate and a bunch of Saltpeter related Chemical Recipes.
[ADDED] New Processing for getting Elemental Sulfur from Sulfur Dioxide. It is similar to the Claus Process but far easier, due to not having to Filter other Materials out of the SO2.
[ADDED] Potassium and Sodium based Chemicals can now be interchanged in a lot of Recipes, but not all of them.
[ADDED] Lots of Chemistry related Materials and Recipes that didn't exist before, to make things more complete.
[ADDED] Crowbarred Covers now go directly into the Inventory if possible.
[ADDED] Tungsten Chemical Processing for Tungsten containing Ores.
[ADDED] Pyrolusite + Hydrochloric Acid Processing and removed its Electrolyzer Recipe.


6.05.45:
[FIXED] Aqua Regia to Chloroplatinic Acid Processing producing a very unbalanced output (more Cl out than you put in), I only needed to change up the Ratios, it's the same Products as before.
[FIXED] Some Bugs regarding the Player Inventory restock from the Slot above Functionality of GT6 in regards of GT6 Tools and their Scrap.
[CHANGED] Tin Alloy counts as Furnace Smeltable now.
[CHANGED] Neodymium and its Magnetic variant are now Diamond-Tool Quality.
[CHANGED] Bushes now grow 5 times slower. They were just too OP.
[ADDED] A dedicated Loot Chest for the Dungeon Loot change in the previous Version. Now it is no longer made of Tin Alloy, but instead is not Craftable and made of Stone and looking a bit like Mossy Stone Bricks.
[ADDED] Cobalt, Nickel, Germanium and Draconium "Storage Set" (Chests, Mass Storages, Shelves, Adv Crafting Tables, Hoppers etc). Also made it easier for me to add more "Storage Sets" by rewriting the Code a little.
[ADDED] A few Recipes regarding BoP Mud and GT6 Mud.
[ADDED] Tin Alloy Fluid Pipes.
[ADDED] Mud Version of GT6 Ores to fit in better with the muddy Swampwater of GT6.
[ADDED]
Some Tools will now autocollect their Drops instead of letting Item Entities fall on the ground, if the Player has enough Inventory Space.
This also applies to certain Materials you can craft Tools out of. You know, the "Magnetic" Variant.
Amoung those Tools (its about half of ALL Tools that can do that) is the Wrench for example, meaning you autocollect whatever you Wrench, no matter what Material the Wrench is.
That kindof fixed the Issue of "Removing Hoppers above a Crucible" as a Side Effect, since the Hopper would not Drop as Item in this case (If you have Space in your Inventory).
As Examples, here are a few Tools that Automatically have this Ability even without being Magnetic: Construction Pickaxes, Wrenches, Mining Drills and the Plow.
[ADDED]
Made several Extruder Recipes accessible much earlier.
The Tier 1 Extruder no longer needs Tungsten Carbide to be crafted, instead it uses Steel.
New Low Heat Extruder Shapes can be made using any Type of regular Steel.
Several Low Tier Metals count as Simple for the Extruder now, meaning they have a fixed low cost and can be formed using the Low Heat Shapes.


6.05.44:
[NOTE] Users of the Custom Veins etc in my Worldgen Config have to manually set the amount of Custom Veins, as it no longer defaults to 8, in order to prevent Debug Error Messages from appearing in the Log (due to NULL Material in Config). And yes the Debug Errors about Worldgen Ores are for Users, not for me, and they are in the GregTech.log
[FIXED] A HUGE Maths Error in the Extruder Recipe Code. It used about 10 times more Energy in some cases than it should have used (all the Metal related Recipes basically). Now it will just use 25% more Energy than a Crucible would.
[FIXED] A Stupid Error in the Extruder Recipe Code that made Wax, Plastic and Rubber Stuff cost exactly the same no matter how many Items the Recipe Outputs.
[FIXED] Fluid Filters resetting again.
[CHANGED] Dungeon Loot Chests will now be replaced with GT6 Chests that only generate their Loot once you open or break them and not before that.
[CHANGED] A tiny thing in the Crafting Recipe Searching Code, might make the Adv Crafting Table create less of a spike when being used.
[CHANGED] Moved Mineral Water production from Mixer to Injector.
[CHANGED] The Tooltip for contained Materials got a bit slimmed down and more overviewable.
[ADDED] GT6 Blocks that have Covers on them now have a Tooltip stating that a Crowbar can be used to remove them.
[ADDED] XP Orbs now get combined if there is more than 32 of them in one World at once. The resulting larger Orb will have the despawn Age of the youngest Orb.
[ADDED] Ender Garbage Bin (only top side) and Ender Garbage Dump (any side) are now accessible via Funnel/Tap.
[ADDED] Recipe for Calcium => Calcium Carbonate (Calcite), mixing Water and CO2. It has Hydrogen Gas as a Byproduct.
[ADDED] Draconium Fluid Pipes. A very high Tier Type of Pipes for people, who have a Draconium Adding Mod installed.
[ADDED]
More proper Titanium Processing.
The Centrifuging of molten Ilmenite into Rutile and Hematite is no longer possible.
You generally don't need a Crucible to process Titanium containing Ores anymore, except for the final step of shaping the resulting Titanium Dust into whatever you need, like Plates or something.
Instead you need Sulfuric Acid to make Rutile and Green Vitriol.
Rutile (or Ilmenite directly) + Coke + Chlorine + Calcite in Burner Mixer is needed to make it into Titanium Chloride.
Titanium Chloride + Sodium or Magnesium = Titanium + Salt.
This change also fixes the Niobium-Titanium Crucible in the Tech Tree, so that it can be used again to progress.
[ADDED]
My own Type of 3D Universal Fluid Cells that follows my standard set of Rules for Fluid Containers, and are all stackable up to 64 and Gas Proof.
Since I was not able to settle for a specific Name of them, due to "Cell", "Capsule" and "Container" being already taken by other Mods and "Can" not really fitting the purpose, I decided to go for "Capsule-Cell-Container".
There is 22 different possible Materials they can be made of, which includes Plastic, all the Wax Types and lots of Metals.
1 Unit of the Material it is made of equals 1000L of Fluid. Usually Drums are better, but those can't be made of Wax or Tin.
They can only be made using the Extruder and the Capsule-Cell-Container Shape.
Did I mention that you can paint them too, just like all the other GT6 Containers?


6.05.43:
[FIXED] Calcification Display of the Boilers.
[FIXED] 3 Mixer Recipes missing their Output Fluid.


6.05.42:
[REMOVED] The GT6 Meta-IC2-Cell-Item, because it won't ever be used anyways (I have a better System for that kinda stuff by now, see Measuring Pots). Also removed some unused Textures aswell.
[REMOVED] Recipe for the Universal Fluid Cell of IC2-Exp. I also replaced the Universal Cell with Empty Cells in Recipes that needed it.
[REMOVED] The "cheaty" Recipes from the Metal Former. The Metal Former itself is still craftable due to compat Reasons.
[CHANGED] IC2-Exp Empty Fuel Rods are now made with Zirconium instead of Steel.
[ADDED] Plantalyzer, a Machine that scans IC2 Crops and Forestry Saplings, similar to how the Bumblelyzer does it, but it does not use Honey at all.
[ADDED] Queue Hopper, a Hopper that remembers the order at which things came in, and always emits the first inserted Item and works it's way to the last inserted Item in an ordered fashion. Professionals would call it a FIFO (First IN, First OUT). You can manually change the Order in its GUI ofcourse.
[ADDED] Glow Glass, a variant of my Clear Glass that emits light like Glowstone. It is produced with the Injector, 1 Glowstone Dust per piece (or half a Glowstone Dust per Slab) and a Block of GT6 Clear Glass. Aside from emitting a constant Light Level this Block uses the same Texture as the normal Clear Glass.
[ADDED] The Chemical Formula Tooltips back. Now even with Subscript Numbers instead of normal ones!
[ADDED] Satanic Bumblebees that produce Soul Combs (Soulsand & Soulsand Oil). Spoiler alert do not continue reading if you wanna find out the combo yourself, it is the combination of CITSILIHIN&CINOMED (you have to read those in reverse).
[ADDED] A Book containing Descriptions of all current GT6 Tools. It is added to all Loot Lists that contain Books of that kind.


6.05.41:
[REWRITE]
The Worldgen Code of GT6 got improved a lot, and that did change quite a lot of internals on how my Worldgen works, including the Config Files. This will also let GalacticGreg crash very hard, due to the massive Changes I made (not that that Addon is needed anymore since I add that Compat myself now).
[COMPAT] Galacticraft & GalaxySpace
Moon and Mars now have GT6 Rocks in their Worldgen.
Moon, Mars and Asteroids now have GT6 Ores. Mars and Asteroids even have Naquadah and Dilithium related Ores.
Schematics and Keys now go into Book Shelves.
Scanner and Printer can now copy NASA Workbench Schematics, and the Schematics can ofcourse be stored on USB just like Books. This is especially useful in Multiplayer since the NASA Workbench eats those things per Player.
[FIXED] GT6 Dungeons were spawning in Twilight Forest and on other Planets (now they don't anymore).
[FIXED] A Bug that caused an exponential Worldgen Loop. I don't know if that Bug was in 6.05.40 already or if it was purely in-Dev, but it's fixed now. (that damn Bug was the reason for wasting a shitload of my time)
[CHANGED] AE Grindstone, because it is way too exploitable to use that thing the way it is (especially because it made Aluminium way too easy if Aluminium Ores are present). I decided to add a lot of Crop related Recipes to it, and kept the Quartz and some low Tier ones, but I removed ALL OTHER RECIPES in it.
[CHANGED] Lead Armors from Thermal Foundation and Galaxy Space now count as Radiation Proof like a Hazmat Suit. (does not apply to IC2 Items that happen to be radioactive)
[CHANGED] The Recipes for dyeing Blocks in the Bath now use less Dye. Using the Spray Cans is still twice as "Dye-Effective" as the Bath.
[IMPROVED] Implemented AEs IMovableTile Interface on my TileEntities. Teleporting them should cause less Issues now.


6.05.40:
[NOTE] Bear has Advanced Rocketry on his Server, and for some reason it turned off The Galacticraft Oxygen System, as if you have used the Config for that (and that caused by just updating GT6, it even fixes itself if you downgrade GT6). If that happens to you too, make sure to check everything Advanced Rocketry Config related, maybe it somehow gets ignored or corrupted.
[COMPAT] Galacticraft
Made most GT6 Blocks
Sealable (Bricks, Glass, Concrete, Asphalt, Long Distance Wires, Dry C-Foam etc),
Conditionally Sealable (for Slabs that are only sealable on one side) or
Entirely Unsealable (Stone/Cobblestone/Mossy/Cracked variants of Rock, Wood of any kind, Bales, Wet C-Foam and ALL Machines).
C-Foamed Wires and Pipes will always be sealable. Most Covers that aren't considered simple Attachments can seal things aswell, Canvas is NOT sealable so you need C-Foam behind it, Huge Pipes without C-Foam are NOT sealable either.
[COMPAT] Galacticraft
I made all shaped Crafting Table Recipes of Galacticraft use Plates (and the different variants of Copper/Iron/Steel) as a potential alternative to the Compressed Items, without messing up OreDict, in order to make Crafting less of a hassle. This does NOT apply to the Recipes inside the NASA Workbench or Machines.
GT6 Batteries are now working to charge Galacticraft things. (I intentionally made GT6 Batteries not chargeable in GC Stuff, so it is one way)
GT6 Wrenches now work on Galacticraft Machines.
GC Machines can now be powered by GT6 EU. I tried my best to make it as lossless as possible, please don't use absurd Voltages like anything past HV.
Bushes do count as Leaves for the Oxygen Collector now.
Desh related Material Data Issues got resolved.
Oxygen Tanks can now be filled in a Canning Machine (Taps on Drums are NOT Canning Machines, I won't make it THAT easy on you!)
Blacklisted Oxygen Canisters for the Tap and some other things! The only GT6 thing that can fill those Canisters now, is the Canning Machine (emptying them via Funnel is no problem).
[FIXED] All Aluminium Ores now crush into Alumina (and they drop twice as many crushed Ores now), and they cannot be smelted into Aluminium either, so even if someone doesn't disable Aluminium Worldgen from other Mods they still have to go through GT6 Aluminium Processing to make it useful (unless they use other Mods Machines to crush it).
[FIXED] Low Tier Rotational Pumps were not working at all for some reason.
[FIXED] GT6 Brown Clay and Mud were preventing Mobspawns ontop of them despite being "natural" Blocks.
[CHANGED] The Wood Plate that is outputted by Recycling Recipes of Vanilla Objects got replaced by the "Generic Wood Plank" Block, so it is placeable now. Also all Planks can now be Crated, not just the Wood Plate ones.
[CHANGED] Made Chisel Purpur Blocks compatible with Et-Futurum Purpur whereever possible, and removed the Chisel Purpur Block Recipe if Et-Futurum is installed.
[CHANGED] Ender Bumblebees now have slightly different requirements if Et-Futurum is installed. They will in that case always require either Chorus Flowers or the Dragon Egg, anything else that they usually accept doesn't count then.


6.05.39:
[FIXED] Electrolyzer and Centrifuge not giving Byproducts in a lot of cases for Impure Dusts and Purified Dusts.
[FIXED] Bumblebees can no longer attack Skeletons, since Skeletons are - well... Skeletons.
[FIXED] Hand Drill not using Steel Rods (or was it Iron Rods? well one of them didn't work properly) to reinforce Bricks.
[FIXED] Bumblebees won't spawn in Space anymore (Galacticraft etc).
[CHANGED] The Hammer Prospecting only uses 1/10th of the Durability it used before.
[ADDED] Liquid Oxygen (compatible with Galacticraft). And a Freezer Recipe for it.
[ADDED] Rotors can now be made in the Welder. This also decreases their recycling Material amount by 1 Screw.
[ADDED] You don't need to mix Salt and Water anymore to electrolyze Saltwater (but it's ofc still possible). Those two ingredients can be inserted directly into the Electrolyzer now without pre-mixing them. Rocksalt (KCl) works now too and produces the currently useless KOH in the Electrolyzer.
[ADDED]
Concrete and Reinforced Concrete Blocks.
No Walk Speed Boost on Concrete. That is still Asphalts Job.
The Concrete Recipe is Rock+Calcite+Ashes then mix the resulting Powder with Water and put it in the Dryer.
The Reinforced Variant needs some sort of Iron or Steel Rod in the Dryer, or just use the Drill like for the Rock Types.
The Default Color of Concrete is Light Gray and it can be painted easily.
Unlike C-Foam it is not pre-colored, but you can autopaint it using the Bath and Dye on the dried Blocks, just like Asphalt.
It has a very smooth Texture, much smoother than the Tiles Construction Foam has.
Regular Concrete needs Stone Pickaxe Level, Reinforced Concrete needs Diamond Pickaxe Level and needs 4 times as much Time to break it.
Blast Resistances are 12 for regular Concrete and 48 for Reinforced Concrete (12+ is Creeper Proof, 16+ is all TNT Proof). For Comparision: Basalt = 18 (36 when reinforced), C-Foam = 24, Obsidian = 36 (gets nerfed to this value by both GT6 and IC2) and Black/Red Granite = 36 (72 when reinforced).
[ADDED]
Autoclave, this time slightly different than the old one, and made with loads of Stainless Steel. (Singleblock still)
It is powered by direct Steam on the bottom Side and is a process that cannot be sped up as it is a Time based Machine.
Most if not all Recipes use about 32 Steam per Tick when running constantly, but knowing most people, they will just slap a stronger Boiler below, to instant fill it up, so that they dont have to worry about putting out the Burning Box in time, what is perfectly fine too. What would not be fine would be if people used a Steam Tank with it, but luckily there is no such thing in GT6. :P
Bauxite Dust Processing with NaOH got moved from the Bath into this Machine.
Most Quartz alike substances can be crystallised with it, this also includes Amber Dust, IC2-Exp Energium Dust (Compressor Recipe got removed now) and the Quartz Seeds from Applied Energistics.
[ADDED]
Burner Mixer.
This is the Fireproof Variant of the Mixer. It is used to burn the mixed result as quickly as possible.
It requires an Igniter to start this Mixer, aswell as the usual Motor/Turbine below.
Some Chemical Recipes that were i nthe regular Mixer got moved to this one.
Some will also be moved to this one later once I notice that I forgot to move them.
Notify me if you find Recipes that need to usually be burned to output something. (Heating Up does NOT Count as Burning!)
Hydrogen + Oxygen = Water does count as burning, that is a good Suggestion btw for future reference.


6.05.38:
[FIXED] Some Asphalt and Mud related Bugs regarding Walking Speed, because I was trusting Minecraft to be not crappy again... I shouldn't have trusted it...
[CHANGED] Redesigned the Texture of the Meteoric Iron Rock and renamed it to Meteorite (that was the kind that survived landing on Surface, right?, This shit is as bad as the difference between Lava and Magma).
[CHANGED] A lot of the Recipes that require Silicon Dioxide (SiO2) now accept Quartzes directly.
[CHANGED] The Crusher now uses Diamond Gems instead of Plates in the Recipe. The Shredder stays the same.
[CHANGED] The Electrolyzer now has two Item and two Fluid Inputs. Old Recipes should still work like usual if you add a Selector Tag with a 0 into it. Also the Electrolyzer now has additional optional Input and Output Sides.
[CHANGED] Most Cutter Recipes now have a different GU/t Rate. Cutting Gems into full Plates is now even Tier 2. Also the Tier 1 Sawmill only needs a Steel Sawblade, so no Aluminium required.
[CHANGED] Having Acids in the Crucible may result in them breaking apart. Use an Acid Proof Crucible if you have to Handle that kind of Stuff.
[CHANGED] A lot of Electrolyzer and Centrifuge Recipe Outputs. They will do Silicon Dioxide and Alumina whereever possible now.
[ADDED] Mud variant of some Forestry Recipes that need Dirt and Water.
[ADDED] Industrial Bumblebees that produce Combs that yield Latex, Wax and Resin. As with before, you need to combine 2 certain highest Tier Species to get it. In this case, Spoiler alert do not continue reading if you wanna find out yourself, it is the combination of DETAVITLUC&CITUANBUS (you have to read those in reverse).
[ADDED] Royal Bumblebees that produce Combs that yield Royal Jelly from both Harvestcraft and Forestry. Spoiler alert do not continue reading if you wanna find out the combo yourself, it is the combination of DETAVITLUC and highest Tier YDNAS (you have to read those in reverse).
[ADDED] Titanium Drum. Twice the Capacity of the Stainless Steel Drum, but no Corrosion Resistance.
[ADDED] Iridium Fluid Pipes (Acid Proof).
[ADDED] Flour and Rice can be used to make Biomass too now.
[ADDED] Sugar + Sulfuric Acid => Carbon + Water + Sulfur Trioxide (in Mixer)
[ADDED] a Tooltip to Items to show if they can be used to Pay a Beacon to make it start. Also made several GT6 Items usable as Beacon Payment.
[ADDED] The Bumblelyzer can now scan also Forestry Bees, but it is ONLY Bees, not Forestry Trees or Forestry Butterflies. This uses only 50L of Honey instead of the usual 100L Forestry uses.
[ADDED]
Alumina, and made all Ore Byproducts that were Aluminium into that Material instead. Have fun with that nerf. :P
Centrifuging Sapphires and Rubies will now give Alumina and the Impurity, instead of the old Aluminium + Oxygen + Impurity from the Electrolyzing that has now been removed for this group of Gems.
Processing Alumina into Aluminium will require some Chemicals, like Hydrofluoric Acid (HF) for example.
You need a Mixer for most things, and in the end you need to put the Chemicals along with Carbon and Alumina into the LV+ Electrolyzer.
The overall Bauxite Processing chain yields 1/2 Unit of Aluminium per Unit of Bauxite and some byproducts such as Ilmenite and Rutile.


6.05.37:
[FIXED] Some Issues that came up during Bear testing. Not in the mood to mention all Details atm. :P
[ADDED]
Breeding System for Bumblebee Species (it was already there but it was unused).
Take 2 Bumbles of the highest Tier of their Breed (Cultivated would be the highest Tier of the Wild ones) and breed them to get a potentially new Bumblebreed of offspring with a 25%-50% chance.
[ADDED] Clay Comb Producing Bumblebees. They are created by breeding 2 specific Types of Bumblebees together. Since it is the first and only type for now, I will give you a hint, so Spoilers ahead if you don't like them: It's SIVLEBMUB&DETAVITLUC (you have to read those in reverse).


6.05.36:
[REMOVED] The Sound Steam Engines do while running, because it Lags, because the Sound System sucks...
[ADDED] Redstone Repeaters can be attached to GregTech Red Alloy Wires and similar, in order to have a one-way output. (no timing in this case!)
[ADDED]
Aggressiveness Stat to Bumblebees to determine the rate at which they will sting you as an Area of Effect.
In order to Bumblebees to produce Combs, there have to be "Flowers" nearby (range depends on Bumbliary). The Flowers depend on what Bumblebee Species you have and can range from Potted Plants all the way up to Dragon Eggs or End Portals.
[ADDED]
A Bumbliary (Apiary but for Bumblebees) is used for producing Stuff and breeding with Bumblebees and crafted with an empty Bumble Hive in the Recipe.
If you break the Bumbliary Block, while the Bumblebees inside are still active (so while a Queen is inside and working), it will MURDER all contained Bumblebees.
In order to access the content of the Drone Slots and the Royal Slot, you have to open the GUI using a Scoop. You can insert Drones and Princesses without Scoop though.
If the Bumbliary is active (a Queen is inside), you will get attacked when you access it, no matter how tame the Bumblebees inside are.
The Quicksilver Thermometer will show local Temperature and Humidity when used on it.
GUI Access is only possible on the Top Side of the Bumbliary!
The Range for Bumblebees to find Flowers is up to 3 meters away from the Bumbliary, so it has a 7x7x7 Range. (It will scan closeby Blocks on the same Y-Level first before scanning the far Corners for performance reasons, for all people worried about Lag who wanna optimize their Setups).
If Bumblebees are inside, despite not being able to bee inside, they will die. Same goes for the ones that cannot bee outside being outside.
Having a Glass Roof counts as INSIDE so be careful about that. It checks if it can Rain next to and ontop of it in order to determine what of the two is the case.
If they are inside, then Rain and Storms don't affect their work output, but Day and Night still do! (they have tiny little Clocks with them to see if they have to work or not)
[ADDED]
The Bumbliary cannot be automated with Pipes or Hoppers. However the Bumblebees inside it will automatically try to breed again if left alone for a Minute, preferrably with the Drone in the Purple Slot (if there is a Drone), otherwise with one of the Drones in the surrounding Slots.
If an unscanned stack of Drones is inserted into the special Drone Slot, it will try to place offspring there first to refill the Slot, this however comes at the cost of always using up two Drones, if there is more than one Drone in that Slot.
So you have to have an Offspring rate of at least 2 on your Breed to automate that, actually 3 because random mutations can screw you over.
As an exception to the Automation Rule, things that are laying at the Floor of the Bumbliary can be extracted via Pipes and Hoppers though, this currently only applies to dead Bumblebees.
[ADDED]
It is possible to have more than one Princess show up every now and then inside the Bumbliary, but make sure that you grab them quickly, because unlike the Drones, the overflow Princesses will die once the breeding begins (because the Queen orders the Drones to kill them, just like IRL).
In case of more than one Princess showing up, the most aggressive one will always become the Queen.
Whenever there is multiple Species of Drones to choose from, the Princess will always try to choose the Species that matches her own Species.
If you put a Bumblebee into a Bumbliary that is definitely not suitable for it (wrong Biome etc) it will just die, so be careful what you do.
That Rule does NOT apply to the Drone that you insert to breed with the Princess.
However, the resulting Offspring has a large chance of instantly dieing, due to it not being fit for the Environment it is born into.
But that has the benefit of guaranteeing survival for the remaining Drones, since the unfit Offspring will just die, you can target breeding much more precisely that way (If you have enough Princesses, because those can die too if unfit).


6.05.35:
[CHANGED] whenever small Gem Ores drop a flawed or chipped Gem, they will drop 2 or 4 of them respectively.
[ADDED]
Bumble Bees. Totally not a Forestry Bee Ripoff or something. The System is quite a bit different and less forgiving.
Bumblebees have a chance of Mutating over time for every generation. This makes short Lifespans very useful if you want to upgrade the breed. The Lifespans can range from 1 Minute to 2 Hours.
However a short Lifespan also means that the chance of mutating in the wrong direction is more likely, because the Mutation can also happen to be a Downgrade, so in the end you likely want a long Lifespan on the highest Tier of Bumblebee to make it less likely to downgrade randomly.
Every Bumblebee Species has a different Texture. Most of them are quite funny. Also there appears to be a Bumblebee Species that seems to have access to some substance I would never add to the Game. XD
[ADDED]
Bumble Hives. Harvestable with Scoop.
They spawn mostly on the Side of Hills, as they have to be 1 Block below ground, while still having to be visible from the Side.
The more Drones a Hive drops, the better the Offspring Count of the Bumblebees of the broken Hive. (1 = Bad, 4 = Max)
The more Combs a Hive drops the better the production Rate of that breed of Bumblebee (1 = Bad, 10 = Good)
Hive Blocks can be collected, but they won't drop any more Bumblebees if you place and break them.
You can paint them or use them as Deco if you want.
[ADDED]
Bumblelyzer (Beealyzer BLOCK for analyzing Bumblebees with the help of Liquid Honey or Honeydew, I don't plan for an Item Variant to exist at all)
To scan a Bumblebee it has to attach a tiny piece of Paper to the Bumblebee with the Data printed on it (the inkless kind of printing).
It can process up to a whole Stack of Drones for just 10L of Honey(dew), since it only needs to scan one Bumblebee and then attach the Paper Tag to all 64 of them.
If for some reason an already scanned Bumblebee enters it, it will not cost anything but a little bit of Energy to deposit it in the Output Slot.


6.05.34:
[OOPS] I overrode the previous version with this one... Guess I gonna combine the Changelog Parts for that then...
[NOTE] I changed the way how Mechanical Safes and similar synchronise their Owner to the Client, by basically only sending over the Information of "That Safe is not yours" to the Client, instead of sending the whole 16 Bytes of Player UUID, which would waste way more Network Bandwidth than I'm comfortable with, considering I just added spammable private C-Foam Blocks.
[FIXED]
Way too many Recipes, so that they use counterpart Materials properly.
Like Knightmetal or Meteoric Steel being able to be used in more Steel requiring Recipes and similar.
I really did way too much of those fixes, and now I don't know if I find the time to make actual new Features this week...
[FIXED] Measuring Pots and Clay Jugs were not able to fill a Cauldron with Water and Bathing Pots and Mixing Bowls will fill with Rainwater now.
[ADDED] The Magnifying Glass can now identify Rocks.
[ADDED] Iron alike Rocks can be used for Flint&Tinder now. This includes Meteoric Iron Rocks if you really don't wanna smelt them into 2.25 Nuggets.
[ADDED]
Advanced C-Foam Spray made mixing Palladium Dust with regular colored C-Foam.
It can only be broken by its Owner, or strong Explosives.
Also prohibits Tool usage or putting on Covers by anyone other than the Owner.
[ADDED]
You can now add Potion Effects to solid Food Items by bathing them in the Potion Fluid or in Medicine/Laxatives.
The whole thing is capped at 1 Potion Effect per Food Item though, so no Effect combinations.
It does not work with Splash Potions or Lingering Potions.
There is not going to be any Tooltip showing what Effect there is on the Food, so you can't see if someone is gonna poison you.
[COMPAT] Made sure some Bluepower Items were properly recognized by my System.
[FIXED] The Death Point Function of the Compass to work on Servers now.
[FIXED] Some Creative Tab Issues.
[FIXED] Flint bearing Rocks not generating at all in 75% of the Chunks, because I forgot to remove a randomizer that I used in Berry Bush generation, also lowered the per Chunk spawn Rate of Rocks, because of this being fixed now.
[FIXED] Debarking Non-GT Trees not causing the Fast Leaf Decay Update.
[FIXED] Cauldron washing Ores was not working properly when the item starts bouncing around.
[FIXED] Meteoric Steel missing the alloying Recipe. Also Meteoric Iron/Steel is a good Crucible Material and easier to handle than Vanadium.
[FIXED] Forgot to fire an Event when a GT6 Tree grows from a Sapling.
[FIXED] Tools made from Infused Water Shards from Thaumcraft didn't require the Thaumium Handle for some reason.
[CHANGED] The Adventure Mode Starter Kit is now disabled per Default, and it now just contains some Sticks, some Flint, some Dry Grass, a Large Sandwich and a Sixpack Purple Drink.
[CHANGED] The Texture of the regular Rock Item to make it easier to distinguish from the Ore bearing ones.
[CHANGED] Berry Bushes are now Light and Rain sensitive. Rain will let them grow twice as fast. If the Bush is within Sky Light, it won't check the actual Light Level and just grow as usual. Only the Center of each Bush is going to be checked for Light and Rain.
[CHANGED] Crucible Molds can now be hand harvested. (only if you placed them after this update!)
[IMPROVED] I made a special Crafting Recipe Type for Toolhead+Handle to reduce overall Crafting Lag and NEI clutter, meaning that no Recipe will be visible, and that the Tooltip is essentially required to determine the needed Handle. Also in some cases you can for example use different Types of Wood or Steel Handles now.
[IMPROVED] The amount of Crafting Recipes is now drastically decreased, by replacing shapeless Crafts of Nuggets to Ingots etc, with one Recipe per Type instead of one Recipe per Type+Material. This basically killed NEI support though, so I need to think about how to solve that.
[ADDED] Tooltips to hint at Shapeless Recipes for Nuggets, Ingots and the likes that lack NEI Handlers.
[ADDED] Small Ores for Salt and Rocksalt, and also Small Ores for Teslatite and Electrotine when their respective mods are loaded.
[ADDED] Sticks to Worldgen, the same way as Rocks basically.
[ADDED] Tooltips showing which Handle you need for a Tool Head, and if the Toolhead needs to be sharpened.
[ADDED] Gem tipped Steel Pickaxes, which however only have a quarter of the Durability of their Full Gem Counterpart. Useful if you have flawed Gems (2 per Pickaxe) and don't know what to do with them. In order to make them you need Steel, so you need to be willing to "waste" 3 Units of Steel for making one. Also some Gems are NOT a good Idea to use for this purpose!


6.05.33:
[FIXED] Zombie Pigmen don't drop the same stuff I added to Zombies anymore. Instead they drop different slightly more Nether related things now.
[CHANGED] Adventure Mode Axe is now Adventure Mode Knife.
[CHANGED] Dirty Water and Seawater filled Wooden Buckets can be emptied by Sneak-Rightclicking.
[ADDED] Fast Leaf Decay Functionality to all Trees now. It can be turned off in the Config if seriously needed. That makes the fast Leaf Decay Mod worthless to have since it's now integrated into GT6.
[ADDED] Zombies can now drop regular Stone Rocks too, at the same chance the Flints are dropped.
[ADDED] Coal and Lignite Rocks can be used for Torches btw. (forgot to add that to the changelog earlier)


6.05.32:
[FIXED] Bushes attaching to already attached Bushes, even though I only intended the grounded Bushes to be attached to.
[FIXED] Juicer voiding excess Fluid if you juice too much Stuff at once.
[FIXED] Mortar is now also craftable with Steel Ingots, not just Iron Ingots.
[FIXED] Saplings were not burning as Furnace Fuel. Also fixed several burn Values for Wood related Blocks.
[FIXED] Exploits regarding Wood and Iron Doors being worth 6 units even though some Mods add the x3 Doors Recipe of future MC Versions, ending up making it worth only 2 Units. I will x3 the Iron Door Recipe too from now on.
[CHANGED] Regular Honey Combs and Wax Combs can now be done via Squeezer, Juicer and Mortar. Not just via Centrifuge. I only do the Forestry-Only Combs as a Centrifuge exclusive, because their thing is a Centrifuge aswell.
[IMPROVED] Juices (but not Smoothies) have more Food Value now, so they are a bit more worth it.
[ADDED] AE Presses can be made from Scratch using the Tier 3 Laser Engraver, making it more possible to disable those annoying AE Meteorites.
[ADDED] Horses, Donkeys and Mules now drop Meat when killed. They will also drop more Meat if they have good Jump or Health Stats. (I intentionally left out the Speed Stat, because it's not variable for Donkeys and Mules)
[ADDED] Zombies now have the following additional Drops when killed by a Player (Looting does not affect these chances): 25% Flint; 20% Stick; 10% Mud Ball; 5% Matches;
[ADDED]
Compass that actually points towards North.
It even has a few Modes, like pointing into the direction that you Face like on a Map (aka inverse North) or pointing to Spawn like the vanilla Compass.
Even to your latest Death Point, if you don't quit the Game like a whiny Ragequitter. Relogging without entirely closing the Client won't delete that Point.
All Compass Modes aside from the North Mode are hidden from NEI.
[ADDED]
Tiny Rocks that are spread over most Biomes. Their worth is 0.25 Units of Material. (also ofcourse I got that Idea from Terrafirmacraft)
They can indicate that there is a Large Ore Vein up to 25 Blocks below the Ground in a closeby Chunk, if the Rocks themselves contain a certain Ore.
Some Rocks are also dropping Flint, that can be used for Flint Tools. There is a 1 in 32 chance that the Flint will be a Meteoric Iron Rock instead (I also tweaked Meteoric Iron and Meteoric Steel).
Other will just be literal Rocks that can be used for some early Tools but not for Swords or Knifes. They can also be crafted into Cobblestone Blocks if you got 4 of them.
The Coal Variants can be burned in a Furnace too, if you desire to use them that way.
It should be possible to find those Rocks in the Nether and the End aswell, just not that abundantly.
Rightclicking will directly insert the Rock into your Inventory, but I would use a Fortune Pickaxe on it instead.


6.05.31:
[ADDED]
A Config to use Electric Cables from GT6 as RF Emitters (1EU:4RF Ratio so it is 1:1). Placing Stuff adjacent to a GT6 Battery Box or Dynamo will work too.
You can power most random RF Mod Stuff with GT6 EU using this Config (but NOT the other way around!). If anyone prefers this kind of Compat over the inbuilt Engine RF Compat, they can turn it on.
Note that it will not convert EU to RF for GT6 Machines that happen to accept RF (such as the RF Converters), unless you have a RF Conductor inbetween. This is because my Stuff always prioritizes my own Interfaces and not the RF ones.


6.05.30:
[FIXED] Basic Machines when they got interrupted, while having the Running Possible Circuit on them, were not restarting as soon as possible, unless you inserted something that would match the recipe.
[FIXED] A ton of Bugs regarding the recharging from IC2 Battery Armor Pieces.
[FIXED] Several Issues that happened when no version of IC2 was loaded, and reworded the Requirements List on the Download Page to reflect this fix.
[FIXED] Engines were not working with the Adjacent Machine ON/OFF Functionality.
[CHANGED] Clay Jug and Clay Measuring Pot Recipes now require the Rolling Pin to be made aswell (in order to flatten the Clay easier before shaping it).
[CHANGED] Flint Tools, except for the Pickaxe, the Sword and the Club, can now be made in a 2x2 Grid (I made every Flint Tool only require one Stick/Bone, just like regular GT6 Tools do, since those are also 1xHandle + 1xToolHead)
[CHANGED] Sneaking while rightclicking a Dust Funnel with a Monkey Wrench will now cycle backwards through its Modes.
[ADDED] Battery Boxes can now finally charge and decharge IC2 Items, I made the Tier conditions very loose, so you can do MV Stuff in LV and vice versa. Note, that Energy and Lapotron Crystals will not work in them and are blacklisted. However the Armor Pieces are not blacklisted.
[ADDED] Jugs and Measuring Pots can now fill Growthcraft Rice Paddies with Water by clicking the Rice ontop of the Paddy. It will reduce the Water content by up to 70 Liters for a fully dry Paddy.
[ADDED]
Brown Clay Blocks, aside from being Brown-Orangeish they are almost exactly like normal Clay Blocks.
This variant is slightly different as it contains Potassium instead of Sodium.
The Lithium Content stays the same as the one of regular vanilla Clay.
It is possible to generify any Brown Clay into vanilla Clay, what essentially would be able to turn its Potassium into Sodium, but that would be pointless considering how infinite Sodium is through Ocean Water.
[ADDED]
Clay "Veins" below Grass in Plains and Savanna alike Biomes (Independant from the Ore Vein Generation Grid).
It will generate 3 times more often with Brown Clay than with Vanilla Clay.
So you can now have large 48m x 48m Clay Mining Pits that are at most 7m deep.
I did this also because Clay is annoying to acquire when digging for it in Water, and because Bricks look nice as Building Material. Also also, Mesas shouldn't be the only Biome where you can get Mass amounts of Clay for Clay Dust.
[ADDED]
Mud Blocks, which will slow down anyone walking on them and are Spade compatible.
Swampwater will turn all adjacent Dirt or Grass Blocks into Mud Blocks when updated or freshly generated.
You can Plant Sugarcanes on Mud even without an adjacent Water Source.
[ADDED]
A Prototype for Berry Bushes (Prototype as in no Crossbreeding System what-so-ever for now, they DO Generate).
The one you can get from the Creative Menu does not have a Berry assigned to it, just rightclick the Bush with a Berry of your choice to set it.
There is no benefit in attaching a Berry Bush to another, it just looks better and has a smaller Size, while giving exactly as much as a regular sized Bush.
They will generate in Forests and Plains with random Berry Type.


6.05.29:
[FIXED] Jugs and Measuring Pots crashing when rightclicking a TileEntity while filled with Water and with IC2-Classic being installed instead of IC2-Exp.
[FIXED] Club did not give Time to Strike Achievement.
[CHANGED] You can now only drink from placed Jugs if you click their Top.
[ADDED] Jugs, Cups and Measuring Pots now get filled with Water when they are in the Rain.
[ADDED] Jugs and Measuring Pots can now fill Growthcraft Rice Paddies with Water, and that slightly more efficiently than Buckets, but as a slight downside not in a 3x3 Area.


6.05.28:
[NOTE] Before Updating to this Version it is advised to Update to 6.05.26 or 6.05.27 first for making sure the last Slot in the Cutter doesn't get filled with the Fluid Display Item. (I added one more Output Slot to it for the Bark)
[REMOVED] The old 2D Measuring Pot and it's corresponding Item, that I once planned to use more, but that Idea got replaced entirely by 3D Fluid Containers instead.
[CHANGED] Flint Tools can now not only be made with Sticks but also with Bones.
[IMPROVED] Ore Byproduct List in NEI.
[ADDED] Coal and Graphite Ores can now be bathed in HF to get more Graphite out of them.
[ADDED] A Ceramic Jug for early drinkable Fluid Storage, that stores up to 2000L (can be filled by clicking Fluid Blocks, but won't place them in World). Also changed the Measuring Pot Recipe to no longer require a Bending Cylinder. Note, that a Jug can contain 2000L of Lava, what is more than a Crucible can hold at once (18 Units overall), so you cannot dump a full Jug into the Crucible.
[ADDED] A Club (weapon) so that Bear989 can go full Caveman on Mobs with slightly higher Damage than a Sword. It works like a slow Pickaxe too, and it costs 6 Units of Material and crushes Blocks, just like the Hammer.
[ADDED] Ore Blocks for PFAA Stones, even though GT6 disables Ores per default if PFAA is loaded. It somehow only grabs the 16x16 Textures when copying them. I guess that is some weird PFAA Render thing and therefore not really something to fix on my side without way too much effort.
[ADDED] Vanilla Furnaces now require some sort of Firestarter in their Recipe to be crafted. (Matches, Flint&Tinder, Flint&Steel, Fire Charges and Lighters work too)
[ADDED] Cinnamon Tree, that has Cyan Planks. Instead of Bark it drops Cinnamon when being unbarked. The Cinnamon won't regrow though, so you can cut the whole thing down anytime.
[ADDED] Tree Bark. It can be outputted in half the usual amount by the Cutter when cutting non-debarked Logs, and it counts as a Dust. It can be used for Wood Pellets too.
[ADDED] Dry Bark Version of the Firestarter. Dry Bark can only be gotten from debarking Dead Logs (which do spawn even in Deserts).
[ADDED]
Random rotten, dry (dead), mossy and frozen Logs to the Landscape.
They don't count as Logs for the OreDict, but they do have Planks (which do count for OreDict)
3 of them can be made using normal Logs in Machines.
The Mossy ones spawn Mushrooms and Harvestcraft Mushroom Gardens ontop of them. So the original Mushroom Gardens, that don't really generate that nicely looking, could be disabled.
[ADDED]
Wood Beams for MC, IC2 and GT6 Logs, which are basically just Logs without Bark on them.
Only used in Processing (gives +1 Plank) and as Decoration, not gonna do anything with Physics on them.
They can be made by rightclicking a compatible Log with a Saw, Knife or Axe, the Axe only losing half as much durability as the others.
Works with most Wooden Logs of other Mods, but will only result in a generic looking Wooden Beam (I tried my best to at least let the rotation match after rightclicking).
[ADDED]
Hydraulic Debarker.
It is debarking Logs to not only get more Bark for your Bite, but also more Wooden Planks from your Logs in the Sawmill.
It does work on all modded Logs too, but will only output a "generic" debarked Log in those cases that will give you a "generic" Wooden Planks Block.
It has a left to right configuration in regards of Input and Output like the Sawmill, while auto-accepting Water from the top and rotation Power from the back.


6.05.27:
[IMPROVED] Some of the Swampwater and Oceanwater Worldgen. Also fixed Lighting in newly generated Oceans and Swamps, because Minecraft was very retarded.
[ADDED] Dryer Recipe for Swampwater (less efficient in making distilled water than literally anything else, but it makes Dirt!)


6.05.26:
[FIXED] Lighting Issues in GT6 Dungeons, if they somehow aren't fixed in NEWLY GENERATED ones, please tell me.
[REMOVED] The Recipe for the Actually Additions Fermenting Barrel, because it should not be THAT stupidly easy to make Oil.
[REMOVED] The Supporter Lists are now no longer attempting to connect to Dropbox, if the normal connection to the GT Website fails (what never happened so far, but hey I'm paranoid), since the Dropbox Links are invalid anyways, because Dropbox sucks.
[CHANGED] Doubled the amount of Samples in the Prospecting Behavior of Hammers. Note that Sample Count has nothing to do with the Range! The Range is still the same (as in dependant on the Tool Quality).
[CHANGED] Ice Cream Recipe to require Cream instead of Milk. Also added Honey Ice Cream.
[IMPROVED] The Nether portal Room now contains a Chest with matches and some Nether related Items, aswell as a Netherwart Farm on the Walls to left and right.
[IMPROVED] The Workshop Room now has a Bookshelf that is guaranteed to contain all the Main GregTech Manuals.
[ADDED] Fat Stat to the now five Food Stats a Player can have (it's about Blood Fat, not Body Fat :P), which will cause a Heart attack if it gets too high (like if you eat 2 Bars of Butter in a row or something stupid like that).
[ADDED] Twilight Forest Portal Room to the GT6 Dungeons (the Portal is not lit, but it is ready to just throw the Diamond in to activate it). It can only spawn if there is a Nether Portal or End Portal already and Twilight Forest is installed. There is also a Chest containing a Diamond, 16 Liveroots and a Twilight Forest Portal Manual (for people who dont know how to throw a Diamond into that Water Pool).
[ADDED] Mystcraft Library Design to the GT6 Dungeons if Mystcraft is installed. Also added a Myst Portal Room, which can only spawn if an End Portal Room already exists.
[ADDED] Dirty Water to Swamps. Remember Ocean Water? Dirty Water is similar, but won't spread towards Air and will fill Bottles and Buckets with Dirty Water.
[ADDED] Ore Block variants for Granite/Diorite/Andesite of Chisel and Et Futurum, Marble/Limestone of Chisel and Abyssal/Quarried Stone from Railcraft.
[ADDED] Medicine Bottles which are always drinkable regardless of Hunger Bar State.
There is the healing kind with 20 Hearts, so it's useful for people who have more than 10 Hearts.
And the Hunger Bar reducing kind aka Laxatives, which remove 10 Hunger/Saturation (and no, I will not add anything other than the Hunger Effect to the consumer of it!).


6.05.25:
[COMPAT] GT6 Shovels can now place Rice Paddies in Farmland, just like vanilla Shovels, and GT6 Wooden Buckets can fill them with Water (they apparently were already able to do that before).
[FIXED] Gas Burners not working with the Adjacent ON/OFF Feature due to me forgetting about that Block.
[CHANGED] You can now fill Wooden Buckets with Distilled Water, this however will un-distill the Water.


6.05.24:
[COMPAT] Growthcraft Community Edition is now more compatible. Like its Bamboo can now be used in a Coke Oven (I also added a Recipe for Biomes O'Plenty Bamboo for this purpose aswell)
[FIXED] MV and HV Chainsaws consuming way too much Energy for Treecapitation.
[FIXED] Control and Detector Covers which are placed on Universal Extenders which are placed on formed Multiblock Parts were not working, because the Multiblock Part didn't implement the Interface for that.
[FIXED] Bottom Texture placement on the Multiblock Centrifuge being Mirrored along the Z-Axis.
[FIXED] A Bug where Supporter Certificates were not given to people, who had a full Inventory at the time they were supposed to receive it, it will now wait until there is a Slot free for the Certificate to spawn in. You can delete "gregtech/certificates.support.dat" from your Save File if you want to reset the "already given" status for all Players.
[FIXED] Oceanwater Block Render Issues with all the things (namely Underwater Plants), because Forge AGAIN royally fucked up the Logic behind certain checks, such as "shouldSideBeRendered" where it first checks "if (Block is not this) then abort checks" before performing the checks for "all the non-this blocks that could be there", resulting in absolute failure. Sadly I cannot fix that in other Mods, that add Fluids.
[FIXED] Honey, again... Apparently there are now THREE fucking kinds of Honey in MC: Forestry Honey because they wanted to be different and chose to change it's name to "for.honey" (that was ages ago, but is still annoying as fuck), Biomes O'Plenty Honey because it uses the proper name that Forestry used originally ("honey"), and GrowthCraft Honey that wanted to be EXTRA SPECIAL and chose "grc.honey", because we obviously "don't" get serious compatibility problems, when there is multiple different kinds of Honey...
[FIXED] Milk, for the same reason as Honey, since GrowthCraft wanted to be SPECIAL and chose "grc.milk", while Forestry and MFR used "milk"...
[FIXED] Transformers and Battery Boxes turning on/off due to adjacent Machines auto-turning on/off when active/inactive.
[FIXED] IC2 Machines not exploding when being overvolted by a GT Energy Emitter. Note, that it uses the GregTech Tiers of Voltage, so 64 EU/t is still fine for a 32 EU/t Machine, while 65 EU/t would blow it up for example.
[CHANGED] GT6 Chocolate Bars can now be fed to Pets. I don't say it's a good Idea, I just say it is possible now, unless you have Actually Additions installed where I have to default Unification to its Chocolate Bar.
[ADDED] Printer Recipes for Forestry Stamps and Letters. They are overall cheaper than the Crafting/Carpenter Recipes. Also some of them enable different Materials to be used, such as Silver, Zinc, Bismuth or Lead. It does check if the Stamp is even craftable before adding the Recipe. (Forestry disables 20n, 50n and 100n Stamps by default, because anything above 10n is useless in gameplay, but if they are enabled they will have Recipes)
[ADDED] Butter and Salted Butter. Currently a Mixing Bowl and Centrifuge Recipe with Heavy Cream.
[ADDED] Beet Juice (will be required instead of Beets for Distillation into Sugar)
[ADDED]
White and Red Grapes, with Crops, Juices, Smoothies, Wines and everything, simply because Growthcraft has Red Grapes and I only had Green and Purple. (also updated the Raisin Cookie/Dough Textures to reflect this colorful addition)
Now Growthcrafts Grape Juice is the Purple one, Binnies old Grape Juices are Red and White, while the Green Grape Juice/Smoothie/Wine is just a GT6 thing now.
Also Purple Grape Juice now turns into Ricardo Sanchez, what is a Wine that is named very similarily to a character in Rick&Morty. I got that Idea, because I literally saw a Bottle of it at home. (because people are gifting random Bottles of Alcohol to others during Holidays, that no one ever drinks, or that just get re-gifted)
[ADDED]
It was time that those get a bit updated, so GT6 Dungeons now spawn with the following Stuff:
Colored Porcellain Cups and Coins in the Barracks and Libraries*, some of the Cups even being filled with a random Drink. (* = only with the Thaumcraft Library Design, Cups in any Library Design always have stretched Night Vision Potions, because that makes total sense, being a place where you need your eyes to read stuff)
A new un-lit Nether Portal Room, similar to the already existing End Portal Room. (Does not always have to generate)
The Crate Room now also contains Barrels and Drums with useful raw Materials (including Stainless Drums full of different kinds of Raw Oil).
A Room with a Pool and random Glowtus Pads in it, which can contain up to 4 Chests with the Bonus Chest Loot.
Barrels full of some randomly selected Drink for the Default Rooms. They can be Ironwood Barrels 33.3% of the time, and Purple Drink is the most likely Stuff contained in them compared to the others.
A WaterDrum+Taps+Funnel+MixingBowl+Cauldron+BathingPot-Setup for the Default Rooms.
A Corner to the Default Room, where there is the Safe, 3 Chests, a Crafting Table, a Mortar, some Coins, a Measuring Pot, an Advanced Crafting Table and 2 Mass Storages, which contain the Cobblestone Variant of the 2 Blocks that the Structure is made of (in order to make it easier to expand the Area)
The T-Intersections in Corridors now have a few Coins and a Cup with a random Drink.


6.05.23:
[COMPAT] Actually Additions Stuff is a bit more supported now. (The 6 Crystals have fitting Materials now, and they can also be done in the Replicator as a special Recipe)
[COMPAT] Binnies Mod Version pre-15 is supported now, but I wouldn't recommend updating to that one, unless you read its changelog and decide that you want it. You can just stay pre-14 with Binnie-Patcher, because pre-15 removes a ton of stuff such as all the Drinks, I will support both as far as possible.
[COMPAT] Balkon's Weapon Mod is now better supported.
[FIXED] Heavy Cream and Coconut Cream can now be made in a GT Mixing Bowl using a Stick made of Sealed Wood (stick not consumed in process).
[FIXED] Sharpening Recipes for Galvanized Steel and other coated Stuff for Ingot->Rod and similar are now gone (since they didn't make sense)
[CHANGED] The GT6 Oven now works slightly faster and consumes 256 HU per process instead of 400 now.
[CHANGED] Scanners now give more and better Data of the clicked Blocks.
[CHANGED] Debug Items in GT6 Blocks will prevent non-creative Players and Automation (such as Hoppers) from taking them out of their Slots, and Debug Items will not drop when the Blocks are broken.
[ADDED] Crops and Bales for Rye, Oats, Barley and Rice. The Bales won't dry or rot in-world, just like vanilla Wheat Bales won't, so it's safe for decorational use.
[ADDED] Grass Bales will show in their Tooltip if they will dry or rot.


6.05.22:
[COMPAT] Et Futurum Stuff, like GT6 Shovels making Grass Paths, and some minor Recipes.
[FIXED] a Crash with Dual-Hotbars Mod.
[FIXED] a Bug where too small Energy Packets were not accepted but they didn't dissipate either, resulting in Transformers getting stuck sometimes.
[FIXED] Regular and Debug Scanner not scanning anything other than Crops, because the Cropnalyzer Behavior overrides everything.


6.05.21: (already out, but its so late that I cant really write a Patreon post today anymore, that will be happening on saturday then when I am more awake)
[COMPAT] Actually Additions Stuff is more supported now.
[COMPAT] Big Reactors Stuff should be more compatible now. Also has Ore Processing Recipes similar to the Uranium/Uraninite related chain.
[FIXED] Crucible Temperature, when inserting things hotter than the Crucible itself, doesn't instantly rise to the Temperature of the inserted Object anymore. (This makes making Obsidian Tools require a Burning Box or Heater below to run)
[FIXED] GT Leaves should now respect the Fast Graphics Mode.
[FIXED] Tools that can place Torches on Rightclick (Pickaxes, Shovels, Drills etc) can now place Glowstone Torches from Galacticraft.
[CHANGED] If Actually Additions is installed, the IC2 Coffee Crop will switch to using the AA Coffee Beans.
[CHANGED] Peanut Butter Recipe, so that Peanuts will output Nut Oil in Squeezer and Juicer again.
[ADDED] Underground Biomes Rocks can now be generified into Vanilla Stone and Cobblestone.
[ADDED] Boxinator can now make Scrapboxes and the Unboxinator now has a Fake Recipe in NEI for Scrapbox unboxing.
[ADDED] The Large Steam Turbine now accepts Steam coming in from any of the 9 Frontmost Blocks, including from the Sides of those Blocks, meaning that you can now place them in a way that makes the Turbine visible (note: I still didn't do the Front Texture thingy properly). (note2: this Change doesn't affect the Distilled Water Output or any already existing or showcased Setups)
[ADDED] The Sifter now gives Bait from Mariculture for all kinds of different Dirt Blocks, except Mycelium.
[ADDED] Portable Scanner (works for Crops) and Debug Scanner back. The Debug one has Infinite Energy too.
[ADDED] Portable Cropnalyzer, which is cheaper than the Portable Scanner, but only scans IC2 Crops.
[ADDED] Electric Trimmer, which is working like an Electric Branch Cutter.
[ADDED] Vanilla Cauldrons can now be filled with the Tap.
[ADDED] Spades which can Silk Harvest Grass, Mycelium, Clay and Podzol. They are also faster than Shovels on said Materials (and faster on Dirt too).


6.05.20:
[FIXED] Transformers somehow emitting alternating current of twice their supposed Voltage.
[FIXED] Batteries not being able to emit that last tiny bit of charge that is left over, even though they theoretically should be able to.


6.05.19:
[FIXED] Batteries and Tools effectively only having half the Electric Capacity due to both charging and decharging doubling up the Energy amount inside the Battery. (this cancelled out each other, meaning it was non-exploitable)
[FIXED] Transformers in inverted Mode QUADRUPLED the Energy out of nothing since last update, if it didnt get limited by its internal Storage to do so.
[FIXED] Cables no longer count Packets that aren't actually transmitted towards burning the Wires.
[IMPROVED] The Generifier now has the ability to process multiple times in parallel similar to the Coke Oven, making it 100 times faster (not a joke, I made it able to process the same Recipe up to 100 times in one go). This should massively reduce the need for multiple Generifiers in Generic Juice Production (even though I made Generifiers a bit more useless with another improvement below).
[IMPROVED] Juices don't have to be generified anymore in order to use them in a Fermenter for Biomass.
[CHANGED] Battery Boxes can now charge/decharge Batteries with 2 Amps, IF THE BATTERY ALLOWS IT. All current Batteries will NOT allow it.
[CHANGED] The amount of Juice that you get for each Fruit (most of the time increased). And GT Juice => Forestry Generic Juice is now always 1:1.
[ADDED] Boxinator and Unboxinator can now handle 9 Dusts/Ingots/Gems/Plates/GemPlates <=> Storage Blocks.


6.05.18:
[NINJA-FIXED] Transformers in inverted Mode were not accepting Energy properly, leading to most Solar Flowers being broken.
[COMPAT] Et-Futurum Jump and Lingering Potions are now brewable by GT6 means.
[FIXED] Distillation Recipes for Potions+Glowstone/Redstone/Gunpowder and similar related Stuff were missing.
[FIXED] Some Honey related Compat Stuff, also made my Wooden Buckets fillable with Honey now.
[CHANGED] Pump Covers when placed on Fluid Pipes, will automatically set themselves to input into the Pipe instead of emitting from it per Default. Same for Conveyors and Item Pipes.
[CHANGED] Singleblock Dynamos now have a 31.25% Loss, instead of a 25% Loss. This does reduce the Output of them, so check your Setups if they are slightly fragile. (Input values remain unchanged, so no explosions)
[CHANGED] back the Honeydew fermenting Recipe for Biomass, since it doesn't collide with Mead anymore. Honeydew also makes the fermenting even faster than Honey.
[CHANGED] Fermenter Recipes that don't need an Item Input now require a Selector Tag in order to work. Barrel based Fermenting will pretend a Selector Tag with value 0 exists. Most of Binnies Wines now have Recipes, including the generic Fruit Wine (since Selector Tags make it possible).
[CHANGED] IC2 Ores are disabled by Default now. (doesn't apply to IC2 Rubber Trees, doesn't work for IC2-Classic)
[CHANGED] price of Large Coil Blocks to only use 4x Annealed Copper Wires instead of 16x Wires.
[ADDED] GT Dynamite Sticks now automatically have Fortune II on them, meaning their Explosions will drop more Items.
[ADDED] Fireproof GT Planks and Logs can now be made using the Bath and Stretched Fire Resistance Brew. (at least until I add something equivalent to Refractory Wax)
[ADDED] Dense Versions of the Small Metal Tanks, that cost 9 times more Metal and have 4 times more Capacity.
[ADDED]
Multiblock Steam Turbines.
Their Tier pretty much matches the one of the Large Boilers.
They do emit 95% of their Distilled Water, unlike the smaller Variants.
It will void Distilled Water Overflow
The Front Texture is currently a bit Small, I know, it is supposed to span over the entire 3x3 later. Function was more important than form right now ;)
[ADDED]
Multiblock Dynamos.
Their Tier should completely match the corresponding Steam Turbines output.
The Efficiency is 75%, so their Loss is only 25%, so it is better than a small Singleblock Dynamo.


6.05.17:
[COMPAT] RandomThings Spectre Iron is now a valid Material and Alloy, that needs to be created using the Crucible. And the Spectre Keys can be shelved now.
[FIXED] Missing Texture for the Dye Fluids and C-Foam Fluids. (forgot to change the Filepaths inside my Code to reflect the renamed Texture Files)
[FIXED & NINJA-FIXED] Missing Recipes for GT Rubber Saplings and Leaves to Latex.
[FIXED] Color of my Meteoric Iron.
[CHANGED] Leaves Scan Range for Logs to be much smaller yet still perfectly fitting for all GT6 Trees. (for example Leaves don't check for Logs above them anymore)
[CHANGED] Tooltips now mention Funnel and Tap Compatibility if the Block doesn't have a GUI.
[CHANGED] Crates can now no longer be opened via Crafting Recipe, inworld Crowbar usage is still possible ofcourse.
[ADDED] Monkey Wrench Mode to the Electric Wrenches. Harvesting the Machines works in both Modes equally.
[ADDED] Bottles for the 8 remaining Binnie Juices that didn't have one before.
[ADDED] 25 Fruit Smoothies.


6.05.16:
[FIXED] A Crash when Binnies Mods aren't installed.
[ADDED] Seed Oil and Fish Oil Bottles (also usable as cooking oil).
[ADDED] Missing Squeezer Recipes for some Fruits.
[ADDED] Rotten Grass can now be crafted into Forestry Mulch.


6.05.15:
[WARNING] I changed quite a few Fluid IDs for Juices and other Beverages, namely the ones that overlap with Binnies ones (in order to only have one Fluid instead of 2 different but identical Fluids). Every related Fluid that isn't stored inside a GT Tank, GT Filter or a GT Bottle might get lost due to the renaming. The Change is that some GT Fluids now use the same Name as Binnies Fluids to have better compatibility, and less Fluids to take into account.
[NOTE] Savegames will say that there is 1 Item missing, that missing Item is supposed to be removed so don't bug me about it.
[IMPROVED] The Entire internal System regarding Fluids in Recipes and cleaned up a ton of Code.
[COMPAT] Added Food Stats to all of Binnies drinkable Fluids, so that they work with the Thermos Can and the Cups.
[FIXED] Simulate Parameter not being respected in GT Electric Items.
[FIXED] Asphalt now no longer needs to be like Soulsand to boost Speed. It works differently now, and won't glitch out anymore either. Slabs and Stairs next to it will work now too.
[CHANGED] Taps, Baths, Mixing Bowls and similar will now have priority to fill Bottles into the GT Variant of the Bottle, instead of choosing a random one depending on Mod loading order.
[CHANGED] Honeydew now ferments into Short Mead. This will cause some Fermenter Recipes which incorporate Honeydew and Biomass to be removed! (this has been changed back in a newer Version, while keeping the Mead too using the Selector Tag)
[CHANGED] Pizzas are now more nutritious than Burgers (aka they give more Food Bar), but less than the Sandwiches.
[ADDED] Some Fruit related Squeezing Recipes that were entirely missing.
[ADDED] Some of the Building Parts for the Dynamo, namely the Coil Blocks (there will be either 18 of those required for the Dynamo).


6.05.14:
[FIXED] A few minor Issues.
[COMPAT] Binnies Juices are now compatible with most GT Stuff.
[ADDED] Recipes for Forestry Crates inside the Boxinator/Unboxinator
[ADDED] Tooltip to the Drain mentioning that it works infinitely in Oceans and Rivers.


6.05.13:
[FIXED] Mining Drill wasn't able to place Torches when empty.
[FIXED] Inserting Fluids using the Tiny Funnel into a Basic Machine didn't update the Machine, so it took ages for it to start.
[CHANGED] Boilers now have a Maximum calcification of 50%, making non-distilled Water Boilers doable but horribly inefficient. Might be a way to have larger Boilers running nonstop without needing to distill extra Water.
[CHANGED] The M-Tomato now has a Bathing Recipe as opposed to being a rare Crop Drop.
[ADDED] 36 Types of Ice Cream! (all Harvestcraft Ice Creams are amoung them, they all cool you down with Enviromine)
[ADDED] Ananas Crop (some may call it Pineapple), and added Several Ananas related things, including a Pizza variant, just remember that you DONT HAVE TO craft every single Food Item that exists.
[ADDED]
Thermoelectric Coolers.
They emit Cold Energy to the Front and Heat Energy to the Back.
It is possible to use the Heat Energy it generates while cooling Stuff to Power something else to max out the Efficiency.
The Efficiency is just 25% for Cooling and 25% for Heating, the remaining 50% are lost entirely, like in normal Electric Energy Converters (and unlike the Electromagnets). The Reason this thing is so inefficient is that it's the cheaper variant of a thing I will add later.
[ADDED]
Cryo Mixer. A Mixer that ensures a cold Temperature while mixing.
Makes Ice Cream for now. Maybe other things too later. ;)
[ADDED]
Freezer. Cools down liquids and solids.
Makes all sorts of Ice and Snow Blocks out of Water, except packed Ice, that needs Ice and a Compressor.


6.05.12:
[FIXED] Hazelnuts dropping from all GT6 Leaves. I also doubled the Hazelnut drop rate.
[ADDED] Empty Crate, which behaves like a Wooden Plank in all aspects. (like the Compressed Wood Plank, including Slabs and Bookshelves made of it)


6.05.11:
[NINJA-FIXED] a Basic Machine Bug with the new Tank System. Don't worry, nothing got voided/deleted or anything.
[FIXED] Previously placed Tanks will now automatically fix their Textures, that last fix apparently only applied to newly placed Tanks.
[FIXED] Grass Bale Blocks not having the proper Furnace Burn Value.
[FIXED] Accidentially nerfed the Plant Remains => Biomass Recipe, while nerfing it for Grass.
[FIXED] Creative Tabs for certain PrefixBlocks being spammed despite being empty.
[CHANGED] Large Boilers try to split the Steam evenly over the 5 output Holes (if not all Holes are connected, or if some of the Holes are backstuffed, all Steam will split over the remaining Holes).
[ADDED] Boxinator and Unboxinator back, they are both electric. (Pill Recipes moved from Canner to Boxinator, Crate-ing Recipes in Bugfix Release tomorrow)
[ADDED]
Hazel Tree.
It has Pink Planks, yeah might not be accurate (I didn't check which color the inside has), but it's not that important to have the proper color on that one, having Pink available is more important, lol.
The Leaves drop Hazelnuts and also Sticks (Hazels are more of a Shrub or large Bush, than a Tree). Also the Nutella Recipe got fixed, since Hazelnuts are available now.


6.05.10:
[FIXED] Metal Tanks using Pipe Textures by accident, don't worry your Multiblock Tanks will work fine, its just choosing the wrong Textures.
[FIXED] Boules not having a Texture, because I had to rename something.
[FIXED] If you killed Sheep while they were burning they dropped cooked Dog Meat instead of cooked Mutton.
[CHANGED] GT Mutton will drop less likely if Harvestcraft is installed, due to the Harvestcraft Mutton Drops, but since the Harvestcraft variant drops far less often, it will still drop the GT variant.
[CHANGED] GT Mutton will not drop at all if Et-Futurum is installed, due to it adding the Mutton of later MC Versions. All the mentioned Mutton Types are OreDicted properly now, this also fixes Scrapmeat not dropping in proper amounts for Sheep and Bighorn Sheep.
[ADDED] The Questing Ram from Twilight Forest will drop a lot of Mutton when killed, but maybe you should do the Quest first before doing that, you don't have to but it would be a waste.


6.05.09:
[COMPAT] Some more Advanced Rocketry things. (Its Rolling Mill and its Cutting Machine aren't needed anymore, as I added Recipes for my Machines)
[COMPAT] Some GalaxySpace things.
[FIXED] Fire Aspect now always cooks the Meat of killed Mobs, but only through GT Tools.
[FIXED] Silicon Tiny Gem Plate Recipe overlap with a Recipe I added for Advanced Rocketry.
[ASDFED] A reference to the 10th install of a short movie, that was stuck in my mind since a while. XD (I added Mutton from Sheep and TF Bighorn Sheep basically)
[ADDED] Glowtus, a glowing variant of the Lily Pad (will emit Light). It generates in Jungles and close to Jungles (so Rivers next to Jungles for example). Is Part of Dungeon Loot too.
[ADDED]
Large Boilers.
They mostly behave like the normal Boilers.
They need a 3x3x3 Hollow of Dense Metal Walls with a 3x3 of Heat Acceptors below.
Right now its kinda pointless to make anything beyond the Stainless Boiler, because there is no Burning Boxes strong enough for the other Boilers (guess what gets added soon), and you already need 8 Dense Burning Boxes for the Stainless Boiler.
If one Block of the Boiler is broken while the Boiler is under Pressure, the whole thing will blow up!
--- Stuff from one major Version later ---
Large Boilers try to split the Steam evenly over the 5 output Holes (if not all Holes are connected, or if some of the Holes are backstuffed, all Steam will split over the remaining Holes).

6.05.08:
[FIXED] Thermos and Cup not working properly with AppleCore.
[FIXED] Material ID Conflict making Trinitanium unobtainable.
[CHANGED] Old Measuring Pots will automatically turn into the new ones, once they are empty, no crafting needed to change them anymore.
[CHANGED] Bathing Pots are now able to actually fill/empty the new Measuring Pots and Thermos Cans, if you click on their border or their non-top sides.


6.05.07:
[COMPAT] Some GalactiCraft and Advanced Rocketry things. (The Galacticraft Compressor isn't needed anymore due to me adding Recipes to my own Compressor)
[FIXED] new Measuring Pots not working properly.
[CHANGED] Measuring Pots and Thermos Cans can now be directly rightclicked to fill or empty Fluid Containing Items from them.
[CHANGED] And they now work as adjacent Tank for Adv Crafting Tables and similar, just like Barrels, Drums, Plastic Cans, Bathing Pots and Mixing Bowls do already.
[ADDED] A Porcelain Cup. It is a Dungeon Loot for now. It can hold 1 Drink and isn't stackable, but it can be placed in world aswell, like the Thermos Can.
[ADDED] GregTech Saplings to Dungeon Loot. This makes sure that people with custom Worldgen can still get the Trees. (Note: I also spawn the currently unused Saplings in them aswell, they might be worth keeping if you find them)
[ADDED]
4 Types of Grass Bales (similar to Hay Bales). Normal, Dry, Moldy and Rotten.
The Normal and Moldy Grass Bales will change depending on environment, while the Dry and Rotten ones will stay like that forever.
Sunny and dry Areas will dry normal Grass, wet Areas will make it moldy and later rotten.


6.05.06:
[NINJA-FIXED] Old Measuring Pot being considered a Container Item.
[FIXED] The Pellet => Compressed Planks Compressor Recipe being missing.
[ADDED] Thermos Can can be filled with a Funnel ontop of it (sides won't work in that case, for logical reasons).
[ADDED] A 3D placeable/paintable variant of the Measuring Pot with visible content (replaces the old one, but won't auto-convert, so you need to craft it into the new one). It can be used for everything the old one can be used, such as watering Crops or picking up Fluid Blocks.


6.05.05:
[FIXED] Eating/Drinking Speed of Items.
[FIXED] Willows growing all the way to the ground, ruining eventual Sapling placement.
[FIXED] Changed Pumps to use Linked Lists instead of Array Lists, might help in case of large Bodies of Water. Also improved removal of vanilla Fluid Blocks.
[CHANGED] Some Blocks can be broken by Hand now. Namely the Thermos, the tiny Funnels and Taps. (you need to break and replace previously placed Taps and Funnels for it to take effect on those)
[CHANGED] The Recipe for Compressed Wood Plates now uses Pellets (see below) instead of Wood Pulp.
[ADDED] Wood Pellets made with Wood Pulp and Glue in a Mixing Bowl. Also Glue can now directly be burned in a Liquid Burning Box, but for less than you would get for making Pellets with it.
[ADDED]
Blue Mahoe Tree. (Blue Planks)
The Leaves will drop Sticks in addition to Saplings when broken, similar to Willows.
[ADDED]
Thermos Can.
You can store up to 16 Drinks in it (4000L), and it is not stackable.
It can be placed in World using sneaking, and rightclicked to drink from it, kinda like the vanilla MC Cake.
But you do not have to place it in order to drink from it, it works like normal Bottles if you don't sneak.
Since it can be placed, you can also paint it if you want.
Rightclicking on a Tap can fill it like any other Bucket or Bottle, just like the Canning Machine.


6.05.04:
[FIXED] Mekanism Salt Block Crafting with 4 Dusts instead of the normal 9.
[ADDED] GT Trees to the Forestry Multifarm API.


6.05.03:
[COMPAT] Some PneumaticCraft and Magneticraft things.
[CHANGED] GT molten Plastic is now the same as PneumaticCrafts molten Plastic.
[ADDED] Peanut Crop, Peanuts and Peanut Butter.
[ADDED] Fireproof variants of GT Logs, Planks and Slabs if you got Refractory Wax.
[ADDED]
Willow Tree (Green Planks)
It gives more Charcoal and Creosote than regular Trees in the Coke Oven. The same amount as the Greatwood Logs of Thaumcraft btw.
The Leaves will drop Sticks in addition to Saplings when broken.


6.05.02:
[NINJA-FIXED] Rightclicking Tree Holes with empty Hand causing crashes.
[FIXED] Some Wood Related things.
[ADDED] Slabs for the Planks, just like the Slabs for GT Stones, Asphalt and C-Foam.
[ADDED] Magnifying Glass can check the Fluid in Burning Boxes.


6.05.01:
[COMPAT] More Binnies related Recipes.
[FIXED] Bottom Textures of Blocks being somehow Z-Mirrored
[ADDED] Fluid-O-Meters and Bucket-O-Meters can now scan for Fluid Blocks and not just Tanks.
[ADDED] Sap/Resin Bag for collecting Resin and Sap from Trees. Won't work on IC2 Rubber Trees btw, that would be way too OP.
[ADDED]
My own Set of Trees and Planks.
The Trees have an advanced form of Leaf Decay, which is only slightly slower than the Faster Leaf Decay Mod, but MUCH faster than vanilla. Up to five Seconds for all of the Leaves to drop, to be precise.
Every Tree got corresponding Planks, including the Rubber Trees.
The Planks and the Logs are Spawnproof, so you can build in dark places with them.
I won't tell where most of the Trees are. But I know a Mod adds a Maple Forest Biome, well you can guess what is in the List of possible Biomes for my Maple Trees.
Trees added so far: Rubber (Yellow Planks), Maple (Red Planks)
[ADDED]
Compressed Wooden Planks made of 2 Wood Pulp in a Compressor.
[ADDED]
Rubber Tree, similar to the IC2 Rubber Tree, but I fixed some things with it.
It has only one Resin Hole and that Resin Hole is always at a point where you don't have to shear off Leaves to get to it.
The Resin Hole itself requires NATURAL Rubber Leaves in the shape of a natural Rubber Tree above it in order to produce Resin. At least 60 of 86 possible Leaf Spots have to be used. You cannot just shear the Leaves and place a bunch of them around it like in IHL, those would not be natural.
Every 30 seconds there is an up to 10% chance of Resin spawning in an empty Resin Hole. The chance goes lower the more Leaves get destroyed.
It will prefer to drop IC2 Resin if available.
The Resin Holes can easily be harvested by simply rightclicking them. No Treetaps needed.
[ADDED]
Maple Tree.
It can be tapped for Maple Sap using the Hand Drill (yep, needs an electric Drill for now :P). Only the top-most tapped Hole will work properly, so you cannot multi-tap it.
The tapped Hole itself requires NATURAL Maple Leaves in the shape of a natural Maple Tree above it in order to produce Sap. At least 250 of 306 possible Leaf Spots have to be used. You cannot just shear the Leaves and place a bunch of them around it like in IHL, those would not be natural.
Every 30 seconds there is an up to 10% chance of Sap spawning in an empty Sap Hole. The chance goes lower the more Leaves get destroyed.
The Sap can be used to make Sugar in the Dryer or Syrup in the Distillery.


6.05.00:
[COMPAT] Binnies Mods Recipes for the GT Centrifuge, addition of Item Data and similar things.
[FIXED] Balance on creating Distilled Water from Crops and Seawater. Regular Vanilla Water => Distilled, is left untouched.
[REMOVED] Mortar Hand Tool variant.
[ADDED] Made Chances Tooltip in NEI Recipes "Blink" Cyan and White, so that they are more easily visible.
[ADDED] Redstone Torches can now be used on GT Redstone Wires as Cover to invert the outgoing Redstone Signal.


//=== Version Number Jump in order to test the Update notifications, also I just noticed that March 13th is the anniversary of me joining Patreon, what is in this time frame. So I might do the Version Number Jump then. ===//


6.04.15:
[FIXED] Pump missing Animated Front Texture.
[FIXED] Empty Boilers not exploding when heated up too much.
[FIXED] Item-O-Meter and Stack-O-Meter not checking all Slots, when the Side they are attached to doesn't display any Slots, and Sensors in general not updating when the Value they selected is 0.
[ADDED] GregTech way of creating all 13 Forestry Electron Tubes and the IC2 Hydration Cell.
[ADDED] Measuring Pot can now water Crops. Works with distilled Water too.
[ADDED] Hammering of chipped Gems into Dust.
[ADDED] Quicksilver Thermometer to measure Temperature in a more mobile fashion. (it is also part of the Thermometer Sensor Block Recipe now)
[ADDED] Redstone Emitter Cover, which emits a constant Redstone Signal. The Cover can also be placed on coverable non-ticking Blocks.


6.04.14:
[IMPROVED] Website a lot and also added more Links towards it on other places, since apparently some Modpack Users don't know where their GregTech Jar File comes from. So yeah, that is also an important thing to do. Now there is a List of recommended Mods on the Downloads Page, the Paypal Buttons aren't german anymore and they accept both, $ and € (was a bit of a hassle to actually find the proper options).
[CHANGED] Hiding a few Materials from NEI, depending on if they are used or not, the Materials will ofcourse still stay available, because I will never remove them entirely!
[ADDED] Mode for Hoppers to insert exact amounts instead of divisible amounts, using Monkey Wrench.
[ADDED] Config to turn off Ocean Water spreading to Air Blocks within Ocean alike Biomes.
[ADDED] Update Notification for Major Updates, once they are ready and tested (including a link to both, the Website and the Config File to turn the thing off). The regular weekly updates don't count as major, so it won't spam you every week. Also older Versions than this one won't display an update notification.
[ADDED] Infinite Lava Springs and Infinite Water Springs (the way I do it for Oil already) to the Worldgen in form of additional Water and Lava Pockets.
[ADDED]
Pump for getting rid of large Bodies of Fluid.
It is powered by Rotation Units (RU).
The Range is 64 Blocks in every horizontal direction, and "infinite" in the vertical direction.
You need to place it adjacent to the Lake in order to pump from it, it will not use Mining Pipes or anything like the GT4 variant.
Fluid Block Input on Front, Energy Input on Back and the Sides are Fluid Output.


6.04.13:
[FIXED] Burning Boxes working under Water.
[FIXED] Covers being placeable ontop of Advanced Crafting Tables.
[FIXED] Oil flowing way too slow in most cases, because adjacent Oil Blocks didn't get updated properly.
[FIXED] Mortar requiring Steel instead of Iron.
[FIXED] Pressure Valve not working with Liquids if its output is connected to a Tank.
[FIXED] EnderIO Conduits draining infinite Items from Mass Storages.
[REMOVED] Hand Crafting Recipes for all Circuit Wires except the Copper ones.
[ADDED] The Loom can now bundle Wires.
[ADDED] Electric Hand Mixer, so you don't consume your Food Bar when using the Mixing Bowl. Yes it is as semi-useless as it sounds, but its cool, mkay!
[ADDED]
Laminator, which covers Objects in a Material.
It's Heat Powered (HU)
Coats Wires with Rubber, Pistons with Resin/Slimeballs, Leather with Wax (Harvestcraft), Refractory Wax around Forestry Woods to Fireproof them (is even slightly cheaper due to not needing Glass).


6.04.12:
[FIXED] Some Bugs that I found in the new Food Stat System, like Nausea not working properly due to having a too low duration.


6.04.11:
[FIXED] Zirconium not having all the Gem Types available.
[ADDED] GregTech Machines/Wires/Pipes/etc. can now also be painted by bathing them in Dye (4 times less efficient than Spray or the IC2 Painter), and unpainted by bathing them in Chlorine.
[ADDED] 4 Apple Crops in the colors Green, Yellow, Red (vanilla apple) and Dark Red.
[ADDED] Mortar as a Block. Has now infinite Durability but has downsides regarding Recipe Output being low in some cases, and it requires a lot more Food to use it than the other Tool Blocks. It works like the Juicer, but without Fluid Output.
[ADDED] Hidden Stats to Players, like how much Alcohol/Caffeine/Salt/Sugar they consumed recently, and giving them positive and/or negative Potion effects based on those Stats. The Cure-All Pill will reset all those Stats. It works on Vanilla, GT and some random other Foods so far.


6.04.10:
[FIXED] An Issue that a lot of Recipes didn't generate anymore.


6.04.09:
[FIXED] A lot of tiny Issues, especially with the Seawater. (including BC Pumps hanging up)
[FIXED] The Mystcraft Dummy Dimension generated GT Ores, which it couldn't use anyways, meaning Mystcraft "baking a Cake" caused a lot of unnecessary Lag. I disabled most, but not all GT Worldgen from that check.
[CHANGED] Byproducts of Tin and Cassiterite Ore. Both have the same Byproducts now.
[ADDED] Wooden Buckets for the IHL Rubber Sap and Spruce Resin Fluids. Also added Creosote alike Torch Recipes for them.
[ADDED] Sifting Cassiterite or Tin Ore has the chance of giving Zirconium Gems.
[ADDED] Some more Chemistry regarding Fluorine and Stuff that contains it.
[ADDED] Conveyor Modules Cover Functionality back.


6.04.08:
[NOTE] Now that I added infinite Seawater, I would recommend you to look into the CodeChickenCore.cfg and set "finiteWater" to true, so that vanilla Water isn't infinite anymore and you need to distill easily accessible Ocean Water if you want infinite H2O. ;)
[FIXED] Scoop not catching Butterflies like intended. Needs testing if it still works though. The Bug was that I forgot to actually add the catching Behavior itself to the Scoop.
[CHANGED] Silicon Dioxide => Silicon now requires Carbon in the Crucible instead of the Electrolyzer.
[CHANGED] Hydrochloric Acid is needed to process Borax now. Electrolyzing it directly is no longer possible.
[CHANGED] The Asphalt Block Recipe to require molten Asphalt and Stone in a Mixer, similar to the Recipe of C-Foam Fluid, including the pre-dyed variants.
[CHANGED] Glass Bottle Behavior regarding Ocean Water, and also made the Glass Bottles consume the regular Water Block when rightclicking with them (you get up to 3 Bottles at once from that).
[ADDED] The Drain now no longer deletes the Water Blocks when pointing into a River Biome.
[ADDED]
Ocean Water Blocks, I call them Seawater sometimes.
Will spread similar to MC Alpha Water, but with more restrictions such as needing to be in Ocean or Beach Biomes or having to be in the vanilla infinite Water constellation and especially no way to place them down manually. I hope I also disabled them in Floodgates and similar, but I didn't test that for non-BC-Floodgates.
They will also contaminate normal vanilla Water Blocks. Previously generated Oceans might take a while to be contaminated, once they get in contact with the newly generated Ocean Water.
River Biome Water is mostly immune to contamination, since it is a commonly known fact that Rivers flow into Oceans and not out of Oceans. But I needed to add a Mechanic that kills "Mid-Ocean-River-Biomes", so the Oceanwater is able to go a few Meters into the Rivers.
They copy the Texture of regular vanilla Water (they just make it a bit darker), meaning they are Resource Pack compatible.
Ocean Water can be dried or electrolyzed to get similar output as Saltwater, but with a far lower Salt content.
The Drain works perfectly fine with Oceanwater, no matter how large the Pipe you connect to it is. There is a special case that automatically considers Seawater Blocks as infinite, and therefore the Block won't even be deleted when drained by it, and therefore saving on Graphics and Network Updates.
Default Ocean Height is 62, it can be configured when needed. I needed that height Limit to prevent spills like that one shown on the Screenshots, interesstingly it doesn't lag the Server to death if it was to happen.
The Ocean Water can NOT be placed by any means, even if you have a Bucket of it, I also tested the Buildcraft Floodgate to make sure it is not possible!


6.04.07:
[FIXED] Inability to take Fluids out of a Crucible if the Room Temperature is too low, by adding a tolerance for low temperature Liquids. (anything < 320 Kelvin)
[FIXED] Sodium Persulfate not being available though the Electrolyzer.
[ADDED] Ironwood Fluid&Item Barrels, which are Fireproof. The Fluid ones also have more Liquid Storage.


6.04.06:
[WARNING] Do not attempt to put a GT Barrel or Drum into a Railcraft Tank to fill/empty it. If you do, you need to go into the Forge Config and set the Delete Erroring TileEntities to true before restarting the Game/Server.
[IMPROVED] Code improvements regarding automated Fluid creation, so that I don't need to bother creating Textures for each Fluid, and so that molten Metals now have a proper "Lava-alike" Texture. Yeah that took up all Wednesday...
[FIXED] Rightclicking Railcraft Tanks with a GT Stuff causing a Crash, because Railcraft doesn't release the Bugfix Update they already coded in May 2016. Seriously, you already did it, WHY DON'T YOU RELEASE IT!?! You are holding Railcraft hostage right now.
[FIXED] Grass alike Stuff and certain Shrubs of Biomes O'Plenty can now be cut using a Knife/Sword/Scythe, and Ender Amethyst is now Oredicted properly.
[FIXED] Immersive Engineering Hammer was still able to make Plates and turn Ores into Dusts. The Hammer itself is still available, since I know its needed for its Multiblocks, I only removed all the Shapeless Recipes using the Hammer. ^^
[CHANGED] You can no longer rightclick Boilers with a Bucket to fill them with Water. Instead, you will need to use the Fluid Funnel, that has been added a few Versions prior. The Funnel already worked on the Boiler in Versions before this one, so you can set it up already.
[CHANGED] Some Recipes that require Blocks of Ingots, now require Solid Blocks, since the Basin is a thing now. This also applies to some Railcraft and Applied Energistics Stuff.
[CHANGED] The clear GT Glass now requires some Chemistry to be produced.
[ADDED] Chemistry related Stuff for a bit more IHL Compat. (this triggered some improvements mentioned above)
[ADDED] The Crucible can now be emptied and filled using Buckets and other Fluid Containers, such as the Measuring Pot for example.


6.04.05:
[CHANGED] Crucible Faucets can now work on a vertical distance, even if there is multiple Drains inbetween them and the target, and they will also fill Crucibles below them, as if they were Molds.
[CHANGED] Vanadium is now slightly harder to obtain, because Ferrovanadium is now giving V2O5 instead of V. The V2O5 can still be molten into Vanadium, but you lose half of it if you do it that way.


6.04.04:
[FIXED] Missing Recipe for Rice Vinegar
[FIXED] Bauxite Ore and Yellow Sapphire now give Rutile instead of Titanium, when electrolyzed.
[CHANGED] Electric Tiers: Aluminium is MV now, while Galvanized Steel is LV. (see Crucible Faucets below for another relevant Change) NOTE: Your existing Machines will stay the same Tier they were before, nothing breaks.
[CHANGED] Selector Circuit Concept entirely, including initial Crafting Recipe. Also has a new set of Textures now.
[CHANGED] Garnierite (Nickel) and Pyrolusite (Manganese) now give more output, due to being Oxide Ores.
[ADDED] Magnifying Glass now works on GT Engines.
[ADDED] Carrot Juices. May or may not improve eyesight, but that is just a Myth.
[ADDED] Crops for Blueberries, Gooseberries, Candleberries, Cranberries, Black White and Red Currants, Blackberries, Raspberries and Strawberries.
[ADDED] Basins. They work like Molds, but for Solid Blocks. Requires a Crucible Faucet ontop to be filled.
[ADDED]
Crucible Faucets
Works when placed next to a Crucible.
They will fill Molds and Bathing Pots below them (also Mixing Bowls if the melting Point is low enough and the Fluid is simple enough)
They are Redstone sensitive too.
NOTE: Not everything will go into a Bathing Pot, sure molten Zinc for early galvanization will, but other Stuff may be too hot for the Bathing Pot.
--- Stuff from one Version later ---
Crucible Faucets can work on a vertical distance, even if there is multiple Drains inbetween them and the target, and they will also fill Crucibles below them, as if they were Molds.


6.04.03:
[FIXED] Thaumcraft Stuff not wanting to be in Bookshelves if Ars Magica isn't loaded. Guess when a copypasta went wrong...
[CHANGED] Ilmenite and Rutile are now harder to process, and won't allow the Electrolyzer anymore. Molds accept Fluids if you didn't know that already.


6.04.02:
[FIXED] Rutile Ore Processing being done in a Crucible instead of a Mixer. (forgot that Stuff can vaporize, yes this means you need a Smelter and a Mixer now)
[FIXED] The Mixing Bowl no longer accepts non-simple Fluids.
[ADDED] Apple Vinegar (fermenting Cider further), Rice Vinegar (fermenting Sake further) and Cane Vinegar (fermenting Rum further, Pirate Brew now needs distillation). Yep, just variations of Vinegar. Should be usable in all Vinegar requiring Recipes.


6.04.01:
[COMPAT] With Ars Magica Items, regarding Unification and Bookshelf Stuff.
[COMPAT] Some more Mariculture Material related things. Added Polished Wood into my Material Database. Also its Custard can be made in a Mixing Bowl now.
[FIXED] Tap/Funnel not working properly on Basic Machines.
[FIXED] The Mod Redstonic causing GT to be crashing on startup.
[FIXED] Zeolite composition and Ore Byproducts.
[FIXED] Cake not being craftable the GT way.
[REMOVED] The option to unificate towards GT Items inside the Unification Config, because it confused too many people.
[CHANGED] Titanium Ore Processing a little (not as much as planned, but still requires at least Magnesium in a Crucible to work). And removed all the direct Titanium Ores/Byproducts and replaced them with Rutile.
[ADDED] 10 new Crops, not all Crops, but it is still 6 Food Crops and 4 Magic Flowers.
[ADDED]
Several Juice related things, including a drinkable Bottle for Forestrys generic Juice.
I also ordered my registrations a bit better so it's easier to add the remaining Juices in the future (that took a lot of time...).
Blueberry Juice counts as Blue Dye btw.
Tomato Juice and Ketchup are two different things now. And yes, I added both of them too, since there is a Tomato Crop now, it was kinda mandatory.


6.04.00:
[API] I added a Material Array Parameter to the PrefixBlocks and PrefixItems, so it is possible to generate less many Items.
[COMPAT] With a Mod which adds Crops, that I don't wanna mention here, due to its content. People who have that Mod will notice it probably.
[FIXED] Lumium Wires counting as "painted" despite not being painted, due to their oscillating Color Value.
[CHANGED] Replaced Forestry Mulch and IC2 Biochaff in Squeezer Outputs with own Items. They will generify into Forestry Mulch inside the Generifier.
[CHANGED] Coke Oven to be able to process up to 16 Coal/Wood at once in parallel, but I doubled the Duration of all Recipes. This means you get everything 8 times faster from it, if you insert large Batches. Yep, I did that to combat Coke Oven Spam, because one shouldn't make way too many of them. Because of that I needed to nerf Silverwood Logs to only output 2 Charcoal instead of 4, but I buffed its Creosote Output instead.
[ADDED] Tooltips to certain GT Blocks stating they are flammable if they are flammable. Some of those have been missing before.
[ADDED] Adamantium Multiblock Tanks (forgot those last year, oops) and Plastic Fluid Pipes (Those originally went missing for unknown Reasons, I have no Idea why I forgot them at all).
[ADDED] Insulated Versions of the Red Alloy, Signalum and Lumium Wires. This is only to prevent Visual Updates whenever the state of the Wire changes. Yes Insulated Lumium Wires are completely useless, but if you have nothing to wire your stuff with and still want it to not blink all the time, then you can insulate a Lumium Wire too.


//=== Version Number Jump due to new Year, and because I wanted 6.03 to end on 42, and because of tiny changes to some API Parts. ===//


6.03.42:
[FIXED] Bug that caused certain reverse Recipes not to generate. This did affect Stone Crucibles for example, but also some Wood things.
[FIXED] Sensors, Buttons, Taps, Funnels and some other things obstructing adjacent GT Blocks regarding Rightclicks.
[CHANGED] Wooden Fluid Barrels and Wooden Multiblock Tanks no longer accept any Gasses and also certain Liquids. I manually selected the Liquids that are allowed. The Fluid Display Items will have a new Line in their Tooltip that lets you know which. Not all of them would go in there though, since their Temperature might burn it up, despite being simple Liquids.
[ADDED] Adamantium Drums with insane high Capacity of 4096000L. Yes, I know Adamantium isn't obtainable yet at all and won't be anytime soon.
[ADDED] "Functionality" to certain Acidous Fluids. This means that Acids need special care when using Pipes and Tanks. Tungsten (not the Carbide!), Stainless Steel, Thaumium, Voidmetal and Adamantium are the only Acid Proof Pipe/Tank Materials.
[ADDED] Cheaper Wooden Barrels (both, Item and Fluid) made with Lead and regular Planks. They have half the Capacity of the ones made with Steel and Sealed Wood.
[ADDED] Overflow Mode to the Mass Storages and Item Barrels (Wirecutter), which will cause them to accept 256 Items more than usually allowed, but everything going over their regular Capacity, will be emitted to the Bottom automatically. That way you don't need shitty Void Upgrades anymore, which destroy all your hard earned Resources, and you can decide yourself where the overflow will go towards, instead of forcing the deletion of Items, like every other Mod does. Seriously all Barrel and Drawer Mods I know, have a stupid Void Upgrade and no way to redirect overflowing Items like I have now... Yes, I sincerely HATE voiding Resources.
[ADDED] Tiny Funnels (available in Ceramic, Plastic, Stainless Steel, Tungsten and Adamantium), which are used to insert Fluids into GT Barrels, Boilers, Tanks and Basic Machines by hand.
[ADDED] Taps, like Funnels they handle Fluid Container Rightclicks. But they fill the Fluid Container instead of emptying it. They also have special interactions with Bathing Pots and Mixing Bowls below them, so you can basically use those as a Kitchen Sink now. Also I should mention that Drums can still accept Covers, in case you wanna hide them behind the Tap.


6.03.41:
[FIXED] Cassiterite Melting Point being below Tin Melting Point.
[CHANGED] Considering Mekanism Osmium no longer as the same thing as actual Osmium. But you can still turn Elemental Osmium from GT into the Fake Osmium from Mekanism using the Generifier. This essentially nullifies all the Sources of Osmium for GT that are located in other Mods, since it's an entirely different Material now. Old Osmium Ingots and Stuff from GT will turn into the Elemental variant automatically. Also GT Tools made of Fake Osmium aren't that good anymore.
[ADDED] Fishmeal (shreddered Fish Meat) and related things such as Recipes and Food Items. Yes, it is like the Mariculture Stuff, note that due to balance reasons, I decided to make the Mariculture Fishmeal just 1/4th of a Dust (aka Small Pile of Dust).
[ADDED] Mortar Recipes for Meat and Fish related Stuff, so Mince Meat and Fishmeal are now earlygame and not Post-Shredder anymore.
[ADDED] Raw Meat and Fish Ingots can now be crafted in a Mixing Bowl, by adding Flour to the Mince Meat or Fishmeal.
[ADDED] Juicer, it does essentially what the Squeezer does, but less efficient and by Hand. It's made of Hardened Clay. Can also make Seed Oil for creating Sealed Wood.


6.03.40:
[CHANGED] Some Shredder Recipes for crushed/purified/refined Ores. This did change the Math slightly on the amount of output you get from smelting them aswell. You now get 1/36th more from smelting purified Ores, and a bit less for the crushed ones.
[ADDED] When Mass Storages and Item Barrels have Dusts or Dust Blocks, they will pile up the Dusts to the selected Size. Same goes for Ingots, Nuggets, Chunks and Ingot-Blocks. Note: If you think this could replace the Dust Funnel entirely, then nope, that won't work. But it will replace the need for a Dust Funnel above the Storage itself.


6.03.39:
[FIXED] A bug that caused improperly coded MC Launchers to Crash on startup.
[FIXED] Wet C-Foam not Rendering on unconnected Pipes and Wires.
[FIXED] Thaumcraft Tallow Candle Bath Recipe, where I wrote "2" instead of "2 Units".
[CHANGED] Item and Fluid Pipes are now more in sync with the Serverwide Time.
[ADDED] Fluid Pipes that can handle 4 and 9 Fluids at once, but they only have the Bandwidth of Medium and Small Pipes for each Fluid respectively. The Pressure Valve does NOT work on those! Would be stupid to use it on them anyways though.


6.03.38:
[FIXED] Stone and Fluid worldgen taking way more time than they should. Blame Vanilla for the Lag it had before this fix.
[FIXED] Multiple Crafting Sounds playing when using the Advanced Crafting Table. Not fixed for any other Crafting Table though.
[FIXED] Double Crafting Sounds in Singleplayer. Apparently they were able to be played Serverside when you are in Singleplayer.
[ADDED] Glue Recipe by mixing Slimeballs and Water.
[ADDED] A Bottle for the MFR Pink Slime Fluid. Yes it's actually edible. It can be used as Glue aswell.
[ADDED] The Advanced Crafting Table can now draw Fluids Directly from adjacent Barrels, Drums, Plastic Cans, Mixing Bowls and Bathing Pots in order to refill Fluid Containers.


6.03.37:
[FIXED] Mariculture Recipes only loading when Extrabiomes is loaded. I accidentially checked for the wrong Mod ID, lol.
[FIXED] Wooden Item Recycling and Burning in Furnace.
[CHANGED] The Sifter Ore Processing for Dust Ores slightly, still same Products.
[ADDED] Easier Recipes for Glue using Sticky Resin or Propolis, and mixing it with Water. Also Glue Bottles are Dungeon Loot aswell now.
[ADDED] Wooden Bathing Pot (ofcourse including the Table Version of it). Has a few more limitations than the stainless one though.


6.03.36:
[FIXED] The Ore Boost Configuration did not work on Small GT Ores. Yes, there is already a Config to multiply Ore Block Drops per Material, if you really want to get more Ores without changing the Worldgen.
[FIXED] Controller Covers turning OFF the Gas Burning Box even though they should give a non-functional ON Signal instead.
[IMPROVED] Fluid Display Item by saving the Fluid Name as a String inside its NBT. Please check your Fluid Filters after the Update in case anything went wrong.


6.03.35:
[COMPAT] Did a bunch of Mariculture related Recipes for my Machines.
[FIXED] The Item Rendering on Mass Storages (and the upcoming Item Barrels), by no longer using vanilla default Rendering and instead using my own rewritten vanilla Item Rendering.
[FIXED] GT Wrenches being able to rotate Piston Heads, Redstone Diodes and Rails (The latter two are for Screwdriver and Crowbar, but the first one was definitely a Bug).
[CHANGED] The Signalum Crucible Recipe, it is still the same Materials and Amounts, but I re-did the Math and found out that there is a way to turn Red Alloy into Signalum with a few little additions. So now you can turn Red Alloy into Signalum easily. And the old Recipe for Signalum still works, it just forms Red Alloy mid-process.
[CHANGED] Sensors and Mass Storages now update their Display only once per Second as opposed to once per Tick, UNLESS the value varies too much from the previous Value, in which case it's still once every time the difference between old and new passed a certain threshold (> 64 for mass storage, >= 50 for sensors).
[ADDED] The Measuring Pot can now pick up Fluid Blocks in World. This might be useful for the tiny Oil puddles.
[ADDED] The Gas Burning Box and with that I mean ONLY THE GAS ONE, can now be turned OFF (but not ON, for that you still need an Igniter) using Controller Covers. This makes Gas burners easier to use than the other two Methods.
[ADDED] The Activity Detectors can now detect if a Burning Box of any Type is currently burning.
[ADDED] Item Barrel, made of Sealed Wood and a bit of Steel, for 10000 Items instead of the usual 1000000 from the Mass Storage. It's a cheap alternative.
[ADDED]
Wood Barrels, Plastic Cans and Metal Drums for storing Fluids.
The Wooden Barrel and the Plastic Can won't accept Covers on it.
They keep their Fluid when mined, but are not stackable in Inventory.
Capacities are 16000L for the Wood one, 32000L for the Plastic one, 64000L for the Stainless Steel one and 256000L for the Tungstensteel and Tungsten ones.
They will auto-emit Fluids to either the top or the bottom direction depending on Fluid density. But it has to be enabled via Monkey Wrench or regular Wrench (yes, both Types work).
Canning Machines can empty and fill the Barrels, Cans and Drums, so they don't have to be placed to do that manually.
The Drums and Barrels can be sealed, using a Soft Hammer, to ferment their contained Fluids over time (instead of by using heat).
Note, that this just works for the Fluid-Only Recipes. This is mainly meant for Alcohol Stuffs and Potions. Duration depends on amount inside the Barrel and can take ages. It is measurable with a Magnifying Glass or a Progress Sensor.
It does Multistep Fermenting all by itself, so it can do Grape Juice -> Wine -> Vinegar for example, if you leave it sealed for too long.
The Plastic Can is the only one that can easily be auto-crafted via Extruder. Remember that the stacksize Limitation does not apply to Item Barrels and Mass Storages.
Because people didn't realise it yet: YOU CAN PAINT THE BARRELS AND DRUMS LIKE ALMOST EVERY OTHER GT BLOCK!!!


6.03.34:
[FIXED] Gave RotaryCrafts Sintered Tungsten a separate Material, and added ways to turn it into the inferior and simpler regular Tungsten.
[FIXED] The Sense/Scythe now actually works on Tall Grass.
[FIXED] Liquid/Gas Burning Boxes not saving their Tank Data properly.
[FIXED] Wooden Creosote Buckets not working with Immersive Engineering installed despite Railcraft delivering a Fluid Block.
[REMOVED] Decided to remove the easier Distillery Fuel/Plastic Recipes again.
[REMOVED] The existence of certain Dusts, like Wrought Iron Dust, Annealed Copper Dust and the Magnetic Dusts.
[CHANGED] Matches and Matchboxes now have a 90% Chance, like the BICs.
[CHANGED] Scraps now stack up to 18 instead of 16. Makes it a tiny bit more practical, and easier to take exact amounts.
[ADDED] The Bun/Bread/Baguette/Cakebottom Shapes for the Forming Press. Yes they are called Molds, that way people can say I added Bread Mold to the Game. PUNS!
[ADDED] The Lathe can now turn flawed and chipped Gems into Bolts directly.
[ADDED] Simple Firestarter made of 2 Sticks and dry Grass. Single Use and only a 50% chance to actually work, oh and it is not stackable.
[ADDED]
Some new Worldgen. Nothing crucial. It's just Oil.
It is a finite Fluid, in the sense of it actually flows down by itself and can be filled partially.
Its the easiest to use Universal Cells (only works on full Blocks) or the Drain Cover (also works on partial Blocks) to collect the Fluid.
Generates also Oil Springs if it's close enough to Bedrock. Those are Random and have an average speed of 1 Bucket per MC Day (20 mins). That can be turned off via Config.
[ADDED]
Grass and Dry Grass Items. (I didn't think I would write so much about simple Grass...)
Dropped by Swords/Knifes/Senses/Scythes, when harvesting vanilla Grass and Ferns.
Dead Bushes will drop Sticks with those Tools.
Can be used to feed Sheep, Cows and Horses. But not Pigs.
Furnace Smelting (or using the Dryer) will turn Grass into dry Grass.
The dry version is craftable with a Stick to create ONE Torch.


6.03.33:
[FIXED] Outputs of Machines with Randomized Outputs being placed inside the wrong Slots, causing the Machine to stop instantly once that Slot is filled. I never noticed it because I always had a Chest on the Output.
[FIXED] Canvas Covers not working with Blocks that have an ID >= 2048. I hate the default processing order of Bitwise Operators...
[FIXED] A large Issue caused by the rewrite of a Utility Function. It caused the Distillation Tower to output all the Fluids to the bottom.
[FIXED] Sealed Wood not being craftable without the generic Wooden Plates, despite Wooden Planks definitely being intended.
[CHANGED] Multiblock Tank Main Valves now have distinct Front Textures, to make it easier to determine what the Front is.
[ADDED] Drains now work horizontally even if there are GT or vanilla Slabs/Stairs in front of them, if they are placed logically. (some Modded Stairs and Slabs MIGHT work too).
[ADDED] The Saws can now recycle some wooden Vanilla things into Wood Plates via Crafting.


6.03.32:
[FIXED] Issue with Fullblock Pipes Cover Rendering.
[FIXED] Germanium and Cerium now have an assigned Color and are therefore visible in NEI.
[FIXED] Electric Tools not using the proper Handle Material for their Hulls. No wonder I got complaints about people not being able to craft certain Tools...
[FIXED] Wooden Pipes being semi-fireproof (Same for the Sealed Wood and Ironwood Stuff), and also for other wooden things such as the cheaper GT Chests. That's what you get for reporting it. XD
[CHANGED] Distillation Solid Outputs are now Tiny Dusts instead of normal Dusts. That's a 9 times nerf. (It was just way too much output)
[ADDED] Some more Glue Support in form of a Glue Bottle that can be used in crafting Sticky Pistons and Leashes.
[ADDED]
Wooden Tank.
Unlike the Railcraft one, it's not made out of "Rocks that look like Wood, but still have to be mined with a Pickaxe", and it won't generate Water out of nothing, that's what the Drain Cover is for.
Shape is a hollow 3x3x3 of Wood Walls, with the Main Block centered on a Side, just like the GT Coke Oven. (those are partially made of any kind of Steel!)
It does NOT have a GUI, just so you know. Magnifying Glass might help if you want to see exact amounts.
Holds 432000L or 432 Buckets, so 16 Buckets per Block, similar to a BC Tank.
It is made of Sealed Wood and a bit of Steel to keep it in place like a large Barrel.
There are Temperature Limits to this Tank though. So don't burn it up with Lava or something.
It auto-emits Fluids towards the Main Block if the Gravity doesn't work against it.
Said Auto-Emit might be useful if you want to have a Line or a Tower of Tanks with the same Fluid.
Use Magnifying Glass to see what Fluid is inside. I don't want too much Graphics Issues when rendering Fluids.
You could stack those Tanks ontop of each other with the Main Valve Block Bottom Center, similar to BC Tanks.
Don't try to store any kind of Steam inside, it will be voided! (Steam isn't supposed to be stored, and I will proceed that way with all GT Tanks)
As it is made of Wood it will ofcourse catch Fire next to Stuff like Burning Boxes.
[ADDED]
Metal Tanks.
Shape is a hollow 3x3x3 of Stainless Steel, Tungstensteel or Tungsten Walls, with the Main Block centered on a Side, just like the GT Coke Oven. (yes, just 3x3x3. If/When I add larger ones they will be made of more robust/expensive Tank Walls)
Those Tanks have other Temperature Limitations and larger Fluid Capacities than the Wooden one, but are otherwise identical.
[ADDED]
Drain Cover.
In case of Pipes it also works when the Pipes are not wrench-connected to the Cover Side. (Because that would look ugly)
It can collect Rain Horizontally aswell, if there is a solid Floor in front of it.
The Drain can collect Fluid Source Blocks too, if not against Gravity. Horizontally always works.
If you use it on a Pipe then make sure the Pipe is large enough to hold an entire Block of that Fluid. It cannot suck partial Fluids, because that would be waste.
--- Stuff from one Version later ---
Drains now work horizontally even if there are GT or vanilla Slabs/Stairs in front of them, if they are placed logically. (some Modded Stairs and Slabs MIGHT work too).


6.03.31:
[FIXED] Extruder Recipes taking ages to load in NEI, when you open uses for an Extruder Shape.
[FIXED] non-ticking TileEntities not synching to other peoples Clients right after being placed, making them Ghost Blocks until they are unloaded and loaded again or until relogging. And this time I found a way to test it in Singleplayer using TE Autonomous Activators, so I can say it's actually fixed.
[CHANGED] Propane and Butane Order in the Distillation Tower Output to be proper.
[ADDED] 3 new Extruder Shapes. Small Gears, regular sized Rods and curved Plates. All the Extruder Shape Recipes (except Plate/Ingot/Curved-Plate) have changed to a two Stage System due to that.


6.03.30:
[NINJA-FIXED] Worldgen Freezing due to a Change I made in PrefixBlocks.
[NINJA-FIXED] A tiny Material Amount Issue for some Recipes.
[FIXED] Multiblocks are now all considered Weather-Proof. There was a case where the Controller Block could have been affected by Weather.
[FIXED] Some Extruder Recipes rounding up their Outputs.
[CHANGED] Made the Large Centrifuge use regular Casings instead of Quadruple Casings. Also the Heat Acceptor now uses Invar instead of Tungsten.
[ADDED] Configs to disable certain IC2 Machine Recipe Lists or GT additions to them.
[ADDED] Plastic Lighter, aka BIC. Also all 3 Lighter Types are now filled with Propane/Butane instead of BC Fuel.
[ADDED] Asphalt Blocks, which have the same Walking Speed Bonus as the old GT5 Concrete Blocks. Default Color for freshly crafted Asphalt Blocks is Gray. Yes, there are Asphalt Slabs too, for people who want to make Sidewalks with them or something.
[ADDED]
Distillation Tower.
Processes Oil into several kinds of Fuel, Plastic, Asphalt and Parrafin Wax.
It has a 3x3 Base of Heat Acceptors (those got a lot cheaper from the Version before, so don't pre-craft!), and ofcourse you don't need to place a heater at all 9 of them. Just one Heater or a Burning Box is enough. Imagine it as if the Acceptors conduct the Heat horizontally aswell, so that it evens out or something, I don't care how you imagine it! :P
and a 3x3x8 of Distillation Tower Hulls, NOT Hollow. Those are cheaper than you might expect, but made of Stainless Steel.
The Fluid Outputs will be depending on the Height of the Output Hole. All 8 output Holes are on the Backside (so the side opposite of the Controller).
The first 6 Holes (counting from top) are outputting dedicated Materials, such as Propane, Butane, Petrol, Kerosene, Diesel and BC Fuel (They are all about equal in amount).
The Bottom most Hole is for Solids, and the remaining Hole right above it is for all Heavy Fluids, in the current case, Lubricant.
Item/Fluid Input are on the Bottom Layer of the Tower. Bottom NOT meaning the Heat Acceptors. It just means the Bottom of the Distillation Tower itself.
It is crafted without any Circuits in its Recipe, as it's required to get this Tower in order to get Plastic for the Circuits (the temporary Furnace Recipe for smelting Silicon Dioxide to Circuit Boards is gone now).


6.03.29:
[FIXED] Exploit with Blaze Rods made via Crucible Molds.
[CHANGED] Coke Oven now automatically outputs Fluids to the 3x3 Blocks below the Bottom, so that no Pump Covers are needed to extract the Creosote.
[CHANGED] Made some Ore Processing Steps a bit cheaper regarding total Energy usage by shortening the Duration.
[ADDED] Lignite Coal Coke, made in the Coke Oven. It gives more Creosote Oil than regular Coal.
[ADDED] Creosote Oil to the things you can fill a Wooden Bucket with.
[ADDED] A lot of new ways to make Torches.
[ADDED] A currently useless Heat Acceptor Part for future Multiblocks.
[ADDED]
Large Centrifuge Multiblock. (3x3x2)
Accepts anything between 512 and 4096 RU.
It has a general 50% Efficiency penalty.
BUT it has the Overclocking penalty removed entirely.


6.03.28:
[NINJA-FIXED] Wires burning up after a set amount of Ticks, because the additional Burn prevention counted backwards, until the byte I used to count underflowed from negative to positive, causing the Burn Counter to be over the limit, resulting in ALL Wires being on Fire.
[NOTE] I can't exactly pinpoint what I did, but I think removing some vanilla function from my TileEntities (which was only used for comparator inventory scanning), made the FPS go up extremely on my Client for some reason, and the RAM usage doesn't escalate insanely fast anymore and now goes from 1GB to 2GB, instead of 2.5GB to 4GB.
[FIXED] Fluid Filter Mechanics getting thrown off on World Reload after updating or adding random Mods (including GT itself, when I add new Fluids). I accidentially saved Fluid IDs instead of Fluid Names.
[CHANGED] Smelting Wood to Charcoal in a Furnace is now disabled per default.
[ADDED] Coke Oven.
Recipes are identical to the Railcraft Coke Oven. It will be able to turn washed and refined Coal Ore (but not crushed) directly into Coal Coke though.
The Coke Oven requires ignition by Flint&Tinter, Lighter or the Igniter Block in order to start the process, and will only stop once it runs out of Logs/Coal.
Aside from the Main Block (which works almost exactly like a Basic Machine), the Fire Bricks are all non-ticking, so they won't contribute to Server Lag.
Fire Bricks are all paintable and coverable, even though MOST functional Covers are not working on them unless you put an Extender on the Fire Bricks and attach the functional Cover to that Extender, that will work as the Extenders do tick, unlike the Fire Bricks.
The Fire Bricks are made with 4 Clay Bricks mixed with 4 Clay Dust (used as mortar) and a Bucket of H2O (Wood Buckets are allowed too). And the Main Block is 1 Fire Bricks Block + 1 Steel Plate + 3 Steel Rods + 2 Steel Screws + 1 Hammer use + 1 Screwdriver use.
If a Part of the Machine is in a Chunk-Unloaded Area and it has been intact before, it will be given the benefit of the doubt and count as complete, but make sure that the automation around it is loaded properly.
It can process 64 Coal or 128 Logs before the Creosote Tank is full.
--- Stuff from one Version later ---
Automatically outputs Fluids to the 3x3 Blocks below the Bottom, so that no Pump Covers are needed to extract the Creosote.
Works with Lignite Coal aswell.


6.03.27:
[NOTE] This is mainly an Optimization Update. I don't even know if it is noticable. I always optimize things, when I'm not having any good Ideas, while still being motivated more than enough to do something.
[IMPROVED] Loading time a tiny bit. A very tiny bit. It should at least have reduced RAM usage a little (okay I calculated it roughly and it was just about 20-40MB of RAM worth of Recipes, but IT'S SOMETHING). I optimized a few more things of that kind. I don't know why, maybe I'm just imagining it, but the FPS are slightly more smooth than usual with this.
[ADDED] 1/72nd of a Pile of Dust. Yeah, that will exist now. Putting Bolts inside the Lathe to make Screws will output one of these for example. It was also the reason I chose 1/72.
[ADDED] Gas Burning Box. Like the Liquid one, but for burning Gasses.
[ADDED] Magnifying Glass works now on Basic Machines to display Modes.
[ADDED] The Slicer. It's kinda like the old one in GT5. I did add some more Shapes to it though.


6.03.26:
[NINJA-FIXED] Safes now actually don't destroy a part of their Content when blown up, unlike all the other Stuff.
[FIXED] Tooltip for amount of Crafting Uses on Electric Tools.
[CHANGED] The Replicator now prefers to Output Ingots, Plates and Gems instead of Dusts whenever possible (except for Redstone, Glowstone and Nikolite/Teslatite/Electrotine). Also prefers Crystalline Silicon/RedstoneAlloy Plates over Ingots.
[ADDED] Matches can now be Handcrafted, and Matchboxes now have 64 instead of 16 Matches inside.
[ADDED] Recipes for Glyceryl, in order to be able to actually produce Dynamite. The production of Glycerol, that is needed for Glyceryl, is done via Biomass in a Distillery, but with a Selector Circuit set to 1 instead of 0. That will replace the Ethanol Output.
[ADDED] The Replicator now has an NEI Recipe List. There are 253 different Materials that can be replicated as of now.
[ADDED] Remote Activator. It is essentially a Remote control with a Range of 128m, that can store 16 different Coordinates (select via Sneak rightclick) to simply activate them on Rightclick. Right now it only works with Dynamite and the Advanced Buttons.
[ADDED]
The possibility to Replicate new Materials via the GT UUM System.
And yes, I added the Tag to every Material manually, there are no unintentional Recipes.
There is Recipes for Wood, Plastic, Rubber, Water/Snow/Ice, Gunpowder, Saltpeter, Phosphates, Apatite, Salts, Several Chemicals, Alloys made of replicatable Materials, most Gems and some more.
I did NOT include things that are Ore alike or Rock Types, and I also did not include Lava nor Obsidian.
But I did have to include Redstone, Glowstone, Nikolite/Teslatite/Electrotine and Lapis.
Sometimes, due to rounding, Compound Materials might be slightly cheaper than their individual Elements. I will keep it like that.
[ADDED]
Dynamite Sticks.
Their Explosions have a 100% drop Rate.
3x3x3 Range around the Dynamite. Shifted by 1 Meter into the ground if placed with the Hand Drill.
Requires to be lit on Fire with Flint&Tinder/Lighter, or with Redstone.
The Purple one can Blast Obsidian, but is twice as expensive.
[ADDED]
Electric Hand Drill (of the non-mining kind).
It is there to be a counterpart of the Robot Arm Drill Tip for crafting.
It can place Dynamite Sticks inside natural Rocks and Ores.
Now used instead of Hammer to reinforce GT Bricks with Steel Rods. Does work both, via Crafting and in World.


6.03.25:
[FIXED] Non-Radioactive Naquadah wasn't Replicatable. Note: Naquadah Gens in the future will only run with Radioactive Naquadah, which can only be gotten from Naquadah Ore Processing.
[FIXED] Saturation values for solid Foods. The Drinks are intentionally left unchanged, since they aren't supposed to be used as Foods.
[INTERNAL] Assigned Food values to Fluids rather than to Bottles and let the Bottles detect the Food value depending on their Fluid (for somewhat useless fancy future stuff). The Only change you should notice for now is only an additional line of Tooltip on the Honey Bottle, nothing else.
[CHANGED] Instead of disabling IC2 Recipes there are now harder Versions of them.
[ADDED] The two Redstone Control Covers now have a possible Invert Mode.
[ADDED] Crops of Pams Harvestcraft are now harvestable with a GT Sense/Scythe.
[ADDED] Mining Tools can now place Torches and certain Candles on Rightclick.
[ADDED]
Pocket Multitool, it's kinda like a Swiss Army Knife.
Knife, Saw, Screwdriver, Wirecutter, Chisel, File and Scissors are included.
Tool Select via Sneak Rightclick on something that can't be used by the current Tool. (I don't want to add a Mode Button)


6.03.24:
[FIXED] Milk Bottles not always being drinkable.
[FIXED] Tooltip of Holy Water not mentioning the Weakness Potion requirement.
[CHANGED] Basic Machines are now immune against failing a Process for one second after they are loaded, in case the Energy Production or Energy Networks or anything else starts late.
[CHANGED] Most IC2 Machines are now disabled by default again.
[CHANGED] Sensors in Decimal Mode are now limited to 9999 instead of 65535, since people apparently loop through the Numbers for whatever reason. (switching from hexadecimal to decimal mode doesn't cause it to limit to 9999, but changing the numerical setting does)
Control Related Stuff
[ADDED] Progress Sensor Support for the Mass Storage to display Item Count properly.
[ADDED] Universal Extenders can now have Control, Progress, Detector and Selector Covers on them in order to set Modes and the ON/OFF State of the attached Machines. Same goes for relaying the Active, Running and Progress States of Machines. This makes Machines that happen to implement my Interfaces, but don't support my Cover System, capable of having such Covers through the use of an Extender.
[ADDED] Auto-Machine-Reboot-Timer Covers, which attempt to restart the Machine every 1, 5, 10, 20 or 30 minutes. The regular Auto-Switch is faster reacting than those for basic Machines though, but some may prefer this Variant over having an external Redstone Timer on a Auto-Redstone-Switch.
[ADDED] Energy Sensor Covers for Energy Storages, that emit Redstone depending on how full they are. Wire Cutters and Screwdrivers can set Modes on this Cover.
[ADDED] Progress Sensor Covers for Machines and similar, that emit Redstone depending on how their Percentages are. Wire Cutters and Screwdrivers can set Modes on this Cover.
[ADDED] Activity Detector Covers for Machines, that emit Redstone depending on what the Indicator Lights of the Status Display would show. Wire Cutters and Screwdrivers can set Modes on this Cover.
[ADDED] Success Detector Cover for Basic Machines, which emits a short Redstone Pulse when it is done Processing something. Machines that produce every tick would emit a constant signal though.
[ADDED] The Igniter now accepts Control Covers. There is no need to use Splitter Cables or similar Methods anymore. This can be very useful with the Auto Reboot Timers.
[ADDED] Mode Selector Covers now work on Electric Engines to adjust their Speed. Possible Modes for this are 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29 and 31. The Mode of a freshly placed Engine would be 15. You need an Extender to relay the Command due to the Engine not accepting Covers.
Tool Related Stuff
[ADDED] Improved Wrenching/Wirecutting Overlay, which actually shows the Connections and Facings now. Very useful for huge Pipes, Wires and anything that has a secondary Facing.
[ADDED] Special Plunger Support for my Basic Machines. They will try to clear the Fluid Output first and then the Fluid Input, 1000L at a time.
[ADDED] You can now use a Chisel to decalcify a Boiler. But for that you need to turn that thing off (the Steam inside has to be in the white Barometer Area, same for the stored Heat, but Water can be inside no problem). Hazmat or Fire Resistance Potion recommended if your Boiler is still hot. :P
Data Related Stuff
[ADDED] The Autocrafter can now accept USB Input for Recipe selection and works with the USB Switch aswell.
[ADDED] The Printer now accepts Molecular Scans for printing Material Dictionaries from them.
[ADDED] HDD Switch, which works like the USB Switch, but with a Single Hard Drive, that has 16 Slots, rather than having to store 16 USB Sticks, also the Drive itself can be placed inside a GT Book Shelf. Btw, did you know you can name USB Sticks and Hard Drives in an Anvil without Issues? Might be helpful, even though all Data is visible in the Tooltips anyways.
Misc Stuff
[ADDED] Creative Tabs for the regular Multiitems. I can't believe I forgot about that.
[ADDED] Small Stibnite Ore to normal Worldgen (lack of Antimony was a tiny Issue) and Small Sperrylite Ore to End Worldgen (just for more variety).


6.03.23:
[COMPAT]
USB 2.0 Sticks and higher are now able to store IC2-Classic Reactor Planner Plans.
For Users of IC2-Classic, it requires Version 1.2.1 or later to start MC with it.
The regular IC2 Users aren't affected by this at all.
[NINJA-FIXED] Crafting Electric Tools made them have 0 Energy Capacity.
[FIXED] Canning Machine Recipes for IC2 Fuel Rods.
[CHANGED] The USB Switch now requires a Selector Cover to select the USB Slot, rather than normal Redstone! (this will remove any interference from random Redstone on Sides where it's not supposed to receive from)
[CHANGED] Flax and Cotton Seeds now give Lubricant instead of Seed Oil.
[ADDED] Magnifying Glass can be used on an Advanced Button to reveal its settings.
[ADDED] Crystal Chargers (Laser equivalent of Battery Box). Some old folks may know a similar thing from IndustrialCraft 1.
[ADDED] 5 Tiers of Energium Crystals (going from 3200000LU to 819200000LU). They are charged with Laser Energy (LU) instead of Electricity (EU) and store a lot more Energy than Batteries.
[ADDED] Laser Energy Absorbers, which convert Lasers to Electricity with a 50% Loss.
[ADDED]
Redstone Selector Covers and Manual Selector Covers. They can be used to select:
The Slot used by the USB Switch (it's a requirement to have the Selector Cover on this Block).
The Packet Count emitted by Battery Boxes, Crystal Chargers and similar Energy Storage Boxes.
The Signal Strength inside a GT Redstone Wire (would only make sense if you used the Manual Selector with this).


6.03.22:
[CRITICAL-FIX]
Boiler not being able to release built up Pressure at all, while Burning Boxes are still running, causing lots of people to rage about explosions.
Before, Boilers could only release a constant amount of Steam, which ofcourse was not accounting for the tiny amounts of Pressure building up due to tiny flickering and similar things.
Said surplus Pressure would never be released until the Boiler is all like "I simply can't take it anymore!!!" and explodes.
Now, whenever the Boiler reaches 3/4, it will start emitting twice the Steam for until it is below 3/4 again, this is usually only happening for a single Tick or maybe two, so it's still safe for Turbines and Engines if that happens.
[LIKELY-FIXED] Immersive Engineering Bug, which was caused by their usage of deprecated GT Interfaces, by dumping said deprecated Interfaces into my Code, so that they "exist" and don't cause a Crash anymore.
[FIXED] Controller Covers, Pump Covers and Display Covers being placeable on things they can't actually interact with.
[FIXED] GT Hoppers not autostacking Items together after sucking in Items from World.
[FIXED] Some Bug in the Advanced Buttons.
[CHANGED] Any Recipe that requires Circuits can now accept Circuits of higher Tiers aswell. So you can essentially use Tier 6 Circuits in pretty much any Circuit requiring Recipe now.
[ADDED] Temporary Centrifuge Recipe for Helium, Neon and Argon.
[ADDED] Pressure Valve, that releases Gasses and only Gasses, when the Pipe is full.
[ADDED] Interface for the Weight-O-Meter to be able to override the Inventory Scan. Applied it to the Crucible.
[ADDED]
USB Switch (decided to not call it "USB Hub", as "USB Switch" fits more to its functionality).
Using the 16 states of Redstone will let you select which Recipe to use by an ajacent Printer, Replicator or Autocrafter.
It doesn't matter on which Side of the Machine you place the USB Switch, as long as it's adjacent.
The Machine, which normally uses the USB Stick has to have a USB Cable, of the proper Tier or better, in that Slot instead.
In case Universal Extenders are used, one Facing of the Extender has to face the USB Switch, while the other faces the Machine.
--- Stuff from one Version later ---
The USB Switch now requires a Selector Cover to select the USB Slot, rather than normal Redstone!


6.03.21:
[ADDED] USB Cables for the four USB Tiers. Ignore those for now, they are just needed for the next Update.
[FIXED] USB Stick requirement for the Matter Replication Stuff to just be USB 3.0 instead of 4.0.


6.03.20:
[NOTE] I totally forgot how unobtainable USB 4.0 Sticks are due to needing Graphene, oops. Quickfix on Friday.
[ADDED]
Quantum Energizer.
An Energy Converter for Light Units (LU) to Quantum Units (QU).
Quantum Energy is required for Mass Fabrication and similar.
[ADDED]
Matter Fabricator.
However, the T1 Matterfab has an efficiency of just 50% (consumes twice as much Energy), while T2 = 62.5%, T3 = 75%, T4 = 87.5% and T5 = 100%, making it more efficient to insert larger amounts of Energy into it at once.
There is a new GT UUM System, which uses Charged Matter and Neutral Matter to create Atoms.
In order to create Charged and Neutral Matter you need to decompose Atoms, meaning you need clean Elements to gain it.
Anything that can be made with the Matter can also be decomposed into Matter again. However, you can decompose Radioactive Materials, despite not being able to replicate them!
For example you could decompose leftover Materials, that are in no way needed. Yes, that includes ridiculously abundant things such as Oxygen too.
Hydrogen will only give charged Matter, since it has no Neutrons. Take this into account when you lack charged Matter.
[NOTE]
The Matterfab also creates the old fashioned IC2 UUM for compatibility (and so you can disable the IC2 Massfab in the Config).
It takes 32768 QU and 36 Scrap (or 4 Scrapboxes) to make 1 UUM Fluid (or 1 UUM Blob if IC2-Classic is installed).
Usually 1 UUM, when created with Scrap, costs 166666 EU, but there is a 75% loss when converting EU -> LU -> QU, which I did factor in, so its actually 131072 EU with this Device (still cheaper, but not THAT much cheaper).
T4 and T5 are more efficient in creating UUM, than the IC2 Massfab. That is in regards of Energy cost. It still needs a slight bit more Scrap.
It does accept 2 Metal Scrap from IC2-Classic per UUM as Amplifier aswell.
[ADDED]
Molecular Scanner.
Requires at least an USB 4.0 Stick to save the precise Data of the scanned Materials.
It is NOT limited to Materials that can be made by a GT Replicator, so you can fabricate USB Sticks with essentially useless Data.
It can only scan Materials that have an ID assigned to them, Materials unknown to GT cannot be scanned therefore.
Dusts, Ingots, Gems, Glass Tubes, Scraps etc. are all amoung the scannable Shapes.
But you need one FULL Material Unit of the Material you want to scan, so in case of the Glass Tubes, Tiny Dusts and Nuggets you have to insert 9, for example.
If you insert more than just one Material Unit (by inserting a Storage Block or a Double Ingot for example) then you successfully wasted Material, since it just needs 1 Unit, but it will still scan it successfully ofcourse. ;P
[ADDED]
Matter Replicator.
It requires Charged Matter (1L per Proton), Neutral Matter (1L per Neutron), 65536 QU per Liter of used Matter (watch out for the efficiency of the Replicator Tier you are using!), and an USB 4.0 Stick with Data of a non-radioactive Element to output 1 Unit of the desired Material.
Trinium, despite it being way far down in the Periodic Table, is a non-radioactive Element, just so you know. But forget making Plutonium with that thing, that will definitely not work.
Its Tiers have the same Type of Energy Efficiency Grade as the Matter Fabricator, so from 50% at T1 to 100% at T5.
[NOTE]
Since that Question came up: 1 Unit = 1 Ingot = 1 Dust = 1 Plate = 1 Gem = 9 Nuggets = etc etc etc


6.03.19:
[FIXED] Red Alloy and similar Wires having an initial loss of 1, making weak Signals impossible to transmit.
[FIXED] If you put a Stack of Batteries into a GT Batbox they were deleting all but one of the Batteries by setting the Stacksize to 1. Now they won't even attempt to charge/discharge when stacked.
[CHANGED] My RC Rock Crusher Recipes now have their Config default to false if the crushed Stack is not a Block.
[ADDED]
Steam Turbines, for Steam -> Rotation Units (RU) with a loss of 33.333...% (exactly 1/3rd).
Explosions happen when overfed with Steam. (explosions are currently disabled, but you know the noise of overcharge)
They will emit 1L of Distilled Water for every 200L of Steam to the sides, just like the Steam Engine. It will be voided if there is nothing to catch it.
[ADDED]
Dynamos, which convert Rotation Units (RU) into Electric Units (EU) with a loss of 25% (1/4th).
Crafted like the Electric Motor of the same Tier, but upside down.
Also has a non-craftable RU->RF Variant. Use MineTweaker for that thing, just like for the Flux variants of the Motors, Engines and Heaters.
[NOTE]
The 2/3 Efficiency of the Turbine and the 3/4 Efficiency of the Dynamo add up to an overall 1/2 Efficiency (exactly 50%) when converting Steam to Electricity, but unlike the RC Turbines (125% Efficiency, but eats Steel), mine are maintenance free, won't consume metals, have realistic ratios and stuff.


6.03.18:
[COMPAT] for IC2-Classic by Speiger. I think I fixed the most important Stuff, and yes it took a while to go through all the things in order to have it all right.
[CHANGED] Sulfuric Acid Recipe to use SO3 instead of SO2 (was a typo of some sort)
[ADDED] Inv Tweaks Support for Stuff, have fun clicking the Buttons that are half obstructed by Slots, because I have no idea how to set their Coords.
[ADDED]
A Charging variant of the Advanced Crafting Table. For Electric Tools and maybe others too, later.
Note that the Input Voltage has to match the Voltage of the Tool itself, the Table doesn't really have any Voltage by itself.
But don't worry there won't be explosions from that particular one, Steve is smart enough not to plug electric Tools into the wrong Voltage.


6.03.17:
[NOTE] Yep, I didn't do anything really useful, as I said earlier for this weeks release, it was a very minor one.
[NOTE] This was due to the "once a year"-opportunity to have some "regular" IRL fun alone, what left me a bit too busy to code.
[NOTE] Also something got my focus back to Terraria and a short Anime (Steins;Gate), so that in combination with aforementioned fun stuff kinda made this some sort of "taking a week off GT" ^^'
[NOTE] But I can't stop working on GT for too long, because I constantly have the urge to add things to it. :D
[FIXED] Battery MultiItems crashing.
[FIXED] Electric Screwdriver Double-Tooltip. (yeah I seriously needed some filler for this Changelog)
[ADDED] Electrolyzer Recipe for charging Certus Quartz.


6.03.16:
[API] Added more Hooks for Covers, so they can change the Item/Fluid Interaction on the Side they are attached to.
[FIXED] Alloying Recipes displayed Temperature in NEI.
[REMOVED] Plasma Cells, because those seriously don't make sense at all.
[CHANGED] The way Nuclear Isotopes are made to require Centrifuging of Refined Ore. This also means that Isotopes no longer go as byproducts of washing Ores like Cobalt. No more Hazmat for that Ore.
[CHANGED] Quarzes no longer smelt into Silicon directly.
[CHANGED] The Advanced Crafting Table cannot use the Robot Arm Tips in Recipes, this is to prevent derping when assigning Recipes to a Blueprint for the Autocrafter. ;)
[ADDED] Functionality to the Pump Cover. Now it works like it did in GT5. Just without the Energy consumption.
[ADDED] All the electric Tools back.


6.03.15:
[NINJA-NOT-FIXED] Something. Maybe. I couldn't replicate the Issue but I did "something". And said "something" had no Effect. XD
[FIXED] A few Bugs.


6.03.14:
[FIXED] The Sulfuric Acid and Aqua Regia Bath Recipes requiring Energy despite Bath Recipes not being supposed to do that... copypasta issues, lol.
[FIXED] Mixing Bowls and Bathing Pots can no longer do Recipes, which output low density (gasses that are lighter than Air) or high temperature Stuff.
[CHANGED] The Canner and other directly electric Machines to use the new old electric Component Items.
[ADDED] Paint Removal Spray.
[ADDED] Most Electric Components such as Motors and Robot Arms back, note that they are component Items and not Covers (at least for now). Also not all of the Tiers are craftable (due to lack of certain required Items), but the Tiers LV to IV are all craftable.
[ADDED] Electric Mixer/Loom/Sifter, but they have a 50% efficiency, meaning every Recipe takes twice as long with them, while costing exactly as much EU as an external Motor/Engine would. So it's only for compactness sake. This means that it wont cost more EU, but it will cost more time!
[ADDED]
The Autocrafter, which Autocrafts most regular Crafting Recipes.
But a fair Warning: Not all Recipes are autocraftable, for example most of the Recipes using Hand-Tools are NOT. This is INTENDED!
Use the dedicated Machines, which I didn't want to make obsolete, that create things like Plates or Buckets etc, and not the Autocrafter!
In order to select a Recipe, you have to make a Blueprint of the exact Items used inside your Recipe (even NBT has to match!) in the Advanced Crafting Table and then place it in the special Slot in the middle of the GUI.
There is a Set of Robot Arm Tips, which have to be used instead of Hand Tools in non-Blacklisted Recipes. You also have to specify those in the Blueprint! Those Tips will never get used up if you use the Autocrafter.


6.03.13:
[FIXED] Argentia Crop dropping Tin instead...
[FIXED] Carbon not working in Crucible.
[FIXED] Advanced Button not saving Switch Mode.


6.03.12:
[FIXED] Spray Tooltips in edge cases.
[FIXED] Basic Machines not pulling Fluids from adjacent Tanks properly.
[ADDED] All the alcoholic Beverages are now burnable for Energy. Note, that none of them is Fuel Efficient though!
[ADDED] Laser Welder. Welds Ingots, Machine Casings and Stuff together using a Laser. (Mixed Metal Ingot Recipe got moved away from the Press and towards the Welder)
[ADDED] Made Dye Fluids Mixable as long as their Category is identical. If you want to mix different Categories, then you have to make Chemical Dyes out of them first! Indigo and Ink don't have a Category and therefore need to be made Chemical Dyes in order to mix them.
[ADDED] Fluid Tanks to the Ender Garbage Bin / Dump in order to be able to void Fluids.
[ADDED] Sulfuric Acid related processing for a lot of Ores.
[ADDED] A lot more Tags for Materials, especially Elements. This is more of a background thing though.
[ADDED] Zirconium and Hafnium as Byproducts for Titanium and Tin related Ores, so that they are available, even if I don't plan to use them right now.
[ADDED] Alternate Mining Laser Recipe.
[ADDED] Pomeraisins


6.03.11:
[FIXED] Burning Boxes
[ADDED] Aqua Regia and related Ore Processing for Gold, Platinum and Tin.


6.03.10:
[COMPAT] Fermenter Recipes for IC2 Biochaff to IC2 Biomass and Distillery Recipes for IC2 Biomass to Ethanol. And added Fuel values for MFR, EIO and IE Fuels aswell. In general I fixed a lot of Fuel Values to reflect the max you can get from them in RF based Generators. (good that GT Stuff consumes so much Energy to justify this increase)
[FIXED] Absurdely low spawn Rate of the large Tetrahedrite and Cassiterite Ore Veins.
[FIXED] Extinguishers not putting out Burning Boxes.
[ADDED] Redstone Machine Switch Circuit Cover, to be able to turn Machines ON/OFF using Redstone (on the Side the Circuit is attached to)
[ADDED] Auto Redstone Machine Switch Circuit Cover, to be able to turn Machines ON/OFF using Redstone (on the Side the Circuit is attached to), but this variant lets the Machine finish its current Batch first before turning it off!
[ADDED] A new advanced Redstone Button with multiple Modes, including a Lever alike Switch Mode and a simple "indicator Lamp" Mode. Also they can be painted and they glow in the Dark when active. They can be inverted aswell. Essentially the ultimate Button. XD
[ADDED] Liquid Burning Box, note that it doesn't accept Gasses even if they are in the Burnable Fuels List!


6.03.09:
[NINJA-FIXED] Issues with Recipe optimization due to no longer copying ItemStacks, despite at least one call to copy() being needed.
[ADDED] A pleasant surprise for every Supporter of GregTech, if it works on the first try, even though I did test it more than usual. ^^
[ADDED] Laser Engraver. With all its current Recipes ofc.
[ADDED] Electric Laser as Power Source for Laser related Stuffs.
[ADDED] Laser Fiber Wire for transmitting Laser Energy. Since Lasers are Light, Losses or Limits aren't realistic, so its essentially lossless.
[ADDED] Fermenter. Now some Potions are easier to do and some Drinks are available again.


6.03.08:
[FIXED] Empty OreDict Prefix Filters causing Nullpointer Exceptions.
[FIXED] Dust Funnel being able to accept from Bottom. This isn't supposed to be like that.
[CHANGED] Magnetic Separator Recipes to be more dynamic.
[CHANGED] Mass Storage is now hiding unnecessary Zeros on its display.
[ADDED] Book with info about Steam related Machines.
[ADDED] Small Tungsten Ore to Overworld and End Worldgen.


6.03.07:
[FIXED] Duranium and Tritanium support.
[ADDED] The new Printer Manual. Can be created by inserting an empty vanilla Book and a unit of chemical black dye into a Printer.
[ADDED] Leather Armor dyeing to the Bath.
[ADDED] Dolamide Ore to the Worldgen. Its byproducts make it possible to get Duranium and Tritanium.
[ADDED] OreDictPrefix Filter (of the Extender kind, just like the Slot based one). It will also relay any Fluids you pass through it (without filtering), just for convenience.
[ADDED] Sluice. It cleans crushed Ores. It is definitely not an "Ore Washer", the "Ore Washer" will totally never be added again.
[ADDED] Magnetic Separator. It takes purified Ores and extracts some of the Magnetic Dusts from them, or pulls the non-magnetic dusts from thenormal ones.
[ADDED] Dryer. It dries Stuff. It can be used in Ore Processing. Side Products usually include distilled Water from Water Vapour.
[ADDED] Sluice Sand, a product of drying the Mud from the Sluice. It can be further processed like a minor form of Rare Earth and also gives a pile of Stone Dust.


6.03.06:
[CHANGED] Some Default Unification Targets for preferring certain Project Red and Ender-IO Stuff.
[CHANGED] Replaced Ghast Guide with Witch Guide, also added the Ghast Info to the Blaze Guide and renamed that Guide to fit for this new additional Info.
[UPDATED] Thermal Expansion related Resources, Mixer Recipes and Unification Targets.
[ADDED] Text to the Spider Guide. Also added more Text to some of the Guides.


6.03.05:
[COMPAT] With the LostBooks Mod by FatherToast regarding placement of Dusty Books in GT Shelves and also with additional Dungeon Loot in GT Shelves (will have a 25% chance to spawn a dusty Book instead of a vanilla empty book). http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288323
[COMPAT] Warpbooks Mod (for Shelves and stuff)
[FIXED] A few small Recipe Issues with the Scanner and the Printer.
[ADDED] A few Mob Hunting Manuals. Sometimes I'm forced by the outside World to "do almost nothing", during these Times I decided I will just write some GT Books, so that I don't "do absolutely nothing useful", like playing Spider Solitaire.
[ADDED] GT Rails, not as complex as Railcrafts ones. They are essentially either improved or cheaper variants of the common vanilla Rails.
[ADDED] Possibility to copy Blueprints to the Scanner/USB/Printer.
[ADDED]
Vanilla alike Hoppers made of different Materials.
I decided that they move Items when they get updated (so almost instant) or once every 64 ticks.
They are less laggy and have slightly different Item movement Speeds and Inventory Sizes.
Can also interact with Minecarts if they are pointing into a Rail or are below a Rail. (also works with modded Rails)
Also they can emit Stacks of specified Stacksizes if you screwdrive them. So they only emit Stacks of 4 Items at once for example.
If you use that Mode then the Stacks inside will be limited to a certain size, which is the largest possible Stacksize divisible by the Mode. So if you set it to 9, then it will be 63 for each Slot for example. This is useful for overflow control.
Only Hoppers with at least one empty Slot can suckup Item Entities above them.
While Hoppers can be Rotated to face upwards, they won't emit into the direction and instead just stop emitting at all.
A simple opaque Block or an Inventory above the Hopper are enough to not make it search for Items laying ontop of it.
Functionality can generally also be disabled by just applying Redstone.
And because that stupid Question came up way too often on Forums and IRC: NO, I FRIKKIN WONT DISABLE THE VANILLA HOPPER, SERIOUSLY IT'S USED IN MANY OTHER MODS CRAFTING RECIPES, HOW INCONSIDERATE CAN YOU BE?!? NOBODY AND ALSO NOBUNNY WILL BE ABLE TO CONVINCE ME OTHERWISE!


6.03.04:
[FIXED] Dollies being able to grab GT Blocks, what leads to a Crash when they try to place them again. Yes, you can't grab GT TileEntities with Dollies anymore.
[ADDED] If you set a Mossy GT Stone Block on Fire it will burn away the Moss. Also Mossy Bricks and Cobbles are very flammable now.
[ADDED] Ender Garbage Bins, which are basically Item Deleters which instead of deleting Items, dumps them into a Garbage Dimension.
[ADDED] Ender Garbage Dumps, which can access the Garbage Dimension to pull out the Garbage. Admin Only.
[ADDED] Mixer Recipes for some Alloys.
[ADDED] Pillars to floating GT6 Dungeons, no more Antigravity Dungeons.
[ADDED] Batteries back from LV to HV at least. Also added Redstone, Teslatite, Nikolite and Electrotine Batteries. (for earlygame purposes)
[ADDED] Battery Boxes back, so you can store your Energy now using GT instead of IC2. Amperage == Amount of Batteries. You need at least one Battery in order to have it conduct Energy. Available as 4 Slots and 16 Slots Version.
[ADDED] Energy Display Cover, works on Battery Boxes aswell. Yes getEnergyCapacity and getEnergyStored actually return the Values of all Batteries combined.


6.03.03:
[FIXED] Some Issue in the Adv Crafting Table.
[FIXED] Exorbitant Plow Durability Loss.
[FIXED] Security Leak in Key Locked Safes. Not gonna mention Details though, but make sure you are not using Key Locked Safes until this Update.
[CHANGED] Piston Doors to use colored CFoam rather than Vanilla Metal/Gem Blocks.
[ADDED] Magnifying Glasses can be used to show whats inside a Shelf without "taking out the Item, opening the inventory and hovering over it to see its tooltip".
[ADDED] Different Icons for different Forestry Letter Sizes in the GT Bookshelf.
[ADDED] Redstoned Versions of the GT Bricks. (and used them to power the Redstone Lamps in the GT6 Dungeons)
[ADDED] Spawn Height and Room Density Config for GT6 Dungeons.
[ADDED] Barracks to the GT6 Dungeons.
[ADDED] Chests to "T" shaped Corridor Segments. Also improved Corridor placement by optimizing useless Corridors away.
[ADDED] Surface Entrance, meaning it is visible on Surface now, meaning I can half the chance so it is 5% instead of 10% to be within the currently loaded chunks.
[ADDED] End Portal Room. This can be turned off in the Config though.


6.03.02:
[IMPROVED] Rendering of GT Blocks by adding a Parameter that tells which sides are obstructued to IRenderedBlock and IRenderedBlockObject.
[FIXED] Colored Books being hidden in NEI
[FIXED] Light Opacity of GT Stone Blocks
[CHANGED] The tiny secret Dungeon to be a larger Stronghold alike Dungeon. Its not limited like vanilla, but still rare per default. Also it kinda looks like my own Base Design, lol.
[ADDED] Corridors, Piston Doors, Crate Storages and Libraries so far.
[ADDED] Safes can generate Loot too now, they have the special quirk of having always all 15 Slots filled, when you open them.
[ADDED] Bookshelves can generate Loot in GT6 Dungeons now. Note, that they generate it if a player gets closer than 32 meters (square radius) to it, since the Books are usually a visible thing, unlike Chest and Safe content. Also ONLY loot that can be placed inside a shelf will be generated, if something couldn't be placed then it will get replaced by a vanilla Book.
[ADDED] Redstone Mechanics to the Shelves. Buttons, Levers and Redstone Torches can be used to add secret Book buttons/switches to the Shelves.


6.03.01:
[FIXED] Accidential Dependancy on Forestry
[FIXED] Bookshelf Cover Interaction (Texture was kinda borken)
[ADDED] A few more vanilla Items that can be scanned in order to get Block Textures for canvases. Flint&Steel for example gives you a Fire Texture, Lava Buckets give a Lava Texture etc.
[ADDED] More Crossmod Wood Variations to the Book Shelves. Like, a lot more.


6.03.00:
[FIXED] Coins not being smeltable.
[ADDED] Some more Books and alike to the things you can add to the Shelves.
[ADDED] Crafting Recipes for the 8 colored Books and their larger variants.
[ADDED] A bit of Dye related Compatibility. Also changed some Flower Squeezer Recipes.
[ADDED] Canvas, it can be used as Cover and works together with the Obscurator in order to be able to copy Textures.
[ADDED] USB Sticks. Yep, just tiny Data Storage Devices. It's needed for storing and transporting Data.
[ADDED] Scanner (of the office kind, not the molecular one!). It can scan the Textures of Blocks and other things aswell as Books and Maps to a USB Stick.
[ADDED] Printer. It can directly print Canvases, Books and Maps that you scanned to a USB Stick before. Uses only Chemical Dyes!
[ADDED]
Book Shelves made of Vanilla/Forestry Wood or Metal.
They are paintable as always.
You can store up to 28 Books (or anything that mentions it in its tooltip!) inside them (7x2 Front, 7x2 Back).
Every normal vanilla Book rises the Enchantment Power Bonus by 1/12th, every enchanted Books rises it by 1/6th, and yes an enchanted book and 10 normal books add up to 1 point. (1 point = 1 vanilla Bookshelf, only full points count!)
If you find a Book/Paper/Manual/Schematic/Scroll/StoneTablet/Disk/Floppy/Painting/Guide/Map/Blueprint/Catalogue/Letter/Note/Clipboard/Wallpaper/Record/Poster etc. of another Mod to be incompatible then tell me about that.
Loose pieces of Paper, Maps and Blueprints will be placed in an "imaginary" Folder for more visual appeal.
You can disable Slots by placing Cobblestone inside them, in case you use Automation on a Bookshelf and don't want inteference (or just need to block the backside of a Shelf).
Also there are no Items "unintentionally" insertable to the Shelf, so stop asking if it's a Bug that you can put certain things in it!
And before you people consider dumping Bibliocraft in favour of this, don't forget that Bibliocraft has far more than just Bookshelves! It also has Clipboards, Signs, Huge Books, Stockroom Catalogues and a lot of other Decoration!
--- Stuff from two Versions later ---
[ADDED] Bookshelves can generate Loot in GT6 Dungeons now. Note, that they generate it if a player gets closer than 32 meters (square radius) to it, since the Books are usually a visible thing, unlike Chest and Safe content. Also ONLY loot that can be placed inside a shelf will be generated, if something couldn't be placed then it will get replaced by a vanilla Book.
[ADDED] Redstone Mechanics to the Shelves. Buttons, Levers and Redstone Torches can be used to add secret Book buttons/switches to the Shelves.
--- Stuff from five Versions later ---
[COMPAT] With the LostBooks Mod by FatherToast regarding placement of Dusty Books in GT Shelves and also with additional Dungeon Loot in GT Shelves (will have a 25% chance to spawn a dusty Book instead of a vanilla empty book). http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288323


//=== Version Number Jump due to URL and Download Host change (technically OvermindDL1 was already responsible for it before, so its not really a change of Host) ===//


6.02.07:
[FIXED] Cover Items being placed on the Mass Storage rather than in the Mass Storage.
[COMPAT] for some Mo'Creatures things.
[COMPAT] for Silver with Werewolves of Howling Moon, Mo'Creatures and anything that my matching process defines to be a Werewolf.
[ADDED] Support for multiple Default Enchants on a Material.
[ADDED] Bathing Pot, its like the Mixing Bowl, but for the Bath Recipes. just like the Mixing Bowl, you cant use this for Gases or very hot Fluids.
[ADDED] Injector, a Machine that injects Stuff. It can inject Carbon Dioxide into Water to make generic Soda. It can also fill Harvestcrafts Donuts with Jelly (but not just Grape Jelly like the original Recipe).
[ADDED] Mint and Tea Crops to IC2.
[ADDED] A new even larger Gem Size called "Legendary Gem", has a 0.01% chance of being outputted by sifting or dropped by a small Ore (Fortune and Silk Touch can increase those chances).
[ADDED] Electric BC Assembly Lasers. Yes its just BC Compat, nothing else. They have to be placed directly ontop of the Assembly Table and are Tiered, so you can't spam them.
[ADDED] Mode to Item Pipes that prevents insertion of Items into or from adjacent Inventories, use the Monkey Wrench to set said Modes. These Modes should prevent things like the BC Assembly Table, from emitting into the Pipes they are supposed to be receiving Items from.
[CHANGED]
Mass Storages can only face horizontally now.
But it can now be Monkey Wrenched for Hopper alike Behavior on the Bottom Side.
Also added Item Displays to them, so you can see what Item is inside.
The Numbers won't sync, if you put a Cover on them, meaning they would lag less (if you aren't interessted in the Numbers).
You can fill a Mass Storage to 100% by giving it an NEI 111 Stack of its Item.
When destroyed by an Explosion, all Items contained are lost, so better craft a more blastproof variant if you store valuables in it. (to prevent massive Item Entity Lag)
When broken the normal way it will drop a max of 512 Stacks (of the respective items max stacksize!), meaning everything above that will be lost! (to prevent massive Item Entity Lag)


6.02.06:
[WARNING]
Renamed the Configs that turn Machine Explosions off. All default to true!
Note, that I will give you some time to prepare by making noise at the "exploding" places (instead of blowing them up) and also give you time to turn the Config off if you like to.
Solar Panels are immune to Weather per default (but not the Transformers!)
Other Electric or Heat based Machines need to be protected from Rain. You can use Covers for that aswell. Why Heat based ones too? Because they would vaporize the Water around them!
Another Note is that Water Blocks are now a Threat to electric and heat based Devices aswell, so cover them up and don't dump water in your workshop!
[FIXED] Construction Pickaxe Crash
[CHANGED] Sensors now use Tin Alloy Double Plates instead of Steel Double Plates.
[CHANGED] Some Mortar Recipes to be more useful. With Low Tier Stuff you can now mortar 4 Items at once to save on durability.
[CHANGED] Clay Balls to now be worth 1 Unit of Clay rather than half a Unit. This makes Clay Blocks worth 4 Dusts as opposed to IndustrialCrafts two Dusts, so it is kindof a buff, at least for Foam related things.
[ADDED] Clay Dust to Clay Ball Recipe in Bath (so basically just add a bit of water to make it shapeable)
[ADDED] Orange and Yellow Sapphire, also the different Sapphires just like the Ruby have impurities now depending on color.
[ADDED] Additional way to get Aluminium from Bauxite. Requires pure Sodium or Potassium however.
[ADDED]
Mass Storage
This Device is basically like a Barrel from various Barrel Mods but with way better UI, aside from lack of item display as of right now.
It can store exactly 1 Million Items inside, no matter how large their original maximum Stacksize is. This means you can go 1 million pickaxes or 1 million cobblestone, it doesn't care if it is unstackable.
The Storage itself has 6 Buttons which give you a varying amount of Items when clicking them (and you dont put Items back when clicking one of the Buttons with an Item in hand). No buggy leftclicking required, in fact leftclicking doesn't do anything, due to the Buttons I added.
If you click the Window in the middle of the Storage with an empty hand, then all Items that can go in the Storage will go there.
Adjacent Advanced Crafting Tables can pull Items from it and also dump empty Container Items into it if allowed.
Using the Screwdriver on it causes its Filter to reset automatically when it gets empty, so you dont have to soft hammer it everytime to set it to a different Item.
--- Stuff from one Version later ---
Mass Storages can only face horizontally now.
But it can now be Monkey Wrenched for Hopper alike Behavior on the Bottom Side.
Also added Item Displays to them, so you can see what Item is inside.
The Numbers won't sync, if you put a Cover on them, meaning they would lag less (if you aren't interessted in the Numbers).
You can fill a Mass Storage to 100% by giving it an NEI 111 Stack of its Item.
When destroyed by an Explosion, all Items contained are lost, so better craft a more blastproof variant if you store valuables in it. (to prevent massive Item Entity Lag)
When broken the normal way it will drop a max of 512 Stacks (of the respective items max stacksize!), meaning everything above that will be lost! (to prevent massive Item Entity Lag)


6.02.05:
[FIXED] Crafting using the Advanced Crafting Table not triggering Vanilla Achievements and some Achievements added by Mods.
[FIXED] Vanilla Tool Repair in Advanced Crafting Table. I shouldn't optimize Math, I have to let Wolfram Alpha do it for me, otherwise I may fail.
[FIXED] Some Crucible Alloying Recipes.
[FIXED] Directional Tooltips of the regular Transformer


6.02.04:
[FIXED] Exploit with Cauldrons being filled by pipes (and then emptied for 1000 Liters of Water using a Bucket), by increasing the amount to be filled from 250 to 334 Liters of Water.
[FIXED] Sliced Ham Recipes.
[CHANGED] Design of regular Metal Plates (and their multiplate variants). Decided to go RC Style, but I made my own Textures for them.
[CHANGED] Converters that convert from Electricity to any other Energy form, now have an Amperage Limiter, so they don't attempt to consume more than they can convert (what would lead to an explosion). This fixes a lot of explosive Electricity Issues.
[ADDED] Tooltips to the Advanced Crafting Table GUI.
[ADDED]
Long Distance Electric Wires. (they took a lot more time to create than I expected, due to me having to check for Chunk Loading and Unloading Issues)
As long as the starting and ending Point are loaded it can transmit power, even/especially if parts of the Wire itself are in unloaded Chunks.
It has a lower loss than normal Wires due to the higher Voltage and better Insulation.
These Wires CANNOT branch off, they are just a from Start to Finish thing. For Branching you need to do some sort of Transformer House, so you can split Energy manually.
Also you cannot use different types of Wire in the same Line.
The Wire Blocks themselves are really just regular non-TileEntity Blocks, they don't even have "visual connections". Just draw a Line of those Wires from Long Distance Transformer A to Long Distance Transformer B.
Also make sure the sending and the receiving ends of this are chunkloaded (for obvious logical reasons, it will NOT cause lag if you don't load them).
All Cables have a loss of 1 EU every 8 Blocks, so 0.125EU per meter. With higher Voltages, these 0.125EU/m are almost unnoticeable.
It will always have a Loss as if the Wire was 512 meters long, whenever it is shorter than 512 meters. This is to discourage usage in short distance connections. So the Loss is at minimum 64EU per Packet.
Only use it if your Wire is more than 64m long, yes 64m, normal Wires have a loss of 1EU/m or even more, meaning it will be useful from 64m and onwards.
If you are doing a SERIOUSLY LONG Cable with this, then make sure that after losses, the Energy the receiving End gets, is more than its minimal Energy Output!
Max Distance is about 8km for EV (2048EU/t), 32km for IV, 131km for LuV, 524km for ZPM, and finally 2097km for UV. Most Servers don't even go past 10km, so I think this should be more than enough.
You can send ANY Amperage through these Cables, but you only have one Surface to connect to.
A yellow indicator Light tells you, that the Target of the Long Distance Transformer is unloaded right now.
Technically speaking, this Device is nothing more than a lossy Energy Teleporter, that requires a Cable made of Dummy Blocks to connect the Start Point and the End Point.


6.02.03:
[FIXED] Server Issues due to that NEI Hook. That new Hook is completely useless, as its not usable the way it was intended, so it got removed.


6.02.02:
[FIXED] Bacon Sandwiches and Ham Slices being uncraftable because of a Typo.
[FIXED] Wooden Milk Bucket vanishing Issues, when MFR isn't installed.
[FIXED] Railcraft Passive Anchor / Engraving Bench Recipe Overrides.
[CHANGED] A huge portion of Obsidian related Material Value balance. Obsidian is now worth 9 Dusts. This is because lots of Mods have different Values for it and 9 seems to be the largest. (originating from IC2 btw)
[COMPAT] Some Forbidden Magic stuff is now supported, in regards of Items and Recipes.
[ADDED] Bronze and Brass Dust Recipes for the Mixer. Note that it is more inefficient than using the Crucible. Also Brass, BismuthBronze and Bronze are all three Furnace-able now and have Recipes for Wooden Buckets being made of them.
[ADDED] Additional Recipes to compress Cobblestone/etc using the Press. Yes, not the Compressor, because that may cause Recipe Conflicts.
[ADDED] Tooltip to Tools showing how many times you can use that Tool as Container Item for crafting.
[ADDED]
Advanced Crafting Table. For anyone who didn't play with GT4, it is basically a Project Table/Bench/etc.
It stores 16 Items to refill from, left of the Grid.
Has 5 Slots for auto-filling Fluid Container Items such as Buckets or Universal Cells, above the Grid.
Got additional 36 Slots in a second GUI that is accessed when you click the Drawers on the Front/Back.
No internal Fluid Tank, meaning Fluids don't get stuck in there anymore.
Also included is a Slot that lets Items be pulled out by a Hopper or any Automation thingy.
Two Buttons which "flush" the Crafting Grid. (either the same way as above via automation, or by filling the Storage Slots)
A small Slot right next to the Crafting output where you can "Park" your Item, for example for Recipes that require multiple steps.
Also with Redpower alike Blueprints that can store Crafting Recipes. Use Shiftclick on an empty Blueprint inside the Blueprint Slot to create one. You can also have "incomplete" Blueprints, meaning you could use a blueprint to put some Items at a certain position on the grid.
Using the Monkey Wrench you can disable Item Input into the 4x4 grid or the 9x4 grid respectively.
Using the Screwdriver you can toggle the "Diversity Filter", of the 4x4 or the 9x4, ON and OFF, if it is ON then it will prevent multiple Slots being filled with the same Item.


6.02.01:
[IMPROVED] Energy Conversion Blocks and Transformers now only update their Active State once every 64 ticks, also there is a "flickering" or "blinking" State in order to save on graphical updates and data packets, meaning less lag in those regards.
[ADDED] A few Recipes for getting Helium/Argon.
[ADDED] If you sneak-rightclick to place a mechanical Safe it will instantly be owned by you, without extra need of rightclicking it to claim it.
[FIXED] Some wrong Sounds being played.
[FIXED] Red Alloy and Signalum Wires emitting Light.
[FIXED] C-Foam Spray not working.


6.02.00:
[IMPORTANT] I didn't test the Mechanical Safe in multiplayer Situations. It should work, but I have no Idea if it prevents other Players from accessing/harvesting it, so feedback is appreciated (even if its duplicate posts).
[REMOVED] Lots of old Code. Also refactored some Stuff! This is the Reason why it is now at 6.02.
[FIXED] Pigs dropping neither Bacon nor Ham.
[FIXED] Cows not dropping Ribs.
[FIXED] Bacon Sandwiches not actually using Bacon.
[FIXED] NEI Bug where certain Recipes arent displayed for the Crucible, despite them being there.
[FIXED] Smelter being able to smelt Ore Stuffs, such as Bauxite into Metals.
[IMPROVED] Molds now accept Fluid Input. Note that whatever Pipe you use has to have enough Bandwidth to fill the Mold 100% in one go. Or you just place it adjacent to the Smelter.
[IMPROVED] that aforementioned render related thing a bit more, by reducing calls to getTileEntity, when checking adjacent GT Blocks for opacity.
[CHANGED] A lot of Machines to accept Inventory Input from the Tank Input Side too (just not automatically, only if you pipe/hopper it in). Did the same to SOME Fluid Input Sides too. Don't worry, your existing automated Setups won't break on this change.
[CHANGED] Bath to have its Auto-Fluid-Output at the Bottom. (this is the only case where I actually changed the purpose of a Side, what might break automation, wait, there is no Recipe outputting Fluids anyways for the Bath so I didn't break anything)
[CHANGED] using Silk Touch on Small Ores increases the percentage of "good" Gems being dropped. And if you somehow can combine Fortune and Silk Touch they would stack together on this effect btw.
[COMPAT] EBXL and BoP stuff is now supported, in regards of Items and Recipes.
[ADDED] Wooden Buckets. They can only carry Milk and Water, but that is enough for pretty much all purposes you could have earlygame. Note, they have the annoying downside of being unstackable, even when empty! They are meant for earlygame and not for cheap bastards. :P
[ADDED] Mortar Recipe for 8 Scraps + Mortar = 8 Tiny Dust. I think this is worth mentioning.
[ADDED] Mushroom Stew Fluid. Should also be compatible with the MFR Mushroom Soup Fluid. It can be created by mixing a vanilla Brown Mushroom with ANY other Mushroom (see ExtraBiomesXL, Biomes O' Plenty, Twilight Forest and Harvestcraft)
[ADDED] Antidote, a Pill that just cures Poison. It is made with vanilla Brown Mushrooms.
[ADDED] TNT Recipe using Flint Dust.
[ADDED] Blast Resistance Tooltips to all my Blocks. Btw regular vanilla TNT Blasts need a Blast Resistance of 16 or greater to be proof. Creepers Blasts are 12+.
[ADDED] The Progress Sensor can now also measure the countdown value of the vanilla Mob Spawner. If it shows "0" it means that the Spawner is trying to spawn something but fails doing so. (Light Level and other Spawn Conditions)
[ADDED] Transformers for Electric Power.
[ADDED] Crystallisation Crucible, which creates Silicon Boules and similar.
[ADDED] Solar Panels (8EU/t Day, 1EU/t with free view to stars). And don't tell me 8EU/t is OP, you know that almost every Machine requires a Converter from EU to its actual power and that those all have a 50% loss! That means its effectively 4 Generic Units per tick unless you are able to power a machine directly with EU. Also the Recipe for the Panel is quite hardcore with crystalline Silicon Plates and stuff. Solar Panels are immune to Weather such as Rain and Thunder (explosion wise)
[ADDED]
Safes with 15 "safe" Inventory Slots.
Mechanical Safe. This Safe can only be opened by its Owner. (Added in a later update: If you sneak-rightclick to place a mechanical Safe it will instantly be owned by you, without extra need of rightclicking it to claim it)
Keylocked Safe which is basically the same as the Mechanical one but also requires a Key Item to be opened and closed. The first used Key will be the one to open/close it. Note: It can only be harvested while open.
I might add a Digital one later, that has a Hexadecimal Number Pad for access by more than one Person.
NOTE: The Safes aren't 100% Blastproof! People can blow them up to get the Resources! (That is if they have a strong enough explosive, what depends on the Material of the Safe itself)
[ADDED]
Stone generator Module.
This Item can be used to generate infinite Stone inside GT Machines by just putting it into the Input Slot.
This process ofcourse still needs Energy to function as you dont just get Stone Items from it, but instead a "Token" that functions as if a vanilla Stone is inside the Machine (works for most Machines).
Yep, combined with an Extruder you could make an "Igneous Extruder" with it. XD
Note, that this is not as OP as it sounds, and GT5 had a Cobble generator as Machine back then too. This is just the new way of doing it.
[ADDED]
Item and Fluid Filters. (in the 3 variations Items, Fluids and Items&Fluids)
They are based on Extenders, so you cannot chain multiple of them together nor combine them with actual Extenders.
They have 54 Slots and default to not let anything go through when they are left empty.
In case of Fluid Containers, such as Buckets or Universal Fluid Cells, it will NOT take the Fluid inside them as Filter Value, you have to rightclick the Item in order to switch it to the contained Fluid.
Things can also go through the Filter in reverse direction (out of the block it is attached to), but in that case the Filter won't apply and will let anything pass.
When Machines pull things through a Filter they only grab things the Filter allows to pass. Note that the main side has to be the one facing the MACHINE and not the Chest.


6.01.08:
[IMPROVED] something render related regarding rendering of obstructed Facings. (the improvement is that they now don't render anymore with GT Machines and Foamed Wires/Pipes)
[FIXED] Another Crash Bug when mining certain Blocks. Like Sugarcanes or Cauldrons.


6.01.07:
[FIXED] Removed several useless Null checks in my OreDict Code.
[CHANGED] Bronze Color again. But not to the old value. Same for Sheldonite being closer to Platinum again.
[ADDED] Galvanized Steel. Needed for Tier 2 Electric Machines. Stainless is now Tier 3 (and the others got shifted too). Note, that your Machines will still stay the same Tier, just the Material they are made of "magically" changes.
[ADDED] Slightly cheaper wooden variants of GT Chests, that only use half as much Metal but are only half as resistant. Still Double Chest sized ofcourse.
[ADDED] Various Circuits and Recipes for them. This also applies to a lot of other Mods Circuits. BC Circuits can be crafted with the Press. But those Recipes require the Redstone Chipset, meaning you still need a BC Lazor for that detail.
[ADDED] The Press back. This time with 3 instead of 2 Input Slots.
[ADDED] The Chemical Bath back. Requires Time but no Energy to work, just like the Coagulator. Can dye certain Items, fry certain other Items, bath certain crushed ores for special resource output and is used with Soldering Alloy to make Circuits.
[ADDED] The Smelter back. Makes Solids into Liquids and heats up other Liquids. Note that I only added FLuids for the most important Materials. Also Note that the Crucible and the Molds don't use Fluids! (also you might not be able to turn those fluids back for now)


6.01.06:
[NOTICE] ForgeMultiBlock dependency cycle on older Versions of it. It has been fixed on ForgeMultiBlock Side, so update FMB to fix eventual Dummy-Container Issues.
[NOTICE] I improved the logging of unknown Stuff in the OreDict. If you want to help update to this version and give me the OreDict.log File
[API] Moved a bunch of Ore Materials inside MT.java into a subclass due to the common 65536 limit problem in java code.
[CHANGED] Platinum, Mithril, Bronze and Nickel RGB Color not fitting to the TE, Forestry, RoC and more other Mods Variants. (it really bugged me after a while). Rhenium got the old Platinum Color with the Shiny Item Set btw. It's a byproduct of Molybdenite Ore I think.
[FIXED] TerraFirmaCraft Item Unification to Vanilla Items.
[FIXED] Basic Machine and Extender Rendering Crash.
[FIXED] Fluid Recipe Bugs by switching from a FluidID HashMap to a String HashMap.
[FIXED] Dyes being mixable with the Fluid Dye Items. Only the solid Dyes can be used to get the mixed Colors.
[REMOVED] a lot of shapeless Crafting Recipes to now make use of the Bowl mentioned below. Like the GT Gunpowder Recipe or the GT Recipes for Dough.
[ADDED] Two more Output Slots for the Shredder.
[ADDED] Tons of Compat work, like Material Support, Recycling Support, new Alloys. (The creation of most RotaryCraft Alloys is NOT supported in the Crucible, since the Crucible isn't a Blast Furnace)
[ADDED] Slot to NEI showing the Machines that can perform Recipes of that Class.
[ADDED] Measuring Cup to grab partial Fluids out of the Mixing Bowl without requiring the Universal Fluid Cells of IC2
[ADDED] Bottles for the Dye Fluids. Btw, its better to put Ink Sacs into the Squeezer and then bottle the Ink, than to directly craft with the Ink.
[ADDED] Electromagnet (Source of Magnetic Energy), it outputs into two Directions.
[ADDED] Polarizer (for making Permanent Magnets for Motors, for example)
[ADDED]
a Mixing Bowl made of Clay and some Red Dye.
It can be used for Mixer Recipes that don't involve Gasses or very hot Fluids.
There is also a "Table" variant of it, if you want it to be 1/2 of a Block more elevated. (needs crafting and a vanilla brick slab)
You can rightclick Fluids into and out of it, if there is a mixing recipe using them.


6.01.05:
[ADDED] Tiny versions of crushed Ores, which are going to be added by IC2 or some of its Addons later.
[ADDED] GT Pipes, that are at least 250 Liters large (later fixed to 334 Liters!!!), can now fill Cauldrons with Water. Pipes smaller than 250 Liters wont even connect.
[ADDED] Clear Glass Blocks with corresponding Glass Slabs, available in 16 colors.
[ADDED] Crucible now adds Bonus to Mob loot if used for killing vanilla-esque Mobs.
[ADDED] Emerald Green, a nice and healthy Algae based Meat substitute, that is made of 100% vegetarian Villagers.
[ADDED] Loom. Does Wool and Silk related things. Also pretty much anything remotely related to regular clothing, like Saddles and Horse Armor.
[ADDED] Generifier. It turns things into a more generic Form, for example it turns Wrought Iron (which should usually be interchangable) into regular Iron. It works without Energy and does 1 process per tick. Also works on certain Fluids.
[ADDED] Extruder. It is more expensive than the Crucible, Energy wise, and needs the Shapes to be made of Tungsten Carbide instead of Steel. (2 Tungsten Carbide = 1 Tungsten + 1 Carbon, the Shapes need 2 Tungsten Carbide instead of 4 Steel, so just one Tungsten and one Carbon per Shape)
[ADDED] My own Set of Tin Cans. Yes, Set, as in it shows the rough content of the Cans. Some of them are valid Cat/Dog Food.
[NOTE] The old Crop Item is now finally removed. (It had the Missing Texture Icons due to the planned removal)


6.01.04:
[API] Made the API Core downloadable as separate Mod.
[FIXED] Warm Foods being way too hot. (Environmine)
[ADDED] A bunch of new somewhat pointless but logical Materials and Recipes.
[ADDED] Redstone Mode to Molds using Monkey Wrench.
[ADDED] Lumium Wires can be foamed and they will still emit light and the C-Foam will glow in the dark if it is on.
[ADDED] Recipes for C-Foam from UB Rocks, which are even precolored in some cases.
[ADDED] Lots of Compat Code.
[ADDED] If you use a GT C-Foam Can on an IC2 Scaffold you will get a scaffolded C-Foam Block, what is basically a cheap way to get RGB paintable and Cover compatible C-Foam Blocks. (Metal Scaffolds are working like in regular IC2)


6.01.03:
[API] You can remove the GT Code (gregtech Folder) from the jar to have just the GT API loaded. I need to contact OvermindDL1 for updating the Download Page in a proper automatic fashion.
[API] Moved NEI Plugin to the API itself.
[API] Moved the Sensor Base Class to the API.
[FIXED] Default Unification Targets not getting set properly, causing them to be overridden by the PrefixItems and PrefixBlocks.
[FIXED] A Server Issue when doing certain things with GT Tools.
[FIXED] Not being able to use Fluids in Electrolyzer due to Recipes only existing for Dusts.
[ADDED] C-Foam. Better than IC2 Foam and also available in the 6 types of GT Slabs.
[ADDED] precolored C-Foam Spray also added the regular Spray Paint.
[ADDED] C-Foam removal and C-Foam hardening Spray.
[ADDED] C-Foam to GT Pipes and Wires. Also makes them as Blast Resistant as regular C-Foam Blocks.
[ADDED] Machines. Mixer, Roaster (for sulfur ores and some other purposes), Distillery (mainly for Potions and Drinks) and Canner to be precisely.
[ADDED] Fire Distinguisher. Just kidding. Ofcourse it is a Fire Extinguisher.
[ADDED] A shitload of compatibilty Recipes and Patches. A shitload is more than a fuck-ton, but it is less then a dontgiveafuck-ton
[ADDED] Pincers, to grab things out of molds faster without getting heat damage.
[ADDED] Tripwire cutting to Scissors and Wire Cutters by rightclick.
[ADDED] Due to "popular" demand you can distill your Vodka as often as you want. The end result will still be Vodka.


6.01.02:
[IMPROVED] Some API Stuff to make Addons easier and added usage examples.
[IMPROVED] Internal Structure of the Mod. API should still be compatible after that.
[IMPROVED] Harvestcraft compatibility. Also removed the Milk Bucket to 4 "Wooden" Milk Bucket Recipe (where do those Wooden Buckets even come from?). Instead replaced it with Milk Bucket + up to 4 Bottles = up to 4 Milk Bottles (which are ofcourse usable in crafting the same way as the "Wooden" Buckets)
[ADDED] Native Compatibility with Enviromine Hydration and Temperature of Food/Drinks.
[ADDED] The Food Items and most Drinks back. Not all of them are craftable yet.
[ADDED] Holding Scissors doubles falling damage height. That means falling 2 meters already loses half a heart.


6.01.01:
[FIXED] various tiny Bugs.
[FIXED] Serious Issue with some Alloying Recipes.
[FIXED] Issue where my Recipe Collision check Function removes a Recipe even though it should just check for a collision.
[FIXED] Adjusted some specific Recipe removal Parameters more precisely to have properly functioning Railcraft compat.
[FIXED] Tools taking damage when used in creative.
[CHANGED] Hammering Ores now only gives crushed Ores, so you need a sifter for Gems.
[ADDED] Recipe for Torches using crushed Coal Ore, because of above.
[ADDED] Scissors. They can shear Mobs and harvest wooly things. NOT SUITABLE FOR SHEARING BLOCKS!!! Do not run around while holding them!
[ADDED] Centrifuge Recipes for impure/purified Piles of Dust back. Also added Electrolyzer Versions of said Recipes with different Outputs.
[ADDED] Squeezer Recipes for the Plant drops back.
[ADDED] Whenever a TileEntity of GT is Erroring while ticking, it switches Texture and Display Name to indicate that something is wrong.
[ADDED] Tooltips for Items that can be used as Cover.
[ADDED] Blank Cover, simply a blank Cover used for Crafting purposes. But it is also decorative with multiple chisel Designs.
[ADDED] Workbench Cover, also available in different Designs.
[ADDED]
Machine Status Display Cover.
The first Lamp (from the left) indicates, that the Machine could run actively, if it were turned on (that means Recipe can be processed AND output is empty enough).
The second Lamp indicates, that the Machine is running, be it with or without Recipe, so it is green in standby too.
The third Lamp indicates, that the Machine is running actively with processed Recipe and everything.
The fourth thing is an ON/OFF switch for the Machine. It doesn't only turn the Machine off, but also eventual Motors/Heaters/Engines/etc which are directly adjacent and pointing into the Machine. (this doesn't apply to lowtech things like the Burning Box)
It has multiple Designs which can be switched using a Chisel. Right now there are only a Bottom Bar and a Top Bar available.
You can still see and also click the Machine through the Cover.
[ADDED]
Automatic Switch Cover.
Automatically turns ON the power to a Machine when it is needed and OFF when unneeded. (see the first indicator lamp in the display cover)
It works together with Machine Status Display Covers, even though it will take full control over the ON/OFF Status.
You can still see and also click the Machine through the Cover.
[NOTE]
The Functional Covers do not have to be attached to the front of the Machine even though they kinda look like that.
You can attach them to any of the 6 Sides. See it like some kind of "Upgrade Slots" :P


6.01.00:
[API] Restructured the API in a way that probably crashes a lot of compat things of other Mods, that is why the secondary version number finally got bumped. The gregapi.tileentitiy package got a bit too full, so I made subpackages and also renamed some TEs to sort things out properly. I did keep a deprecated Version of ITileEntityEnergy at its old place however, because that one is already too widely used.
[FIXED] Pipes and Wires not displaying Color upon being painted. (Paint does NOT influence connection behavior due to the new wrenching/wirecutting based connection system)
[ADDED] Tooltips to things that can damage you when touching them. Lack of said Tooltip automatically indicates that the thing is safe to touch. And yes there are Fluid Pipes that ARE Safe to touch (as of this Version).
[ADDED]
Basic Covers back. Currently only the Plates and Foils are available as Covers.
Covers now also render in Inventory (and also on the NEI Overlay) when attached to stuff.
They Render no longer as if they were floating in the Air, when attached to Wires or Pipes.
They don't block Pipes or Wires when being placed inbetween them, since you can set that via the newer connection system anyways.
Plates as Covers can change Design by chiseling them.
[ADDED]
Electric Wires, similar to the good old GT5 Wires with a few Bug fixes and slightly different stats and Voltages that are also between Tiers.
They behave like every other connecting Block does, just with the difference of using Cutter instead of Wrench.
Also now accept IC² Stuff as Energy Input, because the new connection behavior for GT6 things makes it more efficient than the old Transformer solution.
The Electric Meter Sensor does work on those Wires and it displays the total EU transmitted after substracting loss.
The displayed Number is the amount of EU the adjacent stuff receives, so if it displays 31 then the adjacent receiver will receive exactly 31 EU.


6.00.62:
[FIXED] None of the GT Pipes being Gas Proof and therefore leaking constantly when filled with gasses such as Steam... (was a problem in my base code, that caused it to ignore additions of Booleans to an NBT)
[ADDED] Vanilla Flint&Steel -> GT Flint&Steel Shapeless Recipe.
[ADDED] Some more possible Materials for Pipes, like Ironwood, Thaumium, Void Metal, Adamantium, Enderium and Vibraniumsilver.
[ADDED]
GT Style Freestanding Redstone Wiring.
Made of either Red Alloy (x16 Range), Signalum (x64 Range) or Lumium (x16 Range + glowing)
Only connect to things that the Wire Cutter has set facing to.
When connected to vanilla Redstone Wires or solid Blocks they will substract 1 from the EMITTED Redstone Signal Strength. They won't do that to Machines and other things however, so a Signal Strength of 15 can still be conveyed properly.


6.00.61:
[FIXED] Some Code that was not done properly.
[FIXED] Forestry Support properly.
[ADDED] Improved Output Check and an On-Demand Mode to Basic Machines (use Screwdriver), that only produces when the Output is empty. Useful if you have things that only retrieve Stuff when needed.
[ADDED] Universal Extender, that conveys basically everything, including GT Sensor Data for example.
[ADDED] Machine Casings and adjusted many Recipes to use them. They are just Boxes and basically useless when placed, aside from Deco and Beacon purposes.
[ADDED]
Item Pipes from GT5 back.
They are working the same way they did before, from a mechanic standpoint.
In order to connect, they need a whack with the wrench, at the specified target side. (they also auto-connect to the clicked Block when placed)
They can connect to Air Blocks, other Item Pipes and Inventories.
[ADDED]
Fluid Pipes. Slightly improved.
In order to connect, they need a whack with the wrench, at the specified target side. (they also auto-connect to the clicked Block when placed)
They can connect to Air Blocks, other Fluid Pipes and Tanks.
Fluids are evenly divided as far as I can tell via testing. (May not work with very tiny amounts of Fluids)
Temperature of Fluid matters ofcourse, but now it is no longer arbitrary and matches the Crucibles.
But they will prioritize non-Pipes over Pipes when inserting Fluids!
Progress Sensors measure Fluid throughput.
Temperature Sensors measure Temperature.


6.00.60:
[COMPAT] Another Forestry Compat Update for some of my Cutter Recipes, because someone over there thankfully fixed the aforementioned NBT Junk.
[API] Added Cryo EnergyTag used for cooling down things. Basically the opposite of the Heat EnergyTag. (I didn't make it negative Heat Units, because that could be confusing)
[FIXED] DIV/ZERO in the Crucible Code. Also made the Crucible a bit more precise, so that the Maths that created the zero, doesn't output zeros anymore.
[NERFED] Crafting of vanilla Rails, but only if Railcraft isn't installed.
[ADDED] Dedicated NEI GUI Background Texture for all GT Recipes.
[ADDED] Support for custom Models on PrefixBlocks without needing to create an own PrefixBlock Class.
[ADDED] Curved Plates. Made with Plates in the Roll Bender, or by Hammer Crafting. Also used in Metal Armor Recipes now, all hail the nerf.
[ADDED] Rail Items. Worth 1/4th of an Ingot each. They can be turned into Railcraft Rails too.
[ADDED] A bunch of Recipes.
[ADDED]
5 different Rolling Machines. Yes FIVE. Maybe I will add a sixth one, we'll see.
Everyone of them has a different purpose. one makes Plates, one makes Foils, one bends Rings, one makes Wires and there is also one for Rails.
It needs Rotational Energy (RU) from the back side.
If you place a Chest or something to the left of it, it will automatically suck in Items, and it will emit Items to the right side automatically.


6.00.59:
[COMPAT] Updated Forestry Compat. Finally Forestry has actual Recipe Managers!
[COMPAT] Another Forestry Compat Update for some of my Cutter Recipes. Some Idiot (I'm not going to look up the person who commited that shit) decided to use NBT instead of MetaData to store Sub-IDs of wooden things. Seriously, there are 32766 different possible Sub-IDs in ItemStacks, no need for putting Wood IDs into an NBT, making every comparison Method highly inefficient.
[FIXED] All the sided Behaviors of rotated Basic Machines.
[FIXED] Fluid Handling of Basic Machines.
[FIXED] Vanilla Jukeboxes making dupes on stacked Music Discs, also made Music Discs stackable, because why not.
[ADDED] The Electrolyzer and Centrifuge Recipe generators. This doesn't mean that all Recipes are there, but there are a lot of them. Also changed some Recipes and ofcourse made all 6 Fluid Outputs possible if the Melting/Boiling/Plasma Point is right, and if there is a suitable Fluid.
[ADDED] The Redpower Inventory Tweak, that refills the Hotbar Slot when an Item/Block gets used up with the Items in the column above said Slot.
[ADDED] A way to turn off Auto-Inputs and Outputs of Basic Machines using the Monkey Wrench.
[ADDED]
Block Extenders. (Fun Fact: I thought about those 10 Months ago already)
They can expand the surface Size of Machines (regardless of Mod), so that you can access them easier.
You can NOT chain multiple Extenders in a row, meaning you can only use up to 6 per Machine.
Every Extender has an Input Side (the one where the Arrows point at the center), and an Output Side (the one where the Arrows point away from the Center).
The Input Side has to be connected to the Machine, while the output Side is there to redirect anything, that comes out of the connected Machine, into that direction.
Anything connected to the 4 remaining Sides will get redirected to the Machine placed at the Input Side.
You can also use this, to put a Chest at the Input Side, so that there are more access Points for that Chest, basically like a "Multiblock"-Storage.
Also possible is putting Input and Output at the same direction, meaning you can connect a Machine to itself.
When placed it faces the Input away from the Player as opposed to facing towards the Player like every other Block. This is so it is easier to attach it without the need of wrenching it into place.
Different Extenders have different purposes. There is one for Tanks, one for Inventories and one for both, as of now.
Also, I will NOT add any way to access a GUI through those. Just build your Machines in a way that still lets you access the GUI, if you really need the GUI.
[ADDED]
Compressor. (Like the IC² one but uhhm... more expensive?)
compresses stuff, duh.
It needs Kinetic Energy (KU) from the left side.
If you place a Chest or something ontop of it, it will automatically suck in Items, and it will emit Items to the bottom side automatically.
It has two "front" faces, even though one of them is on the back, technically making it the back face.
[ADDED]
Coagulator. (A place where fluids wait to turn into a dryer shape)
Will turn Latex Fluid into Rubber. Maybe I will find some other Recipe appliances too. And no, this is NOT a Fermenter.
It doesn't need Energy. Just Time.
This is a "Just One Tier" Machine. As in there is only the Stainless Steel Variant of this thing since Time is relatively constant.
Fluid Tank or Pipe Input ontop, and Inventory Output at the Bottom.
[ADDED]
Squeezer. (while it may look like that, it is NOT a Compressor)
Only GT Recipe, that is available as I am writing this, is Resin -> Latex. Also I nerfed the Resin to Rubber ratio to 1:1, instead of 1:3.
But Forestry Recipes are available too, at least the ones which don't involve Containers.
It needs Kinetic Energy (KU) from the top side.
If you place a Chest or something to the left of it, it will automatically suck in Items, and it will emit Items to the right side automatically.
The Fluid Output is on the Bottom. So yes, you can just place it ontop of a Coagulator.
[ADDED]
Centrifuge. (now with 6 Item Outputs and 6 Fluid Outputs)
It doesn't have all the Recipes yet, so DON'T COMPLAIN, I AM GOING TO ADD THEM LATER.
Forestry Centrifuge Recipes are all available to this Machine.
It needs Rotational Energy (RU) from the bottom side.
Input of both, Items or Fluid, is at the top side.
Fluids get outputted to the left, Items to the right.
It has two "front" faces, even though one of them is on the back, technically making it the back face.
[ADDED]
Electrolyzer. (now with 6 Item Outputs and 6 Fluid Outputs)
It doesn't have all the Recipes yet, so DON'T COMPLAIN, I AM GOING TO ADD THEM LATER.
It needs Electric Energy from the bottom side. This thing is one of the very few electric Machines.
Input of both, Items or Fluid, is at the top side.
Fluids get outputted to the left, Items to the right.
It has two "front" faces, even though one of them is on the back, technically making it the back face.
[ADDED]
Buzzsaw. (which is basically a Sawmill/Cutter, like in the good old GT5 Days)
Does Cutting Recipes. 1 Input, 1 regular Output, 1 Output for Dusts.
It needs Rotational Energy (RU) from the back side.
It also needs either Lubricant or Water from the bottom side in order to cut things.
If you place a Chest or something to the left of it, it will automatically suck in Items, and it will emit Items to the right side automatically.


6.00.58:
[ADDED] Progress Sensors for GT Machines. GT Machines now also work with the hasWork thing from Buildcraft now.
[ADDED] =100% and <100% Modes to all Sensors. =0 and >0 are not necessary because there are already = and > Modes.
[FIXED] Critical Bug that made my OreDictManager ignore any non-GT-OreDict-Item. The optimised Interface handling didn't have a fallback to the regular System... (don't worry, this has nothing to do with the large optimization that halved my loading time.)


6.00.57:
[ADDED] Progress Bars to Mod Loading. I know that there is a big ass warning that it may not work right above it (even though Forge itself uses it), but I have made wrappers that should prevent any damages, aka crashes.
[IMPROVED] The Recipe Replacing Portion of the Code, which has caused the 3rd Startup Lagspike in a way that it is now no longer lagging at all. Well, and you get to watch Loading Progress on the first two Lag Spikes. :P
[IMPROVED] The Thaumcraft Portion of the OreDict Code, in order to not spend ages on registering Aspects. This made my Loading Time go from 6 Minutes to 3 Minutes! Yes seriously, that was the source of most of the Lag on Startup!


6.00.56:
[API] Changed a lot of the Recipe System. The Recipe Lists can be found in the RecipeMap Class. The old RecipeAdder is now deprecated. If you fill in any wrong Parameters, the GregTech.log File will contain small StackTraces of where you inserted those.
[API] Improved NEI Recipe Handler to now support up to 12 Fluid Inputs and 12 Fluid Outputs per default, instead of just one of each. Also increased the Max of Item Inputs and Outputs from 9 to 12. It will should work well as long as Item-IN + Fluid-IN <= 12 and Item-OUT + Fluid-OUT <= 12.
[API] Improved Basic Machines to now accept Liquids when the Recipe Handlers specify their existence.
[CHANGED] Recipe Handlers of GT now automatically generate Config Files instead of me having to do that stuff manually. It is inside the Folder called "Recipes". The Recipes themselves are represented by a List of all their Input Items and Fluids.
[CHANGED] Sifter Recipes can now have up to 12 Outputs instead of just 9.
[ADDED] Config to change Tool Durability, Quality, Speed and Handle Material of Materials. (Note that already placed or worldgenerated Blocks are NOT affected by the new Tool Quality/Harvest Levels)
[FIXED] YET ANOTHER Div/0 Error, that somehow never happened to me while testing the IC² Enet Stuff. Why is a Voltage of 0 even possible?!?
[FIXED] Basic Machines sometimes outputting infinite Items due to exceptions happening.


6.00.55:
[FIXED] Fine Wires not being available for certain Materials, making the non-Steel Sifters uncraftable
[FIXED] Div/0 Error, that somehow never happened to me while testing the IC² Enet Stuff.


6.00.54:
[API] Changed a Part of the Recipe System. Also added Hooks into the 3 NEI Recipe List Functions (Usage, Recipes, All Recipes), so that Recipes which are generated dynamically can now be handled properly (also has the side effect of being able to put dynamic recipes at the end of the large list).
[FIXED] Energy Types which only have one Value such as RF, MJ, HU or Vis not being accepted by some consumers, nor being emitted properly by converters, because their Packet Size has to be 1.
[FIXED] Recycling Maceration of certain Materials not yielding the right output Material. (yeah I found that while readding the Macerator btw.)
[CHANGED] Moved the Stacksize Configs into a separate File. It will default to the old Values in the Main Config
[ADDED]
Code for the new Basic Machines. It will be a lot different than the old Basic Machines!
Comsumption Type of Power depends on each individual Machine. A Lathe or a Macerator would need Rotation Energy (RU) from a Motor, a Furnace would need Heat Energy (HU) from a Heater or a Burning Box. Only very few things would accept Electricity (EU) directly, such as the Electrolyzer.
Under normal circumstances every Basic Machine is usually constructed like a "multi-block", since you almost always need to attach some Power Converter (Motors, Heaters, Lasers, Magnets, Engines etc.) to the whole thing.
The Machines run constantly even if they have nothing to do (unless you cut power ofcourse). So "Power Input = ON/OFF-Switch", meaning you need to stop the whole power supply in order to stop the Machine, what can be as simple as using a Splitter Cable or a Batbox with Redstone Mode (if it is electric ofcourse).
Power consumed doesn't depend on the EU/t of the Recipes anymore, the Machine itself calculates duration depending on Energy Input, so the more Energy you insert the faster it is (remember that the thing will explode with a too high Packet Size).
The EU/t of a Recipe are now only the minimum requirement to run the Recipe, if the Energy goes below the EU/t of it, or if the Energy goes below the minimum Input of the Machine itself, it will reset progress and start from 0 again (just like the good old times).
If you use a Soft Hammer on the Machine, it will delete the progress and ALSO delete the Input Items (of the currently processed Recipe), so that it clears out without having to break and replace the thing (because the previous ON/OFF Functionality of the Rubber Hammer is now moved to the Power supply).
Building a Machine can take MUCH more effort than it seems in the beginning, since you need to attach a LOT of things to a Machine for it to work the way you want it to work. Don't wanna waste Energy? Attach some kind of ON/OFF-Switch to the Power Supply. Wanna macerate faster? Attach a second Batbox to your LV Motor to overclock it.
The old Overclocking by using higher Tier Machine mechanism is still in place. If the Max Input of a Machine is 4 times higher or more than the Recipe, and the Min Input smaller than the Recipe, it will half the Process Time and quadruple the Minimum Energy Input.
The GUIs of the Machines will be simple, just Input/Output Slots and a Progress Bar. Since everything else is basically done via "hardware" and other external Blocks now there won't be the need for any Buttons or things like that.
I'm still kindof considering the addition of RF powered low efficiency versions of every Machine. But their Recipes will definitely be disabled per default if I really do that. Not that people instantly go into the final Tier with Big Reactors, and that on default settings.
Most Machines have some kind of Diamond(s) or Diamond Plate(s) in their Recipe, what makes it a little bit harder to create them.
[ADDED]
Oven. (Just a renamed MC Furnace)
Does whatever Recipes a Spider Furnace does.
It needs Heat Energy (HU) from the bottom side.
If you place a Chest or something to the left of it, it will automatically suck in Items, and it will emit Items to the right side automatically.
It has two "front" faces, even though one of them is on the back, technically making it the back face. This is if people wanna have right->left Ovens, they can just turn them around.
[ADDED]
Shreddering Machine. (Just a renamed Macerator, that does not crush Ores)
Does Macerator alike Recipes and with 4 Outputs. (the old Tier = Output Count was stupid anyways)
It needs Rotational Energy (RU) from the left and/or from the right side.
If you place a Chest or something ontop of it, it will automatically suck in Items, and it will emit Items to the bottom side automatically.
Will sometime get different set of Outputs, because I plan to add a Pulverizer for making scraps into dusts, and then I will let it emit scraps instead, so you essentially need two Machines.
It is also responsible for Recipes like Stone/Cobble->StoneDust, Obsidian->ObsidianDust
[ADDED]
Crushing Machine. (Just a renamed Macerator, that does crush Ores but no regular Stuff)
Does Macerator alike Recipes and with 4 Outputs. (the old Tier = Output Count was stupid anyways)
It needs Kinetic Energy (KU) from the back side. And yes, I did something that makes it require positive and negative KU, which only GT Engines with their push/pull-movements can supply for now.
Also the Engine Frequency has a HUGE Effect on the Energy Efficiency depending on Recipes. So sometimes a fast (red) LV Engine is better for short Recipes (crushing Cobble to Gravel) than a slow (blue) MV Engine.
If you place a Chest or something ontop of it, it will automatically suck in Items, and it will emit Items to the bottom side automatically.
It is also responsible for Recipes like Stone->Cobble->Gravel, StoneBricks->CrackedBricks->Cobble and Obsidian->CrushedObsidian (from Railcraft)
For Sand you will have to sift Gravel. Or just use the IC² Macerator.
[ADDED]
Lathe. (which is basically a Lathe, like in the good old GT5 Days)
Does Lathe Recipes. 1 Input, 1 regular Output, 1 Output for Dusts. Makes Ingots to Rods, Bolts to Screws, Nuggets to Rounds, Gem Plates to Lenses and Wood to Sticks.
It needs Rotational Energy (RU) from the bottom side.
If you place a Chest or something to the left of it, it will automatically suck in Items, and it will emit Items to the right side automatically.
It has two "front" faces, even though one of them is on the back, technically making it the back face. This is if people wanna have right->left Lathes, they can just turn them around.
[ADDED]
Sifting Machine. (Automatic Sifting Table)
Does Sifter Recipes and with 9 Outputs.
It needs Kinetic Energy (KU) from the back side. And yes, I did something that makes it require positive and negative KU, which only GT Engines with their push/pull-movements can supply for now.
Also the Engine Frequency has a HUGE Effect on the Energy Efficiency depending on Recipes. So sometimes a fast (red) LV Engine is better for short Recipes than a slow (blue) MV Engine.
If you place a Chest or something ontop of it, it will automatically suck in Items, and it will emit Items to the bottom side automatically.


6.00.53:
[FIXED] Iron Rod Magnetizing Crafting Recipe with 8 Redstone using wrong Rod.
[FIXED] A lot of random Issues, which have been mentioned recently.
[FIXED] Boilers not accepting Energy due to the minimum energy limit I have added to all Machines. (which was added because weak sources shouldn't power strong things directly)
[FIXED] PrefixBlocks and PrefixItems not updating when I replace the Materials. (see Garnet)
[FIXED] Machines keeping old Material Color, after I changed the Material they are made of, because of a missing check if they have been painted or not. (they just saved the color anyways, even when they were unpainted)
[CHANGED] A lot of Garnet related things. Also the two "3 in 1 Garnet" Gems are removed. They will be auto-replaced with Almandine and Andradite. If you changed the Worldgen Config, then you should check it for the 6 new entries for each Garnet Type, because the two old ones are removed.
[ADDED] OreDictItemData for "Limestone Stones" and others. Also added some more stuff as Byproduct for certain Stone Types.
[ADDED] Electric Igniter Block that can start the Burning Box and similar, when it is connected to electricity. Can also shut down regular Nether Portals.
[ADDED] Electric Motor for future GT things. It will run depending on how much EU you dump into it.
[ADDED] A Log File containing the full Material List to the Config Folder (so you can easily look it up when editing Configs).


6.00.52:
[FIXED] Some Bugs and messy Code.
[FIXED] Even more Bugs I found in some Systems...
[API] IBlockToolable.onToolClick now has an additional String List Parameter to return chat to, instead of needing to directly chat to a Player Entity for that. This Parameter is null if nothing is listening to it.
[CHANGED] Coinage Mold now makes a breaking Sound when the regular Hammer Item lacks required durability or the automatic Hammer lacks energy.
[ADDED]
Magnifying Glass.
Now used as a Crafting Tool for making the GT Books, because SCIENCE.
It makes a funny noise when being used as weapon or for mining.
Will get more uses later, once I decided what exactly I want to do with it.
[ADDED]
Automatic Hammering Machine. Currently only useful ontop of the Coinage Mold.
Makes a "Hammer Rightclick" on the Block it is facing and is powered by KU (Steam Engines in this case).
Can also crush Blocks in World. This includes Ores ofcourse, wow that rhymed. Note, that the Tool Quality is still important on this one.
Note, that it requires the Engine to alternate between positive and negative Energy in order to work (GT Engines always do that).
There are not only Hammer Qualities, but also Hammer Power, which depends on the Engine used, and also on the current Speed of the Engine.
1 KU is worth 10 Hammering points, 2000 Hammering Points are needed for the Coinage Mold (every GT Steam Engine can power that) and 10000 Points being one regular use of the Hammer.
[ADDED]
Electric Engines to convert EU into GT-KU. (the stuff the auto-hammer uses)
Speed and therefore also Power can be adjusted with a Screwdriver to a certain extend depending on the Engine.
If the Speed gets changed, the Engine will push and pull with a higher frequency, meaning that some devices get less power but more output when setting it to more speed. So you will need to tweak the hell out of this thing to make it work perfectly.
The Efficiency of the Electric Engine itself is fixed at 50% loss with 2 EU => 1 KU (1 EU => 1 RF, yup 75% loss for RF, because this is not a Flux Dynamo) and doesn't depend on other conditional Factors at all, unlike other Engines.
Like all electric Devices of GT, the Engine accepts up to 2 times the Voltage of its own Tier before exploding (explosions currently disabled, but that doesn't stop me from annoying you by spamming the electric explosion sound)
The Engine itself runs as long as it is connected to Electricity, just like the Electric Heater.


6.00.51:
[IMPROVED] The way Number NBTs are set inside GT Tools. There is no reason to use Long whenever the Numbers happen to be too small for it anyways, and since getLong() also returns values of Byte, Short and Integer NBTs, I save a few Bytes on that for the low durability Tools and similar.
[IMPROVED] The way Material Data is saved in NBTs being now by ID instead of by Name (unless the ID is -1, or it is an old Tag, in which cases it will still use the Names).
[FIXED] Worldgenerator sometimes ending up generating wrong Ores, because of a "break;" being at the wrong location. This was only really visible when the Worldgen Config has been used with a lot of Custom Veins.
[NERFED] GT Metal Crops and similar Rare Drop Crops growth speed to be about ten times slower than before.
[CHANGED] The Mini Nether Portal now uses Obsidian Sticks rather than Obsidian Blocks to be crafted.
[CHANGED] Sensors to now store the displayed and the set Value in two different Variables in order to make Mode switching better.
[ADDED] Dust Funnel to autocraft Dusts into the desired Size in a primitive way. It works only on one Type of Dust at once, unless you are inserting precise amounts of Dust, like you had to do with the Packager in GT5.
[ADDED] A way to override OreDictItemData from inside the Item itself. This can also fake OreDict Entries to some of my Systems. (now you can recycle "disabled" PrefixItems, which are not registered to the OreDict, like those now unobtainable Toolheads, when I nerfed some Materials)
[ADDED] Nether Brick Tools.


6.00.50:
[IMPROVED] The NBT Key Names inside the MetaTool Class to be much shorter in order to save on sync. Old Names are ofcourse still supported, since there is a bunch of pre-existing Tools out there.
[FIXED] Exorbitant Durability on Angmallen and Hepatizon.
[FIXED] A rather huge Error in the Base TileEntity.
[FIXED] Steam Engines not being able to face upwards when placed without using a Wrench afterwards.
[ADDED] Grindstone for sharpening Tools. Needs 1 Sandstone Block for 4 sharpening processes. Also ofcourse has NEI Support.
[ADDED] Chisel Rightclick now turns Smooth Rocks (the double smelted ones) into Chiseled ones.
[ADDED] File Rightclick now turns regular Rocks into the Smooth ones.
[ADDED] Slabs for the GT Rocks. Placeable in all 6 Directions and with all MetaData Types. Crafted with Block + Saw (horizontal). Piston Pushable.
[ADDED] Reinforced Brick Versions of GT Rocks (crafted with a Brick Block and a Steel Rod). They are just more Blast Resistant and harder to mine.
[ADDED] Recycling for damaged vanilly Tools and Armors. You won't get all the Material out of it ofcourse, and pre-repairing the stuff gives you a vanilla bonus on durability and therefore Materials, so you should still try to fully repair stuff before melting it down.


6.00.49:
[FIXED] GT Tools not dealing additional Enchantment Effects against Mobs, such as Endermen and Slimes.
[FIXED] A lot of the terribly cheap crafting Tools being available, so that one could use them in an illogical way for crafting (Hammers, Saws, Files and similar). Ice and Glass Tools in particular, but also Stone and Stone alike Tools in some cases.
[FIXED] Melting Points of Alloys which have a larger or smaller amount of Components, than the end result. This affects Red Alloy, Blue Alloy and maybe very few others.
[CHANGED] Ironwood to require 2 Angmallen (50% Iron, 50% Gold) instead of 1 Gold and substracted 1 Iron. In the end it still has the same Ratios of Iron, Liveroot and Gold, just the Recipe uses Angmallen (I did a similar thing to Stainless Steel in the past by letting it use 3 Invar instead of 1 Nickel).
[ADDED] Some kind of Fake NEI Recipe Handling thingamabob for the Crucible. It should show most Recipes.
[ADDED] Dissolving Enchant against Slimes. Default Materials for that are Copper, Annealed Copper and Hepatizon. Salt and Rocksalt too in theory, but you can't make Weapons of those. This also works against some Twilight Forest Mobs.
[ADDED] Almost full Metallurgy Support for its Materials. So you can now specify its Ores in my World Generator and make GT Tools with those Metals.
[ADDED] Tool Heads and Stuff for Copper. Also removed Flint Chisel to keep balance.
[ADDED] Enderium, Signalum and Lumium Recipes to the Crucible. For Signalum you might want to throw in small amounts of Copper one at a time, after the Silver and the Redstone are already molten, otherwise you either get Sterling Silver or Red Alloy. I DID test that and it DOES work. Also I added that way to the Description of the Alloy itself.
[ADDED]
Tooltips to Materials in order to identify their correspondend Mods (for example Red Alloy => Redpower, or Invar => Thermal Expansion).
For the Materials added by the API alraedy, this means that I added the Mod, which originally added that Material to the Game (for the Mods I remember the Names of).
For the ones who use my Material System, I would recommend adding the Mod ownership to your own Mod if you add a new Material, otherwise you will get labeled as "Not added by GregTech" instead.


6.00.48:
[FIXED] A lot of the faulty References to my Recipe System, which happened due to a change I made to it.


6.00.47:
[API] Changed a lot inside the Recipe System. Also allowed Chances > 100% on Output Items and somewhat proper processing of those.
[FIXED] The Stacksize Config not working properly at all.
[FIXED] "oreBasalticMineralSand" being parsed into "oreBasalt" with the Material "icMineralsand", what resulted into a Situation similar to the one I had with "oreNetherQuartz"
[ADDED] Whenever something vaporizes from the Crucible it will do a lot of Damage to the close environment, including but not limited to AOE Damage against Entities.
[ADDED] When the Sifting Table is used it will exhaust the Player. (that means hunger)
[CHANGED] Sand, Gravel and Red Sand Ores now have Sifter Recipes rather than Pulverisation Recipes, since they are basically pulver already.


6.00.46:
[FIXED] The Smeltery and the Molds not using the actual vanilla Textures for Water, Ice, Lava and Obsidian, what looked rather disturbing.
[FIXED] Burning Box sometimes producing multiple Ashes when being blocked.
[ADDED] Sifting Table. Processes the good old sifting Recipes from GT5 (and some more) by hand. Also with NEI Support but without GUI. You will probably see the obvious NEI Button on it if you have NEI installed.


6.00.45:
[FIXED] Hardness and Stacksizes Config not being applied Serverside.
[FIXED] Book NBT of creative Books being too large.


6.00.44:
[CHANGED] The way Biome Temperature is calculated, after I noticed that the value could be negative, resulting in negative Kelvin in Taiga Biomes, what is not really logical. Warm Biomes now have 3 Kelvin less due to Water Freezing at a MC Temperature of 0.15 (what I see as 0°C), while Cold Biomes now use a whole "new" way of calculating Temperature.
[FIXED] Diamonds Achievement for the different TFC alike GT Gems.
[FIXED] Butchery Knife Color.
[ADDED] Recipes for Gem Plates and Gem Sticks.
[ADDED] Tooltips for Tool Quality, Speed and Durability to the raw Materials (Dusts, Ingots and alike).


6.00.43:
[FIXED] Well it is not my Bug, and I did not fix it, but here is what happens: Player Entities can collect DEAD ItemStacks from the ground. Seriously, those Stacks are DEAD and REMOVED from the World and still Players CAN COLLECT DAT SHIT!!! So I was forced to nullify the Stacksize inside the Stack when the Crucible or any other GT thing collects it. That way the Player still collects the Stack but he will receive NOTHING (like it should be in the first place).
[FIXED] Burning Box accepting Lava Buckets. Ofcourse Lava should not work in a BURNING Box.
[FIXED] Density of Water being lower than the Density of Air due to the result of H + H + O. No wonder the Crucible could not accept Ice.
[CHANGED] File Heads to be 1 1/2 Units rather than 2 Units. This only makes the File Head Mold more effective.
[CHANGED] Remember when I said, that Books don't magically change Text when the underlying Data Changes, because they are Books for fucks sake? Well I take that back. I noticed that those Books contain so much NBT, that my Speakers make the "Heavy LTE Data Transfer Nearby"-Noise whenever I see a written Book in any Inventory, so I decided to only save Title and Author of the Books from now on, to just look up the Title in the Clientside Book Mappings whenever someone opens the Book GUI (but only if the Book does not contain Pages, if it does, and that is the case for all the old Books, then it will display the old value).
[ADDED] Synchronisation of Maximum Stacksizes to the Client.
[ADDED]
Rightclicking the Crucible with Water or Lava Buckets to fill them.
And yes this can be used to inefficiently cool down or heat up stuff.
In case of Lava you should probably make sure to get rid of the Obsidian before molding Stuff, otherwise you will get Obsidian Tools.
Note, that Lava is only 1050K hot, so you cannot heat up things with it that much.


6.00.42:
[ADDED] Tooltip to Materials which contains the Information on what "Enchantment" it gives when being crafted into a GT Tool.
[FIXED] Stainless Steel Recipe by using Invar instead of Nickel now, so that Nichrome cannot form. The Material Ratios for making it are still the same ofcourse.
[FIXED] Liveroots Boiling Point being below the Wrought Iron Melting Point making Ironwood almost impossible to obtain.
[FIXED] Burning Boxes not creating Ashes, when being blocked while being shut down.
[CHANGED] The Saw is now faster on Planks and similar wooden things, but slower on Logs.
[CHANGED] The Cooldown Timer of Crucibles to 100 Ticks after the last HeatEnergy->Temperature Conversion happened rather than 10 Ticks. Once it starts cooling down however, it will still be 1 Kelvin every 10 Ticks. (and yes it is linear because people otherwise would have even more problems with the Maths behind this)
[CHANGED]
The way GregTech applies advanced Stone Hardness.
It is highly advised to set the Hardness Multiplier of Underground Biomes to the >>>Original Value<<< if you use this new Multiplier, otherwise GT Pickaxes will break way too fast (since durability loss goes via Hardness and not via Speed).
The higher the Multiplier the longer the Stone takes to break.
The new way does not change the Hardness, but instead directly the Speed you mine it with any Tool (even non-GT ones).
Also added this Multiplier for Ore Blocks.
This Config does, or at least should, synchronise to the Client whenever you join a Server with it.


6.00.41:
[ADDED] Creative Tabs for MultiTileEntities. This also breaks some Parts of the MultiTileEntity API in case someone uses that already.
[FIXED] Confusing Nickel (Ni) with Niobium (Nb) in two of the Alloys.
[FIXED] Some Mortar Recipes not being added for some reason.
[FIXED] Co-60 not being radioactive enough. Also prepare yourself with a Hazmat or something before washing pure Copper Ore, because that Stuff has Co-60 as byproduct.


6.00.40:
[FIXED] Missing Client Data Updates when using OpenBlocks RGB painting Method.
[FIXED] Some Alloying Recipes being X^X times more expensive than they should.


6.00.39:
[FIXED] freshly placed GT TileEntities having twice the Tickrate until they are unloaded and reloaded at least once. I only found that thanks to the TPS Sensor displaying ~40 TPS after being placed and ~20 TPS when they were loaded from the World.
[FIXED] Crucible doing the smelting Conversions multiple times in a row as soon as the melting point is reached causing things like Rubber or Plastic to evaporate, rather than having a slight loss.
[CHANGED] The Axe can now mine Leaves and similar but at an eighth of the Speed.
[ADDED] The File can now harvest vanilla Iron Bars.
[ADDED] more variety to Dungeon Loot Items. I made most Ingots into Dusts, Plates, Sticks, Gears and similar Stuff. That is what I mean with variety.
[ADDED] Support for OpenBlocks RGB painting (untested)
[ADDED] TPS Sensors for measuring the Server Ticks per Second directly. This Sensor, unlike the others which do every tick, only scans tps every 20 ticks in order to give a more precise result.
[ADDED] Player Counter Sensors to count the amount of Players on the entire Server.


6.00.38:
[IC2] Recommended Minimum IC² Version is now 720, due to fixing the huge Lag GT caused by using the older IC² Recipe System.
[ADDED] back the Recipes for 5 missing Tools.
[ADDED] back the Recycling Processing for Macerating/RockCrushing/Pulverizing.
[ADDED] back the IC² Ore Washing and T-Centrifuging Recipes.
[ADDED] Averaging Modes to all Sensors. Just click the Buttons with a Screwdriver directly to change the Range of averaging.
[ADDED] Construction Pickaxe. This Pickaxe is 2 times faster on anything that is neither an Ore nor a natural Rock and 50% slower on Ores and Rocks. (Rocks are anything an Ore can generate inside, and the vanilla obsidian Block)
[ADDED] Coinage Mold. Insert a Tiny Metal Plate, hammer it with a hard Hammer and then extract your Coin. Currently only default Coins are available through that.
[FIXED] Recipe Scanner not working properly when someone added a null-Stack to a vanilla Shaped non-OreDict Recipe, What Twilight Forest does in some of its Recipes...
[CHANGED] Coin NBT to be better. Place and break Coins to fix their NBT (if it is actually broken, if not then don't worry about that).
[CHANGED] Coins without any Shape assigned to them will have the old GT Coin Texture and no fancy 3D Rendering.
[CHANGED] Coins do not simply despawn when thrown on the ground, instead they place themselves (and if they fail doing that they will still not despawn).
[CHANGED]
Timber Functionality of the Axe.
Is now default, since it is finally fair.
Now takes the height of the Tree into account when determining the breaking Speed of the Log.
Each subsequent Log adds up a little bit more to the slowness and lost durability (1.00, 2.10, 3.30, 4.60, 6.00, 7.50, 9.10, 10.80, 12.60, ...).
In case you missed it from previous Changelogs you can hold sneak to prevent Timbering when using an Axe.


6.00.37:
[API] A Special NBT for recycling Stuff. If that NBT is attached to an Item it will recycle into whatever the NBT specifies. This will also be shown inside the F3+H Tooltip of the Item ofcourse.
[FIXED] Universal Spade having a Wooden handle even though not being crafted with one. This had only visual consequences.
[FIXED] Ambient occlusion being used on the Mold. I finally fixed that shit.
[FIXED] Timber Functionality of the Axe being too generous when the Axe almost breaks.
[CHANGED] The Configuration which sets the maximum Stacksizes to be more precise. This kills all the old Stacksize Configs, so you have to set those back if you used them. The Stacksize of "block" defines the general Stacksizes of random vanilla Stuff.
[ADDED] Electrometer for measuring electric current. (current != storage, just to clarify that)
[ADDED] Tiny Plates, which are like Plates but in Nugget Size. Will be needed for making Coins.
[ADDED] Double Axe, with +50% Durability, +100% Base Attack Damage and -50% Attack Speed.
[ADDED]
Coins. Currently only available as Dungeon Loot or via Creative.
Also even though being theoretically customizable, it would be very hard to NBT Edit each Pixel individually, so you will need to stick with the Gear Shape for now.
Coins can be placed in World as stacks of up to 256 Coins per Cubic Meter (4x4x16).
They can also be picked up by just rightclicking with an empty Hand.
Coin Stacks in World render a lot of Polygons. I removed all the overfluent Polygons, which nobody could see anyways, in order to not let them lag that much.
Added also a Clientside Config (for the GT Config which is outside the Config Folder!) to turn off the 3D of the Coins for inworld Rendering.
The Coins generated in Dungeons are made of Copper, Silver, Gold or Platinum. The Platinum ones being the rarest for some reason I am not going to tell now.


6.00.36:
[API] Improved the Alloy Recipes inside the Material Classes by making multiple combos of Materials able to shape the same Alloy.
[API] Improved the delegation of TileEntities (currently only used in the Mini Nether Portal), so that the Delegator contains World and Coords too, in case there is no TileEntity at the Destination.
[FIXED] RC Electric Feeder unit being uncraftable, because I did not add back my Wires yet.
[FIXED] IC² Cable Wirecutting Recipes.
[FIXED] Iron Achievement by adding it to the Item extraction process of the Mold.
[FIXED] A lot of old Railcraft references still being broken. I made IC² Basalt compatible with GT too while I was at it.
[FIXED] The mirrored Texture Issue of vanilla MC for all GT Blocks. I have set both fliptexture and that one obfuscated value, which happens to be only in those two affected Block Side Functions, to true, whenever it is not a full Block, in order to fix it.
[REMOVED] The Rightclick to get Temperature Function from the Crucibles and the Molds. Build a Thermometer Sensor and place it on the Crucible, or just look if the Stuff you inserted begins to melt, if you cant affort a Steel Plate, 4 Fine Red Alloy Wires, 3 Glass and a Pile of Redstone.
[CHANGED] Thanks to the complaints of axlegear, in order to use Diamonds for making Steel you will need to heat them to 4200 Kelvin before using their Carbon. So in the beginning you now have to use Graphite in order to make Steel.
[CHANGED] Thanks to even more complaints of axlegear Iron Ore processing is now much harder and requires Carbon (from Graphite) or Dark Ashes (product of burning Coal or Lignite Coal, but not Charcoal) and in some cases Calcite. Vanilla alike Iron Ore and Pyrite Ore do not require this. Dark Ashes can be used, because it has a high Carbon Content, aswell as it is not being as flammable as Coal itself.
[CHANGED] Worldgen so some of the small Ores are not pure anymore (Iron, Nickel). Other small pure Ores are now rarer and about 50% replaced by impure variants of them. This should give more variation to Ore Processing, when not finding any large Veins. And yes, it is partially axlegears fault too. Blame him. :P
[CHANGED] Molds now cool down five times faster.
[ADDED] Scrap as remains from 100% broken Tools.
[ADDED] Marble and Basalt. Finally. Because Marble was needed as a more abundant Source of Calcite than large Lapis Veins, and Basalt was uhhm, well, it was already textured since GT5 so I added it too.
[ADDED] Knives, Butchery Knives and Universal Spades back.
[ADDED] Basalt and Iridium Crucibles to progress farther than Vanadium.
[ADDED] Electric Heaters. They are compatible with IC² Electricity.
They are always active, so they either need a Splitter Cable or one of the Storage Block Redstone Modes to be shut down.
The Recipes of them are slightly temporary, but they should be about as resource expensive as the actual way.
This in combination with the Thermometer Sensors makes Fully Automatic Smelteries possible.
[ADDED]
Sensor Block. It can display the scanned Value aswell as emit Redstone based on that Value.
---
Currently done Sensors are:
Thermometer for Temperature
Gibbl-O-Meter for measuring compression (useful for the Steam Boiler)
Luminometer for measuring Light
Chronometer for measuring Time of Day (Serverside. This also means you can detect Lag with it.)
Item-O-Meter for counting Items in an Inventory
Stack-O-Meter for counting Stacks in an Inventory (anything with a stacksize > 0 is considered a Stack)
Fluid-O-Meter for measuring Fluids in Liters (Or as some would call, Millibuckets)
Bucket-O-Meter for measuring Fluids in Cubic Meters (aka Buckets or 1000 Liters)
Weight-O-Meters for measuring Inventory Weight of all Items which have a Weight assigned to them. (in either Gramm, Kilogramme, Tons or Kilotons)
---
The Display itself can be set to either Decimal or Hexadecimal by screwdrivering the displaying Part of the Sensor.
Modes are set using the Screwdriver on something that is not the Display.
It can check if a Value is greater, equal or lower than the displayed Number with Redstone.
It also has a Scale Mode where it emits Redstone on a Scale between 0 and the set Value.
The checked Values can be changed using the 6 Buttons to increment and decrement the Amount by 1, 10 or 100 (1, 16 or 256 in Hexadecimal Mode).
The Sensors do not emit Redstone into the Direction the scanned Block is at, in order to not disturb any potentially Redstone controlled things inside it.
---
The Thermometer Block also works on IC² Nuclear Reactors (including the up to 6 Chambers).
Note, that it displays between 0 and 2000 instead of 0 and 10000 on IC² Reactors, since it measures in Kelvin and not Reactor Heat Units.
A Meltdown at 2000 Kelvin seems most realistic, since it is mostly made of Steel.


6.00.35:
[FIXED] A lot of tiny not that noteworthy things.
[FIXED] Screwdrivers not being craftable from Screwdriver Tips.
[FIXED] The Collection Bounding Box of the Smelting Crucible. This means you need to aim better to hit it, also Molds aren't scrapped as often, when they are harvested while being right next to a Crucible.
[FIXED] The missing Gem Rods (what caused missing Gem Tools) and the missing Crafting Recipe for the Bismuth Chest.
[FIXED] changed Black Bronze to require 2 Electrum instead of 1 Gold and 1 Silver, and Bismuth Bronze to require 4 Brass instead of 3 Copper and 1 Zinc.
[FIXED] Crucibles and Molds made of Metal being considered Machine Blocks rather than Metal Blocks resulting in a Wrench requirement instead of a Pickaxe one. Only newly placed or replaced Crucibles and Molds will be Pickaxable, older ones will stay Wrenchable.
[CHANGE] Moved some Textures to the API, so that they are globally usable in the future. (Barometer Overlays and the Character Overlays)


6.00.34:
[FIXED] Some Alloys not being createable due to a Bug inside the Crucible causing it to require 1 unmolten Material.


6.00.33:
[API] Added a Parameter to the PrefixBlock Constructor, deprecated the old Constructor and removed the even older depricated Constructor.
[API] Added a really needed Quality Parameter to the Tool Interface. This may break some Addons.
[FIXED] Worldgen Loops due to the large Veins. Granite could still cause it if it generated more abundantly. Edit: Seems to not even be the Issue, it must be somewhere else...
[FIXED] An Issue where every flammable GT Ore Block (Small Coal Ore in particular) causes adjacent Chunks to load two ticks after placement, when checking if there is a Fire adjacent to it. THIS was the darn Worldgen Loop. Edit: No it was not, or at least not only...
[FIXED] The Obstructedness Check of Synching Ores causing the infinite Worldgen Loop. This was hopefully all of the Problem...
[FIXED] Screwdriver being uncraftable.
[FIXED] forgetting that I made the scrap Item for a Reason, when I added the Solid Block as Crucible output...
[FIXED] Lighter not being able to light a Fire inside certain GT Blocks.
[FIXED] Forgetting to take the weight of the Crucible itself into account, when distributing Heat to newly inserted Content, resulting in a lot of Temperature being voided.
[CHANGED] The Titles and Authors of GT Books are now a bit more highlighted inside their Tooltips.
[ADDED] Different Stone Hammers and a Flint Chisel. Also changed the Crucible and Mold Recipes to use Chisels instead of Files.
[ADDED] Lighters to the Dungeon Loot.
[ADDED] Crucibles and Molds of Knightmetal (like a a bit better Steel), Fiery Steel (like a much better Steel), Thaumium (like a bit better Iron), Void Metal (like a slightly worse Tungsten), Meteoric Iron (like a slightly better Iron), Meteoric Steel (like a slightly better Steel) and Dark Iron (like a slightly better Steel). That way one can progress with other Mods too. I also added Crucibles from other Metals and a currently unobtainable Carbon Crucible. Now the Crucible Tech Chain should be complete and everything should be meltable. Except Adamantium, that Stuff is too Heat Resistant.
[ADDED] The good old vanilla Tool Nerfs. I optimised the Recipe Selection Process a bit more aswell.
[ADDED] The Information that F3+H shows Material Data of ItemStacks to the second Page of the Book of Smelting.


6.00.32:
[FIXED] That stinkin' Hoe again...
[FIXED] Granite Crucibles being uncraftable thanks to the Stone Crucible.
[FIXED] The Check for the Default Fluid Temperature when adding Fluids.
[FIXED] A missing Side Check in TileEntityBase1. This caused a crash in Open Peripherals. However the Crash itself should not happen for such a tiny thing. Imagine the shitstorm happening if I would scan the Code of all other Mods for Programming mistakes, to crash upon a tiny Error being detected, because that is what they did, essentially.
[ADDED] Mold Shape for the File Head.
[ADDED] Book explaining the Smelting Crucible with all possible Mold Shapes and made it Dungeon Loot.
[ADDED] Automatic Input Mode for the Molds when using the Monkey Wrench on them properly.
[CHANGED] The way Environmental Heat applies to the Smelting Crucible, due to the almost nonexistent Heat up, when using low tier Burning Boxes.
[CHANGED] Scrap Box Drops to drop Scrapmetal instead of Dusts. This is also a nerf of Scrapboxing!
[CHANGED] Nether Brick Melting Point in order to make Steel actually possible.


6.00.31:
[FIXED] A shitload of Issues regarding Hit, Collision and Selection Boxes. MOJANG Y U NO FIX YOUR CHEST AND ANVIL COLLISION BOXES!?! I KNOW IT IS POSSIBLE!!!
[FIXED] Lang File not generating properly, because of the pure existence of Books inside the Localisation.
[FIXED] GT Books not being "Rightclick into Air" Openable.
[FIXED] Another tiny Sync Problem on the PrefixBlocks.
[FIXED] GT Dungeon Loot not being added at all.
[FIXED] Engines not connecting to RF Conduits, due to not implementing the RF Interfaces. Added a Default Implementation in TileEntityBase1 in order to not have to worry about that anymore in the future.
[DISABLED] NEI for my Recipe System, since there is no Machine using it.
[CHANGED] The 3 Crushed Ore Items are now worth 11/9 of a Material Unit per default.
[CHANGED] MOST METALS DO NOT SMELT ANYMORE IN ANY FURNACE!!! This is because I finished the Smelting System and the Molds.
[ADDED] 0.25 large Nugget to have something that is worth 1/4th of a Unit and considered an Ingot Type of thing.
[ADDED] Small Graphite Ores to the Worldgen, which are as rare as Diamond.
[ADDED]
A Functionality to my Metal Chests which enables them to generate Dungeon Loot inside once they are opened/broken the very first time.
That way they generate the Loot in an always up to Date state (too bad the World doesn't generate them :P).
Also added Debug Versions of those Chests to test Dungeon Loot without running around everywhere.
Those Debug Chests require Debug 1 to be enabled for being visible in Creative/NEI.
[ADDED]
Material Dictionaries in Book Form.
They can be crafted in survival by putting 4 Units of a Material around a writable Book, or you just grab them from creative, or collect them from Dungeons.
They randomly spawn in Blacksmith Chests, Stronghold Libraries, Stronghold Corridors, Mineshafts and regular Dungeons.
Bonus Chests can randomly spawn a very select few of those Books (with Information about starting Materials), aswell as the Book of Alloys.
If a Material Dictionary (or the Book of Alloys) goes over 50 Pages it gets a larger Book Icon and it also recycles into twice as much Paper and Aspects.
Those Books do not update their Content when I change or add things, because they are Books for crying out loud.
That is why there is a Timestamp on the Book itself with the Date and Time of the last Serverstart/reboot before its creation.
Due to them being automatically generated I disabled Localisation for them, because it would not work properly.
[ADDED]
The highly anticipated Molds.
In order to fill a Mold, place it horizontally "adjacent" to a Smelting Crucible and rightclick the Side of the top of the Mold at which the Crucible of your choice is next to.
If you want to automatically empty it Hoppers and alike have to wait until the Temperature is up to 50 Degrees higher than the Environmental Temperature. If you do it manually you will get damaged.
They can produce pretty much every Metal thing, as long as it has a simple shape. Sometimes a File is required to sharpen the Edges and things. If your Mold is a failure it will instead produce Nuggets, the amount of Nuggets depends on how many squares you chiseled out, any amount between 1 and 24 is possible (the all 25 variant is the Plate Recipe).


6.00.30:
[ADDED] Made GT Wrenches BC Compatible.
[ADDED] Tin Alloy Steam Engines, Ironwood Steam Engines and Fiery Steel Steam Engines, also removed Bismuth Steam Engines.
[FIXED] Sync Bug on PrefixBlocks. But I really do think that vanilla should be able to set the Block Clientside before my Packet arrives instead of me having to schedule sending it 1 Tick later.
[ADDED] Scrap PrefixItem, in order to have literal garbage. This Item is used for decomposing Stuff and worse than Dusts since you cannot do anything but melting them down and other recycling things. Not only that each piece of Scrap is worth as much as just a Nugget, it also only stacks to 16 meaning it is terrible to handle. This is really proof that I add Junk to the Game.
[ADDED]
Smelting Crucibles of various Materials
each with a different maximum smelting Temperature (125% of the actual melting Point of the Material used to craft it in Kelvin)
including the calculation of Environmental Temperature into the whole process. This does mean that different Biomes mean different smelting. But that would only be useful at Biome Borders since the upcoming Molds would also be affected by Biome Temperature.
the Smelting Crucible requires a Heat Source (at any Side). Right now you only have the choice to put a Burning Box below, since that thing is the only Heat Generator. I do plan to make a regulatable Electric Heater which can keep a constant Temperature.
it can also be used to mix standard Alloys, like Bronze, Invar, Electrum, Ironwood, Annealed Copper, Wrought Iron, Steels and similar. For making regular Steel that way you need pure Carbon, and that stuff is as rare as Diamonds. Literally.
there is also a Book about Alloys which I added to Blacksmith, Stronghold Library and Dungeon Loot.


6.00.29:
[ADDED] BC Gate Compatibility to the GT Energy System for Capacitors.
[ADDED] An awful lot of Configs going with the Tool Crafting Recipes.
[ADDED] Melting and Boiling Points to the F3+H Tooltips of Items.
[ADDED] Railcraft related Recipes and Vanilla Nerfs back.
[ADDED] A (disabled by default) Recipe for the Railcraft Admin Anchor using 2 Nether Stars.
[ADDED] some of the old MetaItems. The Crop Drops, Books and the Minecart Wheels in this case.
[ADDED] Raw Tool Heads, which need to be filed in order to use them. This is not going to affect the current Tool Crafting and is meant for Tool Heads created by Molds.
[ADDED] The Minecart Wheels Item is now autogenerated with the OreDict Prefix "minecartWheels".
[ADDED] Autogenerated IC² alike Item Casings and OreDicting them (and setting the originals as Default Unification Target).
[CHANGED] Any Autogenerated GT Item (not Blocks in this case) will hide itself from Creative/NEI if it is not the Unification Target.
[CHANGED] The Saw Recipe to require just 1 Stick rather than 4.
[CHANGED] PrefixItems and PrefixBlocks now always initialise themselves before MultiItems.
[CHANGED] The Changelog to be UTF-8 rather than ANSI. I hate how Windows created Text Files end up with that borken Format as default.


6.00.28:
[FIXED-AGAIN] The Coords for the Sound System.
[FIXED] Abstract Method Error caused by non-obfuscated Inventory Functions.
[ADDED] Advanced Obstruction detection Code for rightclicking on GT Blocks, and rightclicking with most GT Tools. So if I would now decide to give the soon to be upcoming Pipes a smaller Hit Box, you would still not be able to click obstructed Facings.
[ADDED] Monkey Wrench. This will be used for things like setting secondary Faces and slightly advanced Pipe interaction. It has the same combat and mining Stats a regular Wrench has.
[ADDED] A few more Crafting Recipes for OreDict Items, including the Springs which were missing.


6.00.27:
[FIXED] Freshly placed GT TileEntities not synchronising to the Clients who did not place it, because a Clientside TileEntity is created with the same stats, hiding the Issue.
[FIXED] ConcurrentModificationException when checking for synching TileEntities on newly generated Chunks (or when another Player happens to place a GT TileEntity at the wrong moment).
[FIXED] that Error in getRGBaInt retep pointed out.
[FIXED] TileEntityBase4 not working at all, due to breakBlock removing the TileEntity when it gets placed, unless IMTE_BreakBlock is implemented (what TileEntityBase5 happens to do).
[ADDED]
Miniature Nether Portal for Item, Fluid, Redstone, Comparator Signals and whatever else Transportation (whatever else meaning "every GT thing in existence").
It obeys pretty much the same Rules a normal Nether Portal has, and works ONLY between Overworld and Nether!
And I know about the possibility of abusing two adjacent Portal Connections for Overworld Item Teleportation, but it is so "hard" to achieve, that you deserve it if you can pull off that one.
The Portal has a waiting Tolerance of 100 Ticks before changing the Redstone/Comparator Signal to 0 in case the Redstone Signal doesn't get transmitted properly in that time (Lag for example).


6.00.26:
[FIXED] I am now no longer using the getDescriptionPacket Function, because it tends to send the Packet to all Players, what results in a whole lot of useless Network Lag, when many Players are close together.
[FIXED] Functionality and Tooltips of some GT Tools
[FIXED] A lot of Item NBT related things in the MultiTileEntity System.
[FIXED] ItemBlock Rendering not using the NBT of the Block in Inventory.
[ADDED] MultiTileEntities now store their individual Item Name properly, if they got renamed in an Anvil or similar.
[ADDED] Custom Names now get synched to the Client, so that GUIs, NEI Tooltips and similar things display those Names properly.
[ADDED] Made all current GT Blocks Colorable. Note: Since the Color now gets saved inside the Item NBT you have to place and break old Blocks in order to have two of them Stack together. Also technically all 16777216 Colors are possible, if you paint multiple times, the Colors will mix.


6.00.25:
[FIXED] Storage Blocks and Ores saving an empty NBT Object inside themselves instead of a null, what wastes a bit of Memory.
[FIXED] GT Renderer not being able to render GT Models.
[FIXED] RF Compat not working on some Blocks for weird Reasons. Maybe CJ is having an old RF API in RC, or something else is borked with the RF Interface Hierarchy.
[ADDED]
Steam Engines for emitting Kinetic Energy (KU, not to confuse with the kU of IC²).
They can emit up to twice the Energy displayed on the Tooltip, so there is a bit of tolerance.
BUT if they reach the x2 margin they release the Steam and shut down and therefore loose the Energy and you need to Rubber Hammer them to start them back up.
Also suitable as RF Engines with a loss of at least 50%.
They emit the used Steam (currently directly distilled water, later "used" Steam) to the Sides if possible so you can collect it for distilled water.
Note: You will only get 80% of the distilled Water back!
Currently no fancy Piston Animation on the Engine.


6.00.24: (I fixed this Version and overrode the World corrupting one with it, so please redownload)
[ADDED] PrefixBlocks now store the Item NBT of the Item used to place them. This should for example save the Data of renamed Items.
[FIXED] Old Unification Config File overwriting the new Unification Config File due to having the same Name and me forgetting to remove the old one. Incredible how no one noticed that in the whole GT6 era until now. Seems most people still wanna play GT5.
[FIXED] New Chunks and Chunks with manually placed GT Blocks not saving Properly. Thanks to yet another Bug your previously generated and placed Stuff is safe.


6.00.23:
[FIXED] Strong Boilers being crafted with Quintuple Plates rather than Dense Plates.
[FIXED] Living Entities colliding with hot or full Boilers not getting damage.
[FIXED] Explosion Sizes for the larger Boilers.
[ADDED] Function to get all the related Energy Types to ITileEntityEnergy.
[ADDED] BlockTextureFluid to make it easily possible to Render Fluid Icons in World using my Renderer.
[ADDED] Burning Particles to the Burning Box.
[ADDED] Color for the Energy Unit Tooltips, so that every Energy Unit has its own Color.
[ADDED] IMTE Interface for overriding the Item Name.
[ADDED] Simple Method for writing the Item NBT of the Main Drop inside TileEntityBase4. That Method is also overridden by TileEntityBase6 in order to store Cover Data in the Drop, therefore it has another Name if you extend that Class to make sure it is not getting overridden wrongly by accident.
[REMOVED] the useless and laggy "aSide" Parameter in the setBlockBounds and getRenderPasses Functions of my own Rendering System.
[CHANGED] TileEntityBase6 now overrides some Rendering things in a final fashion in order to later allow Covers to be rendered properly. It has identical "getTexture2", "getRenderPasses2" and "setBlockBounds2" Functions to be overridden properly.
[CHANGED] ITexture.Util is now a bit more split up in order to separate the Render Preparation (color and ambient occlusion) and the actual rendering of the Icon.


6.00.22:
[FIXED] The EnergyCompat Class can now handle RF Packets of different Sizes and all the "To-RF-Conversions" properly.
[FIXED] The Hardcoded Cape Lists now only load if their Online Cape Lists could not be loaded.
[ADDED]
The Boiler Blocks. I think I got everything done right, now.
It is simple: Don't let the Barometer reach the Red Section due to risk of Explosions, and don't let the Boiler run completely out of Water as it melts otherwise.
Currently only supports Water Buckets and Fluid Transport Mechanisms (such as Pipes or adjacent RC Water Tanks) to refill the Water. Every Boiler has a Water Capacity of 4 Buckets (4000L).
Use distilled Water if possible or you risk calcification and therefore decreased Efficiency. Every Boiler starts at 100% Efficiency amd slowly degrades with every bit of non-distilled Water getting converted to Steam. Break and replace the Boiler to clean it, but NOT while it is full of Steam!


6.00.21:
[CHANGE] Replaced all mentions of ForgeDirection inside my Code. ForgeDirection is now only used via the "CS.FORGE_DIR" and "CS.FORGE_DIR_OPPOSITE" Arrays, whenever compatibility Code is needed, because Forge Direction is laggier and also looks much more un-overviewable than a simple Array access (with well named Arrays, without the good Array Names it would be an utter mess).
[FIXED] wrong Block Name for small Atum Sand Ore.


6.00.20:
[FIXED] tTool.equals("equalsIgnoreCase") inside the Wrench Behavior... How the hell did I get that one in...
[FIXED] Forgetting to add the first 6 people to the hardcoded Gold Cape List for Patreon.
[FIXED] Left/Right-Texture-Disorder in Burning Boxes, when they face North or South (Only Texture Pack Users could have seen that).
[FIXED] Forgetting to set hasComparatorOverride to true in the MTE Blocks.
[ADDED] Ore variants for "Atum" Stone and "Atum" Sand, including Mappings for the World Generator. The Small Ores will have the Limestone Dust as secondary Drop.
[ADDED] More Energy Compat Code in order to autoconvert GT KU to RF at the lossless 1:4 Rate (but not the other way around!). However that applies only if the Kinetic Units are positive (as in pushing), the negative ones (pulling) are not converted, so with a GT Engine it would have a 50% Loss. And currently there is no GT Machine which emits KU. And RF can also NOT be used to power GT Machines! (I need to repeat that because people are stupid enough to still assume RF->GT even though I said it twice...)
[ADDED] A lot of Arrays to CS.java for handling Facings, Rotations, Sides and Stuff very efficiently and much better than ForgeDirection in most regards, especially computation Speed, since Arrays are much faster than Objects, Enums or Functions.


6.00.19: 6.00.18: 6.00.17: 6.00.16: 6.00.15:
[FIXED] Drill/Chainsaw/Wrench Head Textures to not look cut off that much.
[CHANGE] Disabled Alpha Blending per Default. Texturepack Users can still enable it if they don't have Problems with it.
[CHANGE] The look of the Capes is now better.


6.00.14:
[FIXED] In World Alpha Overlay Rendering.
[FIXED] Light Opacity Hook not working properly.
[ADDED] A second Cape List containing the Patreons with a slightly different golden GT Cape.
[ADDED] Flint and Tinder Items using other things than Steel (such as Quartz, Jasper or regular Iron).
[ADDED] A way to localise the Energy Names with both, a short name (for example "EU"), and a long name (for example "Electric Energy"), what can be useful for Tooltips.
[ADDED] Bismuth Chest. Also Bismuth, Lead, Tin, Copper and Zinc will be the only Furnace Smeltable Metals, once I do the Smeltery.
[ADDED] Tooltip with Harvest Tool and Level for the MultiTileEntity Blocks, and Localised Names for most Tool Classes.
[ADDED] The Multiplate Crafting Recipes back for obvious Reasons.
[ADDED] Plates are now crafted from Double Ingots rather than using two Ingots. This results in one Hammer Usage more per Craft Earlygame. Double Plates can be made with Triple Ingots, Triple Plates with Quad Ingots and Quad Plates with Quintuple Ingots, so those Ingots make sense too.
[ADDED]
Multiple different "Burning Boxes" and "Dense Burning Boxes" (Lead and Bismuth Versions being the most early Game variants), which convert Furnace Fuel into Heat Energy.
Their Efficiency depends on the Material used to contain the Heat (Invar being the only 100% one but on a low Rate, Tungsten 95% with a very high Rate)
The Dense ones cost about 4-5 times the Material but also have a 4 times the Output Rate, without >>ANY<< change in overall Efficiency. In order to make them you need a Dense Copper Plate, and therefore a way to compress them, meaning it is higher Tech.
At 100% one Furnace Smelting equals 5000 Heat Units, so Coal/Charcoal would be 40000 HU, Coal Coke 80000 HU and Alumentum 160000 HU for example, it also accepts Wooden things (7500 HU), Saplings (2500 HU) and all the other Furnace Fuels.
Many Fuel Materials have some kind of Ashes, which are left behind when burning them. These Ashes has to be removed regularly from the Burning Box in order for it to work. Coal Coke and Alumentum don't have those Ashes for example.
The Burning Box is very primitive, meaning it has no GUI and won't receive any GUI. Anything you insert for burning by rightclicking the front of the Box with it will get burnt without mercy (if it is a valid Fuel, and if you don't happen to wrench the Box right afterwards, and only when the Box is actually active).
In order to work, it also needs a Block without Collision Box in front of it (Air is such a Block without Collision Box).
It also emits its Energy regardless of being acceptable by the Block ontop of it or not, even though in that case it only wastes half the Energy Rate.
This Block is also a Fire Hazard, meaning when active it can randomly set flammable things around it on Fire
The Burning Box requires Flint and Tinder or similar in order to be ignited, blame retep for that.


6.00.13:
[FIXED] Lang File reading the Property Names rather than their actual Values, when enabled.
[FIXED] Hoe requiring Dirt Blocks made of Air to till.
[FIXED] TC Gold Coins being unificated to Gold Nuggets.
[FIXED] Thaumium Blocks of TC not being obtainable. They can be obtained by Chiseling the GT Block of Thaumium Ingots with the GT Chisel (just like every other Storage Block).
[NOT-REALLY-FIXED] "See-Through" Block Breaking Texture by disabling transparency as a whole for that task, it may look ugly but at least it is not causing X-Ray Textures.


6.00.12:
[API] Did some infrastructural work for Covers and their Sync Code.
[FIXED] Cassiterite not giving 0.75 Units of Tin when smolten in Furnace, and also not having 1 Units of Tin when electrolysed (the 0.75 thing is just to make smelting less lucrative compared to the Electrolyzer). Cassiterite Ore drops double amount of crushed Ore instead.
[FIXED] Forgetting to add the temporary Furnace Smelting Tag to Nickel.
[FIXED] Forgetting about the Ore Drop Multiplier for certain Ores.
[FIXED] Gem Plate Storage Blocks and Crates (because Gem Plates are different from regular Plates for Texture Reasons)
[FIXED] GT Blocks not being able to use Alpha in Textures.


6.00.11:
[ADDED] Made Railcrafts 3 Crowbars compatible with my IBlockToolable System. Oops, it was FOUR Crowbars, there seems to be a new Void Metal one.
[CHANGE] Chests now destroy about 2/3rds of their Inventory when blown up, before dropping their content.
[FIXED] Nether Star Items being hidden from NEI (due to not having assigned a proper Color).
[FIXED] forgetting to use the 2/3 Chance for Small Ores instead of Large Ores in those "Bedrock Marker Ore Top Veins".
[FIXED] Forestry Plugins Crashing when accessing Clientside Stuff Serverside. You should mention that crash to the Plugin Devs, because they access getRenderType Serverside, and I cannot do anything but returning 0 against that.
[FIXED] Added my Blocks to the Explosion Whitelist of the IC² Nuclear Explosives, so that they don't destroy Blocks with a too large Resistance.
[API] Added two new Levels to the TileEntityBase Class in order to have the same Code for Inventory Handling, MultiTileEntities and Networking.


6.00.10:
[READDED] Some of the Ore Recipes. I think Hammering Ores should work again.
[ADDED] Chisel to switch up Storage Blocks. It has a Recipe List, so one could add more Recipes for it, than just Storage Blocks.
[ADDED] Bedrock Ores, which are unminable. They are supposed to be "Under Bedrock Vein Markers" for a future Project, which is similar to the MFR Laser Drill. Note, that they are EXTREME-ly rare, but they do contain a few minable instances of their Ore above them, and they remove the upper Bedrock layers around that Area (so you have no problems accessing it).
[FIXED] Chest not auto-unificating content when getting loaded.


6.00.09:
[API] MultiTileEntity Registries now automatically have IDs. Those IDs are identical to their Item/Block IDs. This removes the need of initialising your own Network Handler just for MultiTileEntities. But this also creates the need of implementing the IMultiTileEntity Interface in order to have proper IDs being set, when they are placed.
[NOTICE] MultiTileEntities are NOT MultiBlockTileEntities.
[ADDED] Recipe for the Wrench.
[ADDED] A few Chests using the new MultiTileEntity Registry. All have 54 Slots, without exception. Fancy Materials don't justify a larger Size.
[FIXED] MultiTileEntity Registries now add their Creative Sub Items in order of their Registration, and not in order of their IDs (or in order of their HashCodes what was the actual Problem). That way OCD people don't have to care that much about MetaData IDs anymore in order to sort their Stuff right.
[FIXED] Crates breaking clientside when being rightclicked by a compatible Crowbar.


6.00.08:
[API] A new MultiTileEntity Registry. Currently WIP, but it should work properly. It IS Part of the API so you can "easily" add your own ones with all the things you need. The only thing you need to do yourself is synchronising your TileEntities to the Client (Good that I have a Network Handler API too, which you "could" use for doing that).
[FIXED] Crops again.


6.00.07:
[ADDED] the Glow in Darkness Effect for certain Materials (Glowstone, Lava, Charged Certus, TC Shards and similar).
[FIXED] useless Unification Targets being added for IC², since IC² does allow OreDict Usage by itself, unlike other Mods.


6.00.06:
[API] I changed the Package Name of the PrefixBlock Class and removed the deprecated Constructors while I was at it.
[FIXED] Washing Ores which don't have any Byproducts assigned to them causing a Crash.
[FIXED] Default Unification Targets being fucked up.


6.00.05:
[FIXED] the infinite Worldgen Loop caused by another Bugfix...


6.00.04:
[FIXED] Now catching StackOverflow Errors when exploding a MetaBlock. This is just to prevent a useless Crash.
[FIXED] Creation of empty Ores when Worldgen happens to create air below a gravity affected Ore, before the TileEntity has been created.
[FIXED] Empty MetaBlocks and MetaItems having no Localisation, when they aren't getting generated.
[FIXED] Usage of IBlockAccess Serverside. WHY ARE THOSE INTERFACE FUNCTIONS CLIENTSIDE ONLY!?! WHYYYYYYYY!?! (damn automatic Code Selector)
[FIXED] Added a Null check for the send()-Functions of the GT Network Handlers. Maybe that fixes some Bugs, maybe it doesn't. I have no Idea, since the Log didn't tell me about which Mod caused it.
[ADDED] Client Config to re-enable the Icon Overlays of Crates as you know them. Because Crates look far better when they are incognito. (Maybe I will add tiny Warning Icons, if the contents have explosive or flammable content later)
[ADDED] Made all Crates Flammable, since they are made of Wood. So you can loose Stuff when your Warehouse burns away.


6.00.03:
[DEPENDENCIES] Adjusted to the new Crop System. Requires IC²-679 or higher from now on.
[ADDED] More Crushed Ore Smelting Recipes.
[FIXED] Crates and Ore Blocks not rendering in "otherwise" empty Chunks (you would've noticed it when stacking up to the sky with a bunch of Crates).
[FIXED] Silk Touching the regular GT Ores. Small Ores are as always not Silk Touchable.
[FIXED] Forgetting to reset the Color after Rendering a Texture causing weird Door Colors for example. Even though the Door Color thing is a vanilla Bug.
[FIXED] Gunpowder and other explosive Blocks causing an additional Client Side explosion rather than only doing the Serverside one. Btw. @everyone getting the Idea to use that Feature for mining: Those explosions are flaming, meaning all Drops would be burned.
[FIXED] Improved Worldgen Code by finally finding a fix for the TileEntity Ores causing a stupid update when they get placed, resulting in infinite World Generation Loops, when those are at Chunk Borders. Now I can finally generate Small Ores with the Default "Each-Chunk-Generator" without fearing, that this shit could ever happen again (before it used the "3x3-Chunk-Generator" for avoiding those Loops, causing UB Stone Problems). This fix will also "greatly" reduce Lag from generating my Ores (at least it feels greatly).
[CHANGE] Thanks to a small change in my Sync Code, Clients no longer have empty Ore TileEntities at obstructed Locations. Now they get created on the fly as soon as the Packet arrives. Before this, they got synched on the fly, what was basically the same.


6.00.02:
[ADDED] most of the Tool and Storage Block related Crafting Recipes back.
[ADDED] The Furnace Smelting Tag to some Materials (including Ores) in order to regularly smelt them (this is temporary until I add a Smeltery)
[FIXED] ConcurrentModificationException when spawning Entities during the iteration of all Entities. (like Ore Washing in Cauldrons dropping new Items)


6.00.01:
[FIXED] Creative Tabs for each Type of Prefix are now working properly (They are on a "per MetaItem" Base).
[ADDED] Config to show hidden Items/Prefixes/Materials. Hidden Items are usually for secrets, hidden Materials are things like the Anti-Matter Materials which nobody wants to click when searching NEI, and there are currently no hidden Prefixes due to the Creative Tabs mentioned above.
[ADDED] Registry (aka HashMap) for "Stone => Ore-Block" Mappings, so that custom Ore Blocks can be generated by the GT World Generator. Also useful for Custom World Generators ofcourse.
[ADDED] Native Underground Biomes Support for GT Ores. Will be funny to see things like Lignite-Lignite-Ore and other random Stuff. NOTE: Sometimes it fails and instead places the normal Stone Variant, because UB generated the Stone after GT placed the Ores. But this happens rarely as far as I saw.
[REMOVED] the Debug 3 Config again. I just made it a constant instead of letting the user "decide" to enable GregTech, lol.


6.00.00:
[ADDED]
Universal and technically GregTech independent Energy API with all Use-Cases of the RF API,
plus the Possibility to determine what Type of Energy something is (like Electricity, kinetic Energy or even Redstone Flux),
the Usage of the DataType Long instead of Integer,
additional Data being accessible for the more complicated Energy Network implementations, such as Minimum, Recommended and Maximum Size of Packets to be transmitted into or out of a Block,
the Functions being named in a way so that the second word after "get", "is", "do", "insert" or "extract" is always "Energy" and then being followed by the rest of the Name in a Table alike fashion,
and a simple Compat Class to insert Energy of the Electric Type into IC2 Machines, and Energy of the Redstone Flux Type into RF Acceptors,
also containing an IEnergyItem Interface with really everything one could ask for, such as Coordinates or the Inventory the Item is contained in.
[ADDED]
Universal and technically GregTech independent Network Handler,
where only the sent/received Packets have to be defined. Everything else is done by the Network Handler itself.
Also including optimised Packet implementations for transmitting Coordinates without wasting Bandwidth,
and a default Sound Packet for sending Sounds to the Client.
[ADDED]
Universal and technically GregTech independent OreDictionary Manager,
with the ability to silently (much unlike the very loud regular GT OreDict Manager) Filter out and distribute OreDictionary Events to their respective Targets (such as OreDictPrefixes),
to automatically re-register OreDictionary Names (for OreDict Lists for example) by just adding Strings to a List,
and to find unknown OreDictionary Materials and add them silently to a List (which GregTech would access to put them into the Log).
[ADDED]
Versions of OrePrefixes and Materials, which are no longer Enum dependent,
with shorter class Names for the default OrePrefix and Material Lists (MT and OP),
better HashCode uniqueness by making hashcode independant from IDs,
and also Utility for shorter Code ("OM.stack()" is much shorter than "new OreDictMaterialStack()"),
including a List of all Chemical Elements and their Anti-Elements from Hydrogen to Triennennium,
pretagged with things like METAL, ALKALI_METAL, ALKALINE_EARTH_METAL, LANTHANIDE, ACTINIDE, TRANSITION_METAL, POST_TRANSITION_METAL, METALLOID, NONMETAL, POLYATOMIC_NONMETAL, DIATOMIC_NONMETAL, NOBLE_GAS or ANTIMATTER
complete with ALL Melting and Boiling Points of said Materials (which are known to Wikipedia, and without the decimals),
some Isotopes of them like Deuterium, Tritium, Carbon-13, Carbon-14 or Lithium-6,
and also some aliases like "Aluminium"<=>"Aluminum", "Wolframium"<=>"Tungsten" or "Farnsium"<=>"Ununseptium" and more (and it is possible to easily create an alias to rename a Material).
[ADDED]
Universal and technically GregTech independent Tool Rightclick Usage Interface for the Block Class with all Parameters one will need,
including Tool Name, Approximate Tool Durability, The Coordinates of the Block with the Side clicked (to get the TileEntity if needed) and much more,
returning the Durability lost by the Rightclick on the Block, so that anyone can use his own Tool Damage System,
also including a Compatibility Class for vanilla Blocks and some other Mods Interfaces, so that one doesn't have to implement the special cases for those over and over again.
[ADDED]
Interfaces for delegating whole TileEntities into different positions. This is very useful for extending the Sides of a Block or for Tesseracts, if the accessing "Third Party Mod" decides to use the Interface. All GT-API based TileEntities automatically access that Interface when getting TileEntities.
Interfaces for giving "stupid" Blocks the ability to return a TileEntity, in case you have your own TileEntitiy Registry. "UT.Worlds.getTileEntity" automatically uses that Function of the Blocks if no regular TileEntity has been found.
[ADDED]
MetaItems which everyone can create by just calling a Constructor once (Same goes for MetaBlocks too).
[ADDED]
I now differenciate between the Prefixes for "blockIngot", "blockGem", "blockPlate" and "blockDust", because people fucked with that Prefix way too much.
I re-registered some of the "block"-Prefix based Items to fit their proper new Prefix in order to keep at least some compatibility with the old Prefix.
I also added "blockSolid" for a 100% Cast Metal Block. Even though some "blockIngot"s look like they have been cast in vanilla, I see the difference in crafting.
With all those new Storage Blocks I don't need the Config for Storage Block Recipes anymore, since EVERYTHING is now available in some Storage Block Form or the other.
[ADDED]
Improved Worldgen with Ores for almost every kind of vanilla Stone/Sand.
[FIXED]
Memory Leaks from GT Fluid Stacks caused by the List of all Fluid Stacks being filled with every instance of the GT Fluid Stacks even after the Server Started (since those Fluid Stacks are just for Recipes)
[FIXED]
Railcraft Names for Items being outdated causing some Recipes to be removed.


1.7.10 but savegame incompatible with GT5 and it will stay savegame incompatible forever, because of huge changes making it impossible


Well you made it all the way down here. Here have a reward, this is the Link to the Secret Testing Version https://gregtech.mechaenetia.com/secretdownloads/ but be warned, it can break a lot of shit. Note to Wiki Editors: Please don't redistribute this Link or any hint of this Link existing here, it might be dangerous for stupid people and I don't want them to hurt themselves.
