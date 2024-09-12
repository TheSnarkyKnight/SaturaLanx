package com.snark.saturalanx.recipes;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.Crafting.*;
import com.dunk.tfc.api.TFCFluids;
import com.dunk.tfc.api.TFCItems;
//import com.muurr.tfcpluskvass.ItemSetup;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.FluidSetup;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.dunk.tfc.TileEntities.TEBarrel.getYeastFoods;
import static net.minecraft.init.Items.gunpowder;
//import static com.muurr.tfcpluskvass.ItemSetup.KISSEL;


public class BarrelRecipes {

    public BarrelRecipes(){

    }

    public static void registerBarrelRecipes(){

        SaturaLanx.log.info("Registering barrel recipes...");

        //Misc
        BarrelManager.getInstance().addRecipe((new BarrelRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 80.0F), new FluidStack(TFCFluids.FRESHWATER, 5000), (ItemStack)null, new FluidStack(FluidSetup.SUGARWATER, 5000))).setMinTechLevel(0).setSealedRecipe(false).setSealTime(0));
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(gunpowder,2),new FluidStack(TFCFluids.FRESHWATER,1000),(ItemStack) null,new FluidStack(FluidSetup.GUNPOWDERFLUID,1000)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setSealTime(0));
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(TFCItems.powder,2,4),new FluidStack(TFCFluids.FRESHWATER,1000),(ItemStack) null,new FluidStack(FluidSetup.SALTPETERFLUID,1000)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setSealTime(0));
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(ItemSetup.match,1,0),new FluidStack(FluidSetup.SALTPETERFLUID,100),new ItemStack(ItemSetup.slowmatch,1),new FluidStack(FluidSetup.SALTPETERFLUID,100)).setMinTechLevel(0).setSealedRecipe(true).setRemovesLiquid(true).setSealTime(5000));
        //Incendiary Pot
        if(Config.enableIncendiaryPot) {

            List<Item> string = new ArrayList<Item>();
            string.add(TFCItems.linenString);
            string.add(TFCItems.cottonYarn);
            string.add(TFCItems.grassCordage);

            for (Item i : string) {
                BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(i, 1), new FluidStack(TFCFluids.OLIVEOIL, 100), new ItemStack(ItemSetup.wick, 1), new FluidStack(TFCFluids.OLIVEOIL, 100))).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(true).setSealTime(5000));
                BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(i, 1), new FluidStack(TFCFluids.PITCH, 100), new ItemStack(ItemSetup.wick, 1), new FluidStack(TFCFluids.PITCH, 100))).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(true).setSealTime(5000));
            }
            BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.potterySmallVessel,1,1),new FluidStack(TFCFluids.PITCH,1000),new ItemStack(ItemSetup.pitchPot,1),new FluidStack(TFCFluids.PITCH,1000)).setSealedRecipe(false).setMinTechLevel(0).setSealTime(0).setRemovesLiquid(true)));
            BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(ItemSetup.pitchPot,1),new FluidStack(TFCFluids.PITCH,1000),new ItemStack(TFCItems.potterySmallVessel,1,1), new FluidStack(TFCFluids.PITCH,1000))).setSealTime(0).setSealedRecipe(true).setMinTechLevel(0).setRemovesLiquid(false));
        }

        //Pot Grenades
        if(Config.enablePotGrenades){
            List<Item> string = new ArrayList<Item>();
            string.add(TFCItems.linenString);
            string.add(TFCItems.cottonYarn);
            string.add(TFCItems.grassCordage);
            for(Item i : string) {
                BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(i,1),new FluidStack(TFCFluids.LIMEWATER,100),new ItemStack(ItemSetup.match,1,0),new FluidStack(TFCFluids.LIMEWATER,100)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(true).setSealTime(5000)));
            }
            BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(ItemSetup.match,1,0),new FluidStack(FluidSetup.GUNPOWDERFLUID,100),new ItemStack(ItemSetup.match,1,1),new FluidStack(FluidSetup.GUNPOWDERFLUID,100)).setMinTechLevel(0).setSealedRecipe(true).setRemovesLiquid(true).setSealTime(5000));
            }

        //Tea recipes
        if(Config.enableDandelionFood) {
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.dandelionTop), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 1000), (ItemStack) null, new FluidStack(FluidSetup.DANDELIONTEA, 1000), 0, 250).setSealedRecipe(false).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.dandelionRoot), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 1000), (ItemStack) null, new FluidStack(FluidSetup.DANDELIONCOFFEE, 1000), 0, 250).setSealedRecipe(false).setMinTechLevel(0).setRequiresCooked(true));
        }
        if(Config.enableGoldenrodFood)
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.goldenrodTop), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 1000), (ItemStack) null, new FluidStack(FluidSetup.GOLDENRODTEA, 1000), 0, 250).setSealedRecipe(false).setMinTechLevel(0));
        if(Config.enableDaisyFood)
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.daisyFlower), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 1000), (ItemStack) null, new FluidStack(FluidSetup.DAISYTEA, 1000), 0, 250).setSealedRecipe(false).setMinTechLevel(0));
        if(Config.enableMilkweedFood)
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.milkweedPod), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 500), ItemFoodTFC.createTag(new ItemStack(FoodSetup.preparedMilkweedPod), 8.0F), new FluidStack(TFCFluids.FRESHWATER, 500), 0, 300).setSealedRecipe(false).setSealTime(0).setMinTechLevel(0).setRemovesLiquid(true));

        //Fermentation recipes
        Item[] yeastfoods = getYeastFoods();
        int num = yeastfoods.length;
        int i;
        for(i=0;i<num;++i){

            Item m = yeastfoods[i];

            if(Config.enableWhiteGrapes)
                BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(m), 0.001F, true), new FluidStack(FluidSetup.WHITEGRAPEJUICE, 10000), (ItemStack)null, new FluidStack(FluidSetup.WHITEWHINE, 10000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(m), 0.001F, true), new FluidStack(FluidSetup.SUGARWATER, 10000), (ItemStack)null, new FluidStack(FluidSetup.KILJU, 10000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(m), 0.001F, true), new FluidStack(FluidSetup.MELONJUICE, 10000), (ItemStack)null, new FluidStack(FluidSetup.MELONWINE, 10000))).setMinTechLevel(0));

        }

        //Distillation recipes
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(FluidSetup.WHITEWHINE, 200), (ItemStack)null, new FluidStack(TFCFluids.BRANDY, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(FluidSetup.MELONWINE, 200), (ItemStack)null, new FluidStack(FluidSetup.MELONBRANDY, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(FluidSetup.KILJU, 200), (ItemStack)null, new FluidStack(FluidSetup.PONTIKKA, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));

        //Ethanol distillation
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.WHISKEY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.RYEWHISKEY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.BARLEYWHISKEY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.RICEWHISKEY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.CORNWHISKEY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.VODKA, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.BRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.BERRYBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.DATEBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.FIGBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.FRUITBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.HONEYBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.LEMONBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.ORANGEBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.PAPAYABRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.PEACHBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.PLUMBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.APPLEJACK, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.TEQUILA, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.RUM, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(TFCFluids.SHOCHU, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(FluidSetup.PONTIKKA, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));
        BarrelManager.getInstance().addRecipe((new BarrelFireRecipe((ItemStack)null, new FluidStack(FluidSetup.MELONBRANDY, 200), (ItemStack)null, new FluidStack(FluidSetup.ETHANOL, 75), 200)).setDistillationRecipe(true).setFireTicksScale(false).setSealTime(0).setSealedRecipe(true));

        //Liquor recipes
        if(Config.enableLiqueurs) {
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(FluidSetup.SUGARWATER, 4000), new FluidStack(FluidSetup.ETHANOL, 1000), new FluidStack(FluidSetup.LIQUORBASE, 5000)).setMinTechLevel(0).setSealedRecipe(false).setSealTime(0)));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.HONEYWATER, 4000), new FluidStack(FluidSetup.ETHANOL, 1000), new FluidStack(FluidSetup.LIQUORBASE, 5000)).setMinTechLevel(0).setSealedRecipe(false).setSealTime(0)));

            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.dandelionTop), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.DANDELIONLIQUOR, 5000))).setMinTechLevel(0));

            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.lemon), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.LEMONLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.orange), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.ORANGELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.redApple), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.APPLELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.greenApple), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.APPLELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.peach), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.PEACHLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.plum), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.PLUMLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cherry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.CHERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.papaya), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.PAPAYALIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.date), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.DATELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.coconutMeat), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.COCONUTLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.banana), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.BANANALIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.melonSlice), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.MELONLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.fig), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.FIGLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.grapes), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.GRAPELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.whiteGrapes), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.WHITEGRAPELIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.blueberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.BLUEBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.raspberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.RASPBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.strawberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.STRAWBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.blackberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.BLACKBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cranberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.CRANBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.wintergreenBerry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.WINTERGREENLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.bunchberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.BUNCHBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cloudberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.CLOUDBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.snowberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.SNOWBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.gooseberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.GOOSEBERRYLIQUOR, 5000))).setMinTechLevel(0));
            BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.elderberry), 80.0F), new FluidStack(FluidSetup.LIQUORBASE, 5000), (ItemStack) null, new FluidStack(FluidSetup.ELDERBERRYLIQUOR, 5000))).setMinTechLevel(0));
        }

        //Vinegar recipes
        if(Config.enableWhiteGrapes)
            BarrelManager.getInstance().addRecipe((new BarrelVinegarRecipe(new FluidStack(FluidSetup.WHITEWHINE, 100), new FluidStack(TFCFluids.VINEGAR, 100))).setMinTechLevel(0));
        BarrelManager.getInstance().addRecipe((new BarrelVinegarRecipe(new FluidStack(FluidSetup.KILJU, 100), new FluidStack(TFCFluids.VINEGAR, 100))).setMinTechLevel(0));

        //Muur's Kvass Addon Compat
        /*if(kvassLoaded){

            if(Config.enableLiqueurs)
                BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(ItemSetup.SYRUP, 4000), new FluidStack(FluidList.ETHANOL, 1000), new FluidStack(FluidList.LIQUORBASE, 5000)).setMinTechLevel(0).setSealedRecipe(false).setSealTime(0)));

            for(i=0;i<num;i++){

                Item m = yeastfoods[i];
                BarrelManager.getInstance().addRecipe((new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(m), 0.001F, true), new FluidStack(ItemSetup.SYRUP, 10000), (ItemStack)null, new FluidStack(FluidList.KILJU, 10000))).setMinTechLevel(0));

            }

            BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(ItemSetup.dyingkombucha, 1, 0), new FluidStack(FluidList.SUGARWATER, 500), new ItemStack(ItemSetup.kombucha, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 500))).setMinTechLevel(0).setSealedRecipe(true).setRemovesLiquid(true).setSealTime(60));
            BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(ItemSetup.kombucha, 1, 0), new FluidStack(FluidList.SUGARWATER, 2500), new ItemStack(ItemSetup.kombucha, 2, 0), new FluidStack(TFCFluids.FRESHWATER, 2500))).setMinTechLevel(0).setSealedRecipe(true).setRemovesLiquid(true).setSealTime(120));

            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.GRAPEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.GRAPEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.AGAVEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.AGAVEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.CANEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.CANEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.APPLEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.APPLEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.ORANGEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.ORANGEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.LEMONJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.LEMONMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.CHERRYJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.CHERRYMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.PLUMJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.PLUMMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.PEACHJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.PEACHMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.DATEJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.DATEMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.PAPAYAJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.PAPAYAMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.FIGJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.FIGMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
            BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.BERRYJUICE, 500), new FluidStack(FluidList.SUGARWATER, 500), new FluidStack(ItemSetup.BERRYMORS, 1000))).setSealTime(240).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));

            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.ryeGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.barleyGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cornmealGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.oatGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.riceGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
            BarrelManager.getInstance().addRecipe(new BarrelFireRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.wheatGround), 30.0F), new FluidStack(FluidList.SUGARWATER, 500), (ItemStack)null, new FluidStack(KISSEL, 500), 300, 500));
        }*/

    }
}
