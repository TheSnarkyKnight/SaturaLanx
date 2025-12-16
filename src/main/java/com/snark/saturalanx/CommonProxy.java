package com.snark.saturalanx;

import com.snark.saturalanx.core.*;
import com.snark.saturalanx.recipes.*;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static com.snark.saturalanx.SaturaLanx.bidLoaded;
import static com.snark.saturalanx.SaturaLanx.kvassLoaded;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

        getLoadedMods();
        FoodSetup.initializeFood();
        FoodSetup.registerFood();
        ItemSetup.initItems();
        ItemSetup.registeritems();
        DrinkSetup.initializeDrinks();
        DrinkSetup.registerDrinks();
        FluidSetup.initializeFluids();
        FluidSetup.registerFluids();
        BlockSetup.initializeBlocks();
        BlockSetup.registerBlocks();
        EntitySetup.registerTileEntities();
        EntitySetup.registerEntities();
    }

    public void init(FMLInitializationEvent event){

        ItemSetup.setupItems();
        DrinkSetup.setupDrinks();
        BarrelRecipes.registerBarrelRecipes();
        HopperRecipes.registerHopperRecipes();
        FoodRecipes.registerFoodRecipes();
        KilnRecipes.registerRecipes();
        CraftingRecipes.registerRecipes();
        KnappingRecipes.registerKnapping();


    }

    public void postInit(FMLPostInitializationEvent event){
        MoldRecipes.registerPartialMolds();
        HeatSetup.registerHeat();
    }

    private void getLoadedMods(){

        bidLoaded = Loader.isModLoaded("TFCPlus Bids");

        kvassLoaded = false;
        for(ModContainer m : Loader.instance().getModList()){
            if(m.getModId().equals("tfcpluskvass"))
                kvassLoaded = true;
        }

    }
    
}
