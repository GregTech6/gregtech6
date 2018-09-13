/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import gregapi.api.Abstract_Mod;
import gregapi.block.ToolCompat;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.prefixblock.PrefixBlockFallingEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ObjectStack;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ICover;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.PlankData;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.item.IItemGT;
import gregapi.item.ItemFluidDisplay;
import gregapi.old.Textures;
import gregapi.oredict.IOreDictListenerItem;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.AdvancedCrafting1ToY;
import gregapi.recipes.AdvancedCraftingXToY;
import gregapi.render.ITexture;
import gregapi.render.IconContainerCopied;
import gregapi.render.RendererBlockTextured;
import gregapi.tileentity.render.ITileEntityOnDrawBlockHighlight;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * @author Gregorius Techneticies
 */
public class GT_API_Proxy_Client extends GT_API_Proxy {
	@SuppressWarnings("deprecation")
	public GT_API_Proxy_Client() {
		super();
		CODE_SERVER = T;
		CODE_CLIENT = T;
		CODE_UNCHECKED = F;
		
		for (int i = 0; i < 4; i++) {
			sPosR.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.Force.mRGBa[i], MT.Pyrotheum.mRGBa[i], MT.Sunnarium.mRGBa[i], MT.Glowstone.mRGBa[i], MT.Thaumium.mRGBa[i], MT.InfusedVis.mRGBa[i], MT.InfusedAir.mRGBa[i], MT.InfusedFire.mRGBa[i], MT.FierySteel.mRGBa[i], MT.Firestone.mRGBa[i], MT.ArcaneAsh.mRGBa[i]));
			sPosG.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.Force.mRGBa[i], MT.Pyrotheum.mRGBa[i], MT.Sunnarium.mRGBa[i], MT.Glowstone.mRGBa[i], MT.InfusedAir.mRGBa[i], MT.InfusedEarth.mRGBa[i]));
			sPosB.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.InfusedVis.mRGBa[i], MT.InfusedWater.mRGBa[i], MT.Thaumium.mRGBa[i], MT.Co_60.mRGBa[i], MT.Lumium.mRGBa[i], MT.VinteumPurified.mRGBa[i], MT.ArcaneAsh.mRGBa[i]));
			sNegR.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sNegG.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sNegB.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sRainbow.addAll(Arrays.asList(MT.InfusedBalance.mRGBa[i], MT.GaiaSpirit.mRGBa[i], MT.GaiaSpirit.mRGBa[i], MT.Shimmerwood.mRGBa[i], MT.Shimmerwood.mRGBa[i]));
		}
		
		if (new Date().getMonth() == 3 && new Date().getDate() <= 3) {
			MT.W.setLocal("Wolframium");
			MT.V.setLocal("Vandalium");
			MT.B.setLocal("Boring");
			MT.S.setLocal("Sulphur");
			MT.K.setLocal("Kalium");
			MT.Na.setLocal("Natrium");
			MT.Ar.setLocal("Aragon");
			MT.Al.setLocal("Aluminum");
			MT.Ni.setLocal("Ferrous Metal");
			MT.Pt.setLocal("Shiny Metal");
			MT.Mithril.setLocal("Mana Infused Metal");
			MT.Hg.setLocal("Quicksilver");
			MT.Mo.setLocal("Molly-B");
			MT.Sb.setLocal("Anti-Money");
			MT.Tc.setLocal("Gregorium");
			MT.Si.setLocal("Silicone");
			MT.Cr.setLocal("Chrome");
			MT.Cu.setLocal("Cooper");
			MT.AnnealedCopper.setLocal("Anilled Cooper");
			MT.Mn.setLocal("Animenese");
			MT.As.setLocal("Arse Nick");
			MT.Br.setLocal("Bro, that's mine");
			MT.Kr.setLocal("Kryptonite");
			MT.Bi.setLocal("Biffmiff");
			MT.Sg.setLocal("Resistance is Futile");
			MT.Zr.setLocal("Diamond");
			MT.Au.setLocal("Pyrite");
			MT.Pyrite.setLocal("Gold");
			MT.Fe.setLocal("Arn");
			MT.IronWood.setLocal("Arnwood");
			MT.ShadowIron.setLocal("Shade Arn");
			MT.DarkIron.setLocal("Dank Arn");
			MT.MeteoricIron.setLocal("Metaur Arn");
			MT.GildedIron.setLocal("Guild Arn");
			MT.WroughtIron.setLocal("Wrecked Arn");
			MT.Steel.setLocal("Stil");
			MT.RedSteel.setLocal("Rad Stil");
			MT.BlueSteel.setLocal("Blu Stil");
			MT.BlackSteel.setLocal("Afroamerican Stil");
			MT.DamascusSteel.setLocal("Dank Stil");
			MT.VanadiumSteel.setLocal("Vandalium Stil");
			MT.TungstenSteel.setLocal("Wolf Stil");
			MT.MeteoricSteel.setLocal("Metaur Stil");
			MT.ShadowSteel.setLocal("Shade Stil");
			MT.Steeleaf.setLocal("Still Leave");
			MT.Knightmetal.setLocal("Night Metal");
			MT.FierySteel.setLocal("Fury Stil");
			MT.Thaumium.setLocal("Thaumanominum");
			MT.DarkThaumium.setLocal("Dank Thaumanominum");
			MT.Rb.setLocal("Ruby");
			MT.Ruby.setLocal("Red Sapphire");
			MT.KNO3.setLocal("Niter");
			MT.NaNO3.setLocal("Nitre");
			MT.Glyceryl.setLocal("Nitro");
			MT.Gunpowder.setLocal("Boompowder");
			MT.SulfuricAcid.setLocal("Sulphuric Acid");
			MT.Greenschist.setLocal("Green Shit");
			MT.Blueschist.setLocal("Blue Shit");
			MT.Nikolite.setLocal("Bluestone");
			MT.Teslatite.setLocal("Bluestone");
			MT.Electrotine.setLocal("Bluestone");
			MT.PigIron.setLocal("Ferrobacon");
			MT.TinAlloy.setLocal("Tin*");
			MT.Bronze.setLocal("Tinkerers Alloy");
			MT.BlackBronze.setLocal("Afroamerican Tinkerers Alloy");
			MT.Constantan.setLocal("Cupronickel");
			MT.FakeOsmium.setLocal("Platinum");
			MT.CertusQuartz.setLocal("Citrus Quartz");
			MT.ChargedCertusQuartz.setLocal("Charged Citrus Quartz");
			MT.OREMATS.Galena.setLocal("Silverlead");
			MT.OREMATS.Huebnerite.setLocal("Boobnerite");
			MT.OREMATS.Bromargyrite.setLocal("Bromagnerite");
			MT.OREMATS.Chalcopyrite.setLocal("Chackapackerite");
		}
	}
	
	@Override
	public EntityPlayer getThePlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public boolean sendUseItemPacket(EntityPlayer aPlayer, World aWorld, ItemStack aStack) {
		Minecraft.getMinecraft().playerController.sendUseItem(aPlayer, aWorld, aStack);
		return T;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void onProxyAfterPreInit(Abstract_Mod aMod, FMLPreInitializationEvent aEvent) {
		RenderingRegistry.registerEntityRenderingHandler(PrefixBlockFallingEntity.class, new RenderFallingBlock());
		RenderingRegistry.registerBlockHandler(new RendererBlockTextured(RenderingRegistry.getNextAvailableRenderId()));
		// Check if OptiFine is loaded in order to disable some GT Render Hooks to fix Glitches.
		ITexture.Util.OPTIFINE_LOADED = FMLClientHandler.instance().hasOptifine();
		
		switch (new Date().getMonth()) {// Not going to use Calendar, because it fucking crashes with Missing Resource Exception...
		case  8:
			Textures.BlockIcons.LEAVES[1] = Textures.BlockIcons.LEAVES_MAPLE_YELLOW;
			Textures.BlockIcons.LEAVES[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_YELLOW;
			break;
		case  9:
			Textures.BlockIcons.LEAVES[1] = Textures.BlockIcons.LEAVES_MAPLE_ORANGE;
			Textures.BlockIcons.LEAVES[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_ORANGE;
			break;
		case 10:
			Textures.BlockIcons.LEAVES[1] = Textures.BlockIcons.LEAVES_MAPLE_RED;
			Textures.BlockIcons.LEAVES[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_RED;
			break;
		case 11: case 0:
			Textures.BlockIcons.LEAVES[1] = Textures.BlockIcons.LEAVES_MAPLE_BROWN;
			Textures.BlockIcons.LEAVES[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_BROWN;
			break;
		}
	}
	
	@Override
	public void onProxyBeforeInit(Abstract_Mod aMod, FMLInitializationEvent aEvent) {
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_MAP.values()) LH.add("gt.material." + tMaterial.mNameInternal, tMaterial.mNameLocal);
	}
	
	@Override
	public void onProxyAfterInit(Abstract_Mod aMod, FMLInitializationEvent aEvent) {
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) {
			LH.add("oredict.prefix." + tPrefix.mNameInternal, tPrefix.mNameLocal);
			tPrefix.mNameLocal = LH.get("oredict.prefix." + tPrefix.mNameInternal, tPrefix.mNameLocal);
		}
	}
	
	@Override
	public void onProxyAfterPostInit(Abstract_Mod aMod, FMLPostInitializationEvent aEvent) {
		// Initialising the List of Decorative Plank Icons
		for (int i = 0; i < PlankData.PLANKS.length; i++) {
			Block tBlock = ST.block(PlankData.PLANKS[i]);
			if (tBlock != null && tBlock != NB) PlankData.PLANK_ICONS[i] = new IconContainerCopied(tBlock, ST.meta(PlankData.PLANKS[i]), SIDE_ANY);
		}
	}
	
	public static final List<short[]> sRainbow = new ArrayListNoNulls<>(), sPosR = new ArrayListNoNulls<>(), sPosG = new ArrayListNoNulls<>(), sPosB = new ArrayListNoNulls<>(), sPosA = new ArrayListNoNulls<>(), sNegR = new ArrayListNoNulls<>(), sNegG = new ArrayListNoNulls<>(), sNegB = new ArrayListNoNulls<>(), sNegA = new ArrayListNoNulls<>();
	
	@SubscribeEvent
	public void onTextureStitchedPre(TextureStitchEvent.Pre aEvent) {
		// You should thank me for fixing this Fluid Bug. Seriously, people just don't set the Icons of their registered Fluids...
		for (Fluid aFluid : FluidRegistry.getRegisteredFluids().values()) {
			if (aFluid.getIcon() == null || FluidsGT.BROKEN.contains(aFluid.getName())) {
				Block tBlock = aFluid.getBlock();
				if (tBlock != null && tBlock != NB) try {aFluid.setIcons(tBlock.getIcon(0, 0));} catch(Throwable e) {e.printStackTrace(DEB);}
			}
		}
	}
	
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent aEvent) {
		if (Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI || ST.invalid(aEvent.itemStack)) return;
		if (!DISPLAY_TEMP_TOOLTIP) {DISPLAY_TEMP_TOOLTIP = T; return;}
		
		String aRegName = ST.regName(aEvent.itemStack);
		short aMeta = ST.meta(aEvent.itemStack);
		byte aBlockMeta = (byte)(UT.Code.inside(0, 15, aMeta) ? aMeta : 0);
		Block aBlock = ST.block(aEvent.itemStack);
		
		if (aBlock == Blocks.dirt && aBlockMeta == 1) {
			aEvent.toolTip.set(0, "Coarse Dirt");
		}
		if (MD.RC.mLoaded && aEvent.itemStack.getTagCompound() == null && "Railcraft:part.plate".equalsIgnoreCase(aRegName)) {
			switch(aMeta) {
			case 0: aEvent.toolTip.set(0, LH.Chat.WHITE+MT.Fe.getLocal()+" Plate"); break;
			case 1: aEvent.toolTip.set(0, LH.Chat.WHITE+MT.Steel.getLocal()+" Plate"); break;
			case 2: aEvent.toolTip.set(0, LH.Chat.WHITE+MT.TinAlloy.getLocal()+" Plate"); break;
			case 3: aEvent.toolTip.set(0, LH.Chat.WHITE+MT.Cu.getLocal()+" Plate"); break;
			case 4: aEvent.toolTip.set(0, LH.Chat.WHITE+MT.Pb.getLocal()+" Plate"); break;
			}
		}
		
		OreDictItemData tData = OM.anydata_(aEvent.itemStack);
		
		if (UT.NBT.getNBT(aEvent.itemStack).getBoolean("gt.err.oredict.output")) {
			aEvent.toolTip.clear();
			aEvent.toolTip.add(0, "A Recipe used an OreDict Item as Output directly, without copying it before!");
			aEvent.toolTip.add(1, "This is a typical CallByReference/CallByValue Error of the Modder doing it.");
			aEvent.toolTip.add(2, "Please check all Recipes outputting this Item, and report the Recipes to their Owner.");
			aEvent.toolTip.add(3, "The Owner of the RECIPE, NOT the Owner of the Item!");
			return;
		}
		
		if (ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.contains(aEvent.itemStack, T)) {
			aEvent.toolTip.add(LH.Chat.BLINKING_RED + "Recipe has been removed in favour of the GregTech Ender Garbage Bin");
		}
		
		ICover tCover = CoverRegistry.get(aEvent.itemStack);
		if (tCover != null) tCover.addToolTips(aEvent.toolTip, aEvent.itemStack, aEvent.showAdvancedItemTooltips);
		
		
		if (aBlock != NB && aBlock != null) {
			if (IL.TC_Warded_Glass.equal(aEvent.itemStack, F, T)) {
				aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, 999));
			} else if (ItemsGT.SHOW_RESISTANCE.contains(aEvent.itemStack, T)) {
				aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, aBlock.getExplosionResistance(null)));
				aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aBlock.getHarvestTool(aBlockMeta), "Pickaxe") + " ("+aBlock.getHarvestLevel(aBlockMeta)+")");
			}
		}
		
		
		if (BooksGT.BOOK_REGISTER.containsKey(new ItemStackContainer(aEvent.itemStack)) || BooksGT.BOOK_REGISTER.containsKey(new ItemStackContainer(aEvent.itemStack, W))) {
			aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_SHELFABLE));
		}
		
		
		if (aEvent.itemStack.getItem().isBeaconPayment(aEvent.itemStack)) {
			aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_BEACON_PAYMENT));
		}
		
		
		if (!(aEvent.itemStack.getItem() instanceof ItemFluidDisplay) && SHOW_INTERNAL_NAMES) {
			if (tData != null && tData.hasValidPrefixMaterialData()) {
				if (tData.mBlackListed) {
					if (aEvent.itemStack.getItem() instanceof IItemGT)
					aEvent.toolTip.add(LH.Chat.ORANGE + tData.toString());
					else
					aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta + LH.Chat.WHITE + " - " + LH.Chat.ORANGE + tData.toString());
				} else {
					if (aEvent.itemStack.getItem() instanceof IItemGT)
					aEvent.toolTip.add(LH.Chat.GREEN + tData.toString());
					else
					aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta + LH.Chat.WHITE + " - " + LH.Chat.GREEN + tData.toString());
				}
			} else {
				if (!(aEvent.itemStack.getItem() instanceof IItemGT))
				aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta);
			}
		}
		
		if (tData != null) {
			if (tData.mPrefix != null) {
				for (IOreDictListenerItem tListener : tData.mPrefix.mListenersItem) {
					String tToolTip = tListener.getListenerToolTip(aEvent.itemStack);
					if (tToolTip != null) aEvent.toolTip.add(tToolTip);
				}
			}
			for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) {
				for (IOreDictListenerItem tListener : tMaterial.mMaterial.mListenersItem) {
					String tToolTip = tListener.getListenerToolTip(aEvent.itemStack);
					if (tToolTip != null) aEvent.toolTip.add(tToolTip);
				}
			}
			
			if (tData.hasValidMaterialData()) {
				boolean tShowMaterialToolInfo = tData.mMaterial.mMaterial.mToolTypes > 0 && (tData.mPrefix != null || (aEvent.itemStack.getMaxStackSize() > 1 && tData.mByProducts.length == 0 && tData.mMaterial.mAmount <= U));
				if (tShowMaterialToolInfo) {
					aEvent.toolTip.add(LH.Chat.BLUE + "Q: " + tData.mMaterial.mMaterial.mToolQuality + " - S: " + tData.mMaterial.mMaterial.mToolSpeed + " - D: " + tData.mMaterial.mMaterial.mToolDurability);
				}
				if (SHOW_CHEM_FORMULAS && UT.Code.stringValid(tData.mMaterial.mMaterial.mTooltipChemical) && (tData.mPrefix == null ? tData.mByProducts.length == 0 : tData.mPrefix.contains(TD.Prefix.TOOLTIP_MATERIAL))) {
					aEvent.toolTip.add(LH.Chat.YELLOW + tData.mMaterial.mMaterial.mTooltipChemical);
				}
				if (tData.hasValidPrefixData()) {
					if (tData.mPrefix.contains(TD.Prefix.NEEDS_SHARPENING   )) aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_NEEDS_SHARPENING));
					if (tData.mPrefix.contains(TD.Prefix.NEEDS_HANDLE       )) aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_NEEDS_HANDLE) + LH.Chat.WHITE + tData.mMaterial.mMaterial.mHandleMaterial.getLocal());
					
					ArrayListNoNulls<Integer> tShapelessAmounts = new ArrayListNoNulls<>();
					for (AdvancedCrafting1ToY tHandler : tData.mPrefix.mShapelessManagersSingle ) if (tHandler.hasOutputFor(tData.mMaterial.mMaterial)) tShapelessAmounts.add(1);
					for (AdvancedCraftingXToY tHandler : tData.mPrefix.mShapelessManagers       ) if (tHandler.hasOutputFor(tData.mMaterial.mMaterial)) tShapelessAmounts.add(tHandler.mInputCount);
					if (!tShapelessAmounts.isEmpty()) {
						Collections.sort(tShapelessAmounts);
						aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_SHAPELESS_CRAFT) + LH.Chat.WHITE + tShapelessAmounts);
					}
				}
				if (tShowMaterialToolInfo) {
					if (!tData.mMaterial.mMaterial.mEnchantmentTools.isEmpty()) {
						aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_POSSIBLE_TOOL_ENCHANTS));
						for (ObjectStack<Enchantment> tEnchantment : tData.mMaterial.mMaterial.mEnchantmentTools ) {
							if (tEnchantment.mObject == Enchantment.fortune) {
								aEvent.toolTip.add(LH.Chat.PINK + Enchantment.fortune   .getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.looting .getTranslatedName((int)tEnchantment.mAmount));
							} else if (tEnchantment.mObject == Enchantment.knockback) {
								aEvent.toolTip.add(LH.Chat.PINK + Enchantment.knockback .getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.punch   .getTranslatedName((int)tEnchantment.mAmount));
							} else if (tEnchantment.mObject == Enchantment.fireAspect) {
								aEvent.toolTip.add(LH.Chat.PINK + Enchantment.fireAspect.getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.flame   .getTranslatedName((int)tEnchantment.mAmount));
							} else {
								aEvent.toolTip.add(LH.Chat.PINK + tEnchantment.mObject.getTranslatedName((int)tEnchantment.mAmount));
							}
						}
					}
					if (!tData.mMaterial.mMaterial.mEnchantmentArmors.isEmpty() && (tData.mPrefix == null || !tData.mPrefix.containsAny(TD.Prefix.TOOL_HEAD, TD.Prefix.WEAPON_ALIKE, TD.Prefix.AMMO_ALIKE, TD.Prefix.TOOL_ALIKE))) {
						aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_POSSIBLE_ARMOR_ENCHANTS));
						for (ObjectStack<Enchantment> tEnchantment : tData.mMaterial.mMaterial.mEnchantmentArmors) {
							aEvent.toolTip.add(LH.Chat.PINK + tEnchantment.mObject.getTranslatedName((int)tEnchantment.mAmount));
						}
					}
				}
				if (tData.hasValidPrefixData() && !(aBlock instanceof MultiTileEntityBlockInternal)) {
					if (tData.mMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)) {
						if (tData.mMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) {
							aEvent.toolTip.add(LH.Chat.RED + LH.get(LH.TOOLTIP_FLAMMABLE_AND_EXPLOSIVE));
						} else {
							aEvent.toolTip.add(LH.Chat.RED + LH.get(LH.TOOLTIP_FLAMMABLE));
						}
					} else if (tData.mMaterial.mMaterial.contains(TD.Properties.EXPLOSIVE)) {
						aEvent.toolTip.add(LH.Chat.RED + LH.get(LH.TOOLTIP_EXPLOSIVE));
					}
				}
			}
			if (aEvent.showAdvancedItemTooltips) {
				boolean temp = T;
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) if (tMaterial.mAmount != 0 && !tMaterial.mMaterial.contains(TD.Properties.DONT_SHOW_THIS_COMPONENT)) {
					if (temp) {
						aEvent.toolTip.add(LH.Chat.DCYAN + LH.get(LH.TOOLTIP_CONTAINED_MATERIALS));
						temp = F;
					}
					StringBuilder tString = new StringBuilder(128);
					double aWeight = tMaterial.mAmount > 0 ? tMaterial.mMaterial.getWeight(tMaterial.mAmount) : 0;
					long tWeight = ((long)(aWeight*1000))%1000;
					if (tMaterial.mAmount > 0) {
						long tDigits = ((tMaterial.mAmount % U) * 1000) / U;
						tString.append(LH.Chat.WHITE).append(tMaterial.mAmount / U).append(".").append(tDigits<1?"000":tDigits<10?"00"+tDigits:tDigits<100?"0"+tDigits:tDigits).append(" ");
					} else {
						tString.append(LH.Chat.WHITE).append("?.??? ");
					}
					tString.append(LH.Chat.YELLOW   ).append(tMaterial.mMaterial.getLocal());
					tString.append(LH.Chat.WHITE    ).append(" (");
					tString.append(LH.Chat.CYAN     ).append("M: ");
					tString.append(LH.Chat.WHITE    ).append(tMaterial.mMaterial.mMeltingPoint);
					tString.append(LH.Chat.RED      ).append("K ");
					tString.append(LH.Chat.CYAN     ).append(" B: ");
					tString.append(LH.Chat.WHITE    ).append(tMaterial.mMaterial.mBoilingPoint);
					tString.append(LH.Chat.RED      ).append("K ");
					tString.append(LH.Chat.CYAN     ).append(" W: ");
					tString.append(LH.Chat.WHITE    ).append((long)aWeight).append(".").append(tWeight<1?"000":tWeight<10?"00"+tWeight:tWeight<100?"0"+tWeight:tWeight);
					tString.append(LH.Chat.YELLOW   ).append("kg");
					tString.append(LH.Chat.WHITE    ).append(")");
					aEvent.toolTip.add(tString.toString());
				}
			} else {
				aEvent.toolTip.add(LH.Chat.DGRAY + "Enable F3+H Mode for Info about contained Materials.");
			}
			if (aEvent.itemStack.getItem() instanceof IItemGT && tData.hasValidPrefixMaterialData()) {
				if (tData.mMaterial.mMaterial.mOriginalMod == null) {
					aEvent.toolTip.add(LH.Chat.BLUE + "Mod: Unknown (But definitely not GregTech)");
				} else {
					if (tData.mMaterial.mMaterial.mOriginalMod == MD.MC) {
						aEvent.toolTip.add(LH.Chat.BLUE + "Mod: None (Vanilla Material)");
					} else {
						aEvent.toolTip.add(LH.Chat.BLUE + "Mod: " + tData.mMaterial.mMaterial.mOriginalMod.mName);
					}
				}
			}
		}
	}
	
	public boolean mNeedsToHideMicroblocks = T;
	
	@SubscribeEvent
	public void onClientTickEvent(ClientTickEvent aEvent) {
		if (aEvent.phase == Phase.END) {
			// Now for hiding stuff from NEI that should have never been there in the first place.
			if (mNeedsToHideMicroblocks) {
				if (!SHOW_MICROBLOCKS && NEI) for (ItemStack aStack : new ItemStack[] {ST.make(MD.FMB, "microblock", 1, W), ST.make(MD.ExU, "microblocks", 1, W), ST.make(MD.AE, "item.ItemFacade", 1, W)}) if (ST.valid(aStack)) {
					ST.hide(aStack);
					
					List<ItemStack> tList = new ArrayListNoNulls<>();
					aStack.getItem().getSubItems(aStack.getItem(), CreativeTabs.tabAllSearch, tList);
					for (ItemStack tStack : tList) ST.hide(tStack);
				}
				mNeedsToHideMicroblocks = F;
			}
			
			switch((int)(CLIENT_TIME % 10)) {
			case   0: LH.Chat.RAINBOW_FAST = LH.Chat.RED; LH.Chat.BLINKING_CYAN = LH.Chat.CYAN; LH.Chat.BLINKING_RED = LH.Chat.RED; break;
			case   1: LH.Chat.RAINBOW_FAST = LH.Chat.ORANGE; break;
			case   2: LH.Chat.RAINBOW_FAST = LH.Chat.YELLOW; break;
			case   3: LH.Chat.RAINBOW_FAST = LH.Chat.GREEN; break;
			case   4: LH.Chat.RAINBOW_FAST = LH.Chat.CYAN; break;
			case   5: LH.Chat.RAINBOW_FAST = LH.Chat.DCYAN; LH.Chat.BLINKING_CYAN = LH.Chat.WHITE; LH.Chat.BLINKING_RED = LH.Chat.WHITE; break;
			case   6: LH.Chat.RAINBOW_FAST = LH.Chat.DBLUE; break;
			case   7: LH.Chat.RAINBOW_FAST = LH.Chat.BLUE; break;
			case   8: LH.Chat.RAINBOW_FAST = LH.Chat.PURPLE; break;
			case   9: LH.Chat.RAINBOW_FAST = LH.Chat.PINK; break;
			}
			
			switch((int)(CLIENT_TIME % 50)) {
			case   0: LH.Chat.RAINBOW = LH.Chat.RED; break;
			case   5: LH.Chat.RAINBOW = LH.Chat.ORANGE; break;
			case  10: LH.Chat.RAINBOW = LH.Chat.YELLOW; break;
			case  15: LH.Chat.RAINBOW = LH.Chat.GREEN; break;
			case  20: LH.Chat.RAINBOW = LH.Chat.CYAN; break;
			case  25: LH.Chat.RAINBOW = LH.Chat.DCYAN; break;
			case  30: LH.Chat.RAINBOW = LH.Chat.DBLUE; break;
			case  35: LH.Chat.RAINBOW = LH.Chat.BLUE; break;
			case  40: LH.Chat.RAINBOW = LH.Chat.PURPLE; break;
			case  45: LH.Chat.RAINBOW = LH.Chat.PINK; break;
			}
			
			switch((int)(CLIENT_TIME % 250)) {
			case   0: LH.Chat.RAINBOW_SLOW = LH.Chat.RED; break;
			case  25: LH.Chat.RAINBOW_SLOW = LH.Chat.ORANGE; break;
			case  50: LH.Chat.RAINBOW_SLOW = LH.Chat.YELLOW; break;
			case  75: LH.Chat.RAINBOW_SLOW = LH.Chat.GREEN; break;
			case 100: LH.Chat.RAINBOW_SLOW = LH.Chat.CYAN; break;
			case 125: LH.Chat.RAINBOW_SLOW = LH.Chat.DCYAN; break;
			case 150: LH.Chat.RAINBOW_SLOW = LH.Chat.DBLUE; break;
			case 175: LH.Chat.RAINBOW_SLOW = LH.Chat.BLUE; break;
			case 200: LH.Chat.RAINBOW_SLOW = LH.Chat.PURPLE; break;
			case 225: LH.Chat.RAINBOW_SLOW = LH.Chat.PINK; break;
			}
			
			int tDirection = (CLIENT_TIME % 100 < 50 ? +1 : -1);
			for (short[] tArray : sPosR) tArray[0] = UT.Code.bind8(tArray[0]+tDirection);
			for (short[] tArray : sPosG) tArray[1] = UT.Code.bind8(tArray[1]+tDirection);
			for (short[] tArray : sPosB) tArray[2] = UT.Code.bind8(tArray[2]+tDirection);
			for (short[] tArray : sPosA) tArray[3] = UT.Code.bind8(tArray[3]+tDirection);
			for (short[] tArray : sNegR) tArray[0] = UT.Code.bind8(tArray[0]-tDirection);
			for (short[] tArray : sNegG) tArray[1] = UT.Code.bind8(tArray[1]-tDirection);
			for (short[] tArray : sNegB) tArray[2] = UT.Code.bind8(tArray[2]-tDirection);
			for (short[] tArray : sNegA) tArray[3] = UT.Code.bind8(tArray[3]-tDirection);
			
			boolean
			tNR = UT.Code.inside(  0,  99, (CLIENT_TIME/2) % 300), tNG = UT.Code.inside( 50, 149, (CLIENT_TIME/2) % 300), tNB = UT.Code.inside(100, 199, (CLIENT_TIME/2) % 300),
			tPR = UT.Code.inside(100, 199, (CLIENT_TIME/2) % 300), tPG = UT.Code.inside(150, 249, (CLIENT_TIME/2) % 300), tPB = UT.Code.inside(200, 299, (CLIENT_TIME/2) % 300);
			
			for (short[] tArray : sRainbow) {
			if (tPR) tArray[0] = UT.Code.bind8(tArray[0] + 1);
			if (tPG) tArray[1] = UT.Code.bind8(tArray[1] + 1);
			if (tPB) tArray[2] = UT.Code.bind8(tArray[2] + 1);
			if (tNR) tArray[0] = UT.Code.bind8(tArray[0] - 1);
			if (tNG) tArray[1] = UT.Code.bind8(tArray[1] - 1);
			if (tNB) tArray[2] = UT.Code.bind8(tArray[2] - 1);
			}
			
			CLIENT_TIME++;
		}
	}
	
	@SubscribeEvent
	public void onDrawBlockHighlight(DrawBlockHighlightEvent aEvent) {
		Block aBlock = aEvent.player.worldObj.getBlock(aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ);
		TileEntity aTileEntity = WD.te(aEvent.player.worldObj, aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ, T);
		if (!(aTileEntity instanceof ITileEntityOnDrawBlockHighlight) || !((ITileEntityOnDrawBlockHighlight)aTileEntity).onDrawBlockHighlight(aEvent)) {
			if ((ROTATABLE_VANILLA_BLOCKS.contains(aBlock) || (ToolCompat.IC_WRENCHABLE && aTileEntity instanceof ic2.api.tile.IWrenchable)) && ST.valid(aEvent.currentItem) && ToolsGT.contains(TOOL_wrench, aEvent.currentItem)) {
				drawGrid(aEvent);
				return;
			}
		}
	}
	
	private static List<Block> ROTATABLE_VANILLA_BLOCKS = Arrays.asList(Blocks.piston, Blocks.sticky_piston, Blocks.furnace, Blocks.lit_furnace, Blocks.dropper, Blocks.dispenser, Blocks.chest, Blocks.trapped_chest, Blocks.ender_chest, Blocks.hopper, Blocks.pumpkin, Blocks.lit_pumpkin);
	
	private static void drawGrid(DrawBlockHighlightEvent aEvent) {
		try {
			Class.forName("codechicken.lib.vec.Rotation");
			GL11.glPushMatrix();
			GL11.glTranslated(-(aEvent.player.lastTickPosX + (aEvent.player.posX - aEvent.player.lastTickPosX) * aEvent.partialTicks), -(aEvent.player.lastTickPosY + (aEvent.player.posY - aEvent.player.lastTickPosY) * aEvent.partialTicks), -(aEvent.player.lastTickPosZ + (aEvent.player.posZ - aEvent.player.lastTickPosZ) * aEvent.partialTicks));
			GL11.glTranslated(aEvent.target.blockX + 0.5F, aEvent.target.blockY + 0.5F, aEvent.target.blockZ + 0.5F);
			codechicken.lib.vec.Rotation.sideRotations[aEvent.target.sideHit].glApply();
			GL11.glTranslated(0, -0.501, 0);
			GL11.glLineWidth(2.0F);
			GL11.glColor4f(0, 0, 0, 0.5F);
			GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex3d( 0.50, 0, -0.25);
			GL11.glVertex3d(-0.50, 0, -0.25);
			GL11.glVertex3d( 0.50, 0,  0.25);
			GL11.glVertex3d(-0.50, 0,  0.25);
			GL11.glVertex3d( 0.25, 0, -0.50);
			GL11.glVertex3d( 0.25, 0,  0.50);
			GL11.glVertex3d(-0.25, 0, -0.50);
			GL11.glVertex3d(-0.25, 0,  0.50);
			GL11.glEnd();
			GL11.glPopMatrix();
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
	}
}
