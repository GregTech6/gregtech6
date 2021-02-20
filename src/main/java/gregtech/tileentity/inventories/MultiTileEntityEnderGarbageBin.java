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

package gregtech.tileentity.inventories;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.CS.GarbageGT;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.gui.ContainerClientDefault;
import gregapi.gui.ContainerCommonDefault;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityEnderGarbageBin extends TileEntityBase07Paintable implements IFluidHandler, ITileEntityFunnelAccessible, IMTE_AddToolTips {
	static {
		LH.add("gt.multitileentity.ender.garbage.bin.tooltip.1", "Trash Bin deleting Items & Fluids, or moving them to the Servers Garbage Dump");
		LH.add("gt.multitileentity.ender.garbage.bin.tooltip.2", "Items & Fluids entering this will be dumped inside the Garbage Dimension");
		LH.add("gt.multitileentity.ender.garbage.bin.tooltip.3", "You can stop this from operating using a simple Redstone Signal");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.ender.garbage.bin.tooltip.1"));
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.ender.garbage.bin.tooltip.2"));
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.ender.garbage.bin.tooltip.3"));
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && (aTimer % 100 == 50 || mBlockUpdated) && !hasRedstoneIncoming()) GarbageGT.trash(getInventory());
	}
	
	@Override
	protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {
		for (int i = 0; i < GarbageGT.GARBAGE_FLUIDS.size(); i++) if (GarbageGT.GARBAGE_FLUIDS.get(i).contains(aFluidToFill)) return GarbageGT.GARBAGE_FLUIDS.get(i);
		FluidTankGT tTank = new FluidTankGT().setPreventDraining().setVoidExcess();
		GarbageGT.GARBAGE_FLUIDS.add(tTank);
		return tTank;
	}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		return null;
	}
	
	@Override
	protected IFluidTank[] getFluidTanks2(byte aSide) {
		return GarbageGT.GARBAGE_FLUIDS.isEmpty() ? new FluidTankGT().AS_ARRAY : GarbageGT.GARBAGE_FLUIDS.toArray(ZL_FT);
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return SIDES_BOTTOM_HORIZONTAL[aSide] ? 0 : fill(FORGE_DIR[aSide], aFluid, aDoFill);
	}
	
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?0.0F:PX_P[ 1];}
	@Override public float getSurfaceSize           (byte aSide) {return SIDES_TOP[aSide]?1.0F:PX_P[14];}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return getSurfaceSize(aSide);}
	@Override public boolean isSurfaceSolid         (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2           (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return T;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return 2;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		} else {
			box(aBlock, PX_P[ 1], PX_P[ 0], PX_P[ 1], PX_N[ 1], PX_N[ 4], PX_N[ 1]);
		}
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return (aRenderPass == 0 ? (aSide == SIDE_BOTTOM || aShouldSideBeRendered[aSide]) : aSide != SIDE_TOP && (aSide != SIDE_BOTTOM || aShouldSideBeRendered[aSide])) ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/endergarbage/bin/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.ender.garbage.bin";}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[9];}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID, RES_PATH_GUI + "machines/Trash.png");}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
}
