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

package gregapi.fluid;

import static gregapi.data.CS.*;

import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidGT extends Fluid implements Runnable {
	private final short[] mRGBa;
	
	public final IIconContainer mTexture;
	
	public final boolean mGas;
	public final int mTemperature;
	
	public FluidGT(String aName, String aTextureName, short[] aRGBa, long aTemperatureK, boolean aGas) {
		super(aName);
		mRGBa = aRGBa;
		mTexture = new Textures.BlockIcons.CustomIcon("fluids/" + aTextureName);
		setGaseous(mGas = aGas);
		setTemperature(mTemperature = UT.Code.bindInt(aTemperatureK));
		
		// Ensure that no Mod fucked up the Values.
		GT.mAfterPostInit.add(this);
		GT.mAfterServerStarted.add(this);
		GAPI_POST.mAfterPostInit.add(this);
		GAPI_POST.mAfterServerStarted.add(this);
	}
	
	public FluidGT(String aName, IIconContainer aTexture, short[] aRGBa, long aTemperatureK, boolean aGas) {
		super(aName);
		mRGBa = aRGBa;
		mTexture = aTexture;
		setGaseous(mGas = aGas);
		setTemperature(mTemperature = UT.Code.bindInt(aTemperatureK));
		
		// Ensure that no Mod fucked up the Values.
		GT.mAfterPostInit.add(this);
		GT.mAfterServerStarted.add(this);
		GAPI_POST.mAfterPostInit.add(this);
		GAPI_POST.mAfterServerStarted.add(this);
	}
	
	@Override public String getUnlocalizedName(FluidStack stack) {return getUnlocalizedName();}
	@Override public String getUnlocalizedName() {return "fluid." + unlocalizedName;}
	@Override public String getLocalizedName(FluidStack stack) {return LH.get(getUnlocalizedName());}
	@Override @SuppressWarnings("deprecation") public String getLocalizedName() {return LH.get(getUnlocalizedName());}
	
	@Override public int getTemperature(FluidStack aFluid) {return mTemperature;}
	@Override public boolean isGaseous(FluidStack aFluid) {return mGas;}
	@Override public int getColor(FluidStack aFluid) {return UT.Code.getRGBInt(mRGBa);}
	@Override public IIcon getIcon(FluidStack aFluid) {return mTexture.getIcon(0);}
	
	@Override public int getTemperature(World aWorld, int aX, int aY, int aZ) {return mTemperature;}
	@Override public boolean isGaseous(World aWorld, int aX, int aY, int aZ) {return mGas;}
	@Override public int getColor(World aWorld, int aX, int aY, int aZ) {return UT.Code.getRGBInt(mRGBa);}
	@Override public IIcon getIcon(World aWorld, int aX, int aY, int aZ) {return mTexture.getIcon(0);}
	
	@Override public int getColor() {return UT.Code.getRGBInt(mRGBa);}
	@Override public IIcon getIcon() {return mTexture.getIcon(0);}
	@Override public IIcon getStillIcon() {return mTexture.getIcon(0);}
	@Override public IIcon getFlowingIcon() {return mTexture.getIcon(0);}
	
	@Override
	public void run() {
		// Ensure that no Mod fucked up the Values.
		setGaseous(mGas);
		setTemperature(mTemperature);
	}
}
