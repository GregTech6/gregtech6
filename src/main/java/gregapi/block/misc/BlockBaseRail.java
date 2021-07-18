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

package gregapi.block.misc;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import gregapi.block.IBlockBase;
import gregapi.block.IBlockToolable;
import gregapi.block.ItemBlockBase;
import gregapi.block.ToolCompat;
import gregapi.compat.galacticraft.IBlockSealable;
import gregapi.data.LH;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public class BlockBaseRail extends BlockRailBase implements IBlockBase, IBlockSealable, IBlockToolable {
	public final String mNameInternal;
	public final float mSpeed, mExplosionResistance;
	public final IIconContainer mIconPrimary, mIconSecondary;
	public final int mHarvestLevel;
	public final boolean mPowerRail, mDetectorRail;
	
	/** @param aSpeed is usually 0.4F */
	public BlockBaseRail(Class<? extends ItemBlockBase> aItemClass, String aNameInternal, String aLocalName, boolean aPowerRail, boolean aDetectorRail, float aSpeed, float aExplosionResistance, int aHarvestLevel, IIconContainer aIconPrimary, IIconContainer aIconSecondary) {
		super(aPowerRail || aDetectorRail);
		setBlockName(mNameInternal = aNameInternal);
		setCreativeTab(CreativeTabs.tabTransport);
		ST.register(this, mNameInternal, aItemClass);
		LH.add(mNameInternal+".name", aLocalName);
		mExplosionResistance = aExplosionResistance;
		mHarvestLevel = aHarvestLevel;
		mSpeed = aSpeed;
		mIconSecondary = aIconSecondary;
		mIconPrimary = aIconPrimary;
		mDetectorRail = aDetectorRail;
		mPowerRail = aPowerRail;
		if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(this, 1, W));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		aList.add(LH.Chat.CYAN + LH.get(LH.TOOLTIP_RAILSPEED) + LH.Chat.GREEN + (mSpeed/0.4F) + "x");
	}
	
	@Override public float getRailMaxSpeed(World aWorld, EntityMinecart aCart, int aX, int aY, int aZ) {return mSpeed;}
	
	@Override public final String getUnlocalizedName() {return mNameInternal;}
	@Override public String name(byte aMeta) {return mNameInternal;}
	@Override public String getLocalizedName() {return StatCollector.translateToLocal(mNameInternal+ ".name");}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return Blocks.rail.getBlockHardness(aWorld, aX, aY, aZ);}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return mExplosionResistance;}
	@Override public float getExplosionResistance(Entity aEntity) {return mExplosionResistance;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_crowbar;}
	@Override public int getHarvestLevel(int aMeta) {return mHarvestLevel;}
	@Override public boolean canSilkHarvest() {return canSilkHarvest((byte)0);}
	@Override public boolean canSilkHarvest(World aWorld, EntityPlayer aPlayer, int aX, int aY, int aZ, int aMeta) {return canSilkHarvest(UT.Code.bind4(aMeta));}
	@Override public boolean isToolEffective(String aType, int aMeta) {return getHarvestTool(aMeta).equals(aType);}
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return F;}
	@Override public int damageDropped(int aMeta) {return 0;}
	@Override public int quantityDropped(Random par1Random) {return 1;}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return 0;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public Item getItemDropped(int par1, Random par2Random, int par3) {return Item.getItemFromBlock(this);}
	@Override public Item getItem(World aWorld, int aX, int aY, int aZ) {return Item.getItemFromBlock(this);}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess aWorld, int aX, int aY, int aZ) {return canCreatureSpawn(WD.meta(aWorld, aX, aY, aZ));}
	@SuppressWarnings("unchecked") @Override public void getSubBlocks(Item aItem, CreativeTabs par2CreativeTabs, @SuppressWarnings("rawtypes") List aList) {aList.add(ST.make(aItem, 1, 0));}
	@Override public IIcon getIcon(int aSide, int aMeta) {return ((mPowerRail||mDetectorRail?(aMeta&8)!=0:aMeta>=6)?mIconSecondary:mIconPrimary).getIcon(0);}
	@Override public boolean isSealed(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return F;}
	@Override public Block getBlock() {return this;}
	@Override public byte maxMeta() {return 1;}
	
	@Override public float getExplosionResistance(byte aMeta) {return mExplosionResistance;}
	@Override public int getItemStackLimit(ItemStack aStack) {return 64;}
	@Override public boolean useGravity(byte aMeta) {return F;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return T;}
	@Override public boolean canSilkHarvest(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return F;}
	@Override public int getFlammability(byte aMeta) {return 0;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 0;}
	@Override public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {return aStack;}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (!aWorld.isRemote) {
			if (aTool.equals(TOOL_softhammer) && mPowerRail) {
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, this, (WD.meta(aWorld, aX, aY, aZ) + 8) % 16, 0);
				aWorld.isRemote = F;
				return tResult?10000:0;
			}
			if (aTool.equals(TOOL_crowbar)) {
				byte aMeta = WD.meta(aWorld, aX, aY, aZ);
				aWorld.isRemote = T;
				boolean tResult = aWorld.setBlock(aX, aY, aZ, this, isPowered() ? (aMeta+1) % 10 : ((aMeta/8) * 8) + (((aMeta%8)+1) % 6), 0);
				aWorld.isRemote = F;
				return tResult?2000:0;
			}
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
	
	protected boolean func_150058_a(World aWorld, int aX, int aY, int aZ, int p_150058_5_, boolean p_150058_6_, int p_150058_7_) {
		if (p_150058_7_ >= 8) return F;
		int j1 = p_150058_5_ & 7;
		boolean flag1 = T;
		switch (j1) {
		case 0: if (p_150058_6_) ++aZ; else --aZ; break;
		case 1: if (p_150058_6_) --aX; else ++aX; break;
		case 2: if (p_150058_6_) --aX; else {++aX; ++aY; flag1 = F;} j1 = 1; break;
		case 3: if (p_150058_6_) {--aX; ++aY; flag1 = F;} else ++aX; j1 = 1; break;
		case 4: if (p_150058_6_) ++aZ; else {--aZ; ++aY; flag1 = F;} j1 = 0; break;
		case 5: if (p_150058_6_) {++aZ; ++aY; flag1 = F;} else --aZ; j1 = 0; break;
		}
		return func_150057_a(aWorld, aX, aY, aZ, p_150058_6_, p_150058_7_, j1) || (flag1 && func_150057_a(aWorld, aX, aY - 1, aZ, p_150058_6_, p_150058_7_, j1));
	}
	
	protected boolean func_150057_a(World aWorld, int aX, int aY, int aZ, boolean p_150057_5_, int p_150057_6_, int p_150057_7_) {
		if (aWorld.getBlock(aX, aY, aZ) == this) {
			int j1 = WD.meta(aWorld, aX, aY, aZ);
			int k1 = j1 & 7;
			
			if (p_150057_7_ == 1 && (k1 == 0 || k1 == 4 || k1 == 5)) return F;
			if (p_150057_7_ == 0 && (k1 == 1 || k1 == 2 || k1 == 3)) return F;
			
			if ((j1 & 8) != 0) {
				if (aWorld.isBlockIndirectlyGettingPowered(aX, aY, aZ)) return T;
				return func_150058_a(aWorld, aX, aY, aZ, j1, p_150057_5_, p_150057_6_ + 1);
			}
		}
		return F;
	}
	
	@Override
	protected void func_150048_a(World aWorld, int aX, int aY, int aZ, int p_150048_5_, int p_150048_6_, Block aBlock) {
		if (mPowerRail) {
			boolean flag = aWorld.isBlockIndirectlyGettingPowered(aX, aY, aZ);
			flag = flag || func_150058_a(aWorld, aX, aY, aZ, p_150048_5_, T, 0) || func_150058_a(aWorld, aX, aY, aZ, p_150048_5_, F, 0);
			boolean flag1 = F;
			if (flag && (p_150048_5_ & 8) == 0) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, p_150048_6_ | 8, 3);
				flag1 = T;
			} else if (!flag && (p_150048_5_ & 8) != 0) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, p_150048_6_, 3);
				flag1 = T;
			}
			if (flag1) {
				aWorld.notifyBlocksOfNeighborChange(aX, aY - 1, aZ, this);
				if (p_150048_6_ == 2 || p_150048_6_ == 3 || p_150048_6_ == 4 || p_150048_6_ == 5) {
					aWorld.notifyBlocksOfNeighborChange(aX, aY + 1, aZ, this);
				}
			}
		}
	}
	
	@Override public int tickRate(World aWorld) {return 20;}
	@Override public boolean canProvidePower() {return mDetectorRail;}
	
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		if (mDetectorRail && !aWorld.isRemote) {
			int l = WD.meta(aWorld, aX, aY, aZ);
			if ((l & 8) == 0) func_150054_a(aWorld, aX, aY, aZ, l);
		}
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (mDetectorRail && !aWorld.isRemote) {
			int l = WD.meta(aWorld, aX, aY, aZ);
			if ((l & 8) != 0) func_150054_a(aWorld, aX, aY, aZ, l);
		}
	}
	
	@Override public int isProvidingWeakPower(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return mDetectorRail ? (WD.meta(aWorld, aX, aY, aZ) & 8) != 0 ? 15 : 0 : 0;}
	@Override public int isProvidingStrongPower(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return mDetectorRail ? (WD.meta(aWorld, aX, aY, aZ) & 8) == 0 ? 0 : (aSide == 1 ? 15 : 0) : 0;}
	
	private void func_150054_a(World aWorld, int aX, int aY, int aZ, int aMetaData) {
		boolean flag = (aMetaData & 8) != 0;
		boolean flag1 = F;
		@SuppressWarnings("unchecked")
		List<EntityMinecart> list = aWorld.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(aX + 0.125, aY, aZ + 0.125, aX + 0.875, aY + 0.875, aZ + 0.875));
		
		if (!list.isEmpty()) flag1 = T;
		if (flag1 && !flag) {
			aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMetaData | 8, 3);
			aWorld.notifyBlocksOfNeighborChange(aX, aY, aZ, this);
			aWorld.notifyBlocksOfNeighborChange(aX, aY - 1, aZ, this);
			aWorld.markBlockRangeForRenderUpdate(aX, aY, aZ, aX, aY, aZ);
		}
		if (!flag1 && flag) {
			aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMetaData & 7, 3);
			aWorld.notifyBlocksOfNeighborChange(aX, aY, aZ, this);
			aWorld.notifyBlocksOfNeighborChange(aX, aY - 1, aZ, this);
			aWorld.markBlockRangeForRenderUpdate(aX, aY, aZ, aX, aY, aZ);
		}
		if (flag1) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate(aWorld));
		aWorld.func_147453_f(aX, aY, aZ, this);
	}
	
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		if (mDetectorRail) func_150054_a(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_, p_149726_1_.getBlockMetadata(p_149726_2_, p_149726_3_, p_149726_4_));
	}
	
	@Override public boolean hasComparatorInputOverride() {return mDetectorRail;}
	
	@Override
	public int getComparatorInputOverride(World aWorld, int aX, int aY, int aZ, int aSide) {
		if (mDetectorRail && (WD.meta(aWorld, aX, aY, aZ) & 8) > 0) {
			@SuppressWarnings("unchecked")
			List<EntityMinecartCommandBlock> list = aWorld.getEntitiesWithinAABB(EntityMinecartCommandBlock.class, AxisAlignedBB.getBoundingBox(aX + 0.125, aY, aZ + 0.125, aX + 0.875, aY + 0.875, aZ + 0.875));
			if (list.size() > 0) return list.get(0).func_145822_e().func_145760_g();
			@SuppressWarnings("unchecked")
			List<EntityMinecart> list1 = aWorld.selectEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(aX + 0.125, aY, aZ + 0.125, aX + 0.875, aY + 0.875, aZ + 0.875), IEntitySelector.selectInventories);
			if (list1.size() > 0) return Container.calcRedstoneFromInventory((IInventory)list1.get(0));
		}
		return 0;
	}
	
	@Override
	public void onMinecartPass(World aWorld, EntityMinecart aCart, int aX, int aY, int aZ) {
		if (mPowerRail) {
			byte tRailMeta = WD.meta(aWorld, aX, aY, aZ);
			double tMotion = Math.sqrt(aCart.motionX * aCart.motionX + aCart.motionZ * aCart.motionZ);
			if ((tRailMeta & 8) != 0) {
				if (tMotion > 0.01) {
					aCart.motionX += aCart.motionX / tMotion * 0.06;
					aCart.motionZ += aCart.motionZ / tMotion * 0.06;
				} else {
					tRailMeta &= 7;
					if (tRailMeta == 1) {
							 if (aWorld.getBlock(aX-1, aY, aZ).isNormalCube(aWorld, aX-1, aY, aZ)) aCart.motionX = +0.02;
						else if (aWorld.getBlock(aX+1, aY, aZ).isNormalCube(aWorld, aX+1, aY, aZ)) aCart.motionX = -0.02;
					} else if (tRailMeta == 0) {
							 if (aWorld.getBlock(aX, aY, aZ-1).isNormalCube(aWorld, aX, aY, aZ-1)) aCart.motionZ = +0.02;
						else if (aWorld.getBlock(aX, aY, aZ+1).isNormalCube(aWorld, aX, aY, aZ+1)) aCart.motionZ = -0.02;
					}
				}
			} else {
				if (tMotion < 0.03) {
					aCart.motionX  = 0;
					aCart.motionY  = 0;
					aCart.motionZ  = 0;
				} else {
					aCart.motionX /= 2;
					aCart.motionY  = 0;
					aCart.motionZ /= 2;
				}
			}
		}
	}
	
	@Override public boolean onItemUseFirst(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	@Override
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack.stackSize == 0) return F;
		
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock == Blocks.snow_layer && (WD.meta(aWorld, aX, aY, aZ) & 7) < 1) {
			aSide = SIDE_UP;
		} else if (tBlock != Blocks.vine && tBlock != Blocks.tallgrass && tBlock != Blocks.deadbush && !tBlock.isReplaceable(aWorld, aX, aY, aZ)) {
			aX += OFFX[aSide]; aY += OFFY[aSide]; aZ += OFFZ[aSide];
		}
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || (aY == 255 && getMaterial().isSolid()) || !aWorld.canPlaceEntityOnSide(this, aX, aY, aZ, F, aSide, aPlayer, aStack)) return F;
		
		if (aItem.placeBlockAt(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, onBlockPlaced(aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, aItem.getMetadata(aStack.getItemDamage())))) {
			aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
			aStack.stackSize--;
		}
		return T;
	}
}
