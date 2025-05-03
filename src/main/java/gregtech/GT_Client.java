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

package gregtech;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import gregapi.GT_API;
import gregapi.api.Abstract_Mod;
import gregapi.config.ConfigCategories;
import gregapi.data.LH;
import gregapi.data.MD;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.entities.projectiles.EntityArrow_Potion;
import gregtech.render.GT_Renderer_Entity_Arrow;
import gregtech.render.PlayerModelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

import static gregapi.data.CS.*;

public class GT_Client extends GT_Proxy {
	private final PlayerModelRenderer mPlayerRenderer = new PlayerModelRenderer(mSupporterListSilver, mSupporterListGold);
	
	public int addArmor(String aPrefix) {return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);}
	
	public GT_Client() {super();}
	
	@Override
	public void onProxyAfterPreInit(Abstract_Mod aMod, FMLPreInitializationEvent aEvent) {
		super.onProxyAfterPreInit(aMod, aEvent);
		new GT_Renderer_Entity_Arrow(EntityArrow_Material.class, "arrow");
		new GT_Renderer_Entity_Arrow(EntityArrow_Potion.class, "arrow_potions");
	}
	
	private boolean FIRST_CLIENT_PLAYER_TICK = T;
	
	@SubscribeEvent
	public void onPlayerTickEventClient(PlayerTickEvent aEvent) {
		if (!aEvent.player.isDead && aEvent.phase == Phase.END && aEvent.side.isClient() && CLIENT_TIME > 20) {
			if (aEvent.player == GT_API.api_proxy.getThePlayer()) {
				if (FIRST_CLIENT_PLAYER_TICK) {
					FIRST_CLIENT_PLAYER_TICK = F;
					ChatComponentText tLink;
					if (!mMessage.isEmpty() && ConfigsGT.CLIENT.get(ConfigCategories.news, mMessage, T)) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(mMessage));
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.DGRAY + ""));
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable message in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (mVersionOutdated) {
						aEvent.player.addChatComponentMessage(new ChatComponentText("Major GT6 Update released, for details visit"));
						tLink = new ChatComponentText(LH.Chat.BLUE + "https://gregtech.mechaenetia.com/1.7.10");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://gregtech.mechaenetia.com/1.7.10"));
						aEvent.player.addChatComponentMessage(tLink);
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable checker in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (MD.IC2.mLoaded && !MD.IC2C.mLoaded) {
						try {
							int tVersion = Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get(null)).substring(4, 7));
							if (tVersion < 827) {
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Please update IndustrialCraft!"));
								// IC2 Site doesn't support https.
								tLink = new ChatComponentText(LH.Chat.BLUE + "http://ic2api.player.to:8080/job/IC2_experimental/827/");
								tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://ic2api.player.to:8080/job/IC2_experimental/827/"));
								aEvent.player.addChatComponentMessage(tLink);
							}
						} catch(Throwable e) {/**/}
					}
					if (MD.TC.mLoaded) {
						try {
							if (Class.forName("com.chocohead.patcher.ThaumicFixer") != null) {
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Warning! Chocoheads ThaumicFixer needs to be uninstalled!"));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "Not uninstalling it can lead to crashes when viewing Aspects."));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "Lag is already fixed with a better Version of the ASM Code,"));
								aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "that doesn't obliterate the Thaumcraft API for no reason."));
							}
						} catch(Throwable e) {/**/}
					}
					if (MD.COG.mLoaded && !MD.PFAA.mLoaded && ConfigsGT.CLIENT.get(ConfigCategories.general, "warnings_customoregen", T)) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.RED + "Warning! CustomOreGen will screw up all GregTech Worldgen with its Default Configs!"));
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.ORANGE + "If you don't even use CustomOreGen, I would highly recommend you to remove it."));
						tLink = new ChatComponentText(LH.Chat.DGRAY + "disable warning in the clientside gregtech.cfg");
						tLink.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ConfigsGT.CLIENT.mConfig.getConfigFile().getAbsolutePath()));
						aEvent.player.addChatComponentMessage(tLink);
					}
					if (WOODMANS_BDAY) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(LH.Chat.WHITE+"<"+LH.Chat.GREEN+">:]"+LH.Chat.WHITE+"> Have a nice day!"));
					}
					if (APRIL_FOOLS) {
						aEvent.player.addChatComponentMessage(new ChatComponentText(CHAT_GREG + "Watch your Calendar!"));
					}
				}
			}
		}
	}
	
	private ResourceLocation WATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
	
	@SubscribeEvent
	public void receiveRenderEvent(RenderBlockOverlayEvent aEvent) {
		if (aEvent.blockForOverlay == BlocksGT.Swamp) {
			EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
			Minecraft.getMinecraft().getTextureManager().bindTexture(WATER_OVERLAY);
			Tessellator tessellator = Tessellator.instance;
			GL11.glColor4f(0, aPlayer.getBrightness(aEvent.renderPartialTicks)/2, 0, 0.75F);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glPushMatrix();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-1, -1, -0.5F, 4-aPlayer.rotationYaw/64, 4+aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV( 1, -1, -0.5F,  -aPlayer.rotationYaw/64, 4+aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV( 1,  1, -0.5F,  -aPlayer.rotationYaw/64,   aPlayer.rotationPitch/64);
			tessellator.addVertexWithUV(-1,  1, -0.5F, 4-aPlayer.rotationYaw/64,   aPlayer.rotationPitch/64);
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glColor4f(1, 1, 1, 1);
			GL11.glDisable(GL11.GL_BLEND);
			aEvent.setCanceled(T);
		}
	}
	
	@SubscribeEvent
	public void receiveRenderEvent(RenderPlayerEvent.Pre aEvent) {
//      if (UT.Entities.getFullInvisibility(aEvent.entityPlayer)) {aEvent.setCanceled(true); return;}
	}
	
	@SubscribeEvent
	public void receiveRenderSpecialsEvent(RenderPlayerEvent.Specials.Pre aEvent) {
		mPlayerRenderer.receiveRenderSpecialsEvent(aEvent);
	}
	/*
	@Override
	public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (UT.Stacks.invalid(aStack)) return;
		String tString = "note.harp";
		for (int i = 0, j = mSoundItems.size(); i < j; i++) if (UT.Stacks.equal(mSoundItems.get(i), aStack)) {tString = mSoundNames.get(i); break;}
		if (tString.startsWith("random.explode")) if (aStack.stackSize==3) tString = "random.fuse"; else if (aStack.stackSize==2) tString = "random.old_explode";
		if (tString.startsWith("streaming.")) {
			switch (aStack.stackSize) {
			case  1: tString += "13"; break;
			case  2: tString += "cat"; break;
			case  3: tString += "blocks"; break;
			case  4: tString += "chirp"; break;
			case  5: tString += "far"; break;
			case  6: tString += "mall"; break;
			case  7: tString += "mellohi"; break;
			case  8: tString += "stal"; break;
			case  9: tString += "strad"; break;
			case 10: tString += "ward"; break;
			case 11: tString += "11"; break;
			case 12: tString += "wait"; break;
			default: tString += "wherearewenow"; break;
			}
		}
		if (tString.startsWith("streaming.")) aWorld.playRecord(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ); else aWorld.playSound(aX, aY, aZ, tString, 3.0F, tString.startsWith("note.")?(float)Math.pow(2.0D, (aStack.stackSize - 13) / 12.0D):1.0F, false);
	}*/
	
}
