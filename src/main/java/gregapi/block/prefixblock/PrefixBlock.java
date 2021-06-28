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

package gregapi.block.prefixblock;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gregapi.GT_API_Proxy;
import gregapi.block.IBlockSyncData;
import gregapi.block.IBlockToolable;
import gregapi.block.IPrefixBlock;
import gregapi.block.ToolCompat;
import gregapi.block.behaviors.Drops;
import gregapi.code.ModData;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.lang.LanguageHandler;
import gregapi.network.INetworkHandler;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.random.ExplosionGT;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IRenderedBlock;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.ITexture;
import gregapi.render.RendererBlockTextured;
import gregapi.tileentity.ITileEntity;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * @author Gregorius Techneticies
 */
public class PrefixBlock extends Block implements Runnable, ITileEntityProvider, IBlockSyncData, IRenderedBlock, IBlockToolable, IPrefixBlock {
	public Drops mDrops;
	public boolean mRegisterToOreDict = T, mHidden = F;
	
	public final float mMinX, mMinY, mMinZ, mMaxX, mMaxY, mMaxZ;
	public final int mHarvestLevelOffset, mHarvestLevelMinimum, mHarvestLevelMaximum;
	public final ITexture mTexture;
	public final String mNameInternal, mTool, mModIDOwner;
	public final OreDictPrefix mPrefix;
	public final OreDictMaterialStack mHullMaterial;
	public final OreDictMaterial[] mMaterialList;
	public final float mBaseHardness, mBaseResistance;
	public final boolean mGravity, mBeaconBase, mEnderDragonProof, mWitherProof, mSpawnProof, mOpaque, mNormalCube, mPlacementChecksTemperature, mPlacementChecksAntimatter, mCanBurn, mCanExplode, mRenderOverlayInWorld, mCanGlow, mCanLight;
	
	@Deprecated
	public PrefixBlock(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof) {
		this(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, 0, 0, 0, 1, 1, 1, aGravity, aBeaconBase, aEnderDragonProof, aWitherProof, aOpaque, aNormalCube, aPlacementChecksTemperature, aPlacementChecksAntimatter, aCanBurn, aCanExplode, aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof);
	}
	
	@Deprecated
	public PrefixBlock(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof) {
		this(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ, aGravity, aBeaconBase, aEnderDragonProof, aWitherProof, aOpaque, aNormalCube, aPlacementChecksTemperature, aPlacementChecksAntimatter, aCanBurn, aCanExplode, aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof, OreDictMaterial.MATERIAL_ARRAY);
	}
	
	/**
	 * Specific for Ore Block creation
	 */
	public PrefixBlock(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, OreDictMaterial... aMaterialList) {
		this(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, null, null, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, 999, 0, 0, 0, 1, 1, 1, aGravity, F, aEnderDragonProof, F, T, T, F, F, T, T, T, T, T, F, aMaterialList);
	}
	
	/**
	 * Specific for Ore Block creation
	 * Only saves on one Parameter by using 1 instead of 2 Mod IDs.
	 */
	public PrefixBlock(ModData aMod, String aNameInternal, OreDictPrefix aPrefix, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, OreDictMaterial... aMaterialList) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, null, null, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, 999, 0, 0, 0, 1, 1, 1, aGravity, F, aEnderDragonProof, F, T, T, F, F, T, T, T, T, T, F, aMaterialList);
	}
	
	/**
	 * Only saves on one Parameter by using 1 instead of 2 Mod IDs.
	 */
	public PrefixBlock(ModData aMod, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof, OreDictMaterial... aMaterialList) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, aHullMaterial, aItemClass, aDrops, aTexture, aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, aHarvestLevelOffset, aHarvestLevelMinimum, aHarvestLevelMaximum, aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ, aGravity, aBeaconBase, aEnderDragonProof, aWitherProof, aOpaque, aNormalCube, aPlacementChecksTemperature, aPlacementChecksAntimatter, aCanBurn, aCanExplode, aRenderOverlayInWorld, aCanGlow, aCanLight, aSpawnProof, aMaterialList);
	}
	
	/**
	 * Just create one instance of this Block and everything else is getting registered automatically.
	 * 
	 * @param aModIDOwner the ID of the owning Mod. DO NOT INSERT ANY GREGTECH MODID!!!
	 * @param aModIDTextures the ID of the Texture providing Mod (for the "ModID:TextureName" thing)
	 * @param aNameInternal the internal Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 * @param aPrefix the OreDictPrefix corresponding to this Item.
	 * @param aHullMaterial the Material the Hull consists of. Can be null.
	 * @param aItemClass the Class of the ItemBlock to be used. If you pass null it will default to the regular MetaBlockItem Class.
	 * @param aTexture the Texture underlay for this Block. Used for Ores and Crates. Can be null to use normal Rendering.
	 * @param aVanillaMaterial the Material used to determine the Block.
	 * @param aSoundType the Sound Type of the Block.
	 * @param aTool the Tool used to harvest this Block.
	 * @param aBaseHardness if smaller than zero, then this Block is indestructible.
	 * @param aBaseResistance
	 * @param aHarvestLevelOffset
	 * @param aHarvestLevelMinimum
	 * @param aHarvestLevelMaximum
	 * @param aMinX
	 * @param aMinY
	 * @param aMinZ
	 * @param aMaxX
	 * @param aMaxY
	 * @param aMaxZ
	 * @param aGravity if this Block falls like Gravel.
	 * @param aSpawnProof if this Block cannot spawn Mobs.
	 * @param aBeaconBase if this Block can be used as Beacon Base.
	 * @param aEnderDragonProof if this Block cannot be destroyed by an Ender Dragon (used for the End Ores).
	 * @param aWitherProof if this Block cannot be destroyed by a Wither.
	 * @param aOpaque if this Block is Opaque.
	 * @param aNormalCube if this Block is a normal Cube (for Redstone Stuff).
	 * @param aPlacementChecksTemperature if this Block checks for Temperature to be proper before placing.
	 * @param aPlacementChecksAntimatter if this Block checks for being Antimatter before placing.
	 * @param aCanBurn if this Block can burn if the Material it is made of can burn.
	 * @param aCanExplode if this Block can explode if the Material it is made of can explode.
	 * @param aRenderOverlayInWorld if the Icon Overlay is to be rendered InWorld. Used for Crates and Ores.
	 */
	public PrefixBlock(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, OreDictMaterialStack aHullMaterial, Class<? extends PrefixBlockItem> aItemClass, Drops aDrops, ITexture aTexture, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelOffset, int aHarvestLevelMinimum, int aHarvestLevelMaximum, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ, boolean aGravity, boolean aBeaconBase, boolean aEnderDragonProof, boolean aWitherProof, boolean aOpaque, boolean aNormalCube, boolean aPlacementChecksTemperature, boolean aPlacementChecksAntimatter, boolean aCanBurn, boolean aCanExplode, boolean aRenderOverlayInWorld, boolean aCanGlow, boolean aCanLight, boolean aSpawnProof, OreDictMaterial... aMaterialList) {
		super(aVanillaMaterial);
		mPrefix = aPrefix;
		mNameInternal = aNameInternal;
		mMaterialList = (aMaterialList.length > 0 ? aMaterialList : OreDictMaterial.MATERIAL_ARRAY);
		if (mMaterialList[0] != MT.Empty) throw new IllegalArgumentException("The first element of the custom Material List has to be MT.Empty for technical reasons!");
		
		mMinX = (float)aMinX; mMinY = (float)aMinY; mMinZ = (float)aMinZ; mMaxX = (float)aMaxX; mMaxY = (float)aMaxY; mMaxZ = (float)aMaxZ;
		
		setStepSound(aSoundType);
		mOpaque = aOpaque;
		mGravity = aGravity;
		mCanBurn = aCanBurn;
		mCanGlow = aCanGlow;
		mCanLight = aCanLight;
		mCanExplode = aCanExplode;
		mNormalCube = aNormalCube;
		mBeaconBase = aBeaconBase;
		mSpawnProof = aSpawnProof;
		mWitherProof = aWitherProof;
		mEnderDragonProof = aEnderDragonProof;
		mRenderOverlayInWorld = aRenderOverlayInWorld;
		mPlacementChecksAntimatter = aPlacementChecksAntimatter;
		mPlacementChecksTemperature = aPlacementChecksTemperature;
		
		mTool = aTool.toLowerCase();
		mTexture = aTexture;
		mModIDOwner = aModIDOwner;
		mHullMaterial = aHullMaterial;
		mBaseHardness = aBaseHardness;
		mBaseResistance = aBaseResistance;
		mHarvestLevelOffset = aHarvestLevelOffset;
		mHarvestLevelMinimum = Math.max(0, aHarvestLevelMinimum);
		mHarvestLevelMaximum = Math.max(aHarvestLevelMinimum, aHarvestLevelMaximum);
		mPrefix.addTextureSet(aModIDTextures, F);
		LH.add("oredict." + mPrefix.dat(MT.Empty).toString() + ".name", getLocalName(mPrefix, MT.Empty));
		LH.add(mNameInternal+"."+W+".name", "Any Sub-Block of this one"); // Local Name for the WildcardItem Variant.
		
		opaque = mOpaque;
		lightOpacity = mOpaque ? 255 : 0;
		
		ST.register(this, mNameInternal, aItemClass==null?PrefixBlockItem.class:aItemClass);
		
		mPrefix.mRegisteredItems.add(this); // this optimizes some processes by decreasing the size of the Set.
		
		if (COMPAT_IC2 != null && mPrefix.contains(TD.Prefix.ORE) && mBaseHardness >= 0) for (byte i = 0; i < 16; i++) COMPAT_IC2.valuable(this, i, 3);
		
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
		
		if (mOpaque) VISUALLY_OPAQUE_BLOCKS.add(this);
		mDrops = aDrops==null?new Drops(this, this, this, this, F, F, 0, 0):aDrops;
		
		if (CODE_CLIENT) MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(this), RendererBlockTextured.INSTANCE);
		
		// Execute before all the other things. This is to ensure that PrefixBlocks are created before MultiItems.
		(GAPI.mBeforeInit==null?GAPI.mBeforePostInit:GAPI.mBeforeInit).add(0, this);
	}
	
	/** This ensures, that all Materials are registered at the time this Item registers to the OreDictionary. */
	@Override
	public void run() {
		if (mRegisterToOreDict) {
			boolean tUnificationAllowed = (mPrefix.contains(TD.Prefix.UNIFICATABLE) && !mPrefix.contains(TD.Prefix.UNIFICATABLE_RECIPES));
			for (short i = 0; i < mMaterialList.length; i++) if (mMaterialList[i] != null && mPrefix.isGeneratingItem(mMaterialList[i])) {
				ItemStack tStack = ST.make(this, 1, i);
				ST.update_(tStack);
				LH.add("oredict." + mPrefix.dat(mMaterialList[i]).toString() + ".name", getLocalName(mPrefix, mMaterialList[i]));
				if (tUnificationAllowed) OreDictManager.INSTANCE.addTarget_(mPrefix, mMaterialList[i], tStack); else OreDictManager.INSTANCE.registerOre_(mPrefix, mMaterialList[i], tStack);
			}
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister aIconRegister) {/*
		if (mPrefix.mIconIndexBlock >= 0) {
			MT.NULL.mTextureSetsBlock.get(mPrefix.mIconIndexBlock).registerIcons(aIconRegister);
			HashSet<IIconContainer> tSet = new HashSet<IIconContainer>();
			for (int i = 0; i < mMaterialList.length; i++) if (mMaterialList[i] != null && mMaterialList[i].mTextureSetsBlock != null) {
				IIconContainer tIcon = mMaterialList[i].mTextureSetsBlock.get(mPrefix.mIconIndexBlock);
				if (tSet.add(tIcon)) tIcon.registerIcons(aIconRegister);
			}
		}*/
	}
	
	@Override
	public IIcon getIcon(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		return getIcon(aSide, getMetaDataValue(aWorld, aX, aY, aZ));
	}
	
	@Override
	public IIcon getIcon(int aSide, int aMetaData) {
		if (mPrefix.mIconIndexBlock >= 0) {
			OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
			if (aMaterial != null && aMaterial.mTextureSetsBlock != null)
			return aMaterial    .mTextureSetsBlock.get(mPrefix.mIconIndexBlock).getIcon(0);
			return MT.NULL      .mTextureSetsBlock.get(mPrefix.mIconIndexBlock).getIcon(0);
		}
		return null;
	}
	
	@Override
	public int getRenderColor(int aMetaData) {
		OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
		return aMaterial == null ? super.getRenderColor(aMetaData) : UT.Code.getRGBInt(aMaterial.fRGBa[mPrefix.mState]);
	}
	
	public ITexture getTexture(short aMetaData, boolean aRendersInWorld) {
		if (!mRenderOverlayInWorld && aRendersInWorld) return mTexture;
		if (mPrefix.mIconIndexBlock >= 0) {
			OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
			if (mTexture == null) {
				if (aMaterial != null && aMaterial.mTextureSetsBlock != null)
				return BlockTextureDefault.get(aMaterial, mPrefix, mCanGlow && aMaterial.contains(TD.Properties.GLOWING));
				return BlockTextureDefault.get(MT.NULL, mPrefix);
			}
			if (aMaterial != null && aMaterial.mTextureSetsBlock != null)
			return BlockTextureMulti.get(mTexture, BlockTextureDefault.get(aMaterial, mPrefix, mCanGlow && aMaterial.contains(TD.Properties.GLOWING)));
			return BlockTextureMulti.get(mTexture, BlockTextureDefault.get(MT.NULL, mPrefix));
		}
		return null;
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {
		return getTexture(ST.meta_(aStack), F);
	}
	
	@Override
	public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {
		return aShouldSideBeRendered[aSide] ? getTexture(getMetaDataValue(aWorld, aX, aY, aZ), T) : null;
	}
	
	@Override
	public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {
		return F;
	}
	
	@Override
	public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {
		return F;
	}
	
	@Override
	public int getRenderPasses(ItemStack aStack) {
		return 1;
	}
	
	@Override
	public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {
		return 1;
	}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {
		mRenderParameterTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		return mRenderingObjectBlock != null ? mRenderingObjectBlock : mRenderParameterTileEntity instanceof IRenderedBlockObject ? (IRenderedBlockObject)mRenderParameterTileEntity : null;
	}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {
		return mRenderingObjectStack;
	}
	
	public TileEntity mRenderParameterTileEntity = null;
	public IRenderedBlockObject mRenderingObjectBlock = null, mRenderingObjectStack = null;
	
	public PrefixBlock setRenderingObject(IRenderedBlockObject aBlock, IRenderedBlockObject aStack) {
		mRenderingObjectBlock = aBlock;
		mRenderingObjectStack = aStack;
		return this;
	}
	
	private static boolean LOCK = F;
	
	@Override
	public void onNeighborChange(IBlockAccess aWorld, int aX, int aY, int aZ, int aTileX, int aTileY, int aTileZ) {
		if (!LOCK) {
			LOCK = T;
			TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
			if (aTileEntity instanceof ITileEntity) ((ITileEntity)aTileEntity).onAdjacentBlockChange(aTileX, aTileY, aTileZ);
			LOCK = F;
		}
	}
	
	@Override
	public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		if (!LOCK) {
			LOCK = T;
			TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
			if (aTileEntity instanceof ITileEntity) ((ITileEntity)aTileEntity).onAdjacentBlockChange(aX, aY, aZ);
			LOCK = F;
		}
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2);
	}
	
	@Override
	public void onBlockExploded(World aWorld, int aX, int aY, int aZ, Explosion aExplosion) {
		if (aWorld.isRemote) return;
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (aTileEntity != null) LAST_BROKEN_TILEENTITY.set(aTileEntity);
		OreDictMaterial aMaterial = getMetaMaterial(aTileEntity);
		aWorld.setBlockToAir(aX, aY, aZ);
		if (aMaterial != null && ((mCanExplode && aMaterial.contains(TD.Properties.EXPLOSIVE)) || (mCanBurn && aMaterial.contains(TD.Properties.FLAMMABLE) && mPrefix.contains(TD.Prefix.DUST_BASED)))) try {ExplosionGT.explode(aWorld, null, aX+0.5, aY+0.5, aZ+0.5, ((mPrefix.mAmount>0?mPrefix.mAmount:U)*0.7F)/U, T, T);} catch(StackOverflowError e) {ERR.println("WARNING: StackOverflow during Explosion has been prevented at: " + aX +" ; "+ aY +" ; "+ aZ);}
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World aWorld, int aX, int aY, int aZ, double explosionX, double explosionY, double explosionZ)       {
		OreDictMaterial aMaterial = getMetaMaterial(aWorld, aX, aY, aZ);
		if (aMaterial != null && ((mCanExplode && aMaterial.contains(TD.Properties.EXPLOSIVE)) || (mCanBurn && aMaterial.contains(TD.Properties.FLAMMABLE) && mPrefix.contains(TD.Prefix.DUST_BASED)))) return 0;
		return mBaseResistance * (1+getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ)));
	}
	
	@Override
	public boolean onBlockEventReceived(World aWorld, int aX, int aY, int aZ, int aID, int aData) {
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		return aTileEntity == null || aTileEntity.receiveClientEvent(aID, aData);
	}
	
	@Override
	public int getDamageValue(World aWorld, int aX, int aY, int aZ) {
		return getMetaDataValue(aWorld, aX, aY, aZ);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition aTarget, World aWorld, int aX, int aY, int aZ, EntityPlayer aPlayer) {
		return getItemStackFromBlock(aWorld, aX, aY, aZ, SIDE_UNKNOWN);
	}
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int par6) {
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (tTileEntity != null) LAST_BROKEN_TILEENTITY.set(tTileEntity);
		aWorld.removeTileEntity(aX, aY, aZ);
	}
	
	@Override
	public boolean placeBlock(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		OreDictMaterial aMaterial = getMetaMaterial(aMetaData);
		if (aMaterial != null && (aForcePlacement || ((!mPlacementChecksAntimatter || !aMaterial.contains(TD.Atomic.ANTIMATTER)) && (!mPlacementChecksTemperature || aMaterial.mMeltingPoint > WD.temperature(aWorld, aX, aY, aZ)))) && aWorld.setBlock(aX, aY, aZ, this, UT.Code.bind4(aMaterial.mToolQuality), aCauseBlockUpdates?3:0)) {
			// This darn TileEntity update is ruining World generation Code (infinite Loops when placing TileEntities on Chunk Borders). I'm glad I finally found a way to disable it.
			TileEntity tTileEntity = createTileEntity(aWorld, aX, aY, aZ, aSide, aMetaData, aNBT);
			WD.te(aWorld, aX, aY, aZ, tTileEntity, aCauseBlockUpdates);
			aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2);
			if (!aWorld.isRemote) GT_API_Proxy.SCHEDULED_TILEENTITY_UPDATES.add((PrefixBlockTileEntity)tTileEntity);
			return T;
		}
		return F;
	}
	
	@Override
	public ItemStack getItemStackFromBlock(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide) {
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		return ST.make(this, 1, getMetaDataValue(aTileEntity), aTileEntity instanceof PrefixBlockTileEntity ? ((PrefixBlockTileEntity)aTileEntity).mItemNBT : null);
	}
	
	@Override
	public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		OreDictMaterialStack aMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, UT.Code.side(aSide));
		return aMaterial == null || !mCanBurn || aMaterial.mMaterial.contains(TD.Properties.UNBURNABLE) ? 0 : (aMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)?100:0) + (aMaterial.mMaterial.contains(TD.Properties.BURNING)?200:0);
	}
	
	@Override
	public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		OreDictMaterialStack aMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, UT.Code.side(aSide));
		return aMaterial == null || !mCanBurn || aMaterial.mMaterial.contains(TD.Properties.UNBURNABLE) ? 0 : (aMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)?100:0) + (aMaterial.mMaterial.contains(TD.Properties.BURNING)?200:0);
	}
	
	@Override
	public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		OreDictMaterialStack aMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, UT.Code.side(aSide));
		return aMaterial != null && mCanBurn && aMaterial.mMaterial.contains(TD.Properties.FLAMMABLE) && aMaterial.mMaterial.contains(TD.Properties.UNBURNABLE);
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess aWorld, int aX, int aY, int aZ, Entity aEntity) {
		if (aEntity instanceof EntityDragon) {
			if (mEnderDragonProof) return F;
			OreDictMaterialStack aMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, SIDE_ANY);
			return aMaterial == null || !aMaterial.mMaterial.contains(TD.Properties.ENDER_DRAGON_PROOF);
		}
		if (aEntity instanceof EntityWither) {
			if (mWitherProof) return F;
			OreDictMaterialStack aMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, SIDE_ANY);
			return aMaterial == null || !aMaterial.mMaterial.contains(TD.Properties.WITHER_PROOF);
		}
		return T;
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		OreDictMaterial aMaterial = getMetaMaterial(aWorld, aX, aY, aZ);
		if (!aWorld.isRemote && aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add("This is " + getLocalName(mPrefix, aMaterial));
			return 1;
		}
		if (!aWorld.isRemote && aTool.equals(TOOL_prospector) && mPrefix.contains(TD.Prefix.ORE)) {
			if (aChatReturn != null) aChatReturn.add(getLocalName(OP.ore, aMaterial)+"!");
			return 100;
		}
		// Proceed with the regular onToolClick of the ToolCompat Class, because it has important Code in it.
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public OreDictMaterialStack getMaterialAtSide(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide) {
		if (mHullMaterial != null) return mHullMaterial;
		OreDictMaterial aMaterial = getMetaMaterial(aWorld, aX, aY, aZ);
		return aMaterial == null ? null : OM.stack(mPrefix, aMaterial);
	}
	
	@Override
	public void setExtendedMetaData(IBlockAccess aWorld, int aX, int aY, int aZ, short aMetaData) {
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (aTileEntity == null && aWorld instanceof World) aTileEntity = WD.te((World)aWorld, aX, aY, aZ, createTileEntity((World)aWorld, aX, aY, aZ, SIDE_ANY, aMetaData, null), F);
		if (aTileEntity instanceof PrefixBlockTileEntity) ((PrefixBlockTileEntity)aTileEntity).mMetaData = aMetaData;
		if (aWorld instanceof World && ((World)aWorld).isRemote) WD.update(aWorld, aX, aY, aZ);
	}
	
	@Override
	public short getExtendedMetaData(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return getMetaDataValue(aWorld, aX, aY, aZ);
	}
	
	@Override
	public boolean removeMaterialFromSide(World aWorld, int aX, int aY, int aZ, byte aSide, OreDictMaterialStack aMaterial) {
		OreDictMaterialStack tMaterial = getMaterialAtSide(aWorld, aX, aY, aZ, aSide);
		if (aMaterial.mMaterial == tMaterial.mMaterial && aMaterial.mAmount > 0 && aMaterial.mAmount <= tMaterial.mAmount) {
			ItemStack tStack = OM.dust(aMaterial.mMaterial, tMaterial.mAmount - aMaterial.mAmount);
			if (tStack != null) ST.drop(aWorld, aX+0.5, aY+0.5, aZ+0.5, tStack);
			aWorld.setBlockToAir(aX, aY, aZ);
			return T;
		}
		return F;
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (aWorld.isRemote || checkGravity(aWorld, aX, aY, aZ)) return;
		TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		OreDictMaterial aMaterial = getMetaMaterial(aTileEntity);
		if (aMaterial != null) {
			if (mCanBurn && (mPrefix.contains(TD.Prefix.DUST_BASED) || (mCanExplode && aMaterial.contains(TD.Properties.EXPLOSIVE))) && aMaterial.contains(TD.Properties.FLAMMABLE) && WD.temperature(aWorld, aX, aY, aZ) > C + 100) {
				aWorld.setBlockToAir(aX, aY, aZ);
				try {ExplosionGT.explode(aWorld, null, aX+0.5, aY+0.5, aZ+0.5, (aMaterial.contains(TD.Properties.EXPLOSIVE)?(mPrefix.mAmount>0?mPrefix.mAmount:U)*0.5F:(mPrefix.mAmount>0?mPrefix.mAmount:U)*0.33F)/U, T, T);} catch(StackOverflowError e) {ERR.println("WARNING: StackOverflow during Explosion has been prevented at: " + aX +" ; "+ aY +" ; "+ aZ);}
				return;
			}
			if ((mCanBurn || mCanExplode) && aMaterial.contains(TD.Atomic.ALKALI_METAL)) {
				boolean tExplode = F;
				for (byte tSide : ALL_SIDES_VALID) {
					Block tBlock = aWorld.getBlock(aX+OFFSETS_X[tSide], aY+OFFSETS_Y[tSide], aZ+OFFSETS_Z[tSide]);
					if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
						aWorld.setBlockToAir(aX+OFFSETS_X[tSide], aY+OFFSETS_Y[tSide], aZ+OFFSETS_Z[tSide]);
						tExplode = T;
					}
				}
				if (tExplode) {
					aWorld.setBlockToAir(aX, aY, aZ);
					try {ExplosionGT.explode(aWorld, null, aX+0.5, aY+0.5, aZ+0.5, (aMaterial.contains(TD.Properties.EXPLOSIVE)?(mPrefix.mAmount>0?mPrefix.mAmount:U)*0.5F:(mPrefix.mAmount>0?mPrefix.mAmount:U)*0.33F)/U, T, T);} catch(StackOverflowError e) {ERR.println("WARNING: StackOverflow during Explosion has been prevented at: " + aX +" ; "+ aY +" ; "+ aZ);}
					return;
				}
			}
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World aWorld, int aX, int aY, int aZ, int aMeta, float aChance, int aFortune) {
		ArrayList<ItemStack> tList = mDrops.getDrops(this, aWorld, aX, aY, aZ, aFortune, F);
		aChance = ForgeEventFactory.fireBlockHarvesting(tList, aWorld, this, aX, aY, aZ, 0, aFortune, aChance, F, harvesters.get());
		for (ItemStack tStack : tList) if (RNGSUS.nextFloat() <= aChance) dropBlockAsItem(aWorld, aX, aY, aZ, tStack);
	}
	
	@Override
	public void harvestBlock(World aWorld, EntityPlayer aPlayer, int aX, int aY, int aZ, int aMeta) {
		aPlayer.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
		aPlayer.addExhaustion(0.025F);
		boolean aSilkTouch = EnchantmentHelper.getSilkTouchModifier(aPlayer);
		int aFortune = EnchantmentHelper.getFortuneModifier(aPlayer);
		ArrayList<ItemStack> tList = mDrops.getDrops(this, aWorld, aX, aY, aZ, aFortune, aSilkTouch);
		float aChance = ForgeEventFactory.fireBlockHarvesting(tList, aWorld, this, aX, aY, aZ, 0, aFortune, 1.0F, aSilkTouch, aPlayer);
		for (ItemStack tStack : tList) if (RNGSUS.nextFloat() <= aChance) dropBlockAsItem(aWorld, aX, aY, aZ, tStack);
	}
	
	@Override public final ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aUnusableMetaData, int aFortune) {return mDrops.getDrops(this, aWorld, aX, aY, aZ, aFortune, F);}
	@Override public int getExpDrop(IBlockAccess aWorld, int aMeta, int aFortune) {return mDrops.getExp(this);}
	@Override public int getRenderBlockPass() {return ITexture.Util.MC_ALPHA_BLENDING?1:0;}
	@Override public void getSubBlocks(Item aItem, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {aItem.getSubItems(aItem, aCreativeTab, aList);}
	/** Where I come from, we set the TileEntities ourselves instead of letting a Handler do it. */
	@Override public final TileEntity createNewTileEntity(World aWorld, int aMeta) {return null;}
	/** Where I come from, we set the TileEntities ourselves instead of letting a Handler do it. */
	@Override public final TileEntity createTileEntity(World aWorld, int aMeta) {return null;}
	@Override public String toString() {return mNameInternal;}
	@Override public String getUnlocalizedName() {return mNameInternal;}
	@Override public String getLocalizedName() {return StatCollector.translateToLocal(mNameInternal + ".name");}
	@Override public String getHarvestTool(int aMaterialToolQuality) {return mTool;}
	@Override public boolean isToolEffective(String aType, int aMeta) {return getHarvestTool(aMeta).equals(aType);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX + mMinX, aY + mMinY, aZ + mMinZ, aX + mMaxX, aY + mMaxY, aZ + mMaxZ);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {return AxisAlignedBB.getBoundingBox(aX + mMinX, aY + mMinY, aZ + mMinZ, aX + mMaxX, aY + mMaxY, aZ + mMaxZ);}
	@Override public void setBlockBoundsBasedOnState(IBlockAccess aWorld, int aX, int aY, int aZ) {setBlockBounds(mMinX, mMinY, mMinZ, mMaxX, mMaxY, mMaxZ);}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return mBaseHardness < 0 ? -1 : mBaseHardness == 0 ? 0 : Math.max(1, mBaseHardness * (1+getHarvestLevel(aWorld.getBlockMetadata(aX, aY, aZ))));}
	@Override public int getRenderType() {return RendererBlockTextured.INSTANCE==null?super.getRenderType():RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public int getHarvestLevel(int aMaterialToolQuality) {return (int)UT.Code.bind_(mHarvestLevelMinimum, mHarvestLevelMaximum, mHarvestLevelOffset + aMaterialToolQuality);}
	@Override public int tickRate(World aWorld) {return 2;}
	@Override public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {return getRenderColor(getMetaDataValue(aWorld, aX, aY, aZ));}
	@Override public int getLightOpacity() {return mOpaque?255:0;}
	@Override public boolean isBeaconBase(IBlockAccess aWorld, int aX, int aY, int aZ, int aBeaconX, int aBeaconY, int aBeaconZ) {return mBeaconBase;}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return mOpaque;}
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return mNormalCube;}
	@Override public boolean hasTileEntity(int aMeta) {return T;}
	@Override public boolean renderAsNormalBlock() {return T;}
	@Override public final boolean isOpaqueCube() {return mOpaque;}
	@Override public boolean canSilkHarvest() {return F;}
	@Override public boolean func_149730_j() {return mOpaque;}
	@Override public boolean canCreatureSpawn(EnumCreatureType aType, IBlockAccess aWorld, int aX, int aY, int aZ) {return !mSpawnProof;}
	@Override public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {setBlockBoundsBasedOnState(aWorld, aX, aY, aZ); return super.shouldSideBeRendered(aWorld, aX, aY, aZ, aSide);}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return T;}
	@Override public Block getBlock() {return this;}
	
	public PrefixBlock setHidden(boolean aHidden) {mHidden = aHidden; return this;}
	
	/** @return the Local Name for this Block depending on Prefix and Material. */
	public String getLocalName(OreDictPrefix aPrefix, OreDictMaterial aMaterial) {
		return LanguageHandler.getLocalName(aPrefix, aMaterial);
	}
	
	public short getMetaDataValue(TileEntity aTileEntity) {
		return aTileEntity instanceof PrefixBlockTileEntity?((PrefixBlockTileEntity)aTileEntity).mMetaData:0;
	}
	
	public short getMetaDataValue(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return getMetaDataValue(aWorld.getTileEntity(aX, aY, aZ));
	}
	
	public OreDictMaterial getMetaMaterial(int aMetaData) {
		return UT.Code.exists(aMetaData, mMaterialList)?mMaterialList[aMetaData]:null;
	}
	
	public OreDictMaterial getMetaMaterial(TileEntity aTileEntity) {
		return getMetaMaterial(aTileEntity instanceof PrefixBlockTileEntity?((PrefixBlockTileEntity)aTileEntity).mMetaData:0);
	}
	
	public OreDictMaterial getMetaMaterial(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return getMetaMaterial(aWorld.getTileEntity(aX, aY, aZ));
	}
	
	public TileEntity createTileEntity(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT) {
		PrefixBlockTileEntity rTileEntity = new PrefixBlockTileEntity();
		if (aNBT != null) rTileEntity.readFromNBT(aNBT);
		rTileEntity.mMetaData = aMetaData;
		rTileEntity.mItemNBT = aNBT == null ? null : aNBT.hasKey("gt.nbt.drop") ? aNBT.getCompoundTag("gt.nbt.drop") : aNBT;
		return rTileEntity;
	}
	
	protected boolean checkGravity(World aWorld, int aX, int aY, int aZ) {
		if (mGravity && aY > 0 && aWorld.getTileEntity(aX, aY, aZ) != null && BlockFalling.func_149831_e(aWorld, aX, aY - 1, aZ)) {
			if (!BlockFalling.fallInstantly && aWorld.checkChunksExist(aX-32, aY-32, aZ-32, aX+32, aY+32, aZ+32)) {
				if (!aWorld.isRemote) aWorld.spawnEntityInWorld(new PrefixBlockFallingEntity(aWorld, aX+0.5, aY+0.5, aZ+0.5, this, getItemStackFromBlock(aWorld, aX, aY, aZ, SIDE_UP)));
			} else {
				short tMetaData = getMetaDataValue(aWorld, aX, aY, aZ);
				if (tMetaData > 0) {
					aWorld.setBlockToAir(aX, aY, aZ);
					while (BlockFalling.func_149831_e(aWorld, aX, aY-1, aZ) && aY > 0) --aY;
					if (aY > 0) placeBlock(aWorld, aX, aY, aZ, SIDE_UP, tMetaData, null, F, T);
				}
			}
			return T;
		}
		return F;
	}
	
	@Override public void receiveDataByte     (IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler) {/**/}
	@Override public void receiveDataShort    (IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler) {setExtendedMetaData(aWorld, aX, aY, aZ, aData);}
	@Override public void receiveDataInteger  (IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler) {/**/}
	@Override public void receiveDataLong     (IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler) {/**/}
	@Override public void receiveDataByteArray(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler) {/**/}
	@Override public void receiveDataName     (IBlockAccess aWorld, int aX, int aY, int aZ, String aData, INetworkHandler aNetworkHandler) {if (UT.Code.stringValid(aData)) {TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ); if (aTileEntity instanceof PrefixBlockTileEntity) {if (((PrefixBlockTileEntity)aTileEntity).mItemNBT == null) ((PrefixBlockTileEntity)aTileEntity).mItemNBT = UT.NBT.make(); ((PrefixBlockTileEntity)aTileEntity).mItemNBT.setTag("display", UT.NBT.makeString(((PrefixBlockTileEntity)aTileEntity).mItemNBT.getCompoundTag("display"), "Name", aData));}}}
}
