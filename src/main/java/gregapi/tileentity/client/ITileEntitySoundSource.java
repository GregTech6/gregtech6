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

package gregapi.tileentity.client;

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.ITileEntityUnloadable;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.util.ResourceLocation;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntitySoundSource extends ITileEntityUnloadable {
	@SideOnly(Side.CLIENT)
	public void startSound();
	
	@SideOnly(Side.CLIENT)
	public void stopSound();
	
	@SideOnly(Side.CLIENT)
	public static class SoundSourceTileEntity implements ITickableSound {
		public boolean mRunning = F;
		public float mSoundStrength, mSoundModulation;
		public final IHasWorldAndCoords mTileEntity;
		public final ResourceLocation mResource;
		
		public SoundSourceTileEntity(IHasWorldAndCoords aTileEntity, boolean aRunning, String aSoundName, float aSoundStrength, float aSoundModulation) {
			mTileEntity = aTileEntity;
			mResource = new ResourceLocation(aSoundName);
			mSoundStrength = aSoundStrength;
			mSoundModulation = aSoundModulation;
		}
		
		@Override public ResourceLocation getPositionedSoundLocation() {return mResource;}
		@Override public boolean canRepeat() {return mRunning;}
		@Override public boolean isDonePlaying() {return !mRunning;}
		@Override public int getRepeatDelay() {return 1;}
		@Override public float getVolume() {return mSoundStrength;}
		@Override public float getPitch() {return mSoundModulation;}
		@Override public float getXPosF() {return mTileEntity == null ? 0 : mTileEntity.getX()+0.5F;}
		@Override public float getYPosF() {return mTileEntity == null ? 0 : mTileEntity.getY()+0.5F;}
		@Override public float getZPosF() {return mTileEntity == null ? 0 : mTileEntity.getZ()+0.5F;}
		@Override public AttenuationType getAttenuationType() {return ISound.AttenuationType.LINEAR;}
		@Override public void update() {/**/}
	}
}
