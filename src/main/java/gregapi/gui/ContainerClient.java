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

package gregapi.gui;

import static gregapi.data.CS.*;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.util.ST;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

/**
 * @author Gregorius Techneticies
 */
@SideOnly(Side.CLIENT)
public class ContainerClient extends GuiContainer {
	
	public boolean mCrashed = F;
	
	public ResourceLocation mBackground;
	
	public String mNEI = "";
	
	public ContainerCommon mContainer;
	
	public int getLeft() {return guiLeft;}
	public int getTop() {return guiTop;}
	
	public ContainerClient(ContainerCommon aContainer, String aBackgroundPath) {
		super(aContainer);
		mContainer = aContainer;
		mBackground = new ResourceLocation(aBackgroundPath);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		//
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		mc.renderEngine.bindTexture(mBackground);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawGuiContainerBackgroundLayer2(par1, par2, par3);
	}
	
	protected void drawGuiContainerBackgroundLayer2(float par1, int par2, int par3) {
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawScreen(int aX, int aY, float par3) {
		try {
			super.drawScreen(aX, aY, par3);
			for (int i = 0; i < inventorySlots.inventorySlots.size(); ++i) {
				Slot tSlot = (Slot)inventorySlots.inventorySlots.get(i);
				if (ST.invalid(tSlot.getStack()) && isMouseOverSlot(tSlot, aX, aY) && tSlot instanceof Slot_Base) {
					drawHoveringText(((Slot_Base)tSlot).getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips), aX, aY, fontRendererObj);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace(ERR);
			try {
				Tessellator.instance.draw();
			} catch (Throwable f) {
				f.printStackTrace(ERR);
			}
		}
	}
	
	protected boolean isMouseOverSlot(Slot aSlot, int aX, int aY) {return func_146978_c(aSlot.xDisplayPosition, aSlot.yDisplayPosition, 16, 16, aX, aY);}
	
	/*
	@Override
	protected void drawSlotInventory(Slot par1Slot) {
		try {
			super.drawSlotInventory(par1Slot);
		} catch(Throwable e) {
			try {
				Tessellator.instance.draw();
			} catch(Throwable f) {}
			if (!mCrashed) {
				GT_Log.out.println("Clientside Slot drawing Crash prevented. Seems one Itemstack causes Problems with negative Damage Values or the Wildcard Damage Value. This is absolutely NOT a Bug of GregTech, so don't even think about reporting it to me, it's a Bug of the Mod, which belongs to the almost-crash-causing Item, so bug that Mods Author and not me! Did you hear it? NOT ME!!!");
				e.printStackTrace();
				mCrashed = true;
			}
		}
	}*/
}
