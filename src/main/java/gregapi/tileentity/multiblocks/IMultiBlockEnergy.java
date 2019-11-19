/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.tileentity.multiblocks;

import java.util.Collection;

import gregapi.code.TagData;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockEnergy extends ITileEntityMultiBlockController {
	public boolean isEnergyType                 (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aEmitting);
	public Collection<TagData> getEnergyTypes   (MultiTileEntityMultiBlockPart aPart, byte aSide);
	public boolean isEnergyAcceptingFrom        (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aTheoretical);
	public boolean isEnergyEmittingTo           (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aTheoretical);
	public long doEnergyInjection               (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject);
	public long getEnergyDemanded               (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize);
	public long doEnergyExtraction              (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract);
	public long getEnergyOffered                (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize);
	public long getEnergySizeInputMin           (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputMin          (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergySizeInputRecommended   (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputRecommended  (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergySizeInputMax           (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
	public long getEnergySizeOutputMax          (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide);
}
