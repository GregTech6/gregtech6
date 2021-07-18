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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockFoamable;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.SFX;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.tileentity.ITileEntityFoamable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.BlockCFoamFresh;
import gregtech.tileentity.misc.MultiTileEntityCFoam;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Spray_Foam extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses;
	private final byte mColor;
	private final boolean mOwned;
	
	public Behavior_Spray_Foam(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses, byte aColor, boolean aOwned) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses * 10;
		mOwned = aOwned;
		mColor = UT.Code.bind4(aColor);
		LH.add("gt.behaviour.foamspray."+mColor+".tooltip", "Can place " + DYE_NAMES[mColor] + " C-Foam");
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (aPlayer.isSneaking()) switchMode(aStack, aPlayer);
		return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aStack.stackSize != 1 || aPlayer.isSneaking() || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		
		boolean rOutput = F;
		
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		long tUses = tNBT.getLong("gt.remaining");
		
		if (ST.equal(aStack, mFull, T)) {
			aStack.func_150996_a(mUsed.getItem());
			ST.meta_(aStack, ST.meta_(mUsed));
			tUses = mUses;
		}
		if (ST.equal(aStack, mUsed, T)) {
			long tFoamed = foam(aWorld, aX, aY, aZ, aSide, UT.Entities.hasInfiniteItems(aPlayer)?mUses:tUses, aPlayer, aStack);
			if (tFoamed > 0) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, 1.0F, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses -= tFoamed;
				rOutput = T;
			}
		}
		
		if (tUses <= 0) {
			tUses = 0;
			tNBT.removeTag(NBT_MODE);
			if (mEmpty == null) {
				aStack.stackSize--;
			} else {
				aStack.func_150996_a(mEmpty.getItem());
				ST.meta_(aStack, ST.meta_(mEmpty));
			}
		}
		
		UT.NBT.setNumber(tNBT, "gt.remaining", tUses);
		UT.NBT.set(aStack, tNBT);
		
		return rOutput;
	}
	
	public long foam(World aWorld, int aX, int aY, int aZ, byte aSide, long aUses, EntityPlayer aPlayer, ItemStack aStack) {
		if (aUses < 1) return 0;
		long rUses = 0;
		
		DelegatorTileEntity<TileEntity> aDelegator = WD.te(aWorld, aX, aY, aZ, aSide, T);
		if (aDelegator.mTileEntity instanceof ITileEntityFoamable && !((ITileEntityFoamable)aDelegator.mTileEntity).hasFoam(aDelegator.mSideOfTileEntity)) return ((ITileEntityFoamable)aDelegator.mTileEntity).applyFoam(aDelegator.mSideOfTileEntity, aPlayer, DYES[mColor], mColor, mOwned) ? 10 : 0;
		Block aBlock = aDelegator.getBlock(); aWorld = aDelegator.mWorld; aX = aDelegator.mX; aY = aDelegator.mY; aZ = aDelegator.mZ;
		if (aBlock instanceof IBlockFoamable && !((IBlockFoamable)aBlock).hasFoam(aWorld, aX, aY, aZ, aDelegator.mSideOfTileEntity)) return ((IBlockFoamable)aBlock).applyFoam(aWorld, aX, aY, aZ, aDelegator.mSideOfTileEntity, DYES[mColor], mColor) ? 10 : 0;
		
		try {
			if (UT.Reflection.getClassName(aDelegator.mTileEntity).startsWith("TileEntityCable") && UT.Reflection.getPublicField(aDelegator.mTileEntity, "foamed").getByte(aDelegator.mTileEntity) == 0) {
				if (aUses >= 10) {
					UT.Reflection.callPublicMethod(aDelegator.mTileEntity, "changeFoam", (byte)1);
					return 10;
				}
				return 0;
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		
		if (IL.IC2_Scaffold     .equal(aBlock)) return aUses >= 10 && MultiTileEntityCFoam.setBlock(aWorld, aX, aY, aZ, aDelegator.mSideOfTileEntity, aPlayer, aStack, DYES[mColor], mOwned) ? 10 : 0;
		if (IL.IC2_Scaffold_Iron.equal(aBlock)) return aUses >= 10 && aWorld.setBlock(aX, aY, aZ, IL.IC2_Foam_Reinforced.block(), 0, 3) ? 10 : 0;
		
		aX += OFFX[aSide]; aY += OFFY[aSide]; aZ += OFFZ[aSide];
		
		if (BlocksGT.CFoamFresh != null) {
			byte tSide = UT.Code.getSideForPlayerPlacing(aPlayer);
			switch ((int)getMode(aStack)) {
			case 0:
				if (aUses >= 10 && WD.air(aWorld, aX, aY, aZ) && (mOwned?MultiTileEntityCFoam.setBlock(aWorld, aX, aY, aZ, aSide, aPlayer, aStack, DYES[mColor], mOwned):aWorld.setBlock(aX, aY, aZ, BlocksGT.CFoamFresh, mColor, 3))) {aUses-=10; rUses+=10;}
				return rUses;
			case 1:
				for (byte i = 0; i < 4; i++) {
					if (aUses >= 10 && WD.air(aWorld, aX, aY, aZ) && (mOwned?MultiTileEntityCFoam.setBlock(aWorld, aX, aY, aZ, aSide, aPlayer, aStack, DYES[mColor], mOwned):aWorld.setBlock(aX, aY, aZ, BlocksGT.CFoamFresh, mColor, 3))) {aUses-=10; rUses+=10;} else break;
					aX -= OFFX[tSide]; aY -= OFFY[tSide]; aZ -= OFFZ[tSide];
				}
				return rUses;
			case 2:
				aX -= (SIDES_AXIS_X[tSide] ? 0 : 1);
				aY -= (SIDES_AXIS_Y[tSide] ? 0 : 1);
				aZ -= (SIDES_AXIS_Z[tSide] ? 0 : 1);
				
				for (byte i = 0; i < 3; i++) for (byte j = 0; j < 3; j++) {
					if (aUses >= 10) {
						if (WD.air(aWorld, aX + (SIDES_AXIS_X[tSide]?0:i), aY + (SIDES_AXIS_X[tSide]?i:0) + (SIDES_AXIS_Z[tSide]?j:0), aZ + (SIDES_AXIS_Z[tSide]?0:j))) {
							if (mOwned?MultiTileEntityCFoam.setBlock(aWorld, aX + (SIDES_AXIS_X[tSide]?0:i), aY + (SIDES_AXIS_X[tSide]?i:0) + (SIDES_AXIS_Z[tSide]?j:0), aZ + (SIDES_AXIS_Z[tSide]?0:j), aSide, aPlayer, aStack, DYES[mColor], mOwned):aWorld.setBlock(aX + (SIDES_AXIS_X[tSide]?0:i), aY + (SIDES_AXIS_X[tSide]?i:0) + (SIDES_AXIS_Z[tSide]?j:0), aZ + (SIDES_AXIS_Z[tSide]?0:j), BlocksGT.CFoamFresh, mColor, 3)) {aUses-=10; rUses+=10;}
						}
					} else break;
				}
				return rUses;
			case 3:
				if (aUses >= 5 && WD.air(aWorld, aX, aY, aZ) && aWorld.setBlock(aX, aY, aZ, ((BlockCFoamFresh)BlocksGT.CFoamFresh).mSlabs[OPOS[aSide]], mColor, 3)) {aUses-=5; rUses+=5;}
				return rUses;
			case 4:
				aX -= (SIDES_AXIS_X[tSide] ? 0 : 1);
				aY -= (SIDES_AXIS_Y[tSide] ? 0 : 1);
				aZ -= (SIDES_AXIS_Z[tSide] ? 0 : 1);
				
				for (byte i = 0; i < 3; i++) for (byte j = 0; j < 3; j++) {
					if (aUses >= 5) {
						if (WD.air(aWorld, aX + (SIDES_AXIS_X[tSide]?0:i), aY + (SIDES_AXIS_X[tSide]?i:0) + (SIDES_AXIS_Z[tSide]?j:0), aZ + (SIDES_AXIS_Z[tSide]?0:j))) {
							if (aWorld.setBlock(aX + (SIDES_AXIS_X[tSide]?0:i), aY + (SIDES_AXIS_X[tSide]?i:0) + (SIDES_AXIS_Z[tSide]?j:0), aZ + (SIDES_AXIS_Z[tSide]?0:j), ((BlockCFoamFresh)BlocksGT.CFoamFresh).mSlabs[OPOS[tSide]], mColor, 3)) {aUses-=5; rUses+=5;}
						}
					} else break;
				}
				return rUses;
			}
		}
		return rUses;
	}
	
	public void setMode(ItemStack aStack, long aMode) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT == null) aNBT = UT.NBT.make();
		UT.NBT.set(aStack, UT.NBT.setNumber(aNBT, NBT_MODE, aMode));
	}
	
	public long getMode(ItemStack aStack) {
		return UT.NBT.getNBT(aStack).getLong(NBT_MODE);
	}
	
	public void switchMode(ItemStack aStack, EntityPlayer aPlayer) {
		setMode(aStack, (getMode(aStack) + 1) % (mOwned?3:5));
		switch ((int)getMode(aStack)) {
		case 0: UT.Entities.sendchat(aPlayer, "Single Block Mode"); break;
		case 1: UT.Entities.sendchat(aPlayer, "4m Line Mode"); break;
		case 2: UT.Entities.sendchat(aPlayer, "3mx3m Area Mode"); break;
		case 3: UT.Entities.sendchat(aPlayer, "Single Slab Mode"); break;
		case 4: UT.Entities.sendchat(aPlayer, "3mx3m Slab Mode"); break;
		}
	}
	
	static {
		LH.add("gt.behaviour.foamspray.uses", "Remaining Uses:");
		LH.add("gt.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.foamspray."+mColor+".tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt.remaining"));
		aList.add(LH.get("gt.behaviour.foamspray.uses") + " " + (tRemaining / 10) + "." + (tRemaining % 10));
		aList.add(LH.get("gt.behaviour.unstackable"));
		return aList;
	}
}
