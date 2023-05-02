/**
 * Copyright (c) 2023 GregTech-6 Team
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

import java.util.List;
import java.util.Random;

import gregapi.data.LH;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBase extends Block implements IBlockBase {
	public final String mNameInternal;
	
	public BlockBase(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType) {
		super(aMaterial);
		setStepSound(aSoundType);
		setBlockName(mNameInternal = aNameInternal);
		setCreativeTab(CreativeTabs.tabBlock);
		ST.register(this, mNameInternal, aItemClass);
		LH.add(mNameInternal+"."+W, "Any Sub-Block of this one");
	}
	
	@Override public final String getUnlocalizedName() {return mNameInternal;}
	@Override public String getLocalizedName() {return StatCollector.translateToLocal(mNameInternal);}
	@Override public String getHarvestTool(int aMeta) {return TOOL_pickaxe;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public boolean canSilkHarvest() {return canSilkHarvest((byte)0);}
	@Override public boolean canSilkHarvest(World aWorld, EntityPlayer aPlayer, int aX, int aY, int aZ, int aMeta) {return canSilkHarvest(UT.Code.bind4(aMeta));}
	@Override public boolean isToolEffective(String aType, int aMeta) {return getHarvestTool(aMeta).equals(aType);}
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return T;}
	@Override public boolean renderAsNormalBlock() {return T;}
	@Override public boolean isOpaqueCube() {return T;}
	@Override public boolean func_149730_j() {return isOpaqueCube();}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return isSideSolid(WD.meta(aWorld, aX, aY, aZ), UT.Code.side(aDirection));}
	@Override public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return isOpaqueCube() ? !WD.visOpq(aWorld.getBlock(aX, aY, aZ)) : super.shouldSideBeRendered(aWorld, aX, aY, aZ, aSide);}
	@Override public int damageDropped(int aMeta) {return aMeta;}
	@Override public int quantityDropped(int aMeta, int aFortune, Random aRandom) {return 1;}
	@Override public ItemStack createStackedBlock(int aMeta) {return ST.make(this, 1, damageDropped(aMeta));}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return WD.meta(aWorld, aX, aY, aZ);}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_MAX;}
	@Override public Item getItemDropped(int aMeta, Random aRandom, int aFortune) {return Item.getItemFromBlock(this);}
	@Override public Item getItem(World aWorld, int aX, int aY, int aZ) {return Item.getItemFromBlock(this);}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public boolean canSustainPlant(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide, IPlantable aPlant) {return F;}
	@Override public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess aWorld, int aX, int aY, int aZ) {byte aMeta = WD.meta(aWorld, aX, aY, aZ); return canCreatureSpawn(aMeta) && isSideSolid(aMeta, SIDE_TOP);}
	@Override public boolean isFireSource(World aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return F;}
	@Override public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return getFlammability(aWorld, aX, aY, aZ, aSide) > 0;}
	@Override public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return getFlammability(WD.meta(aWorld, aX, aY, aZ));}
	@Override public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return getFireSpreadSpeed(WD.meta(aWorld, aX, aY, aZ));}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return getExplosionResistance(WD.meta(aWorld, aX, aY, aZ));}
	@Override public float getExplosionResistance(Entity aEntity) {return getExplosionResistance((byte)0);}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return 1;}
	@Override public Block getBlock() {return this;}
	@Override public byte maxMeta() {return 1;}
	@Override public final void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {if (useGravity(WD.meta(aWorld, aX, aY, aZ))) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2); onNeighborBlockChange2(aWorld, aX, aY, aZ, aBlock);}
	@Override public final void onBlockAdded(World aWorld, int aX, int aY, int aZ) {if (useGravity(WD.meta(aWorld, aX, aY, aZ))) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 2); onBlockAdded2(aWorld, aX, aY, aZ);}
	@Override public IIcon getIcon(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {return getIcon(aSide, WD.meta(aWorld, aX, aY, aZ));}
	
	@Override public String name(byte aMeta) {return aMeta == W ? mNameInternal : mNameInternal + "." + aMeta;}
	@Override public boolean useGravity(byte aMeta) {return F;}
	@Override public boolean doesWalkSpeed(byte aMeta) {return F;}
	@Override public boolean doesPistonPush(byte aMeta) {return F;}
	@Override public boolean canSilkHarvest(byte aMeta) {return T;}
	@Override public boolean canCreatureSpawn(byte aMeta) {return F;}
	@Override public boolean isSealable(byte aMeta, byte aSide) {return isSideSolid(aMeta, aSide);}
	@Override public void addInformation(ItemStack aStack, byte aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {/**/}
	@Override public float getExplosionResistance(byte aMeta) {return 10.0F;}
	@Override public int getFlammability(byte aMeta) {return 0;}
	@Override public int getFireSpreadSpeed(byte aMeta) {return 0;}
	@Override public int getItemStackLimit(ItemStack aStack) {return UT.Code.bindStack(OP.block.mDefaultStackSize);}
	@Override public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {return aStack;}
	
	public boolean checkNoEntityCollision(World aWorld, int aX, int aY, int aZ, byte aMeta, Entity aExceptThisOne) {return aWorld.checkNoEntityCollision(AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+1, aY+1, aZ+1), aExceptThisOne);}
	public boolean isSideSolid(int aMeta, byte aSide) {return T;}
	public void updateTick2(World aWorld, int aX, int aY, int aZ, Random aRandom) {/**/}
	public void onNeighborBlockChange2(World aWorld, int aX, int aY, int aZ, Block aBlock) {/**/}
	public void onBlockAdded2(World aWorld, int aX, int aY, int aZ) {/**/}
	
	@Override
	public final void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (aWorld.isRemote || checkGravity(aWorld, aX, aY, aZ)) return;
		updateTick2(aWorld, aX, aY, aZ, aRandom);
	}
	
	public boolean checkGravity(World aWorld, int aX, int aY, int aZ) {
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		if (aY > 0 && useGravity(aMeta) && BlockFalling.func_149831_e(aWorld, aX, aY - 1, aZ)) {
			if (!BlockFalling.fallInstantly && aWorld.checkChunksExist(aX-32, aY-32, aZ-32, aX+32, aY+32, aZ+32)) {
				if (!aWorld.isRemote) aWorld.spawnEntityInWorld(new EntityFallingBlock(aWorld, aX+0.5, aY+0.5, aZ+0.5, this, aMeta));
			} else {
				aWorld.setBlockToAir(aX, aY, aZ);
				while (BlockFalling.func_149831_e(aWorld, aX, aY-1, aZ) && aY > 0) --aY;
				if (aY > 0) WD.set(aWorld, aX, aY, aZ, this, aMeta, 2);
			}
			return T;
		}
		return F;
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
		
		if (!aWorld.getBlock(aX, aY, aZ).isReplaceable(aWorld, aX, aY, aZ)) return F;
		if (!canReplace(aWorld, aX, aY, aZ, aSide, aStack)) return F;
		byte aMeta = UT.Code.bind4(aItem.getMetadata(ST.meta(aStack)));
		if (!checkNoEntityCollision(aWorld, aX, aY, aZ, aMeta, null)) return F;
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || (aY == 255 && getMaterial().isSolid()) || !aWorld.canPlaceEntityOnSide(this, aX, aY, aZ, F, aSide, aPlayer, aStack)) return F;
		
		if (aItem.placeBlockAt(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, onBlockPlaced(aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, aMeta))) {
			aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
			aStack.stackSize--;
		}
		return T;
	}
	
	@Override public final int quantityDropped(Random aRandom) {return quantityDropped(0, 0, aRandom);}
}
