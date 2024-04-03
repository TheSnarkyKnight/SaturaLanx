package com.snark.saturalanx.core;

import com.dunk.tfc.Core.FluidBaseTFC;
import com.snark.saturalanx.SaturaLanx;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidSetup {

    public static Fluid ETHANOL;

    public static Fluid DANDELIONTEA, DANDELIONCOFFEE, GOLDENRODTEA, DAISYTEA;

    public static Fluid SUGARWATER, KILJU, PONTIKKA;
    public static Fluid MELONJUICE, MELONWINE, MELONBRANDY;

    public static Fluid WHITEGRAPEJUICE, WHITEWHINE;

    public static Fluid LIQUORBASE;
    public static Fluid DANDELIONLIQUOR;
    public static Fluid LEMONLIQUOR, ORANGELIQUOR;
    public static Fluid APPLELIQUOR;
    public static Fluid PEACHLIQUOR, PLUMLIQUOR, CHERRYLIQUOR;
    public static Fluid PAPAYALIQUOR, DATELIQUOR, COCONUTLIQUOR, BANANALIQUOR, MELONLIQUOR;
    public static Fluid FIGLIQUOR, GRAPELIQUOR, WHITEGRAPELIQUOR;
    public static Fluid BLUEBERRYLIQUOR, RASPBERRYLIQUOR, STRAWBERRYLIQUOR, BLACKBERRYLIQUOR, CRANBERRYLIQUOR;
    public static Fluid WINTERGREENLIQUOR, BUNCHBERRYLIQUOR, CLOUDBERRYLIQUOR, GOOSEBERRYLIQUOR, SNOWBERRYLIQUOR, ELDERBERRYLIQUOR;
    public static Fluid SALTPETERFLUID, GUNPOWDERFLUID;

    public FluidSetup(){

    }

    public static void initializeFluids(){

        SaturaLanx.log.info("Initializing fluids...");

        //Teas
        DANDELIONTEA = (new FluidBaseTFC("dandeliontea")).setBaseColor(16777088);
        DANDELIONCOFFEE = (new FluidBaseTFC("dandelioncoffee")).setBaseColor(5059328);
        GOLDENRODTEA = (new FluidBaseTFC("goldenrodtea")).setBaseColor(16772235);
        DAISYTEA = (new FluidBaseTFC("daisytea")).setBaseColor(16772235);

        //Juices
        MELONJUICE = (new FluidBaseTFC("melonjuice")).setBaseColor(16724787);
        WHITEGRAPEJUICE = (new FluidBaseTFC("whitegrapejuice")).setBaseColor(15073184);

        //Wines
        KILJU = (new FluidBaseTFC("kilju")).setBaseColor(8434943);
        MELONWINE = (new FluidBaseTFC("melonwine")).setBaseColor(7156798);
        WHITEWHINE = (new FluidBaseTFC("whitewine")).setBaseColor(16777113);

        //Distillates
        PONTIKKA = (new FluidBaseTFC("pontikka")).setBaseColor(10079487);
        MELONBRANDY = (new FluidBaseTFC("melonbrandyr")).setBaseColor(16724787);

        //Misc
        SUGARWATER = (new FluidBaseTFC("sugarwater")).setBaseColor(3381759);
        ETHANOL = (new FluidBaseTFC("ethanol")).setBaseColor(15135487);

        //Liquors
        LIQUORBASE = (new FluidBaseTFC("liquorbase")).setBaseColor(11786751);

        DANDELIONLIQUOR = (new FluidBaseTFC("dandelionliquor")).setBaseColor(15794070);

        LEMONLIQUOR = (new FluidBaseTFC("lemonliquor")).setBaseColor(16645888);
        ORANGELIQUOR = (new FluidBaseTFC("orangeliquor")).setBaseColor(16621312);
        APPLELIQUOR = (new FluidBaseTFC("appleliquor")).setBaseColor(16252872);
        PEACHLIQUOR = (new FluidBaseTFC("peachliquor")).setBaseColor(16764057);
        PLUMLIQUOR = (new FluidBaseTFC("plumliquor")).setBaseColor(9916853);
        CHERRYLIQUOR = (new FluidBaseTFC("cherryliquor")).setBaseColor(9109504);
        PAPAYALIQUOR = (new FluidBaseTFC("papayaliquor")).setBaseColor(16777011);
        DATELIQUOR = (new FluidBaseTFC("dateliquor")).setBaseColor(3342344);
        COCONUTLIQUOR = (new FluidBaseTFC("coconutliquor")).setBaseColor(16777215);
        BANANALIQUOR = (new FluidBaseTFC("bananaliquor")).setBaseColor(14929751);
        MELONLIQUOR = (new FluidBaseTFC("melonliquor")).setBaseColor(16724787);
        FIGLIQUOR = (new FluidBaseTFC("figliquor")).setBaseColor(16724821);
        GRAPELIQUOR = (new FluidBaseTFC("grapeliquor")).setBaseColor(7286184);
        WHITEGRAPELIQUOR = (new FluidBaseTFC("whitegrapeliquor")).setBaseColor(11796326);
        BLUEBERRYLIQUOR = (new FluidBaseTFC("blueberryliquor")).setBaseColor(5211895);
        RASPBERRYLIQUOR = (new FluidBaseTFC("raspberryliquor")).setBaseColor(9961526);
        STRAWBERRYLIQUOR = (new FluidBaseTFC("strawberryliquor")).setBaseColor(16718144);
        BLACKBERRYLIQUOR = (new FluidBaseTFC("blackberryliquor")).setBaseColor(2752563);
        CRANBERRYLIQUOR = (new FluidBaseTFC("cranberryliquor")).setBaseColor(16711714);
        WINTERGREENLIQUOR = (new FluidBaseTFC("wintergreenliquor")).setBaseColor(16714280);
        BUNCHBERRYLIQUOR = (new FluidBaseTFC("bunchberryliquor")).setBaseColor(16721960);
        CLOUDBERRYLIQUOR = (new FluidBaseTFC("cloudberryliquor")).setBaseColor(16752675);
        GOOSEBERRYLIQUOR = (new FluidBaseTFC("gooseberryliquor")).setBaseColor(10544720);
        SNOWBERRYLIQUOR = (new FluidBaseTFC("snowberryliquor")).setBaseColor(16777190);
        ELDERBERRYLIQUOR = (new FluidBaseTFC("elderberryliquor")).setBaseColor(1114163);

        //Explosives
        SALTPETERFLUID = (new FluidBaseTFC("saltpeterfluid")).setBaseColor(14209218);
        GUNPOWDERFLUID = (new FluidBaseTFC("gunpowderfluid")).setBaseColor(4802889);

    }

    public static void registerFluids(){

        SaturaLanx.log.info("Registering fluids...");

        FluidRegistry.registerFluid(DANDELIONTEA);
        FluidRegistry.registerFluid(DANDELIONCOFFEE);
        FluidRegistry.registerFluid(GOLDENRODTEA);
        FluidRegistry.registerFluid(DAISYTEA);

        FluidRegistry.registerFluid(MELONJUICE);
        FluidRegistry.registerFluid(WHITEGRAPEJUICE);

        FluidRegistry.registerFluid(WHITEWHINE);
        FluidRegistry.registerFluid(MELONWINE);

        FluidRegistry.registerFluid(MELONBRANDY);

        FluidRegistry.registerFluid(SUGARWATER);
        FluidRegistry.registerFluid(KILJU);
        FluidRegistry.registerFluid(PONTIKKA);

        FluidRegistry.registerFluid(ETHANOL);

        FluidRegistry.registerFluid(LIQUORBASE);

        FluidRegistry.registerFluid(DANDELIONLIQUOR);

        FluidRegistry.registerFluid(LEMONLIQUOR);
        FluidRegistry.registerFluid(ORANGELIQUOR);
        FluidRegistry.registerFluid(APPLELIQUOR);
        FluidRegistry.registerFluid(PEACHLIQUOR);
        FluidRegistry.registerFluid(PLUMLIQUOR);
        FluidRegistry.registerFluid(CHERRYLIQUOR);
        FluidRegistry.registerFluid(PAPAYALIQUOR);
        FluidRegistry.registerFluid(DATELIQUOR);
        FluidRegistry.registerFluid(COCONUTLIQUOR);
        FluidRegistry.registerFluid(BANANALIQUOR);
        FluidRegistry.registerFluid(MELONLIQUOR);
        FluidRegistry.registerFluid(FIGLIQUOR);
        FluidRegistry.registerFluid(GRAPELIQUOR);
        FluidRegistry.registerFluid(WHITEGRAPELIQUOR);
        FluidRegistry.registerFluid(BLUEBERRYLIQUOR);
        FluidRegistry.registerFluid(RASPBERRYLIQUOR);
        FluidRegistry.registerFluid(STRAWBERRYLIQUOR);
        FluidRegistry.registerFluid(BLACKBERRYLIQUOR);
        FluidRegistry.registerFluid(CRANBERRYLIQUOR);
        FluidRegistry.registerFluid(WINTERGREENLIQUOR);
        FluidRegistry.registerFluid(BUNCHBERRYLIQUOR);
        FluidRegistry.registerFluid(CLOUDBERRYLIQUOR);
        FluidRegistry.registerFluid(GOOSEBERRYLIQUOR);
        FluidRegistry.registerFluid(SNOWBERRYLIQUOR);
        FluidRegistry.registerFluid(ELDERBERRYLIQUOR);

        FluidRegistry.registerFluid(GUNPOWDERFLUID);
        FluidRegistry.registerFluid(SALTPETERFLUID);
    }

    public static int getRGB(int r, int g, int b){
        return (65536 * r) + (256 * g) + b;
    }
}
