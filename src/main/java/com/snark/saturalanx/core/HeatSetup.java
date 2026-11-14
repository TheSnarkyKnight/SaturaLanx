package com.snark.saturalanx.core;

import com.dunk.tfc.api.HeatIndex;
import com.dunk.tfc.api.HeatRegistry;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.ItemSL;
import com.snark.saturalanx.items.tools.ToolHeadSL;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HeatSetup {

    public static void registerHeat(){
        SaturaLanx.log.info("Registering heat data for items...");

        final HeatRegistry reg = HeatRegistry.getInstance();

        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,0),0.35, (double)950.0F,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,1),0.35, (double)985.0F,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,2),0.35, (double)1010.0F,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,3),0.38, (double)930.0F,new ItemStack(TFCItems.brassUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,4),0.38, (double)930.0F,new ItemStack(TFCItems.brassUnshaped,4,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,5),0.35, (double)1500.0F,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,6),0.35, (double)1500.0F,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,7),0.35, (double)1500.0F,new ItemStack(TFCItems.steelUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedBarrel,1,8),0.35, (double)1500.0F,new ItemStack(TFCItems.steelUnshaped,4,0)));

        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,0),0.35, (double)950.0F,new ItemStack(TFCItems.bronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,1),0.35, (double)985.0F,new ItemStack(TFCItems.bismuthBronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,2),0.35, (double)1010.0F,new ItemStack(TFCItems.blackBronzeUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,3),0.38, (double)930.0F,new ItemStack(TFCItems.brassUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,4),0.38, (double)930.0F,new ItemStack(TFCItems.brassUnshaped,4,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,5),0.35, (double)1500.0F,new ItemStack(TFCItems.wroughtIronUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,6),0.35, (double)1500.0F,new ItemStack(TFCItems.wroughtIronUnshaped,4,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,7),0.35, (double)1500.0F,new ItemStack(TFCItems.steelUnshaped,2,0)));
        reg.addIndex(new HeatIndex(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,8),0.35, (double)1500.0F,new ItemStack(TFCItems.steelUnshaped,4,0)));

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

    }
}
