package com.snark.saturalanx.core;

import com.dunk.tfc.Items.ItemAlcohol;
import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.AlcoholItemSL;
import com.snark.saturalanx.items.tools.BucketSL;
import com.snark.saturalanx.items.DrinkItemSL;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.snark.saturalanx.core.FluidSetup.*;
import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class DrinkSetup {

    public static Item oliveOil;
    public static Item melonJuice, melonWine, melonBrandy;
    public static Item whiteGrapeJuice, whiteWine;
    public static Item sugarWater, kilju, pontikka;
    public static Item ethanol, ethanolBucketWood, ethanolBucketClay;
    public static Item liquorBase, dandelionLiquor;
    public static Item lemonLiquor, orangeLiquor;
    public static Item appleLiquor;
    public static Item peachLiquor, plumLiquor, cherryLiquor;
    public static Item papayaLiquor, dateLiquor, coconutLiquor, bananaLiquor, melonLiquor;
    public static Item figLiquor, grapeLiquor, whiteGrapeLiquor;
    public static Item blueberryLiquor, raspberryLiquor, strawberryLiquor, blackberryLiquor, cranberryLiquor;
    public static Item wintergreenLiquor, bunchberryLiquor, cloudberryLiquor, gooseberryLiquor, snowberryLiquor, elderberryLiquor;
    public static Item dandelionTea, dandelionCoffee, goldenrodTea, daisyTea;
    public static ArrayList<ItemDrink> drinks;
    public static HashMap<String, Item> potteryDrinks;
    public DrinkSetup(){

    }

    public static void initializeDrinks() {

        SaturaLanx.log.info("Initializing drinks...");


        //Misc

        oliveOil = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(1.50F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Vegetable).setUnlocalizedName("Olive Oil");

        //Teas

        dandelionTea = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.30F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Vegetable).setUnlocalizedName("DandelionTea");
        dandelionCoffee = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.30F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Vegetable).setUnlocalizedName("DandelionCoffee");
        goldenrodTea = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.30F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Vegetable).setUnlocalizedName("GoldenrodTea");
        daisyTea = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.30F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Vegetable).setUnlocalizedName("DaisyTea");

        //Fruit juice
        whiteGrapeJuice = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.608F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Fruit).setUnlocalizedName("WhiteGrapeJuice");
        melonJuice = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(1.0F).setCalories(0.608F).setCanDrinkInParts(true).setFoodGroup(EnumFoodGroup.Fruit).setUnlocalizedName("MelonJuice");

        //Wine
        whiteWine = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.14F).setCalories(0.492F).setTier(1).setCanDrinkInParts(true).setWaterRestoreRatio(0.375F).setUnlocalizedName("WhiteWine");
        melonWine = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.14F).setCalories(0.492F).setTier(1).setCanDrinkInParts(true).setWaterRestoreRatio(0.375F).setUnlocalizedName("MelonWine");

        //Distillates
        melonBrandy = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.45F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("MelonBrandy");

        //Sugarwater, kilju and pontikka
        sugarWater = (new DrinkItemSL(1000.0F)).setWaterRestoreRatio(0.8F).setCalories(1.0F).setCanDrinkInParts(true).setFoodGroup(null).setUnlocalizedName("SugarWater");
        kilju = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.20F).setCalories(0.560F).setTier(1).setCanDrinkInParts(true).setWaterRestoreRatio(0.350F).setUnlocalizedName("Kilju");
        pontikka = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.45F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("Pontikka");

        //Ethanol
        ethanol = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.95F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("Ethanol");
        ethanolBucketClay = (new BucketSL(Blocks.air).setUnlocalizedName("CeramicBucketEthanol").setContainerItem(TFCItems.clayBucketEmpty).setCreativeTab(tab).setTextureName(MODID + ":" + "CeramicBucketEthanol"));
        ethanolBucketWood = (new BucketSL(Blocks.air).setUnlocalizedName("WoodenBucketEthanol").setContainerItem(TFCItems.woodenBucketEmpty).setCreativeTab(tab).setTextureName(MODID + ":" + "WoodenBucketEthanol"));

        //Liquors
        liquorBase = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("LiquorBase");

        dandelionLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("DandelionLiquor");

        lemonLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("LemonLiquor");
        orangeLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("OrangeLiquor");
        appleLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("AppleLiquor");
        peachLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("PeachLiquor");
        plumLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("PlumLiquor");
        cherryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("CherryLiquor");
        papayaLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("PapayaLiquor");
        dateLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("DateLiquor");
        coconutLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("CoconutLiquor");
        bananaLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("BananaLiquor");
        melonLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("MelonLiquor");
        figLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("FigLiquor");
        grapeLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("GrapeLiquor");
        whiteGrapeLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("WhiteGrapeLiquor");
        blueberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("BlueberryLiquor");
        raspberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("RaspberryLiquor");
        strawberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("StrawberryLiquor");
        blackberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("BlackberryLiquor");
        cranberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("CranberryLiquor");
        wintergreenLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("WintergreenLiquor");
        bunchberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("BunchberryLiquor");
        cloudberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("CloudberryLiquor");
        gooseberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("GooseberryLiquor");
        snowberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("SnowberryLiquor");
        elderberryLiquor = (new AlcoholItemSL(1000.0F)).setAlcoholContent(0.4F).setTier(2).setCanDrinkInParts(true).setUnlocalizedName("ElderberryLiquor");

        drinks = new ArrayList<ItemDrink>();

        if (Config.enableDandelionFood) {
            drinks.add((ItemDrink) dandelionTea);
            drinks.add((ItemDrink) dandelionCoffee);
        }
        if (Config.enableGoldenrodFood) {
            drinks.add((ItemDrink) goldenrodTea);
        }
        if (Config.enableDaisyFood){
            drinks.add((ItemDrink) daisyTea);
        }
        if (Config.enableWhiteGrapes) {
            drinks.add((ItemDrink) whiteGrapeJuice);
            drinks.add((ItemDrink) whiteWine);
        }
        drinks.add((ItemDrink) melonJuice);
        drinks.add((ItemDrink) melonWine);
        drinks.add((ItemDrink) melonBrandy);
        drinks.add((ItemDrink) oliveOil);
        drinks.add((ItemDrink) sugarWater);
        drinks.add((ItemDrink) kilju);
        drinks.add((ItemDrink) pontikka);
        drinks.add((ItemDrink) ethanol);
        if (Config.enableLiqueurs) {
            drinks.add((ItemDrink) liquorBase);
            drinks.add((ItemDrink) dandelionLiquor);
            drinks.add((ItemDrink) lemonLiquor);
            drinks.add((ItemDrink) orangeLiquor);
            drinks.add((ItemDrink) appleLiquor);
            drinks.add((ItemDrink) peachLiquor);
            drinks.add((ItemDrink) plumLiquor);
            drinks.add((ItemDrink) cherryLiquor);
            drinks.add((ItemDrink) papayaLiquor);
            drinks.add((ItemDrink) dateLiquor);
            drinks.add((ItemDrink) coconutLiquor);
            drinks.add((ItemDrink) bananaLiquor);
            drinks.add((ItemDrink) melonLiquor);
            drinks.add((ItemDrink) figLiquor);
            drinks.add((ItemDrink) grapeLiquor);
            drinks.add((ItemDrink) whiteGrapeLiquor);
            drinks.add((ItemDrink) blueberryLiquor);
            drinks.add((ItemDrink) raspberryLiquor);
            drinks.add((ItemDrink) strawberryLiquor);
            drinks.add((ItemDrink) blackberryLiquor);
            drinks.add((ItemDrink) cranberryLiquor);
            drinks.add((ItemDrink) wintergreenLiquor);
            drinks.add((ItemDrink) bunchberryLiquor);
            drinks.add((ItemDrink) cloudberryLiquor);
            drinks.add((ItemDrink) gooseberryLiquor);
            drinks.add((ItemDrink) snowberryLiquor);
            drinks.add((ItemDrink) elderberryLiquor);
        }

        potteryDrinks = new HashMap();
        Iterator drinkIterator = drinks.iterator();

        while(drinkIterator.hasNext()) {
            ItemDrink d = (ItemDrink)drinkIterator.next();
            Item potteryDrink;
            if (d instanceof ItemAlcohol) {
                ItemAlcohol a = (ItemAlcohol)d;
                potteryDrink = (new ItemAlcohol(1000.0F, 1)).setAlcoholContent(a.getAlcoholContent()).setTier(1).setCanDrinkInParts(true);
            } else {
                potteryDrink = (new ItemDrink(1000.0F, 1)).setCanDrinkInParts(true);
            }

            potteryDrink = ((ItemDrink)potteryDrink).setCalories(d.getCalorieRatio()).setWaterRestoreRatio(d.getWaterRestoreRatio()).setFoodGroup(d.getFoodGroup()).setUnlocalizedName(d.getUnlocalizedName().substring(5) + "Pottery").setCreativeTab(tab);
            potteryDrinks.put(d.getUnlocalizedName(), potteryDrink);
        }

    }

    public static void setupDrinks(){

        SaturaLanx.log.info("Setting up drinks...");

        //Teas
        if(Config.enableDandelionFood) {
            ((DrinkItemSL) dandelionTea).setupFluidDrinks(DANDELIONTEA, 1000, dandelionTea, TFCItems.glassBottle);
            ((DrinkItemSL) dandelionCoffee).setupFluidDrinks(DANDELIONCOFFEE, 1000, dandelionCoffee, TFCItems.glassBottle);
        }
        if(Config.enableGoldenrodFood)
            ((DrinkItemSL)goldenrodTea).setupFluidDrinks(GOLDENRODTEA,1000,goldenrodTea,TFCItems.glassBottle);
        if(Config.enableDaisyFood)
            ((DrinkItemSL)daisyTea).setupFluidDrinks(DAISYTEA,1000,daisyTea,TFCItems.glassBottle);
        //White grape juice and wine
        if(Config.enableWhiteGrapes) {
            ((DrinkItemSL) whiteGrapeJuice).setupFluidDrinks(WHITEGRAPEJUICE, 1000, whiteGrapeJuice, TFCItems.glassBottle);
            ((AlcoholItemSL) whiteWine).setupFluidDrinks(WHITEWHINE, 1000, whiteWine, TFCItems.glassBottle);
        }

        ((DrinkItemSL) melonJuice).setupFluidDrinks(MELONJUICE, 1000, melonJuice, TFCItems.glassBottle);
        ((AlcoholItemSL) melonWine).setupFluidDrinks(MELONWINE, 1000, melonWine, TFCItems.glassBottle);
        ((AlcoholItemSL) melonBrandy).setupFluidDrinks(MELONBRANDY, 1000, melonBrandy, TFCItems.glassBottle);

        ((DrinkItemSL)oliveOil).setupFluidDrinks(TFCFluids.OLIVEOIL,1000,oliveOil,TFCItems.glassBottle);

        //Sugarwater, kilju and pontikka
        ((DrinkItemSL)sugarWater).setupFluidDrinks(SUGARWATER,1000,sugarWater,TFCItems.glassBottle);
        ((AlcoholItemSL)kilju).setupFluidDrinks(KILJU,1000,kilju,TFCItems.glassBottle);
        ((AlcoholItemSL)pontikka).setupFluidDrinks(PONTIKKA,1000,pontikka,TFCItems.glassBottle);

        //Ethanol
        ((AlcoholItemSL)ethanol).setupFluidDrinks(ETHANOL,1000,ethanol,TFCItems.glassBottle);
        ((BucketSL)ethanolBucketClay).registerFluidContainerHelper(ETHANOL,1000,new ItemStack(ethanolBucketClay), new ItemStack(TFCItems.clayBucketEmpty));
        ((BucketSL)ethanolBucketWood).registerFluidContainerHelper(ETHANOL,1000,new ItemStack(ethanolBucketWood), new ItemStack(TFCItems.woodenBucketEmpty));

        //Liquors
        if(Config.enableLiqueurs) {
            ((AlcoholItemSL) liquorBase).setupFluidDrinks(LIQUORBASE, 1000, liquorBase, TFCItems.glassBottle);

            ((AlcoholItemSL) dandelionLiquor).setupFluidDrinks(DANDELIONLIQUOR, 1000, dandelionLiquor, TFCItems.glassBottle);

            ((AlcoholItemSL) lemonLiquor).setupFluidDrinks(LEMONLIQUOR, 1000, lemonLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) orangeLiquor).setupFluidDrinks(ORANGELIQUOR, 1000, orangeLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) appleLiquor).setupFluidDrinks(APPLELIQUOR, 1000, appleLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) peachLiquor).setupFluidDrinks(PEACHLIQUOR, 1000, peachLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) plumLiquor).setupFluidDrinks(PLUMLIQUOR, 1000, plumLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) cherryLiquor).setupFluidDrinks(CHERRYLIQUOR, 1000, cherryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) papayaLiquor).setupFluidDrinks(PAPAYALIQUOR, 1000, papayaLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) dateLiquor).setupFluidDrinks(DATELIQUOR, 1000, dateLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) coconutLiquor).setupFluidDrinks(COCONUTLIQUOR, 1000, coconutLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) bananaLiquor).setupFluidDrinks(BANANALIQUOR, 1000, bananaLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) melonLiquor).setupFluidDrinks(MELONLIQUOR, 1000, melonLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) figLiquor).setupFluidDrinks(FIGLIQUOR, 1000, figLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) grapeLiquor).setupFluidDrinks(GRAPELIQUOR, 1000, grapeLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) whiteGrapeLiquor).setupFluidDrinks(WHITEGRAPELIQUOR, 1000, whiteGrapeLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) blueberryLiquor).setupFluidDrinks(BLUEBERRYLIQUOR, 1000, blueberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) raspberryLiquor).setupFluidDrinks(RASPBERRYLIQUOR, 1000, raspberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) strawberryLiquor).setupFluidDrinks(STRAWBERRYLIQUOR, 1000, strawberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) blackberryLiquor).setupFluidDrinks(BLACKBERRYLIQUOR, 1000, blackberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) cranberryLiquor).setupFluidDrinks(CRANBERRYLIQUOR, 1000, cranberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) wintergreenLiquor).setupFluidDrinks(WINTERGREENLIQUOR, 1000, wintergreenLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) bunchberryLiquor).setupFluidDrinks(BUNCHBERRYLIQUOR, 1000, bunchberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) cloudberryLiquor).setupFluidDrinks(CLOUDBERRYLIQUOR, 1000, cloudberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) snowberryLiquor).setupFluidDrinks(SNOWBERRYLIQUOR, 1000, snowberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) gooseberryLiquor).setupFluidDrinks(GOOSEBERRYLIQUOR, 1000, gooseberryLiquor, TFCItems.glassBottle);
            ((AlcoholItemSL) elderberryLiquor).setupFluidDrinks(ELDERBERRYLIQUOR, 1000, elderberryLiquor, TFCItems.glassBottle);
        }
    }

    public static void registerDrinks(){

        SaturaLanx.log.info("Registering drinks...");

        //Bottles
        Iterator drinkIterator = drinks.iterator();
        while (drinkIterator.hasNext()) {
            Item d = (Item) drinkIterator.next();
            GameRegistry.registerItem(d, d.getUnlocalizedName());
        }

        //Jugs
        Iterator<String> potteryDrinkIterator = potteryDrinks.keySet().iterator();

        while(potteryDrinkIterator.hasNext()) {
            String s = (String)potteryDrinkIterator.next();
            Item d = (Item)potteryDrinks.get(s);
            GameRegistry.registerItem(d, d.getUnlocalizedName());
        }

        GameRegistry.registerItem(ethanolBucketClay, ethanolBucketClay.getUnlocalizedName());
        GameRegistry.registerItem(ethanolBucketWood, ethanolBucketWood.getUnlocalizedName());
    }


}
