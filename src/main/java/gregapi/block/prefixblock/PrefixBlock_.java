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

package gregapi.block.prefixblock;

import gregapi.block.behaviors.Drops;
import gregapi.code.ModData;
import gregapi.compat.galacticraft.IBlockSealable;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.ITexture;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 * 
 * Yay for Galacticraft Bullshit forcing me to make this Class!
 */
public class PrefixBlock_ extends PrefixBlock implements IBlockSealable {
	public PrefixBlock_(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aGravity, aEnderDragonProof, aMaterialList);
	}
	
	public PrefixBlock_(ModData aMod, String aNameInternal, OreDictPrefix aPrefix, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, OreDictMaterial... aMaterialList) {
		super(aMod, aNameInternal, aPrefix, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aGravity, aEnderDragonProof, aMaterialList);
	}
	
	public PrefixBlock_(ModData aMod, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof, OreDictMaterial... aMaterialList) {
		super(aMod, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ, aGravity, aBeaconBase, aEnderDragonProof, aWitherProof, aOpaque, aNormalCube, aPlacementChecksTemperature, aPlacementChecksAntimatter, aCanBurn, aCanExplode, aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof, aMaterialList);
	}
	
	public PrefixBlock_(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ, aGravity, aBeaconBase, aEnderDragonProof, aWitherProof, aOpaque, aNormalCube, aPlacementChecksTemperature, aPlacementChecksAntimatter, aCanBurn, aCanExplode, aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof, aMaterialList);
	}
	
	@Override public boolean isSealed(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return mPrefix == OP.blockSolid;}
}
