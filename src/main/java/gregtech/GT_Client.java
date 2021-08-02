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

package gregtech;

import static gregapi.data.CS.*;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import gregapi.GT_API;
import gregapi.api.Abstract_Mod;
import gregapi.config.ConfigCategories;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.Sandwiches;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IconContainerCopied;
import gregapi.util.UT;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.entities.projectiles.EntityArrow_Potion;
import gregtech.render.GT_Renderer_Entity_Arrow;
import gregtech.render.PlayerModelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class GT_Client extends GT_Proxy {
	private final PlayerModelRenderer mPlayerRenderer = new PlayerModelRenderer(mSupporterListSilver, mSupporterListGold);
	
	public int addArmor(String aPrefix) {return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);}
	
	public GT_Client() {super();}
	
	@Override
	public void onProxyAfterPreInit(Abstract_Mod aMod, FMLPreInitializationEvent aEvent) {
		super.onProxyAfterPreInit(aMod, aEvent);
		new GT_Renderer_Entity_Arrow(EntityArrow_Material.class, "arrow");
		new GT_Renderer_Entity_Arrow(EntityArrow_Potion.class, "arrow_potions");
		
		BooksGT.BOOK_TEXTURES_BACK[255] = BooksGT.BOOK_TEXTURES_SIDE[255] = BlockTextureDefault.get(new IconContainerCopied(Blocks.cobblestone, 0, 0));
		
		BooksGT.BOOK_TEXTURES_BACK[  1] = BlockTextureDefault.get("books/BOOK_VANILLA_BACK");
		BooksGT.BOOK_TEXTURES_BACK[  2] = BlockTextureDefault.get("books/BOOK_ENCHANTED_BACK");
		BooksGT.BOOK_TEXTURES_BACK[  3] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Black);
		BooksGT.BOOK_TEXTURES_BACK[  4] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_White);
		BooksGT.BOOK_TEXTURES_BACK[  5] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Red);
		BooksGT.BOOK_TEXTURES_BACK[  6] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Green);
		BooksGT.BOOK_TEXTURES_BACK[  7] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Blue);
		BooksGT.BOOK_TEXTURES_BACK[  8] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Cyan);
		BooksGT.BOOK_TEXTURES_BACK[  9] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Magenta);
		BooksGT.BOOK_TEXTURES_BACK[ 10] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Yellow);
		BooksGT.BOOK_TEXTURES_BACK[ 11] = BlockTextureDefault.get("books/BOOK_MATDICT_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 12] = BlockTextureDefault.get("books/BOOK_GT_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 13] = BlockTextureDefault.get("books/BOOK_THAUMONOMICON_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 14] = BlockTextureDefault.get("books/BOOK_CRIMSONRITES_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 15] = BlockTextureDefault.get("books/STONETABLET_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 16] = BlockTextureDefault.get("books/BOOK_MAPS_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 17] = BlockTextureDefault.get("books/BOOK_CRAFTING_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 18] = BlockTextureDefault.get("books/SCROLL_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 19] = BlockTextureDefault.get("books/BOOK_RAILS_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 20] = BlockTextureDefault.get("books/BOOK_WOLVES_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 21] = BlockTextureDefault.get("books/BOOK_WITCHES_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 22] = BlockTextureDefault.get("books/BOOK_BREWING_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 23] = BlockTextureDefault.get("books/BOOK_VAMPIRES_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 24] = BlockTextureDefault.get("books/BOOK_REIKA_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 25] = BlockTextureDefault.get("books/FOLDER_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 26] = BlockTextureDefault.get("books/FOLDER_RED_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 27] = BlockTextureDefault.get("books/FOLDER_GREEN_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 28] = BlockTextureDefault.get("books/FOLDER_BLUE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 29] = BlockTextureDefault.get("books/CLIPBOARD_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 30] = BlockTextureDefault.get("books/RECORD_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 31] = BlockTextureDefault.get("books/PRINTINGPLATE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 32] = BlockTextureDefault.get("books/BOOK_CATALOGUE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 33] = BlockTextureDefault.get("books/LETTER_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 34] = BlockTextureDefault.get("books/FRAME_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 35] = BlockTextureDefault.get("books/FLOPPY_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 36] = BlockTextureDefault.get("books/VHS_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 37] = BlockTextureDefault.get("books/ID_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 38] = BlockTextureDefault.get("books/AE_PRESS_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 39] = BlockTextureDefault.get("books/BOOK_FZ_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 40] = BlockTextureDefault.get("books/BOOK_OC_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 41] = BlockTextureDefault.get("books/BOOK_IE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 42] = BlockTextureDefault.get("books/BOOK_BOTANIA_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 43] = BlockTextureDefault.get("books/TABLET_COMPUTER_METALLIC_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 44] = BlockTextureDefault.get("books/TABLET_COMPUTER_GOLD_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 45] = BlockTextureDefault.get("books/EXTRUDER_SHAPE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 46] = BlockTextureDefault.get("books/AE_CELL_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 47] = BlockTextureDefault.get("books/AE_HANDHELD_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 48] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Orange);
		BooksGT.BOOK_TEXTURES_BACK[ 49] = BlockTextureDefault.get("books/BOOK_COLORED_BACK", DYE_Purple);
		BooksGT.BOOK_TEXTURES_BACK[ 50] = BlockTextureDefault.get("books/DIV_SIGIL_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 51] = BlockTextureDefault.get("books/LETTER_XL_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 52] = BlockTextureDefault.get("books/LETTER_XXL_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 53] = BlockTextureDefault.get("books/BOOK_DUSTY_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 54] = BlockTextureDefault.get("books/HARD_DRIVE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 55] = BlockTextureDefault.get("books/EXTRUDER_SIMPLE_SHAPE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 56] = BlockTextureDefault.get("books/THAUMOMETER_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 57] = BlockTextureDefault.get("books/TAPE_WHITE_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 58] = BlockTextureDefault.get("books/TAPE_GRAY_BACK");
		BooksGT.BOOK_TEXTURES_BACK[ 59] = BlockTextureDefault.get("books/TAPE_BLACK_BACK");
		
		BooksGT.BOOK_TEXTURES_SIDE[  1] = BlockTextureDefault.get("books/BOOK_VANILLA_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[  2] = BlockTextureDefault.get("books/BOOK_ENCHANTED_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[  3] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Black);
		BooksGT.BOOK_TEXTURES_SIDE[  4] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_White);
		BooksGT.BOOK_TEXTURES_SIDE[  5] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Red);
		BooksGT.BOOK_TEXTURES_SIDE[  6] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Green);
		BooksGT.BOOK_TEXTURES_SIDE[  7] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Blue);
		BooksGT.BOOK_TEXTURES_SIDE[  8] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Cyan);
		BooksGT.BOOK_TEXTURES_SIDE[  9] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Magenta);
		BooksGT.BOOK_TEXTURES_SIDE[ 10] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Yellow);
		BooksGT.BOOK_TEXTURES_SIDE[ 11] = BlockTextureDefault.get("books/BOOK_MATDICT_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 12] = BlockTextureDefault.get("books/BOOK_GT_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 13] = BlockTextureDefault.get("books/BOOK_THAUMONOMICON_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 14] = BlockTextureDefault.get("books/BOOK_CRIMSONRITES_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 15] = BlockTextureDefault.get("books/STONETABLET_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 16] = BlockTextureDefault.get("books/BOOK_MAPS_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 17] = BlockTextureDefault.get("books/BOOK_CRAFTING_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 18] = BlockTextureDefault.get("books/SCROLL_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 19] = BlockTextureDefault.get("books/BOOK_RAILS_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 20] = BlockTextureDefault.get("books/BOOK_WOLVES_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 21] = BlockTextureDefault.get("books/BOOK_WITCHES_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 22] = BlockTextureDefault.get("books/BOOK_BREWING_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 23] = BlockTextureDefault.get("books/BOOK_VAMPIRES_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 24] = BlockTextureDefault.get("books/BOOK_REIKA_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 25] = BlockTextureDefault.get("books/FOLDER_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 26] = BlockTextureDefault.get("books/FOLDER_RED_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 27] = BlockTextureDefault.get("books/FOLDER_GREEN_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 28] = BlockTextureDefault.get("books/FOLDER_BLUE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 29] = BlockTextureDefault.get("books/CLIPBOARD_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 30] = BlockTextureDefault.get("books/RECORD_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 31] = BlockTextureDefault.get("books/PRINTINGPLATE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 32] = BlockTextureDefault.get("books/BOOK_CATALOGUE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 33] = BlockTextureDefault.get("books/LETTER_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 34] = BlockTextureDefault.get("books/FRAME_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 35] = BlockTextureDefault.get("books/FLOPPY_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 36] = BlockTextureDefault.get("books/VHS_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 37] = BlockTextureDefault.get("books/ID_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 38] = BlockTextureDefault.get("books/AE_PRESS_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 39] = BlockTextureDefault.get("books/BOOK_FZ_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 40] = BlockTextureDefault.get("books/BOOK_OC_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 41] = BlockTextureDefault.get("books/BOOK_IE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 42] = BlockTextureDefault.get("books/BOOK_BOTANIA_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 43] = BlockTextureDefault.get("books/TABLET_COMPUTER_METALLIC_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 44] = BlockTextureDefault.get("books/TABLET_COMPUTER_GOLD_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 45] = BlockTextureDefault.get("books/EXTRUDER_SHAPE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 46] = BlockTextureDefault.get("books/AE_CELL_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 47] = BlockTextureDefault.get("books/AE_HANDHELD_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 48] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Orange);
		BooksGT.BOOK_TEXTURES_SIDE[ 49] = BlockTextureDefault.get("books/BOOK_COLORED_SIDE", DYE_Purple);
		BooksGT.BOOK_TEXTURES_SIDE[ 50] = BlockTextureDefault.get("books/DIV_SIGIL_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 51] = BlockTextureDefault.get("books/LETTER_XL_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 52] = BlockTextureDefault.get("books/LETTER_XXL_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 53] = BlockTextureDefault.get("books/BOOK_DUSTY_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 54] = BlockTextureDefault.get("books/HARD_DRIVE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 55] = BlockTextureDefault.get("books/EXTRUDER_SIMPLE_SHAPE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 56] = BlockTextureDefault.get("books/THAUMOMETER_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 57] = BlockTextureDefault.get("books/TAPE_WHITE_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 58] = BlockTextureDefault.get("books/TAPE_GRAY_SIDE");
		BooksGT.BOOK_TEXTURES_SIDE[ 59] = BlockTextureDefault.get("books/TAPE_BLACK_SIDE");

		Sandwiches.INGREDIENT_TEXTURES_SIDES [255] = null;
		Sandwiches.INGREDIENT_TEXTURES_TOP   [255] = null;
		Sandwiches.INGREDIENT_MODEL_IDS      [255] = (byte)255;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[255] = (byte)  0;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [254] = BlockTextureDefault.get("sandwiches/TOAST_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [254] = BlockTextureDefault.get("sandwiches/TOAST_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [254] = (byte)254;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[254] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [253] = BlockTextureDefault.get("sandwiches/TOASTED_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [253] = BlockTextureDefault.get("sandwiches/TOASTED_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [253] = (byte)253;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[253] = (byte)  2;
		
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  0] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 0]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  0] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 0]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  0] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  0] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  1] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 1]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  1] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 1]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  1] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  1] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  2] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 2]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  2] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 2]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  2] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  2] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  3] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 3]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  3] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 3]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  3] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  3] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  4] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 4]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  4] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 4]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  4] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  4] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  5] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 5]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  5] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 5]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  5] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  5] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  6] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 6]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  6] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 6]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  6] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  6] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  7] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 7]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  7] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 7]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  7] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  7] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  8] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 8]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  8] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 8]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  8] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  8] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [  9] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[ 9]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [  9] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[ 9]);
		Sandwiches.INGREDIENT_MODEL_IDS      [  9] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[  9] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 10] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[10]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 10] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[10]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 10] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 10] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 11] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[11]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 11] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[11]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 11] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 11] = (byte)1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 12] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[12]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 12] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[12]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 12] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 12] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 13] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[13]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 13] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[13]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 13] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 13] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 14] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[14]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 14] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[14]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 14] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 14] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 15] = BlockTextureDefault.get("sandwiches/CONDIMENT_SIDES", DYES[15]);
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 15] = BlockTextureDefault.get("sandwiches/CONDIMENT_TOP"  , DYES[15]);
		Sandwiches.INGREDIENT_MODEL_IDS      [ 15] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 15] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 16] = BlockTextureDefault.get("sandwiches/ANANAS_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 16] = BlockTextureDefault.get("sandwiches/ANANAS_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 16] = (byte)  2;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 16] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 17] = BlockTextureDefault.get("sandwiches/BANANA_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 17] = BlockTextureDefault.get("sandwiches/BANANA_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 17] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 17] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 18] = BlockTextureDefault.get("sandwiches/CARROT_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 18] = BlockTextureDefault.get("sandwiches/CARROT_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 18] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 18] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 19] = BlockTextureDefault.get("sandwiches/CHEESE_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 19] = BlockTextureDefault.get("sandwiches/CHEESE_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 19] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 19] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 20] = BlockTextureDefault.get("sandwiches/CHILI_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 20] = BlockTextureDefault.get("sandwiches/CHILI_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 20] = (byte)  2;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 20] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 21] = BlockTextureDefault.get("sandwiches/CHOCOLATE_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 21] = BlockTextureDefault.get("sandwiches/CHOCOLATE_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 21] = (byte)  3;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 21] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 22] = BlockTextureDefault.get("sandwiches/COMB_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 22] = BlockTextureDefault.get("sandwiches/COMB_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 22] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 22] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 23] = BlockTextureDefault.get("sandwiches/CUCUMBER_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 23] = BlockTextureDefault.get("sandwiches/CUCUMBER_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 23] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 23] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 24] = BlockTextureDefault.get("sandwiches/FISH_RAW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 24] = BlockTextureDefault.get("sandwiches/FISH_RAW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 24] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 24] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 25] = BlockTextureDefault.get("sandwiches/FISH_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 25] = BlockTextureDefault.get("sandwiches/FISH_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 25] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 25] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 26] = BlockTextureDefault.get("sandwiches/HAM_RAW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 26] = BlockTextureDefault.get("sandwiches/HAM_RAW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 26] = (byte)  2;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 26] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 27] = BlockTextureDefault.get("sandwiches/HAM_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 27] = BlockTextureDefault.get("sandwiches/HAM_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 27] = (byte)  2;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 27] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 28] = BlockTextureDefault.get("sandwiches/LEMON_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 28] = BlockTextureDefault.get("sandwiches/LEMON_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 28] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 28] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 29] = BlockTextureDefault.get("sandwiches/MEAT_RAW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 29] = BlockTextureDefault.get("sandwiches/MEAT_RAW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 29] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 29] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 30] = BlockTextureDefault.get("sandwiches/MEAT_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 30] = BlockTextureDefault.get("sandwiches/MEAT_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 30] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 30] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 31] = BlockTextureDefault.get("sandwiches/ONION_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 31] = BlockTextureDefault.get("sandwiches/ONION_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 31] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 31] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 32] = BlockTextureDefault.get("sandwiches/RIPEYE_RAW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 32] = BlockTextureDefault.get("sandwiches/RIPEYE_RAW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 32] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 32] = (byte)  3;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 33] = BlockTextureDefault.get("sandwiches/RIPEYE_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 33] = BlockTextureDefault.get("sandwiches/RIPEYE_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 33] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 33] = (byte)  3;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 34] = BlockTextureDefault.get("sandwiches/SOYLENT_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 34] = BlockTextureDefault.get("sandwiches/SOYLENT_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 34] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 34] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 35] = BlockTextureDefault.get("sandwiches/TOFU_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 35] = BlockTextureDefault.get("sandwiches/TOFU_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 35] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 35] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 36] = BlockTextureDefault.get("sandwiches/TOMATO_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 36] = BlockTextureDefault.get("sandwiches/TOMATO_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 36] = (byte) 14;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 36] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 37] = BlockTextureDefault.get("sandwiches/BACON_RAW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 37] = BlockTextureDefault.get("sandwiches/BACON_RAW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 37] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 37] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 38] = BlockTextureDefault.get("sandwiches/BACON_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 38] = BlockTextureDefault.get("sandwiches/BACON_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 38] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 38] = (byte)  1;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 39] = BlockTextureDefault.get("sandwiches/CHUM_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 39] = BlockTextureDefault.get("sandwiches/CHUM_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 39] = (byte)  1;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 39] = (byte)  2;
		Sandwiches.INGREDIENT_TEXTURES_SIDES [ 40] = BlockTextureDefault.get("sandwiches/RAINBOW_SIDES");
		Sandwiches.INGREDIENT_TEXTURES_TOP   [ 40] = BlockTextureDefault.get("sandwiches/RAINBOW_TOP");
		Sandwiches.INGREDIENT_MODEL_IDS      [ 40] = (byte)252;
		Sandwiches.INGREDIENT_MODEL_THICKNESS[ 40] = (byte)  1;
	}
	
	private boolean FIRST_CLIENT_PLAYER_TICK = T;
	
	@SubscribeEvent
	public void onPlayerTickEventClient(PlayerTickEvent aEvent) {
		if (!aEvent.player.isDead && aEvent.phase == Phase.END && aEvent.side.isClient() && CLIENT_TIME > 20) {
			for (int i = 0; i < UT.Sounds.sPlayedSounds.size(); i++) if (UT.Sounds.sPlayedSounds.get(i).mTimer-- < 0) UT.Sounds.sPlayedSounds.remove(i--);
			
			if (aEvent.player == GT_API.api_proxy.getThePlayer()) {
				if (FIRST_CLIENT_PLAYER_TICK) {
					FIRST_CLIENT_PLAYER_TICK = F;
					ChatComponentText tLink;
					if (!mMessage.isEmpty() && ConfigsGT.CLIENT.get(ConfigCategories.news, mMessage, T)) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(mMessage));
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.DGRAY + ""));
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable message in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (mVersionOutdated) {
						aEvent.player.addChatComponentMessage(new ChatComponentText("Major GT6 Update released, for details visit"));
						tLink = new ChatComponentText(LH.Chat.BLUE + "https://gregtech.mechaenetia.com/1.7.10");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://gregtech.mechaenetia.com/1.7.10"));
						aEvent.player.addChatComponentMessage(tLink);
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable checker in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (MD.IC2.mLoaded && !MD.IC2C.mLoaded) {
						try {
							int tVersion = Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get(null)).substring(4, 7));
							if (tVersion < 827) {
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Please update IndustrialCraft!"));
								// IC2 Site doesn't support https.
								tLink = new ChatComponentText(LH.Chat.BLUE + "http://ic2api.player.to:8080/job/IC2_experimental/827/");
								tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://ic2api.player.to:8080/job/IC2_experimental/827/"));
								aEvent.player.addChatComponentMessage(tLink);
							}
						} catch(Throwable e) {/**/}
					}
					if (MD.TC.mLoaded) {
						try {
							if (Class.forName("com.chocohead.patcher.ThaumicFixer") != null) {
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Warning! Chocoheads ThaumicFixer needs to be uninstalled!"));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "Not uninstalling it can lead to crashes when viewing Aspects."));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "Lag is already fixed with a better Version of the ASM Code,"));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "that doesn't obliterate the Thaumcraft API for no reason."));
							}
						} catch(Throwable e) {/**/}
					}
					if (MD.COG.mLoaded && !MD.PFAA.mLoaded && ConfigsGT.CLIENT.get(ConfigCategories.general, "warnings_customoregen", T)) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Warning! CustomOreGen will screw up all GregTech Worldgen with its Default Configs!"));
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "If you don't even use CustomOreGen, I would highly recommend you to remove it."));
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable warning in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (WOODMANS_BDAY) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.WHITE+"<"+LH.Chat.GREEN+">:]"+LH.Chat.WHITE+"> Have a nice day!"));
					}
					if (APRIL_FOOLS) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(CHAT_GREG + "Watch your Calendar!"));
					}
				}
			}
		}
	}
	
	private ResourceLocation WATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
	
	@SubscribeEvent
	public void receiveRenderEvent(RenderBlockOverlayEvent aEvent) {
		if (aEvent.blockForOverlay == BlocksGT.Swamp) {
			EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
			Minecraft.getMinecraft().getTextureManager().bindTexture(WATER_OVERLAY);
			Tessellator tessellator = Tessellator.instance;
			GL11.glColor4f(0, aPlayer.getBrightness(aEvent.renderPartialTicks)/2, 0, 0.75F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glPushMatrix();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-1, -1, -0.5F, 4-aPlayer.rotationYaw/64, 4+aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV( 1, -1, -0.5F,  -aPlayer.rotationYaw/64, 4+aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV( 1,  1, -0.5F,  -aPlayer.rotationYaw/64,   aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV(-1,  1, -0.5F, 4-aPlayer.rotationYaw/64,   aPlayer.rotationPitch/64);
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glColor4f(1, 1, 1, 1);
			GL11.glDisable(GL11.GL_BLEND);
			aEvent.setCanceled(T);
		}
	}
	
	@SubscribeEvent
	public void receiveRenderEvent(RenderPlayerEvent.Pre aEvent) {
//      if (UT.Entities.getFullInvisibility(aEvent.entityPlayer)) {aEvent.setCanceled(true); return;}
	}
	
	@SubscribeEvent
	public void receiveRenderSpecialsEvent(RenderPlayerEvent.Specials.Pre aEvent) {
		mPlayerRenderer.receiveRenderSpecialsEvent(aEvent);
	}
	/*
	@Override
	public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (UT.Stacks.invalid(aStack)) return;
		String tString = "note.harp";
		for (int i = 0, j = mSoundItems.size(); i < j; i++) if (UT.Stacks.equal(mSoundItems.get(i), aStack)) {tString = mSoundNames.get(i); break;}
		if (tString.startsWith("random.explode")) if (aStack.stackSize==3) tString = "random.fuse"; else if (aStack.stackSize==2) tString = "random.old_explode";
		if (tString.startsWith("streaming.")) {
			switch (aStack.stackSize) {
			case  1: tString += "13"; break;
			case  2: tString += "cat"; break;
			case  3: tString += "blocks"; break;
			case  4: tString += "chirp"; break;
			case  5: tString += "far"; break;
			case  6: tString += "mall"; break;
			case  7: tString += "mellohi"; break;
			case  8: tString += "stal"; break;
			case  9: tString += "strad"; break;
			case 10: tString += "ward"; break;
			case 11: tString += "11"; break;
			case 12: tString += "wait"; break;
			default: tString += "wherearewenow"; break;
			}
		}
		if (tString.startsWith("streaming.")) aWorld.playRecord(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ); else aWorld.playSound(aX, aY, aZ, tString, 3.0F, tString.startsWith("note.")?(float)Math.pow(2.0D, (aStack.stackSize - 13) / 12.0D):1.0F, false);
	}*/
	
}
