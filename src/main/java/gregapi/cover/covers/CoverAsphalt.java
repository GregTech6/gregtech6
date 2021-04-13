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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.data.CS.SFX;
import gregapi.render.ITexture;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverAsphalt extends CoverTextureSimple {
	public CoverAsphalt(ITexture aTexture) {
		super(aTexture, SFX.MC_DIG_ROCK);
	}
	
	@Override
	public boolean onWalkOver(byte aCoverSide, CoverData aData, Entity aEntity) {
		if ((aEntity.motionX != 0 || aEntity.motionZ != 0) && !aEntity.isInWater() && !aEntity.isSneaking()) {aEntity.motionX *= 1.3; aEntity.motionZ *= 1.3;}
		return T;
	}
}
