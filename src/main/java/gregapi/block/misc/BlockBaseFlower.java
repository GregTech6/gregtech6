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

package gregapi.block.misc;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.block.IBlockBase;
import gregapi.block.ItemBlockBase;
import gregapi.compat.galacticraft.IBlockSealable;
import gregapi.data.CS.ModIDs;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IOxygenReliantBlock", modid = ModIDs.GC)
})
public abstract class BlockBaseFlower extends BlockFlower implements IBlockBase, IBlockSealable, IOxygenReliantBlock {
	public final String mNameInternal;
	public IIconContainer[] mIcons;
	/** For Creative Subsets, not actually important. */
	public final byte mMaxMeta;
	
	/** @param aSpeed is usually 0.4F */
	public BlockBaseFlower(Class<? extends ItemBlockBase> aItemClass, String aNameInternal, long aMaxMeta, IIconContainer[] aIcons) {
		super(0);
		mMaxMeta = (byte)(UT.Code.bind4(aMaxMeta-1)+1);
		mIcons = aIcons;
		setStepSound(soundTypeGrass);
		setBlockName(mNameInternal = aNameInternal);
		GameRegistry.registerBlock(this, aItemClass == null ? ItemBlockBase.class : aItemClass, getUnlocalizedName());
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(this);
	}
	
	@Override public final String getUnlocalizedName() {return mNameInternal;}
	@Override public String name(int aMeta) {return mNameInternal + "." + aMeta;}
	@Override public String getLocalizedName() {return StatCollector.translateToLocal(mNameInternal+ ".name");}
	@Override public float getBlockHardness(World aWorld, int aX, int aY, int aZ) {return 0;}
	@Override public float getExplosionResistance(Entity aEntity, World aWorld, int aX, int aY, int aZ, double eX, double eY, double eZ) {return 0;}
	@Override public float getExplosionResistance(Entity aEntity) {return 0;}
	@Override public String getHarvestTool(int aMeta) {return TOOL_sword;}
	@Override public int getHarvestLevel(int aMeta) {return 0;}
	@Override public boolean isToolEffective(String aType, int aMeta) {return T;}
	@Override public boolean canBeReplacedByLeaves(IBlockAccess aWorld, int aX, int aY, int aZ) {return T;}
	@Override public boolean isNormalCube(IBlockAccess aWorld, int aX, int aY, int aZ)  {return F;}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return F;}
	@Override public int damageDropped(int aMeta) {return aMeta;}
	@Override public int quantityDropped(Random par1Random) {return 1;}
	@Override public int getDamageValue(World aWorld, int aX, int aY, int aZ) {return aWorld.getBlockMetadata(aX, aY, aZ);}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public Item getItemDropped(int par1, Random aRandom, int par3) {return Item.getItemFromBlock(this);}
	@Override public Item getItem(World aWorld, int aX, int aY, int aZ) {return Item.getItemFromBlock(this);}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@SuppressWarnings("unchecked") @Override public void getSubBlocks(Item aItem, CreativeTabs aTab, @SuppressWarnings("rawtypes") List aList) {for (int i = 0; i < mMaxMeta; i++) aList.add(ST.make(aItem, 1, i));}
	@Override public boolean isSealed(World aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return F;}
	@Override public Block getBlock() {return this;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return mIcons[aMeta % mIcons.length].getIcon(0);}
	@Override public void onOxygenAdded(World aWorld, int aX, int aY, int aZ) {/**/}
	@Override public void onOxygenRemoved(World aWorld, int aX, int aY, int aZ) {if (!aWorld.isRemote && !WD.oxygen(aWorld, aX, aY, aZ)) {aWorld.setBlock(aX, aY, aZ, NB, 0, 3); return;}}
	
	@Override public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {/**/}
	@Override public float getExplosionResistance(int aMeta) {return 0;}
	@Override public boolean useGravity(int aMeta) {return F;}
	@Override public boolean doesWalkSpeed(short aMeta) {return F;}
	@Override public boolean doesPistonPush(short aMeta) {return F;}
	@Override public boolean canCreatureSpawn(int aMeta) {return F;}
	@Override public boolean isSealable(int aMeta, byte aSide) {return F;}
	@Override public int getItemStackLimit(ItemStack aStack) {return 64;}
	@Override public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {return aStack;}
	
	@Override public EnumPlantType getPlantType(IBlockAccess aWorld, int aX, int aY, int aZ) {return EnumPlantType.Plains;}
	@Override public Block getPlant(IBlockAccess aWorld, int aX, int aY, int aZ) {return this;}
	@Override public int getPlantMetadata(IBlockAccess aWorld, int aX, int aY, int aZ) {return aWorld.getBlockMetadata(aX, aY, aZ);}
	@Override public boolean canBlockStay(World aWorld, int aX, int aY, int aZ) {return WD.oxygen(aWorld, aX, aY, aZ) && aWorld.getBlock(aX, aY - 1, aZ).canSustainPlant(aWorld, aX, aY - 1, aZ, ForgeDirection.UP, Blocks.yellow_flower);}
	
	@Override
	public void checkAndDropBlock(World aWorld, int aX, int aY, int aZ) {
		if (canBlockStay(aWorld, aX, aY, aZ)) return;
		dropBlockAsItem(aWorld, aX, aY, aZ, aWorld.getBlockMetadata(aX, aY, aZ), 0);
		aWorld.setBlock(aX, aY, aZ, NB, 0, 2);
	}
	
	@Override public boolean onItemUseFirst(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	
	@Override
	public boolean onItemUse(ItemBlockBase aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack.stackSize == 0) return F;
		
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock == Blocks.snow_layer && (aWorld.getBlockMetadata(aX, aY, aZ) & 7) < 1) {
			aSide = SIDE_UP;
		} else if (tBlock != Blocks.vine && tBlock != Blocks.tallgrass && tBlock != Blocks.deadbush && !tBlock.isReplaceable(aWorld, aX, aY, aZ)) {
			aX += OFFSETS_X[aSide]; aY += OFFSETS_Y[aSide]; aZ += OFFSETS_Z[aSide];
		}
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || (aY == 255 && getMaterial().isSolid()) || !aWorld.canPlaceEntityOnSide(this, aX, aY, aZ, F, aSide, aPlayer, aStack)) return F;
		
		if (aItem.placeBlockAt(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, onBlockPlaced(aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ, aItem.getMetadata(aStack.getItemDamage())))) {
			aWorld.playSoundEffect(aX+0.5F, aY+0.5F, aZ+0.5F, stepSound.func_150496_b(), (stepSound.getVolume() + 1.0F) / 2.0F, stepSound.getPitch() * 0.8F);
			aStack.stackSize--;
		}
		return T;
	}
}
