/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.data;

import gregapi.GT_API;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import static gregapi.data.CS.*;

/** 
 * @author Gregorius Techneticies
 * 
 * Block Icons
 */
public class BI {
	/** Characters */
	public static IIconContainer 
	  CHAR_0 = new Icon("overlays/characters/0")
	, CHAR_1 = new Icon("overlays/characters/1")
	, CHAR_2 = new Icon("overlays/characters/2")
	, CHAR_3 = new Icon("overlays/characters/3")
	, CHAR_4 = new Icon("overlays/characters/4")
	, CHAR_5 = new Icon("overlays/characters/5")
	, CHAR_6 = new Icon("overlays/characters/6")
	, CHAR_7 = new Icon("overlays/characters/7")
	, CHAR_8 = new Icon("overlays/characters/8")
	, CHAR_9 = new Icon("overlays/characters/9")
	
	, CHAR_A = new Icon("overlays/characters/a")
	, CHAR_B = new Icon("overlays/characters/b")
	, CHAR_C = new Icon("overlays/characters/c")
	, CHAR_D = new Icon("overlays/characters/d")
	, CHAR_E = new Icon("overlays/characters/e")
	, CHAR_F = new Icon("overlays/characters/f")
	
	, CHAR_HEX = new Icon("overlays/characters/hex")
	
	, CHAR_HEX_0 = new Icon("overlays/characters/0x0")
	, CHAR_HEX_1 = new Icon("overlays/characters/0x1")
	, CHAR_HEX_2 = new Icon("overlays/characters/0x2")
	, CHAR_HEX_3 = new Icon("overlays/characters/0x3")
	, CHAR_HEX_4 = new Icon("overlays/characters/0x4")
	, CHAR_HEX_5 = new Icon("overlays/characters/0x5")
	, CHAR_HEX_6 = new Icon("overlays/characters/0x6")
	, CHAR_HEX_7 = new Icon("overlays/characters/0x7")
	, CHAR_HEX_8 = new Icon("overlays/characters/0x8")
	, CHAR_HEX_9 = new Icon("overlays/characters/0x9")
	
	, CHAR_HEX_A = new Icon("overlays/characters/0xa")
	, CHAR_HEX_B = new Icon("overlays/characters/0xb")
	, CHAR_HEX_C = new Icon("overlays/characters/0xc")
	, CHAR_HEX_D = new Icon("overlays/characters/0xd")
	, CHAR_HEX_E = new Icon("overlays/characters/0xe")
	, CHAR_HEX_F = new Icon("overlays/characters/0xf")
	
	, CHAR_DIVIDE = new Icon("overlays/characters/divide")
	, CHAR_MULTIPLY = new Icon("overlays/characters/multiply")
	, CHAR_PLUS = new Icon("overlays/characters/plus")
	, CHAR_MINUS = new Icon("overlays/characters/minus")
	, CHAR_MOD = new Icon("overlays/characters/mod")
	, CHAR_GREATER = new Icon("overlays/characters/greater")
	, CHAR_SMALLER = new Icon("overlays/characters/smaller")
	, CHAR_EQUAL = new Icon("overlays/characters/equal")
	
	, CHAR_UP = new Icon("overlays/characters/up")
	, CHAR_UP_RIGHT = new Icon("overlays/characters/upright")
	, CHAR_RIGHT = new Icon("overlays/characters/right")
	, CHAR_DOWN_RIGHT = new Icon("overlays/characters/downright")
	, CHAR_DOWN = new Icon("overlays/characters/down")
	, CHAR_DOWN_LEFT = new Icon("overlays/characters/downleft")
	, CHAR_LEFT = new Icon("overlays/characters/left")
	, CHAR_UP_LEFT = new Icon("overlays/characters/upleft")
	
	, CHAR_SLASH = new Icon("overlays/characters/slash")
	, CHAR_PERCENT = new Icon("overlays/characters/percent")
	
	, CHAR_METER = new Icon("overlays/characters/meter")
	, CHAR_METER_3 = new Icon("overlays/characters/cubicmeter")
	, CHAR_DECAMETER_3 = new Icon("overlays/characters/cubicdecameter")
	, CHAR_KELVIN = new Icon("overlays/characters/kelvin")
	, CHAR_LITER = new Icon("overlays/characters/liter")
	, CHAR_LUMIN = new Icon("overlays/characters/lumin")
	, CHAR_CLOCK = new Icon("overlays/characters/clock")
	, CHAR_GIBBL = new Icon("overlays/characters/gibbl")
	, CHAR_GRAMM = new Icon("overlays/characters/gramm")
	, CHAR_KILOGRAMM = new Icon("overlays/characters/kilogramm")
	, CHAR_TON = new Icon("overlays/characters/ton")
	, CHAR_KILOTON = new Icon("overlays/characters/kiloton")
	, CHAR_EU = new Icon("overlays/characters/eu")
	, CHAR_LU = new Icon("overlays/characters/lu")
	, CHAR_RU = new Icon("overlays/characters/ru")
	, CHAR_NEUTRON = new Icon("overlays/characters/neutron")
	
	, CHAR_SCALE = new Icon("overlays/characters/scale")
	, CHAR_GREG = new Icon("overlays/characters/greg")
	, CHAR_NEI = new Icon("overlays/characters/nei")
	
	, CHARACTERS_DIR[] = {CHAR_UP, CHAR_UP_RIGHT, CHAR_RIGHT, CHAR_DOWN_RIGHT, CHAR_DOWN, CHAR_DOWN_LEFT, CHAR_LEFT, CHAR_UP_LEFT}
	, CHARACTERS_HEX[] = {CHAR_HEX_0, CHAR_HEX_1, CHAR_HEX_2, CHAR_HEX_3, CHAR_HEX_4, CHAR_HEX_5, CHAR_HEX_6, CHAR_HEX_7, CHAR_HEX_8, CHAR_HEX_9, CHAR_HEX_A, CHAR_HEX_B, CHAR_HEX_C, CHAR_HEX_D, CHAR_HEX_E, CHAR_HEX_F}
	, CHARACTERS_DEC[] = {CHAR_0, CHAR_1, CHAR_2, CHAR_3, CHAR_4, CHAR_5, CHAR_6, CHAR_7, CHAR_8, CHAR_9, CHAR_A, CHAR_B, CHAR_C, CHAR_D, CHAR_E, CHAR_F}
	, CHARACTERS_ALL[] = {CHAR_0, CHAR_1, CHAR_2, CHAR_3, CHAR_4, CHAR_5, CHAR_6, CHAR_7, CHAR_8, CHAR_9, CHAR_A, CHAR_B, CHAR_C, CHAR_D, CHAR_E, CHAR_F, CHAR_HEX, CHAR_HEX_0, CHAR_HEX_1, CHAR_HEX_2, CHAR_HEX_3, CHAR_HEX_4, CHAR_HEX_5, CHAR_HEX_6, CHAR_HEX_7, CHAR_HEX_8, CHAR_HEX_9, CHAR_HEX_A, CHAR_HEX_B, CHAR_HEX_C, CHAR_HEX_D, CHAR_HEX_E, CHAR_HEX_F, CHAR_UP, CHAR_UP_RIGHT, CHAR_RIGHT, CHAR_DOWN_RIGHT, CHAR_DOWN, CHAR_DOWN_LEFT, CHAR_LEFT, CHAR_UP_LEFT}
	;
	
	public static ITexture nei() {
		return NEI ? BlockTextureDefault.get(CHAR_NEI, CA_YELLOW_255, F, T, F, F) : null;
	}
	
	public static IIconContainer decimalDigit(long aNumber, long aDigit) {
		aNumber = Math.abs(aNumber);
		aDigit = UT.Code.bind_(0, 19, aDigit);
		long j = 1;
		for (long i = 0; i < aDigit; i++) j*=10;
		return CHARACTERS_DEC[(int)((aNumber / j) % 10)];
	}
	
	public static IIconContainer hexadecimalDigit(long aNumber, long aDigit) {
		aNumber = Math.abs(aNumber);
		aDigit = UT.Code.bind4(aDigit);
		long j = 1;
		for (long i = 0; i < aDigit; i++) j*=16;
		return CHARACTERS_HEX[(int)((aNumber / j) % 16)];
	}
	
	public static IIconContainer[] decimal(long aNumber, long aDigits) {
		IIconContainer[] rIconContainer = new IIconContainer[(int)aDigits];
		aNumber = Math.abs(aNumber);
		aDigits = UT.Code.bind_(0, 19, aDigits);
		for (long i = 0, j = 1; i < aDigits; i++, j*=10) rIconContainer[(int)i] = CHARACTERS_DEC[(int)((aNumber / j) % 10)];
		return rIconContainer;
	}
	
	public static IIconContainer[] hexadecimal(long aNumber, long aDigits) {
		IIconContainer[] rIconContainer = new IIconContainer[(int)aDigits];
		aNumber = Math.abs(aNumber);
		aDigits = UT.Code.bind4(aDigits);
		for (long i = 0, j = 1; i < aDigits; i++, j*=16) rIconContainer[(int)i] = CHARACTERS_HEX[(int)((aNumber / j) % 16)];
		return rIconContainer;
	}
	
	/** Barometer related Textures */
	public static IIconContainer BAROMETER = new Icon("overlays/barometer/base"), BAROMETER_SCALE[] = new IIconContainer[32];
	static {for (int i = 0; i < BAROMETER_SCALE.length; i++) BAROMETER_SCALE[i] = new Icon("overlays/barometer/"+(i<10?"0":"")+i);}
	
	private static class Icon implements IIconContainer, Runnable {
		private IIcon mIcon;
		private String mIconName;
		
		protected Icon(String aIconName) {mIconName = aIconName; if (GT_API.sBlockIconload != null) GT_API.sBlockIconload.add(this);}
		
		@Override public IIcon getIcon(int aRenderPass) {return mIcon;}
		@Override public void run() {mIcon = GT_API.sBlockIcons.registerIcon(RES_PATH_API_BLOCK + mIconName);}
		@Override public ResourceLocation getTextureFile() {return TextureMap.locationBlocksTexture;}
		@Override public short[] getIconColor(int aRenderPass) {return UNCOLOURED;}
		@Override public int getIconPasses() {return 1;}
		@Override public void registerIcons(IIconRegister aIconRegister) {/**/}
		@Override public boolean isUsingColorModulation(int aRenderPass) {return true;}
	}
}
