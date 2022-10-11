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

package gregtech.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.damage.DamageSources;
import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.bumble.IItemBumbleBee;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.BlockDiggable;
import gregtech.tileentity.plants.MultiTileEntityResinHoleRubber;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static gregapi.data.CS.*;

public class MultiItemBumbles extends MultiItemRandom implements IItemBumbleBee {
	public MultiItemBumbles(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Bumblebees", this, (short)2));
		if (!SHOW_BUMBLEBEES) ST.hide(this);
	}
	
	@Override
	public void addItems() {
		make(    0, "Wild Bumblebee"            , "");
		make(   10, "Captive Bumblebee"         , "Is used to enprisonment");
		make(   20, "Common Bumblebee"          , "Works hard for its Family");
		make(   30, "Cultivated Bumblebee"      , "Drinks Wine while watching Oprah");
		
		make(  100, "Surfing Bumblebee"         , "Too chill to work regularily");
		make(  110, "Swimming Bumblebee"        , "");
		make(  120, "Diving Bumblebee"          , "Not gonna jump into Green Water");
		make(  130, "Subnautic Bumblebee"       , "Its Wings became Air Tanks");
		
		make(  200, "Apprentice Bumblebee"      , "");
		make(  210, "Magician Bumblebee"        , "");
		make(  220, "Wizard Bumblebee"          , "");
		make(  230, "Dumblebee"                 , "");
		
		make(  300, "Nether Bumblebee"          , "");
		make(  310, "Hellish Bumblebee"         , "");
		make(  320, "Impish Bumblebee"          , "");
		make(  330, "Demonic Bumblebee"         , "");
		
		make(  400, "End Bumblebee"             , "The End is nigh!");
		make(  410, "Void Bumblebee"            , "Voids parts of its work for no reason");
		make(  420, "Alienated Bumblebee"       , "Doesn't feel comfortable with Society");
		make(  430, "Nihilistic Bumblebee"      , "Who cares? Nothing matters.");
		
		make(  500, "Stoned Bumblebee"          , "Duuuude, I'm so damn biiig");
		make(  510, "Rocking Bumblebee"         , "");
		make(  520, "Hard Rock Bumblebee"       , "");
		make(  530, "Bumbelvis"                 , "Has left the Building");
		
		make(  600, "Jungle Bumblebee"          , "");
		make(  610, "Jungle Bumblebee (T2)"     , "");//TODO Change Bumble Texture and Name
		make(  620, "Jungle Bumblebee (T3)"     , "");//TODO Change Bumble Texture and Name
		make(  630, "Bumblezan"                 , "BuzzIAH!-IAH!");//TODO Change Bumble Texture
		
		make(  700, "Frosty Bumblebee"          , "");
		make(  710, "North Pole Bumblebee"      , "");
		make(  720, "Bumble Elf"                , "");
		make(  730, "Bumble Claus"              , "Ho Ho Ho!");
		
		make(  800, "Bumbleshroom"              , "");
		make(  810, "Bumble Toad"               , "");
		make(  820, "Bumble Bro"                , "It's a me! Bumblio!");
		make(  830, "Bumble Peach"              , "Is in another Castle!");
		
		make(  900, "Sandy Bumblebee"           , "");
		make(  910, "Sandy Bumblebee (T2)"      , "");//TODO Change Bumble Texture and Name
		make(  920, "Sandy Bumblebee (T3)"      , "");//TODO Change Bumble Texture and Name
		make(  930, "Bumbobee"                  , "Shop at Sahara!");
		
		// Normal + Rocky = Clay
		make(10000, "Creative Bumblebee"        , "Interested in Pottery");
		make(10010, "Builder Bumblebee"         , "Also known as Bumblebob the Builder");
		make(10020, "Masonic Bumblebee"         , "Is free to build Brick Walls");
		make(10030, "Illuminumblebee"           , "Nothing suspicious around here");
		
		// Normal + Water = Sticky
		make(10100, "Industrial Bumblebee"      , "Revolutionises Comb Production");
		make(10110, "Overseer Bumblebee"        , "Effectively orders around the Workforce");
		make(10120, "Bumble Tycoon"             , "Taking over the Bumblebeesiness");
		make(10130, "Monopolistic Bumblebee"    , "Owns everything and everyone");
		
		// Normal + Sandy = Royal
		make(10200, "Bumbleknight"              , "For the King of Bumble Castle!");
		make(10210, "Colonial Bumblebee"        , "For the Queen of Bumbleland!");
		make(10220, "Royal Bumblebee"           , "Member of the Royal Family");
		make(10230, "Bumblemonarch"             , "Ruling over the Bumblecountry");
		
		// Nether + End = Satanic
		make(10300, "Bumblegoth"                , "Hangs around in dark places");
		make(10310, "Occult Bumblebee"          , "Practices occult Rituals");
		make(10320, "Antichristumblebee"        , "Screw Bumblebeesus!");
		make(10330, "Satanic Bumblebee"         , "Heil Bumblesatan!");
		
		// Rock + Water = Amnesic / Lubricant
		make(10400, "Forgetful Bumblebee"       , "What was I supposed to do again? It slipped my mind.");
		make(10410, "Amnesic Bumblebee"         , "Who are you? And who am I even?");
		make(10420, "Bumbleheimers"             , "Could you get me some Honey? Thanks. Could you get me some Honey?");
		make(10430, "Bumble in Black"           , "You did not see anything unusual. I did not wiggle my Butt.");
		
		// Jungle + Sandy = Soldier (Damages anything, even Skeletons, makes Bone Combs)
		make(10500, "Private Bumble"            , "Reporting for Duty!");
		make(10510, "Lt. Bumbleson"             , "Yes Sir!");
		make(10520, "Colonel Bumble O'Beeill"   , "Leading the Team");
		make(10530, "General Bumblemond"        , "In charge of the Bumblyene Mountain Base");
		
		// Nether + Military = Pyro
		make(20000, "Blazing Bumblebee"         , "");
		make(20010, "Flaming Bumblebee"         , "");//TODO Change Bumble Texture
		make(20020, "Bumbletrantor"             , "The Last Stormbumble");//TODO Change Bumble Texture
		make(20030, "Pyro Bumble"               , "Likes Unicorns, Rainbows and Candy");//TODO Change Bumble Texture
		
		// Frosty + Military = Cryo
		make(20100, "Blizzful Bumblebee"        , "");
		make(20110, "Freezing Bumblebee"        , "");//TODO Change Bumble Texture
		make(20120, "Mr. Bumblefreeze"          , "Ice to meet you!");//TODO Change Bumble Texture
		make(20130, "Cryo Bumble"               , "Delivered Pizza to I.C.Wiener");//TODO Change Bumble Texture
		
		// End + Military = Aero
		make(20200, "Blitzing Bumblebee"        , "");
		make(20210, "Storming Bumblebee"        , "");//TODO Change Bumble Texture
		make(20220, "Bumbleaang"                , "The Last Bumblebender");//TODO Change Bumble Texture
		make(20230, "Aero Bumble"               , "According to the Laws of Fluid Dynamics, this Bumblebee CAN Fly!");//TODO Change Bumble Texture
		
		// Rocky + Military = Tera
		make(20300, "Basalzed Bumblebee"        , "");
		make(20310, "Quakeing Bumblebee"        , "With a mighty White Beard!");
		make(20320, "Earth Bound Bumblebee"     , "PK Bumble!");
		make(20330, "Tera Bumble"               , "By your Powers combined, I am Captain Bumblebee!");
		
		// Jungle + Water = Swamp
		// Rocky  + Sandy = Red Sand?
		// Rocky  + Frozen = Coal
		// Rocky  + Nether = Heavy Metal
		// Royal  + ??? = Heroic
		// Frosty + Nether = Gloomstone
	}
	
	@Override
	public ItemStack bumbleProductStack(ItemStack aBumbleBee, short aMetaData, long aStacksize, int aProductIndex) {
		switch(aMetaData / 100) {
		case   0: return IL.Comb_Honey      .get(aStacksize);
		case   1: return IL.Comb_Water      .get(aStacksize);
		case   2: return IL.Comb_Magic      .get(aStacksize);
		case   3: return IL.Comb_Nether     .get(aStacksize);
		case   4: return IL.Comb_End        .get(aStacksize);
		case   5: return IL.Comb_Rock       .get(aStacksize);
		case   6: return IL.Comb_Jungle     .get(aStacksize);
		case   7: return IL.Comb_Frozen     .get(aStacksize);
		case   8: return IL.Comb_Shroom     .get(aStacksize);
		case   9: return IL.Comb_Sandy      .get(aStacksize);
		case 100: return IL.Comb_Clay       .get(aStacksize);
		case 101: return IL.Comb_Sticky     .get(aStacksize);
		case 102: return IL.Comb_Royal      .get(aStacksize);
		case 103: return IL.Comb_Soul       .get(aStacksize);
		case 104: return IL.Comb_Amnesic    .get(aStacksize);
		case 105: return IL.Comb_Military   .get(aStacksize);
		case 200: return IL.Comb_Pyro       .get(aStacksize);
		case 201: return IL.Comb_Cryo       .get(aStacksize);
		case 202: return IL.Comb_Aero       .get(aStacksize);
		case 203: return IL.Comb_Tera       .get(aStacksize);
		default : return IL.Comb_Honey      .get(aStacksize);
		}
	}
	
	@Override
	public ChunkCoordinates bumbleCanProduce(World aWorld, int aX, int aY, int aZ, ItemStack aBumbleBee, short aMetaData, int aDistance) {
		boolean temp = T;
		for (byte tSide : ALL_SIDES_VALID) if (WD.oxygen(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide])) {temp = F; break;}
		if (temp) return null;

		aDistance = Math.abs(aDistance);
		int[] tOrderX = RNGSUS.nextBoolean() ? aDistance < SCANS_POS.length ? SCANS_POS[aDistance] : SCANS_POS[SCANS_POS.length-1] : aDistance < SCANS_NEG.length ? SCANS_NEG[aDistance] : SCANS_NEG[SCANS_NEG.length-1];
		int[] tOrderY = RNGSUS.nextBoolean() ? aDistance < SCANS_POS.length ? SCANS_POS[aDistance] : SCANS_POS[SCANS_POS.length-1] : aDistance < SCANS_NEG.length ? SCANS_NEG[aDistance] : SCANS_NEG[SCANS_NEG.length-1];
		int[] tOrderZ = RNGSUS.nextBoolean() ? aDistance < SCANS_POS.length ? SCANS_POS[aDistance] : SCANS_POS[SCANS_POS.length-1] : aDistance < SCANS_NEG.length ? SCANS_NEG[aDistance] : SCANS_NEG[SCANS_NEG.length-1];
		switch(aMetaData / 100) {
		case   1:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				if (WD.anywater(aWorld, aX+i, aY+j, aZ+k)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   2:
			if (BIOMES_MAGICAL.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName)) return new ChunkCoordinates(aX, aY, aZ);
			Block tThaumcraft = ST.block(MD.TC, "blockCustomPlant");
			if (tThaumcraft != NB) for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				if (WD.block(aWorld, aX+i, aY+j, aZ+k, F) == tThaumcraft) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   3: case 200:
			Block tFireFlower  = ST.block(MD.BoP , "flowers2", null);
			Block tWartCrimson = ST.block(MD.NeLi, "CrimsonWartCrop", null);
			Block tWartWarped  = ST.block(MD.NeLi, "WarpedWartCrop", null);
			Block tWartSoggy   = ST.block(MD.NeLi, "SoggyWartCrop", null);
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == NB) continue;
				if (tBlock == Blocks.nether_wart     ) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock == tWartCrimson           ) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock == tWartWarped            ) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock == tWartSoggy             ) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (IL.NeLi_Wither_Rose.equal(tBlock)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (IL.EtFu_Wither_Rose.equal(tBlock)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock == tFireFlower) {
					if (WD.meta(aWorld, aX+i, aY+j, aZ+k) == 2) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
					continue;
				}
			}
			return null;
		case   4: case 202:
			Block tChorusFlower = ST.block(MD.EtFu, "chorus_flower", null);
			if (tChorusFlower == null) {
				if (aWorld.provider.dimensionId == 1 || BIOMES_END.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName)) return new ChunkCoordinates(aX, aY, aZ);
				for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
					Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
					if (tBlock == Blocks.end_portal || tBlock == Blocks.dragon_egg) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				}
				return null;
			}
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == tChorusFlower || tBlock == Blocks.dragon_egg) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   5: case 203:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == Blocks.mossy_cobblestone || tBlock == Blocks.cobblestone || tBlock == Blocks.stone) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				byte tMeta = WD.meta(aWorld, aX+i, aY+j, aZ+k);
				if (tBlock == Blocks.stonebrick && tMeta == 1) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock instanceof BlockStones && (BlockStones.SPAWNABLE[tMeta] || BlockStones.MOSSY[tMeta])) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (WD.stone(tBlock, tMeta)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);;
			}
			return null;
		case   6:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				if (WD.block(aWorld, aX+i, aY+j, aZ+k, F) == Blocks.cocoa) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   7: case 201:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == Blocks.ice || tBlock == Blocks.snow_layer || tBlock == Blocks.snow || tBlock == Blocks.packed_ice) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   8:
			Block tMushroom = ST.block(MD.BoP, "mushrooms", null);
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == Blocks.mycelium || tBlock == Blocks.red_mushroom || tBlock == Blocks.brown_mushroom || tBlock == tMushroom || tBlock == Blocks.red_mushroom_block || tBlock == Blocks.brown_mushroom_block) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case   9: case 105:
			Block tCactus1 = ST.block(MD.BoP, "plants", null), tCactus2 = ST.block(MD.ARS, "desertNova", null);
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == Blocks.cactus || tBlock == tCactus2 || tBlock == BlocksGT.FlowersB) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
				if (tBlock == tCactus1) {
					if (WD.meta(aWorld, aX+i, aY+j, aZ+k) == 12) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
					continue;
				}
				if (tBlock == Blocks.flower_pot) {
					TileEntity tTileEntity = WD.te(aWorld, aX+i, aY+j, aZ+k, F);
					if (tTileEntity instanceof TileEntityFlowerPot) {
						if (Block.getBlockFromItem(((TileEntityFlowerPot)tTileEntity).getFlowerPotItem()) == Blocks.cactus) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
					}
					continue;
				}
			}
			return null;
		case 100:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == Blocks.clay || (tBlock == BlocksGT.Diggables && BlockDiggable.IS_CLAY[WD.meta(aWorld, aX+i, aY+j, aZ+k)])) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case 101:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				Block tBlock = WD.block(aWorld, aX+i, aY+j, aZ+k, F);
				if (tBlock == NB) continue;
				if (tBlock == IL.IC2_Log_Rubber.block()) {if (WD.meta(aWorld, aX+i, aY+j, aZ+k) == 0) continue; return new ChunkCoordinates(aX+i, aY+j, aZ+k);}
				if (tBlock instanceof MultiTileEntityBlock && WD.te(aWorld, aX+i, aY+j, aZ+k, F) instanceof MultiTileEntityResinHoleRubber) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		case 103:
			for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) {
				if (OM.is(OD.soulsand, ST.make(WD.block(aWorld, aX+i, aY+j, aZ+k, F), 1, W))) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		default:
			if (RNGSUS.nextBoolean()) {
				for (int j : tOrderY) for (int i : tOrderX) for (int k : tOrderZ) if (checkFlowers(aWorld, aX+i, aY+j, aZ+k)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			} else {
				for (int j : tOrderY) for (int k : tOrderZ) for (int i : tOrderX) if (checkFlowers(aWorld, aX+i, aY+j, aZ+k)) return new ChunkCoordinates(aX+i, aY+j, aZ+k);
			}
			return null;
		}
	}
	
	public String getFlowerTooltip(short aMetaData) {
		switch(aMetaData / 100) {
		case   1:           return "Water";
		case   2:           return "Magical Biome or Thaumic Flowers";
		case   3: case 200: return MD.BoP.mLoaded ? "Netherwart or Burning Blossoms" : "Netherwart";
		case   4: case 202: return MD.EtFu.mLoaded ? "Chorus Flower or Dragon Egg" : "End Portal, End Biome or Dragon Egg";
		case   5: case 203: return "Stone, Cobble or Mossy";
		case   6:           return "Cocoa";
		case   7: case 201: return "Snow or Ice";
		case   8:           return "Mycelium or Mushrooms";
		case   9: case 105: return "Desert Flowers and Cacti (even potted ones work)";
		case 100:           return "Raw Clay Blocks";
		case 101:           return MD.IC2.mLoaded ? "Rubber Tree Resin Holes (IC2 or GT6)" : "Rubber Tree Resin Holes";
		case 103:           return "Soul Sand Blocks";
		default :           return "Flowers (even potted ones work)";
		}
	}
	
	@Override
	public ItemStack bumbleCombine(ItemStack aBumbleBeeA, short aMetaDataA, ItemStack aBumbleBeeB, short aMetaDataB, byte aBumbleType, Random aRandom) {
		// Special Case to give even chances to Addon Bumbles.
		if (aBumbleBeeB.getItem() != ItemsGT.BUMBLEBEES) return ((IItemBumbleBee)aBumbleBeeB.getItem()).bumbleCombine(aBumbleBeeA, aMetaDataA, aBumbleBeeB, aMetaDataB, aBumbleType, aRandom);
		if (aBumbleBeeA.getItem() != ItemsGT.BUMBLEBEES) return ((IItemBumbleBee)aBumbleBeeA.getItem()).bumbleCombine(aBumbleBeeA, aMetaDataA, aBumbleBeeB, aMetaDataB, aBumbleType, aRandom);
		// The Normal Case if the other Bumble does not match. Should not be possible to call this one in my own class anymore though, because above.
		if (aBumbleBeeB.getItem() != this) return ST.copyAmountAndMeta(1, (aMetaDataA/10)*10+aBumbleType, aBumbleBeeA);
		if (aBumbleBeeA.getItem() != this) return ST.copyAmountAndMeta(1, (aMetaDataB/10)*10+aBumbleType, aBumbleBeeB);
		// And all the GT6 Bumblebee breeding cases.
		switch(aMetaDataA / 10) {
		case    3:
			switch(aMetaDataB / 10) {
			case   13: return ST.make(this, 1, 10100+aBumbleType);
			case   53: return ST.make(this, 1, 10000+aBumbleType);
			case   93: return ST.make(this, 1, 10200+aBumbleType);
			}
			break;
		case   13:
			switch(aMetaDataB / 10) {
			case    3: return ST.make(this, 1, 10100+aBumbleType);
			case   53: return ST.make(this, 1, 10400+aBumbleType);
			}
			break;
		case   33:
			switch(aMetaDataB / 10) {
			case   43: return ST.make(this, 1, 10300+aBumbleType);
			case 1053: return ST.make(this, 1, 20000+aBumbleType);
			}
			break;
		case   43:
			switch(aMetaDataB / 10) {
			case   33: return ST.make(this, 1, 10300+aBumbleType);
			case 1053: return ST.make(this, 1, 20200+aBumbleType);
			}
			break;
		case   53:
			switch(aMetaDataB / 10) {
			case    3: return ST.make(this, 1, 10000+aBumbleType);
			case   13: return ST.make(this, 1, 10400+aBumbleType);
			case 1053: return ST.make(this, 1, 20300+aBumbleType);
			}
			break;
		case   63:
			switch(aMetaDataB / 10) {
			case   93: return ST.make(this, 1, 10500+aBumbleType);
			}
			break;
		case   73:
			switch(aMetaDataB / 10) {
			case 1053: return ST.make(this, 1, 20100+aBumbleType);
			}
			break;
		case   93:
			switch(aMetaDataB / 10) {
			case    3: return ST.make(this, 1, 10200+aBumbleType);
			case   63: return ST.make(this, 1, 10500+aBumbleType);
			}
			break;
		case 1053:
			switch(aMetaDataB / 10) {
			case   33: return ST.make(this, 1, 20000+aBumbleType);
			case   43: return ST.make(this, 1, 20200+aBumbleType);
			case   53: return ST.make(this, 1, 20300+aBumbleType);
			case   73: return ST.make(this, 1, 20100+aBumbleType);
			}
			break;
		}
		return ST.make(this, 1, (aMetaDataA/10)*10+aBumbleType);
	}
	
	@Override
	public boolean bumbleAttack(ItemStack aBumbleBee, short aMetaData, EntityLivingBase aAttacked) {
		if (UT.Entities.isWearingFullInsectHazmat(aAttacked)) return F;
		boolean
		  tSkeleton = (aAttacked instanceof EntitySkeleton || (aAttacked instanceof EntityHorse && ((EntityHorse)aAttacked).getHorseType() == 4))
		, tSnowGolem = (aAttacked.getClass() == EntitySnowman.class)
		, tIronGolem = (aAttacked instanceof EntityIronGolem)
		, tPlayer = (aAttacked instanceof EntityPlayer)
		;
		switch(aMetaData / 100) {
		default: return !tSkeleton && !tSnowGolem && !tIronGolem && aAttacked.attackEntityFrom(DamageSources.getBumbleDamage(), (1+((aMetaData / 10) % 10))  );
		case   9: return !tSkeleton && !tSnowGolem && !tIronGolem && aAttacked.attackEntityFrom(DamageSources.getBumbleDamage(), (1+((aMetaData / 10) % 10))*2);
		case   6: return !tSkeleton && !tSnowGolem && !tIronGolem && aAttacked.attackEntityFrom(DamageSources.getBumbleDamage(), (1+((aMetaData / 10) % 10))*4);
		case   8: return F;
		case   3: if (!tSkeleton && !tIronGolem && aAttacked.attackEntityFrom(DamageSources.getBumbleDamage().setFireDamage(), (1+((aMetaData / 10) % 10))*2)) {aAttacked.setFire((1+((aMetaData / 10) % 10))*10); return T;} return F;
		case 105: case 200: case 201: case 202: case 203: return !tPlayer && aAttacked.attackEntityFrom(DamageSources.getBumbleDamage(), (1+((aMetaData / 10) % 10))*10);
		}
	}
	
	@Override
	public int bumbleMutateChance(ItemStack aBumbleBee, short aMetaData) {
		switch((aMetaData / 10) % 10) {
		case  0: return 500;
		case  1: return 500;
		case  2: return 250;
		case  3: return 25;
		default: return 0;
		}
	}
	
	@Override
	public ItemStack bumbleMutate(ItemStack aBumbleBee, short aMetaData, Random aRandom) {
		switch((aMetaData / 10) % 10) {
		case  0: return ST.copyMeta(aMetaData+10, aBumbleBee);
		case  1: return ST.copyMeta(aMetaData+(aRandom.nextBoolean()?+10:-10), aBumbleBee);
		case  2: return ST.copyMeta(aMetaData+(aRandom.nextBoolean()?+10:-10), aBumbleBee);
		case  3: return ST.copyMeta(aMetaData-10, aBumbleBee);
		default: return ST.copy(aBumbleBee);
		}
	}
	
	@Override
	public int bumbleProductChance(ItemStack aBumbleBee, short aMetaData, int aProductIndex) {
		switch((aMetaData / 10) % 10) {
		case  0: return  2500;
		case  1: return  5000;
		case  2: return  7500;
		case  3: return 10000;
		default: return 10000;
		}
	}
	
	@Override
	public int bumbleProductCount(ItemStack aBumbleBee, short aMetaData) {
		return 1;
	}
	
	@Override
	public ChunkCoordinates bumbleCanProduct(World aWorld, int aX, int aY, int aZ, ItemStack aBumbleBee, short aMetaData, int aProductIndex) {
		return new ChunkCoordinates(aX, aY, aZ);
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		String tTooltip = getFlowerTooltip(ST.meta_(aStack));
		if (UT.Code.stringValid(tTooltip)) aList.add(LH.Chat.CYAN + "Requirement: " + LH.Chat.WHITE + tTooltip);
		NBTTagCompound aBumbleTag = null;
		if (aStack.hasTagCompound()) aBumbleTag = aStack.getTagCompound().getCompoundTag("gt.bumble");
		if (aBumbleTag == null || aBumbleTag.hasNoTags()) {
			aList.add(LH.Chat.BLINKING_RED + "No Genetic Data to display");
			aList.add(LH.Chat.CYAN + "Generates random 'Outsider-Plains-Biome' Genes when used");
		} else {
			if (ST.meta_(aStack) % 10 < 5) {
				aList.add(LH.Chat.RED + "Not scanned yet!");
			} else {
				aList.add(LH.Chat.CYAN + "Humidity: " + LH.Chat.WHITE + Util.getHumidityMin(aBumbleTag) + " to " + Util.getHumidityMax(aBumbleTag) + LH.Chat.RED + "   Temp: " + LH.Chat.WHITE + Util.getTemperatureMin(aBumbleTag) + "K to " + Util.getTemperatureMax(aBumbleTag) + "K");
				aList.add(LH.Chat.GREEN + "Offspring: " + LH.Chat.WHITE + Util.getOffspring(aBumbleTag) + LH.Chat.ORANGE + "   Life: " + LH.Chat.WHITE + Util.getLifeSpan(aBumbleTag) + " ticks");
				aList.add(LH.Chat.YELLOW + "Eff: " + LH.Chat.WHITE + LH.percent(Util.getWorkForce(aBumbleTag)) + "%" + LH.Chat.RED + "   Aggro: " + LH.Chat.WHITE + LH.percent(Util.getAggressiveness(aBumbleTag)) + "%");
				if (Util.getDayActive(aBumbleTag)) {
					if (Util.getNightActive(aBumbleTag)) {
						aList.add(LH.Chat.RAINBOW + "Doesn't take breaks");
					} else {
						aList.add(LH.Chat.PURPLE + "Works at Day");
					}
				} else {
					if (Util.getNightActive(aBumbleTag)) {
						aList.add(LH.Chat.PURPLE + "Works at Night");
					} else {
						aList.add(LH.Chat.BLINKING_RED + "Doesn't work at any Time (BUG!!!)");
					}
				}
				
				if (Util.getRainproof(aBumbleTag)) {
					if (Util.getStormproof(aBumbleTag)) {
						aList.add(LH.Chat.RAINBOW + "Can fly during any Weather");
					} else {
						aList.add(LH.Chat.PURPLE + "Can fly during Rain, but not during Storms");
					}
				} else {
					if (Util.getStormproof(aBumbleTag)) {
						aList.add(LH.Chat.PURPLE + "Can fly during Storms, but not when it Rains");
					} else {
						aList.add(LH.Chat.RED + "Weak to Weather");
					}
				}
				
				if (Util.getOutsideActive(aBumbleTag)) {
					if (Util.getInsideActive(aBumbleTag)) {
						aList.add(LH.Chat.RAINBOW + "Doesn't care whether to bee In- or Outside");
					} else {
						aList.add(LH.Chat.PURPLE + "Needs to bee Outside");
					}
				} else {
					if (Util.getInsideActive(aBumbleTag)) {
						aList.add(LH.Chat.PURPLE + "Needs to bee Inside");
					} else {
						aList.add(LH.Chat.BLINKING_RED + "Doesn't work anywhere (BUG!!!)");
					}
				}
			}
		}
	}
	
	@Override public ItemStack bumbleScan (ItemStack aBumbleBee) {ItemStack rStack = ST.copy(aBumbleBee); short aMeta = ST.meta_(rStack); if (aMeta % 10 < 5) ST.meta_(rStack, aMeta + 5); return rStack;}
	@Override public ItemStack bumbleKill (ItemStack aBumbleBee) {ItemStack rStack = ST.copy(aBumbleBee); short aMeta = ST.meta_(rStack); if (aMeta % 5 != 4) ST.meta_(rStack, (aMeta / 5) * 5 + 4); return rStack;}
	@Override public ItemStack bumbleCrown(ItemStack aBumbleBee) {ItemStack rStack = ST.copy(aBumbleBee); short aMeta = ST.meta_(rStack); if (aMeta % 5 != 2) ST.meta_(rStack, (aMeta / 5) * 5 + 2); return rStack;}
	@Override public boolean bumbleEqual(ItemStack aBumbleBeeA, short aMetaDataA, ItemStack aBumbleBeeB, short aMetaDataB) {return aBumbleBeeA.getItem() == aBumbleBeeB.getItem() && aMetaDataA / 10 == aMetaDataB / 10;}
	@Override public byte bumbleType(ItemStack aBumbleBee) {return (byte)(ST.meta_(aBumbleBee) % 10);}
	
	@Override public ItemStack bumbleDrone      (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10);}
	@Override public ItemStack bumblePrincess   (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 1);}
	@Override public ItemStack bumbleQueen      (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 2);}
	@Override public ItemStack bumbleDead       (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 4);}
	@Override public ItemStack bumbleDrone_     (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 5);}
	@Override public ItemStack bumblePrincess_  (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 6);}
	@Override public ItemStack bumbleQueen_     (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 7);}
	@Override public ItemStack bumbleDead_      (ItemStack aBumbleBee, short aMetaData) {return ST.make(this, 1, (aMetaData / 10) * 10 + 9);}
	
	@Override public int getDefaultStackLimit(ItemStack aStack) {return 64;}
	
	public void make(int aSpeciesID, String aName, String aTooltip) {
		for (int i : new int[] {0, 1, 4}) {
		for (String tFluid : FluidsGT.HONEY) if (FL.exists(tFluid))
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(ST.make(this, 1, aSpeciesID+i), OP.plateTiny.mat(MT.Paper, 1)), ST.array(ST.make(this, 1, aSpeciesID+i+5)), null, null, FL.array(FL.make(tFluid, 10)), null, 64, 16, 0);
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(ST.make(this, 1, aSpeciesID+i), OP.plateTiny.mat(MT.Paper, 1)), ST.array(ST.make(this, 1, aSpeciesID+i+5)), null, null, FL.array(FL.Honeydew.make(10)), null, 64, 16, 0);
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(ST.make(this, 1, aSpeciesID+i+5, "Was already scanned, auto-skipping")), ST.array(ST.make(this, 1, aSpeciesID+i+5, "Just passed to the Output")), null, null, null, null,  1, 16, 0);
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(ST.make(this, 1, aSpeciesID+i+5, "Was already scanned, auto-skipping")), ST.array(ST.make(this, 1, aSpeciesID+i+5, "Just passed to the Output")), null, null, null, null,  1, 16, 0);
		}
		
		addItem(aSpeciesID+0, aName + " Drone"              , aTooltip, "gt:bumbledrone"    , TC.stack(TC.BESTIA , 1));
		addItem(aSpeciesID+1, aName + " Princess"           , aTooltip, "gt:bumbleprincess" , TC.stack(TC.BESTIA , 2));
		addItem(aSpeciesID+2, aName + " Queen"              , aTooltip, "gt:bumblequeen"    , TC.stack(TC.BESTIA , 2), TD.Creative.HIDDEN);
		addItem(aSpeciesID+4, aName + " (Dead)"             , aTooltip, "gt:bumbledead"     , TC.stack(TC.MORTUUS, 1), TD.Creative.HIDDEN);
		addItem(aSpeciesID+5, aName + " Drone (Scanned)"    , aTooltip, "gt:bumbledrone"    , TC.stack(TC.BESTIA , 1), TC.stack(TC.COGNITIO, 1), new OreDictItemData(MT.Paper, U9));
		addItem(aSpeciesID+6, aName + " Princess (Scanned)" , aTooltip, "gt:bumbleprincess" , TC.stack(TC.BESTIA , 2), TC.stack(TC.COGNITIO, 1), new OreDictItemData(MT.Paper, U9));
		addItem(aSpeciesID+7, aName + " Queen (Scanned)"    , aTooltip, "gt:bumblequeen"    , TC.stack(TC.BESTIA , 2), TC.stack(TC.COGNITIO, 1), new OreDictItemData(MT.Paper, U9), TD.Creative.HIDDEN);
		addItem(aSpeciesID+9, aName + " (Dead & Scanned)"   , aTooltip, "gt:bumbledead"     , TC.stack(TC.MORTUUS, 1), TC.stack(TC.COGNITIO, 1), new OreDictItemData(MT.Paper, U9), TD.Creative.HIDDEN);
		
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+0), ST.make(this, 1, aSpeciesID+2));
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+1), ST.make(this, 1, aSpeciesID+2));
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+4), ST.make(this, 1, aSpeciesID+2));
		
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+5), ST.make(this, 1, aSpeciesID+7));
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+6), ST.make(this, 1, aSpeciesID+7));
		ItemsGT.addNEIRedirect(ST.make(this, 1, aSpeciesID+9), ST.make(this, 1, aSpeciesID+7));
		
		ItemStack[] tOutputs = new ItemStack[bumbleProductCount(NI, (short)(aSpeciesID+2))];
		long[] tChances = new long[tOutputs.length];
		for (int i = 0; i < tOutputs.length; i++) {
			tOutputs[i] = bumbleProductStack(NI, (short)(aSpeciesID+2), 1, i);
			tChances[i] = bumbleProductChance(NI, (short)(aSpeciesID+2), i);
		}
		
		RM.BumbleQueens.addFakeRecipe(F, ST.array(ST.make(this, 1, aSpeciesID+2), ST.make(this, 1, aSpeciesID+7)), tOutputs, null, tChances, null, null, 0, 0, 0);
	}
	
	public static boolean checkFlowers(World aWorld, int aX, int aY, int aZ) {
		Block aBlock = WD.block(aWorld, aX, aY, aZ, F);
		if (aBlock == NB) return F;
		if (aBlock == Blocks.flower_pot) {
			TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, F);
			if (tTileEntity instanceof TileEntityFlowerPot) {
				aBlock = Block.getBlockFromItem(((TileEntityFlowerPot)tTileEntity).getFlowerPotItem());
				return aBlock == Blocks.yellow_flower || aBlock == Blocks.red_flower;
			}
			return F;
		}
		if (aBlock == Blocks.double_plant) {
			byte tMeta = WD.meta(aWorld, aX, aY, aZ);
			return tMeta != 2 && tMeta != 3;
		}
		if (BlocksGT.FLOWERS.contains(aBlock)) {
			Block tBlock = WD.block(aWorld, aX, aY-1, aZ);
			if (aBlock == tBlock || aBlock == WD.block(aWorld, aX, aY+1, aZ)) return F;
			int tX = aX+RNGSUS.nextInt(5)-2, tY = aY+RNGSUS.nextInt(3)-2, tZ = aZ+RNGSUS.nextInt(5)-2;
			if (tBlock == WD.block(aWorld, tX, tY, tZ)) {
				tBlock = WD.block(aWorld, tX, ++tY, tZ);
				byte tMeta = WD.meta(aWorld, aX, aY, aZ);
				if (WD.air(aWorld, tX, tY, tZ, tBlock) || WD.grass(tBlock, tMeta)) {
					WD.set(aWorld, tX, tY, tZ, aBlock, tMeta, 3);
				}
			}
			return T;
		}
		return F;
	}
	
	public IIcon PRINCESS, QUEEN, SCANNED, DEAD;
	
	@Override public IIcon getIconIndex(ItemStack aStack) {return getIconFromDamage(ST.meta(aStack));}
	@Override public IIcon getIconFromDamage(int aMetaData) {aMetaData /= 10; aMetaData *= 10; return UT.Code.exists(aMetaData, mIconList) ? mIconList[aMetaData][0] : Textures.ItemIcons.RENDERING_ERROR.getIcon(0);}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {return getIcon(aStack, aRenderPass);}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass) {return getIconFromDamageForRenderPass(ST.meta_(aStack), aRenderPass);}
	
	@Override public boolean requiresMultipleRenderPasses() {return T;}
	
	@Override
	public int getRenderPasses(int aMetaData) {
		switch(aMetaData % 10) {
		case 6: case 7: case 8: case 9: return 3;
		case 1: case 2: case 4: case 5: return 2;
		default: return 1;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		PRINCESS    = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/overlay_princess");
		QUEEN       = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/overlay_queen");
		SCANNED     = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/overlay_scanned");
		DEAD        = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/overlay_dead");

		for (short aMeta = 0, tMaxMeta = (short)mEnabledItems.length(); aMeta < tMaxMeta; aMeta+=10) if (mEnabledItems.get(aMeta)) {
			mIconList[aMeta][0] = aIconRegister.registerIcon(mModID + ":" + getUnlocalizedName() + "/" + aMeta);
		}
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		if (aRenderPass == 0) return getIconFromDamage(aMetaData);
		if (aRenderPass == 1) {
			switch(aMetaData % 10) {
			case 1: case 6: return PRINCESS;
			case 2: case 7: return QUEEN;
			case 3: case 8: return DEAD;
			case 4: case 9: return DEAD;
			}
		}
		return SCANNED;
	}
}
