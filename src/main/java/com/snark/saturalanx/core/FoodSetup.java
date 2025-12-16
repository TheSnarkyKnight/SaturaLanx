package com.snark.saturalanx.core;

import com.dunk.tfc.Render.Item.FoodItemRenderer;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.dunk.tfc.api.HeatIndex;
import com.dunk.tfc.api.HeatRegistry;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.FoodItemSL;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.ArrayList;

public class FoodSetup {

    public static String FOODPATH = "saturalanx:food/";

    public static Item whiteGrapes;
    public static Item dandelionTop, dandelionRoot, goldenrodLeaves, goldenrodTop,preparedNasturtium, milkweedPod, cleanedMilkweedPod, preparedMilkweedPod, calendulaPetals, tulipPetalsRed, tulipPetalsPink, tulipPetalsWhite, tulipPetalsOrange, tulipBulb, daisyFlower, poppyseed;
    public static ArrayList<Item> food;

    public FoodSetup(){

    }

    public static void initializeFood(){

        SaturaLanx.log.info("Initializing food...");

        food = new ArrayList<Item>();
        if(Config.enableDandelionFood) {
            dandelionTop = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("DandelionTop").setTextureName(FOODPATH + "DandelionTop");
            dandelionRoot = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.0F).setWaterPercentage(0.5F).setUnlocalizedName("DandelionRoot").setTextureName(FOODPATH + "DandelionRoot");

            food.add((Item) dandelionTop);
            food.add((Item) dandelionRoot);
        }
        if(Config.enableGoldenrodFood) {
            goldenrodLeaves = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.0F).setWaterPercentage(0.4F).setUnlocalizedName("GoldenrodLeaves").setTextureName(FOODPATH+"GoldenrodLeaves");
            goldenrodTop = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true,true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("GoldenrodTop").setTextureName(FOODPATH+"GoldenrodTop");

            food.add((Item) goldenrodLeaves);
            food.add((Item) goldenrodTop);
        }
        if(Config.enableNasturtiumFood) {
            preparedNasturtium = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("PreparedNasturtium").setTextureName(FOODPATH + "PreparedNasturtium");

            food.add(preparedNasturtium);
        }
        if(Config.enableCalendulaFood) {
            calendulaPetals = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("CalendulaPetals").setTextureName(FOODPATH+"CalendulaPetals");

            food.add(calendulaPetals);
        }
        if(Config.enableDaisyFood) {
            daisyFlower = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("DaisyFlower").setTextureName(FOODPATH+"DaisyFlower");

            food.add(daisyFlower);
        }
        if(Config.enableTulipFood){
            tulipPetalsRed = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("TulipPetalsRed").setTextureName(FOODPATH+"TulipPetalsRed");
            tulipPetalsOrange = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("TulipPetalsOrange").setTextureName(FOODPATH+"TulipPetalsOrange");
            tulipPetalsPink = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("TulipPetalsPink").setTextureName(FOODPATH+"TulipPetalsPink");
            tulipPetalsWhite = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("TulipPetalsWhite").setTextureName(FOODPATH+"TulipPetalsWhite");
            tulipBulb = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("TulipBulb").setTextureName(FOODPATH+"TulipBulb");

            food.add(tulipBulb);
            food.add(tulipPetalsOrange);
            food.add(tulipPetalsPink);
            food.add(tulipPetalsRed);
            food.add(tulipPetalsWhite);
        }
        if(Config.enablePoppyFood) {
            poppyseed = (new FoodItemSL(EnumFoodGroup.Grain, 20, 10, 0, 20, 0, true)).setDecayRate(0.8F).setWaterPercentage(0.2F).setUnlocalizedName("Poppyseed").setTextureName(FOODPATH+"PoppySeed");

            food.add(poppyseed);
        }
        if (Config.enableMilkweedFood) {
            milkweedPod = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, false, true, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("MilkweedPod").setTextureName(FOODPATH+"MilkweedPod");
            preparedMilkweedPod = (new FoodItemSL(EnumFoodGroup.Vegetable, 20, 10, 0, 20, 0, true)).setDecayRate(1.2F).setWaterPercentage(0.4F).setUnlocalizedName("PreparedMilkweedPod").setTextureName(FOODPATH+"PreparedMilkweedPod");

            food.add(milkweedPod);
            food.add(preparedMilkweedPod);
        }
        if(Config.enableWhiteGrapes) {
            whiteGrapes = (new FoodItemSL(EnumFoodGroup.Fruit, 50, 30, 0, 0, 0, true)).setDecayRate(1.8F).setWaterPercentage(0.9F).setUnlocalizedName("WhiteGrapes").setTextureName(FOODPATH + "WhiteGrapes");

            food.add((Item) whiteGrapes);
        }

    }

    public static void registerFood(){

        SaturaLanx.log.info("Registering food...");

        for(Item f: food) {
            GameRegistry.registerItem(f, f.getUnlocalizedName());
        }

    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders(){

        SaturaLanx.log.info("Registering food renders...");

        for(Item f: food){
            MinecraftForgeClient.registerItemRenderer(f, new FoodItemRenderer());
        }

    }

}
