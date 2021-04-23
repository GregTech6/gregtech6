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

package gregapi.item;

import gregapi.render.ITexture;
import gregtech.tileentity.energy.reactors.MultiTileEntityReactorCore;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IItemReactorRod {
	/** @return if a Reactor would accept this Item as a Part of it. */
	public boolean  isReactorRod(ItemStack aStack);
	/** @return is a Reactor Rod is moderated and thus isn't usable for breeding */
	public boolean  isModerated (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** Updates the moderation status of the reactor rod */
	public void     updateModeration (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return the amount of Neutrons emitted by the Rod to each of the 4 surrounding Rods. Only called once every 20 ticks. */
	public int      getReactorRodNeutronEmission  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return if the Reactor would be considered "Active" after calculating the Ticking of the Rod itself. */
	public boolean  getReactorRodNeutronReaction  (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return the Amount of reflected Neutrons after this Rod has been hit with aNeutrons. */
	public int      getReactorRodNeutronReflection(MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, int aNeutrons, boolean aModerated);
	/** @return the Neutron Maximum for the lowest durability loss of this Rod */
	public int      getReactorRodNeutronMaximum   (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack);
	/** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
	public ITexture getReactorRodTextureSides     (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
	/** @return the Texture used for rendering the Rod in a Reactor. Note: only Item ID and Metadata are available in aStack, not NBT! */
	public ITexture getReactorRodTextureTop       (MultiTileEntityReactorCore aReactor, int aSlot, ItemStack aStack, boolean aActive);
}
