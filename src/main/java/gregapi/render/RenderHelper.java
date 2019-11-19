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

package gregapi.render;

import static gregapi.data.CS.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHelper {
	public static RenderItem   mRenderItem   = new RenderItem();
	public static RenderBlocks mRenderBlocks = new RenderBlocks();
	
	public static void renderItemIntoGUI(FontRenderer aFontRenderer, TextureManager aTextureManager, ItemStack aStack, int aX, int aY, boolean aEffect) {
		glAlphaFunc(GL_GREATER, 0.1F);
		glEnable(GL_ALPHA_TEST);
		if (aStack.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(aStack.getItem()).getRenderType())) {
			aTextureManager.bindTexture(TextureMap.locationBlocksTexture);
			Block block = Block.getBlockFromItem(aStack.getItem());
			if (block.getRenderBlockPass() != 0) {
				glEnable(GL_BLEND);
				OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			} else {
				glDisable(GL_BLEND);
			}
			glPushMatrix();
			glTranslatef(aX-2, aY+3, mRenderItem.zLevel-3);
			glScalef(10.0F, 10.0F, 10.0F);
			glTranslatef(1.0F, 0.5F, 1.0F);
			glScalef(1.0F, 1.0F, -1.0F);
			glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			int tColor = aStack.getItem().getColorFromItemStack(aStack, 0);
			glColor4f((tColor >> 16 & 255) / 255F, (tColor >> 8 & 255) / 255F, (tColor & 255) / 255F, 1);
			glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			mRenderBlocks.useInventoryTint = T;
			mRenderBlocks.renderBlockAsItem(block, ST.meta_(aStack), 1.0F);
			mRenderBlocks.useInventoryTint = T;
			glPopMatrix();
		} else {
			ResourceLocation tResource = aTextureManager.getResourceLocation(aStack.getItemSpriteNumber());
			int tMaxRenderPasses = Math.max(1, aStack.getItem().getRenderPasses(ST.meta_(aStack)));
			for (int i = 0; i < tMaxRenderPasses; i++) {
				IIcon tIcon = aStack.getItem().getIcon(aStack, i);
				if (tIcon == null) tIcon = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(tResource)).getAtlasSprite("missingno");
				glDisable(GL_LIGHTING);
				glEnable(GL_BLEND);
				OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
				aTextureManager.bindTexture(tResource);
				int tColor = aStack.getItem().getColorFromItemStack(aStack, i);
				glColor4f((tColor >> 16 & 255) / 255F, (tColor >> 8 & 255) / 255F, (tColor & 255) / 255F, 1);
				
				mRenderItem.renderIcon(aX, aY, tIcon, 16, 16);
				
				if (aEffect && aStack.hasEffect(i)) mRenderItem.renderEffect(aTextureManager, aX, aY);
			}
		}
		glDisable(GL_BLEND);
		glDisable(GL_ALPHA_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_CULL_FACE);
	}
	
	public static void drawWrenchOverlay(EntityPlayer aPlayer, int aX, int aY, int aZ, byte aConnections, byte aSide, float aPartialTicks) {
		try {Class.forName("codechicken.lib.vec.Rotation");} catch (ClassNotFoundException e) {return;}
		
//      GL11.glDepthFunc(GL11.GL_ALWAYS);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glPushMatrix();
		GL11.glTranslated(-(aPlayer.lastTickPosX + (aPlayer.posX - aPlayer.lastTickPosX) * aPartialTicks), -(aPlayer.lastTickPosY + (aPlayer.posY - aPlayer.lastTickPosY) * aPartialTicks), -(aPlayer.lastTickPosZ + (aPlayer.posZ - aPlayer.lastTickPosZ) * aPartialTicks));
		GL11.glTranslated(aX + 0.5, aY + 0.5, aZ + 0.5);
		codechicken.lib.vec.Rotation.sideRotations[aSide].glApply();
		GL11.glTranslated(0, -0.5025, 0);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBegin(GL11.GL_LINES);
		double tColor = (CLIENT_TIME % 42 < 21 ? 0.25 + ((CLIENT_TIME % 21)/40.0) : 0.75 - ((CLIENT_TIME % 21)/40.0));
		GL11.glColor4d(tColor, tColor, tColor, 0.5);
		
		
		GL11.glVertex3d( 0.50, 0, -0.25);
		GL11.glVertex3d(-0.50, 0, -0.25);
		GL11.glVertex3d( 0.50, 0,  0.25);
		GL11.glVertex3d(-0.50, 0,  0.25);
		GL11.glVertex3d( 0.25, 0, -0.50);
		GL11.glVertex3d( 0.25, 0,  0.50);
		GL11.glVertex3d(-0.25, 0, -0.50);
		GL11.glVertex3d(-0.25, 0,  0.50);
		switch(aSide) {
		case SIDE_DOWN:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			break;
		case SIDE_UP:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			break;
		case SIDE_NORTH:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			break;
		case SIDE_SOUTH:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			break;
		case SIDE_WEST:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			break;
		case SIDE_EAST:
			if (FACE_CONNECTED[SIDE_DOWN][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_UP][aConnections]) {
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			if (FACE_CONNECTED[SIDE_NORTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
			}
			if (FACE_CONNECTED[SIDE_SOUTH][aConnections]) {
				GL11.glVertex3d(-0.25, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_WEST][aConnections]) {
				GL11.glVertex3d(-0.50, 0, -0.50);
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(-0.50, 0, -0.25);
				GL11.glVertex3d(-0.25, 0, -0.50);
				
				GL11.glVertex3d(+0.50, 0, +0.50);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(+0.50, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, +0.50);
				
				GL11.glVertex3d(+0.50, 0, -0.50);
				GL11.glVertex3d(+0.25, 0, -0.25);
				GL11.glVertex3d(+0.50, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, -0.50);
				
				GL11.glVertex3d(-0.50, 0, +0.50);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(-0.50, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.50);
			}
			if (FACE_CONNECTED[SIDE_EAST][aConnections]) {
				GL11.glVertex3d(-0.25, 0, -0.25);
				GL11.glVertex3d(+0.25, 0, +0.25);
				GL11.glVertex3d(-0.25, 0, +0.25);
				GL11.glVertex3d(+0.25, 0, -0.25);
			}
			break;
		}
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
//      GL11.glDepthFunc(GL11.GL_LEQUAL);
	}
}
