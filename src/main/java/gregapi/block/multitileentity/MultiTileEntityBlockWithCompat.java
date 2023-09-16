/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.block.multitileentity;

import cpw.mods.fml.common.Optional;
import gregapi.compat.galacticraft.IBlockSealable;
import gregapi.data.IL;
import gregapi.util.UT;
import micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import openblocks.api.IPaintableBlock;
import vazkii.botania.api.mana.IManaTrigger;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "openblocks.api.IPaintableBlock", modid = ModIDs.OB)
, @Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock", modid = ModIDs.GC)
, @Optional.Interface(iface = "vazkii.botania.api.mana.IManaTrigger", modid = ModIDs.BOTA)
})
@SuppressWarnings("deprecation")
public class MultiTileEntityBlockWithCompat extends MultiTileEntityBlock implements IBlockSealable, IOxygenReliantBlock, IPaintableBlock, IManaTrigger {
	protected static MultiTileEntityBlock create(String aModID, String aNameOfVanillaMaterialField, Material aVanillaMaterial, SoundType aSoundType, String aTool, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, boolean aOpaque, boolean aNormalCube) {
		return new MultiTileEntityBlockWithCompat(aModID, aNameOfVanillaMaterialField, aVanillaMaterial, aSoundType, aTool, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aOpaque, aNormalCube);
	}
	protected MultiTileEntityBlockWithCompat(String aModID, String aNameOfVanillaMaterialField, Material aVanillaMaterial, SoundType aSoundType, String aTool, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, boolean aOpaque, boolean aNormalCube) {
		super(aModID, aNameOfVanillaMaterialField, aVanillaMaterial, aSoundType, aTool, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aOpaque, aNormalCube);
	}
	
	@Override public final boolean recolourBlockRGB(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection, int aRGB) {TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); return aTileEntity instanceof IMultiTileEntity.IMTE_OnPainting && ((IMultiTileEntity.IMTE_OnPainting)aTileEntity).onPainting(UT.Code.side(aDirection), aRGB);}
	@Override public final boolean isSealed(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); return aTileEntity instanceof IMultiTileEntity.IMTE_IsSealable && ((IMultiTileEntity.IMTE_IsSealable)aTileEntity).isSealable((byte)(UT.Code.side(aDirection) ^ 1));}
	@Override public final void onOxygenAdded  (World aWorld, int aX, int aY, int aZ) {TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); if (aTileEntity instanceof IMultiTileEntity.IMTE_OnOxygenAdded) ((IMultiTileEntity.IMTE_OnOxygenAdded)aTileEntity).onOxygenAdded  ();}
	@Override public final void onOxygenRemoved(World aWorld, int aX, int aY, int aZ) {TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); if (aTileEntity instanceof IMultiTileEntity.IMTE_OnOxygenRemoved) ((IMultiTileEntity.IMTE_OnOxygenRemoved)aTileEntity).onOxygenRemoved();}
	@Override @Optional.Method(modid = ModIDs.BOTA) public final void onBurstCollision(vazkii.botania.api.internal.IManaBurst aMana, World aWorld, int aX, int aY, int aZ) {if (aWorld.isRemote) return; if (aMana.isFake() || !IL.BOTA_Paintslinger.equal(aMana.getSourceLens(), F, T) || !aMana.getSourceLens().hasTagCompound() || !aMana.getSourceLens().getTagCompound().hasKey("color") || aMana.getSourceLens().getTagCompound().getInteger("color") == -1) return; TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); if (aTileEntity instanceof IMultiTileEntity.IMTE_OnPainting) ((IMultiTileEntity.IMTE_OnPainting)aTileEntity).onPainting(SIDE_UNKNOWN, (aMana.getColor() & 0x00ffffff));}
}
