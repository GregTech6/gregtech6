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

package gregtech.blocks.fluids;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import gregapi.block.IBlock;
import gregapi.block.IBlockOnHeadInside;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.item.IItemGT;
import gregapi.render.RendererBlockFluid;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockWaterlike extends BlockFluidClassic implements IBlock, IItemGT, IBlockOnHeadInside {
	public final Fluid mFluid;
	
	public BlockWaterlike(String aName, Fluid aFluid) {
		super(aFluid, Material.water);
		mFluid = aFluid;
		setResistance(30);
		setBlockName(aName);
		ST.register(this, aName, ItemBlock.class);
		LH.add(getLocalizedName()+".name", getLocalizedName()); // WAILA is retarded...
		LH.add(getUnlocalizedName()+".name", getLocalizedName());
		LH.add(getUnlocalizedName(), getLocalizedName());
		setFluidStack(FL.make(aFluid, 1000));
		ST.hide(this);
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		if (aDoDrain) aWorld.setBlock(aX, aY, aZ, NB, 0, 2);
		return FL.make(getFluid(), 1000);
	}
	
	@Override
	public boolean canDrain(World aWorld, int aX, int aY, int aZ) {
		return WD.meta(aWorld, aX, aY, aZ) == 0;
	}
	
	public void updateFlow(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		int quantaRemaining = quantaPerBlock - WD.meta(aWorld, aX, aY, aZ);
		int expQuanta = -101;
		// check adjacent block levels if non-source
		if (quantaRemaining < quantaPerBlock) {
			if (aWorld.getBlock(aX  , aY-densityDir, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX-1, aY-densityDir, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX+1, aY-densityDir, aZ  ) instanceof BlockWaterlike ||
				aWorld.getBlock(aX  , aY-densityDir, aZ-1) instanceof BlockWaterlike ||
				aWorld.getBlock(aX  , aY-densityDir, aZ+1) instanceof BlockWaterlike) {
				expQuanta = quantaPerBlock - 1;
			} else {
				int maxQuanta = -100;
				maxQuanta = getLargerQuanta(aWorld, aX-1, aY, aZ  , maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX+1, aY, aZ  , maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX  , aY, aZ-1, maxQuanta);
				maxQuanta = getLargerQuanta(aWorld, aX  , aY, aZ+1, maxQuanta);
				expQuanta = maxQuanta - 1;
			}
			if (expQuanta != quantaRemaining) {
				quantaRemaining = expQuanta;
				if (expQuanta <= 0) {
					aWorld.setBlockToAir(aX, aY, aZ);
				} else {
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, quantaPerBlock - expQuanta, 3);
					aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate);
					aWorld.notifyBlocksOfNeighborChange(aX, aY, aZ, this);
				}
			}
		}
		// Here was an else Block that only caused huge amounts of Network Lag with no purpose. Forge, just what the fuck, setting Metadata from 0 to 0 and updating that "change" to Clients? There was no change that needed to be updated!
		
		
		if (canDisplace(aWorld, aX, aY+densityDir, aZ)) {
			if (displaceIfPossible(aWorld, aX, aY+densityDir, aZ)) aWorld.setBlock(aX, aY+densityDir, aZ, this, 1, 3);
			return;
		}
		
		int tFlowMeta  = (aWorld.getBlock(aX, aY-densityDir, aZ) instanceof BlockWaterlike ? 1 : quantaPerBlock - quantaRemaining + 1);
		if (tFlowMeta >= quantaPerBlock) return;
		
		if (aWorld.blockExists(aX  , aY, aZ-1) && displaceIfPossible(aWorld, aX  , aY, aZ-1)) aWorld.setBlock(aX  , aY, aZ-1, this, tFlowMeta, 3);
		if (aWorld.blockExists(aX  , aY, aZ+1) && displaceIfPossible(aWorld, aX  , aY, aZ+1)) aWorld.setBlock(aX  , aY, aZ+1, this, tFlowMeta, 3);
		if (aWorld.blockExists(aX-1, aY, aZ  ) && displaceIfPossible(aWorld, aX-1, aY, aZ  )) aWorld.setBlock(aX-1, aY, aZ  , this, tFlowMeta, 3);
		if (aWorld.blockExists(aX+1, aY, aZ  ) && displaceIfPossible(aWorld, aX+1, aY, aZ  )) aWorld.setBlock(aX+1, aY, aZ  , this, tFlowMeta, 3);
	}
	
	@Override
	public Vec3 getFlowVector(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Vec3 rVector = Vec3.createVectorHelper(0, 0, 0);
		int tDecay = quantaPerBlock - getQuantaValue(aWorld, aX, aY, aZ);
		for (byte tSide : ALL_SIDES_HORIZONTAL) {
			int tX = aX+OFFX[tSide], tZ = aZ+OFFZ[tSide];
			int tOtherDecay = quantaPerBlock - getQuantaValue(aWorld, tX, aY, tZ);
			if (tOtherDecay >= quantaPerBlock) {
				if (!aWorld.getBlock(tX, aY, tZ).getMaterial().blocksMovement()) {
					tOtherDecay = quantaPerBlock - getQuantaValue(aWorld, tX, aY-1, tZ);
					if (tOtherDecay >= 0) {
						int tPower = tOtherDecay - (tDecay - quantaPerBlock);
						rVector = rVector.addVector((tX - aX) * tPower, (aY - aY) * tPower, (tZ - aZ) * tPower);
					}
				}
			} else if (tOtherDecay >= 0) {
				int power = tOtherDecay - tDecay;
				rVector = rVector.addVector((tX - aX) * power, (aY - aY) * power, (tZ - aZ) * power);
			}
		}
		if (aWorld.getBlock(aX, aY+1, aZ) instanceof BlockWaterlike && (
			isBlockSolid(aWorld, aX  , aY  , aZ-1, SIDE_Z_NEG) ||
			isBlockSolid(aWorld, aX  , aY  , aZ+1, SIDE_Z_POS) ||
			isBlockSolid(aWorld, aX-1, aY  , aZ  , SIDE_X_NEG) ||
			isBlockSolid(aWorld, aX+1, aY  , aZ  , SIDE_X_POS) ||
			isBlockSolid(aWorld, aX  , aY+1, aZ-1, SIDE_Z_NEG) ||
			isBlockSolid(aWorld, aX  , aY+1, aZ+1, SIDE_Z_POS) ||
			isBlockSolid(aWorld, aX-1, aY+1, aZ  , SIDE_X_NEG) ||
			isBlockSolid(aWorld, aX+1, aY+1, aZ  , SIDE_X_POS))) {
			rVector = rVector.normalize().addVector(0, -6, 0);
		}
		return rVector.normalize();
	}
	
	@Override
	public int getQuantaValue(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return 0;
		if (aBlock instanceof BlockWaterlike) return quantaPerBlock - aWorld.getBlockMetadata(aX, aY, aZ);
		if (aBlock == Blocks.water || aBlock == Blocks.flowing_water) return 8-aWorld.getBlockMetadata(aX, aY, aZ);
		return -1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return T;
		if (aBlock.getMaterial() == Material.water || WD.visOpq(aBlock)) return F;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (tTileEntity instanceof ITileEntitySurface) return !((ITileEntitySurface)tTileEntity).isSurfaceOpaque(OPOS[aSide]);
		return T;
	}
	
	@Override public boolean isSourceBlock(IBlockAccess aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY, aZ) instanceof BlockWaterlike && aWorld.getBlockMetadata(aX, aY, aZ) == 0;}
	@Override public Block getBlock() {return this;}
	@Override public final String getUnlocalizedName() {return FL.name(mFluid, F);}
	@Override public String getLocalizedName() {return FL.name(mFluid, T);}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.water.getIcon(aSide, aMeta);}
	@Override public int getRenderType() {return RendererBlockFluid.RENDER_ID;}
	@Override public int getRenderBlockPass() {return 1;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return 0;}
	@Override public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return 0;}
	@Override public boolean canDisplace(IBlockAccess aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.canDisplace(aWorld, aX, aY, aZ);}
	@Override public boolean displaceIfPossible(World aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.displaceIfPossible(aWorld, aX, aY, aZ);}
	@Override public boolean canCollideCheck(int aMeta, boolean aFullHit) {return aFullHit && aMeta == 0;}
	@Override public boolean getBlocksMovement(IBlockAccess aWorld, int aX, int aY, int aZ) {return !mEffects.isEmpty();}
	@Override public boolean isNormalCube() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean func_149730_j() {return F;}
	@Override public boolean getTickRandomly() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isAir(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return F;}
	
	public BlockWaterlike addEffect(int aEffectID, int aEffectDuration, int aEffectLevel) {
		mEffects.add(new int[] {aEffectID, aEffectDuration, aEffectLevel});
		return this;
	}
	
	public List<int[]> mEffects = new ArrayListNoNulls<>();
	
	@Override
	public void onHeadInside(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if (!mEffects.isEmpty() && (FL.gas(mFluid) ? !UT.Entities.isImmuneToBreathingGases(aEntity) : !UT.Entities.isWearingFullChemHazmat(aEntity))) {
			for (int[] tEffects : mEffects) aEntity.addPotionEffect(new PotionEffect(tEffects[0], tEffects[1], tEffects[2], F));
			if (getMaterial() != Material.water && SERVER_TIME % 20 == 0) aEntity.attackEntityFrom(DamageSource.drown, 2.0F);
		}
	}
}
