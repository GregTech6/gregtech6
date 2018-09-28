/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi;

/**
 * @author Gregorius Techneticies
 * 
 * This Text is a very bit old, but I think most of it still fits.
 * 
 * ----------------------------------------------------------------
 * There are hooks to load after the 2 API Mods and GT itself.
 * They are in the form of Lists containing "Runnable" classes.
 * Those Lists are to be found in "gregapi.api.Abstract_Mod".
 * The Mod Objects themselves are in "gregapi.data.CS".
 * I have put them at the top of the Class so it's unoverlookable.
 * ----------------------------------------------------------------
 * 
 * If you want your Mod dependent on this API, then instead of
 * adding a dependency to GregTech add a dependency to this API
 * 
 * In order to do so just insert the following at your Loading
 * Order: "required-after:gregapi; required-before:gregapi_post"
 * 
 * This way you can access all the Features I have without needing
 * the Technology, Items, Blocks, Recipes or anything else of my
 * Mod to be there.
 * 
 * Instead you just need either the Core-API or GregTech itself to
 * be installed, so that your Mod works. Of course the Core-API
 * might be more desirable to some of your Users since it doesn't
 * do anything, unlike GregTech, which changes almost everything.
 * 
 * ================================================================
 * Now for some other Informations
 * ================================================================
 * if you have to choose an unlocalised Name or similar, then NEVER
 * use a Name which starts with "gt."! This is MY Name Prefix and
 * not yours. This happens way too often especially with people
 * decompiling my Code to find out how they have to use Stuff.
 * ----------------------------------------------------------------
 * Most of the Constants are found inside the data package.
 * ----------------------------------------------------------------
 * To use the NetworkHandler, just create an instance of it and
 * insert a Dummy for every single Packet you want to send/receive.
 * The implementation of IPacket is completely left to you.
 * 
 * If you need more than 256 different IPackets, consider making a
 * second NetworkHandler with a different Channel-ID.
 * 
 * The API does offer a few Packets for certain Sync stuff.
 * ----------------------------------------------------------------
 */
