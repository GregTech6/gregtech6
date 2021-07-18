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

package gregtech.tileentity.inventories;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.UUID;

import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.inventories.MultiTileEntitySafe;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySafeMechanical extends MultiTileEntitySafe {
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_OWNER) && !OWNERSHIP_RESET) mOwner = UUID.fromString(aNBT.getString(NBT_OWNER));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mOwner != null) aNBT.setString(NBT_OWNER, mOwner.toString());
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.OWNER_CONTROLLED));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aPlayer != null && aPlayer.isSneaking()) allowInteraction(aPlayer);
		return super.onPlaced(aStack, aPlayer, aMTEContainer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean allowInteraction(Entity aEntity) {
		if (mOwner == null && aEntity != null && !OWNERSHIP_RESET) {
			mOwner = aEntity.getUniqueID();
			updateClientData();
		}
		return super.allowInteraction(aEntity);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData()) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setDirectionData(aData[3]);
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlays[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/safes/mechanical/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.safe.mechanical";}
}
