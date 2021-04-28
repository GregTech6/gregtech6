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

package gregapi.block;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLLog;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.lang.LanguageHandler;
import gregapi.oredict.OreDictItemData;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import ic2.api.crops.ICropTile;
import ic2.api.tile.IWrenchable;
import micdoodle8.mods.galacticraft.core.blocks.BlockAdvanced;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fluids.IFluidBlock;

/**
 * @author Gregorius Techneticies
 * 
 * For Internal Use.
 */
public class ToolCompat {
	public static boolean GC_BLOCKADVANCED = F, IC_WRENCHABLE = F, IC_CROPTILE = F;
	
	public static void checkAvailabilities() {
		try {
			BlockAdvanced.class.getCanonicalName();
			GC_BLOCKADVANCED = T;
		} catch(Throwable e) {/**/}
		try {
			IWrenchable.class.getCanonicalName();
			IC_WRENCHABLE = T;
		} catch(Throwable e) {/**/}
		try {
			ICropTile.class.getCanonicalName();
			IC_CROPTILE = T;
		} catch(Throwable e) {/**/}
	}
	
	/** Providing compatibility for vanilla Blocks and certain Mod Interfaces. */
	public static long onToolClick(Block aBlock, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		TileEntity aTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		EntityPlayer aEntityPlayer = aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null;
		EntityLivingBase aEntityLiving = aPlayer instanceof EntityLivingBase ? (EntityLivingBase)aPlayer : null;
		
		try {
		
		if (aTool.equals(TOOL_hoe) && (aEntityPlayer == null || aEntityPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack))) {
			if (!MinecraftForge.EVENT_BUS.post(new UseHoeEvent(aEntityPlayer, aStack, aWorld, aX, aY, aZ))) {
				if (SIDES_TOP_HORIZONTAL[aSide] && !WD.hasCollide(aWorld, aX, aY+1, aZ) && (aBlock == Blocks.grass || aBlock == Blocks.dirt || aBlock == BlocksGT.Grass || IL.EtFu_Path.equal(aBlock) || IL.BoP_Grass_Origin.equal(aBlock) || IL.BoP_Grass_Long.equal(aBlock))) {
					aWorld.playSoundEffect(aX + 0.5F, aY + 0.5F, aZ + 0.5F, Blocks.farmland.stepSound.getStepResourcePath(), (Blocks.farmland.stepSound.getVolume() + 1.0F) * 0.5F, Blocks.farmland.stepSound.getPitch() * 0.8F);
					if (!aWorld.isRemote) aWorld.setBlock(aX, aY, aZ, Blocks.farmland);
					return 10000;
				}
			}
		}
		
		if (aWorld.isRemote) return 0;
		
		if (aTool.equals(TOOL_axe) || aTool.equals(TOOL_saw) || aTool.equals(TOOL_knife)) {
			boolean rReturn = F;
			ItemStack tBark = OM.dust(MT.Bark);
			
			if (!rReturn && BlocksGT.BeamA != null) {
				if (IL.HaC_Log_Maple.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.BeamA, 1, 3);
				}
			}
			if (!rReturn && BlocksGT.BeamB != null) {
				if (IL.HaC_Log_Cinnamon.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.BeamB, 1, 3);
					if (rReturn) tBark = IL.HaC_Cinnamon.get(2, IL.Food_Cinnamon.get(2, OM.dust(MT.Cinnamon, U*2)));
				}
			}
			if (!rReturn && MD.NeLi.mLoaded && (aMeta & 1) == 0) {
				if (IL.NeLi_Stem_Crimson.equal(aBlock) || IL.NeLi_Stem_FoxFire.equal(aBlock) || IL.NeLi_Hyphae_Crimson.equal(aBlock) ) {
					rReturn = aWorld.setBlock(aX, aY, aZ, aBlock, aMeta+1, 3);
					tBark = null;
				}
			}
			if (!rReturn && BlocksGT.Beam1 != null) {
				if (aBlock == Blocks.log || IL.EtFu_Bark_Oak.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam1, aMeta, 3);
				} else if (IL.TF_Log_Darkwood.equal(aBlock) && (aMeta & 3) != 3) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam1, aMeta, 3);
				} else if (IL.TF_Log_Time.equal(aBlock) && (aMeta & 1) == 0) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam1, (aMeta&12)|((aMeta & 2) == 0 ? 1 : 2), 3);
				} else if (IL.HaC_Log_Paperbark.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam1, 3, 3);
					if (rReturn) tBark = ST.make(Items.paper, 4, 0);
				}
			}
			if (!rReturn && BlocksGT.Beam3 != null) {
				if (IL.TC_Greatwood_Log.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam3, aMeta, 3);
				} else if (IL.AETHER_Skyroot_Log.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam3, (aMeta&12)|2, 3);
				} else if (IL.AETHER_Skyroot_Log_Small.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam3, 2, 3);
				} else if (IL.TF_Log_Darkwood.equal(aBlock) && (aMeta & 3) == 3) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam3, aMeta, 3);
				}
			}
			if (!rReturn && BlocksGT.Beam2 != null) {
				if (aBlock == Blocks.log2 || IL.EtFu_Bark_Acacia.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam2, aMeta, 3);
				} else if (IL.IC2_Log_Rubber.equal(aBlock) || IL.MFR_Log_Rubber.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam2, 2, 3);
				} else if (IL.BTL_Weedwood_Log.equal(aBlock)) {
					rReturn = aWorld.setBlock(aX, aY, aZ, IL.BTL_Weedwood_Beam.block(), 0, 3);
				} else if (IL.BTL_Weedwood_Beam.equal(aBlock)) {
					rReturn = F;
				} else if (IL.TF_Log_Trans.equal(aBlock) && (aMeta & 1) == 1) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam2, (aMeta&12)|((aMeta & 2) == 0 ? 0 : 1), 3);
				} else if (OD.logWood.is(ST.make(aBlock, 1, aMeta)) && !OD.beamWood.is(ST.make(aBlock, 1, aMeta))) {
					rReturn = aWorld.setBlock(aX, aY, aZ, BlocksGT.Beam2, (aMeta&12)|3, 3);
				}
			}
			if (rReturn) {
				if (FAST_LEAF_DECAY) WD.leafdecay(aWorld, aX, aY, aZ, null, F, F);
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer instanceof EntityPlayer ? (EntityPlayer)aPlayer : null, tBark, aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]);
				return aTool.equals(TOOL_axe) ? 500 : 1000;
			}
			return 0;
		}
		if (aTool.equals(TOOL_sense) || aTool.equals(TOOL_scythe)) {
			if (IC_CROPTILE && aTileEntity instanceof ICropTile) {
				int tDamage = 0;
				for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) if ((aTileEntity = WD.te(aWorld, aX+i, aY+j, aZ+k, T)) instanceof ICropTile && ((ICropTile)aTileEntity).harvest(T)) tDamage += 10000;
				return tDamage;
			}
			if (aBlock.getClass().getName().endsWith("BlockPamCrop")) {
				int tDamage = 0;
				for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) if (aWorld.getBlockMetadata(aX+i, aY+j, aZ+k) == 7) {
					Block tBlock = aWorld.getBlock(aX+i, aY+j, aZ+k);
					if (tBlock.getClass() == aBlock.getClass()) {
						tBlock.onBlockActivated(aWorld, aX+i, aY+j, aZ+k, aEntityPlayer, aSide, aHitX, aHitY, aHitZ);
						tDamage += 10000;
					}
				}
				return tDamage;
			}
		}
		if (aTool.equals(TOOL_igniter) && (aStack == null || aStack.getItem() != Items.flint_and_steel)) {
			// Ignite any TNT Blocks.
			if (aBlock instanceof BlockTNT) {
				((BlockTNT)aBlock).func_150114_a(aWorld, aX, aY, aZ, 1, aEntityLiving);
				aWorld.setBlockToAir(aX, aY, aZ);
				return 10000;
			}
			// This thing has a special Functionality, which should override spawning Fire Blocks.
			if (!IL.TF_Lamp_of_Cinders.equal(aStack, T, T)) {
				if (aEntityPlayer == null || aEntityPlayer.canPlayerEdit(aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide], aSide, aStack)) {
					if (aWorld.isAirBlock(aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide])) {
						if (WD.oxygen(aWorld, aX, aY, aZ)) aWorld.setBlock(aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide], Blocks.fire);
						return 10000;
					}
				}
			}
		}
		if (aTool.equals(TOOL_chisel) && !aSneaking) {
			ItemStack tChiseledBlock = WD.stack(aWorld, aX, aY, aZ);
			if (tChiseledBlock != null) {
				Recipe tRecipe = RM.Chisel.findRecipe(null, null, T, Integer.MAX_VALUE, null, ZL_FS, tChiseledBlock);
				if (tRecipe != null && tRecipe.blockINblockOUT() && ST.equal(tRecipe.mInputs[0], tChiseledBlock) && WD.set(aWorld, aX, aY, aZ, tRecipe.mOutputs[0])) return 10000;
			}
		}
		if (aTool.equals(TOOL_rotator)) {
			if (aBlock instanceof BlockRotatedPillar || aBlock.getRenderType() == PILLAR_RENDER) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) & 15, 3)) return 5000;
			}
			if (aBlock instanceof BlockPistonBase || aBlock instanceof BlockDispenser) {
				if (aMeta < 6 && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1) % 6, 3)) return 2000;
			}
			if (aBlock instanceof BlockPumpkin || aBlock instanceof BlockFurnace || aBlock instanceof BlockChest || aBlock instanceof BlockEnderChest) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ((aMeta-1)%4)+2, 3)) return 2500;
			}
			if (aBlock instanceof BlockHopper) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1)%6==1?(aMeta+1)%6:2, 3)) return 2500;
			}
			if (aBlock.rotateBlock(aWorld, aX, aX, aX, ForgeDirection.getOrientation(aSide))) return 10000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			if (aBlock instanceof BlockRedstoneDiode) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta / 4) * 4  + (((aMeta%4) + 1) % 4), 3)) return 10000;
			}
		}
		if (aTool.equals(TOOL_crowbar)) {
			if (aBlock instanceof BlockRailBase && (!MD.RC.mLoaded || !(MD.MC.owns(aBlock) || MD.RC.owns(aBlock)))) {
				aWorld.isRemote = T;
				// Why the fuck are the two Coordinate Parameters in isFlexibleRail switched? And then it is used like x y z instead of using the broken namings.
				boolean tResult = aWorld.setBlock(aX, aY, aZ, aBlock, ((BlockRailBase)aBlock).isFlexibleRail(aWorld, aX, aY, aZ) ? (aMeta+1) % 10 : ((aMeta/8) * 8) + (((aMeta%8)+1) % 6), 0);
				aWorld.isRemote = F;
				return tResult?2000:0;
			}
		}
		if (aTool.equals(TOOL_softhammer)) {
			if (aBlock == Blocks.lit_redstone_lamp) {
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, Blocks.redstone_lamp, 0, 0);
				aWorld.isRemote = F;
				return tResult?10000:0;
			}
			if (aBlock == Blocks.redstone_lamp) {
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, Blocks.lit_redstone_lamp, 0, 0);
				aWorld.isRemote = F;
				return tResult?10000:0;
			}
			if (aBlock == Blocks.golden_rail) {
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, aBlock, (aMeta + 8) % 16, 0);
				aWorld.isRemote = F;
				return tResult?10000:0;
			}
			if (aBlock == Blocks.activator_rail) {
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, aBlock, (aMeta + 8) % 16, 0);
				aWorld.isRemote = F;
				return tResult?10000:0;
			}
			if (aBlock instanceof BlockRotatedPillar || aBlock.getRenderType() == PILLAR_RENDER) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) & 15, 3)) return 5000;
			}
			if (aBlock instanceof BlockPistonBase || aBlock instanceof BlockDispenser) {
				if (aMeta < 6 && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1) % 6, 3)) return 2000;
			}
			if (aBlock instanceof BlockPumpkin || aBlock instanceof BlockFurnace || aBlock instanceof BlockChest || aBlock instanceof BlockEnderChest) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ((aMeta-1)%4)+2, 3)) return 2500;
			}
			if (aBlock instanceof BlockHopper) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1)%6==1?(aMeta+1)%6:2, 3)) return 2500;
			}
		}
		if (aTool.equals(TOOL_wrench) || aTool.equals(TOOL_monkeywrench)) {
			if (GC_BLOCKADVANCED && aBlock instanceof BlockAdvanced) {
				return (aSneaking ? ((BlockAdvanced)aBlock).onSneakUseWrench(aWorld, aX, aY, aZ, aEntityPlayer, aSide, aHitX, aHitY, aHitZ) : ((BlockAdvanced)aBlock).onUseWrench(aWorld, aX, aY, aZ, aEntityPlayer, aSide, aHitX, aHitY, aHitZ)) ? 2500 : 0;
			}
			
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			if (IC_WRENCHABLE && aTileEntity instanceof IWrenchable) {
				if (((IWrenchable)aTileEntity).wrenchCanSetFacing(aEntityPlayer, aTargetSide)) {
					((IWrenchable)aTileEntity).setFacing(aTargetSide);
					return 10000;
				}
				if (((IWrenchable)aTileEntity).wrenchCanRemove(aEntityPlayer)) {
					int tDamage = Math.max(10000, (int)(30000 / ((IWrenchable)aTileEntity).getWrenchDropRate()));
					ArrayList<ItemStack> tDrops = aBlock.getDrops(aWorld, aX, aY, aZ, aMeta, 0);
					ItemStack tOutput = ((IWrenchable)aTileEntity).getWrenchDrop(aEntityPlayer);
					
					if (aWorld.setBlockToAir(aX, aY, aZ)) {
						if (RNGSUS.nextInt(tDamage) < aRemainingDurability) {
							for (ItemStack tStack : tDrops) {
								if (tOutput == null) {
									if (aPlayer instanceof EntityPlayer) UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, tStack, F); else ST.drop(aWorld, aX+0.5, aY+0.5, aZ+0.5, tStack);
								} else {
									if (aPlayer instanceof EntityPlayer) UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, tOutput, F); else ST.drop(aWorld, aX+0.5, aY+0.5, aZ+0.5, tOutput);
									tOutput = null;
								}
							}
						}
						return tDamage;
					}
				}
			}
			
			if (aBlock instanceof BlockRotatedPillar || aBlock.getRenderType() == PILLAR_RENDER) {
				if (aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) & 15, 3)) return 5000;
			}
			
			if (aBlock instanceof BlockWorkbench || aBlock instanceof BlockBookshelf) {
				if (aWorld.setBlockToAir(aX, aY, aZ)) {
					ST.drop(aWorld, aX+0.5, aY+0.5, aZ+0.5, ST.make(aBlock, 1, aMeta));
					return 10000;
				}
			}
			
			if (aMeta == aTargetSide) {
				if (aBlock instanceof BlockPumpkin || aBlock instanceof BlockPistonBase || aBlock instanceof BlockDispenser || aBlock instanceof BlockFurnace || aBlock instanceof BlockChest || aBlock instanceof BlockHopper || aBlock instanceof BlockEnderChest) {
					if (aWorld.setBlockToAir(aX, aY, aZ)) {
						ST.drop(aWorld, aX+0.5, aY+0.5, aZ+0.5, ST.make(aBlock, 1, 0));
						return 10000;
					}
				}
			} else {
				if (aBlock instanceof BlockPistonBase || aBlock instanceof BlockDispenser) {
					if (aMeta < 6 && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3)) return 10000;
				}
				if (aBlock instanceof BlockPumpkin || aBlock instanceof BlockFurnace || aBlock instanceof BlockChest || aBlock instanceof BlockEnderChest) {
					if (SIDES_HORIZONTAL[aTargetSide] && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3)) return 10000;
				}
				if (aBlock instanceof BlockHopper) {
					if (SIDES_BOTTOM_HORIZONTAL[aTargetSide] && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3)) return 10000;
				}
			}
			if (aBlock instanceof BlockRailBase || aBlock instanceof BlockRedstoneDiode || aBlock instanceof BlockPistonExtension || aBlock instanceof BlockPistonBase) {
				// wrench doesn't work on those.
			} else {
				if (Arrays.asList(aBlock.getValidRotations(aWorld, aX, aY, aZ)).contains(ForgeDirection.getOrientation(aTargetSide))) {
					if (aBlock.rotateBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aTargetSide))) return 10000;
				}
			}
		}
		if (aTool.equals(TOOL_prospector)) {
			if (prospectOre(aBlock, aMeta, aChatReturn, aWorld, aX, aY, aZ)) return 100;
			if (aBlock != Blocks.obsidian && (aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone) || aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack) || aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone) || WD.stone(aBlock, aMeta))) {
				if (prospectStone(aBlock, aMeta, aQuality, aChatReturn, aWorld, aSide, aX, aY, aZ)) return 10000;
			}
			return 0;
		}
		
		} catch(Throwable e) {
			FMLLog.severe("Exception occured when ToolCompat was used at the Coordinates: [%d;%d;%d] at '%s' with TileEntity '%s' using the Tool '%s' %s", aX, aY, aZ, aBlock.getUnlocalizedName(), aTileEntity.getClass(), aTool, e.toString());
			e.printStackTrace(ERR);
		}
		return 0;
	}
	
	public static boolean prospectOre(Block aBlock, byte aMeta, List<String> aChatReturn, World aWorld, int aX, int aY, int aZ) {
		OreDictItemData tAssotiation = OM.anyassociation(ST.make(aBlock, 1, aWorld.getBlockMetadata(aX, aY, aZ)));
		if (tAssotiation != null && tAssotiation.mPrefix.contains(TD.Prefix.ORE)) {
			if (aChatReturn != null) aChatReturn.add(LanguageHandler.getLocalName(tAssotiation.mPrefix, tAssotiation.mMaterial.mMaterial)+"!");
			return T;
		}
		return F;
	}
	
	public static boolean prospectStone(Block aBlock, byte aMeta, long aQuality, List<String> aChatReturn, World aWorld, byte aSide, int aX, int aY, int aZ) {
		Block tBlock;
		int tX = aX, tY = aY, tZ = aZ, tQuality = (int)UT.Code.bind(1, 20, aQuality + 4);
		
		for (int i = 0, j = tQuality; i < j; i++) {
			tX -= OFFSETS_X[aSide];
			tY -= OFFSETS_Y[aSide];
			tZ -= OFFSETS_Z[aSide];
			
			// The Strings in this do not want to be localized, and not even Backup Lang wants to work.
			tBlock = aWorld.getBlock(tX, tY, tZ);
			if (tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) {
				if (aChatReturn != null) aChatReturn.add("There is Lava behind this Rock");
				break;
			}
			if (tBlock instanceof BlockLiquid || tBlock instanceof IFluidBlock) {
				if (aChatReturn != null) aChatReturn.add("There is a Fluid behind this Rock");
				break;
			}
			if (tBlock instanceof BlockSilverfish || !WD.hasCollide(aWorld, tX, tY, tZ, tBlock)) {
				if (aChatReturn != null) aChatReturn.add("There is an Air Pocket behind this Rock");
				break;
			}
			if (i < 4) if (tBlock != aBlock || aMeta != aWorld.getBlockMetadata(tX, tY, tZ)) {
				if (aChatReturn != null) aChatReturn.add("Material is changing behind this Rock");
				break;
			}
		}
		
		Random tRandom = new Random(aX^aY^aZ^aSide);
		for (int i = 0, j = 1+2*tQuality, k = tQuality * tQuality; i < k; i++) {
			tX = aX-tQuality+tRandom.nextInt(j);
			tY = aY-tQuality+tRandom.nextInt(j);
			tZ = aZ-tQuality+tRandom.nextInt(j);
			tBlock = aWorld.getBlock(tX, tY, tZ);
			
			if (tBlock != NB && tBlock != Blocks.obsidian && tBlock != BlocksGT.RockOres) {
				OreDictItemData tAssotiation = OM.anyassociation((tBlock instanceof IBlockRetrievable ? ((IBlockRetrievable)tBlock).getItemStackFromBlock(aWorld, tX, tY, tZ, SIDE_INVALID) : ST.make(tBlock, 1, aWorld.getBlockMetadata(tX, tY, tZ))));
				if (tAssotiation != null && tAssotiation.mPrefix.containsAny(TD.Prefix.STANDARD_ORE, TD.Prefix.DENSE_ORE)) {
					if (aChatReturn != null) aChatReturn.add("Found traces of " + tAssotiation.mMaterial.mMaterial.getLocal());
					return T;
				}
			}
		}
		if (aChatReturn != null && aChatReturn.isEmpty()) aChatReturn.add("No traces of Ore found");
		return T;
	}
}
