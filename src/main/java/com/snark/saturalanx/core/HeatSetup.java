package com.snark.saturalanx.core;

import com.dunk.tfc.api.HeatIndex;
import com.dunk.tfc.api.HeatRegistry;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import net.minecraft.item.ItemStack;

public class HeatSetup {

    public static void registerHeat(){
        SaturaLanx.log.info("Registering heat data for items...");

        final HeatRegistry reg = HeatRegistry.getInstance();

        if(Config.enableDandelionFood){
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.dandelionTop,1),1,177,null));
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.dandelionRoot,1),1,177,null));
        }
        if(Config.enableGoldenrodFood){
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.goldenrodLeaves,1),1,177,null));
        }
        if(Config.enableNasturtiumFood)
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.preparedNasturtium,1),1,177,null));
        if(Config.enableCalendulaFood)
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.calendulaPetals,1),1,177,null));
        if(Config.enableDaisyFood)
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.daisyFlower,1),1,177,null));
        if(Config.enableTulipFood){
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.tulipPetalsPink,1),1,177,null));
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.tulipPetalsOrange,1),1,177,null));
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.tulipPetalsRed,1),1,177,null));
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.tulipPetalsWhite,1),1,177,null));
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.tulipBulb,1),1,177,null));
        }
        if(Config.enablePoppyFood)
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.poppyseed,1),1,177,null));
        if(Config.enableMilkweedFood)
            reg.addIndex(new HeatIndex(new ItemStack(FoodSetup.preparedMilkweedPod,1),1,177,null));

        if(Config.enableFireHardenedSpears)
            reg.addIndex(new HeatIndex(new ItemStack(TFCItems.woodenSpear,1,0),1,120,new ItemStack(ItemSetup.hardenedWoodSpear,1,0)));
    }
}
