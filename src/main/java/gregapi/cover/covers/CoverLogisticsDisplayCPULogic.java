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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsDisplayCPULogic extends AbstractCoverAttachmentLogisticsDisplay {
	public static final CoverLogisticsDisplayCPULogic INSTANCE = new CoverLogisticsDisplayCPULogic();
	
	public CoverLogisticsDisplayCPULogic() {}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[(int)UT.Code.bind_(0, 10, aData.mVisuals[aSide])]);}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/0", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/1", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/2", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/3", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/4", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/5", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/6", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/7", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/8", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/9", T)
		, BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/10", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/logistics/display/cpu_logic/underlay");
}
