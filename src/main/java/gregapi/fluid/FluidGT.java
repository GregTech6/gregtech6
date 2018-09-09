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
	@Override public String getLocalizedName(FluidStack stack) {return getLocalizedName();}
	@Override public String getLocalizedName() {return LH.get(getUnlocalizedName());}
	
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