package gregtech.tileentity.energy.reactors;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;

public class ReactorCoolant {
	protected static final ArrayList<ReactorCoolant> COOLANTS = new ArrayList<>();
	/// Looked up several times per rod per tick, so keyed by Fluid instead of scanning COOLANTS.
	protected static final HashMap<Fluid, ReactorCoolant> COOLANTS_BY_FLUID = new HashMap<>();

	public FluidStack mCoolant, mHeatant;

	public long mHUPerLiter;
	public boolean mModerates;

	public double mEmissionMultiplier;
	public double mSelfMultiplier;
	public double mMaxMultiplier;
	public double mFactorMultiplier;
	public double mHeatDivisor;

	public ReactorCoolant(FluidStack aCoolant, FluidStack aHeatant, long aHUPerLiter, boolean aModerates, double aEmissionMultiplier, double aSelfMultiplier, double aMaxMultiplier, double aFactorMultiplier, double aHeatDivisor) {
		mCoolant = aCoolant;
		mHeatant = aHeatant;
		mHUPerLiter = aHUPerLiter;
		mModerates = aModerates;
		mEmissionMultiplier = aEmissionMultiplier;
		mSelfMultiplier = aSelfMultiplier;
		mMaxMultiplier = aMaxMultiplier;
		mFactorMultiplier = aFactorMultiplier;
		mHeatDivisor = aHeatDivisor;

		COOLANTS.add(this);
		COOLANTS_BY_FLUID.put(aCoolant.getFluid(), this);
	}

	public ReactorCoolant(FluidStack aCoolant, FluidStack aHeatant, long aHUPerLiter, boolean aModerates) {
		this(aCoolant, aHeatant, aHUPerLiter, aModerates, 1, 1, 1, 1, 1);
	}

	public static ReactorCoolant getCoolant(FluidStack aFluid) {
		return aFluid == null ? null : COOLANTS_BY_FLUID.get(aFluid.getFluid());
	}

	public static ReactorCoolant getCoolantAtIdx(long idx) {
		return COOLANTS.get((int)(idx % ReactorCoolant.COOLANTS.size()));
	}

	public static boolean isCoolant(FluidStack aFluid) {
		return getCoolant(aFluid) != null;
	}

	public int getEmission(int base) {
		// Rounds down, so halved emission matches the old Other - divup(Other, 2).
		return (int) Math.floor((double)base * this.mEmissionMultiplier);
	}

	public int getSelf(int base) {
		return (int) Math.ceil((double)base * this.mSelfMultiplier);
	}

	public int getMax(int base) {
		return (int) Math.ceil((double)base * this.mMaxMultiplier);
	}

	public int getCriticalThreshold() {
		double threshold = 4.0; // Assuming standard reactor setup with reflectors/fuel rods touching it on each side
		if (this.mFactorMultiplier < 0) {
			threshold -= this.mFactorMultiplier;
		} else {
			threshold *= this.mFactorMultiplier;
		}
		return (int)Math.round(threshold);
	}

	/**
	 * Modify the neutron factor based on this coolant's factor multiplier.
	 * @param base the base emission factor of the fuel rod.
	 * If the factor multiplier is negative, it is subtracted from the divisor instead, as is the case with e.g. liquid Na/Sn
	 * @return the modified emission factor
	 */
	public double getFactor(int base) {
		if (this.mFactorMultiplier < 0) {
			return (1.0 / ((double)base + this.mFactorMultiplier));
		}
		return (1.0 / (double)base) * this.mFactorMultiplier;
	}

	/**
	 * Divide the heat by this coolant's heat factor
	 * @param base the input heat
	 * @return the heat left after division
	 */
	public long divideGeneratedHeat(long base) {
		if (this.mHeatDivisor == 1.0) return base; // to prevent rounding errors
		return (long) Math.ceil((double)base / this.mHeatDivisor);
	}

	public static String fractionToString(double n) {
		if (n < 1.0) {
			return "1/" + Math.round(1.0/n);
		} else {
			return Double.toString(n);
		}
	}
}
