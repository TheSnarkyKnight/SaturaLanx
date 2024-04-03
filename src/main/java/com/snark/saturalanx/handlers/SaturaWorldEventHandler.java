package com.snark.saturalanx.handlers;

import com.snark.saturalanx.recipes.AnvilRecipes;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class SaturaWorldEventHandler {

    @SubscribeEvent
    public void onLoadWorld(WorldEvent.Load event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
            AnvilRecipes.registerRecipes();
        }
    }



}
