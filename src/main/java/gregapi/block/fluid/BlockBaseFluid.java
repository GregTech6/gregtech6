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

package gregapi.block.fluid;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.IBlock;
import gregapi.block.IBlockOnHeadInside;
import gregapi.block.MaterialGas;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class BlockBaseFluid extends BlockFluidFinite implements IBlock, IItemGT, IBlockOnHeadInside {
	public final String mNameInternal;
	public final int mFlammability;
	public final Fluid mFluid;
	
	public BlockBaseFluid(String aNameInternal, Fluid aFluid, int aFlammability) {
		this(aNameInternal, aFluid, aFlammability, aFluid.isGaseous()?MaterialGas.instance:aFluid.getTemperature()>500?Material.lava:Material.water);
	}
	
	public BlockBaseFluid(String aNameInternal, Fluid aFluid, int aFlammability, Material aMaterial) {
		super(aFluid, aMaterial);
		mFluid = aFluid;
		setResistance(30);
		mFlammability = aFlammability;
		setBlockName(mNameInternal = aNameInternal);
		ST.register(this, mNameInternal, ItemBlock.class);
		LH.add(getLocalizedName()+".name", getLocalizedName()); // WAILA is retarded...
		// Speaking of retarded, only allowing one type of Block per Fluid is retarded too! So I guess I gotta override all pre-existing Fluids with my Version to make sure shit works.
		UT.Reflection.setField(Fluid.class, aFluid, "block", this);
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		// Forge royally fucked up again. You check for MetaData FIRST and do the set Block to Air SECOND, like I demonstrate here!!!
		FluidStack rFluid = FL.make(getFluid(), (WD.meta(aWorld, aX, aY, aZ) + 1) * 125);
		if (aDoDrain) {
			aWorld.setBlockToAir(aX, aY, aZ);
			updateFluidBlocks(aWorld, aX, aY, aZ);
		}
		return rFluid;
	}
	
	public void updateFluidBlocks(World aWorld, int aX, int aY, int aZ) {
		for (int j = densityDir > 0 ? -1 : 0; j < (densityDir > 0 ? 1 : 2); j++) if (UT.Code.inside(0, aWorld.getHeight(), aY+j)) for (int i = -4; i < 5; i++) for (int k = -4; k < 5; k++) if (i != 0 || j != 0 || k != 0) {
			if (aWorld.getBlock(aX+i, aY+j, aZ+k) == this && aWorld.getBlockMetadata(aX+i, aY+j, aZ+k) > (j == 0 ? Math.abs(i)+Math.abs(j) : 0)) {
				aWorld.scheduleBlockUpdate(aX+i, aY+j, aZ+k, this, tickRate);
			}
		}
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		// Flammability checks.
		if (mFlammability > 0) for (byte tSide : ALL_SIDES_VALID) {
			Block tBlock = WD.block(aWorld, aX, aY, aZ, tSide, F);
			if (tBlock.getMaterial() == Material.lava || tBlock.getMaterial() == Material.fire) {
				WD.burn(aWorld, aX, aY, aZ, T, F);
				return;
			}
		}
		
		boolean changed = F;
		int tRemainingQuanta = WD.meta(aWorld, aX, aY, aZ)+1, oRemainingQuanta = tRemainingQuanta;
		
		tRemainingQuanta = tryToFlowVerticallyInto(aWorld, aX, aY, aZ, tRemainingQuanta);
		
		if (tRemainingQuanta < 1) {
			updateFluidBlocks(aWorld, aX, aY, aZ);
			return;
		}
		if (tRemainingQuanta != oRemainingQuanta) {
			changed = T;
			if (tRemainingQuanta == 1) {
				WD.setIfDiff(aWorld, aX, aY, aZ, this, tRemainingQuanta-1, 2);
				updateFluidBlocks(aWorld, aX, aY, aZ);
				return;
			}
		} else if (tRemainingQuanta == 1) {
			updateFluidBlocks(aWorld, aX, aY, aZ);
			return;
		}
		
		if (aWorld.blockExists(aX  , aY, aZ-1) && displaceIfPossible(aWorld, aX  , aY, aZ-1)) aWorld.setBlockToAir(aX  , aY, aZ-1);
		if (aWorld.blockExists(aX  , aY, aZ+1) && displaceIfPossible(aWorld, aX  , aY, aZ+1)) aWorld.setBlockToAir(aX  , aY, aZ+1);
		if (aWorld.blockExists(aX-1, aY, aZ  ) && displaceIfPossible(aWorld, aX-1, aY, aZ  )) aWorld.setBlockToAir(aX-1, aY, aZ  );
		if (aWorld.blockExists(aX+1, aY, aZ  ) && displaceIfPossible(aWorld, aX+1, aY, aZ  )) aWorld.setBlockToAir(aX+1, aY, aZ  );
		
		int tTotal = tRemainingQuanta, tCount = 1;
		
		int north = getQuantaValueBelow(aWorld, aX  , aY, aZ-1, tRemainingQuanta-1);
		int south = getQuantaValueBelow(aWorld, aX  , aY, aZ+1, tRemainingQuanta-1);
		int west  = getQuantaValueBelow(aWorld, aX-1, aY, aZ  , tRemainingQuanta-1);
		int east  = getQuantaValueBelow(aWorld, aX+1, aY, aZ  , tRemainingQuanta-1);
		
		if (north >= 0) {tCount++; tTotal += north;}
		if (south >= 0) {tCount++; tTotal += south;}
		if (west  >= 0) {tCount++; tTotal += west ;}
		if (east  >= 0) {tCount++; tTotal += east ;}
		
		if (tCount == 1) {
			if (changed) {
				WD.setIfDiff(aWorld, aX, aY, aZ, this, tRemainingQuanta-1, 2);
				updateFluidBlocks(aWorld, aX, aY, aZ);
			}
			return;
		}
		
		int tSpread = tTotal / tCount, tRemainder = tTotal % tCount;
		if (north >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew; --tRemainder;} tCount--;
			if (tNew != north) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX  , aY, aZ-1, this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX  , aY, aZ-1, this, tickRate);} else aWorld.setBlockToAir(aX  , aY, aZ-1);
		}
		if (south >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew; --tRemainder;} tCount--;
			if (tNew != south) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX  , aY, aZ+1, this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX  , aY, aZ+1, this, tickRate);} else aWorld.setBlockToAir(aX  , aY, aZ+1);
		}
		if (west >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew ; --tRemainder;} tCount--;
			if (tNew != west ) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX-1, aY, aZ  , this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX-1, aY, aZ  , this, tickRate);} else aWorld.setBlockToAir(aX-1, aY, aZ  );
		}
		if (east >= 0) {
			int tNew = tSpread;
			if (tRemainder == tCount || tRemainder > 1 && aRandom.nextInt(tCount - tRemainder) != 0) {++tNew ; --tRemainder;} tCount--;
			if (tNew != east ) if (tNew > 0) {if (WD.setIfDiff(aWorld, aX+1, aY, aZ  , this, tNew-1, 2)) aWorld.scheduleBlockUpdate(aX+1, aY, aZ  , this, tickRate);} else aWorld.setBlockToAir(aX+1, aY, aZ  );
		}
		WD.setIfDiff(aWorld, aX, aY, aZ, this, tRemainder > 0 ? tSpread : tSpread - 1, 2);
	}
	
	@Override
	public int tryToFlowVerticallyInto(World aWorld, int aX, int aY, int aZ, int aAmount) {
		if (aY <= 0 || aY+1 >= aWorld.getHeight()) return aWorld.setBlockToAir(aX, aY, aZ) ? 0 : aAmount;
		
		// First do the Water specific check.
		if (mLighterThanWater) {
			int tY = aY;
			while (++tY < aWorld.getHeight() && WD.anywater(aWorld, aX, tY, aZ));
			if (tY-1 > aY) {
				Block tBlock = aWorld.getBlock(aX, tY, aZ);
				if (tBlock == this) {
					int tAmount = 1 + aWorld.getBlockMetadata(aX, tY, aZ) + aAmount;
					if (tAmount > 16) {
						aWorld.setBlock(aX, tY, aZ, this, 16 - 1, 3);
						aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
						return tAmount - 16;
					}
					if (tAmount > 0) {
						aWorld.setBlock(aX, tY, aZ, this, tAmount - 1, 3);
						// Called by the Block Update caused by setBlockToAir
						// aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
						aWorld.setBlockToAir(aX, aY, aZ);
						return 0;
					}
					return aAmount;
				}
				if (WD.air(aWorld, aX, tY, aZ, tBlock) || displaceIfPossible(aWorld, aX, tY, aZ)) {
					aWorld.setBlock(aX, tY, aZ, this, aAmount - 1, 3);
					aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
					return 0;
				}
			}
		}
		
		// Compressed Fluid Blocks behave a little bit "jumpier" than normal ones. ;)
		if (aAmount > 8) {
			int tY = aY - densityDir;
			Block tBlock = aWorld.getBlock(aX, tY, aZ);
			
			// Swap with any finite Fluid Blocks "above" this one unless they are also compressed.
			if (tBlock instanceof BlockFluidFinite) {
				int tMeta = aWorld.getBlockMetadata(aX, tY, aZ);
				if (tMeta > 8) return aAmount;
				aWorld.setBlock(aX, aY, aZ, tBlock, tMeta, 3);
				aWorld.setBlock(aX, tY, aZ, this, aAmount - 1, 3);
				aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
				return 0;
			}
			// Swap with GT6 Water Blocks.
			if (!mLighterThanWater && WD.anywater(tBlock)) {
				aWorld.setBlock(aX, aY, aZ, tBlock, aWorld.getBlockMetadata(aX, tY, aZ), 3);
				aWorld.setBlock(aX, tY, aZ, this, aAmount - 1, 3);
				aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
				return 0;
			}
			// Lets just jump up! Make a Fountain!
			if (WD.air(aWorld, aX, tY, aZ, tBlock) || displaceIfPossible(aWorld, aX, tY, aZ)) {
				// The Block left behind should stay for a bit.
				aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 128 - aAmount * 4);
				// All but one Quanta will move up!
				aWorld.setBlock(aX, tY, aZ, this, aAmount - 2, 3);
				// Since it is a Jump, we will give it a fast reaction time!
				aWorld.scheduleBlockUpdate(aX, tY, aZ, this, 1);
				// Leaving a minimal Block at the original location to make it more Fountain like.
				return 1;
			}
		}
		
		int tY = aY + densityDir;
		Block tBlock = aWorld.getBlock(aX, tY, aZ);
		
		if (tBlock == this) {
			int tAmount = 1 + aWorld.getBlockMetadata(aX, tY, aZ) + aAmount;
			if (tAmount > 8) {
				aWorld.setBlock(aX, tY, aZ, this, 8 - 1, 3);
				aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
				return tAmount - 8;
			}
			if (tAmount > 0) {
				aWorld.setBlock(aX, tY, aZ, this, tAmount - 1, 3);
				// Called by the Block Update caused by setBlockToAir
				// aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
				aWorld.setBlockToAir(aX, aY, aZ);
				return 0;
			}
			return aAmount;
		}
		if (tBlock instanceof BlockFluidBase) {
			if (densityDir < 0 ? getDensity(aWorld, aX, tY, aZ) > density : getDensity(aWorld, aX, tY, aZ) < density) {
				aWorld.setBlock(aX, aY, aZ, tBlock, aWorld.getBlockMetadata(aX, tY, aZ), 3);
				aWorld.setBlock(aX, tY, aZ, this, aAmount - 1, 3);
				// And don't just cast the result of world.getBlock directly like Forge does.
				// Why the fuck do they call world.getBlock more than once for the Block below/above a Fluid...
				// Even worse I noticed that the Block Update caused by the second setBlock will schedule the update for the Block ANYWAYS!!!
				// aWorld.scheduleBlockUpdate(aX, aY, aZ, tBlock, ((BlockFluidBase)tBlock).tickRate(aWorld));
				aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
				return 0;
			}
			return aAmount;
		}
		if (WD.air(aWorld, aX, tY, aZ, tBlock) || displaceIfPossible(aWorld, aX, tY, aZ)) {
			aWorld.setBlock(aX, tY, aZ, this, aAmount - 1, 3);
			// Called by the Block Update caused by setBlockToAir
			// aWorld.scheduleBlockUpdate(aX, tY, aZ, this, tickRate);
			aWorld.setBlockToAir(aX, aY, aZ);
			return 0;
		}
		return aAmount;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess aWorld, int aX, int aY, int aZ, int aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return T;
		if (aBlock == this || aBlock.getMaterial() == Material.water || WD.visOpq(aBlock)) return F;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		if (tTileEntity instanceof ITileEntitySurface) return !((ITileEntitySurface)tTileEntity).isSurfaceOpaque(OPOS[aSide]);
		return T;
	}
	
	@Override public Block getBlock() {return this;}
	@Override public final String getUnlocalizedName() {return mFluid.getUnlocalizedName();}
	@Override public String getLocalizedName() {return LH.get(mFluid.getUnlocalizedName());}
	@Override public void registerBlockIcons(IIconRegister aIconRegister) {/**/}
	@Override public IIcon getIcon(int aSide, int aMeta) {return SIDES_VERTICAL[aSide]?mFluid.getStillIcon():mFluid.getFlowingIcon();}
	@Override @SideOnly(Side.CLIENT) public int getRenderColor(int aMeta) {return mFluid.getColor();}
	@Override @SideOnly(Side.CLIENT) public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {return mFluid.getColor();}
	@Override public int getRenderType() {return RendererBlockFluid.RENDER_ID;}
	@Override public int getRenderBlockPass() {return 1;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return mFlammability;}
	@Override public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aDirection) {return mFlammability;}
	@Override public boolean canDisplace(IBlockAccess aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.canDisplace(aWorld, aX, aY, aZ);}
	@Override public boolean displaceIfPossible(World aWorld, int aX, int aY, int aZ) {return !aWorld.getBlock(aX, aY, aZ).getMaterial().isLiquid() && super.displaceIfPossible(aWorld, aX, aY, aZ);}
	@Override public boolean canCollideCheck(int aMeta, boolean aFullHit) {return aFullHit && aMeta >= 7;}
	@Override public boolean getBlocksMovement(IBlockAccess aWorld, int aX, int aY, int aZ) {return mActLikeWeb || !mEffects.isEmpty();}
	@Override public boolean isNormalCube() {return F;}
	@Override public boolean isOpaqueCube() {return F;}
	@Override public boolean func_149730_j() {return F;}
	@Override public boolean getTickRandomly() {return F;}
	@Override public boolean renderAsNormalBlock() {return F;}
	@Override public boolean isAir(IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@Override public boolean isSideSolid(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {return F;}
	
	
	public boolean mLighterThanWater = F;
	public BlockBaseFluid setLighterThanWater() {
		mLighterThanWater = T;
		return this;
	}
	
	public boolean mActLikeWeb = F;
	public BlockBaseFluid setWeb() {
		mActLikeWeb = T;
		return this;
	}
	
	
	/** This Function has been named wrong. It should be onEntityOverlapWithBlock */
	@Override
	public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity aEntity) {
		if (mActLikeWeb) aEntity.setInWeb();
	}
	
	
	public List<int[]> mEffects = new ArrayListNoNulls<>();
	public BlockBaseFluid addEffect(int aEffectID, int aEffectDuration, int aEffectLevel) {
		mEffects.add(new int[] {aEffectID, aEffectDuration, aEffectLevel});
		return this;
	}
	@Override
	public void onHeadInside(EntityLivingBase aEntity, World aWorld, int aX, int aY, int aZ) {
		if (!mEffects.isEmpty() && (FL.gas(mFluid) ? !UT.Entities.isImmuneToBreathingGases(aEntity) : !UT.Entities.isWearingFullChemHazmat(aEntity))) {
			for (int[] tEffects : mEffects) aEntity.addPotionEffect(new PotionEffect(tEffects[0], tEffects[1], tEffects[2], F));
			if (getMaterial() != Material.water && SERVER_TIME % 20 == 0) aEntity.attackEntityFrom(DamageSource.drown, 2.0F);
		}
	}
}
