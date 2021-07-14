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

package gregtech.api.interfaces.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

@Deprecated
/** Required to exist in GT6 because Immersive Engineering does not work otherwise. This File is likely not in the actual Builds of GT6! */
public interface IGregTechTileEntity {
	@Deprecated public int getErrorDisplayID();
	@Deprecated public void setErrorDisplayID(int aErrorID);
	@Deprecated public int getMetaTileID();
	@Deprecated public int setMetaTileID(short aID);
	@Deprecated public Object getMetaTileEntity();
	@Deprecated public void setMetaTileEntity(Object aMetaTileEntity);
	@Deprecated public void issueTextureUpdate();
	@Deprecated public void issueClientUpdate();
	@Deprecated public void doExplosion(long aExplosionEU);
	@Deprecated public void setOnFire();
	@Deprecated public void setToFire();
	@Deprecated public String setOwnerName(String aName);
	@Deprecated public String getOwnerName();
	@Deprecated public void setInitialValuesAsNBT(NBTTagCompound aNBT, short aID);
	@Deprecated public void onLeftclick(EntityPlayer aPlayer);
	@Deprecated public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float par1, float par2, float par3);
	@Deprecated public float getBlastResistance(byte aSide);
	@Deprecated public ArrayList<ItemStack> getDrops();
	@Deprecated public int getLightOpacity();
	@Deprecated public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB inputAABB, List<AxisAlignedBB> outputAABB, Entity collider);
	@Deprecated public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ);
	@Deprecated public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity collider);
}
