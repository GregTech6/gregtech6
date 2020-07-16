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

package gregapi.wooddict;

import java.util.List;
import java.util.Set;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import net.minecraft.item.Item;

/**
 * @author Gregorius Techneticies
 */
public class WoodDictionary {
	/** Contains all Planks, Stairs and Slabs */
	public static final ItemStackMap<ItemStackContainer, PlankEntry  > PLANKS_ANY = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, PlankEntry  > PLANKS     = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, PlankEntry  > STAIRS     = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, PlankEntry  > SLABS      = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, BeamEntry   > BEAMS      = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, WoodEntry   > WOODS      = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, SaplingEntry> SAPLINGS   = new ItemStackMap<>();
	public static final ItemStackMap<ItemStackContainer, LeafEntry   > LEAVES     = new ItemStackMap<>();
	
	/** Contains all Planks, Stairs and Slabs */
	public static final List<PlankEntry  > LIST_PLANKS_ANY = new ArrayListNoNulls<>();
	public static final List<PlankEntry  > LIST_PLANKS     = new ArrayListNoNulls<>();
	public static final List<PlankEntry  > LIST_STAIRS     = new ArrayListNoNulls<>();
	public static final List<PlankEntry  > LIST_SLABS      = new ArrayListNoNulls<>();
	public static final List<BeamEntry   > LIST_BEAMS      = new ArrayListNoNulls<>();
	public static final List<WoodEntry   > LIST_WOODS      = new ArrayListNoNulls<>();
	public static final List<SaplingEntry> LIST_SAPLINGS   = new ArrayListNoNulls<>();
	public static final List<LeafEntry   > LIST_LEAVES     = new ArrayListNoNulls<>();
	
	/** To prevent the OreDict from messing up Recipes. */
	public static final Set<Item> IGNORED_OREDICT_REGISTRATIONS = new HashSetNoNulls<>();
	
	public static BeamEntry  DEFAULT_BEAM;
	public static PlankEntry DEFAULT_PLANK;
}
