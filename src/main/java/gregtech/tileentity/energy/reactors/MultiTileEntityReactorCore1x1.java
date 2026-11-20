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
import gregapi.util.UT;
import net.minecraft.block.Block;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityReactorCore1x1 extends MultiTileEntityReactorCore {
	@Override
	public int numSlots() { return 1; }

	@Override
	public int slotActivated(float aHitX, float aHitY, float aHitZ) {
		return 0;
	}

	@Override public int slotLength() {return 2;}
	@Override public int slotOfEntry(byte aSide, int aSubSide) {return 0;}
	@Override public int slotBehind(byte aSide, int aSlot) {return -1;}

	@Override
	protected void updateAdjacentCores() {
		// A 1x1 Reactor Core is one rod on a whole block, so it emits half strength at each of the two lateral sub-coordinates of every side.
		int tNeutronCount = (int)UT.Code.divup(getReactorRodNeutronEmission(0), 2);
		boolean tModerated = isReactorRodModerated(0);
		if (tNeutronCount == 0 && !tModerated) return;
		for (byte tSide : ALL_SIDES_HORIZONTAL)
			for (int tSubSide = 0; tSubSide < 2; tSubSide++)
				neutronCountsIn[0] += emitNeutronsFrom(tSide, 0, tSubSide, tNeutronCount, tModerated);
	}

	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
			case 7: return box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 6], PX_N[ 6], PX_P[17], PX_N[ 6]);
			default: return super.setBlockBounds2(aBlock, aRenderPass, aShouldSideBeRendered);
		}
	}

	private static final IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/colored/face2")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/side1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/side2"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/face1"),
		new Textures.BlockIcons.CustomIcon("machines/generators/reactor_core_1x1/overlay/face2")
	};

	@Override protected IIconContainer[] getColoredIcons() { return sColoreds; }
	@Override protected IIconContainer[] getOverlayIcons() { return sOverlays; }

	@Override public String getTileEntityName() {return "gt.multitileentity.generator.reactor.core.1x1";}
}
