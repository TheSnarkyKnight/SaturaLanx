package com.snark.saturalanx.core;

import com.snark.saturalanx.SaturaLanx;
import net.minecraftforge.common.config.Configuration;

import java.io.File;


public class Config {

    public static Configuration config;

    //Food
    public static String FOOD_CATEGORY = "food";
    public static boolean enableDandelionFood = true;
    public static boolean enableGoldenrodFood = true;
    public static boolean enableNasturtiumFood = true;
    public static boolean enableCalendulaFood = true;
    public static boolean enableMilkweedFood = true;
    public static boolean enableDaisyFood = true;
    public static boolean enableTulipFood = true;
    public static boolean enablePoppyFood = true;
    public static boolean enableWhiteGrapes = true;

    //Drinks
    public static String DRINK_CATEGORY = "drinks";
    public static boolean enableLiqueurs = true;

    //Blocks
    public static String BUILDINGBLOCKS = "General Building Blocks";
    public static boolean enableColouredBricks = true;
    public static boolean enableMortaredCobble = true;
    public static boolean enableMortaredCobbleTextures = true;
    public static String GABION = "Gabion";
    public static boolean enableGabion = true;
    public static boolean enableGabionMultistage = true;
    public static boolean enableGabionOpenBottom = true;

    //Traps
    public static String SPIKES = "Spikes";
    public static boolean enableSpikes = true;
    public static int woodenSpikesBreakChance = 50;
    public static int woodenSpikesDamage = 20;
    public static int metalSpikesDamage = 40;
    public static boolean enableLeafCover = true;

    //Ranged weapons
    public static String BOLAS = "Bolas";
    public static boolean enableBolas = true;
    public static int bolasDamage = 100;
    public static int bolasMinSwing = 20;
    public static int bolasMaxSwing = 60;
    public static int bolasChance = 40;
    public static int bolasDuration = 600;
    public static int bolasDurationModifier = 300;
    public static int bolasAmplifier = 4;
    public static boolean bolasShouldAffectPlayers = true;

    //Melee weapons
    public static String EXTRAMACES = "Extra Maces";
    public static boolean enableExtraMaces = true;

    //Gunpowder
    public static String SLOWMATCH = "Slowmatch";
    public static int slowmatchLength = 6000;
    public static String HANDGONNE = "Handgonne";
    public static boolean enableHandgonne = true;
    public static int handgonneLoadingTime = 200;
    public static int handgonneAimingTime = 80;
    public static int handgonneDamage = 320;
    public static String ARQUEBUS = "Matchlock Arquebus";
    public static boolean enableArquebus = true;
    public static int arquebusLoadingTime = 200;
    public static int arquebusAimingTime = 20;
    public static int arquebusDamage = 450;

    public static String MBLUNDERBUSS = "Matchlock Blunderbuss";
    public static boolean enableMBlunderbuss = true;
    public static int mBlunderbussLoadingTime = 200;
    public static int mBlunderbussAimingTime = 20;
    public static int mBlunderbussDamage = 44;
    public static int mBlunderbussPellets = 10;

    public static String MHANDMORTAR = "Matchlock Hand Mortar";
    public static boolean enableMHandMortar = true;
    public static int mHandMortarLoadingTime = 400;
    public static int mHandMortarAimingTime = 40;
    public static int mHandMortarCannonballDamage = 1070;
    public static int mHandMortarBombPhysicalDamage = 530;
    public static int mHandMortarBombExplosionDamage = 16;

    //Incendiaries
    public static String INCENDIARYPOT = "Incendiary Pot";
    public static boolean enableIncendiaryPot = true;
    public static int incendiaryPotWickLength = 1200;
    public static int incendiaryPotDiameter = 3;
    public static int incendiaryPotDamageTimes = 6;
    public static int incendiaryPotBlockLife = 20;


    //Explosives
    public static String POTGRENADE = "Pot Grenades";
    public static boolean enablePotGrenades = true;
    public static int potGrenadeFuseLength = 500;
    public static int potGrenadePowerCap = 64;
    public static int potGrenadeMaxRange = 4;
    public static int potGrenadeMinRange = 1;
    //Pyrotechnics
    public static String PYROTECHNICS = "Pyrotechnics";
    public static boolean enableFirecrackers = true;
    public static boolean enableSmokeBombs = false;
    //Music
    public static String MUSIC = "Music";
    public static boolean enableCastanets = true;
    public static boolean enableRattle = true;
    public static boolean enableRainstick = true;
    public static boolean enablePanFlute = true;
    public static boolean enableSistrum = true;
    public static boolean enableBagpipe = true;


    public Config(){

    }

    public static void init(String configDir){

        if(config==null){
            File path = new File(configDir + "/" + SaturaLanx.MODID + ".cfg");

            config = new Configuration(path);
            loadConfig();
        }
    }

    public static void loadConfig(){

        //Food
        enableDandelionFood = config.getBoolean("Enable Dandelion Food",FOOD_CATEGORY,true,"Enables dandelion tops and dandelion roots, as well as related drinks.");
        enableGoldenrodFood = config.getBoolean("Enable Goldenrod Food",FOOD_CATEGORY,true,"Enables goldenrod tops and goldenrod leaves, as well as related drinks.");
        enableNasturtiumFood = config.getBoolean("Enable Nasturtium Food",FOOD_CATEGORY,true,"Enables prepared nasturtium.");
        enableMilkweedFood = config.getBoolean("Enable Milkweed Food",FOOD_CATEGORY,true,"Enables milkweed pods.");
        enableCalendulaFood = config.getBoolean("Enable Calendula Food",FOOD_CATEGORY,true,"Enables calendula petals.");
        enableTulipFood = config.getBoolean("Enable Tulip Food",FOOD_CATEGORY,true,"Enables tulip petals and bulbs.");
        enableDaisyFood = config.getBoolean("Enable Daisy Food",FOOD_CATEGORY,true,"Enables daisy tops, as well as related drinks.");
        enablePoppyFood = config.getBoolean("Enable Poppy Food",FOOD_CATEGORY,true,"Enables poppy seed.");

        enableWhiteGrapes = config.getBoolean("Enable White Grapes",FOOD_CATEGORY,true,"Enables white grapes.");

        //Drink
        enableLiqueurs = config.getBoolean("Enable Liqueurs",DRINK_CATEGORY,true,"Enables liqueurs.");

        //Blocks
        enableColouredBricks = config.getBoolean("Enable Coloured Bricks",BUILDINGBLOCKS,true,"Enables coloured bricks.");
        enableMortaredCobble = config.getBoolean("Enable Mortared Cobble",BUILDINGBLOCKS,true,"Enables mortared cobblestone.");
        enableMortaredCobbleTextures = config.getBoolean("Enable Custom Mortared Cobble Textures",BUILDINGBLOCKS,true,"Enables custom textures for mortared cobble. If disabled, it will use the same textures as normal cobble");

        enableGabion = config.getBoolean("Enable Gabion",GABION,true,"Enables gabion blocks.");
        enableGabionMultistage = config.getBoolean("Enable Gabion Multistage Destruction",GABION,true,"If true, gabion blocks turn into cobblestone when broken or exploded. If set to false, they are simply destroyed.");
        enableGabionOpenBottom = config.getBoolean("Enable Gabion Open Bottom",GABION,true,"Enables gabions having an open bottom, and spawning a falling cobble block if not placed on a solid block.");

        //Traps
        enableSpikes = config.getBoolean("Enable Spikes",SPIKES,true,"Enable spike blocks");
        woodenSpikesBreakChance = config.getInt("Wooden Spikes Break Chance",SPIKES,50,0,100,"0-100 chance for wooden spikes to break when an entity falls on them");
        woodenSpikesDamage = config.getInt("Wooden Spikes Damage",SPIKES,20,0,Integer.MAX_VALUE,"The damage dealt by wooden spikes");
        metalSpikesDamage = config.getInt("Metal Spikes Damage",SPIKES,40,0,Integer.MAX_VALUE,"The damage dealt by metal spikes");
        enableLeafCover = config.getBoolean("Enable Leaf Cover",SPIKES,true,"Enable leaf cover blocks");

        //Ranged weapons
        enableBolas = config.getBoolean("Enable Bolas",BOLAS,true,"Enables bolas.");
        bolasDamage = config.getInt("Bolas Damage",BOLAS,120,0,Integer.MAX_VALUE,"The amount of damage done by a bolas. Keep in mind that the actual damage equals this * 1.5");
        bolasMinSwing = config.getInt("Bolas Min Swing",BOLAS,20,0,72000,"The amount of time (in game ticks) that the player has to swing a bolas in order to launch it");
        bolasMaxSwing = config.getInt("Bolas Max Swing",BOLAS,60,0,72000,"The amount of time (in game ticks) that the player has to swing a bolas for it to reach max force.");
        bolasChance = config.getInt("Bolas Slowness Chance",BOLAS,40,0,100,"The chance the bolas has to apply the slowness effect. Actual chance is calculated as this value *");
        bolasDuration = config.getInt("Bolas Slowness Duration",BOLAS,600,0,Integer.MAX_VALUE,"The duration of the slowness effect applied by the bolas.");
        bolasDurationModifier = config.getInt("Bolas Slowness Duration Modifier",BOLAS,300,0,Integer.MAX_VALUE,"How much the duration of the slowness effect can vary.");
        bolasAmplifier = config.getInt("Bolas Slowness Amplifier",BOLAS,4,0,5,"The amplifier of the slowness effect applied by the bolas");
        bolasShouldAffectPlayers = config.getBoolean("Bolas Effects Players",BOLAS,true,"If false, players won't be subjected to the bolas' slowing effects");

        //Melee weapons
        enableExtraMaces = config.getBoolean("Enable Extra Maces",EXTRAMACES,true,"Enables stone clubs, lead and brass maces");

        //Firearms
        slowmatchLength = config.getInt("Slowmatch Burn Length",SLOWMATCH,6000,0,Integer.MAX_VALUE,"How long (in ticks) should slowmatch burn");

        enableHandgonne = config.getBoolean("Enable Handgonnes",HANDGONNE,true,"Enables handgonnes.");
        handgonneLoadingTime = config.getInt("Handgonne Loading Time",HANDGONNE,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the handgonne.");
        handgonneAimingTime = config.getInt("Handgonne Aiming Time",HANDGONNE,80,0,72000,"The amount of time (in game ticks) that the player needs to aim and fire the handgonne.");
        handgonneDamage = config.getInt("Handgonne Damage",HANDGONNE,320,1,Integer.MAX_VALUE,"The base damage dealt by handgonne bullets.");

        enableArquebus = config.getBoolean("Enable Arquebuses",ARQUEBUS,true,"Enables arquebuses.");
        arquebusLoadingTime = config.getInt("Arquebus Loading TIme",ARQUEBUS,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the arquebus.");
        arquebusAimingTime = config.getInt("Arquebus Aiming Time",ARQUEBUS,20,0,72000,"The amount of time (in game ticks) that the player needs to aim and fire the arquebus.");
        arquebusDamage = config.getInt("Arquebus Damage",ARQUEBUS,450,0,Integer.MAX_VALUE,"The base damage dealt by arquebus bullets.");

        enableMBlunderbuss = config.getBoolean("Enable Matchlock Blunderbusses",MBLUNDERBUSS,true,"Enables matchlock blunderbusses");
        mBlunderbussLoadingTime = config.getInt("Matchlock Blunderbuss Loading Time",MBLUNDERBUSS,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the matchlock blunderbuss");
        mBlunderbussAimingTime = config.getInt("Matchlock Blunderbuss Aiming Time",MBLUNDERBUSS,20,0,72000,"The amount of time (in game ticks) that the player needs to fire the matchlock blunderbuss");
        mBlunderbussDamage = config.getInt("Matchlock Blunderbuss Damage",MBLUNDERBUSS,44,0,Integer.MAX_VALUE,"The base damage dealt by the matchlock blunderbuss's pellet.");
        mBlunderbussPellets = config.getInt("Matchlock Blunderbuss Pellets",MBLUNDERBUSS,10,0,Integer.MAX_VALUE,"The amounts of pellets fired by the matchlock blunderbuss");

        //Incendiaries
        enableIncendiaryPot = config.getBoolean("Enable Incendiary Pots",INCENDIARYPOT,true,"Enables incendiary pots");
        incendiaryPotWickLength = config.getInt("Incendiary Pot Wick Length",INCENDIARYPOT,600,0,72000,"The amount of time (in game ticks) that the wick burns for.");
        incendiaryPotDiameter = config.getInt("Incendiary Pot Diameter",INCENDIARYPOT,3,0,10,"The diameter in blocks of the incediary pot's oil. If set to 0, it will spawn a cross shape with 1-long arms.");
        incendiaryPotDamageTimes = config.getInt("Incendiary Pot Damage Times",INCENDIARYPOT,6,1,Integer.MAX_VALUE,"How many times the fire inflicted by the flaming oil should damage entities.");
        incendiaryPotBlockLife = config.getInt("Incendiary Pot Block Life",INCENDIARYPOT,20,1,(Integer.MAX_VALUE)/20,"How long the flaming block spawned by incendiary pots stays in the world, expressed in seconds.");

        /*enableFiresticks = config.getBoolean("Enable Firesticks",FIRESTICK,true,"Enables firesticks.");
        firestickMeleeDamage = config.getInt("Firestick Melee Damage",FIRESTICK,50,0,Integer.MAX_VALUE,"Damage dealt by firesticks in melee");
        firestickBurnTime = config.getInt("Firestick Burn Time",FIRESTICK,600,0,Integer.MAX_VALUE,"The burn length (in ticks) of lit firesticks.");
        entityFirestickIgniteChance = config.getInt("Firestick Ignite Chance",FIRESTICK,20,0,100,"Base chance for firesticks to set the hit entity on fire.");
        entityFirestickFireDamageTimes = config.getInt("Firestick Fire Damage Times",FIRESTICK,3,0,Integer.MAX_VALUE,"How many times the fire inflicted by the firestick should damage entities");*/

        //Explosives
        enablePotGrenades = config.getBoolean("Enable Pot Grenades",POTGRENADE,true,"Enables pot grenades");
        potGrenadeFuseLength = config.getInt("Pot Grenade Fuse Lenght",POTGRENADE,300,0,Integer.MAX_VALUE,"Base fuse length for pot grenades");
        potGrenadePowerCap = config.getInt("Pot Grenade Power Cap",POTGRENADE,64,0,Integer.MAX_VALUE,"Hard cap for pot grenades' explosive power");
        potGrenadeMaxRange = config.getInt("Pot Grenade Max Range",POTGRENADE,4,0,Integer.MAX_VALUE,"The max radius of an explosion, expressed in blocks. Note that the actual range is equal to this*2.");
        potGrenadeMinRange = config.getInt("Pot Grenade Min Range",POTGRENADE,1,0,Integer.MAX_VALUE,"The min radius of an explosion, expressed in blocks. Note that the actual range is equal to this*2.");

        //Pyrotechnics
        enableFirecrackers = config.getBoolean("Enable Firecrackers",PYROTECHNICS,true,"Enables firecrackers");
        //enableSmokeBombs = config.getBoolean("Enable Smoke Bombs",PYROTECHNICS,false,"Enables smoke bombs (Currently do not work)");

        //Music
        enableCastanets = config.getBoolean("Enable Castanets",MUSIC,true,"Enables castanets");

        if(config.hasChanged())
            config.save();
    }

    public static Configuration getConfig(){
        return config;
    }
}
