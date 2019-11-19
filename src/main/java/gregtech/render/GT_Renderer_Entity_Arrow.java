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

package gregtech.render;

import static gregapi.data.CS.*;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

public class GT_Renderer_Entity_Arrow extends RenderArrow {
	private final ResourceLocation mTexture;
	
	public GT_Renderer_Entity_Arrow(Class<? extends Entity> aArrowClass, String aTextureName) {
		mTexture = new ResourceLocation(RES_PATH_ENTITY+aTextureName+".png");
		RenderingRegistry.registerEntityRenderingHandler(aArrowClass, this);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityArrow p_110775_1_) {
		return mTexture;
	}
}
