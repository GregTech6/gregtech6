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

package gregapi.block.multitileentity;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ModIDs;
import gregapi.network.INetworkHandler;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.tileentity.ITileEntitySpecificPlacementBehavior;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Gregorius Techneticies
 * 
 * Interfaces to override Block Functionality.
 */
public interface IMultiTileEntity extends ITileEntitySpecificPlacementBehavior {
	/** Those two IDs HAVE to be saved inside the NBT of the TileEntity itself. They get set by the Registry itself, when the TileEntity is placed. */
	public short getMultiTileEntityID();
	public short getMultiTileEntityRegistryID();
	/** Called by the Registry with the default NBT Parameters and the two IDs you have to save, when the TileEntity is created. aNBT may be null, take that into account if you decide to call the regular readFromNBT Function from here. */
	public void initFromNBT(NBTTagCompound aNBT, short aMTEID, short aMTERegistry);
	/** Writes eventual Item Data to the NBT. */
	public NBTTagCompound writeItemNBT(NBTTagCompound aNBT);
	/** Sets the Item Display Name. Use null to reset it. */
	public void setCustomName(String aName);
	public String getCustomName();
	
	public void setShouldRefresh(boolean aShouldRefresh);
	
	// Hooks into the Block Class. Implement them in order to overwrite the Default Behaviours.
	public static interface IMTE_OnNeighborChange                   extends IMultiTileEntity {public void onNeighborChange(IBlockAccess aWorld, int aTileX, int aTileY, int aTileZ);}
	public static interface IMTE_OnNeighborBlockChange              extends IMultiTileEntity {public void onNeighborBlockChange(World aWorld, Block aBlock);}
	public static interface IMTE_OnBlockExploded                    extends IMultiTileEntity {public void onExploded(Explosion aExplosion);}
	public static interface IMTE_GetPickBlock                       extends IMultiTileEntity {public ItemStack getPickBlock(MovingObjectPosition aTarget);}
	public static interface IMTE_BreakBlock                         extends IMultiTileEntity {/** return true to prevent the TileEntity from being removed. */public boolean breakBlock();}
	public static interface IMTE_GetStackFromBlock                  extends IMultiTileEntity {public ItemStack getStackFromBlock(byte aSide);}
	public static interface IMTE_GetFlammability                    extends IMultiTileEntity {public int getFlammability(byte aSide, boolean aDefault);}
	public static interface IMTE_GetFireSpreadSpeed                 extends IMultiTileEntity {public int getFireSpreadSpeed(byte aSide, boolean aDefault);}
	public static interface IMTE_IsFireSource                       extends IMultiTileEntity {public boolean isFireSource(byte aSide);}
	public static interface IMTE_CanEntityDestroy                   extends IMultiTileEntity {public boolean canEntityDestroy(Entity aEntity);}
	public static interface IMTE_OnToolClick                        extends IMultiTileEntity {public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ);}
	public static interface IMTE_GetMaterialAtSide                  extends IMultiTileEntity {public OreDictMaterialStack getMaterialAtSide(byte aSide);}
	public static interface IMTE_RemoveMaterialFromSide             extends IMultiTileEntity {public boolean removeMaterialFromSide(byte aSide, OreDictMaterialStack aMaterial);}
	public static interface IMTE_GetDrops                           extends IMultiTileEntity {public ArrayListNoNulls<ItemStack> getDrops(int aFortune, boolean aSilkTouch);}
	public static interface IMTE_GetBlockHardness                   extends IMultiTileEntity {public float getBlockHardness();}
	public static interface IMTE_GetPlayerRelativeBlockHardness     extends IMultiTileEntity {public float getPlayerRelativeBlockHardness(EntityPlayer aPlayer, float aOriginal);}
	public static interface IMTE_GetExplosionResistance             extends IMultiTileEntity {public float getExplosionResistance(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ); public float getExplosionResistance();}
	public static interface IMTE_IsSideSolid                        extends IMultiTileEntity {public boolean isSideSolid(byte aSide);}
	public static interface IMTE_IsBeaconBase                       extends IMultiTileEntity {public boolean isBeaconBase(int aBeaconX, int aBeaconY, int aBeaconZ);}
	public static interface IMTE_GetLightOpacity                    extends IMultiTileEntity {public int getLightOpacity();}
	public static interface IMTE_GetBlocksMovement                  extends IMultiTileEntity {public boolean getBlocksMovement();}
	public static interface IMTE_ShouldSideBeRendered               extends IMultiTileEntity {public boolean shouldSideBeRendered(byte aSide);}
	public static interface IMTE_AddCollisionBoxesToList            extends IMultiTileEntity {public void addCollisionBoxesToList(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity);}
	public static interface IMTE_GetCollisionBoundingBoxFromPool    extends IMultiTileEntity {public AxisAlignedBB getCollisionBoundingBoxFromPool();}
	public static interface IMTE_GetSelectedBoundingBoxFromPool     extends IMultiTileEntity {public AxisAlignedBB getSelectedBoundingBoxFromPool();}
	public static interface IMTE_UpdateTick                         extends IMultiTileEntity {public void updateTick(Random aRandom);}
	public static interface IMTE_RandomDisplayTick                  extends IMultiTileEntity {public void randomDisplayTick(Random aRandom);}
	public static interface IMTE_OnBlockDestroyedByPlayer           extends IMultiTileEntity {public void onBlockDestroyedByPlayer(int aRandom);}
	public static interface IMTE_OnBlockAdded                       extends IMultiTileEntity {public void onBlockAdded();}
	public static interface IMTE_DropXpOnBlockBreak                 extends IMultiTileEntity {public void dropXpOnBlockBreak(int aXP);}
	public static interface IMTE_CollisionRayTrace                  extends IMultiTileEntity {public MovingObjectPosition collisionRayTrace(Vec3 aVectorA, Vec3 aVectorB);}
	public static interface IMTE_OnBlockActivated                   extends IMultiTileEntity {public boolean onBlockActivated(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ);}
	public static interface IMTE_OnEntityWalking                    extends IMultiTileEntity {public void onEntityWalking(Entity aEntity);}
	public static interface IMTE_OnBlockClicked                     extends IMultiTileEntity {public void onBlockClicked(EntityPlayer aPlayer);}
	public static interface IMTE_VelocityToAddToEntity              extends IMultiTileEntity {public void velocityToAddToEntity(Entity aEntity, Vec3 aVector);}
	public static interface IMTE_SetBlockBoundsBasedOnState         extends IMultiTileEntity {public void setBlockBoundsBasedOnState(Block aBlock);}
	public static interface IMTE_IsProvidingWeakPower               extends IMultiTileEntity {/** Remember that it passes the opposite Side due to the way vanilla works! */ public int isProvidingWeakPower(byte aOppositeSide);}
	public static interface IMTE_IsProvidingStrongPower             extends IMultiTileEntity {/** Remember that it passes the opposite Side due to the way vanilla works! */ public int isProvidingStrongPower(byte aOppositeSide);}
	public static interface IMTE_OnEntityCollidedWithBlock          extends IMultiTileEntity {/** This Function has been named wrong. It should be onEntityOverlapWithBlock */ public void onEntityCollidedWithBlock(Entity aEntity);}
	public static interface IMTE_CanBlockStay                       extends IMultiTileEntity {public boolean canBlockStay();}
	public static interface IMTE_OnFallenUpon                       extends IMultiTileEntity {public void onFallenUpon(Entity aEntity, float aFallDistance);}
	public static interface IMTE_OnBlockHarvested                   extends IMultiTileEntity {public void onBlockHarvested(int aMetaData, EntityPlayer aPlayer);}
	public static interface IMTE_OnBlockPreDestroy                  extends IMultiTileEntity {public void onBlockPreDestroy(int aMetaData);}
	public static interface IMTE_FillWithRain                       extends IMultiTileEntity {public void fillWithRain();}
	public static interface IMTE_GetComparatorInputOverride         extends IMultiTileEntity {public int getComparatorInputOverride(byte aSide);}
	public static interface IMTE_GetLightValue                      extends IMultiTileEntity {public int getLightValue();}
	public static interface IMTE_IsLadder                           extends IMultiTileEntity {public boolean isLadder(EntityLivingBase aEntity);}
	public static interface IMTE_IsNormalCube                       extends IMultiTileEntity {public boolean isNormalCube();}
	public static interface IMTE_IsReplaceable                      extends IMultiTileEntity {public boolean isReplaceable();}
	public static interface IMTE_IsBurning                          extends IMultiTileEntity {public boolean isBurning();}
	public static interface IMTE_IsAir                              extends IMultiTileEntity {public boolean isAir();}
	public static interface IMTE_RemovedByPlayer                    extends IMultiTileEntity {public boolean removedByPlayer(World aWorld, EntityPlayer aPlayer, boolean aWillHarvest);}
	public static interface IMTE_CanPlaceSnowLayerOnRemoval         extends IMTE_RemovedByPlayer {}
	public static interface IMTE_CanCreatureSpawn                   extends IMultiTileEntity {public boolean canCreatureSpawn(EnumCreatureType aType);}
	public static interface IMTE_IsBed                              extends IMultiTileEntity {public boolean isBed(EntityLivingBase aPlayer);}
	public static interface IMTE_GetBedSpawnPosition                extends IMultiTileEntity {public ChunkCoordinates getBedSpawnPosition(EntityPlayer aPlayer);}
	public static interface IMTE_SetBedOccupied                     extends IMultiTileEntity {public void setBedOccupied(EntityPlayer aPlayer, boolean aOccupied);}
	public static interface IMTE_GetBedDirection                    extends IMultiTileEntity {public int getBedDirection();}
	public static interface IMTE_IsBedFoot                          extends IMultiTileEntity {public boolean isBedFoot();}
	public static interface IMTE_BeginLeavesDecay                   extends IMultiTileEntity {public void beginLeavesDecay();}
	public static interface IMTE_CanSustainLeaves                   extends IMultiTileEntity {public boolean canSustainLeaves();}
	public static interface IMTE_IsLeaves                           extends IMultiTileEntity {public boolean isLeaves();}
	public static interface IMTE_CanBeReplacedByLeaves              extends IMultiTileEntity {public boolean canBeReplacedByLeaves();}
	public static interface IMTE_IsWood                             extends IMultiTileEntity {public boolean isWood();}
	public static interface IMTE_IsReplaceableOreGen                extends IMultiTileEntity {public boolean isReplaceableOreGen(Block aTarget);}
	public static interface IMTE_CanConnectRedstone                 extends IMultiTileEntity {public boolean canConnectRedstone(byte aSide);}
	public static interface IMTE_CanPlaceTorchOnTop                 extends IMultiTileEntity {public boolean canPlaceTorchOnTop();}
	public static interface IMTE_IsFoliage                          extends IMultiTileEntity {public boolean isFoliage();}
	public static interface IMTE_CanSustainPlant                    extends IMultiTileEntity {public boolean canSustainPlant(byte aSide, IPlantable aPlantable);}
	public static interface IMTE_OnPlantGrow                        extends IMultiTileEntity {public void onPlantGrow(int sX, int sY, int sZ);}
	public static interface IMTE_IsFertile                          extends IMultiTileEntity {public boolean isFertile();}
	public static interface IMTE_RotateBlock                        extends IMultiTileEntity {public boolean rotateBlock(byte aSide);}
	public static interface IMTE_GetValidRotations                  extends IMultiTileEntity {public ForgeDirection[] getValidRotations();}
	public static interface IMTE_GetEnchantPowerBonus               extends IMultiTileEntity {public float getEnchantPowerBonus();}
	public static interface IMTE_RecolourBlock                      extends IMultiTileEntity {public boolean recolourBlock(byte aSide, byte aColor);}
	public static interface IMTE_ShouldCheckWeakPower               extends IMultiTileEntity {public boolean shouldCheckWeakPower(byte aSide);}
	public static interface IMTE_GetWeakChanges                     extends IMultiTileEntity {public boolean getWeakChanges();}
	public static interface IMTE_OnPainting                         extends IMultiTileEntity {public boolean onPainting(byte aSide, int aRGB);}
	public static interface IMTE_IsSealable                         extends IMultiTileEntity {public boolean isSealable(byte aSide);}
	public static interface IMTE_OnOxygenRemoved                    extends IMultiTileEntity {public void onOxygenRemoved();}
	public static interface IMTE_OnOxygenAdded                      extends IMultiTileEntity {public void onOxygenAdded();}
	public static interface IMTE_RegisterIcons                      extends IMultiTileEntity {@SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aIconRegister);}
	public static interface IMTE_AddHitEffects                      extends IMultiTileEntity {@SideOnly(Side.CLIENT) public boolean addHitEffects(World aWorld, MovingObjectPosition aTarget, EffectRenderer aRenderer);}
	public static interface IMTE_AddDestroyEffects                  extends IMultiTileEntity {@SideOnly(Side.CLIENT) public boolean addDestroyEffects(int aMetaData, EffectRenderer aRenderer);}
	
	public static interface IMTE_SyncDataByte extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataByte(byte aData, INetworkHandler aNetworkHandler);
	}
	
	public static interface IMTE_SyncDataShort extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler);
	}
	
	public static interface IMTE_SyncDataInteger extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataInteger(int aData, INetworkHandler aNetworkHandler);
	}
	
	public static interface IMTE_SyncDataLong extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataLong(long aData, INetworkHandler aNetworkHandler);
	}
	
	public static interface IMTE_SyncDataByteArray extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler);
	}
	
	public static interface IMTE_SyncDataCovers extends IMultiTileEntity {
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataCovers(short[] aCoverIDs, short[] aCoverMetas, INetworkHandler aNetworkHandler);
		/**
		 * If you have something that causes a Crash here, the Connection gets terminated.
		 * @return true if you want the Block to update visually Client Side. This is usually always the case.
		 */
		public boolean receiveDataCovers(short[] aCoverVisuals, boolean[] aVisualsToSync, INetworkHandler aNetworkHandler);
	}

	public static interface IMTE_OnCrafted extends IMultiTileEntity {
		/** Called when it is crafted. aPlayer and/or aWorld may be null! */
		public void onCrafted(EntityPlayer aPlayer, World aWorld, ItemStack aStack);
	}
	
	public static interface IMTE_GetItemName extends IMultiTileEntity {
		/** Overrides the Item Name (Use an already localised String). Return aDefaultName for using the Default Name. You don't need to read the NBT of the Stack, since that is already done, actually, you don't really need the Stack Parameter at all. */
		public String getItemName(ItemStack aStack, String aDefaultName);
	}
	
	public static interface IMTE_OnDespawn extends IMultiTileEntity {
		/** Gets called when the Item despawns. */
		public int onDespawn(EntityItem aEntity, ItemStack aStack);
	}
	
	public static interface IMTE_GetLifeSpan extends IMultiTileEntity {
		/** Gets the life Span of the Item. 6000 = Default */
		public int getLifeSpan(World aWorld, ItemStack aStack);
	}
	
	public static interface IMTE_AddToolTips extends IMultiTileEntity {
		/** Adds ToolTips to the Item. */
		public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H);
	}
	
	public static interface IMTE_GetSubItems extends IMultiTileEntity {
		/** Adds to the Creative Tab. return false to prevent it from being added. */
		public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID);
	}
	
	public static interface IMTE_OnRegistration extends IMultiTileEntity {
		/** Called when the TileEntity is being registered at the MultiTileEntity Registry. */
		public void onRegistration(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnRegistrationFirst extends IMultiTileEntity {
		/** Called when a TileEntity of this particular Class is being registered first at any MultiTileEntity Registry. So basically one call per Class. */
		public void onRegistrationFirst(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnRegistrationFirstOfRegister extends IMultiTileEntity {
		/** Called when a TileEntity of this particular Class is being registered first at a MultiTileEntity Registry. So basically one call per Class and Registry. */
		public void onRegistrationFirstOfRegister(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnRegistrationClient extends IMultiTileEntity {
		/** Called when the TileEntity is being registered at the MultiTileEntity Registry. */
		@SideOnly(Side.CLIENT)
		public void onRegistrationClient(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnRegistrationFirstClient extends IMultiTileEntity {
		/** Called when a TileEntity of this particular Class is being registered first at any MultiTileEntity Registry. So basically one call per Class. */
		@SideOnly(Side.CLIENT)
		public void onRegistrationFirstClient(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnRegistrationFirstOfRegisterClient extends IMultiTileEntity {
		/** Called when a TileEntity of this particular Class is being registered first at a MultiTileEntity Registry. So basically one call per Class and Registry. */
		@SideOnly(Side.CLIENT)
		public void onRegistrationFirstOfRegisterClient(MultiTileEntityRegistry aRegistry, short aID);
	}
	
	public static interface IMTE_OnWalkOver extends IMultiTileEntity {
		public void onWalkOver(EntityLivingBase aEntity);
	}
	
	public static interface IMTE_CanPlace extends IMultiTileEntity {
		/** Return false if this TileEntity cannot be placed at that Location. */
		public boolean canPlace(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ);
	}
	
	public static interface IMTE_OnPlaced extends IMultiTileEntity {
		/** Return false to prevent the Sound from being played, when the Block is placed. aSide is the Side of the Block the Player clicked to place this one. */
		public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ);
	}
	
	public static interface IMTE_GetMaxStackSize extends IMultiTileEntity {
		/** Gets the Max Stacksize of this Item. */
		public byte getMaxStackSize(ItemStack aStack, byte aDefault);
	}
	
	public static interface IMTE_HasMultiBlockMachineRelevantData extends IMultiTileEntity {
		/** Return true to mark this Block as a Machine Block for Multiblocks. */
		public boolean hasMultiBlockMachineRelevantData();
	}
	
	public static interface IMTE_OnlyPlaceableWhenSneaking extends IMultiTileEntity {
		/** Return true to prevent placing this Block without Sneaking. */
		public boolean onlyPlaceableWhenSneaking();
	}
	
	public static interface IMTE_IgnorePlayerCollisionWhenPlacing extends IMultiTileEntity {
		/** Return true to ignore the Player standing in the way of placing this Block. */
		public boolean ignorePlayerCollisionWhenPlacing(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ);
	}
	
	public static interface IMTE_OnItemRightClick extends IMultiTileEntity {
		public ItemStack onItemRightClick(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer);
	}
	
	public static interface IMTE_OnItemUseFirst extends IMultiTileEntity {
		public boolean onItemUseFirst(MultiTileEntityItemInternal aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ);
	}
	
	public static interface IMTE_GetMaxItemUseDuration extends IMultiTileEntity {
		public int getMaxItemUseDuration(MultiTileEntityItemInternal aItem, ItemStack aStack);
	}
	
	public static interface IMTE_GetItemUseAction extends IMultiTileEntity {
		public EnumAction getItemUseAction(MultiTileEntityItemInternal aItem, ItemStack aStack);
	}
	
	public static interface IMTE_OnEaten extends IMultiTileEntity {
		public ItemStack onEaten(MultiTileEntityItemInternal aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer);
	}
	
	public static interface IMTE_GetFoodValues extends IMultiTileEntity {
		@Optional.Method(modid = ModIDs.APC)
		public squeek.applecore.api.food.FoodValues getFoodValues(MultiTileEntityItemInternal aItem, ItemStack aStack);
	}
	
	public static interface IMTE_OnServerStart extends IMultiTileEntity {
		/** Gets called once per class when the Server starts. */
		public void onServerStart();
	}
	
	public static interface IMTE_OnServerStop extends IMultiTileEntity {
		/** Gets called once per class when the Server stops. */
		public void onServerStop();
	}
	
	public static interface IMTE_OnServerLoad extends IMultiTileEntity {
		/** Gets called once per class on the first World Load. */
		public void onServerLoad(File aSaveLocation);
	}
	
	public static interface IMTE_OnServerSave extends IMultiTileEntity {
		/** Gets called once per class when the Server stops. */
		public void onServerSave(File aSaveLocation);
	}
	
	public static interface IMTE_GetOreDictItemData extends IMultiTileEntity {
		/** Gets called to determine OreDictItemData, this is a List with the first element being the Default OreDictItemData, followed by Covers and Stuff. */
		public List<OreDictItemData> getOreDictItemData(List<OreDictItemData> aList);
	}
	
	public static interface IMTE_GetDebugInfo extends IMultiTileEntity {
		public ArrayList<String> getDebugInfo(int aScanLevel);
	}
}
