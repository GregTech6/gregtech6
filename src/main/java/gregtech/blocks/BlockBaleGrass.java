/**
 * Copyright (c) 2024 GregTech-6 Team
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

package gregtech.blocks;

import gregapi.block.misc.BlockBaseBale;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.List;
import java.util.Random;

import static gregapi.data.CS.*;

public class BlockBaleGrass extends BlockBaseBale {
	public BlockBaleGrass(String aUnlocalised) {
		super(null, aUnlocalised, Material.grass, soundTypeGrass, 4, Textures.BlockIcons.BALES_GRASS);
		
		LH.add(getUnlocalizedName()+ ".0", "Grass Bale");
		LH.add(getUnlocalizedName()+ ".4", "Grass Bale");
		LH.add(getUnlocalizedName()+ ".8", "Grass Bale");
		LH.add(getUnlocalizedName()+".12", "Grass Bale");
		OM.reg(ST.make(this, 1, 0), "baleGrass");
		OM.reg(ST.make(this, 1, 4), "baleGrass");
		OM.reg(ST.make(this, 1, 8), "baleGrass");
		OM.reg(ST.make(this, 1,12), "baleGrass");
		IL.Bale.set(ST.make(this, 1, 0));
		
		LH.add(getUnlocalizedName()+ ".1", "Dry Grass Bale");
		LH.add(getUnlocalizedName()+ ".5", "Dry Grass Bale");
		LH.add(getUnlocalizedName()+ ".9", "Dry Grass Bale");
		LH.add(getUnlocalizedName()+".13", "Dry Grass Bale");
		OM.reg(ST.make(this, 1, 1), "baleGrassDry");
		OM.reg(ST.make(this, 1, 5), "baleGrassDry");
		OM.reg(ST.make(this, 1, 9), "baleGrassDry");
		OM.reg(ST.make(this, 1,13), "baleGrassDry");
		IL.Bale_Dry.set(ST.make(this, 1, 1));
		
		LH.add(getUnlocalizedName()+ ".2", "Moldy Grass Bale");
		LH.add(getUnlocalizedName()+ ".6", "Moldy Grass Bale");
		LH.add(getUnlocalizedName()+".10", "Moldy Grass Bale");
		LH.add(getUnlocalizedName()+".14", "Moldy Grass Bale");
		OM.reg(ST.make(this, 1, 2), "baleGrassMoldy");
		OM.reg(ST.make(this, 1, 6), "baleGrassMoldy");
		OM.reg(ST.make(this, 1,10), "baleGrassMoldy");
		OM.reg(ST.make(this, 1,14), "baleGrassMoldy");
		IL.Bale_Moldy.set(ST.make(this, 1, 2));
		
		LH.add(getUnlocalizedName()+ ".3", "Rotten Grass Bale");
		LH.add(getUnlocalizedName()+ ".7", "Rotten Grass Bale");
		LH.add(getUnlocalizedName()+".11", "Rotten Grass Bale");
		LH.add(getUnlocalizedName()+".15", "Rotten Grass Bale");
		OM.reg(ST.make(this, 1, 3), "baleGrassRotten");
		OM.reg(ST.make(this, 1, 7), "baleGrassRotten");
		OM.reg(ST.make(this, 1,11), "baleGrassRotten");
		OM.reg(ST.make(this, 1,15), "baleGrassRotten");
		IL.Bale_Rotten.set(ST.make(this, 1, 3));
	}
	
	@Override
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {
		if ((WD.meta(aWorld, aX, aY, aZ) & 1) == 1) return;
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2400 + RNGSUS.nextInt(2400));
	}
	
	@Override
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		if ((aMeta & 1) == 1) return;
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 1100 + RNGSUS.nextInt(200));
		if (aRandom.nextInt(3) > 0) return;
		if (aWorld.provider.isHellWorld && (aMeta & 2) == 0) {
			aWorld.setBlock(aX, aY, aZ, this, (aMeta & PILLAR_BITS) | 1, 3);
			return;
		}
		if (aRandom.nextInt(3) > 0 && WD.envTemp(aWorld, aX, aY, aZ) < C + 10) return;
		if (aRandom.nextInt(3) > 0 && !(aWorld.isDaytime() && !aWorld.isRaining() && aWorld.canBlockSeeTheSky(aX, aY+2, aZ))) return;
		BiomeGenBase tBiome = aWorld.getBiomeGenForCoords(aX, aZ);
		boolean tWet = (tBiome.rainfall > 0.8F || BIOMES_INFINITE_WATER.contains(tBiome.biomeName));
		if (!tWet) for (byte tSide : ALL_SIDES_VALID) if (WD.anywater(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide])) {tWet = T; break;}
		if ((aMeta & PILLAR_DATA) == 0) {
			if (tWet || (aWorld.isRaining() && tBiome.rainfall > 0 && aWorld.getPrecipitationHeight(aX, aZ) <= aY+2)) {
				aWorld.setBlock(aX, aY, aZ, this, (aMeta & PILLAR_BITS) | 2, 3);
				return;
			}
			aWorld.setBlock(aX, aY, aZ, this, (aMeta & PILLAR_BITS) | 1, 3);
			return;
		}
		if (tWet || aRandom.nextInt(42) == 0 || (aWorld.isRaining() && tBiome.rainfall > 0 && aWorld.getPrecipitationHeight(aX, aZ) <= aY+2)) {
			aWorld.setBlock(aX, aY, aZ, this, (aMeta & PILLAR_BITS) | 3, 3);
			return;
		}
		return;
	}
	
	static {
		LH.add("gt.tooltip.bale", "Bale dries or rots when placed");
		LH.add("gt.tooltip.bale.dry", "It would dry if placed here at the moment");
		LH.add("gt.tooltip.bale.rot", "It would rot if placed here at the moment");
	}
	
	@Override
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
		super.addInformation(aStack, aMeta, aPlayer, aList, aF3_H);
		if ((aMeta & 3) == 0) {
			aList.add(LH.Chat.CYAN + LH.get("gt.tooltip.bale"));
			if (aPlayer != null) {
				if (aPlayer.worldObj.provider.isHellWorld) {
					aList.add(LH.Chat.YELLOW + LH.get("gt.tooltip.bale.dry"));
				} else {
					int aX = UT.Code.roundDown(aPlayer.posX), aY = UT.Code.roundDown(aPlayer.posY), aZ = UT.Code.roundDown(aPlayer.posZ);
					BiomeGenBase tBiome = aPlayer.worldObj.getBiomeGenForCoords(aX, aZ);
					if (tBiome.rainfall > 0.8F || (aPlayer.worldObj.isRaining() && tBiome.rainfall > 0 && aPlayer.worldObj.getPrecipitationHeight(aX, aZ) <= aY+2)) {
						aList.add(LH.Chat.ORANGE + LH.get("gt.tooltip.bale.rot"));
					} else {
						aList.add(LH.Chat.YELLOW + LH.get("gt.tooltip.bale.dry"));
					}
				}
			}
		}
	}
}
