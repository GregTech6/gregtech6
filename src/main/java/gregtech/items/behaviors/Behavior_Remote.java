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

package gregtech.items.behaviors;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.tileentity.ITileEntityRemoteActivateable;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

public class Behavior_Remote extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Remote();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.isSneaking() || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		NBTTagCompound aNBT = UT.NBT.getNBT(aStack);
		ArrayListNoNulls<ChunkCoordinates> tList = getCoords(aNBT, aWorld.provider.dimensionId);
		ChunkCoordinates tCoords = new ChunkCoordinates(aX, aY, aZ);
		if (tList.contains(tCoords)) {
			UT.Entities.sendchat(aPlayer, "Coordinates removed!");
			UT.Sounds.send(SFX.GT_BEEP, 0.5F, 1.0F, aWorld, tCoords);
			tList.remove(tCoords);
		} else if (tList.size() >= 64) {
			UT.Entities.sendchat(aPlayer, "Cant hold more than 64 Coordinates per Dimension!");
			UT.Sounds.send(SFX.GT_BEEP, 0.5F, 0.5F, aWorld, tCoords);
		} else {
			TileEntity tTileEntity = WD.te(aWorld, tCoords, F);
			if (tTileEntity instanceof ITileEntityRemoteActivateable) {
				UT.Entities.sendchat(aPlayer, "Coordinates added!");
				UT.Sounds.send(SFX.GT_BEEP, 0.5F, 1.0F, aWorld, tCoords);
				tList.add(tCoords);
			} else {
				UT.Entities.sendchat(aPlayer, "This cannot be added!");
				UT.Sounds.send(SFX.GT_BEEP, 0.5F, 0.5F, aWorld, tCoords);
			}
		}
		setCoords(aNBT, aWorld.provider.dimensionId, tList);
		UT.NBT.set(aStack, aNBT);
		return T;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (aWorld.isRemote || aPlayer.isSneaking() || !aStack.hasTagCompound()) return aStack;
		ArrayListNoNulls<ChunkCoordinates> tToBeKept = new ArrayListNoNulls<>();
		for (ChunkCoordinates tCoords : getCoords(aStack.getTagCompound(), aWorld.provider.dimensionId)) {
			if (Math.abs(tCoords.posX - aPlayer.posX) <= 128 && Math.abs(tCoords.posY - aPlayer.posY) <= 128 && Math.abs(tCoords.posZ - aPlayer.posZ) <= 128) {
				TileEntity tTileEntity = WD.te(aWorld, tCoords, F);
				if (tTileEntity instanceof ITileEntityRemoteActivateable && ((ITileEntityRemoteActivateable)tTileEntity).remoteActivate()) tToBeKept.add(tCoords);
			} else {
				tToBeKept.add(tCoords);
			}
		}
		setCoords(aStack.getTagCompound(), aWorld.provider.dimensionId, tToBeKept);
		UT.Sounds.send(SFX.MC_CLICK, aPlayer);
		return aStack;
	}
	
	public static boolean addCoords(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		NBTTagCompound aNBT = UT.NBT.getNBT(aStack);
		ArrayListNoNulls<ChunkCoordinates> tList = getCoords(aNBT, aWorld.provider.dimensionId);
		if (tList.size() >= 64) return F;
		ChunkCoordinates tCoords = new ChunkCoordinates(aX, aY, aZ);
		if (tList.contains(tCoords)) return T;
		TileEntity tTileEntity = WD.te(aWorld, tCoords, F);
		if (tTileEntity instanceof ITileEntityRemoteActivateable) {
			UT.Sounds.send(SFX.GT_BEEP, 0.5F, 1.0F, aWorld, tCoords);
			tList.add(tCoords);
			setCoords(aNBT, aWorld.provider.dimensionId, tList);
			UT.NBT.set(aStack, aNBT);
			return T;
		}
		UT.Sounds.send(SFX.GT_BEEP, 0.5F, 0.5F, aWorld, tCoords);
		return F;
	}
	
	public static ArrayListNoNulls<ChunkCoordinates> getCoords(NBTTagCompound aNBT, int aDimension) {
		ArrayListNoNulls<ChunkCoordinates> rList = new ArrayListNoNulls<>();
		if (aNBT == null) return rList;
		NBTTagCompound tNBT = aNBT.getCompoundTag("gt.remote.dim."+aDimension);
		if (tNBT.hasNoTags()) return rList;
		int i = -1; while (tNBT.hasKey("c"+(++i))) {
			rList.add(new ChunkCoordinates(tNBT.getInteger("x"+i), tNBT.getInteger("y"+i), tNBT.getInteger("z"+i)));
		}
		return rList;
	}
	
	public static void setCoords(NBTTagCompound aNBT, int aDimension, ArrayListNoNulls<ChunkCoordinates> aList) {
		if (aList.isEmpty()) {
			aNBT.removeTag("gt.remote.dim."+aDimension);
		} else {
			NBTTagCompound tNBT = UT.NBT.make();
			for (int i = 0, j = aList.size(); i < j; i++) {
				ChunkCoordinates tCoords = aList.get(i);
				UT.NBT.setBoolean(tNBT, "c"+i, T);
				UT.NBT.setNumber (tNBT, "x"+i, tCoords.posX);
				UT.NBT.setNumber (tNBT, "y"+i, tCoords.posY);
				UT.NBT.setNumber (tNBT, "z"+i, tCoords.posZ);
			}
			aNBT.setTag("gt.remote.dim."+aDimension, tNBT);
		}
	}
	
	static {
		LH.add("gt.behaviour.remote", "Activates up to 64 Blocks within a Range of 128m");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.remote"));
		return aList;
	}
}
