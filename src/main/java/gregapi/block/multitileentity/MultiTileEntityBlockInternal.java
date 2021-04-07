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

package gregapi.block.multitileentity;

import static gregapi.data.CS.*;

import gregapi.block.IBlock;
import gregapi.block.IBlockPlacable;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_HasMultiBlockMachineRelevantData;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_RegisterIcons;
import gregapi.item.IItemGT;
import gregapi.render.IRenderedBlock;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.ITexture;
import gregapi.render.RendererBlockTextured;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBlockInternal extends Block implements IBlock, IItemGT, IRenderedBlock, IBlockPlacable {
	public MultiTileEntityRegistry mMultiTileEntityRegistry;
	
	public MultiTileEntityBlockInternal() {
		super(Material.anvil);
	}
	
	@Override public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack) {return null;}
	@Override public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ) {return null;}
	@Override public boolean setBlockBounds(int aRenderPass, ItemStack aStack) {return F;}
	@Override public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return F;}
	@Override public int getRenderPasses(ItemStack aStack) {return 0;}
	@Override public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return 0;}
	@Override public boolean usesRenderPass(int aRenderPass, ItemStack aStack) {return T;}
	@Override public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered) {return T;}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return null;
	}
	
	@Override
	public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {
		TileEntity tTileEntity = mMultiTileEntityRegistry.getNewTileEntity(aStack);
		return tTileEntity instanceof IRenderedBlockObject ? (IRenderedBlockObject)tTileEntity : null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister aIconRegister) {
		for (MultiTileEntityClassContainer tClassContainer : mMultiTileEntityRegistry.mRegistry.values()) if (tClassContainer.mCanonicalTileEntity instanceof IMTE_RegisterIcons) ((IMTE_RegisterIcons)tClassContainer.mCanonicalTileEntity).registerIcons(aIconRegister);
	}
	
	@Override public final int getRenderBlockPass() {return ITexture.Util.MC_ALPHA_BLENDING?1:0;}
	@Override public final int getRenderType() {return RendererBlockTextured.INSTANCE==null?super.getRenderType():RendererBlockTextured.INSTANCE.mRenderID;}
	@Override public final Block getBlock() {return this;}
	@Override public final String getUnlocalizedName() {return mMultiTileEntityRegistry.mNameInternal;}
	@Override public final String getLocalizedName() {return StatCollector.translateToLocal(mMultiTileEntityRegistry.mNameInternal + ".name");}
	
	@Override
	public boolean placeBlock(World aWorld, int aX, int aY, int aZ, byte aSide, short aMetaData, NBTTagCompound aNBT, boolean aCauseBlockUpdates, boolean aForcePlacement) {
		MultiTileEntityContainer aMTEContainer = mMultiTileEntityRegistry.getNewTileEntityContainer(aWorld, aX, aY, aZ, aMetaData, aNBT);
		if (aMTEContainer == null) return F;
		
		Block tReplacedBlock = aWorld.getBlock(aX, aY, aZ);
		
		
		// That is some complicated Bullshit I have to do to make my MTEs work right.
		// Set Block with reverse MetaData first.
		aWorld.setBlock(aX, aY, aZ, aMTEContainer.mBlock, 15-aMTEContainer.mBlockMetaData, 2);
		// Make sure the Block has been set, yes I know setBlock has a true/false return value, but guess what, it is not reliable in 0.0001% of cases!
		if (aWorld.getBlock(aX, aY, aZ) != aMTEContainer.mBlock) {aWorld.setBlock(aX, aY, aZ, NB, 0, 0); return F;}
		// TileEntity should not refresh yet!
		((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(F);
		// Fake-Set the TileEntity first, bypassing a lot of checks.
		WD.te (aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, F);
		// Now set the Block with the REAL MetaData.
		WD.set(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, 0, F);
		// When the TileEntity is set now it SHOULD refresh!
		((IMultiTileEntity)aMTEContainer.mTileEntity).setShouldRefresh(T);
		// But make sure again that the Block we have set was actually set properly, because 0.0001%!
		if (aWorld.getBlock(aX, aY, aZ) != aMTEContainer.mBlock) {aWorld.setBlock(aX, aY, aZ, NB, 0, 0); return F;}
		// And finally properly set the TileEntity for real!
		WD.te (aWorld, aX, aY, aZ, aMTEContainer.mTileEntity, aCauseBlockUpdates);
		// Yep, all this just to set one Block and its TileEntity properly...
		
		
		try {
			if (aMTEContainer.mTileEntity instanceof IMTE_HasMultiBlockMachineRelevantData) {
				if (((IMTE_HasMultiBlockMachineRelevantData)aMTEContainer.mTileEntity).hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(aWorld, aX, aY, aZ, aMTEContainer.mBlock, aMTEContainer.mBlockMetaData, F);
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			if (!aWorld.isRemote && aCauseBlockUpdates) {
				aWorld.notifyBlockChange(aX, aY, aZ, tReplacedBlock);
				aWorld.func_147453_f(aX, aY, aZ, aMTEContainer.mBlock);
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			if (aMTEContainer.mTileEntity instanceof ITileEntity) {
				((ITileEntity)aMTEContainer.mTileEntity).onTileEntityPlaced();
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			aWorld.func_147451_t(aX, aY, aZ);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		return T;
	}
}
