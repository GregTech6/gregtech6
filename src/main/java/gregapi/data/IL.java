/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.data;

import static gregapi.data.CS.*;

import gregapi.api.Abstract_Mod;
import gregapi.code.IItemContainer;
import gregapi.code.TagData;
import gregapi.item.IItemEnergy;
import gregapi.oredict.OreDictItemData;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 * 
 * Class containing all non-OreDict Items of GregTech.
 */
public enum IL implements IItemContainer {
	Display_Fluid,    // <-- Display Fluid for Tank Slots.
	Circuit_Selector, // <-- this is the Circuit that has the adjustable Numbers on it, renamed to Selector Tag later on.
	Empty_Slot,       // <-- Marker for Slots to be left Empty, used in the Anvil.
	
	TE_Slag, TE_Slag_Rich, TE_Cinnabar,
	TE_Phyto_Gro, TE_Phyto_Gro_Rich,
	TE_Rod_Blizz, TE_Rod_Blitz, TE_Rod_Basalz,
	TE_Rockwool, TE_ObsidiGlass, TE_LumiumGlass,
	FR_Planks_Fireproof, FR_Planks,
	FR_Slabs_Fireproof, FR_Slabs,
	FR_Logs_Fireproof, FR_Logs,
	FR_Ice_Shard,
	FR_Iodine_Capsule, FR_Dissipation_Capsule,
	FR_Propolis, FR_Propolis_Silky, FR_Propolis_Sticky, FR_Propolis_Pulsating,
	FR_Pulsating_Dust, FR_Pulsating_Mesh,
	FR_Silk, FR_Silk_Woven,
	FR_Candle,
	FR_Mulch, FR_Fertilizer, FR_Compost,
	FR_HoneyPot, FR_Ambrosia, FR_TinCapsule, FR_WaxCapsule, FR_RefractoryCapsule, FR_MagicCapsule,
	FR_Water_Can, FR_Water_Capsule, FR_Water_RefractoryCapsule, FR_Lava_RefractoryCapsule, FR_Honey_Bucket, FR_Honey_Can, FR_Honey_Capsule, FR_Honey_RefractoryCapsule,
	FR_Stick,
	FR_Scoop,
	FR_Casing_Impregnated, FR_Casing_Sturdy, FR_Casing_Hardened,
	FR_Bee_Drone, FR_Bee_Princess, FR_Bee_Queen, FR_Tree_Sapling, FR_Butterfly, FR_Larvae, FR_Serum, FR_Caterpillar, FR_PollenFertile,
	FR_Royal_Jelly,
	FR_Phosphor,
	FR_Comb_Honey, FR_Comb_Cocoa, FR_Comb_Simmering, FR_Comb_Stringy, FR_Comb_Frozen, FR_Comb_Dripping, FR_Comb_Silky, FR_Comb_Parched, FR_Comb_Mysterious, FR_Comb_Irradiated, FR_Comb_Powdery, FR_Comb_Reddened, FR_Comb_Darkened, FR_Comb_Omega, FR_Comb_Wheaten, FR_Comb_Mossy, FR_Comb_Mellow,
	FR_Pollen_Cluster, FR_Pollen_Cluster_Crystalline,
	FR_Chipset_Tin, FR_Chipset_Bronze, FR_Chipset_Iron, FR_Chipset_Gold,
	FR_ElectronTube_Copper, FR_ElectronTube_Tin, FR_ElectronTube_Bronze, FR_ElectronTube_Iron, FR_ElectronTube_Gold, FR_ElectronTube_Diamond, FR_ElectronTube_Obsidian, FR_ElectronTube_Blaze, FR_ElectronTube_Rubber, FR_ElectronTube_Emerald, FR_ElectronTube_Apatite, FR_ElectronTube_Lapis, FR_ElectronTube_Ender,
	FRMB_Propolis_Unstable, FRMB_Propolis_Breezey, FRMB_Propolis_Burning, FRMB_Propolis_Flowing, FRMB_Propolis_Stony, FRMB_Propolis_Ordered, FRMB_Propolis_Chaotic,
	BINNIE_Comb_Barren, BINNIE_Comb_Rotten, BINNIE_Comb_Bone, BINNIE_Comb_Oil, BINNIE_Comb_Fossil, BINNIE_Comb_Petroleum, BINNIE_Comb_Damp, BINNIE_Comb_Milk, BINNIE_Comb_Fruit, BINNIE_Comb_Seed, BINNIE_Comb_Alcohol, BINNIE_Comb_Rock, BINNIE_Comb_Energetic, BINNIE_Comb_Amber, BINNIE_Comb_Static, BINNIE_Comb_Iron, BINNIE_Comb_Gold, BINNIE_Comb_Copper, BINNIE_Comb_Tin, BINNIE_Comb_Silver, BINNIE_Comb_Bronze, BINNIE_Comb_Uranium, BINNIE_Comb_Clay, BINNIE_Comb_Old, BINNIE_Comb_Fungal, BINNIE_Comb_Tar, BINNIE_Comb_Latex, BINNIE_Comb_Brimstone, BINNIE_Comb_Venom, BINNIE_Comb_Mucous, BINNIE_Comb_Blaze, BINNIE_Comb_Coffee, BINNIE_Comb_Glacial, BINNIE_Comb_Mint, BINNIE_Comb_Citrus, BINNIE_Comb_Peat, BINNIE_Comb_Shadow, BINNIE_Comb_Lead, BINNIE_Comb_Brass, BINNIE_Comb_Electrum, BINNIE_Comb_Zinc, BINNIE_Comb_Titanium, BINNIE_Comb_Tungsten, BINNIE_Comb_Steel, BINNIE_Comb_Iridium, BINNIE_Comb_Platinum, BINNIE_Comb_Lapis, BINNIE_Comb_Sodalite, BINNIE_Comb_Pyrite, BINNIE_Comb_Bauxite, BINNIE_Comb_Cinnabar, BINNIE_Comb_Sphalerite, BINNIE_Comb_Emerald, BINNIE_Comb_Ruby, BINNIE_Comb_Sapphire, BINNIE_Comb_Olivine, BINNIE_Comb_Diamond, BINNIE_Comb_Red, BINNIE_Comb_Yellow, BINNIE_Comb_Blue, BINNIE_Comb_Green, BINNIE_Comb_Black, BINNIE_Comb_White, BINNIE_Comb_Brown, BINNIE_Comb_Orange, BINNIE_Comb_Cyan, BINNIE_Comb_Purple, BINNIE_Comb_Gray, BINNIE_Comb_LightBlue, BINNIE_Comb_Pink, BINNIE_Comb_Lime, BINNIE_Comb_Magenta, BINNIE_Comb_LightGray, BINNIE_Comb_Nickel, BINNIE_Comb_Invar, BINNIE_Comb_Glowing, BINNIE_Comb_Unstable, BINNIE_Comb_Pulp, BINNIE_Comb_Mulch, BINNIE_Comb_Decomposed, BINNIE_Comb_Dusty, BINNIE_Comb_Certus, BINNIE_Comb_Shimmering, BINNIE_Comb_Yellorium, BINNIE_Comb_Cyanite, BINNIE_Comb_Blutonium, 
	BINNIE_Propolis_Water, BINNIE_Propolis_Oil, BINNIE_Propolis_Petroleum, BINNIE_Propolis_Milk, BINNIE_Propolis_Fruit, BINNIE_Propolis_Seed, BINNIE_Propolis_Alcohol, BINNIE_Propolis_Creosote, BINNIE_Propolis_Glacial, BINNIE_Propolis_Peat,
	BINNIE_Dye_Red, BINNIE_Dye_Yellow, BINNIE_Dye_Blue, BINNIE_Dye_Green, BINNIE_Dye_White, BINNIE_Dye_Black, BINNIE_Dye_Brown,
	BoP_Dye_Black, BoP_Dye_Blue, BoP_Dye_Brown, BoP_Dye_White, BoP_Dye_Green,
	BoP_Bone_Small, BoP_Bone_Medium, BoP_Bone_Large, BoP_Flesh, BoP_Flesh_Block, BoP_Celestial, BoP_Celestial_Block, BoP_Comb, BoP_Ashstone, BoP_Ashes, BoP_Ashes_Block, BoP_Quicksand, BoP_Mud, BoP_Mud_Ball, BoP_Mud_Brick, BoP_Mud_Bricks, BoP_Hard_Ice, BoP_GhastlySoul, BoP_PixieDust, BoP_Ichor, BoP_Pinecone, BoP_Berry, BoP_ShroomPowder, BoP_WildCarrots, BoP_Peach, BoP_Persimmon, BoP_HoneyComb, BoP_Ambrosia, BoP_Turnip, BoP_Turnip_Seeds, BoP_Pear, BoP_Jar_Empty, BoP_Jar_Honey, BoP_Jar_Poison, BoP_Jar_Pixie, BoP_Bamboo,
	BoP_Limestone, BoP_Limestone_Polished, BoP_Siltstone, BoP_Siltstone_Polished, BoP_Shale, BoP_Shale_Polished, BoP_Crag_Rock,
	BoP_Dirt_Dried, BoP_Dirt_Hard, BoP_Sand_Hard, BoP_Grass_Origin, BoP_Grass_Long, BoP_Grass_Netherrack, BoP_Grass_Endstone, BoP_Grass_Smoldering, BoP_Grass_Loamy, BoP_Grass_Sandy, BoP_Grass_Silty, BoP_Dirt_Loamy, BoP_Dirt_Sandy, BoP_Dirt_Silty, BoP_Coarse_Loamy, BoP_Coarse_Sandy, BoP_Coarse_Silty,
	EBXL_Dye_Black, EBXL_Dye_Blue, EBXL_Dye_Brown, EBXL_Dye_White,
	EBXL_Cactus_Paste,
	EB_Dirt_Alfisol, EB_Dirt_Andisol, EB_Dirt_Gelisol, EB_Dirt_Histosol, EB_Dirt_Inceptisol, EB_Dirt_Mollisol, EB_Dirt_Oxisol,
	EB_Grass_Alfisol, EB_Grass_Andisol, EB_Grass_Gelisol, EB_Grass_Histosol, EB_Grass_Inceptisol, EB_Grass_Mollisol, EB_Grass_Oxisol,
	ENVM_Spoiled_Milk_Bucket, ENVM_Rotten_Food, ENVM_Bottle_Water_Dirty, ENVM_Bottle_Water_Cold, ENVM_Bottle_Water_Salty,
	NePl_Torch, NePl_Ancient_Debris, NePl_Obsidian, NePl_SoulSoil, NePl_Quartz_Bricks,
	NePl_Blackstone, NePl_Blackstone_Bricks, NePl_Blackstone_Chiseled, NePl_Blackstone_Cracked, NePl_Blackstone_Polished, NePl_Basalt, NePl_Basalt_Polished,
	NeLi_Blackstone, NeLi_Blackstone_Bricks, NeLi_Blackstone_Chiseled, NeLi_Blackstone_Cracked, NeLi_Blackstone_Polished, NeLi_Basalt, NeLi_Basalt_Polished,
	NeLi_Crystal_White, NeLi_Crystal_Blue, NeLi_Crystal_Green, NeLi_Crystal_Yellow, NeLi_Crystal_Magenta,
	NeLi_Torch_Soul, NeLi_Torch_Fox, NeLi_Torch_Shadow, NeLi_Fire_Soul, NeLi_Fire_Shadow, NeLi_Fire_Fox, NeLi_Obsidian, NeLi_Blackstone_Crying, NeLi_Bowl_DevilishMaize, NeLi_Bowl_DevilishPopcorn, NeLi_Bowl_CrimsonStew, NeLi_Bowl_WarpedStew, NeLi_Bowl_FoxfireStew, NeLi_Bottle_Hellderberryjuice, NeLi_Bucket_Spectral_Dew, NeLi_ShroomLight, NeLi_Gloomstone, NeLi_Reed, NeLi_Wither_Rose, NeLi_Foxfire_Lily, NeLi_Foxfire_Powder, NeLi_Wart_Crimson, NeLi_Wart_Warped, NeLi_Wart_Soggy, NeLi_Bread, NeLi_Cookie, NeLi_SoulSoil, NeLi_SoulFarm, NeLi_Gravel, NeLi_Magmatic_Netherrack, NeLi_Magmatic_Blackstone, NeLi_Strider_Flank_Raw, NeLi_Strider_Flank_Cooked,
	NeLi_Quartz_Bricks, NeLi_Quartz_Smooth, NeLi_Quartz_Chiseled_Pillar, NeLi_Void_Block, NeLi_Void_Bricks, NeLi_Void_Smooth, NeLi_Void_Chiseled, NeLi_Void_Pillar, NeLi_Void_Chiseled_Pillar,
	NeLi_Stem_Crimson, NeLi_Stem_Warped, NeLi_Stem_FoxFire, NeLi_Beam1_Crimson, NeLi_Beam1_Warped, NeLi_Beam1_FoxFire, NeLi_Hyphae_Crimson, NeLi_Hyphae_Warped, NeLi_Hyphae_FoxFire, NeLi_Beam2_Crimson, NeLi_Beam2_Warped, NeLi_Beam2_FoxFire,
	EtFu_Chorus_Fruit, EtFu_Chorus_Popped, EtFu_Rabbit_Foot, EtFu_Beet_Seeds, EtFu_Dragon_Breath, EtFu_Lingering_Potion, EtFu_Path, EtFu_Dirt, EtFu_Gravel, EtFu_Sandstone, EtFu_Obsidian,
	EtFu_Granite, EtFu_Diorite, EtFu_Andesite, EtFu_Granite_Smooth, EtFu_Diorite_Smooth, EtFu_Andesite_Smooth,
	GaSu_Granite, GaSu_Diorite, GaSu_Andesite, GaSu_Granite_Smooth, GaSu_Diorite_Smooth, GaSu_Andesite_Smooth, GaSu_Basalt, GaSu_Basalt_Smooth, GaSu_Beet_Seeds,
	CHSL_Granite, CHSL_Diorite, CHSL_Andesite, CHSL_Granite_Smooth, CHSL_Diorite_Smooth, CHSL_Andesite_Smooth,
	CHSL_Present,
	TG_Ore_Cluster_1, TG_Ore_Cluster_2, TG_Spawner_Zombie, TG_Spawner_Bug,
	MFR_Fertilizer,
	MFR_Log_Rubber, MFR_Leaves_Rubber, MFR_Leaves_Rubber_Dry, MFR_Sapling_Rubber, MFR_Sapling_Rubber_Sacred, MFR_Sapling_Rubber_Mega, MFR_Sapling_Rubber_Sacred_Mega,
	RoC_Propolis_Slippery,
	RoC_Comb_Slippery,
	RoC_Ethanol_Extract, RoC_Ethanol_Crystal,
	JABBA_Dolly, JABBA_Dolly_Diamond,
	LOOTBAGS_Bag_0, LOOTBAGS_Bag_1, LOOTBAGS_Bag_2, LOOTBAGS_Bag_3, LOOTBAGS_Bag_4,
	Myst_Desk_Block, Myst_Desk_Item, Myst_Bookstand, Myst_Lectern, Myst_Crystal, Myst_Receptacle, Myst_Ink_Mixer, Myst_Book_Binder, Myst_Ink_Vial,
	ARS_Cerublossom, ARS_DesertNova,
	BOTA_Paintslinger,
	BOTA_Ender_Air_Bottle, BOTA_Mana_String, BOTA_Red_String,
	BOTA_Livingrock, BOTA_Granite, BOTA_Diorite, BOTA_Andesite, BOTA_Basalt, BOTA_Granite_Smooth, BOTA_Diorite_Smooth, BOTA_Andesite_Smooth, BOTA_Basalt_Smooth, BOTA_Granite_Bricks, BOTA_Diorite_Bricks, BOTA_Andesite_Bricks, BOTA_Basalt_Bricks, BOTA_Granite_Chiseled, BOTA_Diorite_Chiseled, BOTA_Andesite_Chiseled, BOTA_Basalt_Chiseled, BOTA_Prismarine, BOTA_Prismarine_Bricks, BOTA_Prismarine_Dark,
	ALF_LivingCobble, ALF_DreamWood, ALF_DreamSapling, ALF_DreamLeaves, ALF_Ice, ALF_Gateway_Core,
	TC_Cinderpearl, TC_Shimmerleaf, TC_Vishroom,
	TC_Greatwood_Log, TC_Greatwood_Planks, TC_Silverwood_Log, TC_Silverwood_Planks,
	TC_Nugget_Chicken, TC_Nugget_Beef, TC_Nugget_Pork, TC_Nugget_Fish, TC_Triple_Meat_Treat,
	TC_Warded_Glass, TC_Block_Air,
	TC_Thaumometer,
	TC_Thaumonomicon, TC_Crimson_Rites, TC_Knowledge_Fragment,
	TC_Block_Flesh, TC_Block_Tallow, TC_Block_Amber, TC_Block_Amber_Bricks,
	TC_Nitor, TC_Alumentum, TC_Tallow, TC_Phial,
	TC_Bucket_Death, TC_Bucket_Pure,
	TF_Roots, TF_Liveroots,
	TF_LiveRoot, TF_Torchberries, TF_NagaScale, TF_BorerEssence, TF_Carminite, TF_Mushgloom, TF_Tall_Grass, TF_Fiddlehead, TF_Dry_Bush,
	TF_Vial_FieryBlood, TF_Vial_FieryTears, TF_Transformation_Powder,
	TF_Hydrachop_Raw, TF_Meef_Raw, TF_Meef_Cooked, TF_Venison_Raw, TF_Venison_Cooked,
	TF_Log_Oak, TF_Log_Canopy, TF_Log_Mangrove, TF_Log_Darkwood, TF_Log_Time, TF_Log_Trans, TF_Log_Mine, TF_Log_Sorting, TF_Core_Time, TF_Core_Trans, TF_Core_Mine, TF_Core_Sorting,
	TF_Nagastone, TF_Deadrock, TF_Deadrock_Cracked, TF_Deadrock_Weathered, TF_Trollsteinn, TF_Mazestone, TF_Mazehedge,
	TF_Giant_Cobble, TF_Giant_Obsidian, TF_Giant_Log, TF_Giant_Leaves,
	TF_Uncrafting, TF_Pick_Giant, TF_Sword_Giant, TF_Lamp_of_Cinders, TF_Cube_of_Annihilation,
	TF_Trophy, TF_Trophy_Naga, TF_Trophy_Lich, TF_Trophy_Hydra, TF_Trophy_Urghast, TF_Trophy_Snowqueen,
	HiL_Ironwood,
	HaC_Log_Cinnamon, HaC_Log_Maple, HaC_Log_Paperbark,
	HaC_Cinnamon, HaC_Royal_Jelly,
	ABYSSAL_Crate, ABYSSAL_Lava,
	ATUM_Scarab, ATUM_Limestone, ATUM_Limecobble,
	RH_Sand_Magnetite, RH_Sand_Olivine, RH_Sand_Coral, RH_Sand_Gypsum,
	TROPIC_Chest, TROPIC_Bamboo, TROPIC_Stick, TROPIC_Log_Palm, TROPIC_Log_Mahogany, TROPIC_Sand_Coral, TROPIC_Sand_Foamy, TROPIC_Sand_Black, TROPIC_Sand_Mineral, TROPIC_Sand_Pure,
	TROPIC_Sapling_Palm, TROPIC_Sapling_Mahogany, TROPIC_Sapling_Grapefruit, TROPIC_Sapling_Lemon, TROPIC_Sapling_Lime, TROPIC_Sapling_Orange,
	TROPIC_Leaves_Palm, TROPIC_Leaves_Mahogany, TROPIC_Leaves_Kapok, TROPIC_Leaves_Grapefruit, TROPIC_Leaves_Lemon, TROPIC_Leaves_Lime, TROPIC_Leaves_Orange, TROPIC_Leaves_Fruit,
	CANDY_Comb, CANDY_Chest,
	CANDY_Log, CANDY_Log_Dark, CANDY_Log_Light,
	CANDY_Plank, CANDY_Plank_Dark, CANDY_Plank_Light,
	CANDY_Sapling_Chocolate, CANDY_Sapling_Caramel, CANDY_Sapling_White, CANDY_Sapling_Cherry,
	CANDY_Leaves_Chocolate, CANDY_Leaves_Caramel, CANDY_Leaves_White, CANDY_Leaves_Cherry,
	BTL_Swamp_Talisman, BTL_Tainted_Potion, BTL_Chest,
	BTL_Bedrock, BTL_Betweenstone, BTL_Pitstone, BTL_Weedwood_Log, BTL_Weedwood_Beam, BTL_Weedwood_Planks, BTL_Weedwood_Bark, BTL_Weedwood_Sapling, BTL_Weedwood_Leaves, BTL_Weedwood_RottenBark, BTL_Portal_Bark,
	BTL_Bark, BTL_Dry_Bark, BTL_Resin, BTL_Rubber, BTL_Skin, BTL_Tar,
	ERE_Mud_Brick, ERE_Spray_Repellant, ERE_Herbicide, ERE_Compost, ERE_Bamboo, ERE_Gaean_Gem, ERE_Gaean_Staff, ERE_Umberstone, ERE_Umbercobble, ERE_White_Planks, ERE_White_Slab, ERE_White_Stairs, ERE_Crate, ERE_Pole, ERE_Ladder, ERE_Pot, ERE_Pot_Raw, ERE_Pot_Cooked,
	ERE_Bambucket_Empty, ERE_Bambucket_Water, ERE_Bambucket_Milk, ERE_Bambucket_Honey, ERE_Bambucket_AntiVenom, ERE_Bambucket_FormicAcid, ERE_Bambucket_BeetleJuice,
	AETHER_Skyroot_Planks, AETHER_Skyroot_Log, AETHER_Skyroot_Log_Gold, AETHER_Skyroot_Log_Small, AETHER_Torch_Ambrosium, AETHER_Bowl, AETHER_Apple, AETHER_Tall_Grass, AETHER_Sand, AETHER_Glass, AETHER_Glass_Pane, AETHER_Dirt, AETHER_Grass, AETHER_Grass_Enchanted, AETHER_Grass_Enchanted_Vanilla, AETHER_Chest,
	AETHER_Skyroot_Sapling_Gold, AETHER_Skyroot_Sapling_Green, AETHER_Skyroot_Sapling_Blue, AETHER_Skyroot_Sapling_Dark, AETHER_Skyroot_Sapling_Purple,
	AETHER_Skyroot_Leaves_Gold, AETHER_Skyroot_Leaves_Green, AETHER_Skyroot_Leaves_Blue, AETHER_Skyroot_Leaves_Dark, AETHER_Skyroot_Leaves_Purple, AETHER_Skyroot_Leaves_Apple,
	AETHER_Bucket_Empty, AETHER_Bucket_Water, AETHER_Bucket_Milk, AETHER_Bucket_Poison,
	GrC_Honey_Jar, GrC_Honey_Bucket, GrC_Honey_Bottle, GrC_Milk_Bucket, GrC_Milk_Bottle,
	GrC_Applecore, GrC_Grape_Purple, GrC_Grape_Green, GrC_Grape_Red,
	GrC_Cheese_Cheddar, GrC_Cheese_Gorgonzola, GrC_Cheese_Swiss, GrC_Cheese_Appenzeller, GrC_Cheese_Asiago, GrC_Cheese_Parmesan, GrC_Cheese_Monterey, GrC_Cheese_Ricotta,
	GrC_Ice_Cream, GrC_Ice_Cream_Chocolate, GrC_Ice_Cream_Grape, GrC_Ice_Cream_Apple, GrC_Ice_Cream_Honey, GrC_Ice_Cream_Melon,
	GrC_Starter_Culture,
	GrC_Bamboo, GrC_Bamboo_Charcoal,
	GrC_Paddy,
	MaCu_Dye_White, MaCu_Dye_Blue, MaCu_Dye_Green, MaCu_Dye_Yellow, MaCu_Dye_Red, MaCu_Dye_Brown, MaCu_Bait_Worm, MaCu_Bait_Ant, MaCu_Bait_Maggot, MaCu_Bait_Grasshopper, MaCu_Bait_Bee, MaCu_Polished_Planks, MaCu_Polished_Logs,
	MoCr_Crab_Raw, MoCr_Crab_Cooked, MoCr_Turkey_Raw, MoCr_Turkey_Cooked, MoCr_Rat_Raw, MoCr_Rat_Cooked, MoCr_Ostrich_Raw, MoCr_Ostrich_Cooked, MoCr_Turtle_Raw,
	AA_Dye_Black, AA_Fertilizer, AA_Dough_Rice, AA_Bread_Rice, AA_XP,
	RC_ShuntingWire, RC_ShuntingWireFrame,
	RC_Rail_Reinforced, RC_Rail_Electric, RC_Rail_Standard, RC_Rail_Wooden, RC_Rail_Adv, RC_Rail_HS,
	RC_Tie_Wood, RC_Tie_Stone, RC_Bed_Wood, RC_Bed_Stone,
	RC_Rebar, RC_Firestone_Cut,
	RC_Post_Metal, RC_Creosote_Wood, RC_Crushed_Obsidian, RC_Concrete, RC_Stone_Abyssal, RC_Stone_Quarried,
	RC_Crowbar_Iron, RC_Crowbar_Steel, RC_Crowbar_Thaumium, RC_Crowbar_Voidmetal,
	RC_Creosote_Bottle, RC_Creosote_Bucket, RC_Creosote_Cell, RC_Creosote_Can, RC_Creosote_Capsule, RC_Creosote_RefractoryCapsule,
	IE_Creosote_Bottle, IE_Creosote_Bucket, IE_Hammer, IE_Slag, IE_Blueprint_Projectiles_Common, IE_Blueprint_Projectiles_Specialized, IE_Blueprint_Projectiles_Electrodes, IE_Crate, IE_Treated_Planks, IE_Treated_Slab, IE_Treated_Stairs,
	FZ_Sludge,
	HBM_Mercury_Bottle, HBM_Mercury_Drop,
	ICBM_Concrete,
	IC2_Debug,
	IC2_Fertilizer, IC2_Grin_Powder, IC2_Spray_WeedEx,
	IC2_ITNT,
	IC2_Scaffold, IC2_Scaffold_Iron,
	IC2_Foam, IC2_Foam_Reinforced, IC2_Wall, IC2_Wall_Reinforced, IC2_Glass_Reinforced,
	IC2_Scrap, IC2_Scrapbox,
	IC2_Mixed_Metal_Ingot, IC2_Advanced_Alloy, IC2_Iridium_Alloy,
	IC2_Log_Rubber, IC2_Leaves_Rubber, IC2_Sapling_Rubber,
	IC2_Resin, IC2_Plantball, IC2_Biochaff,
	IC2_Crop_Seeds,
	IC2_Coal_Ball, IC2_Compressed_Coal_Ball, IC2_Compressed_Coal_Chunk,
	IC2_Carbon_Fiber, IC2_Carbon_Mesh, IC2_Carbon_Plate,
	IC2_Fuel_Rod_Empty,
	IC2_Food_Can_Empty, IC2_Food_Can_Filled, IC2_Food_Can_Spoiled, IC2_Food_Can_Poisonous, IC2_Food_Can_Salmonella,
	IC2_ShaftIron, IC2_ShaftSteel,
	IC2_Machine, IC2_Machine_Adv, IC2_Generator,
	IC2_Industrial_Diamond,
	IC2_Energium_Dust,
	IC2_ForgeHammer, IC2_WireCutter,
	IC2_SuBattery, IC2_ReBattery, IC2_AdvBattery, IC2_EnergyCrystal, IC2_LapotronCrystal,
	IC2_Iridium_Ore, IC2_Iridium_Shard,
	GC_Infinite_Oxygen, GC_Infinite_Battery,
	GC_Torch_Glowstone, GC_Canister, GC_OxyTank_1, GC_OxyTank_2, GC_OxyTank_3, GC_OxyTank_4, GC_OxyTank_5, GC_OxyTank_6, GC_OxyTank_7, GC_OxyTank_Env,
	GC_Schematic_1, GC_Schematic_2, GC_Schematic_3,
	TFC_Torch, TFC_Stick,
	Torch, Stick,
	Arrow_Head_Glass_Empty, Arrow_Head_Glass_Poison, Arrow_Head_Glass_Poison_Long, Arrow_Head_Glass_Poison_Strong, Arrow_Head_Glass_Slowness, Arrow_Head_Glass_Slowness_Long, Arrow_Head_Glass_Weakness, Arrow_Head_Glass_Weakness_Long, Arrow_Head_Glass_Holy_Water,
	Arrow_Wooden_Glass_Empty, Arrow_Wooden_Glass_Poison, Arrow_Wooden_Glass_Poison_Long, Arrow_Wooden_Glass_Poison_Strong, Arrow_Wooden_Glass_Slowness, Arrow_Wooden_Glass_Slowness_Long, Arrow_Wooden_Glass_Weakness, Arrow_Wooden_Glass_Weakness_Long, Arrow_Wooden_Glass_Holy_Water,
	Arrow_Plastic_Glass_Empty, Arrow_Plastic_Glass_Poison, Arrow_Plastic_Glass_Poison_Long, Arrow_Plastic_Glass_Poison_Strong, Arrow_Plastic_Glass_Slowness, Arrow_Plastic_Glass_Slowness_Long, Arrow_Plastic_Glass_Weakness, Arrow_Plastic_Glass_Weakness_Long, Arrow_Plastic_Glass_Holy_Water,
	Shape_Mold_Empty, Shape_Mold_Credit, Shape_Mold_Cylinder, Shape_Mold_Name,
	Shape_Press_Bullet_Casing_Small, Shape_Press_Bullet_Casing_Medium, Shape_Press_Bullet_Casing_Large,
	Shape_Foodmold_Empty, Shape_Foodmold_Bun, Shape_Foodmold_Bread, Shape_Foodmold_Baguette, Shape_Foodmold_Cylinder,
	Shape_Slicer_Empty, Shape_Slicer_Flat, Shape_Slicer_Grid, Shape_Slicer_Eigths, Shape_Slicer_Eigths_Hollow, Shape_Slicer_Split, Shape_Slicer_Quarters, Shape_Slicer_Quarters_Hollow,
	Shape_Extruder_Empty, Shape_Extruder_Plate_Curved, Shape_Extruder_Bottle, Shape_Extruder_Plate, Shape_Extruder_Cell, Shape_Extruder_CCC, Shape_Extruder_Ring, Shape_Extruder_Rod, Shape_Extruder_Rod_Long, Shape_Extruder_Bolt, Shape_Extruder_Ingot, Shape_Extruder_Wire, Shape_Extruder_Casing, Shape_Extruder_Pipe_Tiny, Shape_Extruder_Pipe_Small, Shape_Extruder_Pipe_Medium, Shape_Extruder_Pipe_Large, Shape_Extruder_Pipe_Huge, Shape_Extruder_Block, Shape_Extruder_Sword, Shape_Extruder_Pickaxe, Shape_Extruder_Shovel, Shape_Extruder_Axe, Shape_Extruder_Hoe, Shape_Extruder_Hammer, Shape_Extruder_File, Shape_Extruder_Saw, Shape_Extruder_Gear, Shape_Extruder_Gear_Small, Shape_Extruder_Foil, Shape_Extruder_Plate_Tiny,
	Shape_SimpleEx_Empty, Shape_SimpleEx_Plate_Curved, Shape_SimpleEx_Bottle, Shape_SimpleEx_Plate, Shape_SimpleEx_Cell, Shape_SimpleEx_CCC, Shape_SimpleEx_Ring, Shape_SimpleEx_Rod, Shape_SimpleEx_Rod_Long, Shape_SimpleEx_Bolt, Shape_SimpleEx_Ingot, Shape_SimpleEx_Wire, Shape_SimpleEx_Casing, Shape_SimpleEx_Pipe_Tiny, Shape_SimpleEx_Pipe_Small, Shape_SimpleEx_Pipe_Medium, Shape_SimpleEx_Pipe_Large, Shape_SimpleEx_Pipe_Huge, Shape_SimpleEx_Block, Shape_SimpleEx_Sword, Shape_SimpleEx_Pickaxe, Shape_SimpleEx_Shovel, Shape_SimpleEx_Axe, Shape_SimpleEx_Hoe, Shape_SimpleEx_Hammer, Shape_SimpleEx_File, Shape_SimpleEx_Saw, Shape_SimpleEx_Gear, Shape_SimpleEx_Gear_Small, Shape_SimpleEx_Foil, Shape_SimpleEx_Plate_Tiny,
	Key_Iron, Key_Gold, Key_Copper, Key_Tin, Key_Bronze, Key_Brass, Key_Silver, Key_Platinum, Key_Lead, Key_Plastic,
	Module_Stone_Generator, Module_Basalt_Generator, Module_Blackstone_Generator,
	Compass_North, Compass_Face, Compass_Center, Compass_Spawn, Compass_Death,
	Credit_Iron,
//  Credit_Copper, Credit_Silver, Credit_Gold, Credit_Platinum, Credit_Osmium,
//  Credit_Greg_Copper, Credit_Greg_Cupronickel, Credit_Greg_Silver, Credit_Greg_Gold, Credit_Greg_Platinum, Credit_Greg_Osmium, Credit_Greg_Naquadah, Credit_Greg_Neutronium,
//  Coin_Gold_Ancient, Coin_Doge, Coin_Chocolate,
	Reactor_Rod_Empty,
	Cell_Universal_Fluid,
	Cell_Empty, Cell_Water, Cell_Lava, Cell_Air, Cell_CFoam, Cell_UUM,
	// If you want the filled Variants, just fill them using the Fluid Container Registry.
	Wooden_Bucket_Copper, Wooden_Bucket_Tin, Wooden_Bucket_Zinc, Wooden_Bucket_Lead, Wooden_Bucket_Bismuth, Wooden_Bucket_Brass, Wooden_Bucket_Bronze, Wooden_Bucket_BismuthBronze, Wooden_Bucket_Au,
	Ceramic_Tap, Ceramic_Tap_Raw,
	Ceramic_Funnel, Ceramic_Funnel_Raw,
	Ceramic_Crucible, Ceramic_Crucible_Raw,
	Ceramic_Basin, Ceramic_Basin_Raw,
	Ceramic_Mold, Ceramic_Mold_Raw,
	Ceramic_Faucet, Ceramic_Faucet_Raw,
	Ceramic_Crossing, Ceramic_Crossing_Raw,
	Juicer, Juicer_Raw,
	Ceramic_Bowl, Ceramic_Bowl_Raw,
	Measuring_Pot, Measuring_Pot_Raw,
	Ceramic_Jug, Ceramic_Jug_Raw,
	Porcelain_Cup,
	Compound_Bronze, Compound_Brass, Compound_BismuthBronze,
	Pellet_Wood,
	Bag_Sap_Resin,
	Rope, Rope_Silk, Rope_Grass, Rope_Plastic,
	Slimeball_Borax,
	Cerublossom, DesertNova, Resin,
	Comb_Honey, Comb_Water, Comb_Magic, Comb_Nether, Comb_End, Comb_Rock, Comb_Jungle, Comb_Frozen, Comb_Shroom, Comb_Sandy, Comb_Clay, Comb_Sticky, Comb_Royal, Comb_Soul, Comb_Amnesic, Comb_Military, Comb_Pyro, Comb_Cryo, Comb_Aero, Comb_Tera,
	PlasticCan, Crate, Crate_Fireproof,
	ThermosCan_Empty,
	ThermosCan_Dark_Coffee, ThermosCan_Dark_Cafe_au_lait, ThermosCan_Coffee, ThermosCan_Cafe_au_lait, ThermosCan_Lait_au_cafe, ThermosCan_Dark_Chocolate_Milk,
	ThermosCan_Chocolate_Milk,
	ThermosCan_Tea, ThermosCan_Sweet_Tea, ThermosCan_Ice_Tea,
	Bottle_Empty, Bottle_Milk, Bottle_Milk_Spoiled, Bottle_Rotten_Drink, Bottle_Glue, Bottle_Lubricant, Bottle_Mercury, Bottle_Holy_Water, Bottle_Beer, Bottle_Purple_Drink,
	Pill_Empty,
	Pill_Mint, Pill_Red, Pill_Blue,
	Pill_Iodine,
	Pill_Antidote,
	Pill_Cure_All,
	Food_Can_Empty,
	Food_Can_Undefined_1, Food_Can_Undefined_2, Food_Can_Undefined_3, Food_Can_Undefined_4, Food_Can_Undefined_5, Food_Can_Undefined_6,
	Food_Can_Meat_1, Food_Can_Meat_2, Food_Can_Meat_3, Food_Can_Meat_4, Food_Can_Meat_5, Food_Can_Meat_6,
	Food_Can_Fish_1, Food_Can_Fish_2, Food_Can_Fish_3, Food_Can_Fish_4, Food_Can_Fish_5, Food_Can_Fish_6,
	Food_Can_Veggie_1, Food_Can_Veggie_2, Food_Can_Veggie_3, Food_Can_Veggie_4, Food_Can_Veggie_5, Food_Can_Veggie_6,
	Food_Can_Fruit_1, Food_Can_Fruit_2, Food_Can_Fruit_3, Food_Can_Fruit_4, Food_Can_Fruit_5, Food_Can_Fruit_6,
	Food_Can_Bread_1, Food_Can_Bread_2, Food_Can_Bread_3, Food_Can_Bread_4, Food_Can_Bread_5, Food_Can_Bread_6,
	Food_Can_Rotten_1, Food_Can_Rotten_2, Food_Can_Rotten_3, Food_Can_Rotten_4, Food_Can_Rotten_5, Food_Can_Rotten_6,
	Food_Can_Chum_1, Food_Can_Chum_2, Food_Can_Chum_3, Food_Can_Chum_4, Food_Can_Chum_5, Food_Can_Chum_6,
	Food_PotatoChips_Raw, Food_PotatoChips, Food_PotatoChips_Packaged, Food_ChiliChips, Food_ChiliChips_Packaged,
	Food_Cheese, Food_Cheese_Bar, Food_Cheese_Sliced,
	Food_Rib_Raw, Food_Rib_Cooked,
	Food_RibEyeSteak_Raw, Food_RibEyeSteak_Cooked,
	Food_DogMeat_Raw, Food_DogMeat_Cooked,
	Food_Horse_Raw, Food_Horse_Cooked,
	Food_Mule_Raw, Food_Mule_Cooked,
	Food_Donkey_Raw, Food_Donkey_Cooked,
	Food_Mutton_Raw, Food_Mutton_Cooked,
	Food_Bacon_Raw, Food_Bacon_Cooked,
	Food_Ham_Raw, Food_Ham_Cooked, Food_Ham_Slice_Raw, Food_Ham_Slice_Cooked,
	Food_Chum, Food_Chum_On_Stick,
	Food_Tofu, Food_SoylentGreen,
	Food_Meat, Food_Meat_Raw,
	Food_Fish, Food_Fish_Raw,
	Food_Scrap_Meat,
	Food_Chocolate,
	Food_Butter, Food_Butter_Salted,
	Food_Dough, Food_Dough_Flat, Food_Dough_Flat_Ketchup,
	Food_Dough_Sugar,
	Food_Dough_Sugar_Raisins,
	Food_Dough_Sugar_Chocolate_Raisins,
	Food_Dough_Chocolate,
	Food_Dough_Egg, Food_Dough_Egg_Flat,
	Food_Dough_Abyssal,
	Food_Ice_Cream,
	Food_Ice_Cream_Stracciatella,
	Food_Ice_Cream_Raisin,
	Food_Ice_Cream_Vanilla,
	Food_Ice_Cream_Chocolate,
	Food_Ice_Cream_Mocha,
	Food_Ice_Cream_Caramel,
	Food_Ice_Cream_Mint,
	Food_Ice_Cream_Mint_Chocolate_Chip,
	Food_Ice_Cream_Strawberry,
	Food_Ice_Cream_Cherry,
	Food_Ice_Cream_Blueberry,
	Food_Ice_Cream_Currant,
	Food_Ice_Cream_Blackberry,
	Food_Ice_Cream_Raspberry,
	Food_Ice_Cream_Cranberry,
	Food_Ice_Cream_Gooseberry,
	Food_Ice_Cream_Nutella,
	Food_Ice_Cream_Peanut_Butter,
	Food_Ice_Cream_Lemon,
	Food_Ice_Cream_Kiwi,
	Food_Ice_Cream_Melon,
	Food_Ice_Cream_Banana,
	Food_Ice_Cream_Ananas,
	Food_Ice_Cream_Grape,
	Food_Ice_Cream_Apple,
	Food_Ice_Cream_Maple,
	Food_Ice_Cream_Pistachio,
	Food_Ice_Cream_Bacon,
	Food_Ice_Cream_Chum,
	Food_Ice_Cream_Honey,
	Food_Ice_Cream_Bear,
	Food_Ice_Cream_Neapolitan,
	Food_Ice_Cream_Spumoni_Chocolate,
	Food_Ice_Cream_Spumoni_Vanilla,
	Food_Ice_Cream_Superman,
	Food_Ice_Cream_Rainbow,
	Food_Cookie_Raw, Food_Cookie_Raisins, Food_Cookie_Raisins_Raw, Food_Cookie_Chocolate_Raisins, Food_Cookie_Chocolate_Raisins_Raw, Food_Cookie_Abyssal_Raw,
	Food_Burger_Tofu, Food_Burger_Soylent, Food_Burger_Veggie, Food_Burger_Cheese, Food_Burger_Meat, Food_Burger_Fish, Food_Burger_Chum,
	Food_Sandwich_Veggie, Food_Sandwich_Cheese, Food_Sandwich_Bacon, Food_Sandwich_Steak,
	Food_Large_Sandwich_Veggie, Food_Large_Sandwich_Cheese, Food_Large_Sandwich_Bacon, Food_Large_Sandwich_Steak,
	Food_Fries, Food_Fries_Raw, Food_Fries_Packaged,
	Food_Potato, Food_Potato_Baked, Food_Potato_On_Stick, Food_Potato_On_Stick_Roasted, Food_Potato_Poisonous,
	Food_CakeBottom, Food_CakeBottom_Raw,
	Food_Bread, Food_Bread_Raw, Food_Bread_Sliced, Food_Breads_Sliced,
	Food_Bun, Food_Bun_Raw, Food_Bun_Sliced, Food_Buns_Sliced,
	Food_Baguette, Food_Baguette_Raw, Food_Baguette_Sliced, Food_Baguettes_Sliced,
	Food_Pizza_Veggie, Food_Pizza_Veggie_Raw,
	Food_Pizza_Cheese, Food_Pizza_Cheese_Raw,
	Food_Pizza_Meat, Food_Pizza_Meat_Raw,
	Food_Pizza_Ananas, Food_Pizza_Ananas_Raw,
	Food_Chili_Pepper,
	Food_Banana, Food_Banana_Sliced,
	Food_Ananas, Food_Ananas_Sliced,
	Food_Cinnamon,
	Food_Strawberry,
	Food_Blueberry,
	Food_Raspberry,
	Food_Blackberry,
	Food_Gooseberry,
	Food_Cranberry,
	Food_Candleberry,
	Food_Currants_Red, Food_Currants_Black, Food_Currants_White,
	Food_Peanut, Food_Hazelnut, Food_Coconut,
	Food_Apple_Green, Food_Apple_Yellow, Food_Apple_Red, Food_Apple_DarkRed,
	Food_Apple_Green_Sliced, Food_Apple_Yellow_Sliced, Food_Apple_Red_Sliced, Food_Apple_DarkRed_Sliced,
	Food_Apple_Green_Core, Food_Apple_Yellow_Core, Food_Apple_Red_Core, Food_Apple_DarkRed_Core,
	Food_Lemon, Food_Lemon_Sliced,
	Food_Onion, Food_Onion_Sliced,
	Food_Carrot, Food_Carrot_Sliced,
	Food_Tomato, Food_Tomato_Sliced, Food_MTomato,
	Food_Grapes_Purple, Food_Grapes_Red, Food_Grapes_Green, Food_Grapes_White, Food_Raisins_Purple, Food_Raisins_Red, Food_Raisins_Green, Food_Raisins_White, Food_Raisins_Chocolate,
	Food_Pomegranate, Food_Pomeraisins,
	Food_Cucumber, Food_Cucumber_Sliced,
	Crop_Wheat, Crop_Barley, Crop_Rye, Crop_Oats, Crop_AbyssalOats, Crop_Rice,
	Bale_Wheat, Bale_Barley, Bale_Rye, Bale_Oats, Bale_AbyssalOats, Bale_Rice,
	Grass, Grass_Dry, Grass_Moldy, Grass_Rotten, Bale, Bale_Dry, Bale_Moldy, Bale_Rotten,
	Bark_Dry, Beam, Plank, Plank_Slab, Plank_Stairs, Treated_Planks, Treated_Planks_Slab,
	Mud_Ball, Clay_Ball_Brown, Clay_Ball_Red,
	Remains_Plant, Remains_Fruit, Remains_Veggie, Remains_Nut,
	Schematic, Schematic_Crafting, Schematic_1by1, Schematic_2by2, Schematic_3by3,
	Electrode_FR_Copper, Electrode_FR_Tin, Electrode_FR_Bronze, Electrode_FR_Iron, Electrode_FR_Gold, Electrode_FR_Diamond, Electrode_FR_Obsidian, Electrode_FR_Blaze, Electrode_FR_Rubber, Electrode_FR_Emerald, Electrode_FR_Apatite, Electrode_FR_Lapis, Electrode_FR_Ender,
	Circuit_Plate_Empty, Circuit_Plate_Copper, Circuit_Plate_Gold, Circuit_Plate_Platinum, Circuit_Plate_Magic, Circuit_Plate_Enderium, Circuit_Plate_Signalum, Circuit_Plate_HSLA,
	Circuit_Wire_Copper, Circuit_Wire_Gold, Circuit_Wire_Platinum, Circuit_Wire_Magic, Circuit_Wire_Enderium, Circuit_Wire_Signalum,
	Circuit_Board_Basic, Circuit_Board_Good, Circuit_Board_Advanced, Circuit_Board_Elite, Circuit_Board_Master, Circuit_Board_Ultimate, Circuit_Board_Magic, Circuit_Board_Enderium, Circuit_Board_Signalum, Circuit_Board_BC_Redstone, Circuit_Board_BC_Iron, Circuit_Board_BC_Gold, Circuit_Board_BC_Diamond, Circuit_Board_BC_Ender, Circuit_Board_BC_Quartz, Circuit_Board_BC_Comparator, Circuit_Board_BC_Emerald, Circuit_Board_HSLA_Circuit, Circuit_Board_Power_Module,
	Circuit_Part_Basic, Circuit_Part_Good, Circuit_Part_Advanced, Circuit_Part_Elite, Circuit_Part_Master, Circuit_Part_Ultimate, Circuit_Part_Magic, Circuit_Part_Enderium, Circuit_Part_Signalum, Circuit_Part_EnderPearl, Circuit_Part_EnderEye,
	Circuit_Primitive, Circuit_Basic, Circuit_Good, Circuit_Advanced, Circuit_Elite, Circuit_Master, Circuit_Ultimate, Circuit_Magic, Circuit_Enderium, Circuit_Signalum, Circuit_BC_Redstone, Circuit_BC_Iron, Circuit_BC_Gold, Circuit_BC_Diamond, Circuit_BC_Ender, Circuit_BC_Quartz, Circuit_BC_Comparator, Circuit_BC_Emerald,
	Circuit_Crystal_Diamond, Circuit_Crystal_Ruby, Circuit_Crystal_Emerald, Circuit_Crystal_Sapphire,
	Processor_Crystal_Empty, Processor_Crystal_Diamond, Processor_Crystal_Ruby, Processor_Crystal_Emerald, Processor_Crystal_Sapphire,
	USB_Stick_1, USB_Stick_2, USB_Stick_3, USB_Stick_4,
	USB_Cable_1, USB_Cable_2, USB_Cable_3, USB_Cable_4,
	USB_HDD_1, USB_HDD_2, USB_HDD_3, USB_HDD_4,
	USB_SSD_1, USB_SSD_2, USB_SSD_3, USB_SSD_4,
	Tool_Remote_Activator,
	Thermometer_Quicksilver, Geiger_Counter, Geiger_Counter_Empty,
	Electric_Motor_ULV, Electric_Motor_LV, Electric_Motor_MV, Electric_Motor_HV, Electric_Motor_EV, Electric_Motor_IV, Electric_Motor_LuV, Electric_Motor_ZPM, Electric_Motor_UV, Electric_Motor_PUV1, Electric_Motor_PUV2, Electric_Motor_PUV3, Electric_Motor_PUV4, Electric_Motor_PUV5, Electric_Motor_OMEGA,
	Electric_Pump_ULV, Electric_Pump_LV, Electric_Pump_MV, Electric_Pump_HV, Electric_Pump_EV, Electric_Pump_IV, Electric_Pump_LuV, Electric_Pump_ZPM, Electric_Pump_UV, Electric_Pump_PUV1, Electric_Pump_PUV2, Electric_Pump_PUV3, Electric_Pump_PUV4, Electric_Pump_PUV5, Electric_Pump_OMEGA,
	Conveyor_Module_ULV, Conveyor_Module_LV, Conveyor_Module_MV, Conveyor_Module_HV, Conveyor_Module_EV, Conveyor_Module_IV, Conveyor_Module_LuV, Conveyor_Module_ZPM, Conveyor_Module_UV, Conveyor_Module_PUV1, Conveyor_Module_PUV2, Conveyor_Module_PUV3, Conveyor_Module_PUV4, Conveyor_Module_PUV5, Conveyor_Module_OMEGA,
	Electric_Piston_ULV, Electric_Piston_LV, Electric_Piston_MV, Electric_Piston_HV, Electric_Piston_EV, Electric_Piston_IV, Electric_Piston_LuV, Electric_Piston_ZPM, Electric_Piston_UV, Electric_Piston_PUV1, Electric_Piston_PUV2, Electric_Piston_PUV3, Electric_Piston_PUV4, Electric_Piston_PUV5, Electric_Piston_OMEGA,
	Robot_Arm_ULV, Robot_Arm_LV, Robot_Arm_MV, Robot_Arm_HV, Robot_Arm_EV, Robot_Arm_IV, Robot_Arm_LuV, Robot_Arm_ZPM, Robot_Arm_UV, Robot_Arm_PUV1, Robot_Arm_PUV2, Robot_Arm_PUV3, Robot_Arm_PUV4, Robot_Arm_PUV5, Robot_Arm_OMEGA,
	Field_Generator_ULV, Field_Generator_LV, Field_Generator_MV, Field_Generator_HV, Field_Generator_EV, Field_Generator_IV, Field_Generator_LuV, Field_Generator_ZPM, Field_Generator_UV, Field_Generator_PUV1, Field_Generator_PUV2, Field_Generator_PUV3, Field_Generator_PUV4, Field_Generator_PUV5, Field_Generator_OMEGA,
	Emitter_ULV, Emitter_LV, Emitter_MV, Emitter_HV, Emitter_EV, Emitter_IV, Emitter_LuV, Emitter_ZPM, Emitter_UV, Emitter_PUV1, Emitter_PUV2, Emitter_PUV3, Emitter_PUV4, Emitter_PUV5, Emitter_OMEGA,
	Sensor_ULV, Sensor_LV, Sensor_MV, Sensor_HV, Sensor_EV, Sensor_IV, Sensor_LuV, Sensor_ZPM, Sensor_UV, Sensor_PUV1, Sensor_PUV2, Sensor_PUV3, Sensor_PUV4, Sensor_PUV5, Sensor_OMEGA,
	Robot_Tip_Wrench, Robot_Tip_Screwdriver, Robot_Tip_Saw, Robot_Tip_Hammer, Robot_Tip_Cutter, Robot_Tip_Chisel, Robot_Tip_Rubber, Robot_Tip_Blade, Robot_Tip_Drill, Robot_Tip_File,
	Battery_RE_ULV_Tantalum, ZPM,
	Comp_Laser_Gas_Empty, Comp_Laser_Gas_He, Comp_Laser_Gas_Ne, Comp_Laser_Gas_Ar, Comp_Laser_Gas_Kr, Comp_Laser_Gas_Xe, Comp_Laser_Gas_HeNe, Comp_Laser_Gas_CO, Comp_Laser_Gas_CO2,
	Battery_Lead_Acid_Cell_Empty, Battery_Lead_Acid_Cell_Filled, Battery_Lead_Acid_ULV, Battery_Lead_Acid_LV, Battery_Lead_Acid_MV, Battery_Lead_Acid_HV, Battery_Lead_Acid_EV,
	Battery_Alkaline_Cell_Empty, Battery_Alkaline_Cell_Filled, Battery_Alkaline_ULV, Battery_Alkaline_LV, Battery_Alkaline_MV, Battery_Alkaline_HV, Battery_Alkaline_EV,
	Battery_NiCd_Cell_Empty, Battery_NiCd_Cell_Filled, Battery_NiCd_ULV, Battery_NiCd_LV, Battery_NiCd_MV, Battery_NiCd_HV, Battery_NiCd_EV,
	Battery_LiCoO2_Cell_Empty, Battery_LiCoO2_Cell_Filled, Battery_LiCoO2_ULV, Battery_LiCoO2_LV, Battery_LiCoO2_MV, Battery_LiCoO2_HV, Battery_LiCoO2_EV,
	Battery_LiMn_Cell_Empty, Battery_LiMn_Cell_Filled, Battery_LiMn_ULV, Battery_LiMn_LV, Battery_LiMn_MV, Battery_LiMn_HV, Battery_LiMn_EV,
	Crystal_Energium_Red_ULV, Crystal_Energium_Red_LV, Crystal_Energium_Red_MV, Crystal_Energium_Red_HV, Crystal_Energium_Red_EV, Crystal_Energium_Red_IV,
	Crystal_Energium_Cyan_ULV, Crystal_Energium_Cyan_LV, Crystal_Energium_Cyan_MV, Crystal_Energium_Cyan_HV, Crystal_Energium_Cyan_EV, Crystal_Energium_Cyan_IV,
	Fuel_Can_Plastic_Empty, Fuel_Can_Plastic_Filled,
	Upgrade_Battery, Upgrade_Overclocker, Upgrade_Muffler, Upgrade_Lock,
	Cover_Blank,
	Cover_Warning,
	Cover_Crafting,
	Cover_Auto_Switch, Cover_Redstone_Switch, Cover_Auto_Switch_Redstone, Cover_Auto_Switch_01_Minute, Cover_Auto_Switch_05_Minute, Cover_Auto_Switch_10_Minute, Cover_Auto_Switch_20_Minute, Cover_Auto_Switch_30_Minute,
	Cover_Redstone_Selector, Cover_Manual_Selector, Cover_Button_Selector,
	Cover_Redstone_Emitter,
	Cover_Redstone_Conductor_IN, Cover_Redstone_Conductor_OUT,
	Cover_Pressure_Valve,
	Cover_Machine_Display, Cover_Energy_Display,
	Cover_Scale_Energy, Cover_Scale_Progress,
	Cover_Detector_Possible, Cover_Detector_Passively, Cover_Detector_Actively, Cover_Detector_Success,
	Cover_Screen,
	Cover_Controller,
	Cover_ActivityDetector, Cover_FluidDetector, Cover_ItemDetector, Cover_EnergyDetector,
	Cover_Drain, Cover_Vent, Cover_Shutter, Cover_Filter_Item, Cover_Filter_Fluid,
	Cover_Retriever_Item,
	Cover_Logistics_Generic_Export, Cover_Logistics_Generic_Import, Cover_Logistics_Generic_Storage,
	Cover_Logistics_Fluid_Export, Cover_Logistics_Fluid_Import, Cover_Logistics_Fluid_Storage,
	Cover_Logistics_Item_Export, Cover_Logistics_Item_Import, Cover_Logistics_Item_Storage,
	Cover_Logistics_Dump,
	Cover_Logistics_Display_CPU_Logic, Cover_Logistics_Display_CPU_Control, Cover_Logistics_Display_CPU_Storage, Cover_Logistics_Display_CPU_Conversion,
	Cover_SolarPanel, Cover_SolarPanel_8V, Cover_SolarPanel_LV, Cover_SolarPanel_MV, Cover_SolarPanel_HV, Cover_SolarPanel_EV, Cover_SolarPanel_IV, Cover_SolarPanel_LuV, Cover_SolarPanel_ZPM, Cover_SolarPanel_UV,
	Ingot_IridiumAlloy,
	Dye_SquidInk, Dye_Bonemeal, Dye_Cactus, Dye_Cocoa,
	Tape,Tape_Used, Duct_Tape, Duct_Tape_Used, Brain_Tape, Brain_Tape_Used,
	Dynamite, Dynamite_Strong,
	Paper_Blueprint_Empty, Paper_Blueprint_Used,
	Paper_Printed_Pages, Paper_Printed_Pages_Many,
	Paper_Magic_Empty, Paper_Magic_Page, Paper_Magic_Pages,
	Paper_Magic_Research_0, Paper_Magic_Research_1, Paper_Magic_Research_2, Paper_Magic_Research_3, Paper_Magic_Research_4, Paper_Magic_Research_5, Paper_Magic_Research_6, Paper_Magic_Research_7, Paper_Magic_Research_8,
	Paper_Punch_Card_Empty, Paper_Punch_Card_Encoded,
	Tool_Matches, Tool_MatchBox_Empty, Tool_MatchBox_Used, Tool_MatchBox_Full,
	Tool_Lighter_Invar_Empty, Tool_Lighter_Invar_Used, Tool_Lighter_Invar_Full,
	Tool_Lighter_Platinum_Empty, Tool_Lighter_Platinum_Used, Tool_Lighter_Platinum_Full,
	Tool_Lighter_Plastic_Empty, Tool_Lighter_Plastic_Used, Tool_Lighter_Plastic_Full, Tool_Lighter_Plastic_Broken,
	Tool_Fire_Starter, Tool_Fire_Starter_Bark,
	Tool_Cheat, Tool_Worldgen_Debugger, Tool_Scanner, Tool_Cropnalyzer,
	Tool_Sword_Bronze, Tool_Pickaxe_Bronze, Tool_Shovel_Bronze, Tool_Axe_Bronze, Tool_Hoe_Bronze, // IC2
	Tool_Sword_Steel, Tool_Pickaxe_Steel, Tool_Shovel_Steel, Tool_Axe_Steel, Tool_Hoe_Steel, // RC
	
	Spray_Empty, Spray_Bug, Spray_Pepper, Spray_Hydration, Spray_Ice, Spray_Extinguisher, Spray_Extinguisher_Used, Spray_Foam_Hardener, Spray_Foam_Hardener_Used, Spray_Foam_Remover, Spray_Foam_Remover_Used, Spray_Color_Remover, Spray_Color_Remover_Used,
	Spray_Color_00, Spray_Color_01, Spray_Color_02, Spray_Color_03, Spray_Color_04, Spray_Color_05, Spray_Color_06, Spray_Color_07, Spray_Color_08, Spray_Color_09, Spray_Color_10, Spray_Color_11, Spray_Color_12, Spray_Color_13, Spray_Color_14, Spray_Color_15,
	Spray_Color_Used_00, Spray_Color_Used_01, Spray_Color_Used_02, Spray_Color_Used_03, Spray_Color_Used_04, Spray_Color_Used_05, Spray_Color_Used_06, Spray_Color_Used_07, Spray_Color_Used_08, Spray_Color_Used_09, Spray_Color_Used_10, Spray_Color_Used_11, Spray_Color_Used_12, Spray_Color_Used_13, Spray_Color_Used_14, Spray_Color_Used_15,
	Spray_Foam_00, Spray_Foam_01, Spray_Foam_02, Spray_Foam_03, Spray_Foam_04, Spray_Foam_05, Spray_Foam_06, Spray_Foam_07, Spray_Foam_08, Spray_Foam_09, Spray_Foam_10, Spray_Foam_11, Spray_Foam_12, Spray_Foam_13, Spray_Foam_14, Spray_Foam_15,
	Spray_Foam_Used_00, Spray_Foam_Used_01, Spray_Foam_Used_02, Spray_Foam_Used_03, Spray_Foam_Used_04, Spray_Foam_Used_05, Spray_Foam_Used_06, Spray_Foam_Used_07, Spray_Foam_Used_08, Spray_Foam_Used_09, Spray_Foam_Used_10, Spray_Foam_Used_11, Spray_Foam_Used_12, Spray_Foam_Used_13, Spray_Foam_Used_14, Spray_Foam_Used_15,
	Spray_Foam_Owned_00, Spray_Foam_Owned_01, Spray_Foam_Owned_02, Spray_Foam_Owned_03, Spray_Foam_Owned_04, Spray_Foam_Owned_05, Spray_Foam_Owned_06, Spray_Foam_Owned_07, Spray_Foam_Owned_08, Spray_Foam_Owned_09, Spray_Foam_Owned_10, Spray_Foam_Owned_11, Spray_Foam_Owned_12, Spray_Foam_Owned_13, Spray_Foam_Owned_14, Spray_Foam_Owned_15,
	Spray_Foam_Owned_Used_00, Spray_Foam_Owned_Used_01, Spray_Foam_Owned_Used_02, Spray_Foam_Owned_Used_03, Spray_Foam_Owned_Used_04, Spray_Foam_Owned_Used_05, Spray_Foam_Owned_Used_06, Spray_Foam_Owned_Used_07, Spray_Foam_Owned_Used_08, Spray_Foam_Owned_Used_09, Spray_Foam_Owned_Used_10, Spray_Foam_Owned_Used_11, Spray_Foam_Owned_Used_12, Spray_Foam_Owned_Used_13, Spray_Foam_Owned_Used_14, Spray_Foam_Owned_Used_15,
	
	// Deprecated
	Battery_Hull_LV, Battery_SU_LV_SulfuricAcid, Battery_SU_LV_Mercury, Battery_RE_LV_Sodium, Battery_RE_LV_Cadmium, Battery_RE_LV_Lithium, Battery_RE_LV_Redstone, Battery_RE_LV_Nikolite, Battery_RE_LV_Teslatite, Battery_RE_LV_Electrotine,
	Battery_Hull_MV, Battery_SU_MV_SulfuricAcid, Battery_SU_MV_Mercury, Battery_RE_MV_Sodium, Battery_RE_MV_Cadmium, Battery_RE_MV_Lithium, Battery_RE_MV_Redstone, Battery_RE_MV_Nikolite, Battery_RE_MV_Teslatite, Battery_RE_MV_Electrotine,
	Battery_Hull_HV, Battery_SU_HV_SulfuricAcid, Battery_SU_HV_Mercury, Battery_RE_HV_Sodium, Battery_RE_HV_Cadmium, Battery_RE_HV_Lithium, Battery_RE_HV_Redstone, Battery_RE_HV_Nikolite, Battery_RE_HV_Teslatite, Battery_RE_HV_Electrotine,
	EnergyCrystal_RE_LV_Energium, EnergyCrystal_RE_MV_Energium, EnergyCrystal_RE_HV_Energium, EnergyCrystal_RE_EV_Energium, EnergyCrystal_RE_IV_Energium,
	
	NULL;
	
	public static final IL[]
	  SPRAY_CAN_DYES = {Spray_Color_00, Spray_Color_01, Spray_Color_02, Spray_Color_03, Spray_Color_04, Spray_Color_05, Spray_Color_06, Spray_Color_07, Spray_Color_08, Spray_Color_09, Spray_Color_10, Spray_Color_11, Spray_Color_12, Spray_Color_13, Spray_Color_14, Spray_Color_15}
	, SPRAY_CAN_DYES_USED = {Spray_Color_Used_00, Spray_Color_Used_01, Spray_Color_Used_02, Spray_Color_Used_03, Spray_Color_Used_04, Spray_Color_Used_05, Spray_Color_Used_06, Spray_Color_Used_07, Spray_Color_Used_08, Spray_Color_Used_09, Spray_Color_Used_10, Spray_Color_Used_11, Spray_Color_Used_12, Spray_Color_Used_13, Spray_Color_Used_14, Spray_Color_Used_15}
	, SPRAY_CAN_FOAM = {Spray_Foam_00, Spray_Foam_01, Spray_Foam_02, Spray_Foam_03, Spray_Foam_04, Spray_Foam_05, Spray_Foam_06, Spray_Foam_07, Spray_Foam_08, Spray_Foam_09, Spray_Foam_10, Spray_Foam_11, Spray_Foam_12, Spray_Foam_13, Spray_Foam_14, Spray_Foam_15}
	, SPRAY_CAN_FOAM_USED = {Spray_Foam_Used_00, Spray_Foam_Used_01, Spray_Foam_Used_02, Spray_Foam_Used_03, Spray_Foam_Used_04, Spray_Foam_Used_05, Spray_Foam_Used_06, Spray_Foam_Used_07, Spray_Foam_Used_08, Spray_Foam_Used_09, Spray_Foam_Used_10, Spray_Foam_Used_11, Spray_Foam_Used_12, Spray_Foam_Used_13, Spray_Foam_Used_14, Spray_Foam_Used_15}
	, SPRAY_CAN_FOAM_OWNED = {Spray_Foam_Owned_00, Spray_Foam_Owned_01, Spray_Foam_Owned_02, Spray_Foam_Owned_03, Spray_Foam_Owned_04, Spray_Foam_Owned_05, Spray_Foam_Owned_06, Spray_Foam_Owned_07, Spray_Foam_Owned_08, Spray_Foam_Owned_09, Spray_Foam_Owned_10, Spray_Foam_Owned_11, Spray_Foam_Owned_12, Spray_Foam_Owned_13, Spray_Foam_Owned_14, Spray_Foam_Owned_15}
	, SPRAY_CAN_FOAM_OWNED_USED = {Spray_Foam_Owned_Used_00, Spray_Foam_Owned_Used_01, Spray_Foam_Owned_Used_02, Spray_Foam_Owned_Used_03, Spray_Foam_Owned_Used_04, Spray_Foam_Owned_Used_05, Spray_Foam_Owned_Used_06, Spray_Foam_Owned_Used_07, Spray_Foam_Owned_Used_08, Spray_Foam_Owned_Used_09, Spray_Foam_Owned_Used_10, Spray_Foam_Owned_Used_11, Spray_Foam_Owned_Used_12, Spray_Foam_Owned_Used_13, Spray_Foam_Owned_Used_14, Spray_Foam_Owned_Used_15}
	, CANS_UNDEFINED = {Food_Can_Undefined_1, Food_Can_Undefined_2, Food_Can_Undefined_3, Food_Can_Undefined_4, Food_Can_Undefined_5, Food_Can_Undefined_6}
	, CANS_ROTTEN = {Food_Can_Rotten_1, Food_Can_Rotten_2, Food_Can_Rotten_3, Food_Can_Rotten_4, Food_Can_Rotten_5, Food_Can_Rotten_6}
	, CANS_CHUM = {Food_Can_Chum_1, Food_Can_Chum_2, Food_Can_Chum_3, Food_Can_Chum_4, Food_Can_Chum_5, Food_Can_Chum_6}
	, CANS_FRUIT = {Food_Can_Fruit_1, Food_Can_Fruit_2, Food_Can_Fruit_3, Food_Can_Fruit_4, Food_Can_Fruit_5, Food_Can_Fruit_6}
	, CANS_VEGGIE = {Food_Can_Veggie_1, Food_Can_Veggie_2, Food_Can_Veggie_3, Food_Can_Veggie_4, Food_Can_Veggie_5, Food_Can_Veggie_6}
	, CANS_BREAD = {Food_Can_Bread_1, Food_Can_Bread_2, Food_Can_Bread_3, Food_Can_Bread_4, Food_Can_Bread_5, Food_Can_Bread_6}
	, CANS_MEAT = {Food_Can_Meat_1, Food_Can_Meat_2, Food_Can_Meat_3, Food_Can_Meat_4, Food_Can_Meat_5, Food_Can_Meat_6}
	, CANS_FISH = {Food_Can_Fish_1, Food_Can_Fish_2, Food_Can_Fish_3, Food_Can_Fish_4, Food_Can_Fish_5, Food_Can_Fish_6}
	, KEYS = {Key_Brass, Key_Bronze, Key_Copper, Key_Gold, Key_Iron, Key_Lead, Key_Plastic, Key_Platinum, Key_Silver, Key_Tin}
	, KEYS_FANCY = {Key_Gold, Key_Platinum, Key_Silver}
	, KEYS_CHEAP = {Key_Brass, Key_Bronze, Key_Iron, Key_Lead, Key_Tin}
	, MOTORS = {Electric_Motor_ULV, Electric_Motor_LV, Electric_Motor_MV, Electric_Motor_HV, Electric_Motor_EV, Electric_Motor_IV, Electric_Motor_LuV, Electric_Motor_ZPM, Electric_Motor_UV, Electric_Motor_PUV1, Electric_Motor_PUV2, Electric_Motor_PUV3, Electric_Motor_PUV4, Electric_Motor_PUV5, Electric_Motor_OMEGA}
	, PUMPS = {Electric_Pump_ULV, Electric_Pump_LV, Electric_Pump_MV, Electric_Pump_HV, Electric_Pump_EV, Electric_Pump_IV, Electric_Pump_LuV, Electric_Pump_ZPM, Electric_Pump_UV, Electric_Pump_PUV1, Electric_Pump_PUV2, Electric_Pump_PUV3, Electric_Pump_PUV4, Electric_Pump_PUV5, Electric_Pump_OMEGA}
	, CONVEYERS = {Conveyor_Module_ULV, Conveyor_Module_LV, Conveyor_Module_MV, Conveyor_Module_HV, Conveyor_Module_EV, Conveyor_Module_IV, Conveyor_Module_LuV, Conveyor_Module_ZPM, Conveyor_Module_UV, Conveyor_Module_PUV1, Conveyor_Module_PUV2, Conveyor_Module_PUV3, Conveyor_Module_PUV4, Conveyor_Module_PUV5, Conveyor_Module_OMEGA}
	, PISTONS = {Electric_Piston_ULV, Electric_Piston_LV, Electric_Piston_MV, Electric_Piston_HV, Electric_Piston_EV, Electric_Piston_IV, Electric_Piston_LuV, Electric_Piston_ZPM, Electric_Piston_UV, Electric_Piston_PUV1, Electric_Piston_PUV2, Electric_Piston_PUV3, Electric_Piston_PUV4, Electric_Piston_PUV5, Electric_Piston_OMEGA}
	, ROBOT_ARMS = {Robot_Arm_ULV, Robot_Arm_LV, Robot_Arm_MV, Robot_Arm_HV, Robot_Arm_EV, Robot_Arm_IV, Robot_Arm_LuV, Robot_Arm_ZPM, Robot_Arm_UV, Robot_Arm_PUV1, Robot_Arm_PUV2, Robot_Arm_PUV3, Robot_Arm_PUV4, Robot_Arm_PUV5, Robot_Arm_OMEGA}
	, FIELD_GENERATORS = {Field_Generator_ULV, Field_Generator_LV, Field_Generator_MV, Field_Generator_HV, Field_Generator_EV, Field_Generator_IV, Field_Generator_LuV, Field_Generator_ZPM, Field_Generator_UV, Field_Generator_PUV1, Field_Generator_PUV2, Field_Generator_PUV3, Field_Generator_PUV4, Field_Generator_PUV5, Field_Generator_OMEGA}
	, EMITTERS = {Emitter_ULV, Emitter_LV, Emitter_MV, Emitter_HV, Emitter_EV, Emitter_IV, Emitter_LuV, Emitter_ZPM, Emitter_UV, Emitter_PUV1, Emitter_PUV2, Emitter_PUV3, Emitter_PUV4, Emitter_PUV5, Emitter_OMEGA}
	, SENSORS = {Sensor_ULV, Sensor_LV, Sensor_MV, Sensor_HV, Sensor_EV, Sensor_IV, Sensor_LuV, Sensor_ZPM, Sensor_UV, Sensor_PUV1, Sensor_PUV2, Sensor_PUV3, Sensor_PUV4, Sensor_PUV5, Sensor_OMEGA}
	, MAGIC_RESEARCH_PAPERS = {Paper_Magic_Research_0, Paper_Magic_Research_1, Paper_Magic_Research_2, Paper_Magic_Research_3, Paper_Magic_Research_4, Paper_Magic_Research_5, Paper_Magic_Research_6, Paper_Magic_Research_7, Paper_Magic_Research_8,}
	;
	private ItemStack mStack;
	private boolean mHasNotBeenSet = T;
	
	@Override
	public IItemContainer set(Item aItem) {
		mHasNotBeenSet = F;
		if (aItem == null) {
//          new Exception().printStackTrace(GT_Log.deb);
			return this;
		}
		mStack = ST.amount(1, ST.make(aItem, 1, 0));
		return this;
	}
	
	public IItemContainer set(Item aItem, long aMeta) {
		mHasNotBeenSet = F;
		if (aItem == null) {
//          new Exception().printStackTrace(GT_Log.deb);
			return this;
		}
		mStack = ST.amount(1, ST.make(aItem, 1, aMeta));
		return this;
	}
	
	@Override
	public IItemContainer set(ItemStack aStack) {
		mHasNotBeenSet = F;
		if (ST.invalid(aStack)) {
//          new Exception().printStackTrace(GT_Log.deb);
			return this;
		}
		mStack = ST.amount(1, aStack);
		return this;
	}
	
	public IItemContainer set(Item aItem, OreDictItemData aData, Object... aOreDict) {
		mHasNotBeenSet = F;
		if (aItem == null) {
//          new Exception().printStackTrace(GT_Log.deb);
			return this;
		}
		ItemStack aStack = ST.make(aItem, 1, 0);
		mStack = ST.amount(1, aStack);
		if (aData != null && !OM.reg(aData.toString(), ST.make(aItem, 1, W))) OM.data(ST.make(aItem, 1, W), aData);
		for (Object tOreDict : aOreDict) OM.reg(tOreDict, ST.make(aItem, 1, W));
		return this;
	}
	
	public IItemContainer set(ItemStack aStack, OreDictItemData aData, Object... aOreDict) {
		mHasNotBeenSet = F;
		if (ST.invalid(aStack)) {
//          new Exception().printStackTrace(DEB);
			return this;
		}
		mStack = ST.amount(1, aStack);
		if (aData != null && !OM.reg(aData.toString(), ST.amount(1, aStack))) OM.data(ST.amount(1, aStack), aData);
		for (Object tOreDict : aOreDict) OM.reg(tOreDict, ST.amount(1, aStack));
		return this;
	}
	
	@Override
	public Item item() {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return null;
		return mStack.getItem();
	}
	
	@Override
	public Block block() {
		return ST.block(get(0));
	}
	
	@Override
	public boolean exists() {
		return ST.valid(mStack);
	}
	
	@Override
	public final boolean hasBeenSet() {
		return !mHasNotBeenSet;
	}
	
	@Override
	public boolean equal(Object aStackOrBlock) {
		return mStack != null && (aStackOrBlock instanceof Block ? aStackOrBlock != NB && ST.block_(mStack) == aStackOrBlock : equal(aStackOrBlock, F, F));
	}
	
	@Override
	public boolean equal(Object aStack, boolean aWildcard, boolean aIgnoreNBT) {
		return mStack != null && (aWildcard ? ST.item((ItemStack)aStack) == ST.item_(mStack) : ST.equal((ItemStack)aStack, mStack, aIgnoreNBT));
	}
	
	@Override
	public ItemStack get(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.amount(aAmount, OM.get_(mStack));
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ItemStack getWildcard(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, W, OM.get_(mStack));
	}
	
	@Override
	public ItemStack wild(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, W, OM.get_(mStack));
	}
	
	@Override
	public ItemStack getUndamaged(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, 0, OM.get_(mStack));
	}
	
	@Override
	public ItemStack getAlmostBroken(long aAmount, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, mStack.getMaxDamage()-1, OM.get_(mStack));
	}
	
	@Override
	public ItemStack getWithName(long aAmount, String aDisplayName, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (ST.invalid(rStack)) return null;
		rStack.setStackDisplayName(aDisplayName);
		return ST.amount(aAmount, rStack);
	}
	
	@Override
	public ItemStack getWithNameAndNBT(long aAmount, String aDisplayName, NBTTagCompound aNBT, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (ST.invalid(rStack)) return null;
		UT.NBT.set(rStack, aNBT);
		if (aDisplayName != null) rStack.setStackDisplayName(aDisplayName);
		return ST.amount(aAmount, rStack);
	}
	
	@Override
	public ItemStack getWithCharge(long aAmount, long aEnergy, Object... aReplacements) {
		ItemStack rStack = get(1, aReplacements);
		if (ST.invalid(rStack)) return null;
		if (rStack.getItem() instanceof IItemEnergy) for (TagData tEnergyType : ((IItemEnergy)rStack.getItem()).getEnergyTypes(rStack)) ((IItemEnergy)rStack.getItem()).setEnergyStored(tEnergyType, rStack, aEnergy);
		return ST.amount(aAmount, rStack);
	}
	
	@Override
	public ItemStack getWithMeta(long aAmount, long aMetaValue, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, aMetaValue, OM.get_(mStack));
	}
	
	@Override
	public ItemStack getWithDamage(long aAmount, long aMetaValue, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		return ST.copyAmountAndMeta(aAmount, aMetaValue, OM.get_(mStack));
	}
	
	@Override
	public ItemStack getWithNBT(long aAmount, NBTTagCompound aNBT, Object... aReplacements) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		if (ST.invalid(mStack)) return ST.copyFirst(aReplacements);
		ItemStack rStack = ST.amount(aAmount, OM.get_(mStack));
		UT.NBT.set(rStack, aNBT);
		return rStack;
	}
	
	@Override
	public IItemContainer registerOre(Object... aOreNames) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames) OM.reg(tOreName, get(1));
		return this;
	}
	
	@Override
	public IItemContainer registerWildcardAsOre(Object... aOreNames) {
		if (mHasNotBeenSet && Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) OUT.println("The Enum '" + name() + "' has not been set to an Item at this time!");
		for (Object tOreName : aOreNames) OM.reg(tOreName, wild(1));
		return this;
	}
	
	@SuppressWarnings("deprecation") @Override public Item getItem() {return item();}
	@SuppressWarnings("deprecation") @Override public Block getBlock() {return block();}
}
