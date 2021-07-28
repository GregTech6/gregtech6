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

package gregapi;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import gregapi.api.Abstract_Mod;
import gregapi.block.IBlockBase;
import gregapi.block.ToolCompat;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.prefixblock.PrefixBlockFallingEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ObjectStack;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ICover;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.PlankData;
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.item.ItemFluidDisplay;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.listeners.IOreDictListenerItem;
import gregapi.recipes.AdvancedCrafting1ToY;
import gregapi.recipes.AdvancedCraftingXToY;
import gregapi.render.ITexture;
import gregapi.render.IconContainerCopied;
import gregapi.render.RenderHelper;
import gregapi.render.RendererBlockFluid;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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
	
	public GT_API_Proxy_Client() {
		super();
		CODE_SERVER = T;
		CODE_CLIENT = T;
		CODE_UNCHECKED = F;
		
		for (int i = 0; i < 4; i++) {
			sPosR.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.Force.mRGBa[i], MT.Pyrotheum.mRGBa[i], MT.Sunnarium.mRGBa[i], MT.Mcg.mRGBa[i], MT.Thaumium.mRGBa[i], MT.InfusedVis.mRGBa[i], MT.InfusedAir.mRGBa[i], MT.InfusedFire.mRGBa[i], MT.FierySteel.mRGBa[i], MT.Firestone.mRGBa[i], MT.ArcaneAsh.mRGBa[i]));
			sPosG.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.Force.mRGBa[i], MT.Pyrotheum.mRGBa[i], MT.Sunnarium.mRGBa[i], MT.InfusedAir.mRGBa[i], MT.InfusedEarth.mRGBa[i]));
			sPosB.addAll(Arrays.asList(MT.ChargedCertusQuartz.mRGBa[i], MT.Enderium.mRGBa[i], MT.Vinteum.mRGBa[i], MT.U_235.mRGBa[i], MT.Am_241.mRGBa[i], MT.Pu_241.mRGBa[i], MT.Pu_243.mRGBa[i], MT.Nq_528.mRGBa[i], MT.Nq_522.mRGBa[i], MT.InfusedOrder.mRGBa[i], MT.Mcg.mRGBa[i], MT.InfusedVis.mRGBa[i], MT.InfusedWater.mRGBa[i], MT.Thaumium.mRGBa[i], MT.Co_60.mRGBa[i], MT.Lumium.mRGBa[i], MT.VinteumPurified.mRGBa[i], MT.ArcaneAsh.mRGBa[i]));
			sNegR.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sNegG.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sNegB.addAll(Arrays.asList(MT.InfusedEntropy.mRGBa[i], MT.NetherStar.mRGBa[i]));
			sRainbow.addAll(Arrays.asList(MT.GaiaSpirit.mRGBa[i], MT.GaiaSpirit.mRGBa[i], MT.Shimmerwood.mRGBa[i], MT.Shimmerwood.mRGBa[i], MT.Chimerite.mRGBa[i]));
			sRainbowFast.addAll(Arrays.asList(MT.Infinity.mRGBa[i], MT.InfusedBalance.mRGBa[i]));
		}
	}
	
	@Override
	public int addArmor(String aPrefix) {
		try {return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);} catch(Throwable e) {/**/}
		return 0;
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
		RenderingRegistry.registerBlockHandler(new RendererBlockFluid(RenderingRegistry.getNextAvailableRenderId()));
		RenderingRegistry.registerBlockHandler(new RendererBlockTextured(RenderingRegistry.getNextAvailableRenderId()));
		FluidRegistry.renderIdFluid = RendererBlockFluid.RENDER_ID;
		// Check if OptiFine is loaded in order to disable some GT Render Hooks to fix Glitches.
		ITexture.Util.OPTIFINE_LOADED = FMLClientHandler.instance().hasOptifine();
		
		if (XMAS_IN_JULY) {
			// Christmas in July! Go look it up, it is an actual thing!
			Textures.BlockIcons.LEAVES_CD[0] = Textures.BlockIcons.LEAVES_BLUESPRUCE_XMAS;
			Textures.BlockIcons.LEAVES_CD[8] = Textures.BlockIcons.LEAVES_OPAQUE_BLUESPRUCE_XMAS;
		}
		if (XMAS_IN_DECEMBER) {
			// Normal Holiday Season!
			Textures.BlockIcons.LEAVES_CD[0] = Textures.BlockIcons.LEAVES_BLUESPRUCE_XMAS;
			Textures.BlockIcons.LEAVES_CD[8] = Textures.BlockIcons.LEAVES_OPAQUE_BLUESPRUCE_XMAS;
		}
		
		Date tDate = new Date();
		
		switch (tDate.getMonth()+1) {// Not going to use Calendar, because it fucking crashes with Missing Resource Exception...
		case  1:
			Textures.BlockIcons.LEAVES_AB[1] = Textures.BlockIcons.LEAVES_MAPLE_BROWN;
			Textures.BlockIcons.LEAVES_AB[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_BROWN;
			break;
		case  9:
			Textures.BlockIcons.LEAVES_AB[1] = Textures.BlockIcons.LEAVES_MAPLE_YELLOW;
			Textures.BlockIcons.LEAVES_AB[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_YELLOW;
			break;
		case 10:
			Textures.BlockIcons.LEAVES_AB[1] = Textures.BlockIcons.LEAVES_MAPLE_ORANGE;
			Textures.BlockIcons.LEAVES_AB[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_ORANGE;
			break;
		case 11:
			Textures.BlockIcons.LEAVES_AB[1] = Textures.BlockIcons.LEAVES_MAPLE_RED;
			Textures.BlockIcons.LEAVES_AB[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_RED;
			break;
		case 12:
			Textures.BlockIcons.LEAVES_AB[1] = Textures.BlockIcons.LEAVES_MAPLE_BROWN;
			Textures.BlockIcons.LEAVES_AB[9] = Textures.BlockIcons.LEAVES_OPAQUE_MAPLE_BROWN;
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
			if (tBlock != null && tBlock != NB) PlankData.PLANK_ICONS[i] = new IconContainerCopied(tBlock, ST.meta_(PlankData.PLANKS[i]), SIDE_ANY);
		}
	}

	public static final List<short[]> sRainbow = new ArrayListNoNulls<>(), sRainbowFast = new ArrayListNoNulls<>(), sPosR = new ArrayListNoNulls<>(), sPosG = new ArrayListNoNulls<>(), sPosB = new ArrayListNoNulls<>(), sPosA = new ArrayListNoNulls<>(), sNegR = new ArrayListNoNulls<>(), sNegG = new ArrayListNoNulls<>(), sNegB = new ArrayListNoNulls<>(), sNegA = new ArrayListNoNulls<>();

	@SubscribeEvent
	public void onTextureStitchedPre(TextureStitchEvent.Pre aEvent) {
		// You should thank me for fixing this Fluid Bug. Seriously, some people just don't set the Icons of their registered Fluids...
		for (Fluid aFluid : FluidRegistry.getRegisteredFluids().values()) {
			if (aFluid.getIcon() == null || FluidsGT.BROKEN.contains(aFluid.getName())) {
				try {
					Block tBlock = aFluid.getBlock();
					aFluid.setIcons((tBlock != null && tBlock != NB ? tBlock : Blocks.water).getIcon(0, 0));
				} catch(Throwable e) {
					e.printStackTrace(ERR);
					try {aFluid.setIcons(Blocks.water.getIcon(0, 0));} catch(Throwable f) {f.printStackTrace(ERR);}
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent aEvent) {
		if (Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI || ST.invalid(aEvent.itemStack)) return;
		if (!DISPLAY_TEMP_TOOLTIP) {DISPLAY_TEMP_TOOLTIP = T; return;}
		
		try {
			if (UT.NBT.getNBT(aEvent.itemStack).getBoolean("gt.err.oredict.output")) {
				aEvent.toolTip.clear();
				aEvent.toolTip.add(0, LH.Chat.BLINKING_RED+"A Recipe used an OreDict Item as Output directly, without copying it before!");
				aEvent.toolTip.add(1, LH.Chat.BLINKING_RED+"This is a typical CallByReference/CallByValue Error of the Modder doing it.");
				aEvent.toolTip.add(2, LH.Chat.BLINKING_RED+"Please check all Recipes outputting this Item, and report the Recipes to their Owner.");
				aEvent.toolTip.add(3, LH.Chat.BLINKING_RED+"The Owner of the RECIPE, NOT the Owner of the Item!");
				return;
			}
			
			String aRegName = ST.regName(aEvent.itemStack);
			if (aRegName == null) {
				aEvent.toolTip.set(0, LH.Chat.BLINKING_RED+"ERROR: THIS ITEM HAS NOT BEEN REGISTERED!!!");
				aRegName = "ERROR: THIS ITEM HAS NOT BEEN REGISTERED!!!";
			}
			short aMeta = ST.meta_(aEvent.itemStack);
			byte aBlockMeta = UT.Code.bind4(aMeta);
			Block aBlock = ST.block(aEvent.itemStack);
			Item aItem = ST.item(aEvent.itemStack);
			
			if (aEvent.itemStack.getTagCompound() == null) {
				if (aBlock == Blocks.dirt && aBlockMeta == 1) {
					aEvent.toolTip.set(0, aEvent.toolTip.get(0).replaceAll("Dirt", "Coarse Dirt"));
				}
				if (MD.RC.mLoaded && "Railcraft:part.plate".equalsIgnoreCase(aRegName)) {
					switch(aMeta) {
					case 0: aEvent.toolTip.set(0, LH.Chat.WHITE+LH.get("oredict.plateIron.name")); break;
					case 1: aEvent.toolTip.set(0, LH.Chat.WHITE+LH.get("oredict.plateSteel.name")); break;
					case 2: aEvent.toolTip.set(0, LH.Chat.WHITE+LH.get("oredict.plateTinAlloy.name")); break;
					case 3: aEvent.toolTip.set(0, LH.Chat.WHITE+LH.get("oredict.plateCopper.name")); break;
					case 4: aEvent.toolTip.set(0, LH.Chat.WHITE+LH.get("oredict.plateLead.name")); break;
					}
				}
			}
			
			if (MD.Mek.owns(aRegName)) {
				aEvent.toolTip.set(0, aEvent.toolTip.get(0).replaceAll("Osmium", "Germanium"));
			}
			
			if (ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.contains(aEvent.itemStack, T)) {
				aEvent.toolTip.add(LH.Chat.BLINKING_RED + "Recipe has been removed in favour of the GregTech Ender Garbage Bin");
			}
			
			ICover tCover = CoverRegistry.get(aEvent.itemStack);
			if (tCover != null) tCover.addToolTips(aEvent.toolTip, aEvent.itemStack, aEvent.showAdvancedItemTooltips);
			
			if (aBlock != NB) {
				if (IL.TC_Warded_Glass.equal(aEvent.itemStack, F, T)) {
					aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, 999));
				} else if (ItemsGT.SHOW_RESISTANCE.contains(aEvent.itemStack, T)) {
					if (IL.ICBM_Concrete.block() == aBlock) {
						switch(aMeta) {
						default: aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, 30)); break;
						case  1: aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, 38)); break;
						case  2: aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, 48)); break;
						}
					} else {
						aEvent.toolTip.add(LH.getToolTipBlastResistance(aBlock, aBlock.getExplosionResistance(null)));
					}
					aEvent.toolTip.add(LH.getToolTipHarvest(aBlock.getMaterial(), aBlock.getHarvestTool(aBlockMeta), aBlock.getHarvestLevel(aBlockMeta)));
				}
				if (BlocksGT.openableCrowbar.contains(aBlock)) {
					aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_OPEN_CROWBAR));
				}
			}
			
			if (BooksGT.BOOK_REGISTER.containsKey(aEvent.itemStack, T)) {
				aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_SHELFABLE));
			}
			
			if (aItem.isBeaconPayment(aEvent.itemStack)) {
				aEvent.toolTip.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_BEACON_PAYMENT));
			}
			
			OreDictItemData tData = OM.anydata_(aEvent.itemStack);
			
			if (!(aItem instanceof ItemFluidDisplay) && SHOW_INTERNAL_NAMES) {
				if (tData != null && tData.hasValidPrefixMaterialData()) {
					if (tData.mBlackListed) {
						if (ST.isGT(aItem))
						aEvent.toolTip.add(LH.Chat.ORANGE + tData.toString());
						else
						aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta + LH.Chat.WHITE + " - " + LH.Chat.ORANGE + tData.toString());
					} else {
						if (ST.isGT(aItem))
						aEvent.toolTip.add(LH.Chat.GREEN + tData.toString());
						else
						aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta + LH.Chat.WHITE + " - " + LH.Chat.GREEN + tData.toString());
					}
				} else {
					if (!ST.isGT(aItem))
					aEvent.toolTip.add(LH.Chat.DCYAN + aRegName + LH.Chat.WHITE + " - " + LH.Chat.CYAN + aMeta);
				}
			}
			
			if (tData != null) {
				if (tData.hasValidPrefixData()) {
					for (IOreDictListenerItem tListener : tData.mPrefix.mListenersItem) {
						String tToolTip = tListener.getListenerToolTip(tData.mPrefix, tData.mMaterial.mMaterial, aEvent.itemStack);
						if (tToolTip != null) aEvent.toolTip.add(tToolTip);
					}
				} else {
					if (IL.RC_Firestone_Refined.equal(aEvent.itemStack, T, T)) aEvent.toolTip.add(LH.Chat.CYAN + "Works in Burning Boxes ("+(800*EU_PER_LAVA)+" HU per Lava Block)"); else
					if (IL.RC_Firestone_Cracked.equal(aEvent.itemStack, T, T)) aEvent.toolTip.add(LH.Chat.CYAN + "Works in Burning Boxes ("+(600*EU_PER_LAVA)+" HU per Lava Block)"); else
					if (IL.TF_Pick_Giant       .equal(aEvent.itemStack, T, T)) aEvent.toolTip.add(LH.Chat.CYAN + "Can be repaired with Knightmetal Ingots on the Anvil"); else
					if (IL.TF_Sword_Giant      .equal(aEvent.itemStack, T, T)) aEvent.toolTip.add(LH.Chat.CYAN + "Can be repaired with Ironwood Ingots on the Anvil"); else
					if (IL.TF_Lamp_of_Cinders  .equal(aEvent.itemStack, T, T)) aEvent.toolTip.add(LH.Chat.CYAN + "Can be used as a Lighter for GT6 things and TNT");
				}
				if (tData.hasValidMaterialData()) {
					boolean tUnburnable = F;
					for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) {
						if (tMaterial.mMaterial.contains(TD.Properties.UNBURNABLE)) tUnburnable = T;
						for (IOreDictListenerItem tListener : tMaterial.mMaterial.mListenersItem) {
							String tToolTip = tListener.getListenerToolTip(tData.mPrefix, tData.mMaterial.mMaterial, aEvent.itemStack);
							if (tToolTip != null) aEvent.toolTip.add(tToolTip);
						}
					}
					if (tData.mMaterial.mMaterial.mToolTypes > 0 && (tData.mPrefix != null || (aEvent.itemStack.getMaxStackSize() > 1 && tData.mByProducts.length == 0 && tData.mMaterial.mAmount <= U))) {
						aEvent.toolTip.add(LH.Chat.BLUE + "Q: " + tData.mMaterial.mMaterial.mToolQuality + " - S: " + tData.mMaterial.mMaterial.mToolSpeed + " - D: " + tData.mMaterial.mMaterial.mToolDurability);
					}
					if (SHOW_CHEM_FORMULAS && UT.Code.stringValid(tData.mMaterial.mMaterial.mTooltipChemical) && (tData.mPrefix == null ? tData.mByProducts.length == 0 : tData.mPrefix.contains(TD.Prefix.TOOLTIP_MATERIAL))) {
						aEvent.toolTip.add(LH.Chat.YELLOW + tData.mMaterial.mMaterial.mTooltipChemical);
					}
					if (tData.mMaterial.mMaterial == MT.Nikolite) {
						aEvent.toolTip.set(0, aEvent.toolTip.get(0).replaceAll("(Teslatite|Electrotine)", "Nikolite"));
					}
					if (tData.mMaterial.mMaterial == MT.Ge) {
						aEvent.toolTip.set(0, aEvent.toolTip.get(0).replaceAll("Osmium", "Germanium"));
					}
					if (tData.hasValidPrefixData()) {
						if (tData.mPrefix.contains(TD.Prefix.NEEDS_SHARPENING)) aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_NEEDS_SHARPENING));
						if (tData.mPrefix.contains(TD.Prefix.NEEDS_HANDLE    )) aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_NEEDS_HANDLE) + LH.Chat.WHITE + tData.mMaterial.mMaterial.mHandleMaterial.getLocal());
						
						ArrayListNoNulls<Integer> tShapelessAmounts = new ArrayListNoNulls<>();
						for (AdvancedCrafting1ToY tHandler : tData.mPrefix.mShapelessManagersSingle) if (tHandler.hasOutputFor(tData.mMaterial.mMaterial)) tShapelessAmounts.add(1);
						for (AdvancedCraftingXToY tHandler : tData.mPrefix.mShapelessManagers      ) if (tHandler.hasOutputFor(tData.mMaterial.mMaterial)) tShapelessAmounts.add(tHandler.mInputCount);
						if (!tShapelessAmounts.isEmpty()) {
							Collections.sort(tShapelessAmounts);
							aEvent.toolTip.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_SHAPELESS_CRAFT) + LH.Chat.WHITE + tShapelessAmounts);
						}
						if (tData.mPrefix.contains(TD.Prefix.TOOLTIP_ENCHANTS)) {
							if (!tData.mMaterial.mMaterial.mEnchantmentTools.isEmpty()) {
								if (!tData.mPrefix.contains(TD.Prefix.AMMO_ALIKE)) {
									if (tData.mMaterial.mMaterial.mEnchantmentTools.size() <= 5) {
										aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_POSSIBLE_TOOL_ENCHANTS));
										for (ObjectStack<Enchantment> tEnchantment : tData.mMaterial.mMaterial.mEnchantmentTools) {
											if (tEnchantment.mObject == Enchantment.fortune) {
												aEvent.toolTip.add(LH.Chat.PINK + Enchantment.fortune   .getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.looting.getTranslatedName((int)tEnchantment.mAmount));
											} else if (tEnchantment.mObject == Enchantment.knockback) {
												aEvent.toolTip.add(LH.Chat.PINK + Enchantment.knockback .getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.punch  .getTranslatedName((int)tEnchantment.mAmount));
											} else if (tEnchantment.mObject == Enchantment.fireAspect) {
												if (tEnchantment.mAmount >= 3)
												aEvent.toolTip.add(LH.Chat.PINK + Enchantment.fireAspect.getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.flame  .getTranslatedName((int)tEnchantment.mAmount) + " / Auto Smelt I");
												else
												aEvent.toolTip.add(LH.Chat.PINK + Enchantment.fireAspect.getTranslatedName((int)tEnchantment.mAmount) + " / " + Enchantment.flame  .getTranslatedName((int)tEnchantment.mAmount));
											} else {
												aEvent.toolTip.add(LH.Chat.PINK + tEnchantment.mObject  .getTranslatedName((int)tEnchantment.mAmount));
											}
										}
									} else {
										aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_TOO_MANY_TOOL_ENCHANTS));
									}
								}
							}
							if (MD.BTL.mLoaded && tData.mMaterial.mMaterial.contains(TD.Properties.BETWEENLANDS)) {
								aEvent.toolTip.add(LH.Chat.GREEN + LH.get(LH.TOOLTIP_BETWEENLANDS_RESISTANCE));
							}
							if (!tData.mPrefix.containsAny(TD.Prefix.TOOL_HEAD, TD.Prefix.WEAPON_ALIKE, TD.Prefix.AMMO_ALIKE, TD.Prefix.TOOL_ALIKE)) {
								if (!tData.mMaterial.mMaterial.mEnchantmentArmors.isEmpty()) {
									if (tData.mMaterial.mMaterial.mEnchantmentArmors.size() <= 3) {
										aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_POSSIBLE_ARMOR_ENCHANTS));
										for (ObjectStack<Enchantment> tEnchantment : tData.mMaterial.mMaterial.mEnchantmentArmors) {
											aEvent.toolTip.add(LH.Chat.PINK + tEnchantment.mObject.getTranslatedName((int)tEnchantment.mAmount));
										}
									} else {
										aEvent.toolTip.add(LH.Chat.PURPLE + LH.get(LH.TOOLTIP_TOO_MANY_ARMOR_ENCHANTS));
									}
								}
								if ((IL.TF_Mazestone.exists() || IL.TF_Mazehedge.exists()) && tData.mMaterial.mMaterial.contains(TD.Properties.MAZEBREAKER)) {
									aEvent.toolTip.add(LH.Chat.PINK + LH.get(LH.TOOLTIP_TWILIGHT_MAZE_BREAKING));
								}
							}
						}
						if (aBlock == NB || !(aBlock instanceof MultiTileEntityBlockInternal || aBlock instanceof IBlockBase)) {
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
					if (tUnburnable && !MD.MC.owns(aRegName)) aEvent.toolTip.add(LH.Chat.GREEN + LH.get(LH.TOOLTIP_UNBURNABLE));
				}
				
				if (aEvent.showAdvancedItemTooltips) {
					boolean temp = T;
					for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) if (tMaterial.mAmount != 0 && !tMaterial.mMaterial.contains(TD.Properties.DONT_SHOW_THIS_COMPONENT)) {
						if (temp) {
							aEvent.toolTip.add(LH.Chat.DCYAN + LH.get(LH.TOOLTIP_CONTAINED_MATERIALS));
							temp = F;
						}
						StringBuilder tString = new StringBuilder(128);
						double aWeight = tMaterial.weight();
						long tWeight = ((long)(aWeight*1000))%1000;
						tString.append(LH.Chat.WHITE ).append(UT.Code.displayUnits(tMaterial.mAmount)).append(" ");
						tString.append(LH.Chat.YELLOW).append(tMaterial.mMaterial.getLocal());
						tString.append(LH.Chat.WHITE ).append(" (");
						tString.append(LH.Chat.CYAN  ).append("M: ");
						tString.append(LH.Chat.WHITE ).append(tMaterial.mMaterial.mMeltingPoint);
						tString.append(LH.Chat.RED   ).append("K ");
						tString.append(LH.Chat.CYAN  ).append(" B: ");
						tString.append(LH.Chat.WHITE ).append(tMaterial.mMaterial.mBoilingPoint);
						tString.append(LH.Chat.RED   ).append("K ");
						tString.append(LH.Chat.CYAN  ).append(" W: ");
						tString.append(LH.Chat.WHITE ).append((long)aWeight).append(".").append(tWeight<1?"000":tWeight<10?"00"+tWeight:tWeight<100?"0"+tWeight:tWeight);
						tString.append(LH.Chat.YELLOW).append("kg");
						tString.append(LH.Chat.WHITE ).append(")");
						aEvent.toolTip.add(tString.toString());
					}
				} else {
					aEvent.toolTip.add(LH.Chat.DGRAY + "Enable F3+H Mode for Info about contained Materials.");
				}
				if (ST.isGT(aItem) && tData.hasValidPrefixMaterialData()) {
					if (tData.mMaterial.mMaterial.mOriginalMod == null) {
						aEvent.toolTip.add(LH.Chat.BLUE + "Material from an Unknown Mod");
					} else if (tData.mMaterial.mMaterial.mOriginalMod == MD.MC) {
						aEvent.toolTip.add(LH.Chat.BLUE + "Vanilla Material");
					} else if (tData.mMaterial.mMaterial.mOriginalMod == MD.GAPI) {
						if (tData.mMaterial.mMaterial.mID > 0 && tData.mMaterial.mMaterial.mID < 8000) {
							aEvent.toolTip.add(LH.Chat.BLUE + "Material from the Periodic Table of Elements");
						} else {
							aEvent.toolTip.add(LH.Chat.BLUE + "Random Material handled by Greg API");
						}
					} else {
						aEvent.toolTip.add(LH.Chat.BLUE + "Material from " + tData.mMaterial.mMaterial.mOriginalMod.mName);
					}
				}
			}
			
			// Remove all Nulls and fix eventual Formatting mistakes.
			for (int i = 1, j = aEvent.toolTip.size(); i < j; i++) {
				String tTooltip = aEvent.toolTip.get(i);
				if (tTooltip == null) {aEvent.toolTip.remove(i--); j--;} else aEvent.toolTip.set(i, tTooltip + LH.Chat.RESET_TOOLTIP);
			}
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@SubscribeEvent
	public void onClientTickEvent(ClientTickEvent aEvent) {
		if (aEvent.phase == Phase.END) {
			if (CLIENT_TIME == 10) {
				// Initializing the Fake Furnace Recipe Map
				if (FL.XP.exists()) for (Object tObject : FurnaceRecipes.smelting().getSmeltingList().keySet()) if (tObject instanceof ItemStack) {
					RM.Furnace.addFakeRecipe(F, RM.Furnace.findRecipe(null, null, F, Long.MAX_VALUE, NI, ZL_FS, ST.array((ItemStack)tObject)));
				}
				// Now for hiding stuff from NEI that should have never been there in the first place.
				if (!SHOW_MICROBLOCKS && NEI) for (Item aItem : new Item[] {ST.item(MD.FMB, "microblock"), ST.item(MD.ExU, "microblocks"), ST.item(MD.ExS, "microblocks"), ST.item(MD.AE, "item.ItemFacade")}) if (aItem != null) {
					ST.hide(aItem);
					List<ItemStack> tList = new ArrayListNoNulls<>();
					aItem.getSubItems(aItem, CreativeTabs.tabAllSearch, tList);
					for (ItemStack tStack : tList) ST.hide(tStack);
				}
			}
			
			switch((int)(CLIENT_TIME % 10)) {
			case   0: LH.Chat.RAINBOW_FAST = LH.Chat.RED; LH.Chat.BLINKING_CYAN = LH.Chat.CYAN; LH.Chat.BLINKING_RED = LH.Chat.RED; LH.Chat.BLINKING_ORANGE = LH.Chat.ORANGE; break;
			case   1: LH.Chat.RAINBOW_FAST = LH.Chat.ORANGE; break;
			case   2: LH.Chat.RAINBOW_FAST = LH.Chat.YELLOW; break;
			case   3: LH.Chat.RAINBOW_FAST = LH.Chat.GREEN; break;
			case   4: LH.Chat.RAINBOW_FAST = LH.Chat.CYAN; break;
			case   5: LH.Chat.RAINBOW_FAST = LH.Chat.DCYAN; LH.Chat.BLINKING_CYAN = LH.Chat.WHITE; LH.Chat.BLINKING_RED = LH.Chat.WHITE; LH.Chat.BLINKING_ORANGE = LH.Chat.YELLOW; break;
			case   6: LH.Chat.RAINBOW_FAST = LH.Chat.DBLUE; break;
			case   7: LH.Chat.RAINBOW_FAST = LH.Chat.BLUE; break;
			case   8: LH.Chat.RAINBOW_FAST = LH.Chat.PURPLE; break;
			case   9: LH.Chat.RAINBOW_FAST = LH.Chat.PINK; break;
			}
			
			switch((int)(CLIENT_TIME % 50)) {
			case   0: LH.Chat.RAINBOW = LH.Chat.RED; LH.Chat.BLINKING_GRAY = LH.Chat.GRAY; break;
			case   5: LH.Chat.RAINBOW = LH.Chat.ORANGE; break;
			case  10: LH.Chat.RAINBOW = LH.Chat.YELLOW; break;
			case  15: LH.Chat.RAINBOW = LH.Chat.GREEN; break;
			case  20: LH.Chat.RAINBOW = LH.Chat.CYAN; break;
			case  25: LH.Chat.RAINBOW = LH.Chat.DCYAN; LH.Chat.BLINKING_GRAY = LH.Chat.DGRAY; break;
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
			
			tNR = UT.Code.inside( 0,  9, (CLIENT_TIME/2) % 30); tNG = UT.Code.inside( 5, 14, (CLIENT_TIME/2) % 30); tNB = UT.Code.inside(10, 19, (CLIENT_TIME/2) % 30);
			tPR = UT.Code.inside(10, 19, (CLIENT_TIME/2) % 30); tPG = UT.Code.inside(15, 24, (CLIENT_TIME/2) % 30); tPB = UT.Code.inside(20, 29, (CLIENT_TIME/2) % 30);
			
			for (short[] tArray : sRainbowFast) {
			if (tPR) tArray[0] = UT.Code.bind8(tArray[0] + 10);
			if (tPG) tArray[1] = UT.Code.bind8(tArray[1] + 10);
			if (tPB) tArray[2] = UT.Code.bind8(tArray[2] + 10);
			if (tNR) tArray[0] = UT.Code.bind8(tArray[0] - 10);
			if (tNG) tArray[1] = UT.Code.bind8(tArray[1] - 10);
			if (tNB) tArray[2] = UT.Code.bind8(tArray[2] - 10);
			}
			
			CLIENT_TIME++;
		}
	}
	
	@SubscribeEvent
	public void onDrawBlockHighlight(DrawBlockHighlightEvent aEvent) {
		Block
		aBlock = ST.block(aEvent.player.getCurrentEquippedItem());
		if (aBlock instanceof BlockMetaType && ((BlockMetaType)aBlock).mIsSlab) {
			RenderHelper.drawWrenchOverlay(aEvent.player, aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ, (byte)0, (byte)aEvent.target.sideHit, aEvent.partialTicks);
			return;
		}
		aBlock = aEvent.player.worldObj.getBlock(aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ);
		TileEntity aTileEntity = WD.te(aEvent.player.worldObj, aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ, T);
		if (!(aTileEntity instanceof ITileEntityOnDrawBlockHighlight) || !((ITileEntityOnDrawBlockHighlight)aTileEntity).onDrawBlockHighlight(aEvent)) {
			if ((ROTATABLE_VANILLA_BLOCKS.contains(aBlock) || (ToolCompat.IC_WRENCHABLE && aTileEntity instanceof ic2.api.tile.IWrenchable)) && ST.valid(aEvent.currentItem) && ToolsGT.contains(TOOL_wrench, aEvent.currentItem)) {
				RenderHelper.drawWrenchOverlay(aEvent.player, aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ, (byte)0, (byte)aEvent.target.sideHit, aEvent.partialTicks);
				return;
			}
		}
	}
	
	private static List<Block> ROTATABLE_VANILLA_BLOCKS = Arrays.asList(Blocks.piston, Blocks.sticky_piston, Blocks.furnace, Blocks.lit_furnace, Blocks.dropper, Blocks.dispenser, Blocks.chest, Blocks.trapped_chest, Blocks.ender_chest, Blocks.hopper, Blocks.pumpkin, Blocks.lit_pumpkin);
}
