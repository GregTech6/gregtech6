/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregtech.tileentity.energy.reactors;

import gregapi.old.Textures;
import gregapi.render.*;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import net.minecraft.block.Block;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorCore2x2 extends MultiTileEntityReactorCore implements ITileEntitySwitchableMode {
	@Override
	public int numSlots() { return 4; }

	@Override
	public int slotActivated(float aHitX, float aHitY, float aHitZ) {
		int slot = 0;
		if (aHitZ >= 0.5) slot += 1;
		if (aHitX >= 0.5) slot += 2;
		return slot;
	}

	@Override public int slotLength() {return 1;}

	// Slot layout, every slot is an X and a Z coordinate that are each 0 or 1, as slot = X * 2 + Z.
	// 0 and 2 are at SIDE_Z_NEG    1 3      -->X+
	// 1 and 3 are at SIDE_Z_POS  2|0 2|0   |0 2
	// 0 and 1 are at SIDE_X_NEG  3|1 3|1   v1 3
	// 2 and 3 are at SIDE_X_POS    0 2     Z+
	// Subside layout: a beam travels along one axis and keeps its coordinate on the other one, which is its subside.
	// Travelling along Z, the subside is the X coordinate: subside 0 is slots 0 and 1, subside 1 is slots 2 and 3.
	// Travelling along X, the subside is the Z coordinate: subside 0 is slots 0 and 2, subside 1 is slots 1 and 3.

	private static int slotAt(int aX, int aZ) {return aX * 2 + aZ;}
	private static int xOf(int aSlot) {return aSlot / 2;}
	private static int zOf(int aSlot) {return aSlot % 2;}

	/** @return the coordinate along the travel axis where a beam heading towards aSide enters this core, which is the end it heads away from. */
	private static int entryCoordinate(byte aSide) {return aSide == SIDE_Z_POS || aSide == SIDE_X_POS ? 0 : 1;}
	/** @return the coordinate along the travel axis where a beam heading towards aSide leaves this core. */
	private static int exitCoordinate(byte aSide) {return 1 - entryCoordinate(aSide);}

	@Override
	public int slotOfEntry(byte aSide, int aSubSide) {
		// The beam enters at one end of its travel axis, and the subside tells us where it sits on the other axis.
		return SIDES_AXIS_Z[aSide] ? slotAt(aSubSide, entryCoordinate(aSide)) : slotAt(entryCoordinate(aSide), aSubSide);
	}

	@Override
	public int slotBehind(byte aSide, int aSlot) {
		boolean tAlongZ = SIDES_AXIS_Z[aSide];
		// Once the beam has reached the far end of its travel axis, there is no slot of this core left behind it.
		if ((tAlongZ ? zOf(aSlot) : xOf(aSlot)) == exitCoordinate(aSide)) return -1;
		// Otherwise it moves to the far end, keeping its coordinate on the other axis.
		return tAlongZ ? slotAt(xOf(aSlot), exitCoordinate(aSide)) : slotAt(exitCoordinate(aSide), zOf(aSlot));
	}

	/** @return the subside of aSlot as seen by a beam travelling towards aSide. */
	private static int subSideOf(byte aSide, int aSlot) {return SIDES_AXIS_Z[aSide] ? xOf(aSlot) : zOf(aSlot);}

	@Override
	protected void updateAdjacentCores() {
		// Get amount of neutrons emitted from each rod, and send them to its neighbours, one by one.
		for (int tSlot = 0; tSlot < numSlots(); tSlot++) {
			int tNeutronCount = getReactorRodNeutronEmission(tSlot);
			boolean tModerated = isReactorRodModerated(tSlot);
			if (tNeutronCount == 0 && !tModerated) continue;
			for (byte tSide : ALL_SIDES_HORIZONTAL)
				neutronCountsIn[tSlot] += emitNeutronsFrom(tSide, tSlot, subSideOf(tSide, tSlot), tNeutronCount, tModerated);
		}
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  7: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[ 2], PX_N[10], PX_P[17], PX_N[10]);
		case  8: return box(aBlock, PX_P[ 2], PX_P[ 1], PX_P[10], PX_N[10], PX_P[17], PX_N[ 2]);
		case  9: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 2], PX_N[ 2], PX_P[17], PX_N[10]);
		case 10: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[10], PX_N[ 2], PX_P[17], PX_N[ 2]);
		default: return super.setBlockBounds2(aBlock, aRenderPass, aShouldSideBeRendered);
		}
	}

	private static final IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/colored/face2")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_2x2/overlay/face2")
	};

	@Override protected IIconContainer[] getColoredIcons() { return sColoreds; }
	@Override protected IIconContainer[] getOverlayIcons() { return sOverlays; }

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core";} // Yeah Naming Convention doesn't work on the first one I added, just imagine a ".2x2" at the end of this String.
}
