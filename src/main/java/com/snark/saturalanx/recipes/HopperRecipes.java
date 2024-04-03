package com.snark.saturalanx.recipes;

import com.dunk.tfc.TileEntities.TEHopper;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.FluidSetup;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.Config;

public class HopperRecipes {

    public HopperRecipes(){

    }

    public static void registerHopperRecipes(){

        SaturaLanx.log.info("Registering hopper recipes...");

        if(Config.enableWhiteGrapes)
            TEHopper.registerPressableItem(FoodSetup.whiteGrapes, FluidSetup.WHITEGRAPEJUICE, 0.5F, 10.0F);
        TEHopper.registerPressableItem(TFCItems.melonSlice, FluidSetup.MELONJUICE,0.5F, 10F);
    }
}
