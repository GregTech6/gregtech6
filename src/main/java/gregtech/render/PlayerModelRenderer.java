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

package gregtech.render;

import static gregapi.data.CS.*;

import java.util.Collection;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerModelRenderer extends RenderPlayer {
	private final ResourceLocation[] mResources = new ResourceLocation[] {new ResourceLocation(RES_PATH_MODEL + "BrainTech.png"), new ResourceLocation(RES_PATH_MODEL + "Silver.png"), new ResourceLocation(RES_PATH_MODEL + "MrBrain.png"), new ResourceLocation(RES_PATH_MODEL + "Dev.png"), new ResourceLocation(RES_PATH_MODEL + "Gold.png"), new ResourceLocation(RES_PATH_MODEL + "Crazy.png"), new ResourceLocation(RES_PATH_MODEL + "Fake.png")};
	private final Collection<String> mSupporterListSilver, mSupporterListGold;
	
	public PlayerModelRenderer(Collection<String> aSupporterListSilver, Collection<String> aSupporterListGold) {
		mSupporterListSilver = aSupporterListSilver;
		mSupporterListGold   = aSupporterListGold;
		setRenderManager(RenderManager.instance);
	}
	
	private ResourceLocation getResource(String aPlayer) {
		aPlayer = aPlayer.toLowerCase();
		// To be uncommented once Account Migration gets enforced, because I sure as fuck won't make a Microsoft Account!
//      if (aPlayer.startswith("gregoriust"))         return mResources[6];
		// GT6 Team
		if (aPlayer.equalsIgnoreCase("GregoriusT"))   return mResources[3];
		if (aPlayer.equalsIgnoreCase("OvermindDL1"))  return mResources[3];
		// GT6U Team
		if (aPlayer.equalsIgnoreCase("jihuayu123"))   return mResources[3];
		if (aPlayer.equalsIgnoreCase("Yuesha_Kev14")) return mResources[3];
		if (aPlayer.equalsIgnoreCase("Evanvenir"))    return mResources[3];
		// This "special" Cape is totally just to mess with her. XD
		if (aPlayer.equalsIgnoreCase("CrazyJ1984"))   return mResources[5];
		// People who helped back in ancient GT Versions.
		if (aPlayer.equalsIgnoreCase("Mr_Brain"))     return mResources[2];
		if (aPlayer.equalsIgnoreCase("Friedi4321"))   return mResources[0];
		// Supporter Lists
		if (mSupporterListGold  .contains(aPlayer))   return mResources[4];
		if (mSupporterListSilver.contains(aPlayer))   return mResources[1];
		return null;
	}
	
	public void receiveRenderSpecialsEvent(RenderPlayerEvent.Specials.Pre aEvent) {
		AbstractClientPlayer aPlayer = (AbstractClientPlayer)aEvent.entityPlayer;
//      if (UT.Entities.getFullInvisibility(aPlayer)) {aEvent.setCanceled(true); return;}
		float aPartialTicks = aEvent.partialRenderTick;
		
		if (aPlayer.isInvisible() || aPlayer.getActivePotionEffect(Potion.invisibility) != null) return;
		
		try {
			ResourceLocation tResource = getResource(aPlayer.getCommandSenderName());
			if (tResource == null) tResource = getResource(aPlayer.getUniqueID().toString());
			
			if (tResource != null && !aPlayer.getHideCape()) {
				bindTexture(tResource);
				GL11.glPushMatrix();
				GL11.glTranslatef(0.0F, 0.0F, 0.125F);
				double d0 = aPlayer.field_71091_bM + (aPlayer.field_71094_bP - aPlayer.field_71091_bM) * aPartialTicks - (aPlayer.prevPosX + (aPlayer.posX - aPlayer.prevPosX) * aPartialTicks);
				double d1 = aPlayer.field_71096_bN + (aPlayer.field_71095_bQ - aPlayer.field_71096_bN) * aPartialTicks - (aPlayer.prevPosY + (aPlayer.posY - aPlayer.prevPosY) * aPartialTicks);
				double d2 = aPlayer.field_71097_bO + (aPlayer.field_71085_bR - aPlayer.field_71097_bO) * aPartialTicks - (aPlayer.prevPosZ + (aPlayer.posZ - aPlayer.prevPosZ) * aPartialTicks);
				float f6 = aPlayer.prevRenderYawOffset + (aPlayer.renderYawOffset - aPlayer.prevRenderYawOffset) * aPartialTicks;
				double d3 = MathHelper.sin(f6 * (float)Math.PI / 180.0F);
				double d4 = (-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
				float f7 = (float)d1 * 10.0F;
				float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
				float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;
				if (f7 < -6.0F) f7 = -6.0F;
				if (f7 > 32.0F) f7 = 32.0F;
				if (f8 <  0.0F) f8 =  0.0F;
				float f10 = aPlayer.prevCameraYaw + (aPlayer.cameraYaw - aPlayer.prevCameraYaw) * aPartialTicks;
				f7 += MathHelper.sin((aPlayer.prevDistanceWalkedModified + (aPlayer.distanceWalkedModified - aPlayer.prevDistanceWalkedModified) * aPartialTicks) * 6.0F) * 32.0F * f10;
				if (aPlayer.isSneaking()) {
					f7 += 25.0F;
				}
				GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				((ModelBiped)mainModel).renderCloak(0.0625F);
				GL11.glPopMatrix();
			}
		} catch (Throwable e) {
			e.printStackTrace(ERR);
		}
	}
}
