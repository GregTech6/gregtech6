package gregtech.loaders.b;

import gregapi.data.CS;
import gregapi.data.FL;
import gregapi.data.MT;
import gregtech.tileentity.energy.reactors.ReactorCoolant;

import static gregapi.data.CS.EU_PER_WATER;
import static gregapi.data.CS.STEAM_PER_WATER;

public class Loader_Coolants implements Runnable {
	@Override
	public void run() {
		// Waters
		new ReactorCoolant(FL.DistW.make(1)                      , FL.Steam.make(STEAM_PER_WATER), EU_PER_WATER , true);
		new ReactorCoolant(FL.make(MT.HDO.mLiquid.getFluid(), 1) , FL.Hot_Semi_Heavy_Water       .make(1), CS.EU_PER_SEMI_HEAVY_WATER, true);
		new ReactorCoolant(FL.make(MT.D2O.mLiquid.getFluid(), 1) , FL.Hot_Heavy_Water            .make(1), CS.EU_PER_HEAVY_WATER     , true , 1  , 1, 0.125 , 1  , 1);
		new ReactorCoolant(FL.make(MT.T2O.mLiquid.getFluid(), 1) , FL.Hot_Tritiated_Water        .make(1), CS.EU_PER_TRITIATED_WATER , true , 1  , 1, 0.0625, 1  , 1);

		// Other
		new ReactorCoolant(FL.Coolant_IC2.make(1)                , FL.Coolant_IC2_Hot            .make(1), CS.EU_PER_COOLANT         , false, 4  , 4, 1     , 0.5, 1);

		// Gases
		new ReactorCoolant(FL.CarbonDioxide.make(1)              , FL.Hot_Carbon_Dioxide         .make(1), CS.EU_PER_CO2             , false, 1  , 3, 1     , 1  , 1);
		new ReactorCoolant(FL.Helium.make(1)                     , FL.Hot_Helium                 .make(1), CS.EU_PER_HELIUM          , false, 0.5, 1, 1     , 1  , 1);

		// Molten Metals/Salts
		new ReactorCoolant(FL.make(MT.LiCl.mLiquid.getFluid(), 1), FL.Hot_Molten_LiCl            .make(1), CS.EU_PER_LICL            , false, 0.5, 5, 1.25  , 1  , 1);
		new ReactorCoolant(FL.make(MT.Sn.mLiquid.getFluid(), 1)  , FL.Hot_Molten_Tin             .make(1), CS.EU_PER_TIN             , false, 1  , 1, 1     , -1 , 3);
		new ReactorCoolant(FL.make(MT.Na.mLiquid.getFluid(), 1)  , FL.Hot_Molten_Sodium          .make(1), CS.EU_PER_SODIUM          , false, 1  , 1, 1     , -1 , 6);
		new ReactorCoolant(FL.Thorium_Salt.make(1)               , FL.make(MT.LiCl.mLiquid.getFluid(), 1), CS.EU_PER_THORIUM_SALT    , false, 0.5, 0, 4     , -1 , 1);
	}
}
